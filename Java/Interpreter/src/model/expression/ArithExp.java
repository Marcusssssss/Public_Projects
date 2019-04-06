package model.expression;

import domain.adt.HeapInterface;
import domain.adt.SymTableInterface;
import exceptions.ExpressionException;

public class ArithExp implements IExpression {
    private char operator;
    private IExpression operand1, operand2;

    public ArithExp(char operator, IExpression operand1,
                    IExpression operand2) {
        this.operator = operator;
        this.operand1 = operand1;
        this.operand2 = operand2;
    }

    @Override
    public String toString() {
        return operand1.toString() + " " + operator + " "
                + operand2.toString();
    }

    @Override
    public int evaluate(SymTableInterface<String, Integer> t, HeapInterface heap) throws Exception{
        int result1 = operand1.evaluate(t, heap);
        int result2 = operand2.evaluate(t, heap);
        switch (operator) {
            case '+':
                return result1 + result2;
            case '-':
                return result1 - result2;
            case '*':
                return result1 * result2;
            case '/': {
                if (result2 == 0) {
                    throw new ExpressionException("Cannot " +
                            "divide by 0");
                } else {
                    return result1 / result2;
                }
            }
            default:
                throw new ExpressionException("Invalid " +
                        "operator");
        }
    }
}
