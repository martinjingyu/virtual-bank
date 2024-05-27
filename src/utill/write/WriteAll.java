package utill.write;

import Entity.Kids;
import utill.Paths;
import utill.read.ReadAll;

/**
 * The WriteAll class provides a method to write all data related to a Kid to files.
 */
public class WriteAll {

    /**
     * Writes all data related to a Kid to files.
     *
     * @param id  the ID of the Kid
     * @param kid the Kid object containing all data to be written
     */
    public static void writeAll(String id, Kids kid) {
        Paths paths = new Paths(id);

        // Write product data
        WriteProduct.writeProducts(kid.getProductList().getAllProducts(), paths.product_path);
        // Write selected product data
        WriteSelectedProduct.writeProducts(kid.getSelectedList().getAllProducts(), paths.selectedProduct_path);
        // Write task data
        WriteTask.writeTaskList(kid.getTaskList().getAllTasks(), paths.task_path);
        // Write message data
        WriteMessage.writeMessage(kid.getMessagelist(), paths.message_path);
        // Write transaction history data
        WriteHistoryTransaction.writeHistoryTransaction(kid.getTransactionList().getAllTransactions(), paths.transactionHistory_path);
        // Write account data
        WriteAccount.writeAccount(kid.getAccountManager(), paths.account_path);
    }
}
