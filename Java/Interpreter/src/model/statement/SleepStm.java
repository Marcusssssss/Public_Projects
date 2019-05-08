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
public class SleepStm implements IStatement {

    int number;
    
    public SleepStm(int nr){
        this.number=nr;
    }
    
    public int getNumber(){
        return number;
    }
    
    @Override
    public ProgState execute(ProgState p) throws Exception {
        if(number!=0){
            p.getExeStack().push(new SleepStm(number-1));
        }
        return null;
    }
    
    @Override
    public String toString(){
        return "Sleep("+String.valueOf(number)+")";
    }
}
