package top.mc_plfd_host.Security;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.Signature;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.interfaces.ECPrivateKey;
import org.bouncycastle.jce.interfaces.ECPublicKey;
import org.bouncycastle.jce.spec.ECNamedCurveParameterSpec;

public class ECCUtils {
    
    private static final String[] ECC_CURVES = {
        "secp256r1", "secp384r1", "secp521r1", "brainpoolP256r1", 
        "brainpoolP384r1", "brainpoolP512r1", "sm2"
    };
    
    static {
        Security.addProvider(new BouncyCastleProvider());
    }
    
    public static ECCKeyPair generateKeyPair() {
        return generateKeyPair("secp256r1");
    }
    
    public static ECCKeyPair generateKeyPair(String curve) {
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("EC", "BC");
            ECGenParameterSpec ecSpec = new ECGenParameterSpec(curve);
            keyGen.initialize(ecSpec, new java.security.SecureRandom());
            KeyPair keyPair = keyGen.generateKeyPair();
            return new ECCKeyPair(keyPair.getPublic(), keyPair.getPrivate(), curve);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static ECCKeyPair generateKeyPair(int keySize) {
        String curve;
        switch (keySize) {
            case 256: curve = "secp256r1"; break;
            case 384: curve = "secp384r1"; break;
            case 521: curve = "secp521r1"; break;
            default: curve = "secp256r1";
        }
        return generateKeyPair(curve);
    }
    
    public static String encrypt(String plaintext, PublicKey publicKey) {
        try {
            javax.crypto.Cipher cipher = javax.crypto.Cipher.getInstance("ECIESwithSHA256andAES-CBC", "BC");
            cipher.init(javax.crypto.Cipher.ENCRYPT_MODE, publicKey);
            byte[] ciphertext = cipher.doFinal(plaintext.getBytes());
            return Base64.getEncoder().encodeToString(ciphertext);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static String decrypt(String ciphertext, PrivateKey privateKey) {
        try {
            byte[] ciphertextBytes = Base64.getDecoder().decode(ciphertext);
            javax.crypto.Cipher cipher = javax.crypto.Cipher.getInstance("ECIESwithSHA256andAES-CBC", "BC");
            cipher.init(javax.crypto.Cipher.DECRYPT_MODE, privateKey);
            byte[] plaintext = cipher.doFinal(ciphertextBytes);
            return new String(plaintext);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static String encrypt(String plaintext, ECCKeyPair keyPair) {
        return encrypt(plaintext, keyPair.getPublicKey());
    }
    
    public static String decrypt(String ciphertext, ECCKeyPair keyPair) {
        return decrypt(ciphertext, keyPair.getPrivateKey());
    }
    
    public static String sign(String data, PrivateKey privateKey) {
        try {
            Signature signature = Signature.getInstance("SHA256withECDSA", "BC");
            signature.initSign(privateKey);
            signature.update(data.getBytes());
            byte[] signatureBytes = signature.sign();
            return Base64.getEncoder().encodeToString(signatureBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static boolean verify(String data, String signatureStr, PublicKey publicKey) {
        try {
            Signature signature = Signature.getInstance("SHA256withECDSA", "BC");
            signature.initVerify(publicKey);
            signature.update(data.getBytes());
            byte[] signatureBytes = Base64.getDecoder().decode(signatureStr);
            return signature.verify(signatureBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static String sign(String data, ECCKeyPair keyPair) {
        return sign(data, keyPair.getPrivateKey());
    }
    
    public static boolean verify(String data, String signatureStr, ECCKeyPair keyPair) {
        return verify(data, signatureStr, keyPair.getPublicKey());
    }
    
    public static String getPublicKeyString(PublicKey publicKey) {
        return Base64.getEncoder().encodeToString(publicKey.getEncoded());
    }
    
    public static String getPrivateKeyString(PrivateKey privateKey) {
        return Base64.getEncoder().encodeToString(privateKey.getEncoded());
    }
    
    public static PublicKey getPublicKeyFromString(String keyString) {
        try {
            byte[] keyBytes = Base64.getDecoder().decode(keyString);
            X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("EC", "BC");
            return keyFactory.generatePublic(spec);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static PrivateKey getPrivateKeyFromString(String keyString) {
        try {
            byte[] keyBytes = Base64.getDecoder().decode(keyString);
            PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("EC", "BC");
            return keyFactory.generatePrivate(spec);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static ECCKeyPair getKeyPairFromStrings(String publicKeyString, String privateKeyString) {
        PublicKey publicKey = getPublicKeyFromString(publicKeyString);
        PrivateKey privateKey = getPrivateKeyFromString(privateKeyString);
        if (publicKey == null || privateKey == null) return null;
        return new ECCKeyPair(publicKey, privateKey, "secp256r1");
    }
    
    public static String[] generateKeyStrings() {
        return generateKeyStrings("secp256r1");
    }
    
    public static String[] generateKeyStrings(String curve) {
        ECCKeyPair keyPair = generateKeyPair(curve);
        if (keyPair == null) return null;
        return new String[]{
            keyPair.getPublicKeyString(),
            keyPair.getPrivateKeyString()
        };
    }
    
    public static String[] getSupportedCurves() {
        return ECC_CURVES.clone();
    }
    
    public static int getKeySize(String curve) {
        switch (curve) {
            case "secp256r1": case "brainpoolP256r1": case "sm2": return 256;
            case "secp384r1": case "brainpoolP384r1": return 384;
            case "secp521r1": case "brainpoolP512r1": return 521;
            default: return 256;
        }
    }
    
    public static String getCurveFromPublicKey(PublicKey publicKey) {
        if (publicKey instanceof ECPublicKey) {
            ECPublicKey ecPublicKey = (ECPublicKey) publicKey;
            org.bouncycastle.jce.spec.ECParameterSpec spec = ecPublicKey.getParameters();
            if (spec instanceof ECNamedCurveParameterSpec) {
                return ((ECNamedCurveParameterSpec) spec).getName();
            }
            return null;
        }
        return null;
    }
    
    public static String getCurveFromPrivateKey(PrivateKey privateKey) {
        if (privateKey instanceof ECPrivateKey) {
            ECPrivateKey ecPrivateKey = (ECPrivateKey) privateKey;
            org.bouncycastle.jce.spec.ECParameterSpec spec = ecPrivateKey.getParameters();
            if (spec instanceof ECNamedCurveParameterSpec) {
                return ((ECNamedCurveParameterSpec) spec).getName();
            }
            return null;
        }
        return null;
    }
    
    public static class ECCKeyPair {
        private final PublicKey publicKey;
        private final PrivateKey privateKey;
        private final String curve;
        
        public ECCKeyPair(PublicKey publicKey, PrivateKey privateKey, String curve) {
            this.publicKey = publicKey;
            this.privateKey = privateKey;
            this.curve = curve;
        }
        
        public PublicKey getPublicKey() { return publicKey; }
        public PrivateKey getPrivateKey() { return privateKey; }
        public String getCurve() { return curve; }
        
        public String getPublicKeyString() {
            return ECCUtils.getPublicKeyString(publicKey);
        }
        
        public String getPrivateKeyString() {
            return ECCUtils.getPrivateKeyString(privateKey);
        }
        
        public int getKeySize() {
            return ECCUtils.getKeySize(curve);
        }
        
        public String encrypt(String plaintext) {
            return ECCUtils.encrypt(plaintext, this);
        }
        
        public String decrypt(String ciphertext) {
            return ECCUtils.decrypt(ciphertext, this);
        }
        
        public String sign(String data) {
            return ECCUtils.sign(data, this);
        }
        
        public boolean verify(String data, String signature) {
            return ECCUtils.verify(data, signature, this);
        }
    }
}
