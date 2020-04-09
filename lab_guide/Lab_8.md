<img align="right" src="../logo-small.png">


Multithreading and Concurrent Processing 
========================================

In this lab, we will discuss the ways to increase Java application
performance by using the workers (threads) that process data
concurrently. We will explain the concept of Java threads and
demonstrate their usage. We will also talk about the difference between
parallel and concurrent processing and how to avoid unpredictable
results caused by the concurrent modification of the shared resource.

The following topics will be covered in this lab:

-   Thread vs process
-   User thread vs daemon 
-   Extending class Thread
-   Implementing interface [Runnable]
-   Extending Thread vs implementing [Runnable]
-   Using pool of threads
-   Getting results from a thread
-   Parallel vs concurrent processing
-   Concurrent modification of the same resource

#### Pre-reqs:
- Google Chrome (Recommended)

#### Lab Environment
There is no requirement for any setup.

**Note:** Terminal(s) are already running. You can also open new terminal by clicking:
`File` > `New` > `Terminal`.

All labs are present in `work/learn-java-12-programming` folder. To copy and paste: use **Control-C** and to paste inside of a terminal, use **Control-V**

<h2><span style="color:red;">Instructions:</span></h2>

- Quiz and its answers can be found in the files which are already opened in jupyertLab editor.
- We will be using `mvn` to compile source code and `java` cli to run java code in the lab.
- Intructions below also contain instructions how to use IntelliJ IDEA for local development.
- Make sure to run `cd ~/work/learn-java-12-programming/exercises` command in the terminal(s) before compiling and running code. 
- Run following command to compile and package java project everytime after making change in java source:  `mvn clean package` . You can also run following commands first to clear any changes you made in java project and sync with remote.

`cd ~/work/learn-java-12-programming && git stash && git pull`


<h3><span style="color:red;">Run Java Code</span></h3>

You can run the example by running following command in the terminal:
`java -cp target/learnjava-1.0.jar:target/libs/* com.lv.learnjava.ch08_threads.ConcurrentCollections`

<h3><span style="color:red;">Run Java Code</span></h3>

You can run the example by running following command in the terminal:
`java -cp target/learnjava-1.0.jar:target/libs/* com.lv.learnjava.ch08_threads.Futures`

<h3><span style="color:red;">Run Java Code</span></h3>

You can run the example by running following command in the terminal:
`java -cp target/learnjava-1.0.jar:target/libs/* com.lv.learnjava.ch08_threads.Pools`

<h3><span style="color:red;">Run Java Code</span></h3>

You can run the example by running following command in the terminal:
`java -cp target/learnjava-1.0.jar:target/libs/* com.lv.learnjava.ch08_threads.Synchronization`

<h3><span style="color:red;">Run Java Code</span></h3>

You can run the example by running following command in the terminal:
`java -cp target/learnjava-1.0.jar:target/libs/* com.lv.learnjava.ch08_threads.Threads`

Thread versus process
========================================

Java has two units of execution---process and thread. A **process**
usually represents the whole JVM, although an application can create
another process using [java.lang.ProcessBuilder]. But, since the
multi-process case is outside the scope of this course, we will focus on
the second unit of execution, that is, a **thread**, which is similar to
a process but less isolated from other threads and requires fewer
resources for execution.

A process can have many threads running and at least one thread called
the **main thread**---the one that starts the application---which we use
it in every example. Threads can share resources, including memory and
open files, which allows for better efficiency. But it comes with a
price of higher risk of unintended mutual interference and even blocking
of the execution. That is where programming skills and an understanding
of the concurrency techniques are required. 

User thread versus daemon
========================================

There is a particular kind of thread called a daemon.


The word daemon has an ancient Greek origin meaning *a divinity or
supernatural being of a nature between gods and humans* and *an inner or
attendant spirit or inspiring force*.


In computer science, the term **daemon** has more mundane usage and is
applied to *a computer program that runs as a background process, rather
than being under the direct control of an interactive user*. That is why
there are the following two types of threads in Java:

-   User thread (default), initiated by an application (main thread is
    one such an example)
-   Daemon thread that works in the background in support of user thread
    activity

That is why all daemon threads exit immediately after the last user
thread exits or are terminated by JVM after an unhandled exception.

Extending class thread
========================================

One way to create a thread is to extend the [java.lang.Thread]
class and override its [run()] method. For example:


```
class MyThread extends Thread {
    private String parameter;
    public MyThread(String parameter) {
        this.parameter = parameter;
    }
    public void run() {
        while(!"exit".equals(parameter)){
            System.out.println((isDaemon() ? "daemon" : "  user") +
              " thread " + this.getName() + "(id=" + this.getId() +
                                      ") parameter: " + parameter);
            pauseOneSecond();
        }
        System.out.println((isDaemon() ? "daemon" : "  user") +
          " thread " + this.getName() + "(id=" + this.getId() +
                                  ") parameter: " + parameter);
    }
    public void setParameter(String parameter) {
        this.parameter = parameter;
    }
}
```

If the [run()] method is not overridden, the thread does nothing.
In our example, the thread prints its name and other properties every
second as long as the parameter is not equal to string [\"exit\"];
otherwise it exits. The [pauseOneSecond()] method looks as
follows:


```
private static void pauseOneSecond(){
    try {
        TimeUnit.SECONDS.sleep(1);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
}
```

We can now use the [MyThread] class to run two threads---one user
thread and one daemon thread:


```
public static void main(String... args) {
    MyThread thr1 = new MyThread("One");
    thr1.start();
    MyThread thr2 = new MyThread("Two");
    thr2.setDaemon(true);
    thr2.start();
    pauseOneSecond();
    thr1.setParameter("exit");
    pauseOneSecond();
    System.out.println("Main thread exists");
}
```

As you can see, the main thread creates two other threads, pauses for
one second, sets parameter [exit] on the user thread, pauses
another second, and, finally, exits. (The [main()] method
completes its execution.)

If we run the preceding code, we see something like the following
screenshot (The thread [id] may be different in different
operating systems.):

![]./images_8/64070a3c-f2f0-4470-9c9d-f0bf79dee502.png)

