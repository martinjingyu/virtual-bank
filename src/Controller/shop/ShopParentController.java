package Controller.shop;

import Entity.Kids;
import Entity.Product;
import Entity.ProductList;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ShopParentController {
    private Kids kid;
    private List<Product> boughtProduct;

    public ShopParentController(Kids kid) {
        this.kid = kid;
        this.boughtProduct = new ArrayList<>();
    }

    public Kids getKid() {
        return kid;
    }
    public void updateBoughtProductList(Product product, boolean isSelected) {
        if (isSelected) {
            boughtProduct.add(product);
//            System.out.println("11111111111");


        } else {
            boughtProduct.remove(product);
//            System.out.println("2222222");
//            for (Product products : boughtProduct) {
//                System.out.println(products);
//            }
        }
    }

    public void removeSelectedProduct(Product product) {
        kid.getSelectedList().removeProduct(product);
    }

    public void updateProducts(String name, String price) {
        try {
            double priceValue = Double.parseDouble(price);
            if (!name.matches("[a-zA-Z]+")) {
                JOptionPane.showMessageDialog(null, "Invalid price. Please enter a valid name.(hint:only characters.");
                throw new IllegalArgumentException("不合法的输入: 名称只能包含字母");
            }
            kid.getProductList().addProduct(new Product(name, priceValue));
            System.out.println("Product Name: " + name + ", Price: " + price);
            for (Product products : kid.getProductList().getAllProducts()) {
                System.out.println(products);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid price. Please enter a valid number.");
            throw new IllegalArgumentException("不合法的输入: 价格必须是数字");
        }
    }

    public void updateSelectedProduct(List<JCheckBox> checkBoxes) {
        for (Product product : boughtProduct) {
            kid.getSelectedList().removeProduct(product);
        }
        for (JCheckBox checkBox : checkBoxes) {
            checkBox.setSelected(false);
        }

    }
}
