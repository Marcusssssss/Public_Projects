package domain.adt;

import exceptions.HeapException;

import java.util.HashMap;
import java.util.Map;

public class Heap<T1, T2> implements HeapInterface<T1, T2> {

    Map<Integer, Integer> dictionary;
    int nextFree=1;

    public Heap(){
        dictionary=new HashMap<>();
    }

    @Override
    public int add(int content) {
        dictionary.put(nextFree++, content);
        return nextFree-1;
    }

    @Override
    public void update(int address, int content) {
        dictionary.replace(address, content);
    }

    @Override
    public int lookup(int id) throws HeapException {
        if (dictionary.get(id) != null) {
            return dictionary.get(id);
        }
        throw new HeapException("The item with id='"
                +String.valueOf(id)+"' was not found in the file table!");
    }

    @Override
    public boolean isDefined(int id) {
        return dictionary.get(id)!=null;
    }

    @Override
    public int size() {
        return dictionary.size();
    }

    public boolean containsValue(int v) {
        for (HashMap.Entry<Integer, Integer> e : dictionary.entrySet())
            if (e.getValue() == v)
                return true;
        return false;
    }

    @Override
    public void remove(int v) {
        dictionary.remove(v);
    }

    @Override
    public String toString(){
        String str="";
        for(HashMap.Entry<Integer, Integer> e:dictionary.entrySet()){
            str+=e.getKey().toString()+" -> "+e.getValue().toString()+"; ";
        }
        return str;
    }

    @Override
    public  Map<Integer, Integer> getContent() {
        return dictionary;
    }

    @Override
    public void setContent(Map<Integer, Integer> dict){
        dictionary=dict;
    }
}
