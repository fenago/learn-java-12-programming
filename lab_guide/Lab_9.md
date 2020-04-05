<img align="right" src="../logo-small.png">


JVM Structure and Garbage Collection 
====================================

This chapter provides readers with an overview of **Java Virtual
Machine** (**JVM**) structure and behavior, which are more complex than
you might expect.

A JVM is just an executor of instructions according to the coded logic.
It also finds and loads into the memory the [.class] files
requested by the application, verifies them, interprets the bytecodes
(that is, it translates them into platform-specific binary code), and
passes the resulting binary code to the central processor (or
processors) for execution. It uses several service threads in addition
to the application threads. One of the service threads, called **garbage
collection** (**GC**), performs an important mission of releasing the
memory from unused objects. 

After reading this chapter, readers will better understand what
constitutes Java application execution, Java processes inside JVM, GC,
and how JVM works in general.

The following topics will be covered in this chapter:

-   Java application execution
-   Java processes
-   JVM structure
-   Garbage collection



### Run Java Code
You can run the example by running following command in the terminal:
`java -cp target/learnjava-1.0.jar com.lv.learnjava.ch09_jvm.example.ExampleClass.java`

### Run Java Code
You can run the example by running following command in the terminal:
`java -cp target/learnjava-1.0.jar com.lv.learnjava.ch09_jvm.MyApplication.java`


Java application execution
====================================

Before getting deeper into how the JVM works, let\'s review how to run
an application, bearing in mind that the following statements are used
as synonyms:

-   Run/execute/start the main class
-   Run/execute/start the main method
-   Run/execute/start/launch an application
-   Run/execute/start/launch JVM or a Java process

There are also several ways to do it. In [Chapter
1](https://subscription.packtpub.com/book/programming/9781789957051/1),
*Getting Started with Java 12*, we showed you how to run
the [main(String\[\])] method using IntelliJ IDEA. In this
chapter, we will just repeat some of what has been said already and add
other variations that might be helpful for you.

Using an IDE {#using-an-ide .header-title}
------------

Any IDE allows for running the [main()] method. In IntelliJ IDEA,
it can be done in three ways:

1.  Click the green triangle next to the [main()] method name:

![]./images_9/829439d1-e909-48ff-a189-71fce8cb6af5.png)

2.  After you have executed the [main()] method using the green
    triangle at least once, the name of the class will be added to the
    drop-down menu (on the top line, to the left of the green
    triangle): 

![]./images_9/71cdf33e-cbe3-49ec-9d55-26a848c9931a.png)

Select it and click the green triangle to the right of the menu:

![]./images_9/23dac37d-0992-4cce-a224-a679bf302002.png)

-   Open the [Run] menu and select the name of the class.
    There are several different options to select:

![]./images_9/6620dd27-71ea-4fd8-8588-bcf98ad4c439.png)

In the previous screenshot, you can also see the option to [Edit
Configurations\...]. It can be used for setting the
[Program arguments] that are passed to the [main()]
method at the start, and some other options:

![]./images_9/eaedb621-b0f8-44ee-b192-d60698cc327e.png)

The [VM options] field allows for setting [java]
command options. For example, if you input the [-Xlog:gc], the IDE
will form the following [java] command:


```
java -Xlog:gc -cp . com.lv.learnjava.ch09_jvm.MyApplication
```

The [-Xlog:gc] option requires the GC log to be displayed. We will
use this option in the next section to demonstrate how GC works.
The [-cp .] option (**cp** stands for **classpath**) indicates
that the class is located in a folder on the file tree that starts from
the current directory (the one where the command is entered). In our
case, the [.class] file is located in
the [com/lv/learnjava/ch09\_jvm] folder, where [com] is
the subfolder of the current directory. The classpath can include many
locations where JVM has to look for the [.class] files necessary
for the application\'s execution.

For this demonstration, let\'s set [VM options] as
follows:

![]./images_9/d603fc35-dd88-4aec-b8d3-d0a84927edec.png)

The [Program arguments] field allows for setting a
parameter in the [java] command. For example, let\'s set [one two
three] in this field:

