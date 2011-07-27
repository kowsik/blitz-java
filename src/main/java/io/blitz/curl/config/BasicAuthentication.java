package io.blitz.curl.config;

/**
 *  
 * @author ghermeto
 */
public class BasicAuthentication {
    
    private String username;
    
    private String password;
    
    
    public BasicAuthentication(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    public String toString() {
        return username + ":" + password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
