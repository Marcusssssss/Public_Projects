package domain.adt;

public class Tuple<T, U>{
    private T first;
    private U second;

    public Tuple(T f, U s){
        this.first=f;
        this.second=s;
    }

    public T getFirst() {
        return first;
    }

    public void setFirst(T first) {
        this.first = first;
    }

    public U getSecond() {
        return second;
    }

    public void setSecond(U second) {
        this.second = second;
    }

    public String toString(){
        return "("+first.toString()+" "+second.toString()+")";
    }
}
