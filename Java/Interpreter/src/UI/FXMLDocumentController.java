package UI;

import controller.Controller;
import domain.adt.ExeStack;
import domain.adt.FileTable;
import domain.adt.Heap;
import domain.adt.OutputList;
import domain.adt.ProcInterface;
import domain.adt.ProcTable;
import domain.adt.SymTable;
import domain.adt.SymTableInterface;
import domain.adt.Tuple;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Stack;
import java.util.logging.Level;
import static java.util.logging.Level.SEVERE;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.ProgState;
import model.expression.ArithExp;
import model.expression.BoolExp;
import model.expression.ConstExp;
import model.expression.VarExpr;
import model.expression.rH;
import model.file_statements.closeRFile;
import model.file_statements.openRFile;
import model.file_statements.readFile;
import model.heap_statements.New;
import model.heap_statements.wH;
import model.statement.AssignmentStm;
import model.statement.CompoundStm;
import model.statement.ConditionalStm;
import model.statement.ForkStm;
import model.statement.IStatement;
import model.statement.PrintStm;
import model.statement.SleepStm;
import model.statement.While;
import repo.IRepo;
import repo.Repo;

/**
 *
 * @author asus
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private Button button;
    public ObservableList<String> items;
    public ListView<String> listView;
    ObservableList<String> itemss;
    public IStatement example1, example2, example3, example4, example5, example6
					, example7, example8, example9, example10, example11, example12, examExample1, examExample2;
    public Controller ctr1, ctr2, ctr3, ctr4, ctr5, ctr6, ctr7, ctr8, ctr9, ctr10, ctr11, ctr12, examCtrl1, examCtrl2;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        String selected=listView.getSelectionModel().getSelectedItems().toString();
        selected = selected.substring(1, selected.length()-1);
        try{
            for(Tuple<Controller, IStatement> i:getExamplesInArray()){
                if(i.getSecond().toString().compareTo(selected)==0){
                    
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("SecondFXML.fxml"));
                    try{
                        loader.load();
                    }
                    catch(IOException ex){
                        Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    SecondController display=loader.getController();
                    display.setCtrl(i.getFirst());
                    display.reinit();
                    Parent p=loader.getRoot();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(p));
                    
                    stage.showAndWait();
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex.toString());
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listView.setItems(getExamples());
        listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        listView.getSelectionModel().selectIndices(1);
        listView.getFocusModel().focus(2);
    }    
    
    private List<Tuple<Controller, IStatement>> getExamplesInArray(){
        List<Tuple<Controller, IStatement>> list=new ArrayList<>();
        list.add(new Tuple(examCtrl1, examExample1));
//        list.add(new Tuple(ctr1, example1)); list.add(new Tuple(ctr2, example2));
//        list.add(new Tuple(ctr3, example3)); list.add(new Tuple(ctr4, example4));
//        list.add(new Tuple(ctr5, example5)); list.add(new Tuple(ctr6, example6));
//        list.add(new Tuple(ctr7, example7)); list.add(new Tuple(ctr8, example8));
//        list.add(new Tuple(ctr9, example9)); list.add(new Tuple(ctr10, example10));
        list.add(new Tuple(ctr11, example11)); list.add(new Tuple(ctr12, example12));
        return list;
    }
    
    	 private ObservableList<String> getExamples() {
             

            examExample1=new CompoundStm(
                    new CompoundStm(
                        new AssignmentStm("v", 
                                new ConstExp(10)),
                        new ForkStm(
                                new CompoundStm(
                                        new CompoundStm(
                                            new AssignmentStm("v", 
                                                    new ArithExp('-', 
                                                            new VarExpr("v"), 
                                                            new ConstExp(1))
                                            ),
                                            new AssignmentStm("v", 
                                                    new ArithExp('-', 
                                                            new VarExpr("v"), 
                                                            new ConstExp(1))
                                            )
                                        ), 
                                        new PrintStm(
                                                new VarExpr("v")
                                        )
                                )
                        )
                    ),
                    new CompoundStm(
                            new SleepStm(10),
                            new PrintStm(
                                    new ArithExp('*', 
                                            new VarExpr("v"), 
                                            new ConstExp(10))
                            )
                    )
            );
            
            Stack<SymTableInterface<String, Integer>> stack1= new Stack<>();
            stack1.add(new SymTable<String, Integer>());
            ProgState examPrg1=new ProgState(new ProcTable(), new ExeStack<>(),
                    stack1, new OutputList<>(),
                    new FileTable<>(), new Heap<>(), examExample1, 1);

            IRepo examRepo1=new Repo(examPrg1, "exam1.txt");
            examCtrl1=new Controller(examRepo1);
            
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
                        
                Stack<SymTableInterface<String, Integer>> stack11= new Stack<>();
                stack11.add(new SymTable<String, Integer>());
	        ProgState prg11=new ProgState(new ProcTable(), new ExeStack<>(),
	                 stack11, new OutputList<>(),
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
                
                Stack<SymTableInterface<String, Integer>> stack12= new Stack<>();
                stack12.add(new SymTable<String, Integer>());
	        ProgState prg12=new ProgState(new ProcTable(), new ExeStack<>(),
	                 stack12, new OutputList<>(),
	                new FileTable<>(), new Heap<>(), example12, 1);

	        IRepo repo12=new Repo(prg12, "log12.txt");
	        ctr12=new Controller(repo12);
                
	      // To Creating a Observable List
             
	     ObservableList<String> examples = FXCollections.observableArrayList(
                     examExample1.toString(),
                     example1.toString(), example2.toString(), example3.toString(), 
                     example4.toString(), example5.toString(), example6.toString(), 
                     example7.toString(), example8.toString(), example9.toString(), 
                     example10.toString(), 
                     example11.toString(), example12.toString());
             
            	     return examples;
                     
            // old examples - unadapted
            /*
             example1 =
	                new CompoundStm(
	                        new AssignmentStm("v", new ConstExp(2)),
	                        new PrintStm(new VarExpr("v")));

	        ProgState prg1 = new ProgState(new ExeStack<>(),
	                new Stack<SymTableInterface<String, Integer>>(),
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
	                new Stack<SymTableInterface<String, Integer>>(),
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
	                new Stack<SymTableInterface<String, Integer>>(),
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
	                new Stack<SymTableInterface<String, Integer>>(), new OutputList<>(),
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
	                new Stack<SymTableInterface<String, Integer>>(), new OutputList<>(),
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
	                new Stack<SymTableInterface<String, Integer>>(), new OutputList<>(),
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
	                new Stack<SymTableInterface<String, Integer>>(), new OutputList<>(),
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
	                new Stack<SymTableInterface<String, Integer>>(), new OutputList<>(),
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
	                new Stack<SymTableInterface<String, Integer>>(), new OutputList<>(),
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
	                new Stack<SymTableInterface<String, Integer>>(), new OutputList<>(),
	                new FileTable<>(), new Heap<>(), example10, 1);

	        IRepo repo10=new Repo(prg10, "log10.txt");
	        ctr10=new Controller(repo10);

*/
	  }
    
}
