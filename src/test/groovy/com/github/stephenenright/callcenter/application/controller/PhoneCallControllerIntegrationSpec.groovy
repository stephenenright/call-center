package com.github.stephenenright.callcenter.application.controller

import com.github.stephenenright.callcenter.common.collection.queue.PriorityQueue
import com.github.stephenenright.callcenter.common.collection.queue.PriorityQueueBinaryHeap
import com.github.stephenenright.callcenter.domain.comparator.PhoneCallComparator
import com.github.stephenenright.callcenter.domain.entity.PhoneCall
import org.junit.runner.RunWith
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.test.context.junit4.SpringRunner
import spock.lang.Specification

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
class PhoneCallControllerIntegrationSpec extends Specification {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @SpringBean
    private PriorityQueue<PhoneCall> priorityQueueMaxHeap=  new PriorityQueueBinaryHeap<>(PhoneCallComparator.MAX_COMPARATOR)

    void setup() {
        def priority10 = new PhoneCall("John", "Doe", "2021-10-11T11:14:53.9462385Z", "sip",
                "city", "state", "phone", 10)
        def priority20 = new PhoneCall("John", "Doe", "2021-10-11T11:14:53.9462385Z", "sip",
                "city", "state", "phone", 20)
        def priority5 = new PhoneCall("John", "Doe", "2021-10-11T11:14:53.9462385Z", "sip",
                "city", "state", "phone", 5)
        def priority9 = new PhoneCall("John", "Doe", "2021-10-11T11:14:53.9462385Z", "sip",
                "city", "state", "phone", 9)

        priorityQueueMaxHeap.push(priority10)
        priorityQueueMaxHeap.push(priority20)
        priorityQueueMaxHeap.push(priority5)
        priorityQueueMaxHeap.push(priority9)
    }

    void "element is popped"() {
        when:
            def response = restTemplate.exchange("http://localhost:${port}/api/phone-calls/pop",
                HttpMethod.PUT, null, Map.class)
        then:
            response != null
            response.statusCode == HttpStatus.OK
            response.hasBody()
            response.body != null
            response.body.get("priority") == 20

        when: "Pop is called a second time"
            response = restTemplate.exchange("http://localhost:${port}/api/phone-calls/pop",
                HttpMethod.PUT, null, Map.class)

        then:
            response != null
            response.statusCode == HttpStatus.OK
            response.hasBody()
            response.body != null
            response.body.get("priority") == 10
    }
}
