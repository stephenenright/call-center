package com.github.stephenenright.callcenter.domain.repository.impl;

import com.github.stephenenright.callcenter.common.collection.queue.PriorityQueue;
import com.github.stephenenright.callcenter.domain.entity.PhoneCall;
import com.github.stephenenright.callcenter.domain.repository.PhoneCallRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Stores the phone calls in memory using a priority queue
 */
@Repository
public class PhoneCallMemoryRepositoryImpl implements PhoneCallRepository {

    private final PriorityQueue queue;

    public PhoneCallMemoryRepositoryImpl(PriorityQueue queue) {
        this.queue = queue;
    }

    @Override
    public PhoneCall push(PhoneCall phoneCall) {
        queue.push(phoneCall);
        return phoneCall;
    }

    @Override
    public Optional<PhoneCall> peek() {
        return queue.peek();
    }

    @Override
    public Optional<PhoneCall> pop() {
        return queue.pop();
    }
}
