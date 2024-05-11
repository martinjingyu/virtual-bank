package utill.write;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import Entity.Product;
public class WriteSelectedProduct {
    public static void writeProducts(List<Product> selectedProducts, String fileName) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (Product product : selectedProducts) {
                // 将已选择产品的信息以逗号分隔的格式写入文件
                bw.write(product.getName() + "," + product.getPrice());
                bw.newLine(); // 写入新行
            }
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }
}