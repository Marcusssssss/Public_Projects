package model.expression;

import domain.adt.HeapInterface;
import domain.adt.SymTableInterface;

public class BoolExp implements IExpression {

    String operator;
    IExpression exp1, exp2;

    public BoolExp(String op, IExpression e1, IExpression e2){
        operator=op;
        exp1=e1;
        exp2=e2;
    }

    @Override
    public int evaluate(SymTableInterface<String, Integer> symTable, HeapInterface heap) throws Exception {
        int res1= exp1.evaluate(symTable, heap);
        int res2=exp2.evaluate(symTable, heap);
        switch (operator){
            case "<":
                if(res1<res2)
                    return 1;
            case "<=":
                if(res1<=res2)
                    return 1;
            case "==":
                if(res1==res2)
                    return 1;
            case "!=":
                if(res1!=res2)
                    return 1;
            case ">":
                if(res1>res2)
                    return 1;
            case ">=":
                if(res1>=res2)
                    return 1;
            default: break;
        }
        return 0;
    }

    @Override
    public String toString(){
        return "["+exp1.toString()+operator+exp2.toString()+"]";
    }
}
