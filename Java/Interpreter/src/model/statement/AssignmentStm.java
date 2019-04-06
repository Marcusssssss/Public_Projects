package model.statement;

import domain.adt.SymTableInterface;
import model.ProgState;
import model.expression.IExpression;


public class AssignmentStm implements IStatement {
    private String var;
    private IExpression exp;

    public AssignmentStm(String var, IExpression exp) {
        this.var = var;
        this.exp = exp;
    }

    @Override
    public ProgState execute(ProgState p) throws Exception{
        SymTableInterface<String, Integer> symTable=p.getSymTable();
        int result = exp.evaluate(symTable, p.getHeap());
        symTable.add(var, result);
        //return p;
        return null;
    }

    @Override
    public String toString() {
        return "["+var+"="+String.valueOf(exp)+"] ";
    }
}
