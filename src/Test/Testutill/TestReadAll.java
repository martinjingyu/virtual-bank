/**
 * Tests the initialization of the utility classes by checking various conditions.
 */
package Test.Testutill;

import Entity.*;
import org.junit.jupiter.api.Test;
import java.nio.file.Files;
import utill.Paths;
import utill.read.ReadAll;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for ReadAll utility.
 */
public class TestReadAll {

    /**
     * Tests if the ReadAll class correctly processes files and initializes the Kids
     * object.
     */
    @Test
    public void testReadAllCorrectlyProcessesFiles() {
        String testId = "222";
        Paths paths = new Paths(testId);

        try {
            // Attempt to decrypt each file and catch any exceptions to check which file's
            // content causes issues
            testDecryptFile(paths.product_path);
            testDecryptFile(paths.selectedProduct_path);
            testDecryptFile(paths.task_path);
            testDecryptFile(paths.message_path);
            testDecryptFile(paths.transactionHistory_path);
            paths.account_path.forEach(this::testDecryptFile);

            // Call the readall method
            Kids result = ReadAll.readall(testId);

            // Assert that the result is not null
            assertNotNull(result, "Kids object should not be null");
            assertNotNull(result.getProductList(), "Product list should not be null");
            assertNotNull(result.getTaskList(), "Task list should not be null");
            assertNotNull(result.getAccountManager(), "Account manager should not be null");

        } catch (Exception e) {
            fail("Exception should not be thrown during the test: " + e.getMessage());
        }
    }

    /**
     * Tests the decryption of a file to ensure it can be successfully decrypted.
     *
     * @param filePath the path of the file to decrypt
     */
    private void testDecryptFile(String filePath) {
        try {
            byte[] encryptedBytes = Files.readAllBytes(java.nio.file.Paths.get(filePath));
            String encryptedData = new String(encryptedBytes, java.nio.charset.StandardCharsets.UTF_8);

            System.out.println("Testing decryption of file: " + filePath);
            System.out.println("Encrypted data: " + encryptedData);

            String decryptedData = ReadAll.decryptFileContents(filePath);

            System.out.println("Decrypted data: " + decryptedData);
        } catch (Exception e) {
            System.err.println("Error decrypting file: " + filePath + ", " + e.getMessage());
            e.printStackTrace(); // Print stack trace for more detailed error information
            throw new RuntimeException("Decryption failed for file: " + filePath, e);
        }
    }
}
