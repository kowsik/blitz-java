package io.blitz.gson;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import io.blitz.curl.config.BasicAuthentication;
import java.lang.reflect.Type;

/**
 *
 * @author ghermeto
 */
public class BasicAuthenticationSerializer implements JsonSerializer<BasicAuthentication> {

    public JsonElement serialize(BasicAuthentication src, Type typeOfSrc, 
            JsonSerializationContext context) {
        
        return new JsonPrimitive(src.toString());
    }
    
}
