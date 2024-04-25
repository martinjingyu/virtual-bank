package GUI.log_in;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

class BackPanel extends JPanel {
    private Image backImage;

    public BackPanel(String imagePath) {
        this.backImage = new ImageIcon(imagePath).getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // 绘制背景图片
        g.drawImage(backImage, 0, 0, getWidth(), getHeight(), this);

        // 设置透明度
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setComposite(AlphaComposite.SrcOver.derive(0.35f)); // 设置透明度为35%
        g2d.setColor(getBackground());
        g2d.fillRect(0, 0, getWidth(), getHeight());
        g2d.dispose();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(800, 30); // 设置bank的大小为400x300像素
    }
}

public class Parent {
    public static String fileName = "data/parent_secret.txt";

    public static void addParentPanel(JPanel cardPanel, CardLayout cardLayout, GUIMain g, JFrame frame) {
        BackPanel parentPanel = new BackPanel("image/background.jpg");
        // 添加 parentPanel 的组件和逻辑

        JLabel head_label_1 = new JLabel("WELCOME TO ONLINE BANK");
        head_label_1.setBounds(200, 50, 1000, 50);
        // 创建 Font 对象，设置字体为宋体，大小为16
        head_label_1.setFont(new Font("Times New Roman", Font.BOLD, 40));
        parentPanel.add(head_label_1);

        JLabel head_label = new JLabel("Please choose your identity");
        head_label.setBounds(250, 100, 1000, 50);
        // 创建 Font 对象，设置字体为宋体，大小为16
        head_label.setFont(new Font("Times New Roman", Font.BOLD, 40));
        parentPanel.add(head_label);

        ImageIcon parent_img = new ImageIcon("image/parent.jpg"); // 图片路径
        JLabel parent_img_label = new JLabel(parent_img);
        parent_img_label.setBounds(100, 150, 300, 300);
        parentPanel.add(parent_img_label);

        JButton mainButton1 = new JButton("parent");
        mainButton1.setBounds(80, 500, 150, 50);
        mainButton1.setFont(new Font("Times New Roman", Font.BOLD, 30));
        mainButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showCard(g, "parent");

            }
        });
        parentPanel.add(mainButton1);

        JButton mainButton2 = new JButton("children");
        mainButton2.setBounds(260, 500, 150, 50);
        mainButton2.setFont(new Font("Times New Roman", Font.BOLD, 30));
        mainButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showCard(g, "children");

            }
        });
        parentPanel.add(mainButton2);

        JLabel parent_label = new JLabel("You are parent");
        parent_label.setBounds(620, 250, 1000, 50);
        parent_label.setFont(new Font("Times New Roman", Font.BOLD, 40));

        parentPanel.add(parent_label);
        // parent_label.setText("New Text"); // 更改文本
        JLabel secret_label = new JLabel("PASSWORD : ");
        secret_label.setBounds(550, 340, 260, 50);
        secret_label.setFont(new Font("Times New Roman", Font.BOLD, 40));
        parentPanel.add(secret_label);

        JTextField textField = new JTextField(4);
        textField.setBounds(800, 340, 200, 50);
        parentPanel.add(textField);

        JButton logButton = new JButton("log in");
        logButton.setBounds(700, 450, 110, 50);
        logButton.setFont(new Font("Times New Roman", Font.BOLD, 30));

        parentPanel.add(logButton);

        logButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String text1 = textField.getText();
                System.out.println(text1);
                String contents = "";
                try {
                    FileReader fileReader = new FileReader(fileName);
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    contents = bufferedReader.readLine();
                    bufferedReader.close();
                } catch (IOException error) {
                    System.out.println("error_parent");
                    System.exit(1);
                }
                if (text1.equals(contents)) {
                    System.out.println("text1");
                    // 创建并显示第二个界面
                    GUI.home_page.templete_1 page1 = new GUI.home_page.templete_1(); // 注意，在这个地方由于文件在不同的包中，需要在文件之前添加目录。
                    GUI.home_page.templete_2 page2 = new GUI.home_page.templete_2();
                    GUI.Main secondUI = new GUI.Main(page1, page2);
                    secondUI.setVisible(true);
                    frame.setVisible(false); // 关闭当前界面

                } else {
                    System.out.println("error");
                    showCard(g, "error");
                }
            }
        });

        parentPanel.setLayout(null);

        cardPanel.add(parentPanel, "parent");
    }

    public static void showCard(GUIMain g, String cardName) {
        g.showCard(cardName);
    }

}