The preceding screenshot shows that the daemon thread exits
automatically as soon as the last user thread (main thread in our
example) exits.

Implementing interface Runnable
========================================

The second way to create a thread is to use a class that implements
[java.lang.Runnable]. Here is an example of such a class that has
almost exactly the same functionality as [MyThread] class:


```
class MyRunnable implements Runnable {
    private String parameter, name;
    public MyRunnable(String name) {
        this.name = name;
    }
    public void run() {
        while(!"exit".equals(parameter)){
            System.out.println("thread " + this.name + 
                               ", parameter: " + parameter);
            pauseOneSecond();
        }
        System.out.println("thread " + this.name +
                              ", parameter: " + parameter);
    }
    public void setParameter(String parameter) {
        this.parameter = parameter;
    }
}
```

The difference is that there is
no [isDaemon()] method, [getId()], or any other
out-of-box method. The [MyRunnable] class can be any class that
implements the [Runnable] interface, so we cannot print whether
the thread is daemon or not. That is why we have added
the [name] property, so we can identify the thread.

We can use the [MyRunnable] class to create threads similar to how
we have used the [MyThread] class:


```
public static void main(String... args) {
    MyRunnable myRunnable1 = new MyRunnable("One");
    MyRunnable myRunnable2 = new MyRunnable("Two");

    Thread thr1 = new Thread(myRunnable1);
    thr1.start();
    Thread thr2 = new Thread(myRunnable2);
    thr2.setDaemon(true);
    thr2.start();
    pauseOneSecond();
    myRunnable1.setParameter("exit");
    pauseOneSecond();
    System.out.println("Main thread exists");
}
```

The following screenshot proves that the behavior of
the [MyRunnable] class is similar to the behavior of the
[MyThread] class:

![]./images_8/f0ae87f4-c7cf-4ae8-88ad-2f9352b9f80a.png)

The daemon thread (named [Two]) exits after the last user thread
exists---exactly how it happened with the [MyThread] class.

Extending thread vs implementing Runnable
========================================

Implementation of [Runnable] has the advantage (and in some cases
the only possible option) of allowing the implementation to extend
another class. It is particularly helpful when you would like to add
thread-like behavior to an existing class. Implementing [Runnable]
allows more flexibility in usage. But otherwise, there is no difference
in functionality comparing to the extending of the [Thread] class.

[Thread] class has several constructors that allow setting the
thread name and the group it belongs to. Grouping of threads helps to
manage them in the case of many threads running in
parallel. [Thread] class has also several methods that provide
information about the thread\'s status, its properties, and allows to
control its behavior.

As you have seen, the thread\'s ID is generated automatically. It cannot
be changed but can be reused after the thread is terminated. Several
threads, on the other hand, can be set with the same name.

The execution priority can also be set programmatically with a value
between [Thread.MIN\_PRIORITY] and [Thread.MAX\_PRIORITY].
The smaller the value, the more time the thread is allowed to run, which
means it has higher priority. If not set, the priority value defaults to
[Thread.NORM\_PRIORITY].

The state of a thread can have one of the following values:

-   [NEW]: When a thread has not yet started
-   [RUNNABLE]: When a thread is being executed
-   [BLOCKED]: When a thread is blocked and is waiting for a
    monitor lock
-   [WAITING]: When a thread is waiting indefinitely for another
    thread to perform a particular action
-   [TIMED\_WAITING]: When a thread is waiting for another thread
    to perform an action for up to a specified waiting time
-   [TERMINATED]: When a thread has exited

Threads and any objects for that matter also can *talk to each
other* using the methods [wait()], [notify()], and
[notifyAll()] of the [java.lang.Object] base class. But this
aspect of threads\' behavior is outside the scope of this course.

Using pool of threads 
========================================

Each thread requires resources---**CPU** and **memory**. It means the
number of threads must be controlled, and one way to do it is to create
a fixed number of them---a pool. Besides, creating an object incurs an
overhead that may be significant for some applications. 

In this section, we will look into the [Executor] interfaces and
their implementations provided in the [java.util.concurrent]
package. They encapsulate thread management and minimize the time an
application developer spends on writing the code related to threads\'
life cycles.

There are three [Executor] interfaces defined in the
[java.util.concurrent] package:

-   The base [Executor] interface: It has only one [void
    execute(Runnable r)] method in it.
-   The [ExecutorService] interface: It extends [Executor]
    and adds four groups of methods that manage the life cycle of the
    worker threads and of the executor itself:
    -   [submit()] methods that place a [Runnable] or
        [Callable] object in the queue for the execution
        ([Callable] allows the worker thread to return a value),
        return an object of [Future] interface, which can be used
        to access the value returned by the [Callable], and to
        manage the status of the worker thread
    -   [invokeAll()] methods that place a collection of objects
        of [Callable] interface in the queue for the execution
        which then returns [List] of [Future] objects when
        all the worker threads are complete (there is also an overloaded
        [invokeAll()] method with a timeout)
    -   [invokeAny()] methods that place a collection of
        interface [Callable] objects in the queue for the
        execution; return one [Future] object of any of the worker
        threads, which has completed (there is also an overloaded
        [invokeAny()] method with a timeout)
    -   Methods that manage the worker threads\' status and the service
        itself as follows:
        -   [shutdown()]: Prevents new worker threads from being
            submitted to the service.
        -   [shutdownNow()]: Interrupts each worker thread that is
            not completed. A worker thread should be written so that it
            checks its own status periodically (using
            [Thread.currentThread().isInterrupted()], for example)
            and gracefully shuts down on its own; otherwise, it will
            continue running even after [shutdownNow()] was
            called.
        -   [isShutdown()]: Checks whether the shutdown of the
            executor was initiated.
        -   [awaitTermination(long timeout, TimeUnit timeUnit)]:
            Waits until all worker threads have completed execution
            after a shutdown request, or the timeout occurs, or the
            current thread is interrupted, whichever happens first.
        -   [isTerminated()]: Checks whether all the worker
            threads have completed after the shutdown was initiated. It
            never returns [true] unless either [shutdown()]
            or [shutdownNow()] was called first.


