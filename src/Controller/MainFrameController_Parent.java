package Controller;

import GUI.MainFrame_kid;
import GUI.MainFrame_parent;

import java.awt.event.*;

public class MainFrameController_Parent extends MouseAdapter {
    private MainFrame_parent mainFrameParent;

    public MainFrameController_Parent(MainFrame_parent mainFrameParent) {
        this.mainFrameParent = mainFrameParent;
        mainFrameParent.getButton(1).addMouseListener(this);
        mainFrameParent.getButton(2).addMouseListener(this);
        mainFrameParent.getButton(3).addMouseListener(this);
        mainFrameParent.getButton(4).addMouseListener(this);
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource()== mainFrameParent.getButton(1)){
            mainFrameParent.changePanel(1);
        }
        else if (e.getSource()== mainFrameParent.getButton(2)) {
            mainFrameParent.changePanel(2);
        }
        else if (e.getSource()== mainFrameParent.getButton(3)) {
            mainFrameParent.changePanel(3);
        }
        else if (e.getSource()== mainFrameParent.getButton(4)) {
            mainFrameParent.changePanel(4);
        }


    }
}
