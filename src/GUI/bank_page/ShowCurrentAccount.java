package GUI.bank_page;

import Entity.AccountManager;
import Entity.CurrentAccount;
import Entity.Kids;
import Entity.Message;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.List;
import javax.swing.border.*;

/**
 * The ShowCurrentAccount class represents the GUI for displaying current accounts.
 */
public class ShowCurrentAccount extends JFrame {
    private JPanel mainContent;
    private CurrentComponentList currentComponentList;
    private JPanel accountGrid;
    private JPanel infoPanel;
    private JPanel addButton;
    private final Color mainBgColor = new Color(191, 221, 239); // #bfddef
    private final Color panelBgColor = new Color(239, 239, 239); // #EFEFEF
    private final Color fontColor = new Color(49, 122, 232); // #317AE8
    private final Color submitButtonColor = new Color(103, 201, 86); // #67C956
    private final Color borderColor = new Color(105, 105, 105); // #696969

    /**
     * Constructor to initialize the ShowCurrentAccount GUI.
     */
    public ShowCurrentAccount() {
        initUI();
        pack();
        setVisible(true);
    }

    /**
     * Initializes the UI components.
     */
    private void initUI() {
        setTitle("Current Account");
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.mainContent = new JPanel();
        setContentPane(mainContent);
        mainContent.setPreferredSize(new Dimension(900, 700));
        mainContent.setLayout(new BorderLayout(20, 20)); // Added horizontal and vertical gaps
        mainContent.setBorder(new EmptyBorder(20, 40, 20, 40));
        mainContent.setBackground(mainBgColor);
        mainContent.add(createHeaderPanel(), BorderLayout.NORTH);
    }

    /**
     * Initializes the data to be displayed in the GUI.
     *
     * @param accountList      The list of current accounts to display.
     * @param whetherParent    Indicates whether the user is a parent.
     * @param accountManager   The account manager associated with the accounts.
     */
    public void initData(List<CurrentAccount> accountList, Boolean whetherParent, AccountManager accountManager) {
        accountGrid = createAccountGrid(accountList, whetherParent);
        mainContent.add(accountGrid, BorderLayout.CENTER);
        infoPanel = createTotalInfoPanel(accountManager);
        mainContent.add(infoPanel, BorderLayout.SOUTH);
        pack();
        setVisible(true);
    }

    /**
     * Refreshes the GUI with updated data.
     *
     * @param accountList     The updated list of current accounts.
     * @param accountManager  The updated account manager.
     */
    public void refresh(List<CurrentAccount> accountList, AccountManager accountManager) {
        mainContent.remove(accountGrid);
        mainContent.remove(infoPanel);
        initData(accountList, false, accountManager);
    }