![]./images_9/cd547d91-4c36-4297-bb09-0346fb50c096.png)

This setting will result in the following [java] command:


```
java -DsomeParameter=42 -cp . \
       com.lv.learnjava.ch09_jvm.MyApplication one two three
```

We can read these parameters in the [main()] method:


```
public static void main(String... args){
    System.out.println("Hello, world!"); //prints: Hello, world!
    for(String arg: args){
        System.out.print(arg + " ");     //prints: one two three
    }
    String p = System.getProperty("someParameter");
    System.out.println("\n" + p);        //prints: 42
}
```

Another possible setting on the [Edit Configurations]
screen is in the [Environment variables] field:

![]./images_9/8639cad1-7de2-479e-8cc6-74e1862adf51.png)

That is the way to set environment variables that can be accessed from
the application using [System.getenv()]. For example, let\'s set
the environment variables [x] and [y] , as follows:

![]./images_9/a393641c-7893-4909-8f91-66d724efb7fb.png)

If done as shown in the preceding screenshot, the values of
[x] and [y] can be read not only in the [main()]
method, but anywhere in the application using
the [System.getenv(\"varName\")] method. In our case, the values
of [x] and [y] can be retrieved as follows:


```
String p = System.getenv("x");
System.out.println(p);                  //prints: 42
p = System.getenv("y");
System.out.println(p);                  //prints: 43
```

There are other parameters of the [java] command that can be set
on the [Edit Configurations] screen, too. We encourage
you to spend some time on that screen and view the possible options.

Using the command line with classes {#using-the-command-line-with-classes .header-title}
-----------------------------------

Now, let\'s run [MyApplication] from a command line. To remind
you, the main class looks as follows:


```
package com.lv.learnjava.ch09_jvm;
public class MyApplication {
    public static void main(String... args){
        System.out.println("Hello, world!"); //prints: Hello, world!
        for(String arg: args){
            System.out.print(arg + " ");     //prints all arguments
        }
        String p = System.getProperty("someParameter");
        System.out.println("\n" + p);    //prints someParameter set
                                         // as VM option -D
    }
}
```

First, it has to be compiled using the [javac] command. The
command line looks as follows (provided you open the Terminal window in
the root of the project, in the folder where [pom.xml] resides):


```
javac src/main/java/com/lv/learnjava/ch09_jvm/MyApplication.java
```

That is for Linux-type platforms. On Windows, the command looks similar:


```
javac src\main\java\com.lv.learnjava\ch09_jvm\MyApplication.java
```

The compiled [MyApplication.class] file is placed in the same
folder with [MyApplication.java]. Now we can execute the compiled
class with the [java] command:


```
java -DsomeParameter=42 -cp src/main/java \
           com.lv.learnjava.ch09_jvm.MyApplication one two three
```

Notice that [-cp] points to the folder [src/main/java] (the
path is relative to the current folder), where the package of the main
class starts. The result is as follows:

![]./images_9/144a8b21-fd49-4124-8dc0-00ecc4760228.png)

If the application uses other [.class] files located in different
folders, all the paths to these folders (relative to the current folder)
can be listed after the [-cp] option, separated by a colon
([:]). For example, consider the following:


```
java -cp src/main/java:someOtherFolder/folder \
                        com.lv.learnjava.ch09_jvm.MyApplication
```

Notice that the folders listed with the [-cp] option can contain
any number of [.class] files. This way, JVM can find what it
needs. For example, let\'s create a sub-package, [example] , in
the [com.lv.learnjava.ch09\_jvm] package with the
[ExampleClass] class in it:


```
package com.lv.learnjava.ch09_jvm.example;
public class ExampleClass {
    public static int multiplyByTwo(int i){
        return 2 * i;
    }
}
```

Now let\'s use it in the [MyApplication] class:


```
package com.lv.learnjava.ch09_jvm;
import com.lv.learnjava.ch09_jvm.example.ExampleClass;
public class MyApplication {
    public static void main(String... args){
        System.out.println("Hello, world!"); //prints: Hello, world!
        for(String arg: args){
            System.out.print(arg + " ");    
        }
        String p = System.getProperty("someParameter");
        System.out.println("\n" + p);  //prints someParameter value
        
        int i = ExampleClass.multiplyByTwo(2);
        System.out.println(i);               
    }
}
```

We are going to compile the [MyApplication] class using the same
[javac] command as before:


```
javac src/main/java/com/lv/learnjava/ch09_jvm/MyApplication.java
```

The result is the following error:

![]./images_9/bf5e1e37-390b-4e3f-830c-75681d9983ec.png)

It means that the compiler cannot find the [ExampleClass.class]
file. We need to compile it and put on the classpath:


```
javac src/main/java/com/lv/learnjava/ch09_jvm/example/ExampleClass.java
javac -cp src/main/java \
             src/main/java/com/lv/learnjava/ch09_jvm/MyApplication.java
```

As you can see, we have added the location of
[ExampleClass.class], which is [src/main/java], to the
classpath. Now, we can execute [MyApplication.class]:


```
java -cp src/main/java com.lv.learnjava.ch09_jvm.MyApplication
```

The result is as follows:

![]./images_9/df156ae4-c544-41cd-94e0-c12be9f98cfb.png)

