package Controller.task;

import Entity.Kids;
import GUI.task_page.Task_kid;

import javax.swing.*;
import java.awt.event.*;
import java.util.Objects;

/**
 * The Task_kid_control class handles the control logic for the task kid GUI.
 * It extends MouseAdapter to handle mouse events.
 */
public class Task_kid_control extends MouseAdapter {
    private Task_kid task_kid;
    private Kids kid;

    /**
     * Constructs a new Task_kid_control with the specified kid.
     *
     * @param kid the kid entity to be controlled
     */
    public Task_kid_control(Kids kid) {
        this.kid = kid;
    }

    /**
     * Sets the GUI for this controller.
     *
     * @param GUI the Task_kid GUI to be controlled
     */
    public void setGUI(Task_kid GUI) {
        this.task_kid = GUI;
    }

    /**
     * Adds button controls to the GUI.
     * This method should be implemented to add mouse listeners to the buttons.
     */
    public void addButtonControl() {
        // task_kid.getButton(1).addMouseListener(this);
        // task_kid.getButton(2).addMouseListener(this);
        // task_kid.getButton(3).addMouseListener(this);
        // task_kid.getButton(4).addMouseListener(this);
        // task_kid.getButton(5).addMouseListener(this);
    }

    /**
     * Returns the kid entity controlled by this controller.
     *
     * @return the kid entity
     */
    public Kids getKid() {
        return kid;
    }

    /**
     * Handles the task button click event.
     * If the task's destination is "x", it shows a warning and returns false.
     * Otherwise, it performs some operations (e.g., sending a message) and returns true.
     *
     * @param index the index of the task
     * @return true if the task's destination is not "x", false otherwise
     */
    public boolean taskButton(int index) {
        if (Objects.equals(kid.getTaskList().getTask(index).getDestination(), "x")) {
            task_kid.showWarning();
            return false;
        } else {
            // 发送信息
            // String state = kid.getTaskList().getNonConfirmedTask().getTask(index).getState();
            // kid.getMessagelist().addTaskMessage("Child_Opt", kid.getTaskList().getNonConfirmedTask().getTask(index).getM(state));
            return true;
        }
    }

    /**
     * Returns the task information for the specified task index.
     * If the task is confirmed, it returns a message with the reward.
     * Otherwise, it returns a message with the task's name.
     *
     * @param index the index of the task
     * @return the task information
     */
    public String taskInfo(int index) {
        if (Objects.equals(kid.getTaskList().getNonConfirmedTask().getTask(index).getState(), "Confirmed")) {
            return kid.getTaskList().getNonConfirmedTask().getTask(index).getM("Confirmed") + " " + kid.getTaskList().getNonConfirmedTask().getTask(index).getReward();
        } else {
            return kid.getTaskList().getNonConfirmedTask().getTask(index).getM(kid.getTaskList().getNonConfirmedTask().getTask(index).getState())
                    + kid.getTaskList().getNonConfirmedTask().getTask(index).getName();
        }
    }

    /**
     * Handles the mouse click event.
     * If the task button action returns true, it shows a dialog.
     *
     * @param e the mouse event
     * @param i the index of the task button clicked
     */
    public void mouseClicked(MouseEvent e, int i) {
        if (taskButton(i)) {  // 只有当 taskButton 返回 true 时，继续执行
            task_kid.showDialog(i);
        }
    }
}
