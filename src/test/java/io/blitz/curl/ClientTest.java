package io.blitz.curl;

import io.blitz.curl.config.BasicAuthentication;
import io.blitz.curl.config.Content;
import io.blitz.curl.config.HttpHeader;
import io.blitz.curl.config.variable.AlphaVariable;
import io.blitz.curl.config.variable.IVariable;
import io.blitz.curl.config.variable.ListVariable;
import io.blitz.curl.config.variable.NumberVariable;
import io.blitz.mock.MockURLStreamHandler;
import java.io.ByteArrayOutputStream;
import java.net.HttpCookie;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLStreamHandlerFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 * @author ghermeto
 */
public class ClientTest {
    
    private static MockURLStreamHandler handler;
    private static URLStreamHandlerFactory factory;

    @BeforeClass
    public static void mockSetup() {
        handler = CurlTestSuite.getHandler();
        factory = CurlTestSuite.getFactory();
        try{ URL.setURLStreamHandlerFactory(factory); } catch(Error e) {}
    }

    @Before
    public void resetMock() {
        handler.getConnection().setHeaders(new HashMap<String, String>());
        handler.getConnection().setOutput(new ByteArrayOutputStream());
        handler.getConnection().setUseMapped(false);
    }
    
    @Test
    public void successfulLogin() {
        //create a expected response data
        String responseData = "{\"ok\":true, \"api_key\":\"123\"}";
        handler.getConnection().setResponseData(responseData);
        
        Client client = new Client("user", "apiKey", "localhost", 9295);
        Map<String, Object> response = client.login();
        
        assertNotNull(response);
        assertNotNull(response.get("ok"));
        assertEquals(response.get("ok").getClass(), Boolean.class);
        assertTrue((Boolean)response.get("ok"));
        assertNotNull(response.get("api_key"));
        assertEquals(response.get("api_key"), "123");
        
        assertEquals(handler.getConnection().getHeaders().get("X-API-User"), "user");
        assertEquals(handler.getConnection().getHeaders().get("X-API-Key"), "apiKey");
        
        assertTrue(client.isAuthenticated());
    }
    
    @Test
    public void failedLogin() {
        //create a expected response data
        String responseData = "{\"error\":\"login\", \"reason\":\"test\"}";
        handler.getConnection().setResponseData(responseData);
        
        Client client = new Client("user", "apiKey", "localhost", 9295);
        Map<String, Object> response = client.login();
        
        assertNotNull(response);
        assertNotNull(response.get("error"));
        assertEquals(response.get("error"), "login");
        assertNotNull(response.get("reason"));
        assertEquals(response.get("reason"), "test");

        assertFalse(client.isAuthenticated());
    }
    
    @Test
    public void successfulExecute() {
        try {
            //create a expected response data
            String responseData = "{\"ok\":true, \"status\":\"queued\", "
                    + "\"region\":\"california\", \"job_id\":\"a123\"}";
            handler.getConnection().setResponseData(responseData);
            
            Client client = new Client("user", "apiKey", "localhost", 9295);
            TestEntity test = new TestEntity();
            test.setUrl(new URL("http://www.example.com"));
            
            Map<String, Object> response = client.execute(test);
            
            assertNotNull(response);
            assertNotNull(response.get("ok"));
            assertEquals(response.get("ok").getClass(), Boolean.class);
            assertTrue((Boolean)response.get("ok"));
            assertNotNull(response.get("job_id"));
            assertEquals(response.get("job_id"), "a123");
            assertNotNull(response.get("status"));
            assertEquals(response.get("status"), "queued");
            assertNotNull(response.get("region"));
            assertEquals(response.get("region"), "california");
            
            String output = handler.getConnection().getOutputStreamAsString("UTF-8");
            assertEquals(output, "{\"url\":\"http://www.example.com\"}");
            

        } catch (MalformedURLException ex) {
            assertFalse(true);
        }
    }

