package Entity;

import java.awt.*;
import javax.swing.JOptionPane;
import exceptions.InsufficientFundsException;


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

    public double changeCurrent(double number) throws InsufficientFundsException {
        if (number < 0 && Math.abs(number) > currentTotal) {
            throw new InsufficientFundsException("Insufficient funds for the operation.");
        }
        return currentTotal + number;
    }

    public double changeSaving(double number, double savingTotal) {
        // 如果 number 是负数，且加上 currentTotal 后结果小于 0，则直接返回 currentTotal
        if (number < 0 && Math.abs(number) > savingTotal) {
            JOptionPane.showMessageDialog(null, "Sorry, you can't do this operation.", "Operation Not Allowed", JOptionPane.WARNING_MESSAGE);
            return savingTotal;
        } else {
            // 否则，将 number 加到 currentTotal 上，并返回结果
            return savingTotal + number;
        }
    }


    public double changeSavingGoal(TextField savingGoalTextField) {
        try {
            // 从文本框中获取用户输入并将其转换为 double 类型
            double userInput = Double.parseDouble(savingGoalTextField.getText());
            // 调用 setSavingGoal 方法设置 savingGoal
            setSavingGoal(userInput);
        } catch (NumberFormatException e) {
            // 如果用户输入无效，可以在这里处理错误
            System.err.println("Invalid input. Please enter a valid number.");
            // 或者显示一个警告给用户
            // Alert alert = new Alert(AlertType.ERROR, "Invalid input. Please enter a valid number.");
            // alert.showAndWait();
        }
        return savingGoal;
    }
//    public double changeInterest(double interest) {
//
//    }


}

