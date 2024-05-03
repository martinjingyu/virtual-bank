package Entity;

public class Bank {
    private String name;
    private double savingGoal;
    private double currentInterestRate;
    private double savingInterestRate;
    private double currentTotal;
    private double savingTotal;
    public Bank(){

    }

    public Bank(String name, double savingGoal, double currentInterestRate, double savingInterestRate,
                double currentTotal, double savingTotal) {
        this.name = name;
        this.savingGoal = savingGoal;
        this.currentInterestRate = currentInterestRate;
        this.savingInterestRate = savingInterestRate;
        this.currentTotal = currentTotal;
        this.savingTotal = savingTotal;
    }


    // 各属性的 getter 和 setter 方法

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSavingGoal() {
        return savingGoal;
    }

    public void setSavingGoal(double savingGoal) {
        this.savingGoal = savingGoal;
    }

    public double getCurrentInterestRate() {
        return currentInterestRate;
    }

    public void setCurrentInterestRate(double currentInterestRate) {
        this.currentInterestRate = currentInterestRate;
    }

    public double getSavingInterestRate() {
        return savingInterestRate;
    }

    public void setSavingInterestRate(double savingInterestRate) {
        this.savingInterestRate = savingInterestRate;
    }

    public double getCurrentTotal() {
        return currentTotal;
    }

    public void setCurrentTotal(double currentTotal) {
        this.currentTotal = currentTotal;
    }

    public double getSavingTotal() {
        return savingTotal;
    }

    public void setSavingTotal(double savingTotal) {
        this.savingTotal = savingTotal;
    }

}

