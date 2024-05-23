package Controller.bank;

import Entity.Kids;
import GUI.MainFrame_parent;
import GUI.bank_page.*;

import javax.swing.*;
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
//        this.GUI = GUI;
//        GUI.initData(kid.getSavingAccountList());
//        addListener(GUI);
//        this.parent = parent;
//        addReviewListener(GUI.getMainFrame().history);
//        addSavingDetailsListener(GUI.savingDetails);
//        addCurrentDetailsListener(GUI.currentDetails);
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

