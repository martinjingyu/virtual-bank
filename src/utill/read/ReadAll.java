package utill.read;

import Entity.*;
import utill.Paths;

public class ReadAll {
    public static Kids readall(String id){


        Paths paths = new Paths(id);

        Bank bank = ReadBank.readBank(paths.bank_path);
        ProductList productList = new ProductList();
        ProductList selectedProductList = new ProductList();
        TaskList taskList = new TaskList();
        MessageList messageList =new MessageList();
        HistoryTransactionList historyTransactionList = new HistoryTransactionList();


        ReadProduct.readProducts(paths.product_path,productList);
        ReadProduct.readProducts(paths.selectedProduct_path,selectedProductList);
        ReadTask.readTaskList(paths.task_path, taskList);
        ReadMessage.readMessage(paths.message_path, messageList);

        return new Kids(bank,historyTransactionList,productList,selectedProductList,taskList,messageList);

    }
}
