package Controller.bank;

import Entity.Kids;
import Entity.Message;
import GUI.MainFrame_parent;
import GUI.bank_page.ShowCurrentAccount;
import GUI.bank_page.ShowSavingAccount;
import GUI.bank_page.bank_parents;
import GUI.bank_page.history_page;
import utill.validate.Validate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The Bank_parent_controller class manages the interaction between the bank GUI for parents and the underlying data model for a child user.
 * It handles user actions, such as viewing account details, changing interest rates, and reviewing transaction history.
 */
public class Bank_parent_controller {
    private Kids kid;
    private bank_parents GUI;
    private HistoryController historyController;
    private JFrame currentFrame; // Used to store the reference to the currently opened JFrame

    /**
     * Constructor to initialize the controller with the kid entity.
     *
     * @param kid The Kids entity representing the child user.
     */
    public Bank_parent_controller(Kids kid) {
        this.kid = kid;
        this.historyController = new HistoryController(kid);
    }

    /**
     * Sets the GUI reference for this controller.
     *
     * @param GUI The bank_parents GUI instance.
     */
    public void setGUI(bank_parents GUI) {
        this.GUI = GUI;
    }

    /**
     * Gets the bank_parents GUI associated with this controller.
     *
     * @param GUI The bank_parents GUI instance.
     * @return The bank_parents GUI instance.
     */
    public bank_parents getGUI(bank_parents GUI) {
        return GUI;
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
     * Adds an ActionListener to the button to review the transaction history.
     *
     * @param button The JButton to which the listener is added.
     */
    public void addHistory(JButton button) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                history_page review = new history_page(historyController);
                JFrame Review_win = new JFrame();
                openNewFrame(Review_win);
                Review_win.setTitle("History");
                Review_win.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                Review_win.setLocationRelativeTo(null);
                Review_win.add(review);
                Review_win.setSize(800, 400);
                Review_win.setVisible(true);
            }
        });
    }

    /**
     * Adds an ActionListener to the button to show the current account details.
     *
     * @param button The JButton to which the listener is added.
     */
    public void addCurrentDetails(JButton button) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ShowCurrentAccount showCurrentAccount = new ShowCurrentAccount();
                CurrentAccountController currentAccountController = new CurrentAccountController(kid, showCurrentAccount, true);
                openNewFrame(showCurrentAccount);
            }
        });
    }

    /**
     * Adds an ActionListener to the button to change the interest rate of the accounts.
     *
     * @param button The JButton to which the listener is added.
     */
    public void addChangeInterestRate(JButton button) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dialog = new JDialog(currentFrame, "Change Interest Rate", true);
                dialog.setTitle("Change Interest rate");
                dialog.setResizable(false);
                dialog.setLocationRelativeTo(GUI);
                dialog.setLayout(new GridLayout(4, 1));

                // First row: Label
                JLabel label = new JLabel("Change interest rate", SwingConstants.CENTER);
                label.setSize(200, 50);
                label.setFont(new Font(label.getFont().getName(), Font.BOLD, 18));
                dialog.add(label);

                // Second row: ComboBox
                String[] options = {"15 days", "1 month", "3 months"};
                JComboBox<String> comboBox = new JComboBox<>(options);
                dialog.add(comboBox);

                // Third row: TextField
                JTextField textField = new JTextField();
                dialog.add(textField);

                // Fourth row: Button
                JButton confirmButton = new JButton("Confirm");
                confirmButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String selectedOption = (String) comboBox.getSelectedItem();
                        String userInput = textField.getText();
                        try {
                            double newInterestRate = Validate.validateInterest(userInput);
                            kid.getAccountManager().setInterestRate(newInterestRate, selectedOption);
                            kid.getMessagelist().addMessage(new Message("parent", "The interest rate for " + selectedOption + " has been changed to " + userInput + "%","kid"));
                            dialog.dispose();
                        } catch (Exception ex) {
                            textField.setText("");
                        }
                    }
                });
                dialog.add(confirmButton);

                // Set dialog size and visibility
                dialog.pack();
                dialog.setVisible(true);
            }
        });
    }

    /**
     * Adds an ActionListener to the button to show the saving account details.
     *
     * @param button The JButton to which the listener is added.
     */
    public void addSavingAccountListener(JButton button) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ShowSavingAccount showSavingAccount = new ShowSavingAccount();
                SavingAccountController savingAccountController = new SavingAccountController(kid, showSavingAccount, true);
                openNewFrame(showSavingAccount);
            }
        });
    }

    /**
     * Opens a new JFrame and closes the currently opened JFrame.
     *
     * @param newFrame The new JFrame to be opened.
     */
    private void openNewFrame(JFrame newFrame) {
        if (currentFrame != null) {
            currentFrame.dispose();
        }

        currentFrame = newFrame;
        currentFrame.setLocationRelativeTo(null);
        currentFrame.setVisible(true);
    }
}
