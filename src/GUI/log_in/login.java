/**

The login class is a JPanel that represents the login screen in the GUI of the bank application.
It contains a JLabel for displaying a text message and performs some initialization tasks upon instantiation.
This class performs the following steps:
Declares a private JLabel variable for displaying a text message.
Creates a constructor that initializes the JLabel with the text "this is login" and adds it to the panel.
Performs additional tasks, such as reading product data from a file and printing the list of products.
Includes a main method for testing purposes.
Note: This class assumes that the necessary dependencies and resources are available in the specified locations. **/

package GUI.log_in;

import javax.swing.*;

import Entity.ProductList;
import utill.read.ReadProduct;

public class login extends JPanel {
    private JLabel text;

    public login() {
        text = new JLabel("this is login");
        add(text);
        String productpath = "data/Kids/Martin/Product.txt";
        ProductList productList = new ProductList();
        ReadProduct.readProducts(productpath, productList);
        System.out.println(productList.getAllProducts());

    }

    public static void main(String[] args) {
        login test = new login();
    }
}
