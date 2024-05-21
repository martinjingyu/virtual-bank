package Test.TestEntity.Message;

import Entity.Message;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class MessageTest {

    @Test
    public void testMessageConstructorWithTimestamp() {
        String sender = "Alice";
        String timestamp = "2024-05-17 12:00:00";
        String content = "Hello, Bob!";
        Message message = new Message(sender, timestamp, content);

        assertEquals(sender, message.getSender());
        assertEquals(timestamp, message.getTimestamp());
        assertEquals(content, message.getContent());
    }

    @Test
    public void testMessageConstructorWithoutTimestamp() {
        String sender = "Alice";
        String content = "Hello, Bob!";
        Message message = new Message(sender, content);

        assertEquals(sender, message.getSender());
        assertEquals(content, message.getContent());

        // 验证时间戳是否为当前时间，允许少量偏差
        Instant now = Instant.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                .withZone(ZoneId.systemDefault());
        String expectedTimestamp = formatter.format(now);

        // 因为时间戳是动态生成的，所以这里只验证日期部分
        assertTrue(message.getTimestamp().startsWith(expectedTimestamp.substring(0, 10)));
    }

    @Test
    public void testSetTimestamp() {
        String sender = "Alice";
        String content = "Hello, Bob!";
        String newTimestamp = "2024-05-18 12:00:00";
        Message message = new Message(sender, content);
        message.setTimestamp(newTimestamp);

        assertEquals(newTimestamp, message.getTimestamp());
    }

    @Test
    public void testToString() {
        String sender = "Alice";
        String timestamp = "2024-05-17 12:00:00";
        String content = "Hello, Bob!";
        Message message = new Message(sender, timestamp, content);

        String expectedString = "[" + timestamp + "] " + content;
        assertEquals(expectedString, message.toString());
    }
}