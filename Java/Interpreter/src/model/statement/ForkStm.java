package model.statement;

import domain.adt.ExeStack;
import domain.adt.SymTable;
import domain.adt.SymTableInterface;
import model.ProgState;

import java.util.HashMap;
import java.util.Stack;

public class ForkStm implements IStatement {

    IStatement statement;

    public ForkStm(IStatement stm){
        this.statement=stm;
    }

    @Override
    public ProgState execute(ProgState p) throws Exception {
        ExeStack st=new ExeStack();
        
        Stack<SymTableInterface<String, Integer>> newSymTableStack=new Stack<>();
        for(SymTableInterface<String, Integer> symTable:p.getStackSymTable()){
            newSymTableStack.add(symTable.clone());
        }
        
        ProgState newPState=new ProgState(p.getProcTable(), st, newSymTableStack, p.getOut(), p.getFileTable(), p.getHeap(), statement, p.getId()*10);
        return newPState;
    }

    @Override
    public String toString(){
        return "Fork( "+statement.toString()+") ";
    }
}
