package Controller.bank;

import Entity.Kids;
import Exceptions.InsufficientFundsException;
import GUI.bank_page.Bank_kid;
import GUI.bank_page.ShowSavingAccount;
import utill.validate.Validate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

/**
 * The SavingAccountController class manages the interaction between the GUI and the data model for the saving accounts of a kid.
 * It handles user actions such as creating new accounts, depositing money, withdrawing money, and refreshing the account display.
 */
public class SavingAccountController {
    private Kids kid;
    private ShowSavingAccount GUI;
    private Bank_kid bank_kid;

    /**
     * Constructs a SavingAccountController with the given parameters.
     *
     * @param kid The Kids entity representing the child user.
     * @param GUI The ShowSavingAccount GUI instance.
     * @param whetherParent Boolean indicating if the user is a parent.
     * @param bank_kid The Bank_kid instance for updating account information.
     */
    SavingAccountController(Kids kid, ShowSavingAccount GUI, Boolean whetherParent, Bank_kid bank_kid) {
        this.kid = kid;
        this.GUI = GUI;
        this.bank_kid = bank_kid;

        GUI.initData(kid.getAccountManager().getSavingAccounts(), whetherParent, kid.getAccountManager());
        addListener(GUI);
    }

    /**
     * Constructs a SavingAccountController with the given parameters.
     *
     * @param kid The Kids entity representing the child user.
     * @param GUI The ShowSavingAccount GUI instance.
     * @param whetherParent Boolean indicating if the user is a parent.
     */
    SavingAccountController(Kids kid, ShowSavingAccount GUI, Boolean whetherParent) {
        this.kid = kid;
        this.GUI = GUI;
        GUI.initData(kid.getAccountManager().getSavingAccounts(), whetherParent, kid.getAccountManager());
        addListener(GUI);
    }

