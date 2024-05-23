package Entity;

import java.util.ArrayList;
import java.util.List;

public class MessageList {
    private List<Message> messages;
    private Kids kid;
    public MessageList() {
        this.messages = new ArrayList<>();
    }

    public void setKids(Kids kid) {
        this.kid = kid;
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

    public List<Message> getSystemMessages(String user) {
        List<Message> systemMessages = new ArrayList<>();
        for (Message message : messages) {
            if(user.equals("kid")) {
                if (message.getSender().equals("system_kid")) {
                    systemMessages.add(message);
                }
            }
            if(user.equals("parent")){
                if (message.getSender().equals("system_parent")) {
                    systemMessages.add(message);
                }
            }
        }
        return systemMessages;
    }
    // Adding new methods to handle different types of messages
    public void addTaskMessage(String type, String description) {
        if(type.equals("Child_Opt")){
            Message taskMessage = new Message("system_kid",description);
            messages.add(taskMessage);
        }
        if(type.equals("Parent_Opt")){
            Message taskMessage = new Message("system_parent",description);
            messages.add(taskMessage);
        }

    }
    public void addShopMessage(double price) {
        String formattedString = String.format("Double value: %.2f", price);
        Message shopMessage = new Message("system_kid","bigTransaction");
        messages.add(shopMessage);
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