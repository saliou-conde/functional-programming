package de.arag.functinal.programming.telemetry;


import java.io.Serializable;
import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

import static org.apache.commons.lang3.StringUtils.EMPTY;

/**
 * Wrapper class used in ALEA to add specific functionality. Subclassing is not possible because ZonedDateTime is final. Add undefined to
 * the underlying ZonedDateTime data type.<br>
 * <b>Note: Default static clock is the SystemClock. If some other clock is needed, the application has to change the clock via {@link #setClock(Clock)} when initializing the application.</b>
 */
public class ADateTime implements Comparable<ADateTime>, Serializable {

    private static final long serialVersionUID = 1L;
    private static final String DATE_FORMAT = "dd.MM.yyyy"; //$NON-NLS-1$
    private static final String DATE_WITHOUT_YEAR_FORMAT = "dd.MM."; //$NON-NLS-1$
    private static final String TIME_FORMAT = "HH:mm"; //$NON-NLS-1$
    private static final String DATE_TIME_FORMAT = "dd.MM.yyyy HH:mm"; //$NON-NLS-1$
    private static final String TIME_DATE_FORMAT = "HH:mm dd.MM.yyyy"; //$NON-NLS-1$
    private static final String DATE_TIME_YEAR2DIGIT_FORMAT = "dd.MM.yy HH:mm"; //$NON-NLS-1$
    private static final String YEAR_FIRST_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm"; //$NON-NLS-1$
    public static final String DATE_TIME_FORMAT_SECONDS = "dd.MM.yyyy HH:mm:ss"; //$NON-NLS-1$

    private static final ZoneId EUROPE_ZURICH = ZoneId.of("Europe/Zurich");
    public static final ZoneId DEFAULT_ZONE_ID = EUROPE_ZURICH;

    private final ZonedDateTime dateTime;

    private static Clock clock = ClockFactory.createSystemClock(DEFAULT_ZONE_ID);

    /**
     * static ADateTime instance used to indicate that an ADateTime instance is undefined
     */
    public static final ADateTime UNDEFINED = new ADateTime(0);

    public static final ADateTime MAX_DATETIME = new ADateTime(9999, 12, 31, 0, 0, 0, 0);

    /**
     * Create a {@link ADateTime} object for given input parameter.
     *
     * @param dateTime the DateTime instance to be wrapped
     * @return the created {@link ADateTime} object or null
     */
    public static ADateTime createADateTimeNullSafe(final ZonedDateTime dateTime) {
        if (Objects.nonNull(dateTime)) {
            return new ADateTime(dateTime);
        }
        else {
            return null;
        }
    }

    public ADateTime() {
        this(currentTimeMillis());
    }

    public ADateTime(final LocalDateTime dateTime) {
        this(dateTime.atZone(DEFAULT_ZONE_ID));
    }

    public ADateTime(final Date date) {
        this(date.toInstant());
    }

    public ADateTime(final ZonedDateTime dateTime) {
        if (dateTime == null) {
            throw new IllegalArgumentException("ADateTime should not be initialized with null ZonedDateTime");
        }
        this.dateTime = dateTime;
    }

    public ADateTime(final long milis) {
        this(Instant.ofEpochMilli(milis));
    }

    public ADateTime(final Instant instant) {
        this.dateTime = ZonedDateTime.ofInstant(instant, DEFAULT_ZONE_ID);
    }

    public static long currentTimeMillis() {
        return clock.millis();
    }

    public static ZonedDateTime millis2DateTime(final long millis) {
        return ZonedDateTime.ofInstant(Instant.ofEpochMilli(millis), DEFAULT_ZONE_ID);
    }

    public ADateTime(final int year, final int monthOfYear, final int dayOfMonth, final int hourOfDay, final int minuteOfHour,
            final int secondOfMinute, final int millisOfSecond) {
        this(ZonedDateTime.of(year, monthOfYear, dayOfMonth, hourOfDay, minuteOfHour, secondOfMinute, millisOfSecond * 1_000_000,
                DEFAULT_ZONE_ID));
    }

    public ADateTime(final Calendar calendar) {
        if (calendar == null) {
            throw new IllegalArgumentException("ADateTime should not be initialized with null Calendar"); //$NON-NLS-1$
        }
        this.dateTime = millis2DateTime(calendar.getTimeInMillis());
    }

    /**
     * Constructor used to merge two DateTimes into one by using the date (day, month, year) from one DateTime and the time from the other
     * one.
     *
     * @param date the day date to use
     * @param time the time to use
     */
    public ADateTime(final ADateTime date, final ADateTime time) {
        final LocalDate lDate = LocalDate.from(date.getDateTime());
        final LocalTime lTime = LocalTime.from(time.getDateTime());
        this.dateTime = ZonedDateTime.of(lDate, lTime, DEFAULT_ZONE_ID);
    }

