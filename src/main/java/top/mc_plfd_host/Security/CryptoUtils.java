package top.mc_plfd_host.Security;

import javax.crypto.*;
import javax.crypto.spec.*;
import java.security.*;
import java.security.spec.*;
import java.util.Base64;
import java.nio.charset.StandardCharsets;

public class CryptoUtils {
    
    private static final String AES_ALGORITHM = "AES";
    private static final String AES_TRANSFORMATION = "AES/CBC/PKCS5Padding";
    private static final String RSA_ALGORITHM = "RSA";
    private static final String RSA_TRANSFORMATION = "RSA/ECB/PKCS1Padding";
    
    // AES Encryption
    public static String encryptAES(String plaintext, String password) {
        return encryptAES(plaintext, password, 128);
    }
    
    public static String encryptAES(String plaintext, String password, int keySize) {
        try {
            SecretKeySpec secretKey = generateAESKey(password, keySize);
            if (secretKey == null) return null;
            
            Cipher cipher = Cipher.getInstance(AES_TRANSFORMATION);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            
            byte[] iv = cipher.getIV();
            byte[] ciphertext = cipher.doFinal(plaintext.getBytes(StandardCharsets.UTF_8));
            
            // Combine IV and ciphertext
            byte[] combined = new byte[iv.length + ciphertext.length];
            System.arraycopy(iv, 0, combined, 0, iv.length);
            System.arraycopy(ciphertext, 0, combined, iv.length, ciphertext.length);
            
            return Base64.getEncoder().encodeToString(combined);
        } catch (Exception e) {
            return null;
        }
    }
    
    public static String decryptAES(String encrypted, String password) {
        return decryptAES(encrypted, password, 128);
    }
    
