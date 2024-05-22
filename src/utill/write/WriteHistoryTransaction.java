package utill.write;

import Entity.HistoryTransaction;
import Entity.HistoryTransactionList;
import Entity.Product;
import utill.cryption.EncryptionUtil;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class WriteHistoryTransaction {
    public static void writeHistoryTransaction(List<HistoryTransaction> historyTransactionList, String fileName) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            StringBuilder buffer = new StringBuilder();
            for (HistoryTransaction historyTransaction : historyTransactionList) {
                // 将产品对象的信息以逗号分隔的格式写入缓冲区
                buffer.append(historyTransaction.getSource()).append(",").append(historyTransaction.getDestination()).append(",").append(historyTransaction.getAmount()).append(",").append(historyTransaction.getAmount()).append(System.lineSeparator());
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
