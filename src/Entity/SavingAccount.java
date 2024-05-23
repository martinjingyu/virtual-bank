package Entity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SavingAccount extends Account {

    // Additional attributes
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    // Constructor
    public SavingAccount(String name){
        super(name,0, 0);
        this.startTime = LocalDateTime.now();
        this.endTime = LocalDateTime.now();
    }
    public SavingAccount(String name,double initialBalance, double interestRate,LocalDateTime startTime, LocalDateTime endTime) {
        super(name,initialBalance, interestRate);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    // Time-bound methods
    public void setStartTime(LocalDateTime time) {
        startTime = time;
    }

    public void setEndTime(LocalDateTime time) {
        endTime = time;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public String getFormattedStartTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String formattedDateTime = startTime.format(formatter);
        return formattedDateTime;
    }

    public String getFormattedEndTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String formattedDateTime = endTime.format(formatter);
        return formattedDateTime;
    }


    @Override
    public void calculateInterest() {
        LocalDateTime now = LocalDateTime.now();
        if (now.isBefore(endTime) || endTime == null) {
            long daysBetween = java.time.temporal.ChronoUnit.DAYS.between(startTime, now);
            balance += balance * (interestRate / 100.0) * (daysBetween / 365.25);
        } else {
            long daysBetween = java.time.temporal.ChronoUnit.DAYS.between(startTime, endTime);
            balance += balance * (interestRate / 100.0) * (daysBetween / 365.25);
        }
    }

    public double getIncome(){

        long daysBetween = java.time.temporal.ChronoUnit.DAYS.between(startTime, endTime);
        double income = balance;
        income += income * (interestRate / 100.0) * (daysBetween / 365.25);

        BigDecimal bd = new BigDecimal(income);
        bd = bd.setScale(2, RoundingMode.HALF_UP);

        return bd.doubleValue();
    }
}
