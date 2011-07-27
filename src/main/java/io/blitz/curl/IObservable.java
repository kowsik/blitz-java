package io.blitz.curl;

/**
 * Simple interface to describe a observable object
 * @author ghermeto
 */
public interface IObservable<Listener> {
    
    /**
     * Adds a listener to be notified when a event happens
     * @param listner listener to be notified
     */
    void addListener(Listener listner);
    
    /**
     * Removes the listener from the list and stop getting notified on event changes
     * @param listner listener to be removed
     */
    void removeListener(Listener listner);
}
