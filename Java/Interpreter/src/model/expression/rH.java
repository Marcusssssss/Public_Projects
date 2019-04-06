package model.expression;

import domain.adt.HeapInterface;
import domain.adt.SymTableInterface;
import exceptions.DictionaryException;
import exceptions.HeapException;

public class rH implements IExpression {

    String var_name;

    public rH(String var){
        var_name=var;
    }

    @Override
    public int evaluate(SymTableInterface<String, Integer> symTable, HeapInterface heap) throws Exception {
        int addr, val;
        if(symTable.isDefined(var_name))
            addr=symTable.lookup(var_name);
        else
            throw new DictionaryException("The variable is not defined in symTable!");
        if(heap.isDefined(addr))
            return heap.lookup(addr);
        else
            throw new HeapException("The address is not defined in the heap!");
    }

    @Override
    public String toString(){
        return "rH("+var_name+")";
    }
}
