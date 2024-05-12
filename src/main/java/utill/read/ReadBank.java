package utill.read;

import Entity.Bank;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadBank {
    public static Bank readBank(String fileName) {
        Bank bank = null;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            if ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 6) {
                    String name = parts[0].trim();
                    double savingGoal = Double.parseDouble(parts[1].trim());
                    double currentInterestRate = Double.parseDouble(parts[2].trim());
                    double savingInterestRate = Double.parseDouble(parts[3].trim());
                    double currentTotal = Double.parseDouble(parts[4].trim());
                    double savingTotal = Double.parseDouble(parts[5].trim());

                    // 创建银行对象
                    bank = new Bank(name, savingGoal, currentInterestRate, savingInterestRate,
                            currentTotal, savingTotal);
                } else {
                    System.out.println("Invalid bank data: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return bank;
    }

    public static void main(String[] args) {
        Bank bank = readBank("data/Kids/222/Bank.txt");
        if (bank != null) {
            System.out.println("Bank Information:");
            System.out.println("Name: " + bank.getName());
            System.out.println("Saving Goal: " + bank.getSavingGoal());
            System.out.println("Current Interest Rate: " + bank.getCurrentInterestRate());
            System.out.println("Saving Interest Rate: " + bank.getSavingInterestRate());
            System.out.println("Current Total: " + bank.getCurrentTotal());
            System.out.println("Saving Total: " + bank.getSavingTotal());
        }
    }
}

