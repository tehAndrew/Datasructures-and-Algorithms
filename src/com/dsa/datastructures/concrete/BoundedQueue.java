package com.dsa.datastructures.concrete;

import com.dsa.datastructures.adt.AbstractQueue;
import com.dsa.datastructures.exceptions.EmptyQueueException;
import com.dsa.datastructures.exceptions.NoSpaceLeftException;

/**
 * A queue of a set capacity. Duplicate elements are allowed. Null is a valid element to put in a queue.
 *
 * @author Andreas Palmqvist
 * @param <E> The type of elements to store in the queue.
 */
public class BoundedQueue<E> implements AbstractQueue<E> {
    /* The underlying datastructure of this bounded queue is a circular array.
     */

    /* This is the capacity of this bounded queue gets assigned of no other capacity is defined.
     */
    private static final int STANDARD_CAP = 16;

    /* Error message for the NoSpaceLeftException.
     */
    private static final String NO_SPACE_LEFT_MSG = "Illegal use of full queue.";

    /* The space available in this bounded queue.
     */
    private int capacity;

    /* The amount of elements in this bounded queue.
     */
    private int size;

    /* The index of the first element.
     */
    private int frontIndex;

    /* The index of the last element.
     */
    private int backIndex;

    /* The underlying circular array containing all elements.
     */
    private Object[] queue;

    /**
     * Constructs an empty bounded queue with a capacity of <tt>capacity</tt>.
     *
     * @param capacity the capacity of the new bounded queue.
     */
    public BoundedQueue(int capacity) {
        this.capacity = capacity;
        queue = new Object[capacity];
    }

    /**
     * Constructs an empty bounded queue with a capacity of 16.
     */
    public BoundedQueue() { this(STANDARD_CAP); }

    /**
     * Inserts an element at the end of the queue. This is done in O(1).
     *
     * <p>If the bounded queue is full <tt>NoSpaceLeftException</tt> will be thrown.
     *
     * @param element The new element to insert to this queue.
     */
    @Override
    public void enqueue(E element) {
        if (size + 1 >= capacity) { throw new NoSpaceLeftException(NO_SPACE_LEFT_MSG); }

        queue[backIndex++] = element;
        if (backIndex >= capacity) { backIndex = 0; }

        size++;
    }

    /**
     * Removes the element at the front of this queue and returns it. This is done in O(1).
     *
     * <p>Trying to call this operation on an empty queue will throw <tt>EmptyQueueException</tt>.
     *
     * @throws com.dsa.datastructures.exceptions.EmptyQueueException if the queue is empty.
     * @return the element in the front of this queue.
     */
    @Override
    public E dequeue() {
        if (isEmpty()) { throw new EmptyQueueException(); }

        E front = front();
        queue[frontIndex++] = null;
        if (frontIndex >= capacity) { frontIndex = 0; }

        size--;

        return front;
    }

    /**
     * Returns the element at the front of this queue. This is done in O(1).
     *
     * <p>Trying to call this operation on an empty queue will throw <tt>EmptyQueueException</tt>.
     *
     * @throws com.dsa.datastructures.exceptions.EmptyQueueException if the queue is empty.
     * @return the element in the front of this queue.
     */
    @Override
    public E front() {
        if (isEmpty()) { throw new EmptyQueueException(); }

        return (E) queue[frontIndex];
    }

    /**
     * Returns the amount of elements in the queue.
     *
     * @return The amount of elements in the queue.
     */
    @Override
    public int size() { return size; }
}
