package utill.read;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.StringReader;
import java.io.IOException;
import Entity.HistoryTransaction;
import Entity.HistoryTransactionList;

/**
 * Utility class to read transactions from files or strings into a HistoryTransactionList.
 */
public class ReadTransaction {

    /**
     * Reads transactions from a file and adds them to the provided HistoryTransactionList.
     *
     * @param fileName the name of the file to read transactions from
     * @param historyTransactionList the HistoryTransactionList to add the read transactions to
     */
    public static void readTransactions(String fileName, HistoryTransactionList historyTransactionList) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            readFromBufferedReader(br, historyTransactionList);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    /**
     * Reads transactions from a string and adds them to the provided HistoryTransactionList.
     *
     * @param data the string data containing the transactions
     * @param historyTransactionList the HistoryTransactionList to add the read transactions to
     */
    public static void readTransactionsFromString(String data, HistoryTransactionList historyTransactionList) {
        try (BufferedReader br = new BufferedReader(new StringReader(data))) {
            readFromBufferedReader(br, historyTransactionList);
        } catch (IOException e) {
            System.out.println("Error reading data: " + e.getMessage());
        }
    }

    /**
     * Reads transactions from a BufferedReader and adds them to the provided HistoryTransactionList.
     *
     * @param br the BufferedReader to read transactions from
     * @param historyTransactionList the HistoryTransactionList to add the read transactions to
     * @throws IOException if an I/O error occurs
     */
    private static void readFromBufferedReader(BufferedReader br, HistoryTransactionList historyTransactionList) throws IOException {
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",", 4);  // Use a limit to ensure only the first three commas are split
            if (parts.length == 4) {
                String source = parts[0].trim();
                String destination = parts[1].trim();
                String amountStr = parts[2].trim();
                double amount = Double.parseDouble(amountStr);  // Directly parse the amount as double
                String date = parts[3].trim();
                HistoryTransaction transaction = new HistoryTransaction(source, destination, amount, date);
                historyTransactionList.addTransaction(transaction);
            } else {
                System.out.println("Invalid transaction data: " + line);
            }
        }
    }

    /**
     * Main method for testing purposes. Reads transactions from a file and prints them.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        HistoryTransactionList transactionList = new HistoryTransactionList();
        readTransactions("data/Kids/222/TransactionHistory.txt", transactionList);

        // Print all transactions in the list
        System.out.println("All transactions:");
        for (HistoryTransaction transaction : transactionList.getAllTransactions()) {
            System.out.println(transaction);
        }
    }
}
