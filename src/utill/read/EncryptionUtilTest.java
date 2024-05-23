package utill.read;

import utill.cryption.EncryptionUtil;

public class EncryptionUtilTest {

    private static final String ENCRYPTION_KEY = "1234567890123456";

    public static void main(String[] args) {
        try {
            // 设置环境变量
            System.setProperty("ENCRYPTION_KEY", ENCRYPTION_KEY);

            // 原始数据
            String data = "CA1, shop, -20, 2024:3:10 12:12:11\nTask, CA2, 5, 2024:3:11 12:12:11";

            // 加密数据
            String encryptedData = EncryptionUtil.encrypt(data);
            System.out.println("Encrypted data: " + encryptedData);

            // 解密数据
            String decryptedData = EncryptionUtil.decrypt(encryptedData);
            System.out.println("Decrypted data: " + decryptedData);

            // 验证解密数据是否与原始数据一致
            if (data.equals(decryptedData)) {
                System.out.println("Decryption successful and data matches.");
            } else {
                System.out.println("Decryption failed or data does not match.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

