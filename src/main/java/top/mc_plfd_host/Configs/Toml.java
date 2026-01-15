package top.mc_plfd_host.Configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.toml.TomlFactory;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import top.mc_plfd_host.Messages.Print;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class Toml {
    
    private static final ObjectMapper tomlMapper = new ObjectMapper(new TomlFactory())
            .enable(SerializationFeature.INDENT_OUTPUT)
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    
    public static boolean writeToFile(Object obj, String filePath) {
        try {
            tomlMapper.writeValue(new File(filePath), obj);
            return true;
        } catch (IOException e) {
            Print.error("Failed to write TOML file: " + e.getMessage());
            return false;
        }
    }
    
    public static <T> T readFromFile(String filePath, Class<T> clazz) {
        try {
            T result = tomlMapper.readValue(new File(filePath), clazz);
            return result;
        } catch (IOException e) {
            Print.error("Failed to read TOML file: " + e.getMessage());
            return null;
        }
    }
    
    public static String toTomlString(Object obj) {
        try {
            return tomlMapper.writeValueAsString(obj);
        } catch (IOException e) {
            Print.error("Failed to convert object to TOML string: " + e.getMessage());
            return null;
        }
    }
    
    public static <T> T fromTomlString(String tomlString, Class<T> clazz) {
        try {
            return tomlMapper.readValue(tomlString, clazz);
        } catch (IOException e) {
            Print.error("Failed to convert TOML string to object: " + e.getMessage());
            return null;
        }
    }
    
    public static Map<String, Object> toMap(Object obj) {
        try {
            return tomlMapper.convertValue(obj, Map.class);
        } catch (IllegalArgumentException e) {
            Print.error("Failed to convert object to Map: " + e.getMessage());
            return null;
        }
    }
    
    public static <T> T fromMap(Map<String, Object> map, Class<T> clazz) {
        try {
            return tomlMapper.convertValue(map, clazz);
        } catch (IllegalArgumentException e) {
            Print.error("Failed to convert Map to object: " + e.getMessage());
            return null;
        }
    }
    
    public static void convertJsonToToml(String jsonFilePath, String tomlFilePath) {
        try {
            Object data = Json.readFromFile(jsonFilePath, Object.class);
            if (data != null) {
                writeToFile(data, tomlFilePath);
            }
        } catch (Exception e) {
            Print.error("Failed to convert JSON to TOML: " + e.getMessage());
        }
    }
    
    public static void convertTomlToJson(String tomlFilePath, String jsonFilePath) {
        try {
            Object data = readFromFile(tomlFilePath, Object.class);
            if (data != null) {
                Json.writeToFile(data, jsonFilePath);
            }
        } catch (Exception e) {
            Print.error("Failed to convert TOML to JSON: " + e.getMessage());
        }
    }
    
    public static void convertYamlToToml(String yamlFilePath, String tomlFilePath) {
        try {
            Object data = Yaml.readFromFile(yamlFilePath, Object.class);
            if (data != null) {
                writeToFile(data, tomlFilePath);
            }
        } catch (Exception e) {
            Print.error("Failed to convert YAML to TOML: " + e.getMessage());
        }
    }
    
    public static void convertTomlToYaml(String tomlFilePath, String yamlFilePath) {
        try {
            Object data = readFromFile(tomlFilePath, Object.class);
            if (data != null) {
                Yaml.writeToFile(data, yamlFilePath);
            }
        } catch (Exception e) {
            Print.error("Failed to convert TOML to YAML: " + e.getMessage());
        }
    }
}
