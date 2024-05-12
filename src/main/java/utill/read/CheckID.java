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

public class CheckID {

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
            System.out.println("error_id");
            System.exit(1);
            return false;
        }

    }

}
