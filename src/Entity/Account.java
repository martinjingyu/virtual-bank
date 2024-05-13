package Entity;

import java.time.LocalDateTime;

public abstract class Account {
    // Attributes
    protected double balance;
    protected double interestRate;


    // Constructor
    public Account(double initialBalance, double interestRate) {
        this.balance = initialBalance;
        this.interestRate = interestRate;
    }


    // Set initial balance
    public Account() {
        this.balance = 0;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    // Common methods
    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposited: " + amount);
    }

    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawn: " + amount);
        } else {
            System.out.println("Insufficient funds.");
        }
    }

    public double getBalance() {
        return balance;
    }
    public double getInterestRate() {
        return balance;
    }

    // Abstract method for interest calculation
    public abstract void calculateInterest();
}

