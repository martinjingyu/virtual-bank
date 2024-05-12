package GUI.bank_page;

import javax.swing.*;
import java.awt.*;

public class history_page extends JPanel {
    public history_page(){

        setLayout(null); // Set layout to null for manual component positioning

        // Create and add components
        JTextField jTextField1 = new JTextField(); // Using JTextField instead of JFormattedTextField
        jTextField1.setBounds(300,60,60,40); // Set bounds for position and size
        add(jTextField1); // Add the component to the panel

        JTextField jTextField2 = new JTextField(); // Using JTextField instead of JFormattedTextField
        jTextField2.setBounds(335,60,60,40); // Set bounds for position and size
        add(jTextField2); // Add the component to the panel

    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.fillRect(70, 70, 320, 50); // 绘制顶部白色矩形框
        g.fillRect(70, 150, 320, 50); // 绘制顶部白色矩形框
        g.fillRect(70, 230, 320, 50); // 绘制顶部白色矩形框
        g.fillRect(70, 310, 320, 45); // 绘制顶部白色矩形框
        g.setColor(Color.BLACK);
        g.drawString("Transaction History",70,40);

        g.setColor(Color.black);
        g.drawString("Source",450,40);
        JTextField jTextField1 = new JFormattedTextField();
        jTextField1.setBounds(300,60,60,40);

        g.setColor(Color.black);
        g.drawString("Amount",570,40);
        JTextField jTextField2 = new JFormattedTextField();
        jTextField2.setBounds(335,60,60,40);

        g.setColor(Color.black);
        g.drawString("Destination",690,40);

        setVisible(true);

    }
}
