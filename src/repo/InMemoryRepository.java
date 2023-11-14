package src.repo;

import jdk.jshell.execution.Util;
import src.domain.exception.MyException;
import src.domain.prgstate.MyIDictionary;
import src.domain.prgstate.MyIList;
import src.domain.prgstate.MyIStack;
import src.domain.prgstate.PrgState;
import src.domain.stmt.CompStmt;
import src.domain.stmt.IStmt;
import src.domain.value.StringValue;
import src.domain.value.Value;
import src.utils.Utils;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Stack;
import java.util.Vector;

public class InMemoryRepository implements IRepository{
    private final Vector<PrgState> prgList;
    private final String logFilePath;

    public InMemoryRepository(Vector<PrgState> prgList, String logFilePath) {
        this.prgList = prgList;
        this.logFilePath = logFilePath;
    }

    public InMemoryRepository(PrgState prgState, String logFilePath){
        this.prgList = new Vector<>();
        this.prgList.add(prgState);
        this.logFilePath = logFilePath;
    }

    @Override
    public PrgState getCrtPrg(Integer programIndex) {
        return prgList.get(programIndex);
    }

    public void logPrgStateExec(Integer programIndex) throws MyException {
        try {
            PrintWriter logFile = new PrintWriter(new FileWriter(logFilePath, true));
            PrgState prg = prgList.get(programIndex);
            logFile.println("ExeStack:");
            Stack<IStmt> stk = prg.getStack().getStack();
            while(!stk.isEmpty()) {
                IStmt s = stk.pop();
                if(s instanceof CompStmt)
                    logFile.println(Utils.infixForm((CompStmt) s));
                else
                    logFile.println(s.toString());
            }
            logFile.println("------------------------------------------------------------");
            logFile.println("SymTable:");
            MyIDictionary<String, Value> symTbl = prg.getSymTable();
            for(String key : symTbl.getKeys()) {
                logFile.println(key + "-->" + symTbl.lookup(key));
            }
            logFile.println("------------------------------------------------------------");
            logFile.println("Out:");
            MyIList<Value> out = prg.getOut();
            for(Value v : out.getValues()) {
                logFile.println(v);
            }
            logFile.println("------------------------------------------------------------");
            logFile.println("FileTable:");
            MyIDictionary<StringValue, BufferedReader> fileTable = prg.getFileTable();
            for(StringValue key : fileTable.getKeys()) {
                logFile.println(key);
            }
            logFile.println("------------------------------------------------------------");
            logFile.println("Heap:");
            MyIDictionary<Integer,Value> heap = prg.getHeap();
            for(Integer key : heap.getKeys()) {
                logFile.println(key + "-->" + heap.lookup(key));
            }
            logFile.println("------------------------------------------------------------");
            logFile.flush();
            logFile.close();
        }
        catch (Exception e) {
            throw new MyException(e.getMessage());
        }
    }
}
