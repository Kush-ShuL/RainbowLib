package top.mc_plfd_host.Configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import top.mc_plfd_host.Messages.Print;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class Yaml {
    
    private static final ObjectMapper yamlMapper = new ObjectMapper(new YAMLFactory())
            .enable(SerializationFeature.INDENT_OUTPUT)
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    
    public static boolean writeToFile(Object obj, String filePath) {
        try {
            yamlMapper.writeValue(new File(filePath), obj);
            return true;
        } catch (IOException e) {
            Print.error("Failed to write YAML file: " + e.getMessage());
            return false;
        }
    }
    
    public static <T> T readFromFile(String filePath, Class<T> clazz) {
        try {
            T result = yamlMapper.readValue(new File(filePath), clazz);
            return result;
        } catch (IOException e) {
            Print.error("Failed to read YAML file: " + e.getMessage());
            return null;
        }
    }
    
    public static String toYamlString(Object obj) {
        try {
            return yamlMapper.writeValueAsString(obj);
        } catch (IOException e) {
            Print.error("Failed to convert object to YAML string: " + e.getMessage());
            return null;
        }
    }
    
    public static <T> T fromYamlString(String yamlString, Class<T> clazz) {
        try {
            return yamlMapper.readValue(yamlString, clazz);
        } catch (IOException e) {
            Print.error("Failed to convert YAML string to object: " + e.getMessage());
            return null;
        }
    }
    
    public static Map<String, Object> toMap(Object obj) {
        try {
            return yamlMapper.convertValue(obj, Map.class);
        } catch (IllegalArgumentException e) {
            Print.error("Failed to convert object to Map: " + e.getMessage());
            return null;
        }
    }
    
    public static <T> T fromMap(Map<String, Object> map, Class<T> clazz) {
        try {
            return yamlMapper.convertValue(map, clazz);
        } catch (IllegalArgumentException e) {
            Print.error("Failed to convert Map to object: " + e.getMessage());
            return null;
        }
    }
    
    public static void convertJsonToYaml(String jsonFilePath, String yamlFilePath) {
        try {
            Object data = Json.readFromFile(jsonFilePath, Object.class);
            if (data != null) {
                writeToFile(data, yamlFilePath);
            }
        } catch (Exception e) {
            Print.error("Failed to convert JSON to YAML: " + e.getMessage());
        }
    }
    
    public static void convertYamlToJson(String yamlFilePath, String jsonFilePath) {
        try {
            Object data = readFromFile(yamlFilePath, Object.class);
            if (data != null) {
                Json.writeToFile(data, jsonFilePath);
            }
        } catch (Exception e) {
            Print.error("Failed to convert YAML to JSON: " + e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    public static String get(String filePath, String key) {
        try {
            Map<String, Object> data = yamlMapper.readValue(new File(filePath), Map.class);
            Object value = data.get(key);
            return value != null ? value.toString() : null;
        } catch (IOException e) {
            Print.error("Failed to get key from YAML file: " + e.getMessage());
            return null;
        }
    }
    
    @SuppressWarnings("unchecked")
    public static boolean set(String filePath, String key, Object value) {
        try {
            Map<String, Object> data;
            if (new File(filePath).exists()) {
                data = yamlMapper.readValue(new File(filePath), Map.class);
            } else {
                data = new java.util.HashMap<>();
            }
            data.put(key, value);
            yamlMapper.writeValue(new File(filePath), data);
            return true;
        } catch (IOException e) {
            Print.error("Failed to set key in YAML file: " + e.getMessage());
            return false;
        }
    }
}