-   The [ScheduledExecutorService] interface: It extends
    [ExecutorService] and adds methods that allow scheduling of
    the execution (one-time and periodic one) of the worker threads.

A pool-based implementation of [ExecutorService] can be created
using the [java.util.concurrent.ThreadPoolExecutor] or
[java.util.concurrent.ScheduledThreadPoolExecutor] class. There is
also a [java.util.concurrent.Executors] factory class that covers
most of the practical cases. So, before writing custom code for worker
threads\' pool creation, we highly recommend looking into using the
following factory methods of the [java.util.concurrent.Executors]
class:

-   [newCachedThreadPool()] that creates a thread pool that adds a
    new thread as needed, unless there is an idle thread created before;
    threads that have been idle for 60 seconds are removed from the pool
-   [newSingleThreadExecutor()] that creates an
    [ExecutorService] (pool) instance that executes worker threads
    sequentially
-   [newSingleThreadScheduledExecutor()] that creates a
    single-threaded executor that can be scheduled to run after a given
    delay, or to execute periodically
-   [newFixedThreadPool(int nThreads)] that creates a thread pool
    that reuses a fixed number of worker threads; if a new task is
    submitted when all the worker threads are still executing, it will
    be placed into the queue until a worker thread is available
-   [newScheduledThreadPool(int nThreads)] that creates a thread
    pool of a fixed size that can be scheduled to run after a given
    delay, or to execute periodically
-   [newWorkStealingThreadPool(int nThreads)] that creates a
    thread pool that uses the *work-stealing* algorithm used by
    [ForkJoinPool], which is particularly useful in case the
    worker threads generate other threads, such as in a recursive
    algorithm; it also adapts to the specified number of CPUs, which you
    may set higher or lower than the actual CPUs count on your computer


[Work-stealing algorithm]\
\
A work-stealing algorithm allows threads that have finished their
assigned tasks to help other tasks that are still busy with their
assignments. As an example, see the description of Fork/Join
implementation in the official Oracle Java
documentation (<https://docs.oracle.com/javase/tutorial/essential/concurrency/forkjoin.html>).


Each of these methods has an overloaded version that allows passing in a
[ThreadFactory] that is used to create a new thread when needed.
Let\'s see how it all works in a code sample. First, we run another
version of [MyRunnable] class:


```
class MyRunnable implements Runnable {
    private String name;
    public MyRunnable(String name) {
        this.name = name;
    }
    public void run() {
        try {
            while (true) {
                System.out.println(this.name + " is working...");
                TimeUnit.SECONDS.sleep(1);
            }
        } catch (InterruptedException e) {
            System.out.println(this.name + " was interrupted\n" +
                this.name + " Thread.currentThread().isInterrupted()="
                            + Thread.currentThread().isInterrupted());
        }
    }
}
```

We cannot use [parameter] property anymore to tell the thread to
stop executing because the thread life cycle is now going to be
controlled by the [ExecutorService], and the way it does it is by
calling the [interrupt()] thread method. Also, notice that the
thread we created has an infinite loop, so it will never stop executing
until forced to (by calling the [interrupt()] method).

Let\'s write the code that does the following:

1.  Creates a pool of three threads
2.  Makes sure the pool does not accept more threads
3.  Waits for a fixed period of time to let all the threads finish what
    they do
4.  Stops (interrupts) the threads that did not finish what they do
5.  Exits

The following code performs all the actions described in the preceding
list:


```
ExecutorService pool = Executors.newCachedThreadPool();
String[] names = {"One", "Two", "Three"};
for (int i = 0; i < names.length; i++) {
    pool.execute(new MyRunnable(names[i]));
}
System.out.println("Before shutdown: isShutdown()=" + pool.isShutdown() 
                           + ", isTerminated()=" + pool.isTerminated());
pool.shutdown(); // New threads cannot be added to the pool
//pool.execute(new MyRunnable("Four"));    //RejectedExecutionException
System.out.println("After shutdown: isShutdown()=" + pool.isShutdown() 
                           + ", isTerminated()=" + pool.isTerminated());
try {
    long timeout = 100;
    TimeUnit timeUnit = TimeUnit.MILLISECONDS;
    System.out.println("Waiting all threads completion for "
                                + timeout + " " + timeUnit + "...");
    // Blocks until timeout, or all threads complete execution,
    // or the current thread is interrupted, whichever happens first.
    boolean isTerminated = pool.awaitTermination(timeout, timeUnit);
    System.out.println("isTerminated()=" + isTerminated);
    if (!isTerminated) {
        System.out.println("Calling shutdownNow()...");
        List<Runnable> list = pool.shutdownNow();
        System.out.println(list.size() + " threads running");
        isTerminated = pool.awaitTermination(timeout, timeUnit);
        if (!isTerminated) {
            System.out.println("Some threads are still running");
        }
        System.out.println("Exiting");
    }
} catch (InterruptedException ex) {
    ex.printStackTrace();
}
```

The attempt to add another thread to the pool after
[pool.shutdown()] is called, **generates**
[java.util.concurrent.RejectedExecutionException].

The execution of the preceding code produces the following results:

![]./images_8/49f678c8-65f3-478d-bd52-ba09e44f6068.png)

Notice the [Thread.currentThread().isInterrupted()=false] message
in the preceding screenshot. The thread was interrupted. We know it
because the thread got the [InterruptedException]. Why then does
the [isInterrupted()] method return [false]? That is because
the thread state was cleared immediately after receiving the interrupt
message. We mention it now because it is a source of some programmer
mistakes. For example, if the main thread watches the [MyRunnable]
thread and calls [isInterrupted()] on it, the return value is
going to be [false], which may be misleading after the thread was
interrupted.

