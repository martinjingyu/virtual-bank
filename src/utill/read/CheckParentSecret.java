/**
 * Title      : CheckParentSecret.java
 * Description: This class is used to check if parent's id and secret are correct.
 * Copyright  : Copyright (c) 2024/5/9
 * @author      Weida Peng
 * @version     1.0
 */
package utill.read;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class CheckParentSecret {

    public static boolean checkParentSecret(String text1, String text2, String filePath) {
        try {
            String contents = "";
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);
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
            //
            // contents = scanner.next();

            //
            // System.out.println("Content until first space: " + contents);
            // if (text1.equals(contents)) {
            // System.out.println("text1_right");
            // contents = scanner.next();
            // if (text2.equals(contents)) {
            // System.out.println("text2_right");
            // g.frame.dispose();
            // g.loginListener.onLogin("222");
            // // showCard(g, "children_main");
            // } else {
            // System.out.println("error_secret");
            // showCard(g, "error");
            // }

            // } else {
            // System.out.println("error");
            // showCard(g, "error");
            // }

        } catch (IOException error) {
            System.out.println("error_parent");
            System.exit(1);
            return false;
        }

    }

}
