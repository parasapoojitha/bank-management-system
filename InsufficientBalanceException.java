/**
 * InsufficientBalanceException.java
 * A custom (user-defined) exception, thrown when a withdrawal is not allowed.
 * Concept: custom exception by extending Exception.
 */
public class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}
