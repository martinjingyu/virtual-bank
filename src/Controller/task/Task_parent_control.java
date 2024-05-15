package Controller.task;

import Entity.Kids;
import GUI.task_page.Task_parent;

import javax.swing.*;
import java.awt.event.*;
import java.util.Objects;

public class Task_parent_control {
    private Task_parent task_parent;
    private Kids kid;

    public Task_parent_control(Kids kid) {
        this.kid = kid;
    }
    public void setGUI(Task_parent GUI){
        this.task_parent = GUI;
    }
    public Kids getKid(){
        return kid;
    }

    public void mouseClicked(MouseEvent e,int i) {
        task_parent.showDialog1(i);
    }
    public static boolean validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return false;
        }

        boolean hasLetter = false;
        for (char c : name.toCharArray()) {
            if (!Character.isLetterOrDigit(c) && !Character.isWhitespace(c)) {
                return false;
            }
            if (Character.isLetter(c)) {
                hasLetter = true;
            }
        }
        return hasLetter;
    }


    // 检查description输入
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

    // 检查salary输入
    public static boolean validateSalary(String salary) {
        if (salary == null || salary.isEmpty()) {
            return false;
        }

        boolean hasDecimalPoint = false;
        boolean hasDigitBeforeDecimal = false;

        for (int i = 0; i < salary.length(); i++) {
            char c = salary.charAt(i);
            if (c == '.') {
                if (hasDecimalPoint) {
                    return false; // 多个小数点
                }
                hasDecimalPoint = true;
                if (i == 0 || !Character.isDigit(salary.charAt(i - 1))) {
                    return false; // 小数点前必须有数字
                }
            } else if (!Character.isDigit(c)) {
                return false; // 不是数字
            } else {
                hasDigitBeforeDecimal = true;
            }
        }

        return hasDigitBeforeDecimal;
    }

    public static void main(String[] args) {
//        System.out.println(validateName("John Doe")); // true
//        System.out.println(validateName("John123")); // true
//        System.out.println(validateName("John_123")); // false
//        System.out.println(validateName("123")); // false
//        System.out.println(validateName("  ")); // false


//        System.out.println(validateDescription("This is a sample description.")); // true
//        System.out.println(validateDescription("This description contains invalid characters!@#")); // false
//        System.out.println(validateDescription("")); // true
//        System.out.println(validateDescription(new String(new char[151]).replace('\0', 'a'))); // false

//        System.out.println(validateSalary("12345")); // true
//        System.out.println(validateSalary("123.45")); // true
//        System.out.println(validateSalary(".45")); // false
//        System.out.println(validateSalary("12345.67.89")); // false
//        System.out.println(validateSalary("12345.")); // true
//        System.out.println(validateSalary("12345.0")); // true
//        System.out.println(validateSalary("123a45")); // false
    }
}
