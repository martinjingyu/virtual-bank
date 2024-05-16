package Entity;

import java.util.ArrayList;
import java.util.List;

public class MessageList {
    private List<Message> messages;
    private Kids kid;
    private Parent parent;


    public MessageList() {

        this.messages = new ArrayList<>();
    }

    public void setKids(Kids kid) {
        this.kid = kid;
    }
    public void setParent(Parent parent) {
        this.parent = parent;
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

    public List<Message> getKidParentMessages() {
        List<Message> kidMessages = new ArrayList<>();
        for (Message message : messages) {
            if (message.getSender().equals("parent")||message.getSender().equals("kid")) {
                kidMessages.add(message);
            }
        }
        return kidMessages;
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
    public void addTaskMessage(String type, String description) {
        if(type.equals("Child_Opt")){
            Message taskMessage = new Message("system",description);
            messages.add(taskMessage);
        }
        if(type.equals("Parent_add")){
            Message taskMessage = new Message("parent",description);
            messages.add(taskMessage);
        }

    }


    public void completeTaskMessage(String type, Task task, String description) {
        if(type.equals("Child_Opt")){
            Message taskMessage = new Message("kid",description);
            messages.add(taskMessage);
        }
//        if(type.equals("Parent_add")){
//            Message taskMessage = new Message("parent",description);
//            messages.add(taskMessage);
//        }

    }

    public void addBankMessage(double savingInterestRate) {

        String changeCurrent = String.format("Attention! You have successfully create a new saving account with interest rate to be %d",savingInterestRate);
        Message bankMessage = new Message("system",changeCurrent);
        messages.add(bankMessage);
    }

    public void warnBankMessage(double savingInterestRate) {
        String changeCurrent2 = String.format("Attention! Your kid have successfully create a new saving account with interest rate to be %d",savingInterestRate);
        Message bankMessage = new Message("system",changeCurrent2);
        messages.add(bankMessage);
    }

    public void addShopMessage(double price) {
        String formattedString = String.format("Double value: %.2f", price);
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