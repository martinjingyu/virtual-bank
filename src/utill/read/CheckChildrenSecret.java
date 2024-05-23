/**
 * Title      : Basic_login.java
 * Description: This class is used to check if id and secret are correct.
 * Copyright  : Copyright (c) 2024/5/9
 * @author      Weida Peng
 * @version     1.0
 */
package utill.read;

import java.nio.file.Files;
import java.util.Scanner;
import utill.validation.Validate;
import utill.cryption.*;

/**
 * This class provides a method to check if a given ID and secret belong to a
 * child account,
 * by reading the IDs and secrets from a file using a Scanner object.
 */
public class CheckChildrenSecret {
    /**
     * Checks if the given ID and secret belong to a child account by reading the
     * IDs and secrets from a file.
     *
     * @param text1    The ID to be checked.
     * @param text2    The secret to be checked.
     * @param filePath The path to the file containing the list of IDs and secrets
     *                 for parent and child accounts.
     * @return True if the given ID and secret belong to a child account, false
     *         otherwise.
     */
    public static boolean checkChildrenSecret(String text1, String text2, String filePath) {
        try {
            String content = decryptFileContents(filePath);
            // Create Scanner objects to read file contents
            Scanner scanner = new Scanner(content);
            return Validate.checkChildren(scanner, text1, text2);

        } catch (Exception error) {
            System.out.println("error_parent");
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
