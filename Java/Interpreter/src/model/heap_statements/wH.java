package model.heap_statements;

import domain.adt.HeapInterface;
import domain.adt.SymTable;
import domain.adt.SymTableInterface;
import exceptions.DictionaryException;
import exceptions.HeapException;
import model.ProgState;
import model.expression.IExpression;
import model.statement.IStatement;

public class wH implements IStatement {

    String var_name;
    IExpression expression;

    public wH(String var_name, IExpression expression){
        this.var_name=var_name;
        this.expression=expression;
    }

    @Override
    public ProgState execute(ProgState p) throws Exception {
        SymTableInterface<String, Integer> st=p.getSymTable();
        HeapInterface<Integer, Integer> hp=p.getHeap();
        int addr, var=expression.evaluate(st, hp);
        if(st.isDefined(var_name))
            addr=st.lookup(var_name);
        else
            throw new DictionaryException("The address is not defined in the symTable!");
        if(hp.isDefined(addr))
            hp.update(addr, var);
        else
            throw new HeapException("There is no value corresponding to that address!");
        return p;
    }

    @Override
    public String toString(){
        return "wH("+var_name+", "+expression.toString()+")";
    }
}
