/**
 * Title      : GUIMain.java
 * Description: This class is used to generate main frame of the login page.
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
 * The DownPanel class is a custom JPanel that sets a background image and
 * applies a transparent overlay to it.
 * It overrides the paintComponent method to draw the background image and then
 * apply the transparent overlay using alpha compositing.
 * It also overrides the getPreferredSize method to set a preferred size of 800
 * pixels wide and 30 pixels high.
 * This class performs the following steps:
 * Declares a private Image variable for the background image.
 * Creates a constructor that takes an image path and sets the background image
 * to the specified image.
 * Overrides the paintComponent method to draw the background image and apply a
 * transparent overlay using alpha compositing.
 * Overrides the getPreferredSize method to set a preferred size of 800 pixels
 * wide and 30 pixels high.
 * Note: This class assumes that the necessary images are available in the
 * specified locations.
 */
class DownPanel extends JPanel {
    private Image downImage;

    public DownPanel(String imagePath) {
        this.downImage = new ImageIcon(imagePath).getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(downImage, 0, 0, getWidth(), getHeight(), this);
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

/**
 * 
 * The TransparentPanel class is a custom JPanel that sets a background image
 * and applies a transparent overlay to it.
 * It overrides the paintComponent method to draw the background image and then
 * apply the transparent overlay using alpha compositing.
 * It also overrides the getPreferredSize method to set a preferred size of 800
 * pixels wide and 200 pixels high.
 * This class performs the following steps:
 * Declares a private Image variable for the background image.
 * Creates a constructor that takes an image path and sets the background image
 * to the specified image.
 * Overrides the paintComponent method to draw the background image and apply a
 * transparent overlay using alpha compositing.
 * Overrides the getPreferredSize method to set a preferred size of 800 pixels
 * wide and 200 pixels high.
 * Note: This class assumes that the necessary images are available in the
 * specified locations.
 */
class TransparentPanel extends JPanel {
    private Image backgroundImage;

    public TransparentPanel(String imagePath) {
        this.backgroundImage = new ImageIcon(imagePath).getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setComposite(AlphaComposite.SrcOver.derive(0.35f));
        g2d.setColor(getBackground());
        g2d.fillRect(0, 0, getWidth(), getHeight());
        g2d.dispose();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(800, 200);
    }
}

public class GUIMain {
    public LoginListener loginListener;

    public JFrame frame;
    public JPanel cardPanel;
    public CardLayout cardLayout;

    // public static void main(String[] args) {
    // GUIMain ui = new GUIMain();
    // ui.createAndShowGUI();
    // }
    public void setLoginListener(LoginListener listener) {
        this.loginListener = listener;
    }

    /**
     * 
     * The createAndShowGUI method is used to create and display the graphical user
     * interface (GUI) for the bank application.
     * It sets up the main frame, panels, buttons, and card layout to handle
     * different views or screens of the application.
     * It also adds mouse listeners and sets icons for the help and exit buttons.
     * This method performs the following steps:
     * Creates a new JFrame object with the title "Bank".
     * Sets the default close operation for the frame to exit the application when
     * closed.
     * Sets the size of the frame to 1100 pixels wide and 700 pixels high.
     * Creates a TransparentPanel object for the upper panel with a background
     * image.
     * Sets the layout of the upper panel to null.
     * Creates a help button with an image icon and sets its bounds.
     * Resizes and sets the image icon for the help button.
     * Adds a mouse listener to the help button using the ButtonControl class.
     * Creates an exit button with an image icon and sets its font and bounds.
     * Resizes and sets the image icon for the exit button.
     * Adds a mouse listener to the exit button using the ButtonControl class.
     * Creates a JPanel object for the card layout.
     * Sets the layout of the card panel to card layout.
     * Creates a DownPanel object for the lower panel with a background image.
     * Sets the layout of the lower panel to null.
     * Adds the help and exit buttons to the lower panel.
     * Adds the upper panel, card panel, and lower panel to the frame's content
     * pane.
     * Sets the frame to be visible.
     * Calls methods to add different panels to the card panel using the
     * corresponding classes.
     * Note: This method assumes that the necessary images and classes are available
     * in the specified locations.
     */
    public void createAndShowGUI() {
        frame = new JFrame("Bank");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1100, 700);

        TransparentPanel upPanel = new TransparentPanel("image/bank.jpg");
        upPanel.setLayout(null);

        JButton help = new JButton(new ImageIcon("image/question_mark.jpg"));
        help.setBounds(950, 0, 77, 35);
        int width = help.getWidth();
        int height = help.getHeight();
        ImageIcon icon = (ImageIcon) help.getIcon();
        Image img = icon.getImage();
        Image scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImg);
        help.setIcon(scaledIcon);
        ButtonControl.addMouseListener(help, 1);
        JButton exit = new JButton(new ImageIcon("image/CLOSE.jpg"));
        exit.setFont(new Font("Times New Roman", Font.BOLD, 60));
        exit.setBounds(1020, 0, 68, 40);

        width = exit.getWidth();
        height = exit.getHeight();
        icon = (ImageIcon) exit.getIcon();
        img = icon.getImage();
        scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        scaledIcon = new ImageIcon(scaledImg);
        exit.setIcon(scaledIcon);
        ButtonControl.addMouseListener(exit, 2);

        cardPanel = new JPanel();
        cardLayout = new CardLayout();
        cardPanel.setLayout(cardLayout);

        DownPanel downPanel = new DownPanel("image/white.jpg");
        downPanel.setLayout(null);
        downPanel.add(help);
        downPanel.add(exit);
        // frame.add(upPanel, BorderLayout.NORTH);
        frame.add(cardPanel, BorderLayout.CENTER);
        frame.add(downPanel, BorderLayout.SOUTH);
        frame.setVisible(true);

        Basic_login.addbasicPanel(cardPanel, cardLayout, this);
        Parent.addParentPanel(cardPanel, cardLayout, this, frame);
        Children.addChildrenPanel(cardPanel, cardLayout, this);
        Parent_main.addParent_mainPanel(cardPanel, cardLayout, this);
        Error.addErrorPanel(cardPanel, cardLayout, this);
        Children_main.addChildren_mainPanel(cardPanel, cardLayout, this);
        Remain.addRemainPanel(cardPanel, cardLayout, this);
        Sign.addSignPanel(cardPanel, cardLayout, this);
    }

    /**
     * 
     * The showCard method is used to display a specific panel or "card" in the card
     * layout of the bank application.
     * It takes a String parameter representing the name of the card to be displayed
     * and uses the card layout to show that card.
     * This method performs the following steps:
     * Takes a String parameter representing the name of the card to be displayed.
     * Uses the card layout to show the card with the specified name in the card
     * panel.
     * Note: This method assumes that the necessary panels have already been added
     * to the card panel using their respective classes.
     **/

    public void showCard(String cardName) {
        cardLayout.show(cardPanel, cardName);
    }
}
