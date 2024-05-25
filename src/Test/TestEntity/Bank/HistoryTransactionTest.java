package Test.TestEntity.Bank;

import Entity.HistoryTransaction;
import org.junit.Test;
import static org.junit.Assert.*;

public class HistoryTransactionTest {

    @Test
    public void testConstructorWithCurrentDate() {
        HistoryTransaction transaction = new HistoryTransaction("CA1", "CA2", 100.0);
        assertEquals("CA1", transaction.getSource());
        assertEquals("CA2", transaction.getDestination());
        assertEquals(100.0, transaction.getAmount(), 0.001);
        assertNotNull(transaction.getDate());
    }

    @Test
    public void testConstructorWithSpecifiedDate() {
        String date = "2023:05:21 15:30:45";
        HistoryTransaction transaction = new HistoryTransaction("CA1", "CA2", 50.0, date);
        assertEquals("CA1", transaction.getSource());
        assertEquals("CA2", transaction.getDestination());
        assertEquals(50.0, transaction.getAmount(), 0.001);
        assertEquals(date, transaction.getDate());
    }

    @Test
    public void testGettersAndSetters() {
        HistoryTransaction transaction = new HistoryTransaction("CA1", "CA2", 100.0);
        transaction.setSource("CA1");
        transaction.setDestination("CA2");
        transaction.setAmount(200.0);
        transaction.setDate("2023:05:22 10:20:30");

        assertEquals("CA1", transaction.getSource());
        assertEquals("CA2", transaction.getDestination());
        assertEquals(200.0, transaction.getAmount(), 0.001);
        assertEquals("2023:05:22 10:20:30", transaction.getDate());
    }

    @Test
    public void testGetDay() {
        String date = "2023:05:21 15:30:45";
        HistoryTransaction transaction = new HistoryTransaction("CA1", "Shop", 100.0, date);
        assertEquals("2023:05:21", transaction.getDay());
    }

    @Test
    public void testToString() {
        HistoryTransaction transaction = new HistoryTransaction("Task", "CA3", 100.0, "2023:05:21 15:30:45");
        String expected = "Task, CA3, 100.0, 2023:05:21 15:30:45";
        assertEquals(expected, transaction.toString());
    }
}
