package GUI.log_in;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Error {

    public static void addErrorPanel(JPanel cardPanel, CardLayout cardLayout, GUIMain g) {
        JPanel joiningPanel = new JPanel();
        joiningPanel.setLayout(null);
        JLabel error = new JLabel("Error, please try again! ");
        error.setBounds(200, 400, 1000, 50);
        // 创建 Font 对象，设置字体为宋体，大小为16
        error.setFont(new Font("Times New Roman", Font.BOLD, 60));
        joiningPanel.add(error);
        JButton button = new JButton("back");
        button.setBounds(250, 600, 200, 50);
        button.setFont(new Font("Times New Roman", Font.BOLD, 30));

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showCard(g, "basic");

            }
        });

        joiningPanel.add(button);
        cardPanel.add(joiningPanel, "error");
    }

    public static void showCard(GUIMain g, String cardName) {
        g.showCard(cardName);
    }

}
