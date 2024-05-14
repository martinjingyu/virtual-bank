package GUI.bank_page;

import Controller.bank.Bank_parent_controller;
import Entity.Kids;
import GUI.MainFrame_parent;
import utill.read.ReadAll;

import javax.swing.*;
import java.awt.*;

public class bank_parents extends JPanel{
    private JPanel bank;
    private JButton currentDetails;
    private JButton savingDetails;
    private JButton button_history;
    private JButton button_transfer;
    private JButton button_yes;
    private JButton button_no;
    private Kids kid;
    private bank_parents GUI;
    private MainFrame_parent mainFrameParent;
    private Bank_parent_controller bank_parent_controller;
    private final Color mainBgColor = new Color(191, 221, 239); // #bfddef
    private final Color panelBgColor = new Color(239, 239, 239); // #EFEFEF
    private final Color fontColor = new Color(49, 122, 232); // #317AE8
    public MainFrame_parent getMainFrame(){return this.mainFrameParent;}
    public bank_parents(Bank_parent_controller bank_parent_controller) {
//        this.mainFrameParent = mainFrameParent;
//        Bank_parent_controller bank_parent_controller = new Bank_parent_controller(kid);
        bank_parent_controller.setGUI(this);
        setLayout(null); // 使用绝对布局
        setBackground(mainBgColor);

        currentDetails = new JButton("Details");
        currentDetails.setBounds(590, 190, 140, 30);
//        bank_parent_controller.addCurrentDetails(currentDetails);
        add(currentDetails);

        savingDetails = new JButton("Details");
        savingDetails.setBounds(590, 310, 140, 30);
//        bank_kid_control.addSavingAccountListener(button3);
        add(savingDetails);

        button_history = new JButton("Review");
        button_history.setBounds(590, 420, 140, 30);
        bank_parent_controller.addHistory(button_history);
        add(button_history);

    }

    public void paintComponent(Graphics g) {
        setLayout(null); // 使用绝对布局
        setBackground(mainBgColor);

        Font titleFont = new Font("Arial", Font.PLAIN, 20);
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.fillRect(70, 40, 350, 80); // 绘制顶部白色矩形框
        g.setColor(Color.black);
        g.setFont(titleFont);
        g.drawString("Income", 150, 60); // 在框内写字
        g.drawString("Expenses", 280, 60);
//        g.drawString("+50.00", 150, 100); // 在下方显示 +50
//        g.drawString("-20.00", 280, 100);

        g.setColor(Color.WHITE);
        g.fillRect(500, 40, 270, 80);
        g.setColor(Color.black);
        g.setFont(titleFont);
        g.drawString("Saving Goals", 610, 60);
//        g.drawString(kid.getBank().getSavingTotal(),610,100);
//        g.drawString("300.00", 610, 100);

        g.setColor(Color.WHITE);
        g.fillRect(70, 150, 700, 90);
        g.setColor(Color.black);
        g.setFont(titleFont);
        g.drawString("Current", 90, 220);
//        g.drawString("Interest Rate", 150, 170);
        g.drawString("Total", 280, 170);
//        g.drawString("0.0%", 150, 220);
//        g.drawString("60.00", 280, 220);

        g.setColor(Color.WHITE);
        g.fillRect(70, 270, 700, 90);
        g.setColor(Color.black);
        g.setFont(titleFont);
        g.drawString("Saving", 90, 340);
//        g.drawString("Interest Rate", 150, 290);
        g.drawString("Total", 280, 290);
//        g.drawString("0.0%", 150, 340);
//        g.drawString("60.00", 280, 340);


        g.setColor(Color.WHITE);
        g.fillRect(70, 390, 700, 80);
        g.setColor(Color.black);
        g.setFont(titleFont);
        g.drawString("Transaction History", 90, 440);
    }

    //    initData(){
//        kid.getBank().getSavingTotal();
//
//    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Kids kid = ReadAll.readall(String.valueOf(222));
                bank_parents panel = new bank_parents(new Bank_parent_controller(kid));
//                Bank_parent_controller controller = new Bank_parent_controller(kid,panel);
                JFrame frame = new JFrame("Bank Parents Panel");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(800, 600);
                frame.getContentPane().add(panel);
                frame.setVisible(true);
            }
        });
    }


//    public static void main(String[] args) {
//        Kids kid = ReadAll.readall(String.valueOf(222));
//        Bank_parent_controller bankParentController = new Bank_parent_controller(kid,bank_parents);
//        JFrame frame = new JFrame("Shop Application");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setContentPane(new bank_parents(kid,new Bank_parent_controller(kid,bank_parents)));
//        frame.pack();
//        frame.setVisible(true);
//    }

//    @Override
//    public void actionPerformed(ActionEvent e) {
//
//    }
}
