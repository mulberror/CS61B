package deque;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayDeque<T> implements Iterable<T>, Deque<T> {
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

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T data = array[head];
        array[head] = null;
        head = (head + 1) % capacity;

        size--;
        if (size * 2 < capacity && capacity > 8) {
            resize(capacity / 2);
        }
        return data;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        tail = (tail - 1 + capacity) % capacity;
        T data = array[tail];
        array[tail] = null;

        size--;
        if (size * 2 < capacity && capacity > 8) {
            resize(capacity / 2);
        }
        return data;
    }

    public T get(int index) {
        return array[(head + index) % capacity];
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Deque)) {
            return false;
        }
        if (size() != ((Deque<?>) o).size()) {
            return false;
        }
        for (int i = 0; i < size(); i++) { // Time Complexity high
            T left = (T) ((Deque<?>) o).get(i);
            T right = get(i);
            if (left == null && right == null) {
                continue;
            }
            if (left == null || right == null) {
                return false;
            }
            if (!(left.equals(right))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    private class ArrayDequeIterator implements Iterator<T> {
        private int current = head;

        @Override
        public boolean hasNext() {
            return current != tail;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T item = array[current];
            current = (current + 1) % capacity;
            return item;
        }
    }
}
