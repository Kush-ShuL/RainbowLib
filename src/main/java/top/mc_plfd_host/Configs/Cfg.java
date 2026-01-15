package top.mc_plfd_host.Configs;

import top.mc_plfd_host.Messages.Print;

import java.io.*;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

public class Cfg {
    
    public static boolean writeToFile(Map<String, String> data, String filePath) {
        return writeToFile(data, filePath, "=");
    }
    
    public static boolean writeToFile(Map<String, String> data, String filePath, String delimiter) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Map.Entry<String, String> entry : data.entrySet()) {
                writer.write(entry.getKey() + delimiter + entry.getValue());
                writer.newLine();
            }
            return true;
        } catch (IOException e) {
            Print.error("Failed to write CFG file: " + e.getMessage());
            return false;
        }
    }
    
    public static Map<String, String> readFromFile(String filePath) {
        return readFromFile(filePath, "=");
    }
    
    public static Map<String, String> readFromFile(String filePath, String delimiter) {
        Map<String, String> data = new HashMap<>();
        File file = new File(filePath);
        
        if (!file.exists()) {
            Print.error("CFG file does not exist: " + filePath);
            return data;
        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith("#") || line.startsWith("//")) {
                    continue;
                }
                
                int delimiterIndex = line.indexOf(delimiter);
                if (delimiterIndex > 0) {
                    String key = line.substring(0, delimiterIndex).trim();
                    String value = line.substring(delimiterIndex + delimiter.length()).trim();
                    data.put(key, value);
                }
            }
        } catch (IOException e) {
            Print.error("Failed to read CFG file: " + e.getMessage());
        }
        
        return data;
    }
    
    public static String getValue(String filePath, String key) {
        return getValue(filePath, key, "=");
    }
    
    public static String getValue(String filePath, String key, String delimiter) {
        Map<String, String> data = readFromFile(filePath, delimiter);
        return data.get(key);
    }
    
    public static String getValue(String filePath, String key, String defaultValue, String delimiter) {
        Map<String, String> data = readFromFile(filePath, delimiter);
        return data.getOrDefault(key, defaultValue);
    }
    
    public static boolean setValue(String filePath, String key, String value) {
        return setValue(filePath, key, value, "=");
    }
    
    public static boolean setValue(String filePath, String key, String value, String delimiter) {
        Map<String, String> data = readFromFile(filePath, delimiter);
        data.put(key, value);
        return writeToFile(data, filePath, delimiter);
    }
    
    public static boolean containsKey(String filePath, String key) {
        return containsKey(filePath, key, "=");
    }
    
    public static boolean containsKey(String filePath, String key, String delimiter) {
        Map<String, String> data = readFromFile(filePath, delimiter);
        return data.containsKey(key);
    }
    
    public static boolean removeKey(String filePath, String key) {
        return removeKey(filePath, key, "=");
    }
    
    public static boolean removeKey(String filePath, String key, String delimiter) {
        Map<String, String> data = readFromFile(filePath, delimiter);
        data.remove(key);
        return writeToFile(data, filePath, delimiter);
    }
    
    public static boolean addComment(String filePath, String comment) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write("# " + comment);
            writer.newLine();
            return true;
        } catch (IOException e) {
            Print.error("Failed to add comment to CFG file: " + e.getMessage());
            return false;
        }
    }
    
    public static Map<String, String> readSection(String filePath, String sectionPrefix) {
        return readSection(filePath, sectionPrefix, "=");
    }
    
    public static Map<String, String> readSection(String filePath, String sectionPrefix, String delimiter) {
        Map<String, String> allData = readFromFile(filePath, delimiter);
        Map<String, String> sectionData = new HashMap<>();
        
        for (Map.Entry<String, String> entry : allData.entrySet()) {
            if (entry.getKey().startsWith(sectionPrefix)) {
                String key = entry.getKey().substring(sectionPrefix.length());
                sectionData.put(key, entry.getValue());
            }
        }
        
        return sectionData;
    }
}
