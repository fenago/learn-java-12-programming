<img align="right" src="../logo-small.png">

Java Standard and External Libraries
====================================

It is not possible to write a Java program without using the standard
libraries, also called **Java Class Library*** *(**JCL**). That is why a
solid familiarity with such libraries is as vital for successful
programming as the knowledge of the language itself.

There are also *non-standard* libraries, called **external libraries**
or **third-party libraries** because they are not included in **Java
Development Kit** (**JDK**) distribution. Some of them have long become
a permanent fixture of any programmer toolkit.

To keep track of all the functionality available in these libraries is
not easy. That is because an **Integrated Development Environment**
(**IDE**) gives you a hint about the language possibilities, but it
cannot advise about the functionality of a package not imported yet. The
only package that is imported automatically is [java.lang].

This purpose of this lab is to provide you with an overview of the
functionality of the most popular packages of JCL and external
libraries. 

The topics we are discussing in this lab are as follows:

-   Java Class Library (JCL)
-   [java.lang]
-   [java.util]
-   [java.time]
-   [java.io] and [java.nio]
-   [java.sql] and [javax.sql]
-   [java.net]
-   [java.lang.math] and [java.math]
-   [java.awt], [javax.swing], and [[javafx]]
-   External libraries
-   [org.junit]
-   [org.mockito]
-   [org.apache.log4j] and [org.slf4j]
-   [org.apache.commons]

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
`java -cp target/learnjava-1.0.jar:target/libs/* com.lv.learnjava.ch07_libraries.Codec`

<h3><span style="color:red;">Run Java Code</span></h3>

You can run the example by running following command in the terminal:
`java -cp target/learnjava-1.0.jar:target/libs/* com.lv.learnjava.ch07_libraries.Collections4`

<h3><span style="color:red;">Run Java Code</span></h3>

You can run the example by running following command in the terminal:
`java -cp target/learnjava-1.0.jar:target/libs/* com.lv.learnjava.ch07_libraries.SomeClass`

<h3><span style="color:red;">Run Java Code</span></h3>

You can run the example by running following command in the terminal:
`java -cp target/learnjava-1.0.jar:target/libs/* com.lv.learnjava.ch07_libraries.SomeOtherClass`


Java Class Library
====================================

JCL is a collection of packages that implement the language. In simpler
terms, it is a collection of the [.class] files included in the
JDK and ready to be used. Once you have installed Java, you get them as
part of the installation and can start building your application code up
using the classes of JCL as building blocks that take care of a lot of
low-level plumbing. The JCL richness and ease of usage has substantially
contributed to Java popularity.

In order to use a JCL package, one can import it without adding a new
dependency to the [pom.xml] file. Maven adds JCL to the classpath
automatically. And that is what separates standard library and external
libraries; if you need to add a library (typically, a [.jar] file)
as a dependency in the Maven [pom.xml] configuration file, this
library is an external one. Otherwise, it is a standard library or JCL. 

Some JCL package names start with [java]. Traditionally, they are
called **core Java packages**, while those that start with
[javax] used to be called \"extensions.\" It was done so probably
because the extensions were thought to be optional and maybe even
released independently of JDK. There was also an attempt to promote
the former extension library to become a core package. But that would
require changing the package name from \"java\" to \"javax,\" which
would break the already existing applications that used
the [javax] package. Therefore, the idea was abandoned, so the
distinction between core and extensions gradually disappeared.

That is why, if you look at the official Java API on the Oracle website,
you will see listed as standard not only [java] and
[javax] packages, but also [jdk], [com.sun],
[org.xml], and some other packages too. These extra packages are
primarily used by the tools or other specialized applications. In our
course, we will concentrate mostly on the mainstream Java programming and
talk only about the [java] and [javax] packages.

java.lang
---------

This package is so fundamental that is not required to be imported in
order to use it. The JVM authors decided to import it automatically. It
contains the most often used classes of JCL:

-   [Object] class: The base class of any other Java class
-   [Class] class: Carries metadata of every loaded class at
    runtime
-   [String], [StringBuffer], and
    [StringBuilder] classes: Support operations with type
    [String]
