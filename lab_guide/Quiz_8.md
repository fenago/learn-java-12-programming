
Quiz
========================================


1.  Select all correct statements:
    1.  JVM process can have main threads.
    2.  Main thread is the main process.
    3.  A process can launch another process.
    4.  A thread may launch another thread.


2.  Select all correct statements:
    1.  A daemon is a user thread.
    2.  A daemon thread exits after the first user thread completes.
    3.  A daemon thread exits after the last user thread completes.
    4.  Main thread is a user thread.


3.  Select all correct statements:
    1.  All threads have [java.lang.Thread] as a base class.
    2.  All threads extend [java.lang.Thread].
    3.  All threads implement [java.lang.Thread].
    4.  Daemon thread does not extend [java.lang.Thread].


4.  Select all correct statements:
    1.  Any class can implement the [Runnable] interface.
    2.  The [Runnable] interface implementation is a thread.
    3.  The [Runnable] interface implementation is used by a
        thread.
    4.  The [Runnable] interface has only one method.


5.  Select all correct statements:
    1.  A thread name has to be unique.
    2.  A thread ID is generated automatically.
    3.  A thread name can be set.
    4.  A thread priority can be set.


6.  Select all correct statements:
    1.  A thread pool executes threads.
    2.  A thread pool reuses threads.
    3.  Some thread pools can have a fixed count of threads.
    4.  Some thread pools can have an unlimited count of threads.


7.  Select all correct statements:
    1.  A [Future] object is the only way to get the result from a
        thread.
    2.  A [Callable] object is the only way to get the result from
        a thread.
    3.  A [Callable] object allows getting the result from a
        thread.
    4.  A [Future] object represents a thread.


8.  Select all correct statements:
    1.  Concurrent processing can be done in parallel.
    2.  Parallel processing is possible only with several CPUs or cores
        available on the computer.
    3.  Parallel processing is concurrent processing.
    4.  Without multiple CPU, concurrent processing is impossible.


9.  Select all correct statements:
    1.  Concurrent modification always leads to incorrect results.
    2.  An atomic variable protects the property from concurrent
        modification.
    3.  An atomic variable protects the property from the thread
        interference.
    4.  An atomic variable is the only way to protect the property from
        concurrent modification.


10. Select all correct statements:
    1.  A [synchronized] method is the best way to avoid thread
        interference.
    2.  A [synchronized] keyword can be applied to any method.
    3.  A [synchronized] method can create a processing
        bottleneck.
    4.  A [synchronized] method is easy to implement.


11. Select all correct statements:
    1.  A [synchronized] block makes sense only when it is smaller
        than the method.
    2.  A [synchronized] block requires shared lock.
    3.  Every Java object can provide a lock.
    4.  A [synchronized] block is the best way to avoid thread
        interference.


12. Select all correct statements:
    1.  Using a concurrent collection is preferred rather than using a
        non-concurrent one.
    2.  Using a concurrent collection incurs some overhead.
    3.  Not every concurrent collection fits every concurrent processing
        scenario.
    4.  One can create a concurrent collection by calling
        [Collections.makeConcurrent()] method.


13. Select all correct statements:
    1.  The only way to avoid memory consistency error is to declare
        the [volatile] variable.
    2.  Using the [volatile] keyword guarantees visibility of the
        value change across all threads.
    3.  One of the ways to avoid concurrency is to avoid any state
        management.
    4.  Stateless utility methods cannot have concurrency issues.
