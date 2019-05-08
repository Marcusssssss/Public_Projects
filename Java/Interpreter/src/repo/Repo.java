package repo;

import domain.adt.ExeStackInterface;
import model.ProgState;
import model.statement.IStatement;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Repo implements IRepo{
    private List<ProgState> programStates;
    String logFilePath;

    public Repo(String logFilePath) {
        this.logFilePath=logFilePath;
        programStates = new ArrayList<>();
    }

    public Repo(ProgState ps, String logFilePath) {
        this.logFilePath=logFilePath;
        programStates = new ArrayList<>();
        programStates.add(ps);

        //clearing the file
        try {
            PrintWriter writer = new PrintWriter(new BufferedWriter(
                    new FileWriter(logFilePath)));
            writer.print("");
            writer.close();
        }
        catch(IOException ex){}
    }

    public void addProgr(ProgState progState) {
        programStates.add(progState);
    }

    @Override
    public void logPrgStateExec(ProgState state) {
            ExeStackInterface<IStatement> stk = state.getExeStack();

        try {
                PrintWriter logFile = new PrintWriter(new BufferedWriter(
                        new FileWriter(logFilePath, true)));

                logFile.println("Thread ID:");
                logFile.printf(String.valueOf(state.getId())+"\n");
                logFile.println("ExeStack:");
                logFile.printf(/*crtStm.toString()+*/state.getExeStack().toString()+"\n");
                logFile.println("SymTable:");
                logFile.printf(state.getSymTable().toString()+"\n");
                logFile.println("Output:");
                logFile.printf(state.getOut().toString()+"\n");
                logFile.println("FileTable:");
                logFile.printf(state.getFileTable().toString()+"\n");
                logFile.println("Heap:");
                logFile.printf(state.getHeap().toString()+"\n\n");
                logFile.println("--------------------------------------------------------------");
                logFile.close();
        }
        catch(IOException ex){
            System.out.println(ex.toString());
        }
    }

    public List<ProgState> getPrgList() {
        return programStates;
    }
    
    
    @Override
    public ProgState getPrgStateByID(int id){
        ProgState p=null;
        for(ProgState ps: programStates){
            if(ps.getId()==id)
                p=ps;
        }
        return p;
    }
    
    @Override
    public List<String> getPrgStatesIDs(){
        List<String> ids=new ArrayList<>();
        for(ProgState ps: programStates)
            ids.add(String.valueOf(ps.getId()));
        return ids;
    }
    
    public void setPrgList(List<ProgState> list){
        this.programStates=list;
    }
}
