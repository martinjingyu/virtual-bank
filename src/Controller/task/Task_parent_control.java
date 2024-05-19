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
