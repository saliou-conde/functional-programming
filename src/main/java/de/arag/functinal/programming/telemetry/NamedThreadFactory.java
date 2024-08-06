/*
 * Copyright © Schweizerische Bundesbahnen SBB, 2022.
 */

package de.arag.functinal.programming.telemetry;

import lombok.extern.slf4j.Slf4j;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

@Slf4j
public class NamedThreadFactory implements ThreadFactory {

    private final String prefix;

    private final ThreadFactory delegate;
    private final UncaughtExceptionHandler uncaughtExceptionHandler;
    private int counter;

    public NamedThreadFactory(final String name) {
        this.prefix = name + "-"; //$NON-NLS-1$
        this.delegate = Executors.defaultThreadFactory();
        this.uncaughtExceptionHandler = (t, e) -> log.error("Exception while executing thread. ", e);
        this.counter = 0;
    }

    @Override
    public Thread newThread(final Runnable r) {
        final Thread t = delegate.newThread(r);
        t.setName(prefix + ++counter);
        t.setUncaughtExceptionHandler(uncaughtExceptionHandler);
        return t;
    }

}
