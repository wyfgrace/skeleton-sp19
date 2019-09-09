public class ArrayDeque<T> {
    private int size;
    private T[] items;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        size = 0;
        items = (T[]) new Object[10];
        nextFirst = 3;
        nextLast = 4;
    }

    public ArrayDeque(ArrayDeque<T> other) {
        int otherLength = other.items.length;
        size = other.size;
        items = (T[]) new Object[otherLength];
        nextFirst = other.nextFirst;
        nextLast = other.nextLast;
        System.arraycopy(other.items, 0, items, 0, otherLength);
    }

    public boolean isEmpty() {
        return size == 0;
    }

    int getActualLength() {
        return items.length;
    }

    public void printDeque() {
        int i = 0;
        while (i < size) {
            System.out.print(get(i) + " ");
            i++;
        }
        System.out.println();
    }

    private int newItemLength(){
        int tempItemsLength;
        if (size<60){
            tempItemsLength = 30 + items.length;
        }else{
            tempItemsLength = size + size/2;
        }
        return tempItemsLength;
    }


    private void arrayExpand() {
        if (nextFirst != nextLast) {
            throw new IllegalStateException("Expand can only be called when nextFirst = nextLast");
        }
        int tempItemsLength= newItemLength();
        T[] tempItems = (T[]) new Object[tempItemsLength];
        int rightHalfLength = items.length-1 - nextFirst;
        int rightHalfStart = tempItemsLength - rightHalfLength;
        int tempNextLast = nextFirst;
        int tempNextFirst = rightHalfStart - 1;
        System.arraycopy(items, 0, tempItems, 0, nextLast);
        System.arraycopy(items, nextFirst + 1, tempItems, rightHalfStart, rightHalfLength);
        items = tempItems;
        nextFirst = tempNextFirst;
        nextLast = tempNextLast;

//        System.out.println("new length after expand. " + items.length);
    }

    public void addFirst(T item) {
        if (nextFirst == nextLast) {
            arrayExpand();
        }
        if (nextFirst > 0) {
            size++;
            items[nextFirst] = item;
            nextFirst--;
        } else if (nextFirst == 0) {
            size++;
            items[nextFirst] = item;
            nextFirst = items.length - 1;
        } else {
            throw new IllegalStateException();
        }
    }

    public void addLast(T item) {
        if (nextFirst == nextLast) {
            arrayExpand();
        }
        if (nextLast < items.length - 1) {
            size++;
            items[nextLast] = item;
            nextLast++;
        } else if (nextLast == items.length - 1) {
            size++;
            items[nextLast] = item;
            nextLast = 0;
        } else {
            throw new IllegalStateException();
        }
    }

    public int size() {
        return size;
    }

    private void arrayShrink(){
        if (items.length >20 && size < items.length/3){
            int tempItemsLength = items.length-items.length/3;
            T[] tempItems = (T[]) new Object[tempItemsLength];
            if (nextLast < nextFirst){
                int rightHalfLength = items.length-1 - nextFirst;
                System.arraycopy(items,nextFirst + 1, tempItems, 0, rightHalfLength);
                System.arraycopy(items,0,tempItems,rightHalfLength,nextLast);
            }else {
                System.arraycopy(items,nextFirst+1,tempItems,0,size);
            }
            nextFirst = tempItemsLength-1;
            nextLast = size;
            items= tempItems;

//            System.out.println("new length after shrink. " + items.length);
        }
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        arrayShrink();

        if (nextFirst < items.length - 1) {
            size--;
            final T tempItem = items[nextFirst + 1];
            items[nextFirst + 1] = null;
            nextFirst++;
            return tempItem;
        } else if (nextFirst == items.length - 1) {
            size--;
            final T tempItem = items[0];
            items[0] = null;
            nextFirst = 0;
            return tempItem;
        } else {
            throw new IllegalStateException();
        }
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        arrayShrink();
        if (nextLast > 0) {
            size--;
            final T tempItem = items[nextLast - 1];
            items[nextLast - 1] = null;
            nextLast--;
            return tempItem;
        } else if (nextLast == 0) {
            size--;
            final T tempItem = items[items.length - 1];
            items[items.length - 1] = null;
            nextLast = items.length - 1;
            return tempItem;
        } else {
            throw new IllegalStateException();
        }
    }

    public T get(int index) {
        if (index >= size || index < 0) {
            return null;
        }
        final int position = (index + nextFirst + 1) % items.length;
        return items[position];
    }
}
