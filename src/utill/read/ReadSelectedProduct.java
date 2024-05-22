package utill.read;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.StringReader;
import java.io.IOException;
import Entity.Product;
import Entity.ProductList;

public class ReadSelectedProduct {
    // 从文件读取
    public static void readProducts(String fileName, ProductList productList) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            readFromBufferedReader(br, productList);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    // 从字符串读取
    public static void readProductsFromString(String data, ProductList productList) {
        try (BufferedReader br = new BufferedReader(new StringReader(data))) {
            readFromBufferedReader(br, productList);
        } catch (IOException e) {
            System.out.println("Error reading data: " + e.getMessage());
        }
    }

    // 从BufferedReader读取产品数据
    private static void readFromBufferedReader(BufferedReader br, ProductList productList) throws IOException {
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length == 2) {
                String name = parts[0].trim();
                double price = Double.parseDouble(parts[1].trim());
                Product product = new Product(name, price);
                productList.addProduct(product);
            } else {
                System.out.println("Invalid product data: " + line);
            }
        }
    }

    public static void main(String[] args) {
        ProductList selectedProductList = new ProductList();
        readProducts("data/Kids/222/SelectedProduct.txt", selectedProductList);

        // Print all products in the list
        System.out.println("All products:");
        for (Product product : selectedProductList.getAllProducts()) {
            System.out.println(product);
        }
    }
}
