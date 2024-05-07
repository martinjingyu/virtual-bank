package GUI.message_page;

import Entity.Kids;
import Entity.Message;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class message_kid extends JPanel {
    private JList<Message> messagesList;
    private DefaultListModel<Message> messageModel;
    private JList<String> contactsList;
    private JLabel contactNameLabel;
    private JTextField messageInput;
    private Map<String, Message[]> messagesData;
    private Kids kid;

//    public templete_message() {
//        this.setLayout(new BorderLayout(10, 10));
//        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
//
//        // 初始化消息数据
//        initializeMessages();
//
//        // 创建联系人列表
//        String[] contacts = {"Parents", "System Alerts"};
//        contactsList = new JList<>(contacts);
//        JScrollPane contactsScrollPane = new JScrollPane(contactsList);
//        JPanel contactsPanel = createSectionPanel("Contacts", contactsScrollPane);
//        Color customColor1 = new Color(186, 223, 243);  // 自定义的 RGB 值
//        contactsList.setBackground(customColor1);
//        contactsPanel.setPreferredSize(new Dimension(350, 250));
//
//
//        // 创建消息列表
//        messageModel = new DefaultListModel<>();
//        messagesList = new JList<>(messageModel);
//        messagesList.setCellRenderer(new MessageCellRenderer());
//        JScrollPane messagesScrollPane = new JScrollPane(messagesList);
//        Color customColor2 = new Color(255, 225, 229);  // 自定义的 RGB 值
//        messagesList.setBackground(customColor2);
//        messagesScrollPane.setPreferredSize(new Dimension(400, 350));
//
//        // 创建信息输入框
//        messageInput = new JTextField();
//        messageInput.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                if (!messageInput.getText().isEmpty() && contactsList.getSelectedValue() != null) {
//                    String text = messageInput.getText();
//                    messageModel.addElement(new Message(text, true));
//                    messageInput.setText(""); // 清空输入框
//                }
//            }
//        });
//
//        contactNameLabel = new JLabel("Select a contact");
//        contactNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
////        contactNameLabel.setFont(new Font("Arial", Font.PLAIN, 24));
//
//        // 消息面板设置
//        JPanel messagesPanel = new JPanel(new BorderLayout());
//        messagesPanel.add(contactNameLabel, BorderLayout.NORTH);
//        messagesPanel.add(messagesScrollPane, BorderLayout.CENTER);
//        messagesPanel.add(messageInput, BorderLayout.SOUTH);  // 添加输入框到面板底部
//        messagesPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
//
//        // 添加到面板
//        this.add(messagesPanel, BorderLayout.CENTER);
//        this.add(contactsPanel, BorderLayout.EAST);
//
//        // 添加监听器到联系人列表
//        contactsList.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                if (e.getClickCount() == 1) {
//                    String selectedContact = contactsList.getSelectedValue();
//                    updateMessages(selectedContact);
//                }
//            }
//        });
//    }

    public message_kid(Kids kid) {
        System.out.println(kid.getMessagelist());
        this.kid = kid;
        this.setLayout(new BorderLayout(10, 10));
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // 初始化消息数据
        initializeMessages();

        // 创建联系人列表
        String[] contacts = {"Parents", "System Alerts"};
        contactsList = new JList<>(contacts);
        JScrollPane contactsScrollPane = new JScrollPane(contactsList);
        JPanel contactsPanel = createSectionPanel("Contacts", contactsScrollPane);
        Color customColor1 = new Color(186, 223, 243);  // 自定义的 RGB 值
        contactsList.setBackground(customColor1);
        contactsPanel.setPreferredSize(new Dimension(350, 250));


        // 创建消息列表
        messageModel = new DefaultListModel<>();
        messagesList = new JList<>(messageModel);
        messagesList.setCellRenderer(new MessageCellRenderer());
        JScrollPane messagesScrollPane = new JScrollPane(messagesList);
        Color customColor2 = new Color(255, 225, 229);  // 自定义的 RGB 值
        messagesList.setBackground(customColor2);
        messagesScrollPane.setPreferredSize(new Dimension(400, 350));

        // 创建信息输入框
        messageInput = new JTextField();
        messageInput.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!messageInput.getText().isEmpty() && contactsList.getSelectedValue() != null) {
                    String text = messageInput.getText();
                    messageModel.addElement(new Message(text, "sssss","this is a try"));
                    messageInput.setText(""); // 清空输入框
                }
            }
        });

        contactNameLabel = new JLabel("Select a contact");
        contactNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
