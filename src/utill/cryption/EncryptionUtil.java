package utill.cryption;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;

public class EncryptionUtil {

    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES";
    private static final String ENCRYPTION_KEY = System.getenv("ENCRYPTION_KEY");

    public static String encrypt(String data) throws Exception {
        if (ENCRYPTION_KEY == null || ENCRYPTION_KEY.isEmpty()) {
            throw new IllegalArgumentException("Encryption key not set in environment variables.");
        }
        Key secretKey = new SecretKeySpec(ENCRYPTION_KEY.getBytes(), ALGORITHM);
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedData = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encryptedData);
    }

    public static String decrypt(String encryptedData) throws Exception {
        if (ENCRYPTION_KEY == null || ENCRYPTION_KEY.isEmpty()) {
            throw new IllegalArgumentException("Encryption key not set in environment variables.");
        }
        Key secretKey = new SecretKeySpec(ENCRYPTION_KEY.getBytes(), ALGORITHM);
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decodedData = Base64.getDecoder().decode(encryptedData);
        byte[] decryptedData = cipher.doFinal(decodedData);
        return new String(decryptedData);
    }
}
