package utill.write;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import Entity.MessageList;
import Entity.Message;
import utill.cryption.EncryptionUtil;

public class WriteMessage {
    public static void writeMessage(MessageList messageList, String fileName) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            StringBuilder buffer = new StringBuilder();
            List<Message> messages = messageList.getAllMessages();
            for (Message message : messages) {
                // 将消息对象的信息以特定格式写入文件
                buffer.append(message.getSender() + "," + message.getTimestamp() + "," +message.getReceiver()+","+ "\"" + message.getContent() + "\"").append(System.lineSeparator());
            }
            String encryptedData = EncryptionUtil.encrypt(buffer.toString());
            bw.write(encryptedData);
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }catch (Exception ex) {
            System.out.println("Error encrypting data: " + ex.getMessage());
        }
    }
}