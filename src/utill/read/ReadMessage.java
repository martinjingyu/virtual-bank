package utill.read;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.StringReader;
import java.io.IOException;
import Entity.Message;
import Entity.MessageList;

public class ReadMessage {
    // 从文件读取
    public static void readMessage(String fileName, MessageList messageList) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            readFromBufferedReader(br, messageList);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    // 从字符串读取
    public static void readMessagesFromString(String data, MessageList messageList) {
        try (BufferedReader br = new BufferedReader(new StringReader(data))) {
            readFromBufferedReader(br, messageList);
        } catch (IOException e) {
            System.out.println("Error reading data: " + e.getMessage());
        }
    }

    // 从BufferedReader读取消息数据
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

    public static void main(String[] args) {
        MessageList messageList = new MessageList();
        readMessage("data/Kids/222/Message.txt", messageList);

        // Print all products in the list
        System.out.println("All messages:");
        for (Message message : messageList.getAllMessages()) {
            System.out.println(message);
        }
    }
}
