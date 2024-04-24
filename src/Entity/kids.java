package Entity;

public class kids {
    private int money;
    private float saving_goal;
    private ProductList productList;
    private TaskList taskList;
//    private HistoryTransectionList transectionList;
//    private MessageList messagelist;

    public kids(int money, float saving_goal, ProductList productList, TaskList taskList){
        this.money = money;
        this.saving_goal = saving_goal;
        this.productList = productList;
        this.taskList = taskList;
    }
}
