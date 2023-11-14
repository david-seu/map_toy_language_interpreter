package src.domain.stmt.heap;

import src.domain.exception.MyException;
import src.domain.exp.Exp;
import src.domain.prgstate.MyIDictionary;
import src.domain.prgstate.PrgState;
import src.domain.stmt.IStmt;
import src.domain.type.RefType;
import src.domain.value.RefValue;
import src.domain.value.Value;

public class NewStmt implements IStmt {

    private final String var_name;
    private final Exp exp;

    public NewStmt(String var_name, Exp exp){
        this.var_name = var_name;
        this.exp = exp;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        if(!symTbl.isDefined(var_name))
            throw new MyException("Variable is not defined");
        Value val = symTbl.lookup(var_name);
        if(!val.getType().equals(new RefType()))
            throw new MyException("Variable is not a reference");
        Value expVal = exp.eval(symTbl);
        if(!expVal.getType().equals((val.getType())))
            throw new MyException("Types do not match");
        Integer address = state.getAddressBuilder().getFreeAddress();
        RefValue refVal = new RefValue(address, expVal.getType());
        symTbl.update(var_name, refVal);
        return state;
    }

    @Override
    public String toString(){
        return "new(" + var_name + ", " + exp.toString() + ")";
    }
}