    @Test
    public void successfulComplexExecute() {
        try {
            //create a expected response data
            String responseData = "{\"ok\":true, \"status\":\"queued\", "
                    + "\"region\":\"california\", \"job_id\":\"a123\"}";
            handler.getConnection().setResponseData(responseData);
            
            Client client = new Client("user", "apiKey", "localhost", 9295);
            
            //creates a complex JSON string
            TestEntity test = new TestEntity();
            test.setUrl(new URL("http://www.example.com"));
            test.setRegion("ireland");
            test.setUserAgent("java-api");
            test.setStatus(200);
            Collection<HttpHeader> headers = new ArrayList<HttpHeader>();
            headers.add(new HttpHeader("a","b"));
            headers.add(new HttpHeader("c","d"));
            test.setHeaders(headers);
            Collection<HttpCookie> cookies = new ArrayList<HttpCookie>();
            cookies.add(new HttpCookie("c1","v1"));
            cookies.add(new HttpCookie("c2","v2"));
            test.setCookies(cookies);
            Collection<String> data = new ArrayList<String>();
            data.add("test-data");
            test.setContent(new Content(data));
            test.setUser(new BasicAuthentication("user", "pass"));
            
            Map<String, Object> response = client.execute(test);
            
            assertNotNull(response);
            assertNotNull(response.get("ok"));
            assertEquals(response.get("ok").getClass(), Boolean.class);
            assertTrue((Boolean)response.get("ok"));
            assertNotNull(response.get("job_id"));
            assertEquals(response.get("job_id"), "a123");
            assertNotNull(response.get("status"));
            assertEquals(response.get("status"), "queued");
            assertNotNull(response.get("region"));
            assertEquals(response.get("region"), "california");
            
            String output = handler.getConnection().getOutputStreamAsString("UTF-8");
            String expected = "{\"url\":\"http://www.example.com\",\"region\":\"ireland\","
                    + "\"user-agent\":\"java-api\",\"status\":200,"
                    + "\"cookies\":[\"c1=v1\",\"c2=v2\"],\"headers\":[\"a:b\",\"c:d\"],"
                    + "\"user\":\"user:pass\",\"content\":{\"data\":[\"test-data\"]}}";
            
            assertEquals(expected, output);

        } catch (MalformedURLException ex) {
            assertFalse(true);
        }
    }
    
    @Test
    public void successfulExecuteWithVariables() {
        try {
            //create a expected response data
            String responseData = "{\"ok\":true, \"status\":\"queued\", "
                    + "\"region\":\"california\", \"job_id\":\"a123\"}";
            handler.getConnection().setResponseData(responseData);
            
            Client client = new Client("user", "apiKey", "localhost", 9295);
            
            //creates a complex JSON string
            TestEntity test = new TestEntity();
            test.setUrl(new URL("http://www.example.com"));
            test.setRegion("ireland");
            test.setUserAgent("java-api");
            test.setStatus(200);
            Map<String, IVariable> map = new HashMap<String, IVariable>();
            map.put("foo", new AlphaVariable(1, 5));
            map.put("bar", new NumberVariable(1, 5));
            String[] aVars = {"a","b"};
            Collection<String> vars = Arrays.asList(aVars);
            map.put("tar", new ListVariable(vars));
            test.setVariables(map);
            
            Map<String, Object> response = client.execute(test);
            
            assertNotNull(response);
            assertNotNull(response.get("ok"));
            assertEquals(response.get("ok").getClass(), Boolean.class);
            assertTrue((Boolean)response.get("ok"));
            assertNotNull(response.get("job_id"));
            assertEquals(response.get("job_id"), "a123");
            assertNotNull(response.get("status"));
            assertEquals(response.get("status"), "queued");
            assertNotNull(response.get("region"));
            assertEquals(response.get("region"), "california");
            
            String output = handler.getConnection().getOutputStreamAsString("US-ASCII");
            String expected = "{\"url\":\"http://www.example.com\",\"region\":\"ireland\","
                    + "\"user-agent\":\"java-api\",\"status\":200,"
                    + "\"variables\":{\"tar\":{\"entries\":[\"a\",\"b\"],\"type\":\"list\"},"
                    + "\"foo\":{\"min\":1,\"max\":5,\"type\":\"alpha\"},"
                    + "\"bar\":{\"min\":1,\"max\":5,\"type\":\"number\"}}}";
            
            assertEquals(expected, output);

        } catch (MalformedURLException ex) {
            assertFalse(true);
        }
    }
    
