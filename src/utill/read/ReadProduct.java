package utill.read;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.StringReader;
import java.io.IOException;
import Entity.Product;
import Entity.ProductList;

/**
 * Utility class to read products from files or strings into a ProductList.
 */
public class ReadProduct {

    /**
     * Reads products from a file and adds them to the provided ProductList.
     *
     * @param fileName the name of the file to read products from
     * @param productList the ProductList to add the read products to
     */
    public static void readProducts(String fileName, ProductList productList) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            readFromBufferedReader(br, productList);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    /**
     * Reads products from a string and adds them to the provided ProductList.
     *
     * @param data the string data containing the products
     * @param productList the ProductList to add the read products to
     */
    public static void readProductsFromString(String data, ProductList productList) {
        try (BufferedReader br = new BufferedReader(new StringReader(data))) {
            readFromBufferedReader(br, productList);
        } catch (IOException e) {
            System.out.println("Error reading data: " + e.getMessage());
        }
    }

    /**
     * Reads products from a BufferedReader and adds them to the provided ProductList.
     *
     * @param br the BufferedReader to read products from
     * @param productList the ProductList to add the read products to
     * @throws IOException if an I/O error occurs
     */
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

    /**
     * Main method for testing purposes. Reads products from a string and prints them.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ProductList productList = new ProductList();
        // Assuming decrypted data is stored as a string somewhere, this is just an example
        String decryptedData = "Widget,19.99\nGadget,29.99\n";
        readProductsFromString(decryptedData, productList);

        // Print all products in the list
        System.out.println("All products:");
        for (Product product : productList.getAllProducts()) {
            System.out.println(product);
        }
    }
}
