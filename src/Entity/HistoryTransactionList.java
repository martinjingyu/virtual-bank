package Entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class HistoryTransactionList {
    private static List<HistoryTransaction> transactions;

    // 构造方法
    public HistoryTransactionList() {
        transactions = new ArrayList<>();
    }
    // 添加交易记录
    public void addTransaction(HistoryTransaction transaction) {
        transactions.add(transaction);
    }

    // 获取所有交易记录
    public List<HistoryTransaction> getAllTransactions() {
        return transactions;
    }
    public List<String> getDateList(){
        Set<String> dateSet = new HashSet<>(); // 使用集合来存储日期，以确保唯一性
        for (HistoryTransaction transaction : getAllTransactions()) {
            dateSet.add(transaction.getDay()); // 只保留日期部分并添加到集合中
        }
        return new ArrayList<>(dateSet); // 将集合转换为列表并返回
    }

    public List<String> getTransactionDetails() {
        List<String> details = new ArrayList<>(); // 使用 List 保持插入顺序
        for (HistoryTransaction transaction : transactions) {
            String detail = transaction.getDate() + " - from " + transaction.getSource() +" to " +transaction.getDestination() + " : $ " + transaction.getAmount();
            details.add(detail);
        }
        return details;
    }

    public List<String> getTransactionDetails(String specificDate) {
        List<String> details = new ArrayList<>();
        for (HistoryTransaction transaction : transactions) {
            if (specificDate == null || transaction.getDate().startsWith(specificDate)) {  // 检查是否指定日期或返回所有
//                String detail = transaction.getDate() + " - " + transaction.getType() + ": $" + transaction.getAmount();
                String detail = transaction.getDate() + " - from " + transaction.getSource() +" to " +transaction.getDestination() + " : $ " + transaction.getAmount();
                details.add(detail);
            }
        }
        return details;
    }
    public List<HistoryTransaction> getTransactionsForDate(String specificDate) {
        return transactions.stream()
                .filter(t -> t.getDate().startsWith(specificDate))
                .collect(Collectors.toList());
    }

    public double getIncomeForDate(String date) {
        return getTransactionsForDate(date).stream()
                .filter(t -> t.getAmount() >= 0)
                .mapToDouble(HistoryTransaction::getAmount)
                .sum();
    }

    public double getExpensesForDate(String date) {
        return getTransactionsForDate(date).stream()
                .filter(t -> t.getAmount() < 0)
                .mapToDouble(HistoryTransaction::getAmount)
                .sum();
    }

    public double getBalanceForDate(String date) {
        return getIncomeForDate(date) + getExpensesForDate(date);
    }

    public static String formatAmount(double amount) {
        if (amount >= 0) {
            return String.format("+%.2f", amount);
        } else {
            return String.format("%.2f", amount);
        }
    }

}
