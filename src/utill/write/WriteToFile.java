package utill.write;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WriteToFile {

    public static void writeTextToFile(String text, String filePath) {

        try {

            // 创建 FileWriter 对象，并设置为在文件末尾追加内容
            FileWriter fw = new FileWriter(filePath, true);
            BufferedWriter bw = new BufferedWriter(fw);

            // 写入文本内容
            bw.write(text);
            bw.newLine(); // 写入换行符

            // 关闭 BufferedWriter
            bw.close();

            System.out.println("内容已成功写入文件。" + text);
        } catch (IOException e) {
            System.err.println("写入文件时出现错误：" + e.getMessage());
        }
    }

}
