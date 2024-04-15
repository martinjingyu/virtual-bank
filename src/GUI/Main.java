package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import GUI.templete_1;
import GUI.templete_2;


public class Main extends JFrame {
    private GridBagLayout gridBagLayout;
    private GridBagConstraints gridBagConstraints;
    private JPanel main_page;
    private JPanel menu;
    private JButton button1, button2, button3;
    private JPanel current_panel;
    private JPanel pg1, pg2;

    public Main(templete_1 pg1,templete_2 pg2) {
        super("demo");
        current_panel = pg1;
        this.pg1 = pg1;
        this.pg2 = pg2;
        Jframe_Jpanel();
        navi_button();
        setVisible(true);
    }

    public void Jframe_Jpanel(){
        // 设置主窗口的标题

        this.setSize(1920,1080);
        menu = new JPanel();
        main_page = new JPanel();
        main_page.setLayout(new BorderLayout());

        gridBagLayout = new GridBagLayout();
        this.setLayout(gridBagLayout);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill=GridBagConstraints.BOTH;


        menu.setBackground(Color.pink);
        main_page.setBackground(Color.blue);


        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight =GridBagConstraints.REMAINDER;
        gridBagConstraints.weightx = 0.05;
        gridBagConstraints.weighty = 1;
        gridBagLayout.setConstraints(menu, gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 0.95;
        gridBagConstraints.gridheight =GridBagConstraints.REMAINDER;
        gridBagLayout.setConstraints(main_page, gridBagConstraints);

        this.add(main_page);
        this.add(menu);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    public void navi_button(){

        button1 = new JButton("button1");
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                main_page.remove(current_panel);
                main_page.add(pg1,BorderLayout.CENTER);
                current_panel = pg1;
                revalidate();
                repaint();
            }
        });
        button2 = new JButton("button2");
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main_page.remove(current_panel);
                main_page.add(pg2,BorderLayout.CENTER);
                current_panel = pg2;
                revalidate();
                repaint();
            }
        });

        menu.add(button1);
        menu.add(button2);

    }
    public void sub_page(templete_1 pg1){

    }
    public static void main(String[] args) {
        templete_1 page1 = new templete_1();
        templete_2 page2 = new templete_2();
        new Main(page1,page2);
    }
}