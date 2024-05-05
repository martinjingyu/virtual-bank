package GUI.task_page;

//[pwd]:4/25：暂时未使用文件
// FirstUI.java   此文件用于验证不同页面之间的跳转。不需要拘泥于一种页面布局。可以实现在不同包之间页面的跳转
import GUI.MainFrame;

import javax.swing.*;
import java.awt.event.*;

public class FirstUI extends JFrame {
    public FirstUI() {
        JButton button = new JButton("跳转到第二个界面");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 创建并显示第二个界面
                MainFrame secondUI = new MainFrame();
                secondUI.setVisible(true);
                // 关闭当前界面
                dispose();
            }
        });

        JPanel panel = new JPanel();
        panel.add(button);

        setTitle("第一个界面");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().add(panel);
        pack();
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new FirstUI().setVisible(true);
            }
        });
    }
}