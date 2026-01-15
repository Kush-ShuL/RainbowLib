package top.mc_plfd_host.Types;

public enum NbtTagType {
    END(0, "TAG_End"),
    BYTE(1, "TAG_Byte"),
    SHORT(2, "TAG_Short"),
    INT(3, "TAG_Int"),
    LONG(4, "TAG_Long"),
    FLOAT(5, "TAG_Float"),
    DOUBLE(6, "TAG_Double"),
    BYTE_ARRAY(7, "TAG_Byte_Array"),
    STRING(8, "TAG_String"),
    LIST(9, "TAG_List"),
    COMPOUND(10, "TAG_Compound"),
    INT_ARRAY(11, "TAG_Int_Array"),
    LONG_ARRAY(12, "TAG_Long_Array");
    
    private final int id;
    private final String name;
    
    NbtTagType(int id, String name) {
        this.id = id;
        this.name = name;
    }
    
    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public static NbtTagType fromId(int id) {
        for (NbtTagType type : values()) {
            if (type.id == id) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown NBT tag type: " + id);
    }
}
