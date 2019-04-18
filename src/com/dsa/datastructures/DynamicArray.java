package com.dsa.datastructures;

import com.dsa.datastructures.exceptions.EmptyListException;

/**
 * <p>A data structure of dynamic size. Data is stored sequentially. Accessing elements has a complexity of O(1) and
 * inserting elements has a complexity of O(n).</p>
 *
 * @param <E> The type of elements to store.
 * @author Andreas Palmqvist
 */
public class DynamicArray<E> implements List<E> {
    /* This is the capacity of this dynamic array at the start.
     */
    private static final int STANDARD_CAP = 16;

    /* The space available in this dynamic array.
     */
    private int capacity;

    /* The amount of elements in this dynamic array.
     */
    private int size;

    /* The underlying array containing all elements.
     */
    private Object[] array;

    /* Expands the array by a factor of two. This means the array doesn't need to get copied as often.
     */
    private void expand() {
        capacity = capacity << 1; // Shifting the bits to the left is a more efficient of multiplying by 2.
        Object[] newArr = new Object[capacity];

        for (int i = 0; i < size; i++) {
            newArr[i] = array[i];
        }

        array = newArr;
    }

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

    /* Constructs a dynamic array with a specified capacity.
     */
    private DynamicArray(int startCap) {
        capacity = startCap;
        size     = 0;
        array    = new Object[capacity];
    }

    /**
     * Constructs an empty dynamic array.
     */
    public DynamicArray() {
        this(STANDARD_CAP);
    }

    /**
     * Inserts an element at <tt>index</tt>. Inserting is generally done in O(n), when the array needs to expand
     * however the full operation is done in O(n). The index have to be in range [0, size]. Size in this range
     * represents the first empty spot at the end of the list, inserting here is done in O(1) if the list does not have
     * to expand.
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
        if (size + 1 >= capacity) { expand(); }

        // Make place for the new element by pushing every other element up one index.
        for (int i = index; i < size; i++) {
            array[i + 1] = array[i];
        }

        array[index] = element;
        size++;
    }

    /**
     * Change the element at <tt>index</tt> to <tt>element</tt>. This is done in O(1). The index have to be in range
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

        array[index] = element;
    }

    /**
     * Returns the element at <tt>index</tt>. This is done in O(1). The index have to be in range [0, size).
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
    @SuppressWarnings("unchecked")
    @Override
    public E get(int index) {
        if (isEmpty()) { throw new EmptyListException(); }
        checkIfInsideBounds(index);

        return (E) array[index];
    }

    /**
     * Removes the element att <tt>index</tt>. This is generally done in O(n). The index have to be in range [0, size].
     * Removing element (size - 1) is done in O(1).
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

        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        array[size--] = null;
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
        if (!isEmpty()) {throw new EmptyListException(); }

        while (!isEmpty()) {
            remove(size - 1);
        }
        capacity = STANDARD_CAP;
        array = new Object[capacity];
    }

    /**
     * Returns the amount of elements in the list.
     *
     * @return the amount of elements in the list.
     */
    @Override
    public int size() { return size; }
}