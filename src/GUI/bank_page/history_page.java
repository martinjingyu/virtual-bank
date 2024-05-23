package GUI.bank_page;

import Controller.bank.HistoryController;
import Entity.*;
import utill.read.ReadAll;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import static java.lang.System.out;

public class history_page extends JPanel {

    private HistoryTransactionList transactionList;
    private JTextField nameTextField, priceTextField;
    private HistoryController historyController;
    private JPanel DetailPanel;
    private JPanel accountInfoPanel;
    private List<JLabel> dateList;



    // Define the custom colors
    private final Color mainBgColor = new Color(191, 221, 239); // #bfddef
    private final Color panelBgColor = new Color(239, 239, 239); // #EFEFEF
    private final Color fontColor = new Color(49, 122, 232); // #317AE8
    private final Color submitButtonColor = new Color(103, 201, 86); // #67C956
    private final Color borderColor = new Color(105, 105, 105); // #696969

    public history_page(HistoryController controller) {
        this.historyController = controller;
        this.transactionList = controller.getKid().getTransactionList();
        controller.setGUI(this);

        setBackground(mainBgColor); // Set overall background
        initUI();
        controller.addButtonListener();
    }
    public List<JLabel> getDateList(){return this.dateList;}


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
// Create the Date Panel
    private JPanel createDatePanel() {
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
            System.out.println(date);
            JLabel dateLabel = new JLabel(date);
            dateList.add(dateLabel);

            dateLabel.setFont(new Font("Arial", Font.PLAIN, 16));
            dateLabel.setOpaque(true);
            dateLabel.setBackground(Color.WHITE);
            dateLabel.setBorder(new LineBorder(borderColor)); // Set border for each label
            dateLabel.setHorizontalAlignment(JLabel.CENTER);
            dateLabel.setMaximumSize(new Dimension(Integer.MAX_VALUE, dateLabel.getPreferredSize().height));
//            dateLabel.addMouseListener(new MouseAdapter() {
//                @Override
//                public void mouseClicked(MouseEvent e) {
//                    dateLabel.setBackground(Color.BLUE);
//                }
//            });
            datePanel.add(dateLabel);
        }

        return datePanel;
    }


    // Create the Upload Products Panel
    private JPanel createDetailPanel() {
        JPanel uploadPanel = new JPanel(); // 使用默认的FlowLayout，改进自动布局
        uploadPanel.setLayout(new BoxLayout(uploadPanel, BoxLayout.Y_AXIS)); // 纵向排列
        uploadPanel.setBackground(panelBgColor);
        uploadPanel.setPreferredSize(new Dimension(540, 0));
        uploadPanel.setBorder(new LineBorder(borderColor, 1)); // 设置边框

        JScrollPane scrollPane = new JScrollPane(uploadPanel); // 创建滚动面板包含uploadPanel
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
//        scrollPane.setPreferredSize(new Dimension(540, 400)); // 设置滚动面板的首选大小
        scrollPane.setBorder(new LineBorder(borderColor, 1));  // 设置滚动面板的边框

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

        return uploadPanel; // 返回包含uploadPanel的滚动面板
    }


    // Create the Account Information Panel
    private JPanel createAccountInfoPanel() {
        JPanel accountInfoPanel = new JPanel();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/M/d");
        String formattedDate = LocalDate.now().format(formatter);

        accountInfoPanel.setLayout(new GridLayout(4, 1));  // 使用网格布局
        accountInfoPanel.setBackground(panelBgColor);
        accountInfoPanel.setBorder(new LineBorder(borderColor, 1)); // 添加边框

        // 总收入
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

        // 总支出
        JLabel expensesLabel = new JLabel("Total Expenses:  $" + HistoryTransactionList.formatAmount(totalExpenses));
        expensesLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        expensesLabel.setForeground(fontColor);

        // 总余额
        JLabel balanceLabel = new JLabel("Total Balance:    $" + HistoryTransactionList.formatAmount(totalBalance));
        balanceLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        balanceLabel.setForeground(fontColor);

        // 添加标签到面板
        accountInfoPanel.add(new JLabel("CURRENT ACCOUNT"));
        accountInfoPanel.add(incomeLabel);
        accountInfoPanel.add(expensesLabel);
        accountInfoPanel.add(balanceLabel);

        return accountInfoPanel;
    }


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
