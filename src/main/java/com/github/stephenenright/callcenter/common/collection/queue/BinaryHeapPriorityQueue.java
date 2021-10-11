package com.github.stephenenright.callcenter.common.collection.queue;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * A recursive implementation of a priority queue that uses a binary heap.
 */
public class BinaryHeapPriorityQueue<T> implements PriorityQueue<T> {

    //store the elements in the list
    private final List<T> list;

    //used to order by min or max
    private final Comparator<T> comparator;

    public BinaryHeapPriorityQueue(Comparator<T> comparator) {
        this(comparator, 11);
    }

    public BinaryHeapPriorityQueue(Comparator<T> comparator, int initialCapacity) {
        this.comparator = comparator;

        //A list is used here rather than an array to simplify the code -- array resizing ....
        list = new ArrayList<>(initialCapacity);
    }

    @Override
    public Optional<T> peek() {
        if (list.size() > 0) {
            return Optional.of(list.get(0));
        }

        return Optional.empty();
    }

    @Override
    public void push(T element) {
        list.add(element);
        swim(list.size() - 1);
    }

    @Override
    public Optional<T> pop() {
        if (isEmpty()) {
            return Optional.empty();
        }

        final T maxElement = list.get(0);

        if (list.size() > 1) {
            list.set(0, list.get(list.size() - 1));
            sink(0);
        }

        list.remove(list.size() - 1);
        return Optional.of(maxElement);
    }


    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    private void swim(int index) {
        if (index == 0) {
            return;
        }

        int parent = (index - 1) / 2;
        if (comparator.compare(list.get(index), list.get(parent)) > 0) {
            swap(index, parent);
            swim((parent));
        }
    }

    private void sink(int index) {
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

    private void swap(int first, int second) {
        T temp = list.get(first);
        list.set(first, list.get(second));
        list.set(second, temp);
    }
}
