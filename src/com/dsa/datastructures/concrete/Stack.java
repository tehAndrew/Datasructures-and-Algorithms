package com.dsa.datastructures.concrete;

import com.dsa.datastructures.adt.AbstractStack;
import com.dsa.datastructures.concrete.DynamicArray;
import com.dsa.datastructures.exceptions.EmptyStackException;

/**
 * A stack (LIFO-principle). Duplicate elements are allowed. Null is a valid element to put in a stack.
 *
 * @author Andreas Palmqvist
 * @param <E> The type of elements to store in the queue.
 */
public class Stack<E> implements AbstractStack<E> {
    /* The underlying dynamic array containing all the elements.
     */
    private DynamicArray<E> stack;

    /**
     * Constructs an empty stack.
     */
    public Stack() {
        stack = new DynamicArray<>();
    }

    /**
     * Inserts an element at the top of the stack. This is done in O(1).
     *
     * @param element The new element to insert to this queue.
     */
    public void push(E element) {
        stack.insert(element, stack.size());
    }

    /**
     * Removes the element at the top of this stack and returns it. This is done in O(1).
     *
     * <p>Trying to call this operation on an empty queue will throw <tt>EmptyQueueException</tt>.
     *
     * @throws com.dsa.datastructures.exceptions.EmptyQueueException if the stack is empty.
     * @return the element at the top this stack.
     */
    public E pop() {
        if (isEmpty()) { throw new EmptyStackException(); }

        E top = top();
        stack.remove(stack.size() - 1);
        return top;
    }

    /**
     * Returns the element at the top of this stack. This is done in O(1).
     *
     * <p>Trying to call this operation on an empty stack will throw <tt>EmptyQueueException</tt>.
     *
     * @throws com.dsa.datastructures.exceptions.EmptyQueueException if the stack is empty.
     * @return the element at the top of this stack.
     */
    public E top() {
        if (isEmpty()) { throw new EmptyStackException(); }

        return stack.get(stack.size() - 1);
    }


    public boolean isEmpty() {
        return stack.size() == 0;
    }

}
