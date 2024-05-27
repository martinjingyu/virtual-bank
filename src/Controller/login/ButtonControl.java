/**
 * Title      : ButtonControl.java
 * Description: This class is used control the button action.
 * Copyright  : Copyright (c) 2024/5/9
 * @author      Weida Peng
 * @version     1.0
 */
package Controller.login;

import GUI.log_in.*;
import utill.write.InitializeData;
import java.awt.Desktop;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class ButtonControl {
    public Icon icon;

    /**
     * 
     * Adds a button listener to handle button click events that include text
     * fields.
     * 
     * @param button      the button to add the listener to
     * @param g           the current GUI main class
     * @param textField_1 the first text field
     * @param textField_2 the second text field
     **/

    public static void addButtonListener(JButton button, GUIMain g, JTextField textField_1, JTextField textField_2) {

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField_1.setText("");
                textField_2.setText("");
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

    /**
     * 
     * Adds a button listener to handle button click events that include text
     * fields.
     * 
     * @param button      the button to add the listener to
     * @param g           the current GUI main class
     * @param textField_1 the first text field
     * @param textField_2 the second text field
     * @param textField_3 the third text field
     **/

    public static void addButtonListener(JButton button, GUIMain g, JTextField textField_1, JTextField textField_2,
            JTextField textField_3) {

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField_1.setText("");
                textField_2.setText("");
                textField_3.setText("");
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

    /**
     * 
     * Adds a button listener to handle button click events.
     * 
     * @param button the button to add the listener to
     * @param g      the current GUI main class
     **/
    public static void addButtonListener(JButton button, GUIMain g) {

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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

    /**
     * 
     * Adds a mouse listener to the button to handle button click events based on
     * the given parameter.
     * 
     * @param button the button to add the listener to
     * @param a      an integer parameter to determine the action to be performed
     */
    public static void addMouseListener(JButton button, int a) {
        button.addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent e) {
                if (a == 1) {
                    String filePath = "User_manual\\User manual.pdf";
                    File file = new File(filePath);
                    if (file.exists() && file.isFile() && filePath.toLowerCase().endsWith(".pdf")) {
                        try {
                            Desktop.getDesktop().open(file);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(null, "Error opening PDF file: " + ex.getMessage());
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid PDF file path");
                    }
                } else if (a == 2) {
                    System.exit(0);
                }
            }
        });

    }

    /**
     * 
     * Adds a button listener to handle button click events.
     * 
     * @param button      the button to add the listener to
     * @param g           the current GUI main class
     * @param textField_1 the first text field
     * @param textField_2 the second text field
     **/

    public static void addButtonListener2(JButton button, GUIMain g, JTextField textField_1, JTextField textField_2) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String buttonName = e.getActionCommand();
                System.out.println(buttonName);
                String text2 = textField_2.getText();
                String text1 = textField_1.getText();
                textField_1.setText("");
                textField_2.setText("");
                if (utill.read.CheckChildrenSecret.checkChildrenSecret(text1, text2,
                        "data\\secret.txt.encrypted") == true) {
                    g.frame.dispose();
                    g.loginListener.onLogin_kid(text1);
                } else {
                    GUI.log_in.Basic_login.showCard(g, "error");
                }
            }
        });
    }

    /**
     * 
     * Adds a button listener to handle button click events.
     * 
     * @param button      the button to add the listener to
     * @param g           the current GUI main class
     * @param textField_1 the first text field
     * @param textField_2 the second text field
     **/

    public static void addButtonListener3(JButton button, GUIMain g, JTextField textField_1, JTextField textField_2) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String buttonName = e.getActionCommand();
                System.out.println(buttonName);
                String text2 = textField_2.getText();
                String text1 = textField_1.getText();
                textField_1.setText("");
                textField_2.setText("");
                if (utill.read.CheckParentSecret.checkParentSecret(text1, text2,
                        "data\\secret.txt.encrypted") == true) {
                    g.frame.dispose();
                    g.loginListener.onLogin_parent(text1);
                } else {
                    GUI.log_in.Basic_login.showCard(g, "error");
                }
            }
        });
    }

    /**
     * 
     * Adds a button listener to handle button click events for registering a new
     * account.
     * 
     * @param button         the button to add the listener to
     * 
     * @param g              the current GUI main class
     * 
     * @param textField_1    the first text field (account ID)
     * 
     * @param textField_2    the second text field (password)
     * 
     * @param textField_4    the fourth text field (re-enter password)
     * 
     * @param secret_label_3 the label to display registration messages
     **/
    public static void addButtonListener4(JButton button, GUIMain g, JTextField textField_1, JTextField textField_2,
            JTextField textField_4, JLabel secret_label_3) {
        button.addActionListener(new ActionListener() {
            /*
             * Handles the button click event for registering a new account.
             *
             * @param e the button click event
             */
            @Override
            public void actionPerformed(ActionEvent e) {

                String buttonName = e.getActionCommand();
                System.out.println(buttonName);
                String text1 = textField_1.getText();
                String text2 = textField_2.getText();
                String text3 = textField_4.getText();
                if (text1.isEmpty() || text2.isEmpty()
                        || text3.isEmpty()) {
                    secret_label_3.setText("Your id or passwords is empty!");
                    secret_label_3.setBounds(330, 40, 1000, 50);
                } else if ((!vali_id(text1)) || (!vali_secret(text2)) || (!vali_secret(text3))) {
                    secret_label_3.setText("Your id or secret is illegal!");
                    secret_label_3.setBounds(450, 40, 1000, 50);
                    textField_1.setText(null);
                    textField_2.setText(null);
                    textField_4.setText(null);
                } else {
                    String text_1 = textField_1.getText();
                    String text_2 = text_1;
                    text_1 += ' ';
                    text_1 += textField_2.getText();
                    text_1 += ' ';
                    text_1 += textField_4.getText();

                    int indexOfSpace = text_1.indexOf(" ");

                    String contentBeforeSpace = text_1.substring(0, indexOfSpace);
                    if (utill.read.CheckID.checkID(contentBeforeSpace, "data\\secret.txt.encrypted") == false) {
                        secret_label_3.setText("Account is already signed.");
                        secret_label_3.setBounds(400, 40, 1000, 50);
                    } else {
                        InitializeData.writeTextToFile(text_1, "data\\secret.txt.encrypted");
                        InitializeData.createFolderAndFiles(text_2);
                        secret_label_3.setText("Your account is ready.");
                        secret_label_3.setBounds(400, 40, 1000, 50);
                    }
                    textField_1.setText(null);
                    textField_2.setText(null);
                    textField_4.setText(null);
                    // showCard(g, "basic");
                }
            }

            public boolean vali_id(String input) {
                for (char c : input.toCharArray()) {
                    if (Character.isDigit(c)) {
                    } else {
                        return false;
                    }
                }
                return true;
            }

            public boolean vali_secret(String input) {
                boolean containsLetter = false;
                boolean containsNumber = false;
                for (char c : input.toCharArray()) {
                    if (Character.isLetter(c)) {
                        containsLetter = true;
                    } else if (Character.isDigit(c)) {
                        containsNumber = true;
                    } else {
                        return false;
                    }
                }
                if (containsLetter && containsNumber) {
                    return true;
                }
                return false;
            }
        });
    }

}