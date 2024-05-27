package Controller;

import Controller.bank.Bank_parent_controller;
import Controller.message.Message_parent_controller;
import Controller.shop.ShopParentController;
import Controller.task.Task_parent_control;
import Entity.Kids;
import utill.read.ReadAll;

/**
 * The MainController_Kid class is the central controller for managing different aspects of a kid's account,
 * including banking, shopping, tasks, and messages. It initializes and coordinates the various controllers
 * related to these functionalities.
 */
public class MainController_Parent {
    public MainFrameController_Parent mainFrameController_parent;
    public Bank_parent_controller bank_parent_controller;
    public Task_parent_control task_parent_control;
    public ShopParentController shopParentController;
    public Message_parent_controller messageParentController;

    /**
     * Constructs a MainController_Kid with the given kid's ID.
     * This initializes all sub-controllers for the kid's banking, shopping, tasks, and messages.
     *
     * @param id The ID of the kid.
     */
    public MainController_Parent(String id){
        try {
            Kids kid = ReadAll.readall(id);
            mainFrameController_parent = new MainFrameController_Parent(id,kid);
            bank_parent_controller = new Bank_parent_controller(kid);
            task_parent_control = new Task_parent_control(kid);
            shopParentController = new ShopParentController(kid);
            messageParentController = new Message_parent_controller(kid);
        }  catch (Exception e) {
            e.printStackTrace();  // 或者其他更具体的错误处理
        }

    }
}
