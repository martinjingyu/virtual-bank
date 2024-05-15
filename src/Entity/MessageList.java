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
    public List<Message> getSystemMessages() {
        List<Message> parentMessages = new ArrayList<>();
        for (Message message : messages) {
            if (message.getSender().equals("system")) {
                parentMessages.add(message);
            }
        }
        return parentMessages;
    }


    // Adding new methods to handle different types of messages
    public void addTaskMessage(String type, Task task, String description) {
        if(type.equals("Child_Opt")){
            Message taskMessage = new Message("system",description);
            messages.add(taskMessage);
        }
        if(type.equals("Parent_add")){
            Message taskMessage = new Message("parent",description);
            messages.add(taskMessage);
        }

    }

    public void addBankMessage(double currentInterestRate,double savingInterestRate) {
        String changeCurrent = String.format("Attention! Your current interest rate has changed to %d",currentInterestRate);
        Message bankMessage = new Message("parent",changeCurrent);
        messages.add(bankMessage);
    }

//    my宝宝，这块儿数据结构和我的不匹配，我给改了，保留我的就好
    public void addShopMessage(double price) {
        String formattedString = String.format("Double value: %.2f", price);

//     宝宝，这块儿参数是string要加双引号，你保留我的就会
        Message shopMessage = new Message("system","bigTransaction");
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