package de.arag.functinal.programming.telemetry;
/*
 * Copyright © Schweizerische Bundesbahnen SBB, 2023.
 */

import org.apache.commons.net.ntp.NTPUDPClient;
import org.apache.commons.net.ntp.TimeInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetAddress;
import java.time.Duration;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

class NtpTimeUpdater implements Runnable {

    private static final Logger LOG = LoggerFactory.getLogger(NtpTimeUpdater.class);

    private final InetAddress host;
    private final int port;
    private final NTPUDPClient client;
    private final ScheduledExecutorService executorService;
    private final CountDownLatch countDownLatch;
    private final Duration refreshInterval;
    private volatile long offset = 0;

    NtpTimeUpdater(InetAddress host, int port, NTPUDPClient client, Duration refreshInterval) {
        this.host = host;
        this.port = port;
        this.client = client;
        this.refreshInterval = refreshInterval;
        this.countDownLatch = new CountDownLatch(1);
        this.executorService = new SingleThreadScheduledThreadPoolExecutor(runnable -> {
            Thread thread = Executors.defaultThreadFactory().newThread(runnable);
            thread.setDaemon(true);
            return thread;
        });
    }

    static final class SingleThreadScheduledThreadPoolExecutor extends ScheduledThreadPoolExecutor {

        SingleThreadScheduledThreadPoolExecutor(ThreadFactory threadFactory) {
            super(1, threadFactory);
        }

        @Override
        protected void afterExecute(Runnable runnable, Throwable throwable) {
            super.afterExecute(runnable, throwable);
            if (throwable == null && runnable instanceof Future<?> future) {
                try {
                    if (future.isDone()) {
                        future.get();
                    }
                }
                catch (ExecutionException ee) {
                    throwable = ee.getCause();
                }
                catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            }
            if (throwable != null) {
                LOG.error("Unrecoverable error happened. NtpTimeUpdater will be stopped.", throwable);
            }
        }
    }

    void startScheduling() {
        this.executorService.scheduleAtFixedRate(this, 0, refreshInterval.toMillis(), TimeUnit.MILLISECONDS);
        try {
            // Waiting prevents the clock from having an incorrect time after initialization until the first NTP query has been made.
            // Since the CountDownLatch is set to 1, this only happens once.
            this.countDownLatch.await();
        }
        catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Fetches the {@link TimeInfo} from the NTP server and updates the time offset.
     */
    @Override
    public void run() {
        try {
            LOG.info("NTP open connection {}:{}", host.getHostName(), port);
            client.open();
            TimeInfo timeInfo = client.getTime(host, port);
            timeInfo.computeDetails();
            if (!timeInfo.getComments().isEmpty()) {
                LOG.warn("NTP offset is not calculated regularly: {}", timeInfo.getComments());
            }
            offset = timeInfo.getOffset();
            LOG.info("NTP offset: {}ms ({}), delay: {}ms", offset, Duration.ofMillis(offset), timeInfo.getDelay());
        }
        catch (IOException e) {
            LOG.error("Unable to retrieve time from {}:{}", host.getHostName(), port, e);
        }
        finally {
            countDownLatch.countDown();
            client.close();
            LOG.info("NTP close connection");
        }
    }

    public long getOffset() {
        return offset;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        NtpTimeUpdater other = (NtpTimeUpdater) obj;
        return port == other.port && Objects.equals(host, other.host);
    }

    @Override
    public int hashCode() {
        return Objects.hash(host, port);
    }

    @Override
    public String toString() {
        return host.getHostName() + ":" + port;
    }
}

