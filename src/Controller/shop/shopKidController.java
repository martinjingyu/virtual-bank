package Controller.shop;

import Entity.*;
import Exceptions.InsufficientFundsException;
import GUI.shop_page.Shop_kid;
import GUI.task_page.Task_parent;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.util.ArrayList;
import java.util.List;

public class shopKidController {
    private Kids kid;
    private Shop_kid shop_kid;
    private MessageList messageList;
    private List<Product> selectedProductList;
    private AccountManager accountManager;
    private String selectedAccountName;

    public shopKidController(Kids kid) {
        this.kid = kid;
        this.messageList = kid.getMessagelist();
        this.selectedProductList = new ArrayList<>();
        this.accountManager = kid.getAccountManager();
    }

    /**
     * Initializes the view components with data from the model.
     * @param GUI The Shop_kid view that needs to be initialized.
     */
    public void initializeGUI(Shop_kid GUI) {
        populateAccounts(GUI.getAccountDropdown(), GUI.getCurrentAccountLabel());
        ProductList productList = kid.getProductList();
        GUI.createProductPanels(GUI.getProductsPanel(), productList);
        this.shop_kid = GUI;
    }

    /**
     * Populates the account dropdown and sets the initial display of the current account balance.
     * @param accountDropdown The JComboBox to populate.
     * @param currentAccountLabel The JLabel to display the current account balance.
     */
    public void populateAccounts(JComboBox<String> accountDropdown, JLabel currentAccountLabel) {
        List<CurrentAccount> accounts = kid.getAccountManager().getCurrentAccounts();
        for (CurrentAccount account : accounts) {
            accountDropdown.addItem(account.getName());
        }
        if (!accounts.isEmpty()) {
            accountDropdown.setSelectedItem(accounts.get(0).getName());
            setSelectedAccountName(accounts.get(0).getName(), currentAccountLabel);
        }
        accountDropdown.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                setSelectedAccountName((String) e.getItem(), currentAccountLabel);
            }
        });
    }

    /**
     * Sets the selected account name and updates the current account display.
     * @param accountName The name of the account to select.
     * @param currentAccountLabel The label to update with the current account balance.
     */
    public void setSelectedAccountName(String accountName, JLabel currentAccountLabel) {
        selectedAccountName = accountName;
        double balance = kid.getAccountManager().getCurrentAccountBalance(selectedAccountName);
        currentAccountLabel.setText(String.format("Current Account: $%9.2f", balance));
    }

    /**
     * Updates the selected product list and the total display when a product is selected or deselected.
     * @param product The product related to the selection event.
     * @param isSelected Indicates whether the product is selected.
     * @param selectedTotalLabel The label to update the selected total price on.
     */
    public void updateSelectedProductList(Product product, boolean isSelected, JLabel selectedTotalLabel) {
        double total = Double.parseDouble(selectedTotalLabel.getText().substring(17)); // Extracting the numeric part
        if(isSelected) {
            total += product.getPrice();
            selectedProductList.add(product);
        } else {
            total -= product.getPrice();
            selectedProductList.remove(product);
        }
        selectedTotalLabel.setText(String.format("Selected Total: $%9.2f", total));
    }

    /**
     * Handles the purchase of selected products.
     *
     * @param selectedTotalLabel the label displaying the total price of selected products
     * @param currentAccountLabel the label displaying the current account balance
     * @param toggleButtons the list of toggle buttons representing the product selection
     */
    public void buyProducts(JLabel selectedTotalLabel, JLabel currentAccountLabel, List<JRadioButton> toggleButtons) {
        if (selectedProductList.isEmpty()) {
            JOptionPane.showMessageDialog(this.shop_kid, "You haven't selected any products.", "No Products Selected!", JOptionPane.WARNING_MESSAGE);
            return;
        }

        double totalCost = Double.parseDouble(selectedTotalLabel.getText().substring(17));
        double currentBalance = Double.parseDouble(currentAccountLabel.getText().substring(18));

        if (currentBalance < totalCost) {
            JOptionPane.showMessageDialog(this.shop_kid, "Insufficient balance for this purchase.", "Insufficient Balance!", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int response = JOptionPane.showConfirmDialog(this.shop_kid, String.format("Total cost is $%.2f. Do you want to proceed with the purchase?", totalCost), "Confirm Purchase", JOptionPane.YES_NO_OPTION);
        if (response != JOptionPane.YES_OPTION) {
            return;
        }
        try {
            accountManager.withdrawFromCurrentAccount(selectedAccountName, totalCost);
            if (totalCost > 0.8 * currentBalance) {
                messageList.addShopMessage(totalCost);
            }
            JOptionPane.showMessageDialog(this.shop_kid, "Purchase Successful!");
            selectedProductList.clear();
            SwingUtilities.invokeLater(() -> {
                currentAccountLabel.setText(String.format("Current Account: $%8.2f", currentBalance - totalCost));
                selectedTotalLabel.setText("Selected Total: $     0.00");
            });
            for (JToggleButton button : toggleButtons) {
                button.setSelected(false);
            }
        } catch (InsufficientFundsException e) {
            JOptionPane.showMessageDialog(this.shop_kid, e.getMessage(), "Insufficient Balance!", JOptionPane.ERROR_MESSAGE);
        }
    }
}
