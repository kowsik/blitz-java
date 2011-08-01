package io.blitz.curl.rush;

import io.blitz.curl.IListener;

/**
 * Listener for the Rush test.
 * <pre>
 * IRushListener listener = new IRushListener() {
 *      booolean onData(RushResult result) {
 *          //do something...
 *      }
 * };
 * </pre>
 * @author ghermeto
 * @see io.blitz.curl.IListener
 */
public interface IRushListener extends IListener<RushResult> {
    
}
