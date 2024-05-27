package Controller.bank;

import Entity.HistoryTransactionList;
import Entity.Kids;
import GUI.bank_page.history_page;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * The HistoryController class manages the interaction between the GUI and the data model for the transaction history of a kid.
 * It handles user actions such as selecting a date to view transactions and refreshing the transaction display.
 */
public class HistoryController {
    private Kids kid;
    private history_page GUI;
    private HistoryTransactionList historyTransactionList;
    private String SelectedDate;

    /**
     * Constructs a HistoryController for the given kid.
     *
     * @param kid The Kids entity representing the child user.
     */
    public HistoryController(Kids kid) {
        this.kid = kid;
        this.historyTransactionList = kid.getTransactionList();
    }

    /**
     * Sets the GUI instance for this controller.
     *
     * @param GUI The history_page GUI instance.
     */
    public void setGUI(history_page GUI) {
        this.GUI = GUI;
    }

    /**
     * Gets the Kids entity associated with this controller.
     *
     * @return The Kids entity.
     */
    public Kids getKid() {
        return kid;
    }

    /**
     * Gets the currently selected date.
     *
     * @return The selected date as a String.
     */
    public String getSelectedDate() {
        return SelectedDate;
    }

    /**
     * Checks if a date has been selected.
     *
     * @return true if a date is selected, false otherwise.
     */
    public boolean hasSelectedDate() {
        return SelectedDate != null && !SelectedDate.isEmpty();
    }

    /**
     * Adds mouse listeners to the date labels in the GUI for user interaction.
     */
    public void addButtonListener() {
        for (JLabel label : GUI.getDateList()) {
            label.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    for (JLabel c : GUI.getDateList()) {
                        c.setBackground(Color.white);
                    }
                    label.setBackground(Color.cyan);
                    SelectedDate = label.getText();
                    GUI.refreshUI();
                }
            });
        }
    }

    /**
     * Main method for testing purposes.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        // Kids kid = ReadAll.readall("222");
    }
}
