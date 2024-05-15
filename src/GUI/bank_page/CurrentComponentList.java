package GUI.bank_page;

import Entity.CurrentAccount;
import Entity.SavingAccount;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CurrentComponentList {
    private List<CurrentAccount> currentAccountList;
    private List<JButton> transferButtons;



    CurrentComponentList(List<CurrentAccount> currentAccountList){
        transferButtons = new ArrayList<>();

        int i;
        this.currentAccountList = currentAccountList;
        for(i=0;i<currentAccountList.size();i++){

            JButton button = new JButton("Transfer to this account");
            button.setBackground(Color.green);
            transferButtons.add(button);


        }
    }

    public List<JButton> getTransferButton() {
        return transferButtons;
    }

    public List<CurrentAccount> getCurrentAccountList() {
        return currentAccountList;
    }

}
