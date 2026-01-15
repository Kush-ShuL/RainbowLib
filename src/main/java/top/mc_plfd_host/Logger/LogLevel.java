package top.mc_plfd_host.Logger;

public enum LogLevel {
    TRACE(0, "TRACE"),
    DEBUG(1, "DEBUG"),
    INFO(2, "INFO"),
    WARN(3, "WARN"),
    ERROR(4, "ERROR"),
    OFF(5, "OFF");
    
    private final int level;
    private final String name;
    
    LogLevel(int level, String name) {
        this.level = level;
        this.name = name;
    }
    
    public int getLevel() {
        return level;
    }
    
    public String getName() {
        return name;
    }
    
    public boolean isEnabled(LogLevel targetLevel) {
        return this.level <= targetLevel.level;
    }
}
