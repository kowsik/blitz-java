package io.blitz.curl;

import io.blitz.curl.exception.ValidationException;
import io.blitz.curl.sprint.ISprintListener;
import io.blitz.curl.sprint.Request;
import io.blitz.curl.sprint.Response;
import io.blitz.curl.sprint.SprintResult;
import java.util.Map;

/**
 * Used to generate a Sprint Test.
 * <p> 
 * Sprinting is a simple HTTP (or SSL) request to a page in your app or 
 * your RESTful API.
 * </p>
 * <pre>
 * Sprint sprint = new Sprint("you@example.com", "my-bitz-api-key");
 * sprint.setUrl(new java.net.URL("http://your.cool.app"));
 * sprint.addListener(new ISprintListener() {
 *      onError(ErroResult error) {
 *          // do something...
 *      }
 *      onSuccess(SprintResult result) {
 *          // do something...
 *      }
 * });
 * sprint.execute();
 * </pre>
 * @author ghermeto
 * @version 0.1.0
 * @see {@link io.blitz.curl.AbstractTest AbstractTest}
 */
public class Sprint extends AbstractTest<ISprintListener, SprintResult> {

    public Sprint(String username, String apiKey) {
        setCredentials(username, apiKey);
    }

    public Sprint(String username, String apiKey, String host, Integer port) {
        setCredentials(username, apiKey, host, port);
    }

    /**
     * Verifies the Sprint requirements. Should throw a <code>ValidationException</code>
     * if the URL field is not set.
     * @throws ValidationException 
     */
    @Override
    public void checkRequirements() throws ValidationException {
        if(getUrl() == null) {
            throw new ValidationException("Url is required");
        }
    }

    /**
     * Should return a <code>SprintResult</code> object populated with the 
     * successful response from the server.
     * @param result the deserialized result from the JSON response
     * @return a successful sprint result object
     * @see SprintResult
     */
    protected SprintResult createSuccessResult(Map<String, Object> result) {
        
        String region = (String) result.get("region");
        Number duration = (Number) result.get("duration");
        Number connect = (Number) result.get("connect");
        //assemble the request object
        Request request = null;
        if(result.containsKey("request")) {
            Map<String, Object> req = (Map<String, Object>) result.get("request"); 
            String line = (String) req.get("line");
            String method = (String) req.get("method");
            String url = (String) req.get("url");
            String content = (String) req.get("content");
            Map<String, Object> headers = (req.containsKey("headers")) ?
                    (Map<String, Object>) req.get("headers") : null;
            
            request = new Request(line, method, url, headers, content);
        }
        //assemble the response object
        Response response = null;
        if(result.containsKey("response")) {
            Map<String, Object> res = (Map<String, Object>) result.get("response"); 
            String line = (String) res.get("line");
            Number status = (Number) res.get("status");
            String message = (String) res.get("message");
            String content = (String) res.get("content");
            Map<String, Object> headers = (res.containsKey("headers")) ?
                    (Map<String, Object>) res.get("headers") : null;
            
            response = new Response(line, (status!=null) ? status.intValue() : null, 
                    message, headers, content);
        }
        Double durationDbl = (duration != null) ? duration.doubleValue() : null;
        Double connectDbl = (connect != null) ? connect.doubleValue() : null;
                
        return new SprintResult(region, durationDbl, connectDbl, request, response);
    }
}
