package Entity;

import Exceptions.InsufficientFundsException;

import java.time.LocalDateTime;

public abstract class Account {
    private String name;
    // Attributes
    protected double balance;
    protected double interestRate;


    // Constructor
    public Account(String name, double initialBalance, double interestRate) {
        this.name = name;
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
        System.out.println("Balance: " + balance);
        balance += amount;
        System.out.println("Deposited: " + amount);
        System.out.println("Balance after: " + balance);
    }

    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawn: " + amount);
        } else {

            System.out.println("Insufficient funds.");
            throw new InsufficientFundsException("Insufficient funds.");
        }
    }

    public void setName(String name){
        this.name = name;
    }

    public double getBalance() {
        return balance;
    }
    public double getInterestRate() {
        return balance;
    }
    public String getName(){
        return  name;
    }

    // Abstract method for interest calculation
    public abstract void calculateInterest();

    public static void main(String[] args) {

    }
}

