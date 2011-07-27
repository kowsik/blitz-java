package io.blitz.curl.sprint;

/**
 * Contains the result from a successful sprint.
 * @author ghermeto
 */
public class SprintResult {
    
    /**
     * The region from which this sprint was executed
     */
    private String region;
    
    /**
     * The overall response time for the successful hit
     */
    private Double duration;
    
    /**
     * The time it took for the TCP connection
     */
    private Double connect;
    
    /**
     * The request object containing the URL, headers and content, if any
     */
    private Request request;
    
    /**
     * The response object containing the status code, headers and content, if any
     */
    private Response response;

    public SprintResult(String region, Double duration, Double connect, 
            Request request, Response response) {
        this.region = region;
        this.duration = duration;
        this.connect = connect;
        this.request = request;
        this.response = response;
    }

    public Double getConnect() {
        return connect;
    }

    public Double getDuration() {
        return duration;
    }

    public String getRegion() {
        return region;
    }

    public Request getRequest() {
        return request;
    }

    public Response getResponse() {
        return response;
    }
}
