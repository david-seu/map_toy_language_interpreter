package src.domain.exp;

import src.domain.exception.MyException;
import src.domain.prgstate.MyIDictionary;
import src.domain.value.Value;

public class VarExp implements Exp{
    private final String id;

    public VarExp(String i) {id=i;}

    @Override
    public Value eval(MyIDictionary<String, Value> tbl) throws MyException {
        return tbl.lookup(id);
    }

    @Override
    public String toString(){
        return id;
    }
}
