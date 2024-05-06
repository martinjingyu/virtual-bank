package GUI.message_page;

import javax.swing.*;
import java.awt.*;

public class message {
    private JFrame frame;
    private JList<String> systemMessagesList;
    private JList<String> contactsList;

    public message() {
        frame = new JFrame("Messages and Contacts");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
