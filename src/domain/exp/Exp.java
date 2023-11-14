package src.domain.exp;

import src.domain.exception.MyException;
import src.domain.prgstate.MyIDictionary;
import src.domain.value.Value;

public interface Exp {
    Value eval(MyIDictionary<String, Value> tbl) throws MyException;
}
