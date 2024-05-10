package utill.read;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class CheckParentSecret {

    public static boolean checkParentSecret(String text1, String text2, String filePath) {
        try {
            String contents = "";
            // 打开文件
            File file = new File(filePath); // 替换为您的文件路径

            // 创建 Scanner 对象来读取文件内容
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
            // // 读取直到第一个空格
            // contents = scanner.next();

            // // 打印结果
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

            // 关闭 Scanner

        } catch (IOException error) {
            System.out.println("error_parent");
            System.exit(1);
            return false;
        }

    }

}