So, in the case where another thread may be monitoring the
[MyRunnable] thread, the implementation of [MyRunnable] has
to be changed to the following. Note how
the [interrupt()] method is called in the [catch] block: 


```
class MyRunnable implements Runnable {
   private String name;
   public MyRunnable(String name) {
      this.name = name;
   }
   public void run() {
      try {
         while (true) {
             System.out.println(this.name + " is working...");
             TimeUnit.SECONDS.sleep(1);
         }
      } catch (InterruptedException e) {
         Thread.currentThread().interrupt();
         System.out.println(this.name + " was interrupted\n" +
           this.name + " Thread.currentThread().isInterrupted()="
                       + Thread.currentThread().isInterrupted());
      }
   }
}
```

Now, if we run this thread using the same [ExecutorService] pool
again, the result will be:

![]./images_8/bf2e32e0-1742-4d59-b187-c309615e6f19.png)

As you can see, now the value returned by the [isInterrupted()]
method is [true] and corresponds to what has happened. To be fair,
in many applications, once the thread is interrupted, its status is not
checked again. But setting the correct state is a good practice,
especially in those cases where you are not the author of the higher
level code that creates the thread.

In our example, we have used a cached thread pool that creates a new
thread as needed or, if available, reuses the thread already used, but
which completed its job and returned to the pool for a new assignment.
We did not worry about too many threads created because our demo
application had three worker threads at the most and they were quite
short lived.

But, in the case where an application does not have a fixed limit of the
worker threads it might need or there is no good way to predict how much
memory a thread may take or how long it can execute, setting a ceiling
on the worker thread count prevents an unexpected degradation of the
application performance, running out of memory, or depletion of any
other resources the worker threads use. If the thread behavior is
extremely unpredictable, a single thread pool might be the only
solution, with an option of using a custom thread pool executor. But, in
the majority of the cases, a fixed-size thread pool executor is a good
practical compromise between the application needs and the code
complexity (earlier in this section, we listed all possible pool types
created by [Executors] factory class). 

Setting the size of the pool too low may deprive the application of the
chance to utilize the available resources effectively. So, before
selecting the pool size, it is advisable to spend some time monitoring
the application with the goal of identifying the idiosyncrasy of the
application behavior. In fact, the cycle deploy-monitor-adjust has to be
repeated throughout the application\'s lifecycle in order to accommodate
and take advantage of the changes that happened in the code or the
executing environment.

The first characteristic you take into account is the number of CPUs in
your system, so the thread pool size can be at least as big as the
CPU\'s count. Then, you can monitor the application and see how much
time each thread engages the CPU and how much of the time it uses other
resources (such as I/O operations). If the time spent not using the CPU
is comparable with the total executing time of the thread, then you can
increase the pool size by the following ratio: the time CPU was not used
divided by the total executing time. But, that is in the case where
another resource (disk or database) is not a subject of contention
between the threads. If the latter is the case, then you can use that
resource instead of the CPU as the delineating factor.

Assuming the worker threads of your application are not too big or too
long executing, and belong to the mainstream population of the typical
working threads that complete their job in a reasonably short period of
time, you can increase the pool size by adding the (rounded up) ratio of
the desired response time and the time a thread uses the CPU or another
most contentious resource. This means that, with the same desired
response time, the less a thread uses the CPU or another concurrently
accessed resource, the bigger the pool size should be. If the
contentious resource has its own ability to improve concurrent access
(like a connection pool in the database), consider utilizing that
feature first.

If the required number of threads running at the same time changes at
runtime under the different circumstances, you can make the pool size
dynamic and create a new pool with a new size (shutting down the old
pool after all its threads have completed). The recalculation of the
size of a new pool might also be necessary after you add or remove the
available resources. You can use
[Runtime.getRuntime().availableProcessors()] to programmatically
adjust the pool size based on the current count of the available CPUs,
for example.

If none of the ready-to-use thread pool executor implementations that
come with the JDK suit the needs of a particular application, before
writing the thread managing code from scratch, try to use the
[java.util.concurrent.ThreadPoolExecutor] class first. It has
several overloaded constructors.

To give you an idea of its capabilities, here is the constructor with
the biggest number of options:


```
ThreadPoolExecutor (int corePoolSize, 
                    int maximumPoolSize, 
                    long keepAliveTime, 
                    TimeUnit unit, 
                    BlockingQueue<Runnable> workQueue, 
                    ThreadFactory threadFactory, 
                    RejectedExecutionHandler handler)
```

The parameters of the preceding constructor are as follows:

-   [corePoolSize] is the number of threads to keep in the pool,
    even if they are idle, unless the [allowCoreThreadTimeOut(boolean
    value)] method is called with [true] value.
-   [maximumPoolSize] is the maximum number of threads to allow in
    the pool.
-   [keepAliveTime]: When the number of threads is greater than
    the core, this is the maximum time that excess idle threads will
    wait for new tasks before terminating.
-   [unit] is the time unit for the [keepAliveTime]
    argument.
-   [workQueue] is the queue to use for holding tasks before they
    are executed; this queue will hold only the [Runnable] objects
    submitted by the [execute()] method.
-   [threadFactory] is the factory to use when the executor
    creates a new thread.
-   [handler] is the handler to use when the execution is blocked
    because the thread bounds and queue capacities are reached.

Each of the previous constructor parameters except the
[workQueue] can also be set via the corresponding setter after the
object of the [ThreadPoolExecutor] class has been created, thus
allowing more flexibility and dynamic adjustment of the existing pool
characteristics.

Getting results from thread
========================================

In our examples, so far, we used the [execute()] method of
the [ExecutorService] interface to start a thread. In fact, this
method comes from the [Executor] base interface.
Meanwhile, the [ExecutorService] interface has other methods
(listed in the previous *Using pool of threads* section) that can start
threads and get back the results of thread execution.

The object that brings back the result of the thread execution is of
type [Future]---an interface that has the following methods:

