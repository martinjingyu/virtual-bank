package utill.read;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.List;

import Entity.*;

public class ReadAccount {
    public static void readAccount(List<String> fileName, AccountManager accountManager) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName.get(0)))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",",2);
//                System.out.println(parts.length);
                if (parts.length == 2) {
                    String name = parts[0].trim();
                    double balance = Double.parseDouble(parts[1].trim());
                    CurrentAccount currentAccount = new CurrentAccount(name,balance);
                    accountManager.addCurrentAccount(currentAccount);
                } else {
                    System.out.println("Invalid product data: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy:MM:dd HH:mm:ss");
        try (BufferedReader br = new BufferedReader(new FileReader(fileName.get(1)))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",",5);
//                System.out.println(parts.length);
                if (parts.length == 5) {
                    String name = parts[0].trim();
                    double balance = Double.parseDouble(parts[1].trim());
                    double interest = Double.parseDouble(parts[2].trim());
                    LocalDateTime startTime = LocalDateTime.parse(parts[3].trim(), formatter);
                    LocalDateTime endTime = LocalDateTime.parse(parts[4].trim(), formatter);


                    SavingAccount savingAccount = new SavingAccount(name,balance,interest,startTime,endTime);
                    accountManager.addSavingAccount(savingAccount);
                } else {
                    System.out.println("Invalid product data: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        try (BufferedReader br = new BufferedReader(new FileReader(fileName.get(2)))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",",2);
//                System.out.println(parts.length);
                if (parts.length == 2) {
                    String name = parts[0].trim();
                    double saving_goal = Double.parseDouble(parts[1].trim());
                    accountManager.setUserID(name);
                    accountManager.setSavingGoal(saving_goal);

                } else {
                    System.out.println("Invalid product data: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
