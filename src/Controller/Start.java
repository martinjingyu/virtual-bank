package Controller;
// [pwd]:4/25：作为启动文件，用于启动整个项目

import GUI.MainFrame_kid;
import GUI.MainFrame_parent;
import GUI.log_in.LoginListener;
import java.util.Locale;

public class Start implements LoginListener {
    public static void main(String[] args) {
        Locale.setDefault(Locale.ENGLISH);
        GUI.log_in.GUIMain ui = new GUI.log_in.GUIMain();
        ui.createAndShowGUI();
        ui.setLoginListener(new Start());
    }
    public void onLogin_kid(String id) {
        // 创建并显示新的窗口
        MainController_Kid mainControllerKid = new MainController_Kid(id);
        System.out.println(mainControllerKid.bank_kid_control.getKid().getTransactionList().getAllTransactions());
        MainFrame_kid mainFrameKid = new MainFrame_kid(mainControllerKid);
    }
    public void onLogin_parent(String id){
        MainController_Parent mainController = new MainController_Parent(id);
        MainFrame_parent mainFrameParent = new MainFrame_parent(mainController);
    }
}