package Controller;

import Controller.bank.Bank_kid_control;
import Controller.message.Message_kid_controller;
import Controller.shop.shopKidController;
import Controller.task.Task_kid_control;
import Entity.Kids;
import utill.read.ReadAll;

/**
 * The MainController_Kid class is the central controller for managing different aspects of a kid's account,
 * including banking, shopping, tasks, and messages. It initializes and coordinates the various controllers
 * related to these functionalities.
 */
public class MainController_Kid {
    public Bank_kid_control bank_kid_control;
    public shopKidController ShopController;
    public MainFrameController_Kid mainFrameControllerKid;
    public Message_kid_controller message_kid_controller;
    public Task_kid_control task_kid_control;

    /**
     * Constructs a MainController_Kid with the given kid's ID.
     * This initializes all sub-controllers for the kid's banking, shopping, tasks, and messages.
     *
     * @param id The ID of the kid.
     */
    public MainController_Kid(String id) {
        try {
            Kids kid = ReadAll.readall(id);
            mainFrameControllerKid = new MainFrameController_Kid(id, kid);

            bank_kid_control = new Bank_kid_control(kid);
            ShopController = new shopKidController(kid);
            task_kid_control = new Task_kid_control(kid);
            message_kid_controller = new Message_kid_controller(kid);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
