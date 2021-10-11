package com.github.stephenenright.callcenter.common.collection.queue;

import lombok.extern.slf4j.Slf4j;

import java.util.Comparator;

/**
 * An implementation of a priority queue that uses a binary heap.
 */
@Slf4j
public class PriorityQueueBinaryHeap<T> extends PriorityQueueAbstract<T> {

    public PriorityQueueBinaryHeap(Comparator<T> comparator) {
        this(comparator, 11);
    }

    public PriorityQueueBinaryHeap(Comparator<T> comparator, int initialCapacity) {
        super(comparator,initialCapacity);
    }

    @Override
    protected void swim(int index) {
        if (index == 0) {
            return;
        }

        int parent = (index - 1) / 2;
        while (index > 0 && comparator.compare(list.get(parent), list.get(index)) < 0) {
            swap(parent, index);
            parent = (index - 1) / 2;
            index = parent;
        }
    }

    @Override
    protected void sink(int index) {
        int maxChild;
        while (index < list.size() / 2) {
            final int left = index * 2 + 1;
            final int right = index * 2 + 2;

            if (right < list.size() && comparator.compare(list.get(left), list.get(right)) < 0) {
                maxChild = right;
            } else {
                maxChild = left;
            }

            if (comparator.compare(list.get(index), list.get(maxChild)) >= 0) {
                break;
            }

            swap(index, maxChild);
            index = maxChild;
        }
    }
}
