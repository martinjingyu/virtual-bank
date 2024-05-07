package Entity;

import java.util.List;

public class Kids {
    private Bank bank;
    private ProductList productList;
    private TaskList taskList;
    private HistoryTransactionList transactionList;
    private MessageList messagelist;

    public Kids(){

    }

    public Kids(Bank bank,HistoryTransactionList transactionList,
                ProductList productList, TaskList taskList,MessageList messagelist){
        this.bank = bank;
        this.transactionList = transactionList;
        this.productList = productList;
        this.taskList = taskList;
        this.messagelist = messagelist;
        this.getMessagelist().setKids(this);
    }

    public ProductList getProductList() {
        return this.productList;
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
