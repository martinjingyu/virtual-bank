package Entity;

import java.time.LocalDateTime;

public class SavingAccount extends Account{
    private LocalDateTime StartTime;
    private LocalDateTime EndTime;

    public SavingAccount(){
        setMoney(0);
    }

    public void setStartTime(LocalDateTime time){
        StartTime = time;
    }
    public void setEndTime(LocalDateTime time){
        EndTime = time;
    }
    public LocalDateTime getStartTime(){
        return StartTime;
    }
    public LocalDateTime getEndTime(){
        return  EndTime;
    }
}
