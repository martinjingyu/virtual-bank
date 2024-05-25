package Entity;

import Exceptions.InsufficientFundsException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * The AccountManager class manages multiple accounts, both current and saving accounts,
 * and handles various operations such as deposits, withdrawals, transfers, and balance inquiries.
 */
public class AccountManager {
    private String userID;
    private double savingGoal;
    private List<CurrentAccount> currentAccounts;
    private List<SavingAccount> savingAccounts;
    private HistoryTransactionList historyTransactionList;
    private double interest1;
    private double interest2;
    private double interest3;

    /**
     * Constructs an AccountManager with the specified history transaction list.
     *
     * @param historyTransactionList the history transaction list
     */
    public AccountManager(HistoryTransactionList historyTransactionList) {
        this.currentAccounts = new ArrayList<>();
        this.savingAccounts = new ArrayList<>();
        this.historyTransactionList = historyTransactionList;
    }

    /**
     * Gets the user ID.
     *
     * @return the user ID
     */
    public String getUserID() {
        return userID;
    }

    /**
     * Sets the user ID.
     *
     * @param name the user ID
     */
    public void setUserID(String name) {
        this.userID = name;
    }

    /**
     * Sets the saving goal.
     *
     * @param amount the saving goal amount
     */
    public void setSavingGoal(double amount) {
        this.savingGoal = amount;
    }

    /**
     * Gets the saving goal.
     *
     * @return the saving goal
     */
    public double getSavingGoal() {
        return savingGoal;
    }

    /**
     * Gets the list of current accounts.
     *
     * @return the list of current accounts
     */
    public List<CurrentAccount> getCurrentAccounts() {
        return currentAccounts;
    }

    /**
     * Gets the list of saving accounts.
     *
     * @return the list of saving accounts
     */
    public List<SavingAccount> getSavingAccounts() {
        return savingAccounts;
    }

    /**
     * Gets a current account by its name.
     *
     * @param name the name of the account
     * @return the current account, or null if not found
     */
    public Account getCurrentAccountByName(String name) {
        for (Account account : currentAccounts) {
            if (account.getName().equals(name)) {
                return account;
            }
        }
        return null;
    }

    /**
     * Adds a current account.
     *
     * @param account the current account to add
     */
    public void addCurrentAccount(CurrentAccount account) {
        currentAccounts.add(account);
    }

    /**
     * Removes a current account by its index.
     *
     * @param index the index of the current account to remove
     */
    public void removeCurrentAccount(int index) {
        currentAccounts.remove(index);
    }

    /**
     * Adds a saving account.
     *
     * @param account the saving account to add
     */
    public void addSavingAccount(SavingAccount account) {
        savingAccounts.add(account);
    }

    /**
     * Adds a saving account with specified details.
     *
     * @param account        the saving account to add
     * @param startTime      the start time of the saving account
     * @param endTime        the end time of the saving account
     * @param initialBalance the initial balance of the saving account
     * @param interestRate   the interest rate of the saving account
     */
    public void addSavingAccount(SavingAccount account, LocalDateTime startTime, LocalDateTime endTime, double initialBalance, double interestRate) {
        account.setStartTime(startTime);
        account.setEndTime(endTime);
        account.deposit(initialBalance);
        account.setInterestRate(interestRate);
        savingAccounts.add(account);
    }

    /**
     * Removes a saving account by its index.
     *
     * @param index the index of the saving account to remove
     */
    public void removeSavingAccount(int index) {
        savingAccounts.remove(index);
    }

    /**
     * Deletes a saving account.
     *
     * @param account the saving account to delete
     */
    public void deleteSavingAccount(SavingAccount account) {
        savingAccounts.remove(account);
    }

    /**
     * Deletes a current account.
     *
     * @param account the current account to delete
     */
    public void deleteCurrentAccount(CurrentAccount account) {
        currentAccounts.remove(account);
    }

