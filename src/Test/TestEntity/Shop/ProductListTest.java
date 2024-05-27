package Test.TestEntity.Shop;

import Entity.Product;
import Entity.ProductList;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Test class for the ProductList entity.
 */
public class ProductListTest {
    private ProductList productList;
    private Product product1;
    private Product product2;
    private Product product3;

    /**
     * Sets up the test environment.
     */
    @Before
    public void setUp() {
        productList = new ProductList();
        product1 = new Product("Apple", 1.0);
        product2 = new Product("Banana", 0.5);
        product3 = new Product("Orange", 0.8);
    }

    /**
     * Tests the addProduct method.
     */
    @Test
    public void testAddProduct() {
        productList.addProduct(product1);
        assertTrue(productList.getAllProducts().contains(product1));
    }

    /**
     * Tests the removeProduct method.
     */
    @Test
    public void testRemoveProduct() {
        productList.addProduct(product1);
        productList.removeProduct(product1);
        assertFalse(productList.getAllProducts().contains(product1));
    }

    /**
     * Tests the getAllProducts method.
     */
    @Test
    public void testGetAllProducts() {
        productList.addProduct(product1);
        productList.addProduct(product2);
        productList.addProduct(product3);
        List<Product> allProducts = productList.getAllProducts();
        assertEquals(3, allProducts.size());
        assertTrue(allProducts.contains(product1));
        assertTrue(allProducts.contains(product2));
        assertTrue(allProducts.contains(product3));
    }

    /**
     * Tests the getProductByName method.
     */
    @Test
    public void testGetProductByName() {
        productList.addProduct(product1);
        productList.addProduct(product2);
        productList.addProduct(product3);
        Product foundProduct = productList.getProductByName("Banana");
        assertEquals(product2, foundProduct);
    }

    /**
     * Tests the getProductByName method when the product is not found.
     */
    @Test
    public void testGetProductByNameNotFound() {
        productList.addProduct(product1);
        Product foundProduct = productList.getProductByName("NonExistingProduct");
        assertNull(foundProduct);
    }
}
