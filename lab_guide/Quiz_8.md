
Quiz
========================================


1.  Select all correct statements:
    a.  JVM process can have main threads.
    b.  Main thread is the main process.
    c.  A process can launch another process.
    d.  A thread may launch another thread.


2.  Select all correct statements:
    a.  A daemon is a user thread.
    b.  A daemon thread exits after the first user thread completes.
    c.  A daemon thread exits after the last user thread completes.
    d.  Main thread is a user thread.


3.  Select all correct statements:
    a.  All threads have [java.lang.Thread] as a base class.
    b.  All threads extend [java.lang.Thread].
    c.  All threads implement [java.lang.Thread].
    d.  Daemon thread does not extend [java.lang.Thread].


4.  Select all correct statements:
    a.  Any class can implement the [Runnable] interface.
    b.  The [Runnable] interface implementation is a thread.
    c.  The [Runnable] interface implementation is used by a
        thread.
    d.  The [Runnable] interface has only one method.


5.  Select all correct statements:
    a.  A thread name has to be unique.
    b.  A thread ID is generated automatically.
    c.  A thread name can be set.
    d.  A thread priority can be set.


6.  Select all correct statements:
    a.  A thread pool executes threads.
    b.  A thread pool reuses threads.
    c.  Some thread pools can have a fixed count of threads.
    d.  Some thread pools can have an unlimited count of threads.


7.  Select all correct statements:
    a.  A [Future] object is the only way to get the result from a
        thread.
    b.  A [Callable] object is the only way to get the result from
        a thread.
    c.  A [Callable] object allows getting the result from a
        thread.
    d.  A [Future] object represents a thread.


8.  Select all correct statements:
    a.  Concurrent processing can be done in parallel.
    b.  Parallel processing is possible only with several CPUs or cores
        available on the computer.
    c.  Parallel processing is concurrent processing.
    d.  Without multiple CPU, concurrent processing is impossible.


9.  Select all correct statements:
    a.  Concurrent modification always leads to incorrect results.
    b.  An atomic variable protects the property from concurrent
        modification.
    c.  An atomic variable protects the property from the thread
        interference.
    d.  An atomic variable is the only way to protect the property from
        concurrent modification.


10. Select all correct statements:
    a.  A [synchronized] method is the best way to avoid thread
        interference.
    b.  A [synchronized] keyword can be applied to any method.
    c.  A [synchronized] method can create a processing
        bottleneck.
    d.  A [synchronized] method is easy to implement.


11. Select all correct statements:
    a.  A [synchronized] block makes sense only when it is smaller
        than the method.
    b.  A [synchronized] block requires shared lock.
    c.  Every Java object can provide a lock.
    d.  A [synchronized] block is the best way to avoid thread
        interference.


12. Select all correct statements:
    a.  Using a concurrent collection is preferred rather than using a
        non-concurrent one.
    b.  Using a concurrent collection incurs some overhead.
    c.  Not every concurrent collection fits every concurrent processing
        scenario.
    d.  One can create a concurrent collection by calling
        [Collections.makeConcurrent()] method.


13. Select all correct statements:
    a.  The only way to avoid memory consistency error is to declare
        the [volatile] variable.
    b.  Using the [volatile] keyword guarantees visibility of the
        value change across all threads.
    c.  One of the ways to avoid concurrency is to avoid any state
        management.
    d.  Stateless utility methods cannot have concurrency issues.
