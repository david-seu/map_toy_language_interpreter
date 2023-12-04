package src.domain.exp;

import src.domain.exception.MyException;
import src.domain.prgstate.MyIDictionary;
import src.domain.type.RefType;
import src.domain.value.Value;

public class VarExp implements Exp{
    private final String id;

    public VarExp(String i) {id=i;}

    @Override
    public Value eval(MyIDictionary<String, Value> symTbl, MyIDictionary<Integer, Value> heap) throws MyException {

        Value val = symTbl.lookup(id);
        return val;
    }

    @Override
    public String toString(){
        return id;
    }
}
