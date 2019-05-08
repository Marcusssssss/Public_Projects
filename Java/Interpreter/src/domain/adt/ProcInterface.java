/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.adt;

import exceptions.FileTableException;
import exceptions.ProcException;
import java.util.List;
import java.util.Map;
import model.expression.IExpression;
import model.statement.IStatement;

/**
 *
 * @author asus
 */
public interface ProcInterface {
    void add(String name, Tuple<List<IExpression>, IStatement> v2);

    void update(String v1, Tuple<List<IExpression>, IStatement> v2);

    Tuple lookup(String id) throws ProcException;

    int size();
    
    boolean isDefined(String name);
    
    String toString();

    List<IExpression> getListExpressionsByName(String name);
    
    Map<String, Tuple<List<IExpression>, IStatement>> getContent();
    
    Map<String, String> getStringContent();
}
