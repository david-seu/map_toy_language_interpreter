package src.domain.exception;

import src.domain.value.Value;

public class VariableNotDefinedException extends MyException{
    public VariableNotDefinedException(String id) {
        super("Variable " + id + " is not defined!");
    }
}
