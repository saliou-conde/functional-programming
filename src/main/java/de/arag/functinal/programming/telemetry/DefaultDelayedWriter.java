/*
 * Project: RCS - Rail Control System
 *
 * © Copyright by SBB AG, Alle Rechte vorbehalten
 */
package de.arag.functinal.programming.telemetry;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Schreibt Strings in ein File ohne zu blockieren und ohne den File handle offen zu lassen.
 */
public class DefaultDelayedWriter {
    private String serverName;
    private final List<String> buffer = new ArrayList<>();
    private final Path resolvedPath;

    public DefaultDelayedWriter(Path path) {
        this.resolvedPath = path.resolve(getFileName(LocalDateTime.now()));
    }

    public void addToBuffer(final String text) {
        synchronized (buffer) {
            buffer.add(text);
        }
    }

    public void write() throws IOException {
        List<String> copy;
        synchronized (buffer) {
            copy = new ArrayList<>(buffer);
            buffer.clear();
        }
        if (resolvedPath != null) {
            final Path path = Paths.get(resolvedPath.toFile().getAbsolutePath());
            if (path.toFile().exists() || path.getParent().toFile().exists() || path.getParent().toFile().mkdirs()) {
                Files.write(path, copy, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            }
        }
    }

    private String getFileName(final LocalDateTime date) {
        final String dateStr = date.toString();
        return String.format("alea-telemetry_%s_%s.csv", dateStr, getServerName());
    }

    private String getServerName() {
        if (serverName == null) {
            try {
                InetAddress addr = InetAddress.getLocalHost();
                serverName = addr.getCanonicalHostName();
            }
            catch (UnknownHostException e) {
                //Falls kein Hostname gefunden wird, random reingeben.
                serverName = "SERVER_" + Long.toHexString(Double.doubleToLongBits(Math.random()));
            }
        }
        return serverName;
    }
}