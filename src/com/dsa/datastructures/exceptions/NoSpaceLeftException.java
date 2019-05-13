package com.dsa.datastructures.exceptions;

/**
 * A runtime exception that is thrown when trying to add elements to a bounded Data Structure.
 *
 * @author Andreas Palmqvist
 */
public class NoSpaceLeftException extends RuntimeException {
    public NoSpaceLeftException(String message) { super(message); }
}