/**
 * Title      : Parent_main.java
 * Description: This class is used to generate the login page for you to choose identity and sign up a new account.
 * Copyright  : Copyright (c) 2024/5/9
 * @deprecated  addParent_mainPanel
 * @author      Weida Peng
 * @version     1.0
 */
package GUI.log_in;

import javax.swing.*;
import java.awt.*;

public class Parent_main {
    /**
     * 
     * The addParent_mainPanel method is used to add the parent_mainPanel to the
     * cardPanel in the GUI of the bank application.
     * It takes the cardPanel, cardLayout, and GUIMain objects as parameters and
     * adds the parent_mainPanel with a label to the cardPanel.
     * This method performs the following steps:
     * Takes the cardPanel, cardLayout, and GUIMain objects as parameters.
     * Creates a new JPanel called parent_mainPanel.
     * Adds a JLabel with the text "parent_main" to the parent_mainPanel.
     * Adds the parent_mainPanel to the cardPanel with the specified name
     * "parent_main".
     */
    public static void addParent_mainPanel(JPanel cardPanel, CardLayout cardLayout, GUIMain g) {
        JPanel parent_mainPanel = new JPanel();
        parent_mainPanel.add(new JLabel("parent_main"));

        cardPanel.add(parent_mainPanel, "parent_main");

    }

    /**
     * 
     * The showCard method is used to display a specific panel or "card" in the GUI
     * of the bank application.
     * It takes the GUIMain object and a String parameter representing the name of
     * the card to be displayed and calls the showCard method of the GUIMain object.
     * This method performs the following steps:
     * Takes the GUIMain object and a String parameter representing the name of the
     * card to be displayed.
     * Calls the showCard method of the GUIMain object, passing the cardName
     * parameter.
     */
    public static void showCard(GUIMain g, String cardName) {
        g.showCard(cardName);
    }

}
