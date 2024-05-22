package utill.read;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.StringReader;
import java.io.IOException;
import Entity.Task;
import Entity.TaskList;

public class ReadTask {
    // 从文件读取
    public static void readTaskList(String fileName, TaskList taskList) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            readFromBufferedReader(br, taskList);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    // 从字符串读取
    public static void readTasksFromString(String data, TaskList taskList) {
        try (BufferedReader br = new BufferedReader(new StringReader(data))) {
            readFromBufferedReader(br, taskList);
        } catch (IOException e) {
            System.out.println("Error reading data: " + e.getMessage());
        }
    }

    // 从BufferedReader读取任务数据
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

    public static void main(String[] args) {
        TaskList taskList = new TaskList();
        readTaskList("data/Kids/222/Task.txt", taskList);

        // Print all products in the list
        System.out.println("All task:");
        for (Task task : taskList.getAllTasks()) {
            System.out.println(task);
        }
    }
}
