package Controller;

import GUI.MainFrame;
import java.awt.event.*;

public class MainFrameController extends MouseAdapter {
    private MainFrame mainFrame;

    public MainFrameController(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        mainFrame.getButton(1).addMouseListener(this);
        mainFrame.getButton(2).addMouseListener(this);
        mainFrame.getButton(3).addMouseListener(this);
        mainFrame.getButton(4).addMouseListener(this);
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource()==mainFrame.getButton(1)){
            mainFrame.changePanel(1);
        }
        else if (e.getSource()==mainFrame.getButton(2)) {
            mainFrame.changePanel(2);
        }
        else if (e.getSource()==mainFrame.getButton(3)) {
            mainFrame.changePanel(3);
        }
        else if (e.getSource()==mainFrame.getButton(4)) {
            mainFrame.changePanel(4);
        }


    }
}
