package utill.write;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import Entity.Task;
import Entity.TaskList;


public class WriteTask {
    public static void writeTaskList(List<Task> taskList, String fileName) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (Task task : taskList) {
                // 将任务信息以逗号分隔的格式写入文件
                bw.write(task.getName() + "," + task.getReward() + "," + task.getState() + "," + task.getDescription() + "," + task.getDestination());
                bw.newLine(); // 写入新行
            }
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }
}
