package model.file_statements;

import domain.adt.*;
import exceptions.FileTableException;
import model.ProgState;
import model.expression.IExpression;
import model.statement.IStatement;

import java.io.*;

public class openRFile implements IStatement {

    IExpression var_file_id;
    String file_name;

    public openRFile(IExpression v_f_i, String f_n){
        var_file_id=v_f_i;
        file_name=f_n;
    }

    @Override
    public ProgState execute(ProgState progState) throws Exception {
        FileTableInterface fileTable=progState.getFileTable();
        SymTableInterface table=progState.getSymTable();
        if(fileTable.containsFile(file_name)==false) {
            File file=new File(System.getProperty("user.dir")+"\\"+file_name);
            if(file.exists()){
                BufferedReader br = new BufferedReader(new FileReader(file_name));
                Tuple t=new Tuple(file_name, br);
                fileTable.add(t);
                table.add(var_file_id.toString(), fileTable.getIndex()-1);
                return progState;
            }
            else throw new IOException("Inexistent file!");
        }
        else throw new FileTableException("File already existent!");
    }

    @Override
    public String toString(){
        return "openRFile("+var_file_id.toString()+", "+file_name+"); ";
    }
}
