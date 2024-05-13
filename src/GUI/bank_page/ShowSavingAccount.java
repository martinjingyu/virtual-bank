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
import java.util.List;
import javax.swing.border.*;

public class ShowSavingAccount extends JFrame {
    private JButton actionButton;
    private JPanel mainContent;
    private final Color mainBgColor = new Color(191, 221, 239); // #bfddef
    private final Color panelBgColor = new Color(239, 239, 239); // #EFEFEF
    private final Color fontColor = new Color(49, 122, 232); // #317AE8
    private final Color submitButtonColor = new Color(103, 201, 86); // #67C956
    private final Color borderColor = new Color(105, 105, 105); // #696969

    public ShowSavingAccount() {

        initUI();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    public void initData(List<SavingAccount> accountList){
        mainContent.add(createAccountGrid(accountList),BorderLayout.CENTER);
        mainContent.add(createTotalInfoPanel(),BorderLayout.SOUTH);

    }
    private void initUI(){
        setTitle("Saving Account");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.mainContent = new JPanel();
        setContentPane(mainContent);

        mainContent.setPreferredSize(new Dimension(900, 540));
        mainContent.setLayout(new BorderLayout(20, 20)); // Added horizontal and vertical gaps
        mainContent.setBorder(new EmptyBorder(20, 40, 20, 40));
        mainContent.setBackground(mainBgColor);
        mainContent.add(createHeaderPanel(), BorderLayout.NORTH);

    }



    private JPanel createComponents(int i, ComponentList componentList) {
        // 创建组件的面板
        JPanel component = new JPanel();

        component.setLayout(new BoxLayout(component, BoxLayout.Y_AXIS));
        component.setBorder(new CompoundBorder(new LineBorder(Color.GRAY, 1), new EmptyBorder(5, 10, 5, 10)));
        component.setBackground(Color.white);
        component.setPreferredSize(new Dimension(200, 120));

        // 创建标签并设置格式
        SavingAccount account = componentList.getSavingAccountList().get(i);
        JProgressBar progressBar = componentList.getBarlist().get(i);

        JLabel nameLabel = new JLabel("name", SwingConstants.CENTER);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 12));
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel remainLabel = new JLabel("Remain: xxxx:xx:xx", SwingConstants.CENTER);
        remainLabel.setFont(new Font("Arial", Font.BOLD, 12));
        remainLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel startEndLabel = new JLabel("Start time: "+account.getStartTime()+" End time: "+ account.getEndTime(), SwingConstants.CENTER);
        startEndLabel.setFont(new Font("Arial", Font.PLAIN, 10));
        startEndLabel.setAlignmentX(Component.CENTER_ALIGNMENT);



        // 添加组件到面板
        component.add(nameLabel);
        component.add(Box.createVerticalStrut(0));
        component.add(progressBar);
        component.add(Box.createVerticalStrut(0));
        component.add(remainLabel);
        component.add(Box.createVerticalStrut(0));
        component.add(startEndLabel);

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
        JPanel accountGrid = new JPanel();
        accountGrid.setLayout(new GridLayout(4, 3, 10, 5)); // 增加了行列间的间隔
        accountGrid.setBackground(Color.LIGHT_GRAY);
        accountGrid.setBorder(new LineBorder(Color.BLACK, 2)); // 添加边框
        ComponentList componentList = new ComponentList(accountList);

        for (int i = 0; i < accountList.size(); i++) {
            accountGrid.add(createComponents(i,componentList));
        }
        accountGrid.add(createAddComponents());
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

    public static void main(String[] args) {
        // 创建 JFrame 并显示
        JFrame frame = new ShowSavingAccount();
    }
}




