package GUI;
import Controller.MainController;
import GUI.bank_page.Bank_kid;
import GUI.message_page.message_kid;
import GUI.shop_page.Shop_kid;
import GUI.task_page.Task_kid;

import javax.swing.*;
import java.awt.*;


//public class MainFrame extends JFrame implements RefreshListener{
public class MainFrame_kid extends JFrame {
    private BorderLayout borderLayout;
    private JPanel main_page;
    private JPanel menu;
    private JLabel button1, button2, button3, button4;
    private JPanel current_panel;
    private JPanel pg1, pg2,pg3,pg4;
    private MainController mainController;
    private Bank_kid bankKid;
    private Shop_kid shopKid;
    private Task_kid taskKid;
//    private Message_kid messageKid;

    public MainFrame_kid(MainController mainController) {
        super("demo");
        this.mainController = mainController;
        mainController.mainFrameController.setGUI(this);
        InitiateAll();
        current_panel = pg1;
        frame_panel();
        navi_button();
        mainController.mainFrameController.addButtonListener();
        mainController.mainFrameController.addFrameListener();

        setVisible(true);
    }

    private void InitiateAll(){
        this.pg1 = new Bank_kid(mainController.bank_kid_control, this);
        this.pg2 = new Shop_kid(mainController.ShopController);
        this.pg3 = new Task_kid(mainController.task_kid_control, this);
        this.pg4 = new message_kid(mainController.message_kid_controller);
    }


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





//    @Override
    public void refresh() {
        int tempIndex = 0;
        main_page.remove(current_panel);
        if(current_panel == pg1){
            tempIndex = 1;
        }
        else if (current_panel == pg2) {
            tempIndex =2;

        }
        else if(current_panel == pg3){
            tempIndex = 3;
        }
        else if(current_panel == pg4){
            tempIndex = 4;
        }
        else{
            tempIndex = 1;
        }
        InitiateAll();



//        this.pg3 = new Task_kid(this);

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
// 如果没有确定的索引，则默认显示 pg1
                current_panel = pg1;
                break;
        }

        main_page.add(current_panel, BorderLayout.CENTER);
        revalidate();
        repaint();

    }


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

    public void frame_panel(){
        // 设置主窗口的标题

        this.setSize(960,540);
        borderLayout = new BorderLayout();
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
    public void navi_button() {
        // Initialize buttons with their default icons
        // Initialize buttons with their specific icon sizes
        button1 = createButtonWithSize("bank_white");  // Larger size for button1
        button2 = createButtonWithSize("shop");  // Smaller size for button2
        button3 = createButtonWithSize("task");  // Standard size for button3
        button4 = createButtonWithSize("message");  // Standard size for button4

        // Set layout for the menu panel
        menu.setLayout(new GridLayout(15, 1, 0, 5)); // Adjust grid layout for spacing if needed

        // Add buttons to the menu panel
        menu.add(new JLabel()); // Add spacing if needed
        menu.add(button1);
        menu.add(new JLabel()); // Add spacing between buttons
        menu.add(button2);
        menu.add(new JLabel()); // Add spacing between buttons
        menu.add(button3);
        menu.add(new JLabel()); // Add spacing between buttons
        menu.add(button4);
    }

    public JLabel createButtonWithSize(String iconName) {
        int width = 25; // 默认宽度和高度
        int height = 25;

        if (iconName.equals("bank")||iconName.equals("bank_white")) {
            width = height = 32; // bank图标的特定尺寸
        } else if (iconName.equals("shop")||iconName.equals("shop_white")) {
            width = height = 20; // shop图标的特定尺寸
        }

        // 路径可能包括选中状态的特定图标
        String iconPath = "image/" + iconName +  ".png";
        ImageIcon icon = new ImageIcon(iconPath);
        Image img = icon.getImage();
        Image scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);

        JLabel button = new JLabel(new ImageIcon(scaledImg));
        button.setHorizontalAlignment(SwingConstants.CENTER);
        return button;
    }

    public static void main(String[] args) {
        MainFrame_kid main = new MainFrame_kid(new MainController("222"));
    }

}