package Controller.message;

import Entity.Parent;
import Entity.Message;
import GUI.message_page.message_parent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Message_parent_controller {
    private Parent parent;
    private message_parent gui;
    private String selectedContact = null;  // Maintain the state of the selected contact

    public Message_parent_controller(Parent parent) {
        this.parent = parent;
    }

    public void setGUI(message_parent gui) {
        this.gui = gui;
        setupListeners();
    }

    public void setupListeners() {
        gui.getContactList().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    String currentSelected = gui.getContactList().getSelectedValue();
                    if (currentSelected != null) {
                        selectedContact = currentSelected;  // Update the selected contact
                        gui.updateContactSelection(selectedContact);
                        loadMessagesForContact(selectedContact);
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
                gui.getMessageInput().setText("");  // 发送后清空输入框
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

    private void sendMessage(String message) {
        if (message.isEmpty()) {
            JOptionPane.showMessageDialog(gui, "Please enter a message.");
            return;
        }
        gui.getMessageModel().addElement(new Message("parent", message));  // Assuming constructor exists
    }

    private void loadMessagesForContact(String contact) {
        gui.getMessageModel().clear();
        Message[] messages = contact.equals("Kids") ? parent.getMessagelist().getKidParentMessages().toArray(new Message[0]) :
                parent.getMessagelist().getSystemMessages().toArray(new Message[0]);
        for (Message msg : messages) {
            gui.getMessageModel().addElement(msg);
        }
    }
}
