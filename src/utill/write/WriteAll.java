package utill.write;

import Entity.Kids;
import utill.Paths;
import utill.read.ReadAll;

public class WriteAll {
    public static void writeAll(String id, Kids kid){
        Paths paths = new Paths(id);


        WriteProduct.writeProducts(kid.getProductList().getAllProducts(),paths.product_path);
        WriteTask.writeTaskList(kid.getTaskList().getAllTasks(),paths.task_path);
        WriteMessage.writeMessage(kid.getMessagelist(),paths.message_path);

        WriteSelectedProduct.writeProducts(kid.getSelectedList().getAllProducts(), paths.selectedProduct_path);

    }
    public static void main (String[] args){
        Kids kid = ReadAll.readall("222");

        writeAll("test",kid);
    }
}
