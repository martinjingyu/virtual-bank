package Controller.bank;

import Entity.Kids;
import GUI.MainFrame_parent;
import GUI.bank_page.ShowCurrentAccount;
import GUI.bank_page.ShowSavingAccount;
import GUI.bank_page.bank_parents;
import GUI.bank_page.history_page;
import utill.validate.Validate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Bank_parent_controller {
    //    private Parent parent;
    private Kids kid;
    private bank_parents GUI;
    private HistoryController historyController;
    private JFrame currentFrame; // 用于存储当前打开的 JFrame 引用

    public Bank_parent_controller(Kids kid) {
        this.kid = kid;
        this.historyController = new HistoryController(kid);
    }

    public void setGUI(bank_parents GUI) {
        this.GUI = GUI;
    }
    public bank_parents getGUI(bank_parents GUI) { return GUI; }
    public Kids getKid() {
        return kid;
    }
    //    public void addListener(bank_parents GUI){
//        addHistory(GUI.getMainFrame().getButton(3));
//    }
    public void  addHistory(JButton button){
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                HistoryController historyController1 = new HistoryController(kid);
                history_page review = new history_page(historyController);
                JFrame Review_win = new JFrame();
                openNewFrame(Review_win);
                Review_win.setTitle("History");
                Review_win.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                Review_win.setLocationRelativeTo(null);
                Review_win.add(review);
                Review_win.setSize(800, 400);
                Review_win.setVisible(true);
            }
        });
    }
    public void addCurrentDetails(JButton button){
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ShowCurrentAccount showCurrentAccount = new ShowCurrentAccount();
                CurrentAccountController currentAccountController = new CurrentAccountController(kid,showCurrentAccount,true);
                openNewFrame(showCurrentAccount);
            }
        });

    }

    public void addChangeInterestRate(JButton button){
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dialog = new JDialog(currentFrame, "Change Interest Rate", true);
                dialog.setBounds(
                        new Rectangle(
                                400,
                                300,
                                500, 500
                        )
                );
                dialog.setLayout(new GridLayout(4, 1));

                // 第一行：文字
                JLabel label = new JLabel("Change interest rate", SwingConstants.CENTER);
                label.setFont(new Font(label.getFont().getName(), Font.BOLD, 18)); // 设置加粗和大小
                dialog.add(label);

                // 第二行：下拉表单
                String[] options = {"15 days", "1 month", "3 months"};
                JComboBox<String> comboBox = new JComboBox<>(options);
                dialog.add(comboBox);

                // 第三行：文本输入框
                JTextField textField = new JTextField();
                dialog.add(textField);

                // 第四行：按钮
                JButton confirmButton = new JButton("Confirm");
                confirmButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // 在这里可以处理确认按钮的点击事件
                        String selectedOption = (String) comboBox.getSelectedItem();
                        String userInput = textField.getText();
                        System.out.println("Selected option: " + selectedOption);
                        System.out.println("User input: " + userInput);
                        if (selectedOption.equals("15 days")){
                            try {
                                double newInterestRate = Validate.validateNumber(userInput);
                            } catch (Exception ex) {
                                throw new RuntimeException(ex);
                            }
                        } else if (selectedOption.equals("1 month")) {
                            try {
                                double newInterestRate = Validate.validateNumber(userInput);
                            } catch (Exception ex) {
                                throw new RuntimeException(ex);
                            }
                        }else {
                            try {
                                double newInterestRate = Validate.validateNumber(userInput);
                            } catch (Exception ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                        dialog.dispose(); // 关闭对话框
                    }
                });
                dialog.add(confirmButton);

                // 设置对话框大小并显示
                dialog.pack();
                dialog.setVisible(true);

            }
        });
    }
    public void addSavingAccountListener(JButton button){
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ShowSavingAccount showSavingAccount = new ShowSavingAccount();
                SavingAccountController savingAccountController = new SavingAccountController(kid, showSavingAccount, true);
                openNewFrame(showSavingAccount);
            }
        });
    }
    private void openNewFrame(JFrame newFrame) {
        // 关闭当前打开的 JFrame
        if (currentFrame != null) {
            currentFrame.dispose();
        }

        // 更新 currentFrame 并显示新的 JFrame
        currentFrame = newFrame;
        currentFrame.setLocationRelativeTo(null);
        currentFrame.setVisible(true);
    }
}

