package GUI.bank_page;

import Controller.bank.SavingAccountController;
import Entity.SavingAccount;

import javax.swing.*;
import java.awt.*;
import java.time.Duration;
import java.time.LocalDateTime;

public class ShowSavingAccount extends JFrame {
    private JProgressBar progressBar;
    private JButton actionButton;
    private SavingAccountController savingAccountController;
    private SavingAccount savingAccount;

    public ShowSavingAccount() {
        setTitle("Saving Account");
        setSize(960, 540);
        savingAccount = new SavingAccount();
        savingAccount.setStartTime(LocalDateTime.now());
        savingAccount.setEndTime(LocalDateTime.now().plusMinutes(1));
        this.savingAccountController = savingAccountController;

        // 设置布局为网格布局
        setLayout(new BorderLayout());
        JPanel mainGrid = new JPanel(new GridLayout());
        mainGrid.setSize(900,500);
        add(mainGrid,BorderLayout.CENTER);


        // 创建进度条并设置初始值
        progressBar = new JProgressBar(0, 100);

        // 添加进度条到 JFrame 上
        JPanel j = new JPanel(new BorderLayout());
        j.setBackground(Color.blue);
        j.add(progressBar, BorderLayout.NORTH);
        add(j);

        // 创建按钮
        actionButton = new JButton("Start");
        actionButton.addActionListener(e -> {
            // 模拟进度增加
            savingAccount.setStartTime(LocalDateTime.now());
            savingAccount.setEndTime(LocalDateTime.now().plusMinutes(5)); // 示例: 5分钟后完成
            updateProgressBar();
        });

        // 添加按钮到 JFrame 上
        j.add(actionButton, BorderLayout.SOUTH);

        // 启动定时器，每秒更新进度条
        Timer timer = new Timer(1000, e -> updateProgressBar());
        timer.start();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    private void updateProgressBar() {
        // 计算已经过的时间比例
        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime startTime = savingAccount.getStartTime();
        LocalDateTime endTime = savingAccount.getEndTime();
        double progress = 100.0 * Duration.between(startTime, currentTime).toMillis() /
                Duration.between(startTime, endTime).toMillis();

        // 更新进度条的值
        progressBar.setValue((int) progress);

        // 检查是否已完成
        if (progress >= 100) {
            actionButton.setText("Completed");
            actionButton.setBackground(Color.GREEN);
            actionButton.setEnabled(false);
        }
    }

    public static void main(String[] args) {
        // 创建 JFrame 并显示
        JFrame frame = new ShowSavingAccount();
    }
}


