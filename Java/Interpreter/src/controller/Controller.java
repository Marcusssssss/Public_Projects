package controller;

import exceptions.StatementExecutionException;
import model.statement.IStatement;
import repo.IRepo;
import model.ProgState;
import domain.adt.*;

import exceptions.StackException;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class Controller {

    IRepo repo;
    ExecutorService executor;
    boolean displayFlag;
    List<ProgState> finishedPrograms;

    public Controller(IRepo repo){

        this.repo=repo;
        this.displayFlag=true;
    }
    
    public ExecutorService getExecutor(){
        return executor;
    }

    public void setExecutor(){
        executor=Executors.newFixedThreadPool(2);
    }

    
    public IRepo getRepo() {
        return repo;
    }

    public void oneStepForAllPrg(List<ProgState> prgList) throws Exception{
        prgList.forEach(prg->repo.logPrgStateExec(prg));

        List<Callable<ProgState>> callList = prgList.stream()
                .map((ProgState p) -> (Callable<ProgState>)(() -> {return p.oneStep();}))
                .collect(Collectors.toList());

        List<ProgState> newPrgList = executor.invokeAll(callList).stream()
                .map(future -> {
                    try {
                        return future.get();
                    }
                    catch(Exception e) {
                        System.out.println(e.getMessage());
                        return null;
                    }})
                    .filter(p -> p!=null)
                    .collect(Collectors.toList());
        prgList.addAll(newPrgList);
        prgList.forEach(prg ->repo.logPrgStateExec(prg));
        repo.setPrgList(prgList);
    }


    public void allStep() throws Exception, IOException {
        List<ProgState> prgList=removeCompletedPrg(repo.getPrgList());;
        Map<Integer, Tuple<String, BufferedReader>> ft=prgList.get(0).getFileTable().getContent();
        try{
            executor = Executors.newFixedThreadPool(2);

            while(prgList.size() > 0){
                ProgState prg=prgList.get(0);
                prg.getHeap().setContent(conservativeGarbageCollector(
                        prg.getSymTable().getContent().values(),
                        prg.getHeap().getContent()));
                oneStepForAllPrg(prgList);
                prgList=removeCompletedPrg(repo.getPrgList());
            }
            executor.shutdownNow();
            repo.getPrgList().forEach(prg->repo.logPrgStateExec(prg));
            repo.getPrgList().forEach(prg->prg.finalOutput());
            repo.setPrgList(prgList);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        finally {
            closeFiles(ft);
            finishedPrograms.stream().forEach(p->p.finalOutput());
        }
    }


    public void closeFiles(Map<Integer, Tuple<String, BufferedReader>> ft){
        ft.entrySet().stream().forEach(
                e->{
                    try {
                        e.getValue().getSecond().close();
                    }
                    catch(IOException ex){System.out.println(ex.getMessage());}
                }
        );
        ft.clear();
    }

    public Map<Integer,Integer> conservativeGarbageCollector(
            Collection<Integer> symTableValues, Map<Integer,Integer> heap){
        return heap.entrySet().stream()
                .filter(e->symTableValues.contains(e.getKey()))
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public List<ProgState> removeCompletedPrg(List<ProgState> inPrgList){

        return inPrgList.stream()
                .filter(p -> p.isNotCompleted())
                .collect(Collectors.toList());
    }
}


/*package controller;

import exceptions.StatementExecutionException;
import model.statement.IStatement;
import repo.IRepo;
import model.ProgState;
import domain.adt.*;
import exceptions.CtrlException;

import exceptions.StackException;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class Controller {

    IRepo repo;
    ExecutorService executor;
    boolean displayFlag;
    List<ProgState> finishedPrograms;

    public Controller(IRepo repo){
        this.repo=repo;
        this.displayFlag=true;
        setExecutor();
    }

    public void setExecutor(){
        executor = Executors.newFixedThreadPool(2);
    }
    
    public ExecutorService getExecutor(){
        return executor;
    }
    
    public IRepo getRepo() {
        return repo;
    }

    public void oneStepForAllPrg(List<ProgState> prgList) throws Exception{
        prgList.forEach(prg->repo.logPrgStateExec(prg));

        List<Callable<ProgState>> callList = prgList.stream()
                .map((ProgState p) -> (Callable<ProgState>)(() -> {return p.oneStep();}))
                .collect(Collectors.toList());
        
        List<ProgState> newPrgList=null;
        try{
                newPrgList = executor.invokeAll(callList).stream()
                .map(future -> {
                    try {
                        return future.get();
                    }
                    catch(Exception e) {
                        System.out.println(e.getMessage());
                        return null;
                    }})
                    .filter(p -> p!=null)
                    .collect(Collectors.toList());
        } catch(InterruptedException ex){
            throw new Exception(ex.getMessage());
        }
        prgList.addAll(newPrgList);
        prgList.forEach(prg ->repo.logPrgStateExec(prg));
        repo.setPrgList(prgList);
    }


    public void allStep() throws Exception, IOException {
        List<ProgState> prgList=removeCompletedPrg(repo.getPrgList());;
        Map<Integer, Tuple<String, BufferedReader>> ft=prgList.get(0).getFileTable().getContent();
//try{
        //
            
            //executor=Executors.newFixedThreadPool(2);
            
        //
        while(prgList.size() > 0){
            ProgState prg=prgList.get(0);
            prg.getHeap().setContent(conservativeGarbageCollector(
                    prg.getSymTable().getContent().values(),
                    prg.getHeap().getContent()));
            oneStepForAllPrg(prgList);
            prgList=removeCompletedPrg(repo.getPrgList());
        }
//}catch(Exception ex){System.out.print(ex.getMessage());}        
        executor.shutdownNow();
        repo.getPrgList().forEach(prg->repo.logPrgStateExec(prg));
        repo.getPrgList().forEach(prg->prg.finalOutput());
        repo.setPrgList(prgList);
//}finally{
        closeFiles(ft);
        //finishedPrograms.stream().forEach(p->p.finalOutput());
        if(prgList.size()==0)
            throw new Exception("The program is finished!");
//}
    }

    public void closeFiles(Map<Integer, Tuple<String, BufferedReader>> ft){
        ft.entrySet().stream().forEach(
                e->{
                    try {
                        e.getValue().getSecond().close();
                    }
                    catch(IOException ex){System.out.println(ex.getMessage());}
                }
        );
        ft.clear();
    }

    public Map<Integer,Integer> conservativeGarbageCollector(
            Collection<Integer> symTableValues, Map<Integer,Integer> heap){
        return heap.entrySet().stream()
                .filter(e->symTableValues.contains(e.getKey()))
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public List<ProgState> removeCompletedPrg(List<ProgState> inPrgList){

        return inPrgList.stream()
                .filter(p -> p.isNotCompleted())
                .collect(Collectors.toList());
    }
}
*/