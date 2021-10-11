package com.github.stephenenright.callcenter.common.collection.queue;

import java.util.Comparator;

/**
 * A recursive implementation of a priority queue that uses a binary heap.
 */
public class PriorityQueueRecursiveBinaryHeap<T> extends PriorityQueueAbstract<T> {

    public PriorityQueueRecursiveBinaryHeap(Comparator<T> comparator) {
        this(comparator, 11);
    }

    public PriorityQueueRecursiveBinaryHeap(Comparator<T> comparator, int initialCapacity) {
        super(comparator,initialCapacity);
    }

    @Override
    protected void swim(int index) {
        if (index == 0) {
            return;
        }

        int parent = (index - 1) / 2;
        if (comparator.compare(list.get(index), list.get(parent)) > 0) {
            swap(index, parent);
            swim((parent));
        }
    }


    @Override
    protected void sink(int index) {
        final int left = index * 2 + 1;
        final int right = index * 2 + 2;

        if (left >= list.size()) {
            return;
        }

        int maxChild = left;

        if (right < list.size() && comparator.compare(list.get(left), list.get(right)) < 0) {
            maxChild = right;
        } else {
            maxChild = left;
        }


        if (comparator.compare(list.get(index), list.get(maxChild)) < 0) {
            swap(index, maxChild);
            sink(maxChild);
        }
    }
}
