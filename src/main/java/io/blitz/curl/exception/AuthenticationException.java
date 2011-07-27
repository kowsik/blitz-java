package io.blitz.curl.exception;

/**
 * Exceptions thrown when there is a problem with the client authentication 
 * against the server.
 * @author ghermeto
 */
public class AuthenticationException extends BlitzException {

    /**
     * Constructs an instance of <code>AuthenticationException</code> with the 
     * specified error and reason message.
     * @param reason the detailed error message.
     */
    public AuthenticationException(String reason) {
        super("login", reason);
    }

    /**
     * Constructs an instance of <code>AuthenticationException</code> with the 
     * specified error and reason message.
     * @param error the error key
     * @param reason the detailed error message.
     */
    public AuthenticationException(String error, String reason) {
        super(error, reason);
    }
}
