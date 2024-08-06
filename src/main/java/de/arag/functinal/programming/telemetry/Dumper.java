/*
 * Project: RCS - Rail Control System
 *
 * © Copyright by SBB AG, Alle Rechte vorbehalten
 */
package de.arag.functinal.programming.telemetry;

import io.micrometer.core.instrument.util.NamedThreadFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import jakarta.annotation.PreDestroy;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Schreibt Strings in ein File ohne zu blockieren und ohne den Filehandle offen zu lassen. <br>
 */
@Slf4j
@Service
public class Dumper {
    private static final int FLUSH_DELAY_MS = 10000;

    private final Object writeLock = new Object();
    private Object future;
    private final int timeoutInMillis;
    private final DefaultDelayedWriter delayedWriter;
    private final ScheduledExecutorService scheduledExecutorService;
    private final Runnable shutDownAction;

    public Dumper(final DefaultDelayedWriter delayedWriter) {
        this.delayedWriter = delayedWriter;
        this.timeoutInMillis = FLUSH_DELAY_MS;
        this.scheduledExecutorService = Executors.newScheduledThreadPool(1, new NamedThreadFactory("Dumper"));
        shutDownAction = scheduledExecutorService::shutdown;

    }

    public void dump(final String text) {
        delayedWriter.addToBuffer(text);
        triggerWrite(timeoutInMillis);
    }

    private void triggerWrite(final int timeoutInMillis) {
        synchronized (writeLock) {
            if (future == null) {
                future = scheduledExecutorService.schedule(Dumper.this::write, timeoutInMillis, TimeUnit.MILLISECONDS);
            }
        }
    }

    private void write() {
        try {
            delayedWriter.write();
        }
        catch (IOException e) {
            log.info("Fehler beim schreiben der Daten", e);
        }
        finally {
            synchronized (writeLock) {
                future = null;
            }
        }
    }

    @PreDestroy
    public void close() {
        if (shutDownAction != null) {
            shutDownAction.run();
        }
        write();
    }
}