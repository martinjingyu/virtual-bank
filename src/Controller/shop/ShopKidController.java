package Controller.shop;

import Entity.*;
import Exceptions.InsufficientFundsException;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ShopKidController {
    private Kids kid;
    private MessageList messageList;
    private List<Product> selectedProductList;
    private AccountManager accountManager;
    private String selectedAccountName;

    public ShopKidController(Kids kid) {
        this.kid = kid;
        this.messageList = kid.getMessagelist();
        this.selectedProductList = new ArrayList<>();
        this.accountManager = kid.getAccountManager();
    }

    public void setSelectedAccountName(String accountName,JLabel currentAccountLabel) {
        this.selectedAccountName = accountName;
        updateCurrentAccountDisplay(currentAccountLabel);
    }

    public Kids getKid(){return kid;}

    public double calculateSelectedTotal() {
        double total = 0;
        for (Product product : selectedProductList) {
            total += product.getPrice();
        }
        return total;
    }

    public void updateCurrentAccountDisplay(JLabel currentAccountLabel) {
        System.out.println(selectedAccountName);
        System.out.println(accountManager.getCurrentAccountBalance(selectedAccountName));
        currentAccountLabel.setText(String.format("Current Account: $%9.2f", accountManager.getCurrentAccountBalance(selectedAccountName)));
    }

    public void updateSelectedTotalDisplay(JLabel selectedTotalLabel) {
        selectedTotalLabel.setText(String.format("Selected Total: $%9.2f", calculateSelectedTotal()));
    }

    public void updateSelectedProductList(Product product, boolean isSelected,JLabel selectedTotalLabel) {
        if (isSelected) {
            selectedProductList.add(product);
            updateSelectedTotalDisplay(selectedTotalLabel);
        } else {
            selectedProductList.remove(product);
            updateSelectedTotalDisplay(selectedTotalLabel);
        }
    }


    public void buyProducts(JLabel selectedTotalLabel, JLabel currentAccountLabel,List<JRadioButton> toggleButtons) {
        if (selectedProductList.isEmpty()) {
            JOptionPane.showMessageDialog(null, "You haven't selected any products.", "No Products Selected!", JOptionPane.WARNING_MESSAGE);
            return;
        }

        double totalCost = calculateSelectedTotal();
        double currentBalance = accountManager.getCurrentAccountBalance(selectedAccountName);
        try {
            accountManager.withdrawFromCurrentAccount(selectedAccountName,totalCost);
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
