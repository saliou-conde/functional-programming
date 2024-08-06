package de.arag.functinal.programming.telemetry;
/*
 * Copyright © Schweizerische Bundesbahnen SBB, 2023.
 */

import org.apache.commons.net.ntp.NTPUDPClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UncheckedIOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.Clock;
import java.time.Duration;
import java.time.ZoneId;
import java.util.Objects;
import java.util.Timer;

/**
 * Factory for generating clocks. The following implementations are currently supported:
 * <ul>
 *   <li>java.time.Clock.SystemClock</li>
 *   <li>java.time.Clock.OffsetClock</li>
 *   <li>ch.sbb.tms.capaopt.techstack.time.NtpClock</li>
 * </ul>
 */
public final class ClockFactory {

    private static final Logger LOG = LoggerFactory.getLogger(ClockFactory.class);

    private ClockFactory() {
        // prevent instantiation, static methods only
    }

    /**
     * Creates a new Clock.SystemClock.
     *
     * @param zoneId {@link ZoneId}
     * @return java.time.Clock.SystemClock
     */
    public static Clock createSystemClock(ZoneId zoneId) {
        Clock clock = Clock.system(zoneId);
        LOG.info("Create new '{}' with actual time '{}'", clock, clock.instant());
        return clock;
    }

    /**
     * Creates a new Clock.OffsetClock. The underlying clock is a SystemClock with the given zoneId.
     *
     * @param zoneId         {@link ZoneId}
     * @param offsetDuration offset in ms
     * @return java.time.Clock.OffsetClock
     */
    public static Clock createOffsetClock(ZoneId zoneId, Duration offsetDuration) {
        Clock clock = Clock.offset(Clock.system(zoneId), offsetDuration);
        LOG.info("Create new '{}' with actual time '{}'", clock, clock.instant());
        return clock;
    }

    /**
     * Creates a new NtpClock. The method always creates a new NtpTimeUpdater including {@link Timer}. With many NtpClocks, this can
     * lead to excessive access to the respective NTP server. The user of this factory is therefore responsible for eventually
     * implementing a singleton pattern. The NtpClock does not implement the Serializable interface because the NtpTimeUpdater
     * contains the non-serializable class NTPUDPClient.
     *
     * @param zoneId            {@link ZoneId}
     * @param host              The host name of the NTP server.
     * @param port              The port number of the NTP server (usually 123).
     * @param connectionTimeout The duration to use for the NTP server socket connection timeout.
     * @param refreshInterval   The duration between consecutive NTP time access task executions.
     * @throws NullPointerException     if any of the parameters should be null.
     * @throws IllegalArgumentException if port number is out of range.
     * @throws UncheckedIOException     containing a UnknownHostException, if no IP address for the host could be found, or if a scope_id
     *                                  was specified for a global IPv6 address.
     */
    public static Clock createNtpClock(ZoneId zoneId, String host, Integer port, Duration connectionTimeout, Duration refreshInterval) {
        Objects.requireNonNull(zoneId, "zoneId");
        Objects.requireNonNull(host, "host");
        Objects.requireNonNull(port, "port");
        Objects.requireNonNull(connectionTimeout, "connectionTimeout");
        Objects.requireNonNull(refreshInterval, "refreshInterval");
        verifyPortRange(port);

        NTPUDPClient client = new NTPUDPClient();
        client.setDefaultTimeout(getConnectionTimeoutMillis(connectionTimeout));

        NtpTimeUpdater ntpTimeUpdater = new NtpTimeUpdater(getInetAddress(host), port, client, refreshInterval.abs());
        ntpTimeUpdater.startScheduling();

        Clock clock = new NtpClock(zoneId, ntpTimeUpdater);
        LOG.info("Create new '{}' with actual time '{}'", clock, clock.instant());
        return clock;
    }

    static void verifyPortRange(Integer port) {
        if (port < 0 || port > 0xFFFF) {
            throw new IllegalArgumentException("Port out of range: " + port);
        }
    }

    static int getConnectionTimeoutMillis(Duration connectionTimeout) {
        long millis = connectionTimeout.abs().toMillis();
        if (millis > Integer.MAX_VALUE) {
            LOG.warn("NTP connection timeout {}ms exceeds the value of Integer.MAX_VALUE", millis);
            return Integer.MAX_VALUE;
        }
        return (int) millis;
    }

    static InetAddress getInetAddress(String host) {
        try {
            return InetAddress.getByName(host);
        }
        catch (UnknownHostException e) {
            throw new UncheckedIOException(e);
        }
    }
}

