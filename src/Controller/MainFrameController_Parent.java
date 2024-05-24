package Controller;

import Entity.Kids;
import GUI.MainFrame_kid;
import GUI.MainFrame_parent;
import utill.write.WriteAll;

import javax.swing.*;
import java.awt.event.*;

/**
 * MainFrameController_Parent is the controller class for the MainFrame_parent GUI.
 * It handles user interactions and updates the view accordingly.
 */
public class MainFrameController_Parent extends MouseAdapter {
    private MainFrame_parent mainFrameParent;
    private String ID;
    private Kids kid;
    private JLabel currentSelectedButton = null;

    /**
     * Constructs a MainFrameController_Parent with the specified parent ID and Kids object.
     *
     * @param id The ID of the parent.
     * @param kid The Kids object containing the kid's data.
     */
    public MainFrameController_Parent(String id, Kids kid) {
        this.ID = id;
        this.kid = kid;
    }

    /**
     * Sets the GUI for this controller.
     *
     * @param mainFrameParent The MainFrame_parent GUI.
     */
    public void setGUI(MainFrame_parent mainFrameParent) {
        this.mainFrameParent = mainFrameParent;
        addButtonListener();
    }

    /**
     * Adds mouse listeners to the buttons in the GUI.
     */
    public void addButtonListener() {
        mainFrameParent.getButton(1).addMouseListener(this);
        mainFrameParent.getButton(2).addMouseListener(this);
        mainFrameParent.getButton(3).addMouseListener(this);
        mainFrameParent.getButton(4).addMouseListener(this);
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
        mainFrameParent.changePanel(getPanelIndex(clickedButton));
    }

    /**
     * Gets the panel index corresponding to the specified button.
     *
     * @param button The button whose panel index is to be determined.
     * @return The panel index of the specified button.
     */
    private int getPanelIndex(JLabel button) {
        if (button == mainFrameParent.getButton(1)) return 1;
        if (button == mainFrameParent.getButton(2)) return 2;
        if (button == mainFrameParent.getButton(3)) return 3;
        if (button == mainFrameParent.getButton(4)) return 4;
        return 0; // Default case or error
    }

    /**
     * Adds a window listener to the main frame to handle window closing events.
     */
    public void addFrameListener() {
        mainFrameParent.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                int option = JOptionPane.showConfirmDialog(mainFrameParent, "Are you sure you want to exit?", "Exit Confirmation", JOptionPane.YES_NO_OPTION);
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
            selectedButton.setIcon(mainFrameParent.createButtonWithSize(iconName + "_white").getIcon());
        }
    }

    /**
     * Resets the icon of the specified button.
     *
     * @param button The button whose icon is to be reset.
     * @param iconName The base name of the icon.
     * @param width The width of the icon.
     * @param height The height of the icon.
     */
    private void resetIcon(JLabel button, String iconName, int width, int height) {
        button.setIcon(mainFrameParent.createButtonWithSize(iconName).getIcon());
    }

    /**
     * Gets the icon name for the specified button.
     *
     * @param button The button whose icon name is to be determined.
     * @return The icon name for the specified button.
     */
    private String getIconNameForButton(JLabel button) {
        if (button == mainFrameParent.getButton(1)) return "bank";
        if (button == mainFrameParent.getButton(2)) return "shop";
        if (button == mainFrameParent.getButton(3)) return "task";
        if (button == mainFrameParent.getButton(4)) return "message";
        return "";
    }

    /**
     * Resets the icons for all buttons to their default state.
     */
    private void resetAllIcons() {
        resetIcon(mainFrameParent.getButton(1), "bank", 30, 30);
        resetIcon(mainFrameParent.getButton(2), "shop", 20, 20);
        resetIcon(mainFrameParent.getButton(3), "task", 25, 25);
        resetIcon(mainFrameParent.getButton(4), "message", 25, 25);
    }

    /**
     * Writes the kid's data to a file.
     */
    public void WriteKidToFile() {
        WriteAll.writeAll(this.ID, this.kid);
    }
}
