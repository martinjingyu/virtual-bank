package Controller.shop;

import Entity.Kids;
import Entity.Product;
import Entity.ProductList;
import GUI.RefreshListener;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The ShopParentController class manages the interactions between the UI and the data model for the parent's shop.
 */
public class ShopParentController {
    private Kids kid;
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
                JOptionPane.showMessageDialog(null, "Invalid price. Please enter a valid name. (hint: only characters).");
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
}
