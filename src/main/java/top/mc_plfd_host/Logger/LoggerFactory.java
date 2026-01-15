package top.mc_plfd_host.Logger;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class LoggerFactory {
    
    private static final ConcurrentMap<String, Logger> loggers = new ConcurrentHashMap<>();
    private static LogLevel globalLevel = LogLevel.INFO;
    
    private LoggerFactory() {
        // Utility class, prevent instantiation
    }
    
    public static Logger getLogger(String name) {
        return loggers.computeIfAbsent(name, key -> new SimpleLogger(key, globalLevel));
    }
    
    public static Logger getLogger(Class<?> clazz) {
        return getLogger(clazz.getSimpleName());
    }
    
    public static Logger getLogger() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        if (stackTrace.length >= 3) {
            String className = stackTrace[2].getClassName();
            return getLogger(className.substring(className.lastIndexOf('.') + 1));
        }
        return getLogger("Unknown");
    }
    
    public static void setGlobalLevel(LogLevel level) {
        globalLevel = level;
        loggers.values().forEach(logger -> logger.setLevel(level));
    }
    
    public static LogLevel getGlobalLevel() {
        return globalLevel;
    }
    
    public static void clearCache() {
        loggers.clear();
    }
    
    public static int getLoggerCount() {
        return loggers.size();
    }
}
