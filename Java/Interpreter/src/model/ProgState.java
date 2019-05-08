package model;

import exceptions.StackException;
import model.statement.IStatement;
import domain.adt.*;
import java.util.Stack;

public class ProgState {

    private ExeStackInterface<IStatement> exeStack;
    private Stack<SymTableInterface<String, Integer>> stackSymTable;
    private OutputListInterface<Integer> out;
    private FileTableInterface fileTable;
    private HeapInterface heap;
    private ProcInterface procTable;
    IStatement originalProgram;
    boolean displayFlag;
    private int id;

    public ProgState(ProcInterface procTable, ExeStackInterface<IStatement> exeStack,
                     Stack<SymTableInterface<String, Integer>> stackSymTable,
                     OutputListInterface<Integer> out, FileTableInterface table,
                     HeapInterface heap, IStatement origPg, int id){
        this.exeStack=exeStack;
        this.stackSymTable=stackSymTable;
        this.out=out;
        this.fileTable=table;
        this.heap=heap;
        this.id=id;
        this.procTable=procTable;
        displayFlag=true;
        exeStack.push(origPg);
    }

    public ProgState oneStep() throws Exception{
        if (exeStack.isEmpty()) {
            if(displayFlag) {
                finalOutput();
            }
            throw new StackException("The execution finished for thread with ID="+String.valueOf(this.id)+"!\n\n");
        }
        IStatement crtStmt = exeStack.pop();
        if(displayFlag){
            toString(crtStmt);
        }
        return crtStmt.execute(this);
    }

    public ExeStackInterface<IStatement> getExeStack() {
        return exeStack;
    }

    public void setExeStack(ExeStackInterface<IStatement> exeStack) {
        this.exeStack = exeStack;
    }

    public SymTableInterface<String, Integer> getSymTable() {
        return stackSymTable.peek();
    }

    public Stack<SymTableInterface<String, Integer>> getStackSymTable() {
        return stackSymTable;
    }
    
    public void setSymTable(Stack<SymTableInterface<String, Integer>> stackSymTable) {
        this.stackSymTable = stackSymTable;
    }

    public OutputListInterface<Integer> getOut() {
        return out;
    }

    public void setOut(OutputListInterface<Integer> out) {
        this.out = out;
    }

    public IStatement getOriginalProgram() {
        return originalProgram;
    }

    public void setOriginalProgram(IStatement originalProgram) {
        this.originalProgram = originalProgram;
    }

    public FileTableInterface getFileTable() {
        return fileTable;
    }

    public void setFileTable(FileTable fileTable) {
        this.fileTable = fileTable;
    }

    public HeapInterface getHeap() {
        return heap;
    }

    public void setHeap(HeapInterface heap) {
        this.heap = heap;
    }

    public boolean isNotCompleted(){
        return !this.exeStack.isEmpty();
    }

    public void switchDisplay(){
        this.displayFlag = !this.displayFlag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public ProcInterface getProcTable(){
        return procTable;
    }
    
    public void setProcInterface(ProcInterface inter){
        this.procTable=inter;
    }

    public void toString(IStatement crtStmt){
        System.out.println("\n\n"+"\nThread ID: "+String.valueOf(id)+
                "\nExeStack: "+crtStmt.toString()+exeStack.toString()+
                "\nSymbols table_"+String.valueOf(id)+": "+this.getSymTable().toString()+
                "\nOut: "+this.getOut().toString()+
                "\nFile table: "+this.getFileTable().toString()+
                "\nHeap: "+this.getHeap().toString()
        );
    }

    public void finalOutput(){
        System.out.println("\n\n"+"\nThread ID: "+String.valueOf(id)+
                "\nExeStack: "+exeStack.toString()+
                "\nSymbols table_"+String.valueOf(id)+": "+this.getSymTable().toString()+
                "\nOut: "+this.getOut().toString()+
                "\nFile table: "+this.getFileTable().toString()+
                "\nHeap: "+this.getHeap().toString()
        );
    }
}
