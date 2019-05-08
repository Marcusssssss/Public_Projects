/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.statement;

import java.util.List;
import model.ProgState;
import model.expression.IExpression;

/**
 *
 * @author asus
 */
public class CallStm implements IStatement {

    List<IExpression> list;
    String name;
    IStatement stm;
    
    public CallStm(String n, List<IExpression> l){
        list=l;
        name=n;
    }
    
    @Override
    public ProgState execute(ProgState p) throws Exception {
        p.getExeStack().push(new ReturnStm());
        return null;
    }
    
    @Override
    public String toString(){
        return "";
    }
}
