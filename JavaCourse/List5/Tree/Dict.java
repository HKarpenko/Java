package Tree;

public interface Dict <T extends Comparable<T>>{
    public boolean search(T searchingValue);
    public boolean insert(T insertingValue);
    public boolean remove(T removingValue);
    public T min();
    public T max();
}
