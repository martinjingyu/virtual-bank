package utill.write;

import Entity.AccountManager;
import Entity.CurrentAccount;
import Entity.SavingAccount;
import utill.cryption.EncryptionUtil;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * The WriteAccount class provides methods to write account data to files.
 */
public class WriteAccount {

    /**
     * Writes the current account data to a file.
     *
     * @param accountManager the account manager containing the current account data
     * @param fileName       the file name for writing the current account data
     */
    public static void writeAccount(AccountManager accountManager, List<String> fileName) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName.get(0)))) {
            StringBuilder buffer = new StringBuilder();
            List<CurrentAccount> accounts = accountManager.getCurrentAccounts();
            for (CurrentAccount account : accounts) {
                buffer.append(account.getName()).append(",").append(account.getBalance()).append(System.lineSeparator());
            }
            String encryptedData = EncryptionUtil.encrypt(buffer.toString());
            bw.write(encryptedData);
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        } catch (Exception ex) {
            System.out.println("Error encrypting data: " + ex.getMessage());
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName.get(1)))) {
            StringBuilder buffer = new StringBuilder();
            List<SavingAccount> accounts = accountManager.getSavingAccounts();
            for (SavingAccount account : accounts) {
                buffer.append(account.getName()).append(",").append(account.getBalance()).append(",").append(account.getInterestRate()).append(",").append(account.getStartTime()).append(",").append(account.getEndTime()).append(System.lineSeparator());
            }
            String encryptedData = EncryptionUtil.encrypt(buffer.toString());
            bw.write(encryptedData);
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        } catch (Exception ex) {
            System.out.println("Error encrypting data: " + ex.getMessage());
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName.get(2)))) {
            StringBuilder buffer = new StringBuilder();
            buffer.append(accountManager.getUserID()).append(",").append(accountManager.getSavingGoal()).append(",").append(accountManager.getAllInterst()).append(System.lineSeparator());
            String encryptedData = EncryptionUtil.encrypt(buffer.toString());
            bw.write(encryptedData);
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        } catch (Exception ex) {
            System.out.println("Error encrypting data: " + ex.getMessage());
        }
    }
}
