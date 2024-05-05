package utill;

public class Paths {
    public String bank_path;
    public String product_path;
    public String task_path;
    public String message_path;
    public String transactionHistory_path;
    public Paths(String id){
        bank_path = "data/Kids/"+id+"/Bank.txt";
        product_path = "data/Kids/"+id+"/Product.txt";
        task_path = "data/Kids/"+id+"/Task.txt";
        message_path = "data/Kids/"+id+"/Message.txt";
        transactionHistory_path = "data/Kids/"+id+"/TransactionHistory";
    }
}
