package GUI.bank_page;

import Entity.SavingAccount;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a list of components associated with each saving account.
 */
public class SavingComponentList {
    private List<SavingAccount> savingAccountList;
    private List<JProgressBar> barlist;
    private List<JButton> cancelButton;
    private List<JLabel> remainingList;

    /**
     * Initializes a SavingComponentList object with the provided list of saving accounts.
     *
     * @param savingAccountList The list of saving accounts to be associated with the components.
     */
    SavingComponentList(List<SavingAccount> savingAccountList) {
        barlist = new ArrayList<>();
        cancelButton = new ArrayList<>();
        remainingList = new ArrayList<>();

        this.savingAccountList = savingAccountList;
        for (int i = 0; i < savingAccountList.size(); i++) {
            // Creating progress bar for each saving account
            JProgressBar progressBar = new JProgressBar(0, 100);
            progressBar.setStringPainted(true); // Show percentage
            progressBar.setAlignmentX(Component.CENTER_ALIGNMENT);
            barlist.add(progressBar);

            // Creating cancel button for each saving account
            JButton button = new JButton("Early withdraw");
            button.setBackground(Color.red);
            cancelButton.add(button);

            // Creating label to display remaining time for each saving account
            JLabel label = new JLabel("Remain:");
            label.setFont(new Font("Arial", Font.BOLD, 12));
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            remainingList.add(label);
        }
    }

    /**
     * Retrieves the list of cancel buttons associated with each saving account.
     *
     * @return The list of cancel buttons.
     */
    public List<JButton> getCancelButton() {
        return cancelButton;
    }

    /**
     * Retrieves the list of saving accounts associated with the components.
     *
     * @return The list of saving accounts.
     */
    public List<SavingAccount> getSavingAccountList() {
        return savingAccountList;
    }

    /**
     * Retrieves the list of progress bars associated with each saving account.
     *
     * @return The list of progress bars.
     */
    public List<JProgressBar> getBarlist() {
        return barlist;
    }

    /**
     * Retrieves the list of labels displaying remaining time associated with each saving account.
     *
     * @return The list of labels displaying remaining time.
     */
    public List<JLabel> getRemainingList() {
        return remainingList;
    }
}
