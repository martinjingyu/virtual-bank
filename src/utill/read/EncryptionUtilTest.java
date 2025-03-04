package utill.read;

import utill.cryption.EncryptionUtil;

public class EncryptionUtilTest {

    private static final String ENCRYPTION_KEY = "1234567890123456";

    public static void main(String[] args) {
        try {
            // 设置环境变量
            System.setProperty("ENCRYPTION_KEY", ENCRYPTION_KEY);

            // 原始数据
            String data = "CA1,shop,-20.0,2024/3/10 12:12:11\n" +
                    "Task,CA2,5.0,2024/3/11 12:12:11\n" +
                    "CA2,CA1,3.0,2024/5/28 03:04:33\n" +
                    "SA1,CA1,10.0,2024/5/28 03:04:39\n" +
                    "SA4,CA2,10.08,2024/5/28 03:04:45\n" +
                    "CA1,Sa44,-12.0,2024/5/28 03:04:55\n" +
                    "CA2,shop,-4.0,2024/5/28 03:05:18\n" +
                    "CA2,shop,-3.0,2024/5/28 03:05:24\n" +
                    "CA2,shop,-4.0,2024/5/28 03:05:27\n" +
                    "parent,CA2,11.0,2024/5/28 03:08:09\n";
            String data1 ="Martin,200.0,1.5,3.0,4.25";
            String data2 ="SA1,10.0,10.0,2024-01-10T12:12:40,2024-10-10T12:12:40,15 days\n" +
                    "SA2,10.0,10.0,2024-01-10T12:12:40,2024-10-10T12:12:40, 1 month\n" +
                    "SA3,10.0,10.0,2024-01-10T12:12:40,2024-10-10T12:12:40,3 months\n" +
                    "SA4,10.0,10.0,2024-01-10T12:12:40,2024-02-10T12:14:40,15 days";

            // 加密数据
            String encryptedData = EncryptionUtil.encrypt(data);
            System.out.println("Encrypted data: " + encryptedData);

            // 解密数据
            String encryptedTest = "fM/1I5UjyrzbRmfEwmQNyhjB/pU4eqThR8OQg7Yfrfe6jUq35HYdvuJ2IbPO7C9zVFBDNKZ72Dp/iult4J54zy/VOczHAXRSvfYFV9jKirKrhbYe3DgnNFuivIXOGkdvC3lYI1rSWI6mVG0tTECMmNBaoj2ACIeqvkvMANP/+Yu07XwmT4VG4wW9UuWaXKei0ntMM5nTfYZKzZxkZ//1FfqVaijQ5W9iq39j/hrV2zXL7vhGaDRcVURGY8krW/NvZ+GyIcltpxogHa1qooyTMYUUgUUtYRCRTVdtHzIlTfg=";
            String asd ="bWvisLYHtAYhjehmOcEjrjNLo9xILbwwbJ/3wijYqF9J5Whn8vtokeFElmxHHJuofNptbgv/HbSdruRA2FEs4A==";

            String decryptedData = EncryptionUtil.decrypt(asd);
            System.out.println("Decrypted data: " + decryptedData);

//            // 验证解密数据是否与原始数据一致
//            if (data.equals(decryptedData)) {
//                System.out.println("Decryption successful and data matches.");
//            } else {
//                System.out.println("Decryption failed or data does not match.");
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

