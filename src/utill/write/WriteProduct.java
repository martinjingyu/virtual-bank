package utill.write;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import Entity.ProductList;
import Entity.Product;

public class WriteProduct {
    public static void writeProducts(List<Product> productList, String fileName) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            List<Product> products = productList;
            for (Product product : products) {
                // 将产品对象的信息以逗号分隔的格式写入文件
                bw.write(product.getName() + "," + product.getPrice());
                bw.newLine(); // 写入新行
            }
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }
}