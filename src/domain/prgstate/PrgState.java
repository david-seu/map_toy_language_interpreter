package src.domain.prgstate;

import src.domain.stmt.IStmt;
import src.domain.value.StringValue;
import src.domain.value.Value;
import src.utils.AddressBuilder;

import java.io.BufferedReader;

public class PrgState {
    private final MyIStack<IStmt> stk;
    private final MyIDictionary<String, Value> symTable;
    private final MyIList<Value> out;
    private final MyIDictionary<StringValue, BufferedReader> fileTable;
    private final MyIDictionary<Integer,Value> heap;

    private final AddressBuilder addressBuilder;
    private IStmt originalProgram; //optional field, but good to have

    public PrgState(MyIStack<IStmt> stk, MyIDictionary<String, Value> symTable, MyIList<Value> out, MyIDictionary<StringValue,BufferedReader> fileTable, MyIDictionary<Integer,Value> heap, AddressBuilder addressBuilder, IStmt prg){
        this.stk = stk;
        this.symTable = symTable;
        this.out = out;
        this.fileTable = fileTable;
        this.heap = heap;
        this.addressBuilder = addressBuilder;
        this.originalProgram = prg;
        stk.push(prg);
    }

    public MyIStack<IStmt> getStack() {
        return stk;
    }

    public MyIDictionary<String, Value> getSymTable() {
        return symTable;
    }

    public MyIList<Value> getOut() {
        return out;
    }

    public MyIDictionary<StringValue, BufferedReader> getFileTable() {
        return fileTable;
    }

    public MyIDictionary<Integer,Value> getHeap(){
        return this.heap;
    }

    public AddressBuilder getAddressBuilder(){
        return this.addressBuilder;
    }

    public IStmt getOriginalProgram() {
        return originalProgram;
    }

    public void setOriginalProgram(IStmt originalProgram) {
        this.originalProgram = originalProgram;
    }

    @Override
    public String toString() {
        return "ExeStack:\n" + stk.toString() + "\nSymTable:\n" + symTable.toString() + "\nOut:\n" + out.toString() + "\nFileTable:\n" + fileTable.toString() + "\nHeap:\n" + heap.toString() + "\n";
    }
}
