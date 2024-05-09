/**
 * Title      : Sign.java
 * Description: This class is a sign up window.
 * Copyright  : Copyright (c) 2024/5/9
 * @author      Weida Peng
 * @version     1.0
 */
package GUI.log_in;

import javax.imageio.ImageIO;
import javax.swing.*;
import Controller.login.ButtonControl;
import java.awt.*;
import java.io.File;
import java.io.IOException;

class ImagePanel extends JPanel {
    private Image backgroundImage;

    public ImagePanel(String imagePath) {
        try {
            backgroundImage = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}

public class Sign {

    public static void addSignPanel(JPanel cardPanel, CardLayout cardLayout, GUIMain g) {
        JPanel signPanel = new ImagePanel("image/background3.jpg");
        signPanel.setLayout(null);
        JLabel children = new JLabel("Sign for children ");
        children.setBounds(150, 200, 1000, 60);
        children.setFont(new Font("Times New Roman", Font.BOLD, 40));
        signPanel.add(children);

        JLabel id_label_1 = new JLabel("ID : ");
        id_label_1.setBounds(100, 320, 260, 50);
        id_label_1.setFont(new Font("Times New Roman", Font.BOLD, 30));
        signPanel.add(id_label_1);

        JTextField textField_1 = new JTextField(4);
        textField_1.setBounds(300, 320, 200, 50);
        signPanel.add(textField_1);

        JLabel secret_label_1 = new JLabel("PASSWORD : ");
        secret_label_1.setBounds(100, 380, 260, 50);
        secret_label_1.setFont(new Font("Times New Roman", Font.BOLD, 30));
        signPanel.add(secret_label_1);

        JTextField textField_2 = new JTextField(4);
        textField_2.setBounds(300, 380, 200, 50);
        signPanel.add(textField_2);

        JLabel parent = new JLabel("Sign for parent ");
        parent.setBounds(650, 200, 1000, 60);
        parent.setFont(new Font("Times New Roman", Font.BOLD, 40));
        signPanel.add(parent);

        JLabel secret_label_2 = new JLabel("PASSWORD : ");
        secret_label_2.setBounds(550, 380, 260, 50);
        secret_label_2.setFont(new Font("Times New Roman", Font.BOLD, 30));
        signPanel.add(secret_label_2);

        JTextField textField_4 = new JTextField(4);
        textField_4.setBounds(750, 380, 200, 50);
        signPanel.add(textField_4);

        JLabel secret_label_3 = new JLabel("Enter your id and passwords, then click Sign");
        secret_label_3.setBounds(250, 100, 1000, 50);
        secret_label_3.setFont(new Font("Times New Roman", Font.BOLD, 30));
        signPanel.add(secret_label_3);

        JButton button_1 = new JButton("sign");
        button_1.setBounds(250, 500, 200, 50);
        button_1.setFont(new Font("Times New Roman", Font.BOLD, 40));
        ButtonControl.addButtonListener4(button_1, g, textField_1, textField_2, textField_4, secret_label_3);

        // button_1.addActionListener(new ActionListener() {
        // public void actionPerformed(ActionEvent e) {
        // if (textField_1.getText().isEmpty() || textField_2.getText().isEmpty()
        // || textField_4.getText().isEmpty()) {
        // secret_label_3.setText("Your id or passwords is empty!");
        // secret_label_3.setBounds(350, 100, 1000, 50);
        // } else {
        // String text_1 = textField_1.getText();
        // String text_2 = text_1;
        // text_1 += ' ';
        // text_1 += textField_2.getText();
        // text_1 += ' ';
        // text_1 += textField_4.getText();
        // int indexOfSpace = text_1.indexOf(" ");

        // String contentBeforeSpace = text_1.substring(0, indexOfSpace);
        // if (utill.read.CheckID.checkID(contentBeforeSpace, "data/secret.txt") ==
        // false) {
        // secret_label_3.setText("Account is already signed.");
        // secret_label_3.setBounds(400, 100, 1000, 50);
        // } else {
        // utill.write.WriteToFile.writeTextToFile(text_1, "data/secret.txt");
        // utill.write.WriteToFile.createFolderAndFiles(text_2);
        // secret_label_3.setText("Your account is ready.");
        // secret_label_3.setBounds(400, 100, 1000, 50);
        // }
        // textField_1.setText(null);
        // textField_2.setText(null);
        // textField_4.setText(null);
        // // showCard(g, "basic");
        // }
        // }
        // });
        JButton button_2 = new JButton("back");
        button_2.setBounds(650, 500, 200, 50);
        button_2.setFont(new Font("Times New Roman", Font.BOLD, 40));
        ButtonControl.addButtonListener(button_2, g);

        // button_2.addActionListener(new ActionListener() {
        // public void actionPerformed(ActionEvent e) {
        // showCard(g, "basic");

        // }
        // });

        signPanel.add(button_1);
        signPanel.add(button_2);
        cardPanel.add(signPanel, "sign");
    }

    public static void showCard(GUIMain g, String cardName) {
        g.showCard(cardName);
    }
}
