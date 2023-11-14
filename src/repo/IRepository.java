package src.repo;

import src.domain.exception.MyException;
import src.domain.prgstate.PrgState;

public interface IRepository {
    PrgState getCrtPrg(Integer programIndex);

    void logPrgStateExec(Integer programIndex) throws MyException;
}
