package utill.read;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.StringReader;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

import Entity.AccountManager;
import Entity.CurrentAccount;
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

        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length == 2) {
                String name = parts[0].trim();
                double balance = Double.parseDouble(parts[1].trim());
                CurrentAccount currentAccount = new CurrentAccount(name, balance);
                accountManager.addCurrentAccount(currentAccount);
            } else if (parts.length == 5) {
                String name = parts[0].trim();
                double balance = Double.parseDouble(parts[1].trim());
                double interest = Double.parseDouble(parts[2].trim());
                LocalDateTime startTime = LocalDateTime.parse(parts[3].trim(), formatter);
                LocalDateTime endTime = LocalDateTime.parse(parts[4].trim(), formatter);
                SavingAccount savingAccount = new SavingAccount(name, balance, interest, startTime, endTime);
                accountManager.addSavingAccount(savingAccount);
            } else {
                System.out.println("Invalid account data: " + line);
            }
        }
    }
}
