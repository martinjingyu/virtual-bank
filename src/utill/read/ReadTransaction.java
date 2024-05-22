package utill.read;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.StringReader;
import java.io.IOException;
import Entity.HistoryTransaction;
import Entity.HistoryTransactionList;

public class ReadTransaction {
    // 从文件读取
    public static void readTransactions(String fileName, HistoryTransactionList historyTransactionList) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            readFromBufferedReader(br, historyTransactionList);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    // 从字符串读取
    public static void readTransactionsFromString(String data, HistoryTransactionList historyTransactionList) {
        try (BufferedReader br = new BufferedReader(new StringReader(data))) {
            readFromBufferedReader(br, historyTransactionList);
        } catch (IOException e) {
            System.out.println("Error reading data: " + e.getMessage());
        }
    }

    // 从BufferedReader读取交易数据
    private static void readFromBufferedReader(BufferedReader br, HistoryTransactionList historyTransactionList) throws IOException {
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",", 4);  // 使用限制，确保只分割前两个逗号
            if (parts.length == 4) {
                String source = parts[0].trim();
                String destination = parts[1].trim();
                String amountStr = parts[2].trim();
                double amount = Double.parseDouble(amountStr);  // 直接将金额解析为double
                String date = parts[3].trim().replace(":", "/"); // 替换冒号为斜杠，正确处理日期和时间
                HistoryTransaction transaction = new HistoryTransaction(source, destination, amount, date);
                historyTransactionList.addTransaction(transaction);
            } else {
                System.out.println("Invalid transaction data: " + line);
            }
        }
    }

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
