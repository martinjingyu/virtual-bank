package Entity;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class Message {
    private String sender;
    private String timestamp;
    private String content;

    public Message(String sender, String timestamp, String content) {
        this.sender = sender;
        this.timestamp = timestamp;
        this.content = content;
    }
    public Message(String sender, String content) {
        this.content = content;
        this.sender = sender;

        Instant timestamp = Instant.now();

        // 创建一个DateTimeFormatter格式化器
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                .withZone(ZoneId.systemDefault());

        // 将时间戳转换为字符串
        String formattedDate = formatter.format(timestamp);
        this.timestamp = formattedDate;
    }


    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

//    @Override
//    public String toString() {
//        return "Message{" +
//                "sender='" + sender + '\'' +
//                ", timestamp=" + timestamp +
//                ", content='" + content + '\'' +
//                '}';
//    }

    @Override
    public String toString() {
        return "[" + timestamp + "] " + content;
    }

}