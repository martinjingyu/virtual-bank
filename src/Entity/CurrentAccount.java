package Entity;

/**
 * The CurrentAccount class represents a type of bank account that does not earn interest.
 * It extends the Account class.
 */
public class CurrentAccount extends Account {

    /**
     * Constructs a CurrentAccount with the specified name and an initial balance of zero.
     *
     * @param name the name of the account holder
     */
    public CurrentAccount(String name) {
        super(name, 0, 0);
    }

    /**
     * Constructs a CurrentAccount with the specified name and initial balance.
     * The interest rate is set to zero as current accounts do not earn interest.
     *
     * @param name           the name of the account holder
     * @param initialBalance the initial balance of the account
     */
    public CurrentAccount(String name, double initialBalance) {
        super(name, initialBalance, 0);  // Set interest rate to 0 as current accounts do not earn interest
    }

    /**
     * Calculates interest for the current account.
     * This method does not perform any interest calculation as the interest rate is always zero for current accounts.
     */
    @Override
    public void calculateInterest() {
        // No interest calculation for current accounts, as the interest rate is always 0.
        // Method can remain empty or contain logging if required.
        System.out.println("No interest to calculate for current accounts.");
    }
}