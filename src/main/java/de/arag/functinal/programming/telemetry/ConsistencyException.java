package de.arag.functinal.programming.telemetry;


import org.apache.commons.lang3.StringUtils;

/**
 * A consistency exception is an unchecked runtime exception that may be thrown in cases of violated invariants assumed by the programmer.
 * <p>
 * Such an exception means that something is broken within the system that must be fixed by the development team. Usually it is a component
 * that is not used/called the way the original programmer intended it to be used. This also means that the user of a productive system
 * should <b><i>NEVER</i></b> that exception class.
 * <p>
 * Consistency exceptions are used quite similar as assert-statements. The difference is that asserts are not checked in a production
 * system, only during development. Therefore,
 * <ul>
 * <li>Use asserts as pre- and post-conditions and invariants of operations
 * <li>Use asserts for checks that will be executed in development/tests
 * <li>Use consistency checks for conditions that cannot be tested
 * <li>Use consistency exception to signal potentially caught failures/exceptions
 * </ul>
 *
 */
public class ConsistencyException extends RuntimeException {

    /**
     * Default Serial ID
     */
    private static final long serialVersionUID = 1L;

    protected String errorCode = StringUtils.EMPTY;

    public ConsistencyException(final String message) {
        super(message);
    }

    public ConsistencyException(final Throwable cause) {
        super(cause);
    }

    public ConsistencyException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public ConsistencyException(final String message, final String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public ConsistencyException(final String message, final Throwable cause, final String errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return this.errorCode;
    }
}