    public static String decryptAES(String encrypted, String password, int keySize) {
        try {
            SecretKeySpec secretKey = generateAESKey(password, keySize);
            if (secretKey == null) return null;
            
            byte[] combined = Base64.getDecoder().decode(encrypted);
            
            // Extract IV and ciphertext
            byte[] iv = new byte[16];
            byte[] ciphertext = new byte[combined.length - 16];
            System.arraycopy(combined, 0, iv, 0, 16);
            System.arraycopy(combined, 16, ciphertext, 0, ciphertext.length);
            
            IvParameterSpec ivSpec = new IvParameterSpec(iv);
            Cipher cipher = Cipher.getInstance(AES_TRANSFORMATION);
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpec);
            
            byte[] decrypted = cipher.doFinal(ciphertext);
            return new String(decrypted, StandardCharsets.UTF_8);
        } catch (Exception e) {
            return null;
        }
    }
    
    private static SecretKeySpec generateAESKey(String password, int keySize) {
        try {
            byte[] key = password.getBytes(StandardCharsets.UTF_8);
            MessageDigest sha = MessageDigest.getInstance("SHA-256");
            key = sha.digest(key);
            
            // Use only the required number of bytes
            byte[] keyBytes = new byte[keySize / 8];
            System.arraycopy(key, 0, keyBytes, 0, keyBytes.length);
            
            return new SecretKeySpec(keyBytes, AES_ALGORITHM);
        } catch (Exception e) {
            return null;
        }
    }
    
    // RSA Encryption
    public static KeyPair generateRSAKeyPair(int keySize) {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(RSA_ALGORITHM);
            keyPairGenerator.initialize(keySize);
            return keyPairGenerator.generateKeyPair();
        } catch (Exception e) {
            return null;
        }
    }
    
    public static String encryptRSA(String plaintext, PublicKey publicKey) {
        try {
            Cipher cipher = Cipher.getInstance(RSA_TRANSFORMATION);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            
            byte[] ciphertext = cipher.doFinal(plaintext.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(ciphertext);
        } catch (Exception e) {
            return null;
        }
    }
    
    public static String decryptRSA(String encrypted, PrivateKey privateKey) {
        try {
            Cipher cipher = Cipher.getInstance(RSA_TRANSFORMATION);
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            
            byte[] ciphertext = Base64.getDecoder().decode(encrypted);
            byte[] decrypted = cipher.doFinal(ciphertext);
            return new String(decrypted, StandardCharsets.UTF_8);
        } catch (Exception e) {
            return null;
        }
    }
    
    public static String getPublicKeyString(PublicKey publicKey) {
        if (publicKey == null) return null;
        return Base64.getEncoder().encodeToString(publicKey.getEncoded());
    }
    
    public static String getPrivateKeyString(PrivateKey privateKey) {
        if (privateKey == null) return null;
        return Base64.getEncoder().encodeToString(privateKey.getEncoded());
    }
    
    public static PublicKey getPublicKeyFromString(String keyString) {
        try {
            if (keyString == null) return null;
            byte[] keyBytes = Base64.getDecoder().decode(keyString);
            X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
            return keyFactory.generatePublic(spec);
        } catch (Exception e) {
            return null;
        }
    }
    
    public static PrivateKey getPrivateKeyFromString(String keyString) {
        try {
            if (keyString == null) return null;
            byte[] keyBytes = Base64.getDecoder().decode(keyString);
            PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
            return keyFactory.generatePrivate(spec);
        } catch (Exception e) {
            return null;
        }
    }
    
    // Caesar Cipher (simple substitution cipher)
    public static String encryptCaesar(String plaintext, int shift) {
        if (plaintext == null) return null;
        StringBuilder result = new StringBuilder();
        
        for (int i = 0; i < plaintext.length(); i++) {
            char c = plaintext.charAt(i);
            
            if (Character.isUpperCase(c)) {
                result.append((char) ((c - 'A' + shift) % 26 + 'A'));
            } else if (Character.isLowerCase(c)) {
                result.append((char) ((c - 'a' + shift) % 26 + 'a'));
            } else {
                result.append(c);
            }
        }
        
        return result.toString();
    }
    
    public static String decryptCaesar(String ciphertext, int shift) {
        return encryptCaesar(ciphertext, 26 - shift);
    }
    
    // Vigenère Cipher
    public static String encryptVigenere(String plaintext, String key) {
        if (plaintext == null || key == null) return null;
        
        StringBuilder result = new StringBuilder();
        String keyUpper = key.toUpperCase();
        int keyIndex = 0;
        
        for (int i = 0; i < plaintext.length(); i++) {
            char c = plaintext.charAt(i);
            
            if (Character.isUpperCase(c)) {
                int shift = keyUpper.charAt(keyIndex % keyUpper.length()) - 'A';
                result.append((char) ((c - 'A' + shift) % 26 + 'A'));
                keyIndex++;
            } else if (Character.isLowerCase(c)) {
                int shift = keyUpper.charAt(keyIndex % keyUpper.length()) - 'A';
                result.append((char) ((c - 'a' + shift) % 26 + 'a'));
                keyIndex++;
            } else {
                result.append(c);
            }
        }
        
        return result.toString();
    }
    
    public static String decryptVigenere(String ciphertext, String key) {
        if (ciphertext == null || key == null) return null;
        
        StringBuilder result = new StringBuilder();
        String keyUpper = key.toUpperCase();
        int keyIndex = 0;
        
        for (int i = 0; i < ciphertext.length(); i++) {
            char c = ciphertext.charAt(i);
            
            if (Character.isUpperCase(c)) {
                int shift = keyUpper.charAt(keyIndex % keyUpper.length()) - 'A';
                result.append((char) ((c - 'A' - shift + 26) % 26 + 'A'));
                keyIndex++;
            } else if (Character.isLowerCase(c)) {
                int shift = keyUpper.charAt(keyIndex % keyUpper.length()) - 'A';
                result.append((char) ((c - 'a' - shift + 26) % 26 + 'a'));
                keyIndex++;
            } else {
                result.append(c);
            }
        }
        
        return result.toString();
    }
    
    // XOR Cipher
    public static String encryptXOR(String plaintext, String key) {
        if (plaintext == null || key == null) return null;
        
        byte[] plaintextBytes = plaintext.getBytes(StandardCharsets.UTF_8);
        byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);
        
        byte[] result = new byte[plaintextBytes.length];
        for (int i = 0; i < plaintextBytes.length; i++) {
            result[i] = (byte) (plaintextBytes[i] ^ keyBytes[i % keyBytes.length]);
        }
        
        return Base64.getEncoder().encodeToString(result);
    }
    
    public static String decryptXOR(String encrypted, String key) {
        if (encrypted == null || key == null) return null;
        
        byte[] encryptedBytes = Base64.getDecoder().decode(encrypted);
        byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);
        
        byte[] result = new byte[encryptedBytes.length];
        for (int i = 0; i < encryptedBytes.length; i++) {
            result[i] = (byte) (encryptedBytes[i] ^ keyBytes[i % keyBytes.length]);
        }
        
        return new String(result, StandardCharsets.UTF_8);
    }
    
    // Simple XOR with single byte key
    public static String encryptXOR(String plaintext, byte key) {
        if (plaintext == null) return null;
        
        byte[] plaintextBytes = plaintext.getBytes(StandardCharsets.UTF_8);
        byte[] result = new byte[plaintextBytes.length];
        
        for (int i = 0; i < plaintextBytes.length; i++) {
            result[i] = (byte) (plaintextBytes[i] ^ key);
        }
        
        return Base64.getEncoder().encodeToString(result);
    }
    
    public static String decryptXOR(String encrypted, byte key) {
        return encryptXOR(encrypted, key); // XOR is its own inverse
    }
    
    // ROT13 (simple Caesar cipher with shift 13)
    public static String encryptROT13(String text) {
        return encryptCaesar(text, 13);
    }
    
    public static String decryptROT13(String text) {
        return decryptCaesar(text, 13);
    }
    
    // Atbash Cipher (reverse alphabet)
    public static String encryptAtbash(String plaintext) {
        if (plaintext == null) return null;
        StringBuilder result = new StringBuilder();
        
        for (int i = 0; i < plaintext.length(); i++) {
            char c = plaintext.charAt(i);
            
            if (Character.isUpperCase(c)) {
                result.append((char) ('Z' - (c - 'A')));
            } else if (Character.isLowerCase(c)) {
                result.append((char) ('z' - (c - 'a')));
            } else {
                result.append(c);
            }
        }
        
        return result.toString();
    }
    
    public static String decryptAtbash(String ciphertext) {
        return encryptAtbash(ciphertext); // Atbash is its own inverse
    }
    
    // Generate random key for XOR cipher
    public static String generateRandomKey(int length) {
        try {
            SecureRandom random = new SecureRandom();
            byte[] key = new byte[length];
            random.nextBytes(key);
            return Base64.getEncoder().encodeToString(key).substring(0, length);
        } catch (Exception e) {
            return null;
        }
    }
    
    // Password-based key derivation
    public static String deriveKey(String password, String salt, int iterations, int keyLength) {
        try {
            if (password == null || salt == null) return null;
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec spec = new PBEKeySpec(password.toCharArray(), salt.getBytes(StandardCharsets.UTF_8), iterations, keyLength);
            SecretKey tmp = factory.generateSecret(spec);
            return Base64.getEncoder().encodeToString(tmp.getEncoded());
        } catch (Exception e) {
            return null;
        }
    }
    
    // Simple password strength checker
    public static PasswordStrength checkPasswordStrength(String password) {
        if (password == null) return new PasswordStrength(0, "Empty password");
        
        int score = 0;
        StringBuilder feedback = new StringBuilder();
        
        // Length check
        if (password.length() >= 8) {
            score += 1;
        } else {
            feedback.append("Password should be at least 8 characters. ");
        }
        
        if (password.length() >= 12) {
            score += 1;
        }
        
        // Complexity checks
        boolean hasLower = password.matches(".*[a-z].*");
        boolean hasUpper = password.matches(".*[A-Z].*");
        boolean hasDigit = password.matches(".*\\d.*");
        boolean hasSpecial = password.matches(".*[!@#$%^&*()_+=\\-\\[\\]{};':\"\\\\|,.<>/?].*");
        
        if (hasLower) score++;
        else feedback.append("Add lowercase letters. ");
        
        if (hasUpper) score++;
        else feedback.append("Add uppercase letters. ");
        
        if (hasDigit) score++;
        else feedback.append("Add numbers. ");
        
        if (hasSpecial) score++;
        else feedback.append("Add special characters. ");
        
        // Common patterns check
        if (password.toLowerCase().contains("password") || 
            password.toLowerCase().contains("123456") ||
            password.toLowerCase().contains("qwerty")) {
            score--;
            feedback.append("Avoid common patterns. ");
        }
        
        String strength;
        if (score >= 5) {
            strength = "Very Strong";
        } else if (score >= 4) {
            strength = "Strong";
        } else if (score >= 3) {
            strength = "Medium";
        } else if (score >= 2) {
            strength = "Weak";
        } else {
            strength = "Very Weak";
        }
        
        return new PasswordStrength(score, strength + " - " + feedback.toString());
    }
    
    public static class PasswordStrength {
        private final int score;
        private final String message;
        
        public PasswordStrength(int score, String message) {
            this.score = score;
            this.message = message;
        }
        
        public int getScore() { return score; }
        public String getMessage() { return message; }
        
        @Override
        public String toString() {
            return message;
        }
    }
}
