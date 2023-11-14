package src.utils;

import src.domain.exp.Exp;
import src.domain.prgstate.*;
import src.domain.stmt.CompStmt;
import src.domain.stmt.IStmt;
import src.domain.value.StringValue;
import src.domain.value.Value;

import java.io.BufferedReader;

public class Utils {
    public static PrgState createPrgState(IStmt stmt) {
        MyIStack<IStmt> stk1 = new MyStack<>();
        MyIDictionary<String, Value> symTable1 = new MyDictionary<>();
        MyIList<Value> out1 = new MyList<>();
        MyIDictionary<StringValue, BufferedReader> fileTable1 = new MyDictionary<>();
        return new PrgState(stk1, symTable1, out1, fileTable1, stmt);
    }

    public static String infixForm(CompStmt stmt){
        if (stmt.getSecond() instanceof CompStmt){
            return stmt.getFirst().toString() + "\n" + infixForm((CompStmt) stmt.getSecond());
        }
        return stmt.getFirst().toString() + "\n" + stmt.getSecond().toString();
    }
}
