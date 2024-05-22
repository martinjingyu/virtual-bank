package Controller.bank;

import Entity.Kids;

import GUI.bank_page.ShowCurrentAccount;
import GUI.bank_page.ShowSavingAccount;
import GUI.bank_page.history_page;
import GUI.bank_page.Bank_kid;
import GUI.RefreshListener;
import utill.validate.Validate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class Bank_kid_control implements RefreshListener{
    private static JTextField savingGoalTextField;
    private static int clickCount=0;
    private String inputText;
    private Kids kid;
    private Bank_kid GUI;
    private HistoryController historyController;
    private JButton button_save;
    private final Color fontColor = new Color(49, 122, 232); // #317AE8
    private final Font font = new Font("Arial", Font.PLAIN, 20);
//    private JDialog dialog;
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
                    JDialog dialog = new JDialog();//构造一个新的JFrame，作为新窗口。

                    dialog.setBounds(// 让新窗口与SwingTest窗口示例错开50像素。
                            new Rectangle(
                                    80,
                                    80,
                                    300, 300
                            )
                    );
                    dialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL); // 设置模式类型。
                    dialog.setLayout(new GridBagLayout()); // 使用GridBagLayout布局
                    GridBagConstraints gbc = new GridBagConstraints();
                    gbc.insets = new Insets(10, 10, 10, 10); // 设置组件之间的间距

                    JLabel jl1 = new JLabel("How much you want to save?");
                    gbc.gridx = 0;
                    gbc.gridy = 0;
                    gbc.gridwidth = 2;
                    dialog.add(jl1, gbc);

                    JTextField savingGoalTextField = new JTextField(10);
                    gbc.gridx = 0;
                    gbc.gridy = 1;
                    gbc.gridwidth = 2;
                    dialog.add(savingGoalTextField, gbc);

                    JButton button_save = new JButton("Save");
                    gbc.gridx = 0;
                    gbc.gridy = 2;
                    gbc.gridwidth = 2;
                    gbc.anchor = GridBagConstraints.CENTER;
                    dialog.add(button_save, gbc);

                    button_save.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String input = savingGoalTextField.getText();
                            try{
                                double value = Validate.validateNumber(input);
                                dialog.dispose(); // 关闭对话框
                                kid.getAccountManager().setSavingGoal(value);
                                GUI.revalidate();
                                GUI.repaint();
                                GUI.getSavingGoals().setText(String.valueOf(value));
                            }
                            catch (Exception e1){
                                savingGoalTextField.setText("");
                            }
                        }
                    });

                    dialog.pack(); // 调整窗口大小以适应所有组件
                    dialog.setLocationRelativeTo(null); // 将窗口居中
                    dialog.setVisible(true);

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


    @Override
    public void refreshUI() {
//        GUI.refresh();
    }
}
