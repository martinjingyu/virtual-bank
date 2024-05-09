package GUI.bank_page;

import Controller.MainController;
import GUI.MainFrame;
import Controller.bank.Bank_kid_control;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Bank_kid extends JPanel {
    private JPanel bank;
    private JButton button_goal;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button_history;
    private JButton button_yes;
    private JButton button_no;
    public JTextField savingGoalTextField;
    private MainFrame mainFrame;
    private int clickCount;
    private String saving;
    private String current;
    private Bank_kid_control bank_kid_control;
    private MainController mainController;



    public Bank_kid() {
        setLayout(null); // 使用绝对布局
        button1 = new JButton("INTO");
        button1.setBounds(590, 190, 140, 30);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dialog = new JDialog();
                bank_kid_control.getKid().getBank().transaction(dialog,"saving","current");
                dialog.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        // 在对话框关闭时刷新页面
                        mainFrame.refresh();
                    }
                });
            }
        });
        add(button1);

        button_goal = new JButton("Edit");
        button_goal.setBounds(680,80,70,30);
        savingGoalTextField = new JTextField();
        savingGoalTextField.setBounds(580, 80, 75, 30);
        savingGoalTextField.setVisible(false);
        add(savingGoalTextField);
        button_goal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clickCount++; // 每次点击增加点击次数
                // 根据点击次数的奇偶性设置文本框的可见性
                if (clickCount % 2 == 1) {
                    savingGoalTextField.setVisible(true);
                    revalidate();
                    repaint();
                } else {
                    savingGoalTextField.setVisible(false);
                    bank_kid_control.getKid().getBank().changeSavingGoal(savingGoalTextField);
                    mainFrame.refresh();
                }

            }
        });
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

    public Bank_kid(MainController mainController, MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.mainController = mainController;
        this.bank_kid_control = mainController.bank_kid_control;
        bank_kid_control.setGUI(this);
        setLayout(null); // 使用绝对布局

        button_goal = new JButton("Edit");
        button_goal.setBounds(680,80,70,30);
        savingGoalTextField = new JTextField();
        savingGoalTextField.setBounds(580, 80, 75, 30);
        savingGoalTextField.setVisible(false);
        add(savingGoalTextField);
        bank_kid_control.addButtonListener(button_goal);
        add(button_goal);

        button3 = new JButton("INTO");
        button3.setBounds(590, 310, 140, 30);
        bank_kid_control.addButtonListener(button3);
        add(button3);

        button_history = new JButton("Review");
        button_history.setBounds(590, 420, 140, 30);
        bank_kid_control.addButtonListener(button_history);
        add(button_history);
    }
    private void setController(Bank_kid_control bank_kid_control){
        this.bank_kid_control =bank_kid_control;
    }
    public MainFrame getMainFrame(){return this.mainFrame;}

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
        g.drawString(String.format("%.2f",bank_kid_control.getKid().getBank().getSavingGoal()), 610, 100);
//        g.drawString("300.00", 610, 100);

        g.setColor(Color.WHITE);
        g.fillRect(70, 150, 700, 90);
        g.setColor(Color.black);
        g.drawString("Current", 90, 220);
        g.drawString("Interest Rate", 150, 170);
        g.drawString("Total", 280, 170);
        g.drawString(String.format("%.2f",bank_kid_control.getKid().getBank().getCurrentInterestRate()), 150, 220);
        g.drawString(String.format("%.2f",bank_kid_control.getKid().getBank().getCurrentTotal()), 280, 220);

        g.setColor(Color.WHITE);
        g.fillRect(70, 270, 700, 90);
        g.setColor(Color.black);
        g.drawString("Saving", 90, 340);
        g.drawString("Interest Rate", 150, 290);
        g.drawString("Total", 280, 290);
        g.drawString(String.format("%.2f",bank_kid_control.getKid().getBank().getSavingInterestRate()), 150, 340);
        g.drawString(String.format("%.2f",bank_kid_control.getKid().getBank().getSavingTotal()), 280, 340);

        g.setColor(Color.WHITE);
        g.fillRect(70, 390, 700, 80);
        g.setColor(Color.black);
        g.drawString("Transaction History", 90, 440);
    }


}
