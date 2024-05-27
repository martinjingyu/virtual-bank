package Entity;

import Exceptions.InsufficientFundsException;

import javax.swing.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * The Account class represents a bank account with basic functionalities
 * such as deposit, withdraw, and interest rate management.
 */
public abstract class Account {
    private String name;
    protected double balance;
    protected double interestRate;

    /**
     * Constructs an Account with the specified name, initial balance, and interest rate.
     *
     * @param name          the name of the account
     * @param initialBalance the initial balance of the account
     * @param interestRate  the interest rate of the account
     */
    public Account(String name, double initialBalance, double interestRate) {
        this.name = name;
        this.balance = initialBalance;
        this.interestRate = interestRate;
    }

    /**
     * Constructs an Account with an initial balance of zero.
     */
    public Account() {
        this.balance = 0;
    }

    /**
     * Sets the interest rate for the account.
     *
     * @param interestRate the new interest rate
     */
    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    /**
     * Deposits the specified amount into the account.
     *
     * @param amount the amount to deposit
     */
    public void deposit(double amount) {
        System.out.println("Balance: " + balance);
        balance += amount;
        System.out.println("Deposited: " + amount);
        System.out.println("Balance after: " + balance);
    }

    /**
     * Withdraws the specified amount from the account.
     *
     * @param amount the amount to withdraw
     * @throws InsufficientFundsException if the amount exceeds the current balance
     */
    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawn: " + amount);
        } else {
            System.out.println("Insufficient funds.");
            JOptionPane.showMessageDialog(null, "Insufficient funds.", "Insufficient funds", JOptionPane.ERROR_MESSAGE);
            throw new InsufficientFundsException("Insufficient funds.");
        }
    }

    /**
     * Sets the name of the account.
     *
     * @param name the new name of the account
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the current balance of the account.
     *
     * @return the current balance
     */
    public double getBalance() {
        BigDecimal bd = new BigDecimal(balance);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    /**
     * Gets the interest rate of the account.
     *
     * @return the interest rate
     */
    public double getInterestRate() {
        BigDecimal bd = new BigDecimal(interestRate);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    /**
     * Gets the name of the account.
     *
     * @return the name of the account
     */
    public String getName() {
        return name;
    }

    /**
     * Abstract method to calculate interest for the account.
     * Subclasses must provide an implementation for this method.
     */
    public abstract void calculateInterest();

    public static void main(String[] args) {
        // Main method for testing purposes
    }
}
