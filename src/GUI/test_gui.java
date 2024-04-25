package GUI;

//[pwd]:4/25：暂时未使用文件
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import GUI.home_page.homepage_tem;
import GUI.home_page.templete_1;

public class test_gui extends JFrame {
    private GridBagLayout gridBagLayout;
    private GridBagConstraints gridBagConstraints;
    private JPanel main_page;
    private JPanel menu;
    private JButton button1, button2;
    private JPanel current_panel;

    public static void main(String[] args) {
        homepage_tem page1 = new homepage_tem();
        new test_gui(page1);
    }

    public test_gui(JPanel panel) {
        super("demo");
        current_panel = panel;
        Jframe_Jpanel();
        navi_button();
        setVisible(true);
    }

    public void Jframe_Jpanel() {
        // 设置主窗口的标题

        this.setSize(1920, 1080);
        menu = new JPanel();
        main_page = new JPanel();
        main_page.setLayout(new BorderLayout());

        gridBagLayout = new GridBagLayout();
        this.setLayout(gridBagLayout);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.BOTH;

        menu.setBackground(Color.pink);
        main_page.setBackground(Color.blue);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = GridBagConstraints.REMAINDER;
        gridBagConstraints.weightx = 0.05;
        gridBagConstraints.weighty = 1;
        gridBagLayout.setConstraints(menu, gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 0.95;
        gridBagConstraints.gridheight = GridBagConstraints.REMAINDER;
        gridBagLayout.setConstraints(main_page, gridBagConstraints);

        this.add(main_page);
        this.add(menu);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void navi_button() {

        button1 = new JButton("button1");
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                main_page.remove(current_panel);
                main_page.add(current_panel, BorderLayout.CENTER);
                revalidate();
                repaint();
            }
        });
        button2 = new JButton("button2");
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main_page.remove(current_panel);
                revalidate();
                repaint();
            }
        });

        menu.add(button1);
        menu.add(button2);

    }

    public void sub_page(templete_1 pg1) {

    }

}