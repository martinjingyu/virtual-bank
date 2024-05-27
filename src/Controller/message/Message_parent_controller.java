package Controller.message;

import Entity.Kids;
import Entity.Message;
import GUI.message_page.message_parent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The {@code Message_parent_controller} class handles the interaction between the parent's message GUI and the underlying message data.
 * It manages the GUI events and updates the message list accordingly.
 */
public class Message_parent_controller {
    private Kids kid;
    private message_parent gui;
    private String selectedContact = null;  // Maintain the state of the selected contact

    /**
     * Constructs a {@code Message_parent_controller} object with the specified kid entity.
     *
     * @param kid the kid entity associated with this controller
     */
    public Message_parent_controller(Kids kid) {
        this.kid = kid;
    }

    /**
     * Sets the GUI for this controller and initializes listeners.
     *
     * @param gui the GUI to be controlled
     */
    public void setGUI(message_parent gui) {
        this.gui = gui;
        setupListeners();
    }

    /**
     * Sets up listeners for the GUI components to handle user interactions.
     */
    public void setupListeners() {
        gui.getContactList().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    String currentSelected = gui.getContactList().getSelectedValue();
                    if (currentSelected != null) {
                        selectedContact = currentSelected;  // Update the selected contact
                        gui.updateContactSelection(selectedContact);
                        gui.getMessageInput().setEnabled(true);  // Enable the input field
                        gui.getMessageInput().setText("Please click enter to send");  // Set placeholder text
                        gui.getMessageInput().setForeground(new Color(225, 226, 226));
                    }
                }
            }
        });

        gui.getMessageInput().addActionListener(e -> {
            String text = gui.getMessageInput().getText();
            if (selectedContact != null && !text.equals("Please click enter to send") && !text.isEmpty()) {
                sendMessage(text);
                gui.getMessageInput().setText("");  // Clear input field after sending
                gui.getMessageInput().setForeground(Color.BLACK);
            }
        });

        gui.getMessageInput().addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                JTextField field = (JTextField) e.getSource();
                if (field.getText().equals("Please select a contact") || field.getText().equals("Please click enter to send")) {
                    field.setText("");
                    field.setForeground(Color.BLACK);
                }
            }

            public void focusLost(FocusEvent e) {
                JTextField field = (JTextField) e.getSource();
                if (field.getText().isEmpty()) {
                    field.setForeground(Color.GRAY);
                    if (selectedContact == null) {
                        field.setText("Please select a contact");
                    } else {
                        field.setText("Please click enter to send");
                    }
                }
            }
        });

    }

    /**
     * Sends a message from the parent to the selected contact.
     *
     * @param message the message content to be sent
     */
    private void sendMessage(String message) {
        if (message.isEmpty()) {
            JOptionPane.showMessageDialog(gui, "Please enter a message.");
            return;
        }
        if (gui.getContactList().getSelectedValue().equals("System Alerts")) {
            Message message1 = new Message("parent", message, "system_parent");
            kid.getMessagelist().addMessage(message1);
            gui.getMessageModel().addElement(message1);  // Assuming constructor exists
        } else if (gui.getContactList().getSelectedValue().equals("Kid")) {
            Message message1 = new Message("parent", message, "kid");
            kid.getMessagelist().addMessage(message1);
            gui.getMessageModel().addElement(message1);  // Assuming constructor exists
        }
    }


    public List<Message> getMessagesForContact(String contact) {

        return kid.getMessagelist().getAllMessages().stream()
                .filter(message -> (("parent".equals(message.getSender()) && contact.equals(message.getReceiver()) )||(contact.equals(message.getSender()) && "parent".equals(message.getReceiver()))))
                .collect(Collectors.toList());
    }
}
