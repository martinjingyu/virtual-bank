package utill.read;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import Entity.Task;
import Entity.TaskList;
public class ReadTask {
    public static void readTaskList(String fileName, TaskList taskList) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
//                System.out.println(parts.length);
                if (parts.length == 3) {
                    String name = parts[0].trim();
                    double price = Double.parseDouble(parts[1].trim());
                    String state = parts[2].trim();
                    Task task = new Task(name, price,state);
                    taskList.addTask(task);
                } else {
                    System.out.println("Invalid product data: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
    public static void main(String[] args) {
        TaskList taskList = new TaskList();
        readTaskList("data/Kids/Martin/Task.txt", taskList);

        // Print all products in the list
        System.out.println("All task:");
        for (Task task : taskList.getAllTasks()) {
            System.out.println(task);
        }
    }
}
