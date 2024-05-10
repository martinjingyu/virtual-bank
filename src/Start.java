
// [pwd]:4/25：作为启动文件，用于启动整个项目

import Controller.MainController;
import Entity.Kids;
import GUI.MainFrame;
import GUI.log_in.LoginListener;
import utill.read.ReadAll;

import javax.swing.*;

public class Start implements LoginListener {
    public static void main(String[] args) {
        GUI.log_in.GUIMain ui = new GUI.log_in.GUIMain();
        ui.createAndShowGUI();
        ui.setLoginListener(new Start());
    }
    public void onLogin(String id) {
        // 创建并显示新的窗口
        MainController mainController = new MainController(id);
        MainFrame mainFrame = new MainFrame(mainController);
    }
}