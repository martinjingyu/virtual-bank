package Entity;

import Exceptions.InsufficientFundsException;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
        BigDecimal bd = new BigDecimal(balance);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
    public double getInterestRate() {
        BigDecimal bd = new BigDecimal(interestRate);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
    public String getName(){
        return  name;
    }

    // Abstract method for interest calculation
    public abstract void calculateInterest();

    public static void main(String[] args) {

    }
}

