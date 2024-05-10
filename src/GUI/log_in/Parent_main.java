package GUI.log_in;

import javax.swing.*;
import java.awt.*;

public class Parent_main {

    public static void addParent_mainPanel(JPanel cardPanel, CardLayout cardLayout, GUIMain g) {
        JPanel parent_mainPanel = new JPanel();
        parent_mainPanel.add(new JLabel("parent_main"));

        cardPanel.add(parent_mainPanel, "parent_main");

    }

    public static void showCard(GUIMain g, String cardName) {
        g.showCard(cardName);
    }

}
