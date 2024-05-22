package Test.Testutill;

import Entity.Product;
import Entity.ProductList;
import org.junit.Test;
import utill.read.ReadProduct;

import static org.junit.Assert.*;

public class TestReadProduct {

    @Test
    public void testReadProductsFromString() {
        String testData = "Widget,19.99\nGadget,29.99\n";  // 测试数据
        ProductList productList = new ProductList();  // 创建一个新的产品列表

        ReadProduct.readProductsFromString(testData, productList);  // 调用方法解析字符串到产品列表

        assertEquals("Number of products parsed incorrect", 2, productList.getAllProducts().size());  // 检查是否解析了两个产品
        Product firstProduct = productList.getAllProducts().get(0);
        Product secondProduct = productList.getAllProducts().get(1);

        // 检查第一个产品的详细信息
        assertEquals("Widget", firstProduct.getName());
        assertEquals(19.99, firstProduct.getPrice(), 0.001);  // 0.001是delta，允许的误差范围

        // 检查第二个产品的详细信息
        assertEquals("Gadget", secondProduct.getName());
        assertEquals(29.99, secondProduct.getPrice(), 0.001);
    }
}
