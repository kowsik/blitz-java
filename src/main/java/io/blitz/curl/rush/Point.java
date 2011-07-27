package io.blitz.curl.rush;

import java.util.Date;

/**
 * Snapshot of a rush at time[i] containing information about hits, errors
 * timeouts, etc.
 * @author ghermeto
 */
public class Point {
    
    /**
     * The timestamp of this snapshot
     */
    private Date timestamp;
    
    /**
     * The average response time at this time
     */
    private Double duration;
    
    /**
     * The total number of hits that were generated
     */
    private Integer total;
    
    /**
     * The number of successful hits
     */
    private Integer hits;
    
    /**
     * The number of errors
     */
    private Integer errors;
    
    /**
     * The number of timeouts
     */
    private Integer timeouts;
    
    /**
     * The concurrency level at this time
     */
    private Integer volume;
    
    /**
     * The total number of bytes sent
     */
    private Integer txBytes;
    
    /**
     * The total number of bytes received
     */
    private Integer rxBytes;

    public Point(Date timestamp, Double duration, Integer total, Integer hits, 
            Integer errors, Integer timeouts, Integer volume, Integer txBytes, Integer rxBytes) {
        this.timestamp = timestamp;
        this.duration = duration;
        this.total = total;
        this.hits = hits;
        this.errors = errors;
        this.timeouts = timeouts;
        this.volume = volume;
        this.txBytes = txBytes;
        this.rxBytes = rxBytes;
    }

    public Double getDuration() {
        return duration;
    }

    public Integer getErrors() {
        return errors;
    }

    public Integer getHits() {
        return hits;
    }

    public Integer getRxBytes() {
        return rxBytes;
    }

    public Integer getTimeouts() {
        return timeouts;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public Integer getTotal() {
        return total;
    }

    public Integer getTxBytes() {
        return txBytes;
    }

    public Integer getVolume() {
        return volume;
    }
}
