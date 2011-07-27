package io.blitz.curl.config;

/**
 *
 * @author ghermeto
 */
public class Interval {

    private Integer interactions;
    
    private Integer start;
    
    private Integer end;
    
    private Integer duration;

    public Interval() {
    }
    
    public Interval(Integer start, Integer end, Integer duration) {
        this.start = start;
        this.end = end;
        this.duration = duration;
    }
    
    public Interval(Integer interactions, Integer start, Integer end, Integer duration) {
        this.interactions = interactions;
        this.start = start;
        this.end = end;
        this.duration = duration;
    }
    
    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getEnd() {
        return end;
    }

    public void setEnd(Integer end) {
        this.end = end;
    }

    public Integer getInteractions() {
        return interactions;
    }

    public void setInteractions(Integer interactions) {
        this.interactions = interactions;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }
}
