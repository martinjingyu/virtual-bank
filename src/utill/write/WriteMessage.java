package utill.write;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import Entity.MessageList;
import Entity.Message;

public class WriteMessage {
    public static void writeMessage(MessageList messageList, String fileName) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            List<Message> messages = messageList.getAllMessages();
            for (Message message : messages) {
                // 将消息对象的信息以特定格式写入文件
                bw.write(message.getSender() + "," + message.getTimestamp() + "," + "\"" + message.getContent() + "\"");
                bw.newLine(); // 写入新行
            }
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }
}