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

/**
 * 
 * The ImagePanel class is a custom JPanel that displays an image as its
 * background.
 */
class ImagePanel extends JPanel {
    private Image backgroundImage;

    /**
     * 
     * Constructor for the ImagePanel class that sets the background image to the
     * specified image path.
     * 
     * @param imagePath the path to the image file to be used as the background
     */
    public ImagePanel(String imagePath) {
        try {
            backgroundImage = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 
     * Overrides the paintComponent method in JPanel to draw the background image on
     * the panel.
     * 
     * @param g the Graphics object used to paint the component
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}

/**
 * 
 * The Sign class contains methods for adding and displaying the sign panel in a
 * GUI application.
 */
public class Sign {
    /**
     * 
     * Adds the sign panel to the card panel with the specified image background and
     * sets up the sign components.
     * 
     * @param cardPanel  the JPanel where the sign panel will be added
     * 
     * @param cardLayout the CardLayout used to switch between panels
     * 
     * @param g          the main GUI class instance
     */
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
        secret_label_3.setBounds(250, 40, 1000, 50);
        secret_label_3.setFont(new Font("Times New Roman", Font.BOLD, 30));
        signPanel.add(secret_label_3);

        JLabel secret_label_4 = new JLabel("Your id need to be ALL NUMBER");
        secret_label_4.setBounds(400, 100, 1000, 50);
        secret_label_4.setFont(new Font("Times New Roman", Font.BOLD, 20));
        signPanel.add(secret_label_4);

        JLabel secret_label_5 = new JLabel("Your password need to contain NUMBER and WORD, length 6 to 16");
        secret_label_5.setBounds(250, 140, 1000, 50);
        secret_label_5.setFont(new Font("Times New Roman", Font.BOLD, 20));
        signPanel.add(secret_label_5);

        JButton button_1 = new JButton("sign");
        button_1.setBounds(250, 500, 200, 50);
        button_1.setFont(new Font("Times New Roman", Font.BOLD, 40));
        ButtonControl.addButtonListener4(button_1, g, textField_1, textField_2, textField_4, secret_label_3);

        JButton button_2 = new JButton("back");
        button_2.setBounds(650, 500, 200, 50);
        button_2.setFont(new Font("Times New Roman", Font.BOLD, 40));
        ButtonControl.addButtonListener(button_2, g, textField_1, textField_2, textField_4);

        signPanel.add(button_1);
        signPanel.add(button_2);
        cardPanel.add(signPanel, "sign");
    }

    /**
     * 
     * Shows the specified card in the main GUI.
     * 
     * @param g        the main GUI class instance
     * @param cardName the name of the card to be shown
     */
    public static void showCard(GUIMain g, String cardName) {
        g.showCard(cardName);
    }
}
