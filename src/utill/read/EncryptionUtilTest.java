package utill.read;

import utill.cryption.EncryptionUtil;

public class EncryptionUtilTest {

    private static final String ENCRYPTION_KEY = "1234567890123456";

    public static void main(String[] args) {
        try {
            // 设置环境变量
            System.setProperty("ENCRYPTION_KEY", ENCRYPTION_KEY);

            // 原始数据
            String data = "Independent study1*11.0*ToBeConfirmed*Please sweep the floor in the living room and kitchen. Make sure to remove all dust and debris. Please also ensure that the corners are well cleaned.*CA1\n" +
                    "Independent study2*12.0*ToBeConfirmed*Complete your math homework and review the chapter on algebra.*CA1\n" +
                    "Independent study3*13.0*ToBeTaken*Practice playing a song on your musical instrument for at least 30 minutes, focusing on improving your technique and accuracy with each session.*CA1\n" +
                    "Independent study4*14.0*Taken*Wash the dishes after dinner and wipe down the kitchen counters.*CA1\n" +
                    "Independent study5*15.0*Taken*Help with laundry by sorting clothes into appropriate loads, loading the washing machine, transferring clothes to the dryer, and folding clean clothes neatly.*CA1\n" +
                    "Independent study6*16.0*Taken*Study for your upcoming science test by reviewing your notes, creating flashcards for key concepts, and completing practice questions for better retention.*CA1\n" +
                    "Independent study7*17.0*ToBeConfirmed*Help with grocery shopping by making a list of needed items, checking off each item as you find it, and helping to carry the groceries into the house.*CA1\n" +
                    "Independent study8*18.0*Taken*Complete a 20-minute workout session, including stretching, strength exercises, and a short cardio routine to improve your overall fitness and health.*CA1\n" +
                    "Independent study9*19.0*ToBeConfirmed*Write a thank-you note to your teacher, expressing your gratitude for their support and guidance, and include specific examples of how they've helped you.*CA1";

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

