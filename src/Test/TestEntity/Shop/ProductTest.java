package Test.TestEntity.Shop;

import Entity.Product;
import org.junit.Test;
import static org.junit.Assert.*;

public class ProductTest {

    @Test
    public void testGetName() {
        Product product = new Product("TestProduct", 10.0);
        assertEquals("TestProduct", product.getName());
    }

    @Test
    public void testSetName() {
        Product product = new Product("TestProduct", 10.0);
        product.setName("NewProduct");
        assertEquals("NewProduct", product.getName());
    }

    @Test
    public void testGetPrice() {
        Product product = new Product("TestProduct", 10.0);
        assertEquals(10.0, product.getPrice(), 0.001);
    }

    @Test
    public void testSetPrice() {
        Product product = new Product("TestProduct", 10.0);
        product.setPrice(20.0);
        assertEquals(20.0, product.getPrice(), 0.001);
    }

    @Test
    public void testToString() {
        Product product = new Product("TestProduct", 10.0);
        assertEquals("Product{name='TestProduct', price=10.0}", product.toString());
    }
}
