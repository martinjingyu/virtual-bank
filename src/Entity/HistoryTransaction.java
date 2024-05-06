package Entity;

public class HistoryTransaction {
    private String type; // 交易类型（例如存款、取款等）
    private double amount; // 交易金额
    private String date; // 交易日期

    // 构造方法
    public HistoryTransaction(String type, double amount, String date) {
        this.type = type;
        this.amount = amount;
        this.date = date;
    }

    // 各属性的 getter 和 setter 方法

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Transaction: " + type + ", Amount: " + amount + ", Date: " + date;
    }
}
