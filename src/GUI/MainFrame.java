package GUI;
import Entity.Kids;
import GUI.bank_page.bank_kid;
import GUI.message_page.templete_message;
import GUI.shop_page.shop_kid;
import GUI.task_page.Task_kid;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class MainFrame extends JFrame {
    private BorderLayout borderLayout;
    private JPanel main_page;
    private JPanel menu;
    private JLabel button1, button2, button3, button4;
    private JPanel current_panel;
    private JPanel pg1, pg2,pg3,pg4;
    private Kids kid;


    public MainFrame(Kids kid) {
        super("demo");
        this.pg1 = new bank_kid(kid);
        this.pg2 = new shop_kid(kid);
        this.pg3 = new Task_kid(kid);
        this.pg4 = new templete_message(kid);
        this.kid = kid;

        current_panel = pg1;
        Jframe_Jpanel();
        navi_button();
        setVisible(true);
    }


    public void Jframe_Jpanel(){
        // 设置主窗口的标题

        this.setSize(960,540);
        borderLayout = new BorderLayout();
        menu = new JPanel();
        main_page = new JPanel(new BorderLayout());

        menu.setBackground(Color.pink);
        main_page.setBackground(Color.blue);

        menu.setPreferredSize(new Dimension(51, 540));



        this.add(main_page,BorderLayout.CENTER);

        this.add(menu, BorderLayout.WEST);
        main_page.add(current_panel,BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    public void navi_button(){

        button1 = new JLabel();
        ImageIcon icon = new ImageIcon("bank.png");
        Image img = icon.getImage();
        Image scaledImg = img.getScaledInstance(25, 25, Image.SCALE_SMOOTH); // 自动调整宽度，高度等比例缩放
        button1.setIcon(new ImageIcon(scaledImg)); // 替换为您的图片路径
        button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // 添加按钮点击事件的处理代码
                main_page.remove(current_panel);
                current_panel = pg1;
                main_page.add(current_panel, BorderLayout.CENTER);
                revalidate();
                repaint();
            }
        });
        button1.setHorizontalAlignment(SwingConstants.CENTER); // 设置水平居中对齐

        button2 = new JLabel();
        button2.setIcon(new ImageIcon(scaledImg)); // 替换为您的图片路径
        button2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // 添加按钮点击事件的处理代码
                main_page.remove(current_panel);
                current_panel = pg2;
                main_page.add(current_panel, BorderLayout.CENTER);
                revalidate();
                repaint();
            }
        });
        button2.setHorizontalAlignment(SwingConstants.CENTER); // 设置水平居中对齐

        button3 = new JLabel();
        button3.setIcon(new ImageIcon(scaledImg)); // 替换为您的图片路径
        button3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // 添加按钮点击事件的处理代码
                main_page.remove(current_panel);
                main_page.remove(current_panel);
                current_panel = pg3;
                main_page.add(current_panel, BorderLayout.CENTER);
                revalidate();
                repaint();
            }
        });
        button3.setHorizontalAlignment(SwingConstants.CENTER); // 设置水平居中对齐

        button4 = new JLabel();
        button4.setIcon(new ImageIcon(scaledImg)); // 替换为您的图片路径
        button4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // 添加按钮点击事件的处理代码
                main_page.remove(current_panel);
                main_page.remove(current_panel);
                current_panel = pg4;
                main_page.add(current_panel, BorderLayout.CENTER);
                revalidate();
                repaint();
            }
        });
        button4.setHorizontalAlignment(SwingConstants.CENTER); // 设置水平居中对齐

        menu.setLayout(new GridLayout(15, 1)); // 竖直排列按钮
        menu.add(new JLabel());
        menu.add(new JLabel());

        menu.add(button1);
        menu.add(new JLabel());

        menu.add(button2);
        menu.add(new JLabel());

        menu.add(button3);
        menu.add(new JLabel());

        menu.add(button4);
        menu.add(new JLabel());


    }

    public static void main(String[] args) {
        MainFrame main = new MainFrame(new Kids());
    }

}