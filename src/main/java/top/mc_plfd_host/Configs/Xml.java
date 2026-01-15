package top.mc_plfd_host.Configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import top.mc_plfd_host.Messages.Print;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class Xml {
    
    private static final ObjectMapper xmlMapper = new XmlMapper()
            .enable(SerializationFeature.INDENT_OUTPUT)
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    
    public static boolean writeToFile(Object obj, String filePath) {
        try {
            xmlMapper.writeValue(new File(filePath), obj);
            return true;
        } catch (IOException e) {
            Print.error("Failed to write XML file: " + e.getMessage());
            return false;
        }
    }
    
    public static <T> T readFromFile(String filePath, Class<T> clazz) {
        try {
            T result = xmlMapper.readValue(new File(filePath), clazz);
            return result;
        } catch (IOException e) {
            Print.error("Failed to read XML file: " + e.getMessage());
            return null;
        }
    }
    
    public static String toXmlString(Object obj) {
        try {
            return xmlMapper.writeValueAsString(obj);
        } catch (IOException e) {
            Print.error("Failed to convert object to XML string: " + e.getMessage());
            return null;
        }
    }
    
    public static <T> T fromXmlString(String xmlString, Class<T> clazz) {
        try {
            return xmlMapper.readValue(xmlString, clazz);
        } catch (IOException e) {
            Print.error("Failed to convert XML string to object: " + e.getMessage());
            return null;
        }
    }
    
    public static Map<String, Object> toMap(Object obj) {
        try {
            return xmlMapper.convertValue(obj, Map.class);
        } catch (IllegalArgumentException e) {
            Print.error("Failed to convert object to Map: " + e.getMessage());
            return null;
        }
    }
    
    public static <T> T fromMap(Map<String, Object> map, Class<T> clazz) {
        try {
            return xmlMapper.convertValue(map, clazz);
        } catch (IllegalArgumentException e) {
            Print.error("Failed to convert Map to object: " + e.getMessage());
            return null;
        }
    }
    
    public static void convertJsonToXml(String jsonFilePath, String xmlFilePath) {
        try {
            Object data = Json.readFromFile(jsonFilePath, Object.class);
            if (data != null) {
                writeToFile(data, xmlFilePath);
            }
        } catch (Exception e) {
            Print.error("Failed to convert JSON to XML: " + e.getMessage());
        }
    }
    
    public static void convertXmlToJson(String xmlFilePath, String jsonFilePath) {
        try {
            Object data = readFromFile(xmlFilePath, Object.class);
            if (data != null) {
                Json.writeToFile(data, jsonFilePath);
            }
        } catch (Exception e) {
            Print.error("Failed to convert XML to JSON: " + e.getMessage());
        }
    }
    
    public static void convertYamlToXml(String yamlFilePath, String xmlFilePath) {
        try {
            Object data = Yaml.readFromFile(yamlFilePath, Object.class);
            if (data != null) {
                writeToFile(data, xmlFilePath);
            }
        } catch (Exception e) {
            Print.error("Failed to convert YAML to XML: " + e.getMessage());
        }
    }
    
    public static void convertXmlToYaml(String xmlFilePath, String yamlFilePath) {
        try {
            Object data = readFromFile(xmlFilePath, Object.class);
            if (data != null) {
                Yaml.writeToFile(data, yamlFilePath);
            }
        } catch (Exception e) {
            Print.error("Failed to convert XML to YAML: " + e.getMessage());
        }
    }
    
    public static void convertTomlToXml(String tomlFilePath, String xmlFilePath) {
        try {
            Object data = Toml.readFromFile(tomlFilePath, Object.class);
            if (data != null) {
                writeToFile(data, xmlFilePath);
            }
        } catch (Exception e) {
            Print.error("Failed to convert TOML to XML: " + e.getMessage());
        }
    }
    
    public static void convertXmlToToml(String xmlFilePath, String tomlFilePath) {
        try {
            Object data = readFromFile(xmlFilePath, Object.class);
            if (data != null) {
                Toml.writeToFile(data, tomlFilePath);
            }
        } catch (Exception e) {
            Print.error("Failed to convert XML to TOML: " + e.getMessage());
        }
    }
}
