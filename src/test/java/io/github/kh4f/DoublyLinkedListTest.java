package io.github.kh4f;

import org.junit.jupiter.api.*;
import java.util.LinkedList;
import static org.junit.jupiter.api.Assertions.*;

public class DoublyLinkedListTest {
    private DoublyLinkedList<String> myList;
    LinkedList<String> origList;

    @BeforeEach
    public void setUp() {
        myList = new DoublyLinkedList<>();
        myList.addLast("1");
        myList.addLast("2");
        myList.addLast("3");

        origList = new LinkedList<>();
        origList.addLast("1");
        origList.addLast("2");
        origList.addLast("3");
    }

    @AfterEach
    public void afterEach() {
        assertArrayEquals(origList.toArray(), myList.toStringArray());
    }

    @Test
    public void testAddFirst() {
        myList.addFirst("5");
        origList.addFirst("5");
    }

    @Test
    public void testAddLast() {
        myList.addLast("5");
        origList.addLast("5");
    }

    @Test
    public void testRemoveFirst() throws DoublyLinkedList.DoublyLinkedListException {
        myList.removeFirst();
        origList.removeFirst();
    }

    @Test
    public void testRemoveLast() throws DoublyLinkedList.DoublyLinkedListException {
        myList.removeLast();
        origList.removeLast();
    }

    @Test
    public void testRemoveAll() throws DoublyLinkedList.DoublyLinkedListException {
        myList.clear();
        origList.clear();
    }

    @Test
    public void testGet() throws DoublyLinkedList.DoublyLinkedListException {
        myList.addLast(myList.get(1));
        origList.addLast(origList.get(1));
    }

    @Test
    public void testInsert() throws DoublyLinkedList.DoublyLinkedListException {
        myList.insert(1, "5");
        origList.add(1, "5");
    }

    @Test
    public void testRemove() throws DoublyLinkedList.DoublyLinkedListException {
        myList.remove(1);
        origList.remove(1);
    }
}
