package io.blitz.curl.rush;

import io.blitz.curl.Rush;
import java.util.Collection;

/**
 * Represents the results returned by the rush. Contains the entire timeline
 * of snapshot values from the rush as well as the region from which the
 * rush was executed.
 * @author ghermeto
 * @see Point
 * @see Rush
 */
public class RushResult {

    /**
     * The region from which this sprint was executed
     */
    private String region;
    
    /**
     * timeline of snapshot values from the rush
     */
    private Collection<Point> timeline;

    public RushResult(String region, Collection<Point> timeline) {
        this.region = region;
        this.timeline = timeline;
    }

    public String getRegion() {
        return region;
    }

    public Collection<Point> getTimeline() {
        return timeline;
    }
}
