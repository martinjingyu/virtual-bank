/**
 * Title      : Children_main.java
 * Description: This class is used to generate children home page, but is deprecated.
 * Copyright  : Copyright (c) 2024/5/9
 * @deprecated  CircularImageButton
 * @author      Weida Peng
 * @version     1.0
 */
package GUI.log_in;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * 
 * The CircularImageButton class represents a custom JButton with a circular
 * shape.
 * 
 * It provides methods for setting the appearance of the button and checking
 * whether a click is within the circle of the button.
 */

class CircularImageButton extends JButton {

        /**
         * 
         * Constructs a CircularImageButton with the specified ImageIcon.
         * 
         * @param icon the ImageIcon to be displayed on the button
         */

        public CircularImageButton(ImageIcon icon) {
                super(icon);

                // Set the appearance of the button
                setContentAreaFilled(false);
                setFocusPainted(false);
        }

        /**
         * 
         * Paints the component with the circular shape and different effects when
         * pressed.
         * 
         * @param g the Graphics object used for painting
         */
        @Override
        protected void paintComponent(Graphics g) {
                if (getModel().isArmed()) {
                        // Draw different effects when the button is pressed
                        g.setColor(Color.lightGray);
                } else {
                        g.setColor(getBackground());
                }
                g.fillOval(0, 0, getSize().width - 1, getSize().height - 1); // Draw round button
                super.paintComponent(g);
        }

        /**
         * 
         * Paints the component's border.
         * 
         * @param g the Graphics object used for painting
         */
        @Override
        protected void paintBorder(Graphics g) {
        }

        /**
         * 
         * Checks whether the specified coordinates are within the circle of the button.
         * 
         * @param x the x-coordinate of the point to be checked
         * @param y the y-coordinate of the point to be checked
         * @return true if the coordinates are within the circle of the button, false
         *         otherwise
         */
        @Override
        public boolean contains(int x, int y) {
                int radius = Math.min(getWidth(), getHeight()) / 2;
                int centerX = getWidth() / 2;
                int centerY = getHeight() / 2;
                return Math.pow(x - centerX, 2) + Math.pow(y - centerY, 2) <= Math.pow(radius, 2);
        }
}

public class Children_main {
        /**
         * 
         * The addChildren_mainPanel method is used to add components to the
         * children_mainPanel and attach it to the cardPanel.
         * 
         * @param cardPanel  the JPanel where the card layout is used
         * 
         * @param cardLayout the CardLayout used to switch between different panels
         * 
         * @param g          the GUIMain object used to call the showCard method
         */
        public static void addChildren_mainPanel(JPanel cardPanel, CardLayout cardLayout, GUIMain g) {

                BackPanel children_mainPanel = new BackPanel("image/background2.jpg");
                children_mainPanel.setLayout(null);
                JLabel children_title = new JLabel("children_main");
                children_mainPanel.add(children_title);
                children_title.setBounds(300, 100, 200, 50);

                JButton check_remain = new JButton("check_remain");
                check_remain.setBounds(250, 200, 200, 50);
                check_remain.addActionListener(new ActionListener() {

                        public void actionPerformed(ActionEvent e) {
                                showCard(g, "remain");

                        }
                });
                children_mainPanel.add(check_remain);

                JButton back = new JButton("Log Out");
                back.setBounds(250, 300, 200, 50);
                back.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                                showCard(g, "basic");

                        }
                });
                children_mainPanel.add(back);
                ImageIcon imageIcon1 = new ImageIcon("image/circle.jpg");
                CircularImageButton circularButton = new CircularImageButton(imageIcon1);
                circularButton.setPreferredSize(new Dimension(100, 100));
                circularButton.setBounds(800, 340, 100, 100);
                children_mainPanel.add(circularButton);
                cardPanel.add(children_mainPanel, "children_main");

        }

        /**
         * 
         * The showCard method is used to switch to a specified card in the GUIMain
         * object.
         * 
         * @param g        the GUIMain object where the card is shown
         * @param cardName the name of the card to be shown
         */
        public static void showCard(GUIMain g, String cardName) {
                g.showCard(cardName);
        }

}
