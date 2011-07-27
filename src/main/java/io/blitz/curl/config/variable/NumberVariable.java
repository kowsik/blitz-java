package io.blitz.curl.config.variable;

/**
 *
 * @author ghermeto
 */
public class NumberVariable implements IVariable{

    private int min;
    
    private int max;
    
    private String type;

    public NumberVariable() {
        this.type = "number";
    }

    public NumberVariable(int min, int max) {
        this.min = min;
        this.max = max;
        this.type = "number";
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
