package Entity;

import java.util.List;

public class Kids {
    private Bank bank;
    private ProductList productList;
    private ProductList selectedProduct;
    private TaskList taskList;
    private HistoryTransactionList transactionList;
    private MessageList messagelist;
    private List<SavingAccount> savingAccountList;

    public Kids(){

    }

    public Kids(Bank bank,HistoryTransactionList transactionList,
                ProductList productList, ProductList selectedProduct, TaskList taskList, MessageList messagelist){
        this.bank = bank;
        this.transactionList = transactionList;
        this.productList = productList;
        this.taskList = taskList;
        this.messagelist = messagelist;
        this.selectedProduct = selectedProduct;
        this.getMessagelist().setKids(this);

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

    public List<SavingAccount> getSavingAccountList(){
        return this.savingAccountList;
    }
}
