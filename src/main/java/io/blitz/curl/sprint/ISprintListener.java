package io.blitz.curl.sprint;

import io.blitz.curl.IListener;

/**
 * Listener for the Sprint test.
 * <pre>
 * ISprintListener listener = new ISprintListener() {
 *      booolean onData(SprintResult result) {
 *          //do something...
 *      }
 * };
 * </pre>
 * @author ghermeto
 * @see io.blitz.curl.IListener
 */
public interface ISprintListener extends IListener<SprintResult> {
}
