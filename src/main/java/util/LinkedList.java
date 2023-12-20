package util;

import java.util.Iterator;
import java.lang.Object;

public interface LinkedList<T> {
    int size();
    boolean isEmpty();
    Object getHead() throws Exception;
    Object getTail() throws Exception;
    T getFirst() throws Exception;
    T getLast() throws Exception;
    void addFirst(T value);
    void addLast(T value);
    void removeAll() throws Exception;
    void removeFirst() throws Exception;
    void removeLast() throws Exception;
    void insert(int index, T value) throws Exception;
    void remove(int index) throws Exception;
    T get(int index) throws Exception;
    String toString();
    String[] toStringArray();
    Iterator<T> iterator();
}
