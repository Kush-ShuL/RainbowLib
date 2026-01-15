package top.mc_plfd_host.Messages;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Print {
    
    public static void info(String message) {
        System.out.println("[INFO] " + getTimeStamp() + " - " + message);
    }
    
    public static void error(String message) {
        System.err.println("[ERROR] " + getTimeStamp() + " - " + message);
    }
    
    public static void warn(String message) {
        System.out.println("[WARN] " + getTimeStamp() + " - " + message);
    }
    
    public static void debug(String message) {
        System.out.println("[DEBUG] " + getTimeStamp() + " - " + message);
    }
    
    public static void plain(String message) {
        System.out.println(message);
    }
    
    public static void plain(Object obj) {
        System.out.println(obj);
    }
    
    private static String getTimeStamp() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
