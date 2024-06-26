package Controller.shop;

import Entity.CurrentAccount;
import Entity.Kids;
import Entity.Product;
import Entity.ProductList;
import GUI.RefreshListener;
import GUI.shop_page.Shop_kid;
import GUI.shop_page.Shop_parent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import static java.sql.Types.NULL;

/**
 * The ShopParentController class manages the interactions between the UI and the data model for the parent's shop.
 */
public class ShopParentController {
    private Kids kid;
    private Shop_parent shop_parent;
    private List<Product> boughtProduct;
    private RefreshListener refreshListener;

    /**
     * Constructs a ShopParentController for the specified kid.
     *
     * @param kid the kid associated with this controller
     */
    public ShopParentController(Kids kid) {
        this.kid = kid;
        this.boughtProduct = new ArrayList<>();
    }

    /**
     * Sets the refresh listener for the UI.
     *
     * @param listener the refresh listener to set
     */
    public void setRefreshListener(RefreshListener listener) {
        this.refreshListener = listener;
    }

    /**
     * Initializes the view components with data from the model.
     * @param GUI The Shop_kid view that needs to be initialized.
     */
    public void initializeGUI(Shop_parent GUI) {
        this.shop_parent = GUI;
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
     * Adds a refresh listener for the UI.
     *
     * @param listener the refresh listener to add
     */
    public void addListener(RefreshListener listener) {
        this.refreshListener = listener;
    }

    /**
     * Initializes the account dropdown with account names and sets the initial selected account.
     *
     * @param accountDropdown the JComboBox to populate with account names
     * @param currentAccountLabel the label to update with the selected account's balance
     */
    public void initializeAccountDropdown(JComboBox<String> accountDropdown, JLabel currentAccountLabel) {
        List<CurrentAccount> accounts = kid.getAccountManager().getCurrentAccounts();
        for (CurrentAccount account : accounts) {
            accountDropdown.addItem(account.getName());
        }

        accountDropdown.setFont(new Font("Arial", Font.PLAIN, 13));

        if (!accounts.isEmpty()) {
            String firstAccountName = accounts.get(0).getName();
            accountDropdown.setSelectedItem(firstAccountName);
            setSelectedAccountName(firstAccountName, currentAccountLabel);
        }

        accountDropdown.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String selectedAccount = (String) e.getItem();
                    setSelectedAccountName(selectedAccount, currentAccountLabel);
                }
            }
        });
    }

    /**
     * Sets the selected account name and updates the current account display.
     *
     * @param accountName the name of the account to select
     * @param currentAccountLabel the label to update with the current account balance
     */
    public void setSelectedAccountName(String accountName, JLabel currentAccountLabel) {
        double currentBalance = kid.getAccountManager().getCurrentAccountBalance(accountName);
        currentAccountLabel.setText(String.format("$%.2f", currentBalance));
    }


    /**
     * Updates the bought product list based on the selection state.
     *
     * @param product the product to update
     * @param isSelected whether the product is selected or not
     */
    public void updateBoughtProductList(Product product, boolean isSelected) {
        if (isSelected) {
            boughtProduct.add(product);
        } else {
            boughtProduct.remove(product);
        }
    }

    /**
     * Confirms and submits the products after user confirmation.
     *
     * @param checkBoxes the list of check boxes representing the product selection
     */
    public void confirmAndSubmitProducts(List<JCheckBox> checkBoxes) {
        if (boughtProduct.isEmpty()) {
            JOptionPane.showMessageDialog(this.shop_parent, "You haven't selected any products.", "No Products Selected", JOptionPane.WARNING_MESSAGE);
            return;
        }

        StringBuilder message = new StringBuilder("You have selected the following products:\n");
        for (Product product : boughtProduct) {
            message.append(product.getName()).append("\n");
        }
        message.append("Are you sure you want to submit these products?");

        int response = JOptionPane.showConfirmDialog(this.shop_parent, message.toString(), "Confirm Submission", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
        if (response == JOptionPane.YES_OPTION) {
            for (Product product : boughtProduct) {
                kid.getSelectedList().removeProduct(product);
            }
            for (JCheckBox checkBox : checkBoxes) {
                checkBox.setSelected(false);
            }
            boughtProduct.clear(); // Clear the boughtProduct list after submission

            if (refreshListener != null) {
                refreshListener.refreshUI();
            }

        }
    }

    /**
     * Updates the products based on the provided name and price.
     *
     * @param name the name of the product
     * @param price the price of the product
     */
    public void updateProducts(String name, String price ,JTextField nameTextField, JTextField priceTextField) {
        if (name.trim().equals("Name should only contain characters (A-Z, a-z)") || price.trim().equals("Price should be a valid number")) {
            JOptionPane.showMessageDialog(this.shop_parent, "Both name and price must be filled out.", "Input Required", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            double priceValue = Double.parseDouble(price);
            if (!name.matches("[a-zA-Z]+")) {
                JOptionPane.showMessageDialog(this.shop_parent, "Invalid name. Please enter a valid name. (hint: only characters).");
                throw new IllegalArgumentException("Invalid input: Name can only contain letters.");
            }
            if (name.length() > 10) {
                JOptionPane.showMessageDialog(this.shop_parent, "The product name cannot be longer than 10 characters.");
                return;
            }
            boolean productExists = false;
            for (Product product : kid.getProductList().getAllProducts()) {
                if (product.getName().equalsIgnoreCase(name)) {
                    productExists = true;
                    break;
                }
            }
            if (productExists) {
                JOptionPane.showMessageDialog(this.shop_parent, "This product already exists. Please enter a different name.");
                return;
            }
            String message = String.format("Are you sure you want to submit %s - $%.2f?", name, priceValue);
            int response = JOptionPane.showConfirmDialog(this.shop_parent, message, "Confirm Submission", JOptionPane.YES_NO_OPTION);
            if (response != JOptionPane.YES_OPTION) {
                return;
            }
            kid.getProductList().addProduct(new Product(name, priceValue));
            System.out.println("Product Name: " + name + ", Price: " + price);
            for (Product products : kid.getProductList().getAllProducts()) {
                System.out.println(products);
            }

            setupFocusListener(nameTextField, "Name should only contain characters (A-Z, a-z)");
            setupFocusListener(priceTextField, "Price should be a valid number");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this.shop_parent, "Invalid price. Please enter a valid number.");
            throw new  IllegalArgumentException("Invalid input: Price must be a number.");
        }
    }

    /**
     * Sets up focus listeners for text fields to handle placeholder text.
     *
     * @param textField the text field to set up the listener for
     * @param placeholderText the placeholder text to display
     */
    public void setupFocusListener(JTextField textField, String placeholderText) {

        textField.setText(placeholderText);
        textField.setForeground(Color.GRAY);
        textField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals(placeholderText)) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setForeground(Color.GRAY);
                    textField.setText(placeholderText);
                }
            }
        });
    }
}
