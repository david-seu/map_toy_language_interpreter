import src.controller.Controller;
import src.domain.commands.ExitCommand;
import src.domain.commands.RunExample;
import src.domain.exception.EmptyStackException;
import src.domain.exp.*;
import src.domain.prgstate.*;
import src.domain.stmt.*;
import src.domain.stmt.filestmt.CloseRFileStmt;
import src.domain.stmt.filestmt.OpenRFileStmt;
import src.domain.stmt.filestmt.ReadFileStmt;
import src.domain.type.BoolType;
import src.domain.type.IntType;
import src.domain.type.RefType;
import src.domain.type.StringType;
import src.domain.value.BoolValue;
import src.domain.value.IntValue;
import src.domain.stmt.heap.*;
import src.domain.value.StringValue;
import src.repo.IRepository;
import src.repo.InMemoryRepository;
import src.utils.Utils;
import src.view.TextView;
import javafx;


import java.util.Vector;

public class Main{
    public static void main(String[] args) throws EmptyStackException {
        Vector<PrgState> prgList = new Vector<>();
        IStmt ex1= new CompStmt(new VarDeclStmt("v",new IntType()),
                        new CompStmt(new AssignStmt("v",
                                new ValueExp(new IntValue(2))),
                                        new PrintStmt(new VarExp("v"))));
        prgList.add(Utils.createPrgState(ex1, 1));
        IStmt ex2 = new CompStmt( new VarDeclStmt("a",new IntType()),
                new CompStmt(new VarDeclStmt("b",new IntType()),
                        new CompStmt(new AssignStmt("a", new ArithExp('+',new ValueExp(new IntValue(2)),
                                new ArithExp('*',new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5))))),
                                        new CompStmt(new AssignStmt("b",new ArithExp('+',new VarExp("a"),
                                                new ValueExp(new IntValue(1)))), new PrintStmt(new VarExp("b"))))));
        prgList.add(Utils.createPrgState(ex2, 2));
        IStmt ex3 = new CompStmt(new VarDeclStmt("a",new BoolType()),
                new CompStmt(new VarDeclStmt("v", new IntType()),
                        new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))),
                                new CompStmt(new IfStmt(new VarExp("a"),new AssignStmt("v",new ValueExp(new  IntValue(2))),
                                        new AssignStmt("v", new ValueExp(new IntValue(3)))), new PrintStmt(new  VarExp("v"))))));
        prgList.add(Utils.createPrgState(ex3, 3));
        IStmt ex4 = new CompStmt(new VarDeclStmt("varf",new StringType()),
                new CompStmt(new AssignStmt("varf",
                        new ValueExp(new StringValue("C:\\Users\\seu21\\Documente\\GitHub\\map_toy_language_interpreter\\test.in"))),
                            new CompStmt(new OpenRFileStmt(new VarExp("varf")),new CompStmt(new VarDeclStmt("varc",new IntType()),
                                    new CompStmt(new ReadFileStmt(new VarExp("varf"),"varc"),
                                            new CompStmt(new PrintStmt(new VarExp("varc")),
                                                    new CompStmt(new ReadFileStmt(new VarExp("varf"),"varc"),
                                                            new CompStmt(new PrintStmt(new VarExp("varc")),
                                                                    new CloseRFileStmt(new VarExp("varf"))))))))));
        prgList.add(Utils.createPrgState(ex4, 4));
        IStmt ex5 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                new CompStmt(new NewStmt("v", new ValueExp(new IntValue(20))),
                        new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
                                new CompStmt(new NewStmt("a", new VarExp("v")),
                                        new CompStmt(new PrintStmt(new VarExp("v")),
                                                new PrintStmt(new VarExp("a")))))));
        prgList.add(Utils.createPrgState(ex5, 5));
        IStmt ex6 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                new CompStmt(new NewStmt("v",new ValueExp(new IntValue(20))),
                        new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
                                new CompStmt(new NewStmt("a", new VarExp("v")),
                                        new CompStmt(new PrintStmt(new rHExp(new VarExp("v"))),
                                                new PrintStmt(new ArithExp('+', new rHExp(new rHExp(new VarExp("a"))), new ValueExp(new IntValue(5)))))))));
        prgList.add(Utils.createPrgState(ex6, 6));
        IStmt ex7 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                new CompStmt(new NewStmt("v",new ValueExp(new IntValue(20))),
                        new CompStmt(new PrintStmt(new rHExp(new VarExp("v"))),
                                new CompStmt(new wHStmt("v",new ValueExp(new IntValue(30))),
                                        new PrintStmt(new ArithExp('+', new rHExp(new VarExp("v")),
                                                new ValueExp(new IntValue(5))))))));
        prgList.add(Utils.createPrgState(ex7, 7));
        IStmt ex8 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                new CompStmt(new NewStmt("v", new ValueExp(new IntValue(20))),
                        new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
                                new CompStmt(new NewStmt("a", new VarExp("v")),
                                        new CompStmt(new NewStmt("v", new ValueExp(new IntValue(30))),
                                                new PrintStmt(new rHExp(new rHExp(new VarExp("a")))))))));
        prgList.add(Utils.createPrgState(ex8, 8));
        IStmt ex9 = new CompStmt(new VarDeclStmt("v", new IntType()),
                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(4))),
                        new CompStmt(new WhileStmt(new BooleanExp(new VarExp("v"), new ValueExp(new IntValue(0)), ">"),
                                new CompStmt(new PrintStmt(new VarExp("v")),
                                        new AssignStmt("v", new ArithExp('-',new VarExp("v"), new ValueExp(new IntValue(1)))))),
                                                new PrintStmt(new VarExp("v")))));
        prgList.add(Utils.createPrgState(ex9, 9));
        IStmt ex10 = new CompStmt(new VarDeclStmt("v", new IntType()),
                new CompStmt(new VarDeclStmt("a", new RefType(new IntType())),
                        new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(10))),
                                new CompStmt(new NewStmt("a", new ValueExp(new IntValue(22))),
                                        new CompStmt(new ForkStmt(
                                                new CompStmt(new wHStmt("a", new ValueExp(new IntValue(30))),
                                                                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(32))),
                                                                        new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new rHExp(new VarExp("a"))))))),
                                                new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new rHExp(new VarExp("a")))))))));
        prgList.add(Utils.createPrgState(ex10, 10));

        TextView view = new TextView();
        view.addCommand(new ExitCommand("0", "Exit"));

        for(PrgState prg: prgList){
            if(prg == null) continue;
            IRepository repo = new InMemoryRepository(prg, "log" + (prgList.indexOf(prg)+1) + ".txt");
            Controller ctrl = new Controller(repo);
            view.addCommand(new RunExample(String.valueOf(prgList.indexOf(prg) + 1), prg.getStack().top().toString(), ctrl));
        }

        view.show();
    }
}
