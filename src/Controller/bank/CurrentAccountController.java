package Controller.bank;

import Entity.AccountManager;
import Entity.Kids;
import GUI.bank_page.ShowCurrentAccount;
import utill.validate.Validate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

/**
 * The CurrentAccountController class manages the interaction between the GUI and the data model for the current accounts of a kid.
 * It handles user actions such as transferring funds between accounts, creating new accounts, and refreshing the account display.
 */
public class CurrentAccountController {
    private Kids kid;
    private ShowCurrentAccount GUI;

    /**
     * Constructor to initialize the controller with the kid entity and the GUI.
     *
     * @param kid The Kids entity representing the child user.
     * @param GUI The ShowCurrentAccount GUI instance.
     * @param whetherParent Boolean indicating if the parent is accessing the account.
     */
    public CurrentAccountController(Kids kid, ShowCurrentAccount GUI, Boolean whetherParent) {
        this.kid = kid;
        this.GUI = GUI;
        GUI.initData(kid.getAccountManager().getCurrentAccounts(), whetherParent, kid.getAccountManager());
        addListener(GUI);
    }

    /**
     * Adds listeners to the GUI components for user interactions.
     *
     * @param GUI The ShowCurrentAccount GUI instance.
     */
    private void addListener(ShowCurrentAccount GUI) {
        List<JButton> cancelButtons = GUI.getComponentList().getTransferButton();
        for (int i = 0; i < cancelButtons.size(); i++) {
            JButton button = cancelButtons.get(i);
            int finalI = i;
            button.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    JDialog frame = new JDialog();
                    frame.setResizable(false);
                    frame.setTitle("Select Current Account");
                    frame.setModal(true);
                    frame.setSize(300, 120);
                    JPanel panel = new JPanel(new BorderLayout());

                    List<String> accountNames = kid.getAccountManager().getCurrentAccountNames();
                    String[] namesArray = accountNames.toArray(new String[0]);
                    JComboBox<String> comboBox = new JComboBox<>(namesArray);
                    JTextField textField = new JTextField();
                    panel.add(comboBox, BorderLayout.CENTER);
                    panel.add(textField, BorderLayout.NORTH);
                    JButton confirmButton = new JButton("Confirm");
                    confirmButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            try {
                                int selectedIndex = comboBox.getSelectedIndex();
                                double value = Validate.validateNumber(textField.getText());
                                kid.getAccountManager().transfer(selectedIndex, finalI, value);
                                refresh(kid.getAccountManager());
                                frame.dispose();
                            } catch (Exception e1) {
                                textField.setText("");
                            }
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
     * Creates a new current account through a dialog.
     */
    public void createNewAccount() {
        JDialog dialog = new JDialog(GUI, "Create New Account", true);
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
                try {
                    String accountName = Validate.validateName(nameField.getText());
                    Boolean whetherRepeat = Validate.validateRepeat(accountName, kid.getAccountManager().getCurrentAccountNames());
                    if (!whetherRepeat) {
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
     * Refreshes the GUI with updated account information.
     *
     * @param accountManager The AccountManager instance managing the current accounts.
     */
    public void refresh(AccountManager accountManager) {
        GUI.refresh(kid.getAccountManager().getCurrentAccounts(), accountManager);
        addListener(GUI);
    }

    /**
     * Gets the ShowCurrentAccount GUI instance.
     *
     * @return The ShowCurrentAccount GUI instance.
     */
    public ShowCurrentAccount getGUI() {
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
