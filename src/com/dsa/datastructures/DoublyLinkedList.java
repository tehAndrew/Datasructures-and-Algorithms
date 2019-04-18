package com.dsa.datastructures;

import com.dsa.datastructures.exceptions.EmptyListException;

/**
 * A data structure of dynamic size. The list is navigable in two direction as its nodes is linked in both directions.
 * The elements are accessible indirectly from the first- and last node.
 *
 * Inserting elements to the front- and back of the list is done in O(1). Reading and writing to the list generally is
 * done in O(n) although still slightly faster than a regular linked list.
 *
 * @param <E> The type of elements to store.
 * @author Andreas Palmqvist
 */
public class DoublyLinkedList <E> implements List<E> {
    /* A node is a container for an element. A linked list node points to the next node. In this way multiple nodes
     * makes the linked list.
     */
    private class Node {
        E data;
        Node next;
        Node prev;

        Node(E data, Node next, Node prev) {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }
    }

    /* The amount of nodes in this linked list
     */
    private int size;

    /* The first element in this linked list. Serves as the entry point.
     */
    private Node head;

    /* The last element in this linked list. Serves as the entry point.
     */
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

    /* Iterates from either the head- or the tail node depending on whether the index is larger than size / 2 or not.
     * Throws IndexOutOfBoundsException if index is not in range [0, size).
     */
    private Node getNode(int index) {
        checkIfInsideBounds(index);

        Node curNode;

        if (index <= size() >> 1) {
            curNode = head;
            for (int i = 0; i < index; i++) {
                curNode = curNode.next;
            }
        } else {
            curNode = tail;
            for (int i = size() - 1; i > index; i--) {
                curNode = curNode.prev;
            }
        }
        return curNode;
    }

    /* When inserting an element to an empty list, the node associated with that element gets assigned to both the head
     * and tail.
     */
    private void insertFirstElement(E element) {
        head = new Node(element, null, null);
        tail = head;
    }

    /**
     * Constructs an empty doubly linked list.
     */
    public DoublyLinkedList() {
        size = 0;
        head = null;
        tail = null;
    }

    /**
     * Inserts <tt>element</tt> at the beginning of this list. This is done in O(1).
     *
     * @param element The new element to insert to the list.
     */
    public void insertFirst(E element) {
        if (isEmpty()) { insertFirstElement(element); }
        else {
            Node next = head;
            head = new Node(element, next, null);
        }
    }

    /**
     * Inserts <tt>element</tt> to the end of this list. This is done in O(1).
     *
     * @param element The new element to insert to the list.
     */
    public void insertLast(E element) {
        if (isEmpty()) { insertFirstElement(element); }
        else {
            Node prev = tail;
            tail = new Node(element, null, prev);
        }
    }

    /**
     * Removes the first element of this list. This is done in O(1).
     *
     * @throws EmptyListException if this linked list is empty.
     */
    public void removeFirst() {
        if (isEmpty()) { throw new EmptyListException(); }

        Node remove = head;
        head = head.next; // If the head is the sole element it will be null after this operation.
        remove.next = null;
        remove.prev = null;
        size--;

        if (isEmpty()) { tail = null; } // Since head and tail pointed to the same node in this case, tail also has to
                                        // be set to null.
    }

    /**
     * Removes the last element of this list. This is done in O(1).
     *
     * @throws EmptyListException if this linked list is empty.
     */
    public void removeLast() {
        if (isEmpty()) { throw new EmptyListException(); }

        Node remove = tail;
        tail = tail.prev; // If the tail is the sole element it will be null after this operation.
        remove.next = null;
        remove.prev = null;
        size--;

        if (isEmpty()) { head = null; } // Since head and tail pointed to the same node in this case, head also has to
                                        // be set to null.
    }

    /**
     * Inserts an element at <tt>index</tt>. Inserting is done in O(n). The index have to be in range [0, size]. Size in
     * this range represents the first empty spot at the end of the list. Inserting at index 0 or at index size is done
     * in O(1).
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

        if (index == 0) { insertFirst(element); }
        else if (index == size()) { insertLast(element); }
        else {
            Node next = getNode(index);
            Node prev = next.prev;
            Node node = new Node(element, next, prev);

            next.prev = node;
            prev.next = node;
        }
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

        return getNode(index).data;
    }

    /**
     * Removes the element att <tt>index</tt>. This is generally done in O(n). The index have to be in range [0, size].
     * Removing element 0 or at element (size - 1) is done in O(1).
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

        if (index == 0) { removeFirst(); }
        else if (index == size() - 1) { removeLast(); }
        else {
            Node toRemove = getNode(index);
            Node prev = toRemove.prev;
            Node next = toRemove.next;

            prev.next = next;
            next.prev = prev;
            toRemove.next = null;
            toRemove.prev = null;
        }
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
            removeFirst();
        }
    }

    /**
     * Returns the amount of elements in the list.
     *
     * @return the amount of elements in the list
     */
    @Override
    public int size() {
        return size;
    }
}
