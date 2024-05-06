package Entity;

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
    }

    public Bank getBank() {
        return bank;
    }

    public TaskList getTaskList() {
        return taskList;
    }


}
