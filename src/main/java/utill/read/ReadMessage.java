package utill.read;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;

import Entity.Message;
import Entity.MessageList;

public class ReadMessage {
    public static void readMessage(String fileName, MessageList messageList) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",",3);
//                System.out.println(parts.length);
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
                    System.out.println("Invalid product data: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        MessageList messageList = new MessageList();
        readMessage("data/Kids/Martin/Message.txt", messageList);

        // Print all products in the list
        System.out.println("All message:");
        for (Message message : messageList.getAllMessages()) {
            System.out.println(message);
        }
    }
}