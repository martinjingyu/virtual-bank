package Controller;

import GUI.MainFrame_kid;
import GUI.MainFrame_parent;
import GUI.log_in.LoginListener;

import java.util.Locale;

/**
 * The Start class serves as the entry point for the application. It creates the login GUI and listens for login events.
 */
public class Start implements LoginListener {

    /**
     * The main method, entry point of the application.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        Locale.setDefault(Locale.ENGLISH);
        GUI.log_in.GUIMain ui = new GUI.log_in.GUIMain();
        ui.createAndShowGUI();
        ui.setLoginListener(new Start());
    }

    /**
     * Called when a kid logs in.
     *
     * @param id The ID of the logged-in kid.
     */
    public void onLogin_kid(String id) {
        // Create and display a new window
        MainController_Kid mainControllerKid = new MainController_Kid(id);
        System.out.println(mainControllerKid.bank_kid_control.getKid().getTransactionList().getAllTransactions());
        MainFrame_kid mainFrameKid = new MainFrame_kid(mainControllerKid);
    }

    /**
     * Called when a parent logs in.
     *
     * @param id The ID of the logged-in parent.
     */
    public void onLogin_parent(String id) {
        MainController_Parent mainController = new MainController_Parent(id);
        MainFrame_parent mainFrameParent = new MainFrame_parent(mainController);
    }
}
