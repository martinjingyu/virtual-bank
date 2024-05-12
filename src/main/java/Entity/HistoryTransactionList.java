package Entity;

import java.util.ArrayList;
import java.util.List;

public class HistoryTransactionList {
    private List<HistoryTransaction> transactions;

    // 构造方法
    public HistoryTransactionList() {
        transactions = new ArrayList<>();
    }
    // 添加交易记录
    public void addTransaction(HistoryTransaction transaction) {
        transactions.add(transaction);
    }

    // 获取所有交易记录
    public List<HistoryTransaction> getTransactions() {
        return transactions;
    }
}