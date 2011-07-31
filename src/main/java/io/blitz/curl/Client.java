package io.blitz.curl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import io.blitz.curl.config.BasicAuthentication;
import io.blitz.curl.config.HttpHeader;
import io.blitz.curl.exception.BlitzException;
import io.blitz.gson.ArrayDeserializer;
import io.blitz.gson.BasicAuthenticationSerializer;
import io.blitz.gson.HttpCookieSerializer;
import io.blitz.gson.HttpHeaderSerializer;
import io.blitz.gson.MapDeserializer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Modifier;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownServiceException;
import java.util.Collection;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ghermeto
 */
public class Client {
    
    /**
     * Username. Usually it is your registered email address.
     */
    private String user;
    
    /**
     * Authentication key avialable on your blitz.io account
     */
    private String apiKey;
    
    /**
     * Server host (no protocol).
     */
    private String host;
    
    /**
     * Server port
     */
    private int port;
    
    /**
     * Api Key returned by the login, to be used on all subsequent requests.
     */
    private String authenticatedKey;

    public Client(String user, String apiKey) {
        this(user, apiKey, "blitz.io", 80);
    }

    public Client(String user, String apiKey, String host, int port) {
        this.user = user;
        this.apiKey = apiKey;
        this.port = port;
        this.host = host;
        this.authenticatedKey = null;
    }

    /**
     * 
     * @return 
     */
    public Map<String, Object> login() {
        try {
            URL url = new URL("http", host, port, "/login/api");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            //set the headers
            addCredentials(connection);
            //gets the response
            InputStream stream = connection.getInputStream();
            Map<String, Object> response = fromJsonStream(stream);
            //handle response error
            if(response == null) {
                throw new BlitzException("client", "No response.");
            }
            //authenticate
            else if(response.get("ok") != null) {
                if(response.get("api_key") != null) {
                    authenticatedKey = response.get("api_key").toString();
                }
            }
            return response;
        } catch (UnknownServiceException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            throw new BlitzException("server", "Protocol does not support input");
        } catch (MalformedURLException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            throw new BlitzException("client", "Malformed URL. Please check your host");
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            throw new BlitzException("server", "Can't connect to the server");
        }
    }

    /**
     * 
     * @param test
     * @return 
     */
    public Map<String, Object> execute(TestEntity test) {
        if(test == null) {
            throw new BlitzException("client", "Test exeuction requires a valid test");
        }
        try {
            String data = toJson(test);
            URL url = new URL("http", host, port, "/api/1/curl/execute");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            //set the method
            connection.setRequestMethod("POST");
            //set the headers
            connection.setRequestProperty("content-length", Integer.toString(data.length()));
            addCredentials(connection);
            //sends the JSON data
            connection.setDoOutput(true);
            OutputStream ost = connection.getOutputStream();
            PrintWriter pw = new PrintWriter(ost);
            pw.print(data);
            pw.flush();
            pw.close();
            //gets the response
            InputStream stream = connection.getInputStream();
            return fromJsonStream(stream);
        } catch (UnknownServiceException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            throw new BlitzException("server", "Protocol does not support input");
        } catch (MalformedURLException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            throw new BlitzException("client", "Malformed URL. Please check your host");
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            throw new BlitzException("server", "Unknown problem connecting with the server");
        }
    }

    /**
     * Issue a job status request to the server.
     * @param jobId the id of the job
     * @return a deserialized map with the current status
     */
    public Map<String, Object> getJobStatus(String jobId) {
        if(jobId == null) {
            throw new BlitzException("client", "Invalid job ID");
        }
        try {
            URL url = new URL("http", host, port, "/api/1/jobs/"+jobId+"/status");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            //set the headers
            addCredentials(connection);
            //gets the response
            InputStream stream = connection.getInputStream();
            return fromJsonStream(stream);
        } catch (UnknownServiceException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            throw new BlitzException("server", "Protocol does not support input");
        } catch (MalformedURLException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            throw new BlitzException("client", "Malformed URL. Please check your host");
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            throw new BlitzException("server", "Unknown problem connecting with the server");
        }
    }
    
    /**
     * Send a abort request for a specified job.
     * @param jobId the id of the job to be aborted
     * @return a deserialized response
     */
    public Map<String, Object> abort(String jobId) {
        if(jobId == null) {
            throw new BlitzException("client", "Invalid job ID");
        }
        try {
            String data = "";
            URL url = new URL("http", host, port, "/api/1/jobs/"+jobId+"/abort");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            //set the headers
            addCredentials(connection);
            connection.setRequestProperty("content-length", Integer.toString(data.length()));
            //set the method
            connection.setRequestMethod("PUT");
            //sends the empty data
            connection.setDoOutput(true);
            OutputStream ost = connection.getOutputStream();
            PrintWriter pw = new PrintWriter(ost);
            pw.print(data);
            pw.flush();
            pw.close();
            //gets the response
            InputStream stream = connection.getInputStream();
            return fromJsonStream(stream);
        } catch (UnknownServiceException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            throw new BlitzException("server", "Protocol does not support input");
        } catch (MalformedURLException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            throw new BlitzException("client", "Malformed URL. Please check your host");
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            throw new BlitzException("server", "Unknown problem connecting with the server");
        }
    }
    
    /**
     * Gets a JSON string from a <code>InputStream</code> and deserialize it.
     * @param stream the stream that will input the JSON
     * @return a map with the deserialized JSON response
     */
    protected Map<String, Object> fromJsonStream(InputStream stream) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Map.class, new MapDeserializer())
                .registerTypeAdapter(Collection.class, new ArrayDeserializer())
                .disableHtmlEscaping().create();
        try {
            Reader reader = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
            return gson.fromJson(reader, Map.class);
        } catch (JsonSyntaxException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            throw new BlitzException("server", "Invalid JSON syntax from the server");
        } catch (JsonIOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            throw new BlitzException("server", "Problem retrieving JSON");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            throw new BlitzException("server", "Response encoding not compatible with UTF-8");
        }
    }
    
    /**
     * Private method that serializes all the non transient properties of the
     * current object to a JSON string.
     * Use Google Gson implementation to handle the JSON serialization.
     * @return 
     */
    private String toJson(TestEntity test) {
        Gson gson = new GsonBuilder()
                .excludeFieldsWithModifiers(Modifier.TRANSIENT)
                .registerTypeAdapter(HttpHeader.class, new HttpHeaderSerializer())
                .registerTypeAdapter(HttpCookie.class, new HttpCookieSerializer())
                .registerTypeAdapter(BasicAuthentication.class, new BasicAuthenticationSerializer())
                .disableHtmlEscaping().create();
        return gson.toJson(test);
    }

    /**
     * Add the necessary headers to the connection to authenticate the call on blitz.io
     * @param connection the connection to set the headers
     */
    private void addCredentials(HttpURLConnection connection) {
        String key = (authenticatedKey == null) ? apiKey : authenticatedKey;
        connection.setRequestProperty("X-API-User", user);
        connection.setRequestProperty("X-API-Key", key);
        connection.setRequestProperty("X-API-Client", "java");
    }

    public boolean isAuthenticated() {
        return (authenticatedKey!=null);
    }
    
    
}
