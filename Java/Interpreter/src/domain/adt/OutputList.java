package domain.adt;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class OutputList<T> implements OutputListInterface<T> {
    Stack<T> list;

    public OutputList() {
        list = new Stack<T>();
    }

    @Override
    public void add(T v) {
        list.add(v);
    }

    @Override
    public T pop() {
        return list.pop();
    }


    @Override
    public String toString() {
        String str="";
        for (T e : list) {
            str+=e.toString()+"; ";
        }
        return str;
    }

    @Override
    public int size() {
        return list.size();
    }


    public Stack<T> getList() {
        return list;
    }
    
    @Override
    public List<String> getOutput(){
        List<String> newList=new ArrayList<>();
        for (T e : list) {
            newList.add(e.toString());
        }
        return newList;
    }
}
