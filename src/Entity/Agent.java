package Entity;

import utill.validate.Validate;

import javax.swing.*;
import java.util.List;
import java.util.ArrayList;

/**
 * The Agent class represents an assistant that helps users manage their bank accounts.
 * It handles commands for deleting and modifying account names, as well as input validation.
 */
public class Agent {
    private String state;
    private Kids kid;
    private String accountType; // "saving" or "current"
    private int selectedIndex; // to store the selected account index for deletion or modification

    /**
     * Constructs an Agent with the specified kid.
     *
     * @param kid the Kids object associated with this Agent
     */
    public Agent(Kids kid) {
        this.kid = kid;
        state = "wait";
        accountType = "";
        selectedIndex = -1;
    }

    /**
     * Processes user input and returns the appropriate response based on the current state.
     *
     * @param text the user input text
     * @return the response based on the processed input
     */
    public String receiveUserInput(String text) {
        String output = "";

        if (text.equalsIgnoreCase("cancel")) {
            state = "wait";
            output = "Operation cancelled. Please enter a new command.";
            return output;
        }

        switch (state) {
            case "wait":
                if (text.equalsIgnoreCase("delete account")) {
                    state = "select account type";
                    output = "Please specify the type of account to delete: 'saving' or 'current'.";
                } else if (text.equalsIgnoreCase("modify account name")) {
                    state = "select account type for modification";
                    output = "Please specify the type of account to modify: 'saving' or 'current'.";
                } else {
                    output = "Invalid command. Please say 'delete account' or 'modify account name'.";
                }
                break;

            case "select account type":
                if (text.equalsIgnoreCase("saving") || text.equalsIgnoreCase("current")) {
                    accountType = text.toLowerCase();
                    state = "select account for deletion";
                    output = "Please enter the name of the " + accountType + " account to delete.";
                } else {
                    output = "Invalid account type. Please specify 'saving' or 'current'.";
                }
                break;

            case "select account type for modification":
                if (text.equalsIgnoreCase("saving") || text.equalsIgnoreCase("current")) {
                    accountType = text.toLowerCase();
                    state = "select account for modification";
                    output = "Please enter the name of the " + accountType + " account to modify.";
                } else {
                    output = "Invalid account type. Please specify 'saving' or 'current'.";
                }
                break;

            case "select account for deletion":
                if (accountType.equals("saving")) {
                    selectedIndex = getSavingAccountIndexByName(text);
                    if (selectedIndex != -1) {
                        if (kid.getAccountManager().getSavingAccounts().get(selectedIndex).getBalance() == 0) {
                            JOptionPane pane = new JOptionPane();
                            int response = pane.showConfirmDialog(null,
                                    "Are you sure you want to delete this "+accountType+ " "+kid.getAccountManager().getSavingAccounts().get(selectedIndex).getName()+"?",
                                    "Confirm Deletion",
                                    JOptionPane.YES_NO_OPTION,
                                    JOptionPane.WARNING_MESSAGE);
                            if (response == JOptionPane.YES_OPTION) {
                                kid.getAccountManager().removeSavingAccount(selectedIndex);
                                output = accountType + " account deleted successfully.";
                                // User confirmed, proceed with deletion
                                JOptionPane.showMessageDialog(null,
                                        "Account deleted successfully.",
                                        "Deletion Successful",
                                        JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                // User did not confirm, do nothing
                                JOptionPane.showMessageDialog(null,
                                        "Account deletion canceled.",
                                        "Deletion Canceled",
                                        JOptionPane.INFORMATION_MESSAGE);
                                output ="You can enter \"cancel\" to end";
                            }
                        } else {
                            output = "Account has a balance. Please clear the balance before deleting.";
                        }
                    } else {
                        output = "Account not found. Please try again.";
                    }
                } else {
                    selectedIndex = getCurrentAccountIndexByName(text);
                    if (selectedIndex != -1) {
                        if (kid.getAccountManager().getCurrentAccounts().get(selectedIndex).getBalance() == 0) {
                            kid.getAccountManager().removeCurrentAccount(selectedIndex);
                            output = accountType + " account deleted successfully.";
                        } else {
                            output = "Account has a balance. Please clear the balance before deleting.";
                        }
                    } else {
                        output = "Account not found. Please try again.";
                    }
                }
                state = "wait";
                break;

            case "select account for modification":
                if (accountType.equals("saving")) {
                    selectedIndex = getSavingAccountIndexByName(text);
                } else {
                    selectedIndex = getCurrentAccountIndexByName(text);
                }

                if (selectedIndex != -1) {
                    state = "enter new name";
                    output = "Please enter the new name for the " + accountType + " account.";
                } else {
                    output = "Account not found. Please try again.";
                }
                break;

            case "enter new name":
                try {
                    text = Validate.validateName(text);
                    if (accountType.equals("saving")) {
                        kid.getAccountManager().getSavingAccounts().get(selectedIndex).setName(text);
                    } else {
                        kid.getAccountManager().getCurrentAccounts().get(selectedIndex).setName(text);
                    }
                    output = accountType + " account name modified successfully.";
                    state = "wait";
                } catch (Exception e1) {
                    output = "Invalid name. Please enter a valid name.";
                }
                break;
        }

        return output;
    }

    /**
     * Gets the index of a saving account by its name.
     *
     * @param name the name of the saving account
     * @return the index of the saving account, or -1 if not found
     */
    private int getSavingAccountIndexByName(String name) {
        List<SavingAccount> accounts = kid.getAccountManager().getSavingAccounts();
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getName().equalsIgnoreCase(name)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Gets the index of a current account by its name.
     *
     * @param name the name of the current account
     * @return the index of the current account, or -1 if not found
     */
    private int getCurrentAccountIndexByName(String name) {
        List<CurrentAccount> accounts = kid.getAccountManager().getCurrentAccounts();
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getName().equalsIgnoreCase(name)) {
                return i;
            }
        }
        return -1;
    }
}
