package io.blitz.curl.config.variable;

/**
 *
 * @author ghermeto
 */
public class UdidVariable implements IVariable{
    
    private String type;

    public UdidVariable() {
        this.type = "udid";
    }

    public String getType() {
        return type;
    }
}
