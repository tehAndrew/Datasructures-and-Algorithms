package com.dsa.datastructures.exceptions;

/**
 * A runtime exception that is thrown when trying to read from or pop from an empty stack.
 *
 * @author Andreas Palmqvist
 */
public class EmptyStackException extends RuntimeException {
    public EmptyStackException() {
        super("Illegal use of empty stack.");
    }
}
