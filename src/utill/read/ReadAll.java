package utill.read;

import Entity.*;
import utill.Paths;

public class ReadAll {
    public static Kids readall(String id){


        Paths paths = new Paths(id);


        ProductList productList = new ProductList();
        ProductList selectedProductList = new ProductList();
        TaskList taskList = new TaskList();
        MessageList messageList =new MessageList();
        HistoryTransactionList historyTransactionList = new HistoryTransactionList();
        AccountManager accountManager = new AccountManager();

        ReadProduct.readProducts(paths.product_path,productList);
        ReadProduct.readProducts(paths.selectedProduct_path,selectedProductList);
        ReadTask.readTaskList(paths.task_path, taskList);
        ReadMessage.readMessage(paths.message_path, messageList);
        ReadTransaction.readTransactions(paths.transactionHistory_path,historyTransactionList);
        ReadAccount.readAccount(paths.account_path, accountManager);

        return new Kids(historyTransactionList,productList,selectedProductList,taskList,messageList, accountManager);

    }
}
