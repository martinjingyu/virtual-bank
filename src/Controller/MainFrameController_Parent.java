package Controller;

import Entity.Kids;
import GUI.MainFrame_kid;
import GUI.MainFrame_parent;
import utill.write.WriteAll;

import javax.swing.*;
import java.awt.event.*;

public class MainFrameController_Parent extends MouseAdapter {
    private MainFrame_parent mainFrameParent;
    private String ID;
    private Kids kid;
    public MainFrameController_Parent(String id, Kids kid) {
        this.ID = id;
        this.kid = kid;
    }
    public void setGUI(MainFrame_parent mainFrameParent){
        this.mainFrameParent = mainFrameParent;
    }
    public MainFrameController_Parent(MainFrame_parent mainFrameParent) {
        this.mainFrameParent = mainFrameParent;
        mainFrameParent.getButton(1).addMouseListener(this);
        mainFrameParent.getButton(2).addMouseListener(this);
        mainFrameParent.getButton(3).addMouseListener(this);
        mainFrameParent.getButton(4).addMouseListener(this);
    }
    public void addButtonListener(){
        mainFrameParent.getButton(1).addMouseListener(this);
        mainFrameParent.getButton(2).addMouseListener(this);
        mainFrameParent.getButton(3).addMouseListener(this);
        mainFrameParent.getButton(4).addMouseListener(this);
    }
    public void addFrameListener(){
        mainFrameParent.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                int option = JOptionPane.showConfirmDialog(mainFrameParent, "Are you sure you want to exit?", "Exit Confirmation", JOptionPane.YES_NO_OPTION);
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
        if(e.getSource()== mainFrameParent.getButton(1)){
            System.out.println("asaaaa");
            mainFrameParent.changePanel(1);
        }
        else if (e.getSource()== mainFrameParent.getButton(2)) {
            System.out.println("bbbaa");
            mainFrameParent.changePanel(2);
        }
        else if (e.getSource()== mainFrameParent.getButton(3)) {
            mainFrameParent.changePanel(3);
        }
        else if (e.getSource()== mainFrameParent.getButton(4)) {
            mainFrameParent.changePanel(4);
        }


    }
    public void WriteKidToFile(){
        WriteAll.writeAll(this.ID, this.kid);
    }
}
