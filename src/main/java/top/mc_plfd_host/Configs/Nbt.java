package top.mc_plfd_host.Configs;

import top.mc_plfd_host.Messages.Print;
import top.mc_plfd_host.Types.NbtTagType;

import java.io.*;
import java.nio.ByteOrder;
import java.util.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class Nbt {
    
    public static boolean writeToFile(Map<String, Object> data, String filePath) {
        return writeToFile(data, filePath, false);
    }
    
    public static boolean writeToFile(Map<String, Object> data, String filePath, boolean compressed) {
        try (FileOutputStream fos = new FileOutputStream(filePath);
             OutputStream os = compressed ? new GZIPOutputStream(fos) : fos;
             DataOutputStream dos = new DataOutputStream(os)) {
            
            // Write compound tag with empty name (root)
            writeCompoundTag(dos, "", data);
            return true;
        } catch (IOException e) {
            Print.error("Failed to write NBT file: " + e.getMessage());
            return false;
        }
    }
    
    public static Map<String, Object> readFromFile(String filePath) {
        return readFromFile(filePath, false);
    }
    
    public static Map<String, Object> readFromFile(String filePath, boolean compressed) {
        try (FileInputStream fis = new FileInputStream(filePath);
             InputStream is = compressed ? new GZIPInputStream(fis) : fis;
             DataInputStream dis = new DataInputStream(is)) {
            
            // Read root compound tag
            byte tagId = dis.readByte();
            if (tagId != NbtTagType.COMPOUND.getId()) {
                throw new IOException("Root tag must be a compound tag");
            }
            
            // Read and skip root tag name
            readString(dis);
            
            // Read compound data
            return readCompoundTag(dis);
        } catch (IOException e) {
            Print.error("Failed to read NBT file: " + e.getMessage());
            return null;
        }
    }
    
    public static Map<String, Object> readFromFile(String filePath, boolean compressed, String rootName) {
        try (FileInputStream fis = new FileInputStream(filePath);
             InputStream is = compressed ? new GZIPInputStream(fis) : fis;
             DataInputStream dis = new DataInputStream(is)) {
            
            // Read root compound tag
            byte tagId = dis.readByte();
            if (tagId != NbtTagType.COMPOUND.getId()) {
                throw new IOException("Root tag must be a compound tag");
            }
            
            // Read root tag name
            String name = readString(dis);
            if (!name.equals(rootName)) {
                throw new IOException("Expected root name '" + rootName + "' but found '" + name + "'");
            }
            
            // Read compound data
            return readCompoundTag(dis);
        } catch (IOException e) {
            Print.error("Failed to read NBT file: " + e.getMessage());
            return null;
        }
    }
    
    private static void writeCompoundTag(DataOutputStream dos, String name, Map<String, Object> data) throws IOException {
        dos.writeByte(NbtTagType.COMPOUND.getId());
        writeString(dos, name);
        
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            writeTag(dos, entry.getKey(), entry.getValue());
        }
        
        // End tag
        dos.writeByte(NbtTagType.END.getId());
    }
    
    private static Map<String, Object> readCompoundTag(DataInputStream dis) throws IOException {
        Map<String, Object> data = new LinkedHashMap<>();
        
        while (true) {
            byte tagId = dis.readByte();
            if (tagId == NbtTagType.END.getId()) {
                break;
            }
            
            String name = readString(dis);
            Object value = readTagValue(dis, NbtTagType.fromId(tagId));
            data.put(name, value);
        }
        
        return data;
    }
    
    private static void writeTag(DataOutputStream dos, String name, Object value) throws IOException {
        if (value instanceof Map) {
            writeCompoundTag(dos, name, (Map<String, Object>) value);
        } else if (value instanceof List) {
            writeListTag(dos, name, (List<?>) value);
        } else if (value instanceof String) {
            writeStringTag(dos, name, (String) value);
        } else if (value instanceof Byte) {
            writeByteTag(dos, name, (Byte) value);
        } else if (value instanceof Short) {
            writeShortTag(dos, name, (Short) value);
        } else if (value instanceof Integer) {
            writeIntTag(dos, name, (Integer) value);
        } else if (value instanceof Long) {
            writeLongTag(dos, name, (Long) value);
        } else if (value instanceof Float) {
            writeFloatTag(dos, name, (Float) value);
        } else if (value instanceof Double) {
            writeDoubleTag(dos, name, (Double) value);
        } else if (value instanceof byte[]) {
            writeByteArrayTag(dos, name, (byte[]) value);
        } else if (value instanceof int[]) {
            writeIntArrayTag(dos, name, (int[]) value);
        } else if (value instanceof long[]) {
            writeLongArrayTag(dos, name, (long[]) value);
        } else {
            throw new IOException("Unsupported type: " + value.getClass());
        }
    }
    
    private static Object readTagValue(DataInputStream dis, NbtTagType type) throws IOException {
        switch (type) {
            case END:
                return null;
            case BYTE:
                return dis.readByte();
            case SHORT:
                return dis.readShort();
            case INT:
                return dis.readInt();
            case LONG:
                return dis.readLong();
            case FLOAT:
                return dis.readFloat();
            case DOUBLE:
                return dis.readDouble();
            case BYTE_ARRAY:
                return readByteArray(dis);
            case STRING:
                return readString(dis);
            case LIST:
                return readListTag(dis);
            case COMPOUND:
                return readCompoundTag(dis);
            case INT_ARRAY:
                return readIntArray(dis);
            case LONG_ARRAY:
                return readLongArray(dis);
            default:
                throw new IOException("Unknown tag type: " + type);
        }
    }
    
    private static void writeStringTag(DataOutputStream dos, String name, String value) throws IOException {
        dos.writeByte(NbtTagType.STRING.getId());
        writeString(dos, name);
        writeString(dos, value);
    }
    
    private static void writeByteTag(DataOutputStream dos, String name, byte value) throws IOException {
        dos.writeByte(NbtTagType.BYTE.getId());
        writeString(dos, name);
        dos.writeByte(value);
    }
    
    private static void writeShortTag(DataOutputStream dos, String name, short value) throws IOException {
        dos.writeByte(NbtTagType.SHORT.getId());
        writeString(dos, name);
        dos.writeShort(value);
    }
    
    private static void writeIntTag(DataOutputStream dos, String name, int value) throws IOException {
        dos.writeByte(NbtTagType.INT.getId());
        writeString(dos, name);
        dos.writeInt(value);
    }
    
    private static void writeLongTag(DataOutputStream dos, String name, long value) throws IOException {
        dos.writeByte(NbtTagType.LONG.getId());
        writeString(dos, name);
        dos.writeLong(value);
    }
    
    private static void writeFloatTag(DataOutputStream dos, String name, float value) throws IOException {
        dos.writeByte(NbtTagType.FLOAT.getId());
        writeString(dos, name);
        dos.writeFloat(value);
    }
    
    private static void writeDoubleTag(DataOutputStream dos, String name, double value) throws IOException {
        dos.writeByte(NbtTagType.DOUBLE.getId());
        writeString(dos, name);
        dos.writeDouble(value);
    }
    
    private static void writeByteArrayTag(DataOutputStream dos, String name, byte[] value) throws IOException {
        dos.writeByte(NbtTagType.BYTE_ARRAY.getId());
        writeString(dos, name);
        dos.writeInt(value.length);
        dos.write(value);
    }
    
    private static void writeIntArrayTag(DataOutputStream dos, String name, int[] value) throws IOException {
        dos.writeByte(NbtTagType.INT_ARRAY.getId());
        writeString(dos, name);
        dos.writeInt(value.length);
        for (int i : value) {
            dos.writeInt(i);
        }
    }
    
    private static void writeLongArrayTag(DataOutputStream dos, String name, long[] value) throws IOException {
        dos.writeByte(NbtTagType.LONG_ARRAY.getId());
        writeString(dos, name);
        dos.writeInt(value.length);
        for (long l : value) {
            dos.writeLong(l);
        }
    }
    
    private static void writeListTag(DataOutputStream dos, String name, List<?> value) throws IOException {
        dos.writeByte(NbtTagType.LIST.getId());
        writeString(dos, name);
        
        if (value.isEmpty()) {
            dos.writeByte(NbtTagType.END.getId());
            dos.writeInt(0);
            return;
        }
        
        // Determine list type from first element
        Object first = value.get(0);
        NbtTagType listType;
        
        if (first instanceof Map) {
            listType = NbtTagType.COMPOUND;
        } else if (first instanceof String) {
            listType = NbtTagType.STRING;
        } else if (first instanceof Byte) {
            listType = NbtTagType.BYTE;
        } else if (first instanceof Short) {
            listType = NbtTagType.SHORT;
        } else if (first instanceof Integer) {
            listType = NbtTagType.INT;
        } else if (first instanceof Long) {
            listType = NbtTagType.LONG;
        } else if (first instanceof Float) {
            listType = NbtTagType.FLOAT;
        } else if (first instanceof Double) {
            listType = NbtTagType.DOUBLE;
        } else if (first instanceof byte[]) {
            listType = NbtTagType.BYTE_ARRAY;
        } else if (first instanceof int[]) {
            listType = NbtTagType.INT_ARRAY;
        } else if (first instanceof long[]) {
            listType = NbtTagType.LONG_ARRAY;
        } else {
            throw new IOException("Unsupported list element type: " + first.getClass());
        }
        
        dos.writeByte(listType.getId());
        dos.writeInt(value.size());
        
        for (Object item : value) {
            writeTagValue(dos, listType, item);
        }
    }
    
    private static void writeTagValue(DataOutputStream dos, NbtTagType type, Object value) throws IOException {
        switch (type) {
            case END:
                break;
            case BYTE:
                dos.writeByte((Byte) value);
                break;
            case SHORT:
                dos.writeShort((Short) value);
                break;
            case INT:
                dos.writeInt((Integer) value);
                break;
            case LONG:
                dos.writeLong((Long) value);
                break;
            case FLOAT:
                dos.writeFloat((Float) value);
                break;
            case DOUBLE:
                dos.writeDouble((Double) value);
                break;
            case BYTE_ARRAY:
                byte[] bytes = (byte[]) value;
                dos.writeInt(bytes.length);
                dos.write(bytes);
                break;
            case STRING:
                writeString(dos, (String) value);
                break;
            case LIST:
                writeListValue(dos, (List<?>) value);
                break;
            case COMPOUND:
                writeCompoundValue(dos, (Map<String, Object>) value);
                break;
            case INT_ARRAY:
                int[] ints = (int[]) value;
                dos.writeInt(ints.length);
                for (int i : ints) {
                    dos.writeInt(i);
                }
                break;
            case LONG_ARRAY:
                long[] longs = (long[]) value;
                dos.writeInt(longs.length);
                for (long l : longs) {
                    dos.writeLong(l);
                }
                break;
            default:
                throw new IOException("Unknown tag type: " + type);
        }
    }
    
    private static void writeListValue(DataOutputStream dos, List<?> value) throws IOException {
        // This is a simplified implementation
        // In a real implementation, you'd need to handle the list type properly
        for (Object item : value) {
            if (item instanceof Map) {
                writeCompoundValue(dos, (Map<String, Object>) item);
            } else if (item instanceof String) {
                writeString(dos, (String) item);
            } else if (item instanceof Integer) {
                dos.writeInt((Integer) item);
            }
            // Add other types as needed
        }
    }
    
    private static void writeCompoundValue(DataOutputStream dos, Map<String, Object> value) throws IOException {
        for (Map.Entry<String, Object> entry : value.entrySet()) {
            writeTag(dos, entry.getKey(), entry.getValue());
        }
        dos.writeByte(NbtTagType.END.getId());
    }
    
    private static String readString(DataInputStream dis) throws IOException {
        int length = dis.readShort() & 0xFFFF;
        byte[] bytes = new byte[length];
        dis.readFully(bytes);
        return new String(bytes, "UTF-8");
    }
    
    private static void writeString(DataOutputStream dos, String value) throws IOException {
        byte[] bytes = value.getBytes("UTF-8");
        if (bytes.length > 32767) {
            throw new IOException("String too long: " + bytes.length);
        }
        dos.writeShort(bytes.length);
        dos.write(bytes);
    }
    
    private static byte[] readByteArray(DataInputStream dis) throws IOException {
        int length = dis.readInt();
        byte[] bytes = new byte[length];
        dis.readFully(bytes);
        return bytes;
    }
    
    private static int[] readIntArray(DataInputStream dis) throws IOException {
        int length = dis.readInt();
        int[] array = new int[length];
        for (int i = 0; i < length; i++) {
            array[i] = dis.readInt();
        }
        return array;
    }
    
    private static long[] readLongArray(DataInputStream dis) throws IOException {
        int length = dis.readInt();
        long[] array = new long[length];
        for (int i = 0; i < length; i++) {
            array[i] = dis.readLong();
        }
        return array;
    }
    
    private static List<?> readListTag(DataInputStream dis) throws IOException {
        byte listTypeId = dis.readByte();
        int length = dis.readInt();
        NbtTagType listType = NbtTagType.fromId(listTypeId);
        
        List<Object> list = new ArrayList<>(length);
        for (int i = 0; i < length; i++) {
            list.add(readTagValue(dis, listType));
        }
        
        return list;
    }
}
