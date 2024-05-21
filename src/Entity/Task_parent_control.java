package Controller.task;

import Entity.Kids;
import GUI.task_page.Task_parent;

import java.awt.event.*;

/**
 * The Task_parent_control class handles the control logic for the task parent GUI.
 */
public class Task_parent_control {
    private Task_parent task_parent;
    private Kids kid;

    /**
     * Constructs a new Task_parent_control with the specified kid.
     *
     * @param kid the kid entity to be controlled
     */
    public Task_parent_control(Kids kid) {
        this.kid = kid;
    }

    /**
     * Sets the GUI for this controller.
     *
     * @param GUI the Task_parent GUI to be controlled
     */
    public void setGUI(Task_parent GUI) {
        this.task_parent = GUI;
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
     * Handles the mouse click event and shows a dialog in the parent task GUI.
     *
     * @param e the mouse event
     * @param i the index of the task button clicked
     */
    public void mouseClicked(MouseEvent e, int i) {
        task_parent.showDialog1(i);
    }
}
