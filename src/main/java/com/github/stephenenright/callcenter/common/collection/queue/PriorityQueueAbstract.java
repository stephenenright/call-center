package com.github.stephenenright.callcenter.common.collection.queue;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

//TODO Refactor Extract Heap Code To A Heap Data Structure
abstract class PriorityQueueAbstract<T> implements PriorityQueue<T> {

    //store the elements in the list
    protected final List<T> list;

    //used to order by min or max
    protected final Comparator<T> comparator;

    public PriorityQueueAbstract(Comparator<T> comparator) {
        this(comparator, 11);
    }

    public PriorityQueueAbstract(Comparator<T> comparator, int initialCapacity) {
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


    protected void swap(int first, int second) {
        T temp = list.get(first);
        list.set(first, list.get(second));
        list.set(second, temp);
    }

    protected abstract void sink(int index);

    protected abstract void swim(int index);
}