There is no need to list folders that contain classes from the **Java
Class Library** (**JCL**). The JVM knows where to find them.

Using the command line with JAR files {#using-the-command-line-with-jar-files .header-title}
-------------------------------------

Keeping the compiled files in a folder as [.class] files is not
always convenient, especially when many compiled files of the same
framework belong to different packages and are distributed as a single
library. In such cases, the compiled [.class] files are usually
archived together in a [.jar] file. The format of such an archive
is the same as the format of a [.zip] file. The only difference is
that a [.jar] file also includes a manifest file that contains
metadata describing the archive (we will talk more about the manifest in
the next section).

To demonstrate how to use it, let\'s create a [.jar] file with the
[ExampleClass.class] file and another [.jar] file with
[MyApplication.class] in it, using the following commands:


```
cd src/main/java
jar -cf myapp.jar com/lv/learnjava/ch09_jvm/MyApplication.class
jar -cf example.jar \
           com/lv/learnjava/ch09_jvm/example/ExampleClass.class
```

Notice that we need to run the [jar] command in the folder where
the package of [.class] file begins. 

Now we can run the application as follows:


```
java -cp myapp.jar:example.jar \
     com.lv.learnjava.ch09_jvm.MyApplication
```

The [.jar] files are in the current folder. If we would like to
execute the application from another folder (let\'s go back to the root
directory, [cd ../../..]), the command should look like this:


```
java -cp src/main/java/myapp.jar:src/main/java/example.jar \
                         com.lv.learnjava.ch09_jvm.MyApplication
```

Notice that every [.jar] file has to be listed on the classpath
individually. To specify just a folder where all the [.jar] files
reside (as is the case with [.class] files) is not good enough. If
the folder contains only [.jar] files, all these files can be
included in the classpath as follows:


```
java -cp src/main/java/* com.lv.learnjava.ch09_jvm.MyApplication
```

 As you can see, the wildcard symbol has to be added after the folder
name.

Using the command line with an executable JAR file {#using-the-command-line-with-an-executable-jar-file .header-title}
--------------------------------------------------

It is possible to avoid specifying the main class in the command line.
Instead, we can create an executable [.jar] file. It can be
accomplished by placing the name of the main class -- the one you need
to run and that contains the [main()] method -- into the manifest
file. Here are the steps:

1.  Create a text file, [manifest.txt] (the name actually does not
    matter, but this name makes the intent clear), which contains the
    following line:


```
 Main-Class: com.lv.learnjava.ch09_jvm.MyApplication 
```

There has to be a space after the colon ([:]), and there has to be
an invisible newline symbol at the end, so make sure you have pressed
the *Enter* key and the cursor has jumped to the beginning of the next
line.

2.  Execute the command:


```
cd src/main/java 
jar -cfm myapp.jar manifest.txt com/lv/learnjava/ch09_jvm/*.class \ 
                        com/lv/learnjava/ch09_jvm/example/*.class
```

Notice the sequence of [jar] command options ([fm]) and the
sequence of the following files: [myapp.jar manifest.txt]. They
have to be the same because [f] stands for the file the
[jar] command is going to create, and [m] stands for the
manifest source. If you include options as [mf], then the files
have to be listed as [manifest.txt myapp.jar].

3.  Now we can run the application using the following command:


```
java -jar myapp.jar 
```

Another way to create an executable [.jar] file is much easier:


```
jar cfe myjar.jar com.lv.learnjava.ch09_jvm.MyApplication \
                  com/lv/learnjava/ch09_jvm/*.class       \       
                  com/lv/learnjava/ch09_jvm/example/*.class
```

This command generates a manifest with the specified main class name
automatically: option [c] stands for **create a new archive**,
option [f] means **archive file name**, and option
[e] indicates an **application entry point**.

Java processes 
====================================

As you may have guessed already, JVM does not know anything about the
Java language and source code. It only knows how to read bytecode. It
reads the bytecodes and other information from [.class] files,
transforms (interprets) the bytecodes into a sequence of binary code
instructions specific to the current platform (where JVM is running),
and passes the resulting binary code to the microprocessor that executes
it. When talking about this transformation, programmers often refer to
it as a **Java** **process**,** **or just **process**.

The JVM is often referred to as a **JVM instance**. That is because
every time a [java] command is executed, a new instance of JVM is
launched, dedicated to running the particular application as a separate
process with its own allocated memory (the size of the memory is set as
a default value or passed in as a command option). Inside this Java
process, multiple threads are running, each with its own allocated
memory. Some are service threads created by the JVM; others are
application threads created and controlled by the application.

That is the big picture of JVM executing the compiled code. But if you
look closer and read the JVM specification, you will discover that the
word *process,* in relation to JVM, is used to describe the JVM internal
processes too. The JVM specification identifies several other processes
running inside the JVM that usually are not mentioned by programmers,
except maybe the **class loading process**.

That is so because most of the time, we can successfully write and
execute Java programs without knowing anything about the internal JVM
processes. But once in a while, some general understanding of JVM\'s
internal workings helps to identify the root cause of certain issues.
That is why in this section, we will provide a short overview of all the
processes that happen inside the JVM. Then, in the following sections,
we will discuss in more detail JVM\'s memory structure and other aspects
of its functionality that may be useful to a programmer.

There are two subsystems that run all the JVM internal processes:

-   **The classloader**: This reads [.class] file and populates a
    method area in JVM memory with the class-related data:
    -   Static fields
    -   Method bytecodes
    -   Class metadata that describes the class
-   **The execution engine**: This executes the bytecodes using the
    following:
    -   A heap area for object instantiation
    -   Java and native method stacks for keeping track of the methods
        called
    -   A garbage collection process that reclaims the memory

Processes that run inside the main JVM process include the following:

-   Processes performed by the classloader include the following:
    -   Class loading
    -   Class linking
    -   Class initialization
-   Processes performed by the execution engine include the following:
    -   Class instantiation
    -   Method execution
    -   Garbage collection
    -   Application termination


[The JVM architecture]\
\
JVM architecture can be described as having two subsystems:
the **classloader** and the **execution engine**, which run the service
processes and application threads using runtime data memory areas such
as method area, heap, and application thread stacks. **Threads** are
lightweight processes that require less resource allocation than the JVM
execution process.


The list may give you the impression that these processes are executed
sequentially. To some degree, this is true,if we\'re talking about one
class only. It is not possible to do anything with a class before
loading. The execution of a method can begin only after all the previous
processes are completed. However, the GC, for example, does not happen
immediately after the use of an object is stopped (see the *Garbage
collection* section). Also, an application can exit any time when an
unhandled exception or some other error happens.


Only the classloader processes are regulated by the JVM specification.
The execution engine implementation is largely at the discretion of each
vendor. It is based on the language semantics and the performance goals
set by the implementation authors.


Processes of the execution engine are in a realm not regulated by the
JVM specification. There is common sense, tradition, known and proven
solutions, and a Java language specification that can guide a JVM
vendor\'s implementation decision. But there is no single regulatory
document. The good news is that the most popular JVMs use similar
solutions or, at least, that\'s how it looks at a high level. 

With this in mind, let\'s discuss each of the seven processes listed
previously in more detail.

Class loading {#class-loading .header-title}
-------------

According to the JVM specification, the loading phase includes finding
the [.class] file by its name (in the locations listed on a
classpath) and creating its representation in the memory.

The first class to be loaded is the one passed in the command line, with
the [main(String\[\])] method in it. The classloader reads the
[.class] file, parses it, and populates the method area with
static fields and method bytecodes. It also creates an instance of
[java.lang.Class] that describes the class. Then the classloader
links the class (see the *Class linking* section), initializes it (see
the *Class initialization* section), and then passes it to the execution
engine for running its bytecodes.

The [main(String\[\])] method is an entrance door into the
application. If it calls a method of another class, that class has to be
found on the classpath, loaded, initialized, and only then can its
method be executed too. If this - just loaded - method calls a method of
another class, that class has to be found, loaded, and initialized too,
and so on. That is how a Java application starts and gets going.

[The] [main(String\[\])] [method]\
\
Every class can have a [main(String\[\])] method, and often does.
Such a method is used to run the class independently as a standalone
application for testing or demo purposes. The presence of such a method
does not make the class [main]. The class becomes [main]
only if identified as such in a [java] command line or in a
[.jar] file manifest.


That being said, let\'s continue with the discussion of the loading
process.

If you look in the API of [java.lang.Class], you will not see a
public constructor there. The classloader creates its instance
automatically and, by the way, it is the same instance that is returned
by [getClass()] method you can invoke on any Java object.

It does not carry the class static data (that is maintained in the
method area), nor state values (they are in an object, created during
the execution). It does not contain method bytecodes either (they are
stored in the method area, too). Instead, the [Class] instance
provides metadata that describes the class -- its name, package, fields,
constructors, method signatures, and so on. The metadata is useful not
only for JVM but also for the application.


All the data created by the classloader in the memory and maintained by
the execution engine is called a **binary representation of the type**.


If the [.class] file has errors or does not adhere to a certain
format, the process is terminated. This means that some validation of
the loaded class format and its bytecodes is performed by the loading
process already. More verification follows at the beginning of the next
process, called **class linking**.

Here is the high-level description of the loading process. It performs
three tasks:

-   Finding and reading the [.class] file
-   Parsing it according to the internal data structure into the method
    area
-   Creating an instance of [java.lang.Class] with the class
    metadata

Class linking {#class-linking .header-title}
-------------

According to the JVM specification, the linking resolves the references
of the loaded class, so the methods of the class can be executed.

Here is a high-level description of the linking process. It performs
three tasks:

1.  **Verification of the binary representation of a class or an
    interface**:

Although JVM can reasonably expect that the [.class] file was
produced by the Java compiler and all the instructions satisfy the
constraints and requirements of the language, there is no guarantee that
the loaded file was produced by the known compiler implementation, or a
compiler at all.

That\'s why the first step of the linking process is verification. This
makes sure that the binary representation of the class is structurally
correct, which means the following:

-   -   The arguments of each method invocation are compatible with the
        method descriptor.
    -   The return instruction matches the return type of its method.
    -   Some other checks and verification that vary depending on the
        JVM vendor.

2.  **Preparation of static fields in the method area**:

After verification is successfully completed, the interface or class
(static) variables are created in the method area and initialized to the
default values of their types. The other kinds of initialization, such
as the explicit assignments specified by a programmer and static
initialization blocks, are deferred to the process called **class
initialization** (see the *Class initialization* section).

3.  **Resolution of symbolic references into concrete references that
    point to the method area**:

If the loaded bytecodes refer to other methods, interfaces, or classes,
the symbolic references are resolved into concrete references that point
to the method area, which is done by the resolution process. If the
referred interfaces and classes are not loaded yet, the classloader
finds them and loads as needed.

Class initialization {#class-initialization .header-title}
--------------------

According to the JVM specification, the initialization is accomplished
by executing the class initialization methods. That is when the
programmer-defined initialization (in static blocks and static
assignments) is performed, unless the class was already initialized at
the request of another class.

The last part of this statement is an important one because the class
may be requested several times by different (already loaded) methods,
and also because JVM processes are executed by different threads and may
access the same class concurrently. So, **coordination** (called also
**synchronization**) between different threads is required, which
substantially complicates the JVM implementation.

Class instantiation {#class-instantiation .header-title}
-------------------

This step may never happen. Technically, an instantiation process,
triggered by the [new] operator, is the first step of the
execution. If the [main(String\[\])] method (which is static) uses
only static methods of other classes, the instantiation never happens.
That\'s why it is reasonable to identify this process as separate from
the execution.

Besides, this activity has very specific tasks:

-   Allocating memory for the object (its state) in the heap area
-   Initialization of the instance fields to the default values
-   Creating thread stacks for Java and native methods

Execution starts when the first method (not a constructor) is ready to
be executed. For every application thread, a dedicated runtime stack is
created, where every method call is captured in a stack frame. For
example, if an exception happens, we get data from the current stack
frames when we call the [printStackTrace()] method.

Method execution {#method-execution .header-title}
----------------

The first application thread (called **main thread**) is created when
the [main(String\[\])] method starts executing. It can create
other application threads.

The execution engine reads the bytecodes, interprets them, and sends the
binary code to the microprocessor for  execution. It also maintains a
count of how many times and how often each method was called. If the
count exceeds a certain threshold, the execution engine uses a compiler,
called the **Just-In-Time** (**JIT**) compiler, which compiles the
method bytecodes into native code. This way, the next time the method is
called, it will be ready without needing an interpretation. It
substantially improves code performance.

The instruction currently being executed and the address of the next
instruction are maintained in the **program counter** (**PC**)
registers. Each thread has its own dedicated PC registers. It also
improves performance and keeps track of the execution.

Garbage collection {#garbage-collection .header-title}
------------------

The **garbage collector** (**GC**) runs the process that identifies the
objects that are not referenced anymore and can be removed from the
memory.

There is a Java static method, [System.gc()], which can be used
programmatically to trigger the GC, but the immediate execution is not
guaranteed. Every GC cycle affects the application performance, so the
JVM has to maintain a balance between memory availability and the
ability to execute the bytecodes quickly enough.

Application termination {#application-termination .header-title}
-----------------------

There are several ways an application can be terminated (and the JVM
stopped or exited) programmatically:

-   Normal termination, without an error status code
-   Abnormal termination, because of an unhandled exception
-   Forced programmatic exit, with or without an error status code

If there are no exceptions and infinite loops, the
[main(String\[\])] method completes with a return statement or
after its last statement is executed. As soon as it happens, the main
application thread passes the control flow to the JVM and the JVM stops
executing too. That is the happy end, and many applications enjoy it in
real life. Most of our examples, except those when we have demonstrated
exceptions or infinite loops, have exited successfully too.

However, there are other ways a Java application can exit, some of them
quite graceful too -- others not so much. If the main application thread
created child threads or, in other words, a programmer has written code
that generates other threads, even the graceful exit may not be easy. It
all depends on the kind of child threads created.

If any of them is a user thread (the default), then the JVM instance
continues to run even after the main thread exits. Only after all user
threads are completed does the JVM instance stop. It is possible for the
main thread to request the child user thread to complete. But until it
exits, the JVM continues running. And this means that the application is
still running too.

But if all child threads are daemon threads, or there are no child
threads running, the JVM instance stops running as soon as the main
application thread exits.

How the application exits in the case of an exception depends on the
code design. We have touched on this in [Chapter
4](https://subscription.packtpub.com/book/programming/9781789957051/4), *Exception
Handling,* while discussing the best practices of exception handling. If
the thread captures all the exceptions in a try-catch block in
[main(String\[\])] or a similarly high-level method, then it is up
to the application (and the programmer who wrote the code) to decide how
best to proceed -- to try to change the input data and repeat the block
of code that generated the exception, to log the error and continue, or
to exit.

If, on the other hand, the exception remains unhandled and propagates
into the JVM code, the thread (where the exception happened) stops
executing and exits. What happens next depends on the type of the thread
and some other conditions. The following are four possible options:

-   If there are no other threads, the JVM stops executing and returns
    an error code and the stack trace.
-   If the thread with an unhandled exception was not the main one,
    other threads (if present) continue running.
-   If the main thread has thrown an unhandled exception and the child
    threads (if present) are daemons, they exit too.
-   If there is at least one user child thread, the JVM continues
    running until all user threads exit.

There are also ways to programmatically force the application to stop:

-   [System.exit(0);]
-   [Runtime.getRuntime().exit(0);]
-   [Runtime.getRuntime().halt(0);]

All of these methods force the JVM to stop executing any thread and exit
with a status code passed in as the parameter ([0] in our
examples):

-   Zero indicates normal termination.
-   The nonzero value indicates abnormal termination.

If the Java command was launched by some script or another system, the
value of the status code can be used for the automation of the
decision-making regarding the next step. But that is already outside the
application and Java code.

The first two methods have identical functionality because this is how
[System.exit()] is implemented:


```
public static void exit(int status) { 
    Runtime.getRuntime().exit(status); 
}
```

To see the source code in the IDE, just click on the method.


The JVM exits when some thread invokes the [exit()] method of the
[Runtime] or [System] classes, or the [halt()] method
of the [Runtime] class, and the exit or halt operation is
permitted by the security manager. The difference between [exit()]
and [halt()] is that [halt()] forces the JVM exit
immediately, while [exit()] performs additional actions that can
be set using the [Runtime.addShutdownHook()] method. But all these
options are rarely used by mainstream programmers.

JVM structure
====================================

JVM structure can be described in terms of the runtime data structure in
the memory and in terms of the two subsystems that use the runtime data
-- the classloader and the execution engine.

Runtime data areas {#runtime-data-areas .header-title}
------------------

Each of the runtime data areas of JVM memory belongs to one of two
categories:

-   **Shared areas** that include the following:
    -   **Method area**: Class metadata, static fields, and method
        bytecodes
    -   **Heap area**: Objects (states)
-   **Unshared areas** dedicated to a particular application thread,
    which includes the following:
    -   **Java stack**: Current and caller frames, each frame keeping
        the state of Java (non native) method invocation:
        -   Values of local variables
        -   Method parameter values
        -   Values of operands for intermediate calculations (operand
            stack)
        -   Method return value (if any)
    -   **PC register**: The next instruction to execute
    -   **Native method stack**: The state of the native method
        invocations

We have already discussed that a programmer has to be careful when using
reference types and not modify the object itself unless it needs to be
done. In a multi-threaded application, if a reference to an object can
be passed between threads, we have to be extra careful because of the
possibility of the concurrent modification of the same data. On the
bright side, though, such a shared a area can be and often is used as
the method of communication between threads. 

Classloaders {#classloaders .header-title}
------------

The classloader performs the following three functions:

-   Reads a [.class] file
-   Populates the method area
-   Initializes static fields not initialized by a programmer

Execution engine {#execution-engine .header-title}
----------------

The execution engine does the following:

-   Instantiates objects in the heap area
-   Initializes static and instance fields, using initializers written
    by the programmer
-   Adds/removes frames to and from the Java stack
-   Updates the PC register with the next instruction to execute
-   Maintains the native method stack
-   Keeps count of method calls and compiles popular ones
-   Finalizes objects
-   Runs garbage collection
-   Terminates the application

Garbage collection
====================================

Automatic memory management is an important aspect of JVM that relieves
the programmer from the need to do it programmatically. In Java, the
process that cleans up memory and allows it to be reused is called
a **garbage collection**.

Responsiveness, throughput, and stop-the-world {#responsiveness-throughput-and-stop-the-world .header-title}
----------------------------------------------

The effectiveness of GC affects two major application characteristics
-- **responsiveness** and **throughput**:

-   **Responsiveness**: This is measured by how quickly an application
    responds (brings the necessary data) to the request; for example,
    how quickly a website returns a page, or how quickly a desktop
    application responds to an event. The smaller the response time, the
    better the user experience.
-   **Throughput**: This indicates the amount of work an application can
    do in a unit of time; for example, how many requests a web
    application can serve, or how many transactions the database can
    support. The bigger the number, the more value the application can
    potentially generate and the more user requests it can support.

Meanwhile, GC needs to move data around, which is impossible to
accomplish while allowing data processing because the references are
going to change. That\'s why GC needs to stop application thread
execution once in a while for a period of time, called
**stop-the-world***.* The longer these periods are, the quicker GC does
its job and the longer an application freeze lasts, which can eventually
grow big enough to affect both the application\'s responsiveness and
throughput.

Fortunately, it is possible to tune the GC behavior using Java command
options, but that is outside the scope of this book. We will concentrate
instead on a high-level view of the main activity of a GC inspecting
objects in the heap and removing those that don\'t have references in
any thread stack.

Object age and generations {#object-age-and-generations .header-title}
--------------------------

The basic GC algorithm determines *how old* each object is. The term
**age** refers to the number of collection cycles the object has
survived.

When JVM starts, the heap is empty and is divided into three sections:

-   The young generation
-   The old or tenured generation
-   Humongous regions for holding the objects that are 50% of the size
    of a standard region or larger

The young generation has three areas:

-   An Eden space
-   Survivor 0 (S0)
-   Survivor 1 (S1)

The newly created objects are placed in Eden. When it is filling up, a
minor GC process starts. It removes the unreferred and circular referred
objects and moves the others to the S1 area. During the next minor
collection, S0 and S1 switch roles. The referenced objects are moved
from Eden and S1 to S0.

During each of the minor collections, the objects that have reached a
certain age are moved to the old generation. As a result of this
algorithm, the old generation contains objects that are older than a
certain age. This area is bigger than the young generation and, because
of that, the garbage collection here is more expensive and happens not
as often as in the young generation. But it is checked eventually (after
several minor collections). The unreferenced objects are removed and the
memory is defragmented. This cleaning of the old generation is
considered a major collection.

When stop-the-world is unavoidable {#when-stop-the-world-is-unavoidable .header-title}
----------------------------------

Some collections of the objects in the old generation are done
concurrently and some are done using stop-the-world pauses. The steps
include the following:

1.  **Initial marking**: This marks the survivor regions (root regions)
    that may have references to objects in the old generation and is
    done using a stop-the-world pause.
2.  **Scanning**: This searches survivor regions for references to the
    old generation, and is  done concurrently while the application
    continues to run.
3.  **Concurrent marking**: This marks live objects over the entire heap
    and is done concurrently while the application continues to run.
4.  **Remark**: The completes the marking of live objects and is done
    using a stop-the-world pause.
5.  **Cleanup**: The calculates the age of live objects and frees
    regions (using stop-the-world) and returns them to the free list.
    This is done concurrently.

The preceding sequence might be interspersed with the young generation
evacuations because most of the objects are short-lived and it is easier
to free up a lot of memory by scanning the young generation more often.
There is also a mixed phase (when G1 collects the regions already marked
as mostly garbage in both the young and the old generations), and
humongous allocation (when large objects are moved to or evacuated from
humongous regions).

To help with GC tuning, the JVM provides platform-dependent default
selections for the garbage collector, heap size, and runtime compiler.
But fortunately, the JVM vendors improve and tune the GC process all the
time, so most of the applications work just fine with default GC
behavior.

Summary
====================================

In this chapter, the reader has learned how a Java application can be
executed using an IDE or the command line. Now you can write your own
applications and launch them in a manner most appropriate for the given
environment. Knowledge about the JVM structure and its processes --
class loading, linking, initialization, execution, garbage
collection, and application termination -- provides you with better
control over the application\'s execution and transparency about the
performance and current state of the JVM.

In the next chapter, we will discuss and demonstrate how to manage --
insert, read, update, and delete -- data in a database from a Java
application. We will also provide a short introduction to SQL language
and basic database operations: how to connect to a database, how to
create the database structure, how to write database expressions using
SQL, and how to execute them.
