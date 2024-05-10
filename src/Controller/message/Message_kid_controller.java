package Controller.message;

import Entity.Kids;
import Entity.Message;
import GUI.message_page.message_kid;

import javax.swing.*;
import java.awt.event.*;
import java.awt.event.ActionListener;

public class Message_kid_controller {

    private Kids kid;
    private message_kid GUI;

    public Message_kid_controller(Kids kid) {
        this.kid = kid;
    }

    public void setGUI(message_kid GUI) {
        this.GUI = GUI;
    }
    public Kids getKid() {
        return kid;
    }

    public void addButtonListener() {
        GUI.getMessageInput().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!GUI.getMessageInput().getText().isEmpty() && GUI.getContactList().getSelectedValue() != null) {
                    String text = GUI.getMessageInput().getText();
                    GUI.getMessageModel().addElement(new Message("kid", text));
                    GUI.getMessageInput().setText(""); // 清空输入框
                }
            }
        });

        GUI.getContactList().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    String selectedContact = GUI.getContactList().getSelectedValue();
                    GUI.updateMessages(selectedContact);
                }
            }
        });
    }

}


