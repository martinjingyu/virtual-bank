package GUI.bank_page;

import Entity.Kids;
import GUI.MainFrame_kid;
import Controller.bank.Bank_kid_control;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The Bank_kid class represents the graphical user interface for the banking functionalities
 * accessible to kids. It extends the JPanel class.
 */
public class Bank_kid extends JPanel {
    private JPanel bank;
    private JButton button_goal;
    private JButton button1;
    private JButton button3;
    private JButton button_history;
    private MainFrame_kid mainFrameKid;
    public JLabel savingGoal;
    private Bank_kid_control bank_kid_control;
    private Kids kid;
    private final Color mainBgColor = new Color(191, 221, 239); // #bfddef
    private final Color panelBgColor = new Color(239, 239, 239); // #EFEFEF
    private final Color fontColor = new Color(49, 122, 232); // #317AE8
    private final Font font = new Font("Arial Black", Font.BOLD, 18);
    private JLabel currentTotal;
    private JLabel savingTotal;
    private JLabel income;
    private JLabel expenses;

    /**
     * Constructs a Bank_kid object with the specified Bank_kid_control and MainFrame_kid.
     *
     * @param bank_kid_control the controller for managing bank transactions for kids
     * @param mainFrameKid     the main frame for the kid's interface
     */
    public Bank_kid(Bank_kid_control bank_kid_control, MainFrame_kid mainFrameKid) {
        this.mainFrameKid = mainFrameKid;
        this.bank_kid_control = bank_kid_control;
        bank_kid_control.setGUI(this);
        setLayout(null); // Use absolute layout
        initData();

        setBackground(mainBgColor);
        button_goal = new JButton("Edit");
        button_goal.setBounds(680, 80, 70, 30);
        bank_kid_control.addEditSavingGoalButtonListener(button_goal);
        add(button_goal);

        button1 = new JButton("Details");
        button1.setBounds(590, 190, 140, 30);
        bank_kid_control.addCurrentAccountListener(button1);
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

    /**
     * Sets the controller for managing bank transactions for kids.
     *
     * @param bank_kid_control the controller for managing bank transactions for kids
     */
    private void setController(Bank_kid_control bank_kid_control) {
        this.bank_kid_control = bank_kid_control;
    }

    /**
     * Gets the main frame for the kid's interface.
     *
     * @return the main frame for the kid's interface
     */
    public MainFrame_kid getMainFrame() {
        return this.mainFrameKid;
    }

    /**
     * Initializes the data for displaying income, expenses, saving goals, and account balances.
     */
    public void initData() {
        removeAll();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/M/d");
        String formattedDate = LocalDate.now().format(formatter);

        // Display income
        income = new JLabel(String.valueOf(bank_kid_control.getKid().getTransactionList().getIncomeForDate(formattedDate,bank_kid_control.getKid().getAccountManager())));
        income.setBounds(150, 70, 100, 50);
        income.setForeground(new Color(-9975466));
        income.setFont(font);
        add(income);

        // Display expenses
        expenses = new JLabel(String.valueOf(bank_kid_control.getKid().getTransactionList().getExpensesForDate(formattedDate,bank_kid_control.getKid().getAccountManager())));
        expenses.setBounds(280, 70, 100, 50);
        expenses.setFont(font);
        expenses.setForeground(Color.RED);
        add(expenses);

        // Display saving goal
        savingGoal = new JLabel(String.valueOf(bank_kid_control.getKid().getAccountManager().getSavingGoal()));
        savingGoal.setBounds(590, 70, 100, 50);
        savingGoal.setForeground(new Color(-9975466));
        savingGoal.setFont(font);
        add(savingGoal);

        // Display current account balance
        currentTotal = new JLabel(String.valueOf(bank_kid_control.getKid().getAccountManager().getTotalCurrentBalance()));
        currentTotal.setBounds(280, 190, 100, 50);
        currentTotal.setForeground(new Color(-9975466));
        currentTotal.setFont(font);
        add(currentTotal);

        // Display saving account balance
        savingTotal = new JLabel(String.valueOf(bank_kid_control.getKid().getAccountManager().getTotalSavingBalance()));
        savingTotal.setFont(font);
        savingTotal.setForeground(new Color(-9975466));
        savingTotal.setBounds(280, 310, 100, 50);
        add(savingTotal);
    }

    /**
     * Gets the saving goal label.
     *
     * @return the saving goal label
     */
    public JLabel getSavingGoals() {
        return this.savingGoal;
    }

    /**
     * Updates the displayed account balances.
     */
    public void updateAccounts() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/M/d");
        String formattedDate = LocalDate.now().format(formatter);
        currentTotal.setText(String.valueOf(bank_kid_control.getKid().getAccountManager().getTotalCurrentBalance()));
        savingTotal.setText(String.valueOf(bank_kid_control.getKid().getAccountManager().getTotalSavingBalance()));
        income.setText(String.valueOf(bank_kid_control.getKid().getTransactionList().getIncomeForDate(formattedDate,bank_kid_control.getKid().getAccountManager())));
        expenses.setText(String.valueOf(bank_kid_control.getKid().getTransactionList().getExpensesForDate(formattedDate,bank_kid_control.getKid().getAccountManager())));
        revalidate();
        repaint();
    }

    /**
     * Refreshes the main frame for the kid's interface.
     */
    public void refresh() {
        mainFrameKid.refresh();
    }

    /**
     * This method overrides the {@code paintComponent} method to customize the appearance of the {@code Bank_kid} panel.
     * It draws various rectangles and text to display income, expenses, saving goals, current and saving account totals,
     * and transaction history.
     *
     * @param g the {@code Graphics} context in which to paint
     */

    protected void paintComponent(Graphics g) {
        Font titleFont = new Font("Arial", Font.BOLD, 22);
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.fillRect(70, 40, 350, 80); // Draw top white rectangle
        g.setColor(Color.BLACK);
        g.setFont(titleFont);
        g.drawString("Income", 150, 60); // Write text inside the rectangle
        g.drawString("Expenses", 280, 60);
        g.setColor(Color.GREEN);

        g.setColor(Color.WHITE);
        g.fillRect(500, 40, 270, 80);
        g.setColor(Color.BLACK);
        g.setFont(titleFont);
        g.drawString("Saving Goals", 570, 60);

        g.setColor(Color.WHITE);
        g.fillRect(70, 150, 700, 90);
        g.setColor(Color.BLACK);
        g.setFont(titleFont);
        g.drawString("Current", 90, 220);
        g.drawString("Total", 280, 170);

        g.setColor(Color.WHITE);
        g.fillRect(70, 270, 700, 90);
        g.setColor(Color.BLACK);
        g.setFont(titleFont);
        g.drawString("Saving", 90, 340);
        g.drawString("Total", 280, 290);

        g.setColor(Color.WHITE);
        g.fillRect(70, 390, 700, 80);
        g.setColor(Color.BLACK);
        g.setFont(titleFont);
        g.drawString("Transaction History", 90, 440);
    }

}
