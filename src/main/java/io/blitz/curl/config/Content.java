package io.blitz.curl.config;

import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author ghermeto
 */
public class Content {

    private Collection<String> data;

    public Content() {
        data = new ArrayList<String>();
    }

    public Content(Collection<String> data) {
        this.data = data;
    }

    public Collection<String> getData() {
        return data;
    }

    public void setData(Collection<String> data) {
        this.data = data;
    }
}