    /**
     * Gets the total balance of all current accounts.
     *
     * @return the total balance of all current accounts
     */
    public double getTotalCurrentBalance() {
        BigDecimal bd = new BigDecimal(currentAccounts.stream().mapToDouble(CurrentAccount::getBalance).sum());
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    /**
     * Gets the balance of a current account by its name.
     *
     * @param accountName the name of the account
     * @return the balance of the current account, or 0 if not found
     */
    public double getCurrentAccountBalance(String accountName) {
        for (CurrentAccount account : currentAccounts) {
            if (account.getName().equals(accountName)) {
                return account.getBalance();
            }
        }
        return 0;
    }

    /**
     * Gets the total balance of all saving accounts.
     *
     * @return the total balance of all saving accounts
     */
    public double getTotalSavingBalance() {
        BigDecimal bd = new BigDecimal(savingAccounts.stream().mapToDouble(SavingAccount::getBalance).sum());
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    /**
     * Gets the total income from all saving accounts.
     *
     * @return the total income from all saving accounts
     */
    public double getTotalSavingIncome() {
        BigDecimal bd = new BigDecimal(savingAccounts.stream().mapToDouble(SavingAccount::getIncome).sum());
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    /**
     * Prints the details of all current accounts.
     */
    public void printCurrentAccountDetails() {
        currentAccounts.forEach(account -> System.out.println("Current Account Balance: " + account.getBalance()));
    }

    /**
     * Prints the details of all saving accounts.
     */
    public void printSavingAccountDetails() {
        savingAccounts.forEach(account -> System.out.println("Saving Account Balance: " + account.getBalance()));
    }

    /**
     * Performs an early withdrawal from a saving account to a current account.
     *
     * @param currentIndex the index of the current account
     * @param savingIndex  the index of the saving account
     */
    public void earlyWithdrew(int currentIndex, int savingIndex) {
        CurrentAccount currentAccount = currentAccounts.get(currentIndex);
        SavingAccount savingAccount = savingAccounts.get(savingIndex);

        savingAccount.setEndTime(LocalDateTime.now());
        currentAccount.deposit(savingAccount.getBalance());
        try {
            savingAccount.withdraw(savingAccount.getBalance());
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    /**
     * Gets the names of all saving accounts.
     *
     * @return the list of saving account names
     */
    public List<String> getSavingAccountNames() {
        List<String> names = new ArrayList<>();
        for (SavingAccount account : savingAccounts) {
            names.add(account.getName());
        }
        return names;
    }

    /**
     * Gets the names of all current accounts.
     *
     * @return the list of current account names
     */
    public List<String> getCurrentAccountNames() {
        List<String> names = new ArrayList<>();
        for (CurrentAccount account : currentAccounts) {
            names.add(account.getName());
        }
        return names;
    }

    /**
     * Withdraws from a saving account and deposits into a current account.
     *
     * @param currentIndex the index of the current account
     * @param savingIndex  the index of the saving account
     */
    public void savingWithdrewToCurrent(int currentIndex, int savingIndex) {
        CurrentAccount currentAccount = currentAccounts.get(currentIndex);
        SavingAccount savingAccount = savingAccounts.get(savingIndex);

        savingAccount.calculateInterest();
        currentAccount.deposit(savingAccount.getBalance());
        try {
            savingAccount.withdraw(savingAccount.getBalance());
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        HistoryTransaction historyTransaction = new HistoryTransaction(currentAccount.getName(), savingAccount.getName(), savingAccount.getBalance());
        historyTransactionList.addTransaction(historyTransaction);

        System.out.println(historyTransaction);
    }

    /**
     * Withdraws an amount from a CurrentAccount by name.
     *
     * @param accountName the name of the account
     * @param amount      the amount to withdraw
     * @return true if the operation was successful, false otherwise
     * @throws InsufficientFundsException if there are insufficient funds or account not found
     */
    public boolean withdrawFromCurrentAccount(String accountName, double amount) throws InsufficientFundsException {
        for (CurrentAccount account : currentAccounts) {
            if (account.getName().equals(accountName)) {
                if (account.getBalance() >= amount) {
                    account.withdraw(amount);

                    HistoryTransaction historyTransaction = new HistoryTransaction(account.getName(), "shop", -amount);
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

    /**
     * Transfers an amount from one current account to another.
     *
     * @param from  the index of the source account
     * @param to    the index of the destination account
     * @param value the amount to transfer
     * @throws InsufficientFundsException if there are insufficient funds in the source account
     */
    public void transfer(int from, int to, double value) throws InsufficientFundsException {
        CurrentAccount currentAccountFrom = currentAccounts.get(from);
        CurrentAccount currentAccountTo = currentAccounts.get(to);

        currentAccountFrom.withdraw(value);
        currentAccountTo.deposit(value);

        HistoryTransaction historyTransaction = new HistoryTransaction(currentAccountFrom.getName(), currentAccountTo.getName(), value);
        historyTransactionList.addTransaction(historyTransaction);

        System.out.println(historyTransaction);
    }

    /**
     * Deposits an amount from a current account to a saving account.
     *
     * @param currentIndex    the index of the current account
     * @param savingIndex     the index of the saving account
     * @param value           the amount to deposit
     * @param selectedDuration the selected duration for the saving account
     * @throws InsufficientFundsException if there are insufficient funds in the current account
     */
    public void depositCurrentToSaving(int currentIndex, int savingIndex, double value, String selectedDuration) throws InsufficientFundsException {
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
                savingAccount.setInterestRate(interest1);
                break;
            case "1 month":
                result = now.plusMonths(1);
                savingAccount.setInterestRate(interest1);
                break;
            case "3 months":
                result = now.plusMonths(3);
                savingAccount.setInterestRate(interest1);
                break;
        }
        savingAccount.setEndTime(result);

        HistoryTransaction historyTransaction = new HistoryTransaction(currentAccount.getName(), savingAccount.getName(), value);
        historyTransactionList.addTransaction(historyTransaction);
    }

    /**
     * Creates a new saving account with the specified name.
     *
     * @param name the name of the new saving account
     */
    public void createNewSavingAccount(String name) {
        savingAccounts.add(new SavingAccount(name));
    }

    /**
     * Creates a new current account with the specified name.
     *
     * @param name the name of the new current account
     */
    public void createNewCurrentAccount(String name) {
        currentAccounts.add(new CurrentAccount(name));
    }

    /**
     * Sets the interest rate for a specified duration.
     *
     * @param interestRate the interest rate
     * @param date         the duration (e.g., "15 days", "1 month", "3 months")
     */
    public void setInterestRate(Double interestRate, String date) {
        switch (date) {
            case "15 days":
                interest1 = interestRate;
                break;
            case "1 month":
                interest2 = interestRate;
                break;
            case "3 months":
                interest3 = interestRate;
                break;
        }
    }

    /**
     * Gets all interest rates as a comma-separated string.
     *
     * @return a string representing all interest rates
     */
    public String getAllInterst() {
        return interest1 + "," + interest2 + "," + interest3;
    }

    /**
     * Gets the interest rate by index.
     *
     * @param index the index of the interest rate (0 for 15 days, 1 for 1 month, 2 for 3 months)
     * @return the interest rate
     */
    public double getAllInterst(int index) {
        switch (index) {
            case 0:
                return interest1;
            case 1:
                return interest2;
            case 2:
                return interest3;
            default:
                return interest1;
        }
    }
}
