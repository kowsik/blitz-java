package io.blitz.curl.config;

import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author ghermeto
 */
public class Pattern {
    
    private Integer interactions;

    private Collection<Interval> intervals;

    public Pattern() {
        intervals = new ArrayList<Interval>();
    }
    
    public Pattern(Collection<Interval> intervals) {
        this.intervals = intervals;
    }
    
    public Pattern(Integer interactions, Collection<Interval> intervals) {
        this.interactions = interactions;
        this.intervals = intervals;
    }

    public Integer getInteractions() {
        return interactions;
    }

    public void setInteractions(Integer interactions) {
        this.interactions = interactions;
    }

    public Collection<Interval> getIntervals() {
        return intervals;
    }

    public void setIntervals(Collection<Interval> intervals) {
        this.intervals = intervals;
    }
}
