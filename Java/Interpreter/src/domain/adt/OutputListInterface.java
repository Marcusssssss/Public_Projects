package domain.adt;

import java.util.List;

public interface OutputListInterface<T> {
    void add(T v);

    T pop();

    String toString();

    int size();
    
    List<String> getOutput();
}
