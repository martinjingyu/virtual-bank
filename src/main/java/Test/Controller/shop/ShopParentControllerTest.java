//package Controller.shop;
//
//import Entity.Kids;
//import Entity.Product;
//import Entity.ProductList;
//import GUI.RefreshListener;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.Mockito.*;
//
//public class ShopParentControllerTest {
//    private ShopParentController controller;
//    private Kids mockKid;
//    private RefreshListener mockRefreshListener;
//
//    @BeforeEach
//    public void setUp() {
//        mockKid = mock(Kids.class);
//        mockRefreshListener = mock(RefreshListener.class);
//        controller = new ShopParentController(mockKid);
//        controller.setRefreshListener(mockRefreshListener);
//    }
//
//    @Test
//    public void testUpdateBoughtProductList() {
//        Product product = new Product("Test Product", 10.0);
//        controller.updateBoughtProductList(product, true);
//        List<Product> boughtProduct = controller.getBoughtProduct();
//        assertEquals(1, boughtProduct.size());
//        assertEquals(product, boughtProduct.get(0));
//    }
//
//    @Test
//    public void testUpdateProducts() {
//        controller.updateProducts("Test Product", "10.0");
//        ProductList productList = mockKid.getProductList();
//        List<Product> allProducts = productList.getAllProducts();
//        assertEquals(1, allProducts.size());
//        assertEquals("Test Product", allProducts.get(0).getName());
//        assertEquals(10.0, allProducts.get(0).getPrice());
//    }
//
//    @Test
//    public void testUpdateProductsInvalidInput() {
//        assertThrows(IllegalArgumentException.class, () -> controller.updateProducts("123", "abc"));
//    }
//
//    // Add more test methods as needed
//}
