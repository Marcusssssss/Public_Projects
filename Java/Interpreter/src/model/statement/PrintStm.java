package model.statement;

import domain.adt.OutputListInterface;
import model.ProgState;
import model.expression.IExpression;

public class PrintStm implements IStatement {
    private IExpression expression;

    public PrintStm(IExpression ex){
        this.expression=ex;
    }

    public IExpression getExpression() {
        return expression;
    }

    @Override
    public ProgState execute(ProgState p) throws Exception {
        OutputListInterface<Integer> out=p.getOut();
        out.add(expression.evaluate(p.getSymTable(), p.getHeap()));
        //return p;
        return null;
    }

    @Override
    public String toString() {
        return "[Print("+expression.toString()+")] ";
    }
}
