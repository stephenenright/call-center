package com.github.stephenenright.callcenter.common.collection.queue;

import lombok.extern.slf4j.Slf4j;

import java.util.Optional;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * A Priority Queue decorator that wraps a priority queue to provide basic synchronization (could be better, quick and dirty .....)
 * to prevent race conditions between the producer and consumer
 */
@Slf4j
public class PriorityQueueSynchronized<T> implements PriorityQueue<T> {

    private PriorityQueue<T> queue;

    //quick solution to prevent race conditions between producer and consumers of the priority queue
    private ReadWriteLock lock;

    public PriorityQueueSynchronized(PriorityQueue<T> queue) {
        this.queue = queue;
        this.lock = new ReentrantReadWriteLock();
    }

    @Override
    public void push(T element) {
        try {
            lock.writeLock().lock();
            queue.push(element);
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public Optional<T> pop() {
        try {
            lock.writeLock().lock();
            return queue.pop();
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public Optional<T> peek() {
        try {
            lock.readLock().lock();
            return queue.peek();
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public boolean isEmpty() {
        try {
            lock.readLock().lock();
            return queue.isEmpty();
        } finally {
            lock.readLock().unlock();
        }
    }
}
