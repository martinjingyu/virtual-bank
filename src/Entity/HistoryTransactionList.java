package Entity;

import Entity.AccountManager;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * The HistoryTransactionList class manages a list of HistoryTransaction objects, providing methods to add transactions,
 * retrieve transactions, and calculate financial summaries such as total income, expenses, and balance.
 */
public class HistoryTransactionList {
    private List<HistoryTransaction> transactions;
    private List<CurrentAccount>currentAccountList;
    private List<SavingAccount> savingAccountList;

    /**
     * Constructs a HistoryTransactionList with an empty list of transactions.
     */
    public HistoryTransactionList() {
        transactions = new ArrayList<>();
    }

    /**
     * Adds a transaction to the list.
     *
     * @param transaction the transaction to be added
     */
    public void addTransaction(HistoryTransaction transaction) {
        transactions.add(transaction);
    }

    /**
     * Gets all transactions in the list.
     *
     * @return a list of all transactions
     */
    public List<HistoryTransaction> getAllTransactions() {
        return transactions;
    }


    /**
     * Gets a list of unique transaction dates.
     *
     * @return a list of unique transaction dates
     */
    public List<String> getDateList() {
        Set<String> dateSet = new HashSet<>(); // Use a set to store unique dates
        for (HistoryTransaction transaction : getAllTransactions()) {
            dateSet.add(transaction.getDay()); // Add only the date part to the set
        }
        return new ArrayList<>(dateSet); // Convert the set to a list and return
    }

    /**
     * Gets a list of transaction details as formatted strings.
     *
     * @return a list of formatted transaction details
     */
    public List<String> getTransactionDetails() {
        List<String> details = new ArrayList<>(); // Use a list to maintain insertion order
        for (HistoryTransaction transaction : transactions) {
            String detail = transaction.getDate() + " - from " + transaction.getSource() + " to " + transaction.getDestination() + " : $ " + transaction.getAmount();
            details.add(detail);
        }
        return details;
    }

    /**
     * Gets a list of transaction details for a specific date.
     *
     * @param specificDate the date for which to retrieve transaction details
     * @return a list of formatted transaction details for the specific date
     */
    public List<String> getTransactionDetails(String specificDate) {
        List<String> details = new ArrayList<>();
        for (HistoryTransaction transaction : transactions) {
            if (specificDate == null || transaction.getDate().startsWith(specificDate)) {
                String detail = transaction.getDate() + " - from " + transaction.getSource() + " to " + transaction.getDestination() + " : $ " + transaction.getAmount();
                details.add(detail);
            }
        }
        return details;
    }

    /**
     * Gets a list of transactions for a specific date.
     *
     * @param specificDate the date for which to retrieve transactions
     * @return a list of transactions for the specific date
     */
    public List<HistoryTransaction> getTransactionsForDate(String specificDate) {
        return transactions.stream()
                .filter(t -> t.getDate().startsWith(specificDate))
                .collect(Collectors.toList());
    }

    /**
     * Calculates the total income from all transactions.
     *
     * @return the total income
     */
    public double getTotalIncome() {
        return transactions.stream()
                .filter(t -> t.getAmount() > 0) // Assume positive amounts represent income
                .mapToDouble(HistoryTransaction::getAmount)
                .sum();
    }

    /**
     * Calculates the total expenses from all transactions.
     *
     * @return the total expenses
     */
    public double getTotalExpenses() {
        return transactions.stream()
                .filter(t -> t.getAmount() < 0) // Assume negative amounts represent expenses
                .mapToDouble(t -> -t.getAmount()) // Convert expenses to positive for summing
                .sum();
    }

    /**
     * Calculates the total balance from all transactions.
     *
     * @return the total balance
     */
    public double getTotalBalance() {
        return getTotalIncome() - getTotalExpenses(); // Total balance = total income - total expenses
    }

    /**
     * Calculates the total income for a specific date.
     *
     * @param date the date for which to calculate income
     * @return the total income for the specific date
     */
    public double getIncomeForDate(String date, AccountManager accountManager) {
        List<String> savingAccountNames = accountManager.getSavingAccountNames();

        return getTransactionsForDate(date).stream()
                .filter(t -> t.getSource().equals("Task") || savingAccountNames.contains(t.getSource()))
                .mapToDouble(HistoryTransaction::getAmount)
                .sum();
    }

    /**
     * Calculates the total expenses for a specific date.
     *
     * @param date the date for which to calculate expenses
     * @return the total expenses for the specific date
     */
    public double getExpensesForDate(String date, AccountManager accountManager) {

        List<String> savingAccountNames = accountManager.getSavingAccountNames();

        return getTransactionsForDate(date).stream()
                .filter(t -> "shop".equals(t.getDestination())|| savingAccountNames.contains(t.getDestination())) // Filter transactions with destination "shop"
                .mapToDouble(HistoryTransaction::getAmount)
                .sum();
    }

    /**
     * Calculates the total balance for a specific date.
     *
     * @param date the date for which to calculate the balance
     * @return the total balance for the specific date
     */
    public double getBalanceForDate(String date, AccountManager accountManager) {

        return getIncomeForDate(date,accountManager) + getExpensesForDate(date,accountManager);
    }

    /**
     * Formats an amount as a string with a plus or minus sign and two decimal places.
     *
     * @param amount the amount to format
     * @return the formatted amount string
     */
    public static String formatAmount(double amount) {
        if (amount >= 0) {
            return String.format("+%.2f", amount);
        } else {
            return String.format("%.2f", amount);
        }
    }
}
