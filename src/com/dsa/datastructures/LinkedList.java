package com.dsa.datastructures;

import com.dsa.datastructures.exceptions.EmptyListException;
import java.util.NoSuchElementException;


/**
 * A doubly linked list. The list is navigable in two directions. Generally the complexity of accessing an element is
 * O(n). Inserting an element into the list is done in O(1) however. To insert elements in O(1), use the iterator.
 *
 * @param <E> The type of elements to store.
 * @author Andreas Palmqvist
 */
public class LinkedList<E> implements List<E> {
    /* A node is a container for an element. A linked list node points to the next- and previous nodes. In this way
     * multiple nodes makes the linked list.
     */
    private class Node {
        E data;
        Node prev;
        Node next;

        Node(E data, Node prev, Node next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }
    }

    /* The amount of elements in this dynamic array.
     */
    private int size;

    /* Head points to the first node of this list and tail points to the last.
     */
    private Node head;
    private Node tail;

    /* Throws IndexOutOfBoundsException if index is not in range [0, size). Makes it possible to securely read from or
     * write to any node in this linked list.
     */
    private void checkIfInsideBounds(int index) {
        if (index < 0 || index >= size) { throw new IndexOutOfBoundsException(); }
    }

    /* Throws IndexOutOfBoundsException if index is not in range [0, size]. Makes it possible to securely put a node in
     * front of any node or after any node.
     */
    private void checkIfInsideBoundsInsert(int index) {
        if (index < 0 || index > size) { throw new IndexOutOfBoundsException(); }
    }

    /* Returns the node at index. Throws IndexOutOfBoundsException if index is not in range [0, size).
     */
    private Node getNode(int index) {
        checkIfInsideBounds(index);

        // Shifting the bits of size to the right by one is equivalent to an integer division by two.
        if (index <= size() >> 1) {
            Node curNode = head;
            for (int i = 0; i < index; i++) {
                curNode = curNode.next;
            }
            return curNode;
        } else {
            Node curNode = tail;
            for (int i = size() - 1; i > index; i--) {
                curNode = curNode.prev;
            }
            return curNode;
        }
    }

    /* Inserts a node containing a specified element before a specified node.
     */
    private void insertBeforeNode(E element, Node node) {
        if (node.prev == null) { insertFront(element); }
        else {
            Node prev = node.prev;
            Node newNode = new Node(element, prev, node);
            node.prev = newNode;
            prev.next = newNode;
        }
        size++;
    }

    /* Removes a specified node from the list.
     */
    private void removeNode(Node node) {
        if (isEmpty()) { throw new EmptyListException(); }

        if (node == head) { removeFront(); }
        else if (node == tail) { removeLast(); }
        else {
            Node prev = node.prev;
            Node next = node.next;

            node.data = null;
            node.next = null;
            node.prev = null;

            prev.next = next;
            next.prev = prev;
            size--;
        }
    }

    /**
     * Inserts an <tt>element</tt> to the front of this list. The operation is done in O(1).
     *
     * @param element the element to insert.
     */
    public void insertFront(E element) {
        Node prevHead = head;
        head = new Node(element, null, prevHead);

        if (prevHead == null) { tail = head; }
        else {prevHead.prev = head;}
        size++;
    }

    /**
     * Inserts <tt>element</tt> to the end of this list. The operation is done in O(1).
     *
     * @param element the element to insert.
     */
    public void insertLast(E element) {
        Node prevTail = tail;
        tail = new Node(element, prevTail, null);

        if (prevTail == null) { head = tail; }
        else {prevTail.next = tail;}
        size++;
    }

    /**
     * Removes the first element of this list. The operation is done in O(1).
     */
    public void removeFront() {
        if (isEmpty()) { throw new EmptyListException(); }

        Node newHead = head.next;
        head.data = null;
        head.next = null;
        head = newHead;

        if (head == null) { tail = null; }
        else { head.prev = null; }
        size--;
    }

    /**
     * Removes the last element of this list. The operation is done in O(1).
     */
    public void removeLast() {
        if (isEmpty()) { throw new EmptyListException(); }

        Node newTail = tail.prev;
        tail.data = null;
        tail.next = null;
        tail = newTail;

        if (tail == null) { head = null; }
        else { tail.next = null; }
        size--;
    }

