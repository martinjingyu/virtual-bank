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
}
