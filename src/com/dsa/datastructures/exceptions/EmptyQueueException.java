package com.dsa.datastructures.exceptions;

/**
 * A runtime exception that is thrown when trying to read from or dequeue from an empty queue.
 *
 * @author Andreas Palmqvist
 */
public class EmptyQueueException extends RuntimeException {
    public EmptyQueueException() {
        super("Illegal use of empty queue.");
    }
}