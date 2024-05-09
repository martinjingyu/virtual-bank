/**
 * Title      : GUIMain.java
 * Description: This class is used to generate main frame of the login page.
 * Copyright  : Copyright (c) 2024/5/9
 * @author      Weida Peng
 * @version     1.0
 */
package GUI.log_in;

import javax.swing.*;
import Controller.login.ButtonControl;
import java.awt.*;

class DownPanel extends JPanel {
    private Image downImage;

    public DownPanel(String imagePath) {
        this.downImage = new ImageIcon(imagePath).getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(downImage, 0, 0, getWidth(), getHeight(), this);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setComposite(AlphaComposite.SrcOver.derive(0.35f));
        g2d.setColor(getBackground());
        g2d.fillRect(0, 0, getWidth(), getHeight());
        g2d.dispose();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(800, 30);
    }
}

class TransparentPanel extends JPanel {
    private Image backgroundImage;

    public TransparentPanel(String imagePath) {
        this.backgroundImage = new ImageIcon(imagePath).getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setComposite(AlphaComposite.SrcOver.derive(0.35f));
        g2d.setColor(getBackground());
        g2d.fillRect(0, 0, getWidth(), getHeight());
        g2d.dispose();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(800, 200);
    }
}

public class GUIMain {
    public LoginListener loginListener;

    public JFrame frame;
    public JPanel cardPanel;
    public CardLayout cardLayout;

    // public static void main(String[] args) {
    // GUIMain ui = new GUIMain();
    // ui.createAndShowGUI();
    // }
    public void setLoginListener(LoginListener listener) {
        this.loginListener = listener;
    }

    public void createAndShowGUI() {
        frame = new JFrame("Bank");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1100, 700);

        TransparentPanel upPanel = new TransparentPanel("image/bank.jpg");
        upPanel.setLayout(null);

        JButton help = new JButton(new ImageIcon("image/question_mark.jpg"));
        help.setBounds(950, 0, 77, 35);
        int width = help.getWidth();
        int height = help.getHeight();
        ImageIcon icon = (ImageIcon) help.getIcon();
        Image img = icon.getImage();
        Image scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImg);
        help.setIcon(scaledIcon);
        ButtonControl.addMouseListener(help, 1);
        // help.addMouseListener(new MouseAdapter() {
        // @Override
        // public void mouseClicked(MouseEvent e) {
        // JOptionPane.showMessageDialog(null, "If you have any question, please phone
        // me : #123456789", "HELP",
        // JOptionPane.INFORMATION_MESSAGE);
        // }
        // });

        JButton exit = new JButton(new ImageIcon("image/CLOSE.jpg"));
        exit.setFont(new Font("Times New Roman", Font.BOLD, 60));
        exit.setBounds(1020, 0, 68, 40);

        width = exit.getWidth();
        height = exit.getHeight();
        icon = (ImageIcon) exit.getIcon();
        img = icon.getImage();
        scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        scaledIcon = new ImageIcon(scaledImg);
        exit.setIcon(scaledIcon);
        ButtonControl.addMouseListener(exit, 2);
        // exit.addMouseListener(new MouseAdapter() {
        // @Override
        // public void mouseClicked(MouseEvent e) {
        // System.exit(0);
        // }
        // });

        cardPanel = new JPanel();
        cardLayout = new CardLayout();
        cardPanel.setLayout(cardLayout);

        DownPanel downPanel = new DownPanel("image/white.jpg");
        downPanel.setLayout(null);
        downPanel.add(help);
        downPanel.add(exit);
        // frame.add(upPanel, BorderLayout.NORTH);
        frame.add(cardPanel, BorderLayout.CENTER);
        frame.add(downPanel, BorderLayout.SOUTH);
        frame.setVisible(true);

        Basic_login.addbasicPanel(cardPanel, cardLayout, this);
        Parent.addParentPanel(cardPanel, cardLayout, this, frame);
        Children.addChildrenPanel(cardPanel, cardLayout, this);
        Parent_main.addParent_mainPanel(cardPanel, cardLayout, this);
        Error.addErrorPanel(cardPanel, cardLayout, this);
        Children_main.addChildren_mainPanel(cardPanel, cardLayout, this);
        Remain.addRemainPanel(cardPanel, cardLayout, this);
        Sign.addSignPanel(cardPanel, cardLayout, this);
    }

    public void showCard(String cardName) {
        cardLayout.show(cardPanel, cardName);
    }
}
