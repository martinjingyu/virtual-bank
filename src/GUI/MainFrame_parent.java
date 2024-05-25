package GUI;

import Controller.MainController_Parent;

import GUI.bank_page.bank_parents;
import GUI.message_page.message_parent;
import GUI.shop_page.Shop_kid;
import GUI.shop_page.Shop_parent;
import GUI.task_page.Task_parent;

import javax.swing.*;
import java.awt.*;

/**
 * The MainFrame_parent class represents the main frame for the parent user interface.
 * It contains panels for various functionalities such as banking, shopping, tasks, and messages.
 */
public class MainFrame_parent extends JFrame {
    private JPanel main_page;
    private JPanel menu;
    private JLabel button1, button2, button3, button4;
    private JPanel current_panel;
    private JPanel pg1, pg2, pg3, pg4;
    private MainController_Parent mainController_parent;

    /**
     * Constructs a new MainFrame_parent object with the specified MainController_Parent.
     *
     * @param mainController The MainController_Parent object associated with this MainFrame_parent.
     */
    public MainFrame_parent(MainController_Parent mainController) {
        super("demo");
        this.mainController_parent = mainController;
        InitiateAll();
        current_panel = this.pg1;
        frame_panel();
        navi_button();

        mainController.mainFrameController_parent.setGUI(this);
        mainController.mainFrameController_parent.addButtonListener();
        mainController.mainFrameController_parent.addFrameListener();

        setVisible(true);
    }

    /**
     * Initializes all the panels for banking, shopping, tasks, and messages.
     */
    private void InitiateAll() {
        this.pg1 = new bank_parents(mainController_parent.bank_parent_controller);
        this.pg2 = new Shop_parent(mainController_parent.shopParentController);
        this.pg3 = new Task_parent(mainController_parent.task_parent_control, this);
        this.pg4 = new message_parent(mainController_parent.messageParentController);
    }

    /**
     * Changes the current panel based on the specified index.
     *
     * @param pgIndex The index of the panel to be displayed.
     */
    public void changePanel(int pgIndex) {
        main_page.remove(current_panel);
        switch (pgIndex) {
            case 1:
                current_panel = pg1;
                break;
            case 2:
                current_panel = pg2;
                break;
            case 3:
                current_panel = pg3;
                break;
            case 4:
                current_panel = pg4;
                break;
            default:
                current_panel = pg1;
                break;
        }
        main_page.add(current_panel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    /**
     * Refreshes the user interface by updating the panels.
     */
    public void refresh() {
        int tempIndex = 0;
        main_page.remove(current_panel);
        if (current_panel == pg1) {
            tempIndex = 1;
        } else if (current_panel == pg2) {
            tempIndex = 2;

        } else if (current_panel == pg3) {
            tempIndex = 3;
        } else if (current_panel == pg4) {
            tempIndex = 4;
        } else {
            tempIndex = 1;
        }
        InitiateAll();


        switch (tempIndex) {
            case 1:
                current_panel = pg1;
                break;
            case 2:
                current_panel = pg2;
                break;
            case 3:
                current_panel = pg3;
                break;
            case 4:
                current_panel = pg4;
                break;
            default:
                current_panel = pg1;
                break;
        }

        main_page.add(current_panel, BorderLayout.CENTER);
        revalidate();
        repaint();

    }

    /**
     * Retrieves the button corresponding to the specified index.
     *
     * @param index The index of the button.
     * @return The JLabel representing the button.
     */
    public JLabel getButton(int index) {
        switch (index) {
            case 1:
                return button1;
            case 2:
                return button2;
            case 3:
                return button3;
            case 4:
                return button4;
            default:
                return button1;
        }
    }

    /**
     * Sets up the main frame panel layout.
     */
    public void frame_panel() {
        this.setSize(960, 540);
        this.setResizable(false);
        menu = new JPanel();
        main_page = new JPanel(new BorderLayout());

        menu.setBackground(Color.pink);
        main_page.setBackground(Color.blue);

        menu.setPreferredSize(new Dimension(51, 540));

        this.add(main_page, BorderLayout.CENTER);

        this.add(menu, BorderLayout.WEST);
        main_page.add(current_panel, BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

    }

    /**
     * Sets up the navigation buttons in the menu panel.
     */
    public void navi_button() {
        button1 = createButtonWithSize("bank_white");
        button2 = createButtonWithSize("shop");
        button3 = createButtonWithSize("task");
        button4 = createButtonWithSize("message");

        menu.setLayout(new GridLayout(15, 1, 0, 5));

        menu.add(new JLabel());
        menu.add(button1);
        menu.add(new JLabel());
        menu.add(button2);
        menu.add(new JLabel());
        menu.add(button3);
        menu.add(new JLabel());
        menu.add(button4);
    }

    /**
     * Creates a navigation button with the specified icon size.
     *
     * @param iconName The name of the icon.
     * @return The JLabel representing the button with the icon.
     */
    public JLabel createButtonWithSize(String iconName) {
        int width = 25;
        int height = 25;

        if (iconName.equals("bank") || iconName.equals("bank_white")) {
            width = height = 32;
        } else if (iconName.equals("shop") || iconName.equals("shop_white")) {
            width = height = 20;
        }

        String iconPath = "image/" + iconName + ".png";
        ImageIcon icon = new ImageIcon(iconPath);
        Image img = icon.getImage();
        Image scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);

        JLabel button = new JLabel(new ImageIcon(scaledImg));
        button.setHorizontalAlignment(SwingConstants.CENTER);
        return button;
    }

    /**
     * Main method to create and display the MainFrame_parent.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        MainFrame_parent main = new MainFrame_parent(new MainController_Parent("222"));
    }
}
