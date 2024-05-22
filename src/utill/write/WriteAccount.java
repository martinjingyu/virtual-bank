package utill.write;

import Entity.*;
import utill.cryption.EncryptionUtil;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class WriteAccount {
    public static void writeAccount(AccountManager accountManager, List<String> fileName) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName.get(0)))) {
            StringBuilder buffer = new StringBuilder();
            List<CurrentAccount> accounts = accountManager.getCurrentAccounts();
            for (CurrentAccount account : accounts) {
                // 将消息对象的信息以特定格式写入文件
                buffer.append(account.getName() + "," + account.getBalance()).append(System.lineSeparator());
            }
            String encryptedData = EncryptionUtil.encrypt(buffer.toString());
            bw.write(encryptedData);
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }catch (Exception ex) {
            System.out.println("Error encrypting data: " + ex.getMessage());
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName.get(1)))) {
            StringBuilder buffer = new StringBuilder();
            List<SavingAccount> accounts = accountManager.getSavingAccounts();
            for (SavingAccount account : accounts) {
                // 将消息对象的信息以特定格式写入文件
                buffer.append(account.getName() + "," + account.getBalance()+","+account.getInterestRate()+","+account.getStartTime()+","+account.getEndTime()).append(System.lineSeparator());
            }
            String encryptedData = EncryptionUtil.encrypt(buffer.toString());
            bw.write(encryptedData);
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }catch (Exception ex) {
            System.out.println("Error encrypting data: " + ex.getMessage());
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName.get(2)))) {
            StringBuilder buffer = new StringBuilder();
            buffer.append(accountManager.getUserID() + "," + accountManager.getSavingGoal()).append(System.lineSeparator());
            String encryptedData = EncryptionUtil.encrypt(buffer.toString());
            bw.write(encryptedData);
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }catch (Exception ex) {
            System.out.println("Error encrypting data: " + ex.getMessage());
        }
    }
}
