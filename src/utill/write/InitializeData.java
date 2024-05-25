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

import Entity.Message;
import Entity.MessageList;
import utill.cryption.*;

/**
 * The InitializeData class provides methods for writing text to files, creating folders and files,
 * and decrypting file contents.
 */
public class InitializeData {

    /**
     * Writes the given text to the specified file path.
     *
     * @param text     The text to be written to the file.
     * @param filePath The path of the file to write the text to.
     */
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

    /**
     * Creates folders and files at the specified folder path.
     *
     * @param folderPath The path of the folder where files will be created.
     */
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

        File aFile = new File(folder, "Task.txt");
        try (FileWriter writer = new FileWriter(aFile)) {
            writer.write(EncryptionUtil.encrypt(
                    "novice task*10.0*ToBeTaken*Read the user manual and familiarize yourself with the software.*NULL"));
        } catch (Exception e) {
            System.out.println("Error writing to file：" + e.getMessage());
        }

        try  {
            Message message1 = new Message("system_kid","Welcome to our virtual bank application.");
            Message message2 = new Message("system_parent","Welcome to our virtual bank application.");
            Message message3 = new Message("system_kid","Before using our software, please read the user manual carefully");
            Message message4 = new Message("system_parent","Before using our software, please read the user manual carefully");
            MessageList messageList = new MessageList();
            messageList.addMessage(message1);
            messageList.addMessage(message2);
            messageList.addMessage(message3);
            messageList.addMessage(message4);
            WriteMessage.writeMessage(messageList,folderPath+"/"+"Message.txt");
        } catch (Exception e) {
            System.out.println("Error writing to file：" + e.getMessage());
        }

    }

    /**
     * Decrypts the contents of the specified file.
     *
     * @param filePath The path of the file to decrypt.
     * @return The decrypted file contents as a string.
     * @throws Exception If an error occurs during decryption.
     */
    public static String decryptFileContents(String filePath) throws Exception {
        byte[] fileContent = Files.readAllBytes(java.nio.file.Paths.get(filePath));
        return EncryptionUtil.decrypt(new String(fileContent, java.nio.charset.StandardCharsets.UTF_8));
    }

}
