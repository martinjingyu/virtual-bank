package utill.read;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.StringReader;
import java.io.IOException;
import Entity.Task;
import Entity.TaskList;

/**
 * Utility class to read tasks from files or strings into a TaskList.
 */
public class ReadTask {

    /**
     * Reads tasks from a file and adds them to the provided TaskList.
     *
     * @param fileName the name of the file to read tasks from
     * @param taskList the TaskList to add the read tasks to
     */
    public static void readTaskList(String fileName, TaskList taskList) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            readFromBufferedReader(br, taskList);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    /**
     * Reads tasks from a string and adds them to the provided TaskList.
     *
     * @param data the string data containing the tasks
     * @param taskList the TaskList to add the read tasks to
     */
    public static void readTasksFromString(String data, TaskList taskList) {
        try (BufferedReader br = new BufferedReader(new StringReader(data))) {
            readFromBufferedReader(br, taskList);
        } catch (IOException e) {
            System.out.println("Error reading data: " + e.getMessage());
        }
    }

    /**
     * Reads tasks from a BufferedReader and adds them to the provided TaskList.
     *
     * @param br the BufferedReader to read tasks from
     * @param taskList the TaskList to add the read tasks to
     * @throws IOException if an I/O error occurs
     */
    private static void readFromBufferedReader(BufferedReader br, TaskList taskList) throws IOException {
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split("\\*");
            if (parts.length == 5) {
                String name = parts[0].trim();
                double price = Double.parseDouble(parts[1].trim());
                String state = parts[2].trim();
                String description = parts[3].trim();
                String destination = parts[4].trim();
                Task task = new Task(name, price, state, description, destination);
                taskList.addTask(task);
            } else {
                System.out.println("Invalid task data: " + line);
            }
        }
    }

    /**
     * Main method for testing purposes. Reads tasks from a file and prints them.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        TaskList taskList = new TaskList();
        readTaskList("data/Kids/222/Task.txt", taskList);

        // Print all tasks in the list
        System.out.println("All tasks:");
        for (Task task : taskList.getAllTasks()) {
            System.out.println(task);
        }
    }
}
