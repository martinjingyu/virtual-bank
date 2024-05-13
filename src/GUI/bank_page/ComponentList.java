package GUI.bank_page;

import Entity.SavingAccount;

import javax.swing.*;
import java.awt.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class ComponentList {
    private List<SavingAccount> savingAccountList;
    private List<JProgressBar> barlist;
    private Timer timer;

    ComponentList(List<SavingAccount> savingAccountList){
        int i;
        this.savingAccountList = savingAccountList;
        for(i=0;i<savingAccountList.size();i++){
            JProgressBar progressBar = new JProgressBar(0, 100);
            progressBar.setStringPainted(true); // 显示百分比
            progressBar.setAlignmentX(Component.CENTER_ALIGNMENT);
            barlist.add(progressBar);
        }
        timer = new Timer(1000, e -> updateProgressBar(savingAccountList,barlist));
        timer.start();
    }

    private void updateProgressBar(List<SavingAccount> savingAccountList, List<JProgressBar> progressBarList) {
        // 计算已经过的时间比例
        int i;
        for(i=0;i<savingAccountList.size();i++){
            SavingAccount savingAccount = savingAccountList.get(i);
            JProgressBar progressBar = progressBarList.get(i);
            LocalDateTime currentTime = LocalDateTime.now();
            LocalDateTime startTime = savingAccount.getStartTime();
            LocalDateTime endTime = savingAccount.getEndTime();
            double progress = 100.0 * Duration.between(startTime, currentTime).toMillis() /
                    Duration.between(startTime, endTime).toMillis();

            // 更新进度条的值
            progressBar.setValue((int) progress);

            // 检查是否已完成
            if (progress >= 100) {
                System.out.println("finish");
            }
        }
    }

    public List<SavingAccount> getSavingAccountList() {
        return savingAccountList;
    }

    public List<JProgressBar> getBarlist() {
        return barlist;
    }
}