-   The wrapper classes of all primitive types: [Byte],
    [Boolean], [Short], [Character], [Integer],
    [Long], [Float], and [Double]
-   [Number] class: The base class for the wrapper classes of
    the numeric primitive types---all the previously listed, except
    [Boolean]
-   [System] class: Provides access to important system operations
    and the standard input and output (we have used
    the [System.out] object in every code example in this course)
-   [Runtime] class: Provides access to the execution environment
-   The [Thread] and [Runnable] interfaces: Fundamental for
    creating Java threads
-   The [Iterable] interface: Used by the iteration statements
-   [Math] class: Provides methods for basic numeric operations
-   [Throwable] class: The base class for all exceptions
-   [Error] class: An exception class; all its children are used
    to communicate system errors that can\'t be caught by an application
-   [Exception] class: This class and its direct children
    represent checked exceptions
-   [RuntimeException] class: This class and its children that
    represent unchecked exceptions, also called runtime exceptions
-   [ClassLoader] class: Reads the [.class] files and puts
    (loads) them into memory; it also can be used to build a customized
    class-loader
-   [Process] and [ProcessBuilder] classes: Allow creating
    other JVM processes
-   Many other useful classes and interfaces

java.util
---------

Most of the content of the [java.util] package is dedicated to
supporting Java collections:

-   The [Collection] interface: The base interface of many other
    interfaces of collections, it declares all the basic methods
    necessary to manage collection elements: [size()],
    [add()], [remove()], [contains()],
    [stream()], and others; it also extends
    the [java.lang.Iterable] interface and inherits its methods,
    including [iterator()] and [forEach()], which means that
    any implementation of the [Collection] interface or any of its
    children---[List], [Set], [Queue],
    [Deque], and others---can be used in iteration statements too:
    [ArrayList], [LinkedList], [HashSet],
    [AbstractQueue], [ArrayDeque], and others.
-   The [Map] interface and classes that implement it:
    [HashMap], [TreeMap], and others.
-   The [Collections] class: Provides many static methods to
    analyze, manipulate, and convert collections.
-   Many other collection interfaces, classes, and related utilities.

We have talked about Java collections and saw examples of their usage in

*Data Structures, Generics, and Popular Utilities*.

The [java.util] package also includes several other useful
classes:

-   [Objects]: Provides various object-related utility methods,
    some of which we have over-viewed in
    *Data Structures, Generics, and Popular Utilities.*
-   [Arrays]: Contains some 160 static methods to manipulate
    arrays, some of which we have over-viewed in
    *Data Structures, Generics, and Popular Utilities.*
-   [Formatter]: Allows formatting of any primitive type,
    [String], [Date], and other types; we have demonstrated
    the examples of its usage in
    *Data Structures, Generics, and Popular Utilities.*
-   [Optional], [OptionalInt], [OptionalLong], and
    [OptionalDouble]: These classes help to avoid
    [NullPointerException] by wrapping the actual value that can
    be [null] or not.
-   [Properties]: Helps to read, and create key-value pairs used
    for application configuration, and similar purposes.
-   [Random]: Complements [java.lang.Math.random()] method
    by generating streams of pseudo-random numbers.
-   [StringTokeneizer]: Breaks the [String] object into the
    tokens separated by the specified delimiter.
-   [StringJoiner]: Constructs a sequence of characters separated
    by the specified delimiter, and optionally surrounded by the
    specified prefix and suffix.
-   Many other useful utility classes including the classes that
    support internationalization and base64-encoding and -decoding.

java.time
---------

The [java.time] package contains classes for managing dates, time,
periods, and durations. The package includes the following:

-   The [Month] enum
-   The [DayOfWeek] enum
-   The [Clock] class that returns current instant, date, and time
    using a time zone
-   The [Duration] and [Period] classes represent and
    compare amounts of time in different time units
-   The [LocalDate], [LocalTime], and [LocalDateTime]
    classes represent dates and times without a time zone
-   The [ZonedDateTime] class represents date and time with a time
    zone
-   The [ZoneId] class identifies a time zone such as
    *America/Chicago*
