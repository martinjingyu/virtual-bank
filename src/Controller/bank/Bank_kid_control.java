package Controller.bank;

import Entity.Kids;

import GUI.bank_page.ShowCurrentAccount;
import GUI.bank_page.ShowSavingAccount;
import GUI.bank_page.history_page;
import GUI.bank_page.Bank_kid;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Bank_kid_control{
    private static JTextField savingGoalTextField;
    private static int clickCount=0;
    private Kids kid;
    private Bank_kid GUI;
    private HistoryController historyController;
    private JFrame currentFrame; // 用于存储当前打开的 JFrame 引用
    public Bank_kid_control(Kids kid){
        this.kid = kid;
        this.historyController = new HistoryController(kid);

    }
    public void setGUI(Bank_kid GUI){
        this.GUI = GUI;
    }
    public Kids getKid(){
        return kid;
    }

    public void addEditSavingGoalButtonListener(JButton button){
        savingGoalTextField = new JTextField();
        savingGoalTextField.setBounds(630, 80, 75, 30);
        savingGoalTextField.setVisible(false);
        GUI.getMainFrame().add(savingGoalTextField);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clickCount++; // 每次点击增加点击次数
                // 根据点击次数的奇偶性设置文本框的可见性
                if (clickCount % 2 == 1) {
                    savingGoalTextField.setVisible(true);

                    GUI.getMainFrame().revalidate();
                    GUI.getMainFrame().repaint();
                } else {
                    savingGoalTextField.setVisible(false);
                }
            }
        });
    }

    public void addCurrentAccountListener(JButton button){
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ShowCurrentAccount showCurrentAccount = new ShowCurrentAccount();
                CurrentAccountController currentAccountController = new CurrentAccountController(kid,showCurrentAccount,false);
                openNewFrame(showCurrentAccount);
            }
        });
    }

    public void addSavingAccountListener(JButton button){
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ShowSavingAccount showSavingAccount = new ShowSavingAccount();
                SavingAccountController savingAccountController = new SavingAccountController(kid,showSavingAccount,false);
                openNewFrame(showSavingAccount);
            }
            public void refreshSaving(){
                ShowSavingAccount showSavingAccount = new ShowSavingAccount();
                SavingAccountController savingAccountController = new SavingAccountController(kid,showSavingAccount,false);
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


    public void  addReviewListener(JButton button){
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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


}
