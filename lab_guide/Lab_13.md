<img align="right" src="../logo-small.png">


Reactive Programming 
====================

In this chapter, the reader will be introduced to the **Reactive
Manifesto** and the world of reactive programming. We start with
defining and discussing the main related concepts -- asynchronous,
non-blocking, and responsive. Using them, we then define and discuss
reactive programming, the main reactive frameworks, and talk about
**RxJava** in more detail.

The following topics will be covered in this chapter:

-   Asynchronous processing
-   Non-blocking API
-   Reactive -- responsive, resilient, elastic, message-driven
-   Reactive streams
-   RxJava


### Run Java Code
You can run the example by running following command in the terminal:
`java -cp target/learnjava-1.0.jar com.lv.learnjava.ch15_reactive.BlockingOperators.java`

### Run Java Code
You can run the example by running following command in the terminal:
`java -cp target/learnjava-1.0.jar com.lv.learnjava.ch15_reactive.CreateObservable.java`

### Run Java Code
You can run the example by running following command in the terminal:
`java -cp target/learnjava-1.0.jar com.lv.learnjava.ch15_reactive.DisposableUsage.java`

### Run Java Code
You can run the example by running following command in the terminal:
`java -cp target/learnjava-1.0.jar com.lv.learnjava.ch15_reactive.HotObservable.java`

### Run Java Code
You can run the example by running following command in the terminal:
`java -cp target/learnjava-1.0.jar com.lv.learnjava.ch15_reactive.MeasuringSystem.java`

### Run Java Code
You can run the example by running following command in the terminal:
`java -cp target/learnjava-1.0.jar com.lv.learnjava.ch15_reactive.NonBlockingOperators.java`

### Run Java Code
You can run the example by running following command in the terminal:
`java -cp target/learnjava-1.0.jar com.lv.learnjava.ch15_reactive.Scheduler.java`


Asynchronous processing
====================

**Asynchronous** means that the requestor gets the response immediately,
but the result is not there. Instead, the requestor waits until the
result is sent to them, or saved in the database, or, for example,
presented as an object that allows checking if the result is ready. If
the latter is the case, the requestor calls a certain method to this
object periodically and, when the result is ready, retrieves it using
another method on the same object. The advantage of asynchronous
processing is that the requestor can do other things while waiting.

