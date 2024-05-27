package GUI.bank_page;

import Entity.SavingAccount;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a list of components for managing finished saving accounts.
 */
public class FinishList {
    private List<SavingAccount> savingAccountList;
    private List<JButton> Buttonlist;

    /**
     * Constructs a FinishList object with a list of saving accounts.
     *
     * @param savingAccountList The list of saving accounts.
     */
    public FinishList(List<SavingAccount> savingAccountList) {
        Buttonlist = new ArrayList<>();
        this.savingAccountList = savingAccountList;

        for (SavingAccount savingAccount : savingAccountList) {
            JButton finishButton = new JButton("Take my Money!");
            Buttonlist.add(finishButton);
        }
    }

    /**
     * Retrieves the list of buttons.
     *
     * @return The list of buttons.
     */
    public List<JButton> getButtonlist() {
        return Buttonlist;
    }
}