-   [V get()]: Blocks until the thread finishes; returns the
    result (*if available*)
-   [V get(long timeout, TimeUnit unit)]: Blocks until the thread
    finishes or the provided timeout is up; returns the result (if
    available)
-   [boolean isDone()]: Returns [true] if the thread has
    finished
-   [boolean cancel(boolean mayInterruptIfRunning)]: Tries to
    cancel execution of the thread; returns [true] if successful;
    returns [false] also in the case the thread had finished
    normally by the time the method was called
-   [boolean isCancelled()]: Returns [true] if the thread
    execution was canceled before it has finished normally

The remark *if available* in the description of the [get()]
method means that the result is not always available in principle, even
when the [get()] method without parameters is called. It all
depends on the method used to produce the [Future] object. Here is
a list of all the methods of [ExecutorService] that return
[Future] object(s):

-   [Future\<?\> submit(Runnable task)]: Submits the thread (task)
    for execution; returns a [Future] representing the task;
    the [get()] method of the returned [Future] object
    returns [null]; for example, let\'s use [MyRunnable]
    class that works only 100 milliseconds:


```
class MyRunnable implements Runnable {
   private String name;
   public MyRunnable(String name) {
     this.name = name;
   }
   public void run() {
     try {
         System.out.println(this.name + " is working...");
         TimeUnit.MILLISECONDS.sleep(100);
         System.out.println(this.name + " is done");
     } catch (InterruptedException e) {
         Thread.currentThread().interrupt();
         System.out.println(this.name + " was interrupted\n" +
           this.name + " Thread.currentThread().isInterrupted()="
                       + Thread.currentThread().isInterrupted());
     }
   }
}
```

And, based on the code examples of the previous section, let\'s create a
method that shuts down the pool and terminates all the threads, if
necessary:


```
void shutdownAndTerminate(ExecutorService pool){
   try {
      long timeout = 100;
      TimeUnit timeUnit = TimeUnit.MILLISECONDS;
      System.out.println("Waiting all threads completion for "
                             + timeout + " " + timeUnit + "...");
      //Blocks until timeout or all threads complete execution, 
      //  or the current thread is interrupted, 
      //  whichever happens first.
      boolean isTerminated = 
                        pool.awaitTermination(timeout, timeUnit);
      System.out.println("isTerminated()=" + isTerminated);
      if (!isTerminated) {
          System.out.println("Calling shutdownNow()...");
          List<Runnable> list = pool.shutdownNow();
          System.out.println(list.size() + " threads running");
          isTerminated = pool.awaitTermination(timeout, timeUnit);
          if (!isTerminated) {
             System.out.println("Some threads are still running");
          }
          System.out.println("Exiting");
      }
   } catch (InterruptedException ex) {
      ex.printStackTrace();
   }
}
```

We will use the preceding [shutdownAndTerminate()] method in a
[finally] block to make sure no running threads were left behind.
And here is the code we are going to execute:


```
ExecutorService pool = Executors.newSingleThreadExecutor();

Future future = pool.submit(new MyRunnable("One"));
System.out.println(future.isDone());         //prints: false
System.out.println(future.isCancelled());    //prints: false
try{
    System.out.println(future.get());        //prints: null
    System.out.println(future.isDone());     //prints: true
    System.out.println(future.isCancelled());//prints: false
} catch (Exception ex){
    ex.printStackTrace();
} finally {
    shutdownAndTerminate(pool);
}
```

The output of this code you can see on this screenshot:

![]./images_8/65e86f9d-ee6e-4dc2-8eff-ffc4176a6b2d.png)

As expected, the [get()] method of the [Future] object
returns [null], because [run()] method of [Runnable]
does not return anything. All we can get back from the
returned [Future] is the information that the task was completed,
or not.

-   [Future\<T\> submit(Runnable task, T result)]: Submits the
    thread (task) for execution; returns a [Future] representing
    the task with the provided [result] in it; for example, we
    will use the following class as the result:


```
class Result {
    private String name;
    private double result;
    public Result(String name, double result) {
        this.name = name;
        this.result = result;
    }
    @Override
    public String toString() {
        return "Result{name=" + name +
                ", result=" + result + "}";
    }
}
```

The following code demonstrates how the default result is returned by
the [Future] returned by the [submit()] method:


```
ExecutorService pool = Executors.newSingleThreadExecutor();
Future<Result> future = pool.submit(new MyRunnable("Two"), 
                                        new Result("Two", 42.));
System.out.println(future.isDone());          //prints: false
System.out.println(future.isCancelled());     //prints: false
try{
    System.out.println(future.get());         //prints: null
    System.out.println(future.isDone());      //prints: true
    System.out.println(future.isCancelled()); //prints: false
} catch (Exception ex){
    ex.printStackTrace();
} finally {
    shutdownAndTerminate(pool);
}
```

If we execute the preceding code, the output is going to be as follows:

![]./images_8/439dde2c-24e8-4b83-94a2-f63463680d1d.png)

As was expected, the [get()] method of [Future] returns the
object passed in as a parameter.

-   [Future\<T\> submit(Callable\<T\> task)]: Submits the thread
    (task) for execution; returns a [Future] representing the task
    with the result produced and returned by the [V call()] method
    of the [Callable] interface; that is the only [Callable]
    method the interface has. For example:


```
class MyCallable implements Callable {
   private String name;
   public MyCallable(String name) {
        this.name = name;
   }
   public Result call() {
      try {
         System.out.println(this.name + " is working...");
         TimeUnit.MILLISECONDS.sleep(100);
         System.out.println(this.name + " is done");
         return new Result(name, 42.42);
      } catch (InterruptedException e) {
         Thread.currentThread().interrupt();
         System.out.println(this.name + " was interrupted\n" +
           this.name + " Thread.currentThread().isInterrupted()="
                       + Thread.currentThread().isInterrupted());
      }
      return null;
   }
```

The result of the preceding code is as follows:

![]./images_8/23c8f3be-7d43-4712-a80d-f4a08f581a89.png)

