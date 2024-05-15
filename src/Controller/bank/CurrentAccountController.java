package Controller.bank;

import Entity.Kids;
import GUI.bank_page.ShowCurrentAccount;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CurrentAccountController {
    private Kids kid;
    private ShowCurrentAccount GUI;

    CurrentAccountController(Kids kid,ShowCurrentAccount GUI){
        this.kid = kid;
        this.GUI = GUI;
        GUI.initData(kid.getAccountManager().getCurrentAccounts());
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
                    JFrame frame = new JFrame("Select Current Account");
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
                                refresh();
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
                    frame.pack();
                    frame.setLocationRelativeTo(null); // 居中显示
                    frame.setVisible(true);
                }
            });
        }

    }
    public void refresh(){
        GUI.refresh(kid.getAccountManager().getCurrentAccounts());
        addListener(GUI);

    }
    public ShowCurrentAccount getGUI(){
        return GUI;
    }
    public Kids getKid() {
        return kid;
    }
}