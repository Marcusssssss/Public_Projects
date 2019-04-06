package model.file_statements;

import domain.adt.FileTableInterface;
import domain.adt.SymTableInterface;
import domain.adt.Tuple;
import exceptions.FileTableException;
import model.ProgState;
import model.expression.IExpression;
import model.statement.IStatement;

import java.io.BufferedReader;

public class closeRFile implements IStatement {

    IExpression exp_file_id;

    public closeRFile(IExpression exp_file_id){
        this.exp_file_id=exp_file_id;
    }

    @Override
    public ProgState execute(ProgState p) throws Exception {
        SymTableInterface symTable=p.getSymTable();
        FileTableInterface fileTable=p.getFileTable();
        int val=exp_file_id.evaluate(symTable, p.getHeap());
        Tuple t=fileTable.lookup(val);

        if(t==null)
            throw new FileTableException("No entry for the evaluated value at closing file!");
        BufferedReader br=BufferedReader.class.cast(t.getSecond());
        br.close();
        fileTable.remove(exp_file_id.evaluate(symTable, p.getHeap()));
        return p;
    }

    @Override
    public String toString(){
        return "closeRFile("+exp_file_id.toString()+")";
    }
}
