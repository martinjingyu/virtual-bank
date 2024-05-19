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

    public List<Message> getKidParentMessages() {
        List<Message> kidMessages = new ArrayList<>();
        for (Message message : messages) {
            if (message.getSender().equals("parent")||message.getSender().equals("kid")) {
                kidMessages.add(message);
            }
        }
        return kidMessages;
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

//    public void warnBankMessage(Bank bank, double currentInterestRate,double savingInterestRate) {
//        String changeCurrent2 = String.format("Attention! You have already changes the current interest rate to %d",currentInterestRate);
//        Message bankMessage = new Message("system",changeCurrent2);
//        messages.add(bankMessage);
//    }


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