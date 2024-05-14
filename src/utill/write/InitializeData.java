/**
 * Title      : WriteToFile.java
 * Description: This class is used to create files and write messages into files.
 * Copyright  : Copyright (c) 2024/5/9
 * @author      Weida Peng
 * @version     1.0
 */
package utill.write;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

public class InitializeData {

    public static void writeTextToFile(String text, String filePath) {

        try {

            FileWriter fw = new FileWriter(filePath, true);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write(text);
            bw.newLine();
            bw.close();

            System.out.println("The contents have been successfully written to the file." + text);
        } catch (IOException e) {
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

        String[] fileNames = { "Bank.txt", "Message.txt", "Product.txt", "Task.txt", "TransactionHistory.txt" };
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

        File aFile = new File(folder, "Bank.txt");
        try (FileWriter writer = new FileWriter(aFile)) {
            writer.write("User, 0.00, 0.00, 0.00, 0.00, 0.00");
        } catch (IOException e) {
            System.out.println("Error writing to file：" + e.getMessage());
        }
    }

}
