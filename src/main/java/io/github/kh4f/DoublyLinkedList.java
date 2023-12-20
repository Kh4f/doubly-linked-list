package io.github.kh4f;

import util.LinkedList;
import java.util.Arrays;
import java.util.Iterator;

public class DoublyLinkedList<T> implements Iterable<T>, LinkedList<T> {

    public static class DoublyLinkedListException extends Exception {
        public DoublyLinkedListException(String message) {
            super(message);
        }
    }

    private Node<T> head;
    private Node<T> tail;
    private int size;

    public static class Node<T> {
        private T value;
        private Node<T> prev;
        private Node<T> next;

        public Node(T value) {
            this(value, null, null);
        }
        public Node(T value, Node<T> prev, Node<T> next) {
            this.value = value;
            this.prev = prev;
            this.next = next;
        }

        public T getValue() {
            return value;
        }
        public void setValue(T value) {
            this.value = value;
        }
        public Node<T> getPrev() {
            return prev;
        }
        public Node<T> getNext() {
            return next;
        }
    }

    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void checkEmpty() throws DoublyLinkedListException {
        if (isEmpty()) throw new DoublyLinkedListException("Empty list");
    }

    public Node<T> getHead() throws  DoublyLinkedListException {
        checkEmpty();
        return head;
    }

    public Node<T> getTail() throws DoublyLinkedListException {
        checkEmpty();
        return tail;
    }

    public T getFirst() throws DoublyLinkedListException {
        checkEmpty();
        return head.value;
    }

    public T getLast() throws DoublyLinkedListException {
        checkEmpty();
        return tail.value;
    }

    public void addFirst(T value) {
        Node<T> newNode = new Node<>(value);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        size++;
    }

    public void addLast(T value) {
        if (isEmpty()) {
            addFirst(value);
            return;
        }
        tail.next = new Node<>(value, tail, null);
        tail = tail.next;
        size++;
    }

    public void removeFirst() throws DoublyLinkedListException {
        checkEmpty();
        head = head.next;
        if (head != null) {
            head.prev = null;
        } else {
            tail = null;
        }
        size--;
    }

    public void removeLast() throws DoublyLinkedListException {
        checkEmpty();
        tail = tail.prev;
        if (tail != null) {
            tail.next = null;
        } else {
            head = null;
        }
        size--;
    }

    public void removeAll() throws DoublyLinkedListException {
        while (!isEmpty()) {
            removeLast();
        }
    }

    public void insert(int index, T value) throws DoublyLinkedListException {
        if (index == 0) {
            addFirst(value);
            return;
        } else if (index == size) {
            addLast(value);
            return;
        }
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Incorrect index");
        }
        Node<T> newNode = new Node<>(value);
        Node<T> prev = getNode(index - 1);
        Node<T> next =  prev.next;

        prev.next = newNode;
        newNode.prev = prev;

        next.prev = newNode;
        newNode.next = next;

        size++;
    }

    public void remove(int index) throws DoublyLinkedListException {
        checkEmpty();

        if (index == 0) {
            removeFirst();
            return;
        } else if (index == (size() - 1)) {
            removeLast();
            return;
        }
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Incorrect index");
        }

        Node<T> curr = getNode(index);
        Node<T> prev = curr.prev;
        Node<T> next = curr.next;
        prev.next = next;
        next.prev = prev;

        size--;
    }

    private Node<T> getNode(int index) throws DoublyLinkedListException {
        checkEmpty();

        if (index < 0) {
            throw new IndexOutOfBoundsException("Index must be grater or equal 0");
        }
        if (index >= size) {
            throw new IndexOutOfBoundsException(String.format("Index must be below %d", size));
        }
        int counter = 0;
        Node<T> current = head;
        while (counter++ < index) {
            current = current.next;
        }

        return current;
    }

    public T get(int index) throws DoublyLinkedListException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Incorrect index");
        }
        return getNode(index).value;
    }

    public String toString() {
        return Arrays.toString(toStringArray());
    }

    public String[] toStringArray() {
        String[] newAr = new String[size()];

        Node<T> currNode = head;
        int index = 0;
        while (currNode != null) {
            newAr[index] = String.valueOf(currNode.getValue());
            currNode = currNode.getNext();
            index += 1;
        }

        return newAr;
    }

    public Iterator<T> iterator() {
        class DoublyLinkedListIterator implements Iterator<T> {
            Node<T> curr = head;

            @Override
            public boolean hasNext() {
                return curr != null;
            }

            @Override
            public T next() {
                T value = curr.value;
                curr = curr.next;
                return value;
            }
        }

        return new DoublyLinkedListIterator();
    }
}