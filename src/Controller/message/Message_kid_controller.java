package Controller.message;

import Entity.Kids;
import Entity.Message;
import Entity.MessageList;
import GUI.message_page.message_kid;
import Entity.Agent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.stream.Collectors;


/**
 * The {@code Message_kid_controller} class handles the interaction between the kid's message GUI and the underlying message data.
 * It manages the GUI events and updates the message list accordingly.
 */
public class Message_kid_controller {
    private Kids kid;
    private message_kid gui;
    private String selectedContact = null;  // Maintain the state of the selected contact
    private Agent agent;

    /**
     * Constructs a {@code Message_kid_controller} object with the specified kid entity.
     *
     * @param kid the kid entity associated with this controller
     */
    public Message_kid_controller(Kids kid) {
        this.kid = kid;
        agent = new Agent(kid);
    }

    /**
     * Sets the GUI for this controller and initializes listeners.
     *
     * @param gui the GUI to be controlled
     */
    public void setGUI(message_kid gui) {
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

                if(gui.getContactList().getSelectedValue().equals("System Alerts"))
                {
                    String agentOut = agent.receiveUserInput(text);
                    kid.getMessagelist().addMessage(new Message("system_kid",agentOut,"kid"));
                    gui.getMessageModel().addElement(new Message("system_kid",agentOut,"kid"));  // Assuming constructor exists
                }


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
     * Sends a message from the kid to the selected contact.
     *
     * @param message the message content to be sent
     */
    private void sendMessage(String message) {
        if (message.isEmpty()) {
            JOptionPane.showMessageDialog(gui, "Please enter a message.");
            return;
        }
        if(gui.getContactList().getSelectedValue().equals("System Alerts")){
            Message message1 = new Message("kid", message,"system_kid");
            kid.getMessagelist().addMessage(message1);
            gui.getMessageModel().addElement(message1);  // Assuming constructor exists
        } else if (gui.getContactList().getSelectedValue().equals("Parents")) {
            Message message1 = new Message("kid", message,"parent");
            kid.getMessagelist().addMessage(message1);
            gui.getMessageModel().addElement(message1);  // Assuming constructor exists
        }


    }

    /**
     * Retrieves the messages for the selected contact.
     *
     * @param contact the selected contact
     * @return the list of messages for the selected contact
     */
    public List<Message> getMessagesForContact(String contact) {

        return kid.getMessagelist().getAllMessages().stream()
                .filter(message -> (("kid".equals(message.getSender()) && contact.equals(message.getReceiver()))||(contact.equals(message.getSender()) && "kid".equals(message.getReceiver()))))
                .collect(Collectors.toList());
    }
}