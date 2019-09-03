public class ArrayDeque<T> {
    private int size;
    private T[] items;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        size = 0;
        items = (T[]) new Object[8];
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

    public void printDeque() {
        int i = 0;
        while (i < size) {
            System.out.print(get(i) + " ");
            i++;
        }
        System.out.println();
    }

    private void arrayExpand() {
        if (nextFirst != nextLast) {
            throw new IllegalStateException("Expand can only be called when nextFirst = nextLast");
        }
        T[] tempItems = (T[]) new Object[2 * items.length];
        int tempItemsLength = 2 * items.length;
        int rightHalfLength = size - nextFirst;
        int rightHalfStart = tempItemsLength - rightHalfLength;
        int tempNextFirst = nextFirst;
        int tempNextLast = rightHalfStart - 1;
        System.arraycopy(items, 0, tempItems, 0, nextFirst);
        System.arraycopy(items, nextFirst + 1, tempItems, rightHalfStart, rightHalfLength);
        items = tempItems;
        nextFirst = tempNextFirst;
        nextLast = tempNextLast;
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

    public T removeFirst() {
        if (size == 0) {
            return null;
        }

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
            throw new IllegalStateException();
        }
        final int position = (index + nextFirst + 1) % items.length;
        return items[position];
    }
}
