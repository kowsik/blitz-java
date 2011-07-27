package io.blitz.curl.config;

/**
 *
 * @author ghermeto
 */
public class HttpHeader {

    private String field;
    private String value;
    
    public HttpHeader(String field, String value) {
        this.field = field;
        this.value = value;
    }
    
    @Override
    public String toString() {
        return field + ":" + value;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
