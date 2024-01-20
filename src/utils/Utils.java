package src.utils;

import src.domain.exception.MyException;
import src.domain.prgstate.*;
import src.domain.stmt.CompStmt;
import src.domain.stmt.IStmt;
import src.domain.type.Type;
import src.domain.value.RefValue;
import src.domain.value.StringValue;
import src.domain.value.Value;
import java.io.BufferedReader;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Utils {
    public static String infixForm(CompStmt stmt){
        if (stmt.getSecond() instanceof CompStmt){
            return stmt.getFirst().toString() + "\n" + infixForm((CompStmt) stmt.getSecond());
        }
        return stmt.getFirst().toString() + "\n" + stmt.getSecond().toString();
    }

    public static PrgState createPrgState(IStmt stmt, Integer id) {
        MyIDictionary<String, Type> typeEnv = new MyDictionary<>();
        try {
            stmt.typeCheck(typeEnv);
        } catch (MyException e) {
            System.out.println(id + ": " +e.getMessage());
            return null;
        }
        MyIStack<IStmt> stk1 = new MyStack<>();
        MyIDictionary<String, Value> symTable1 = new MyDictionary<>();
        MyIDictionary<Integer, Value> heap = new MyHeap();
        MyIList<Value> out1 = new MyList<>();
        MyIDictionary<StringValue, BufferedReader> fileTable1 = new MyDictionary<>();
        return new PrgState(stk1, symTable1, out1, fileTable1, heap, stmt);
    }

    public static MyIDictionary<String, Value> cloneSymTable(MyIDictionary<String, Value> symTable) throws MyException {
        Map<String, Value> collect = symTable.getContent();
        MyIDictionary<String, Value> cloneSymTable = new MyDictionary<>();
        for(String key: collect.keySet()){
            cloneSymTable.add(key, collect.get(key).deepCopy());
        }
        return cloneSymTable;
    }

}
