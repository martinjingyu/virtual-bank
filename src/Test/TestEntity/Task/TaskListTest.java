package Test.TestEntity.Task;


import Entity.Task;
import Entity.TaskList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {
    private TaskList taskList;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
        taskList.addTask(new Task("Clean the room", 5.0, "ToBeTaken", "Sweep the floor", "Living Room"));
        taskList.addTask(new Task("Do homework", 3.0, "ToBeConfirmed", "Math homework", "Desk"));
        taskList.addTask(new Task("Wash dishes", 2.0, "Taken", "Wash all dishes", "Kitchen"));
    }

    @Test
    public void testAddTask() {
        Task newTask = new Task("Mow the lawn", 4.0, "ToBeTaken", "Mow the front yard", "Yard");
        taskList.addTask(newTask);
        assertEquals(4, taskList.getSize());
        assertEquals(newTask, taskList.getTaskByName("Mow the lawn"));
    }

    @Test
    public void testRemoveTask() {
        Task task = taskList.getTaskByName("Do homework");
        taskList.removeTask(task);
        assertEquals(2, taskList.getSize());
        assertNull(taskList.getTaskByName("Do homework"));
    }

    @Test
    public void testGetTask() {
        Task task = taskList.getTask(0);
        assertEquals("Clean the room", task.getName());
    }

    @Test
    public void testUpdateTask() {
        Task updatedTask = new Task("Clean the room", 6.0, "Confirmed", "Sweep and mop the floor", "Living Room");
        taskList.updateTask("Clean the room", updatedTask);
        Task task = taskList.getTaskByName("Clean the room");
        assertEquals(6.0, task.getReward());
        assertEquals("Confirmed", task.getState());
        assertEquals("Sweep and mop the floor", task.getDescription());
    }

    @Test
    public void testGetNonConfirmedTask() {
        TaskList nonConfirmedTasks = taskList.getNonConfirmedTask();
        assertEquals(3, nonConfirmedTasks.getSize());
    }

    @Test
    public void testChangeDepository() {
        taskList.changeDepository("Garage");
        for (Task task : taskList.getAllTasks()) {
            assertEquals("Garage", task.getDestination());
        }
    }

    @Test
    public void testGetAllTasks() {
        List<Task> tasks = taskList.getAllTasks();
        assertEquals(3, tasks.size());
    }

    @Test
    public void testGetSize() {
        assertEquals(3, taskList.getSize());
    }

    @Test
    public void testGetTaskByName() {
        Task task = taskList.getTaskByName("Clean the room");
        assertNotNull(task);
        assertEquals("Clean the room", task.getName());
    }

    @Test
    public void testCheckDuplicateName() {
        assertFalse(taskList.checkDuplicateName("Clean the room"));
        assertTrue(taskList.checkDuplicateName("Walk the dog"));
    }

    @Test
    public void testGetIndex() {
        int index = taskList.getIndex("Do homework");
        assertEquals(1, index);
        index = taskList.getIndex("Non-existing task");
        assertEquals(-10, index);
    }

    @Test
    public void testDescriptionSet() {
        String description = "This is a long description that should be wrapped correctly into multiple lines to fit the view.";
        String expectedHtml = "<html>This is a long description that should be <br>wrapped correctly into multiple lines to fit <br>the view. </html>";
        assertEquals(expectedHtml, taskList.descriptionSet(description));
    }
}