In
[](https://subscription.packtpub.com/book/programming/9781789957051/8)[Chapter
8](https://subscription.packtpub.com/book/programming/9781789957051/8),
*Multithreading and Concurrent Processing*, we have demonstrated how
a child thread can be created. Such a child thread then sends a
non-asynchronous (blocking) request and waits for its return doing
nothing. The main thread, meanwhile, continues executing and
periodically calls the child thread object to see whether the result is
ready. That is the most basic of asynchronous processing
implementations. In fact, we have used it already when we used parallel
streams.

The parallel-stream operations behind the scenes that create child
threads break the stream into segments, and assign each segment to a
dedicated thread for processing, and then aggregate the partial results
from all the segments into the final result. In the previous chapter, we
have even written functions that did the aggregating job. As a reminder,
the function was called a **combiner**.

Let\'s compare the performance of sequential and parallel streams using
an example.

Sequential and parallel streams {#sequential-and-parallel-streams .header-title}
-------------------------------

To demonstrate the difference between sequential and parallel
processing, let\'s imagine a system that collects data from 10 physical
devices (sensors) and calculates an average. The following is the
[get()] method that collects a measurement from a sensor
identified by ID:


```
double get(String id){
    try{
        TimeUnit.MILLISECONDS.sleep(100);
    } catch(InterruptedException ex){
        ex.printStackTrace();
    }
    return id * Math.random();
}
```

We have put a delay for 100 milliseconds to imitate the time it takes to
collect the measurement from the sensor. As for the resulting
measurement value, we use the [Math.random()] method. We are going
to call this [get()] method using an object of
the [MeasuringSystem] class, where the method belongs. 

Then, we are going to calculate an average -- to offset the errors and
other idiosyncrasies of an individual device:


```
void getAverage(Stream<Integer> ids) {
    LocalTime start = LocalTime.now();
    double a = ids.mapToDouble(id -> new MeasuringSystem().get(id))
                  .average()
                  .orElse(0);
    System.out.println((Math.round(a * 100.) / 100.) + " in " +
          Duration.between(start, LocalTime.now()).toMillis() + " ms");
}
```

Notice how we convert the stream of IDs into [DoubleStream] using
the [mapToDouble()] operation so we can apply
the [average()] operation. The [average()] operation returns
an [Optional\<Double\>] object, and we call
its [orElse(0)] method that returns either the calculated value or
zero (if, for example, the measuring system could not connect to any of
its sensors and returned an empty stream). 

The last line of the [getAverage()] method prints the result and
the time it took to calculate it. In real code, we would return the
result and use it for other calculations. But, for demonstration, we
just print it.

Now, we can compare the performance of a sequential stream processing
with the performance of the parallel processing:


```
List<Integer> ids = IntStream.range(1, 11)
                             .mapToObj(i -> i)
                             .collect(Collectors.toList());
getAverage(ids.stream());          //prints: 2.99 in 1030 ms
getAverage(ids.parallelStream());  //prints: 2.34 in  214 ms
```

The results may be different if you run this example because, as you may
recall, we simulate the collected measurements as random values.

As you can see, the processing of a parallel stream is five times faster
than the processing of a sequential stream. The results are different
because the measurement produces a slightly different result every
time. 

Although behind the scenes, the parallel stream uses asynchronous
processing, this is not what programmers have in mind when talking about
the asynchronous processing of requests. From the application\'s
perspective, it is just parallel (also called concurrent) processing. It
is faster than sequential processing, but the main thread has to wait
until all the calls are made and the data is retrieved. If each call
takes at least 100 ms (as it is in our case), then the processing of all
the calls cannot be completed in less time.

Of course, we can create a child thread and let it make all the calls
and wait until they are complete, while the main thread does something
else. We even can create a service that does it, so the application
would just tell such a service what has to be done and then continue
doing something else. Later, the main thread can call the service again
and get the result or pick it up in some agreed location.

That would be the truly asynchronous processing the programmers are
talking about. But, before writing such a code, let\'s look at the
[CompletableFuture] class located in
the [java.util.concurrent] package. It does everything described,
and even more.

Using the CompletableFuture object {#using-the-completablefuture-object .header-title}
----------------------------------

Using the [CompletableFuture] object, we can separate sending the
request to the measuring system by getting the result from the
[CompletableFuture] object. That is exactly the scenario we
described while explaining what asynchronous processing is. Let\'s
demonstrate it in the code:


```
List<CompletableFuture<Double>> list = 
     ids.stream()
        .map(id -> CompletableFuture.supplyAsync(() -> 
                                       new MeasuringSystem().get(id)))
        .collect(Collectors.toList());
```

The [supplyAsync()] method does not wait for the call to the
measuring system to return. Instead, it immediately creates a
[CompletableFuture] object and returns it, so that a client can
use this object any time later to retrieve the values returned by the
measuring system:


```
LocalTime start = LocalTime.now();
double a = list.stream()
               .mapToDouble(cf -> cf.join().doubleValue())
               .average()
               .orElse(0);
System.out.println((Math.round(a * 100.) / 100.) + " in " +
     Duration.between(start, LocalTime.now()).toMillis() + " ms"); 
                                               //prints: 2.92 in 6 ms
```

There are also methods that allow checking whether the value was
returned at all, but that is not the point of this demonstration, which
is to show how the [CompletableFuture] class can be used to
organize asynchronous processing.

The created list of [CompletableFuture] objects can be stored
anywhere and processed very quickly (in 6 ms, in our case), provided
that the measurements have been received already. Between creating the
list of [CompletableFuture] objects and processing them, the
system is not blocked and can do something else.

The [CompletableFuture] class has many methods and support from
several other classes and interfaces. For example, a fixed-size thread
pool can be added to limit the number of threads:


```
ExecutorService pool = Executors.newFixedThreadPool(3);
List<CompletableFuture<Double>> list = ids.stream()
        .map(id -> CompletableFuture.supplyAsync(() -> 
                         new MeasuringSystem().get(id), pool))
        .collect(Collectors.toList());
```

There is a variety of such pools for different purposes and different
performance. But all that does not change the overall system design, so
we omit such details.

As you can see, the power of asynchronous processing is great. There is
also a variation of asynchronous API called a **non-blocking API**,
which we are going to discuss in the next section.


Non-blocking API
====================

The client of a non-blocking API expects to get the results back
quickly, that is, without being blocked for a significant amount of
time. So, the notion of a non-blocking API implies a highly responsive
application. It can process the request synchronously or
asynchronously---it does not matter for the client. In practice, though,
this typically means that the application uses asynchronous processing
that facilitates an increased throughput and improved performance.

The term **non-blocking** came into use with the
[java.nio] package. The **non-blocking input/output** (**NIO**)
provides support for intensive input/output operations. It describes how
the application is implemented: it does not dedicate an execution thread
to each of the requests but provides several lightweight worker threads
that do the processing asynchronously and concurrently. 

The java.io package versus the java.nio package {#the-java.io-package-versus-the-java.nio-package .header-title}
-----------------------------------------------

Writing and reading data to and from an external memory (a hard drive,
for example) is a much slower operation than the processes in the memory
only. The already-existing classes and interfaces of the [java.io]
package worked well, but once in a while became the performance
bottleneck. The new [java.nio] package was created to provide a
more effective I/O support.

The [java.io] implementation is based on I/O stream processing. As
we have seen in the previous section, it is basically a blocking
operation even if some kind of concurrency is happening behind the
scenes. To increase the speed, the [java.nio] implementation was
introduced based on the reading/writing to/from a buffer in the memory.
Such a design allowed it to separate the slow process of
filling/emptying the buffer and quickly reading/writing from/to it.

In a way, it is similar to what we have done in our example of
[CompletableFuture] usage. The additional advantage of having data
in a buffer is that it is possible to inspect the data, going there and
back along the buffer, which is impossible while reading sequentially
from the stream. It has provided more flexibility during data
processing. In addition, the [java.nio] implementation introduced
another middleman process called a **channel** for the bulk data
transfer to and from a buffer.

The reading thread is getting data from a channel and only receives what
is currently available, or nothing at all (if no data is in the
channel). If data is not available, the thread, instead of remaining
blocked, can do something else -- reading/writing to/from other
channels, for example, the same way the main thread in our
[CompletableFuture] example was free to do whatever had to be done
while the measuring system was reading data from its sensors.

This way, instead of dedicating a thread to one I/O process, a few
worker threads can serve many I/O processes. Such a solution was called
a **non-blocking I/O** and later was applied to other processes, the
most prominent being the *event processing in an event loop*, also
called a **run loop**.

The event/run loop {#the-eventrun-loop .header-title}
------------------

Many non-blocking systems are based on the **event** (or **run**) loop
-- a thread that is continually executed. It receives events (requests,
messages) and then dispatches them to the corresponding event handlers
(workers). There is nothing special about the event handlers. They are
just methods (functions) dedicated by the programmer for the processing
of the particular event type.

Such a design is called a **reactor design pattern***.* It is
constructed around processing concurrent events and service requests. It
also gave the name to the **reactive programming** and **reactive
systems** that *react* to events and process them concurrently.

Event loop-based design is widely used in operating systems and in
graphical user interfaces. It is available in Spring WebFlux in Spring 5
and implemented in JavaScript, and its popular executing environment
Node.JS. The last uses an event loop as its processing backbone. The
toolkit, Vert.x, is built around the event loop too.

Before the adoption of an event loop, a dedicated thread was assigned to
each incoming request -- much like in our demonstration of stream
processing. Each of the threads required the allocation of a certain
amount of resources that were not request-specific, so some of the
resources -- mostly memory allocation -- were wasted. Then, as the
number of requests grew, the CPU needed to switch its context from one
thread to another more often to allow more-or-less concurrent processing
of all the requests. Under the load, the overhead of switching the
context is substantial enough to affect the performance of an
application.

Implementing an event loop has addressed these two issues. It eliminated
the waste of the resources by avoiding the creation of a thread
dedicated to each request and keeping it around until the request is
processed. With an event loop in place, only much smaller memory
allocation is needed for each request to capture its specifics, which
makes it possible to keep far more requests in memory so that they can
be processed concurrently. The overhead of the CPU context-switching has
become much smaller too, because of the diminishing context size.

The non-blocking API is the way the processing of the requests is
implemented. It makes the systems able to handle a much bigger load
while remaining highly responsive and resilient.

Reactive
====================

The term **reactive** is usually used in the context of reactive
programming and reactive systems. The reactive programming (also called
Rx programming) is based on asynchronous data streams (also called
**reactive streams**). It was introduced as **Reactive Extension**
(**RX**) of Java, also called
**RxJava** ([http://reactivex.io](http://reactivex.io/)). Later, the RX
support was added to Java 9 in the [java.util.concurrent] package.
It allows a [Publisher] to generate a stream of data, to which a
[Subscriber] can asynchronously subscribe.

One principal difference between reactive streams and standard streams
(also called **Java 8 streams** located in the [java.util.stream]
package) is that a source (publisher) of the reactive stream pushes
elements to subscribers at its own rate, while in standard streams, a
new element is pulled and emitted only after the previous one was
processed. 

As you have seen, we were able to process data asynchronously even
without this new API, by using [CompletableFuture]. But after
writing such a code a few times, you notice that most of the code is
just plumbing, so you get a feeling there has to be an even simpler and
more convenient solution. That\'s how the Reactive Streams initiative
([http://www.reactive-streams.org](https://www.reactive-streams.org/))
was born. The scope of the effort was defined as follows: 

*\"The scope of Reactive Streams is to find a minimal set of interfaces,
methods, and protocols that will describe the necessary operations and
entities to achieve the goal -- asynchronous streams of data with
non-blocking back pressure.\"*


The term **non-blocking back pressure** refers to one of the problems of
asynchronous processing: coordinating of the speed rate of the incoming
data with the ability of the system to process them without the need for
stopping (blocking) the data input. The solution is to inform the source
that the consumer has difficulty in keeping up with the input. Also, the
processing should react to the change of the rate of the incoming data
in a more flexible manner than just blocking the flow, thus the name
*reactive*.

There are several libraries already that implement the Reactive Streams
API: RxJava ([http://reactivex.io](http://reactivex.io/)), Reactor
([https://projectreactor.io](https://projectreactor.io/)), Akka Streams
(<https://akka.io/docs>), and Vert.x (<https://vertx.io/>) are among the
most known. Writing code using RxJava or another library of asynchronous
streams constitutes *reactive programming*. It realizes the goal
declared in the Reactive Manifesto
([https://www.reactivemanifesto.org](https://www.reactivemanifesto.org/))
as building reactive systems that are *responsive*, *resilient*,
*elastic*, and *message-driven*.

Responsive {#responsive .header-title}
----------

It seems that this term is self-explanatory. The ability to respond in a
timely manner is one of the primary qualities of any system. There are
many ways to achieve it. Even a traditional blocking API supported by
enough servers and other infrastructure can achieve decent
responsiveness under a growing load.

Reactive programming helps to do it using less hardware. It comes with a
price, as reactive code requires changing the way we think about control
flow. But after some time, this new way of thinking becomes as natural
as any other familiar skill.

We will see quite a few examples of reactive programming in the
following sections.

Resilient {#resilient .header-title}
---------

Failures are inevitable. The hardware crashes, the software has defects,
unexpected data is received, or an untested execution path was taken --
any of these events or a combination of them can happen at any time.
*Resilience* is the ability of a system to continue delivering the
expected results under unexpected circumstances.

It can be achieved using redundancy of the deployable components and
hardware, using isolation of parts of the system so the domino effect
becomes less probable, designing the system with automatically
replaceable parts, raising an alarm so that qualified personnel can
interfere, for example. We have also talked about distributed systems as
a good example of resilient systems by design.

A distributed architecture eliminates a single point of failure. Also,
breaking the system into many specialized components that talk to one
another using messages allows better tuning of the duplication of the
most critical parts and creates more opportunities for their isolation
and the potential failure containment.

Elastic {#elastic .header-title}
-------

The ability to sustain the biggest possible load is usually associated
with **scalability**. But, the ability to preserve the same performance
characteristics under a varying load, not under the growing one only, is
called **elasticity**.

A client of an elastic system should not notice any difference between
the idle periods and the periods of the peak load. Non-blocking reactive
style of implementation facilitates this quality. Also, breaking the
program into smaller parts and converting them into services that can be
deployed and managed independently allows for the fine-tuning of the
resources allocation.

Such small services are called microservices, and many of them together
can comprise a reactive system that can be both scalable and elastic. We
will talk about such architecture in more details in the following
sections and the next chapter.

Message-driven {#message-driven .header-title}
--------------

We have established already that components isolation and system
distribution are two aspects that help to keep the system responsive,
resilient, and elastic. Loose and flexible connections are important
conditions that support these qualities too. And, the asynchronous
nature of the reactive system simply does not leave the designer other
choices but to build communication between components on messages.

It creates a breathing space around each component without which the
system would be a tightly coupled monolith susceptible to all kinds of
problems, not to mention a maintenance nightmare.

In the next chapter, we are going to look at the architectural style
that can be used to build an application as a collection of
loosely-coupled microservices that communicate using messages.

Reactive streams 
====================

The Reactive Streams API introduced in Java 9 consists of the following
four interfaces:


```
@FunctionalInterface
public static interface Flow.Publisher<T> {
    public void subscribe(Flow.Subscriber<T> subscriber);
}
public static interface Flow.Subscriber<T> {
    public void onSubscribe(Flow.Subscription subscription);
    public void onNext(T item);
    public void onError(Throwable throwable);
    public void onComplete();
}
public static interface Flow.Subscription {
    public void request(long numberOfItems);
    public void cancel();
}
public static interface Flow.Processor<T,R>
              extends Flow.Subscriber<T>, Flow.Publisher<R> {
}
```

A [Flow.Subscriber] object can be passed as a parameter into the
[subscribe()] method of [Flow.Publisher\<T\>]. The publisher
then calls the subscriber\'s [onSubscribe()] method and passes to
it as a parameter a [Flow.Subsctiption] object. Now, the
subscriber can call [request(long numberOfItems)] on the
subscription object to request data from the publisher. That is the way
the **pull model** can be implemented, which leaves it to a subscriber
to decide when to request another item for processing. The subscriber
can unsubscribe from the publisher services by calling the
[cancel()] method on subscription.

In return, the publisher can pass to the subscriber a new item by
calling the subscriber\'s [onNext()] method. When no more data
will be coming (all the data from the source were emitted) the publisher
calls the subscriber\'s [onComplete()] method. Also, by calling
the subscriber\'s [onError()] method, the publisher can tell the
subscriber that it has encountered a problem. 

The [Flow.Processor] interface describes an entity that can act as
both a subscriber and a publisher. It allows creating chains (pipelines)
of such processors, so a subscriber can receive an item from a
publisher, transform it, then pass the result to the next subscriber or
processor.

In a push model, the publisher can call [onNext()] without any
request from the subscriber. If the rate of processing is lower than the
rate of the item publishing, the subscriber can use various strategies
to relieve the pressure. For example, it can skip the items or create a
buffer for temporary storage with the hope that the item production will
slow down and the subscriber will be able to catch up. 

This is the minimal set of interfaces the Reactive Streams initiative
has defined in support of the asynchronous data streams with
non-blocking back pressure. As you can see, it allows the subscriber and
publisher to talk to each other and coordinate the rate of incoming
data, thus making possible a variety of solutions for the back pressure
problem we discussed in the *Reactive* section.

There are many ways to implement these interfaces. Currently, in JDK 9,
there is only one implementation of one of the interfaces: the
[SubmissionPublisher] class implements [Flow.Publisher]. The
reason for that is that these interfaces are not supposed to be used by
an application developer. It is a **Service Provider Interface**
(**SPI**) that is used by the developers of the reactive streams
libraries. If need be, use one of the already-existing toolkits that
have implemented the Reactive Streams API we have mentioned already:
RxJava, Reactor, Akka Streams, Vert.x, or any other library of your
preference.

RxJava
====================

We will use **RxJava
2.2.7** ([http://reactivex.io](http://reactivex.io/)) in our examples.
It can be added to the project by the following dependency:


```
<dependency>
    <groupId>io.reactivex.rxjava2</groupId>
    <artifactId>rxjava</artifactId>
    <version>2.2.7</version>
</dependency>
```

Let\'s first compare two implementations of the same functionality using
the [java.util.stream] package and
the [io.reactivex] package. The sample program is going to be very
simple:

-   Create a stream of
    integers [1],[2],[3],[4],[5].
-   Filter only even numbers ([2] and [4]).
-   Calculate a square root of each of the filtered numbers.
-   Calculate the sum of all the square roots.

Here is how it can be implemented using
the [java.util.stream] package:


```
double a = IntStream.rangeClosed(1, 5)
                    .filter(i -> i % 2 == 0)
                    .mapToDouble(Double::valueOf)
                    .map(Math::sqrt)
                    .sum();
System.out.println(a);          //prints: 3.414213562373095
```

And, the same functionality implemented with RxJava looks as follows:


```
Observable.range(1, 5)
          .filter(i -> i % 2 == 0)
          .map(Math::sqrt)
          .reduce((r, d) -> r + d)
          .subscribe(System.out::println);   //prints: 3.414213562373095
```

RxJava is based on the [Observable] object (which plays the role
of [Publisher]) and [Observer] that subscribes to
the [Observable] and waits for the data to be emitted.

By contrast, with
the [Stream] functionality, [Observable] has significantly
different capabilities. For example, a stream, once closed, cannot be
reopened, while an [Observable] object can be used again. Here is
an example:


```
Observable<Double> observable = Observable.range(1, 5)
        .filter(i -> i % 2 == 0)
        .doOnNext(System.out::println)    //prints 2 and 4 twice
        .map(Math::sqrt);
observable
        .reduce((r, d) -> r + d)
        .subscribe(System.out::println);  //prints: 3.414213562373095
observable
        .reduce((r, d) -> r + d)
        .map(r -> r / 2)
        .subscribe(System.out::println);  //prints: 1.7071067811865475
```

In the preceding example, as you can see from the comments,
the [doOnNext()] operation was called twice, which means the
observable object emitted values twice, once for each processing
pipeline:

![](6_files/acc3327d-5a97-4801-868f-30cb0ec98179.png)

If we do not want [Observable] running twice, we can cache its
data, by adding the [cache()] operation:


```
Observable<Double> observable = Observable.range(1,5)
        .filter(i -> i % 2 == 0)
        .doOnNext(System.out::println)  //prints 2 and 4 only once
        .map(Math::sqrt)
        .cache();
observable
        .reduce((r, d) -> r + d)
        .subscribe(System.out::println); //prints: 3.414213562373095
observable
        .reduce((r, d) -> r + d)
        .map(r -> r / 2)
        .subscribe(System.out::println);  //prints: 1.7071067811865475
```

As you can see, the second usage of the same [Observable] took
advantage of the cached data, thus allowing for better performance:

![](6_files/3ec2a6d2-6903-489f-8d6c-52cffc64cfe0.png)

RxJava provides such a rich functionality that there is no way we can
review it all in detail in this book. Instead, we will try to cover the
most popular API. The API describes the methods available for invocation
using an [Observable] object. Such methods are often also called
**operations** (as in the case with the standard Java 8 streams too) or
**operators** (mostly used in connection to reactive streams). We will
use these three terms, methods, operations, and operators,
interchangeably as synonyms. 

Observable types {#observable-types .header-title}
----------------

Talking about RxJava 2 API (notice that is it quite different than
RxJava 1), we will use the online documentation that can be found
at <http://reactivex.io/RxJava/2.x/javadoc/index.html>.

An observer subscribes to receive values from an observable object,
which can behave as one of the following types: 

-   **Blocking**: Waiting until the result is returned
-   **Non-blocking**: Processing the emitted elements asynchronously
-   **Cold**: Emitting an element at the observer\'s request
-   **Hot**: Emitting elements whether an observer has subscribed or not

An observable object can be an object of one of the following classes of
the [io.reactivex ]package:

-   [Observable\<T\>]: Can emit none, one, or many elements; does
    not support backpressure.
-   [Flowable\<T\>]: Can emit none, one, or many elements;
    supports backpressure.
-   [Single\<T\>]: Can emit either one element or error;
    the notion of backpressure does not apply.
-   [Maybe\<T\>]: Represents a deferred computation; can emit
    either no value, one value, or error; the notion of backpressure
    does not apply.
-   [Completable]: Represents a deferred computation without any
    value; indicates completion of the task or error; the notion of
    backpressure does not apply.

An object of each of these classes can behave as blocking, non-blocking,
cold, or a hot observable. They are different because of the number of
values that can be emitted, an ability to defer the returning of the
result, or returning the flag of the task completion only, and because
of their ability to handle backpressure.

Blocking versus non-blocking {#blocking-versus-non-blocking .header-title}
----------------------------

To demonstrate this behavior, we create an observable that emits five
sequential integers, starting with [1]:


```
Observable<Integer> obs = Observable.range(1,5);
```

All the blocking methods (operators) of [Observable] starts with
the \"blocking\", so the [blockingLast()] is one of the blocking
operators, which blocks the pipeline until the last of the elements is
emitted:


```
Double d2 = obs.filter(i -> i % 2 == 0)
               .doOnNext(System.out::println)  //prints 2 and 4
               .map(Math::sqrt)
               .delay(100, TimeUnit.MILLISECONDS)
               .blockingLast();
System.out.println(d2);                        //prints: 2.0
```

In this example, we select only even numbers, print the selected
element, then calculate the square root and wait for 100 ms (imitating a
long-running calculation). The result of this example looks as follows:

![](6_files/05b15d75-8715-4bba-9f7a-dd6bb4bf7f11.png)

The non-blocking version of the same functionality looks as follows:


```
List<Double> list = new ArrayList<>();
obs.filter(i -> i % 2 == 0)
   .doOnNext(System.out::println)  //prints 2 and 4
   .map(Math::sqrt)
   .delay(100, TimeUnit.MILLISECONDS)
   .subscribe(d -> {
        if(list.size() == 1){
            list.remove(0);
        }
        list.add(d);
   });
System.out.println(list);          //prints: []
```

We use the [List] object to capture the result because, as you may
remember, the lambda expression does not allow using the non-final
variables.

As you can see, the resulting list is empty. That is because the
pipeline calculations are performed without blocking (asynchronously).
So, because of the delay of 100 ms, the control meanwhile went down to
the last line that prints the list content, which is still empty. We can
set a delay in front of the last line:


```
try {
    TimeUnit.MILLISECONDS.sleep(200);
} catch (InterruptedException e) {
    e.printStackTrace();
}
System.out.println(list);   //prints: [2.0]
```

The delay has to be 200 ms at least because the pipeline processes two
elements, each with 100 ms delay. Now, you can see, the list contains an
expected value of [2.0]. 

That is basically the difference between blocking and non-blocking
operators. Other classes, that represent an [observable], have
similar blocking operators. Here are examples of blocking
[Flowable], [Single], and [Maybe]:


```
Flowable<Integer> obs = Flowable.range(1,5);
Double d2 = obs.filter(i -> i % 2 == 0)
        .doOnNext(System.out::println)  //prints 2 and 4
        .map(Math::sqrt)
        .delay(100, TimeUnit.MILLISECONDS)
        .blockingLast();
System.out.println(d2);                 //prints: 2.0

Single<Integer> obs2 = Single.just(42);
int i2 = obs2.delay(100, TimeUnit.MILLISECONDS).blockingGet();
System.out.println(i2);                 //prints: 42

Maybe<Integer> obs3 = Maybe.just(42); 
int i3 = obs3.delay(100, TimeUnit.MILLISECONDS).blockingGet(); 
System.out.println(i3);                 //prints: 42 
```

The [Completable] class has blocking operators that allow setting
a timeout:


```
(1) Completable obs = Completable.fromRunnable(() -> {
            System.out.println("Running...");       //prints: Running...
            try {
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    });                                           
(2) Throwable ex = obs.blockingGet();
(3) System.out.println(ex);                               //prints: null

//(4) ex = obs.blockingGet(15, TimeUnit.MILLISECONDS);
//                                java.util.concurrent.TimeoutException: 
//               The source did not signal an event for 15 milliseconds.

(5) ex = obs.blockingGet(150, TimeUnit.MILLISECONDS);
(6) System.out.println(ex);                               //prints: null

(7) obs.blockingAwait();
(8) obs.blockingAwait(15, TimeUnit.MILLISECONDS);
```

The result of the preceding code is presented in the following
screenshot:

![](6_files/91ef13a4-dd77-4c60-9509-1005bc323e87.png) 

The first [Run] message comes from line 2 in response to
the call of the blocking [blockingGet()] method. The first
[null] message comes from line 3. Line 4 throws an
exception because the timeout was set to 15 ms, while the actual
processing is set to a 100 ms delay. The second [Run]
message comes from line 5 in response to the
[blockingGet()] method call. This time, the timeout is set to 150
ms, which is more than 100 ms, and the method was able to return before
the timeout was up.

The last two lines, 7 and 8, demonstrate the usage of
the [blockingAwait()] method with and without a timeout. This
method does not return a value but allows the observable pipeline to run
its course. It is interesting to notice that it does not break with an
exception even when the timeout is set to a smaller value than the time
the pipelines takes to finish. Apparently, it starts its waiting after
the pipeline has finished processing unless it is a defect that will be
fixed later (the documentation is not clear on this point).

Although the blocking operations exist (and we will review more of such
while talking about each observable type in the following sections),
they are and should be used only in the cases when it is not possible to
implement the required functionality using non-blocking operations only.
The main thrust of reactive programming is to strive to process all
requests asynchronously in a non-blocking style. 

Cold versus hot {#cold-versus-hot .header-title}
---------------

All the examples we have seen so far demonstrated only a [cold]
observable, those that provide the next value only at the request of the
processing pipeline after the previous value has been processed already.
Here is another example:


```
Observable<Long> cold = Observable.interval(10, TimeUnit.MILLISECONDS);
cold.subscribe(i -> System.out.println("First: " + i));
pauseMs(25);
cold.subscribe(i -> System.out.println("Second: " + i));
pauseMs(55);
```

We have used the method [interval()] to create an
[Observable] object that represents a stream of sequential numbers
emitted every specified interval (in our case, every 10 ms). Then, we
subscribed to the created object, wait 25 ms, subscribe again, and wait
another 55 ms. The [pauseMs()] method looks as follows:


```
void pauseMs(long ms){
    try {
        TimeUnit.MILLISECONDS.sleep(ms);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
}
```

If we run the preceding example, the output will be as follows:

![](6_files/d1fc35cc-61d1-4bea-890d-b04cd6ebf4a0.png)

As you can see, each of the pipelines processed every value emitted by
the cold observable.

To convert the *cold* observable into a *hot* one, we use
the [publish()] method that converts the observable into a
[ConnectableObservable] object that extends
the [Observable]:


```
ConnectableObservable<Long> hot = 
         Observable.interval(10, TimeUnit.MILLISECONDS).publish();
hot.connect();
hot.subscribe(i -> System.out.println("First: " + i));
pauseMs(25);
hot.subscribe(i -> System.out.println("Second: " + i));
pauseMs(55);
```

As you can see, we have to call the [connect()] method in order
that the [ConnectableObservable] object starts emitting values.
The output looks like the following:

![](6_files/bb0c0666-56f5-4ae7-b5e8-50f33d697ae1.png)

The output shows that the second pipeline did not receive the first
three values because it has subscribed to the observable later. So, the
observable emits values independent of the ability of the observers to
process them. If the processing falls behind, and new values keep coming
while previous ones are not fully processed yet, the [Observable]
class puts them into a buffer. If this buffer grows big enough, the JVM
can run out of memory because, as we have mentioned already, the
[Observable] class does not have an ability of backpressure
management.

For such cases, the [Flowable] class is a better candidate for the
observable because it does have an ability to handle the backpressure.
Here is an example: 


```
PublishProcessor<Integer> hot = PublishProcessor.create();
hot.observeOn(Schedulers.io(), true)
   .subscribe(System.out::println, Throwable::printStackTrace);
for (int i = 0; i < 1_000_000; i++) {
    hot.onNext(i);
}
```

The [PublishProcessor] class extends [Flowable] and has
the [onNext(Object o)] method that forces it to emit the passed-in
object. Before calling it, we have subscribed to the observable using
the [Schedulers.io()] thread. We will talk about the schedulers in
the *Multithreading (Scheduler)* section.

The [subscribe()] method has several overloaded versions. We
decided to use the one that accepts two [Consumer] functions: the
first one processes the passed-in value, the second processes an
exception if it was thrown by any of the pipeline operations (similar to
a [Catch] block).

If we run the preceding example, it will print successfully the first
127 values and then will throw [MissingBackpressureException], as
shown on the following screenshot:

![](6_files/9978a4cb-5329-42f5-88b1-8035f01d4c2b.png)

The message in the exception provides a clue: [Could not emit value due
to lack of requests]*.* Apparently, the rate of emitting the
values is higher than the rate of consuming them and an internal buffer
can keep only 128 elements. If we add a delay (simulating a longer
processing time), the result will be even worse:


```
PublishProcessor<Integer> hot = PublishProcessor.create();
hot.observeOn(Schedulers.io(), true)
   .delay(10, TimeUnit.MILLISECONDS)
   .subscribe(System.out::println, Throwable::printStackTrace);
for (int i = 0; i < 1_000_000; i++) {
    hot.onNext(i);
}
```

Even the first 128 elements will not get through and the output will
have only [MissingBackpressureException]. 

To address the issue, a backpressure strategy has to be set. For
example, let\'s drop every value that the pipeline did not manage to
process:


```
PublishProcessor<Integer> hot = PublishProcessor.create();
hot.onBackpressureDrop(v -> System.out.println("Dropped: "+ v))
   .observeOn(Schedulers.io(), true)
   .subscribe(System.out::println, Throwable::printStackTrace);
for (int i = 0; i < 1_000_000; i++) {
    hot.onNext(i);
}
```

Notice that the strategy has to be set before
the [observeOn()] operation, so it will be picked up by the
created [Schedulers.io()] thread.

The output shows that many of the emitted values were dropped. Here is
an output fragment:

![](6_files/bde4ce8b-9e32-408f-99b3-b81a27bda6c2.png)

We will talk about other backpressure strategies in the *Operators*
section, in the overview of the corresponding operators.

Disposable {#disposable .header-title}
----------

Please notice that a [subscribe()] method actually returns
a [Disposable] object that can be queried to check whether the
pipeline processing has been completed (and disposed of):


```
Observable<Integer> obs = Observable.range(1,5);
List<Double> list = new ArrayList<>();
Disposable disposable =
     obs.filter(i -> i % 2 == 0)
        .doOnNext(System.out::println)     //prints 2 and 4
        .map(Math::sqrt)
        .delay(100, TimeUnit.MILLISECONDS)
        .subscribe(d -> {
            if(list.size() == 1){
                list.remove(0);
            }
            list.add(d);
        });
System.out.println(list);                    //prints: []
System.out.println(disposable.isDisposed()); //prints: false
try {
    TimeUnit.MILLISECONDS.sleep(200);
} catch (InterruptedException e) {
    e.printStackTrace();
}
System.out.println(disposable.isDisposed());  //prints: true
System.out.println(list);                     //prints: [2.0]
```

It is also possible to enforce the disposing of a pipeline, thus
effectively canceling the processing:


```
Observable<Integer> obs = Observable.range(1,5);
List<Double> list = new ArrayList<>();
Disposable disposable =
     obs.filter(i -> i % 2 == 0)
        .doOnNext(System.out::println)       //prints 2 and 4
        .map(Math::sqrt)
        .delay(100, TimeUnit.MILLISECONDS)
        .subscribe(d -> {
            if(list.size() == 1){
                list.remove(0);
            }
            list.add(d);
        });
System.out.println(list);                    //prints: []
System.out.println(disposable.isDisposed()); //prints: false
disposable.dispose();
try {
    TimeUnit.MILLISECONDS.sleep(200);
} catch (InterruptedException e) {
    e.printStackTrace();
}
System.out.println(disposable.isDisposed()); //prints: true
System.out.println(list);                    //prints: []
```

As you can see, by adding the call to [disposable.dispose()], we
have stopped processing: the list content, even after a 200 ms delay,
remains empty (the last line of the preceding example).

This method of forced disposal can be used to make sure that there are
no run-away threads. Each created [Disposable] object can be
disposed of in the same way the resources are released in a
[finally] block. The [CompositeDisposable] class helps to
handle multiple [Disposable] objects in a coordinated manner.

When an [onComplete] or [onError] event happens, the
pipeline is disposed of automatically.

For example, you can use the [add()] method and add a newly
created [Disposable] object to the [CompositeDisposable]
object. Then, when necessary, the [clear()] method can be invoked
on the [CompositeDisposable] object. It will remove the
collected [Disposable] objects and call the [dispose()]
method on each of them.

Creating an observable {#creating-an-observable .header-title}
----------------------

You have seen already a few methods of creating an observable in our
examples. There are many other factory methods in [Observable],
[Flowable], [Single], [Maybe],
and [Completable]. Not all of the following methods are available
in each of these interfaces though (see the comments; *all* means that
all of the listed interfaces have it):

-   [create()]: Creates an [Observable] object by providing
    the full implementation (all)
-   [defer()]: Creates a new [Observable] object every time
    a new [Observer] subscribes (all)
-   [empty()]: Creates an empty [Observable] object that
    completes immediately upon subscription (all, except [Single])
-   [never()]: Creates an [Observable] object that does not
    emit anything and does nothing at all; does not even complete (all)
-   [error()]: Creates an [Observable] object that emits an
    exception immediately upon subscription (all)
-   [fromXXX()]: Creates an [Observable] object, where XXX
    can be *Callable*, *Future* (all), *Iterable*, *Array*,
    *Publisher* ([Observable] and [Flowable]), *Action*,
    *Runnable* ([Maybe] and [Completable]); which means it
    creates an [Observable] object based on the provided function
    or object
-   [generate()]: Creates a cold [Observable] object that
    generates values based on the provided function or object
    ([Observable] and [Flowable] only)
-   [range(), rangeLong(), interval(), intervalRange()]: Creates
    an [Observable] object that emits sequential [int]
    or [long] values limited or not by the specified range and
    spaced by the specified time
    interval ([Observable] and [Flowable] only)
-   [just()]: Creates an [Observable] object based on the
    provided object or a set of objects (all, except
    [Completable])
-   [timer()]: Creates an [Observable] object that, after
    the specified time, emits an [0L] signal (all) and then
    completes for [Observable] and [Flowable]

There are also many other helpful methods, such as [repeat()],
[startWith()], and similar. We just do not have enough space to
list all of them. Refer to the online documentation
(<http://reactivex.io/RxJava/2.x/javadoc/index.html>).

Let\'s look at an example of the [create()] method usage. The
[create()] method of [Observable] looks as follows:


```
public static Observable<T> create(ObservableOnSubscribe<T> source)
```

The passed-in object has to be an implementation of the functional
interface [ObservableOnSubscribe\<T\>] that has only one abstract
method, [subscribe()]:


```
void subscribe(ObservableEmitter<T> emitter)
```

The [ObservableEmitter\<T\>] interface contains the following
methods:

-   [boolean isDisposed()]: Returns [true] if the processing
    pipeline was disposed or the emitter was terminated
-   [ObservableEmitter\<T\> serialize()]: Provides the
    serialization algorithm used by the calls to [onNext()],
    [onError()], and [onComplete()], located in the base
    class [Emitter]
-   [void setCancellable(Cancellable c)]: Sets on this emitter a
    [Cancellable] implementation (a functional interface that has
    only one method,[cancel()])
-   [void setDisposable(Disposable d)]: Sets on this
    emitter a [Disposable] implementation (an interface that has
    two methods: [isDispose()] and [dispose()])
-   [boolean tryOnError(Throwable t)]: Handles the error
    condition, attempts to emit the provided exception, and returns
    [false] if the emission is not allowed

To create an observable, all the preceding interfaces can be implemented
as follows:


```
ObservableOnSubscribe<String> source = emitter -> {
    emitter.onNext("One");
    emitter.onNext("Two");
    emitter.onComplete();
};
Observable.create(source)
          .filter(s -> s.contains("w"))
          .subscribe(v -> System.out.println(v),
                     e -> e.printStackTrace(),
                     () -> System.out.println("Completed"));
pauseMs(100); 
```

Let\'s look at the preceding example closer. We have created an
[ObservableOnSubscribe] function [source] and implemented
the emitter: we told the emitter to emit [One] at the
first call to [onNext()], to emit [Two] at the
second call to [onNext()], and then to call [onComplete()].
We have passed the [source] function into the [create()]
method and built the pipeline to process all the emitted values.

To make it more interesting, we have added
the [filter()] operator that allows propagating further only the
values with the *w* character. We have also chosen the
[subscribe()] method version with three parameters: the
functions [Consumer onNext], [Consumer onError], and [Action
onComplete]. The first is called every time a next value reached
the method, the second is called when an exception was emitted, and the
third is called when the source emits an [onComplete()] signal.
After creating the pipeline, we have paused for 100 ms to give the
asynchronous process a chance to finish. The result looks as follows:

![](6_files/32e93418-0fbd-49e6-a192-45d44769fd0c.png)

If we remove the line [emitter.onComplete()] from the emitter
implementation, only message [Two] will be displayed.

Those are the basics of how the [create()] method can be used. As
you can see, it allows for a full customization. In practice, it is
rarely used because there are many simpler ways to create an observable.
We review them in the following sections.

You will see examples of other factory methods used in our
examples throughout other sections of this chapter.

Operators {#operators .header-title}
---------

There are literally hundreds (if we count all the overloaded versions)
of operators available in each of the observable
interfaces, [Observable], [Flowable], [Single], [Maybe],
or [Completable]. 

In [Observable] and [Flowable], the number of methods goes
beyond 500. That is why in this section we are going to provide just an
overview and a few examples that will help the reader to navigate the
maze of possible options.

To help see the big picture, we have grouped all the operators into ten
categories: transforming, filtering, combining, converting from XXX,
exceptions handling, life cycle events handling, utilities, conditional
and boolean, backpressure, and connectable.


Please notice, these are not all the operators available. You can see
more in the online documentation
(<http://reactivex.io/RxJava/2.x/javadoc/index.html>).


Transforming {#transforming .header-title}
------------

These operators transform the values emitted by an observable:

-   [buffer()]: Collects the emitted values into bundles
    according to the provided parameters or using the provided
    functions, and emits these bundles periodically one at a time
-   [flatMap()]: Produces observables based on the current
    observable and inserts them into the current flow, one of the most
    popular operators
-   [groupBy()]: Divides the current [Observable] into
    groups of observables ([GroupedObservables] objects)
-   [map()]: Transforms the emitted value using the provided
    function
-   [scan()]: Applies the provided function to each value in
    combination with the value produced as the result of the previous
    application of the same function to the previous value
-   [window()]: Emits groups of values similar to
    [buffer()] but as observables, each of which emits a subset of
    values from the original observable and then terminates with an
    [onCompleted()]

The following code demonstrates the use of [map()],
[flatMap()], and [groupBy()]:


```
Observable<String> obs = Observable.fromArray("one", "two");

obs.map(s -> s.contains("w") ? 1 : 0)
   .forEach(System.out::print);              //prints: 01

List<String> os = new ArrayList<>();
List<String> noto = new ArrayList<>();
obs.flatMap(s -> Observable.fromArray(s.split("")))
        .groupBy(s -> "o".equals(s) ? "o" : "noto")
        .subscribe(g -> g.subscribe(s -> {
            if (g.getKey().equals("o")) {
                os.add(s);
            } else {
                noto.add(s);
            }
        }));
System.out.println(os);                  //prints: [o, o]
System.out.println(noto);                //prints: [n, e, t, w]
```

Filtering  {#filtering .header-title}
----------

The following operators (and their multiple overloaded versions) select
which of the values will continue to flow through the pipeline:

-   [debounce()]: Emits a value only when a specified time-span
    has passed without the observable emitting another value
-   [distinct()]: Selects only unique values
-   [elementAt(long n)]: Emits only one value with the
    specified [n] position in the stream
-   [filter()]: Emits only the values that match the specified
    criteria
-   [firstElement()]: Emits only the first value
-   [ignoreElements()]: Does not emit values; only
    the [onComplete()] signal goes through
-   [lastElement()]: Emits only the last value
-   [sample()]: Emits the most recent value emitted within the
    specified time interval
-   [skip(long n)]: Skips the first [n] values
-   [take(long n)]: Emits only the first [n] values

The following are examples of some of the just-listed operators\' uses:


```
Observable<String> obs = Observable.just("onetwo")
        .flatMap(s -> Observable.fromArray(s.split("")));
// obs emits "onetwo" as characters           
obs.map(s -> {
            if("t".equals(s)){
               NonBlockingOperators.pauseMs(15);
            }
            return s;
        })
        .debounce(10, TimeUnit.MILLISECONDS)
        .forEach(System.out::print);               //prints: eo
obs.distinct().forEach(System.out::print);         //prints: onetw
obs.elementAt(3).subscribe(System.out::println);   //prints: t
obs.filter(s -> s.equals("o"))
   .forEach(System.out::print);                    //prints: oo
obs.firstElement().subscribe(System.out::println); //prints: o
obs.ignoreElements().subscribe(() -> 
       System.out.println("Completed!"));          //prints: Completed!
Observable.interval(5, TimeUnit.MILLISECONDS)
   .sample(10, TimeUnit.MILLISECONDS)
   .subscribe(v -> System.out.print(v + " "));     //prints: 1 3 4 6 8 
pauseMs(50);
```

Combining {#combining .header-title}
---------

The following operators (and their multiple overloaded versions) create
a new observable using multiple source observables:

-   [concat(src1, src2)]: Creates an [Observable] that emits
    all values of [src1], then all values of [src2]
-   [combineLatest(src1, src2, combiner)]: Creates an
    [Observable] that emits a value emitted by either of two
    sources combined with the latest value emitted by each source using
    the provided function combiner
-   [join(src2, leftWin, rightWin, combiner)]: Combines values
    emitted by two observables during the [leftWin] and
    [rightWin] time windows according to the [combiner]
    function
-   [merge()]: Combines multiple observables into one; in contrast
    to [concat()], it may interleave them, whereas
    [concat()] never interleaves the emitted values from different
    observables
-   [startWith(T item)]: Adds the specified value before emitting
    values from the source observable
-   [startWith(Observable\<T\> other)]: Adds the values from the
    specified observable before emitting values from the source
    observable
-   [switchOnNext(Observable\<Observable\> observables)]: Creates
    a new [Observable] that emits the most-recently emitted values
    of the specified observables
-   [zip()]: Combines the values of the specified observables
    using the provided function

The following code demonstrates the use of some of these operators:


```
Observable<String> obs1 = Observable.just("one")
                      .flatMap(s -> Observable.fromArray(s.split("")));
Observable<String> obs2 = Observable.just("two")
                      .flatMap(s -> Observable.fromArray(s.split("")));
Observable.concat(obs2, obs1, obs2)
          .subscribe(System.out::print);             //prints: twoonetwo
Observable.combineLatest(obs2, obs1, (x,y) -> "("+x+y+")")
          .subscribe(System.out::print);          //prints: (oo)(on)(oe)
System.out.println();
obs1.join(obs2, i -> Observable.timer(5, TimeUnit.MILLISECONDS),
                i -> Observable.timer(5, TimeUnit.MILLISECONDS),
              (x,y) -> "("+x+y+")").subscribe(System.out::print); 
                          //prints: (ot)(nt)(et)(ow)(nw)(ew)(oo)(no)(eo)
Observable.merge(obs2, obs1, obs2)
          .subscribe(System.out::print);             //prints: twoonetwo
obs1.startWith("42")
    .subscribe(System.out::print); //prints: 42one
Observable.zip(obs1, obs2, obs1,  (x,y,z) -> "("+x+y+z+")")
          .subscribe(System.out::print);       //prints: (oto)(nwn)(eoe) 
```

Converting from XXX {#converting-from-xxx .header-title}
-------------------

These operators are pretty straightforward. Here is the list of from-XXX
operators of the [Observable] class:

-   [fromArray(T\... items)]: Creates an [Observable] from a
    varargs
-   [fromCallable(Callable\<T\> supplier)]: Creates an
    [Observable] from a [Callable] function
-   [fromFuture(Future\<T\> future)]: Creates an
    [Observable] from a [Future] object
-   [fromFuture(Future\<T\> future, long timeout, TimeUnit
    unit)]: Creates an [Observable] from
    a [Future] object with the timeout parameters applied to the
    [future]
-   [fromFuture(Future\<T\> future, long timeout, TimeUnit unit,
    Scheduler scheduler)]: Creates an [Observable] from
    a [Future] object with the timeout parameters applied to
    the [future] and the scheduler ([Schedulers.io()] is
    recommended, see the *Multithreading (Scheduler)* section)
-   [fromFuture(Future\<T\> future, Scheduler scheduler)]: Creates
    an [Observable] from a [Future] object on the specified
    scheduler ([Schedulers.io()] is recommended, see the
    *Multithreading (Scheduler)* section)
-   [fromIterable(Iterable\<T\> source)]: Creates an
    [Observable] from an iterable object ([List], for
    example)
-   [fromPublisher(Publisher\<T\> publisher)]: Creates an
    [Observable] from a [Publisher] object

Exceptions handling {#exceptions-handling .header-title}
-------------------

The [subscribe()] operator has an overloaded version that accepts
the [Consumer\<Throwable\>] function that handles any exception
raised anywhere in the pipeline. It works similar to the all-embracing
[try-catch] block. If you have this function passed into the
[subscribe()] operator, you can be sure that is the only place
where all exceptions will end up.

But, if you need to handle the exceptions in the middle of the pipeline,
so the values flow can be recovered and processed by the rest of the
operators, after the operator that has thrown the exception, the
following operators (and their multiple overloaded versions) can help
with that:

-   [onErrorXXX()]: Resumes the provided sequence when
    an exception was caught; XXX indicates what the operator
    does: [onErrorResumeNext()], [onErrorReturn()],
    or [onErrorReturnItem()]
-   [retry()]: Creates an [Observable] that repeats the
    emissions emitted from the source; re-subscribes to the source
    [Observable] if it calls [onError()]

The demo code looks as follows:


```
Observable<String> obs = Observable.just("one")
                     .flatMap(s -> Observable.fromArray(s.split("")));
Observable.error(new RuntimeException("MyException"))
          .flatMap(x -> Observable.fromArray("two".split("")))
          .subscribe(System.out::print,
           e -> System.out.println(e.getMessage())//prints: MyException
          );
Observable.error(new RuntimeException("MyException"))
          .flatMap(y -> Observable.fromArray("two".split("")))
          .onErrorResumeNext(obs)
          .subscribe(System.out::print);          //prints: one
Observable.error(new RuntimeException("MyException"))
          .flatMap(z -> Observable.fromArray("two".split("")))
          .onErrorReturnItem("42")
          .subscribe(System.out::print);          //prints: 42
```

Life cycle events handling {#life-cycle-events-handling .header-title}
--------------------------

These operators are invoked each on a certain event that happened
anywhere in the pipeline. They work similarly to the operators described
in the *Exceptions handling* section.

The format of these operators is [doXXX()], where XXX is the name
of the event: [onComplete], [onNext], [onError], and
similar. Not all of them are available in all the classes, and some of
them are slightly different in [Observable], [Flowable],
[Single], [Maybe], or [Completable]. But, we do not
have space to list all the variations of all these classes and will
limit our overview to a few examples of the life cycle events-handling
operators of the [Observable] class:

-   [doOnSubscribe(Consumer\<Disposable\>
    onSubscribe)]: Executes when an observer subscribes
-   [doOnNext(Consumer\<T\> onNext)]: Applies the
    provided [Consumer] function when the source observable
    calls [onNext]
-   [doAfterNext(Consumer\<T\> onAfterNext)]: Applies the provided
    [Consumer] function to the current value after it is pushed
    downstream
-   [doOnEach(Consumer\<Notification\<T\>\>
    onNotification)]: Executes the [Consumer] function for
    each emitted value
-   [doOnEach(Observer\<T\> observer)]: Notifies an
    [Observer] for each emitted value and the terminal event it
    emits
-   [doOnComplete(Action onComplete)]: Executes
    the provided [Action] function after the source observable
    generates the [onComplete] event
-   [doOnDispose(Action onDispose)]: Executes
    the provided [Action] function after the pipeline was disposed
    of by the downstream
-   [doOnError(Consumer\<Throwable\> onError)]: Executes when
    the [onError] event is sent
-   [doOnLifecycle(Consumer\<Disposable\> onSubscribe, Action
    onDispose)]: Calls the corresponding [onSubscribe] or
    [onDispose] function for the corresponding event
-   [doOnTerminate(Action onTerminate)]: Executes
    the provided [Action] function when the source observable
    generates the [onComplete] event or an exception
    ([onError] event) is raised
-   [doAfterTerminate(Action onFinally)]: Executes the provided
    [Action] function after the source observable generates
    the [onComplete] event or an exception ([onError] event)
    is raised
-   [doFinally(Action onFinally)]: Executes the provided
    [Action] function after the source observable generates
    the [onComplete] event or an exception ([onError] event)
    is raised, or the pipeline was disposed of by the downstream

Here is a demo code:


```
Observable<String> obs = Observable.just("one")
            .flatMap(s -> Observable.fromArray(s.split("")));

obs.doOnComplete(() -> System.out.println("Completed!")) 
        .subscribe(v -> {
            System.out.println("Subscribe onComplete: " + v);
        });        
pauseMs(25);
```

If we run this code, the output will be as follows:

![](6_files/929070d6-779c-4885-b06c-e1b7cfcd6664.png)

You will also see other examples of these operators\' usage in the
*Multithreading (scheduler)* section.

Utilities {#utilities .header-title}
---------

Various useful operators (and their multiple overloaded versions) can be
used for controlling the pipeline behavior:

-   [delay()]: Delays the emission by the specified period of time
-   [materialize()]: Creates an [Observable] that represents
    both the emitted values and the notifications sent
-   [dematerialize()]: Reverses the result of
    the [materialize()] operator
-   [observeOn()]: Specifies the [Scheduler] (thread) on
    which the [Observer] should observe the [Observable]
    (see the *Multithreading (scheduler)* section)
-   [serialize()]: Forces serialization of the emitted values
    and notifications
    -   [subscribe()]: Subscribes to the emissions and
        notifications from an observable; various overloaded versions
        accept callbacks used for a variety of events, including
        [onComplete] and [onError]; only after
        [subscribe()] is invoked the values start flowing through
        the pipeline
-   [subscribeOn()]: Subscribes the [Observer] to the
    [Observable] asynchronously using the specified
    [Scheduler] (see the *Multithreading (scheduler)* section)
-   [timeInterval(), timestamp()]: Converts an
    [Observable\<T\>] that emits values into
    [Observable\<Timed\<T\>\>], which, in turn, emits the amount
    of time elapsed between the emissions or the timestamp
    correspondingly
-   [timeout()]: Repeats the emissions of the source
    [Observable]; generates an error if no emissions happen after
    the specified period of time
-   [using()]: Creates a resource that is disposed of
    automatically along with the [Observable]; works similarly to
    the try-with-resources construct

The following code contains a few examples of some of these operators
used in a pipeline:


```
Observable<String> obs = Observable.just("one")
                     .flatMap(s -> Observable.fromArray(s.split("")));
obs.delay(5, TimeUnit.MILLISECONDS)
   .subscribe(System.out::print);                          //prints: one
pauseMs(10);
System.out.println(); //used here just to break the line
Observable source = Observable.range(1,5);
Disposable disposable = source.subscribe();
Observable.using(
  () -> disposable,
  x -> source,
  y -> System.out.println("Disposed: " + y) //prints: Disposed: DISPOSED
)
.delay(10, TimeUnit.MILLISECONDS)
.subscribe(System.out::print);                          //prints: 12345
pauseMs(25);
```

If we run all these examples, the output will look as follows:

![](6_files/df074538-1f00-4620-b111-cd98c848471e.png)

As you can see, the pipeline, when completed, sends
the [DISPOSED] signal to the [using] operator (the
third parameter), so the [Consumer] function we pass as the third
parameter can dispose of the resource used by the pipeline. 

Conditional and Boolean {#conditional-and-boolean .header-title}
-----------------------

The following operators (and their multiple overloaded versions) allow
the evaluating of one or more observables or emitted values and change
the logic of the processing accordingly:

-   [all(Predicate criteria)]: Returns [Single\<Boolean\>]
    with a [true] value, if all the emitted values match the
    provided criteria
-   [amb()]: Accepts two or more source observables and emits
    values from only the first of them that starts emitting
-   [contains(Object
    value)]: Returns [Single\<Boolean\>] with [true],
    if the observable emits the provided value
-   [defaultIfEmpty(T value)]: Emits the provided value if the
    source [Observable] does not emit anything
-   [sequenceEqual()]: Returns [Single\<Boolean\>] with [true],
    if the provided sources emit the same sequence; an overloaded
    version allows to provide the equality function used for comparison
-   [skipUntil(Observable other)]: Discards emitted values until
    the provided [Observable other] emits a value
-   [skipWhile(Predicate condition)]: Discards emitted values as
    long as the provided condition remains [true]
-   [takeUntil(Observable other)]: Discards emitted values after
    the provided [Observable other] emits a value
-   [takeWhile(Predicate condition)]: Discards emitted values
    after the provided condition became [false]

This code contains a few demo examples:


```
Observable<String> obs = Observable.just("one")
                  .flatMap(s -> Observable.fromArray(s.split("")));
Single<Boolean> cont = obs.contains("n");
System.out.println(cont.blockingGet());             //prints: true
obs.defaultIfEmpty("two")
   .subscribe(System.out::print);                   //prints: one
Observable.empty().defaultIfEmpty("two")
          .subscribe(System.out::print);            //prints: two

Single<Boolean> equal = Observable.sequenceEqual(obs, 
                                 Observable.just("one"));
System.out.println(equal.blockingGet());            //prints: false

equal = Observable.sequenceEqual(Observable.just("one"), 
                                 Observable.just("one"));
System.out.println(equal.blockingGet());           //prints: true

equal = Observable.sequenceEqual(Observable.just("one"), 
                                 Observable.just("two"));
System.out.println(equal.blockingGet());           //prints: false
```

Backpressure {#backpressure .header-title}
------------

We have discussed and demonstrated the **backpressure** effect and the
possible drop strategy in *Cold versus hot* section. The other strategy
may be as follows:


```
Flowable<Double> obs = Flowable.fromArray(1.,2.,3.);
obs.onBackpressureBuffer().subscribe();
//or
obs.onBackpressureLatest().subscribe();
```

The buffering strategy allows defining the buffer size and providing a
function that can be executed if the buffer overflows. The latest
strategy tells the values producer to pause (when the consumer cannot
process the emitted values on time) and emit the next value on request.

The backpressure operators are available only in the [Flowable]
class.

Connectable  {#connectable .header-title}
------------

The operators of this category allow connecting observables and thus
achieve more precisely-controlled subscription dynamics:

-   [publish()]: Converts an [Observable] object into a
    [ConnectableObservable] object
-   [replay()]: Returns a [ConnectableObservable] object
    that repeats all the emitted values and notifications every time a
    new [Observer] subscribes
-   [connect()]: Instructs a [ConnectableObservable] to
    begin emitting values to the subscribers
-   [refCount()]: Converts a [ConnectableObservable] to
    an [Observable]

We have demonstrated how [ConnectableObservable] works in the
*Cold versus hot* section. One principal difference between
[ConnectiableObservable] and [Observable] is that
[ConnectableObservable] does not start emitting value until its
[connect] operator is called.

Multithreading (scheduler) {#multithreading-scheduler .header-title}
--------------------------

RxJava is single-threaded by default. This means that the source
observable and all its operators notify the observers on the same thread
on which the [subscribe()] operator is called.

Тhere are two operators, [observeOn()] and
[subscribeOn()], that allow moving the executing of individual
actions to a different thread. These methods take as an argument a
[Scheduler] object that, well, schedules the individual actions to
be executed on a different thread.


The [subscribeOn()] operator declares which scheduler should emit
the values.\
The [observeOn()] operator declares which scheduler should observe
and process values.


The [Schedulers] class contains factory methods that create
[Scheduler] objects with different life cycles and performance
configuration:

-   [computation()]: Creates a scheduler based on a bounded thread
    pool with a size up to the number of available processors; it should
    be used for CPU-intensive computations;
    use [Runtime.getRuntime().availableProcessors()] to avoid
    using more this type of schedulers than available processors;
    otherwise, the performance may degrade because of the overhead of
    the thread-context switching
-   [io()]: Creates a scheduler based on an unbounded thread pool
    used for I/O-related work, such as working with files and databases
    in general when the interaction with the source is blocking by
    nature; avoid using it otherwise, because it may spin too many
    threads and negatively affect performance and memory usage
-   [newThread()]: Creates a new thread every time and does not
    use any pool; it is an expensive way to create a thread, so you are
    expected to know exactly what is the reason for using it
-   [single()]: Creates a scheduler based on a single thread that
    executes all the tasks sequentially; useful when the sequence of the
    execution matters
-   [trampoline()]: Creates a scheduler that executes tasks in a
    first-in-first-out manner; useful for executing recursive algorithms
-   [from(Executor executor)]: Creates a scheduler based on the
    provided executor (thread pool), which allows for better controlling
    the max number of threads and their life cycles. We talked about
    thread pools in [Chapter
    8](https://subscription.packtpub.com/book/programming/9781789957051/8),
    *Multithreading and Concurrent Processing*. To remind you, here are
    the pools we have discussed:


```
          Executors.newCachedThreadPool();
          Executors.newSingleThreadExecutor();
          Executors.newFixedThreadPool(int nThreads);
          Executors.newScheduledThreadPool(int poolSize);
          Executors.newWorkStealingPool(int parallelism);
```

As you can see, some of the other factory methods of the
[Schedulers] class are backed by one of these thread pools and
serves just as a simpler and shorter expression of a thread pool
declaration. To make the examples simpler and comparable, we are going
to use only a [computation()] scheduler. Let\'s look at the basics
of parallel/concurrent processing in RxJava.

The following code is an example of delegating CPU-intensive
calculations to dedicated threads:


```
Observable.fromArray("one","two","three")
          .doAfterNext(s -> System.out.println("1: " + 
                 Thread.currentThread().getName() + " => " + s))
          .flatMap(w -> Observable.fromArray(w.split(""))
                           .observeOn(Schedulers.computation())
              //.flatMap(s -> {             
              //      CPU-intensive calculations go here
              // }  
                .doAfterNext(s -> System.out.println("2: " + 
                         Thread.currentThread().getName() + " => " + s))
          )
          .subscribe(s -> System.out.println("3: " + s));
pauseMs(100);
```

In this example, we decided to create a sub-flow of characters from each
emitted word and let a dedicated thread process characters of each word.
The output of this example looks as follows:

![](6_files/57d488fe-4d0a-4ded-a88f-ec5cee99e3ad.png)

As you can see, the main thread was used to emit the words, and the
characters of each word were processed by a dedicated thread. Please
notice that although in this example the sequence of the results coming
to the [subscribe()] operation corresponds to the sequence the
words and characters were emitted, in real-life cases, the calculation
time of each value will not be the same, so there is no guarantee that
the results will come in the same sequence.

If need be, we can put each word emission on a dedicated non-main thread
too, so the main thread can be free to do what else can be done. For
example, note the following:


```
Observable.fromArray("one","two","three")
        .observeOn(Schedulers.computation())
        .doAfterNext(s -> System.out.println("1: " + 
                         Thread.currentThread().getName() + " => " + s))
        .flatMap(w -> Observable.fromArray(w.split(""))
                .observeOn(Schedulers.computation())
                .doAfterNext(s -> System.out.println("2: " + 
                         Thread.currentThread().getName() + " => " + s))
        )
        .subscribe(s -> System.out.println("3: " + s));
pauseMs(100);
```

The output of this example is as follows:

![](6_files/24e6722c-156c-4952-97c9-0542c0115dd9.png)

As you can see, the main thread does not emit the words anymore.

In RxJava 2.0.5, a new simpler way of parallel processing was
introduced, similar to the parallel processing in the standard Java 8
streams. Using [ParallelFlowable], the same functionality can be
achieved as follows:


```
ParallelFlowable src = 
                     Flowable.fromArray("one","two","three").parallel();
src.runOn(Schedulers.computation())
   .doAfterNext(s -> System.out.println("1: " + 
                        Thread.currentThread().getName() + " => " + s))
   .flatMap(w -> Flowable.fromArray(((String)w).split("")))
   .runOn(Schedulers.computation())
   .doAfterNext(s -> System.out.println("2: " + 
                        Thread.currentThread().getName() + " => " + s))
   .sequential()
   .subscribe(s -> System.out.println("3: " + s));
pauseMs(100);
```

As you can see, the [ParallelFlowable] object is created by
applying the [parallel()] operator to the regular
[Flowable]. Then, the [runOn()] operator tells the created
observable to use the [computation()] scheduler for emitting the
values. Please notice that there is no need anymore to set another
scheduler (for processing the characters) inside the [flatMap()]
operator. It can be set outside it -- just in the main pipeline, which
makes the code simpler. The result looks like this:

![](6_files/0d9a69ea-9164-4067-a4cf-a0c788ccc54b.png)

As for the [subscribeOn()] operator, its location in the pipeline
does not play any role. Wherever it is placed, it still tells the
observable which scheduler should emit the values. Here is an example:


```
Observable.just("a", "b", "c")
          .doAfterNext(s -> System.out.println("1: " + 
                         Thread.currentThread().getName() + " => " + s))
          .subscribeOn(Schedulers.computation())
          .subscribe(s -> System.out.println("2: " + 
                        Thread.currentThread().getName() + " => " + s));
pauseMs(100);
```

The result looks like this:

![](6_files/247bfa6a-3899-46f7-865e-bbe7a13cff8f.png)

Even if we change the location of the [subscribeOn()] operator as
in the following example, the result does not change:


```
Observable.just("a", "b", "c")
          .subscribeOn(Schedulers.computation())
          .doAfterNext(s -> System.out.println("1: " + 
                         Thread.currentThread().getName() + " => " + s))
          .subscribe(s -> System.out.println("2: " + 
                         Thread.currentThread().getName() + " => " + s));
pauseMs(100);
```

And, finally, here is the example with both operators:


```
Observable.just("a", "b", "c")
          .subscribeOn(Schedulers.computation())
          .doAfterNext(s -> System.out.println("1: " + 
                       Thread.currentThread().getName() + " => " + s))
          .observeOn(Schedulers.computation())
          .subscribe(s -> System.out.println("2: " + 
                      Thread.currentThread().getName() + " => " + s));
pauseMs(100);
```

The result now shows that two threads are used: one for subscribing and
another for observing:

![](6_files/0a8ab8db-ecac-44f0-8bbc-b9ff7235b83d.png)

This concludes our short overview of RxJava, which is a big and
still-growing library with a lot of possibilities, many of which we just
did not have space in this book to review. We encourage you to try and
learn it because it seems that reactive programming is the way modern
data processing is heading.

Summary
====================

In this chapter, the reader has learned what reactive programming is and
what its main concepts are: asynchronous, non-blocking, responsive, and
so on. The reactive streams were introduced and explained in simple
terms, as well as the RxJava library, the first solid implementation
that supports reactive programming principles.

In the next chapter, we will talk about microservices as the foundation
for creating reactive systems and will review another library that
successfully supports reactive programming: **Vert.x**. We will use it
to demonstrate how various microservices can be built.

