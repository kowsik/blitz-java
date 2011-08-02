package io.blitz.curl;

import com.google.gson.annotations.SerializedName;
import io.blitz.curl.config.BasicAuthentication;
import io.blitz.curl.config.Content;
import io.blitz.curl.config.HttpHeader;
import io.blitz.curl.config.variable.IVariable;
import java.net.HttpCookie;
import java.net.URL;
import java.util.Collection;
import java.util.Map;

/**
 * Base class for the Blitz curl tests.
 * @author ghermeto
 */
public class TestEntity {

    /**
     * URL to run the tests against
     */
    private URL url;
    
    /**
     * Region from which the test should start
     */
    private String region;
    
    /**
     * User agent to be used on the test. It is serialized with the name 'user-agent'
     */
    @SerializedName("user-agent")
    private String userAgent;
    
    /**
     * referrer to be used on the test
     */
    private URL referrer;
    
    /**
     * The expected status response
     */
    private Integer status;
    
    /**
     * The timeout to be used on the test
     */
    private Integer timeout;
    
    /**
     * 
     */
    private String ssl;
    
    /**
     * cookies to send on the test
     */
    private Collection<HttpCookie> cookies;
    
    /**
     * headers to send on the test
     */
    private Collection<HttpHeader> headers;
    
    /**
     * User and pasword for basic authentication to be used on the test
     */
    private BasicAuthentication user;

    /**
     * Content data to be sent on the test
     */
    private Content content;
    
    /**
     * Variables to be used on the url. The key should be the variable name and
     * the variable instance
     */
    private Map<String, IVariable> variables;

    /**
     * Getter for the content property
     * @return content
     */
    public Content getContent() {
        return content;
    }

    /**
     * Setter for the content property
     * @param content 
     */
    public void setContent(Content content) {
        this.content = content;
    }

    /**
     * Getter for the cookie list.
     * @return cookie list
     */
    public Collection<HttpCookie> getCookies() {
        return cookies;
    }

    /**
     * Setter for the cookie list. The cookies should have version equals 0 (zero)
     * to be compliant with Nestcape cookies.
     * @param cookies 
     */
    public void setCookies(Collection<HttpCookie> cookies) {
        this.cookies = cookies;
    }

    /**
     * Getter for the headers
     * @return header list
     */
    public Collection<HttpHeader> getHeaders() {
        return headers;
    }

    /**
     * Setter for the headers
     * @param headers 
     */
    public void setHeaders(Collection<HttpHeader> headers) {
        this.headers = headers;
    }

    /**
     * Getter for the referrer property
     * @return referrer url
     */
    public URL getReferrer() {
        return referrer;
    }

    /**
     * Setter for the referrer property
     * @param referrer 
     */
    public void setReferrer(URL referrer) {
        this.referrer = referrer;
    }

    /**
     * Getter for the region property
     * @return region
     */
    public String getRegion() {
        return region;
    }

    /**
     * Setter for the region property
     * @param region 
     */
    public void setRegion(String region) {
        this.region = region;
    }

    /**
     * Getter for the ssl property
     * @return ssl
     */
    public String getSsl() {
        return ssl;
    }

    /**
     * Setter for the ssl property
     * @param ssl 
     */
    public void setSsl(String ssl) {
        this.ssl = ssl;
    }

    /**
     * Getter for the status property
     * @return http status code
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * Setter for the status property
     * @param status 
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * Getter for the timeout property
     * @return timeout
     */
    public Integer getTimeout() {
        return timeout;
    }

    /**
     * Setter for the timeout property
     * @param timeout 
     */
    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    /**
     * Getter for the url property
     * @return url for the test
     */
    public URL getUrl() {
        return url;
    }

    /**
     * Setter for the url peoperty
     * @param url 
     */
    public void setUrl(URL url) {
        this.url = url;
    }

    /**
     * Getter for the user property
     * @return basic authentication credentials
     */
    public BasicAuthentication getUser() {
        return user;
    }

    /**
     * Setter for the user property
     * @param user 
     */
    public void setUser(BasicAuthentication user) {
        this.user = user;
    }

    /**
     * Getter for the userAgent property
     * @return user-agent
     */
    public String getUserAgent() {
        return userAgent;
    }

    /**
     * Setter for the userAgent property
     * @param userAgent 
     */
    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    /**
     * Getter for the variable hash
     * @return map of variables
     */
    public Map<String, IVariable> getVariables() {
        return variables;
    }

    /**
     * Setter for the variable hash
     * @param variables 
     */
    public void setVariables(Map<String, IVariable> variables) {
        this.variables = variables;
    }
}
