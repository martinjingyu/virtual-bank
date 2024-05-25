package Test.TestEntity.Message;

import Entity.Message;
import Entity.MessageList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.ArrayList;

public class MessageListTest {
    private MessageList messageList;

    @Before
    public void setUp() {
        messageList = new MessageList();
    }

    @Test
    public void testAddMessage() {
        Message message = new Message("Alice", "Hello, Bob!");
        messageList.addMessage(message);
        List<Message> messages = messageList.getAllMessages();

        assertEquals(1, messages.size());
        assertEquals(message, messages.get(0));
    }

    @Test
    public void testGetParentKidMessages() {
        Message parentMessage = new Message("parent", "Hello, kid!");
        Message kidMessage = new Message("kid", "Hello, parent!");
        Message otherMessage = new Message("other", "Hello, world!");

        messageList.addMessage(parentMessage);
        messageList.addMessage(kidMessage);
        messageList.addMessage(otherMessage);

        List<Message> parentKidMessages = messageList.getParentKidMessages();

        assertEquals(2, parentKidMessages.size());
        assertTrue(parentKidMessages.contains(parentMessage));
        assertTrue(parentKidMessages.contains(kidMessage));
    }

    @Test
    public void testGetSystemMessagesForKid() {
        Message kidSystemMessage = new Message("system_kid", "System message for kid");
        Message parentSystemMessage = new Message("system_parent", "System message for parent");
        Message otherMessage = new Message("other", "Hello, world!");

        messageList.addMessage(kidSystemMessage);
        messageList.addMessage(parentSystemMessage);
        messageList.addMessage(otherMessage);

        List<Message> systemMessages = messageList.getSystemMessages("kid");

        assertEquals(1, systemMessages.size());
        assertTrue(systemMessages.contains(kidSystemMessage));
    }

    @Test
    public void testGetSystemMessagesForParent() {
        Message kidSystemMessage = new Message("system_kid", "System message for kid");
        Message parentSystemMessage = new Message("system_parent", "System message for parent");
        Message otherMessage = new Message("other", "Hello, world!");

        messageList.addMessage(kidSystemMessage);
        messageList.addMessage(parentSystemMessage);
        messageList.addMessage(otherMessage);

        List<Message> systemMessages = messageList.getSystemMessages("parent");

        assertEquals(1, systemMessages.size());
        assertTrue(systemMessages.contains(parentSystemMessage));
    }

    @Test
    public void testAddTaskMessageForChildOpt() {
        messageList.addTaskMessage("Child_Opt", "Task description for child");

        List<Message> messages = messageList.getAllMessages();

        assertEquals(1, messages.size());
        assertEquals("system_kid", messages.get(0).getSender());
        assertEquals("Task description for child", messages.get(0).getContent());
    }

    @Test
    public void testAddTaskMessageForParentOpt() {
        messageList.addTaskMessage("Parent_Opt", "Task description for parent");

        List<Message> messages = messageList.getAllMessages();

        assertEquals(1, messages.size());
        assertEquals("system_parent", messages.get(0).getSender());
        assertEquals("Task description for parent", messages.get(0).getContent());
    }


    @Test
    public void testToString() {
        Message message1 = new Message("Alice", "Hello, Bob!");
        Message message2 = new Message("Bob", "Hello, Alice!");

        messageList.addMessage(message1);
        messageList.addMessage(message2);

        String expected = "MessageList:\n" +
                "[" + message1.getTimestamp() + "] Hello, Bob!\n" +
                "[" + message2.getTimestamp() + "] Hello, Alice!\n";

        assertEquals(expected, messageList.toString());
    }
}





