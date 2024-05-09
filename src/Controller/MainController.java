package Controller;

import Controller.bank.Bank_kid_control;
import Controller.task.Task_kid_control;
import Entity.Kids;
import utill.read.ReadAll;

public class MainController {
    private Kids kid;
    public Bank_kid_control bank_kid_control;
    public Task_kid_control task_kid_control;
    public MainController(String id){
        Kids kid = ReadAll.readall(id);
        bank_kid_control = new Bank_kid_control(kid);
        task_kid_control = new Task_kid_control(kid);
    }
    public Kids getKid(){
        return kid;
    }
}
