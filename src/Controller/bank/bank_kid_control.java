package Controller.bank;

import Entity.Kids;
import GUI.MainFrame;
import GUI.bank_page.bank_kid;
import GUI.bank_page.history_page;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class bank_kid_control {
    private static JTextField savingGoalTextField;
    private static int clickCount=0;

    public static void addButtonListener(MainFrame mainFrame, JButton button, Kids kid){

        savingGoalTextField = new JTextField();
        savingGoalTextField.setBounds(630, 80, 75, 30);
        savingGoalTextField.setVisible(false);
        mainFrame.add(savingGoalTextField);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String buttonName = e.getActionCommand();
                System.out.println(buttonName);
                if (buttonName.equals("Edit")){
                    clickCount++; // 每次点击增加点击次数
                    // 根据点击次数的奇偶性设置文本框的可见性
                    if (clickCount % 2 == 1) {
                        savingGoalTextField.setVisible(true);
                        mainFrame.revalidate();
                        mainFrame.repaint();
                    } else {
                        savingGoalTextField.setVisible(false);
                        kid.getBank().changeSavingGoal(savingGoalTextField);
                        mainFrame.refresh();
                    }
                }else if (buttonName.equals("INTO")){
                    JDialog dialog = new JDialog();
                    kid.getBank().transaction(dialog, "current", "saving");
                    dialog.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosed(WindowEvent e) {
                            mainFrame.refresh();
                        }
                    });
                } else if (buttonName.equals("Review")) {
                    history_page review = new history_page();
                    JFrame Review_win = new JFrame();
                    Review_win.setTitle("History");
                    Review_win.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    Review_win.setLocationRelativeTo(null);
                    Review_win.add(review);
                    Review_win.setSize(800, 400);
                    Review_win.setVisible(true);
                }

            }
        });
    }

}
