package com.dsa.datastructures.adt;

import com.dsa.datastructures.adt.IterableList;

/**
 * An interface representing an iterator that can be used to iterate through an iterable list.
 *
 * @author Andreas Palmqvist
 * @see IterableList
 * @param <E> The type of elements that is stored in the iterable list.
 */
public interface ListIterator<E> {
    /**
     * Iterate to the next element of the list.
     *
     * <p>Throws <tt>NoSuchElementException</tt> if the list does not have a next element.
     *
     * @return The next element of the list.
     * @throws java.util.NoSuchElementException if the list does not have a next element.
     */
    E next();

    /**
     * Returns whether the list has a next element or not.
     *
     * @return True if the list has a next element.
     */
    boolean hasNext();

    /**
     * Insert <tt>element</tt> before the element that would be returned by <tt>next()</tt>. If there is no next
     * element, <tt>element</tt> will be inserted at the end of the list.
     *
     * <p>The element returned by <tt>next()</tt> is preserved after this operation, the element returned by the
     * previous <tt>next()</tt> call is not. Operations that depend on that element can not be called after an insert.
     *
     * @param element The element to insert to the list.
     */
    void insert(E element);

    /**
     * Removes the element last returned by <tt>next()</tt>. This can only be done if <tt>next()</tt> has been called
     * at least once and if insert has not been called for the current position of the iterator.
     *
     * <p>Throws <tt>IllegalStateException</tt> if <tt>next()</tt> has not been called at least once or if
     * <tt>insert()</tt> has been called for the current position of this iterator.
     *
     * @throws IllegalStateException if <tt>next()</tt> has not been called at least once or if <tt>insert()</tt> has
     *                               been called for the current position of this iterator.
     */
    void remove();

    /**
     * Sets the element last returned by <tt>next()</tt> to <tt>element</tt>. This can only be done if <tt>next()</tt>
     * has been called at least once and if insert has not been called for the current position of the iterator.
     *
     * <p>Throws <tt>IllegalStateException</tt> if <tt>next()</tt> has not been called at least once or if
     * <tt>insert()</tt> has been called for the current position of this iterator.
     *
     * @param element The element to place at the current position of the iterator.
     * @throws IllegalStateException if <tt>next()</tt> has not been called at least once or if <tt>insert()</tt> has
     *                               been called for the current position of this iterator.
     */
    void set(E element);
}
