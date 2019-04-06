package domain.adt;

import java.util.List;
import java.util.Stack;

public interface ExeStackInterface<T> {
    T pop();

    void push(T v);

    boolean isEmpty();

    int size();

    String toString();
    
    Stack<T> getStack();
    
    List<String> getContent();
}
