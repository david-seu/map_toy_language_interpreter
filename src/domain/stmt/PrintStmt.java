package src.domain.stmt;

import src.domain.exception.MyException;
import src.domain.exp.Exp;
import src.domain.prgstate.MyIList;
import src.domain.prgstate.PrgState;
import src.domain.value.Value;

public class PrintStmt implements IStmt {
    private final Exp exp;
    public PrintStmt(Exp exp) {
        this.exp = exp;
    }

    @Override
    public String toString() {
        return "print(" + exp.toString() + ")";
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIList<Value> out = state.getOut();
        out.add(exp.eval(state.getSymTable()));
        state.getStack().pop();
        return state;
    }
}