//        contactNameLabel.setFont(new Font("Arial", Font.PLAIN, 24));

        // 消息面板设置
        JPanel messagesPanel = new JPanel(new BorderLayout());
        messagesPanel.add(contactNameLabel, BorderLayout.NORTH);
        messagesPanel.add(messagesScrollPane, BorderLayout.CENTER);
        messagesPanel.add(messageInput, BorderLayout.SOUTH);  // 添加输入框到面板底部
        messagesPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        // 添加到面板
        this.add(messagesPanel, BorderLayout.CENTER);
        this.add(contactsPanel, BorderLayout.EAST);

        // 添加监听器到联系人列表
        contactsList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    String selectedContact = contactsList.getSelectedValue();
                    updateMessages(selectedContact);
                }
            }
        });
    }

    private JPanel createSectionPanel(String title, JScrollPane scrollPane) {
        JPanel sectionPanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
        sectionPanel.add(titleLabel, BorderLayout.NORTH);
        sectionPanel.add(scrollPane, BorderLayout.CENTER);
        return sectionPanel;
    }

    private void initializeMessages() {
        messagesData = new HashMap<>();
        System.out.println(kid.getMessagelist().getAllMessages());

        List<Message> msgList = kid.getMessagelist().getAllMessages();
        for(Message msg: msgList){
            System.out.println(msg);
            Message[] messages = msgList.toArray(new Message[0]);
            messagesData.put("Parents", messages);
           // messagesData.put("Parents", new Message[]{new Message("kid", "timestamp","this is a try"),new Message("parent", "sssss","this is a try")});
            messagesData.put("System Alerts", new Message[]{new Message("[Alert] System maintenance tonight.", "timestamp2","this is a try")});
        }




    }

    private void updateMessages(String contact) {
        contactNameLabel.setText(contact);  // 更新顶部标签为当前选中的联系人名字
        messageModel.clear();
        if (messagesData.containsKey(contact)) {
            for (Message msg : messagesData.get(contact)) {
                messageModel.addElement(msg);
            }
        }
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        final JPanel panel1 = new JPanel();
        // 设置布局为 GridBagLayout
        panel1.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // 初始化约束条件
        gbc.gridx = 0;  // 组件所在的列
        gbc.gridy = 0;  // 组件所在的行
        gbc.gridwidth = 1;  // 组件占据的列数
        gbc.gridheight = 1;  // 组件占据的行数
        gbc.fill = GridBagConstraints.BOTH;  // 如何填充空间
        gbc.insets = new Insets(0, 0, 0, 0);  // 外边距
        gbc.weightx = 1.0;  // 横向扩展权重
        gbc.weighty = 1.0;  // 纵向扩展权重

        // 添加组件，可以是任意的 Swing 组件
//        panel1.add(yourComponent, gbc);
    }




    static class MessageCellRenderer extends JLabel implements ListCellRenderer<Message> {
        @Override
        public Component getListCellRendererComponent(JList<? extends Message> list, Message value, int index, boolean isSelected, boolean cellHasFocus) {
            setText(value.getContent());
            setOpaque(true);
            if (value.getSender().equals("kid")) {
                setHorizontalAlignment(SwingConstants.RIGHT);
                setBackground(Color.LIGHT_GRAY);
            } else {
                setHorizontalAlignment(SwingConstants.LEFT);
                setBackground(Color.WHITE);
            }
            return this;
        }
    }
}