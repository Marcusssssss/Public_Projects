package UI;

import controller.Controller;
import domain.adt.ExeStack;
import domain.adt.FileTable;
import domain.adt.Heap;
import domain.adt.OutputList;
import domain.adt.SymTable;
import domain.adt.Tuple;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.JTextField;
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
import model.statement.While;
import repo.IRepo;
import repo.Repo;

/**
 *
 * @author asus
 */
public class SecondController implements Initializable {
    
    private Controller ctrl;
    private ExecutorService executor;
    private boolean isExecutorSet=false;
    private int selectedProgStateID;
    @FXML
    private Button oneStepButton, allStepButton;
    private TextField progStatesTextField;
    
    void setCtrl(Controller ctr){
        this.ctrl=ctr;
    }
    
//--------------------------------------------ONE STEP EVENT------------------------------------------------------------------------
    
    @FXML
    private void handleOneStepButtonAction(ActionEvent event) {
        try{
            if(!isExecutorSet)
                ctrl.setExecutor();
            
            populateAll();
            IRepo repo=ctrl.getRepo();
            List<ProgState> prgList=ctrl.removeCompletedPrg(repo.getPrgList());
            Map<Integer, Tuple<String, BufferedReader>> ft=prgList.get(0).getFileTable().getContent();
            //executor = Executors.newFixedThreadPool(2);
            ProgState prg=prgList.get(0);
            
            prg.getHeap().setContent(ctrl.conservativeGarbageCollector(
                        prg.getSymTable().getContent().values(),
                        prg.getHeap().getContent()));
            repo.setPrgList(ctrl.removeCompletedPrg(repo.getPrgList()));
            ctrl.oneStepForAllPrg(prgList);
            if(prgList.size()==0){
                ctrl.getExecutor().shutdownNow();
                isExecutorSet=false;
                throw new Exception("The program is finished!");
            }
            
            repo.getPrgList().forEach(prog->repo.logPrgStateExec(prog));
            repo.setPrgList(prgList);
        }
        catch(Exception ex){
            treatException(ex);
        } 
    }
    
//--------------------------------------------ALL STEP EVENT------------------------------------------------------------------------
    
    @FXML
    private void handleAllStepButtonAction(ActionEvent event) {
        //allStep method from Controller class copied...
        try{
            IRepo repo=ctrl.getRepo();
            List<ProgState> prgList=ctrl.removeCompletedPrg(repo.getPrgList());;
            Map<Integer, Tuple<String, BufferedReader>> ft=prgList.get(0).getFileTable().getContent();
            
            while(prgList.size() > 0){
                ProgState prg=prgList.get(0);
                prg.getHeap().setContent(ctrl.conservativeGarbageCollector(
                        prg.getSymTable().getContent().values(),
                        prg.getHeap().getContent()));
                ctrl.oneStepForAllPrg(prgList);
                prgList=ctrl.removeCompletedPrg(repo.getPrgList());
                populateAll();
            }
            
            repo.getPrgList().forEach(prg->repo.logPrgStateExec(prg));
            repo.getPrgList().forEach(prg->prg.finalOutput());
            repo.setPrgList(prgList);
            
            ctrl.closeFiles(ft);
        if(prgList.size()==0)
            throw new Exception("The program is finished!");
        }
        catch(Exception ex){
            treatException(ex);
        }
    }
    
    private void treatException(Exception ex){
        Alert alert;
        populateAll();
        if(ex.getMessage().contains("The program is finished!"))
            alert = new Alert(AlertType.INFORMATION, "The program is finished!", ButtonType.OK);
        else
            alert = new Alert(AlertType.ERROR, "Exception: " + ex.toString(), ButtonType.OK);
        
        alert.showAndWait();
        System.out.print(ex);
    }
    
    public void reinit(){
        selectedProgStateID=1;
        progStatesListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                selectedProgStateID=Integer.parseInt(newValue);
                populateAll();
            }
        });
    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //progStatesTextField.setText(String.valueOf(ctrl.getRepo().getPrgList().size()));
        
        outputListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        outputListView.getSelectionModel().selectIndices(1);
        outputListView.getFocusModel().focus(2);
        
        exeStackListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        exeStackListView.getSelectionModel().selectIndices(1);
        exeStackListView.getFocusModel().focus(2);
        
        progStatesListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        progStatesListView.getSelectionModel().selectIndices(1);
        progStatesListView.getFocusModel().focus(2);
        
        symTableView.setEditable(true);
        symFirstCol.setCellValueFactory(new PropertyValueFactory<Objectt, String>("firstCol"));
        symSecondCol.setCellValueFactory(new PropertyValueFactory<Objectt, String>("secondCol"));
        
        heapTableView.setEditable(true);
        heapFirstCol.setCellValueFactory(new PropertyValueFactory<Objectt, String>("firstCol"));
        heapSecondCol.setCellValueFactory(new PropertyValueFactory<Objectt, String>("secondCol"));
        
        fileTableView.setEditable(true);
        fileFirstCol.setCellValueFactory(new PropertyValueFactory<Objectt, String>("firstCol"));
        fileSecondCol.setCellValueFactory(new PropertyValueFactory<Objectt, String>("secondCol"));
    }
    
    public void populateAll(){
        //progStatesTextField.setText(String.valueOf(ctrl.getRepo().getPrgList().size()));
        populateHeap();
        populateFileTable();
        populateIDs();
        populateSymTable();
        populateExeStack();
        populateOutlist();
    }
    
