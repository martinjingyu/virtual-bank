/**
 * Title      : Children.java
 * Description: This class is used to generate the login page for children.
 * Copyright  : Copyright (c) 2024/5/9
 * @author      Weida Peng
 * @version     1.0
 */
package GUI.log_in;

import javax.swing.*;
import Controller.login.ButtonControl;
import java.awt.*;

public class Children {
    public static String fileName = "data/children_secret.txt";

    public static void addChildrenPanel(JPanel cardPanel, CardLayout cardLayout, GUIMain g) {
        BackPanel childrenPanel = new BackPanel("image/background.jpg");

        JLabel head_label_1 = new JLabel("WELCOME TO ONLINE BANK");
        head_label_1.setBounds(250, 50, 1000, 50);
        head_label_1.setFont(new Font("Times New Roman", Font.BOLD, 40));
        childrenPanel.add(head_label_1);

        JLabel head_label = new JLabel("Please choose your identity");
        head_label.setBounds(300, 100, 1000, 50);
        head_label.setFont(new Font("Times New Roman", Font.BOLD, 40));
        childrenPanel.add(head_label);

        ImageIcon children_img = new ImageIcon("image/children.jpg");
        JLabel children_img_label = new JLabel(children_img);
        children_img_label.setBounds(100, 150, 300, 300);
        childrenPanel.add(children_img_label);

        JButton mainButton1 = new JButton("parent");
        mainButton1.setBounds(80, 500, 150, 50);
        mainButton1.setFont(new Font("Times New Roman", Font.BOLD, 30));
        ButtonControl.addButtonListener(mainButton1, g);
        // mainButton1.addActionListener(new ActionListener() {
        // public void actionPerformed(ActionEvent e) {
        // showCard(g, "parent");

        // }
        // });
        childrenPanel.add(mainButton1);

        JButton mainButton2 = new JButton("children");
        mainButton2.setBounds(260, 500, 150, 50);
        mainButton2.setFont(new Font("Times New Roman", Font.BOLD, 30));
        ButtonControl.addButtonListener(mainButton2, g);
        // mainButton2.addActionListener(new ActionListener() {
        // public void actionPerformed(ActionEvent e) {
        // showCard(g, "children");

        // }
        // });
        childrenPanel.add(mainButton2);

        JLabel children_label = new JLabel("You are children");
        children_label.setBounds(620, 250, 1000, 50);
        children_label.setFont(new Font("Times New Roman", Font.BOLD, 40));
        childrenPanel.add(children_label);

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
        ButtonControl.addButtonListener2(logButton, g, textField_1, textField_2);

        // logButton.addActionListener(new ActionListener() {
        // // 在登录按钮的 ActionListener 中调用此方法
        // public void actionPerformed(ActionEvent e) {

        // String text2 = textField_2.getText();
        // String text1 = textField_1.getText();
        // if (utill.read.CheckChildrenSecret.checkChildrenSecret(text1, text2,
        // "data/secret.txt") == true) {
        // g.frame.dispose();
        // g.loginListener.onLogin(text1);
        // } else {
        // showCard(g, "error");
        // }

        // }
        // });

        childrenPanel.setLayout(null);

        cardPanel.add(childrenPanel, "children");
    }

    public static void showCard(GUIMain g, String cardName) {
        g.showCard(cardName);
    }

}
