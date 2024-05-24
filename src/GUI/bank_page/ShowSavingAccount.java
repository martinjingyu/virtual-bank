package GUI.bank_page;

import Controller.bank.Bank_kid_control;
import Entity.AccountManager;
import Entity.Kids;
import Entity.Message;
import Entity.SavingAccount;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.swing.border.*;

public class ShowSavingAccount extends JFrame {
    private JButton actionButton;
    private JPanel mainContent;
    private SavingComponentList savingComponentList;
    private JPanel accountGrid;
    private List<JPanel> accountPanelList;
    private JPanel infoPanel;
    private List<JPanel> finishPanelList;
    private FinishList finishList;
    private List<Duration> remainingTime;
    private JPanel addButton;
    private Timer timer;
    private ActionListener actionListener;
    private final Color mainBgColor = new Color(191, 221, 239); // #bfddef
    private final Color panelBgColor = new Color(239, 239, 239); // #EFEFEF
    private final Color fontColor = new Color(49, 122, 232); // #317AE8
    private final Color submitButtonColor = new Color(103, 201, 86); // #67C956
    private final Color borderColor = new Color(105, 105, 105); // #696969

    public ShowSavingAccount() {

        initUI();

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
            if(progress<100){
                Component[] list = accountGrid.getComponents();
                list[i] = accountPanelList.get(i);
                accountGrid.removeAll();
                for(int j=0; j< list.length;j++){
                    accountGrid.add(list[j]);
                }
                accountGrid.revalidate(); // 用于重新布局组件
                accountGrid.repaint();    // 用于请求重绘组件
            }
            if (progress >= 100) {
                Component[] list = accountGrid.getComponents();
                list[i] = finishPanelList.get(i);

                accountGrid.removeAll();
                for(int j=0; j< list.length;j++){
                    accountGrid.add(list[j]);

                }
                accountGrid.revalidate(); // 用于重新布局组件
                accountGrid.repaint();    // 用于请求重绘组件
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
            savingComponentList.getRemainingList().get(i).setText("Remaining time: " + remainingTime);
        }
    }

    public void initData(List<SavingAccount> accountList,Boolean whetherParent, AccountManager accountManager){
        createFinishPanelList(accountList,whetherParent);
        accountGrid = createAccountGrid(accountList,whetherParent);
        mainContent.add(accountGrid,BorderLayout.CENTER);
        infoPanel = createTotalInfoPanel(accountManager);
        mainContent.add(infoPanel,BorderLayout.SOUTH);
        timer = new Timer(1000, e -> {updateProgressBar(accountList, savingComponentList.getBarlist());updateRemaining(accountList);});
        timer.start();
        pack();
        setVisible(true);
    }
    public void refresh(List<SavingAccount> accountList,Boolean whetherParent){
        refreshFinishPanelList(accountList);

    }
    private void initUI(){
        setTitle("Saving Account");
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
    private void createFinishPanelList(List<SavingAccount> accountList,Boolean whetherParent){
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

            JLabel totalIncome = new JLabel("Total income: "+accountList.get(i).getIncome(), SwingConstants.CENTER);
            totalIncome.setFont(new Font("Arial", Font.PLAIN, 12));
            totalIncome.setAlignmentX(finish.CENTER_ALIGNMENT);


            // 添加组件到面板
            finish.add(Box.createVerticalStrut(15));
            finish.add(nameLabel);
            finish.add(Box.createVerticalStrut(5));
            finish.add(totalIncome);
            finish.add(Box.createVerticalStrut(5));
            if(whetherParent==false){
                finish.add(finishButton);
            }

            finish.add(Box.createVerticalStrut(0));
            if (accountList.get(i).getBalance()==0){
                totalIncome.setText("this account is empty");
                finishButton.setText("Deposit");
            }
            finishPanelList.add(finish);
        }

    }

    private void refreshFinishPanelList(List<SavingAccount> accountList){
        for(int i=0; i<accountList.size();i++){
            if (accountList.get(i).getBalance()==0){
                int labelCount =0;
                Component[] list= finishPanelList.get(i).getComponents();
                for(Component component: list){
                    if(component instanceof JButton){
                        ((JButton) component).setText("Deposit");
                    }
                    if(component instanceof JLabel){
                        labelCount++;
                        if(labelCount==2){
                            ((JLabel) component).setText("this account is empty");
                        }
                    }
                }
            }
        }
    }

