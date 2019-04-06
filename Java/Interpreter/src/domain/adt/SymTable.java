package domain.adt;

import java.util.HashMap;
import exceptions.DictionaryException;

public class SymTable<T1, T2> implements SymTableInterface<T1, T2> {
    HashMap<T1, T2> dictionary;

    public SymTable() {
        dictionary = new HashMap<T1, T2>();
    }

    @Override
    public void add(T1 v1, T2 v2) {
        dictionary.put(v1, v2);
    }

    @Override
    public void update(T1 v1, T2 v2) {
        dictionary.put(v1, v2);
    }

    @Override
    public T2 lookup(T1 id) throws DictionaryException{
        if (dictionary.get(id) != null) {
            return dictionary.get(id);
        }
        throw new DictionaryException("The item with id='"
                +id.toString()+"' was not found in the dictionary.");
    }

    @Override
    public boolean isDefined(String id) {
        return dictionary.get(id) != null;
    }

    @Override
    public boolean contains(T1 v) {
        return dictionary.get(v) != null;
    }

    @Override
    public int size() {
        return dictionary.size();
    }

    @Override
    public void setValue(T1 v1, T2 v2) {
        dictionary.put(v1, v2);
    }

    @Override
    public String toString() {
        String dictionaryString = "";
        for (HashMap.Entry<T1, T2> e : dictionary.entrySet()) {
            dictionaryString +=e.getKey().toString() +
                    " --> " + e.getValue().toString()+"; ";
        }
        return dictionaryString;
    }

    @Override
    public HashMap<T1, T2> getContent() {
        return dictionary;
    }

    @Override
    public SymTable clone(){
        SymTable st=new SymTable();
        for(HashMap.Entry<T1, T2> e: dictionary.entrySet()){
            st.add(new String(String.class.cast(e.getKey())), new Integer(Integer.class.cast(e.getValue())));
        }
        return st;
    }
}
