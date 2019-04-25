package com.dsa.datastructures;

/**
 * An interface representing a list that can be iterated through.
 *
 * @author Andreas Palmqvist
 * @param <E> The type of elements to store in the list.
 */
public interface IterableList<E> {
    /**
     * Returns an iterator to this list.
     *
     * @return An iterator to this list.
     * @see ListIterator
     */
    ListIterator<E> getIterator();
}
