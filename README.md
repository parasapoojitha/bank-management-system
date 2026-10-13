# Bank Management System

A console-based Java application that simulates basic banking operations using core OOP concepts.

## Features
- Create Savings and Current accounts
- Deposit, withdraw, transfer money, and check balance
- Savings account enforces a minimum balance of 500
- Current account allows an overdraft up to 5000
- Custom exception for failed transactions
- Input validation for all user entries

## OOP Concepts Used
- **Abstraction:** abstract class `Account`
- **Inheritance:** `SavingsAccount` and `CurrentAccount` extend `Account`
- **Polymorphism:** each account type overrides `withdraw()` with its own rule
- **Encapsulation:** private fields with getters
- **Exception handling:** custom `InsufficientBalanceException`, try-catch
- **Collections:** `HashMap` to store accounts by account number

## Project Structure
| File | Purpose |
|------|---------|
| `Account.java` | Abstract parent class |
| `SavingsAccount.java` | Savings rules (minimum balance) |
| `CurrentAccount.java` | Current rules (overdraft) |
| `InsufficientBalanceException.java` | Custom exception |
| `Bank.java` | Stores accounts, handles transfers |
| `Main.java` | Menu-driven user interface |

## How to Run
```bash
javac *.java
java Main
```

## Future Improvements
- Save accounts to a file or MySQL database using JDBC
- Add transaction history and PIN login
- Build a GUI with JavaFX or Swing
