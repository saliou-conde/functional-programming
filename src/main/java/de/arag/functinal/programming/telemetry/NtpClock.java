package de.arag.functinal.programming.telemetry;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Objects;

/**
 * The NtpClock does not implement the Serializable interface because the NtpTimeUpdater contains the non-serializable class NTPUDPClient.
 */
class NtpClock extends Clock {

    private final NtpTimeUpdater ntpTimeUpdater;

    private final ZoneId zone;

    NtpClock(ZoneId zone, NtpTimeUpdater ntpTimeUpdater) {
        this.zone = zone;
        this.ntpTimeUpdater = ntpTimeUpdater;
    }

    @Override
    public ZoneId getZone() {
        return zone;
    }

    @Override
    public Clock withZone(ZoneId zone) {
        if (this.zone.equals(zone)) {
            return this;
        }
        return new NtpClock(zone, ntpTimeUpdater);
    }

    @Override
    public long millis() {
        return Math.addExact(System.currentTimeMillis(), ntpTimeUpdater.getOffset());
    }

    @Override
    public Instant instant() {
        return Instant.ofEpochMilli(millis());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        NtpClock other = (NtpClock) obj;
        return Objects.equals(ntpTimeUpdater, other.ntpTimeUpdater) && Objects.equals(zone, other.zone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ntpTimeUpdater, zone);
    }

    @Override
    public String toString() {
        return "NtpClock[" + zone + ", " + ntpTimeUpdater.toString() + "]";
    }
}

