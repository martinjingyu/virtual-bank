package GUI.bank_page;

import Controller.bank.HistoryController;
import Entity.*;
import utill.read.ReadAll;

import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;


/**
 * This class represents the GUI for the history page, displaying transaction history and related information.
 */
public class history_page extends JPanel {

    private HistoryTransactionList transactionList;
    private JTextField nameTextField, priceTextField;
    private HistoryController historyController;
    private JScrollPane DetailPanel;
    private JPanel accountInfoPanel;
    private List<JLabel> dateList;

    // Define the custom colors
    private final Color mainBgColor = new Color(191, 221, 239); // #bfddef
    private final Color panelBgColor = new Color(239, 239, 239); // #EFEFEF
    private final Color fontColor = new Color(49, 122, 232); // #317AE8
    private final Color submitButtonColor = new Color(103, 201, 86); // #67C956
    private final Color borderColor = new Color(105, 105, 105); // #696969

    /**
     * Constructs a new {@code history_page} object with the specified {@code HistoryController}.
     *
     * @param controller the controller for handling history operations
     */
    public history_page(HistoryController controller) {
        this.historyController = controller;
        this.transactionList = controller.getKid().getTransactionList();
        controller.setGUI(this);

        setBackground(mainBgColor); // Set overall background
        initUI();
        controller.addButtonListener();
    }

    /**
     * Retrieves the list of date labels.
     *
     * @return the list of date labels
     */
    public List<JLabel> getDateList() {
        return this.dateList;
    }

    /**
     * Initializes the user interface components.
     */
    private void initUI() {
        // Set the layout with padding around the entire layout
        setPreferredSize(new Dimension(900, 540));
        setLayout(new BorderLayout(20, 20)); // Added horizontal and vertical gaps
        setBorder(new EmptyBorder(20, 40, 20, 40)); // Added horizontal and vertical margins

        add(createHeaderPanel(), BorderLayout.NORTH);
        add(createDatePanel(), BorderLayout.WEST);
        DetailPanel = createDetailPanel();
        add(DetailPanel, BorderLayout.CENTER);
        accountInfoPanel = createAccountInfoPanel();
        add(accountInfoPanel, BorderLayout.SOUTH);
    }

    /**
     * Refreshes the user interface.
     */
    public void refreshUI() {
        remove(DetailPanel);
        remove(accountInfoPanel);
        DetailPanel = createDetailPanel();
        accountInfoPanel = createAccountInfoPanel();
        add(DetailPanel, BorderLayout.CENTER);
        add(accountInfoPanel, BorderLayout.SOUTH);
        validate();
        repaint();
    }

    // Create the header "Family Mall" with margin
    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(mainBgColor);
        Border headerBorder = BorderFactory.createMatteBorder(0, 0, 3, 0, borderColor); // Added bottom border
        headerPanel.setBorder(headerBorder);

