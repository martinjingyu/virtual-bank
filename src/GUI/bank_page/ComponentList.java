package GUI.bank_page;

import Entity.SavingAccount;

import javax.swing.*;
import java.awt.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ComponentList {
    private List<SavingAccount> savingAccountList;
    private List<JProgressBar> barlist;
    private Timer timer;

    ComponentList(List<SavingAccount> savingAccountList){
        barlist = new ArrayList<>();
        int i;
        this.savingAccountList = savingAccountList;
        for(i=0;i<savingAccountList.size();i++){
            JProgressBar progressBar = new JProgressBar(0, 100);
            progressBar.setStringPainted(true); // 显示百分比
            progressBar.setAlignmentX(Component.CENTER_ALIGNMENT);
            barlist.add(progressBar);
        }

    }



    public List<SavingAccount> getSavingAccountList() {
        return savingAccountList;
    }

    public List<JProgressBar> getBarlist() {
        return barlist;
    }
}
