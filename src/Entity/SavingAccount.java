package Entity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The SavingAccount class represents a type of bank account that earns interest over a specified period.
 * It extends the Account class.
 */
public class SavingAccount extends Account {

    // Additional attributes
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    // Constructor
    /**
     * Constructs a SavingAccount with the specified name and initializes the start and end time to the current time.
     *
     * @param name the name of the account holder
     */
    public SavingAccount(String name) {
        super(name, 0, 0);
        this.startTime = LocalDateTime.now();
        this.endTime = LocalDateTime.now();
    }

    /**
     * Constructs a SavingAccount with the specified name, initial balance, interest rate, start time, and end time.
     *
     * @param name           the name of the account holder
     * @param initialBalance the initial balance of the account
     * @param interestRate   the interest rate of the account
     * @param startTime      the start time of the saving period
     * @param endTime        the end time of the saving period
     */
    public SavingAccount(String name, double initialBalance, double interestRate, LocalDateTime startTime, LocalDateTime endTime) {
        super(name, initialBalance, interestRate);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    // Time-bound methods
    public void setStartTime(LocalDateTime time) {
        startTime = time;
    }

    public void setEndTime(LocalDateTime time) {
        endTime = time;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    /**
     * Gets the formatted start time of the saving period.
     *
     * @return the formatted start time as a string
     */
    public String getFormattedStartTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        return startTime.format(formatter);
    }

    /**
     * Gets the formatted end time of the saving period.
     *
     * @return the formatted end time as a string
     */
    public String getFormattedEndTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        return endTime.format(formatter);
    }

    @Override
    public void calculateInterest() {
        LocalDateTime now = LocalDateTime.now();
        if (now.isBefore(endTime) || endTime == null) {
            long daysBetween = java.time.temporal.ChronoUnit.DAYS.between(startTime, now);
            balance += balance * (interestRate / 100.0) * (daysBetween / 365.25);
        } else {
            long daysBetween = java.time.temporal.ChronoUnit.DAYS.between(startTime, endTime);
            balance += balance * (interestRate / 100.0) * (daysBetween / 365.25);
        }
    }

    /**
     * Calculates the total income earned from the saving account.
     *
     * @return the total income earned
     */
    public double getIncome() {
        long daysBetween = java.time.temporal.ChronoUnit.DAYS.between(startTime, endTime);
        double income = balance + balance * (interestRate / 100.0) * (daysBetween / 365.25);
        BigDecimal bd = new BigDecimal(income);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
