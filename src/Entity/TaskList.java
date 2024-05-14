package Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TaskList{
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
    public Task getTask(int index){
        return taskList.get(index);
    }

    public void updateTask(String taskName, Task newTaskData) {
        for (Task task : taskList) {
            if (task.getName().equals(taskName)) {
                task.setName(newTaskData.getName());
                task.setReward(newTaskData.getReward());
                task.setState(newTaskData.getState());
                task.setDescription(newTaskData.getDescription());
                task.setDestination(newTaskData.getDestination());
                break;
            }
        }
    }

    public TaskList getNonConfirmedTask(){
        TaskList nonTask = new TaskList();
        for (Task task : taskList) {
            if (!Objects.equals(task.getState(), "Confirmed")) {
                nonTask.addTask(task);
            }
        }
        return nonTask;
    }
    public TaskList changeDepository(String destination){
        TaskList tasks = new TaskList();
        for (Task task : taskList) {
            task.setDestination(destination);
        }
        return tasks;
    }



    public List<Task> getAllTasks() {
        return taskList;
    }

    public int getSize(){
        return taskList.size();
    }

    public Task getTaskByName(String name) {
        for (Task task : taskList) {
            if (task.getName().equals(name)) {
                return task;
            }
        }
        return null;
    }
    public int getIndex(String name){
        for (Task task : taskList) {
            if (task.getName().equals(name)) {
                return taskList.indexOf(task);
            }
        }
        return -10;
    }

    public String descriptionSet(Task task){
        StringBuilder htmlBuilder = new StringBuilder("<html>");
        int length = 0;
        int maxLineLength = 59;
        for (String word : task.getDescription().split(" ")) {
            // Check if adding this word would exceed the max line length
            if (length + word.length() > maxLineLength) {
                htmlBuilder.append("<br>");
                length = 0;
            }
            htmlBuilder.append(word).append(" ");
            length += word.length() + 1; // add 1 for the space
        }

        htmlBuilder.append("</html>");
        return htmlBuilder.toString();
    }


    // You can add more methods as needed

    public static void main(String[] args) {
        // Example usage
        TaskList taskList = new TaskList();

        // Add some tasks
        taskList.addTask(new Task("Clean the room", 5.0,"checking","Please sweep the floor in the living room and kitchen.Please sweep the floor in the living room and kitchen.Please sweep the floor in the living room and kitchen.","saving"));
        taskList.addTask(new Task("Do homework", 3.0,"done","111","current"));
        taskList.addTask(new Task("Wash dishes", 2.0,"processing","111","current"));
        taskList.addTask(new Task("Wash dishes", 2.0,"released","111","current"));

        // Get all tasks
//        List<Task> allTasks = taskList.getAllTasks();
//        System.out.println("All tasks:");
//        for (Task task : allTasks) {
//            System.out.println(task);
//        }
//        taskList.changeDepository("saving");
//        List<Task> allTasks1 = taskList.getAllTasks();
//        System.out.println("All tasks1:");
//        for (Task task : allTasks1) {
//            System.out.println(task);
//        }

        System.out.println(taskList.descriptionSet(taskList.getTask(0)));


        // Get a task by name
        Task cleanRoomTask = taskList.getTaskByName("Clean the room");
        System.out.println("\nTask found by name: " + cleanRoomTask);
    }
}