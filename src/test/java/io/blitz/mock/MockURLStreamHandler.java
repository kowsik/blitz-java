/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package io.blitz.mock;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;

/**
 *
 * @author ghermeto
 */
public class MockURLStreamHandler extends URLStreamHandler{

    private MockHttpURLConnection connection;

    public MockURLStreamHandler() {
        connection = new MockHttpURLConnection();
    }
    
    
    @Override
    protected URLConnection openConnection(URL u) throws IOException {
        connection.setPath((u == null) ? null : u.getPath());
        return connection;
    }

    public MockHttpURLConnection getConnection() {
        return connection;
    }

    public void setConnection(MockHttpURLConnection connection) {
        this.connection = connection;
    }
}
