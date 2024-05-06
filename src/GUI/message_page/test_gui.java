package GUI.message_page;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class test_gui extends JFrame {
    private BorderLayout borderLayout;
    private JPanel main_page;
    private JPanel menu;
    private JLabel button1, button2, button3, button4;
    private JPanel current_panel;

    public static void main(String[] args) {
        templete_message page4 = new templete_message();
        new test_gui(page4);
    }

    public test_gui(JPanel panel) {
        super("demo");
        current_panel = panel;
        Jframe_Jpanel();
        navi_button();
        setVisible(true);
    }

    public void Jframe_Jpanel(){
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
        button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // 添加按钮点击事件的处理代码
                main_page.remove(current_panel);
                main_page.add(current_panel, BorderLayout.CENTER);
                revalidate();
                repaint();
            }
        });
        button1.setHorizontalAlignment(SwingConstants.CENTER); // 设置水平居中对齐

        button2 = new JLabel();
        button2.setIcon(new ImageIcon(scaledImg)); // 替换为您的图片路径
        button2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // 添加按钮点击事件的处理代码
                main_page.remove(current_panel);
                revalidate();
                repaint();
            }
        });
        button2.setHorizontalAlignment(SwingConstants.CENTER); // 设置水平居中对齐

        button3 = new JLabel();
        button3.setIcon(new ImageIcon(scaledImg)); // 替换为您的图片路径
        button3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // 添加按钮点击事件的处理代码
                main_page.remove(current_panel);
                revalidate();
                repaint();
            }
        });
        button3.setHorizontalAlignment(SwingConstants.CENTER); // 设置水平居中对齐

        button4 = new JLabel();
        button4.setIcon(new ImageIcon(scaledImg)); // 替换为您的图片路径
        button4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // 添加按钮点击事件的处理代码
                main_page.remove(current_panel);
                revalidate();
                repaint();
            }
        });
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
    public void sub_page(templete_message pg4){

    }

    public static class message {
        private JFrame frame;
        private JList<String> systemMessagesList;
        private JList<String> contactsList;

        public message() {
            frame = new JFrame("Messages and Contacts");
            frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

            JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
            mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            // 创建系统消息列表
            systemMessagesList = new JList<>(new String[]{"[Security Warning] Illegal transaction...", "[System] Welcome back!"});
            JScrollPane systemMessagesScrollPane = new JScrollPane(systemMessagesList);
            JPanel systemMessagesPanel = createSectionPanel("System Messages", systemMessagesScrollPane);

            // 创建联系人列表
            contactsList = new JList<>(new String[]{"System message", "Parent", "New user"});
            JScrollPane contactsScrollPane = new JScrollPane(contactsList);
            JPanel contactsPanel = createSectionPanel("Contacts", contactsScrollPane);

            // 将两个分区面板添加到主面板
            mainPanel.add(systemMessagesPanel, BorderLayout.WEST);
            mainPanel.add(contactsPanel, BorderLayout.CENTER);

            frame.setContentPane(mainPanel);
            frame.setSize(new Dimension(600, 400)); // 设置窗口初始大小
            frame.setLocationRelativeTo(null); // 居中窗口
            frame.setVisible(true);
        }

        // 创建带标题和滚动面板的分区面板
        private JPanel createSectionPanel(String title, JScrollPane scrollPane) {
            JPanel sectionPanel = new JPanel(new BorderLayout());
            sectionPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

            JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
            titleLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));

            // 设置滚动面板的首选大小
            scrollPane.setPreferredSize(new Dimension(100, 400)); // 适当调整宽度和高度

            sectionPanel.add(titleLabel, BorderLayout.NORTH);
            sectionPanel.add(scrollPane, BorderLayout.CENTER);

            return sectionPanel;
        }

        public static void main(String[] args) {
            SwingUtilities.invokeLater(() -> new message());
        }
    }
}