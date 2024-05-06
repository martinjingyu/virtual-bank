package GUI.log_in;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;

public class Children {
    public static String fileName = "data/children_secret.txt";

    public static void addChildrenPanel(JPanel cardPanel, CardLayout cardLayout, GUIMain g) {
        BackPanel childrenPanel = new BackPanel("image/background.jpg");
        // 添加 childrenPanel 的组件和逻辑
        // 添加 childrenPanel 的组件和逻辑

        JLabel head_label_1 = new JLabel("WELCOME TO ONLINE BANK");
        head_label_1.setBounds(200, 50, 1000, 50);
        // 创建 Font 对象，设置字体为宋体，大小为16
        head_label_1.setFont(new Font("Times New Roman", Font.BOLD, 40));
        childrenPanel.add(head_label_1);

        JLabel head_label = new JLabel("Please choose your identity");
        head_label.setBounds(250, 100, 1000, 50);
        // 创建 Font 对象，设置字体为宋体，大小为16
        head_label.setFont(new Font("Times New Roman", Font.BOLD, 40));
        childrenPanel.add(head_label);

        ImageIcon children_img = new ImageIcon("image/children.jpg"); // 图片路径
        JLabel children_img_label = new JLabel(children_img);
        children_img_label.setBounds(100, 150, 300, 300);
        childrenPanel.add(children_img_label);

        JButton mainButton1 = new JButton("parent");
        mainButton1.setBounds(80, 500, 150, 50);
        mainButton1.setFont(new Font("Times New Roman", Font.BOLD, 30));
        mainButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showCard(g, "parent");

            }
        });
        childrenPanel.add(mainButton1);

        JButton mainButton2 = new JButton("children");
        mainButton2.setBounds(260, 500, 150, 50);
        mainButton2.setFont(new Font("Times New Roman", Font.BOLD, 30));
        mainButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showCard(g, "children");

            }
        });
        childrenPanel.add(mainButton2);

        JLabel children_label = new JLabel("You are children");
        children_label.setBounds(620, 250, 1000, 50);
        children_label.setFont(new Font("Times New Roman", Font.BOLD, 40));

        childrenPanel.add(children_label);
        // children_label.setText("New Text"); // 更改文本

        JLabel id_label = new JLabel("ID : ");
        id_label.setBounds(550, 320, 260, 50);
        id_label.setFont(new Font("Times New Roman", Font.BOLD, 40));
        childrenPanel.add(id_label);

        JTextField textField_1 = new JTextField(4);
        textField_1.setBounds(800, 320, 200, 50);
        childrenPanel.add(textField_1);

        JLabel secret_label = new JLabel("PASSWORD : ");
        secret_label.setBounds(550, 380, 260, 50);
        secret_label.setFont(new Font("Times New Roman", Font.BOLD, 40));
        childrenPanel.add(secret_label);

        JTextField textField_2 = new JTextField(4);
        textField_2.setBounds(800, 380, 200, 50);
        childrenPanel.add(textField_2);

        JButton logButton = new JButton("log in");
        logButton.setBounds(700, 450, 110, 50);
        logButton.setFont(new Font("Times New Roman", Font.BOLD, 30));

        childrenPanel.add(logButton);

        logButton.addActionListener(new ActionListener() {
            // 在登录按钮的 ActionListener 中调用此方法
            public void actionPerformed(ActionEvent e) {

                String text1 = textField_2.getText();
                String text2 = textField_1.getText();
                g.frame.dispose();
                g.loginListener.onLogin("222");
                System.out.println(text1);
                String contents = "";
                try {
                    // 打开文件
                    File file = new File("data/children_secret.txt"); // 替换为您的文件路径

                    // 创建 Scanner 对象来读取文件内容
                    Scanner scanner = new Scanner(file);

                    // 读取直到第一个空格
                    contents = scanner.next();

                    // 打印结果
                    System.out.println("Content until first space: " + contents);
                    if (text1.equals(contents)) {
                        System.out.println("text1_right");
                        contents = scanner.next();
                        if (text2.equals(contents)) {
                            System.out.println("text2_right");
                            showCard(g, "children_main");
                        } else {
                            System.out.println("error_secret");
                            showCard(g, "error");
                        }

                    } else {
                        System.out.println("error");
                        showCard(g, "error");
                    }

                    // 关闭 Scanner
                    scanner.close();
                } catch (IOException error) {
                    System.out.println("error_children");
                    System.exit(1);
                }

            }
        });

        childrenPanel.setLayout(null);

        cardPanel.add(childrenPanel, "children");
    }


    public static void showCard(GUIMain g, String cardName) {
        g.showCard(cardName);
    }

}
