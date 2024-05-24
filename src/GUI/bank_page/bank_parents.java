package GUI.bank_page;

import Controller.bank.Bank_parent_controller;
import Entity.Kids;
import GUI.MainFrame_parent;
import utill.read.ReadAll;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class bank_parents extends JPanel{
    private JPanel bank;
    private JButton currentDetails;
    private JButton savingDetails;
    private JButton button_history;
    private JButton changeInterestRate;
    private Kids kid;
    private bank_parents GUI;
    private MainFrame_parent mainFrameParent;
    private Bank_parent_controller bank_parent_controller;
    private final Color mainBgColor = new Color(191, 221, 239); // #bfddef
    private final Color panelBgColor = new Color(239, 239, 239); // #EFEFEF
    private final Font font = new Font("Arial Black", Font.BOLD, 18);

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

        changeInterestRate = new JButton("Change interest rates");
        changeInterestRate.setBounds(360,315,170,30);
        bank_parent_controller.addChangeInterestRate(changeInterestRate);
        add(changeInterestRate);

        savingDetails = new JButton("Details");
        savingDetails.setBounds(590, 315, 140, 30);
        bank_parent_controller.addSavingAccountListener(savingDetails);
        add(savingDetails);

        button_history = new JButton("Review");
        button_history.setBounds(590, 420, 140, 30);
        bank_parent_controller.addHistory(button_history);
        add(button_history);

    }

    public void initData(){
        removeAll();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy:M:d");
        String formattedDate = LocalDate.now().format(formatter);

        JLabel income = new JLabel(String.valueOf(bank_parent_controller.getKid().getTransactionList().getIncomeForDate(formattedDate)));
        income.setBounds(150,70,100,50);
        income.setForeground(new Color(-9975466));
        income.setFont(font);
        add(income);

        JLabel expenses = new JLabel(String.valueOf(bank_parent_controller.getKid().getTransactionList().getExpensesForDate(formattedDate)));
        expenses.setBounds(280,70,100,50);
        expenses.setFont(font);
        expenses.setForeground(Color.RED);
        add(expenses);

        JLabel savingGoal = new JLabel(String.valueOf(bank_parent_controller.getKid().getAccountManager().getSavingGoal()));
        savingGoal.setBounds(590,70,100,50);
        savingGoal.setForeground(new Color(-9975466));
        savingGoal.setFont(font);
        add(savingGoal);

        JLabel currentTotal = new JLabel(String.valueOf(bank_parent_controller.getKid().getAccountManager().getTotalCurrentBalance()));
        currentTotal.setBounds(280,190,100,50);
        currentTotal.setForeground(new Color(-9975466));
        currentTotal.setFont(font);
        add(currentTotal);

        JLabel savingTotal = new JLabel(String.valueOf(bank_parent_controller.getKid().getAccountManager().getTotalSavingBalance()));
        savingTotal.setForeground(new Color(-9975466));
        savingTotal.setFont(font);
        savingTotal.setBounds(280,310,100,50);
        add(savingTotal);
    }

    public void paintComponent(Graphics g) {
        setLayout(null); // 使用绝对布局
        setBackground(mainBgColor);

        Font titleFont = new Font("Arial", Font.BOLD, 22);
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


}
