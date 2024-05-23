package Controller.bank;

import Entity.Kids;
import Exceptions.InsufficientFundsException;
import GUI.bank_page.Bank_kid;
import GUI.bank_page.ShowSavingAccount;
import utill.validate.Validate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;


public class SavingAccountController {
    private Kids kid;
    private ShowSavingAccount GUI;
    private Bank_kid bank_kid;

    SavingAccountController(Kids kid,ShowSavingAccount GUI,Boolean whetherParent, Bank_kid bank_kid){
        this.kid = kid;
        this.GUI = GUI;
        this.bank_kid = bank_kid;

        GUI.initData(kid.getAccountManager().getSavingAccounts(),whetherParent,kid.getAccountManager());
        addListener(GUI);
    }
    SavingAccountController(Kids kid,ShowSavingAccount GUI,Boolean whetherParent){
        this.kid = kid;
        this.GUI = GUI;
        GUI.initData(kid.getAccountManager().getSavingAccounts(),whetherParent,kid.getAccountManager());
        addListener(GUI);
    }

    private void addListener(ShowSavingAccount GUI){
        List<JButton> cancelButtons = GUI.getComponentList().getCancelButton();
        int i;
        for(i =0;i<cancelButtons.size();i++){
            JButton button = cancelButtons.get(i);
            int finalI = i;
            button.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    JDialog frame = new JDialog();
                    frame.setResizable(false);
                    frame.setModal(true);
                    frame.setTitle("Select Current Account");

                    JPanel panel = new JPanel(new BorderLayout());
                    frame.setSize(300,100);

                    // 添加组件到对话框中
                    List<String> accountNames= kid.getAccountManager().getCurrentAccountNames();
                    String[] namesArray = accountNames.toArray(new String[0]);
                    JComboBox<String> comboBox = new JComboBox<>(namesArray);
                    panel.add(comboBox, BorderLayout.CENTER);

                    JButton confirmButton = new JButton("Confirm");
                    confirmButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            // 获取用户选择的 current account
                            int selectedIndex = comboBox.getSelectedIndex();

                            // 执行 earlyWithdrew 方法
                            kid.getAccountManager().earlyWithdrew(selectedIndex, finalI);
                            bank_kid.updateAccounts();
                            refresh(true);
                            // 关闭对话框
                            frame.dispose();
                        }
                    });
                    panel.add(confirmButton, BorderLayout.SOUTH);

                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    frame.getContentPane().add(panel);
                    frame.setLocationRelativeTo(null); // 居中显示
                    frame.setVisible(true);
                }
            });
        }

        List<JButton> finishButtons = GUI.getFinishList().getButtonlist();
        for(i = 0;i<finishButtons.size();i++){

            JButton button = finishButtons.get(i);
            int finalI = i;
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(button.getText().equals("Deposit")){
                        JDialog frame = new JDialog();
                        frame.setTitle("Select Current Account");
                        frame.setModal(true);
                        frame.setResizable(false);
                        JPanel panel = new JPanel(new BorderLayout());
                        frame.setSize(300,150);

                        // 添加组件到对话框中
                        List<String> accountNames= kid.getAccountManager().getCurrentAccountNames();
                        String[] namesArray = accountNames.toArray(new String[0]);
                        JPanel middle = new JPanel(new BorderLayout());
                        JComboBox<String> comboBox = new JComboBox<>(namesArray);
                        JComboBox<String> time = new JComboBox<>(new String[]{"15 days","1 month","3 months"});

                        middle.add(comboBox, BorderLayout.CENTER);
                        middle.add(time,BorderLayout.SOUTH);

                        panel.add(middle,BorderLayout.CENTER);

                        JTextField textField = new JTextField();

                        JButton confirmButton = new JButton("Confirm");
                        confirmButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                // 获取用户选择的 current account
                                int selectedIndex = comboBox.getSelectedIndex();
                                String selectedTime = (String) time.getSelectedItem();
                                try{
                                    double value = Validate.validateNumber(textField.getText());
                                    kid.getAccountManager().depositCurrentToSaving(selectedIndex, finalI,value,selectedTime);
                                    refresh(true);
                                    bank_kid.updateAccounts();
                                    // 关闭对话框
                                    frame.dispose();
                                }
                                catch (InsufficientFundsException e2){
                                    JOptionPane.showMessageDialog(null, "Insufficient Funds", "Error", JOptionPane.ERROR_MESSAGE);
                                    textField.setText("");
                                }
                                catch (Exception e1){
                                    textField.setText("");
                                }


                                // 执行 earlyWithdrew 方法

                            }
                        });
                        panel.add(confirmButton, BorderLayout.SOUTH);
                        panel.add(textField,BorderLayout.NORTH);

                        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        frame.setContentPane(panel);
                        frame.setLocationRelativeTo(null); // 居中显示
                        frame.setVisible(true);

                    }
                    else if (button.getText().equals("Take my Money!")) {
                        JDialog frame = new JDialog();
                        frame.setModal(true);
                        frame.setResizable(false);
                        frame.setTitle("Select Current Account");

                        frame.setResizable(false);
                        JPanel panel = new JPanel(new BorderLayout());
                        frame.setSize(300,100);


                        // 添加组件到对话框中
                        List<String> accountNames= kid.getAccountManager().getCurrentAccountNames();
                        String[] namesArray = accountNames.toArray(new String[0]);
                        JComboBox<String> comboBox = new JComboBox<>(namesArray);

                        panel.add(comboBox, BorderLayout.CENTER);


                        JButton confirmButton = new JButton("Confirm");
                        confirmButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                // 获取用户选择的 current account
                                int selectedIndex = comboBox.getSelectedIndex();

                                // 执行 earlyWithdrew 方法
                                kid.getAccountManager().savingWithdrewToCurrent(selectedIndex, finalI);
                                refresh(true);
                                bank_kid.updateAccounts();
                                // 关闭对话框
                                frame.dispose();
                            }
                        });
                        panel.add(confirmButton, BorderLayout.SOUTH);

                        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        frame.getContentPane().add(panel);
                        frame.setLocationRelativeTo(null); // 居中显示
                        frame.setVisible(true);
                    }
                }
            });

        }
        if(GUI.getAddButton() != null)
        {
            GUI.getAddButton().addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    createNewAccount();
                }
            });
        }

    }
    public void createNewAccount(){
        JDialog dialog = new JDialog(GUI, "Create New Account", true);
        dialog.setResizable(false);
        dialog.setLayout(new BorderLayout());
        dialog.setSize(300, 200);
        dialog.setLocationRelativeTo(GUI);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(2, 1));
        JLabel nameLabel = new JLabel("Enter Account Name:");
        JTextField nameField = new JTextField();
        inputPanel.add(nameLabel);
        inputPanel.add(nameField);

        JButton confirmButton = new JButton("Confirm");
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String accountName = nameField.getText().trim();
                try{
                    accountName = Validate.validateName(accountName);
                    Boolean whether_Repeat = Validate.validateRepeat(accountName,kid.getAccountManager().getSavingAccountNames());
                    GUI.afterAddAccount(kid.getAccountManager(),accountName);
                    dialog.dispose();
                    addListener(GUI);
                }
                catch (Exception e1){
                    nameField.setText("");
                }
            }
        });
        inputPanel.add(nameLabel);
        inputPanel.add(Box.createVerticalStrut(10));
        inputPanel.add(nameField);
        inputPanel.add(Box.createVerticalStrut(10));
        inputPanel.add(confirmButton);

        dialog.getContentPane().add(inputPanel);
        dialog.pack();
        dialog.setVisible(true);

    }
    public void refresh(Boolean whetherParent){
        GUI.refresh(kid.getAccountManager().getSavingAccounts(),whetherParent);
    }
    public ShowSavingAccount getGUI(){
        return GUI;
    }
    public Kids getKid() {
        return kid;
    }
}
