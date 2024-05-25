package GUI;

import Controller.MainController_Kid;
import GUI.bank_page.Bank_kid;
import GUI.message_page.message_kid;
import GUI.shop_page.Shop_kid;
import GUI.task_page.Task_kid;

import javax.swing.*;
import java.awt.*;

/**
 * The MainFrame_kid class represents the main frame of the application for kids.
 * It contains navigation buttons and panels for different functionalities like banking, shopping, tasks, and messages.
 */
public class MainFrame_kid extends JFrame {
    private JPanel main_page;
    private JPanel menu;
    private JLabel button1, button2, button3, button4;
    private JPanel current_panel;
    private JPanel pg1, pg2, pg3, pg4;
    private MainController_Kid mainControllerKid;

    /**
     * Constructs a MainFrame_kid object with the specified MainController_Kid.
     * @param mainControllerKid the MainController_Kid object to control the main frame
     */
    public MainFrame_kid(MainController_Kid mainControllerKid) {
        super("demo");
        this.mainControllerKid = mainControllerKid;

        InitiateAll();
        current_panel = pg1;
        frame_panel();
        navi_button();

        mainControllerKid.mainFrameControllerKid.setGUI(this);
        mainControllerKid.mainFrameControllerKid.addButtonListener();
        mainControllerKid.mainFrameControllerKid.addFrameListener();

        setVisible(true);
    }

    /**
     * Initializes all panels for different functionalities.
     */
    public void InitiateAll(){
        this.pg1 = new Bank_kid(mainControllerKid.bank_kid_control, this);
        this.pg2 = new Shop_kid(mainControllerKid.ShopController);
        this.pg3 = new Task_kid(mainControllerKid.task_kid_control, this);
        this.pg4 = new message_kid(mainControllerKid.message_kid_controller);
    }

    /**
     * Refreshes the panels.
     */
    public void refresh(){
        this.pg1 = new Bank_kid(mainControllerKid.bank_kid_control, this);
        this.pg2 = new Shop_kid(mainControllerKid.ShopController);
        this.pg3 = new Task_kid(mainControllerKid.task_kid_control, this);
    }

    /**
     * Changes the current panel based on the index.
     * @param pgIndex the index of the panel to be displayed
     */
    public void changePanel(int pgIndex){
        main_page.remove(current_panel);
        switch (pgIndex){
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
     * Returns the button based on the index.
     * @param index the index of the button
     * @return the JLabel representing the button
     */
    public JLabel getButton(int index){
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
     * Creates the main frame panel.
     */
    public void frame_panel(){
        this.setSize(960,540);
        this.setResizable(false);
        menu = new JPanel();
        main_page = new JPanel(new BorderLayout());

        menu.setBackground(Color.pink);
        main_page.setBackground(Color.blue);

        menu.setPreferredSize(new Dimension(51, 540));

        this.add(main_page,BorderLayout.CENTER);
        this.add(menu, BorderLayout.WEST);
        main_page.add(current_panel,BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    }

    /**
     * Creates navigation buttons.
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
     * Creates a button with a specific icon size.
     * @param iconName the name of the icon
     * @return the JLabel representing the button with the specified icon size
     */
    public JLabel createButtonWithSize(String iconName) {
        int width = 25;
        int height = 25;

        if (iconName.equals("bank") || iconName.equals("bank_white")) {
            width = height = 32;
        } else if (iconName.equals("shop") || iconName.equals("shop_white")) {
            width = height = 20;
        }

        String iconPath = "image/" + iconName +  ".png";
        ImageIcon icon = new ImageIcon(iconPath);
        Image img = icon.getImage();
        Image scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);

        JLabel button = new JLabel(new ImageIcon(scaledImg));
        button.setHorizontalAlignment(SwingConstants.CENTER);
        return button;
    }

    /**
     * The main method to run the MainFrame_kid GUI.
     * @param args command line arguments
     */
    public static void main(String[] args) {
        MainFrame_kid main = new MainFrame_kid(new MainController_Kid("222"));
    }
}
