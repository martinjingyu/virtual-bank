package Entity;

public class Kids {
    private Bank bank;
    private ProductList productList;
    private ProductList selectedProduct;
    private TaskList taskList;
    private HistoryTransactionList transactionList;
    private MessageList messagelist;
    private AccountManager accountManager;
    public Kids(){
    }


    public Kids(Bank bank, HistoryTransactionList transactionList,
                ProductList productList, ProductList selectedProduct, TaskList taskList, MessageList messagelist, AccountManager accountManager){
        this.bank = bank;
        this.transactionList = transactionList;
        this.productList = productList;
        this.taskList = taskList;
        this.messagelist = messagelist;
        this.selectedProduct = selectedProduct;
        this.getMessagelist().setKids(this);
        this.accountManager = accountManager;

    }

    public AccountManager getBothAccountList() {
        return accountManager;
    }


    public ProductList getProductList() {
        return this.productList;
    }
    public ProductList getSelectedList() {
        return this.selectedProduct;
    }

    public HistoryTransactionList getTransactionList(){return this.transactionList;}

    public Bank getBank() {
        return bank;
    }

    public TaskList getTaskList() {
        return taskList;
    }

    public MessageList getMessagelist() {
        return messagelist;
    }

}
