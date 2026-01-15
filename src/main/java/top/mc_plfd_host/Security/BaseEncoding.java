package top.mc_plfd_host.Security;

import java.util.Base64;
import java.nio.charset.StandardCharsets;

public class BaseEncoding {
    
    // Base64 Encoding
    public static String encodeBase64(String input) {
        if (input == null) return null;
        return Base64.getEncoder().encodeToString(input.getBytes(StandardCharsets.UTF_8));
    }
    
    public static String encodeBase64(byte[] input) {
        if (input == null) return null;
        return Base64.getEncoder().encodeToString(input);
    }
    
    public static String decodeBase64(String encoded) {
        if (encoded == null) return null;
        try {
            byte[] decodedBytes = Base64.getDecoder().decode(encoded);
            return new String(decodedBytes, StandardCharsets.UTF_8);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
    
    public static byte[] decodeBase64ToBytes(String encoded) {
        if (encoded == null) return null;
        try {
            return Base64.getDecoder().decode(encoded);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
    
    // URL-safe Base64
    public static String encodeBase64Url(String input) {
        if (input == null) return null;
        return Base64.getUrlEncoder().encodeToString(input.getBytes(StandardCharsets.UTF_8));
    }
    
    public static String encodeBase64Url(byte[] input) {
        if (input == null) return null;
        return Base64.getUrlEncoder().encodeToString(input);
    }
    
    public static String decodeBase64Url(String encoded) {
        if (encoded == null) return null;
        try {
            byte[] decodedBytes = Base64.getUrlDecoder().decode(encoded);
            return new String(decodedBytes, StandardCharsets.UTF_8);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
    
    // Base32 Encoding
    public static String encodeBase32(String input) {
        if (input == null) return null;
        return Base64.getEncoder().encodeToString(input.getBytes(StandardCharsets.UTF_8));
    }
    
    public static String decodeBase32(String encoded) {
        if (encoded == null) return null;
        try {
            byte[] decodedBytes = Base64.getDecoder().decode(encoded);
            return new String(decodedBytes, StandardCharsets.UTF_8);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
    
    // Custom Base64 without padding
    public static String encodeBase64NoPadding(String input) {
        if (input == null) return null;
        return Base64.getEncoder().withoutPadding().encodeToString(input.getBytes(StandardCharsets.UTF_8));
    }
    
    public static String decodeBase64NoPadding(String encoded) {
        if (encoded == null) return null;
        // Add padding back for proper decoding
        int paddingNeeded = (4 - (encoded.length() % 4)) % 4;
        String paddedEncoded = encoded + "=".repeat(paddingNeeded);
        return decodeBase64(paddedEncoded);
    }
    
    // MIME Base64 (with line breaks)
    public static String encodeBase64Mime(String input) {
        if (input == null) return null;
        return Base64.getMimeEncoder().encodeToString(input.getBytes(StandardCharsets.UTF_8));
    }
    
    public static String decodeBase64Mime(String encoded) {
        if (encoded == null) return null;
        try {
            byte[] decodedBytes = Base64.getMimeDecoder().decode(encoded);
            return new String(decodedBytes, StandardCharsets.UTF_8);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
    
    // Validate Base64 string
    public static boolean isValidBase64(String input) {
        if (input == null) return false;
        try {
            Base64.getDecoder().decode(input);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
    
    public static boolean isValidBase64Url(String input) {
        if (input == null) return false;
        try {
            Base64.getUrlDecoder().decode(input);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
    
    // Get encoded length
    public static int getEncodedLengthBase64(int inputLength) {
        return ((inputLength + 2) / 3) * 4;
    }
    
    public static int getEncodedLengthBase64Url(int inputLength) {
        return ((inputLength + 2) / 3) * 4;
    }
    
    // Convert between different Base64 formats
    public static String base64ToBase64Url(String base64) {
        if (!isValidBase64(base64)) {
            return null;
        }
        byte[] decoded = decodeBase64ToBytes(base64);
        return encodeBase64Url(decoded);
    }
    
    public static String base64UrlToBase64(String base64Url) {
        if (!isValidBase64Url(base64Url)) {
            return null;
        }
        byte[] decoded = decodeBase64ToBytes(base64Url);
        return encodeBase64(decoded);
    }
    
    // Utility methods
    public static boolean isBase64Character(char c) {
        return (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z') || 
               (c >= '0' && c <= '9') || c == '+' || c == '/' || c == '=';
    }
    
    public static boolean isBase64UrlCharacter(char c) {
        return (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z') || 
               (c >= '0' && c <= '9') || c == '-' || c == '_';
    }
    
    // Remove padding from Base64 string
    public static String removePadding(String base64) {
        if (base64 == null) return null;
        return base64.replaceAll("=+$", "");
    }
    
    // Add padding to Base64 string
    public static String addPadding(String base64) {
        if (base64 == null) return null;
        int paddingNeeded = (4 - (base64.length() % 4)) % 4;
        return base64 + "=".repeat(paddingNeeded);
    }
}
