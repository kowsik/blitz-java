package io.blitz.curl;

/**
 * Blitz curl listener. Describes the listener which will be called when a test
 * event occurs 
 * @author ghermeto
 */
public interface IListener<Result> {
    
    /**
     * Will be called when the client returns an error result
     * @param result 
     */
    void onError(ErrorResult result);
    
    /**
     * Will be called when the client return successful data
     * @param result 
     */
    void onSuccess(Result result);
}
