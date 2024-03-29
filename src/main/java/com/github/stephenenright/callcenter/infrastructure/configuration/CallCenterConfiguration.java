package com.github.stephenenright.callcenter.infrastructure.configuration;

import com.github.stephenenright.callcenter.common.collection.queue.PriorityQueueBinaryHeap;
import com.github.stephenenright.callcenter.common.collection.queue.PriorityQueue;
import com.github.stephenenright.callcenter.common.collection.queue.PriorityQueueSynchronized;
import com.github.stephenenright.callcenter.domain.comparator.PhoneCallComparator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CallCenterConfiguration {

    /**
     * Creates a priority queue using a max binary heap --  highest priority is max value (sorted descending)
     */
    @Bean
    public PriorityQueue priorityQueueMaxHeap() {
        return new PriorityQueueSynchronized(
                new PriorityQueueBinaryHeap(PhoneCallComparator.MAX_COMPARATOR));
    }
}
