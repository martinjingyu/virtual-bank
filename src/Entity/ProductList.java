package Entity;

import java.util.ArrayList;
import java.util.List;

public class ProductList {
    private List<Product> productList;

    public ProductList() {
        this.productList = new ArrayList<>();
    }

    public void addProduct(Product product) {
        productList.add(product);
    }

    public void removeProduct(Product product) {
        productList.remove(product);
    }

    public List<Product> getAllProducts() {
        return productList;
    }

    public Product getProductByName(String name) {
        for (Product product : productList) {
            if (product.getName().equals(name)) {
                return product;
            }
        }
        return null;
    }

    // You can add more methods as needed

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