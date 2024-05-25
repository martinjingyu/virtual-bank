package GUI.bank_page;

import Entity.CurrentAccount;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a list of components for managing current accounts.
 */
public class CurrentComponentList {
    private List<CurrentAccount> currentAccountList;
    private List<JButton> transferButtons;

    /**
     * Constructs a CurrentComponentList object with a list of current accounts.
     *
     * @param currentAccountList The list of current accounts.
     */
    public CurrentComponentList(List<CurrentAccount> currentAccountList) {
        transferButtons = new ArrayList<>();
        this.currentAccountList = currentAccountList;

        for (CurrentAccount currentAccount : currentAccountList) {
            JButton button = new JButton("Transfer to this account");
            button.setBackground(Color.green);
            transferButtons.add(button);
        }
    }

    /**
     * Retrieves the list of transfer buttons.
     *
     * @return The list of transfer buttons.
     */
    public List<JButton> getTransferButton() {
        return transferButtons;
    }

    /**
     * Retrieves the list of current accounts.
     *
     * @return The list of current accounts.
     */
    public List<CurrentAccount> getCurrentAccountList() {
        return currentAccountList;
    }
}
