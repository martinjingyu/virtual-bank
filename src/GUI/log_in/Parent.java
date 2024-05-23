/**
 * Title      : Parent.java
 * Description: This class is used to generate a page for parent to log in.
 * Copyright  : Copyright (c) 2024/5/9
 * @author      Weida Peng
 * @version     1.0
 */
package GUI.log_in;

import Controller.login.ButtonControl;
import javax.swing.*;
import java.awt.*;

class BackPanel extends JPanel {
    private Image backImage;

    public BackPanel(String imagePath) {
        this.backImage = new ImageIcon(imagePath).getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backImage, 0, 0, getWidth(), getHeight(), this);

        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setComposite(AlphaComposite.SrcOver.derive(0.35f));
        g2d.setColor(getBackground());
        g2d.fillRect(0, 0, getWidth(), getHeight());
        g2d.dispose();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(800, 30);
    }
}

public class Parent {

    /**
     * 
     * The addParentPanel method is used to add the parent panel to the card panel
     * in the GUI of the bank application.
     * 
     * @param cardPanel  the JPanel where the parent panel will be added
     * 
     * @param cardLayout the CardLayout used to switch between panels
     * 
     * @param g          the GUIMain object that handles the GUI operations
     * 
     * @param frame      the JFrame object representing the main frame of the
     *                   application
     */
    public static void addParentPanel(JPanel cardPanel, CardLayout cardLayout, GUIMain g, JFrame frame) {
        BackPanel parentPanel = new BackPanel("image/background.jpg");

        JLabel head_label_1 = new JLabel("WELCOME TO ONLINE BANK");
        head_label_1.setBounds(250, 50, 1000, 50);
        head_label_1.setFont(new Font("Times New Roman", Font.BOLD, 40));
        parentPanel.add(head_label_1);

        JLabel head_label = new JLabel("Please choose your identity");
        head_label.setBounds(300, 100, 1000, 50);
        head_label.setFont(new Font("Times New Roman", Font.BOLD, 40));
        parentPanel.add(head_label);

        ImageIcon parent_img = new ImageIcon("image/parent.jpg");
        JLabel parent_img_label = new JLabel(parent_img);
        parent_img_label.setBounds(100, 150, 300, 300);
        parentPanel.add(parent_img_label);

        JLabel parent_label = new JLabel("You are parent");
        parent_label.setBounds(620, 250, 1000, 50);
        parent_label.setFont(new Font("Times New Roman", Font.BOLD, 40));
        parentPanel.add(parent_label);

        JLabel id_label = new JLabel("ID : ");
        id_label.setBounds(550, 320, 260, 50);
        id_label.setFont(new Font("Times New Roman", Font.BOLD, 40));
        parentPanel.add(id_label);

        JTextField textField_1 = new JTextField(4);
        textField_1.setBounds(800, 320, 200, 50);
        parentPanel.add(textField_1);

        JLabel secret_label = new JLabel("PASSWORD : ");
        secret_label.setBounds(550, 380, 260, 50);
        secret_label.setFont(new Font("Times New Roman", Font.BOLD, 40));
        parentPanel.add(secret_label);

        JPasswordField textField_2 = new JPasswordField(4);
        textField_2.setBounds(800, 380, 200, 50);
        parentPanel.add(textField_2);

        JButton logButton = new JButton("log in");
        logButton.setBounds(700, 450, 110, 50);
        logButton.setFont(new Font("Times New Roman", Font.BOLD, 30));
        parentPanel.add(logButton);
        ButtonControl.addButtonListener3(logButton, g, textField_1, textField_2);

        JButton mainButton1 = new JButton("parent");
        mainButton1.setBounds(80, 500, 150, 50);
        mainButton1.setFont(new Font("Times New Roman", Font.BOLD, 30));
        ButtonControl.addButtonListener(mainButton1, g, textField_1, textField_2);
        parentPanel.add(mainButton1);

        JButton mainButton2 = new JButton("children");
        mainButton2.setBounds(260, 500, 150, 50);
        mainButton2.setFont(new Font("Times New Roman", Font.BOLD, 30));
        ButtonControl.addButtonListener(mainButton2, g, textField_1, textField_2);
        parentPanel.add(mainButton2);

        parentPanel.setLayout(null);

        cardPanel.add(parentPanel, "parent");
    }

    /**
     * 
     * The showCard method is used to switch the current card panel in the GUI to
     * the specified card panel.
     * 
     * @param g        the GUIMain object that handles the GUI operations
     * @param cardName the name of the card panel to be displayed
     */
    public static void showCard(GUIMain g, String cardName) {
        g.showCard(cardName);
    }

}
