import src.controller.Controller;
import src.domain.commands.ExitCommand;
import src.domain.commands.RunExample;
import src.domain.exp.ArithExp;
import src.domain.exp.ValueExp;
import src.domain.exp.VarExp;
import src.domain.prgstate.*;
import src.domain.stmt.*;
import src.domain.stmt.filestmt.CloseRFileStmt;
import src.domain.stmt.filestmt.OpenRFileStmt;
import src.domain.stmt.filestmt.ReadFileStmt;
import src.domain.type.BoolType;
import src.domain.type.IntType;
import src.domain.type.StringType;
import src.domain.value.BoolValue;
import src.domain.value.IntValue;
import src.domain.value.StringValue;
import src.repo.IRepository;
import src.repo.InMemoryRepository;
import src.utils.Utils;
import src.view.TextView;

public class Main {
    public static void main(String[] args) {
        IStmt ex1= new CompStmt(new VarDeclStmt("v",new IntType()), new CompStmt(new AssignStmt("v",new ValueExp(new IntValue(2))), new PrintStmt(new VarExp("v"))));
        PrgState prg1 = Utils.createPrgState(ex1);
        IStmt ex2 = new CompStmt( new VarDeclStmt("a",new IntType()), new CompStmt(new VarDeclStmt("b",new IntType()), new CompStmt(new AssignStmt("a", new ArithExp('+',new ValueExp(new IntValue(2)),new ArithExp('*',new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5))))),   new CompStmt(new AssignStmt("b",new ArithExp('+',new VarExp("a"), new ValueExp(new IntValue(1)))), new PrintStmt(new VarExp("b"))))));
        PrgState prg2 = Utils.createPrgState(ex2);
        IStmt ex3 = new CompStmt(new VarDeclStmt("a",new BoolType()), new CompStmt(new VarDeclStmt("v", new IntType()), new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))), new CompStmt(new IfStmt(new VarExp("a"),new AssignStmt("v",new ValueExp(new  IntValue(2))), new AssignStmt("v", new ValueExp(new IntValue(3)))), new PrintStmt(new  VarExp("v"))))));
        PrgState prg3 = Utils.createPrgState(ex3);
        IStmt exFile = new CompStmt(new VarDeclStmt("varf",new StringType()),new CompStmt(new AssignStmt("varf",new ValueExp(new StringValue("C:\\Users\\seu21\\Documente\\GitHub\\map_toy_language_interpreter\\test.in"))), new CompStmt(new OpenRFileStmt(new VarExp("varf")),new CompStmt(new VarDeclStmt("varc",new IntType()), new CompStmt(new ReadFileStmt(new VarExp("varf"),"varc"), new CompStmt(new PrintStmt(new VarExp("varc")), new CompStmt(new ReadFileStmt(new VarExp("varf"),"varc"), new CompStmt(new PrintStmt(new VarExp("varc")), new CloseRFileStmt(new VarExp("varf"))))))))));
        PrgState prg4 = Utils.createPrgState(exFile);
        IRepository repo1 = new InMemoryRepository(prg1, "log1.txt");
        Controller ctrl1 = new Controller(repo1);
        IRepository repo2 = new InMemoryRepository(prg2, "log2.txt");
        Controller ctrl2 = new Controller(repo2);
        IRepository repo3 = new InMemoryRepository(prg3, "log3.txt");
        Controller ctrl3 = new Controller(repo3);
        IRepository repo4 = new InMemoryRepository(prg4, "log4.txt");
        Controller ctrl4 = new Controller(repo4);

        TextView view = new TextView();
        view.addCommand(new ExitCommand("0", "exit"));
        view.addCommand(new RunExample("1", ex1.toString(), ctrl1));
        view.addCommand(new RunExample("2", ex2.toString(), ctrl2));
        view.addCommand(new RunExample("3", ex3.toString(), ctrl3));
        view.addCommand(new RunExample("4", exFile.toString(), ctrl4));
        view.show();
    }
}
