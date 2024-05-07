package Entity;

import java.util.ArrayList;
import java.util.List;

public class MessageList {
    private List<Message> messages;

    public MessageList() {
        this.messages = new ArrayList<>();
    }

    public void addMessage(Message message) {
        messages.add(message);
    }

    public List<Message> getAllMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public List<Message> getParentKidMessages() {
        List<Message> parentMessages = new ArrayList<>();
        for (Message message : messages) {
            if (message.getSender().equals("parent")||message.getSender().equals("kid")) {
                parentMessages.add(message);
            }
        }
        return parentMessages;
    }
    public List<Message> getSystemMessages() {
        List<Message> parentMessages = new ArrayList<>();
        for (Message message : messages) {
            if (message.getSender().equals("system")) {
                parentMessages.add(message);
            }
        }
        return parentMessages;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("MessageList:\n");
        for (Message message : messages) {
            sb.append(message.toString()).append("\n");
        }
        return sb.toString();
    }
}