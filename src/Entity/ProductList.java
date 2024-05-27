package Entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of products with functionalities to add, remove, and retrieve products.
 */
public class ProductList {
    private List<Product> productList;

    /**
     * Constructs an empty ProductList.
     */
    public ProductList() {
        this.productList = new ArrayList<>();
    }

    /**
     * Adds a product to the list.
     *
     * @param product the product to be added
     */
    public void addProduct(Product product) {
        productList.add(product);
    }

    /**
     * Removes a product from the list.
     *
     * @param product the product to be removed
     */
    public void removeProduct(Product product) {
        productList.remove(product);
    }

    /**
     * Returns all products in the list.
     *
     * @return a list of all products
     */
    public List<Product> getAllProducts() {
        return productList;
    }

    /**
     * Retrieves a product by its name.
     *
     * @param name the name of the product to retrieve
     * @return the product with the specified name, or null if not found
     */
    public Product getProductByName(String name) {
        for (Product product : productList) {
            if (product.getName().equals(name)) {
                return product;
            }
        }
        return null;
    }

    /**
     * Main method for example usage.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Example usage
        ProductList productList = new ProductList();

        // Add some products
        productList.addProduct(new Product("Apple", 1.0));
        productList.addProduct(new Product("Banana", 0.5));
        productList.addProduct(new Product("Orange", 0.8));

        // Get all products
        List<Product> allProducts = productList.getAllProducts();
        System.out.println("All products:");
        for (Product product : allProducts) {
            System.out.println(product);
        }

        // Get a product by name
        Product apple = productList.getProductByName("Apple");
        System.out.println("\nProduct found by name: " + apple);
    }
}
