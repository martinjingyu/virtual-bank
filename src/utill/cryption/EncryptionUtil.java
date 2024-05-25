package utill.cryption;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.Key;
import java.util.Base64;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Utility class for encryption and decryption using AES algorithm.
 */
public class EncryptionUtil {

    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES";
    private static final String ENCRYPTION_KEY = "1234567890123456"; // Example key, should be securely managed

    /**
     * Encrypts the given data using AES algorithm.
     *
     * @param data the data to be encrypted
     * @return the encrypted data as a Base64 encoded string
     * @throws Exception if any encryption error occurs
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
     * Decrypts the given encrypted data using AES algorithm.
     *
     * @param encryptedData the data to be decrypted
     * @return the decrypted data as a string
     * @throws Exception if any decryption error occurs
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
     * Generates a SHA-256 hash of the given data.
     *
     * @param data the data to be hashed
     * @return the hashed data as a Base64 encoded string
     * @throws NoSuchAlgorithmException if SHA-256 algorithm is not available
     */
    public static String generateHash(String data) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(data.getBytes());
        return Base64.getEncoder().encodeToString(hash);
    }

    /**
     * Main method to encrypt the contents of a file specified by the command line argument.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java utill.cryption.EncryptionUtil <file_path>");
            return;
        }

        String filePath = args[0];
        try {
            // Read the file content
            String content = readFile(filePath);

            // Encrypt the file content
            String encryptedContent = encrypt(content);

            // Save the encrypted content to a new file
            String encryptedFilePath = filePath + ".encrypted";
            writeFile(encryptedFilePath, encryptedContent);

            System.out.println("File encrypted successfully. Encrypted file path: " + encryptedFilePath);
        } catch (Exception e) {
            System.out.println("Error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Reads the content of a file.
     *
     * @param filePath the path of the file to read
     * @return the content of the file as a string
     * @throws IOException if any I/O error occurs
     */
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

    /**
     * Writes content to a file.
     *
     * @param filePath the path of the file to write to
     * @param content the content to write to the file
     * @throws IOException if any I/O error occurs
     */
    private static void writeFile(String filePath, String content) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            bw.write(content);
        }
    }
}
