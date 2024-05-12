package Entity;

public abstract class Account {
    private double money;
    public double getMoney(){
        return this.money;
    }
    public void setMoney(double amount){
        this.money = amount;
    }
}
