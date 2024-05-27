package GUI.message_page;

import Controller.message.Message_kid_controller;
import Entity.Kids;
import Entity.Message;
import utill.read.ReadAll;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

/**
 * The {@code message_kid} class represents a graphical user interface (GUI) for displaying messages and contacts for a kid user.
 * It provides functionality for viewing messages, selecting contacts, and sending new messages.
 */
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

    /**
     * Constructs a {@code message_kid} object with the specified controller.
     *
     * @param controller the controller for handling message operations
     */
    public message_kid(Message_kid_controller controller) {
        this.controller = controller;
        initUI();
        this.controller.setGUI(this);
        this.controller.setupListeners();
    }

    /**
     * Initializes the graphical user interface elements.
     */
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

    /**
     * Creates the header panel containing the title.
     *
     * @return the header panel
     */
    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(mainBgColor);
        JLabel titleLabel = new JLabel("Messages", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 40));
        titleLabel.setForeground(fontColor);
        headerPanel.add(titleLabel);
        return headerPanel;
    }

    /**
     * Creates the panel for displaying contacts.
     *
     * @return the contacts panel
     */
    private JPanel createContactsPanel() {
        contactsList = new JList<>(new String[]{"Parents", "System Alerts"});
        contactsList.setFont(new Font("SansSerif", Font.PLAIN, 16));
        contactsList.setBackground(panelBgColor);
        contactsList.setForeground(Color.BLACK);
        contactsList.setSelectionBackground(borderColor);
        contactsList.setSelectionForeground(Color.WHITE);
        JScrollPane scrollPane = new JScrollPane(contactsList);
        scrollPane.setPreferredSize(new Dimension(180, 250));
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);
        return panel;
    }

    /**
     * Creates the panel for displaying messages.
     *
     * @return the messages panel
     */
    private JPanel createMessagesPanel() {
        messageModel = new DefaultListModel<>();
        JList<Message> messagesList = new JList<>(messageModel);
        messagesList.setCellRenderer(new MessageCellRenderer());
        messagesList.setFont(new Font("SansSerif", Font.PLAIN, 16));
        messagesList.setBackground(panelBgColor);
        JScrollPane scrollPane = new JScrollPane(messagesList);
        scrollPane.setPreferredSize(new Dimension(570, 350));
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(contactNameLabel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(createInputPanel(), BorderLayout.SOUTH);
        return panel;
    }

    /**
     * Creates the panel for message input.
     *
     * @return the input panel
     */
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

    /**
     * Updates the selected contact's information.
     *
     * @param contact the selected contact
     */
    public void updateContactSelection(String contact) {
        contactNameLabel.setText(contact);
        messageModel.clear();
        if(contact.equals("Parents")){
            List<Message> filteredMessages = controller.getMessagesForContact("parent");
            for (Message message : filteredMessages) {
                messageModel.addElement(message);
            }
        }
        if(contact.equals("System Alerts")){
            List<Message> filteredMessages = controller.getMessagesForContact("system_kid");
            for (Message message : filteredMessages) {
                messageModel.addElement(message);
            }
        }

    }

    /**
     * Custom cell renderer for formatting messages in the message list.
     */
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
                    case "system_kid":
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

    /**
     * Main method to launch the GUI.
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        try {
            Kids kid = ReadAll.readall(String.valueOf(222));
            Message_kid_controller messageController = new Message_kid_controller(kid);
            message_kid messageKid = new message_kid(messageController);
            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(messageKid);
            frame.setSize(800, 600);
            frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
