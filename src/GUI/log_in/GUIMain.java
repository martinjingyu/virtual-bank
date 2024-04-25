package GUI.log_in;

import javax.swing.*;

import java.awt.*;

import java.awt.event.*;

class DownPanel extends JPanel {
    private Image downImage;

    public DownPanel(String imagePath) {
        this.downImage = new ImageIcon(imagePath).getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // 绘制背景图片
        g.drawImage(downImage, 0, 0, getWidth(), getHeight(), this);

        // 设置透明度
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setComposite(AlphaComposite.SrcOver.derive(0.35f)); // 设置透明度为35%
        g2d.setColor(getBackground());
        g2d.fillRect(0, 0, getWidth(), getHeight());
        g2d.dispose();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(800, 30); // 设置bank的大小为400x300像素
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
        // 绘制背景图片
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);

        // 设置透明度
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setComposite(AlphaComposite.SrcOver.derive(0.35f)); // 设置透明度为35%
        g2d.setColor(getBackground());
        g2d.fillRect(0, 0, getWidth(), getHeight());
        g2d.dispose();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(800, 200); // 设置bank的大小为400x300像素
    }
}

public class GUIMain {

    public JFrame frame;
    public JPanel cardPanel;
    public CardLayout cardLayout;

    // public static void main(String[] args) {
    // GUIMain ui = new GUIMain();
    // ui.createAndShowGUI();
    // } [pwd]:4/25：将启动项外移

    public void createAndShowGUI() {
        frame = new JFrame("Bank");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1100, 700);

        TransparentPanel upPanel = new TransparentPanel("image/bank.jpg");// 更换为你的背景图片路径
        upPanel.setLayout(null);

        JButton help = new JButton(new ImageIcon("image/bank.jpg"));
        help.setBounds(850, 20, 77, 35);
        // 获取按钮的宽度和高度
        int width = help.getWidth();
        int height = help.getHeight();
        // 加载图像
        ImageIcon icon = (ImageIcon) help.getIcon();
        Image img = icon.getImage();
        // 缩放图像到按钮的大小
        Image scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        // 创建新的 ImageIcon 对象，使用缩放后的图像
        ImageIcon scaledIcon = new ImageIcon(scaledImg);
        // 设置按钮的图标为缩放后的图像
        help.setIcon(scaledIcon);
        help.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // 点击图片时弹出另一个窗口显示 "welcome" 文字
                JOptionPane.showMessageDialog(null, "If you have any question, please phone me : #123456789", "HELP",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });

        JButton exit = new JButton(new ImageIcon("image/bank.jpg"));
        exit.setFont(new Font("Times New Roman", Font.BOLD, 60));
        exit.setBounds(850, 80, 68, 40);
        // 获取按钮的宽度和高度
        width = help.getWidth();
        height = help.getHeight();
        // 加载图像
        icon = (ImageIcon) help.getIcon();
        img = icon.getImage();
        // 缩放图像到按钮的大小
        scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        // 创建新的 ImageIcon 对象，使用缩放后的图像
        scaledIcon = new ImageIcon(scaledImg);
        // 设置按钮的图标为缩放后的图像
        exit.setIcon(scaledIcon);
        exit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // 点击图片时退出程序
                System.exit(0);
            }
        });
        upPanel.add(help);
        upPanel.add(exit);

        cardPanel = new JPanel();
        cardLayout = new CardLayout();
        cardPanel.setLayout(cardLayout);

        DownPanel downPanel = new DownPanel("image/white.jpg");// 更换为你的背景图片路径
        downPanel.setLayout(null);

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

    }

    public void showCard(String cardName) {
        cardLayout.show(cardPanel, cardName);
    }
}
