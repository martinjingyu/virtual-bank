/**
 * Title      : Basic_login.java
 * Description: This class is used to check if id and secret are correct.
 * Copyright  : Copyright (c) 2024/5/9
 * @author      Weida Peng
 * @version     1.0
 */
package utill.read;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import utill.validation.Validate;

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

            File file = new File(filePath);

            // Create Scanner objects to read file contents
            Scanner scanner = new Scanner(file);
            return Validate.checkChildren(scanner, text1, text2);

        } catch (IOException error) {
            System.out.println("error_parent");
            System.exit(1);
            return false;
        }

    }

}
