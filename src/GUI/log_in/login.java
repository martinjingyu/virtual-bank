package GUI.log_in;
import javax.swing.*;

import Entity.ProductList;
import utill.read.ReadProduct;

public class login extends JPanel{
    private  JLabel text;
    public login(){
        text = new JLabel("this is login");
        add(text);
        String productpath = "data/Kids/Martin/Product.txt";
        ProductList productList = new ProductList();
        ReadProduct.readProducts(productpath,productList);
        System.out.println(productList.getAllProducts());


    }
    public static void main(String[] args){
        login test = new login();
    }
}
