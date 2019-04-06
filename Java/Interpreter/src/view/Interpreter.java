package view;

import java.util.ArrayList;
import java.util.Arrays;

import controller.Controller;
import domain.adt.*;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.StackPane;
import javafx.event.ActionEvent;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.ProgState;
import model.expression.*;
import model.file_statements.closeRFile;
import model.file_statements.openRFile;
import model.file_statements.readFile;
import model.heap_statements.New;
import model.heap_statements.wH;
import model.statement.*;
import repo.IRepo;
import repo.Repo;

class Interpreter extends Application {

	private IStatement example1, example2, example3, example4, example5, example6
					, example7, example8, example9, example10, example11, example12;
	
	private Controller ctr1, ctr2, ctr3, ctr4, ctr5, ctr6, ctr7, ctr8, ctr9, ctr10, ctr11, ctr12;
	
    public static void main(String[] args) {
        launch(args);
        
//        TextMenu menu = new TextMenu();
//        menu.addCommand(new ExitCommand("0", "exit"));
//        menu.addCommand(new RunExample("1", example1.toString(), ctr1));
//        menu.addCommand(new RunExample("2", example2.toString(), ctr2));
//        menu.addCommand(new RunExample("3", example3.toString(), ctr3));
//        menu.addCommand(new RunExample("4", example4.toString(), ctr4));
//        menu.addCommand(new RunExample("5", example5.toString(), ctr5));
//        menu.addCommand(new RunExample("6", example6.toString(), ctr6));
//        menu.addCommand(new RunExample("7", example7.toString(), ctr7));
//        menu.addCommand(new RunExample("8", example8.toString(), ctr8));
//        menu.addCommand(new RunExample("9", example9.toString(), ctr9));
//        menu.addCommand(new RunExample("10", example10.toString(), ctr10));
//        menu.addCommand(new RunExample("11", example11.toString(), ctr11));
//        menu.addCommand(new RunExample("12", example12.toString(), ctr12));
//        menu.show();
        
    }

