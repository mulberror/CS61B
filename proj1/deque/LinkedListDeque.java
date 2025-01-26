package deque;

import java.util.Iterator;

public class LinkedListDeque<T> {
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
        if (!isEmpty()) {
            Node<T> deleteNode = head.next;
            head.next = deleteNode.next;
            deleteNode.next.prev = head;
            size--;
            return deleteNode.data;
        } else {
            return null;
        }
    }

    public T removeLast() {
        if (!isEmpty()) {
            Node<T> deleteNode = tail.prev;
            tail.prev = deleteNode.prev;
            deleteNode.prev.next = tail;
            size--;
            return deleteNode.data;
        } else {
            return null;
        }
    }

    public T get(int index) {
         Node<T> node = head.next;
         for (int i = 0; i < index && node != tail; i++) {
             node = node.next;
         }
         return node.data;
    }

    public void printDeque() {
        Node<T> current = head.next;
        while (current != tail) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }
}
