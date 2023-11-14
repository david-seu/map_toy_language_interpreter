package src.domain.stmt.filestmt;

import src.domain.exception.MyException;
import src.domain.exp.Exp;
import src.domain.prgstate.MyIDictionary;
import src.domain.prgstate.MyIStack;
import src.domain.prgstate.PrgState;
import src.domain.stmt.IStmt;
import src.domain.type.StringType;
import src.domain.value.StringValue;
import src.domain.value.Value;

import java.io.BufferedReader;

public class CloseRFileStmt implements IStmt {

    private final Exp exp;

    public CloseRFileStmt(Exp exp) {
        this.exp = exp;
    }

    @Override
    public String toString(){
        return "closeRFile(" + exp.toString() + ")";
    }
    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIStack<IStmt> stk = state.getStack();
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        MyIDictionary<StringValue, BufferedReader> fileTbl = state.getFileTable();
        stk.pop();
        Value expVal = exp.eval(symTbl);
        StringValue val = new StringValue((String) expVal.getVal());
        if(!expVal.getType().equals(new StringType()))
            throw new MyException("Expression is not a string");
        if(!fileTbl.isDefined(val))
            throw new MyException("File is not opened");
        try{
            BufferedReader br = fileTbl.lookup(val);
            br.close();
            fileTbl.remove(val);
        }
        catch (Exception e){
            throw new MyException(e.toString());
        }

        return state;
    }
}
