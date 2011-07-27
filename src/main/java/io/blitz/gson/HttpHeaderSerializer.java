package io.blitz.gson;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import io.blitz.curl.config.HttpHeader;
import java.lang.reflect.Type;

/**
 *
 * @author ghermeto
 */
public class HttpHeaderSerializer implements JsonSerializer<HttpHeader> {

    public JsonElement serialize(HttpHeader src, Type typeOfSrc, 
            JsonSerializationContext context) {
        
        return new JsonPrimitive(src.toString());
    }
    
}