-   The [java.time.format.DateTimeFormatter] class allows
    presenting date and time in accordance with the **International
    Standards Organization** (**ISO**) formats, like the pattern
    *YYYY-MM-DD* and similar
-    some other classes that support date and time manipulation

We discussed most of these classes in  *Data
Structures, Generics, and Popular Utilities.*

java.io and java.nio
--------------------

The [java.io] and [java.nio] packages contain classes and
interfaces that support reading and writing data using streams,
serialization, and file systems. The difference between these two
packages is as follows:

-   The [java.io] package classes allow reading/writing data as
    they come without caching (we discussed it in 
    *Strings, Input/Output, and Files*), while classes of
    the [java.nio] package create a buffer that allows moving back
    and forth along the populated buffer.
-   The [java.io] package classes block the stream until all the
    data is read or written, while classes of
    the [java.nio] package are implemented in a non-blocking style
    (we will talk about non-blocking style in
    *Reactive Programming*).

java.sql and javax.sql
----------------------

These two packages compose the **Java Database Connectivity** (**JDBC**)
API that allows accessing and processing data stored in a data source,
typically a relational database. The [javax.sql] package
complements the [java.sql] package by providing support for the
following:

-   The [DataSource] interface as an alternative to the
    [DriverManager] class
-   Connections and statements pooling
-   Distributed transactions
-   Rowsets

java.net
--------

The [java.net] package contains classes that support applications
networking on the following two levels:

-   **Low-level networking**, based on:
    -   IP addresses
    -   Sockets, which are basic bidirectional data communication
        mechanisms
    -   Various network interfaces
-   **High-level networking**, based on:
    -   **Universal Resource Identifier** (**URI**)
    -   **Universal Resource Locator** (**URL**)
    -   Connections to the resource pointed to by URLs

We will talk about this package and see code examples in *Network
Programming*.

java.lang.math and java.math
----------------------------

The [java.lang.math] package contains methods for performing basic
numeric operations, such as calculating the minimum and maximum of two
numeric values, the absolute value, the elementary exponential,
logarithms, square roots, trigonometric functions, and many other
mathematical operations.

The [java.math] package complements Java primitive types and
wrapper classes of the [java.lang] package by allowing working
with much bigger numbers using the [BigDecimal] and
[BigInteger] classes.

java.awt, javax.swing, and javafx
---------------------------------

The first Java library that supported building a **Graphical User
Interface** (**GUI**) for desktop applications was the **Abstract Window
Toolkit** (**AWT**) in the [java.awt] package. It provided an
interface to the native system of the executing platform that allowed
creating and managing windows, layouts, and events. It also had the
basic GUI widgets (like text fields, buttons, and menus), provided
access to the system tray, and allowed to launch a web browser and email
a client from the Java code. Its heavy dependence on the native code
made AWT-based GUI look different on different platforms.

In 1997, Sun Microsystems and Netscape Communication Corporation
introduced Java **Foundation Classes**, later called **Swing** and
placed them in the [javax.swing] package. The GUI components built
with Swing were able to emulate the look and feel of some native
platforms but also allowed you to plug in a look and feel that did not
depend on the platform it was running on. It expanded the list of
widgets the GUI could have by adding tabbed panels, scroll panes,
tables, and lists. Swing components are called lightweight because they
do not depend on the native code and are fully implemented in Java.

In 2007, Sun Microsystems announced the creation of JavaFX, which has
eventually become a software platform for creating and delivering
desktop applications across many different devices. It was intended to
replace Swing as the standard GUI library for Java SE. The JavaFX
framework is located in the packages that start with [javafx] and
supports all major desktop **Operating Systems** (**OS**) and multiple
mobile OSes, including Symbian OS, Windows Mobile, and some proprietary
real-time OSes.

