package Entity;

public class CurrentAccount extends Account {
    // Constructor

    public CurrentAccount(String name){
        super(name,0,0);
    }
    public CurrentAccount(String name,double initialBalance) {
        super(name,initialBalance, 0);  // Set interest rate to 0 as current accounts do not earn interest
    }

    @Override
    public void calculateInterest() {
        // No interest calculation for current accounts, as the interest rate is always 0.
        // Method can remain empty or contain logging if required.
        System.out.println("No interest to calculate for current accounts.");
    }
}
