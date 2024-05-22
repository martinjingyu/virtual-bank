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

/**
 * 
 * The CheckChildrenSecret class provides a utility method to check the validity
 * of children's secret.
 */
public class CheckChildrenSecret {
    /**
     * 
     * Checks if the provided ID and password match the children's secret stored in
     * a file.
     * 
     * @param text1    the ID to be checked
     * 
     * @param text2    the password to be checked
     * 
     * @param filePath the path to the file containing the children's secret
     * 
     * @return true if the ID and password match the children's secret, false
     *         otherwise
     */
    public static boolean checkChildrenSecret(String text1, String text2, String filePath) {
        try {
            String contents = "";
            File file = new File(filePath);

            // Create Scanner objects to read file contents
            Scanner scanner = new Scanner(file);
            contents = scanner.next();
            System.out.println("content:" + contents);
            while (contents != null) {
                System.out.println("id: " + contents);
                if (text1.equals(contents)) {
                    System.out.println("id_right");
                    contents = scanner.next();

                    if (text2.equals(contents)) {
                        System.out.println("sec_right");
                        scanner.close();
                        return true;
                    } else {
                        System.out.println("error_secret");
                        scanner.next();
                        if (scanner.hasNext()) {
                            contents = scanner.next();
                        } else {
                            scanner.close();
                            return false;
                        }
                    }
                } else {
                    scanner.next();
                    scanner.next();
                    if (scanner.hasNext()) {
                        contents = scanner.next();
                    } else {
                        scanner.close();
                        return false;
                    }
                }
            }
            System.out.println("final_error_secret");
            scanner.close();
            return false;

        } catch (IOException error) {
            System.out.println("error_parent");
            System.exit(1);
            return false;
        }

    }

}
