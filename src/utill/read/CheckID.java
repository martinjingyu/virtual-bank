/**
 * Title      : CheckID.java
 * Description: This class is used to check if the id is already exist.
 * Copyright  : Copyright (c) 2024/5/9
 * @author      Weida Peng
 * @version     1.0
 */
package utill.read;

import java.nio.file.Files;
import java.util.Scanner;
import utill.cryption.*;
import utill.validation.Validate;

/**
 * This class provides a method to check if a given ID already exists in a file
 * containing a list of IDs,
 * by reading the file using a Scanner object.
 */
public class CheckID {
    /**
     * Checks if the given ID already exists in a file by reading the file using a
     * Scanner object.
     *
     * @param text1    The ID to be checked.
     * @param filePath The path to the file containing the list of IDs.
     * @return True if the given ID does not exist in the file, false otherwise.
     */
    public static boolean checkID(String text1, String filePath) {
        try {
            String content = decryptFileContents(filePath);
            // Create Scanner objects to read file contents
            Scanner scanner = new Scanner(content);
            return Validate.checkid(scanner, text1);

        } catch (Exception error) {
            System.out.println("error_id");
            System.exit(1);
            return false;
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
