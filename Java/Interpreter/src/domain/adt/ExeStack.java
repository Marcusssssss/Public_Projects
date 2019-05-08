package domain.adt;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


public class ExeStack<T> implements ExeStackInterface<T> {
    Stack<T> stack;

    public ExeStack() {
        stack = new Stack<T>();
    }

    @Override
    public T pop() {
        return stack.pop();
    }

    @Override
    public void push(T v) {
        stack.push(v);
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public int size() {
        return stack.size();
    }
    
    @Override
    public Stack<T> getStack() {
        return stack;
    }

    @Override
    public String toString(){
        String out="";
        for(T e:stack){
            out+=e.toString()+" ";
        }
        return out;
    }
    
    @Override
    public List<String> getContent(){
        List<String> newList=new ArrayList<>();
        for(T e:stack){
            newList.add(e.toString());
        }
        return newList;
    }

}
