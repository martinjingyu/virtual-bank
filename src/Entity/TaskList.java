package Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The TaskList class represents a list of tasks and provides various methods to manage the tasks.
 */
public class TaskList {
    private List<Task> taskList;

    /**
     * Constructs a new TaskList with an empty list of tasks.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Adds a new task to the task list.
     *
     * @param task the task to be added
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Removes a task from the task list.
     *
     * @param task the task to be removed
     */
    public void removeTask(Task task) {
        taskList.remove(task);
    }

    /**
     * Returns the task at the specified index.
     *
     * @param index the index of the task to be returned
     * @return the task at the specified index
     */
    public Task getTask(int index) {
        return taskList.get(index);
    }

    /**
     * Updates the task with the specified name with new task data.
     *
     * @param taskName the name of the task to be updated
     * @param newTaskData the new task data to update
     */
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

    /**
     * Returns a TaskList of tasks that are not confirmed.
     *
     * @return a TaskList of tasks that are not confirmed
     */
    public TaskList getNonConfirmedTask() {
        TaskList nonTask = new TaskList();
        for (Task task : taskList) {
            if (!Objects.equals(task.getState(), "Confirmed")) {
                nonTask.addTask(task);
            }
        }
        return nonTask;
    }

    /**
     * Changes the destination of all tasks in the task list.
     *
     * @param destination the new destination to be set for all tasks
     * @return the TaskList after changing the destination
     */
    public TaskList changeDepository(String destination) {
        for (Task task : taskList) {
            task.setDestination(destination);
        }
        return this;
    }

    /**
     * Returns a list of all tasks in the task list.
     *
     * @return a list of all tasks in the task list
     */
    public List<Task> getAllTasks() {
        return taskList;
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return the number of tasks in the task list
     */
    public int getSize() {
        return taskList.size();
    }

    /**
     * Returns the task with the specified name.
     *
     * @param name the name of the task to be returned
     * @return the task with the specified name, or null if no task with the name exists
     */
    public Task getTaskByName(String name) {
        for (Task task : taskList) {
            if (task.getName().equals(name)) {
                return task;
            }
        }
        return null;
    }

    /**
     * Checks if there is a duplicate task name in the task list.
     *
     * @param name the name of the task to be checked
     * @return true if there is no duplicate task name, false otherwise
     */
    public boolean checkDuplicateName(String name) {
        for (Task task : taskList) {
            if (task.getName().equals(name)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns the index of the task with the specified name.
     *
     * @param name the name of the task
     * @return the index of the task with the specified name, or -10 if no task with the name exists
     */
    public int getIndex(String name) {
        for (Task task : taskList) {
            if (task.getName().equals(name)) {
                return taskList.indexOf(task);
            }
        }
        return -10;
    }

    /**
     * Formats a description into an HTML string with line breaks.
     *
     * @param description the description to be formatted
     * @return the formatted HTML string
     */
    public String descriptionSet(String description) {
        StringBuilder htmlBuilder = new StringBuilder("<html>");
        int length = 0;
        int maxLineLength = 45;
        for (String word : description.split(" ")) {
            if (length + word.length() > maxLineLength) {
                htmlBuilder.append("<br>");
                length = 0;
            }
            htmlBuilder.append(word).append(" ");
            length += word.length() + 1;
        }

        htmlBuilder.append("</html>");
        return htmlBuilder.toString();
    }
}
