package Controller.task;

import Entity.Kids;
import GUI.task_page.Task_kid;

import javax.swing.*;
import java.awt.event.*;
import java.util.Objects;


public class Task_kid_control extends MouseAdapter {
    private Task_kid task_kid;
    private Kids kid;

    public Task_kid_control(Kids kid) {
        this.kid = kid;
    }
    public void setGUI(Task_kid GUI){
        this.task_kid = GUI;
    }
    public void addButtonControl(){

        //task_kid.getButton(1).addMouseListener(this);

//        task_kid.getButton(2).addMouseListener(this);
//        task_kid.getButton(3).addMouseListener(this);
//        task_kid.getButton(4).addMouseListener(this);
//        task_kid.getButton(5).addMouseListener(this);
    }

    public Kids getKid(){
        return kid;
    }


    public boolean taskButton(int index){
        if(Objects.equals(kid.getTaskList().getTask(index).getDestination(), "x")){
            task_kid.showWarning();
            return false;
        }else {
            // 发送信息
//            String state = kid.getTaskList().getNonConfirmedTask().getTask(index).getState();
//            kid.getMessagelist().addTaskMessage("Child_Opt",kid.getTaskList().getNonConfirmedTask().getTask(index).getM(state));
            return true;

        }
    }

    public String taskInfo(int index){
        if(Objects.equals(kid.getTaskList().getNonConfirmedTask().getTask(index).getState(), "Confirmed")){
            return kid.getTaskList().getNonConfirmedTask().getTask(index).getM("Confirmed") +" " + kid.getTaskList().getNonConfirmedTask().getTask(index).getReward();

        }else {
            return kid.getTaskList().getNonConfirmedTask().getTask(index).getM(kid.getTaskList().getNonConfirmedTask().getTask(index).getState())
                    + kid.getTaskList().getNonConfirmedTask().getTask(index).getName();
        }
    }



//    @Override
    public void mouseClicked(MouseEvent e,int i) {
//        if(e.getSource()==task_kid.getButton(1)){
//            taskButton(1);
//        } else if(e.getSource()==task_kid.getButton(2)){
//            taskButton(2);
//        } else if(e.getSource()==task_kid.getButton(3)){
//            taskButton(3);
//        } else if(e.getSource()==task_kid.getButton(4)){
//            taskButton(4);
//        }
        if (taskButton(i)) {  // 只有当 taskButton 返回 true 时，继续执行
            task_kid.showDialog(i);
        }



    }






//        }
//        else if (e.getSource()==mainFrame.getButton(2)) {
//            mainFrame.changePanel(2);
//        }
//        else if (e.getSource()==mainFrame.getButton(3)) {
//            mainFrame.changePanel(3);
//        }
//        else if (e.getSource()==mainFrame.getButton(4)) {
//            mainFrame.changePanel(4);
//        }
//
//
//    }
}