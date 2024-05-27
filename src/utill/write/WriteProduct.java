package utill.write;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import Entity.Product;
import utill.cryption.EncryptionUtil; // 导入加密工具类


public class WriteProduct {
    public static void writeProducts(List<Product> productList, String fileName) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            StringBuilder buffer = new StringBuilder();
            for (Product product : productList) {
                // 将产品对象的信息以逗号分隔的格式写入缓冲区
                buffer.append(product.getName()).append(",").append(product.getPrice()).append(System.lineSeparator());
            }
            // 加密整个缓冲区的内容并写入文件
            String encryptedData = EncryptionUtil.encrypt(buffer.toString());
            bw.write(encryptedData);
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        } catch (Exception ex) {
            System.out.println("Error encrypting data: " + ex.getMessage());
        }
    }
}