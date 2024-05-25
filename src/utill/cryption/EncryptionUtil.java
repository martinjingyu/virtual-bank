package utill.cryption;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.Key;
import java.util.Base64;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * The EncryptionUtil class provides utility methods for encryption, decryption, and hash generation.
 */
public class EncryptionUtil {

    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES";
    private static final String ENCRYPTION_KEY = "1234567890123456";

    /**
     * Encrypts the given data using AES encryption algorithm.
     *
     * @param data The data to be encrypted.
     * @return The encrypted data.
     * @throws Exception If an error occurs during encryption.
     */
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

    /**
     * Decrypts the given encrypted data using AES decryption algorithm.
     *
     * @param encryptedData The encrypted data to be decrypted.
     * @return The decrypted data.
     * @throws Exception If an error occurs during decryption.
     */
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

    /**
     * Generates a hash value for the given data using SHA-256 hashing algorithm.
     *
     * @param data The data for which the hash value is to be generated.
     * @return The hash value of the data.
     * @throws NoSuchAlgorithmException If the specified hashing algorithm is not available.
     */
    public static String generateHash(String data) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(data.getBytes());
        return Base64.getEncoder().encodeToString(hash);
    }

    /**
     * Main method to encrypt a file.
     *
     * @param args Command-line arguments. The file path to be encrypted.
     */
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java utill.cryption.EncryptionUtil <file_path>");
            return;
        }

        String filePath = args[0];
        try {
            // Read file content
            String content = readFile(filePath);

            // Encrypt file content
            String encryptedContent = encrypt(content);

            // Write encrypted content back to the file or save as a new file
            String encryptedFilePath = filePath + ".encrypted";
            writeFile(encryptedFilePath, encryptedContent);

            System.out.println("File encrypted successfully. Encrypted file path: " + encryptedFilePath);
        } catch (Exception e) {
            System.out.println("Error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static String readFile(String filePath) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                contentBuilder.append(line).append("\n");
            }
        }
        return contentBuilder.toString();
    }

    private static void writeFile(String filePath, String content) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            bw.write(content);
        }
    }
}
