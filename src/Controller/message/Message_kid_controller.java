package Controller.message;

import Entity.Kids;
import Entity.Message;
import GUI.message_page.message_kid;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Message_kid_controller {
    private Kids kid;
    private message_kid gui;
    private String selectedContact = null;  // Maintain the state of the selected contact

    public Message_kid_controller(Kids kid) {
        this.kid = kid;
    }

    public void setGUI(message_kid gui) {
        this.gui = gui;
        setupListeners();
    }

    public void setupListeners() {
        gui.getContactList().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    String currentSelected = gui.getContactList().getSelectedValue();

                    System.out.println(gui.getContactList());
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
        gui.getMessageModel().addElement(new Message("kid", message));  // Assuming constructor exists
    }

    private void loadMessagesForContact(String contact) {
        gui.getMessageModel().clear();
        Message[] messages = contact.equals("Parents") ? kid.getMessagelist().getParentKidMessages().toArray(new Message[0]) :
                kid.getMessagelist().getSystemMessages().toArray(new Message[0]);
        for (Message msg : messages) {
            gui.getMessageModel().addElement(msg);
        }
    }
}
