package de.arag.functinal.programming.telemetry;

import java.util.concurrent.TimeUnit;

import java.time.Duration;
import java.time.Instant;


/**
 * Base type encapsulating the org.java.time.Duration class
 */
public class ADuration {

    public static final long MILLIS_PER_DAY = 24 * 3600 * 1000;
    public static final long MILLIS_PER_HOUR = 3600 * 1000;
    public static final long MILLIS_PER_MINUTE = 60 * 1000;
    public static final long MILLIS_PER_SECOND = 1000;

    private final Duration duration;

    /**
     * constructor
     *
     * @param duration
     *          value to be set. Interpreted differently based on unit
     * @param unit
     *          time unit to be applied for duration value
     */
    public ADuration(final long duration, final TimeUnit unit) {
        this.duration = Duration.ofMillis(toMillis(duration, unit));
    }

    /**
     * constructor
     *
     * @param duration
     *          value to be set. Interpreted differently based on unit
     * @param unit
     *          time unit to be applied for duration value
     */
    public ADuration(final double duration, final TimeUnit unit) {
        this.duration = Duration.ofMillis(toMillis(duration, unit));
    }

    /**
     * copy constructor
     *
     * @param duration
     *          duration used to initialize new instance with
     */
    public ADuration(final Duration duration) {
        this.duration = duration;
    }

    public ADuration(final long duration) {
        this.duration = Duration.ofMillis(duration);
    }

    // for JUnit Tests only
    public ADuration() {
        this.duration = Duration.ofSeconds(0);
    }

    public ADuration(final long millis1, final long millis2) {
        this.duration = Duration.between(Instant.ofEpochMilli(millis1), Instant.ofEpochMilli(millis2));
    }

    public static ADuration ofStandardDays(final long days) {
        return new ADuration(Duration.ofDays(days));
    }

    private long toMillis(final double durationToConvert, final TimeUnit unit) {
        switch (unit) {
            case DAYS:
                return (long) (durationToConvert * MILLIS_PER_DAY);
            case HOURS:
                return (long) (durationToConvert * MILLIS_PER_HOUR);
            case MINUTES:
                return (long) (durationToConvert * MILLIS_PER_MINUTE);
            case SECONDS:
                return (long) (durationToConvert * MILLIS_PER_SECOND);
            case MILLISECONDS:
                return (long) (durationToConvert);
            default:
                throw new ConsistencyException("Not implemented."); //$NON-NLS-1$
        }
    }

    /**
     * get duration as milli seconds
     *
     * @return milliseconds of duration
     */
    public long getMillis() {
        return duration.toMillis();
    }

    /**
     * get duration in seconds
     *
     * @return seconds of duration
     */
    public long getAsSeconds() {
        return duration.toMillis() / MILLIS_PER_SECOND; //duration.getSeconds() - in case of negative values the value can differ with 1 sec
    }

    /**
     * get duration in minutes
     *
     * @return minutes of duration
     */
    public long getAsMinutes() {
        return duration.toMillis() / MILLIS_PER_MINUTE; //duration.toMinutes()
    }

    /**
     * get duration in hours
     *
     * @return hours of duration
     */
    public long getAsHours() {
        return duration.toMillis() / MILLIS_PER_HOUR; //duration.toHours()
    }

    /**
     * get duration in days
     *
     * @return days of duration
     */
    public long getAsDays() {
        return duration.toMillis() / MILLIS_PER_DAY; //duration.toDays()
    }

    /**
     * returns internal DateTime instance
     *
     * @return internal DateTime instance
     */
    public Duration getDuration() {
        return duration;
    }

    /**
     * Hashcode
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((duration == null) ? 0 : duration.hashCode());
        return result;
    }

    /**
     * Equals
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        final ADuration other = (ADuration) obj;
        if (duration == null) {
            if (other.duration != null) {
                return false;
            }
        }
        else if (!duration.equals(other.duration)) {
            return false;
        }
        return true;
    }
}