    /**
     * Adds listeners to the buttons in the ShowSavingAccount GUI for user interaction.
     *
     * @param GUI The ShowSavingAccount GUI instance.
     */
    private void addListener(ShowSavingAccount GUI) {
        List<JButton> cancelButtons = GUI.getComponentList().getCancelButton();
        for (int i = 0; i < cancelButtons.size(); i++) {
            JButton button = cancelButtons.get(i);
            int finalI = i;
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JDialog frame = new JDialog();
                    frame.setResizable(false);
                    frame.setModal(true);
                    frame.setTitle("Select Current Account");

                    JPanel panel = new JPanel(new BorderLayout());
                    frame.setSize(300, 100);

                    // Add components to the dialog
                    List<String> accountNames = kid.getAccountManager().getCurrentAccountNames();
                    String[] namesArray = accountNames.toArray(new String[0]);
                    JComboBox<String> comboBox = new JComboBox<>(namesArray);
                    panel.add(comboBox, BorderLayout.CENTER);

                    JButton confirmButton = new JButton("Confirm");
                    confirmButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            // Get the selected current account
                            int selectedIndex = comboBox.getSelectedIndex();

                            // Execute early withdrawal
                            kid.getAccountManager().earlyWithdrew(selectedIndex, finalI);
                            bank_kid.updateAccounts();
                            refresh(true);
                            // Close the dialog
                            frame.dispose();
                        }
                    });
                    panel.add(confirmButton, BorderLayout.SOUTH);

                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    frame.getContentPane().add(panel);
                    frame.setLocationRelativeTo(null); // Center the dialog
                    frame.setVisible(true);
                }
            });
        }

        List<JButton> finishButtons = GUI.getFinishList().getButtonlist();
        for (int i = 0; i < finishButtons.size(); i++) {
            JButton button = finishButtons.get(i);
            int finalI = i;
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (button.getText().equals("Deposit")) {
                        createDepositDialog(finalI);
                    } else if (button.getText().equals("Take my Money!")) {
                        createWithdrawDialog(finalI);
                    }
                }
            });
        }

        if (GUI.getAddButton() != null) {
            GUI.getAddButton().addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    createNewAccount();
                }
            });
        }
    }

    /**
     * Creates a dialog for depositing money into a saving account.
     *
     * @param accountIndex The index of the saving account.
     */
    private void createDepositDialog(int accountIndex) {
        JDialog frame = new JDialog();
        frame.setTitle("Deposit to Saving Account");
        frame.setModal(true);
        frame.setResizable(false);
        frame.setSize(300, 150);

        JPanel panel = new JPanel(new BorderLayout());

        List<String> accountNames = kid.getAccountManager().getCurrentAccountNames();
        String[] namesArray = accountNames.toArray(new String[0]);
        JPanel middle = new JPanel(new BorderLayout());
        JComboBox<String> comboBox = new JComboBox<>(namesArray);
        JComboBox<String> time = new JComboBox<>(new String[]{"15 days", "1 month", "3 months"});

        middle.add(comboBox, BorderLayout.CENTER);
        middle.add(time, BorderLayout.SOUTH);

        panel.add(middle, BorderLayout.CENTER);

        JTextField textField = new JTextField();
        JButton confirmButton = new JButton("Confirm");
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = comboBox.getSelectedIndex();
                String selectedTime = (String) time.getSelectedItem();
                try {
                    double value = Validate.validateNumber(textField.getText());
                    kid.getAccountManager().depositCurrentToSaving(selectedIndex, accountIndex, value, selectedTime);
                    refresh(true);
                    bank_kid.updateAccounts();
                    frame.dispose();
                } catch (InsufficientFundsException e2) {
                    JOptionPane.showMessageDialog(null, "Insufficient Funds", "Error", JOptionPane.ERROR_MESSAGE);
                    textField.setText("");
                } catch (Exception e1) {
                    textField.setText("");
                }
            }
        });
        panel.add(confirmButton, BorderLayout.SOUTH);
        panel.add(textField, BorderLayout.NORTH);

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setContentPane(panel);
        frame.setLocationRelativeTo(null); // Center the dialog
        frame.setVisible(true);
    }

    /**
     * Creates a dialog for withdrawing money from a saving account.
     *
     * @param accountIndex The index of the saving account.
     */
    private void createWithdrawDialog(int accountIndex) {
        JDialog frame = new JDialog();
        frame.setModal(true);
        frame.setResizable(false);
        frame.setTitle("Withdraw from Saving Account");
        frame.setSize(300, 100);

        JPanel panel = new JPanel(new BorderLayout());

        List<String> accountNames = kid.getAccountManager().getCurrentAccountNames();
        String[] namesArray = accountNames.toArray(new String[0]);
        JComboBox<String> comboBox = new JComboBox<>(namesArray);

        panel.add(comboBox, BorderLayout.CENTER);

        JButton confirmButton = new JButton("Confirm");
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = comboBox.getSelectedIndex();
                kid.getAccountManager().savingWithdrewToCurrent(selectedIndex, accountIndex);
                refresh(true);
                bank_kid.updateAccounts();
                frame.dispose();
            }
        });
        panel.add(confirmButton, BorderLayout.SOUTH);

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().add(panel);
        frame.setLocationRelativeTo(null); // Center the dialog
        frame.setVisible(true);

    }

    /**
     * Creates a new saving account.
     */
    public void createNewAccount() {
        JDialog dialog = new JDialog(GUI, "Create New Account", true);
        dialog.setResizable(false);
        dialog.setLayout(new BorderLayout());
        dialog.setSize(300, 200);
        dialog.setLocationRelativeTo(GUI);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(2, 1));
        JLabel nameLabel = new JLabel("Enter Account Name:");
        JTextField nameField = new JTextField();
        inputPanel.add(nameLabel);
        inputPanel.add(nameField);

        JButton confirmButton = new JButton("Confirm");
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String accountName = nameField.getText().trim();
                try {
                    accountName = Validate.validateName(accountName);
                    Boolean whetherRepeat = Validate.validateRepeat(accountName, kid.getAccountManager().getSavingAccountNames());
                    if (whetherRepeat) {
                        throw new Exception("Account name already exists");
                    }
                    GUI.afterAddAccount(kid.getAccountManager(), accountName, kid);
                    dialog.dispose();
                    addListener(GUI);
                } catch (Exception e1) {
                    nameField.setText("");
                }
            }
        });
        inputPanel.add(nameLabel);
        inputPanel.add(Box.createVerticalStrut(10));
        inputPanel.add(nameField);
        inputPanel.add(Box.createVerticalStrut(10));
        inputPanel.add(confirmButton);

        dialog.getContentPane().add(inputPanel);
        dialog.pack();
        dialog.setVisible(true);
    }

    /**
     * Refreshes the saving accounts display in the GUI.
     *
     * @param whetherParent Boolean indicating if the user is a parent.
     */
    public void refresh(Boolean whetherParent) {
        GUI.refresh(kid.getAccountManager().getSavingAccounts(), whetherParent);
    }

    /**
     * Gets the ShowSavingAccount GUI instance.
     *
     * @return The ShowSavingAccount GUI instance.
     */
    public ShowSavingAccount getGUI() {
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
}
