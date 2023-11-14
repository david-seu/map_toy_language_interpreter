package src.domain.stmt;

import src.domain.exception.MyException;
import src.domain.prgstate.PrgState;

public interface IStmt {
    PrgState execute(PrgState state) throws MyException;
}
