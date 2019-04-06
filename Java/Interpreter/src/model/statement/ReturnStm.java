/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.statement;

import model.ProgState;

/**
 *
 * @author asus
 */
public class ReturnStm implements IStatement{

    public ReturnStm(){}
    
    @Override
    public ProgState execute(ProgState p) throws Exception {
        p.getStackSymTable().pop();
        return null;
    }
}
