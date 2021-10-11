package com.github.stephenenright.callcenter.common.collection.queue;

import java.util.Optional;

public interface PriorityQueue<T> {

    void push(T element);

    Optional<T> pop();

    Optional<T> peek();

    boolean isEmpty();

}
