package utill.write;

import Entity.HistoryTransaction;
import utill.cryption.EncryptionUtil;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * The WriteHistoryTransaction class provides a method to write a list of history transactions to a file.
 */
public class WriteHistoryTransaction {

    /**
     * Writes a list of history transactions to a file.
     *
     * @param historyTransactionList the list of history transactions to be written
     * @param fileName               the name of the file to write the transactions to
     */
    public static void writeHistoryTransaction(List<HistoryTransaction> historyTransactionList, String fileName) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            StringBuilder buffer = new StringBuilder();
            for (HistoryTransaction historyTransaction : historyTransactionList) {
                // Append history transaction details to the buffer in a comma-separated format
                buffer.append(historyTransaction.getSource())
                        .append(",")
                        .append(historyTransaction.getDestination())
                        .append(",")
                        .append(historyTransaction.getAmount())
                        .append(",")
                        .append(historyTransaction.getDate())
                        .append(System.lineSeparator());
            }
            // Encrypt the entire buffer content and write to the file
            String encryptedData = EncryptionUtil.encrypt(buffer.toString());
            bw.write(encryptedData);
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        } catch (Exception ex) {
            System.out.println("Error encrypting data: " + ex.getMessage());
        }
    }
}

