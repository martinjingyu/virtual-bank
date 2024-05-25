package Entity;

/**
 * The Kids class represents a user entity with various lists and managers for handling accounts, products, tasks, transactions, and messages.
 */
public class Kids {
    private AccountManager accountManager;
    private ProductList productList;
    private ProductList selectedProduct;
    private TaskList taskList;
    private HistoryTransactionList transactionList;
    private MessageList messagelist;

    /**
     * Constructs a Kids object with default values.
     */
    public Kids() {
    }

    /**
     * Constructs a Kids object with specified lists and managers.
     *
     * @param transactionList the transaction list
     * @param productList     the product list
     * @param selectedProduct the selected product list
     * @param taskList        the task list
     * @param messagelist     the message list
     * @param accountManager  the account manager
     */
    public Kids(HistoryTransactionList transactionList,
                ProductList productList, ProductList selectedProduct, TaskList taskList, MessageList messagelist, AccountManager accountManager) {
        this.transactionList = transactionList;
        this.productList = productList;
        this.taskList = taskList;
        this.messagelist = messagelist;
        this.selectedProduct = selectedProduct;
        this.getMessagelist().setKids(this);
        this.accountManager = accountManager;
    }

    /**
     * Gets the account manager.
     *
     * @return the account manager
     */
    public AccountManager getAccountManager() {
        return accountManager;
    }

    /**
     * Gets the product list.
     *
     * @return the product list
     */
    public ProductList getProductList() {
        return this.productList;
    }

    /**
     * Gets the selected product list.
     *
     * @return the selected product list
     */
    public ProductList getSelectedList() {
        return this.selectedProduct;
    }

    /**
     * Gets the transaction list.
     *
     * @return the transaction list
     */
    public HistoryTransactionList getTransactionList() {
        return this.transactionList;
    }

    /**
     * Gets the task list.
     *
     * @return the task list
     */
    public TaskList getTaskList() {
        return taskList;
    }

    /**
     * Gets the message list.
     *
     * @return the message list
     */
    public MessageList getMessagelist() {
        return messagelist;
    }
}
