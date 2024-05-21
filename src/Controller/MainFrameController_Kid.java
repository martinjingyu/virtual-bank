package Controller;

import Entity.Kids;
import GUI.MainFrame_kid;
import utill.write.WriteAll;

import javax.swing.*;
import java.awt.event.*;

public class MainFrameController_Kid extends MouseAdapter {
    private MainFrame_kid mainFrameKid;
    private String ID;
    private Kids kid;
    private JLabel currentSelectedButton;

    public MainFrameController_Kid(String id, Kids kid) {
        this.ID = id;
        this.kid = kid;

    }

    public void setGUI(MainFrame_kid mainFrameKid) {
        this.mainFrameKid = mainFrameKid;
    }

    public void addButtonListener() {
        mainFrameKid.getButton(1).addMouseListener(this);
        mainFrameKid.getButton(2).addMouseListener(this);
        mainFrameKid.getButton(3).addMouseListener(this);
        mainFrameKid.getButton(4).addMouseListener(this);
    }

    private int getPanelIndex(JLabel button) {
        if (button == mainFrameKid.getButton(1)) return 1;
        if (button == mainFrameKid.getButton(2)) return 2;
        if (button == mainFrameKid.getButton(3)) return 3;
        if (button == mainFrameKid.getButton(4)) return 4;
        return 0; // Default case or error
    }

    public void addFrameListener() {
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

    // Method to update icons for selected button
    public void updateIcons(JLabel selectedButton) {
        if (currentSelectedButton != selectedButton) {
            // Reset all buttons to normal icon
            resetAllIcons();
            // Update the icon for the new selected button
            currentSelectedButton = selectedButton;
            String iconName = getIconNameForButton(selectedButton);
            selectedButton.setIcon(mainFrameKid.createButtonWithSize(iconName + "_white").getIcon());
        }
    }

    private void resetIcon(JLabel button, String iconName) {
//        button.setIcon(new ImageIcon("image/" + iconName + ".png"));
        button.setIcon(mainFrameKid.createButtonWithSize(iconName).getIcon());
    }

    private String getIconNameForButton(JLabel button) {
        if (button == mainFrameKid.getButton(1)) return "bank";
        if (button == mainFrameKid.getButton(2)) return "shop";
        if (button == mainFrameKid.getButton(3)) return "task";
        if (button == mainFrameKid.getButton(4)) return "message";
        return "";
    }

    private void resetAllIcons() {
        resetIcon(mainFrameKid.getButton(1), "bank");
        resetIcon(mainFrameKid.getButton(2), "shop");
        resetIcon(mainFrameKid.getButton(3), "task");
        resetIcon(mainFrameKid.getButton(4), "message");
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JLabel clickedButton = (JLabel) e.getSource();
        updateIcons(clickedButton);
        mainFrameKid.changePanel(getPanelIndex(clickedButton));
    }


    public void WriteKidToFile() {
        WriteAll.writeAll(this.ID, this.kid);
    }

}