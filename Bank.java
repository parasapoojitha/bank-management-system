import java.util.HashMap;

/**
 * Bank.java
 * Stores all accounts and performs bank operations.
 * Concepts: HashMap (key = account number), polymorphism
 * (an Account variable can hold a SavingsAccount or CurrentAccount).
 */
public class Bank {
    private HashMap<Integer, Account> accounts = new HashMap<>();
    private int nextAccountNumber = 1001;

    // Create a new account and return it
    public Account createAccount(String type, String name, double openingBalance) {
        Account acc;
        if (type.equals("1")) {
            acc = new SavingsAccount(nextAccountNumber, name, openingBalance);
        } else {
            acc = new CurrentAccount(nextAccountNumber, name, openingBalance);
        }
        accounts.put(nextAccountNumber, acc);
        nextAccountNumber++;
        return acc;
    }

    // Returns null if the account does not exist
    public Account getAccount(int accountNumber) {
        return accounts.get(accountNumber);
    }

    // Move money from one account to another
    public void transfer(int fromAcc, int toAcc, double amount)
            throws InsufficientBalanceException {
        Account from = getAccount(fromAcc);
        Account to = getAccount(toAcc);
        if (from == null || to == null) {
            throw new IllegalArgumentException("One of the accounts does not exist.");
        }
        if (fromAcc == toAcc) {
            throw new IllegalArgumentException("Cannot transfer to the same account.");
        }
        from.withdraw(amount);   // if this fails, deposit below never runs
        to.deposit(amount);
    }

    public void showAll() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts found.");
            return;
        }
        for (Account a : accounts.values()) {
            System.out.println(a);
        }
    }
}
