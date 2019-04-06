package model.expression;

import domain.adt.HeapInterface;
import domain.adt.SymTableInterface;
import exceptions.DictionaryException;

public class VarExpr implements IExpression {
    private String name;

    public VarExpr(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int evaluate(SymTableInterface<String, Integer> t, HeapInterface heap) throws Exception{
        if (t.isDefined(name)) {
            return t.lookup(name);
        } else {
            throw new DictionaryException("symTable does not" +
                    " contain the variable");
        }
    }
}
