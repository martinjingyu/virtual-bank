package Controller.bank;

import Entity.AccountManager;
import Entity.Kids;
import GUI.bank_page.ShowCurrentAccount;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class CurrentAccountController {
    private Kids kid;
    private ShowCurrentAccount GUI;


    CurrentAccountController(Kids kid,ShowCurrentAccount GUI,Boolean whetherParent){
        this.kid = kid;
        this.GUI = GUI;
        GUI.initData(kid.getAccountManager().getCurrentAccounts(),whetherParent,kid.getAccountManager());
        addListener(GUI);
    }

    private void addListener(ShowCurrentAccount GUI){
        List<JButton> cancelButtons = GUI.getComponentList().getTransferButton();
        int i;
        for(i =0;i<cancelButtons.size();i++){
            JButton button = cancelButtons.get(i);
            int finalI = i;
            button.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    JDialog frame = new JDialog();
                    frame.setResizable(false);
                    frame.setTitle("Select Current Account");
                    frame.setModal(true);
                    frame.setSize(300,120);
                    JPanel panel = new JPanel(new BorderLayout());

                    // 添加组件到对话框中
                    List<String> accountNames= kid.getAccountManager().getSavingAccountNames();
                    String[] namesArray = accountNames.toArray(new String[0]);
                    JComboBox<String> comboBox = new JComboBox<>(namesArray);
                    JTextField textField = new JTextField();
                    panel.add(comboBox, BorderLayout.CENTER);
                    panel.add(textField,BorderLayout.NORTH);
                    JButton confirmButton = new JButton("Confirm");
                    confirmButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            try{
                                int selectedIndex = comboBox.getSelectedIndex();
                                double value = Double.parseDouble(textField.getText());

                                // 执行 earlyWithdrew 方法
                                kid.getAccountManager().transfer(selectedIndex, finalI,value);
                                refresh(kid.getAccountManager());
                                // 关闭对话框
                                frame.dispose();
                            }
                            catch (Exception e1){
                                System.out.println("wrong input");
                                frame.dispose();
                            }
                            // 获取用户选择的 current account

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
    public void refresh(AccountManager accountManager){
        GUI.refresh(kid.getAccountManager().getCurrentAccounts(),accountManager);
        addListener(GUI);
    }
    public ShowCurrentAccount getGUI(){
        return GUI;
    }
    public Kids getKid() {
        return kid;
    }
}