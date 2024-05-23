package Controller.shop;

import Entity.*;
import Exceptions.InsufficientFundsException;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The shopKidController class manages the interactions between the UI and the data model for the kid's shop.
 */
public class shopKidController {
    private Kids kid;
    private MessageList messageList;
    private List<Product> selectedProductList;
    private AccountManager accountManager;
    private String selectedAccountName;

    /**
     * Constructs a shopKidController for the specified kid.
     *
     * @param kid the kid associated with this controller
     */
    public shopKidController(Kids kid) {
        this.kid = kid;
        this.messageList = kid.getMessagelist();
        this.selectedProductList = new ArrayList<>();
        this.accountManager = kid.getAccountManager();

    }

    /**
     * Sets the selected account name and updates the current account display.
     *
     * @param accountName the name of the account to select
     * @param currentAccountLabel the label to update with the current account balance
     */
    public void setSelectedAccountName(String accountName, JLabel currentAccountLabel) {
        this.selectedAccountName = accountName;
        updateCurrentAccountDisplay(currentAccountLabel);
    }

    /**
     * Returns the kid associated with this controller.
     *
     * @return the kid associated with this controller
     */
    public Kids getKid() {
        return kid;
    }

    /**
     * Calculates the total price of the selected products.
     *
     * @return the total price of the selected products
     */
    public double calculateSelectedTotal() {
        double total  = 0;
        for (Product product : selectedProductList) {
            total += product.getPrice();
        }
        return total;
    }

    /**
     * Updates the current account display with the current balance.
     *
     * @param currentAccountLabel the label to update
     */
    public void updateCurrentAccountDisplay(JLabel currentAccountLabel) {
        System.out.println(selectedAccountName);
        System.out.println(accountManager.getCurrentAccountBalance(selectedAccountName));
        currentAccountLabel.setText(String.format("Current Account: $%9.2f", accountManager.getCurrentAccountBalance(selectedAccountName)));
    }

    /**
     * Updates the selected total display with the total price of selected products.
     *
     * @param selectedTotalLabel the label to update
     */
    public void updateSelectedTotalDisplay(JLabel selectedTotalLabel) {
        selectedTotalLabel.setText(String.format("Selected Total: $%9.2f", calculateSelectedTotal()));
    }

    /**
     * Updates the list of selected products and updates the total display.
     *
     * @param product the product to add or remove
     * @param isSelected whether the product is selected or not
     * @param selectedTotalLabel the label to update
     */
    public void updateSelectedProductList(Product product, boolean isSelected, JLabel selectedTotalLabel) {
        if (isSelected) {
            selectedProductList.add(product);
            updateSelectedTotalDisplay(selectedTotalLabel);
        } else {
            selectedProductList.remove(product);
            updateSelectedTotalDisplay(selectedTotalLabel);
        }
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
            JOptionPane.showMessageDialog(null, "You haven't selected any products.", "No Products Selected!", JOptionPane.WARNING_MESSAGE);
            return;
        }

        double totalCost = calculateSelectedTotal();
        double currentBalance = accountManager.getCurrentAccountBalance(selectedAccountName);

        if (currentBalance < totalCost) {
            JOptionPane.showMessageDialog(null, "Insufficient balance for this purchase.", "Insufficient Balance!", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int response = JOptionPane.showConfirmDialog(null, String.format("Total cost is $%.2f. Do you want to proceed with the purchase?", totalCost), "Confirm Purchase", JOptionPane.YES_NO_OPTION);
        if (response != JOptionPane.YES_OPTION) {
            return;
        }
        try {
            accountManager.withdrawFromCurrentAccount(selectedAccountName, totalCost);
            if (totalCost > 0.8 * currentBalance) {
                messageList.addShopMessage(totalCost);
            }
            JOptionPane.showMessageDialog(null, "Purchase Successful!");
            selectedProductList.clear();
            updateSelectedTotalDisplay(selectedTotalLabel);
            updateCurrentAccountDisplay(currentAccountLabel);
            for (JToggleButton button : toggleButtons) {
                button.setSelected(false);
            }
        } catch (InsufficientFundsException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Insufficient Balance!", JOptionPane.ERROR_MESSAGE);
        }
    }
}
