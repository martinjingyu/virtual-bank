package GUI.bank_page;

import Entity.SavingAccount;

import javax.swing.*;
import java.awt.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class FinishList {
    private List<SavingAccount> savingAccountList;
    private List<JButton> Buttonlist;

    FinishList(List<SavingAccount> savingAccountList){
        int i;
        this.savingAccountList = savingAccountList;
        for(i=0;i<savingAccountList.size();i++){
            JButton finishButton = new JButton("Take my Money!");
            finishButton.setFont(new Font("Arial", Font.BOLD, 12));
            finishButton.setAlignmentX(Component.CENTER_ALIGNMENT);
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