//--------------------------------------------OUT LIST VIEW------------------------------------------------------------------------
    
    public ListView<String> outputListView;
    
    public void populateOutlist(){
        outputListView.setItems(getOutList());
    }
    
    private ObservableList<String> getOutList() {
        ObservableList<String> data = FXCollections.observableArrayList(
                ctrl.getRepo().getPrgStateByID(selectedProgStateID).getOut().getOutput());
        return data;
    }

    
//--------------------------------------------ExeStack LIST VIEW------------------------------------------------------------------------
    
    public ListView<String> exeStackListView;
    
    public void populateExeStack(){
        exeStackListView.getItems().addAll(getExeStack());
    }
    
    private ObservableList<String> getExeStack() {
        ObservableList<String> data = FXCollections.observableArrayList(
                ctrl.getRepo().getPrgStateByID(selectedProgStateID).getExeStack().getContent());
        return data;
    }

    
//--------------------------------------------PrgStates IDs LIST VIEW------------------------------------------------------------------------
    
    public ListView<String> progStatesListView;
    
    public void populateIDs(){
        progStatesListView.setItems(getIDs());
    }
    
    private ObservableList<String> getIDs() {
        ObservableList<String> data = FXCollections.observableArrayList(ctrl.getRepo().getPrgStatesIDs());
        return data;
    }

//--------------------------------------------SymTable TABLE VIEW-------------------------------------------------------------------
    
    public TableView<Objectt> symTableView;
    @FXML
    private TableColumn<Objectt,String> symFirstCol;
    @FXML
    private TableColumn<Objectt,String> symSecondCol;
    
    public void populateSymTable(){
        symTableView.setItems(getValuesForSymTable());
    }
    
    private ObservableList<Objectt> getValuesForSymTable() {
        ObservableList<Objectt> data = FXCollections.observableArrayList();
        Map<String, Integer> dictionary=ctrl.getRepo().getPrgStateByID(selectedProgStateID).getSymTable().getContent();
        for(HashMap.Entry<String, Integer> e:dictionary.entrySet()){
            data.add(new Objectt(e.getKey().toString(), e.getValue().toString()));
        }
        return data;
    }
    
    
//--------------------------------------------HEAP TABLE TABLE VIEW-------------------------------------------------------------------
    
    public TableView<Objectt> heapTableView;
    @FXML
    private TableColumn<Objectt,String> heapFirstCol;
    @FXML
    private TableColumn<Objectt,String> heapSecondCol;
    
    public void populateHeap(){
        heapTableView.setItems(getValuesForHeap());
    }
    
    private ObservableList<Objectt> getValuesForHeap() {
        ObservableList<Objectt> data = FXCollections.observableArrayList();
        Map<Integer, Integer> dictionary=ctrl.getRepo().getPrgStateByID(selectedProgStateID).getHeap().getContent();
        for(HashMap.Entry<Integer, Integer> e:dictionary.entrySet()){
            data.add(new Objectt(e.getKey().toString(), e.getValue().toString()));
        }
        return data;
    }
    
//--------------------------------------------FILE TABLE TABLE VIEW-------------------------------------------------------------------
    
    public TableView<Objectt> fileTableView;
    @FXML
    private TableColumn<Objectt,String> fileFirstCol;
    @FXML
    private TableColumn<Objectt,String> fileSecondCol;
    
    public void populateFileTable(){
        fileTableView.setItems(getValuesForFileTable());
    }
    
    private ObservableList<Objectt> getValuesForFileTable() {
        ObservableList<Objectt> data = FXCollections.observableArrayList();
        Map<String, String> dictionary=ctrl.getRepo().getPrgStateByID(selectedProgStateID).getFileTable().getStringContent();
        for(HashMap.Entry<String, String> e:dictionary.entrySet()){
            data.add(new Objectt(e.getKey(), e.getValue()));
        }
        return data;
    }
}
