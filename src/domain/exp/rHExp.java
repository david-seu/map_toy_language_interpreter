package src.domain.exp;

import src.domain.exception.MyException;
import src.domain.prgstate.MyIDictionary;
import src.domain.prgstate.PrgState;
import src.domain.stmt.IStmt;
import src.domain.type.RefType;
import src.domain.value.Value;

public class rHExp implements Exp {
    private Exp exp;

    public rHExp(Exp exp){
        this.exp = exp;
    }


    @Override
    public Value eval(MyIDictionary<String, Value> symTable, MyIDictionary<Integer, Value> heap) throws MyException {
        Value valExp = exp.eval(symTable, heap);
        if(!(valExp.getType() instanceof RefType))
        {
            throw new MyException("Expression is not a reference");
        }
        Integer address = (Integer) valExp.getVal();
        if(!heap.isDefined(address)){
            throw new MyException("Address is not defined");
        }
        return heap.lookup(address);
    }

    @Override
    public String toString(){
        return "rH(" + exp.toString() + ")";
    }


}