    @Test
    public void successfulSprintJobStatus() {
        //create a expected response data
        String responseData = "{\"_id\":\"a123\",\"ok\":true,"
                + "\"result\":{\"region\":\"california\",\"duration\":10,\"connect\":1,"
                + "\"request\":{\"line\":\"GET / HTTP/1.1\",\"method\":\"GET\","
                + "\"url\":\"http://localhost:9295\",\"headers\":{},\"content\":\"abc\"},"
                + "\"response\":{\"line\":\"GET / HTTP/1.1\",\"message\":\"message\","
                + "\"status\":200,\"headers\":{},\"content\":\"abd\"}}}";
        handler.getConnection().setResponseData(responseData);
        
        Client client = new Client("user", "apiKey", "localhost", 9295);
        Map<String, Object> response = client.getJobStatus("a123");
        
        assertNotNull(response);
        assertNotNull(response.get("ok"));
        assertEquals(response.get("ok").getClass(), Boolean.class);
        assertTrue((Boolean)response.get("ok"));
        assertNotNull(response.get("_id"));
        assertEquals(response.get("_id"), "a123");
        assertNotNull(response.get("result"));
        assertEquals(response.get("result").getClass(), HashMap.class);

        Map<String, Object> result = (Map) response.get("result");
        assertNotNull(result.get("region"));
        assertEquals(result.get("region"), "california");
        assertNotNull(result.get("request"));
        assertEquals(result.get("request").getClass(), HashMap.class);
        assertNotNull(result.get("response"));
        assertEquals(result.get("response").getClass(), HashMap.class);
    }

    @Test
    public void successfulRushJobStatus() {
        //create a expected response data
        String responseData = "{\"_id\":\"c123\",\"ok\":true,"
                + "\"result\":{\"region\":\"california\",\"timeline\":["
                + "{\"duration\":1,\"total\":10,\"executed\":8,\"errors\":1,"
                + "\"timeouts\":1,\"volume\":10},"
                + "{\"duration\":2,\"total\":100,\"executed\":80,\"errors\":10,"
                + "\"timeouts\":10,\"volume\":100}"
                + "]}}";
        handler.getConnection().setResponseData(responseData);
        
        Client client = new Client("user", "apiKey", "localhost", 9295);
        Map<String, Object> response = client.getJobStatus("c123");
        
        assertNotNull(response);
        assertNotNull(response.get("ok"));
        assertEquals(response.get("ok").getClass(), Boolean.class);
        assertTrue((Boolean)response.get("ok"));
        assertNotNull(response.get("_id"));
        assertEquals(response.get("_id"), "c123");
        assertNotNull(response.get("result"));
        assertEquals(response.get("result").getClass(), HashMap.class);
        Map<String, Object> result = (Map) response.get("result");
        assertNotNull(result.get("region"));
        assertEquals(result.get("region"), "california");
        assertNotNull(result.get("timeline"));
        assertEquals(result.get("timeline").getClass(), ArrayList.class);
    }

    @Test
    public void successfulAbort() {
        //create a expected response data
        String responseData = "{\"_id\":\"c123\",\"ok\":true}";
        handler.getConnection().setResponseData(responseData);
        
        Client client = new Client("user", "apiKey", "localhost", 9295);
        Map<String, Object> response = client.abort("c123");
        
        assertNotNull(response);
        assertNotNull(response.get("ok"));
        assertEquals(response.get("ok").getClass(), Boolean.class);
        assertTrue((Boolean)response.get("ok"));
        assertNotNull(response.get("_id"));
        assertEquals(response.get("_id"), "c123");
    }
}
