package com.github.stephenenright.callcenter.common.collection.queue

import com.github.stephenenright.callcenter.domain.comparator.PhoneCallComparator
import com.github.stephenenright.callcenter.domain.entity.PhoneCall
import spock.lang.Specification


class PriorityQueueRecursiveBinaryHeapSpec extends Specification {

    def 'pop on queue should return the phone call with the highest priority'() {
        given:
        def priority10 = new PhoneCall("John", "Doe", "2021-10-11T11:14:53.9462385Z", "sip",
                "city", "state", "phone", 10)
        def priority20 = new PhoneCall("John", "Doe", "2021-10-11T11:14:53.9462385Z", "sip",
                "city", "state", "phone", 20)
        def priority5 = new PhoneCall("John", "Doe", "2021-10-11T11:14:53.9462385Z", "sip",
                "city", "state", "phone", 5)
        def priority9 = new PhoneCall("John", "Doe", "2021-10-11T11:14:53.9462385Z", "sip",
                "city", "state", "phone", 9)


        def queue = new PriorityQueueRecursiveBinaryHeap(PhoneCallComparator.MAX_COMPARATOR);
        queue.push(priority10)
        queue.push(priority20)
        queue.push(priority5)
        queue.push(priority9)

        when:
        def popped20 = queue.pop()

        then:
        popped20.isPresent()
        popped20.get().priority == 20

        def popped10 = queue.pop();
        popped10.isPresent()
        popped10.get().priority == 10

        def popped9 = queue.pop();
        popped9.isPresent()
        popped9.get().priority == 9

        def popped5 = queue.pop();
        popped5.isPresent()
        popped5.get().priority == 5

        queue.isEmpty() == true
        queue.pop().empty == true  // no items n the queue
    }
}
