<img align="right" src="../logo-small.png">


Java - Getting New Features 
===========================

In this chapter, the reader will learn about the current most
significant projects that will add new features to Java and enhance it
in other aspects. After reading this chapter, the reader will understand
how to follow Java development and will envision the road map of future
Java releases. If so desired, the reader can become a JDK source
contributor too.

The following topics will be covered in this chapter:

-   Java continues to evolve
-   Panama project
-   Valhalla project
-   Amber project
-   Loom project
-   Skara project



Java continues to evolve
===========================

This is the best news for any Java developer: Java is actively supported
and continues to be enhanced to stay abreast with the latest demands of
the industry. It means that whatever you hear about other languages and
newest technologies, you will get the best features and functionality
added to Java soon. And with the new release schedule---every half
year---you can be assured that the new additions will be released as
soon as they prove to be useful and practical. 

While thinking about designing a new application or new functionality to
be added to the existing one, it is important to know how Java may be
enhanced in the near future. Such knowledge can help you to design the
new code in such a manner that it will be easier to accommodate new Java
functions and make your application simpler and more powerful. To follow
all the **JDK Enhancement Proposals** (**JEP**) could be impractical for
a mainstream programmer because one has to follow too many different
threads of discussion and development. By contrast, staying on top of
one of the Java enhancement projects in the area of your interests is
much easier. You could even try to contribute to one such project as an
expert in a particular area, or just as an interested party.

In this chapter, we are going to review the five most important, in our
opinion, Java enhancement projects:

-   **Project Panama**: Focused on the interoperability with non-Java
    libraries
-   **Project Valhalla**: Conceived around the introduction of a new
    value type and related generics enhancements
-   **Project Amber**: Aims to bring features that can make writing Java
    code more readable and concise and target specific use cases such
    as data class, pattern match, raw string literals, concise method
    bodies, and lambda enhancements, to name the most significant
    sub-projects
-   **Project Loom**: Addresses the creation of lightweight threads
    called **fibers** and makes asynchronous coding easier

Panama project
===========================

Throughout the book, we advised using various Java libraries--- the
standard **Java Class Library** (**JCL**) and external Java libraries
that help to improve code quality and make the development time shorter.
But there are also non-Java external libraries that may be needed for
your application. Such a need has increased recently with the growing
demand for using machine learning algorithms for data processing. The
porting of these algorithms to Java does not always keep up with the
latest achievements in the area of recognizing faces, classifying human
actions in videos, and tracking camera movements, for example.

The existing mechanism of utilizing the libraries written in different
languages is **Java Native Interface** (**JNI**), **Java Native Access**
(**JNA**), and **Java Native Runtime** (**JNR**). Despite all these
facilities, accessing the native code (the code in other languages
compiled for the particular platform) is not as easy as using a Java
library. Besides, it limits the **Java Virtual Machine** (**JVM**) code
optimization and often requires writing code in C. 

