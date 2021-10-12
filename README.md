# Call Center

A simple project that connects to a third party web socket to fetch incoming phone call information and stores them into 
a priority queue. A Rest API is provided to pop the phone call with the highest priority from the queue.

## Notes
* A Phone call is validated using a strict validator to ensure all fields are set. Maybe we could use an anonymous phone call
  validator to allow anonymous phone calls, where a user does not leave name, address etc
* Todo comments are markers for future enhancement
* Highest priority is priority with max value. This can be changed by reversing the comparator

## Running
The app can be started from an IDE such as: intellij by executing the class `CallCenterApplication` or from the command
line

```
./gradlew bootRun
```

The endpoint `http://localhost:8081/api/phone-calls/pop` can be used to pop an item from the queue

![Rest Call](./rest-call.png?raw=true)



## Tests

Tests are written in groovy and can be executed from an IDE such as: intellij, or from the command line:

```
./gradlew test
```

## Configuration

Configuration is contained in /src/main/resources/application.yml

### Third Party Service
The third party service by default is configured to use the following service by default:

```
callCenter:
  service:
    phoneAnswer:
      scheme: ws
      host: localhost
      port: 7777
```

## Improvements
* A reactive stack could be used such as Spring Webflux with Kotlin, to simplify the programming model and improve the
  scalability.
* Resilience / fallback ... in the event the third party ws service is down
* Heap Data Structure could be abstracted from the priority queue implementation
* Better techniques can be used to prevent race conditions between the consumer and producer of the phone call messages
* Split the project into subprojects / dependencies, for example maybe a library for the websocket client code, common
  library code etc ....
