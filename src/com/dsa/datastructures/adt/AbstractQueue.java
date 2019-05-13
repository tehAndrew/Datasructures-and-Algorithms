package com.dsa.datastructures.adt;

/**
 * An interface representing a queue (FIFO-principle). Duplicate elements are allowed. Null is a valid element to put in
 * a queue.
 *
 * @author Andreas Palmqvist
 * @param <E> The type of elements to store in the queue.
 */
public interface AbstractQueue<E> extends Collection {
    /**
     * Inserts an element at the end of the queue.
     *
     * @param element The new element to insert to this queue.
     */
    void enqueue(E element);

    /**
     * Removes the element at the front of this queue and returns it.
     *

     * @throws com.dsa.datastructures.exceptions.EmptyQueueException if the queue is empty.
     * @return the element in the front of this queue.
     */
    E dequeue();

    /**
     * Returns the element at the front of this queue.
     *
     * @throws com.dsa.datastructures.exceptions.EmptyQueueException if the queue is empty.
     * @return the element in the front of this queue.
     */
    E front();
}
