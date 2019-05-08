package model.statement;

import model.ProgState;
import model.expression.IExpression;

public class ConditionalStm implements IStatement {
    private IExpression exp;
    private IStatement then;
    private IStatement els;

    public ConditionalStm(IExpression exp, IStatement then, IStatement els){
        this.exp=exp;
        this.then=then;
        this.els=els;
    }

    public IExpression getExp() {
        return exp;
    }

    public IStatement getThen() {
        return then;
    }

    public IStatement getEls() {
        return els;
    }

    @Override
    public ProgState execute(ProgState p) {

        try {
            if (exp.evaluate(p.getSymTable(), p.getHeap())!=0)
                then.execute(p);
            else els.execute(p);
            //return p;
            return null;
        }
        catch(Exception ex){return null;}
    }

    @Override
    public String toString() {
        return "[If ("+exp+") Then ("+then+") Else ("+els+")] ";
    }
}
