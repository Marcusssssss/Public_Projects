package domain.adt;

import exceptions.FileTableException;
import java.lang.Integer;
import java.util.HashMap;
import java.util.Map;

public class FileTable<T1, T2> implements FileTableInterface<T1, T2>{

    private Map<Integer, Tuple> dictionary;

    private int index=1;

    public FileTable(){
        dictionary=new HashMap<>();
    }

    @Override
    public void add(Tuple v2) {
        dictionary.put(this.index++, v2);
    }

    @Override
    public void update(int v1, Tuple v2) {
        dictionary.put(v1, v2);
    }

    @Override
    public boolean isDefined(int id) {
        return dictionary.get(id)!=null;
    }

    public boolean containsValue(Tuple v) {
        for (HashMap.Entry<Integer, Tuple> e : dictionary.entrySet())
            if (e.getValue() == v)
                return true;
        return false;
    }

    public boolean containsFile(String s) {
        for(HashMap.Entry<Integer, Tuple> e : dictionary.entrySet())
            if(e.getValue().getFirst().toString().compareTo(s)==0)
                return true;
        return false;
    }

    @Override
    public Tuple lookup(int id) throws FileTableException {
        Tuple res=dictionary.get(id);
        if (res != null) {
            return res;
        }
        throw new FileTableException("The item with id='"
                +String.valueOf(id)+"' was not found in the file table!");
    }

    @Override
    public int size() {
        return dictionary.size();
    }

    public int getCurrentIndex(){
        return this.index;
    }

    public void remove(int v){
        dictionary.remove(v);
    }

    @Override
    public String toString() {
        String dictionaryString="";
        for(HashMap.Entry<Integer, Tuple> e : dictionary.entrySet()){
            dictionaryString+=e.getKey().toString()+" --> "
                    +String.class.cast(e.getValue().getFirst())+"; ";
        }
        return dictionaryString;
    }

    @Override
    public int getIndex(){
        return this.index;
    }

    @Override
    public Map<Integer, Tuple> getContent(){
        return dictionary;
    }
    
    @Override
    public Map<String, String> getStringContent(){
        Map<String, String> tostr=new HashMap<>();
        for(HashMap.Entry<Integer, Tuple> e : dictionary.entrySet())
            tostr.put(e.getKey().toString(), String.class.cast(e.getValue().getFirst()));
        return tostr;
    }
}
