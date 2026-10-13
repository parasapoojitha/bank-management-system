import java.util.Scanner;

/**
 * Main.java
 * Console menu for the Bank Management System.
 * Concepts: Scanner, loops, switch, exception handling (try-catch).
 */
public class Main {
    static Scanner sc = new Scanner(System.in);
    static Bank bank = new Bank();

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n===== Bank Management System =====");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. Check Balance");
            System.out.println("6. View All Accounts");
            System.out.println("7. Exit");
            choice = readInt("Enter your choice: ");

            try {
                switch (choice) {
                    case 1: createAccount(); break;
                    case 2: deposit(); break;
                    case 3: withdraw(); break;
                    case 4: transfer(); break;
                    case 5: checkBalance(); break;
                    case 6: bank.showAll(); break;
                    case 7: System.out.println("Thank you for banking with us!"); break;
                    default: System.out.println("Invalid choice. Try 1-7.");
                }
            } catch (InsufficientBalanceException e) {
                System.out.println("Transaction failed: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (choice != 7);
    }

    static void createAccount() {
        System.out.println("Account type: 1. Savings (min balance 500)  2. Current (overdraft 5000)");
        String type = readInt("Choose type (1 or 2): ") == 1 ? "1" : "2";
        System.out.print("Enter holder name: ");
        String name = sc.nextLine().trim();
        if (name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty.");
        }
        double opening = readDouble("Enter opening balance: ");
        if (type.equals("1") && opening < 500) {
            throw new IllegalArgumentException("Savings account needs at least 500 to open.");
        }
        Account acc = bank.createAccount(type, name, opening);
        System.out.println("Account created! Your account number is " + acc.getAccountNumber());
    }

    static void deposit() {
        Account acc = findAccount();
        double amount = readDouble("Enter amount to deposit: ");
        acc.deposit(amount);
        System.out.println("Deposited. New balance: " + acc.getBalance());
    }

    static void withdraw() throws InsufficientBalanceException {
        Account acc = findAccount();
        double amount = readDouble("Enter amount to withdraw: ");
        acc.withdraw(amount);
        System.out.println("Withdrawn. New balance: " + acc.getBalance());
    }

    static void transfer() throws InsufficientBalanceException {
        int from = readInt("Enter FROM account number: ");
        int to = readInt("Enter TO account number: ");
        double amount = readDouble("Enter amount to transfer: ");
        bank.transfer(from, to, amount);
        System.out.println("Transfer successful.");
    }

    static void checkBalance() {
        Account acc = findAccount();
        System.out.println(acc);
    }

    // Asks for an account number and returns the account, or throws if not found
    static Account findAccount() {
        int number = readInt("Enter account number: ");
        Account acc = bank.getAccount(number);
        if (acc == null) {
            throw new IllegalArgumentException("Account not found.");
        }
        return acc;
    }

    // ---------- Helper methods for safe input ----------

    static int readInt(String message) {
        while (true) {
            System.out.print(message);
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid whole number.");
            }
        }
    }

    static double readDouble(String message) {
        while (true) {
            System.out.print(message);
            try {
                return Double.parseDouble(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
}
