import src.controller.Controller;
import src.domain.commands.ExitCommand;
import src.domain.commands.RunExample;
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
import src.domain.value.RefValue;
import src.domain.stmt.heap.*;
import src.domain.value.StringValue;
import src.repo.IRepository;
import src.repo.InMemoryRepository;
import src.utils.Utils;
import src.view.TextView;

public class Main {
    public static void main(String[] args) {
        IStmt ex1= new CompStmt(new VarDeclStmt("v",new IntType()),
                        new CompStmt(new AssignStmt("v",
                                new ValueExp(new IntValue(2))),
                                        new PrintStmt(new VarExp("v"))));
        PrgState prg1 = Utils.createPrgState(ex1);
        IStmt ex2 = new CompStmt( new VarDeclStmt("a",new IntType()),
                new CompStmt(new VarDeclStmt("b",new IntType()),
                        new CompStmt(new AssignStmt("a", new ArithExp('+',new ValueExp(new IntValue(2)),
                                new ArithExp('*',new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5))))),
                                        new CompStmt(new AssignStmt("b",new ArithExp('+',new VarExp("a"),
                                                new ValueExp(new IntValue(1)))), new PrintStmt(new VarExp("b"))))));
        PrgState prg2 = Utils.createPrgState(ex2);
        IStmt ex3 = new CompStmt(new VarDeclStmt("a",new BoolType()),
                new CompStmt(new VarDeclStmt("v", new IntType()),
                        new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))),
                                new CompStmt(new IfStmt(new VarExp("a"),new AssignStmt("v",new ValueExp(new  IntValue(2))),
                                        new AssignStmt("v", new ValueExp(new IntValue(3)))), new PrintStmt(new  VarExp("v"))))));
        PrgState prg3 = Utils.createPrgState(ex3);
        IStmt ex4 = new CompStmt(new VarDeclStmt("varf",new StringType()),
                new CompStmt(new AssignStmt("varf",
                        new ValueExp(new StringValue("C:\\Users\\seu21\\Documente\\GitHub\\map_toy_language_interpreter\\test.in"))),
                            new CompStmt(new OpenRFileStmt(new VarExp("varf")),new CompStmt(new VarDeclStmt("varc",new IntType()),
                                    new CompStmt(new ReadFileStmt(new VarExp("varf"),"varc"),
                                            new CompStmt(new PrintStmt(new VarExp("varc")),
                                                    new CompStmt(new ReadFileStmt(new VarExp("varf"),"varc"),
                                                            new CompStmt(new PrintStmt(new VarExp("varc")),
                                                                    new CloseRFileStmt(new VarExp("varf"))))))))));
        PrgState prg4 = Utils.createPrgState(ex4);
        IStmt ex5 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                new CompStmt(new NewStmt("v", new ValueExp(new IntValue(20))),
                        new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
                                new CompStmt(new NewStmt("a", new VarExp("v")),
                                        new CompStmt(new PrintStmt(new VarExp("v")),
                                                new PrintStmt(new VarExp("a")))))));
        PrgState prg5 = Utils.createPrgState(ex5);
        IStmt ex6 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                new CompStmt(new NewStmt("v",new ValueExp(new IntValue(20))),
                        new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
                                new CompStmt(new NewStmt("a", new VarExp("v")),
                                        new CompStmt(new PrintStmt(new rHExp(new VarExp("v"))),
                                                new PrintStmt(new ArithExp('+', new rHExp(new rHExp(new VarExp("a"))), new ValueExp(new IntValue(5)))))))));
        PrgState prg6 = Utils.createPrgState(ex6);
        IStmt ex7 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                new CompStmt(new NewStmt("v",new ValueExp(new IntValue(20))),
                        new CompStmt(new PrintStmt(new rHExp(new VarExp("v"))),
                                new CompStmt(new wHStmt("v",new ValueExp(new IntValue(30))),
                                        new PrintStmt(new ArithExp('+', new rHExp(new VarExp("v")),
                                                new ValueExp(new IntValue(5))))))));
        PrgState prg7 = Utils.createPrgState(ex7);
        IStmt ex8 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                new CompStmt(new NewStmt("v", new ValueExp(new IntValue(20))),
                        new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
                                new CompStmt(new NewStmt("a", new VarExp("v")),
                                        new CompStmt(new NewStmt("v", new ValueExp(new IntValue(30))),
                                                new PrintStmt(new rHExp(new rHExp(new VarExp("a")))))))));
        PrgState prg8 = Utils.createPrgState(ex8);
        IStmt ex9 = new CompStmt(new VarDeclStmt("v", new IntType()),
                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(4))),
                        new CompStmt(new WhileStmt(new BooleanExp(new VarExp("v"), new ValueExp(new IntValue(0)), ">"),
                                new CompStmt(new PrintStmt(new VarExp("v")),
                                        new AssignStmt("v", new ArithExp('-',new VarExp("v"), new ValueExp(new IntValue(1)))))),
                                                new PrintStmt(new VarExp("v")))));
        PrgState prg9 = Utils.createPrgState(ex9);
        IStmt ex10 = new CompStmt(new VarDeclStmt("v", new IntType()),
                new CompStmt(new VarDeclStmt("a", new RefType(new IntType())),
                        new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(10))),
                                new CompStmt(new NewStmt("a", new ValueExp(new IntValue(22))),
                                        new CompStmt(new ForkStmt(
                                                new CompStmt(new wHStmt("a", new ValueExp(new IntValue(30))),
                                                                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(32))),
                                                                        new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new rHExp(new VarExp("a"))))))),
                                                new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new rHExp(new VarExp("a")))))))));
        PrgState prg10 = Utils.createPrgState(ex10);
        IRepository repo1 = new InMemoryRepository(prg1, "log1.txt");
        Controller ctrl1 = new Controller(repo1);
        IRepository repo2 = new InMemoryRepository(prg2, "log2.txt");
        Controller ctrl2 = new Controller(repo2);
        IRepository repo3 = new InMemoryRepository(prg3, "log3.txt");
        Controller ctrl3 = new Controller(repo3);
        IRepository repo4 = new InMemoryRepository(prg4, "log4.txt");
        Controller ctrl4 = new Controller(repo4);
        IRepository repo5 = new InMemoryRepository(prg5, "log5.txt");
        Controller ctrl5 = new Controller(repo5);
        IRepository repo6 = new InMemoryRepository(prg6, "log6.txt");
        Controller ctrl6 = new Controller(repo6);
        IRepository repo7 = new InMemoryRepository(prg7, "log7.txt");
        Controller ctrl7 = new Controller(repo7);
        IRepository repo8 = new InMemoryRepository(prg8, "log8.txt");
        Controller ctrl8 = new Controller(repo8);
        IRepository repo9 = new InMemoryRepository(prg9, "log9.txt");
        Controller ctrl9 = new Controller(repo9);
        IRepository repo10 = new InMemoryRepository(prg10, "log10.txt");
        Controller ctrl10 = new Controller(repo10);

        TextView view = new TextView();
        view.addCommand(new ExitCommand("0", "exit"));
        view.addCommand(new RunExample("1", ex1.toString(), ctrl1));
        view.addCommand(new RunExample("2", ex2.toString(), ctrl2));
        view.addCommand(new RunExample("3", ex3.toString(), ctrl3));
        view.addCommand(new RunExample("4", ex4.toString(), ctrl4));
        view.addCommand(new RunExample("5", ex5.toString(), ctrl5));
        view.addCommand(new RunExample("6", ex6.toString(), ctrl6));
        view.addCommand(new RunExample("7", ex7.toString(), ctrl7));
        view.addCommand(new RunExample("8", ex8.toString(), ctrl8));
        view.addCommand(new RunExample("9", ex9.toString(), ctrl9));
        view.addCommand(new RunExample("10", ex10.toString(), ctrl10));
        view.show();
    }
}
