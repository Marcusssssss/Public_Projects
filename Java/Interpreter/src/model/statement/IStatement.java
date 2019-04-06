package model.statement;

import java.lang.Exception;

import model.ProgState;

public interface IStatement {
    ProgState execute(ProgState p) throws Exception;
    String toString();
}
