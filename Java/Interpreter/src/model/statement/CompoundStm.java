package model.statement;

import model.ProgState;
import domain.adt.ExeStackInterface;

public class CompoundStm implements IStatement {
    private IStatement first, second;

    public CompoundStm(IStatement one, IStatement two) {
        this.first=one;
        this.second=two;
    }

    public IStatement getFirst() {
        return first;
    }

    public IStatement getSecond() {
        return second;
    }

    @Override
    public ProgState execute(ProgState p) {
        ExeStackInterface<IStatement> exec = p.getExeStack();
        exec.push(second);
        exec.push(first);
        //return p;
        return null;
    }

    @Override
    public String toString() {
        return "<<"+first+"| "+second+">> ";
    }
}