    public Calendar toCalendar(final Locale locale) {
        Locale loc = locale;
        if (loc == null) {
            loc = Locale.getDefault();
        }

        final Calendar cal = Calendar.getInstance(loc);
        cal.setTimeInMillis(dateTime.toInstant().toEpochMilli());

        return cal;
    }

    public static long daysBetween(final ADateTime begin, final ADateTime end) {
        return ChronoUnit.DAYS.between(begin.dateTime, end.dateTime);
    }

    /**
     * Returns whether the underlying DateTime contains a defined date
     *
     * @return is internal DateTime undefined or not
     */
    public boolean isUndefined() {
        return this.equals(UNDEFINED);
    }

    /**
     * Returns the whether the underlying DateTime contains a defined date
     *
     * @return internal DateTime defined or not
     */
    public boolean isDefined() {
        return !this.isUndefined();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((dateTime == null) ? 0 : dateTime.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ADateTime other = (ADateTime) obj;
        return dateTime.isEqual(other.dateTime);
    }

    /**
     * Test if time is same by using 1sec tolerance.
     *
     * @param other The time to test
     * @return TRUE if times are the same.
     */
    public boolean sameTime(final ADateTime other) {
        if (other == null) {
            return false;
        }

        return (Math.abs(this.getMillis() - other.getMillis()) / 1000) < 1;
    }

    /**
     * Test if time is same by comparing the format <code>DATE_TIME_FORMAT</code>.
     *
     * @param other The time to test
     * @return TRUE if times are the same.
     */
    public boolean sameMinute(final ADateTime other) {
        if (other == null) {
            return false;
        }

        return Objects.equals(this.getAsDateTimeString(), other.getAsDateTimeString());
    }

    /**
     * Add a ADuration to this DateTime.
     *
     * @param duration ADuration that should be added to this ADateTime
     * @return new instance of a ADateTime
     */
    public ADateTime plus(final ADuration duration) {
        return new ADateTime(this.dateTime.plus(duration.getMillis(), ChronoUnit.MILLIS));
    }

    /**
     * Add a Duration to this DateTime.
     *
     * @param duration Duration that should be added to this ADateTime
     * @return new instance of a ADateTime
     */
    public ADateTime plus(final Duration duration) {
        return new ADateTime(this.dateTime.plus(duration));
    }

    /**
     * Add a number of years to this DateTime.
     *
     * @param years the number of years to add
     * @return new instance of a ADateTime
     */
    public ADateTime plusYears(final int years) {
        return new ADateTime(this.dateTime.plusYears(years));
    }

    /**
     * Add a number of months to this DateTime.
     *
     * @param months the number of months to add
     * @return new instance of a ADateTime
     */
    public ADateTime plusMonths(final int months) {
        return new ADateTime(this.dateTime.plusMonths(months));
    }

    /**
     * Add a number of days from this DateTime.
     *
     * @param days the number of days to add
     * @return new instance of a ADateTime
     */
    public ADateTime plusDays(final int days) {
        return new ADateTime(this.dateTime.plusDays(days));
    }

    /**
     * Add a number of hours from this DateTime.
     *
     * @param hours the number of hours to add
     * @return new instance of a ADateTime
     */
    public ADateTime plusHours(final int hours) {
        return new ADateTime(this.dateTime.plusHours(hours));
    }

    /**
     * Add a number of minutes to this DateTime.
     *
     * @param minutes the number of minutes to add
     * @return new instance of a ADateTime
     */
    public ADateTime plusMinutes(final int minutes) {
        return new ADateTime(this.dateTime.plusMinutes(minutes));
    }

    /**
     * Subtract a ADuration to this DateTime.
     *
     * @param duration ADuration that should be added to this ADateTime
     * @return new instance of a ADateTime
     */
    public ADateTime minus(final ADuration duration) {
        return new ADateTime(this.dateTime.minus(duration.getMillis(), ChronoUnit.MILLIS));
    }

    /**
     * Subtract a Duration to this DateTime.
     *
     * @param duration Duration that should be added to this ADateTime
     * @return new instance of a ADateTime
     */
    public ADateTime minus(final Duration duration) {
        return new ADateTime(this.dateTime.minus(duration));
    }

    /**
     * Subtract a number of months from this DateTime.
     *
     * @param months the number of months to subtract
     * @return new instance of a ADateTime
     */
    public ADateTime minusMonths(final int months) {
        return new ADateTime(this.dateTime.minusMonths(months));
    }

    /**
     * Subtract a number of years from this DateTime.
     *
     * @param years the number of months to subtract
     * @return new instance of a ADateTime
     */
    public ADateTime minusYears(final int years) {
        return new ADateTime(this.dateTime.minusYears(years));
    }

    /**
     * Subtract a number of days from this DateTime.
     *
     * @param days the number of days to subtract
     * @return new instance of a ADateTime
     */
    public ADateTime minusDays(final int days) {
        return new ADateTime(this.dateTime.minusDays(days));
    }

    /**
     * Subtract a number of hours from this DateTime.
     *
     * @param hours the number of hours to subtract
     * @return new instance of a ADateTime
     */
    public ADateTime minusHours(final int hours) {
        return new ADateTime(this.dateTime.minusHours(hours));
    }

    /**
     * Subtract a number of minutes to this DateTime.
     *
     * @param minutes the number of minutes to subtract
     * @return new instance of a ADateTime
     */
    public ADateTime minusMinutes(final int minutes) {
        return new ADateTime(this.dateTime.minusMinutes(minutes));
    }

    public static ADateTime getFirstDayOfThisMonth() {
        return new ADateTime(ZonedDateTime.now(clock).withDayOfMonth(1));
    }

    public static ADateTime getFirstDayOfLastMonth() {
        return new ADateTime(ZonedDateTime.now(clock).minusMonths(1).withDayOfMonth(1));
    }

    /**
     * Returns milliseconds from 1.1.1970
     *
     * @return milliseconds from 1.1.1970
     */
    public long getMillis() {
        return dateTime.toInstant().toEpochMilli();
    }

    public int getDayOfYear() {
        return dateTime.getDayOfYear();
    }

    public int getDayOfMonth() {
        return dateTime.getDayOfMonth();
    }

    public int getMonthOfYear() {
        return dateTime.getMonth().getValue();
    }

    public int getYear() {
        return dateTime.getYear();
    }

    public int getHourOfDay() {
        return dateTime.getHour();
    }

    public int getMinuteOfHour() {
        return dateTime.getMinute();
    }

    public int getSecondOfMinute() {
        return dateTime.getSecond();
    }

    public int getMillisOfSecond() {
        return dateTime.getNano() / 1_000_000;
    }

    public int getCenturyOfEra() {
        return dateTime.get(ChronoField.YEAR_OF_ERA) / 100;
    }

    /**
     * Tests whether the given {@link ZonedDateTime} occurs today
     *
     * @return <code>true</code> if the given ZonedDateTime occurs today
     */
    public boolean today() {
        final ZonedDateTime now = ZonedDateTime.now(clock);
        return (dateTime.getYear() == now.getYear() && dateTime.getMonth() == now.getMonth()
                && dateTime.getDayOfMonth() == now.getDayOfMonth());
    }

    /**
     * Format as dd.MM.yyyy format
     *
     * @return string formatted using dd.MM.yyyy format
     */
    public String getAsDateString() {
        return toString(DATE_FORMAT);
    }

    /**
     * Format string as "dd.MM.".
     *
     * @return the formatted string
     */
    public String getAsDateWithoutYearString() {
        return toString(DATE_WITHOUT_YEAR_FORMAT);
    }

    /**
     * Format as HH:mm format
     *
     * @return string formatted using HH:mm format
     */
    public String getAsTimeString() {
        return toString(TIME_FORMAT);
    }

    /**
     * Format as dd.MM.yyyy HH:mm format
     *
     * @return string formatted using dd.MM.yyyy HH:mm format
     */
    public String getAsDateTimeString() {
        return toString(DATE_TIME_FORMAT);
    }

    /**
     * Format as HH:mm dd.MM.yyyy format
     *
     * @return string formatted using TIME_DATE_FORMAT format
     */
    public String getAsTimeDateString() {
        return toString(TIME_DATE_FORMAT);
    }

    /**
     * Format as dd.MM.yy HH:mm format
     *
     * @return string formatted using dd.MM.yy HH:mm format
     */
    public String getAsDateTimeYear2DigitString() {
        return toString(DATE_TIME_YEAR2DIGIT_FORMAT);
    }

    /**
     * Format as yyyy-MM-dd HH:mm format
     *
     * @return string formatted using yyyy-MM-dd HH:mm format
     */
    public String getAsYearFirstDateTimeString() {
        return toString(YEAR_FIRST_DATE_TIME_FORMAT);
    }

    @Override
    public String toString() {
        return getAsDateTimeString();
    }

    /**
     * return datetime formatted with format provided
     *
     * @param format string to be used for formatting
     * @return datetime formatted as string
     */
    public String toString(final String format) {
        if (isUndefined()) {
            return EMPTY;
        }
        else {
            return AleaDateTimeFormatter.of(format).format(this);
        }
    }

    /**
     * return datetime formatted with DateTimeFormatter provided
     *
     * @param dateTimeFormat DateTimeFormatter to be used for formatting
     * @return datetime formatted as string
     */
    public String toString(final DateTimeFormatter dateTimeFormat) {
        if (isUndefined()) {
            return EMPTY;
        }
        else {
            return dateTimeFormat.format(dateTime);
        }
    }

    /**
     * compare instance with another ADateTime instance
     *
     * @param dateTimeToCompare instance to compare with
     * @return return type provided by compareTo method of internal datetime instance
     */
    @Override
    public int compareTo(final ADateTime dateTimeToCompare) {
        final int result = this.dateTime.compareTo(dateTimeToCompare.dateTime);
        //if the 2 dates don't differ on seconds level, it will return the difference in nanoseconds instead of {-1 , 0, 1}
        return (result == 0) ? 0 : (result < 0) ? -1 : 1;
    }

    /**
     * returns internal DateTime instance
     *
     * @return internal DateTime instance
     */
    public ZonedDateTime getDateTime() {
        return dateTime;
    }

    public Date getDate() {
        return Date.from(dateTime.toInstant());
    }

    public boolean isEqual(final ADateTime dt) {
        return dateTime.isEqual(dt.dateTime);
    }

    public boolean isAfter(final ADateTime dt) {
        return dateTime.isAfter(dt.getDateTime());
    }

    public boolean isAfterNow() {
        return dateTime.isAfter(ZonedDateTime.now(clock));
    }

    public boolean isAfterToday() {
        return dateTime.toLocalDate().isAfter(ZonedDateTime.now(clock).toLocalDate());
    }

    public boolean isBefore(final ADateTime dt) {
        return dateTime.isBefore(dt.getDateTime());
    }

    public boolean isBeforeNow() {
        return dateTime.isBefore(ZonedDateTime.now(clock));
    }

    public boolean isBeforeToday() {
        return dateTime.toLocalDate().isBefore(ZonedDateTime.now(clock).toLocalDate());
    }

    public boolean isToday() {
        return dateTime.toLocalDate().equals(ZonedDateTime.now(clock).toLocalDate());
    }

    public boolean isBeforeDate(final ADateTime dt) {
        return dateTime.toLocalDate().isBefore(dt.dateTime.toLocalDate());
    }

    public boolean isAfterDate(final ADateTime dt) {
        return dateTime.toLocalDate().isAfter(dt.dateTime.toLocalDate());
    }

    public static boolean isSameDate(final ADateTime first, final ADateTime second) {
        return first.dateTime.toLocalDate().equals(second.dateTime.toLocalDate());
    }

    /**
     * Truncates the time portion of an ADateTime
     *
     * @return ADateTime without time information
     */
    public ADateTime truncateTime() {
        if (isUndefined()) {
            return UNDEFINED;
        }
        return new ADateTime(dateTime.truncatedTo(ChronoUnit.DAYS));
    }

    public boolean hasNoTime() {
        return dateTime.getHour() == 0 && dateTime.getMinute() == 0 && dateTime.getSecond() == 0;
    }

    /**
     * Tests for undefined state or a long value <code>0</code>
     *
     * @param aDateTime may be <code>null</code>
     * @return <code>true</code> if the given ADateTime is undefined
     */
    public static boolean isUndefined(final ADateTime aDateTime) {
        if (aDateTime == null) {
            return true;
        }
        if (aDateTime.equals(UNDEFINED)) {
            return true;
        }
        if (aDateTime.getMillis() == 0) {
            return true;
        }
        return false;
    }

    /**
     * Tests for defined datetime
     *
     * @param aDateTime may be <code>null</code>
     * @return <code>true</code> if the given ADateTime is defined
     */
    public static boolean isDefined(final ADateTime aDateTime) {
        return !isUndefined(aDateTime);
    }

    public static ADateTime getUndefinedIfNull(final ADateTime dateTime) {
        return dateTime != null ? dateTime : ADateTime.UNDEFINED;
    }

    /**
     * Get the time information for today, time and date information for the past or the future.
     *
     * @param aDateTime may be <code>null</code>
     * @return the formatted time or time and time information
     */
    public static String getAsTimeTodayOrElseTimeDate(final ADateTime aDateTime) {
        if (Objects.nonNull(aDateTime)) {
            if (aDateTime.today()) {
                return aDateTime.getAsTimeString();
            }
            else {
                return aDateTime.getAsTimeDateString();
            }
        }
        else {
            return EMPTY;
        }
    }

    public final ADateTime withYear(final int year) {
        return new ADateTime(dateTime.withYear(year));
    }

    public final ADateTime withTime(final LocalTime time) {
        return withTime(dateTime.toLocalDate(), time);
    }

    public final ADateTime withTime(final LocalDate date, final LocalTime time) {
        return new ADateTime(ZonedDateTime.of(date, time, DEFAULT_ZONE_ID));
    }

    public static Clock getClock() {
        return clock;
    }

    /**
     * Sets the internal Clock. This is needed when configuring some other clock than the default SystemClock.
     */
    public static void setClock(final Clock newClock) {
        clock = newClock;
    }
}
