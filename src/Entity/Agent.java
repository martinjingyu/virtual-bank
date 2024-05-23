package Entity;

import utill.validate.Validate;

import java.util.List;
import java.util.ArrayList;
public class Agent {
    private String state;
    private Kids kid;
    private String accountType; // "saving" or "current"
    private int selectedIndex; // to store the selected account index for deletion or modification

    public Agent(Kids kid){
        this.kid = kid;
        state = "wait";
        accountType = "";
        selectedIndex = -1;
    }

    public String receiveUserInput(String text){
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
                            kid.getAccountManager().removeSavingAccount(selectedIndex);
                            output = accountType + " account deleted successfully.";
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
                try{
                    text=Validate.validateName(text);
                    if (accountType.equals("saving")) {
                        kid.getAccountManager().getSavingAccounts().get(selectedIndex).setName(text);
                    } else {
                        kid.getAccountManager().getCurrentAccounts().get(selectedIndex).setName(text);
                    }
                    output = accountType + " account name modified successfully.";
                    state = "wait";
                }
                catch (Exception e1){
                    output = "Invalid name. Please enter a valid name.";
                }
                break;
        }

        return output;
    }

    private int getSavingAccountIndexByName(String name) {
        List<SavingAccount> accounts = kid.getAccountManager().getSavingAccounts();
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getName().equalsIgnoreCase(name)) {
                return i;
            }
        }
        return -1;
    }

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
