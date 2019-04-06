/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.adt;

import exceptions.ProcException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.expression.IExpression;
import model.statement.IStatement;

/**
 *
 * @author asus
 */
public class ProcTable implements ProcInterface {
    private Map<String, Tuple<List<IExpression>, IStatement>> dictionary;

    public ProcTable(){
        dictionary=new HashMap<>();
    }
    
    @Override
    public void add(String name, Tuple<List<IExpression>, IStatement> v2) {
        dictionary.put(name, v2);
    }

    @Override
    public void update(String v1, Tuple<List<IExpression>, IStatement> v2) {
        dictionary.put(v1, v2);
    }

    @Override
    public Tuple lookup(String name) throws ProcException {
        if(dictionary.containsKey(name))
            return dictionary.get(name);
        else throw new ProcException("Inexistent value in Procedure Table!");
    }

    @Override
    public boolean isDefined(String id) {
        return dictionary.containsKey(id);
    }
    
    @Override
    public int size() {
        return dictionary.size();
    }

    @Override
    public Map<String, Tuple<List<IExpression>, IStatement>> getContent() {
        return dictionary;
    }
    
    @Override
    public List<IExpression> getListExpressionsByName(String name){
        for(HashMap.Entry<String, Tuple<List<IExpression>, IStatement>> e:dictionary.entrySet())
            if(name.compareTo(e.getKey())==0)
                return e.getValue().getFirst();
        return null;
    }
    
    @Override
    public Map<String, String> getStringContent() {
        Map<String, String> toStr=new HashMap<>();
        for(HashMap.Entry<String, Tuple<List<IExpression>, IStatement>> e:dictionary.entrySet()){
            String varList="";
            for(IExpression s:e.getValue().getFirst()){
                varList+=s.toString()+',';
            }
            toStr.put(e.getKey(), varList);
        }
        return toStr;
    }
}
