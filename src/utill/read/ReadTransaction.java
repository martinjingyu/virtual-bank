package utill.read;

import Entity.HistoryTransaction;
import Entity.HistoryTransactionList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadTransaction {
    public static HistoryTransactionList readTransactions(String fileName) {
        HistoryTransactionList transactionList = new HistoryTransactionList();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String type = parts[0].trim();
                    double amount = Double.parseDouble(parts[1].trim());
                    String date = parts[2].trim();
                    HistoryTransaction transaction = new HistoryTransaction(type, amount, date);
                    transactionList.addTransaction(transaction);
                } else {
                    System.out.println("Invalid transaction data: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return transactionList;
    }

    public static void main(String[] args) {
        HistoryTransactionList transactionList = readTransactions("data/Kids/222/TransactionHistory.txt");
        if (transactionList != null) {
            System.out.println("Transaction History:");
            for (HistoryTransaction transaction : transactionList.getTransactions()) {
                System.out.println(transaction);
            }
        }
    }
}