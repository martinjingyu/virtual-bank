package Entity;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public void removeTask(Task task) {
        taskList.remove(task);
    }

    public List<Task> getAllTasks() {
        return taskList;
    }

    public Task getTaskByName(String name) {
        for (Task task : taskList) {
            if (task.getName().equals(name)) {
                return task;
            }
        }
        return null;
    }

    // You can add more methods as needed

    public static void main(String[] args) {
        // Example usage
        TaskList taskList = new TaskList();

        // Add some tasks
        taskList.addTask(new Task("Clean the room", 5.0,"checking","111"));
        taskList.addTask(new Task("Do homework", 3.0,"done","111"));
        taskList.addTask(new Task("Wash dishes", 2.0,"processing","111"));
        taskList.addTask(new Task("Wash dishes", 2.0,"released","111"));

        // Get all tasks
        List<Task> allTasks = taskList.getAllTasks();
        System.out.println("All tasks:");
        for (Task task : allTasks) {
            System.out.println(task);
        }

        // Get a task by name
        Task cleanRoomTask = taskList.getTaskByName("Clean the room");
        System.out.println("\nTask found by name: " + cleanRoomTask);
    }
}