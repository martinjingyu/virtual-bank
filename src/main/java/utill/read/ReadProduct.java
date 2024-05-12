package utill.read;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import Entity.Product;
import Entity.ProductList;

public class ReadProduct {
    public static void readProducts(String fileName, ProductList productList) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
//                System.out.println(parts.length);
                if (parts.length == 2) {
                    String name = parts[0].trim();
                    double price = Double.parseDouble(parts[1].trim());
                    Product product = new Product(name, price);
                    productList.addProduct(product);
                } else {
                    System.out.println("Invalid product data: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        ProductList productList = new ProductList();
        readProducts("data/Kids/Martin/Product.txt", productList);

        // Print all products in the list
        System.out.println("All products:");
        for (Product product : productList.getAllProducts()) {
            System.out.println(product);
        }
    }
}
