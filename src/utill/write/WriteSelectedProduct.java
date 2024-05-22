package utill.write;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import Entity.Product;
import utill.cryption.EncryptionUtil;

public class WriteSelectedProduct {
    public static void writeProducts(List<Product> selectedProducts, String fileName) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            StringBuilder buffer = new StringBuilder();
            for (Product product : selectedProducts) {
                // 将已选择产品的信息以逗号分隔的格式写入文件
                buffer.append(product.getName() + "," + product.getPrice()).append(System.lineSeparator());
            }
            String encryptedData = EncryptionUtil.encrypt(buffer.toString());
            bw.write(encryptedData);
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
        catch (Exception ex) {
            System.out.println("Error encrypting data: " + ex.getMessage());
        }
    }
}