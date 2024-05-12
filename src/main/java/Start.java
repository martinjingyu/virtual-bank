
// [pwd]:4/25：作为启动文件，用于启动整个项目

import Controller.MainController;
import GUI.MainFrame_kid;
import GUI.MainFrame_parent;
import GUI.log_in.LoginListener;

public class Start implements LoginListener {
    public static void main(String[] args) {
        GUI.log_in.GUIMain ui = new GUI.log_in.GUIMain();
        ui.createAndShowGUI();
        ui.setLoginListener(new Start());
    }
    public void onLogin_kid(String id) {
        // 创建并显示新的窗口
        MainController mainController = new MainController(id);

        MainFrame_kid mainFrameKid = new MainFrame_kid(mainController);
    }
    public void onLogin_parent(String id){
        MainController mainController = new MainController(id);

        MainFrame_parent mainFrameParent = new MainFrame_parent(mainController);
    }
}