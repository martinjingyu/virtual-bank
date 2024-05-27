package Entity;

/**
 * Represents a product with a name and a price.
 */
public class Product {
    private String name;
    private double price;

    /**
     * Constructs a Product with the specified name and price.
     *
     * @param name the name of the product
     * @param price the price of the product
     */
    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    /**
     * Gets the name of the product.
     *
     * @return the name of the product
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the product.
     *
     * @param name the new name of the product
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the price of the product.
     *
     * @return the price of the product
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the price of the product.
     *
     * @param price the new price of the product
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Returns a string representation of the product.
     *
     * @return a string representation of the product
     */
    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
