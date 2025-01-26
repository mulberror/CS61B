package deque;

public class ArrayDeque<T> {
    private T[] array;

    private int head = 0, tail = 0;
    private int size = 0, capacity = 8;

    @SuppressWarnings("unchecked")
    public ArrayDeque() {
        array = (T[]) new Object[capacity];
    }

    private void resize(int newCapacity) {
        T[] newArray = (T[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newArray[i] = array[(head + i) % capacity];
        }
        array = newArray;
        head = 0;
        tail = size;
        capacity = newCapacity;
    }

    public boolean isEmpty() {
        return head == tail;
    }

    /*
     * for test
     */
    public int capacity() {
        return capacity;
    }

    public int size() {
        return size;
    }

    public void addFirst(T data) {
        head = (head - 1 + capacity) % capacity;
        array[head] = data;

        size++;
        if (size == capacity) {
            resize(capacity * 2);
        }
    }

    public void addLast(T data) {
        array[tail] = data;
        tail = (tail + 1) % capacity;

        size++;
        if (size == capacity) {
            resize(capacity * 2);
        }
    }

    public void printDeque() {
        for (int i = head; i != tail; i = (i + 1) % capacity) {
            System.out.print(array[i] + " ");
        }
    }

    T removeFirst() {
        T data = array[head];
        array[head] = null;
        head = (head + 1) % capacity;

        size--;
        if (size * 2 < capacity && capacity > 8) {
            resize(capacity / 2);
        }
        return data;
    }

    T removeLast() {
        tail = (tail - 1 + capacity) % capacity;
        T data = array[tail];
        array[tail] = null;

        size--;
        if (size * 2 < capacity && capacity > 8) {
            resize(capacity / 2);
        }
        return data;
    }
}
