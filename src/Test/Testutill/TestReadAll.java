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

public class TestReadAll {

    @Test
    public void testReadAllCorrectlyProcessesFiles() {
        String testId = "222";
        Paths paths = new Paths(testId);

        try {
            // 尝试解密每个文件，并捕获任何异常，以便检查是哪个文件的内容导致问题
            testDecryptFile(paths.product_path);
            testDecryptFile(paths.selectedProduct_path);
            testDecryptFile(paths.task_path);
            testDecryptFile(paths.message_path);
            testDecryptFile(paths.transactionHistory_path);
            paths.account_path.forEach(this::testDecryptFile);

            // 调用readall方法
            Kids result = ReadAll.readall(testId);

            // 断言结果不为空
            assertNotNull(result, "Kids object should not be null");
            assertNotNull(result.getProductList(), "Product list should not be null");
            assertNotNull(result.getTaskList(), "Task list should not be null");
            assertNotNull(result.getAccountManager(), "Account manager should not be null");

        } catch (Exception e) {
            fail("Exception should not be thrown during the test: " + e.getMessage());
        }
    }

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
            e.printStackTrace(); // 打印堆栈跟踪以便更详细的错误信息
            throw new RuntimeException("Decryption failed for file: " + filePath, e);
        }
    }
}
