package Test.TestEntity.Bank;

import Entity.HistoryTransaction;
import Entity.HistoryTransactionList;
import Entity.AccountManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class HistoryTransactionListTest {

    private HistoryTransactionList historyTransactionList;
    private AccountManager accountManager;

    @BeforeEach
    public void setUp() {
        historyTransactionList = new HistoryTransactionList();
        accountManager = new AccountManager();
    }

    @Test
    public void testConstructor() {
        assertNotNull(historyTransactionList.getAllTransactions());
        assertTrue(historyTransactionList.getAllTransactions().isEmpty());
    }

    @Test
    public void testAddTransaction() {
        HistoryTransaction transaction = new HistoryTransaction("Task", "shop", 100.0);
        historyTransactionList.addTransaction(transaction);
        assertEquals(1, historyTransactionList.getAllTransactions().size());
        assertTrue(historyTransactionList.getAllTransactions().contains(transaction));
    }

    @Test
    public void testGetAllTransactions() {
        HistoryTransaction transaction1 = new HistoryTransaction("Task", "shop", 100.0);
        HistoryTransaction transaction2 = new HistoryTransaction("Task", "shop", 200.0);
        historyTransactionList.addTransaction(transaction1);
        historyTransactionList.addTransaction(transaction2);

        List<HistoryTransaction> transactions = historyTransactionList.getAllTransactions();
        assertEquals(2, transactions.size());
        assertTrue(transactions.contains(transaction1));
        assertTrue(transactions.contains(transaction2));
    }

    @Test
    public void testGetDateList() {
        HistoryTransaction transaction1 = new HistoryTransaction("Task", "shop", 100.0, "2024-05-27 10:00:00");
        HistoryTransaction transaction2 = new HistoryTransaction("Task", "shop", 200.0, "2024-05-28 11:00:00");
        historyTransactionList.addTransaction(transaction1);
        historyTransactionList.addTransaction(transaction2);

        List<String> dateList = historyTransactionList.getDateList();
        assertEquals(2, dateList.size());
        assertTrue(dateList.contains("2024-05-27"));
        assertTrue(dateList.contains("2024-05-28"));
    }

    @Test
    public void testGetTransactionDetails() {
        HistoryTransaction transaction1 = new HistoryTransaction("Task", "shop", 100.0, "2024-05-27 10:00:00");
        HistoryTransaction transaction2 = new HistoryTransaction("Task", "shop", 200.0, "2024-05-28 11:00:00");
        historyTransactionList.addTransaction(transaction1);
        historyTransactionList.addTransaction(transaction2);

        List<String> details = historyTransactionList.getTransactionDetails();
        assertEquals(2, details.size());
        assertEquals("2024-05-27 10:00:00 - from Task to shop : $ 100.0", details.get(0));
        assertEquals("2024-05-28 11:00:00 - from Task to shop : $ 200.0", details.get(1));
    }

    @Test
    public void testGetTransactionDetailsForSpecificDate() {
        HistoryTransaction transaction1 = new HistoryTransaction("Task", "shop", 100.0, "2024-05-27 10:00:00");
        HistoryTransaction transaction2 = new HistoryTransaction("Task", "shop", 200.0, "2024-05-28 11:00:00");
        historyTransactionList.addTransaction(transaction1);
        historyTransactionList.addTransaction(transaction2);

        List<String> details = historyTransactionList.getTransactionDetails("2024-05-27");
        assertEquals(1, details.size());
        assertEquals("2024-05-27 10:00:00 - from Task to shop : $ 100.0", details.get(0));
    }

    @Test
    public void testGetTransactionsForDate() {
        HistoryTransaction transaction1 = new HistoryTransaction("Task", "shop", 100.0, "2024-05-27 10:00:00");
        HistoryTransaction transaction2 = new HistoryTransaction("Task", "shop", 200.0, "2024-05-28 11:00:00");
        historyTransactionList.addTransaction(transaction1);
        historyTransactionList.addTransaction(transaction2);

        List<HistoryTransaction> transactions = historyTransactionList.getTransactionsForDate("2024-05-27");
        assertEquals(1, transactions.size());
        assertTrue(transactions.contains(transaction1));
    }

    @Test
    public void testGetTotalIncome() {
        HistoryTransaction incomeTransaction = new HistoryTransaction("Task", "shop", 100.0);
        HistoryTransaction expenseTransaction = new HistoryTransaction("shop", "Task", -50.0);
        historyTransactionList.addTransaction(incomeTransaction);
        historyTransactionList.addTransaction(expenseTransaction);

        double totalIncome = historyTransactionList.getTotalIncome();
        assertEquals(100.0, totalIncome);
    }

    @Test
    public void testGetTotalExpenses() {
        HistoryTransaction incomeTransaction = new HistoryTransaction("Task", "shop", 100.0);
        HistoryTransaction expenseTransaction = new HistoryTransaction("shop", "Task", -50.0);
        historyTransactionList.addTransaction(incomeTransaction);
        historyTransactionList.addTransaction(expenseTransaction);

        double totalExpenses = historyTransactionList.getTotalExpenses();
        assertEquals(50.0, totalExpenses);
    }

    @Test
    public void testGetTotalBalance() {
        HistoryTransaction incomeTransaction = new HistoryTransaction("Task", "shop", 100.0);
        HistoryTransaction expenseTransaction = new HistoryTransaction("shop", "Task", -50.0);
        historyTransactionList.addTransaction(incomeTransaction);
        historyTransactionList.addTransaction(expenseTransaction);

        double totalBalance = historyTransactionList.getTotalBalance();
        assertEquals(50.0, totalBalance);
    }


    @Test
    public void testFormatAmount() {
        assertEquals("+100.00", HistoryTransactionList.formatAmount(100.0));
        assertEquals("-50.00", HistoryTransactionList.formatAmount(-50.0));
    }
}
