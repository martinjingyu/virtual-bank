package utill.read;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.StringReader;
import java.io.IOException;
import Entity.Message;
import Entity.MessageList;

/**
 * Utility class to read messages from files or strings into a MessageList.
 */
public class ReadMessage {

    /**
     * Reads messages from a file and adds them to the provided MessageList.
     *
     * @param fileName the name of the file to read messages from
     * @param messageList the MessageList to add the read messages to
     */
    public static void readMessage(String fileName, MessageList messageList) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            readFromBufferedReader(br, messageList);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    /**
     * Reads messages from a string and adds them to the provided MessageList.
     *
     * @param data the string data containing the messages
     * @param messageList the MessageList to add the read messages to
     */
    public static void readMessagesFromString(String data, MessageList messageList) {
        try (BufferedReader br = new BufferedReader(new StringReader(data))) {
            readFromBufferedReader(br, messageList);
        } catch (IOException e) {
            System.out.println("Error reading data: " + e.getMessage());
        }
    }

    /**
     * Reads messages from a BufferedReader and adds them to the provided MessageList.
     *
     * @param br the BufferedReader to read messages from
     * @param messageList the MessageList to add the read messages to
     * @throws IOException if an I/O error occurs
     */
    private static void readFromBufferedReader(BufferedReader br, MessageList messageList) throws IOException {
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",", 3);
            if (parts.length == 3) {
                String sender = parts[0].trim();
                String timestamp = parts[1].trim();
                String content = parts[2].trim();
                // Handle potential quotes in content
                if (content.startsWith("\"") && content.endsWith("\"")) {
                    content = content.substring(1, content.length() - 1); // Remove quotes
                }
                Message message = new Message(sender, timestamp, content);
                messageList.addMessage(message);
            } else {
                System.out.println("Invalid message data: " + line);
            }
        }
    }

    /**
     * Main method for testing purposes. Reads messages from a file and prints them.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MessageList messageList = new MessageList();
        readMessage("data/Kids/222/Message.txt", messageList);

        // Print all messages in the list
        System.out.println("All messages:");
        for (Message message : messageList.getAllMessages()) {
            System.out.println(message);
        }
    }
}
