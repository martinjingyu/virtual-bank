package Entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AccountManager {
    private String userID;
    private double savingGoal;
    private List<CurrentAccount> currentAccounts;
    private List<SavingAccount> savingAccounts;


    public AccountManager() {
        this.currentAccounts = new ArrayList<>();
        this.savingAccounts = new ArrayList<>();
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

    // 添加活期账户
    public void addCurrentAccount(CurrentAccount account) {
        currentAccounts.add(account);
    }

    // 删除活期账户
    public void removeCurrentAccount(CurrentAccount account) {
        currentAccounts.remove(account);
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
    public void removeSavingAccount(SavingAccount account) {
        savingAccounts.remove(account);
    }

    // 获取所有活期账户的总余额
    public double getTotalCurrentBalance() {
        return currentAccounts.stream().mapToDouble(CurrentAccount::getBalance).sum();
    }

    // 获取所有储蓄账户的总余额
    public double getTotalSavingBalance() {
        return savingAccounts.stream().mapToDouble(SavingAccount::getBalance).sum();
    }

    // 打印所有活期账户的详情
    public void printCurrentAccountDetails() {
        currentAccounts.forEach(account -> System.out.println("Current Account Balance: " + account.getBalance()));
    }

    // 打印所有储蓄账户的详情
    public void printSavingAccountDetails() {
        savingAccounts.forEach(account -> System.out.println("Saving Account Balance: " + account.getBalance()));
    }
    public void earlyWithdrew(int currentIndex, int savingIndex){
        CurrentAccount currentAccount = currentAccounts.get(currentIndex);
        SavingAccount savingAccount = savingAccounts.get(savingIndex);

        savingAccount.setEndTime(LocalDateTime.now());
        currentAccount.deposit(savingAccount.getBalance());
        savingAccount.withdraw(savingAccount.getBalance());
    }
    public List<String> getSavingAccountNames(){
        List<String> names = new ArrayList<>();
        for(SavingAccount account: savingAccounts){
            names.add(account.getName());
        }
        return names;
    }
    public void withdrewToCurrent(int currentIndex, int savingIndex){
        CurrentAccount currentAccount = currentAccounts.get(currentIndex);
        SavingAccount savingAccount = savingAccounts.get(savingIndex);

        savingAccount.withdraw(savingAccount.getBalance());

    }
}