    /**
     * Inserts an element at <tt>index</tt>. Inserting is generally done in O(n). The index have to be in range
     * [0, size]. Size in this range represents the first empty spot at the end of the list, inserting here is done in
     * O(1). Inserting at index 0 is also done in O(1).
     *
     * <p>Trying to use <tt>insert</tt> outside of the range will throw <tt>IndexOutOfBoundsException</tt>.
     *
     * @param element The new element to insert to the list.
     * @param index The index where to insert <tt>element</tt>.
     * @throws IndexOutOfBoundsException if the specified index is out of bounds and not at end of the dynamic array.
     */
    @Override
    public void insert(E element, int index) {
        checkIfInsideBoundsInsert(index);

        if (index < size()) { insertBeforeNode(element, getNode(index)); }
        else { insertLast(element); }
    }

    /**
     * Change the element at <tt>index</tt> to <tt>element</tt>. This is done in O(n). The index have to be in range
     * [0, size).
     *
     * <p>Trying to call this operation on an empty list will throw <tt>EmptyListException</tt>
     *
     * <p>Trying to use <tt>set</tt> outside of the range will throw <tt>IndexOutOfBoundsException</tt>.
     *
     * @param element The new element to place at <tt>index</tt>.
     * @param index The place in the list to change element.
     * @throws EmptyListException if this list is empty.
     * @throws IndexOutOfBoundsException if the specified index is out of bounds.
     */
    @Override
    public void set(E element, int index) {
        if (isEmpty()) { throw new EmptyListException(); }
        checkIfInsideBounds(index);

        getNode(index).data = element;
    }

    /**
     * Returns the element at <tt>index</tt>. This is done in O(n). The index have to be in range [0, size).
     *
     * <p>Trying to call this operation on an empty list will throw <tt>EmptyListException</tt>
     *
     * <p>Trying to use <tt>get</tt> outside of the range will throw <tt>IndexOutOfBoundsException</tt>.
     *
     * @param index The index of the element to return.
     * @return the element at <tt>index</tt>.
     * @throws EmptyListException if this list is empty.
     * @throws IndexOutOfBoundsException if the specified index is out of bounds.
     */
    @Override
    public E get(int index) {
        if (isEmpty()) { throw new EmptyListException(); }
        checkIfInsideBounds(index);

        return getNode(index).data;
    }

    /**
     * Removes the element att <tt>index</tt>. This is generally done in O(n). The index have to be in range [0, size].
     * Size in this range represents the first empty spot at the end of the list, removing at this index is done in
     * O(1). Removing at index 0 is also done in O(1).
     *
     * <p>Trying to call this operation on an empty list will throw <tt>EmptyListException</tt>
     *
     * <p>Trying to use <tt>remove</tt> outside of the range will throw <tt>IndexOutOfBoundsException</tt>.
     *
     * @param index The index of the element to remove.
     * @throws EmptyListException if this list is empty.
     * @throws IndexOutOfBoundsException if the specified index is out of bounds.
     */
    @Override
    public void remove(int index) {
        if (isEmpty()) { throw new EmptyListException(); }
        checkIfInsideBounds(index);

        removeNode(getNode(index));
    }

    /**
     * Removes all elements of the list. This is done in O(n).
     *
     * <p>Trying to call this operation on an empty list will throw <tt>EmptyListException</tt>
     *
     * @throws EmptyListException if the list is already empty.
     */
    @Override
    public void empty() {
        if (isEmpty()) { throw new EmptyListException(); }

        while (!isEmpty()) {
            removeFront();
        }
    }

    /**
     * Returns the amount of elements in the list.
     *
     * @return The amount of elements in the list.
     */
    @Override
    public int size() { return size; }

    /**
     * Returns an iterator to this list.
     *
     * @return An iterator to this list.
     * @see ListIterator
     */
    @Override
    public ListIterator<E> getIterator() {
        return new ListIterator<E>() {
            private Node currentNode = null;
            private Node next = head;

            @Override
            public E next() {
                if (!hasNext()) { throw new NoSuchElementException(); }

                currentNode = next;
                next = next.next;

                return currentNode.data;
            }

            @Override
            public boolean hasNext() {
                return next != null;
            }

            @Override
            public void insert(E element) {
                if (!hasNext()) { insertLast(element); }
                else { insertBeforeNode(element, next); }
            }

            @Override
            public void remove() {
                if (currentNode == null) { throw new IllegalStateException(); }

                Node toRemove = currentNode;
                currentNode = null;

                removeNode(toRemove);
            }

            @Override
            public void set(E element) {
                if (currentNode == null) { throw new IllegalStateException(); }

                currentNode.data = element;
            }
        };
    }
}
