/**
 * Title      : Error.java
 * Description: This class is used to generate the error page to indicate that the input of user is error.
 * Copyright  : Copyright (c) 2024/5/9
 * @author      Weida Peng
 * @version     1.0
 */

package GUI.log_in;

import javax.swing.*;
import Controller.login.ButtonControl;
import java.awt.*;

public class Error {
    /**
     * 
     * The addErrorPanel method is used to add components to the joiningPanel and
     * attach it to the cardPanel.
     * 
     * @param cardPanel  the JPanel where the card layout is used
     * @param cardLayout the CardLayout used to switch between different panels
     * @param g          the GUIMain object used to call the addButtonListener
     *                   method
     *                   This method creates a panel to display an error message. It
     *                   contains a text label indicating the error,
     *                   and a button to go back to the previous panel. It uses the
     *                   ButtonControl class to handle button clicks
     *                   and switch between different panels.
     */
    public static void addErrorPanel(JPanel cardPanel, CardLayout cardLayout, GUIMain g) {
        JPanel joiningPanel = new JPanel();
        joiningPanel.setLayout(null);
        JLabel error = new JLabel("Error, please try again! ");
        error.setBounds(250, 200, 1000, 50);
        error.setFont(new Font("Times New Roman", Font.BOLD, 60));
        joiningPanel.add(error);
        JButton button = new JButton("back");
        button.setBounds(450, 280, 200, 50);
        button.setFont(new Font("Times New Roman", Font.BOLD, 30));
        ButtonControl.addButtonListener(button, g);
        joiningPanel.add(button);
        cardPanel.add(joiningPanel, "error");
    }

    /**
     * 
     * The showCard method is used to switch to a specified card in the GUIMain
     * object.
     * 
     * @param g        the GUIMain object where the card is shown
     * @param cardName the name of the card to be shown
     *                 This method calls the showCard method in the GUIMain object,
     *                 passing in the name of the card to be shown.
     *                 It is used to switch between different panels displayed in
     *                 the GUI.
     */
    public static void showCard(GUIMain g, String cardName) {
        g.showCard(cardName);
    }

}
