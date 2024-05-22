package utill.read;

import Entity.Product;
import Entity.ProductList;
import org.junit.Test;
import utill.read.ReadSelectedProduct;

import static org.junit.Assert.*;

public class TestReadSelectedProduct {

    @Test
    public void testReadProductsFromString() {
        String testData = "Apple,3.0\nbee,1.0\nT-shirt,5.0\n";  // 测试数据
        ProductList productList = new ProductList();  // 创建一个新的产品列表

        ReadSelectedProduct.readProductsFromString(testData, productList);  // 调用方法解析字符串到产品列表

        assertEquals("Number of products parsed incorrect", 3, productList.getAllProducts().size());  // 检查是否解析了三个产品
        Product firstProduct = productList.getAllProducts().get(0);
        Product secondProduct = productList.getAllProducts().get(1);
        Product thirdProduct = productList.getAllProducts().get(2);

        // 检查第一个产品的详细信息
        assertEquals("Apple", firstProduct.getName());
        assertEquals(3.0, firstProduct.getPrice(), 0.001);  // 0.001是delta，允许的误差范围

        // 检查第二个产品的详细信息
        assertEquals("bee", secondProduct.getName());
        assertEquals(1.0, secondProduct.getPrice(), 0.001);

        // 检查第三个产品的详细信息
        assertEquals("T-shirt", thirdProduct.getName());
        assertEquals(5.0, thirdProduct.getPrice(), 0.001);
    }
}