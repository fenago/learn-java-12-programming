Quiz
====================

1.  Select all the correct statements:

-   a.  Asynchronous processing always provides results later.
    b.  Asynchronous processing always provides responses quickly.
    c.  Asynchronous processing can use parallel processing.
    d.  Asynchronous processing always provides results faster than a
        blocking call.

2.  Can [CompletableFuture] be used without using a thread pool?
3.  What does *nio* in [java.nio] stand for?
4.  Is an [event] loop the only design that supports a
    non-blocking API?
5.  What does the *Rx* in RxJava stand for?
6.  Which Java package of **Java Class Library** (**JCL**) supports
    reactive streams?
7.  Select all classes from the following list that can represent an
    observable in a reactive stream:
    a.  [Flowable]
    b.  [Probably]
    c.  [CompletableFuture]
    d.  [Single]


8.  How do you know that the particular method (operator) of the
    [Observable] class is blocking?
9.  What is the difference between a cold and a hot observable?
10. The [subscribe()] method of [Observable] returns
    the [Disposable] object. What happens when
    the [dispose()] method is called on this object?
11. Select all the names of the methods that create an
    [Observable] object:

-   a.  [interval()]
    b.  [new()]
    c.  [generate()]
    d.  [defer()]

12. Name two transforming [Observable] operators.
13. Name two filtering [Observable] operators.
14. Name two backpressure-processing strategies.
15. Name two [Observable] operators that allow adding threads to
    the pipeline processing.
