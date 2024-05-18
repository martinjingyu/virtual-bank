package Controller;

import Controller.bank.Bank_parent_controller;
import Controller.message.Message_parent_controller;
import Controller.shop.ShopParentController;
import Controller.task.Task_parent_control;
import Entity.Kids;
import utill.read.ReadAll;

public class MainController_Parent {
    public MainFrameController_Parent mainFrameController_parent;
    public Bank_parent_controller bank_parent_controller;
    public Task_parent_control task_parent_control;
    public ShopParentController shopParentController;
    public Message_parent_controller messageParentController;



    public MainController_Parent(String id){
        Kids kid = ReadAll.readall(id);
        mainFrameController_parent = new MainFrameController_Parent(id,kid);
        bank_parent_controller = new Bank_parent_controller(kid);
        task_parent_control = new Task_parent_control(kid);
        shopParentController = new ShopParentController(kid);
        messageParentController = new Message_parent_controller(kid);



    }
}
