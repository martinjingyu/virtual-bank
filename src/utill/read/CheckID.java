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

/**
 * 
 * The CheckID class provides a utility method to check if a given ID already
 * exists in a file.
 */
public class CheckID {
    /**
     * 
     * Checks if the provided ID already exists in the file.
     * 
     * @param text1    the ID to be checked
     * 
     * @param filePath the path to the file containing the IDs
     * 
     * @return true if the ID doesn't exist in the file, false otherwise
     */
    public static boolean checkID(String text1, String filePath) {
        try {
            String contents = "";
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);
            contents = scanner.next();
            System.out.println("content:" + contents);
            while (contents != null) {
                System.out.println("id: " + contents);
                if (text1.equals(contents)) {
                    System.out.println("ID_ALREADY_EXIST");
                    scanner.close();
                    return false;
                } else {
                    scanner.next();
                    scanner.next();
                    if (scanner.hasNext()) {
                        contents = scanner.next();
                    } else {
                        System.out.println("ID_DONT_HAVE");
                        scanner.close();
                        return true;
                    }
                }
            }
            System.out.println("ID_DONT_HAVE");
            scanner.close();
            return true;

        } catch (IOException error) {
            System.out.println("error_id");
            System.exit(1);
            return false;
        }

    }

}
