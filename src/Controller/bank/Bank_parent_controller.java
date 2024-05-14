package Controller.bank;

import Entity.Kids;
import GUI.MainFrame_parent;
import GUI.bank_page.bank_parents;
import GUI.bank_page.history_page;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Bank_parent_controller {
    //    private Parent parent;
    private Kids kid;
    private bank_parents GUI;
    private HistoryController historyController;

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
