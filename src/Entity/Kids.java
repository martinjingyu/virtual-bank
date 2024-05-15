package Entity;

public class Kids {
    private AccountManager accountManager;
    private ProductList productList;
    private ProductList selectedProduct;
    private TaskList taskList;
    private HistoryTransactionList transactionList;
    private MessageList messagelist;

    public Kids(){
    }


    public Kids(HistoryTransactionList transactionList,
                ProductList productList, ProductList selectedProduct, TaskList taskList, MessageList messagelist, AccountManager accountManager){
        this.transactionList = transactionList;
        this.productList = productList;
        this.taskList = taskList;
        this.messagelist = messagelist;
        this.selectedProduct = selectedProduct;
        this.getMessagelist().setKids(this);
        this.accountManager = accountManager;

    }

    public AccountManager getAccountManager() {
        return accountManager;
    }


    public ProductList getProductList() {
        return this.productList;
    }
    public ProductList getSelectedList() {
        return this.selectedProduct;
    }

    public HistoryTransactionList getTransactionList(){return this.transactionList;}


    public TaskList getTaskList() {
        return taskList;
    }

    public MessageList getMessagelist() {
        return messagelist;
    }

}
