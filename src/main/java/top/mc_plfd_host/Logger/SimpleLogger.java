package top.mc_plfd_host.Logger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SimpleLogger implements Logger {
    
    private final String name;
    private LogLevel level;
    private static final DateTimeFormatter TIME_FORMATTER = 
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
    
    public SimpleLogger(String name) {
        this.name = name;
        this.level = LogLevel.INFO;
    }
    
    public SimpleLogger(String name, LogLevel level) {
        this.name = name;
        this.level = level;
    }
    
    @Override
    public void trace(String message) {
        log(LogLevel.TRACE, message, null);
    }
    
    @Override
    public void debug(String message) {
        log(LogLevel.DEBUG, message, null);
    }
    
    @Override
    public void info(String message) {
        log(LogLevel.INFO, message, null);
    }
    
    @Override
    public void warn(String message) {
        log(LogLevel.WARN, message, null);
    }
    
    @Override
    public void error(String message) {
        log(LogLevel.ERROR, message, null);
    }
    
    @Override
    public void trace(String message, Throwable throwable) {
        log(LogLevel.TRACE, message, throwable);
    }
    
    @Override
    public void debug(String message, Throwable throwable) {
        log(LogLevel.DEBUG, message, throwable);
    }
    
    @Override
    public void info(String message, Throwable throwable) {
        log(LogLevel.INFO, message, throwable);
    }
    
    @Override
    public void warn(String message, Throwable throwable) {
        log(LogLevel.WARN, message, throwable);
    }
    
    @Override
    public void error(String message, Throwable throwable) {
        log(LogLevel.ERROR, message, throwable);
    }
    
    private void log(LogLevel logLevel, String message, Throwable throwable) {
        if (!logLevel.isEnabled(level)) {
            return;
        }
        
        String timestamp = LocalDateTime.now().format(TIME_FORMATTER);
        String threadName = Thread.currentThread().getName();
        String logMessage = String.format("[%s] [%s] [%s] [%s] %s", 
            timestamp, logLevel.getName(), threadName, name, message);
        
        if (logLevel == LogLevel.ERROR || logLevel == LogLevel.WARN) {
            System.err.println(logMessage);
        } else {
            System.out.println(logMessage);
        }
        
        if (throwable != null) {
            if (logLevel == LogLevel.ERROR || logLevel == LogLevel.WARN) {
                throwable.printStackTrace(System.err);
            } else {
                throwable.printStackTrace(System.out);
            }
        }
    }
    
    @Override
    public boolean isTraceEnabled() {
        return LogLevel.TRACE.isEnabled(level);
    }
    
    @Override
    public boolean isDebugEnabled() {
        return LogLevel.DEBUG.isEnabled(level);
    }
    
    @Override
    public boolean isInfoEnabled() {
        return LogLevel.INFO.isEnabled(level);
    }
    
    @Override
    public boolean isWarnEnabled() {
        return LogLevel.WARN.isEnabled(level);
    }
    
    @Override
    public boolean isErrorEnabled() {
        return LogLevel.ERROR.isEnabled(level);
    }
    
    @Override
    public void setLevel(LogLevel level) {
        this.level = level;
    }
    
    @Override
    public LogLevel getLevel() {
        return level;
    }
    
    @Override
    public String getName() {
        return name;
    }
}
