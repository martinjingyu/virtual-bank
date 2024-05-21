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
    private JLabel currentSelectedButton = null;

    public MainFrameController_Parent(String id, Kids kid) {
        this.ID = id;
        this.kid = kid;
    }
    public void setGUI(MainFrame_parent mainFrameParent){
        this.mainFrameParent = mainFrameParent;
        addButtonListener();
    }

    public void addButtonListener(){
        mainFrameParent.getButton(1).addMouseListener(this);
        mainFrameParent.getButton(2).addMouseListener(this);
        mainFrameParent.getButton(3).addMouseListener(this);
        mainFrameParent.getButton(4).addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JLabel clickedButton = (JLabel) e.getSource();
        updateIcons(clickedButton);
        mainFrameParent.changePanel(getPanelIndex(clickedButton));
    }

    private int getPanelIndex(JLabel button) {
        if(button == mainFrameParent.getButton(1)) return 1;
        if(button == mainFrameParent.getButton(2)) return 2;
        if(button == mainFrameParent.getButton(3)) return 3;
        if(button == mainFrameParent.getButton(4)) return 4;
        return 0; // Default case or error
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

    // Method to update icons based on the selected button
    public void updateIcons(JLabel selectedButton) {
        if (currentSelectedButton != selectedButton) {
            // Reset all buttons to normal icon
            resetAllIcons();
            // Update the icon for the new selected button
            currentSelectedButton = selectedButton;
            String iconName = getIconNameForButton(selectedButton);
            selectedButton.setIcon(mainFrameParent.createButtonWithSize(iconName+"_white").getIcon());

        }
    }

    private void resetIcon(JLabel button, String iconName,int width, int height) {
//        button.setIcon(new ImageIcon("image/" + iconName + ".png"));
        button.setIcon(mainFrameParent.createButtonWithSize(iconName).getIcon());
    }

    private String getIconNameForButton(JLabel button) {
        if (button == mainFrameParent.getButton(1)) return "bank";
        if (button == mainFrameParent.getButton(2)) return "shop";
        if (button == mainFrameParent.getButton(3)) return "task";
        if (button == mainFrameParent.getButton(4)) return "message";
        return "";
    }

    private void resetAllIcons() {
        resetIcon(mainFrameParent.getButton(1), "bank",30,30);
        resetIcon(mainFrameParent.getButton(2), "shop",20,20);
        resetIcon(mainFrameParent.getButton(3), "task",25,25);
        resetIcon(mainFrameParent.getButton(4), "message",25,25);
    }

    public void WriteKidToFile(){
        WriteAll.writeAll(this.ID, this.kid);
    }
}
