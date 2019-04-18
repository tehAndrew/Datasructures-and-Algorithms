package com.dsa.util;

/**
 * A functional interface that compares two objects.
 *
 * @param <T> The type of objects to store
 * @author Andreas Palmqvist
 */
public interface Comparator <T> {
    boolean compare(T obj1, T obj2);
}
