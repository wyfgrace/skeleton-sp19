public interface Deque<T> {
    default public boolean isEmpty(){
        return size()==0;
    }
    public void printDeque();
    public void addFirst(T item);
    public void addLast(T item);
    public int size();
    public T removeFirst();
    public T removeLast();
    public T get(int index);
}
