package Entity;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * The Message class represents a message with a sender, timestamp, and content.
 */
public class Message {
    private String sender;
    private String timestamp;
    private String content;

    /**
     * Constructs a Message object with specified sender, timestamp, and content.
     *
     * @param sender    the sender of the message
     * @param timestamp the timestamp of the message
     * @param content   the content of the message
     */
    public Message(String sender, String timestamp, String content) {
        this.sender = sender;
        this.timestamp = timestamp;
        this.content = content;
    }

    /**
     * Constructs a Message object with specified sender and content.
     * The timestamp is set to the current time.
     *
     * @param sender  the sender of the message
     * @param content the content of the message
     */
    public Message(String sender, String content) {
        this.content = content;
        this.sender = sender;

        Instant timestamp = Instant.now();

        // Create a DateTimeFormatter formatter
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                .withZone(ZoneId.systemDefault());

        // Convert the timestamp to a string
        String formattedDate = formatter.format(timestamp);
        this.timestamp = formattedDate;
    }

    /**
     * Gets the sender of the message.
     *
     * @return the sender of the message
     */
    public String getSender() {
        return sender;
    }

    /**
     * Gets the timestamp of the message.
     *
     * @return the timestamp of the message
     */
    public String getTimestamp() {
        return timestamp;
    }

    /**
     * Sets the timestamp of the message.
     *
     * @param timestamp the new timestamp of the message
     */
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Gets the content of the message.
     *
     * @return the content of the message
     */
    public String getContent() {
        return content;
    }

    /**
     * Returns a string representation of the message.
     *
     * @return a string representation of the message
     */
    @Override
    public String toString() {
        return "[" + timestamp + "] " + content;
    }
}
