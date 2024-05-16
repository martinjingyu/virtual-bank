package GUI;
import Controller.MainController;
import Controller.MainController_Parent;
import GUI.bank_page.Bank_kid;
import GUI.bank_page.bank_parents;
import GUI.message_page.message_parent;
import GUI.shop_page.Shop_kid;
import GUI.shop_page.Shop_parent;
import GUI.task_page.Task_kid;
import GUI.task_page.Task_parent;

import javax.swing.*;
import java.awt.*;

public class MainFrame_parent extends JFrame {
    private BorderLayout borderLayout;
    private JPanel main_page;
    private JPanel menu;
    private JLabel button1, button2, button3, button4;
    private JPanel current_panel;
    private JPanel pg1, pg2,pg3,pg4;
    private MainController_Parent mainController_parent;

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
    private void InitiateAll(){
        this.pg1 = new bank_parents(mainController_parent.bank_parent_controller);
        this.pg2 = new Shop_parent(mainController_parent.shopParentController);
        this.pg3 = new Task_parent(mainController_parent.task_parent_control,this);
        this.pg4 = new message_parent(mainController_parent.messageParentController);

//        this.pg1 = new Bank_kid();
//        this.pg2 = new Shop_kid(mainController.ShopController);
//        this.pg3 = new Task_kid(mainController.task_kid_control, this);
//        this.pg4 = new Shop_kid(mainController.ShopController);
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
        InitiateAll();
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
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    public void navi_button(){

        button1 = new JLabel();
        ImageIcon icon = new ImageIcon("bank.png");
        Image img = icon.getImage();
        Image scaledImg = img.getScaledInstance(25, 25, Image.SCALE_SMOOTH); // 自动调整宽度，高度等比例缩放
        button1.setIcon(new ImageIcon(scaledImg)); // 替换为您的图片路径
        button1.setHorizontalAlignment(SwingConstants.CENTER); // 设置水平居中对齐

        button2 = new JLabel();
        button2.setIcon(new ImageIcon(scaledImg)); // 替换为您的图片路径


        button2.setHorizontalAlignment(SwingConstants.CENTER); // 设置水平居中对齐

        button3 = new JLabel();
        button3.setIcon(new ImageIcon(scaledImg)); // 替换为您的图片路径
        button3.setHorizontalAlignment(SwingConstants.CENTER); // 设置水平居中对齐

        button4 = new JLabel();
        button4.setIcon(new ImageIcon(scaledImg)); // 替换为您的图片路径
        button4.setHorizontalAlignment(SwingConstants.CENTER); // 设置水平居中对齐

        menu.setLayout(new GridLayout(15, 1)); // 竖直排列按钮
        menu.add(new JLabel());
        menu.add(new JLabel());

        menu.add(button1);
        menu.add(new JLabel());

        menu.add(button2);
        menu.add(new JLabel());

        menu.add(button3);
        menu.add(new JLabel());

        menu.add(button4);
        menu.add(new JLabel());


    }

    public static void main(String[] args) {
        MainFrame_parent main = new MainFrame_parent(new MainController_Parent("222"));
    }

}
