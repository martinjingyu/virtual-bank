/**
 * Title      : Validate.java
 * Description: This class is used to check if id and secret are correct.
 * Copyright  : Copyright (c) 2024/5/20
 * @author      Weida Peng
 * @version     1.0
 */
package utill.validate;

import java.util.Scanner;

/**
 * This class contains three methods to check if a given ID or secret exists in
 * a list of IDs and secrets.
 */
public class ValidateAccount {
    /**
     * Checks if both the given ID and secret belong to a parent account in the
     * provided scanner input.
     *
     * @param scanner The scanner input that contains a list of IDs and secrets for
     *                parent and child accounts.
     * @param text1   The ID to be checked.
     * @param text2   The secret to be checked.
     * @return True if the given ID and secret belong to a parent account, false
     *         otherwise.
     */
    public static boolean checkParent(Scanner scanner, String text1, String text2) {
        String contents = "";
        contents = scanner.next();
        System.out.println("content:" + contents);
        while (contents != null) {
            System.out.println("id: " + contents);
            if (text1.equals(contents)) {
                System.out.println("id_right");
                scanner.next();
                contents = scanner.next();

                if (text2.equals(contents)) {
                    System.out.println("sec_right");
                    scanner.close();
                    return true;
                } else {
                    System.out.println("error_secret");
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
    }

    /**
     * Checks if both the given ID and secret belong to a child account in the
     * provided scanner input.
     *
     * @param scanner The scanner input that contains a list of IDs and secrets for
     *                parent and child accounts.
     * @param text1   The ID to be checked.
     * @param text2   The secret to be checked.
     * @return True if the given ID and secret belong to a child account, false
     *         otherwise.
     */
    public static boolean checkChildren(Scanner scanner, String text1, String text2) {
        String contents = "";
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
    }

    /**
     * Checks if the given ID already exists in the provided scanner input.
     *
     * @param scanner The scanner input that contains a list of IDs and secrets for
     *                parent and child accounts.
     * @param text1   The ID to be checked.
     * @return True if the given ID does not exist in the scanner input, false
     *         otherwise.
     */
    public static boolean checkid(Scanner scanner, String text1) {
        String contents = "";
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
    }

}
