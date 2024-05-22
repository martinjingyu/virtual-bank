/**
 * Title      : Basic_login.java
 * Description: This class is used to generate the login page for you to choose identity and sign up a new account.
 * Copyright  : Copyright (c) 2024/5/9
 * @author      Weida Peng
 * @version     1.0
 */

package GUI.log_in;

import javax.swing.*;
import Controller.login.ButtonControl;
import java.awt.*;

/**
 * 
 * The Back_Panel class represents a custom JPanel with a background image.
 * 
 * It provides methods for setting the background image and painting the panel
 * with transparency.
 */
class Back_Panel extends JPanel {
    private Image backImage;

    /**
     * 
     * Constructs a Back_Panel object with the specified image path.
     * 
     * @param imagePath the path to the background image
     */
    public Back_Panel(String imagePath) {
        this.backImage = new ImageIcon(imagePath).getImage();
    }

    /**
     * 
     * Paints the panel with the background image and transparency.
     * 
     * @param g the Graphics object used for painting
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw background picture
        g.drawImage(backImage, 0, 0, getWidth(), getHeight(), this);

        // setOpacity
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setComposite(AlphaComposite.SrcOver.derive(0.35f));
        g2d.setColor(getBackground());
        g2d.fillRect(0, 0, getWidth(), getHeight());
        g2d.dispose();
    }

    /**
     * 
     * Returns the preferred size of the panel.
     * 
     * @return the preferred size of the panel as a Dimension object
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(800, 30);
    }
}

/**
 * 
 * The Basic_login class represents the basic login panel of the GUI for an
 * online bank application.
 * 
 * It provides methods for adding and displaying the basic login panel.
 */
public class Basic_login {
    /**
     * 
     * Adds the basic login panel to the card panel with the specified card layout.
     * 
     * @param cardPanel  the panel where the basic login panel will be added
     * 
     * @param cardLayout the layout manager used by the card panel
     * 
     * @param g          the current GUI main class
     */
    public static void addbasicPanel(JPanel cardPanel, CardLayout cardLayout, GUIMain g) {
        Back_Panel basicPanel = new Back_Panel("image/background.jpg");

        JLabel head_label_1 = new JLabel("WELCOME TO ONLINE BANK");
        head_label_1.setBounds(250, 50, 1000, 50);
        head_label_1.setFont(new Font("Times New Roman", Font.BOLD, 40));
        basicPanel.add(head_label_1);

        JLabel head_label = new JLabel("Please choose your identity");
        head_label.setBounds(300, 100, 1000, 50);
        head_label.setFont(new Font("Times New Roman", Font.BOLD, 40));
        basicPanel.add(head_label);

        ImageIcon basic_img = new ImageIcon("image/basic.jpg");
        JLabel basic_img_label = new JLabel(basic_img);
        basic_img_label.setBounds(100, 150, 300, 300);
        basicPanel.add(basic_img_label);

        JButton mainButton1 = new JButton("parent");
        mainButton1.setBounds(80, 500, 150, 50);
        mainButton1.setFont(new Font("Times New Roman", Font.BOLD, 30));
        ButtonControl.addButtonListener(mainButton1, g);
        basicPanel.add(mainButton1);

        JButton mainButton2 = new JButton("children");
        mainButton2.setBounds(260, 500, 150, 50);
        mainButton2.setFont(new Font("Times New Roman", Font.BOLD, 30));
        ButtonControl.addButtonListener(mainButton2, g);
        basicPanel.add(mainButton2);

        JLabel basic_label = new JLabel("You identity is unknown");
        basic_label.setBounds(550, 250, 1000, 50);
        basic_label.setFont(new Font("Times New Roman", Font.BOLD, 40));
        JButton logButton = new JButton("sign up");
        logButton.setBounds(650, 350, 200, 50);
        logButton.setFont(new Font("Times New Roman", Font.BOLD, 30));
        ButtonControl.addButtonListener(logButton, g);

        basicPanel.add(logButton);
        basicPanel.add(basic_label);
        basicPanel.setLayout(null);
        cardPanel.add(basicPanel, "basic");
    }

    /**
     * 
     * Shows the card with the specified card name in the GUI.
     * 
     * @param g        the current GUI main class
     * @param cardName the name of the card to be shown
     */
    public static void showCard(GUIMain g, String cardName) {
        g.showCard(cardName);
    }

}
