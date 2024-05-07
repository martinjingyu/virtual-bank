package Entity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import Exceptions.InsufficientFundsException;


public class Bank {
    private String name;
    private double savingGoal;
    private double currentInterestRate;
    private double savingInterestRate;
    private double currentTotal;
    private double savingTotal;
    private JButton button_yes;
    private JButton button_no;
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

    public void changeCurrent(double number) throws InsufficientFundsException {
        if (number < 0 && Math.abs(number) > currentTotal) {

            throw new InsufficientFundsException("Insufficient funds for the operation.");
        }
        this.currentTotal = currentTotal + number;
//        System.out.println(this.currentTotal);
    }


    public void changeSaving(double number) throws InsufficientFundsException{
        // 如果 number 是负数，且加上 currentTotal 后结果小于 0，则直接返回 currentTotal
        if (number < 0 && Math.abs(number) > savingTotal) {


            throw new InsufficientFundsException("Insufficient funds for the operation.");
        }
            // 否则，将 number 加到 currentTotal 上，并返回结果
        savingTotal += number; // 更新 savingTotal 的值

    }

    public double changeSavingGoal(JTextField savingGoalTextField) {
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

    public double changeCurrentInterestRate(JTextField currentInterestRateTestField) {
        try {
            // 从文本框中获取用户输入并将其转换为 double 类型
            double userInput = Double.parseDouble(currentInterestRateTestField.getText());
            // 调用 setSavingGoal 方法设置 savingGoal
            setCurrentInterestRate(userInput);
        } catch (NumberFormatException e) {
            // 如果用户输入无效，可以在这里处理错误
            System.err.println("Invalid input. Please enter a valid number.");
            // 或者显示一个警告给用户
            // Alert alert = new Alert(AlertType.ERROR, "Invalid input. Please enter a valid number.");
            // alert.showAndWait();
        }
        return currentInterestRate;
    }

    public double changeSavingInterestRate(JTextField savingInterestRateTestField) {
        try {
            // 从文本框中获取用户输入并将其转换为 double 类型
            double userInput = Double.parseDouble(savingInterestRateTestField.getText());
            // 调用 setSavingGoal 方法设置 savingGoal
            setSavingInterestRate(userInput);
        } catch (NumberFormatException e) {
            // 如果用户输入无效，可以在这里处理错误
            System.err.println("Invalid input. Please enter a valid number.");
            // 或者显示一个警告给用户
            // Alert alert = new Alert(AlertType.ERROR, "Invalid input. Please enter a valid number.");
            // alert.showAndWait();
        }
        return savingInterestRate;
    }


    public void transaction(JDialog dialog,String source,String destination){
//            JDialog dialog = new JDialog();//构造一个新的JFrame，作为新窗口。
            dialog.setBounds(// 让新窗口与SwingTest窗口示例错开50像素。
                    new Rectangle(
                            100,
                            100,
                            400, 400
                    )
            );

            JLabel jl1 = new JLabel();
            jl1.setBounds(50, 50, 100, 50);
            dialog.getContentPane().add(jl1);
            jl1.setText(source);

            JLabel jl2 = new JLabel();
            jl2.setBounds(170, 50, 20, 50);
            dialog.getContentPane().add(jl2);
            jl2.setText("to");

            JLabel jl3 = new JLabel();
            jl3.setBounds(210, 50, 100, 50);
            dialog.getContentPane().add(jl3);
            jl3.setText(destination);

            JLabel jl4 = new JLabel();
            jl4.setBounds(50, 120, 200, 50);
            dialog.getContentPane().add(jl4);
            jl4.setText("How much?");

            JTextField textField = new JTextField();
            textField.setBounds(190, 120, 100, 50);
            dialog.getContentPane().add(textField);

            JLabel jl5 = new JLabel();
            dialog.getContentPane().add(jl4);
            jl5.setText("Are you sure to operate?");
            jl5.setBounds(50, 220, 300, 20);

            button_yes = new JButton("YES");
            button_yes.setBounds(100, 270, 80, 40);
            button_yes.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        // 从文本框中获取用户输入并将其转换为 double 类型
                        double userInput = Double.parseDouble(textField.getText());
                        if(source.equals("saving")){
                            saving_to_current(userInput);
                        }else {
                            current_to_saving(userInput);
                        }
                    } catch (NumberFormatException exception) {
                        // 如果用户输入无效，可以在这里处理错误
                        System.err.println("Invalid input. Please enter a valid number.");
                        // 或者显示一个警告给用户
                        // Alert alert = new Alert(AlertType.ERROR, "Invalid input. Please enter a valid number.");
                        // alert.showAndWait();
                    }finally {
                        // 不论是否发生异常，都关闭对话框
                        dialog.dispose();
                    }

                }
            });
            button_no = new JButton("NO");
            button_no.setBounds(200, 270, 80, 40);

            jl5.setVerticalAlignment(JLabel.CENTER);
            jl5.setHorizontalAlignment(JLabel.CENTER);// 注意方法名别写错了。
            dialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);    // 设置模式类型。
            dialog.setLayout(null); // 设置布局为null，使用绝对布局
            dialog.add(button_yes);
            dialog.add(button_no);
            dialog.setVisible(true);

    }

    public void current_to_saving(double amount){
        try {
            changeCurrent(-amount);
            changeSaving(amount);

        }
        catch(InsufficientFundsException e){
            JOptionPane.showMessageDialog(null, "Sorry, you can't do this operation.", "Operation Not Allowed", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void saving_to_current(double amount){
        try {
            changeSaving(-amount);
            changeCurrent(amount);

        }
        catch (InsufficientFundsException e){
            JOptionPane.showMessageDialog(null, "Sorry, you can't do this operation.", "Operation Not Allowed", JOptionPane.WARNING_MESSAGE);

        }
    }
    public double transfer_to_kid(JTextField transferAmountTextField){
        try {
            // 从文本框中获取用户输入并将其转换为 double 类型
            double userInput = Double.parseDouble(transferAmountTextField.getText());
            // 调用 setSavingGoal 方法设置 savingGoal
            setSavingGoal(userInput);
        } catch (NumberFormatException e) {
            // 如果用户输入无效，可以在这里处理错误
            System.err.println("Invalid input. Please enter a valid number.");
            // 或者显示一个警告给用户
            // Alert alert = new Alert(AlertType.ERROR, "Invalid input. Please enter a valid number.");
            // alert.showAndWait();
        }
        return savingTotal;
    }


}

