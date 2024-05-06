package GUI.bank_page;

import Entity.Kids;
import Entity.Bank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class bank_kid extends JPanel implements ActionListener {
    private JPanel bank;
    private JButton button_goal;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton edit_goals;
    private JButton button_history;
    private JButton button_yes;
    private JButton button_no;
    private Kids kid;


    public bank_kid() {
        setLayout(null); // 使用绝对布局
        button1 = new JButton("INTO");
        button1.setBounds(590, 190, 140, 30);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                setLayout(null);
                JDialog dialog = new JDialog();//构造一个新的JFrame，作为新窗口。
                dialog.setBounds(// 让新窗口与SwingTest窗口示例错开50像素。
                        new Rectangle(
                                100,
                                100,
                                400, 400
                        )
                );

                JPanel whitePanel1 = new JPanel();
                whitePanel1.setBackground(Color.WHITE);
                whitePanel1.setBounds(50, 50, 100, 50);
                dialog.getContentPane().add(whitePanel1);

                JLabel jl1 = new JLabel();
                jl1.setBounds(160, 50, 20, 50);
                dialog.getContentPane().add(jl1);
                jl1.setText("to");

                // 创建白色框2
                JPanel whitePanel2 = new JPanel();
                whitePanel2.setBackground(Color.WHITE);
                whitePanel2.setBounds(190, 50, 100, 50);
                dialog.getContentPane().add(whitePanel2);

                JLabel jl2 = new JLabel();
                jl2.setBounds(50, 120, 200, 50);
                dialog.getContentPane().add(jl2);
                jl2.setText("How much?");

                JTextField textField = new JTextField();
                textField.setBounds(190, 120, 100, 50);
                dialog.getContentPane().add(textField);

                JLabel jl3 = new JLabel();
                dialog.getContentPane().add(jl3);
                jl3.setText("Are you sure to operate?");
                jl3.setBounds(50, 220, 300, 20);

                button_yes = new JButton("YES");
                button_yes.setBounds(100, 270, 80, 40);
                button_no = new JButton("NO");
                button_no.setBounds(200, 270, 80, 40);

                jl3.setVerticalAlignment(JLabel.CENTER);
                jl3.setHorizontalAlignment(JLabel.CENTER);// 注意方法名别写错了。
                dialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);    // 设置模式类型。
                dialog.setLayout(null); // 设置布局为null，使用绝对布局
                dialog.add(button_yes);
                dialog.add(button_no);
                dialog.setVisible(true);
            }
        });
        add(button1);

        button_goal = new JButton("Edit");
        button_goal.setBounds(680,60,70,30);
//        button_goal.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                kids_1.getBank().changeSavingGoal();
//            }
//        });
        add(button_goal);

//        button2 = new JButton("OUTO");
//        button2.setBounds(630, 190, 100, 30);
//        //button2.addActionListener();
//        add(button2);

        button3 = new JButton("INTO");
        button3.setBounds(590, 310, 140, 30);
        //button3.addActionListener();
        add(button3);

