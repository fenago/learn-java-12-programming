

Assessments
===========

1.  b), c), d)
2.  Add a dependency on JMH to the project (or classpath, if run
    manually) and add the annotation @Benchmark to the method you would
    like to test for performance
3.  As the main method using a Java command with an explicitly named
    main class, as the main method using a java command with an
    executable .jar file, and using an IDE running as the main method or
    using a plugin and running an individual method
4.  Any two of the following: Mode.AverageTime, Mode.Throughput,
    Mode.SampleTime, and Mode.SingleShotTime

5.  Any two of the following: TimeUnit.NANOSECONDS,
    TimeUnit.MICROSECONDS, TimeUnit.MILLISECONDS, TimeUnit.SECONDS,
    TimeUnit.MINUTES, TimeUnit.HOURS, and TimeUnit.DAYS
6.  Using an object of a class with the annotation @State
7.  Using the annotation @Param in front of the state property
8.  Using the annotation @CompilerConrol
9.  Using a parameter of the type Blackhole that consumes the produced
    result
10. Using the annotation @Fork

