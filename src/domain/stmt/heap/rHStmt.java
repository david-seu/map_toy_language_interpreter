package src.domain.stmt.heap;

import src.domain.exception.MyException;
import src.domain.exp.Exp;
import src.domain.prgstate.MyIDictionary;
import src.domain.prgstate.PrgState;
import src.domain.stmt.IStmt;
import src.domain.type.RefType;
import src.domain.value.RefValue;
import src.domain.value.Value;

public class rHStmt implements IStmt {

    private Exp exp;

    public rHStmt(Exp exp){
        this.exp = exp;
    }


    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIDictionary<String,Value> symTable = state.getSymTable();
        MyIDictionary<Integer,Value> heap = state.getHeap();
        Value valExp = exp.eval(symTable);
        if(!valExp.getType().equals(new RefType()))
        {
            throw new MyException("Expression is not a reference");
        }
        Integer address = ((RefValue)valExp).getAddress();
        if(heap.isDefined(address)){

        }
        return state;
    }

    @Override
    public String toString(){
        return "rH(" + exp.toString() + ")";
    }
}
