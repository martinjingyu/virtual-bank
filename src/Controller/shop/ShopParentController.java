package Controller.shop;

import Entity.CurrentAccount;
import Entity.Kids;
import Entity.Product;
import Entity.ProductList;
import GUI.RefreshListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

/**
 * The ShopParentController class manages the interactions between the UI and the data model for the parent's shop.
 */
public class ShopParentController {
    private Kids kid;
    private List<Product> boughtProduct;
    private String selectedAccountName;
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
        // 设置选中的账户名称
        // 更新当前账户显示标签
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
     * Updates the products based on the provided name and price.
     *
     * @param name the name of the product
     * @param price the price of the product
     */
    public void updateProducts(String name, String price) {
        try {
            double priceValue = Double.parseDouble(price);
            if (!name.matches("[a-zA-Z]+")) {
                JOptionPane.showMessageDialog(null, "Invalid name. Please enter a valid name. (hint: only characters).");
                throw new IllegalArgumentException("Invalid input: Name can only contain letters.");
            }
            kid.getProductList().addProduct(new Product(name, priceValue));
            System.out.println("Product Name: " + name + ", Price: " + price);
            for (Product products : kid.getProductList().getAllProducts()) {
                System.out.println(products);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid price. Please enter a valid number.");
            throw new  IllegalArgumentException("Invalid input: Price must be a number.");
        }
    }

    /**
     * Updates the selected products and refreshes the UI.
     *
     * @param checkBoxes the list of check boxes representing the product selection
     */
    public void updateSelectedProduct(List<JCheckBox> checkBoxes) {
        for (Product product : boughtProduct) {
            kid.getSelectedList().removeProduct(product);
        }
        for (JCheckBox checkBox : checkBoxes) {
            checkBox.setSelected(false);
        }
        if (refreshListener != null) {
            refreshListener.refreshUI();
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
