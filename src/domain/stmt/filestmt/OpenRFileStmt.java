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
import java.io.FileReader;

public class OpenRFileStmt implements IStmt {

    private final Exp exp;

    public OpenRFileStmt(Exp exp) {
        this.exp = exp;
    }

    @Override
    public String toString() {
        return "openRFile(" + exp.toString() + ")";
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIStack<IStmt> stk = state.getStack();
        MyIDictionary<String, Value> symTable = state.getSymTable();
        MyIDictionary<StringValue, BufferedReader> fileTable = state.getFileTable();
        stk.pop();
        Value expVal = exp.eval(symTable);
        StringValue val = new StringValue((String)expVal.getVal());
        if(!expVal.getType().equals(new StringType()))
            throw new MyException("Expression is not a string");
        if(fileTable.isDefined((StringValue) expVal))
            throw new MyException("File already opened");
        try{
            BufferedReader br = new BufferedReader(new FileReader((String)expVal.getVal()));
            fileTable.add(val, br);
        }
        catch (Exception e){
            throw new MyException(e.toString());
        }
        return state;
    }
}
