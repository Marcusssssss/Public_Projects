package model.statement;

import domain.adt.ExeStack;
import domain.adt.ExeStackInterface;
import domain.adt.HeapInterface;
import domain.adt.SymTableInterface;
import model.ProgState;
import model.expression.IExpression;

public class While implements IStatement {

    IExpression expression;
    IStatement statement;

    public While(IExpression exp, IStatement stm){
        expression=exp;
        statement=stm;
    }

    @Override
    public ProgState execute(ProgState p) throws Exception {
        SymTableInterface st=p.getSymTable();
        HeapInterface hp=p.getHeap();
        ExeStackInterface exeStack=p.getExeStack();
        if(expression.evaluate(st, hp)!=0) {
            exeStack.push(this);
            exeStack.push(statement);
        }
        //return p;
        return null;
    }

    @Override
    public String toString(){
        return "[while("+expression.toString()+")"+statement.toString()+"]";
    }
}
