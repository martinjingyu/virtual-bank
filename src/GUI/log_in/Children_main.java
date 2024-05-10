package GUI.log_in;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

// 自定义圆形图像按钮类
class CircularImageButton extends JButton {
        public CircularImageButton(ImageIcon icon) {
                super(icon);

                // 设置按钮的外观
                setContentAreaFilled(false); // 设置按钮透明
                setFocusPainted(false); // 移除按钮焦点边框
        }

        @Override
        protected void paintComponent(Graphics g) {
                if (getModel().isArmed()) {
                        // 当按钮被按下时绘制不同的效果
                        g.setColor(Color.lightGray);
                } else {
                        g.setColor(getBackground());
                }
                g.fillOval(0, 0, getSize().width - 1, getSize().height - 1); // 绘制圆形按钮
                super.paintComponent(g);
        }

        @Override
        protected void paintBorder(Graphics g) {
                // 不绘制边框
        }

        // 检测点击是否在按钮的圆形范围内
        @Override
        public boolean contains(int x, int y) {
                int radius = Math.min(getWidth(), getHeight()) / 2;
                int centerX = getWidth() / 2;
                int centerY = getHeight() / 2;
                return Math.pow(x - centerX, 2) + Math.pow(y - centerY, 2) <= Math.pow(radius, 2);
        }
}

public class Children_main {

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
                // 准备按钮图像
                ImageIcon imageIcon1 = new ImageIcon("image/circle.jpg");
                CircularImageButton circularButton = new CircularImageButton(imageIcon1);
                circularButton.setPreferredSize(new Dimension(100, 100));
                circularButton.setBounds(800, 340, 100, 100);
                // 将按钮添加到 JFrame
                children_mainPanel.add(circularButton);

                cardPanel.add(children_mainPanel, "children_main");

        }

        public static void showCard(GUIMain g, String cardName) {
                g.showCard(cardName);
        }

}
