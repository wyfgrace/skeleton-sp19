public interface Deque<T> {
    default boolean isEmpty(){
        return size()==0;
    }
    void printDeque();
    void addFirst(T item);
    void addLast(T item);
    int size();
    T removeFirst();
    T removeLast();
    T get(int index);
}
