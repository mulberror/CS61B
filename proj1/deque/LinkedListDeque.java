package deque;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedListDeque<T> implements Iterable<T>, Deque<T> {
    private static class Node<T> {
        T data;
        Node<T> next;
        Node<T> prev;

        Node(T data, Node<T> prev, Node<T> next) {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }
    }

    private final Node<T> head;
    private final Node<T> tail;
    private int size;

    public LinkedListDeque() {
        head = new Node<T>(null, null, null);
        tail = new Node<T>(null, null, null);
        head.next = tail;
        tail.prev = head;
        size = 0;
    }

    public boolean isEmpty() {
        return head.next == tail && tail.prev == head;
    }

    public int size() {
        return size;
    }

    public void addFirst(T data) {
        Node<T> newNode = new Node<T>(data, head, head.next);
        head.next.prev = newNode;
        head.next = newNode;
        size++;
    }

    public void addLast(T data) {
        Node<T> newNode = new Node<T>(data, tail.prev, tail);
        tail.prev.next = newNode;
        tail.prev = newNode;
        size++;
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        Node<T> deleteNode = head.next;
        head.next = deleteNode.next;
        deleteNode.next.prev = head;
        size--;
        return deleteNode.data;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        Node<T> deleteNode = tail.prev;
        tail.prev = deleteNode.prev;
        deleteNode.prev.next = tail;
        size--;
        return deleteNode.data;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
         Node<T> node = head.next;
         for (int i = 0; i < index && node != tail; i++) {
             node = node.next;
         }
         return node.data;
    }

    private T getRecursiveHelper(Node<T> node, int index) {
        if (index == 0) {
            return node.data;
        }
        return getRecursiveHelper(node.next, index - 1);
    }

    public T getRecursive(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return getRecursiveHelper(head, index);
    }

    public void printDeque() {
        Node<T> current = head.next;
        while (current != tail) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof LinkedListDeque)) {
            return false;
        }
        if (size() != ((LinkedListDeque<?>) o).size()) {
            return false;
        }
        LinkedListDeque<T> other = (LinkedListDeque<T>) o;
        Node<T> p = head.next;
        Node<T> q = other.head.next;
        while (p != tail && q != other.tail) {
            if (p.data != q.data) {
                return false;
            }
            p = p.next;
            q = q.next;
        }
        return true;
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<T> {
        private Node<T> current = head.next;

        @Override
        public boolean hasNext() {
            return current != tail;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T item = current.data;
            current = current.next;
            return item;
        }
    }
}
