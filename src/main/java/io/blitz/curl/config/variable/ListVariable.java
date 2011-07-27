package io.blitz.curl.config.variable;

import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author ghermeto
 */
public class ListVariable implements IVariable{
    
    private Collection<String> entries;
    
    private String type;

    public ListVariable() {
        this.type = "list";
        this.entries = new ArrayList<String>();
    }

    public ListVariable(Collection<String> list) {
        this.type = "list";
        this.entries = list;
    }

    public Collection<String> getEntries() {
        return entries;
    }

    public void setEntries(Collection<String> list) {
        this.entries = list;
    }
    
    public void addToList(String item) {
        this.entries.add(item);
    }

    public String getType() {
        return type;
    }
}
