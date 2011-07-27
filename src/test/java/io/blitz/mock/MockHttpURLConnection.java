/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package io.blitz.mock;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ghermeto
 */
public class MockHttpURLConnection extends HttpURLConnection {
    
    private String path;
    
    private Map<String, String> headers;
    
    private String responseData;
    
    private Map<String, String> mappedResponseData;
    
    private ByteArrayOutputStream output;
    
    private boolean useMapped;

    public MockHttpURLConnection() {
        this(null);
    }

    public MockHttpURLConnection(URL url) {
        super(url);
        this.path = (url == null) ? null : url.getPath();
        this.headers = new HashMap<String, String>();
        this.output = new ByteArrayOutputStream();
        this.mappedResponseData = new HashMap<String, String>();
        useMapped = false;
    }

    @Override
    public void disconnect() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean usingProxy() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void connect() throws IOException {
        System.err.println("connected");
    }
    
    @Override
    public void setRequestProperty(String key, String value) {
        headers.put(key, value);
    }
    
    @Override
    public InputStream getInputStream() {
        String data = null;
        if(useMapped) {
            data = mappedResponseData.get(path);
        }
        else {
            data = responseData;
        }
        try {
            return new ByteArrayInputStream(data.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException("Error converting responseData String into stream");
        }
    }

    public String getResponseData() {
        return responseData;
    }

    public void setResponseData(String responseData) {
        this.responseData = responseData;
    }
    
    @Override
    public OutputStream getOutputStream() {
        return output;
    }

    public void setOutput(ByteArrayOutputStream output) {
        this.output = output;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }
    
    public String getOutputStreamAsString(String enc) {
        try {
            return output.toString(enc);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(MockHttpURLConnection.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public void setMappedData(String path, String data) {
        mappedResponseData.put(path, data);
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public boolean isUseMapped() {
        return useMapped;
    }

    public void setUseMapped(boolean useMapped) {
        this.useMapped = useMapped;
    }
}