	@Override
	public void start(Stage primaryStage) throws Exception {
		createTheExamples();
		try {
			
			
			StackPane root = (StackPane)FXMLLoader.load(getClass().getResource("FirstPage.fxml"));
			Scene scene = new Scene(root,400,150);
			scene.getStylesheets().add(getClass().getResource("FirstPage.css").toExternalForm());
			primaryStage.setScene(scene);
			 primaryStage.setTitle("FXML ListView Example");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void createTheExamples() {
/*
		 	example1 =
	                new CompoundStm(
	                        new AssignmentStm("v", new ConstExp(2)),
	                        new PrintStm(new VarExpr("v")));

	        ProgState prg1 = new ProgState(new ExeStack<>(),
	                new SymTable<>(),
	                new OutputList<>(), new FileTable<>(), new Heap<>(),  example1, 1);

	        IRepo repo1 = new Repo(prg1, "log1.txt");
	        ctr1 = new Controller(repo1);

	        example2 =
	                new CompoundStm(
	                        new AssignmentStm("a", new ArithExp(
	                                '+', new ConstExp(2),
	                                new ArithExp('*',
	                                        new ConstExp(3),
	                                        new ConstExp(5)))),
	                        new CompoundStm(
	                                new AssignmentStm("b", new ArithExp(
	                                        '-', new VarExpr("a"),
	                                        new ArithExp('+', new ArithExp('/',
	                                                new ConstExp(4), new ConstExp(2)),
	                                                new ConstExp(7)))),
	                                new PrintStm(new VarExpr("b"))));

	        ProgState prg2 = new ProgState(new ExeStack<>(),
	                new SymTable<>(),
	                new OutputList<>(), new FileTable<>(), new Heap<>(),  example2, 1);

	        IRepo repo2 = new Repo(prg2, "log2.txt");
	        ctr2 = new Controller(repo2);

	        example3 =
	                new CompoundStm(
	                        new AssignmentStm("a",
	                                new ArithExp('-',
	                                        new ConstExp(2),
	                                        new ConstExp(2))),
	                        new CompoundStm(
	                                new ConditionalStm(new VarExpr("a"),
	                                        new AssignmentStm("v",
	                                                new ConstExp(2)),
	                                        new AssignmentStm("v",
	                                                new ConstExp(3))),
	                                new PrintStm(new VarExpr("v"))));

	        ProgState prg3 = new ProgState(new ExeStack<>(),
	                new SymTable<>(),
	                new OutputList<>(), new FileTable<>(),
	                new Heap<>(), example3, 1);

	        IRepo repo3 = new Repo(prg3, "log3.txt");
	        ctr3 = new Controller(repo3);



	        example4=new CompoundStm(
	                new CompoundStm(
	                        new openRFile(new VarExpr("var_f"), "test_in.txt"),
	                        new CompoundStm(new readFile(new VarExpr("var_f"), "c"),
	                                new PrintStm(new VarExpr("c")))),
	                new CompoundStm(
	                        new ConditionalStm(new VarExpr("c"),
	                                new CompoundStm(
	                                        new readFile(new VarExpr("var_f"), "c"),
	                                        new PrintStm(new VarExpr("c"))),
	                                new PrintStm(new ConstExp(0))),
	                        new closeRFile(new VarExpr("var_f"))));

	        ProgState prg4=new ProgState(new ExeStack<>(),
	                new SymTable<>(), new OutputList<>(),
	                new FileTable<>(), new Heap<>(), example4, 1);

	        IRepo repo4=new Repo(prg4, "log4.txt");
	        ctr4=new Controller(repo4);


	        example5=new CompoundStm(
	                new CompoundStm(
	                        new openRFile(new VarExpr("var_f"), "test_in.txt"),
	                        new CompoundStm(new readFile(new ArithExp('+',
	                                new ConstExp(2), new VarExpr("var_f")), "c"),
	                                new PrintStm(new VarExpr("c")))),
	                new CompoundStm(
	                        new ConditionalStm(new VarExpr("c"),
	                                new CompoundStm(
	                                        new readFile(new VarExpr("var_f"), "c"),
	                                        new PrintStm(new VarExpr("c"))),
	                                new PrintStm(new VarExpr("c"))),
	                        new closeRFile(new VarExpr("var_f"))));

	        ProgState prg5=new ProgState(new ExeStack<>(),
	                new SymTable<>(), new OutputList<>(),
	                new FileTable<>(), new Heap<>(), example5, 1);

	        IRepo repo5=new Repo(prg5, "log5.txt");
	        ctr5=new Controller(repo5);

	        example6=new CompoundStm(
	                new CompoundStm(
	                        new AssignmentStm("v", new ConstExp(10)),
	                        new New("v", new ConstExp(20))
	                ),
	                new CompoundStm(
	                        new New("a", new ConstExp(22)),
	                        new PrintStm(new VarExpr("v"))
	                )
	        );

	        ProgState prg6=new ProgState(new ExeStack<>(),
	                new SymTable<>(), new OutputList<>(),
	                new FileTable<>(), new Heap<>(), example6, 1);

	        IRepo repo6=new Repo(prg6, "log6.txt");
	        ctr6=new Controller(repo6);

	        example7=new CompoundStm(
	                new CompoundStm(
	                        new AssignmentStm("v", new ConstExp(10)),
	                        new New("v", new ConstExp(20))),
	                new CompoundStm(new CompoundStm(
	                        new New("a", new ConstExp(22)),
	                        new PrintStm(new ArithExp('+',
	                                new ConstExp(100),
	                                new rH("v")))
	                ),
	                        new PrintStm(new ArithExp(
	                                '+',
	                                new ConstExp(100),
	                                new rH("a")
	                            )
	                        )
	                )
	        );

	        ProgState prg7=new ProgState(new ExeStack<>(),
	                new SymTable<>(), new OutputList<>(),
	                new FileTable<>(), new Heap<>(), example7, 1);

	        IRepo repo7=new Repo(prg7, "log7.txt");
	        ctr7=new Controller(repo7);


	        example8=new CompoundStm(
	                new CompoundStm(
	                    new AssignmentStm("v", new ConstExp(10)),
	                    new New("v", new ConstExp(20))
	                ),
	                new CompoundStm(
	                        new New("a", new ConstExp(22)),
	                            new CompoundStm(
	                                new CompoundStm(
	                                        new wH("a", new ConstExp(30)),
	                                        new PrintStm(new VarExpr("a"))
	                                        ),
	                                new PrintStm(new rH("a")))
	                )
	        );

	        ProgState prg8=new ProgState(new ExeStack<>(),
	                new SymTable<>(), new OutputList<>(),
	                new FileTable<>(), new Heap<>(), example8, 1);

	        IRepo repo8=new Repo(prg8, "log8.txt");
	        ctr8=new Controller(repo8);

	        example9=new CompoundStm(
	                new CompoundStm(
	                        new AssignmentStm("v", new ConstExp(6)),
	                        new While(
	                                new BoolExp(">=",
	                                    new ArithExp('-',
	                                            new VarExpr("v"),
	                                            new ConstExp(4)),
	                                    new ConstExp(0)),
	                                new CompoundStm(
	                                        new PrintStm(new VarExpr("v")),
	                                            new AssignmentStm("v",
	                                                new ArithExp('-',
	                                                        new VarExpr("v"),
	                                                        new ConstExp(1)))
	                                        )
	                                )
	                    ), new PrintStm(new VarExpr("v"))
	                );

	        ProgState prg9=new ProgState(new ExeStack<>(),
	                new SymTable<>(), new OutputList<>(),
	                new FileTable<>(), new Heap<>(), example9, 1);

	        IRepo repo9=new Repo(prg9, "log9.txt");
	        ctr9=new Controller(repo9);

	        example10=
	                new CompoundStm(
	                    new CompoundStm(
	                        new openRFile(new VarExpr("var_x"), "test_in.txt"),
	                        new openRFile(new VarExpr("var_y"), "test_in1.txt")
	                    ),
	                    new CompoundStm(
	                            new readFile(new VarExpr("var_x"), "v"),
	                            new PrintStm(new VarExpr("v"))
	                    )
	        );

	        ProgState prg10=new ProgState(new ExeStack<>(),
	                new SymTable<>(), new OutputList<>(),
	                new FileTable<>(), new Heap<>(), example10, 1);

	        IRepo repo10=new Repo(prg10, "log10.txt");
	        ctr10=new Controller(repo10);


	        example11=
	                new CompoundStm(
	                       new CompoundStm(
	                               new AssignmentStm("v", new ConstExp(10)),
	                               new New("a", new ConstExp(22))
	                       ),
	                       new CompoundStm(
	                               new ForkStm(
	                                       new CompoundStm(
	                                           new CompoundStm(
	                                                   new wH("a", new ConstExp(30)),
	                                                   new AssignmentStm("v", new ConstExp(32))
	                                           ),
	                                           new CompoundStm(
	                                                   new PrintStm(new rH("a")),
	                                                   new PrintStm(new VarExpr("v"))
	                                           )
	                                       )
	                               ),
	                               new CompoundStm(
	                                       new PrintStm(new rH("a")),
	                                       new PrintStm(new VarExpr("v"))
	                               )
	                       )
	                );

	        ProgState prg11=new ProgState(new ExeStack<>(),
	                new SymTable<>(), new OutputList<>(),
	                new FileTable<>(), new Heap<>(), example11, 1);

	        IRepo repo11=new Repo(prg11, "log11.txt");
	        ctr11=new Controller(repo11);


	        example12=
	                new CompoundStm(
	                    new CompoundStm(
	                        new New("v", new ConstExp(1)),
	                        new ForkStm(
	                            new CompoundStm(
	                                new ForkStm(new wH("v", new ConstExp(2))),
	                                new CompoundStm(
	                                    new PrintStm(new VarExpr("v")),
	                                    new wH("v", new ConstExp(3))
	                                    )
	                            )
	                        )
	                    ),
	                    new PrintStm(new VarExpr("v"))
	                );

	        ProgState prg12=new ProgState(new ExeStack<>(),
	                new SymTable<>(), new OutputList<>(),
	                new FileTable<>(), new Heap<>(), example12, 1);

	        IRepo repo12=new Repo(prg12, "log12.txt");
	        ctr12=new Controller(repo12);
	}
	
	public ArrayList<Controller> getControllers(){
		ArrayList<Controller> ctrls=new ArrayList<>(Arrays.asList(
				ctr1, ctr2, ctr3, ctr4, ctr5, ctr6, ctr7, ctr8, ctr9, ctr10, ctr11, ctr12));
		return ctrls;
	}

	public ArrayList<IStatement> getExamples(){
		ArrayList<IStatement> examples=new ArrayList<>(Arrays.asList(
				example1, example2, example3, example4, example5, example6
				, example7, example8, example9, example10, example11, example12));
		return examples;*/
	}
}