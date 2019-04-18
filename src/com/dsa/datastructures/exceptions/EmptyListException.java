package com.dsa.datastructures.exceptions;

/**
 * A runtime exception that is thrown when trying to read from or shrink an empty list.
 *
 * @author Andreas Palmqvist
 */
public class EmptyListException extends RuntimeException {
    public EmptyListException() {
        super("Illegal use of empty list.");
    }
}
