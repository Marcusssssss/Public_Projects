package model.heap_statements;

import domain.adt.HeapInterface;
import domain.adt.SymTableInterface;
import model.ProgState;
import model.expression.IExpression;
import model.statement.IStatement;

public class New implements IStatement {

    String var_name;
    IExpression expression;

    public New(String name, IExpression exp){
        var_name=name;
        expression=exp;
    }

    @Override
    public ProgState execute(ProgState p) throws Exception {
        HeapInterface hp=p.getHeap();
        SymTableInterface st=p.getSymTable();
        int addr=hp.add(expression.evaluate(st, hp));
        if(st.isDefined(var_name))
            st.update(var_name, addr);
        else
            st.add(var_name, addr);
        p.getSymTable().add(var_name, addr);
        return p;
    }

    @Override
    public String toString() {
        return "new("+var_name+", "+expression.toString()+")";
    }
}
