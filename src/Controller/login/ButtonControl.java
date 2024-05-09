package Controller.login;

import GUI.log_in.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonControl {

    public static void addButtonListener(JButton button, GUIMain g) {

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 在这里编写按钮点击后的行为
                String buttonName = e.getActionCommand();
                System.out.println(buttonName);
                if (buttonName == "parent") {
                    System.out.println(buttonName);
                    GUI.log_in.Basic_login.showCard(g, "parent");
                } else if (buttonName == "children") {
                    System.out.println(buttonName);
                    GUI.log_in.Basic_login.showCard(g, "children");
                } else if (buttonName == "sign up") {
                    System.out.println(buttonName);
                    GUI.log_in.Basic_login.showCard(g, "sign");
                } else if (buttonName == "back") {
                    System.out.println(buttonName);
                    GUI.log_in.Basic_login.showCard(g, "basic");
                }


            }
        });
    }

    public static void addButtonListener(JButton button) {
    }

    public static void addButtonListener2(JButton button, GUIMain g, JTextField textField_1, JTextField textField_2) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 在这里编写按钮点击后的行为
                String buttonName = e.getActionCommand();
                System.out.println(buttonName);
                String text2 = textField_2.getText();
                String text1 = textField_1.getText();
                if (utill.read.CheckChildrenSecret.checkChildrenSecret(text1, text2, "data/secret.txt") == true) {
                    g.frame.dispose();
                    g.loginListener.onLogin_kid(text1);
                } else {
                    GUI.log_in.Basic_login.showCard(g, "error");
                }
            }
        });
    }

    public static void addButtonListener3(JButton button, GUIMain g, JTextField textField_1, JTextField textField_2) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 在这里编写按钮点击后的行为
                String buttonName = e.getActionCommand();
                System.out.println(buttonName);
                String text2 = textField_2.getText();
                String text1 = textField_1.getText();
                if (utill.read.CheckParentSecret.checkParentSecret(text1, text2, "data/secret.txt") == true) {
                    g.frame.dispose();
                    g.loginListener.onLogin_parent(text1);
                } else {
                    GUI.log_in.Basic_login.showCard(g, "error");
                }
            }
        });
    }

    public static void addButtonListener4(JButton button, GUIMain g, JTextField textField_1, JTextField textField_2,
                                          JTextField textField_4, JLabel secret_label_3) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 在这里编写按钮点击后的行为
                String buttonName = e.getActionCommand();
                System.out.println(buttonName);
                if (textField_1.getText().isEmpty() || textField_2.getText().isEmpty()
                        || textField_4.getText().isEmpty()) {
                    secret_label_3.setText("Your id or passwords is empty!");
                    secret_label_3.setBounds(350, 100, 1000, 50);
                } else {
                    String text_1 = textField_1.getText();
                    String text_2 = text_1;
                    text_1 += ' ';
                    text_1 += textField_2.getText();
                    text_1 += ' ';
                    text_1 += textField_4.getText();
                    // 查找第一个空格的位置
                    int indexOfSpace = text_1.indexOf(" ");

                    // 提取第一个空格之前的内容
                    String contentBeforeSpace = text_1.substring(0, indexOfSpace);
                    if (utill.read.CheckID.checkID(contentBeforeSpace, "data/secret.txt") == false) {
                        secret_label_3.setText("Account is already signed.");
                        secret_label_3.setBounds(400, 100, 1000, 50);
                    } else {
                        utill.write.WriteToFile.writeTextToFile(text_1, "data/secret.txt");
                        utill.write.WriteToFile.createFolderAndFiles(text_2);
                        secret_label_3.setText("Your account is ready.");
                        secret_label_3.setBounds(400, 100, 1000, 50);
                    }
                    textField_1.setText(null);
                    textField_2.setText(null);
                    textField_4.setText(null);
                    // showCard(g, "basic");
                }
            }
        });
    }

}