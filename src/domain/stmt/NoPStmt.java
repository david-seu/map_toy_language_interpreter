package src.domain.stmt;

import src.domain.exception.EmptyStackException;
import src.domain.prgstate.PrgState;

public class NoPStmt implements IStmt {

    public NoPStmt() {
    }

    @Override
    public PrgState execute(PrgState state) throws EmptyStackException {
        state.getStack().pop();
        return state;
    }
}
