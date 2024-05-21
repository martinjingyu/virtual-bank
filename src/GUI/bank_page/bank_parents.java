package GUI.bank_page;

import Controller.bank.Bank_parent_controller;
import Entity.Kids;
import GUI.MainFrame_parent;
import utill.read.ReadAll;

import javax.swing.*;
import java.awt.*;

public class bank_parents extends JPanel{
    private JPanel bank;
    private JButton currentDetails;
    private JButton savingDetails;
    private JButton button_history;
    private JButton button_transfer;
    private JButton button_yes;
    private JButton button_no;
    private Kids kid;
    private bank_parents GUI;
    private MainFrame_parent mainFrameParent;
    private Bank_parent_controller bank_parent_controller;
    private final Color mainBgColor = new Color(191, 221, 239); // #bfddef
    private final Color panelBgColor = new Color(239, 239, 239); // #EFEFEF
    private final Color fontColor = new Color(49, 122, 232); // #317AE8
    private final Font font = new Font("Arial", Font.PLAIN, 20);

    public MainFrame_parent getMainFrame(){return this.mainFrameParent;}
    public bank_parents(Bank_parent_controller bank_parent_controller) {
        this.bank_parent_controller = bank_parent_controller;
        bank_parent_controller.setGUI(this);
        setLayout(null); // 使用绝对布局
        setBackground(mainBgColor);
        initData();

        currentDetails = new JButton("Details");
        currentDetails.setBounds(590, 190, 140, 30);
        bank_parent_controller.addCurrentDetails(currentDetails);
        add(currentDetails);

        savingDetails = new JButton("Details");
        savingDetails.setBounds(590, 310, 140, 30);
        bank_parent_controller.addSavingAccountListener(savingDetails);
        add(savingDetails);

        button_history = new JButton("Review");
        button_history.setBounds(590, 420, 140, 30);
        bank_parent_controller.addHistory(button_history);
        add(button_history);

    }

    public void initData(){
        JLabel income = new JLabel(String.valueOf(bank_parent_controller.getKid().getTransactionList().getIncomeForDate("2024/3/10")));
        income.setBounds(150,70,100,50);
        income.setFont(font);
        add(income);

        JLabel expenses = new JLabel(String.valueOf(bank_parent_controller.getKid().getTransactionList().getExpensesForDate("2024/3/10")));
        expenses.setBounds(280,70,100,50);
        expenses.setFont(font);
        add(expenses);

        JLabel savingGoal = new JLabel(String.valueOf(bank_parent_controller.getKid().getAccountManager().getSavingGoal()));
        savingGoal.setBounds(590,70,100,50);
        savingGoal.setFont(font);
        add(savingGoal);

        JLabel currentTotal = new JLabel(String.valueOf(bank_parent_controller.getKid().getAccountManager().getTotalCurrentBalance()));
        currentTotal.setBounds(280,190,100,50);
        currentTotal.setFont(font);
        add(currentTotal);

        JLabel savingTotal = new JLabel(String.valueOf(bank_parent_controller.getKid().getAccountManager().getTotalSavingBalance()));
        savingTotal.setFont(font);
        savingTotal.setBounds(280,310,100,50);
        add(savingTotal);
    }

    public void paintComponent(Graphics g) {
        setLayout(null); // 使用绝对布局
        setBackground(mainBgColor);

        Font titleFont = new Font("Arial", Font.PLAIN, 20);
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.fillRect(70, 40, 350, 80); // 绘制顶部白色矩形框
        g.setColor(Color.black);
        g.setFont(titleFont);
        g.drawString("Income", 150, 60); // 在框内写字
        g.drawString("Expenses", 280, 60);

        g.setColor(Color.WHITE);
        g.fillRect(500, 40, 270, 80);
        g.setColor(Color.black);
        g.setFont(titleFont);
        g.drawString("Saving Goals", 590, 60);

        g.setColor(Color.WHITE);
        g.fillRect(70, 150, 700, 90);
        g.setColor(Color.black);
        g.setFont(titleFont);
        g.drawString("Current", 90, 220);
        g.drawString("Total", 280, 170);


        g.setColor(Color.WHITE);
        g.fillRect(70, 270, 700, 90);
        g.setColor(Color.black);
        g.setFont(titleFont);
        g.drawString("Saving", 90, 340);
        g.drawString("Total", 280, 290);


        g.setColor(Color.WHITE);
        g.fillRect(70, 390, 700, 80);
        g.setColor(Color.black);
        g.setFont(titleFont);
        g.drawString("Transaction History", 90, 440);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Kids kid = ReadAll.readall(String.valueOf(222));
                bank_parents panel = new bank_parents(new Bank_parent_controller(kid));
                JFrame frame = new JFrame("Bank Parents Panel");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(800, 600);
                frame.getContentPane().add(panel);
                frame.setVisible(true);
            }
        });
    }

}
