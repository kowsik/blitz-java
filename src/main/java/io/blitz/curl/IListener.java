package io.blitz.curl;

/**
 * Blitz curl listener. Describes the listener which will be called when a test
 * event occurs 
 * @author ghermeto
 */
public interface IListener<Result> {
    
    /**
     * Will be called when the client return successful data. If returns false
     * it will send an abort request to blitz.
     * @param result
     * @return false if the current job should be aborted.
     */
    boolean onData(Result result);
}