    /**
     * Creates the header panel for the GUI.
     *
     * @return The header panel.
     */
    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(mainBgColor);
        Border headerBorder = BorderFactory.createMatteBorder(0, 0, 3, 0, borderColor); // Added bottom border
        headerPanel.setBorder(headerBorder);
        JLabel titleLabel = new JLabel("Current Account");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 40));
        titleLabel.setForeground(fontColor);
        headerPanel.add(titleLabel);
        return headerPanel;
    }

    /**
     * Creates a panel containing total account information.
     *
     * @param accountManager The account manager containing the account information.
     * @return The panel containing total account information.
     */
    private JPanel createTotalInfoPanel(AccountManager accountManager) {
        JPanel accountInfoPanel = new JPanel();
        accountInfoPanel.setLayout(new GridLayout(4, 1));  // 使用网格布局
        accountInfoPanel.setBackground(panelBgColor);
        accountInfoPanel.setBorder(new LineBorder(borderColor, 1)); // 添加边框
        JLabel inputLabel = new JLabel("Total Money:     $" + accountManager.getTotalCurrentBalance());
        inputLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        inputLabel.setForeground(fontColor);
        accountInfoPanel.add(new JLabel("Current ACCOUNT"));
        accountInfoPanel.add(inputLabel);
        return accountInfoPanel;
    }

    /**
     * Creates the grid layout for displaying current accounts.
     *
     * @param accountList    The list of current accounts to display.
     * @param whetherParent  Indicates whether the user is a parent.
     * @return The panel containing the account grid layout.
     */
    private JPanel createAccountGrid(List<CurrentAccount> accountList, Boolean whetherParent) {
        accountGrid = new JPanel();
        accountGrid.setLayout(new GridLayout(4, 3, 10, 5)); // 增加了行列间的间隔
        accountGrid.setBackground(Color.LIGHT_GRAY);
        accountGrid.setBorder(new LineBorder(Color.BLACK, 2)); // 添加边框
        currentComponentList = new CurrentComponentList(accountList);
        for (int i = 0; i < accountList.size(); i++) {
            accountGrid.add(createComponents(i, currentComponentList, whetherParent));
        }
        if (accountList.size() < 12) {
            if (whetherParent) {
                accountGrid.add(new JPanel());
            }
            if (!whetherParent) {
                addButton = createAddComponents();
                accountGrid.add(addButton);
            }
        }
        for (int i = 0; i < 11 - accountList.size(); i++) {
            accountGrid.add(new JPanel());
        }
        return accountGrid;
    }

    /**
     * Creates components for displaying each individual current account.
     *
     * @param i                   The index of the current account.
     * @param currentComponentList  The list of current components.
     * @param whetherParent        Indicates whether the user is a parent.
     * @return The panel containing the components for the current account.
     */
    private JPanel createComponents(int i, CurrentComponentList currentComponentList, Boolean whetherParent) {
        JPanel component = new JPanel();
        component.setLayout(new BoxLayout(component, BoxLayout.Y_AXIS));
        component.setBorder(new CompoundBorder(new LineBorder(Color.GRAY, 1), new EmptyBorder(5, 10, 5, 10)));
        component.setBackground(Color.white);
        component.setPreferredSize(new Dimension(200, 150));
        CurrentAccount account = currentComponentList.getCurrentAccountList().get(i);
        JButton transferButton = currentComponentList.getTransferButton().get(i);
        JLabel nameLabel = new JLabel(currentComponentList.getCurrentAccountList().get(i).getName(), SwingConstants.CENTER);
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 10));
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel balanceLabel = new JLabel("Balance:" + currentComponentList.getCurrentAccountList().get(i).getBalance() + "$", SwingConstants.CENTER);
        balanceLabel.setFont(new Font("Arial", Font.PLAIN, 10));
        balanceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        transferButton.setFont(new Font("Arial", Font.PLAIN, 10));
        transferButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        component.add(Box.createVerticalStrut(20));
        component.add(nameLabel);
        component.add(Box.createVerticalStrut(10));
        component.add(balanceLabel);
        component.add(Box.createVerticalStrut(10));
        if (!whetherParent) {
            component.add(transferButton);
            component.add(Box.createVerticalStrut(20));
        }
        return component;
    }

    /**
     * Creates the panel for adding a new current account.
     *
     * @return The panel for adding a new current account.
     */
    private JPanel createAddComponents() {
        JPanel addPanel = new JPanel();
        addPanel.setBorder(new CompoundBorder(new LineBorder(Color.GRAY, 1), new EmptyBorder(20, 10, 5, 10)));
        addPanel.setBackground(Color.white);
        addPanel.setPreferredSize(new Dimension(200, 120));
        JLabel addLabel = new JLabel("+", SwingConstants.CENTER);
        addLabel.setFont(new Font("Arial", Font.BOLD, 50));
        addLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        addPanel.add(addLabel);
        return addPanel;
    }

    /**
     * Handles the addition of a new account and updates the GUI accordingly.
     *
     * @param accountManager The account manager associated with the new account.
     * @param name           The name of the new account.
     * @param kid            The kid associated with the new account.
     */
    public void afterAddAccount(AccountManager accountManager, String name, Kids kid) {
        accountManager.createNewCurrentAccount(name);
        kid.getMessagelist().addMessage(new Message("system_kid", "You have created a new current account " + "\"" + name + "\""));
        mainContent.removeAll();
        mainContent.add(createHeaderPanel(), BorderLayout.NORTH);
        initData(accountManager.getCurrentAccounts(), false, accountManager);
        pack();
        setVisible(true);
    }

    /**
     * Retrieves the add button panel.
     *
     * @return The add button panel.
     */
    public JPanel getAddButton() {
        return addButton;
    }

    /**
     * Retrieves the current component list.
     *
     * @return The current component list.
     */
    public CurrentComponentList getComponentList() {
        return currentComponentList;
    }

    /**
     * The main method for testing the ShowCurrentAccount class.
     *
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        // 创建 JFrame 并显示
        JFrame frame = new ShowCurrentAccount();
    }
}