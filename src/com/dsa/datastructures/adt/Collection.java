package com.dsa.datastructures.adt;

/**
 * An interface representing a collection of data.
 *
 * @author Andreas Palmqvist
 */
public interface Collection {
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
