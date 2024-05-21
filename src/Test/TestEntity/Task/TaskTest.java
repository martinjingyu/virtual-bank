package Test.TestEntity.Task;

import static org.junit.jupiter.api.Assertions.*;

import Entity.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the Task class.
 */

public class TaskTest {

    @Test
    public void testGettersAndSetters() {
        Task task = new Task("Test Task", 100.0, "ToBeTaken", "Description", "Destination");

        // Test getters
        assertEquals("Test Task", task.getName());
        assertEquals(100.0, task.getReward());
        assertEquals("ToBeTaken", task.getState());
        assertEquals("Description", task.getDescription());
        assertEquals("Destination", task.getDestination());

        // Test setters
        task.setName("New Task Name");
        task.setReward(200.0);
        task.setState("Taken");
        task.setDescription("New Description");
        task.setDestination("New Destination");

        assertEquals("New Task Name", task.getName());
        assertEquals(200.0, task.getReward());
        assertEquals("Taken", task.getState());
        assertEquals("New Description", task.getDescription());
        assertEquals("New Destination", task.getDestination());
    }

    @Test
    public void testGetCondition() {
        Task task = new Task("Test Task", 100.0, "ToBeTaken", "Description", "Destination");

        assertEquals("Pick it", task.getCondition("ToBeTaken"));
        assertEquals("Submitted", task.getCondition("ToBeConfirmed"));
        assertEquals("Action", task.getCondition("Taken"));
    }

    @Test
    public void testGetCondition1() {
        Task task = new Task("Test Task", 100.0, "ToBeTaken", "Description", "Destination");

        assertEquals("Delete", task.getCondition1("ToBeTaken"));
        assertEquals("Review", task.getCondition1("ToBeConfirmed"));
        assertEquals("Pending", task.getCondition1("Taken"));
    }

    @Test
    public void testGetM() {
        Task task = new Task("Test Task", 100.0, "ToBeTaken", "Description", "Destination");

        assertEquals("You have picked the task", task.getM("ToBeTaken"));
        assertEquals("Please wait patiently for parent's confirmation, you have submitted the task", task.getM("ToBeConfirmed"));
        assertEquals("You have submitted the task", task.getM("Taken"));
        assertEquals("Parents have confirmed your task, you have received reward $", task.getM("Confirmed"));
    }

    @Test
    public void testGetCon1() {
        Task task = new Task("Test Task", 100.0, "ToBeTaken", "Description", "Destination");

        assertEquals("Do you want to pick this task?", task.getCon1("ToBeTaken"));
        assertEquals("Submitted", task.getCon1("ToBeConfirmed"));
        assertEquals("Do you want to submit or cancel this task? Yes for submit, No for cancel.", task.getCon1("Taken"));
    }

    @Test
    public void testGetCon2() {
        Task task = new Task("Test Task", 100.0, "ToBeTaken", "Description", "Destination");

        assertEquals("You have taken this task!", task.getCon2("ToBeTaken"));
        assertEquals("Submitted", task.getCon2("ToBeConfirmed"));
        assertEquals("You have submitted this task!", task.getCon2("Taken"));
    }

    @Test
    public void testGetText() {
        Task task = new Task("Test Task", 100.0, "ToBeTaken", "Description", "Destination");

        assertEquals("You can take this task", task.getText("ToBeTaken"));
        assertEquals("Please wait for parent's confirmation", task.getText("ToBeConfirmed"));
        assertEquals("You have taken this task.", task.getText("Taken"));
    }

    @Test
    public void testTaskOperation() {
        Task task = new Task("Test Task", 100.0, "ToBeTaken", "Description", "Destination");

        task = task.taskOperation(task);
        assertEquals("Taken", task.getState());

        task = task.taskOperation(task);
        assertEquals("ToBeConfirmed", task.getState());

        task = task.taskOperation(task);
        assertEquals("Confirmed", task.getState());
    }
}
