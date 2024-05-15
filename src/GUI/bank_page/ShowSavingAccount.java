package GUI.bank_page;

import Controller.bank.SavingAccountController;
import Entity.Account;
import Entity.HistoryTransactionList;
import Entity.SavingAccount;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.swing.border.*;

public class ShowSavingAccount extends JFrame {
    private JButton actionButton;
    private JPanel mainContent;
    private ComponentList componentList;
    private JPanel accountGrid;
    private JPanel infoPanel;
    private List<JPanel> finishPanelList;
    private FinishList finishList;
    private List<Duration> remainingTime;
    private JPanel addButton;
    private Timer timer;
    private final Color mainBgColor = new Color(191, 221, 239); // #bfddef
    private final Color panelBgColor = new Color(239, 239, 239); // #EFEFEF
    private final Color fontColor = new Color(49, 122, 232); // #317AE8
    private final Color submitButtonColor = new Color(103, 201, 86); // #67C956
    private final Color borderColor = new Color(105, 105, 105); // #696969

    public ShowSavingAccount() {

        initUI();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);
    }
    private void updateProgressBar(List<SavingAccount> savingAccountList, List<JProgressBar> progressBarList) {
        // 计算已经过的时间比例
        int i;
        for(i=0;i<savingAccountList.size();i++){
            SavingAccount savingAccount = savingAccountList.get(i);
            JProgressBar progressBar = progressBarList.get(i);
            LocalDateTime currentTime = LocalDateTime.now();
            LocalDateTime startTime = savingAccount.getStartTime();
            LocalDateTime endTime = savingAccount.getEndTime();
            double progress = 100.0 * Duration.between(startTime, currentTime).toMillis() /
                    Duration.between(startTime, endTime).toMillis();

            // 更新进度条的值
            progressBar.setValue((int) progress);

            // 检查是否已完成
            if (progress >= 100) {
                Component[] list = accountGrid.getComponents();
                list[i] = finishPanelList.get(i);

                accountGrid.removeAll();
                for(int j=0; j< list.length;j++){
                    accountGrid.add(list[j]);
                }
            }
        }

    }
    private void updateRemaining(List<SavingAccount> accountList){
        for (int i = 0; i < accountList.size(); i++) {
            SavingAccount account = accountList.get(i);
            Duration remaining = Duration.between(LocalDateTime.now(), account.getEndTime());
            long hours = remaining.toHours();
            long minutes = remaining.toMinutesPart();
            long seconds = remaining.toSecondsPart();
            String remainingTime = String.format("%02d:%02d:%02d", hours, minutes, seconds);
            componentList.getRemainingList().get(i).setText("Remaining time: " + remainingTime);
        }
    }

    public void initData(List<SavingAccount> accountList){
        createFinishPanelList(accountList);
        accountGrid = createAccountGrid(accountList);
        mainContent.add(accountGrid,BorderLayout.CENTER);
        infoPanel = createTotalInfoPanel();
        mainContent.add(infoPanel,BorderLayout.SOUTH);
        timer = new Timer(1000, e -> {updateProgressBar(accountList,componentList.getBarlist());updateRemaining(accountList);});
        timer.start();
        pack();
        setVisible(true);
    }
    public void refresh(List<SavingAccount> accountList){
        createFinishPanelList(accountList);
    }
    private void initUI(){
        setTitle("Saving Account");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.mainContent = new JPanel();
        setContentPane(mainContent);
        mainContent.setPreferredSize(new Dimension(900, 700));
        mainContent.setLayout(new BorderLayout(20, 20)); // Added horizontal and vertical gaps
        mainContent.setBorder(new EmptyBorder(20, 40, 20, 40));
        mainContent.setBackground(mainBgColor);
        mainContent.add(createHeaderPanel(), BorderLayout.NORTH);

    }
    private void createFinishPanelList(List<SavingAccount> accountList){
        finishPanelList = new ArrayList<>();
        finishList = new FinishList(accountList);
        int i;
        for(i=0;i<finishList.getButtonlist().size();i++){
            JPanel finish = new JPanel();
            // 创建标签并设置格式
            finish.setLayout(new BoxLayout(finish, BoxLayout.Y_AXIS));
            finish.setBorder(new CompoundBorder(new LineBorder(Color.GRAY, 1), new EmptyBorder(5, 10, 5, 10)));
            finish.setBackground(Color.white);
            finish.setPreferredSize(new Dimension(200, 150));
            JLabel nameLabel = new JLabel(accountList.get(i).getName(), SwingConstants.CENTER);
            nameLabel.setFont(new Font("Arial", Font.BOLD, 12));
            nameLabel.setAlignmentX(finish.CENTER_ALIGNMENT);

            JButton finishButton = finishList.getButtonlist().get(i);
            finishButton.setFont(new Font("Arial", Font.PLAIN, 12));
            finishButton.setAlignmentX(finish.CENTER_ALIGNMENT);

            JLabel totalIncome = new JLabel("Total income: something", SwingConstants.CENTER);
            totalIncome.setFont(new Font("Arial", Font.PLAIN, 12));
            totalIncome.setAlignmentX(finish.CENTER_ALIGNMENT);


            // 添加组件到面板
            finish.add(Box.createVerticalStrut(15));
            finish.add(nameLabel);
            finish.add(Box.createVerticalStrut(5));
            finish.add(totalIncome);
            finish.add(Box.createVerticalStrut(5));
            finish.add(finishButton);
            finish.add(Box.createVerticalStrut(0));
            if (accountList.get(i).getBalance()==0){
                totalIncome.setText("this account is empty");
                finishButton.setText("Deposit");
            }
            finishPanelList.add(finish);
        }

    }

    private JPanel createComponents(int i, ComponentList componentList) {
        // 创建组件的面板
        JPanel component = new JPanel();

        component.setLayout(new BoxLayout(component, BoxLayout.Y_AXIS));
        component.setBorder(new CompoundBorder(new LineBorder(Color.GRAY, 1), new EmptyBorder(5, 10, 5, 10)));
        component.setBackground(Color.white);
        component.setPreferredSize(new Dimension(200, 150));

        // 创建标签并设置格式
        SavingAccount account = componentList.getSavingAccountList().get(i);
        JProgressBar progressBar = componentList.getBarlist().get(i);
        JButton cancel = componentList.getCancelButton().get(i);

        JLabel nameLabel = new JLabel(componentList.getSavingAccountList().get(i).getName(), SwingConstants.CENTER);


        JLabel remainLabel = componentList.getRemainingList().get(i);

        JLabel startLabel = new JLabel("Start time: "+account.getStartTime(), SwingConstants.CENTER);
        startLabel.setFont(new Font("Arial", Font.PLAIN, 10));
        startLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel endLabel = new JLabel(" End time: "+ account.getEndTime(), SwingConstants.CENTER);
        endLabel.setFont(new Font("Arial", Font.PLAIN, 10));
        endLabel.setAlignmentX(Component.CENTER_ALIGNMENT);


        cancel.setFont(new Font("Arial", Font.PLAIN, 10));
        cancel.setAlignmentX(Component.CENTER_ALIGNMENT);



        // 添加组件到面板
        component.add(nameLabel);
        component.add(Box.createVerticalStrut(0));
        component.add(progressBar);
        component.add(Box.createVerticalStrut(0));
        component.add(remainLabel);
        component.add(Box.createVerticalStrut(0));
        component.add(startLabel);
        component.add(Box.createVerticalStrut(0));
        component.add(endLabel);
        component.add(Box.createVerticalStrut(0));
        component.add(cancel);

        return component;
    }

    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(mainBgColor);
        Border headerBorder = BorderFactory.createMatteBorder(0, 0, 3, 0, borderColor); // Added bottom border
        headerPanel.setBorder(headerBorder);

        JLabel titleLabel = new JLabel("Saving Account");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 40));
        titleLabel.setForeground(fontColor);

        headerPanel.add(titleLabel);
        return headerPanel;
    }

    private JPanel createTotalInfoPanel(){
        JPanel accountInfoPanel = new JPanel();
        accountInfoPanel.setLayout(new GridLayout(4, 1));  // 使用网格布局
        accountInfoPanel.setBackground(panelBgColor);
        accountInfoPanel.setBorder(new LineBorder(borderColor, 1)); // 添加边框

        // 总收入
        JLabel inputLabel = new JLabel("Total Input Money:     $" + "something to be added");
        inputLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        inputLabel.setForeground(fontColor);

        JLabel incomeLabel = new JLabel("Total Expenses:  $" +  "something to be added");
        incomeLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        incomeLabel.setForeground(fontColor);

        accountInfoPanel.add(new JLabel("Saving ACCOUNT"));
        accountInfoPanel.add(inputLabel);
        accountInfoPanel.add(incomeLabel);

        return accountInfoPanel;

    }

    private JPanel createAccountGrid(List<SavingAccount> accountList) {
        accountGrid = new JPanel();
        accountGrid.setLayout(new GridLayout(4, 3, 10, 5)); // 增加了行列间的间隔
        accountGrid.setBackground(Color.LIGHT_GRAY);
        accountGrid.setBorder(new LineBorder(Color.BLACK, 2)); // 添加边框
        componentList = new ComponentList(accountList);

        for (int i = 0; i < accountList.size(); i++) {
            if(accountList.get(i).getEndTime().isAfter(LocalDateTime.now())&&accountList.get(i).getBalance()>0){
                accountGrid.add(createComponents(i,componentList));
            }
            else{accountGrid.add(finishPanelList.get(i));
            }

        }
        addButton = createAddComponents();
        accountGrid.add(addButton);
        for (int i = 0; i < 11-accountList.size(); i++) {
            accountGrid.add(new JPanel());
        }

        return accountGrid;

    }
    private JPanel createAddComponents(){
        JPanel addPanel = new JPanel();
        addPanel.setBorder(new CompoundBorder(new LineBorder(Color.GRAY, 1), new EmptyBorder(5, 10, 5, 10)));
        addPanel.setBackground(Color.white);
        addPanel.setPreferredSize(new Dimension(200, 120));

        JLabel addLabel = new JLabel("+", SwingConstants.CENTER);
        addLabel.setFont(new Font("Arial", Font.BOLD, 50));
        addLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        addPanel.add(addLabel);
        return addPanel;
    }
    public JPanel getAddButton(){
        return addButton;
    }

    public ComponentList getComponentList() {
        return componentList;
    }

    public FinishList getFinishList() {
        return finishList;
    }

    public static void main(String[] args) {
        // 创建 JFrame 并显示
        JFrame frame = new ShowSavingAccount();
    }
}




