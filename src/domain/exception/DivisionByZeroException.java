package src.domain.exception;

public class DivisionByZeroException extends MyException{
    public DivisionByZeroException() {
        super("Division by zero!");
    }
}
