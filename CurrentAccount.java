/**
 * CurrentAccount.java
 * Rule: allows an overdraft (balance can go down to -5000).
 * Concepts: inheritance, method overriding, polymorphism.
 */
public class CurrentAccount extends Account {
    private static final double OVERDRAFT_LIMIT = 5000;

    public CurrentAccount(int accountNumber, String holderName, double balance) {
        super(accountNumber, holderName, balance);
    }

    @Override
    public void withdraw(double amount) throws InsufficientBalanceException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be greater than 0.");
        }
        if (balance - amount < -OVERDRAFT_LIMIT) {
            throw new InsufficientBalanceException(
                    "Overdraft limit of " + OVERDRAFT_LIMIT + " exceeded.");
        }
        balance -= amount;
    }

    @Override
    public String getAccountType() {
        return "Current";
    }
}