As you can see, the [get()] method of the [Future] returns
the value produced by the [call()] method of the
[MyCallable] class

-   [List\<Future\<T\>\> invokeAll(Collection\<Callable\<T\>\>
    tasks)]: Executes all the [Callable] tasks of the
    provided collection; returns a list of [Futures] with the
    results produced by the executed [Callable] objects
-   [List\<Future\<T\>\>
    invokeAll(Collection\<Callable\<T\>\>]: Executes all
    the [Callable] tasks of the provided collection; returns a
    list of [Futures] with the results produced by the
    executed [Callable] objects or the timeout expires, whichever
    happens first
-   [T invokeAny(Collection\<Callable\<T\>\> tasks)]: Executes all
    the [Callable] tasks of the provided collection; returns the
    result of one that has completed successfully (meaning, without
    throwing an exception), if any do
-   [T invokeAny(Collection\<Callable\<T\>\> tasks, long timeout,
    TimeUnit unit)]: Executes all the [Callable] tasks of
    the provided collection; returns the result of one that has
    completed successfully (meaning, without throwing an exception), if
    such is available before the provided timeout expires

As you can see, there are many ways to get the results from a thread.
The method you choose depends on the particular needs of your
application.

Parallel vs concurrent processing
========================================

When we hear about working threads executing at the same time, we
automatically assume that they literally do what they are programmed to
do in parallel. Only after we look under the hood of such a system we,
do realize that such parallel processing is possible only when the
threads are executed each by a different CPU. Otherwise, they time-share
the same processing power. We perceive them working at the same time
only because the time slots they use are very short---a fraction of the
time units we have used in our everyday life. When the threads share the
same resource, in computer science, we say they do it *concurrently*.

Concurrent modification of the same resource
========================================

Two or more threads modifying the same value while other threads read it
is the most general description of one of the problems of concurrent
access. Subtler problems include **thread interference** and **memory
consistency** errors, which both produce unexpected results in seemingly
benign fragments of code. In this section, we are going to demonstrate
such cases and ways to avoid them.

At first glance, the solution seems quite straightforward: just allow
only one thread at a time to modify/access the resource and that\'s it.
But if the access takes a long time, it creates a bottleneck that might
eliminate the advantage of having many threads working in parallel. Or,
if one thread blocks access to one resource while waiting for access to
another resource and the second thread blocks access to the second
resource while waiting for access to the first one, it creates a problem
called a **deadlock**. These are two very simple examples of the
possible challenges a programmer encounters while using multiple
threads.

First, we\'ll reproduce a problem caused by the concurrent modification
of the same value. Let\'s create a [Calculator] interface:


```
interface Calculator {
    String getDescription();
    double calculate(int i);
}
```

We will use [getDescription()] method to capture the description
of the implementation. Here is the first implementation:


```
class CalculatorNoSync implements Calculator{
    private double prop;
    private String description = "Without synchronization";
    public String getDescription(){ return description; }
    public double calculate(int i){
        try {
            this.prop = 2.0 * i;
            TimeUnit.MILLISECONDS.sleep(i);
            return Math.sqrt(this.prop);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Calculator was interrupted");
        }
        return 0.0;
    }
}
```

As you can see, the [calculate()] method assigns a new value to
the [prop] property, then does something else (we simulate it by
calling the [sleep()] method), and then calculates the square root
of the value assigned to the [prop] property. The [\"Without
synchronization\"] description depicts the fact that the value of
the [prop] property is changing every time the [calculate()]
method is called---without any coordination or **synchronization**, as
it is called in the case of the coordination between threads when they
concurrently modify the same resource.

We are now going to share this object between two threads, which means
that the [prop] property is going to be updated and used
concurrently. So some kind of thread synchronization around
the [prop] property is necessary, but we have decided that our
first implementation does not do it.

The following is the method we are going to use while executing
every [Calculator] implementation we are going to create:


```
void invokeAllCallables(Calculator c){
    System.out.println("\n" + c.getDescription() + ":");
    ExecutorService pool = Executors.newFixedThreadPool(2);
    List<Callable<Result>> tasks = List.of(new MyCallable("One", c), 
                                           new MyCallable("Two", c));
    try{
        List<Future<Result>> futures = pool.invokeAll(tasks);
        List<Result> results = new ArrayList<>();
        while (results.size() < futures.size()){
            TimeUnit.MILLISECONDS.sleep(5);
            for(Future future: futures){
                if(future.isDone()){
                    results.add((Result)future.get());
                }
            }
        }
        for(Result result: results){
            System.out.println(result);
        }
    } catch (Exception ex){
        ex.printStackTrace();
    } finally {
        shutdownAndTerminate(pool);
    }
}
```

As you can see, the preceding method does the following:

-   Prints description of the passed-in [Calculator]
    implementation.
-   Creates a fixed-size pool for two threads.
-   Creates a list of two [Callable] tasks---the objects of the
    following [MyCallable] class:


```
class MyCallable implements Callable<Result> {
    private String name;
    private Calculator calculator;
    public MyCallable(String name, Calculator calculator) {
        this.name = name;
        this.calculator = calculator;
    }
    public Result call() {
        double sum = 0.0;
        for(int i = 1; i < 20; i++){
            sum += calculator.calculate(i);
        }
        return new Result(name, sum);
    }
}
```

-   The list of tasks is passed into the [invokeAll()] method of
    the pool, where each of the tasks is executed by invoking
    the [call()] method; each [call()] method applies
    the [calculate()] method of the passed-in [Calculator]
    object to every one of the 19 numbers from 1 to 20 and sums up the
    results; the resulting sum is returned inside the [Result]
    object along with the name of the [MyCallable] object.


-   Each [Result] object is eventually returned inside a
    [Future] object.
-   The [invokeAllCallables()] method then iterates over the list
    of [Future] objects and checks each of them if the task is
    completed; when a task is completed, the result is added to the
    [List\<Result\> results].
-   After all the tasks are completed,
    the [invokeAllCallables()] method then prints all the elements
    of the [List\<Result\> results] and terminates the pool.

Here is the result we got from one of our runs of
[invokeAllCallables(new CalculatorNoSync())]:

![]./images_8/ee4ffa39-aed6-4bc1-8ade-3010abd580d8.png)

The actual numbers are slightly different every time we run the
preceding code, but the result of task [One] never equals the
result of task [Two]. That is because, in the period between
setting the value of the [prop] field and returning its square
root in the [calculate()] method, the other thread managed to
assign a different value to [prop]. This is a case of thread
interference.

There are several ways to address this problem. We start with an atomic
variable as the way to achieve thread-safe concurrent access to a
property. Then we will also demonstrate two methods of thread
synchronization.

Atomic variable
---------------

An **atomic variable** is a that can be updated only when its current
value matches the expected one. In our case, it means that
a [prop] value should not be used if it has been changed by
another thread.

The [java.util.concurrent.atomic] package has a dozen classes that
support this logic: [AtomicBoolean],
[AtomicInteger], [AtomicReference],
and [AtomicIntegerArray], to name a few. Each of these classes has
many methods that can be used for different synchronization needs. Check
the online API documentation for each of these classes
([https://docs.oracle.com/en/java/javase/12/docs/api/java.base/java/util/concurrent/atomic/package-summary.ht](https://docs.oracle.com/en/java/javase/12/docs/api/java.base/java/util/concurrent/atomic/package-summary.html)[ml](https://docs.oracle.com/en/java/javase/12/docs/api/java.base/java/util/concurrent/atomic/package-summary.html)).
For the demonstration, we will use only two methods present in all of
them:

-   [V get()]: Returns the current value
-   [boolean compareAndSet(V expectedValue, V newValue)]: Sets the
    value to [newValue] if the current value equals via operator
    ([==]) the [expectedValue]; returns [true] if
    successful or [false] if the actual value was not equal to the
    expected value

Here is how the [AtomicReference] class can be used to solve the
problem of threads\' interference while accessing
the [prop] property of the [Calculator] object concurrently
using these two methods:


```
class CalculatorAtomicRef implements Calculator {
    private AtomicReference<Double> prop = new AtomicReference<>(0.0);
    private String description = "Using AtomicReference";
    public String getDescription(){ return description; }
    public double calculate(int i){
        try {
            Double currentValue = prop.get();
            TimeUnit.MILLISECONDS.sleep(i);
            boolean b = this.prop.compareAndSet(currentValue, 2.0 * i);
            //System.out.println(b);    //prints: true for one thread 
                                        //and false for another thread
            return Math.sqrt(this.prop.get());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Calculator was interrupted");
        }
        return 0.0;
    }
}
```

As you can see, the preceding code makes sure that
the [currentValue] of the [prop] property does not change
while the thread was sleeping. The following is the screenshot of the
messages produced when we run [invokeAllCallables(new
CalculatorAtomicRef())]:

![]./images_8/8788646d-40f2-42d0-81a7-e508f491d793.png)

Now the results produced by the threads are the same.

The following classes of the [java.util.concurrent] package
provide synchronization support too:

-   [Semaphore]: Restricts the number of threads that can access a
    resource
-   [CountDownLatch]: Allows one or more threads to wait until a
    set of operations being performed in other threads are completed
-   [CyclicBarrier]: Allows a set of threads to wait for each
    other to reach a common barrier point
-   [Phaser]: Provides a more flexible form of barrier that may be
    used to control phased computation among multiple threads
-   [Exchanger]: Allows two threads to exchange objects at a
    rendezvous point and is useful in several pipeline designs

Synchronized method
-------------------

Another way to solve the problem is to use a synchronized method. Here
is another implementation of the [Calculator] interface that uses
this method of solving threads interference:


```
class CalculatorSyncMethod implements Calculator {
    private double prop;
    private String description = "Using synchronized method";
    public String getDescription(){ return description; }
    synchronized public double calculate(int i){
        try {
            this.prop = 2.0 * i;
            TimeUnit.MILLISECONDS.sleep(i);
            return Math.sqrt(this.prop);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Calculator was interrupted");
        }
        return 0.0;
    }
}
```

We have just added the [synchronized] keyword in front of the
[calculate()] method. Now, if we run [invokeAllCallables(new
CalculatorSyncMethod())], the results of both threads are always
going to be the same:

![]./images_8/e46785a3-45cb-4e57-a6e9-4703d6dac73a.png)

This is because another thread cannot enter the synchronized method
until the current thread (the one that has entered the method already)
has exited it. This is probably the simplest solution, but this approach
may cause performance degradation if the method takes a long time to
execute. In such cases, a synchronized block can be used, which wraps
only several lines of code in an atomic operation.

Synchronized block
------------------

Here is an example of a synchronized block used to solve the problem of
the threads\' interference:


```
class CalculatorSyncBlock implements Calculator {
    private double prop;
    private String description = "Using synchronized block";
    public String getDescription(){
        return description;
    }
    public double calculate(int i){
        try {
            //there may be some other code here
            synchronized (this) {
                this.prop = 2.0 * i;
                TimeUnit.MILLISECONDS.sleep(i);
                return Math.sqrt(this.prop);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Calculator was interrupted");
        }
        return 0.0;
    }
}
```

As you can see, the synchronized block acquires a lock
on [this] object, which is shared by both threads, and releases it
only after the threads exit the block. In our demo code, the block
covers all the code of the method, so there is no difference in
performance. But imagine there is more code in the method (we commented
the location as [there may be some other code here]). If that is
the case, the synchronized section of the code is smaller, thus having
fewer chances to become a bottleneck.

If we run [invokeAllCallables(new CalculatorSyncBlock())], the
results are as follows:

![]./images_8/a6abe37c-0940-4d27-aaa7-f73573a310f8.png)

