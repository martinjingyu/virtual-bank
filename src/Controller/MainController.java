package Controller;

import Controller.bank.Bank_kid_control;
import Controller.message.Message_kid_controller;
import Controller.shop.ShopController;
//import Controller.task.Task_kid_control;
import Controller.task.Task_kid_control;
import Entity.Kids;
import GUI.RefreshListener;
import utill.read.ReadAll;

public class MainController {
    private Kids kid;
    private String ID;
    private RefreshListener listener;
    public Bank_kid_control bank_kid_control;
    public ShopController ShopController;
    public MainFrameController mainFrameController;
    public Message_kid_controller message_kid_controller;

    public Task_kid_control task_kid_control;
    public MainController(String id){
        this.ID = id;
        Kids kid = ReadAll.readall(id);
        mainFrameController = new MainFrameController(id,kid);

        bank_kid_control = new Bank_kid_control(kid);
        ShopController = new ShopController(kid);
        task_kid_control = new Task_kid_control(kid);
        message_kid_controller = new Message_kid_controller(kid);

    }

    public Kids getKid(){
        return kid;
    }


}
