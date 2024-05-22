package Test.TestEntity.TransactionHistory;

import Entity.HistoryTransaction;
import Entity.HistoryTransactionList;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class HistoryTransactionListTest {
    private HistoryTransactionList transactionList;

    @Before
    public void setUp() {
        transactionList = new HistoryTransactionList();
    }

    @Test
    public void testAddTransaction() {
        HistoryTransaction transaction = new HistoryTransaction("CA1", "CA2", 10.0);
        transactionList.addTransaction(transaction);
        List<HistoryTransaction> transactions = transactionList.getAllTransactions();
        assertEquals(1, transactions.size());
        assertEquals(transaction, transactions.get(0));
    }

    @Test
    public void testGetAllTransactions() {
        HistoryTransaction transaction1 = new HistoryTransaction("CA2", "CA3", 20.0);
        HistoryTransaction transaction2 = new HistoryTransaction("CA3", "Shop", -5.0);
        transactionList.addTransaction(transaction1);
        transactionList.addTransaction(transaction2);

        List<HistoryTransaction> transactions = transactionList.getAllTransactions();
        assertEquals(2, transactions.size());
        assertEquals(transaction1, transactions.get(0));
        assertEquals(transaction2, transactions.get(1));
    }

    @Test
    public void testGetDateList() {
        HistoryTransaction transaction1 = new HistoryTransaction("SA1", "CA1", 80.0, "2023:05:21 15:30:45");
        HistoryTransaction transaction2 = new HistoryTransaction("CA2", "Shop", -5.0, "2023:05:22 10:20:30");
        transactionList.addTransaction(transaction1);
        transactionList.addTransaction(transaction2);

        List<String> dates = transactionList.getDateList();
        assertEquals(2, dates.size());
        assertTrue(dates.contains("2023:05:21"));
        assertTrue(dates.contains("2023:05:22"));
    }

    @Test
    public void testGetTransactionDetails() {
        HistoryTransaction transaction1 = new HistoryTransaction("Task", "CA2", 10.0, "2023:05:21 15:30:45");
        HistoryTransaction transaction2 = new HistoryTransaction("CA1", "SA3", 50.0, "2023:05:22 10:20:30");
        transactionList.addTransaction(transaction1);
        transactionList.addTransaction(transaction2);

        List<String> details = transactionList.getTransactionDetails();
        assertEquals(2, details.size());
        assertTrue(details.contains("2023:05:21 15:30:45 - from Task to CA2 : $ 10.0"));
        assertTrue(details.contains("2023:05:22 10:20:30 - from CA1 to SA3 : $ 50.0"));
    }

    @Test
    public void testGetTransactionDetailsForSpecificDate() {
        HistoryTransaction transaction1 = new HistoryTransaction("CA1", "CA2", 20.0, "2023:05:21 15:30:45");
        HistoryTransaction transaction2 = new HistoryTransaction("CA3", "CA1", 30.0, "2023:05:21 16:20:30");
        HistoryTransaction transaction3 = new HistoryTransaction("CA2", "SA1", 100.0, "2023:05:22 11:20:30");
        transactionList.addTransaction(transaction1);
        transactionList.addTransaction(transaction2);
        transactionList.addTransaction(transaction3);

        List<String> details = transactionList.getTransactionDetails("2023:05:21");
        assertEquals(2, details.size());
        assertTrue(details.contains("2023:05:21 15:30:45 - from CA1 to CA2 : $ 20.0"));
        assertTrue(details.contains("2023:05:21 16:20:30 - from CA3 to CA1 : $ 30.0"));
    }

    @Test
    public void testGetIncomeForDate() {
        HistoryTransaction transaction1 = new HistoryTransaction("Task", "CA1", 10.0, "2023:05:21 15:30:45");
        HistoryTransaction transaction2 = new HistoryTransaction("Task", "CA2", 20.0, "2023:05:21 16:20:30");
        transactionList.addTransaction(transaction1);
        transactionList.addTransaction(transaction2);

        double income = transactionList.getIncomeForDate("2023:05:21");
        assertEquals(30.0, income, 0.001);
    }

    @Test
    public void testGetExpensesForDate() {
        HistoryTransaction transaction1 = new HistoryTransaction("CA2", "Shop", -10.0, "2023:05:21 15:30:45");
        HistoryTransaction transaction2 = new HistoryTransaction("CA1", "Shop", -5.0, "2023:05:21 16:20:30");
        transactionList.addTransaction(transaction1);
        transactionList.addTransaction(transaction2);

        double expenses = transactionList.getExpensesForDate("2023:05:21");
        assertEquals(-15.0, expenses, 0.001);
    }

    @Test
    public void testGetBalanceForDate() {
        HistoryTransaction transaction1 = new HistoryTransaction("Task", "CA1", 20.0, "2023:05:21 15:30:45");
        HistoryTransaction transaction2 = new HistoryTransaction("CA1", "Shop", -5.0, "2023:05:21 16:20:30");
        transactionList.addTransaction(transaction1);
        transactionList.addTransaction(transaction2);

        double balance = transactionList.getBalanceForDate("2023:05:21");
        assertEquals(15.0, balance, 0.001);
    }

    @Test
    public void testFormatAmount() {
        assertEquals("+100.00", HistoryTransactionList.formatAmount(100.0));
        assertEquals("-50.00", HistoryTransactionList.formatAmount(-50.0));
    }
}
