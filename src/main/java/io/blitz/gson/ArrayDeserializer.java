package io.blitz.gson;

import java.util.ArrayList;
import java.util.Collection;
import java.lang.reflect.Type;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonParseException;
import com.google.gson.JsonDeserializationContext;

/** 
 * @author ghermeto
 */
public class ArrayDeserializer extends DeserializerHelper
    implements JsonDeserializer<Collection<Object>> {

    /**
     * 
     * @param element
     * @param typeOfT
     * @param context
     * @return
     * @throws JsonParseException 
     */
    public Collection<Object> deserialize(JsonElement element, Type typeOfT, 
            JsonDeserializationContext context) throws JsonParseException {
        if (element.isJsonNull()) {
            return null;
        } 
        JsonArray json = element.getAsJsonArray();
        Collection<Object> result = new ArrayList<Object>();
        for (JsonElement jsonValue : json) {
            result.add(getValue(jsonValue, context));
        }
        return result;
    }
}