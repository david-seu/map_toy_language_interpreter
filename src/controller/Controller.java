package src.controller;

import src.domain.exception.MyException;
import src.domain.prgstate.MyIStack;
import src.domain.prgstate.PrgState;
import src.domain.stmt.IStmt;
import src.repo.IRepository;

public class Controller {
    private final IRepository repo;
    Integer programIndex = 0;

    public Controller(IRepository r) {repo=r;}

    public void oneStep(PrgState state) throws MyException {
        MyIStack<IStmt> stk = state.getStack();
        if (stk.isEmpty()) {
            System.out.println("Stack is empty");
        }
        else {
            IStmt crtStmt = stk.top();
            crtStmt.execute(state);
        }
    }

    public void allStep() throws MyException {
        PrgState prg = repo.getCrtPrg(programIndex);
        repo.logPrgStateExec(programIndex);
        while (!prg.getStack().isEmpty()) {
            oneStep(prg);
            repo.logPrgStateExec(programIndex);
            System.out.println(prg);
        }
    }

    public void display() {
        System.out.println(repo.getCrtPrg(programIndex));
    }

    public void selectProgram(Integer programIndex) {
        this.programIndex = programIndex;
    }
}
