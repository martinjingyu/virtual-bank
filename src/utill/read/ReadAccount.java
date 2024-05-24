package utill.read;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.StringReader;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.List;

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
    public static void readAccountsFromString(List<String> data, AccountManager accountManager) {
        String line;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy:MM:dd HH:mm:ss");
        try {
            BufferedReader br = new BufferedReader(new StringReader(data.get(0)));
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    // 第一个length == 2的是currentAccount
                    String name = parts[0].trim();
                    double balance = Double.parseDouble(parts[1].trim());
                    CurrentAccount currentAccount = new CurrentAccount(name, balance);
                    accountManager.addCurrentAccount(currentAccount);
                }
            }
        }
        catch (IOException e) {
            System.out.println("Error reading data: " + e.getMessage());
        }
        try {
            BufferedReader br = new BufferedReader(new StringReader(data.get(1)));
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    // 第一个length == 2的是currentAccount
                    String name = parts[0].trim();
                    formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
                    double balance = Double.parseDouble(parts[1].trim());
                    double interest = Double.parseDouble(parts[2].trim());
                    LocalDateTime startTime = LocalDateTime.parse(parts[3].trim(), formatter);
                    LocalDateTime endTime = LocalDateTime.parse(parts[4].trim(), formatter);
                    SavingAccount savingAccount = new SavingAccount(name, balance, interest, startTime, endTime);
                    accountManager.addSavingAccount(savingAccount);
                }
            }
        }
        catch (IOException e) {
            System.out.println("Error reading data: " + e.getMessage());
        }
        try {
            BufferedReader br = new BufferedReader(new StringReader(data.get(2)));
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    String userID = parts[0].trim();
                    double savingGoal = Double.parseDouble(parts[1].trim());
                    double interest1 = Double.parseDouble(parts[2].trim());
                    double interest2 = Double.parseDouble(parts[3].trim());
                    double interest3 = Double.parseDouble(parts[4].trim());
                    accountManager.setUserID(userID);
                    accountManager.setSavingGoal(savingGoal);
                    accountManager.setInterestRate(interest1,"15 days");
                    accountManager.setInterestRate(interest2,"1 month");
                    accountManager.setInterestRate(interest3,"3 months");
                }
            }
        }
        catch (IOException e) {
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

    }
}
