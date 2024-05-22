/**
 * Title      : Remain.java
 * Description: This class is used to get time from the computer.
 * Copyright  : Copyright (c) 2024/5/9
 * @author      Weida Peng
 * @version     1.0
 */
package GUI.log_in;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Date;

public class Remain {
    public static String fileName = "./data/remain.txt";

    /**
     * 
     * The addRemainPanel method is used to add the remain panel to the card panel
     * in the GUI of the bank application.
     * 
     * @param cardPanel  the JPanel where the remain panel will be added
     * 
     * @param cardLayout the CardLayout used to switch between panels
     * 
     * @param g          the GUIMain object that handles the GUI operations
     */
    public static void addRemainPanel(JPanel cardPanel, CardLayout cardLayout, GUIMain g) {
        JPanel remainPanel = new JPanel();

        String contents = "";
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            contents = bufferedReader.readLine();
            bufferedReader.close();
        } catch (IOException error) {
            System.out.println("error_remain");
            System.exit(1);
        }

        JLabel remain_title = new JLabel("remain : " + contents);
        remainPanel.add(remain_title);
        remain_title.setBounds(300, 100, 200, 50);

        JButton back_children_main = new JButton("back");
        back_children_main.setBounds(250, 200, 200, 50);
        back_children_main.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showCard(g, "children_main");

            }
        });
        remainPanel.add(back_children_main);

        Date currentTime = new Date();
        try {

            FileWriter writer = new FileWriter(fileName, true);

            writer.write("\n" + "Current system time: " + currentTime.toString());

            writer.close();

            System.out.println("当前系统时间已写入文件：" + fileName);
        } catch (IOException e) {
            System.out.println("写入文件时出现错误：" + e.getMessage());
        }

        remainPanel.setLayout(null);
        cardPanel.add(remainPanel, "remain");

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
