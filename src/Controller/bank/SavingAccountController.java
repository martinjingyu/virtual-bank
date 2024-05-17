package Controller.bank;

import Entity.Kids;
import GUI.bank_page.ShowSavingAccount;

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

    SavingAccountController(Kids kid,ShowSavingAccount GUI,Boolean whetherParent){
        this.kid = kid;
        this.GUI = GUI;

        GUI.initData(kid.getAccountManager().getSavingAccounts(),whetherParent);
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
                    JFrame frame = new JFrame("Select Current Account");
                    JPanel panel = new JPanel(new BorderLayout());

                    // 添加组件到对话框中
                    List<String> accountNames= kid.getAccountManager().getSavingAccountNames();
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
                            refresh(true);
                            // 关闭对话框
                            frame.dispose();
                        }
                    });
                    panel.add(confirmButton, BorderLayout.SOUTH);

                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    frame.getContentPane().add(panel);
                    frame.pack();
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
                        JFrame frame = new JFrame("Select Current Account");
                        JPanel panel = new JPanel(new BorderLayout());

                        // 添加组件到对话框中
                        List<String> accountNames= kid.getAccountManager().getSavingAccountNames();
                        String[] namesArray = accountNames.toArray(new String[0]);
                        JComboBox<String> comboBox = new JComboBox<>(namesArray);
                        JComboBox<String> time = new JComboBox<>(new String[]{"15 days","1 month","3 months"});

                        panel.add(comboBox, BorderLayout.CENTER);
                        panel.add(time,BorderLayout.CENTER);

                        JTextField textField = new JTextField();

                        JButton confirmButton = new JButton("Confirm");
                        confirmButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                // 获取用户选择的 current account
                                int selectedIndex = comboBox.getSelectedIndex();
                                String selectedTime = (String) time.getSelectedItem();
                                try{
                                    double value = Double.parseDouble(textField.getText());
                                    kid.getAccountManager().depositCurrentToSaving(selectedIndex, finalI,value,selectedTime);
                                    refresh(true);
                                    // 关闭对话框
                                    frame.dispose();
                                }
                                catch (Exception e1){
                                    System.out.println("wrong input");
                                    textField.setText("");
                                    frame.dispose();
                                }

                                // 执行 earlyWithdrew 方法

                            }
                        });
                        panel.add(confirmButton, BorderLayout.SOUTH);
                        panel.add(textField,BorderLayout.NORTH);

                        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        frame.getContentPane().add(panel);
                        frame.pack();
                        frame.setLocationRelativeTo(null); // 居中显示
                        frame.setVisible(true);

                    }
                    else if (button.getText().equals("Take my Money!")) {
                        System.out.println(kid.getAccountManager().getSavingAccounts().get(finalI).getEndTime());
                        JFrame frame = new JFrame("Select Current Account");
                        JPanel panel = new JPanel(new BorderLayout());

                        // 添加组件到对话框中
                        List<String> accountNames= kid.getAccountManager().getSavingAccountNames();
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
                                // 关闭对话框
                                frame.dispose();
                            }
                        });
                        panel.add(confirmButton, BorderLayout.SOUTH);

                        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        frame.getContentPane().add(panel);
                        frame.pack();
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
                if (!accountName.isEmpty()) {
                    GUI.afterAddAccount(kid.getAccountManager(),accountName);
                    dialog.dispose();
                    addListener(GUI);

                } else {
                    JOptionPane.showMessageDialog(dialog, "Account name cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
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
