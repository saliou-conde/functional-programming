package de.arag.functinal.programming.telemetry;
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//


import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAccessor;

public final class AleaDateTimeFormatter {
    public static final AleaDateTimeFormatter ISO_PARSER_FORMATTER;
    public static final AleaDateTimeFormatter ISO_PARSER_FORMATTER_OFFSET;
    public static final AleaDateTimeFormatter ISO_FORMATTER;
    public static final AleaDateTimeFormatter ISO_DATE_FORMATTER;
    public static final AleaDateTimeFormatter ISO_NO_MILLIS_FORMATTER;
    public static final AleaDateTimeFormatter FULL_DATE_TIME;
    private final DateTimeFormatter formatter;

    private AleaDateTimeFormatter(DateTimeFormatter formatter) {
        this.formatter = formatter;
    }

    public static AleaDateTimeFormatter of(String format) {
        return new AleaDateTimeFormatter(DateTimeFormatter.ofPattern(format));
    }

    public ADateTime parseDateTime(String text) {
        TemporalAccessor ta = this.formatter.parseBest(text, LocalDateTime::from, LocalDate::from, LocalTime::from);
        if (ta instanceof LocalDateTime time) {
            return ADateTime.createADateTimeNullSafe(time.atZone(ZoneId.systemDefault()));
        } else if (ta instanceof LocalDate date) {
            return ADateTime.createADateTimeNullSafe(ZonedDateTime.of(date, LocalTime.MIDNIGHT, ZoneId.systemDefault()));
        } else if (ta instanceof LocalTime time) {
            return ADateTime.createADateTimeNullSafe(ZonedDateTime.of(LocalDate.now(ADateTime.getClock()), time, ZoneId.systemDefault()));
        } else {
            throw new DateTimeParseException("Cannot parse text: ", text, 0);
        }
    }

    public String format(ADateTime dateTime) {
        return dateTime.getDateTime().format(this.formatter);
    }

    public String format(LocalTime time) {
        return time.format(this.formatter);
    }

    public String format(long timestamp) {
        return Instant.ofEpochMilli(timestamp).atZone(ADateTime.DEFAULT_ZONE_ID).format(this.formatter);
    }

    public String toString() {
        return this.formatter.toString();
    }

    static {
        ISO_PARSER_FORMATTER = new AleaDateTimeFormatter(DateTimeFormatter.ISO_DATE_TIME);
        ISO_PARSER_FORMATTER_OFFSET = new AleaDateTimeFormatter(DateTimeFormatter.ISO_DATE_TIME.withZone(ADateTime.DEFAULT_ZONE_ID));
        ISO_FORMATTER = new AleaDateTimeFormatter(DateTimeFormatter.ISO_DATE_TIME);
        ISO_DATE_FORMATTER = new AleaDateTimeFormatter(DateTimeFormatter.ISO_DATE);
        ISO_NO_MILLIS_FORMATTER = new AleaDateTimeFormatter(DateTimeFormatter.ISO_DATE_TIME);
        FULL_DATE_TIME = new AleaDateTimeFormatter(DateTimeFormatter.ISO_DATE_TIME);
    }
}

