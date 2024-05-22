package utill.read;

import Entity.*;
import utill.Paths;
import utill.cryption.EncryptionUtil;

import java.nio.file.Files;

public class Read {
    public static Kids readall(String id) throws Exception {
        Paths paths = new Paths(id);

        ProductList productList = new ProductList();
        ProductList selectedProductList = new ProductList();
        TaskList taskList = new TaskList();
        MessageList messageList = new MessageList();
        HistoryTransactionList historyTransactionList = new HistoryTransactionList();
        AccountManager accountManager = new AccountManager(historyTransactionList);

        // 解密并读取产品列表
        String decryptedProducts = decryptFileContents(paths.product_path);
        ReadProduct.readProductsFromString(decryptedProducts, productList);

//        String decryptedSelectedProducts = decryptFileContents(paths.selectedProduct_path);
//        ReadProduct.readProductsFromString(decryptedSelectedProducts, selectedProductList);
//
//        String decryptedTasks = decryptFileContents(paths.task_path);
//        ReadTask.readTasksFromString(decryptedTasks, taskList);
//
//        String decryptedMessages = decryptFileContents(paths.message_path);
//        ReadMessage.readMessagesFromString(decryptedMessages, messageList);
//
//        String decryptedTransactions = decryptFileContents(paths.transactionHistory_path);
//        ReadTransaction.readTransactionsFromString(decryptedTransactions, historyTransactionList);
//
//        String decryptedAccounts = decryptFileContents(paths.account_path);
//        ReadAccount.readAccountsFromString(decryptedAccounts, accountManager);

        return new Kids(historyTransactionList, productList, selectedProductList, taskList, messageList, accountManager);
    }

    private static String decryptFileContents(String filePath) throws Exception {
        byte[] fileContent = Files.readAllBytes(java.nio.file.Paths.get(filePath));
        return EncryptionUtil.decrypt(new String(fileContent, java.nio.charset.StandardCharsets.UTF_8));  // Assuming EncryptionUtil.decrypt() takes a string and returns a string.
    }
}
