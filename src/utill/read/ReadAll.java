package utill.read;

import Entity.*;
import utill.Paths;
import utill.cryption.EncryptionUtil;

import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class ReadAll {
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

        // 解密并读取 selectedProductList
        String decryptedSelectedProducts = decryptFileContents(paths.selectedProduct_path);
        ReadSelectedProduct.readProductsFromString(decryptedSelectedProducts, selectedProductList);

        // 解密并读取 taskList
        String decryptedTasks = decryptFileContents(paths.task_path);
        ReadTask.readTasksFromString(decryptedTasks, taskList);

        // 解密并读取 messageList
        String decryptedMessages = decryptFileContents(paths.message_path);
        ReadMessage.readMessagesFromString(decryptedMessages, messageList);

        // 解密并读取 historyTransactionList
        String decryptedTransactions = decryptFileContents(paths.transactionHistory_path);
        ReadTransaction.readTransactionsFromString(decryptedTransactions, historyTransactionList);

        // 解密并读取 accountManager
        List<String> decryptedAccounts = decryptMultipleFileContents(paths.account_path);
        ReadAccount.readAccountsFromString(decryptedAccounts, accountManager);

        return new Kids(historyTransactionList, productList, selectedProductList, taskList, messageList, accountManager);
    }

    public static String decryptFileContents(String filePath) throws Exception {
        byte[] fileContent = Files.readAllBytes(java.nio.file.Paths.get(filePath));
        return EncryptionUtil.decrypt(new String(fileContent, java.nio.charset.StandardCharsets.UTF_8));  // Assuming EncryptionUtil.decrypt() takes a string and returns a string.
    }

    private static List<String> decryptMultipleFileContents(List<String> filePaths) throws Exception {
        List<String> output = new ArrayList<>();
        for (String filePath : filePaths) {
            output.add(decryptFileContents(filePath));
        }
        return output;
    }
}
