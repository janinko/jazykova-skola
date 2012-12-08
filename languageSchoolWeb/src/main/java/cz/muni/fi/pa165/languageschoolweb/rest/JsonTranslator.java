package cz.muni.fi.pa165.languageschoolweb.rest;

import com.google.gson.Gson;

/**
 *
 * @author zabka
 */
public class JsonTranslator {
    public <T> T jsonToObject(String json, Class<T> classOfT) {
        Gson gson = new Gson();
        return gson.fromJson(json, classOfT);        
    }
    
    public String objectToJson(Object object) {
        Gson gson = new Gson();
        return gson.toJson(object);        
    }
}
