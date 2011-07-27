package io.blitz.curl.config.variable;

/**
 *
 * @author ghermeto
 */
public class AlphaVariable implements IVariable{

    private int min;
    
    private int max;
    
    private String type;

    public AlphaVariable() {
        this.type = "alpha";
    }

    public AlphaVariable(int min, int max) {
        this.min = min;
        this.max = max;
        this.type = "alpha";
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public String getType() {
        return type;
    }
}
