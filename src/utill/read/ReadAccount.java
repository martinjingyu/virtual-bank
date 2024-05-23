package utill.read;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.StringReader;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

import Entity.AccountManager;
import Entity.CurrentAccount;
import Entity.HistoryTransactionList;
import Entity.SavingAccount;

public class ReadAccount {
    // 从文件读取
    public static void readAccount(String fileName, AccountManager accountManager) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            readFromBufferedReader(br, accountManager);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    // 从字符串读取
    public static void readAccountsFromString(String data, AccountManager accountManager) {
        try (BufferedReader br = new BufferedReader(new StringReader(data))) {
            readFromBufferedReader(br, accountManager);
        } catch (IOException e) {
            System.out.println("Error reading data: " + e.getMessage());
        }
    }

    // 从BufferedReader读取账户数据
    private static void readFromBufferedReader(BufferedReader br, AccountManager accountManager) throws IOException {
        String line;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy:MM:dd HH:mm:ss");
        boolean isFirstCurrentAccountFound = false;

        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length == 2) {
                if (!isFirstCurrentAccountFound) {
                    // 第一个length == 2的是currentAccount
                    String name = parts[0].trim();
                    double balance = Double.parseDouble(parts[1].trim());
                    CurrentAccount currentAccount = new CurrentAccount(name, balance);
                    accountManager.addCurrentAccount(currentAccount);
                } else {
                    // 第二个length == 2的是user
                    String userID = parts[0].trim();
                    double savingGoal = Double.parseDouble(parts[1].trim());
                    accountManager.setUserID(userID);
                    accountManager.setSavingGoal(savingGoal);
                }
            } else if (parts.length == 5) {
                // SavingAccount
                String name = parts[0].trim();
                formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
                double balance = Double.parseDouble(parts[1].trim());
                double interest = Double.parseDouble(parts[2].trim());
                LocalDateTime startTime = LocalDateTime.parse(parts[3].trim(), formatter);
                LocalDateTime endTime = LocalDateTime.parse(parts[4].trim(), formatter);
                SavingAccount savingAccount = new SavingAccount(name, balance, interest, startTime, endTime);
                accountManager.addSavingAccount(savingAccount);
                isFirstCurrentAccountFound = true;
            } else {
                System.out.println("Invalid account data: " + line);
            }
        }
    }

    public static void main(String[] args) {
        AccountManager accountManager = new AccountManager(new HistoryTransactionList());
        String data = "CA1,10.0\nCA2,20.0\nCA3,15.0\nSA1,10.0,3.0,2024:01:10 12:12:40,2024:10:10 12:12:40\nSA2,10.0,3.0,2024:01:10 12:12:40,2024:10:10 12:12:40\nSA3,10.0,3.0,2024:01:10 12:12:40,2024:10:10 12:12:40\nSA4,10.0,3.0,2024:01:10 12:12:40,2024:02:10 12:14:40\nMartin,200.0";
        readAccountsFromString(data, accountManager);

        // 打印结果以验证读取是否正确
        System.out.println("Current Accounts: " + accountManager.getCurrentAccounts());
        System.out.println("Saving Accounts: " + accountManager.getSavingAccounts());
        System.out.println("User ID: " + accountManager.getUserID());
        System.out.println("Saving Goal: " + accountManager.getSavingGoal());
    }
}
