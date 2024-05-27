package Controller;

import Entity.Kids;
import GUI.MainFrame_kid;
import utill.write.WriteAll;

import javax.swing.*;
import java.awt.event.*;

/**
 * MainFrameController_Kid is the controller class for the MainFrame_kid GUI.
 * It handles user interactions and updates the view accordingly.
 */
public class MainFrameController_Kid extends MouseAdapter {
    private MainFrame_kid mainFrameKid;
    private String ID;
    private Kids kid;
    private JLabel currentSelectedButton;

    /**
     * Constructs a MainFrameController_Kid with the specified kid ID and Kids object.
     *
     * @param id The ID of the kid.
     * @param kid The Kids object containing the kid's data.
     */
    public MainFrameController_Kid(String id, Kids kid) {
        this.ID = id;
        this.kid = kid;
    }

    /**
     * Sets the GUI for this controller.
     *
     * @param mainFrameKid The MainFrame_kid GUI.
     */
    public void setGUI(MainFrame_kid mainFrameKid) {
        this.mainFrameKid = mainFrameKid;
    }

    /**
     * Adds mouse listeners to the buttons in the GUI.
     */
    public void addButtonListener() {
        mainFrameKid.getButton(1).addMouseListener(this);
        mainFrameKid.getButton(2).addMouseListener(this);
        mainFrameKid.getButton(3).addMouseListener(this);
        mainFrameKid.getButton(4).addMouseListener(this);
    }

    /**
     * Gets the panel index corresponding to the specified button.
     *
     * @param button The button whose panel index is to be determined.
     * @return The panel index of the specified button.
     */
    private int getPanelIndex(JLabel button) {
        if (button == mainFrameKid.getButton(1)) return 1;
        if (button == mainFrameKid.getButton(2)) return 2;
        if (button == mainFrameKid.getButton(3)) return 3;
        if (button == mainFrameKid.getButton(4)) return 4;
        return 0; // Default case or error
    }

    /**
     * Adds a window listener to the main frame to handle window closing events.
     */
    public void addFrameListener() {
        mainFrameKid.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                int option = JOptionPane.showConfirmDialog(mainFrameKid, "Are you sure you want to exit?", "Exit Confirmation", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    WriteKidToFile();
                    System.exit(0);
                }
            }
        });
    }

    /**
     * Updates the icons for the selected button and resets icons for other buttons.
     *
     * @param selectedButton The button that was selected.
     */
    public void updateIcons(JLabel selectedButton) {
        if (currentSelectedButton != selectedButton) {
            resetAllIcons();
            currentSelectedButton = selectedButton;
            String iconName = getIconNameForButton(selectedButton);
            selectedButton.setIcon(mainFrameKid.createButtonWithSize(iconName + "_white").getIcon());
        }
    }

    /**
     * Resets the icon of the specified button.
     *
     * @param button The button whose icon is to be reset.
     * @param iconName The base name of the icon.
     */
    private void resetIcon(JLabel button, String iconName) {
        button.setIcon(mainFrameKid.createButtonWithSize(iconName).getIcon());
    }

    /**
     * Gets the icon name for the specified button.
     *
     * @param button The button whose icon name is to be determined.
     * @return The icon name for the specified button.
     */
    private String getIconNameForButton(JLabel button) {
        if (button == mainFrameKid.getButton(1)) return "bank";
        if (button == mainFrameKid.getButton(2)) return "shop";
        if (button == mainFrameKid.getButton(3)) return "task";
        if (button == mainFrameKid.getButton(4)) return "message";
        return "";
    }

    /**
     * Resets the icons for all buttons to their default state.
     */
    private void resetAllIcons() {
        resetIcon(mainFrameKid.getButton(1), "bank");
        resetIcon(mainFrameKid.getButton(2), "shop");
        resetIcon(mainFrameKid.getButton(3), "task");
        resetIcon(mainFrameKid.getButton(4), "message");
    }

    /**
     * Handles mouse click events on the buttons.
     *
     * @param e The mouse event.
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        JLabel clickedButton = (JLabel) e.getSource();
        updateIcons(clickedButton);
        mainFrameKid.InitiateAll();
        mainFrameKid.changePanel(getPanelIndex(clickedButton));
    }

    /**
     * Writes the kid's data to a file.
     */
    public void WriteKidToFile() {
        WriteAll.writeAll(this.ID, this.kid);
    }
}
