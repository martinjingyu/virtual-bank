package utill.write;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import Entity.Task;
import Entity.TaskList;
import utill.cryption.EncryptionUtil;


public class WriteTask {
    public static void writeTaskList(List<Task> taskList, String fileName) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            StringBuilder buffer = new StringBuilder();
            for (Task task : taskList) {
                // 将任务信息以星号分隔的格式写入文件
                buffer.append(task.getName() + "*" + task.getReward() + "*" + task.getState() + "*" + task.getDescription() + "*" + task.getDestination()).append(System.lineSeparator());

            }
            String encryptedData = EncryptionUtil.encrypt(buffer.toString());
            bw.write(encryptedData);
        }
        catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
        catch (Exception ex) {
            System.out.println("Error encrypting data: " + ex.getMessage());
        }
    }
}
