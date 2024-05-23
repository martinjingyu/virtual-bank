package Entity;

import Exceptions.InsufficientFundsException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;

public class AccountManager {
    private String userID;
    private double savingGoal;
    private List<CurrentAccount> currentAccounts;
    private List<SavingAccount> savingAccounts;
    private HistoryTransactionList historyTransactionList;


    public AccountManager(HistoryTransactionList historyTransactionList) {
        this.currentAccounts = new ArrayList<>();
        this.savingAccounts = new ArrayList<>();
        this.historyTransactionList = historyTransactionList;
    }
    public String getUserID() {
        return userID;
    }
    public void setUserID(String name){
        this.userID = name;
    }
    public void setSavingGoal(double amount){
        this.savingGoal= amount;
    }

    public double getSavingGoal() {
        return savingGoal;
    }

    public List<CurrentAccount> getCurrentAccounts() {
        return currentAccounts;
    }

    public List<SavingAccount> getSavingAccounts() {
        return savingAccounts;
    }
    public Account getCurrentAccountByName(String name){
        for (Account account : currentAccounts) {
            if (account.getName().equals(name)) {
                return account;
            }
        }
//        return -1;
        return null;
    }

    // 添加活期账户
    public void addCurrentAccount(CurrentAccount account) {
        currentAccounts.add(account);
    }

    // 删除活期账户
    public void removeCurrentAccount(int index) {
        currentAccounts.remove(index);
    }
    public void addSavingAccount(SavingAccount account) {
        savingAccounts.add(account);
    }
    // 添加储蓄账户
    public void addSavingAccount(SavingAccount account, LocalDateTime startTime, LocalDateTime endTime, double initialBalance, double interestRate) {
        account.setStartTime(startTime);
        account.setEndTime(endTime);
        account.deposit(initialBalance); // Deposit initial balance
        account.setInterestRate(interestRate);
        savingAccounts.add(account);
    }

    // 删除储蓄账户
    public void removeSavingAccount(int index) {
        savingAccounts.remove(index);
    }
    public void deleteSavingAccount(SavingAccount account) {
        savingAccounts.remove(account);
    }

    public void deleteCurrentAccount(CurrentAccount account) {
        currentAccounts.remove(account);
    }

    // 获取所有活期账户的总余额
    public double getTotalCurrentBalance() {
        return currentAccounts.stream().mapToDouble(CurrentAccount::getBalance).sum();
    }

    public double getCurrentAccountBalance(String accountName) {
        System.out.println(currentAccounts);
        for (CurrentAccount account : currentAccounts) {
            System.out.println(account.getName());
            if (account.getName().equals(accountName)) {

                return account.getBalance();
            }
        }
        return -1;  // Or you could throw an exception
    }

    // 获取所有储蓄账户的总余额
    public double getTotalSavingBalance() {
        BigDecimal bd = new BigDecimal(savingAccounts.stream().mapToDouble(SavingAccount::getBalance).sum());
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
    public double getTotalSavingIncome(){
        BigDecimal bd = new BigDecimal(savingAccounts.stream().mapToDouble(SavingAccount::getIncome).sum());
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    // 打印所有活期账户的详情
    public void printCurrentAccountDetails() {
        currentAccounts.forEach(account -> System.out.println("Current Account Balance: " + account.getBalance()));
    }

    // 打印所有储蓄账户的详情
    public void printSavingAccountDetails() {
        savingAccounts.forEach(account -> System.out.println("Saving Account Balance: " + account.getBalance()));
    }
    public void earlyWithdrew(int currentIndex, int savingIndex) {
        CurrentAccount currentAccount = currentAccounts.get(currentIndex);
        SavingAccount savingAccount = savingAccounts.get(savingIndex);

        savingAccount.setEndTime(LocalDateTime.now());
        currentAccount.deposit(savingAccount.getBalance());
        try {
            savingAccount.withdraw(savingAccount.getBalance());
        }
        catch (Exception e1){

        }

    }
    public List<String> getSavingAccountNames(){
        List<String> names = new ArrayList<>();
        for(SavingAccount account: savingAccounts){
            names.add(account.getName());
        }
        return names;
    }
    public List<String> getCurrentAccountNames(){
        List<String> names = new ArrayList<>();
        for(CurrentAccount account: currentAccounts){
            names.add(account.getName());
        }
        return names;
    }
    public void savingWithdrewToCurrent(int currentIndex, int savingIndex) {
        CurrentAccount currentAccount = currentAccounts.get(currentIndex);
        SavingAccount savingAccount = savingAccounts.get(savingIndex);

        savingAccount.calculateInterest();
        currentAccount.deposit(savingAccount.getBalance());
        try{
            savingAccount.withdraw(savingAccount.getBalance());
        }
        catch (Exception e1){

        }



        HistoryTransaction historyTransaction= new HistoryTransaction(currentAccount.getName(),savingAccount.getName(),savingAccount.getBalance());
        historyTransactionList.addTransaction(historyTransaction);

        System.out.println(historyTransaction);

    }

    /**
     * Withdraws an amount from a CurrentAccount by name.
     * @param accountName The name of the account.
     * @param amount The amount to withdraw.
     * @return true if the operation was successful, false otherwise.
     */
    public boolean withdrawFromCurrentAccount(String accountName, double amount) throws InsufficientFundsException {
        for (CurrentAccount account : currentAccounts) {
            if (account.getName().equals(accountName)) {
                if (account.getBalance() >= amount) {
                    account.withdraw(amount);

                    HistoryTransaction historyTransaction= new HistoryTransaction(account.getName(),"shop",-amount);
                    historyTransactionList.addTransaction(historyTransaction);

                    System.out.println(historyTransaction);

                    return true;
                } else {
                    throw new InsufficientFundsException("Insufficient funds for withdrawal.");
                }
            }
        }
        throw new InsufficientFundsException("Account not found.");
    }
    public void transfer(int from, int to, double value) throws InsufficientFundsException{
        CurrentAccount currentAccountFrom = currentAccounts.get(from);
        CurrentAccount currentAccountTo = currentAccounts.get(to);

        currentAccountFrom.withdraw(value);
        currentAccountTo.deposit(value);

        HistoryTransaction historyTransaction= new HistoryTransaction(currentAccountFrom.getName(),currentAccountTo.getName(),value);
        historyTransactionList.addTransaction(historyTransaction);

        System.out.println(historyTransaction);
    }
    public void depositCurrentToSaving(int currentIndex, int savingIndex, double value, String selectedDuration) throws InsufficientFundsException{
        CurrentAccount currentAccount = currentAccounts.get(currentIndex);
        SavingAccount savingAccount = savingAccounts.get(savingIndex);

        currentAccount.withdraw(value);
        savingAccount.deposit(value);
        savingAccount.setStartTime(LocalDateTime.now());
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime result = now;
        switch (selectedDuration) {
            case "15 days":
                result = now.plusDays(15);
                break;
            case "1 month":
                result = now.plusMonths(1);
                break;
            case "3 months":
                result = now.plusMonths(3);
                break;
        }
        savingAccount.setEndTime(result);

        HistoryTransaction historyTransaction= new HistoryTransaction(currentAccount.getName(),savingAccount.getName(),value);
        historyTransactionList.addTransaction(historyTransaction);
    }

    public void createNewSavingAccount(String name){
        savingAccounts.add(new SavingAccount(name));
    }
    public void createNewCurrentAccount(String name){
        currentAccounts.add(new CurrentAccount(name));
    }
}
