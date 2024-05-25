package utill;

import java.util.ArrayList;
import java.util.List;

/**
 * The Paths class holds the file paths for various data related to a specific user.
 */
public class Paths {
    public String product_path;
    public String task_path;
    public String message_path;
    public String transactionHistory_path;
    public String selectedProduct_path;
    public List<String> account_path;

    /**
     * Constructs a Paths object for a specific user ID.
     *
     * @param id the user ID
     */
    public Paths(String id) {
        account_path = new ArrayList<>();
        product_path = "data/Kids/" + id + "/Product.txt";
        task_path = "data/Kids/" + id + "/Task.txt";
        message_path = "data/Kids/" + id + "/Message.txt";
        transactionHistory_path = "data/Kids/" + id + "/TransactionHistory.txt";
        selectedProduct_path = "data/Kids/" + id + "/SelectedProduct.txt";
        account_path.add("data/Kids/" + id + "/CurrentAccount.txt");
        account_path.add("data/Kids/" + id + "/SavingAccount.txt");
        account_path.add("data/Kids/" + id + "/User.txt");
    }
}
