package domain.adt;

import exceptions.HeapException;

import java.util.Map;

public interface HeapInterface<T1, T2> {
    int add(int cont);

    void update(int v1, int v2);

    int lookup(int id) throws HeapException;

    boolean isDefined(int id);

    boolean containsValue(int v);

    int size();

    void remove(int v);

    String toString();

    Map getContent();

    void setContent(Map<Integer, Integer> dictionary);
}
