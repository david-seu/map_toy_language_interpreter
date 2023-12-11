package src.domain.stmt;

import src.domain.exception.MyException;
import src.domain.prgstate.MyIStack;
import src.domain.prgstate.PrgState;

public class CompStmt implements IStmt {
    private final IStmt first;
    private final IStmt second;


    public CompStmt(){
        second = null;
        first = null;
    };

    public CompStmt(IStmt first, IStmt second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIStack<IStmt> stk = state.getStack();
        stk.push(second);
        stk.push(first);
        return null;
    }

    @Override
    public String toString() {
        return first.toString() + "; " + second.toString();
    }

    public IStmt getSecond() {
        return second;
    }

    public IStmt getFirst() {
        return first;
    }


}