    private JPanel createComponents(int i, SavingComponentList savingComponentList,Boolean whetherParent) {
        // 创建组件的面板
        JPanel component = new JPanel();

        component.setLayout(new BoxLayout(component, BoxLayout.Y_AXIS));
        component.setBorder(new CompoundBorder(new LineBorder(Color.GRAY, 1), new EmptyBorder(5, 10, 5, 10)));
        component.setBackground(Color.white);
        component.setPreferredSize(new Dimension(200, 150));

        // 创建标签并设置格式
        SavingAccount account = savingComponentList.getSavingAccountList().get(i);
        JProgressBar progressBar = savingComponentList.getBarlist().get(i);
        JButton cancel = savingComponentList.getCancelButton().get(i);

        JLabel nameLabel = new JLabel(savingComponentList.getSavingAccountList().get(i).getName(), SwingConstants.CENTER);


        JLabel remainLabel = savingComponentList.getRemainingList().get(i);

        LocalDateTime time = account.getStartTime();


        JLabel startLabel = new JLabel("Start time: "+account.getFormattedStartTime(), SwingConstants.CENTER);
        startLabel.setFont(new Font("Arial", Font.PLAIN, 10));
        startLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel endLabel = new JLabel(" End time: "+ account.getFormattedEndTime(), SwingConstants.CENTER);
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
        if(whetherParent==false){
            component.add(Box.createVerticalStrut(0));
            component.add(cancel);
        }


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

    private JPanel createTotalInfoPanel(AccountManager accountManager){
        JPanel accountInfoPanel = new JPanel();
        accountInfoPanel.setLayout(new GridLayout(4, 1));  // 使用网格布局
        accountInfoPanel.setBackground(panelBgColor);
        accountInfoPanel.setBorder(new LineBorder(borderColor, 1)); // 添加边框

        // 总收入
        JLabel inputLabel = new JLabel("Total Input Money:     $" + accountManager.getTotalSavingBalance());
        inputLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        inputLabel.setForeground(fontColor);

        JLabel incomeLabel = new JLabel("Total Income:  $" +  accountManager.getTotalSavingIncome());
        incomeLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        incomeLabel.setForeground(fontColor);

        JLabel interest = new JLabel("Current Interest rate :   " +"15 days: "+accountManager.getAllInterst(0)+
                "%            1 month: "+accountManager.getAllInterst(1)+"%                3 month: "+ accountManager.getAllInterst(2)+"%");
        interest.setFont(new Font("Arial", Font.PLAIN, 16));
        interest.setForeground(fontColor);

        accountInfoPanel.add(new JLabel("Saving ACCOUNT"));
        accountInfoPanel.add(inputLabel);
        accountInfoPanel.add(incomeLabel);
        accountInfoPanel.add(interest);

        return accountInfoPanel;

    }

    private JPanel createAccountGrid(List<SavingAccount> accountList,Boolean whetherParent) {
        accountGrid = new JPanel();
        accountPanelList = new ArrayList<>();
        accountGrid.setLayout(new GridLayout(4, 3, 10, 5)); // 增加了行列间的间隔
        accountGrid.setBackground(Color.LIGHT_GRAY);
        accountGrid.setBorder(new LineBorder(Color.BLACK, 2)); // 添加边框
        savingComponentList = new SavingComponentList(accountList);

        for (int i = 0; i < accountList.size(); i++) {
            if(accountList.get(i).getEndTime().isAfter(LocalDateTime.now())&&accountList.get(i).getBalance()>0){
                JPanel component = createComponents(i, savingComponentList,whetherParent);
                accountGrid.add(component);
                accountPanelList.add(component);
            }
            else{
                JPanel component = createComponents(i, savingComponentList,whetherParent);
                accountPanelList.add(component);
                accountGrid.add(finishPanelList.get(i));
            }

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
        addLabel.setAlignmentX(addPanel.CENTER_ALIGNMENT);
        addLabel.setAlignmentY(addPanel.CENTER_ALIGNMENT);
        addPanel.add(Box.createVerticalGlue());
        addPanel.add(addLabel);
        addPanel.add(Box.createVerticalGlue());
        return addPanel;
    }
    public void afterAddAccount(AccountManager accountManager, String name, Kids kid){
        timer.stop();
        accountManager.createNewSavingAccount(name);
        kid.getMessagelist().addMessage(new Message("system_kid","You have created a new saving account "+"\""+name+"\""));
        mainContent.removeAll();
        mainContent.add(createHeaderPanel(), BorderLayout.NORTH);
        initData(accountManager.getSavingAccounts(),false,accountManager);
        pack();
        setVisible(true);
    }
    public JPanel getAddButton(){
        return addButton;
    }

    public SavingComponentList getComponentList() {
        return savingComponentList;
    }

    public FinishList getFinishList() {
        return finishList;
    }

    public static void main(String[] args) {
        // 创建 JFrame 并显示
    }
}




