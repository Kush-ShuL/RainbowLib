package top.mc_plfd_host.Configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import top.mc_plfd_host.Messages.Print;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class Json {
    
    private static final ObjectMapper objectMapper = new ObjectMapper()
            .enable(SerializationFeature.INDENT_OUTPUT)
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    
    public static boolean writeToFile(Object obj, String filePath) {
        try {
            objectMapper.writeValue(new File(filePath), obj);
            return true;
        } catch (IOException e) {
            Print.error("Failed to write JSON file: " + e.getMessage());
            return false;
        }
    }
    
    public static <T> T readFromFile(String filePath, Class<T> clazz) {
        try {
            T result = objectMapper.readValue(new File(filePath), clazz);
            return result;
        } catch (IOException e) {
            Print.error("Failed to read JSON file: " + e.getMessage());
            return null;
        }
    }
    
    public static String toJsonString(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (IOException e) {
            Print.error("Failed to convert object to JSON string: " + e.getMessage());
            return null;
        }
    }
    
    public static <T> T fromJsonString(String jsonString, Class<T> clazz) {
        try {
            return objectMapper.readValue(jsonString, clazz);
        } catch (IOException e) {
            Print.error("Failed to convert JSON string to object: " + e.getMessage());
            return null;
        }
    }
    
    public static Map<String, Object> toMap(Object obj) {
        try {
            return objectMapper.convertValue(obj, Map.class);
        } catch (IllegalArgumentException e) {
            Print.error("Failed to convert object to Map: " + e.getMessage());
            return null;
        }
    }
    
    public static <T> T fromMap(Map<String, Object> map, Class<T> clazz) {
        try {
            return objectMapper.convertValue(map, clazz);
        } catch (IllegalArgumentException e) {
            Print.error("Failed to convert Map to object: " + e.getMessage());
            return null;
        }
    }
    
    @SuppressWarnings("unchecked")
    public static String get(String filePath, String key) {
        try {
            Map<String, Object> data = objectMapper.readValue(new File(filePath), Map.class);
            Object value = data.get(key);
            return value != null ? value.toString() : null;
        } catch (IOException e) {
            Print.error("Failed to get key from JSON file: " + e.getMessage());
            return null;
        }
    }
    
    @SuppressWarnings("unchecked")
    public static boolean set(String filePath, String key, Object value) {
        try {
            Map<String, Object> data;
            if (new File(filePath).exists()) {
                data = objectMapper.readValue(new File(filePath), Map.class);
            } else {
                data = new java.util.HashMap<>();
            }
            data.put(key, value);
            objectMapper.writeValue(new File(filePath), data);
            return true;
        } catch (IOException e) {
            Print.error("Failed to set key in JSON file: " + e.getMessage());
            return false;
        }
    }
}
