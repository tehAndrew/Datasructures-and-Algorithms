package com.dsa.datastructures;

/**
 * An interface representing a sequential collection of elements. Duplicate elements are allowed. Null is a valid
 * element to put in a list.
 *
 * @author Andreas Palmqvist
 * @param <E> The type of elements to store in the list.
 */
public interface List <E> extends IterableList<E> {
    /**
     * Inserts an element at <tt>index</tt>.
     *
     * @param element The new element to insert to this list.
     * @param index The index where to insert <tt>element</tt>.
     * @throws IndexOutOfBoundsException if the specified index is out of bounds.
     */
    void insert(E element, int index);

    /**
     * Change the element at <tt>index</tt> to <tt>element</tt>.
     *
     * @param element The new element to place at <tt>index</tt>.
     * @param index The place in the list to change element.
     * @throws com.dsa.datastructures.exceptions.EmptyListException if this linked list is empty.
     * @throws IndexOutOfBoundsException if the specified index is out of bounds.
     */
    void set(E element, int index);

    /**
     * Returns the element at <tt>index</tt>.
     *
     * @param index The index of the element to return.
     * @return The element at <tt>index</tt>.
     * @throws com.dsa.datastructures.exceptions.EmptyListException if the list is empty.
     * @throws IndexOutOfBoundsException if the specified index is out of bounds.
     */
    E get(int index);

    /**
     * Removes the element att <tt>index</tt>.
     *
     * @param index The index of the element to remove.
     * @throws com.dsa.datastructures.exceptions.EmptyListException if the list is empty.
     * @throws IndexOutOfBoundsException if the specified index is out of bounds.
     */
    void remove(int index);

    /**
     * Removes all elements of the list.
     *
     * @throws com.dsa.datastructures.exceptions.EmptyListException if the list is already empty.
     */
    void empty();

    /**
     * Returns the amount of elements in the list.
     *
     * @return The amount of elements in the list.
     */
    int size();

    /**
     * Checks whether the list is empty or not.
     *
     * @return True if the list is empty.
     */
    default boolean isEmpty() { return size() == 0; }
}
