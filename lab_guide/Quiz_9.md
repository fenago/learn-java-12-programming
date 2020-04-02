

JVM Structure and Garbage Collection
====================================

1.  Select all of the correct statements:
    1.   An IDE executes Java code without compiling it.
    2.  An IDE uses the installed Java to execute the code.
    3.  An IDE checks the code without using the Java installation.
    4.  An IDE uses the compiler of the Java installation. 

2.  Select all of the correct statements:
    1.  All the classes used by the application have to be listed on the
        classpath.
    2.  The locations of all the classes used by the application have to
        be listed on the classpath.
    3.  The compiler can find a class if it is in the folder listed on
        the classpath.
    4.  Classes of the main package do not need to be listed on the
        classpath.

3.  Select all of the correct statements:
    1.  All the .jar files used by the application have to be listed on
        the classpath.
    2.  The locations of all the .jar files used by the application have
        to be listed on the classpath. 
    3.  The JVM can find a class only if it is in the .jar file listed
        on the classpath.
    4.  Every class can have the main() method.

4.  Select all of the correct statements:
    1.  Every .jar file that has a manifest is an executable. 
    2.  If the -jar option is used by the java command, the classpath
        option is ignored.
    3.  Every .jar file has a manifest.
    4.  An executable .jar is a ZIP file with a manifest.

5.  Select all of the correct statements:
    1.  Class loading and linking can work in parallel on different
        classes.
    2.  Class loading moves the class to the execution area.
    3.  Class linking connects two classes.
    4.  Class linking uses memory references.

6.  Select all of the correct statements:
    1.  Class initialization assigns values to the instance properties.
    2.  Class initialization happens every time the class is referred to
        by another class.
    3.  Class initialization assigns values to static properties.
    4.  Class initialization provides data to the instance of
        java.lang.Class.

7.  Select all of the correct statements:
    1.  Class instantiation may never happen.
    2.  Class instantiation includes object properties initialization.
    3.  Class instantiation includes memory allocation on a heap.
    4.  Class instantiation includes executing a constructor code.

8.  Select all of the correct statements:
    1.  Method execution includes binary code generation.
    2.  Method execution includes source code compilation.
    3.  Method execution includes reusing the binary code produced by
        the  JIT compiler.
    4.  Method execution counts how many times every method was called.

9.  Select all of the correct statements:
    1.  Garbage collection starts immediately
        after the System.gc() method is called.
    2.  The application can be terminated with or without an error code.
    3.  The application exits as soon as an exception is thrown.
    4.  The main thread is a user thread.

10. Select all of the correct statements:
    1.  The JVM has memory areas shared across all threads.
    2.  The JVM has memory areas not shared across threads.
    3.  Class metadata is shared across all threads.
    4.  Method parameter values are not shared across threads.

11. Select all of the correct statements:
    1.  The classloader populates the method area.
    2.  The classloader allocates memory on a heap.
    3.  The classloader writes to the .class file.
    4.  The classloader resolves method references.

12. Select all of the correct statements:
    1.  The execution engine allocates memory on a heap.
    2.  The execution engine terminates the application.
    3.  The execution engine runs garbage collection.
    4.  The execution engine initializes static fields not initialized
        by a programmer.

13. Select all of the correct statements: 
    1.  The number of transactions per second that a database can
        support is a throughput measure.
    2.  When the garbage collector pauses the application, it is called
        stop-all-things.
    3.  How slowly the website returns data is a responsiveness measure.
    4.  The garbage collector clears the CPU queue of jobs. 

14. Select all of the correct statements: 
    1.  Object age is measured by the number of seconds since the
        object was created.
    2.  The older the object, the more probable it is going to be
        removed from the memory.
    3.  Cleaning the old generation is a major collection.
    4.  Moving an object from one area of the young generation to
        another area of the young generation is a minor collection.

15. Select all of the correct statements:
    1.  The garbage collector can be tuned by setting the parameters of
        the javac command.
    2.  The garbage collector can be tuned by setting the parameters of
        the java command.
    3.  The garbage collector works with its own logic and cannot change
        its behavior based on the set parameters.
    4.  Cleaning the old generation area requires a stop-the-world
        pause.
