package utill.write;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import Entity.Bank;

public class WriteBank {
    public static void writeBank(Bank bank, String fileName) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            // 将银行对象的信息以特定格式写入文件
            bw.write(bank.getName() + "," + bank.getSavingGoal() + "," +
                    bank.getCurrentInterestRate() + "," + bank.getSavingInterestRate() + "," +
                    bank.getCurrentTotal() + "," + bank.getSavingTotal());
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }
}