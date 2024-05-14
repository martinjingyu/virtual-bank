package Controller.shop;

import Entity.*;
import Exceptions.InsufficientFundsException;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ShopController {
    private Kids kid;
    private Bank bank;
    private MessageList messageList;
    private List<Product> selectedProductList;

    public ShopController(Kids kid) {
        this.kid = kid;
        this.bank = kid.getBank();
        this.messageList = kid.getMessagelist();
        this.selectedProductList = new ArrayList<>();
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
        currentAccountLabel.setText(String.format("Current Account: $%9.2f", bank.getCurrentTotal()));
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

    public void buyProducts(JLabel selectedTotalLabel, JLabel currentAccountLabel,List<JRadioButton> toggleButtons,JComboBox<String> accountDropdown) {
        if (selectedProductList.isEmpty()) {
            JOptionPane.showMessageDialog(null, "You haven't selected any products.", "No Products Selected!", JOptionPane.WARNING_MESSAGE);
            return;
        }

        double totalCost = calculateSelectedTotal();
        try {
            bank.changeCurrent(-totalCost);
            if (totalCost > 0.8 * bank.getCurrentTotal()) {
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
