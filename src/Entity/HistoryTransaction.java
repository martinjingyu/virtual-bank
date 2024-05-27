package Entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The HistoryTransaction class represents a transaction history entry with details
 * such as source, destination, amount, and date of the transaction.
 */
public class HistoryTransaction {
    private String source;
    private String destination; // Type of transaction (e.g., deposit, withdrawal, etc.)
    private double amount; // Transaction amount
    private String date; // Transaction date

    /**
     * Constructs a HistoryTransaction with the specified source, destination, and amount.
     * The date is set to the current date and time.
     *
     * @param source      the source of the transaction
     * @param destination the destination or type of transaction
     * @param amount      the amount of the transaction
     */
    public HistoryTransaction(String source, String destination, double amount) {
        this.source = source;
        this.destination = destination;
        this.amount = amount;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/M/d HH:mm:ss");
        this.date = LocalDateTime.now().format(formatter);
    }

    /**
     * Constructs a HistoryTransaction with the specified source, destination, amount, and date.
     *
     * @param source      the source of the transaction
     * @param destination the destination or type of transaction
     * @param amount      the amount of the transaction
     * @param date        the date of the transaction
     */
    public HistoryTransaction(String source, String destination, double amount, String date) {
        this.source = source;
        this.destination = destination;
        this.amount = amount;
        this.date = date;
    }

    /**
     * Gets the source of the transaction.
     *
     * @return the source of the transaction
     */
    public String getSource() {
        return source;
    }

    /**
     * Sets the source of the transaction.
     *
     * @param source the new source of the transaction
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * Gets the destination or type of the transaction.
     *
     * @return the destination or type of the transaction
     */
    public String getDestination() {
        return destination;
    }

    /**
     * Sets the destination or type of the transaction.
     *
     * @param destination the new destination or type of the transaction
     */
    public void setDestination(String destination) {
        this.destination = destination;
    }

    /**
     * Gets the amount of the transaction.
     *
     * @return the amount of the transaction
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Sets the amount of the transaction.
     *
     * @param amount the new amount of the transaction
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * Gets the date of the transaction.
     *
     * @return the date of the transaction
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets the date of the transaction.
     *
     * @param date the new date of the transaction
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Gets the day part of the transaction date.
     *
     * @return the day part of the transaction date
     */
    public String getDay() {
        String[] parts = getDate().split("\\s+");
        return parts[0];
    }

    /**
     * Returns a string representation of the transaction.
     *
     * @return a string representation of the transaction
     */
    @Override
    public String toString() {
        return source + ", " + destination + ", " + amount + ", " + date;
    }
}