JavaFX adds the support of smooth animation, web views, audio and video
playback, and styles to the arsenal of a GUI developer, based on
**Cascading Style Sheets** (**CSS**). However, Swing has more components
and the third-party libraries, so using JavaFX may require creating
custom components and plumbing that was implemented in Swing a long time
ago. That\'s why, although JavaFX is recommended as the first choice for
desktop GUI implementation, Swing will remain part of Java *for the
foreseeable future*, according to the official response on the Oracle
website
(<http://www.oracle.com/technetwork/java/javafx/overview/faq-1446554.html#6>). So,
it is possible to continue using Swing, but, if possible, better to
switch to JavaFX.

We will talk about JavaFX and see code examples in  *Java
GUI Programming*.


External libraries
====================================

Different lists of the most used third-party non-JCL libraries include
between 20 and 100 libraries. In this section, we are going to discuss
those that are included in the majority of such lists. All of them are
open source projects.

org.junit
---------

The [org.junit] package is the root package of an open source
testing framework JUnit. It can be added to the project as the following
[pom.xml] dependency:


```
<dependency>
    <groupId>junit</groupId>
    <artifactId>junit</artifactId>
    <version>4.12</version>
    <scope>test</scope>
</dependency>
```

The [scope] value in the preceding [dependency] tag tells
Maven to include the library [.jar] file only when the test code
is going to be run, not into the production [.jar] file of the
application. With the dependency in place, you can now create a test.
You can write code yourself or let the IDE do it for you using the
following steps:

1.  Right-click on the class name you would like to test.
2.  Select [Go To.]
3.  Select [Test.]
4.  Click [Create New Test.]


5.  Click the checkbox for the methods of the class you would like to
    test.
6.  Write code for the generated test methods with the [\@Test]
    annotation.
7.  Add methods with the [\@Before] and
    [\@After] annotations if necessary.

Let\'s assume we have the following class:


```
public class SomeClass {
    public int multiplyByTwo(int i){
        return i * 2;
    }
}
```

If you follow the preceding steps listed, the following test class will
be created under the [test] source tree:


```
import org.junit.Test;
public class SomeClassTest {
    @Test
    public void multiplyByTwo() {
    }
}
```

Now you can implement the [void multiplyByTwo()] method as
follows:


```
@Test
public void multiplyByTwo() {
    SomeClass someClass = new SomeClass();
    int result = someClass.multiplyByTwo(2);
    Assert.assertEquals(4, result);
}
```

A **unit** is a minimal piece of code that can be tested, thus the name.
The best testing practices consider a method as a minimal testable unit.
That\'s why a unit test usually tests a method.

org.mockito
-----------

One of the problems a unit test often faces is the need to test a method
that uses a third-party library, a data source, or a method of another
class. While testing, you want to control all the inputs, so you can
predict the expected result of the tested code. That is where the
technique of simulating or mocking the behavior of the objects the
tested code interacts with comes in handy.

An open source framework Mockito ([org.mockito] root package name)
allows the accomplishment of exactly that---the creation of **mock
objects**. To use it is quite easy and straightforward. Here is one
simple case. Let\'s assume we need to test
another [SomeClass] method:


```
public class SomeClass {
    public int multiplyByTwoTheValueFromSomeOtherClass(SomeOtherClass 
                                                        someOtherClass){
        return someOtherClass.getValue() * 2;
    }
}
```

To test this method, we need to make sure the [getValue()] method
returns a certain value, so we are going to mock this method. In order
to do it, follow these steps:

1.  Add a dependency to the Maven [pom.xml] configuration file:


```
        <dependency>
            <groupId>org.mockito</groupId>
            <artifactId>mockito-core</artifactId>
            <version>2.23.4</version>
            <scope>test</scope>
        </dependency>
```

2.  Call the [Mockito.mock()] method for the class you need to
    simulate:


```
SomeOtherClass mo = Mockito.mock(SomeOtherClass.class);
```

3.  Set the value you need to be returned from a method:


```
Mockito.when(mo.getValue()).thenReturn(5);
```

4.  Now you can pass the mocked object as a parameter into the method
    you are testing that calls the mocked method:


```
SomeClass someClass = new SomeClass();
int result = someClass.multiplyByTwoTheValueFromSomeOtherClass(mo);
```

5.  The mocked method returns the result you have predefined:


```
Assert.assertEquals(10, result);
```

6.  After the preceding steps have been performed, here\'s how the test
    method looks:


```
@Test
public void multiplyByTwoTheValueFromSomeOtherClass() {
    SomeOtherClass mo = Mockito.mock(SomeOtherClass.class);
    Mockito.when(mo.getValue()).thenReturn(5);

    SomeClass someClass = new SomeClass();
    int result = 
           someClass.multiplyByTwoTheValueFromSomeOtherClass(mo);
    Assert.assertEquals(10, result);
}
```

Mockito has certain limitations. For example, you cannot mock static
methods and private methods. Otherwise, it is a great way to isolate the
code you are testing by reliably predicting the results of the used
third-party classes. 

org.apache.log4j and org.slf4j
------------------------------

Throughout this course, we used [System.out] to display the results.
In the real-life application, one can do it too and redirect the output
to a file, for example, for later analysis. After doing it for some
time, you will notice that you need more details about each output: the
date and time of each statement, and the class name where the logging
statement was generated, for example. As the code base grows, you will
find that it would be nice to send output from different subsystems or
packages to different files or turn off some messages, when everything
works as expected, and turn them back on when an issue has been detected
and more detailed information about code behavior is needed. And you
don\'t want the size of the log file to grow uncontrollably.

It is possible to write your own code that accomplishes all that. But
there are several frameworks that do it based on the settings in a
configuration file, which you can change every time you need to change
the logging behavior. The two most popular frameworks used for that are
called [log4j] (pronounced as *LOG-FOUR-JAY*) and [slf4j]
(pronounced as *S-L- F-FOUR-JAY*).

In fact, these two frameworks are not rivals. The
[slf4j] framework is a facade that provides unified access to an
underlying actual logging framework, one of them can be [log4j]
too. Such a facade is especially helpful during a library development
when programmers do not know in advance what kind of a logging framework
will be used by the application that uses the library. By writing code
using [slf4j], the programmers allow later to configure it to use
any logging system.

So, if your code is going to be used only by the application your team
develops, using just [log4j] is quite enough. Otherwise, consider
using [slf4j].

And, as in the case of any third-party library, before you can use
the [log4j] framework, you have to add a corresponding dependency
to the Maven [pom.xml] configuration file:


```
<dependency>
    <groupId>org.apache.logging.log4j</groupId>
    <artifactId>log4j-api</artifactId>
    <version>2.11.1</version>
</dependency>
<dependency>
    <groupId>org.apache.logging.log4j</groupId>
    <artifactId>log4j-core</artifactId>
    <version>2.11.1</version>
</dependency>
```

For example, here\'s how the framework can be used:


```
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
public class SomeClass {
    static final Logger logger = 
                        LogManager.getLogger(SomeClass.class.getName());
    public int multiplyByTwoTheValueFromSomeOtherClass(SomeOtherClass 
                                                        someOtherClass){
        if(someOtherClass == null){
            logger.error("The parameter should not be null");
            System.exit(1);
        }
        return someOtherClass.getValue() * 2;
    }
    public static void main(String... args){
        new SomeClass().multiplyByTwoTheValueFromSomeOtherClass(null);
    }
}
```

If we run the preceding [main()] method, the result will be the
following:


```
18:34:07.672 [main] ERROR SomeClass - The parameter should not be null
Process finished with exit code 1
```

As you can see, if no [log4j]-specific configuration file is added
to the project, [log4j] will provide a default configuration in
the [DefaultConfiguration] class. The default configuration is as
follows:

1.  The log message will go to a console.
2.  The pattern of the message is going to be [\"%d{HH:mm:ss.SSS} \[%t\]
    %-5level %logger{36} - %msg%n\"].
3.  The level of [logging] will be [Level.ERROR] (other
    levels are [OFF], [FATAL], [WARN], [INFO],
    [DEBUG], [TRACE], and [ALL]).

The same result is achieved by adding the [log4j2.xml] file to the
[resources] folder (which Maven places on the classpath) with the
following content:


```
<?xml version="1.0" encoding="UTF-8"?>
<Configuration status="WARN">
    <Appenders>
        <Console name="Console" target="SYSTEM_OUT">
            <PatternLayout pattern="%d{HH:mm:ss.SSS} [%t] %-5level 
                                                %logger{36} - %msg%n"/>
        </Console>
    </Appenders>
    <Loggers>
        <Root level="error">
            <AppenderRef ref="Console"/>
        </Root>
    </Loggers>
</Configuration>
```

If that is not good enough for you, it is possible to change the
configuration to log messages of different levels, to different files,
and so on. Read the [log4J] documentation
([https://logging.apache.org](https://logging.apache.org/)).

org.apache.commons
------------------

The [org.apache.commons] package is another popular library
developed as a project called **Apache Commons**. It is maintained by an
open source community of programmers called **Apache Software
Foundation**. This organization was formed from the Apache Group in
1999. The Apache Group has grown around the development of the Apache
HTTP Server since 1993. The Apache HTTP Server is an open source
cross-platform web server that has remained the most popular web server
since April 1996.

The Apache Commons project has the following three parts:

-   **Commons Sandbox**: A workspace for Java component development; you
    can contribute to the open source working there.
-   **Commons Dormant**: A repository of components that are currently
    inactive; you can use the code there, but have to build the
    components yourself since these components will probably not be
    released in the near future.
-   **Commons Proper**: The reusable Java components, which compose the
    actual [org.apache.commons] library.

We discussed the [org.apache.commons.io] package in  *String,
Input/Output, and Files*.\
In the following subsections, we will discuss only three of Commons
Proper most popular packages:

-   [org.apache.commons.lang3]
-   [org.apache.commons.collections4]
-   [org.apache.commons.codec.binary]

But there are many more packages under [org.apache.commons] that
contain thousands of classes that can easily be used and can help make
your code more elegant and efficient.

lang and lang3
--------------

The [org.apache.commons.lang3] package is actually the version 3
of the [org.apache.commons.lang] package. The decision to create a
new package was forced by the fact that changes introduced in version 3
were backward incompatible, which means that the existing applications
that use the previous version of [org.apache.commons.lang] package
may stop working after the upgrade to version 3. But in the majority of
mainstream programming, adding [3] to an import statement (as the
way to migrate to the new version) typically does not break anything.

According to the documentation,
the [org.apache.commons.lang3] package provides highly reusable
static utility methods, chiefly concerned with adding value to the
[java.lang] classes*.* Here are a few notable examples:

-   The [ArrayUtils] class: Allows to search and manipulate
    arrays; we discussed and demonstrated it in
    *Data Structures, Generics, and Popular Utilities*
-   The [ClassUtils] class: Provides some metadata about a class
-   The [ObjectUtils] class: Checks an array of objects for
    [null], compares objects, and calculates the median and
    minimum/maximum of an array of objects in a null-safe manner; we
    discussed and demonstrated it in *Data
    Structures, Generics, and Popular Utilities*
-   The [SystemUtils] class: Provides information about the
    execution environment
-   The [ThreadUtils] class: Finds information about currently
    running threads
-   The [Validate] class: Validates individual values and
    collections, compares them, checks for [null], matches, and
    performs many other validations
-   The [RandomStringUtils] class: Generates [String]
    objects from the characters of various character sets
-   The [StringUtils] class: We discussed in *String,
    Input/Output, and Files*

collections4
------------

Although on the surface the content of
the [org.apache.commons.collections4] package looks quite similar
to the content of the [org.apache.commons.collections] package
(which is the version 3 of the package), the migration to the version 4
may not be as smooth as just adding \"4\" to the import statement.
Version 4 removed deprecated classes, added generics and other features
incompatible with the previous versions.

One has to be hard-pressed to come up with a collection type or a
collection utility that is not present in this package or one of its
sub-packages. The following is just a high-level list of features and
utilities included:

-   The [Bag] interface for collections that have several copies
    of each object.
-   A dozen classes that implement the [Bag] interface, for
    example, here is how the [HashBag] class can be used:


```
        Bag<String> bag = new HashBag<>();
        bag.add("one", 4);
        System.out.println(bag);                 //prints: [4:one]
        bag.remove("one", 1);
        System.out.println(bag);                 //prints: [3:one]
        System.out.println(bag.getCount("one")); //prints: 3
```

-   The [BagUtils] class that transforms [Bag]-based
    collections.
-   The [BidiMap] interface for bidirectional maps that allow you
    to retrieve not only value by its key but also a key by its value;
    it has several implementations, for example:


```
        BidiMap<Integer, String> bidi = new TreeBidiMap<>();
        bidi.put(2, "two");
        bidi.put(3, "three");
        System.out.println(bidi);             //prints: {2=two, 3=three}
        System.out.println(bidi.inverseBidiMap()); 
                                              //prints: {three=3, two=2}
        System.out.println(bidi.get(3));      //prints: three
        System.out.println(bidi.getKey("three")); //prints: 3
        bidi.removeValue("three"); 
        System.out.println(bidi);              //prints: {2=two}
```

-   [MapIterator] interface to provide simple and quick iteration
    over maps, for example:


```
        IterableMap<Integer, String> map =
                           new HashedMap<>(Map.of(1, "one", 2, "two"));
        MapIterator it = map.mapIterator();
        while (it.hasNext()) {
            Object key = it.next();
            Object value = it.getValue();
            System.out.print(key + ", " + value + ", "); 
                                              //prints: 2, two, 1, one, 
            if(((Integer)key) == 2){
                it.setValue("three");
            }
        }
        System.out.println("\n" + map);      //prints: {2=three, 1=one}
```

-   Ordered maps and sets that keep the elements in a certain order,
    like [List] does, for example:


```
        OrderedMap<Integer, String> map = new LinkedMap<>();
        map.put(4, "four");
        map.put(7, "seven");
        map.put(12, "twelve");
        System.out.println(map.firstKey()); //prints: 4
        System.out.println(map.nextKey(2)); //prints: null
        System.out.println(map.nextKey(7)); //prints: 12
        System.out.println(map.nextKey(4)); //prints: 7
```

-   Reference maps, their keys, and/or values can be removed by the
    garbage collector.
-   Various implementations of the [Comparator] interface.
-   Various implementations of the [Iterator] interface.
-   Classes that convert array and enumerations to collections.
-   Utilities that allow testing or creating a union, intersection, and
    closure of collections.
-   The [CollectionUtils], [ListUtils], [MapUtils],
    [MultiMapUtils], [MultiSetUtils], [QueueUtils],
    [SetUtils] classes and many other interface-specific utility
    classes.

Read the package documentation
(<https://commons.apache.org/proper/commons-collections>) for more
details.

codec.binary
------------

The [org.apache.commons.codec.binary] package provides support for
Base64, Base32, Binary, and Hexadecimal String encoding and decoding.
The encoding is necessary to make sure that the data you sent across
different systems will not be changed on the way because of the
restrictions on the range of characters in different protocols. Besides,
some systems interpret the sent data as control characters (a modem, for
example).

Here is the code snippet that demonstrates the basic encoding and
decoding capabilities of the [Base64] class of this package:


```
String encodedStr = 
           new String(Base64.encodeBase64("Hello, World!".getBytes()));
System.out.println(encodedStr);         //prints: SGVsbG8sIFdvcmxkIQ==

System.out.println(Base64.isBase64(encodedStr));        //prints: true

String decodedStr = 
               new String(Base64.decodeBase64(encodedStr.getBytes()));
System.out.println(decodedStr);                 //prints: Hello, World!
```

You can read more about this package on the Apache Commons project site
(<https://commons.apache.org/proper/commons-codec>).

Summary
====================================

In this lab, we provided an overview of the functionality of the
most popular packages of
JCL: [java.lang], [java.util], [java.time], [java.io], [java.nio], [java.sql],
[javax.sql], [java.net], [java.lang.math],
[java.math], [java.awt], [javax.swing],
and [javafx].

The most popular external libraries were represented by the
[org.junit], [org.mockito], [org.apache.log4j],
[org.slf4j], and [org.apache.commons] packages. It helps you
to avoid writing custom code in the cases when such functionality
already exists and can just be imported and used out of the box.

In the next lab, we will talk about Java threads and demonstrate
their usage. We will also explain the difference between parallel and
concurrent processing. We will demonstrate how to create a thread and
how to execute, monitor, and stop it. It will be very useful material
not only for those who are going to write code for multi-threaded
processing but also for those who would like to improve their
understanding of how the JVM works, which will be the topic of the
following lab.
