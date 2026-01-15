package top.mc_plfd_host.Security;

import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.HexFormat;

public class HashUtils {
    
    private static final String HEX_CHARS = "0123456789ABCDEF";
    
    public static String md5(String input) {
        return hash(input, "MD5");
    }
    
    public static String sha1(String input) {
        return hash(input, "SHA-1");
    }
    
    public static String sha256(String input) {
        return hash(input, "SHA-256");
    }
    
    public static String sha384(String input) {
        return hash(input, "SHA-384");
    }
    
    public static String sha512(String input) {
        return hash(input, "SHA-512");
    }
    
    public static String sha3_256(String input) {
        return hash(input, "SHA3-256");
    }
    
    public static String sha3_512(String input) {
        return hash(input, "SHA3-512");
    }
    
    public static String md5(byte[] input) {
        return hash(input, "MD5");
    }
    
    public static String sha1(byte[] input) {
        return hash(input, "SHA-1");
    }
    
    public static String sha256(byte[] input) {
        return hash(input, "SHA-256");
    }
    
    public static String sha512(byte[] input) {
        return hash(input, "SHA-512");
    }
    
    public static String sha3_256(byte[] input) {
        return hash(input, "SHA3-256");
    }
    
    public static String sha3_512(byte[] input) {
        return hash(input, "SHA3-512");
    }
    
    private static String hash(String input, String algorithm) {
        if (input == null) return null;
        return hash(input.getBytes(), algorithm);
    }
    
    private static String hash(byte[] input, String algorithm) {
        if (input == null) return null;
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            byte[] hashBytes = md.digest(input);
            return bytesToHex(hashBytes);
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }
    
    public static String hmacMD5(String data, String key) {
        return hmac(data, key, "HmacMD5");
    }
    
    public static String hmacSHA256(String data, String key) {
        return hmac(data, key, "HmacSHA256");
    }
    
    public static String hmacSHA512(String data, String key) {
        return hmac(data, key, "HmacSHA512");
    }
    
    private static String hmac(String data, String key, String algorithm) {
        if (data == null || key == null) return null;
        try {
            javax.crypto.spec.SecretKeySpec secretKey = new javax.crypto.spec.SecretKeySpec(
                key.getBytes(), algorithm);
            javax.crypto.Mac mac = javax.crypto.Mac.getInstance(algorithm);
            mac.init(secretKey);
            byte[] hmacBytes = mac.doFinal(data.getBytes());
            return bytesToHex(hmacBytes);
        } catch (Exception e) {
            return null;
        }
    }
    
    public static String pbkdf2(String password, String salt, int iterations, int keyLength) {
        try {
            javax.crypto.SecretKeyFactory factory = javax.crypto.SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            javax.crypto.spec.PBEKeySpec spec = new javax.crypto.spec.PBEKeySpec(
                password.toCharArray(), salt.getBytes(), iterations, keyLength);
            byte[] hashBytes = factory.generateSecret(spec).getEncoded();
            return bytesToHex(hashBytes);
        } catch (Exception e) {
            return null;
        }
    }
    
    public static int crc32(String input) {
        return crc32(input.getBytes());
    }
    
    public static int crc32(byte[] input) {
        if (input == null) return 0;
        java.util.zip.CRC32 crc = new java.util.zip.CRC32();
        crc.update(input);
        return (int) crc.getValue();
    }
    
    public static int adler32(String input) {
        return adler32(input.getBytes());
    }
    
    public static int adler32(byte[] input) {
        if (input == null) return 0;
        java.util.zip.Adler32 adler = new java.util.zip.Adler32();
        adler.update(input);
        return (int) adler.getValue();
    }
    
    public static String hashFile(String filePath, String algorithm) {
        try (InputStream is = new FileInputStream(filePath)) {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            byte[] buffer = new byte[8192];
            int read;
            while ((read = is.read(buffer)) != -1) {
                md.update(buffer, 0, read);
            }
            return bytesToHex(md.digest());
        } catch (Exception e) {
            return null;
        }
    }
    
    public static String md5File(String filePath) {
        return hashFile(filePath, "MD5");
    }
    
    public static String sha256File(String filePath) {
        return hashFile(filePath, "SHA-256");
    }
    
    public static String sha512File(String filePath) {
        return hashFile(filePath, "SHA-512");
    }
    
    public static boolean verifyHash(String input, String hash, String algorithm) {
        String computed = hash(input, algorithm);
        if (computed == null) return false;
        return computed.equalsIgnoreCase(hash);
    }
    
    public static boolean verifyHash(byte[] input, String hash, String algorithm) {
        String computed = hash(input, algorithm);
        if (computed == null) return false;
        return computed.equalsIgnoreCase(hash);
    }
    
    public static boolean constantTimeEquals(String a, String b) {
        if (a == null || b == null) return false;
        if (a.length() != b.length()) return false;
        int result = 0;
        for (int i = 0; i < a.length(); i++) {
            result |= a.charAt(i) ^ b.charAt(i);
        }
        return result == 0;
    }
    
    public static String generateSalt(int length) {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[length];
        random.nextBytes(salt);
        return bytesToHex(salt);
    }
    
    public static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(HEX_CHARS.charAt((b >> 4) & 0x0F));
            sb.append(HEX_CHARS.charAt(b & 0x0F));
        }
        return sb.toString();
    }
    
    public static byte[] hexToBytes(String hex) {
        if (hex == null || hex.length() % 2 != 0) return null;
        byte[] bytes = new byte[hex.length() / 2];
        for (int i = 0; i < hex.length(); i += 2) {
            bytes[i / 2] = (byte) (HEX_CHARS.indexOf(hex.charAt(i)) << 4 | 
                                   HEX_CHARS.indexOf(hex.charAt(i + 1)));
        }
        return bytes;
    }
    
    public static String bytesToBase64(byte[] bytes) {
        return java.util.Base64.getEncoder().encodeToString(bytes);
    }
    
    public static byte[] base64ToBytes(String base64) {
        try {
            return java.util.Base64.getDecoder().decode(base64);
        } catch (Exception e) {
            return null;
        }
    }
}