As you can see, the results are exactly the same as in the previous two
examples. Different types of locks for different needs and with
different behavior are assembled in the
[java.util.concurrent.locks] package. 

Each object in Java inherits the [wait()], [notify()],
and [notifyAll()] methods from the base object. These methods can
also be used to control the threads\' behavior and their access to the
locks.

Concurrent collections 
-----------------------

Another way to address concurrency is to use a thread-safe collection
from the [java.util.concurrent] package. Before you select which
collection to use, read the
*Javadoc* (<https://docs.oracle.com/en/java/javase/12/docs/api/index.html>)
to see whether the limitations of the collection are acceptable for your
application. Here is the list of these collections and some
recommendations:

-   [ConcurrentHashMap\<K,V\>]: Supports full concurrency of
    retrievals and high-expected concurrency for updates; use it when
    the concurrency requirements are very demanding and you need to
    allow locking on the write operation but do not need to lock the
    element.
-   [ConcurrentLinkedQueue\<E\>]: A thread-safe queue based on
    linked nodes; employs an efficient non-blocking algorithm.
-   [ConcurrentLinkedDeque\<E\>]: A concurrent queue based on
    linked nodes; both [ConcurrentLinkedQueque] and
    [ConcurrentLinkedDeque] are an appropriate choice when many
    threads share access to a common collection.
-   [ConcurrentSkipListMap\<K,V\>]: A concurrent
    [ConcurrentNavigableMap] interface implementation.
-   [ConcurrentSkipListSet\<E\>]: A concurrent
    [NavigableSet] implementation based on a
    [ConcurrentSkipListMap].
    The [ConcurrentSkipListSet] and [ConcurrentSkipListMap] classes,
    as per the *Javadoc*, *provide expected average log(n) time cost for
    the contains, add, and remove operations and their variants.
    Ascending ordered views and their iterators are faster than
    descending ones*; use them when you need to iterate quickly through
    the elements in a certain order.
-   [CopyOnWriteArrayList\<E\>]: A thread-safe variant of
    [ArrayList] in which all mutative operations (add, set, and so
    on) are implemented by making a fresh copy of the underlying
    array; as per the *Javadoc*, the [CopyOnWriteArrayList]
    class *is ordinarily too costly, but may be more efficient than
    alternatives when traversal operations vastly outnumber mutations,
    and is useful when you cannot or don\'t want to synchronize
    traversals, yet need to preclude interference among concurrent
    threads*; use it when you do not need to add new elements at
    different positions and do not require sorting; otherwise,
    use [ConcurrentSkipListSet].
-   [CopyOnWriteArraySet\<E\>]: A set that uses an internal
    [CopyOnWriteArrayList] for all of its operations.


-   [PriorityBlockingQueue]: It is a better choice when a natural
    order is acceptable and you need fast adding of elements to the tail
    and fast removing of elements from the head of the queue;
    **blocking** means that the queue waits to become non-empty when
    retrieving an element and waits for space to become available in the
    queue when storing an element.
-   [ArrayBlockingQueue], [LinkedBlockingQueue], and
    [LinkedBlockingDeque] have a fixed size (bounded); the other
    queues are unbounded.

Use these and similar characteristics and recommendations as with the
guidelines, but execute comprehensive testing and performance-measuring
before and after implementing your functionality. To demonstrate some of
these collections capabilities, let\'s
use [CopyOnWriteArrayList\<E\>]. First, let\'s look at how an
[ArrayList] behaves when we try to modify it concurrently:


```
List<String> list = Arrays.asList("One", "Two");
System.out.println(list);
try {
    for (String e : list) {
        System.out.println(e);  //prints: One
        list.add("Three");      //UnsupportedOperationException
    }
} catch (Exception ex) {
    ex.printStackTrace();
}
System.out.println(list);       //prints: [One, Two]
```

As expected, the attempt to modify a list while iterating on it
generates an exception and the list remains unmodified.

Now, let\'s use [CopyOnWriteArrayList\<E\>] in the same
circumstances:


```
List<String> list = 
             new CopyOnWriteArrayList<>(Arrays.asList("One", "Two"));
System.out.println(list);
try {
    for (String e : list) {
        System.out.print(e + " "); //prints: One Two
        list.add("Three");         //adds element Three
    }
} catch (Exception ex) {
    ex.printStackTrace();
}
System.out.println("\n" + list);   //prints: [One, Two, Three, Three]
```

The output this code produces looks as follows:

![]./images_8/b7b971e8-31b9-4dd8-9bc0-f7128165d117.png)

