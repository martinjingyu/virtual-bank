package Entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProductList {
    private Map<Product, Integer> productMap;

    public ProductList() {
        this.productMap = new HashMap<>();
    }

    public void addProduct(Product product, int quantity) {
        productMap.put(product, productMap.getOrDefault(product, 0) + quantity);
    }

    public void removeProduct(Product product, int quantity) {
        if (productMap.containsKey(product)) {
            int newQuantity = productMap.get(product) - quantity;
            if (newQuantity > 0) {
                productMap.put(product, newQuantity);
            } else {
                productMap.remove(product);
            }
        }
    }

    public List<Product> getAllProducts() {
        return productMap.keySet().stream().collect(Collectors.toList());
    }

    public Product getProductByName(String name) {
        return productMap.keySet().stream()
                .filter(product -> product.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    public Integer getQuantity(Product product) {
        return productMap.getOrDefault(product, 0);
    }

    // You can add more methods as needed

    public static void main(String[] args) {
        // Example usage
        ProductList productList = new ProductList();

        // Add some products
        productList.addProduct(new Product("Apple", 1.0), 10);
        productList.addProduct(new Product("Banana", 0.5), 5);
        productList.addProduct(new Product("Orange", 0.8), 8);

        // Get all products
        List<Product> allProducts = productList.getAllProducts();
        System.out.println("All products:");
        for (Product product : allProducts) {
            System.out.println(product + " - Quantity: " + productList.getQuantity(product));
        }

        // Get a product by name
        Product apple = productList.getProductByName("Apple");
        System.out.println("\nProduct found by name: " + apple + " - Quantity: " + productList.getQuantity(apple));
    }
}
