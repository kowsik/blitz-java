package io.blitz.curl;

/**
 * Used to notify the listeners about errors returned by the server.
 * @author ghermeto
 */
public class ErrorResult {
    
    private String error;
    
    private String reason;

    public ErrorResult() {
    }

    public ErrorResult(String error, String reason) {
        this.error = error;
        this.reason = reason;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
