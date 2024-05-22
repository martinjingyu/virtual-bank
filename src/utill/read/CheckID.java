/**
 * Title      : CheckID.java
 * Description: This class is used to check if the id is already exist.
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

            File file = new File(filePath);
            Scanner scanner = new Scanner(file);
            return Validate.checkid(scanner, text1);

        } catch (IOException error) {
            System.out.println("error_id");
            System.exit(1);
            return false;
        }

    }

}
