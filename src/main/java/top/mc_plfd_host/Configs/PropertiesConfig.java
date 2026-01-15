package top.mc_plfd_host.Configs;

import top.mc_plfd_host.Messages.Print;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class PropertiesConfig {
    
    public static boolean writeToFile(Map<String, String> data, String filePath) {
        return writeToFile(data, filePath, null);
    }
    
    public static boolean writeToFile(Map<String, String> data, String filePath, String comments) {
        try {
            java.util.Properties props = new java.util.Properties();
            for (Map.Entry<String, String> entry : data.entrySet()) {
                props.setProperty(entry.getKey(), entry.getValue());
            }
            
            try (FileOutputStream fos = new FileOutputStream(filePath)) {
                props.store(fos, comments);
            }
            return true;
        } catch (IOException e) {
            Print.error("Failed to write Properties file: " + e.getMessage());
            return false;
        }
    }
    
    public static java.util.Properties readFromFile(String filePath) {
        try {
            java.util.Properties props = new java.util.Properties();
            try (FileInputStream fis = new FileInputStream(filePath)) {
                props.load(fis);
            }
            return props;
        } catch (IOException e) {
            Print.error("Failed to read Properties file: " + e.getMessage());
            return null;
        }
    }
    
    public static Map<String, String> readFromFileAsMap(String filePath) {
        java.util.Properties props = readFromFile(filePath);
        if (props == null) {
            return null;
        }
        
        return Map.ofEntries(props.entrySet().stream()
                .map(entry -> Map.entry(entry.getKey().toString(), entry.getValue().toString()))
                .toArray(Map.Entry[]::new));
    }
    
    public static String getProperty(String filePath, String key) {
        java.util.Properties props = readFromFile(filePath);
        if (props == null) {
            return null;
        }
        return props.getProperty(key);
    }
    
    public static String getProperty(String filePath, String key, String defaultValue) {
        java.util.Properties props = readFromFile(filePath);
        if (props == null) {
            return defaultValue;
        }
        return props.getProperty(key, defaultValue);
    }
    
    public static boolean setProperty(String filePath, String key, String value) {
        java.util.Properties props = readFromFile(filePath);
        if (props == null) {
            props = new java.util.Properties();
        }
        props.setProperty(key, value);
        
        Map<String, String> data = Map.ofEntries(props.entrySet().stream()
                .map(entry -> Map.entry(entry.getKey().toString(), entry.getValue().toString()))
                .toArray(Map.Entry[]::new));
        
        return writeToFile(data, filePath);
    }
    
    public static Set<String> getPropertyNames(String filePath) {
        java.util.Properties props = readFromFile(filePath);
        if (props == null) {
            return null;
        }
        return props.stringPropertyNames();
    }
    
    public static boolean containsKey(String filePath, String key) {
        java.util.Properties props = readFromFile(filePath);
        if (props == null) {
            return false;
        }
        return props.containsKey(key);
    }
    
    public static boolean removeProperty(String filePath, String key) {
        java.util.Properties props = readFromFile(filePath);
        if (props == null) {
            return false;
        }
        props.remove(key);
        
        Map<String, String> data = Map.ofEntries(props.entrySet().stream()
                .filter(entry -> !entry.getKey().equals(key))
                .map(entry -> Map.entry(entry.getKey().toString(), entry.getValue().toString()))
                .toArray(Map.Entry[]::new));
        
        return writeToFile(data, filePath);
    }
}