        JLabel titleLabel = new JLabel("Transaction History");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 40));
        titleLabel.setForeground(fontColor);

        headerPanel.add(titleLabel);
        return headerPanel;
    }

    // Create the To-Do List Panel
    private JScrollPane createDatePanel() {
        JPanel datePanel = new JPanel();
        datePanel.setLayout(new BoxLayout(datePanel, BoxLayout.Y_AXIS));
        datePanel.setPreferredSize(new Dimension(270, 400));
        datePanel.setBackground(panelBgColor);

        JScrollPane scrollPane = new JScrollPane(datePanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(new LineBorder(borderColor, 1));  // Set a border to the scroll pane

        List<String> dates = transactionList.getDateList(); // Assuming you have this method to get dates
        dateList = new ArrayList<>();
        for (String date : dates) {
            JLabel dateLabel = new JLabel(date);
            dateList.add(dateLabel);

            dateLabel.setFont(new Font("Arial", Font.PLAIN, 16));
            dateLabel.setOpaque(true);
            dateLabel.setBackground(Color.WHITE);
            dateLabel.setBorder(new LineBorder(borderColor)); // Set border for each label
            dateLabel.setHorizontalAlignment(JLabel.CENTER);
            dateLabel.setMaximumSize(new Dimension(Integer.MAX_VALUE, dateLabel.getPreferredSize().height));
            datePanel.add(dateLabel);
        }

        return scrollPane;
    }

    // Create the Upload Products Panel
    private JScrollPane createDetailPanel() {
        JPanel uploadPanel = new JPanel(); // Use default FlowLayout, improved automatic layout
        uploadPanel.setLayout(new BoxLayout(uploadPanel, BoxLayout.Y_AXIS)); // Arrange vertically
        uploadPanel.setBackground(panelBgColor);
        uploadPanel.setPreferredSize(new Dimension(540, 400));
        uploadPanel.setBorder(new LineBorder(borderColor, 1)); // Set border

        List<String> details = transactionList.getTransactionDetails(historyController.getSelectedDate());

        for (String detail : details) {
            JLabel detailLabel = new JLabel(detail);
            detailLabel.setFont(new Font("Arial", Font.PLAIN, 16));
            detailLabel.setOpaque(true);
            detailLabel.setBackground(Color.WHITE);
            detailLabel.setBorder(new LineBorder(borderColor));
            detailLabel.setHorizontalAlignment(JLabel.LEFT);
            detailLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            detailLabel.setMaximumSize(new Dimension(Integer.MAX_VALUE, detailLabel.getPreferredSize().height));

            uploadPanel.add(detailLabel);
        }

        JScrollPane scrollPane = new JScrollPane(uploadPanel); // Create a scroll panel containing uploadPanel
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(new LineBorder(borderColor, 1));  // Set border for the scroll pane

        return scrollPane; // Return the scroll pane containing uploadPanel
    }

    // Create the Account Information Panel
    private JPanel createAccountInfoPanel() {
        JPanel accountInfoPanel = new JPanel();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/M/d");
        String formattedDate = LocalDate.now().format(formatter);

        accountInfoPanel.setLayout(new GridLayout(4, 1));  // Use grid layout
        accountInfoPanel.setBackground(panelBgColor);
        accountInfoPanel.setBorder(new LineBorder(borderColor, 1)); // Add border

        // Total income
        double totalIncome;
        double totalExpenses;
        double totalBalance;

        if (historyController.hasSelectedDate()) {
            totalIncome = transactionList.getIncomeForDate(historyController.getSelectedDate());
            totalExpenses = transactionList.getExpensesForDate(historyController.getSelectedDate());
            totalBalance = transactionList.getBalanceForDate(historyController.getSelectedDate());
        } else {
            totalIncome = transactionList.getTotalIncome();
            totalExpenses = transactionList.getTotalExpenses();
            totalBalance = transactionList.getTotalBalance();
        }
        JLabel incomeLabel = new JLabel("Total Income:     $" + HistoryTransactionList.formatAmount(totalIncome));
        incomeLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        incomeLabel.setForeground(fontColor);

        // Total expenses
        JLabel expensesLabel = new JLabel("Total Expenses:  $" + HistoryTransactionList.formatAmount(totalExpenses));
        expensesLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        expensesLabel.setForeground(fontColor);

        // Total balance
        JLabel balanceLabel = new JLabel("Total Balance:    $" + HistoryTransactionList.formatAmount(totalBalance));
        balanceLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        balanceLabel.setForeground(fontColor);

        // Add labels to the panel
        accountInfoPanel.add(new JLabel("CURRENT ACCOUNT"));
        accountInfoPanel.add(incomeLabel);
        accountInfoPanel.add(expensesLabel);
        accountInfoPanel.add(balanceLabel);

        return accountInfoPanel;
    }

    /**
     * The main method to run the history page GUI.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        try {
            Kids kid = ReadAll.readall(String.valueOf(222));
            JFrame frame = new JFrame("Shop Application");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setContentPane(new history_page(new HistoryController(kid)));
            frame.pack();
            frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