//        button4 = new JButton("OUTO");
//        button4.setBounds(630, 310, 100, 30);
//        //button4.addActionListener();
//        add(button4);

        button_history = new JButton("Review");
        button_history.setBounds(590, 420, 140, 30);
        button_history.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //this.dispose();
                history_page review = new history_page();
                JFrame Review_win = new JFrame();
                Review_win.setTitle("History");
                Review_win.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                Review_win.setLocationRelativeTo(null);
                Review_win.add(review);
                Review_win.setSize(800, 400);
                Review_win.setVisible(true);
            }
        });
        add(button_history);
    }
    public bank_kid(Kids kid) {
        this.kid = kid;

        setLayout(null); // 使用绝对布局
        button1 = new JButton("INTO");
        button1.setBounds(590, 190, 140, 30);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                setLayout(null);
                JDialog dialog = new JDialog();//构造一个新的JFrame，作为新窗口。
                dialog.setBounds(// 让新窗口与SwingTest窗口示例错开50像素。
                        new Rectangle(
                                100,
                                100,
                                400, 400
                        )
                );

                JPanel whitePanel1 = new JPanel();
                whitePanel1.setBackground(Color.WHITE);
                whitePanel1.setBounds(50, 50, 100, 50);
                dialog.getContentPane().add(whitePanel1);

                JLabel jl1 = new JLabel();
                jl1.setBounds(160, 50, 20, 50);
                dialog.getContentPane().add(jl1);
                jl1.setText("to");

                // 创建白色框2
                JPanel whitePanel2 = new JPanel();
                whitePanel2.setBackground(Color.WHITE);
                whitePanel2.setBounds(190, 50, 100, 50);
                dialog.getContentPane().add(whitePanel2);

                JLabel jl2 = new JLabel();
                jl2.setBounds(50, 120, 200, 50);
                dialog.getContentPane().add(jl2);
                jl2.setText("How much?");

                JTextField textField = new JTextField();
                textField.setBounds(190, 120, 100, 50);
                dialog.getContentPane().add(textField);

                JLabel jl3 = new JLabel();
                dialog.getContentPane().add(jl3);
                jl3.setText("Are you sure to operate?");
                jl3.setBounds(50, 220, 300, 20);

                button_yes = new JButton("YES");
                button_yes.setBounds(100, 270, 80, 40);
                button_no = new JButton("NO");
                button_no.setBounds(200, 270, 80, 40);

                jl3.setVerticalAlignment(JLabel.CENTER);
                jl3.setHorizontalAlignment(JLabel.CENTER);// 注意方法名别写错了。
                dialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);    // 设置模式类型。
                dialog.setLayout(null); // 设置布局为null，使用绝对布局
                dialog.add(button_yes);
                dialog.add(button_no);
                dialog.setVisible(true);
            }
        });
        add(button1);

//        button2 = new JButton("OUTO");
//        button2.setBounds(630, 190, 100, 30);
//        //button2.addActionListener();
//        add(button2);

        button3 = new JButton("INTO");
        button3.setBounds(590, 310, 140, 30);
        //button3.addActionListener();
        add(button3);

//        button4 = new JButton("OUTO");
//        button4.setBounds(630, 310, 100, 30);
//        //button4.addActionListener();
//        add(button4);

        button_history = new JButton("Review");
        button_history.setBounds(590, 420, 140, 30);
        button_history.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //this.dispose();
                history_page review = new history_page();
                JFrame Review_win = new JFrame();
                Review_win.setTitle("History");
                Review_win.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                Review_win.setLocationRelativeTo(null);
                Review_win.add(review);
                Review_win.setSize(800, 400);
                Review_win.setVisible(true);
            }
        });
        add(button_history);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.fillRect(70, 40, 350, 80); // 绘制顶部白色矩形框
        g.setColor(Color.BLACK);
        g.drawString("Income", 150, 60); // 在框内写字
        g.drawString("Expenses", 280, 60);
//        g.drawString("+50.00", 150, 100); // 在下方显示 +50
//        g.drawString("-20.00", 280, 100);

        g.setColor(Color.WHITE);
        g.fillRect(500, 40, 270, 80);
        g.setColor(Color.black);
        g.drawString("Saving Goals", 570, 60);
        g.drawString(String.format("%.2f",kid.getBank().getSavingGoal()), 610, 100);
//        g.drawString("300.00", 610, 100);

        g.setColor(Color.WHITE);
        g.fillRect(70, 150, 700, 90);
        g.setColor(Color.black);
        g.drawString("Current", 90, 220);
        g.drawString("Interest Rate", 150, 170);
        g.drawString("Total", 280, 170);
        g.drawString(String.format("%.2f",kid.getBank().getCurrentInterestRate()), 150, 220);
        g.drawString(String.format("%.2f",kid.getBank().getCurrentTotal()), 280, 220);

        g.setColor(Color.WHITE);
        g.fillRect(70, 270, 700, 90);
        g.setColor(Color.black);
        g.drawString("Saving", 90, 340);
        g.drawString("Interest Rate", 150, 290);
        g.drawString("Total", 280, 290);
        g.drawString(String.format("%.2f",kid.getBank().getSavingInterestRate()), 150, 340);
        g.drawString(String.format("%.2f",kid.getBank().getSavingTotal()), 280, 340);

        g.setColor(Color.WHITE);
        g.fillRect(70, 390, 700, 80);
        g.setColor(Color.black);
        g.drawString("Transaction History", 90, 440);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
