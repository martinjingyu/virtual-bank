package Entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class HistoryTransaction {
    private String Source;
    private String Destination; // 交易类型（例如存款、取款等）
    private double amount; // 交易金额
    private String date; // 交易日期

    // 构造方法
    public HistoryTransaction(String source,String destination, double amount){
        this.Source = source;
        this.Destination = destination;
        this.amount = amount;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy:M:d HH:mm:ss");
        String formattedDate = LocalDate.now().format(formatter);

        this.date = formattedDate;
    }
    public HistoryTransaction(String source,String destination, double amount, String date) {
        this.Source = source;
        this.Destination = destination;
        this.amount = amount;
        this.date = date;
    }

    // 各属性的 getter 和 setter 方法
    public String getSource(){ return Source;}
    public void setSource(){ this.Source = Source;}
    public String getDestination() {
        return Destination;
    }

    public void setDestination(String type) {
        this.Destination = Destination;
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
    public String getDay(){
        String[] parts = getDate().split("\\s+");
        return parts[0];
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {return Source + ", " + Destination + ", " + amount + ", "+ date;}
}
