package Entity;

import java.util.ArrayList;
import java.util.List;

/**
 * The MessageList class represents a list of messages associated with a kid.
 */
public class MessageList {
    private List<Message> messages;
    private Kids kid;

    /**
     * Constructs an empty MessageList.
     */
    public MessageList() {
        this.messages = new ArrayList<>();
    }

    /**
     * Sets the Kids object associated with this message list.
     *
     * @param kid the Kids object to associate with this message list
     */
    public void setKids(Kids kid) {
        this.kid = kid;
    }

    /**
     * Adds a message to the list.
     *
     * @param message the message to add
     */
    public void addMessage(Message message) {
        messages.add(message);
    }

    /**
     * Returns all messages in the list.
     *
     * @return a list of all messages
     */
    public List<Message> getAllMessages() {
        return messages;
    }

    /**
     * Sets the messages in the list.
     *
     * @param messages the new list of messages
     */
    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    /**
     * Returns all messages sent by either the parent or the kid.
     *
     * @return a list of messages sent by the parent or the kid
     */
    public List<Message> getParentKidMessages() {
        List<Message> parentMessages = new ArrayList<>();
        for (Message message : messages) {
            if (message.getSender().equals("parent") || message.getSender().equals("kid")) {
                parentMessages.add(message);
            }
        }
        return parentMessages;
    }

    /**
     * Returns all system messages for a specific user.
     *
     * @param user the user to filter messages for (either "kid" or "parent")
     * @return a list of system messages for the specified user
     */
    public List<Message> getSystemMessages(String user) {
        List<Message> systemMessages = new ArrayList<>();
        for (Message message : messages) {
            if (user.equals("kid")) {
                if (message.getSender().equals("system_kid")) {
                    systemMessages.add(message);
                }
            }
            if (user.equals("parent")) {
                if (message.getSender().equals("system_parent")) {
                    systemMessages.add(message);
                }
            }
        }
        return systemMessages;
    }

    /**
     * Adds a task message based on the specified type and description.
     *
     * @param type        the type of task ("Child_Opt" or "Parent_Opt")
     * @param description the description of the task
     */
    public void addTaskMessage(String type, String description) {
        if (type.equals("Child_Opt")) {
            Message taskMessage = new Message("system_kid", description,"kid");
            messages.add(taskMessage);
        }
        if (type.equals("Parent_Opt")) {
            Message taskMessage = new Message("system_parent", description,"parent");
            messages.add(taskMessage);
        }
    }

    /**
     * Adds a shop message with the specified price.
     *
     * @param price the price of the shop transaction
     */
    public void addShopMessage(double price) {
        String formattedString = String.format("Double value: %.2f", price);
        Message shopMessage = new Message("system_kid", "bigTransaction","kid");
        Message shopMessage1 = new Message("system_parent", "bigTransaction","parent");
        messages.add(shopMessage);
    }

    /**
     * Returns a string representation of the message list.
     *
     * @return a string representation of the message list
     */
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
