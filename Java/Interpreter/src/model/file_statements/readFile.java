package model.file_statements;

import domain.adt.FileTable;
import domain.adt.FileTableInterface;
import domain.adt.SymTableInterface;
import exceptions.FileTableException;
import model.ProgState;
import model.expression.IExpression;
import model.statement.IStatement;

import java.io.BufferedReader;

public class readFile implements IStatement {

    IExpression exp_file_id;
    String var_name;

    public readFile(IExpression  exp_file_id, String var_name){
        this.exp_file_id=exp_file_id;
        this.var_name=var_name;
    }

    @Override
    public ProgState execute(ProgState p) throws Exception {
        FileTableInterface fileTable=p.getFileTable();
        SymTableInterface symTable=p.getSymTable();

        int val=exp_file_id.evaluate(symTable, p.getHeap());
        BufferedReader br=BufferedReader.class.cast(fileTable.lookup(val).getSecond());
        if(br!=null) {
            String line=br.readLine();
            if(line==null)
                line="0";
            int value=Integer.parseInt(line);
            if(symTable.contains(var_name))
                symTable.update(var_name, value);
            else
                symTable.add(var_name, value);
            return p;
        }
        else
            throw new FileTableException("No entry in FileTable for the exp_file_id!");
    }

    @Override
    public String toString(){
        return "readFile("+exp_file_id.toString()+", "+var_name+")";
    }
}
