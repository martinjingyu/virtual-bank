package Controller;

import Entity.Kids;
import GUI.MainFrame_kid;
import utill.write.WriteAll;

import javax.swing.JOptionPane;
import java.awt.event.*;

public class MainFrameController extends MouseAdapter {
    private MainFrame_kid mainFrameKid;
    private String ID;
    private Kids kid;
    public MainFrameController() {

    }
    public MainFrameController(String id, Kids kid) {
        this.ID = id;
        this.kid = kid;
    }

    public MainFrameController(MainFrame_kid mainFrameKid) {
        this.mainFrameKid = mainFrameKid;
        mainFrameKid.getButton(1).addMouseListener(this);
        mainFrameKid.getButton(2).addMouseListener(this);
        mainFrameKid.getButton(3).addMouseListener(this);
        mainFrameKid.getButton(4).addMouseListener(this);
    }
    public void setGUI(MainFrame_kid mainFrameKid){
        this.mainFrameKid = mainFrameKid;
    }
    public void addButtonListener(){
        mainFrameKid.getButton(1).addMouseListener(this);
        mainFrameKid.getButton(2).addMouseListener(this);
        mainFrameKid.getButton(3).addMouseListener(this);
        mainFrameKid.getButton(4).addMouseListener(this);
    }
    public void addFrameListener(){
        mainFrameKid.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                int option = JOptionPane.showConfirmDialog(mainFrameKid, "Are you sure you want to exit?", "Exit Confirmation", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    // 用户确认退出，执行写入数据的程序

                    WriteKidToFile();

                    // 关闭程序
                    System.exit(0);
                }
            }
        });
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource()== mainFrameKid.getButton(1)){
            mainFrameKid.changePanel(1);
        }
        else if (e.getSource()== mainFrameKid.getButton(2)) {
            mainFrameKid.changePanel(2);
        }
        else if (e.getSource()== mainFrameKid.getButton(3)) {
            mainFrameKid.changePanel(3);
        }
        else if (e.getSource()== mainFrameKid.getButton(4)) {
            mainFrameKid.changePanel(4);
        }

    }
    public void WriteKidToFile(){
        WriteAll.writeAll(this.ID, this.kid);
    }
}
