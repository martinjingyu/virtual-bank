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
    public void addTaskMessage(Task task, String description) {
        Message taskMessage = new Message("parent",description);
        messages.add(taskMessage);
    }

    public void addBankMessage(Bank bank, double currentInterestRate,double savingInterestRate) {
        String changeCurrent = String.format("Attention! Your current interest rate has changed to %d",currentInterestRate);
        Message bankMessage = new Message("parent",changeCurrent);
        messages.add(bankMessage);
    }

    public void addShopMessage(double price) {
        String bigTransaction = String.format("Attention! You made a large expenditure, spending %d.", price);
        Message shopMessage = new Message("system",bigTransaction);
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