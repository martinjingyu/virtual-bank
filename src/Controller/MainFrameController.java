package Controller;

import GUI.MainFrame_kid;
import java.awt.event.*;

public class MainFrameController extends MouseAdapter {
    private MainFrame_kid mainFrameKid;
    public MainFrameController() {

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
}