As you can see, the list was modified without an exception, but not the
currently iterated copy. That is the behavior you can use if needed.

Addressing memory consistency error
-----------------------------------

Memory consistency errors can have many forms and causes in a
multithreaded environment. They are well discussed in the *Javadoc* of
the [java.util.concurrent] package. Here, we will mention only the
most common case, which is caused by a lack of visibility.

When one thread changes a property value, the other might not see the
change immediately, and you cannot use the synchronized keyword for a
primitive type. In such a situation, consider using the [volatile]
keyword for the property; it guarantees its read/write visibility
between different threads.

Concurrency problems are not easy to solve. That is why it is not
surprising that more and more developers now take a more radical
approach. Instead of managing an object state, they prefer processing
data in a set of stateless operations. We will see examples of such code
in  *Functional
Programming* and 
*Java Standard Streams*. It seems that Java and many modern languages
and computer systems are evolving in this direction.

Summary
========================================

In this lab, we talked about multithreaded processing, the ways to
organize it and avoid unpredictable results caused by the concurrent
modification of the shared resource. We have shown readers how to create
threads and execute them using pools of threads. We have also
demonstrated how the results can be extracted from the threads that have
completed successfully and discussed the difference between parallel and
concurrent processing. 

In the next lab, we will provide readers with a deeper understanding
of JVM, its structure and processes, and we\'ll discuss in detail the
garbage collection process that keeps memory from being overflown. By
the end of the lab, the readers will know what constitutes Java
application execution, Java processes inside JVM, garbage collection,
and how JVM works in general.

