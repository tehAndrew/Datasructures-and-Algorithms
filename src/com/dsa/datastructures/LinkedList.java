package com.dsa.datastructures;

import com.dsa.datastructures.exceptions.EmptyListException;

/**
 * A data structure of dynamic size. The list is navigable in one direction as its nodes is linked in only one
 * direction. The elements is accessible indirectly from the first node.
 *
 * Inserting elements to the front of the list is done in O(1). Reading and writing to the list generally is done in
 * O(n).
 *
 * @param <E> The type of elements to store.
 * @author Andreas Palmqvist
 */
public class LinkedList <E> implements List<E> {
    /* A node is a container for an element. A linked list node points to the next node. In this way multiple nodes
     * makes the linked list.
     */
    private class Node {
        E data;
        Node next;

        Node(E data, Node next) {
            this.data = data;
            this.next = null;
        }
    }

    /* The amount of nodes in this linked list
     */
    private int size;

    /* The first element in this linked list. Serves as the entry point.
     */
    private Node head;

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

    /* Iterates from the head node to the node at index. Throws IndexOutOfBoundsException if index is not in range
     * [0, size).
     */
    private Node getNode(int index) {
        checkIfInsideBounds(index);

        Node curNode = head;
        for (int i = 1; i < index; i++) {
            curNode = curNode.next;
        }
        return curNode;
    }

    /**
     * Constructs an empty linked list.
     */
    public LinkedList() {
        size = 0;
        head = null;
    }

    /**
     * Inserts <tt>element</tt> at the beginning of this list. This is done in O(1).
     *
     * @param element The new element to insert to the list.
     */
    public void insertFirst(E element) {
        Node next = head;
        head = new Node(element, next);
        size++;
    }

    /**
     * Removes the first element of this list. This is done in O(1).
     *
     * @throws EmptyListException if this linked list is empty.
     */
    public void removeFirst() {
        if (isEmpty()) { throw new EmptyListException(); }

        Node remove = head;
        head = head.next;
        remove.next = null;
        size--;
    }

    /**
     * Inserts an element at <tt>index</tt>. Inserting is done in O(n). The index have to be in range [0, size]. Size in
     * this range represents the first empty spot at the end of the list. Inserting at index 0 is done in O(1).
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
        else {
            Node prev = getNode(index - 1);
            Node next = prev.next;

            prev.next = new Node(element, next);
            size++;
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
     * Removing element 0 is done in O(1).
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
        else {
            Node prev = getNode(index - 1);
            Node toRemove = prev.next;

            prev.next = toRemove.next;
            toRemove.next = null;
            size--;
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