package Controller.bank;

import Entity.Kids;
import GUI.bank_page.ShowSavingAccount;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class SavingAccountController {
    private Kids kid;
    private ShowSavingAccount GUI;

    SavingAccountController(Kids kid,ShowSavingAccount GUI){
        this.kid = kid;
        this.GUI = GUI;
        GUI.initData(kid.getAccountManager().getSavingAccounts());
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
                            GUI.refresh(kid.getAccountManager().getSavingAccounts());
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
                                GUI.refresh(kid.getAccountManager().getSavingAccounts());
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
                    else if (button.getText().equals("Take my Money!")) {


                    }
                }
            });

        }


    }


    public Kids getKid() {
        return kid;
    }
}
