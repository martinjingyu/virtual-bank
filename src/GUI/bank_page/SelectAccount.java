package GUI.bank_page;

import Controller.bank.SavingAccountController;
import Entity.AccountManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class SelectAccount {
    private JFrame frame;
    private JPanel mainPanel;
    private JPanel header;
    private JComboBox<String> comboBox;
    private AccountManager accountManager;
    private JTextField textField;
    private JButton button;
    private int selectedAccount;


    public SelectAccount(SavingAccountController controller,int selectedAccount,String title,String accountType, AccountManager accountManager,Boolean whether_text){
        this.accountManager = accountManager;
        this.selectedAccount = selectedAccount;
        setFrame();
        setMainPanel();
        setTitle(title);
        setComboBox(accountType,accountManager);
        setTextField(whether_text);
    }
    private void setTitle(String title){
        header = new JPanel(new BorderLayout());
        JLabel label = new JLabel(title);
        header.add(label);
    }
    private void setFrame(){
        frame = new JFrame();
        frame.setTitle("Select Current Account");
    }
    private void setMainPanel(){
        mainPanel = new JPanel(new BorderLayout());
    }
    private void setComboBox(String accountType,AccountManager accountManager){

        String[] namesArray = new String[0];
        if(accountType.equals("current")){
            List<String> accountNames = accountManager.getCurrentAccountNames();
            namesArray = accountNames.toArray(new String[0]);
        }
        else if (accountType.equals("saving")) {
            List<String> accountNames = accountManager.getSavingAccountNames();
            namesArray = accountNames.toArray(new String[0]);
        }
        comboBox = new JComboBox<>(namesArray);
    }
    private void setTextField(Boolean whetherText){
        if(whetherText == Boolean.TRUE){
            textField = new JTextField();
        }
    }

    private void setButton(Boolean whetherText,String accountType, String action){
        button = new JButton("Confirm");

        double value;
        if(whetherText==true){
            value = Double.parseDouble(textField.getText());
        }
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = comboBox.getSelectedIndex();

                // 执行 earlyWithdrew 方法
                performedConfirm(action);

                // 关闭对话框
                frame.dispose();

            }
        });
    }
    private void performedConfirm(String action){
        if(action.equals("earlyWithdrew")){
            int selectedIndex = comboBox.getSelectedIndex();

            // 执行 earlyWithdrew 方法
            accountManager.earlyWithdrew(selectedIndex, selectedIndex);
            //controller.refresh();
            // 关闭对话框
            frame.dispose();
        }
        else if (action.equals("Deposit")) {
            int selectIndex = comboBox.getSelectedIndex();

            // 执行 earlyWithdrew 方法
            double value = Double.parseDouble(textField.getText());

            accountManager.deposit(selectIndex,this.selectedAccount,value);
            controller.refresh();
            // 关闭对话框
            frame.dispose();
        }
        else if (action.equals("TakeMoney")) {
            int selectedIndex = comboBox.getSelectedIndex();

            // 执行 earlyWithdrew 方法
            accountManager.withdrewToCurrent( selectedIndex,this.selectedAccount);
            controller.refresh();
            // 关闭对话框
            frame.dispose();
        }
    }





}
