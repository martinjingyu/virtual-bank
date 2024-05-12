package Entity;

import java.time.LocalDateTime;

public class SavingAccount extends Account {
    // Additional attributes
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    // Constructor
    public SavingAccount(double initialBalance, double interestRate) {
        super(initialBalance, interestRate);
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
}
