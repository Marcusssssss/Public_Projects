package domain.adt;

import exceptions.FileTableException;

import java.util.Map;

public interface FileTableInterface<T1, T2> {
    void add(Tuple v2);

    void update(int v1, Tuple v2);

    Tuple lookup(int id) throws FileTableException;

    boolean isDefined(int id);

    boolean containsFile(String s);

    int size();

    void remove(int v);

    String toString();

    int getIndex();

    Map<Integer, Tuple> getContent();
    
    Map<String, String> getStringContent();
}