The **Panama** project (<https://openjdk.java.net/projects/panama>) is
set to address these issues, including the support of C++ functionality.
The authors use the term **foreign libraries***.* This term includes all
the libraries in other languages. The idea behind the new approach is
to translate the native headers into the corresponding Java interfaces
using a tool called **jextract**. The generated interfaces allow
accessing the native methods and data structures directly, without
writing C code.

Not surprisingly, the supporting classes are planned to be stored in the
[java.foreign] package.

At the time of writing (March 2019), the early-access builds of
Panama are based on an incomplete version of Java 13 and intended for
expert users. It is expected to reduce the amount of work for creating
Java bindings for native libraries by 90% and produce code that performs
four to five times faster than JNI at least.


Valhalla project
===========================

The motivation for the **Valhalla project**
(<https://openjdk.java.net/projects/valhalla>) came from the fact that,
since Java was first introduced almost 25 years ago, the hardware has
changed and the decisions made at that time would have a different
outcome today. For example, the operation of getting a value from memory
and an arithmetic operation incurred roughly the same cost in terms of
the performance time. Nowadays, the situation has changed. The memory
access is from 200 to 1,000 times longer than an arithmetic operation.
This means that an operation that involves primitive types is much
cheaper than the operation based on their wrapping types.

When we do something with two primitive types, we grab values and use
them in an operation. When we do the same operation with wrapper types,
we first use the reference to access the object (which is now much
longer---relative to the operation itself---than 20 years ago), and only
then can we grab the value. That is why the Valhalla project attempts to
introduce for a reference type a new **value** type, which provides
access to a value without using the reference---the same way a primitive
type is available by value.

It will also save on memory consumption and the efficiency of wrapping
arrays. Each element will be now represented by a value, not by a
reference.

Such a solution logically leads to the question of generics. Today,
generics can be used only for a wrapping type. We can write
[List\<Integer\>], but we cannot write [List\<int\>]. And
that is what the Valhalla project is poised to address too. It is going
to *extend generic types to support the specialization of generic
classes and interfaces over primitive types*. The extension will allow
using a primitive type in generics too.

Amber project
===========================

The **Amber** **project** (<https://openjdk.java.net/projects/amber>) is
focused on small Java syntax enhancements that would make it more
expressive, concise, and simpler. These improvements are going to
increase the Java programmers\' productivity and make their code-writing
more enjoyable. 

Two Java features created by the Amber project have been delivered
already.
Other new features are going to be released with the future Java
version. We will look closer only at five of them in the following
subsections:

-   Data class
-   Pattern match
-   Raw string literals
-   Concise method bodies
-   Lambda leftovers

Data class {#data-class .header-title}
----------

There are classes that carry data only. Their purpose is to keep several
values together and nothing else. For example:


```
public class Person {
    public int age;
    public String firstName, lastName;

    public Person(int age, String firstName, String lastName) {
        this.age = age;
        this.lastName = lastName;
        this.firstName = firstName;
    }
}
```

They may also include the standard set of the [equals()],
[hashCode()], and [toString()] methods. If that is the case,
why bother and write the implementation for these methods? They can be
automatically generated - the same way your IDE can do it today. That is
the idea behind the new entity called **data class** that can be defined
as simply as follows:


```
record Person(int age, String firstName, String lastName) {}
```

The rest will be assumed present by default. 

But then, as Brian Goetz wrote
(<https://cr.openjdk.java.net/~briangoetz/amber/datum.html>), the
questions start coming:

*\"Are they extensible? Are the fields mutable? Can I control the
behavior of the generated methods or the accessibility of the fields?
Can I have additional fields and constructors?\"*


\- *Brian Goetz*

That is where the current state of this idea is---in the middle of the
attempt to limit the scope and still provide a value to the language.

Stay tuned. 

Pattern match {#pattern-match .header-title}
-------------

From time to time, almost every programmer encounters the need to switch
to different processing of the value depending on its type. For example:


```
SomeClass someObj = new SomeClass();
Object value = someOtherObject.getValue("someKey");
if (value instanceof BigDecimal) {
    BigDecimal v = (BigDecimal) value;
    BigDecimal vAbs = v.abs();
    ...
} else if (value instanceof Boolean) {
    Boolean v = (Boolean)value;
    boolean v1 = v.booleanValue();
    ...
} else if (value instanceof String) {
    String v = (String) value;
    String s = v.substring(3);
    ...
}
...
```

While writing such a code, you get bored pretty quickly. And that is
what pattern matching is going to fix. After the feature is implemented,
the preceding code example can be changed to look as follows:


```
SomeClass someObj = new SomeClass();
Object value = someOtherObject.getValue("someKey");
if (value instanceof BigDecimal v) {
    BigDecimal vAbs = v.abs();
    ...
} else if (value instanceof Boolean v) {
    boolean v1 = v.booleanValue();
    ...
} else if (value instanceof String v) {
    String s = v.substring(3);
    ...
}
...
```

Nice, isn\'t it? It will also support the inlined version, such as the
following one:


```
if (value instanceof String v && v.length() > 4) {
    String s = v.substring(3);
    ...
}
```

This new syntax will first be allowed in an [if] statement and
later added to a [switch] statement too.

Raw string literals {#raw-string-literals .header-title}
-------------------

Once in a while, you may wish to have an output indented, so it will
look something like this, for example:

![]./images_17/a637643d-9433-4819-9aff-9df0b3156aac.png)

To achieve it, the code looks as follows:


```
String s = "The result:\n" +
           "   - the symbol A was not found;\n" +
           "   - the type of the object was not Integer either.";
System.out.println(s); 
```

After adding the new *raw string literal,* the same code can be changed
to look like this:


```
String s = `The result:
               - the symbol A was not found;
               - the type of the object was not Integer either.
           `;
System.out.println(s); 
```

This way, the code looks much less cluttered and easier to write. It
will also be possible to align the raw string literal against left
margin using the [align()] method, set an indent value using
the [indent(int n)] method, and the value of the indent after
alignment using the [align(int indent)] method.

Similarly, putting the string inside the symbols ([\`]) will allow
us to avoid using the escape indicator backslash ([\\]). For
example, while executing a command, the current code may contain this
line:


```
Runtime.getRuntime().exec("\"C:\\Program Files\\foo\" bar");
```

With a raw string literal in place, the same line can be changed to the
following:


```
Runtime.getRuntime().exec(`"C:\Program Files\foo" bar`);
```

Again, it is easier to write and to read.

Concise method bodies {#concise-method-bodies .header-title}
---------------------

The idea of this feature was expired by the lambda expressions syntax,
which can be very compact. For example:


```
Function<String, Integer> f = s -> s.length();
```

Or, using method reference, it can be expressed even shorter:


```
Function<String, Integer> f = String::length;
```

The logical extension of this approach was this: why not apply the same
short-hand style to the standard getters? Look at this method:


```
String getFirstName() { return firstName; }
```

It can easily be shortened to the following form:


```
String getFirstName() -> firstName;
```

Or, consider the case when the method uses another method:


```
int getNameLength(String name) { return name.length(); }
```

It can be shortened by the method reference too, as follows:


```
int getNameLength(String name) = String::length;
```

But, as of this writing (March 2019), this proposal is still in the
early stages, and many things can be changed in the final release.

Lambda leftovers {#lambda-leftovers .header-title}
----------------

The Amber project plans three additions to the lambda expressions
syntax:

-   Shadowing local variable
-   Netter disambiguation of functional expressions
-   Using an underscore to indicate a not-used parameter

Using an underscore instead of a parameter name  {#using-an-underscore-instead-of-a-parameter-name .header-title}
------------------------------------------------

In many other programming languages, an underscore ([\_]) in a
lambda expression denotes an unnamed parameter. After Java 9 made it
illegal to use an underscore as an identifier, the Amber project plans
to use it for a lambda parameter in the cases when this parameter is not
actually needed for the current implementation. For example, look at
this function:


```
BiFunction<Integer, String, String> f = (i, s) -> s.substring(3);
```

The parameter ([i]) is not used in the function body, but we still
provide the identifier as a placeholder. 

With the new addition, it will be possible to replace it with the
underscore, thus avoiding using an identifier and indicating that the
parameter is never used:


```
BiFunction<Integer, String, String> f = (_, s) -> s.substring(3);
```

This way, it is more difficult to miss the fact that one input value is
not used.

Shadowing a local variable {#shadowing-a-local-variable .header-title}
--------------------------

Currently, it is not possible to give a parameter of a lambda expression
the same name as is used as an identifier in the local context. For
example:


```
int i = 42;
//some other code
BiFunction<Integer, String, String> f = (i, s) -> s.substring(3); //error
```

In future releases, such name reuse will be possible.

Better disambiguation of functional expressions {#better-disambiguation-of-functional-expressions .header-title}
-----------------------------------------------

As of this writing, it is possible to have a method overloaded as
follows:


```
void method(Function<String, String> fss){
    //do something
}
void method(Predicate<String> predicate){
    //do something
}
```

But, it is possible to use it only by defining the type of the passed-in
function explicitly:


```
Predicate<String> pred = s -> s.contains("a");
method(pred);
```

An attempt to use it with the inlined lambda expression will fail:


```
method(s -> s.contains("a"));   // compilation error
```

The compiler complains because of an ambiguity it cannot resolve because
both functions have one input parameter of the same type and are
different only when it comes to the [return] type.

The Amber project may address it, but the final decision is not made yet
because it depends on the effect this proposal has on the compiler
implementation. 


Loom project
===========================

The **Loom project** (<https://openjdk.java.net/projects/loom>) may be
the most significant of the projects listed in this chapter that can
give Java a power boost. From the very early days almost 25 years ago,
Java provided a relatively simple multi-threading model with a
well-defined synchronization mechanism. We described it in [Chapter
8](https://subscription.packtpub.com/book/programming/9781789957051/8),
*Multithreading and Concurrent Processing*. This simplicity, as well as
the overall simplicity and security of Java, was one of the main factors
of Java\'s success. Java servlets allowed the processing of many
concurrent requests and were at the foundation of Java-based HTTP
servers.

The thread in Java is based on the OS kernel thread, though, which is a
general-purpose thread. But the kernel OS thread was designed to perform
many different system tasks too. It makes such a thread too heavy
(requiring too many resources) for the business needs of a particular
application. The actual business operations necessary to satisfy a
request received by an application, typically do not require all the
thread capability. This means that the current thread-model limits the
application power. To estimate how strong the limitation is, it is
enough to observe that an HTTP server today can handle more than a
million concurrent open sockets, while the JVM cannot handle more than a
few thousand.

That was the motivation for introducing asynchronous processing, using
the threads minimally and introducing lightweight processing workers
instead. We talked about it in [Chapter
15](https://subscription.packtpub.com/book/programming/9781789957051/15),
*Reactive Programming* and [Chapter
16](https://subscription.packtpub.com/book/programming/9781789957051/16),
*Microservices*. The asynchronous processing model works very well, but
its programming is not as simple as in other Java programming. It also
requires a significant effort to integrate with legacy code based on the
threads and even more effort to migrate the legacy code to adopt the new
model. 

Adding such a complexity made Java not as easy to learn as it used to
be, and the Loom project is set to bring the simplicity of Java
concurrent processing back into use by making it more lightweight.

The project plans to add to Java a new class, [Fiber], in support
of a lightweight thread construct, managed by the JVM. Fibers take much
fewer resources. They also have almost no or very little overhead of
context switching, the procedure necessary when a thread is suspended
and another thread has to start or continue its own job that was
suspended because of the CPU time-share or similar. The context
switching of the current threads is one of the main reasons for
performance limitation.

To give you an idea of how light the fibers are, compared to the
threads, the Loom developers Ron Pressler and Alan Bateman provided the
following numbers
(<http://cr.openjdk.java.net/~rpressler/loom/JVMLS2018.pdf>):

-   **Thread**:
    -   Typically 1 MB reserved for stack + 16 KB of kernel data
        structures
    -   \~2,300 bytes per started thread, includes **Virtual Machine**
        (**VM**) metadata 
-   **Fiber**:
    -   Continuation stack: hundreds of bytes to KBs
    -   200-240 bytes per fiber in the current prototype

As you can see, we can hope there will a significant improvement in the
performance of concurrent processing.

The term **continuation** is not new. It was used before *fibers*. It
denotes *a sequence of instructions that execute sequentially, and may
suspend itself*. Another part of the concurrent processor is a
**scheduler** that *assigns continuations to CPU cores, replacing a
paused one with another that\'s ready to run, and ensuring that a
continuation that is ready to resume will eventually be assigned to a
CPU core.* A current thread model also has a continuation and a
scheduler, even if they are not always exposed as APIs. The Loom project
intends to separate the continuation and the scheduler and to implement
Java fibers on top of them. The existing [ForkJoinPool] will
probably serve as the fiber. 

You can read more about the motivation and goals of the Loom project in
the project proposal
(<https://cr.openjdk.java.net/~rpressler/loom/Loom-Proposal.html>),
which is a relatively easy and very instructive read for any Java
developer.


Skara project
===========================

The **Skara project** (<http://openjdk.java.net/projects/skara>) is not
adding new features to Java. It is focused on improving access to the
Java source code of JDK. 

To access the source code today, you need to download it from a
Mercurial repository and compile it manually. The Skara project\'s goal
is to move the source code to Git because Git is now the most popular
source repository, and many programmers use it already. The source code
of the examples in this book, as you know, is stored on GitHub too. 

The current results of the Skara project you can see in GitHub already
(<https://github.com/Project-Skara/jdk>). Well, it still uses a mirror
of the JDK Mercurial repository. But, in the future, it will become more
independent.


Summary
===========================

In this chapter, the reader has learned about the current most
significant projects that enhance the JDK. We hope that you were able to
understand how to follow Java development and have envisioned the road
map of future Java releases. There are many more on-going projects
(<https://openjdk.java.net/projects>) that you can look at too. We also
hope that you got sufficiently excited by the prospect to become a
productive JDK source contributor and active community member. Welcome!
