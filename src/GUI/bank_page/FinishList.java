package GUI.bank_page;

import Entity.SavingAccount;

import javax.swing.*;
import java.awt.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FinishList {
    private List<SavingAccount> savingAccountList;
    private List<JButton> Buttonlist;

    FinishList(List<SavingAccount> savingAccountList){
        Buttonlist = new ArrayList<>();
        int i;
        this.savingAccountList = savingAccountList;
        for(i=0;i<savingAccountList.size();i++){
            JButton finishButton = new JButton("Take my Money!");
            Buttonlist.add(finishButton);
        }

    }

    public List<SavingAccount> getSavingAccountList() {
        return savingAccountList;
    }

    public List<JButton> getButtonlist() {
        return Buttonlist;
    }
}