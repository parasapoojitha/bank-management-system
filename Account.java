/**
 * Account.java
 * Abstract parent class for all account types.
 * Concepts: abstraction, encapsulation, inheritance (parent class).
 */
public abstract class Account {
    private int accountNumber;
    private String holderName;
    protected double balance;   // protected so child classes can use it

    public Account(int accountNumber, String holderName, double balance) {
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.balance = balance;
    }

    public int getAccountNumber() { return accountNumber; }
    public String getHolderName() { return holderName; }
    public double getBalance() { return balance; }

    // Same for every account type
    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be greater than 0.");
        }
        balance += amount;
    }

    // Each account type has its own withdrawal rule (polymorphism)
    public abstract void withdraw(double amount) throws InsufficientBalanceException;

    // Each account type has its own name
    public abstract String getAccountType();

    @Override
    public String toString() {
        return String.format("A/C No: %-5d Name: %-15s Type: %-8s Balance: %.2f",
                accountNumber, holderName, getAccountType(), balance);
    }
}
