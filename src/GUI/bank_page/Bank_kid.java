package GUI.bank_page;

import Entity.HistoryTransaction;
import Entity.HistoryTransactionList;
import Entity.Kids;
import GUI.MainFrame_kid;
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
    private MainFrame_kid mainFrameKid;
    private int clickCount;
    private String saving;
    private String current;
    private Bank_kid_control bank_kid_control;
    private Kids kid;
    private final Color mainBgColor = new Color(191, 221, 239); // #bfddef
    private final Color panelBgColor = new Color(239, 239, 239); // #EFEFEF
    private final Color fontColor = new Color(49, 122, 232); // #317AE8



    public Bank_kid(Kids kid) {
        this.kid=kid;
        setLayout(null); // 使用绝对布局
        button1 = new JButton("INTO");
        button1.setBounds(590, 190, 140, 30);
        add(button1);

        button_goal = new JButton("Edit");
        button_goal.setBounds(680,80,70,30);
        savingGoalTextField = new JTextField();
        savingGoalTextField.setBounds(580, 80, 75, 30);
        savingGoalTextField.setVisible(false);
        add(savingGoalTextField);

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
//                history_page review = new history_page(this.kid);
                JFrame Review_win = new JFrame();
                Review_win.setTitle("History");
                Review_win.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                Review_win.setLocationRelativeTo(null);
//                Review_win.add(review);
                Review_win.setSize(800, 400);
                Review_win.setVisible(true);
            }
        });
        add(button_history);
    }

    public Bank_kid(Bank_kid_control bank_kid_control, MainFrame_kid mainFrameKid) {
        this.mainFrameKid = mainFrameKid;
        this.bank_kid_control = bank_kid_control;
        bank_kid_control.setGUI(this);
        setLayout(null); // 使用绝对布局

        setBackground(mainBgColor);
        button_goal = new JButton("Edit");
        button_goal.setBounds(680,80,70,30);
        bank_kid_control.addEditSavingGoalButtonListener(button_goal);
        add(button_goal);

        button1 = new JButton("Details");
        button1.setBounds(590, 190, 140, 30);
        add(button1);

        button3 = new JButton("Details");
        button3.setBounds(590, 310, 140, 30);
        bank_kid_control.addSavingAccountListener(button3);
        add(button3);

        button_history = new JButton("Review");
        button_history.setBounds(590, 420, 140, 30);
        bank_kid_control.addReviewListener(button_history);
        add(button_history);
    }
    private void setController(Bank_kid_control bank_kid_control){
        this.bank_kid_control =bank_kid_control;
    }
    public MainFrame_kid getMainFrame(){return this.mainFrameKid;}

    protected void paintComponent(Graphics g) {
        Font titleFont = new Font("Arial", Font.PLAIN, 20);
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.fillRect(70, 40, 350, 80); // 绘制顶部白色矩形框
        g.setColor(Color.BLACK);
        g.setFont(titleFont);
        g.drawString("Income", 150, 60); // 在框内写字
//        g.drawString(HistoryTransactionList.formatAmount(Controller.getKid().getTransactionList());
        g.drawString("Expenses", 280, 60);
        g.setColor(Color.green);
//        g.drawString("+50.00", 150, 100); // 在下方显示 +50
//        g.drawString("-20.00", 280, 100);

        g.setColor(Color.WHITE);
        g.fillRect(500, 40, 270, 80);
        g.setColor(Color.black);
        g.setFont(titleFont);
        g.drawString("Saving Goals", 570, 60);
        g.setColor(Color.green);
        g.drawString(String.format("%.2f",bank_kid_control.getKid().getAccountManager().getSavingGoal()), 610, 100);
//        g.drawString("300.00", 610, 100);

        g.setColor(Color.WHITE);
        g.fillRect(70, 150, 700, 90);
        g.setColor(Color.black);
        g.setFont(titleFont);
        g.drawString("Current", 90, 220);
//        g.drawString("Interest Rate", 150, 170);
        g.drawString("Total", 280, 170);
//        g.drawString(String.format("%.2f",bank_kid_control.getKid().getBank().getCurrentInterestRate()), 150, 220);
        g.drawString(String.format("%.2f",bank_kid_control.getKid().getAccountManager().getTotalCurrentBalance()), 280, 220);

        g.setColor(Color.WHITE);
        g.fillRect(70, 270, 700, 90);
        g.setColor(Color.black);
        g.setFont(titleFont);
        g.drawString("Saving", 90, 340);
//        g.drawString("Interest Rate", 150, 290);
        g.drawString("Total", 280, 290);
//        g.drawString(String.format("%.2f",bank_kid_control.getKid().getBank().getSavingInterestRate()), 150, 340);
//        g.drawString(String.format("%.2f",bank_kid_control.getKid().getBank().getSavingTotal()), 280, 340);

        g.setColor(Color.WHITE);
        g.fillRect(70, 390, 700, 80);
        g.setColor(Color.black);
        g.setFont(titleFont);
        g.drawString("Transaction History", 90, 440);
    }


}
