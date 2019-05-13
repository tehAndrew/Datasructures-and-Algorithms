package com.dsa.datastructures.concrete;

import com.dsa.datastructures.adt.AbstractQueue;
import com.dsa.datastructures.concrete.BoundedQueue;
import com.dsa.datastructures.exceptions.NoSpaceLeftException;

/**
 * A queue (FIFO-principle). Duplicate elements are allowed. Null is a valid element to put in a queue.
 *
 * @author Andreas Palmqvist
 * @param <E> The type of elements to store in the queue.
 */
public class Queue<E> implements AbstractQueue<E> {
    /* This is the capacity of this dynamic array at the start.
     */
    private static final int START_CAPACITY = 16;

    /* The space available in this queue.
     */
    private int capacity;

    /* The underlying bounded queue containing all elements.
     */
    private BoundedQueue<E> queue;

    /* Puts all elements in a new larger bounded queue.
     */
    private void expand() {
        capacity *= 2;
        BoundedQueue<E> newQueue = new BoundedQueue<>(capacity);

        while (!queue.isEmpty()) {
            newQueue.enqueue(queue.dequeue());
        }

        queue = newQueue;
    }

    /**
     * Constructs an empty queue.
     */
    public Queue() {
        capacity = START_CAPACITY;
        queue = new BoundedQueue<>(capacity);
    }

    /**
     * Inserts an element at the end of the queue. This is done in O(1).
     *
     * @param element The new element to insert to this queue.
     */
    @Override
    public void enqueue(E element) {
        try {
            queue.enqueue(element);
        } catch (NoSpaceLeftException ex) {
            expand();
            queue.enqueue(element);
        }
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
    public E dequeue() { return queue.dequeue(); }

    /**
     * Returns the element at the front of this queue. This is done in O(1).
     *
     * <p>Trying to call this operation on an empty queue will throw <tt>EmptyQueueException</tt>.
     *
     * @throws com.dsa.datastructures.exceptions.EmptyQueueException if the queue is empty.
     * @return the element in the front of this queue.
     */
    @Override
    public E front() { return queue.front(); }

    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }
}
