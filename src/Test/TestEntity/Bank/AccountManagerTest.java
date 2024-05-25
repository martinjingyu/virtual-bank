package Test.TestEntity.Bank;

import Exceptions.InsufficientFundsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import Entity.*;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class AccountManagerTest {
    private AccountManager accountManager;
    private HistoryTransactionList historyTransactionList;

    @BeforeEach
    void setUp() {
        historyTransactionList = new HistoryTransactionList();
        accountManager = new AccountManager(historyTransactionList);
    }

    @Test
    void addCurrentAccount() {
        CurrentAccount currentAccount = new CurrentAccount("Test");
        accountManager.addCurrentAccount(currentAccount);
        assertEquals(1, accountManager.getCurrentAccounts().size());
        assertTrue(accountManager.getCurrentAccounts().contains(currentAccount));
    }

    @Test
    void removeCurrentAccount() {
        CurrentAccount currentAccount = new CurrentAccount("Test");
        accountManager.addCurrentAccount(currentAccount);
        assertEquals(1, accountManager.getCurrentAccounts().size());
        accountManager.removeCurrentAccount(0);
        assertEquals(0, accountManager.getCurrentAccounts().size());
    }

    @Test
    void addSavingAccount() {
        SavingAccount savingAccount = new SavingAccount("Test");
        accountManager.addSavingAccount(savingAccount);
        assertEquals(1, accountManager.getSavingAccounts().size());
        assertTrue(accountManager.getSavingAccounts().contains(savingAccount));
    }

    @Test
    void removeSavingAccount() {
        SavingAccount savingAccount = new SavingAccount("Test");
        accountManager.addSavingAccount(savingAccount);
        assertEquals(1, accountManager.getSavingAccounts().size());
        accountManager.removeSavingAccount(0);
        assertEquals(0, accountManager.getSavingAccounts().size());
    }

    @Test
    void getTotalCurrentBalance() {
        CurrentAccount currentAccount1 = new CurrentAccount("Test1");
        CurrentAccount currentAccount2 = new CurrentAccount("Test2");
        currentAccount1.deposit(100);
        currentAccount2.deposit(200);
        accountManager.addCurrentAccount(currentAccount1);
        accountManager.addCurrentAccount(currentAccount2);
        assertEquals(300, accountManager.getTotalCurrentBalance());
    }

    @Test
    void getCurrentAccountBalance() {
        CurrentAccount currentAccount1 = new CurrentAccount("Test1");
        CurrentAccount currentAccount2 = new CurrentAccount("Test2");
        currentAccount1.deposit(100);
        currentAccount2.deposit(200);
        accountManager.addCurrentAccount(currentAccount1);
        accountManager.addCurrentAccount(currentAccount2);
        assertEquals(100, accountManager.getCurrentAccountBalance("Test1"));
        assertEquals(200, accountManager.getCurrentAccountBalance("Test2"));
        assertEquals(0, accountManager.getCurrentAccountBalance("Test3"));
    }

    @Test
    void getTotalSavingBalance() {
        SavingAccount savingAccount1 = new SavingAccount("Test1");
        SavingAccount savingAccount2 = new SavingAccount("Test2");
        savingAccount1.deposit(100);
        savingAccount2.deposit(200);
        accountManager.addSavingAccount(savingAccount1);
        accountManager.addSavingAccount(savingAccount2);
        assertEquals(300, accountManager.getTotalSavingBalance());
    }

    @Test
    void getTotalSavingIncome() {
        SavingAccount savingAccount1 = new SavingAccount("Test1");
        SavingAccount savingAccount2 = new SavingAccount("Test2");
        savingAccount1.deposit(50);
        savingAccount2.deposit(75);
        accountManager.addSavingAccount(savingAccount1);
        accountManager.addSavingAccount(savingAccount2);
        assertEquals(125, accountManager.getTotalSavingIncome());
    }

    @Test
    void earlyWithdrew() {
        CurrentAccount currentAccount = new CurrentAccount("Current");
        SavingAccount savingAccount = new SavingAccount("Saving");
        savingAccount.deposit(100);
        accountManager.addCurrentAccount(currentAccount);
        accountManager.addSavingAccount(savingAccount);
        accountManager.earlyWithdrew(0, 0);
        assertEquals(100, accountManager.getTotalCurrentBalance());
        assertEquals(0, accountManager.getTotalSavingBalance());
    }

    @Test
    void withdrawFromCurrentAccount() {
        CurrentAccount currentAccount = new CurrentAccount("Test");
        currentAccount.deposit(100);
        accountManager.addCurrentAccount(currentAccount);
        assertDoesNotThrow(() -> accountManager.withdrawFromCurrentAccount("Test", 50));
        assertEquals(50, accountManager.getCurrentAccountBalance("Test"));
        assertThrows(InsufficientFundsException.class, () -> accountManager.withdrawFromCurrentAccount("Test", 100));
    }

    @Test
    void transfer() {
        CurrentAccount currentAccount1 = new CurrentAccount("Source");
        CurrentAccount currentAccount2 = new CurrentAccount("Destination");
        currentAccount1.deposit(100);
        accountManager.addCurrentAccount(currentAccount1);
        accountManager.addCurrentAccount(currentAccount2);
        assertDoesNotThrow(() -> accountManager.transfer(0, 1, 50));
        assertEquals(50, accountManager.getCurrentAccountBalance("Source"));
        assertEquals(50, accountManager.getCurrentAccountBalance("Destination"));
        assertThrows(InsufficientFundsException.class, () -> accountManager.transfer(0, 1, 100));
    }

    @Test
    void depositCurrentToSaving() {
        CurrentAccount currentAccount = new CurrentAccount("Current");
        SavingAccount savingAccount = new SavingAccount("Saving");
        currentAccount.deposit(100);
        accountManager.addCurrentAccount(currentAccount);
        accountManager.addSavingAccount(savingAccount);
        assertDoesNotThrow(() -> accountManager.depositCurrentToSaving(0, 0, 50, "15 days"));
        assertEquals(50, accountManager.getCurrentAccountBalance("Current"));
        assertEquals(50, accountManager.getTotalSavingBalance());
    }

    @Test
    void createNewSavingAccount() {
        accountManager.createNewSavingAccount("Test");
        assertEquals(1, accountManager.getSavingAccounts().size());
        assertEquals("Test", accountManager.getSavingAccounts().get(0).getName());
    }

    @Test
    void createNewCurrentAccount() {
        accountManager.createNewCurrentAccount("Test");
        assertEquals(1, accountManager.getCurrentAccounts().size());
        assertEquals("Test", accountManager.getCurrentAccounts().get(0).getName());
    }

    @Test
    void setInterestRate() {
        accountManager.setInterestRate(0.5, "15 days");
        accountManager.setInterestRate(1.0, "1 month");
        accountManager.setInterestRate(1.5, "3 months");
        assertEquals(0.5, accountManager.getAllInterst(0));
        assertEquals(1.0, accountManager.getAllInterst(1));
        assertEquals(1.5, accountManager.getAllInterst(2));
    }

    @Test
    void getAllInterst() {
        accountManager.setInterestRate(0.5, "15 days");
        accountManager.setInterestRate(1.0, "1 month");
        accountManager.setInterestRate(1.5, "3 months");
        assertEquals("0.5,1.0,1.5", accountManager.getAllInterst());
    }

    @Test
    void getCurrentAccountNames() {
        CurrentAccount currentAccount1 = new CurrentAccount("Test1");
        CurrentAccount currentAccount2 = new CurrentAccount("Test2");
        accountManager.addCurrentAccount(currentAccount1);
        accountManager.addCurrentAccount(currentAccount2);
        assertEquals(2, accountManager.getCurrentAccountNames().size());
        assertTrue(accountManager.getCurrentAccountNames().contains("Test1"));
        assertTrue(accountManager.getCurrentAccountNames().contains("Test2"));
    }

    @Test
    void getSavingAccountNames() {
        SavingAccount savingAccount1 = new SavingAccount("Test1");
        SavingAccount savingAccount2 = new SavingAccount("Test2");
        accountManager.addSavingAccount(savingAccount1);
        accountManager.addSavingAccount(savingAccount2);
        assertEquals(2, accountManager.getSavingAccountNames().size());
        assertTrue(accountManager.getSavingAccountNames().contains("Test1"));
        assertTrue(accountManager.getSavingAccountNames().contains("Test2"));
    }
}

