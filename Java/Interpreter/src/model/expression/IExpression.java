package model.expression;

//import util.ISymTable;
import domain.adt.HeapInterface;
import domain.adt.SymTableInterface;

public interface IExpression {
    int evaluate(SymTableInterface<String, Integer> symTable, HeapInterface heap)throws Exception;
    String toString();
}
