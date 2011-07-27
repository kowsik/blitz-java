package io.blitz.curl.exception;

/**
 * Exceptions thrown when a validation error occur during a test execution
 * @author ghermeto
 */
public class ValidationException extends BlitzException {

    /**
     * Constructs an instance of <code>ValidationException</code> with the 
     * specified error and reason message.
     * @param reason the detailed error message.
     */
    public ValidationException(String reason) {
        super("validation", reason);
    }
}
