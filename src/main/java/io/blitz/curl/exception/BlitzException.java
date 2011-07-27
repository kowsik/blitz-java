package io.blitz.curl.exception;

/**
 * Exceptions thrown when an error is returned
 * @author ghermeto
 */
public class BlitzException extends RuntimeException {

    private String reason;
    private String error;
    
    /**
     * Creates a new instance of <code>ValidationException</code> without 
     * a error key and reason messages.
     */
    public BlitzException() {
    }

    /**
     * Constructs an instance of <code>ValidationException</code> with the 
     * specified error key and reason message.
     * @param error the error key.
     * @param reason the detailed error message.
     */
    public BlitzException(String error, String reason) {
        super(reason);
        this.error = error;
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
