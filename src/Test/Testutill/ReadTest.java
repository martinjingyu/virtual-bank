package Test.Testutill;

import static org.junit.jupiter.api.Assertions.*;

import Entity.Product;
import Entity.ProductList;
import Entity.Kids;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import utill.cryption.EncryptionUtil;

import java.nio.file.Files;
import java.nio.file.Paths;
import utill.read.ReadAll;

import static org.junit.jupiter.api.Assertions.*;

class ReadTest {

    private static final String TEST_FILE_PATH = "data/Kids/222/Product.txt";
    private static final String TEST_DATA = "Apple,3.0\nbee,1.0\nT-shirt,5.0\n";

    @BeforeAll
    static void setup() throws Exception {
        // 读取环境变量
        String encryptionKey = System.getenv("ENCRYPTION_KEY");
        System.out.println("Environment Variable ENCRYPTION_KEY: " + encryptionKey);

        // 确保加密密钥已设置
        if (encryptionKey == null || encryptionKey.isEmpty()) {
            throw new IllegalArgumentException("Encryption key not set in environment variables.");
        }

        // Encrypt the test data and write to file
        String encryptedData = EncryptionUtil.encrypt(TEST_DATA);
        Files.write(Paths.get(TEST_FILE_PATH), encryptedData.getBytes(java.nio.charset.StandardCharsets.UTF_8));
    }

    @Test
    void testReadAll() throws Exception {
        // 读取环境变量
        String encryptionKey = System.getenv("ENCRYPTION_KEY");
        System.out.println("Environment Variable ENCRYPTION_KEY: " + encryptionKey);

        // 确保加密密钥已设置
        if (encryptionKey == null || encryptionKey.isEmpty()) {
            throw new IllegalArgumentException("Encryption key not set in environment variables.");
        }

        Kids kid = ReadAll.readall("222");

        ProductList productList = kid.getProductList();

        // Verify the product list size
        assertEquals(3, productList.getAllProducts().size());

        // Verify the product details
        Product product1 = productList.getAllProducts().get(0);
        assertEquals("Apple", product1.getName());
        assertEquals(3.0, product1.getPrice());

        Product product2 = productList.getAllProducts().get(1);
        assertEquals("bee", product2.getName());
        assertEquals(1.0, product2.getPrice());

        Product product3 = productList.getAllProducts().get(2);
        assertEquals("T-shirt", product3.getName());
        assertEquals(5.0, product3.getPrice());
    }
}
