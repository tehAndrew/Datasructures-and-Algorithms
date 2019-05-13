package com.dsa.datastructures.adt;

/**
 * An interface representing a stack (LIFO-principle). Duplicate elements are allowed. Null is a valid element to put in
 * a stack.
 *
 * @author Andreas Palmqvist
 * @param <E> The type of elements to store in the stack.
 */
public interface AbstractStack<E> {
    /**
     * Inserts an element at the top of the stack.
     *
     * @param element The new element to insert to this stack.
     */
    void push(E element);

    /**
     * Removes the element at the top of this stack and returns it.
     *
     * @throws com.dsa.datastructures.exceptions.EmptyStackException if the stack is empty.
     * @return the element at the top of this stack.
     */
    E pop();

    /**
     * Returns the element at the top of this stack.
     *
     * @throws com.dsa.datastructures.exceptions.EmptyStackException if the stack is empty.
     * @return the element at the top of this stack.
     */
    E top();

    boolean isEmpty();
}
