package utill.validate;
import Entity.Account;

import javax.swing.*;
import java.util.List;

/**
 * The Validate class provides methods to validate various inputs.
 */
public class Validate {

    /**
     * Validates a name.
     *
     * @param name the name to be validated
     * @return true if the name is valid, false otherwise
     */
    public static String validateName(String name) throws Exception{

        if (name == null || name.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Invalid input. Name cannot be empty.", "Input Error", JOptionPane.ERROR_MESSAGE);
            throw new Exception("Invalid name: name cannot be empty.");
        }

        boolean hasLetter = false;
        for (char c : name.toCharArray()) {
            if (!Character.isLetterOrDigit(c) && !Character.isWhitespace(c)) {
                JOptionPane.showMessageDialog(null, "Invalid input. Name can only contain letters, digits, and spaces.", "Input Error", JOptionPane.ERROR_MESSAGE);
                throw new Exception("Invalid name: contains invalid characters.");
            }
            if (Character.isLetter(c)) {
                hasLetter = true;
            }
        }

        if (!hasLetter) {
            JOptionPane.showMessageDialog(null, "Invalid input. Name must contain at least one letter.", "Input Error", JOptionPane.ERROR_MESSAGE);
            throw new Exception("Invalid name: must contain at least one letter.");
        }

        if (name.charAt(name.length() - 1) == ' ') {
            JOptionPane.showMessageDialog(null, "Invalid input. The last character can't be a space. ", "Input Error", JOptionPane.ERROR_MESSAGE);
            throw new Exception("The last character can't be a space. ");
        }

        return name;
    }


    /**
     * Validates a description.
     *
     * @param description the description to be validated
     * @return true if the description is valid, false otherwise
     */
    public static boolean validateDescription(String description) {
        if (description == null || description.length() > 150) {
            return false;
        }

        for (char c : description.toCharArray()) {
            if (!Character.isLetterOrDigit(c) && !Character.isWhitespace(c) && c != '.' && c != ',' && c != '!' && c != '?') {
                return false;
            }
        }
        return true;
    }


    /**
     * Validates a salary.
     *
     * @param salary the salary to be validated
     * @return true if the salary is valid, false otherwise
     */
    public static boolean validateSalary(String salary) {
        if (salary == null || salary.isEmpty()) {
            return false;
        }

        boolean hasDecimalPoint = false;
        boolean hasDigitBeforeDecimal = false;
        int decimalDigits = 0;


        for (int i = 0; i < salary.length(); i++) {
            char c = salary.charAt(i);
            if (c == '.') {
                if (hasDecimalPoint) {
                    return false; // Multiple decimal points
                }
                hasDecimalPoint = true;
                if (i == 0 || !Character.isDigit(salary.charAt(i - 1))) {
                    return false; // Must have a digit before the decimal point
                }
            } else if (!Character.isDigit(c)) {
                return false; // Not a digit
            } else {
                if (hasDecimalPoint) {
                    decimalDigits++;
                    if (decimalDigits > 2) {
                        return false; // More than two decimal places
                    }
                }
                hasDigitBeforeDecimal = true;
            }
        }


        if (hasDecimalPoint && decimalDigits == 0) {
            return false; // No digits after the decimal point
        }

        return hasDigitBeforeDecimal;
    }

    public static double validateNumber(String input) throws Exception{
        if (input.matches("^\\d+(\\.\\d{1,2})?$")) {
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                // 处理数字格式异常
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number.", "Input Error", JOptionPane.ERROR_MESSAGE);
                throw e;
            }
        } else {
            // 处理不匹配正则表达式的输入
            JOptionPane.showMessageDialog(null, "Invalid input. Please enter a number with at most two decimal places.", "Input Error", JOptionPane.ERROR_MESSAGE);
            throw new Exception("invalid number");
        }
    }

    public static boolean validateRepeat(String name, List<String> accountList) throws Exception{
        for (String account : accountList) {
            if (account.equals(name)) {
                JOptionPane.showMessageDialog(null, "Invalid input. This name has been used", "Input Error", JOptionPane.ERROR_MESSAGE);
                throw new Exception();
                 }
        }
        return true;
    }

    public static double validateInterest(String number) throws Exception{

        int dotIndex = number.indexOf(".");
        System.out.println(dotIndex);
        if(dotIndex!=-1 && number.substring(dotIndex+1).length()>2){

            JOptionPane.showMessageDialog(null, "Please inter a number with at most two decimal", "Input Error",JOptionPane.ERROR_MESSAGE);
            throw new Exception();
        }
        Double output = Validate.validateNumber(number);
        if(output>20||output<0){
            JOptionPane.showMessageDialog(null, "The interest rate is supposed to be a number between 0 and 20", "Input Error",JOptionPane.ERROR_MESSAGE);
            throw new Exception();
        }

        return output;
    }
}
