package com.dsa.datastructures.concrete;

import com.dsa.datastructures.adt.AbstractList;
import com.dsa.datastructures.adt.ListIterator;
import com.dsa.datastructures.exceptions.EmptyListException;
import java.util.NoSuchElementException;

/**
 * A data structure representing an array of dynamic size. Data is stored sequentially. Accessing elements has a
 * complexity of O(1) and inserting elements has an amortized complexity of O(n).
 *
 * @param <E> The type of elements to store.
 * @author Andreas Palmqvist
 */
public class DynamicArray<E> implements AbstractList<E> {
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
     * write to any element in this list.
     */
    private void checkIfInsideBounds(int index) {
        if (index < 0 || index >= size) { throw new IndexOutOfBoundsException(); }
    }

    /* Throws IndexOutOfBoundsException if index is not in range [0, size]. Makes it possible to securely insert an
     * element in front of- or after another element.
     */
    private void checkIfInsideBoundsInsert(int index) {
        if (index < 0 || index > size) { throw new IndexOutOfBoundsException(); }
    }

    /**
     * Constructs an empty dynamic array.
     */
    public DynamicArray() {
        capacity = STANDARD_CAP;
        size     = 0;
        array    = new Object[capacity];
    }

    /**
     * Inserts an element at <tt>index</tt>. Inserting has an amortized complexity O(n). The index have to be in range
     * [0, size]. Size in this range represents the first empty spot at the end of the list, inserting here has an
     * has an amortized complexity of O(1);
     *
     * <p>Trying to use <tt>insert</tt> outside of the range [0, size] will throw <tt>IndexOutOfBoundsException</tt>.
     *
     * @param element The new element to insert to the list.
     * @param index The index where to insert <tt>element</tt>.
     * @throws IndexOutOfBoundsException if the specified index is not in range [0, size].
     */
    @Override
    public void insert(E element, int index) {
        checkIfInsideBoundsInsert(index);
        if (size + 1 >= capacity) { expand(); }

        // Make place for the new element by pushing every other element up one index.
        for (int i = size() - 1; i >= index; i--) {
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
     * <p>Trying to use <tt>set</tt> outside of the range [0, size) will throw <tt>IndexOutOfBoundsException</tt>.
     *
     * @param element The new element to place at <tt>index</tt>.
     * @param index The place in the list to change element.
     * @throws EmptyListException if this list is empty.
     * @throws IndexOutOfBoundsException if the specified index is not in range [0, size).
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
     * <p>Trying to use <tt>get</tt> outside of the range [0, size) will throw <tt>IndexOutOfBoundsException</tt>.
     *
     * @param index The index of the element to return.
     * @return the element at <tt>index</tt>.
     * @throws EmptyListException if this list is empty.
     * @throws IndexOutOfBoundsException if the specified index is not in range [0, size).
     */
    @SuppressWarnings("unchecked")
    @Override
    public E get(int index) {
        if (isEmpty()) { throw new EmptyListException(); }
        checkIfInsideBounds(index);

        return (E) array[index];
    }

    /**
     * Removes the element att <tt>index</tt>. This is has an amortized complexity O(n). The index have to be in range
     * [0, size]. Removing element (size - 1) has an amortised complexity O(1).
     *
     * <p>Trying to call this operation on an empty list will throw <tt>EmptyListException</tt>
     *
     * <p>Trying to use <tt>remove</tt> outside of the range [0, size] will throw <tt>IndexOutOfBoundsException</tt>.
     *
     * @param index The index of the element to remove.
     * @throws EmptyListException if this list is empty.
     * @throws IndexOutOfBoundsException if the specified index is not in range [0, size].
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
            private int currentIndex = -1;
            private int nextIndex = 0;

            @Override
            public E next() {
                if (!hasNext()) { throw new NoSuchElementException(); }

                currentIndex = nextIndex;
                nextIndex++;

                return (E) array[currentIndex];
            }

            @Override
            public boolean hasNext() { return nextIndex < size(); }

            @Override
            public void insert(E element) {
                DynamicArray.this.insert(element, nextIndex);
                currentIndex = -1;
                nextIndex++;
            }

            @Override
            public void remove() {
                if (currentIndex == -1) { throw new IllegalStateException(); }

                DynamicArray.this.remove(currentIndex);
                nextIndex = currentIndex;
                currentIndex = -1;
            }

            @Override
            public void set(E element) {
                if (currentIndex == -1) { throw new IllegalStateException(); }

                array[currentIndex] = element;
            }
        };
    }
}