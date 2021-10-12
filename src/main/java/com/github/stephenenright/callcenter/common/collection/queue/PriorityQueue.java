package com.github.stephenenright.callcenter.common.collection.queue;

import java.util.Optional;

public interface PriorityQueue<T> {

    /**
     * Adds an item to the queue
     * @param element the element to add
     */
    void push(T element);

    /**
     * Returns and removes the element with the highest priority from the queue
     * @return  the element, or an empty optional if the queue is empty
     */
    Optional<T> pop();

    /**
     * Returns the element with the highest priority without removing it
     * @return  the element with the highest priority, or an empty optional if the queue is empty
     */
    Optional<T> peek();

    /**
     * determines if the queue is empty
     * @return true if queue is empty, otherwise false
     */
    boolean isEmpty();

}
