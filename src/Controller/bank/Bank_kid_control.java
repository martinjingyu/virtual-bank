package Controller.bank;

import Entity.Kids;
import GUI.bank_page.ShowCurrentAccount;
import GUI.bank_page.ShowSavingAccount;
import GUI.bank_page.history_page;
import GUI.bank_page.Bank_kid;
import GUI.RefreshListener;
import utill.validate.Validate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The Bank_kid_control class manages the interaction between the bank GUI and the underlying data model for a child user.
 * It handles user actions, such as editing saving goals and viewing account information.
 */
public class Bank_kid_control implements RefreshListener {

    private static JTextField savingGoalTextField;
    private static int clickCount = 0;
    private String inputText;
    private Kids kid;
    private Bank_kid GUI;
    private HistoryController historyController;
    private JButton button_save;
    private final Color fontColor = new Color(49, 122, 232); // #317AE8
    private final Font font = new Font("Arial", Font.PLAIN, 20);
    private JFrame currentFrame; // Used to store the reference to the currently opened JFrame

    /**
     * Constructor to initialize the controller with the kid entity.
     *
     * @param kid The Kids entity representing the child user.
     */
    public Bank_kid_control(Kids kid) {
        this.kid = kid;
        this.historyController = new HistoryController(kid);
    }

    /**
     * Sets the GUI reference for this controller.
     *
     * @param GUI The Bank_kid GUI instance.
     */
    public void setGUI(Bank_kid GUI) {
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
     * Adds an ActionListener to the button to handle the editing of saving goals.
     *
     * @param button The JButton to which the listener is added.
     */
    public void addEditSavingGoalButtonListener(JButton button) {
        savingGoalTextField = new JTextField();
        savingGoalTextField.setBounds(630, 80, 75, 30);
        savingGoalTextField.setVisible(false);
        GUI.getMainFrame().add(savingGoalTextField);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dialog = new JDialog();
                dialog.setBounds(new Rectangle(400, 300, 300, 300));
                dialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
                dialog.setLayout(new GridBagLayout());
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.insets = new Insets(10, 10, 10, 10);

                JLabel jl1 = new JLabel("How much you want to save?");
                gbc.gridx = 0;
                gbc.gridy = 0;
                gbc.gridwidth = 2;
                dialog.add(jl1, gbc);

                JTextField savingGoalTextField = new JTextField(10);
                gbc.gridx = 0;
                gbc.gridy = 1;
                gbc.gridwidth = 2;
                dialog.add(savingGoalTextField, gbc);

                JButton button_save = new JButton("Save");
                gbc.gridx = 0;
                gbc.gridy = 2;
                gbc.gridwidth = 2;
                gbc.anchor = GridBagConstraints.CENTER;
                dialog.add(button_save, gbc);

                button_save.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String input = savingGoalTextField.getText();
                        try {
                            double value = Validate.validateNumber(input);
                            dialog.dispose();
                            kid.getAccountManager().setSavingGoal(value);
                            GUI.revalidate();
                            GUI.repaint();
                            GUI.getSavingGoals().setText(String.valueOf(value));
                            refreshUI();
                        } catch (Exception e1) {
                            savingGoalTextField.setText("");
                        }
                    }
                });

                dialog.pack();
                dialog.setVisible(true);
            }
        });
    }

    /**
     * Adds an ActionListener to the button to show the current account details.
     *
     * @param button The JButton to which the listener is added.
     */
    public void addCurrentAccountListener(JButton button) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ShowCurrentAccount showCurrentAccount = new ShowCurrentAccount();
                CurrentAccountController currentAccountController = new CurrentAccountController(kid, showCurrentAccount, false);
                openNewFrame(showCurrentAccount);
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
                SavingAccountController savingAccountController = new SavingAccountController(kid, showSavingAccount, false, GUI);
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

    /**
     * Adds an ActionListener to the button to review the transaction history.
     *
     * @param button The JButton to which the listener is added.
     */
    public void addReviewListener(JButton button) {
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
     * Refreshes the UI of the Bank_kid GUI.
     */
    @Override
    public void refreshUI() {
        // Implement the logic to refresh the Bank_kid GUI here
        // Example: GUI.refresh();
    }
}
