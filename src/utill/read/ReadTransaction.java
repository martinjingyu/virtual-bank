package utill.read;

import Entity.HistoryTransaction;
import Entity.HistoryTransactionList;
import Entity.TaskList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadTransaction {
    public static HistoryTransactionList readTransactions(String fileName, HistoryTransactionList historyTransactionList) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",", 4);  // 使用限制，确保只分割前两个逗号
                if (parts.length == 4) {
                    String Source = parts[0].trim();
                    String Destination = parts[1].trim();
                    String amountStr = parts[2].trim();
                    double amount = Double.parseDouble(amountStr);  // 直接将金额解析为double
                    String date = parts[3].trim().replace(":", "/"); // 替换冒号为斜杠，正确处理日期和时间
                    HistoryTransaction transaction = new HistoryTransaction(Source, Destination, amount, date);
                    historyTransactionList.addTransaction(transaction);
                } else {
                    System.out.println("Invalid transaction data: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return historyTransactionList; // 返回传入的 historyTransactionList，而不是新建一个
    }
    public static void main(String[] args) {
        HistoryTransactionList transactionList = new HistoryTransactionList();
        readTransactions("data/Kids/222/TransactionHistory.txt",transactionList);
        if (transactionList != null) {
            System.out.println("Transaction History:");
            for (HistoryTransaction transaction : transactionList.getAllTransactions()) {
                System.out.println(transaction);
            }
        }
        System.out.println(transactionList.getTransactionDetails());
    }
}