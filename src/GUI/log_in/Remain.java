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
import java.io.FileWriter;
import java.io.IOException;

public class Remain {
    public static String fileName = "./data/remain.txt";

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

    public static void showCard(GUIMain g, String cardName) {
        g.showCard(cardName);
    }

}
