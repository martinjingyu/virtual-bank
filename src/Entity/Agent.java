package Entity;

import javax.swing.*;
import java.util.Optional;

public class Agent {
    private String state;
    private String accountToDelete;
    private Kids kid;

    public Agent(Kids kid) {
        this.kid = kid;
        state = "wait";
        accountToDelete = "";
    }

    public String receiveUserInput(String text) {
        String output = "";

        switch (state) {
            case "wait":
                if (text.toLowerCase().contains("delete")&&text.toLowerCase().contains("account")) {
                    state = "promptAccountName";
                    output = "If you want to delete account, please enter the account name you want to delete:";
                } else {
                    output = "I'm here to help. You can ask me to delete an account.";
                }
                break;

//            case "promptAccountName":
//                accountToDelete = text.trim();
//                Optional<SavingAccount> account = kid.getAccountManager().getSavingAccountByName(accountToDelete);
//
//                if (account.isPresent()) {
//                    double balance = account.get().getBalance();
//                    if (balance > 0) {
//                        state = "wait";
//                        output = "The account \"" + accountToDelete + "\" has a balance of " + balance + ". Please clear the balance before deleting the account.";
//                    } else {
//                        int confirmation = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete the account \"" + accountToDelete + "\"?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
//                        if (confirmation == JOptionPane.YES_OPTION) {
//                            kid.getAccountManager().deleteSavingAccount(accountToDelete);
//                            output = "The account \"" + accountToDelete + "\" has been successfully deleted.";
//                        } else {
//                            output = "Account deletion cancelled.";
//                        }
//                        state = "wait";
//                    }
//                } else {
//                    output = "The account \"" + accountToDelete + "\" does not exist. Please enter a valid account name or create a new account.";
//                    state = "wait";
//                }
//                break;

            default:
                state = "wait";
                output = "I'm here to help. You can ask me to delete an account.";
                break;
        }

        return output;
    }
}