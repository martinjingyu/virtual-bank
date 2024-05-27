package utill.read;

import Entity.*;
import utill.Paths;
import utill.cryption.EncryptionUtil;

import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility class to read all necessary data for Kids object.
 */
public class ReadAll {

    /**
     * Reads and decrypts all necessary data based on the given ID and returns a Kids object.
     *
     * @param id the ID used to generate the file paths
     * @return a Kids object containing all decrypted data
     * @throws Exception if any file reading or decryption error occurs
     */
    public static Kids readall(String id) throws Exception {
        Paths paths = new Paths(id);

        ProductList productList = new ProductList();
        ProductList selectedProductList = new ProductList();
        TaskList taskList = new TaskList();
        MessageList messageList = new MessageList();
        HistoryTransactionList historyTransactionList = new HistoryTransactionList();
        AccountManager accountManager = new AccountManager(historyTransactionList);

        // Decrypt and read the product list
        String decryptedProducts = decryptFileContents(paths.product_path);
        ReadProduct.readProductsFromString(decryptedProducts, productList);

        // Decrypt and read the selected product list
        String decryptedSelectedProducts = decryptFileContents(paths.selectedProduct_path);
        ReadSelectedProduct.readProductsFromString(decryptedSelectedProducts, selectedProductList);

        // Decrypt and read the task list
        String decryptedTasks = decryptFileContents(paths.task_path);
        ReadTask.readTasksFromString(decryptedTasks, taskList);

        // Decrypt and read the message list
        String decryptedMessages = decryptFileContents(paths.message_path);
        ReadMessage.readMessagesFromString(decryptedMessages, messageList);

        // Decrypt and read the transaction history list
        String decryptedTransactions = decryptFileContents(paths.transactionHistory_path);
        ReadTransaction.readTransactionsFromString(decryptedTransactions, historyTransactionList);

        // Decrypt and read the account manager
        List<String> decryptedAccounts = decryptMultipleFileContents(paths.account_path);
        ReadAccount.readAccountsFromString(decryptedAccounts, accountManager);

        return new Kids(historyTransactionList, productList, selectedProductList, taskList, messageList, accountManager);
    }

    /**
     * Decrypts the contents of a file.
     *
     * @param filePath the path of the file to be decrypted
     * @return the decrypted content of the file as a string
     * @throws Exception if any file reading or decryption error occurs
     */
    public static String decryptFileContents(String filePath) throws Exception {
        byte[] fileContent = Files.readAllBytes(java.nio.file.Paths.get(filePath));
        return EncryptionUtil.decrypt(new String(fileContent, java.nio.charset.StandardCharsets.UTF_8));  // Assuming EncryptionUtil.decrypt() takes a string and returns a string.
    }

    /**
     * Decrypts the contents of multiple files.
     *
     * @param filePaths the list of file paths to be decrypted
     * @return a list of decrypted content of the files as strings
     * @throws Exception if any file reading or decryption error occurs
     */
    private static List<String> decryptMultipleFileContents(List<String> filePaths) throws Exception {
        List<String> output = new ArrayList<>();
        for (String filePath : filePaths) {
            output.add(decryptFileContents(filePath));
        }
        return output;
    }
}
