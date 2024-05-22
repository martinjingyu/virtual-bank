package GUI.bank_page;

import Entity.AccountManager;
import Entity.CurrentAccount;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.List;
import javax.swing.border.*;

public class ShowCurrentAccount extends JFrame {
    private JButton actionButton;
    private JPanel mainContent;
    private CurrentComponentList currentComponentList;
    private JPanel accountGrid;
    private JPanel infoPanel;
    private JPanel addButton;
    private Timer timer;
    private final Color mainBgColor = new Color(191, 221, 239); // #bfddef
    private final Color panelBgColor = new Color(239, 239, 239); // #EFEFEF
    private final Color fontColor = new Color(49, 122, 232); // #317AE8
    private final Color submitButtonColor = new Color(103, 201, 86); // #67C956
    private final Color borderColor = new Color(105, 105, 105); // #696969

    public ShowCurrentAccount() {

        initUI();

        pack();
        setVisible(true);
    }

    public void initData(List<CurrentAccount> accountList, Boolean whetherParent,AccountManager accountManager){
        accountGrid = createAccountGrid(accountList,whetherParent);
        mainContent.add(accountGrid,BorderLayout.CENTER);
        infoPanel = createTotalInfoPanel(accountManager);
        mainContent.add(infoPanel,BorderLayout.SOUTH);

        pack();
        setVisible(true);
    }
    public void refresh(List<CurrentAccount> accountList,AccountManager accountManager){
        mainContent.remove(accountGrid);
        mainContent.remove(infoPanel);
        initData(accountList,false,accountManager);
    }
    private void initUI(){
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
    private JPanel createComponents(int i, CurrentComponentList currentComponentList,Boolean whetherParent) {
        // 创建组件的面板
        JPanel component = new JPanel();

        component.setLayout(new BoxLayout(component, BoxLayout.Y_AXIS));
        component.setBorder(new CompoundBorder(new LineBorder(Color.GRAY, 1), new EmptyBorder(5, 10, 5, 10)));
        component.setBackground(Color.white);
        component.setPreferredSize(new Dimension(200, 150));

        // 创建标签并设置格式
        CurrentAccount account = currentComponentList.getCurrentAccountList().get(i);
        JButton transferButton = currentComponentList.getTransferButton().get(i);

        JLabel nameLabel = new JLabel(currentComponentList.getCurrentAccountList().get(i).getName(), SwingConstants.CENTER);
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 10));
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);


        JLabel balanceLabel = new JLabel("Balance:"+currentComponentList.getCurrentAccountList().get(i).getBalance()+"$", SwingConstants.CENTER);
        balanceLabel.setFont(new Font("Arial", Font.PLAIN, 10));
        balanceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);


        transferButton.setFont(new Font("Arial", Font.PLAIN, 10));
        transferButton.setAlignmentX(Component.CENTER_ALIGNMENT);



        // 添加组件到面板
        component.add(Box.createVerticalStrut(20));
        component.add(nameLabel);
        component.add(Box.createVerticalStrut(10));
        component.add(balanceLabel);
        component.add(Box.createVerticalStrut(10));
        if(whetherParent==false){
            component.add(transferButton);
            component.add(Box.createVerticalStrut(20));
        }


        return component;
    }

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

    private JPanel createTotalInfoPanel(AccountManager accountManager){
        JPanel accountInfoPanel = new JPanel();
        accountInfoPanel.setLayout(new GridLayout(4, 1));  // 使用网格布局
        accountInfoPanel.setBackground(panelBgColor);
        accountInfoPanel.setBorder(new LineBorder(borderColor, 1)); // 添加边框

        // 总收入
        JLabel inputLabel = new JLabel("Total Money:     $" + accountManager.getTotalCurrentBalance());
        inputLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        inputLabel.setForeground(fontColor);


        accountInfoPanel.add(new JLabel("Current ACCOUNT"));
        accountInfoPanel.add(inputLabel);

        return accountInfoPanel;

    }

    private JPanel createAccountGrid(List<CurrentAccount> accountList,Boolean whetherParent) {
        accountGrid = new JPanel();
        accountGrid.setLayout(new GridLayout(4, 3, 10, 5)); // 增加了行列间的间隔
        accountGrid.setBackground(Color.LIGHT_GRAY);
        accountGrid.setBorder(new LineBorder(Color.BLACK, 2)); // 添加边框
        currentComponentList = new CurrentComponentList(accountList);


        for (int i = 0; i < accountList.size(); i++) {
            accountGrid.add(createComponents(i, currentComponentList,whetherParent));
        }
        if(accountList.size()<12){
            if(whetherParent){
                accountGrid.add(new JPanel());
            }
            if(!whetherParent){
                addButton = createAddComponents();
                accountGrid.add(addButton);
            }
        }

        for (int i = 0; i < 11-accountList.size(); i++) {
            accountGrid.add(new JPanel());
        }

        return accountGrid;

    }
    private JPanel createAddComponents(){
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
    public void afterAddAccount(AccountManager accountManager, String name){
        accountManager.createNewCurrentAccount(name);
        mainContent.removeAll();
        mainContent.add(createHeaderPanel(), BorderLayout.NORTH);
        initData(accountManager.getCurrentAccounts(),false,accountManager);
        pack();
        setVisible(true);
    }
    public JPanel getAddButton(){
        return addButton;
    }
    public CurrentComponentList getComponentList() {
        return currentComponentList;
    }

    public static void main(String[] args) {
        // 创建 JFrame 并显示
        JFrame frame = new ShowCurrentAccount();
    }
}





