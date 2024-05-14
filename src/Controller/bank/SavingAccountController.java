package Controller.bank;

import Entity.Kids;
import GUI.bank_page.ShowSavingAccount;

public class SavingAccountController {
    private Kids kid;
    private ShowSavingAccount GUI;

    SavingAccountController(Kids kid,ShowSavingAccount GUI){
        this.kid = kid;
        this.GUI = GUI;
        GUI.initData(kid.getBothAccountList().getSavingAccounts());
        addListener(GUI);
    }

    private void addListener(ShowSavingAccount GUI){

    }


    public Kids getKid() {
        return kid;
    }
}
