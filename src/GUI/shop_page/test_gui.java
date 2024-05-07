package GUI.shop_page;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import GUI.shop_page.shop_kid;
import Entity.Kids;
import Entity.Bank;
import Entity.Kids;
import Entity.Product;
import Entity.ProductList;
import utill.read.ReadAll;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import exceptions.InsufficientFundsException;

public class test_gui extends JFrame {
    private BorderLayout borderLayout;
    private JPanel main_page;
    private JPanel menu;
    private JLabel button1, button2, button3, button4;
    private JPanel current_panel;

    public static void main(String[] args) {
        Kids kid = ReadAll.readall(String.valueOf(222));
        shop_kid page1 = new shop_kid(kid);
        shop_parent page2 = new shop_parent();
        new test_gui(page1);
    }

    public test_gui(JPanel panel) {
        super("demo");
        current_panel = panel;
        Jframe_Jpanel();
        navi_button();
        setVisible(true);
    }

    public void Jframe_Jpanel(){
        // 设置主窗口的标题

        this.setSize(900,540);
        borderLayout = new BorderLayout();
        menu = new JPanel();
        main_page = new JPanel(new BorderLayout());

        menu.setBackground(Color.pink);
        main_page.setBackground(Color.blue);

//        menu.setPreferredSize(new Dimension(51, 540));

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

}