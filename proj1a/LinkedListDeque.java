public class LinkedListDeque<T> {
    private static class Node<T> {
        private final T item;
        private Node<T> pre;
        private Node<T> next;
        private Node(Node<T> m, T i, Node<T> n) {
            item = i;
            pre = m;
            next = n;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "item=" + item +
                    '}';
        }
    }
    private Node<T> first;
    private Node<T> last;
    private int size;


    public LinkedListDeque(){
        first = null;
        last = null;
        size = 0;

    }

    public LinkedListDeque(LinkedListDeque<T> other){
        first = null;
        last = null;
        size = 0;

        Node<T> p = other.first;
        while (p != null){
            addLast(p.item);
            p = p.next;
        }
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void printDeque(){
        Node<T> printItem = first;
        while (printItem != null){
            System.out.print(printItem + " ");
            printItem = printItem.next;
        }
        System.out.println();

    }


    public void addFirst(T item) {
        if (first == null) {
            first = new Node<>(null,item,null);
            last = first;
        }else {
            Node<T> temp = new Node<>(null, item, first);
            first.pre = temp;
            first = temp;
        }
        size++;
    }

    public void addLast(T item) {
        if (last == null){
            last = new Node<>(null,item,null);
            first = last;
        }else {
            Node<T> temp = new Node<>(last, item, null);
            last.next = temp;
            last = temp;
        }
        size++;
    }


    public int size() {
        return size;
    }


    public T removeFirst() {
        Node<T> p = first;
        if (p == null) {
            throw new IllegalStateException("Linked list deque is empty");
        } else if(p.next == null) {
            first = null;
            last = null;
            size--;
            return p.item;
        } else {
            first = p.next;
            first.pre = null;
            size--;
            return p.item;
        }

    }

    public T removeLast() {
        Node<T> p = last;
        if (p == null) {
            throw new IllegalStateException("Linked list deque is empty");
        } else if(p.pre == null) {
            last = null;
            first = null;
            size--;
            return p.item;
        } else {
            last = p.pre;
            last.next = null;
            size--;
            return p.item;
        }
    }

    public T get(int index) {
        int i = 0;
        Node<T> p = first;
        while (i < index){
            if (p.next == null){
                return null;
            }
            p = p.next;
            i = i+1;
        }
        return p.item;
    }

    private T getRecursiveInternal(Node<T> p, int index) {
        if (p == null) {
            return null;
        } else if (index == 0) {
            return p.item;
        } else {
            return getRecursiveInternal(p.next, index-1);
        }
    }

    public T getRecursive(int index){
        return getRecursiveInternal(first, index);
    }
}
