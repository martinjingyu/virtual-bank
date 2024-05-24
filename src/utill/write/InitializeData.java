/**
 * Title      : InitializeData.java
 * Description: This class is used to create files and write messages into files.
 * Copyright  : Copyright (c) 2024/5/9
 * @author      Weida Peng
 * @version     1.0
 */
package utill.write;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.io.File;
import utill.cryption.*;

public class InitializeData {

    public static void writeTextToFile(String text, String filePath) {

        try {
            System.out.println(text);
            text += "\n";
            System.out.println(text);
            String content = decryptFileContents(filePath);
            content += text;
            System.out.println(content);
            String encryptedContent = EncryptionUtil.encrypt(content);
            System.out.println(encryptedContent);
            FileWriter fw = new FileWriter(filePath, false);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write(encryptedContent);
            bw.close();

            System.out.println("The contents have been successfully written to the file." + text);
        } catch (Exception e) {
            System.err.println("Error writing to file:" + e.getMessage());
        }
    }

    public static void createFolderAndFiles(String folderPath) {
        folderPath = "data/Kids/" + folderPath;
        File folder = new File(folderPath);
        if (!folder.exists()) {
            boolean isCreated = folder.mkdirs();
            if (!isCreated) {
                System.out.println("Unable to create folder:" + folderPath);
                return;
            }
        }

        String[] fileNames = { "Account.txt", "CurrentAccount.txt",
                "Message.txt", "Product.txt", "SavingAccount.txt",
                "SelectedProduct.txt", "Task.txt", "TransactionHistory.txt",
                "User.txt" };
        for (String fileName : fileNames) {
            File file = new File(folder, fileName);
            try {
                if (!file.exists()) {
                    boolean isFileCreated = file.createNewFile();
                    if (!isFileCreated) {
                        System.out.println("Unable to create folder:" + fileName);
                    }
                }
            } catch (IOException e) {
                System.out.println("Unable to create folder:" + e.getMessage());
            }
        }

        // // 这是例子！！！！

        // File aFile = new File(folder, "User.txt");
        // try (FileWriter writer = new FileWriter(aFile)) {
        // writer.write(EncryptionUtil.encrypt(folderPath + ",200.0,1.5,3.0,4.25")); //
        // folderPath是id
        // } catch (Exception e) {
        // System.out.println("Error writing to file：" + e.getMessage());
        // }

        // // 结束例子！！！！

        File aFile = new File(folder, "Task.txt");
        try (FileWriter writer = new FileWriter(aFile)) {
            writer.write(EncryptionUtil.encrypt(
                    "novice task*10.0*ToBeTaken*Read the user manual and familiarize yourself with the software.*NULL")); // folderPath是id
        } catch (Exception e) {
            System.out.println("Error writing to file：" + e.getMessage());
        }

    }

    /**
     * Reads the contents of a file, decrypts the content using an EncryptionUtil
     * class, and returns it as a string.
     *
     * @param filePath The path of the file to be decrypted.
     * @return The decrypted file contents as a string.
     * @throws Exception If there is an error while reading or decrypting the file.
     */
    public static String decryptFileContents(String filePath) throws Exception {
        byte[] fileContent = Files.readAllBytes(java.nio.file.Paths.get(filePath));
        return EncryptionUtil.decrypt(new String(fileContent, java.nio.charset.StandardCharsets.UTF_8)); // re //
                                                                                                         // string.
    }

}
