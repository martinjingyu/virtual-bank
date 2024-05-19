package GUI.message_page;

import Controller.message.Message_kid_controller;
import Entity.Kids;
import Entity.Message;
import utill.read.ReadAll;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class message_kid extends JPanel {
    private Message_kid_controller controller;
    private JList<String> contactsList;
    private DefaultListModel<Message> messageModel;
    private JTextField messageInput;
    private JLabel contactNameLabel;
    private final Color mainBgColor = new Color(191, 221, 239); // #bfddef
    private final Color panelBgColor = new Color(239, 239, 239); // #EFEFEF
    private final Color borderColor = new Color(220, 220, 220); // #696969
    private final Color fontColor = new Color(49, 122, 232); // #317AE8

    public message_kid(Message_kid_controller controller) {
        this.controller = controller;
        initUI();
        this.controller.setGUI(this);
        this.controller.setupListeners();

    }

    private void initUI() {
        setPreferredSize(new Dimension(900, 540));
        setLayout(new BorderLayout(20, 20));
        setBackground(mainBgColor);
        setBorder(new EmptyBorder(20, 40, 20, 40));

        add(createHeaderPanel(), BorderLayout.NORTH);
        contactNameLabel = new JLabel("Select a contact", SwingConstants.CENTER);
        contactNameLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        add(createMessagesPanel(), BorderLayout.CENTER);
        add(createContactsPanel(), BorderLayout.WEST);
    }

    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(mainBgColor);
        JLabel titleLabel = new JLabel("Messages", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 40));
        titleLabel.setForeground(fontColor);
        headerPanel.add(titleLabel);
        return headerPanel;
    }

    private JPanel createContactsPanel() {
        contactsList = new JList<>(new String[]{"Parents", "System Alerts"});
        contactsList.setFont(new Font("SansSerif", Font.PLAIN, 16));
        contactsList.setBackground(panelBgColor);
        contactsList.setForeground(Color.BLACK);
        contactsList.setSelectionBackground(borderColor);
        contactsList.setSelectionForeground(Color.WHITE);
        JScrollPane scrollPane = new JScrollPane(contactsList);
        scrollPane.setPreferredSize(new Dimension(350, 250));
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);
        return panel;
    }

    private JPanel createMessagesPanel() {
        messageModel = new DefaultListModel<>();
        JList<Message> messagesList = new JList<>(messageModel);
        messagesList.setCellRenderer(new MessageCellRenderer());
        messagesList.setFont(new Font("SansSerif", Font.PLAIN, 16));
        messagesList.setBackground(panelBgColor);
        JScrollPane scrollPane = new JScrollPane(messagesList);
        scrollPane.setPreferredSize(new Dimension(400, 350));
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(contactNameLabel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(createInputPanel(), BorderLayout.SOUTH);
        return panel;
    }

    private JPanel createInputPanel() {
        messageInput = new JTextField("Please select a contact");
        messageInput.setFont(new Font("SansSerif", Font.PLAIN, 16));
        messageInput.setForeground(Color.GRAY);
        messageInput.setEnabled(false);
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(messageInput, BorderLayout.CENTER);
        return panel;
    }

    // Accessor methods for the controller to use
    public JTextField getMessageInput() {
        return messageInput;
    }

    public JList<String> getContactList() {
        return contactsList;
    }

    public DefaultListModel<Message> getMessageModel() {
        return messageModel;
    }

    public void updateContactSelection(String contact) {
        contactNameLabel.setText(contact);
        messageModel.clear();  // Optionally delegate to controller to handle updating model
    }

    static class MessageCellRenderer extends JLabel implements ListCellRenderer<Message> {
        private static final Color kidMessageColor = new Color(239, 239, 239); // Light blue for kid's messages
        private static final Color parentMessageColor = new Color(225, 226, 226); // Light orange for parent's messages
        private static final Color systemMessageColor = new Color(225, 226, 226); // Light grey for system messages
        private static final Color selectedColor = new Color(173, 216, 230); // Light blue for selected messages

        @Override
        public Component getListCellRendererComponent(JList<? extends Message> list, Message value, int index, boolean isSelected, boolean cellHasFocus) {
            setText(formatMessage(value));
            setOpaque(true);
            setFont(list.getFont());
            setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); // Consistent padding to prevent shifting

            // Determine alignment based on the sender
            if ("kid".equals(value.getSender())) {
                setHorizontalAlignment(SwingConstants.RIGHT);
            } else {
                setHorizontalAlignment(SwingConstants.LEFT);
            }

            if (isSelected) {
                setBackground(selectedColor);
                setForeground(Color.WHITE);
            } else {
                setForeground(Color.DARK_GRAY);
                switch (value.getSender()) {
                    case "kid":
                        setBackground(kidMessageColor);
                        break;
                    case "parent":
                        setBackground(parentMessageColor);
                        break;
                    case "system":
                        setBackground(systemMessageColor);
                        break;
                    default:
                        setBackground(list.getBackground());
                }
            }
            return this;
        }

        private String formatMessage(Message message) {
            return "<html><div style='text-align: " + (message.getSender().equals("kid") || message.getSender().equals("system") ? "right" : "left") + ";'>" +
                    message.getTimestamp() + "<br/>" +
                    message.getContent() + "</div></html>";
        }
    }
    public static void main(String[] args) {
        Kids kid = ReadAll.readall(String.valueOf(222));
        Message_kid_controller messageController = new Message_kid_controller(kid);
        message_kid messageKid = new message_kid(messageController);
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(messageKid);
        frame.setSize(800, 600);
        frame.setVisible(true);
    }
}

