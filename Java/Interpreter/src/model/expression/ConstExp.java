package model.expression;

import domain.adt.HeapInterface;
import domain.adt.SymTableInterface;

public class ConstExp implements IExpression {
    private int value;

    public ConstExp(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public int evaluate(SymTableInterface<String, Integer> symTable, HeapInterface heap) {
        return value;
    }
}
