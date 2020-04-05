<img align="right" src="../logo-small.png">


Numeric stream interfaces
=====================

In this chapter, we will talk about processing data streams, which are
different from the I/O streams we reviewed in
[Chapter 5] *Strings,
Input/Output, and Files*. We will define what data streams are, how to
process their elements using methods (operations) of
the [java.util.stream.Stream] object, and how to chain (connect)
stream operations in a pipeline. We will also discuss stream
initialization and how to process streams in parallel.

The following topics will be covered in this chapter, as follows:

-   Streams as a source of data and operations
-   Stream initialization
-   Operations (methods)
-   Numeric stream interfaces
-   Parallel streams


<h3><span style="color:red;">Run Java Code</span></h3>

You can run the example by running following command in the terminal:
`java -cp target/learnjava-1.0.jar:target/libs/* com.lv.learnjava.ch14_streams.Initialize`

<h3><span style="color:red;">Run Java Code</span></h3>

You can run the example by running following command in the terminal:
`java -cp target/learnjava-1.0.jar:target/libs/* com.lv.learnjava.ch14_streams.IntermediateOps`

<h3><span style="color:red;">Run Java Code</span></h3>

You can run the example by running following command in the terminal:
`java -cp target/learnjava-1.0.jar:target/libs/* com.lv.learnjava.ch14_streams.NumericStreams`

<h3><span style="color:red;">Run Java Code</span></h3>

You can run the example by running following command in the terminal:
`java -cp target/learnjava-1.0.jar:target/libs/* com.lv.learnjava.ch14_streams.Person`

<h3><span style="color:red;">Run Java Code</span></h3>

You can run the example by running following command in the terminal:
`java -cp target/learnjava-1.0.jar:target/libs/* com.lv.learnjava.ch14_streams.TerminalOps`


Streams as a source of data and operations
=====================

Lambda expressions, described and demonstrated in the previous chapter,
together with functional interfaces added a powerful functional
programming capability to Java. They allow passing behavior (functions)
as parameters to libraries optimized for the performance of the data
processing. This way, an application programmer can concentrate on the
business aspects of the developed system, leaving the performance
aspects to the specialists -- the authors of the library. One example of
such a library is [java.util.stream], which is going to be the
focus of this chapter.

In
*Strings, Input/Output, and Files*, we talked about I/O streams as the
source of data, but beyond that, they are not of much help for the
further processing of the data. And they are byte- or character-based,
not object-based. You can create a stream of objects only after the
objects have been programmatically created and serialized first. The I/O
streams are just connections to external resources, mostly files and not
much else. However, sometimes it is possible to make a transition from
an I/O stream to [java.util.stream.Stream]. For example, the
[BufferedReader] class has the [lines()] method that
converts the underlying character-based stream into a
[Stream\<String\>] object.

The streams of the [java.util.stream] package, on the other hand,
are oriented on processing collections of objects. In 
*Data Structures, Generics, and Popular Utilities,* we described two
methods of the [Collection] interface that allow reading
collection elements as elements of a stream: [default Stream\<E\>
stream()] and [default Stream\<E\> parallelStream()]. We
also have mentioned the [stream()] method
of [java.util.Arrays]. It has the following eight overloaded
versions that convert an array or a part of it into a stream of the
corresponding data types:

-   [static DoubleStream stream(double\[\] array)]
-   [static DoubleStream stream(double\[\] array, int startInclusive,
    int endExclusive)]
-   [static IntStream stream(int\[\] array)]
-   [static IntStream stream(int\[\] array, int startInclusive, int
    endExclusive)] 
-   [static LongStream stream(long\[\] array)] 
-   [static LongStream stream(long\[\] array, int startInclusive, int
    endExclusive)] 
-   [static \<T\> Stream\<T\> stream(T\[\] array)] 
-   [static \<T\> Stream\<T\> stream(T\[\] array, int startInclusive,
    int endExclusive)] 

Let\'s now look at the streams of the
package [java.util.stream] closer. The best way to understand what
a stream is to compare it with a collection. The latter is a data
structure stored in memory. Every collection element is computed before
being added to the collection. By contrast, an element emitted by a
stream exists somewhere else, in the source, and is computed on demand.
So, a collection can be a source for a stream.

A [Stream] object is an implementation
of [Stream] interface, [IntStream], [LongStream], or
[DoubleStream]; the last three are called **numeric streams**. The
methods of the [Stream] interface are also available in numeric
streams. Some of the numeric streams have a few extra methods, such
as [average()] and [sum()], specific numeric values. In this
chapter, we are going to speak mostly about the [Stream] interface
and its methods, but everything we will cover is equally applicable to
numeric streams too.

A stream *produces (*or *emits*) stream elements as soon as the
previously emitted element has been processed. It allows declarative
presentation of methods (operations) that can be applied to the emitted
elements, also in parallel. Today, when the machine learning
requirements of large dataset processing are becoming ubiquitous, this
feature reinforces the position of Java among the few modern programming
languages of choice.


Stream initialization
=====================

There are many ways to create and initialize a stream -- an object of
type [Stream] or any of the numeric interfaces. We grouped them by
classes and interfaces that have [Stream]-creating methods. We did
it for the reader\'s convenience, so it would be easier for the reader
to remember and find them if need be.

Stream interface {#stream-interface .header-title}
----------------

This group of [Stream] factories is composed of static methods
that belong to the [Stream] interface.

empty() {#empty .header-title}
-------

The [Stream\<T\> empty()] method creates an empty stream that does
not emit any element:


```
Stream.empty().forEach(System.out::println);   //prints nothing
```

The [Stream] method [forEach()] acts similarly to the
[Collection] method [forEach()] and applies the passed-in
function to each of the stream elements:


```
new ArrayList().forEach(System.out::println);  //prints nothing
```

The result is the same as creating a stream from an empty collection:


```
new ArrayList().stream().forEach(System.out::println);  //prints nothing
```

Without any element emitted, nothing happens. We will discuss
the [Stream] method [forEach()] in the *Terminal operations*
section.

of(T\... values) {#oft...-values .header-title}
----------------

The [of(T\... values)] method accepts varargs and can also create
an empty stream:


```
Stream.of().forEach(System.out::print);       //prints nothing
```

But it is most often used for initializing a non-empty stream:


```
Stream.of(1).forEach(System.out::print);           //prints: 1
Stream.of(1,2).forEach(System.out::print);         //prints: 12
Stream.of("1 ","2").forEach(System.out::print);    //prints: 1 2
```

Notice the method reference used for the invocation of
the [println()] and [print()] methods.

Another way to use the [of(T\... values)] method is as follows:


```
String[] strings = {"1 ", "2"};
Stream.of(strings).forEach(System.out::print);      //prints: 1 2
```

If there is no type specified for the [[Stream]] object, the
compiler does not complain if the array contains a mix of types:


```
Stream.of("1 ", 2).forEach(System.out::print);      //prints: 1 2
```

[Adding generics that declare the expected element type causes
an exception when at least one of the listed elements has a different
type:]


```
//Stream<String> stringStream = Stream.of("1 ", 2);   //compile error
```

Generics help a programmer to avoid many mistakes, so they should be
added wherever possible.

The [[of(T\... values)]] method can also be used for the
concatenation of multiple streams. Let\'s assume, for example, that we
have the following four streams that we would like to concatenate into
one:


```
Stream<Integer> stream1 = Stream.of(1, 2);
Stream<Integer> stream2 = Stream.of(2, 3);
Stream<Integer> stream3 = Stream.of(3, 4);
Stream<Integer> stream4 = Stream.of(4, 5);
```

We would like to concatenate them into a new stream that emits values
[1,2,2,3,3,4,4,5]. First, we try the following code:


```
Stream.of(stream1, stream2, stream3, stream4)
      .forEach(System.out::print);
              //prints: java.util.stream.ReferencePipeline$Head@58ceff1j
```

It does not do what we hoped for. It treats each stream as an object of
the internal class [[java.util.stream.ReferencePipeline]]
that is used in the [[Stream]] interface implementation. So,
we need to add the [[flatMap()]] operation to convert each
stream element into a stream (we describe it in the *Intermediate
operations* section):


```
Stream.of(stream1, stream2, stream3, stream4)
      .flatMap(e -> e).forEach(System.out::print);   //prints: 12233445
```

The function we passed into [[flatMap()]] as a parameter ([[e
-\> e]]) looks like it\'s doing nothing, but that is because
each element of the stream is a stream already, so there is no need to
transform it. By returning an element as the result of the
[[flatMap()]] operation, we tell the pipeline to treat the
return value as a [[Stream]] object.

ofNullable(T t) {#ofnullablet-t .header-title}
---------------

The [ofNullable(T t)] method returns a [Stream\<T\>]
emitting a single element if the passed-in parameter [t] is not
[null]; otherwise, it returns an empty [Stream]. To
demonstrate the usage of the [ofNullable(T t)] method, we have
created the following method:


```
void printList1(List<String> list){
    list.stream().forEach(System.out::print);
}
```

We have executed this method twice - with the parameter list equal
to [null] and to a [List] object. Here are the results:


```
//printList1(null);                          //NullPointerException
List<String> list = List.of("1 ", "2");
printList1(list);                            //prints: 1 2
```

Notice how the first call to the [printList1()] method generates
[NullPointerException]. To avoid the exception, we could implement
the method as follows:


```
void printList1(List<String> list){ 
     (list == null ? Stream.empty() : list.stream()) 
                           .forEach(System.out::print);
} 
```

The same result can be achieved with the [ofNullable(T t)] method:


```
void printList2(List<String> list){
    Stream.ofNullable(list).flatMap(l -> l.stream())
                           .forEach(System.out::print);
}
```

Notice how we have added [flatMap()] because, otherwise, the
[Stream] element that flows into [forEach()] would be a
[List] object. We will talk more about the [flatMap()]
method in the *Intermediate operations* section. The function passed
into the [flatMap()] operation in the preceding code can be
expressed as a method reference too:


```
void printList4(List<String> list){
    Stream.ofNullable(list).flatMap(Collection::stream)
                           .forEach(System.out::print);
}
```

iterate(Object, UnaryOperator) {#iterateobject-unaryoperator .header-title}
------------------------------

Two static methods of the [Stream] interface allow generating a
stream of values using an iterative process similar to the traditional
[for] loop, as follows:

-   [Stream\<T\> iterate(T seed, UnaryOperator\<T\> func)]:
    Creates an infinite sequential stream based on the iterative
    application of the second parameter, the function [func], to
    the first parameter [seed], producing a stream of values
    [seed], [f(seed)], [f(f(seed))], an so on
-   [Stream\<T\> iterate(T seed, Predicate\<T\> hasNext,
    UnaryOperator\<T\> next)]: Creates a finite sequential stream
    based on the iterative application of the third parameter, the
    [next] function, to the first parameter [seed],
    producing a stream of values
    [seed], [f(seed)], [f(f(seed))], and so on, as
    long as the third parameter, the function [hasNext], returns
    true

The following code demonstrates the usage of these methods, as follows:


```
Stream.iterate(1, i -> ++i).limit(9)
      .forEach(System.out::print); //prints: 123456789

Stream.iterate(1, i -> i < 10, i -> ++i)
      .forEach(System.out::print);        //prints: 123456789
```

Notice that we were forced to add an intermediate operator [limit(int
n)] to the first pipeline to avoid generating an infinite number
of generated values. We will talk more about this method in
the *Intermediate operations* section. 

concat (Stream a, Stream b) {#concat-stream-a-stream-b .header-title}
---------------------------

The [Stream\<T\> concat(Stream\<\> a, Stream\<T\> b)] static
method of the [Stream] interface creates a stream of values based
on two streams, [a] and [b], passed in as parameters. The
newly created stream consists of all the elements of the first
parameter, [a], followed by all the elements of the second
parameter, [b]. The following code demonstrates this method:


```
Stream<Integer> stream1 = List.of(1, 2).stream();
Stream<Integer> stream2 = List.of(2, 3).stream();
Stream.concat(stream1, stream2)
 .forEach(System.out::print); //prints: 1223
```

Notice that element [2] is present in both original streams and
consequently is emitted twice by the resulting stream.

generate (Supplier) {#generate-supplier .header-title}
-------------------

The static method [Stream\<T\> generate(Supplier\<T\> supplier)]
of the [Stream] interface creates an infinite stream where each
element is generated by the provided [Supplier\<T\>] function. The
following are two examples:


```
Stream.generate(() -> 1).limit(5)
 .forEach(System.out::print);        //prints: 11111

Stream.generate(() -> new Random().nextDouble()).limit(5)
      .forEach(System.out::println);      //prints: 0.38575117472619247
                                          //        0.5055765386778835
                                          //        0.6528038976983277
                                          //        0.4422354489467244
                                          //        0.06770955839148762
```

If you run this code, you will probably get different results because of
the random (pseudo-random) nature of the generated values.

Since the created stream is infinite, we have added a [limit(int
n)] operation that allows only the specified number of stream
elements to flow through. We will talk more about this method in
the *Intermediate operations* section. 

Stream.Builder interface {#stream.builder-interface .header-title}
------------------------

The [Stream.Builder\<T\> builder()] static method returns an
internal (located inside the [Stream] interface) interface
[Builder] that can be used to construct a [Stream] object.
The interface [Builder] extends the [Consumer] interface and
has the following methods:

-   [default Stream.Builder\<T\> add(T t)]: Calls
    the [accept(T)] method and returns the ([Builder]
    object), thus allowing chaining [add(T t)] methods in a fluent
    dot- connected style
-   [void accept(T t)]: Adds an element to the stream (this method
    comes from the [Consumer] interface)
-   [Stream\<T\> build()]: Transitions this builder from the
    constructing state to the [built] state; after this method is
    called, no new elements can be added to this stream

The usage of the [add(T t)] method is straightforward:


```
Stream.<String>builder().add("cat").add(" dog").add(" bear")
      .build().forEach(System.out::print);       //prints: cat dog bear
```

Please notice how we have added the generics [\<String\>] in front
of the [builder()] method. This way, we tell the builder that the
stream we are creating will have [String] type elements.
Otherwise, it will add the elements as [Object] types and will not
make sure that the added elements are of the [String] type.

The [accept(T t)] method is used when the builder is passed as a
parameter of the [Consumer\<T\>] type or when you do not need to
chain the methods that add the elements. For example, the following is a
code example:


```
Stream.Builder<String> builder = Stream.builder();
List.of("1", "2", "3").stream().forEach(builder);   
builder.build().forEach(System.out::print);        //prints: 123
```

The [forEach(Consumer\<T\> consumer)] method accepts a
[Consumer] function that has the [accept(T t)] method. Every
time an element is emitted by the stream, the [forEach()] method
receives it and passes it to the [accept(T t)] method of the
[Builder] object. Then, when the [build()] method is called
in the next line, the [Stream] object is created and starts
emitting the elements added earlier by the [accept(T t)] method.
The emitted elements are passed to the [forEach()] method, which
then prints them one by one.

And here is an example of an explicit usage of the [accept(T
t)] method:


```
List<String> values = List.of("cat", " dog", " bear");
Stream.Builder<String> builder = Stream.builder();
for(String s: values){
    if(s.contains("a")){
        builder.accept(s);
    }
}
builder.build().forEach(System.out::print);        //prints: cat bear
```

This time, we decided not to add all the list elements to the stream,
but only those that contain the character [a]. As was expected,
the created stream contains only the [cat] and
[bear] elements. Also, notice how we
use [\<String\>] generics to make sure that all the stream
elements are of the [String] type.

Other classes and interfaces {#other-classes-and-interfaces .header-title}
----------------------------

In Java 8, two default methods were added to the
[java.util.Collection] interface, as follows:

-   [Stream\<E\> stream()]: Returns a stream of the elements of
    this collection
-   [Stream\<E\> parallelStream()]: Returns (possibly) a parallel
    stream of the elements of this collection; *possibly,* because the
    JVM attempts to split the stream into several chunks and process
    them in parallel (if there are several CPUs) or virtually parallel
    (using CPU time-sharing); but it is not always possible and depends
    in part on the nature of the requested processing

It means that all the collection interfaces that extend this interface,
including [Set] and [List], have these methods. For
example: 


```
List.of("1", "2", "3").stream().forEach(builder);
List.of("1", "2", "3").parallelStream().forEach(builder);
```

We will talk about parallel streams in the *Parallel processing*
section.

We have described eight static overloaded [stream()] methods of
the [java.util.Arrays] class at the beginning of the *Streams as a
source of data and operations* section. Here is an example of another
way of creating a stream, using the subset of an array:


```
int[] arr = {1, 2, 3, 4, 5}; 
Arrays.stream(arr, 2, 4).forEach(System.out::print); //prints: 34 
```

The [java.util.Random] class allows creating numeric streams of
pseudo-random values, as follows:

-   [DoubleStream doubles()]: Creates an unlimited stream of
    [double] values between [0] (inclusive) and [1]
    (exclusive)
-   [IntStream ints()] and [LongStream longs()]: Create an
    unlimited stream of corresponding type values
-   [DoubleStream doubles(long streamSize)]: Creates a stream (of
    the specified size) of [double] values between [0]
    (inclusive) and [1] (exclusive)
-   [IntStream ints(long streamSize)] and [LongStream longs(long
    streamSize)]: Creates a stream of the specified size of the
    corresponding type values
-   [IntStream ints(int randomNumberOrigin, int
    randomNumberBound)]: Creates an unlimited stream
    of [int] values between [randomNumberOrigin] (inclusive)
    and [randomNumberBound] (exclusive)
-   [LongStream longs(long randomNumberOrigin, long
    randomNumberBound)]: Creates an unlimited stream
    of [long] values
    between [randomNumberOrigin] (inclusive)
    and [randomNumberBound] (exclusive)
-   [DoubleStream doubles(long streamSize, double randomNumberOrigin,
    double randomNumberBound)]: Creates a stream of the specified
    size of [double] values
    between [randomNumberOrigin] (inclusive)
    and [randomNumberBound] (exclusive)

Here is an example of one of the preceding methods:


```
new Random().ints(5, 8).limit(5) 
            .forEach(System.out::print);    //prints: 56757 
```

The [java.nio.file.Files] class has six static methods creating
streams of lines and paths, as follows:

-   [Stream\<String\> lines(Path path)]: Creates a stream of lines
    from the file specified by the provided path
-   [Stream\<String\> lines(Path path, Charset cs)]: Creates a
    stream of lines from the file specified by the provided path; bytes
    from the file are decoded into characters using the provided charset
-   [Stream\<Path\> list(Path dir)]: Creates a stream of files and
    directories in the specified directory
-   [Stream\<Path\> walk(Path start, FileVisitOption\...
    options)]: Creates a stream of files and directories of the
    file tree that starts with [Path start]
-   [Stream\<Path\> walk(Path start, int maxDepth, FileVisitOption\...
    options)]: Creates a stream of files and directories of the
    file tree that starts with [Path start] down to the specified
    depth [maxDepth]
-   [Stream\<Path\> find(Path start, int maxDepth, BiPredicate\<Path,
    BasicFileAttributes\> matcher, FileVisitOption\...
    options)]: Creates a stream of files and directories (that
    match the provided predicate) of the file tree that starts
    with [Path start] down to the specified depth, specified
    by [maxDepth] value

Other classes and methods that create streams include the following:

-   The [java.util.BitSet] class has the [IntStream
    stream()] method, which creates a stream of indices for which
    this [BitSet] contains a bit in the set state.
-   The [java.io.BufferedReader] class has the [Stream\<String\>
    lines()] method, which creates a stream of lines from this
    [BufferedReader] object, typically from a file.
-   The [java.util.jar.JarFile] class has the [Stream\<JarEntry\>
    stream()] method that creates a stream of the ZIP-file
    entries.
-   The [java.util.regex.Pattern] class has the [Stream\<String\>
    splitAsStream(CharSequence input)] method, which creates a
    stream from the provided sequence around matches of this pattern.
-   A [java.lang.CharSequence] interface has two methods, as
    follows:
    -    [default IntStream chars()]: creates a stream of
        [int] zero-extending the char values
    -   [default IntStream codePoints()]: creates a stream of code
        point values from this sequence

There is also a [java.util.stream.StreamSupport] class that
contains static low-level utility methods for library developers. But we
won\'t be reviewing it, as this is outside the scope of this book.


Operations (methods)
=====================

Many methods of the [Stream] interface, those that have a
functional interface type as a parameter, are called **operations**
because they are not implemented as traditional methods. Their
functionality is passed into the method as a function. The operations
are just shells that call a method of the functional interface assigned
as the type of the parameter method.

For example, let\'s look at the [Stream\<T\> filter (Predicate\<T\>
predicate)] method. Its implementation is based on the call to the
method boolean [test(T t)] of the [Predicate\<T\>] function.
So, instead of saying, *We use the [filter()] method of
the [Stream] object to select some of the stream elements and skip
others,* programmers prefer to say, *We apply an operation filter that
allows some of the stream elements to get through and skip others*. It
describes the nature of the action (operation), not the particular
algorithm, which is unknown until the method receives a particular
function. There are two groups of operations in
the [Stream] interface, as follows:

-   **Intermediate operations**: Instance methods that return
    a [Stream] object
-   **Terminal operations:** Instance methods that return some type
    other than [Stream]

Stream processing is organized typically as a pipeline using a fluent
(dot-connected) style. A [Stream] creating method or another
stream source starts such a pipeline. A terminal operation produces the
final result or a side-effect and eponymously ends the pipeline. An
intermediate operation can be placed between the originating
[Stream] object and the terminal operation.

An intermediate operation processes stream elements (or not, in some
cases) and returns the modified (or not) [Stream] object, so the
next intermediate or terminal operation can be applied. Examples of
intermediate operations are the following:

-   [Stream\<T\> filter(Predicate\<T\> predicate)]: Selects only
    elements matching a criterion
-   [Stream\<R\> map(Function\<T,R\> mapper)]: Transforms elements
    according to the passed-in function; please notice that the type of
    the returned [Stream] object may be quite different from the
    input type
-   [Stream\<T\> distinct()]: Removes duplicates
-   [Stream\<T\> limit(long maxSize)]: Limits a stream to the
    specified number of elements
-   [Stream\<T\> sorted()]: Arranges the stream elements in a
    certain order

We will discuss some other intermediate operations in the *Intermediate
operations* section.

The processing of the stream elements actually begins only when a
terminal operation starts executing. Then all the intermediate
operations (if present) start processing in sequence. As soon as the
terminal operation has finished execution, the stream closes and cannot
be reopened.

Examples of terminal operations are [forEach()],
[findFirst()], [reduce()], [collect()], [sum()],
[max()], and other methods of the [Stream] interface that do
not return the [Stream] object. We will discuss them in
the *Terminal operations* section.

All the [Stream] operations support parallel processing, which is
especially helpful in the case of a large amount of data processed on a
multi-core computer. We will discuss it in the *Parallel streams*
section.

Intermediate operations {#intermediate-operations .header-title}
-----------------------

As we have mentioned already, an intermediate operation returns a
[Stream] object that emits the same or modified values and may
even be of a different type than the stream source.

The intermediate operations can be grouped by their functionality in
four categories of operations that perform **filtering**, **mapping**,
**sorting**, or **peeking**.

Filtering {#filtering .header-title}
---------

This group includes operations that remove duplicates, skip some of the
elements, limit the number of processed elements, and select for the
further processing only those that pass certain criteria, as follows:

-   [Stream\<T\> distinct()]: Compares stream elements using
    [method] [Object.equals(Object)] and skips duplicates
-   [Stream\<T\> skip(long n)]: Ignores the provided number of
    stream elements that are emitted first
-   [Stream\<T\> limit(long maxSize)]: Allows only the provided
    number of stream elements to be processed
-   [Stream\<T\> filter(Predicate\<T\> predicate)]: Allows only
    those elements to be processed that result in [true] when
    processed by the provided [Predicate] function
-   [default Stream\<T\> dropWhile(Predicate\<T\> predicate)]:
    Skips those first elements of the stream that result in [true]
    when processed by the provided [Predicate] function
-   [default Stream\<T\> takeWhile(Predicate\<T\> predicate)]:
    Allows only those first elements of the stream to be processed that
    result in [true] when processed by the provided
    [Predicate] function

The following is the code that demonstrates how the operations just
described work:


```
Stream.of("3", "2", "3", "4", "2").distinct()
                         .forEach(System.out::print);     //prints: 324

List<String> list = List.of("1", "2", "3", "4", "5");
list.stream().skip(3).forEach(System.out::print);         //prints: 45

list.stream().limit(3).forEach(System.out::print);        //prints: 123

list.stream().filter(s -> Objects.equals(s, "2"))
             .forEach(System.out::print);                 //prints: 2

list.stream().dropWhile(s -> Integer.valueOf(s) < 3)
             .forEach(System.out::print);                 //prints: 345

list.stream().takeWhile(s -> Integer.valueOf(s) < 3)
             .forEach(System.out::print);                 //prints: 12
```

Notice that we were able to reuse the source [List\<String\>]
object, but could not reuse the [Stream] object. Once a
[Stream] object is closed, it cannot be reopened.

Mapping {#mapping .header-title}
-------

This group includes arguably the most important intermediate operations.
They are the only intermediate operations that modify the elements of
the stream. They **map** (transform) the original stream element value
to a new one, as follows:

-   [Stream\<R\> map(Function\<T, R\> mapper)]: Applies the
    provided function to each element of type [T] of the stream
    and produces a new element value of type [R]
-   [IntStream mapToInt(ToIntFunction\<T\> mapper)]: Applies the
    provided function to each element of type [T] of the stream
    and produces a new element value of type [int]
-   [LongStream mapToLong(ToLongFunction\<T\> mapper)]: Applies
    the provided function to each element of type [T] of the
    stream and produces a new element value of type [long]
-   [DoubleStream mapToDouble(ToDoubleFunction\<T\>
    mapper)]: Applies the provided function to each element of
    type [T] of the stream and produces a new element value of
    type [double]
-   [Stream\<R\> flatMap(Function\<T, Stream\<R\>\> mapper)]:
    Applies the provided function to each element of type [T] of
    the stream and produces a [Stream\<R\>] object that emits
    elements of type [R]
-   [IntStream flatMapToInt(Function\<T, IntStream\>
    mapper)]: Applies the provided function to each element of
    type [T] of the stream and produces a [IntStream] object
    that emits elements of type [int]
-   [LongStream flatMapToLong(Function\<T, LongStream\>
    mapper)]: Applies the provided function to each element of
    type [T] of the stream and produces
    a [LongStream] object that emits elements of type [long]
-   [DoubleStream flatMapToDouble(Function\<T, DoubleStream\>
    mapper)]: Applies the provided function to each element of
    type [T] of the stream and produces
    a [DoubleStream] object that emits elements of
    type [double]

The following are examples of the usage of these operations, as follows:


```
List<String> list = List.of("1", "2", "3", "4", "5");
list.stream().map(s -> s + s)
             .forEach(System.out::print);    //prints: 1122334455

list.stream().mapToInt(Integer::valueOf)
             .forEach(System.out::print);    //prints: 12345

list.stream().mapToLong(Long::valueOf)
             .forEach(System.out::print);    //prints: 12345

list.stream().mapToDouble(Double::valueOf)
             .mapToObj(Double::toString)
             .map(s -> s + " ")
             .forEach(System.out::print);  //prints: 1.0 2.0 3.0 4.0 5.0

list.stream().mapToInt(Integer::valueOf)
             .flatMap(n -> IntStream.iterate(1, i -> i < n, i -> ++i))
             .forEach(System.out::print);        //prints: 1121231234

list.stream().map(Integer::valueOf)
             .flatMapToInt(n ->
                  IntStream.iterate(1, i -> i < n, i -> ++i))
             .forEach(System.out::print);        //prints: 1121231234

list.stream().map(Integer::valueOf)
             .flatMapToLong(n ->
                  LongStream.iterate(1, i -> i < n, i -> ++i))
             .forEach(System.out::print);        //prints: 1121231234

list.stream().map(Integer::valueOf)
             .flatMapToDouble(n ->
                  DoubleStream.iterate(1, i -> i < n, i -> ++i))
             .mapToObj(Double::toString)
             .map(s -> s + " ")
             .forEach(System.out::print);
                       //prints: 1.0 1.0 2.0 1.0 2.0 3.0 1.0 2.0 3.0 4.0
```

In the last example, converting the stream to [DoubleStream], we
transformed each numeric value to a [String] object and added
whitespace, so the result can be printed with whitespace between the
numbers. These examples are very simple: just conversion with minimal
processing. But in real life, each [map()] or [flatMap()]
operation typically accepts a more complex function that does something
more useful.

Sorting {#sorting .header-title}
-------

The following two intermediate operations sort the stream elements, as
follows: 

-   [Stream\<T\> sorted()]: Sorts stream elements in natural order
    (according to their [Comparable] interface implementation)
-   [Stream\<T\> sorted(Comparator\<T\> comparator)]: Sorts stream
    elements in order according to the provided [Comparator\<T\>]
    object

Naturally, these operations cannot be finished until all the elements
are emitted, so such processing creates a lot of overhead, slows down
performance, and has to be used for small streams. 

Here is demo code:


```
List<String> list = List.of("2", "1", "5", "4", "3");
list.stream().sorted().forEach(System.out::print);  //prints: 12345
list.stream().sorted(Comparator.reverseOrder())
             .forEach(System.out::print);           //prints: 54321
```

Peeking {#peeking .header-title}
-------

An intermediate [Stream\<T\> peek(Consumer\<T\> action)]
operation applies the provided [Consumer\<T\>] function to each
stream element but does not change the stream values
([Consumer\<T\>] returns [void]). This operation is used for
debugging. The following code shows how it works:


```
List<String> list = List.of("1", "2", "3", "4", "5");
list.stream()
 .peek(s -> System.out.print("3".equals(s) ? 3 : 0))
 .forEach(System.out::print); //prints: 0102330405
```

Terminal operations {#terminal-operations .header-title}
-------------------

**Terminal operations** are the most important operations in a stream
pipeline. It is possible to accomplish everything in them without using
any other operations.

We have used already the [forEach(Consumer\<T\>)] terminal
operation to print each element. It does not return a value, thus it is
used for its side-effects. But the [Stream] interface has many
more powerful terminal operations that do return values.

Chief among them is the [collect()] operation, which has two forms
as follows:

-   [R collect(Collector\<T, A, R\> collector)]
-   [R collect(Supplier\<R\> supplier, BiConsumer\<R, T\>
    accumulator, BiConsumer\<R, R\> combiner)]

It allows composing practically any process that can be applied to a
stream. The classic example is as follows:


```
List<String> list = Stream.of("1", "2", "3", "4", "5")
                          .collect(ArrayList::new,
                                   ArrayList::add,
                                   ArrayList::addAll);
System.out.println(list);  //prints: [1, 2, 3, 4, 5]
```

This example is used in such a way as to be suitable for parallel
processing. The first parameter of the [collect()] operation is a
function that produces a value based on the stream element. The second
parameter is the function that accumulates the result. And the third
parameter is the function that combines the accumulated results from all
the threads that processed the stream.

But having only one such generic terminal operation would force
programmers to write the same functions repeatedly. That is why the API
authors added the [Collectors] class, which generates many
specialized [Collector] objects without the need to create three
functions for every [collect()] operation.

In addition to that, the API authors added to the [Stream]
interface various even more specialized terminal operations that are
much simpler and easier to use. In this section, we will review all the
terminal operations of the [Stream] interface and, in the
[Collect] subsection, look at the plethora of [Collector]
objects produced by the [Collectors] class. We start with the most
simple terminal operation that allows processing each element of this
stream one at a time.

In our examples, we are going to use the following
class: [Person]:


```
public class Person {
    private int age;
    private String name;
    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }
    public int getAge() {return this.age; }
    public String getName() { return this.name; }
    @Override
    public String toString() {
        return "Person{" + "name='" + this.name + "'" +
                                       ", age=" + age + "}";
    }
}
```

Processing each element {#processing-each-element .header-title}
-----------------------

There are two terminal operations in this group, as follows:

-   [void forEach(Consumer\<T\> action)]: Applies the provided
    action for each element of this stream
-   [void forEachOrdered(Consumer\<T\> action)]: Applies the
    provided action for each element of this stream in the order defined
    by the source, regardless of whether the stream is sequential or
    parallel

If the order in which you need the elements to be processed is important
and it has to be the order in which values are arranged at the source,
use the second method, especially if you can foresee that it is possible
your code is going to be executed on a computer with several CPUs.
Otherwise, use the first one, as we did in all our examples.

Let\'s see an example of using the [forEach()] operation for
reading comma-separated values (age and name) from a file and creating
[Person] objects. We have placed the following
[persons.csv] file (*csv* stands for *comma-separated values*)
file in the [resources] folder:


```
23 , Ji m
    2 5 , Bob
  15 , Jill
17 , Bi ll
```

We have added spaces inside and outside the values in order to take this
opportunity to show you some simple but very useful tips for working
with real-life data.

First, we will just read the file and display its content line by line,
but only those lines that contain the letter [J]:


```
Path path = Paths.get("src/main/resources/persons.csv");
try (Stream<String> lines = Files.newBufferedReader(path).lines()) {
    lines.filter(s -> s.contains("J"))
         .forEach(System.out::println);  //prints: 23 , Ji m
                                         //          15 , Jill
} catch (IOException ex) {
    ex.printStackTrace();
}
```

That is a typical way of using the [forEach()] operation: to
process each element independently. This code also provides an example
of a try-with-resources construct that closes the [BufferedReader]
object automatically. 

And the following is how an inexperienced programmer might write the
code that reads the stream elements from the [Stream\<String\>
lines] object and creates a list of [Person] objects:


```
List<Person> persons = new ArrayList<>();
lines.filter(s -> s.contains("J")).forEach(s -> {
    String[] arr = s.split(",");
    int age = Integer.valueOf(StringUtils.remove(arr[0], ' '));
    persons.add(new Person(age, StringUtils.remove(arr[1], ' ')));
});
```

 You can see how the [split()] method is used to break each line
by a comma that separates the values and how
the [org.apache.commons.lang3.StringUtils.remove()] method removes
spaces from each value. Although this code works well in small examples
on a single-core computer, it might create unexpected results with a
long stream and parallel processing.

That is the reason that lambda expressions require all the variables to
be final or effectively final because the same function can be executed
in a different context.

The following is a correct implementation of the preceding code:


```
List<Person> persons = lines.filter(s -> s.contains("J"))
        .map(s -> s.split(","))
        .map(arr -> {
            int age = Integer.valueOf(StringUtils.remove(arr[0], ' '));
            return new Person(age, StringUtils.remove(arr[1], ' '));
        }).collect(Collectors.toList());
```

To improve readability, we can create a method that does the job of
mapping:


```
private Person createPerson(String[] arr){
    int age = Integer.valueOf(StringUtils.remove(arr[0], ' '));
    return new Person(age, StringUtils.remove(arr[1], ' '));
}
```

Now we can use it as follows:


```
List<Person> persons = lines.filter(s -> s.contains("J"))
                            .map(s -> s.split(","))
                            .map(this::createPerson)
                            .collect(Collectors.toList());
```

As you can see, we have used the [collect()] operator and
the [Collector] function created by
the [Collectors.toList()] method. We will see more functions
created by the [Collectors] class in the *Collect* subsection.

Counting all elements {#counting-all-elements .header-title}
---------------------

The [long count()] terminal operation of
the [Stream] interface looks straightforward and benign. It
returns the number of elements in this stream. Those who are used to
working with collections and arrays may use
the [count()] operation without thinking twice. The following code
snippet demonstrates a caveat:


```
long count = Stream.of("1", "2", "3", "4", "5")
                   .peek(System.out::print)
                   .count();
System.out.print(count);          //prints: 5               
```

If we run the preceding code, the result will look as follows:

![]./images_12/e04347d3-776e-41c2-86c1-c0618d4dfd3a.png)

As you see, the code that implements the [count()] method was able
to determine the stream size without executing all the pipeline.
The [peek()] operation did not print anything, which proves that
elements were not emitted. So, if you expected to see the values of the
stream printed, you might be puzzled and expect that the code has some
kind of defect. 

Another caveat is that it is not always possible to determine the stream
size at the source. Besides, the stream may be infinite. So, you have to
use [count()] with care.

Another possible way to determine the stream size is by using
the [collect()] operation:


```
long count = Stream.of("1", "2", "3", "4", "5")
                   .peek(System.out::print)         //prints: 12345
                   .collect(Collectors.counting());
System.out.println(count);                          //prints: 5 
```

The following screenshot that shows what happens after the preceding
code example has been run:

![]./images_12/7197acc9-da9c-4d3c-a9f2-afd8c19c919b.png)

As you can see, the [collect()] operation does not calculate the
stream size at the source. That is because
the [collect()] operation is not as specialized as
the [count()] operation. It just applies the passed-in collector
to the stream. And the collector just counts the elements provided to it
by the [collect()] operation.

Match all, any, none {#match-all-any-none .header-title}
--------------------

There are three seemingly very similar terminal operations that allow us
to assess whether all, any, or none of the stream elements have a
certain value, as follows:

-   [boolean allMatch(Predicate\<T\> predicate)]: Returns
    [true] when each of the stream elements returns [true]
    when used as a parameter of the
    provided [Predicate\<T\>] function
-   [boolean anyMatch(Predicate\<T\> predicate)]: Returns
    [true] when one of the stream elements returns [true]
    when used as a parameter of the
    provided [Predicate\<T\>] function
-   [boolean noneMatch(Predicate\<T\> predicate)]: Returns
    [true] when none of this stream elements returns [true]
    when used as a parameter of the provided [Predicate\<T\>]
    function

The following are examples of their usage:


```
List<String> list = List.of("1", "2", "3", "4", "5");
boolean found = list.stream()
                    .peek(System.out::print)             //prints: 123
                    .anyMatch(e -> "3".equals(e));
System.out.println(found);                               //prints: true

boolean noneMatches = list.stream()
                          .peek(System.out::print)       //prints: 123
                          .noneMatch(e -> "3".equals(e));
System.out.println(noneMatches);                         //prints: false

boolean allMatch = list.stream()
                       .peek(System.out::print)          //prints: 1
                       .allMatch(e -> "3".equals(e));
System.out.println(allMatch);                            //prints: false
```

Please notice that all these operations are optimized so as not to
process all the stream elements if the result can be determined early.

Find any or first {#find-any-or-first .header-title}
-----------------

The following terminal operations allow finding any element or the first
element of the stream correspondingly, as follows:

-   [Optional\<T\> findAny()]: Returns an [Optional] with
    the value of any element of the stream, or an empty [Optional]
    if the stream is empty
-   [Optional\<T\> findFirst()]: Returns an [Optional] with
    the value of the first element of the stream, or an empty
    [Optional] if the stream is empty

The following examples illustrate these operations:


```
List<String> list = List.of("1", "2", "3", "4", "5");
Optional<String> result = list.stream().findAny();
System.out.println(result.isPresent());    //prints: true
System.out.println(result.get());          //prints: 1

result = list.stream()
             .filter(e -> "42".equals(e))
             .findAny();
System.out.println(result.isPresent());    //prints: false
//System.out.println(result.get());        //NoSuchElementException

result = list.stream().findFirst();
System.out.println(result.isPresent());    //prints: true
System.out.println(result.get());          //prints: 1
```

In the first and third of the preceding examples, the [findAny()]
and [findFirst()] operations produce the same result: they both
find the first element of the stream. But in parallel processing, the
result may be different.

When the stream is broken into several parts for parallel processing,
the [findFirst()] operation always returns the first element of
the stream, while the [findAny()] operation returns the first
element only in one of the processing threads.

Now let\'s talk about [class java.util.Optional] in more detail.

Optional class {#optional-class .header-title}
--------------

The object of [java.util.Optional] is used to avoid returning
[null] ( as it may cause [NullPointerException]). Instead,
an [Optional] object provides methods that allow checking the
presence of a value and substituting it with a predefined value if the
return value is [null]. For example:


```
List<String> list = List.of("1", "2", "3", "4", "5");
String result = list.stream()
                    .filter(e -> "42".equals(e))
                    .findAny()
                    .or(() -> Optional.of("Not found"))
                    .get();
System.out.println(result);                       //prints: Not found

result = list.stream()
             .filter(e -> "42".equals(e))
             .findAny()
             .orElse("Not found");
System.out.println(result);                      //prints: Not found

Supplier<String> trySomethingElse = () -> {
    //Code that tries something else
    return "43";
};
result = list.stream()
             .filter(e -> "42".equals(e))
             .findAny()
             .orElseGet(trySomethingElse);
System.out.println(result);                      //prints: 43

list.stream()
    .filter(e -> "42".equals(e))
    .findAny()
    .ifPresentOrElse(System.out::println,
        () -> System.out.println("Not found")); //prints: Not found
```

As you can see, if the [Optional] object is empty, then the
following applies, as follows:

-   The [or()] method of the [Optional] class allows
    returning an alternative [Optional] object.
-   The [orElse()] method allows returning an alternative value.
-   The [orElseGet()] method allows providing the [Supplier]
    function, which returns an alternative value.
-   The [ifPresentOrElse()] method allows providing two functions:
    one that consumes the value from the [Optional] object, and
    another one that does something else in the case the
    [Optional] object is empty.

Min and max {#min-and-max .header-title}
-----------

The following terminal operations return the minimum or maximum value of
stream elements, if present, as follows:

-   [Optional\<T\> min(Comparator\<T\> comparator)]: Returns the
    minimum element of this stream using the provided [Comparator]
    object
-   [Optional\<T\> max(Comparator\<T\> comparator)]: Returns the
    maximum element of this stream using the provided [Comparator]
    object

The following code demonstrates this:


```
List<String> list = List.of("a", "b", "c", "c", "a");
String min = list.stream()
                 .min(Comparator.naturalOrder())
                 .orElse("0");
System.out.println(min);     //prints: a

String max = list.stream()
 .max(Comparator.naturalOrder())
                 .orElse("0");
System.out.println(max);     //prints: c
```

As you can see, in the case of non-numerical values, the minimum element
is the one that is the first when ordered from the left to the right
according to the provided comparator. And the maximum, accordingly, is
the last element. In the case of numeric values, the minimum and maximum
are just that: the smallest and the biggest numbers among the stream
elements:


```
int mn = Stream.of(42, 77, 33)
               .min(Comparator.naturalOrder())
               .orElse(0);
System.out.println(mn);    //prints: 33

int mx = Stream.of(42, 77, 33)
               .max(Comparator.naturalOrder())
               .orElse(0);
System.out.println(mx);    //prints: 77
```

Let\'s look at another example, using the [Person] class. The task
is to find the oldest person in the following list:


```
List<Person> persons = List.of(new Person(23, "Bob"),
 new Person(33, "Jim"),
 new Person(28, "Jill"),
 new Person(27, "Bill"));
```

In order to do that, we can create the following
[Compartor\<Person\>] that compares [Person] objects only by
age:


```
Comparator<Person> perComp = (p1, p2) -> p1.getAge() - p2.getAge();
```

Then, using this comparator, we can find the oldest person:


```
Person theOldest = persons.stream()
                          .max(perComp)
                          .orElse(null);
System.out.println(theOldest);    //prints: Person{name='Jim', age=33}
```

To array {#to-array .header-title}
--------

The following two terminal operations generate an array that contains
stream elements, as follows:

-   [Object\[\] toArray()]: Creates an array of objects; each
    object is an element of the stream
-   [A\[\] toArray(IntFunction\<A\[\]\> generator)]: Creates an
    array of stream elements using the provided function

Let\'s look at some examples:


```
List<String> list = List.of("a", "b", "c");
Object[] obj = list.stream().toArray();
Arrays.stream(obj).forEach(System.out::print);    //prints: abc

String[] str = list.stream().toArray(String[]::new);
Arrays.stream(str).forEach(System.out::print);    //prints: abc
```

 The first example is straightforward. It converts elements to an array
of the same type. As for the second example, the representation of
[IntFunction] as [String\[\]::new] is probably not obvious,
so let\'s walk through it. The [String\[\]::new] is a method
reference that represents the lambda expression [i -\> new
String\[i\]] because the [toArray()] operation receives from
the stream not the elements, but their count:


```
String[] str = list.stream().toArray(i -> new String[i]);
```

We can prove it by printing an [i] value:


```
String[] str = list.stream()
                   .toArray(i -> {
                          System.out.println(i);    //prints: 3
                          return  new String[i];
                   });
```

The [i -\> new String\[i\]] expression is an
[IntFunction\<String\[\]\>] that, according to its documentation,
accepts an [int] parameter and returns the result of the specified
type. It can be defined using an anonymous class as follows:


```
IntFunction<String[]> intFunction = new IntFunction<String[]>() { 
         @Override 
         public String[] apply(int i) { 
              return new String[i]; 
         } 
}; 
```

The [java.util.Collection] interface has a very similar method
that converts the collection to an array:


```
List<String> list = List.of("a", "b", "c");
String[] str = list.toArray(new String[lits.size()]);
Arrays.stream(str).forEach(System.out::print);    //prints: abc
```

The only difference is that the [toArray()] of the [Stream]
interface accepts a function, while the [toArray()] of the
[Collection] interface takes an array.

Reduce {#reduce .header-title}
------

This terminal operation is called [reduce] because it processes
all the stream elements and produces one value, thus reducing all the
stream elements to one value. But this is not the only operation that
does it. The [collect] operation reduces all the values of the
stream element to one result as well. And, in a way, all terminal
operations are reductive. They produce one value after processing many
elements.

So, you may look at [reduce] and [collect] as synonyms that
help to add structure and classification to many operations available in
the [Stream] interface. Also, operations in the
[reduce] group can be viewed as specialized versions of the
[collect] operation because [collect()] can be tailored to
provide the same functionality as the [reduce()] operation.

That said, let\'s look at a group of [reduce] operations, as
follows:

-   [Optional\<T\> reduce(BinaryOperator\<T\> accumulator)]:
    Reduces the elements of the stream using the provided associative
    function that aggregates the elements; returns an [Optional]
    with the reduced value if available
-   [T reduce(T identity, BinaryOperator\<T\> accumulator)]:
    Provides the same functionality as the previous [reduce()]
    version but with the identity parameter used as the initial value
    for an accumulator or a default value if a stream is empty
-   [U reduce(U identity, BiFunction\<U,T,U\> accumulator,
    BinaryOperator\<U\> combiner)]: Provides the same
    functionality as the previous [reduce()] versions but, in
    addition, uses the [combiner] function to aggregate the
    results when this operation is applied to a parallel stream; if the
    stream is not parallel, the [combiner] function is not used

To demonstrate the [reduce()] operation, we are going to use the
same [Person] class we have used before and the same list of
[Person] objects as the source for our stream examples:


```
List<Person> persons = List.of(new Person(23, "Bob"),
                               new Person(33, "Jim"),
                               new Person(28, "Jill"),
                               new Person(27, "Bill"));
```

Let\'s find the oldest person in this list using
the [reduce()] operation:


```
Person theOldest = list.stream()
              .reduce((p1, p2) -> p1.getAge() > p2.getAge() ? p1 : p2)
              .orElse(null);
System.out.println(theOldest);    //prints: Person{name='Jim', age=33}
```

The implementation is somewhat surprising, isn\'t it?
The [reduce()] operation takes an accumulator, but it seems it did
not accumulate anything. Instead, it compares all stream elements. Well,
the accumulator saves the result of the comparison and provides it as
the first parameter for the next comparison (with the next element). You
can say that the accumulator, in this case, accumulates the results of
all previous comparisons. 

Let\'s now accumulate something explicitly. Let\'s assemble all the
names from a list of persons in one comma-separated list:


```
String allNames = list.stream()
                      .map(p -> p.getName())
                      .reduce((n1, n2) -> n1 + ", " + n2)
                      .orElse(null);
System.out.println(allNames);            //prints: Bob, Jim, Jill, Bill
```

The notion of accumulation, in this case, makes a bit more sense,
doesn\'t it?

Now let\'s use the [identity] value to provide some initial value:


```
String all = list.stream()
                 .map(p -> p.getName())
                 .reduce("All names: ", (n1, n2) -> n1 + ", " + n2);
System.out.println(all);   //prints: All names: , Bob, Jim, Jill, Bill
```

Notice that this version of the [reduce()] operation returns
[value], not the [Optional] object. That is because, by
providing the initial value, we guarantee that at least this value will
be present in the result if the stream turns out to be empty. But the
resulting string does not look as pretty as we hoped. Apparently, the
provided initial value is treated as any other stream element, and a
comma is added after it by the accumulator we have created. To make the
result look pretty again, we could use the first version of the
[reduce()] operation again and add the initial value this way:


```
String all = "All names: " + list.stream()
                                 .map(p -> p.getName())
                                 .reduce((n1, n2) -> n1 + ", " + n2)
                                 .orElse(null);
System.out.println(all);     //prints: All names: Bob, Jim, Jill, Bill
```

Or we can use a space as a separator instead of a comma:


```
String all = list.stream()
                 .map(p -> p.getName())
                 .reduce("All names:", (n1, n2) -> n1 + " " + n2);
System.out.println(all);     //prints: All names: Bob Jim Jill Bill
```

Now the result looks better. While demonstrating the [collect()]
operation in the next subsection, we will show a better way to create a
comma-separated list of values with a prefix.

Meanwhile, let\'s continue to review the [reduce()] operation and
look at its third form: the one with three parameters: [identity],
[accumulator], and [combiner]. Adding the combiner to the
[reduce()] operation does not change the result:


```
String all = list.stream()
                 .map(p -> p.getName())
                 .reduce("All names:", (n1, n2) -> n1 + " " + n2,
                                       (n1, n2) -> n1 + " " + n2 );
System.out.println(all);      //prints: All names: Bob Jim Jill Bill
 
```

That is because the stream is not parallel and the combiner is used only
with a parallel stream. If we make the stream parallel, the result
changes:


```
String all = list.parallelStream()
                 .map(p -> p.getName())
                 .reduce("All names:", (n1, n2) -> n1 + " " + n2,
                                       (n1, n2) -> n1 + " " + n2 );
System.out.println(all); 
  //prints: All names: Bob All names: Jim All names: Jill All names: Bill
```

Apparently, for a parallel stream, the sequence of elements is broken
into subsequences, each processed independently, and their results
aggregated by the combiner. While doing that, the combiner adds the
initial value (identity) to each of the results. Even if we remove the
combiner, the result of the parallel stream processing remains the same,
because a default combiner behavior is provided:


```
String all = list.parallelStream()
                 .map(p -> p.getName())
                 .reduce("All names:", (n1, n2) -> n1 + " " + n2);
System.out.println(all); 
  //prints: All names: Bob All names: Jim All names: Jill All names: Bill
```

In the previous two forms of the [reduce()] operations, the
identity value was used by the accumulator. In the third form, the
identity value is used by the combiner (notice, the [U] type is
the combiner type). To get rid of the repetitive identity value in the
result, we have decided to remove it (and the trailing space) from the
second parameter in the combiner:


```
String all = list.parallelStream().map(p->p.getName())
                 .reduce("All names:", (n1, n2) -> n1 + " " + n2,
       (n1, n2) -> n1 + " " + StringUtils.remove(n2, "All names: "));
System.out.println(all);      //prints: All names: Bob Jim Jill Bill 
```

The result is as expected.

In our string-based examples so far, the identity has not just been an
initial value. It also served as an identifier (a label) in the
resulting string. But when the elements of the stream are numeric, the
identity looks more like just an initial value. Let\'s look at the
following example:


```
List<Integer> ints = List.of(1, 2, 3);
int sum = ints.stream()
              .reduce((i1, i2) -> i1 + i2)
              .orElse(0);
System.out.println(sum);                          //prints: 6
sum = ints.stream()
          .reduce(Integer::sum)
          .orElse(0);
System.out.println(sum);                          //prints: 6
sum = ints.stream()
          .reduce(10, Integer::sum);
System.out.println(sum);                         //prints: 16
sum = ints.stream()
          .reduce(10, Integer::sum, Integer::sum);
System.out.println(sum);                         //prints: 16
```

The first two of the pipelines are exactly the same, except that the
second pipeline uses a method reference. The third and the fourth
pipelines have the same functionality too. They both use an initial
value of [10]. Now the first parameter makes more sense as the
initial value than the identity, doesn\'t it? In the fourth pipeline, we
added a combiner, but it is not used because the stream is not parallel.
Let\'s make it parallel and see what happens:


```
List<Integer> ints = List.of(1, 2, 3);
int sum = ints.parallelStream()
              .reduce(10, Integer::sum, Integer::sum);
System.out.println(sum);                        //prints: 36
```

The result is [36] because the initial value of [10] was
added three times, with each partial result. Apparently, the stream was
broken into three subsequences. But it is not always the case, as the
number of the subsequences changes as the stream grows and the number of
CPUs on the computer increases. That is why you cannot rely on a certain
fixed number of subsequences and it is better not to use a non-zero
initial value with parallel streams:


```
List<Integer> ints = List.of(1, 2, 3);
int sum = ints.parallelStream()
              .reduce(0, Integer::sum, Integer::sum);
System.out.println(sum);                             //prints: 6
sum = 10 + ints.parallelStream()
               .reduce(0, Integer::sum, Integer::sum);
System.out.println(sum);                             //prints: 16
 
```

As you can see, we have set the identity to [0], so every
subsequence will get it, but the total is not affected when the result
from all the processing threads is assembled by the combinator.

Collect {#collect .header-title}
-------

Some of the usages of the [collect()] operation are very simple
and can be easily mastered by any beginner, while other cases can be
complex and not easy to understand even for a seasoned programmer.
Together with the operations discussed already, the most popular cases
of [collect()] usage we present in this section are more than
enough for all the needs a beginner may have and will cover most needs
of a more experienced professional. Together with the operations of
numeric streams (see the *Numeric stream interfaces *section), they
cover all the needs a mainstream programmer will ever have.

As we have mentioned already, the [collect()] operation is very
flexible and allows us to customize stream processing. It has two forms,
as follows:

-   [R collect(Collector\<T, A, R\> collector)]: Processes the
    stream elements of type [T] using the provided
    [Collector] and producing the result of type [R] via an
    intermediate accumulation of type [A]
-   [R collect(Supplier\<R\> supplier, BiConsumer\<R, T\> accumulator,
    BiConsumer\<R, R\> combiner)]: Processes the stream elements
    of type [T] using the provided functions:
    -   [Supplier\<R\> supplier]: Creates a new result container
    -   [BiConsumer\<R, T\> accumulator]: A stateless function
        that adds an element to the result container
    -   [BiConsumer\<R, R\> combiner]: A stateless function that
        merges two partial result containers: adds the elements from the
        second result container into the first result container

Let\'s look at the second form of the [collect()] operation first.
It is very similar to the [reduce()] operation with three
parameters we have just demonstrated: [supplier],
[accumulator], and [combiner]. The biggest difference is
that the first parameter in the [collect()] operation is not an
identity or the initial value, but the container, an object, that is
going to be passed between functions and which maintains the state of
the processing.

Let\'s demonstrate how it works by selecting the oldest person from the
list of [Person] objects. For the following example, we are going
to use the familiar [Person] class as the container but add to it
a constructor without parameters with two setters:


```
public Person(){}
public void setAge(int age) { this.age = age;}
public void setName(String name) { this.name = name; }
```

Adding a constructor without parameters and setters is necessary because
the [Person] object as a container should be creatable at any
moment without any parameters and should be able to receive and keep the
partial results: the [name] and [age] of the person who is
the oldest, so far. The [collect()] operation will use this
container while processing each element and, after the last element is
processed, will contain the name and the age of the oldest person.

We will use again the same list of persons:


```
List<Person> list = List.of(new Person(23, "Bob"),
                            new Person(33, "Jim"),
                            new Person(28, "Jill"),
                            new Person(27, "Bill"));
```

And here is the [collect()] operation that finds the oldest person
in the list:


```
BiConsumer<Person, Person> accumulator = (p1, p2) -> {
    if(p1.getAge() < p2.getAge()){
        p1.setAge(p2.getAge());
        p1.setName(p2.getName());
    }
};
BiConsumer<Person, Person> combiner = (p1, p2) -> {
    System.out.println("Combiner is called!");
    if(p1.getAge() < p2.getAge()){
        p1.setAge(p2.getAge());
        p1.setName(p2.getName());
    }
};
Person theOldest = list.stream()
                       .collect(Person::new, accumulator, combiner);
System.out.println(theOldest);     //prints: Person{name='Jim', age=33}
```

We tried to inline the functions in the operation call, but it looked a
bit difficult to read, so we decided to create functions first and then
use them in the [collect()] operation. The container, a
[Person] object, is created only once before the first element is
processed. In this sense, it is similar to the initial value of
the [reduce()] operation. Then it is passed to the accumulator,
which compares it to the first element. The [age] field in the
container was initialized to the default value of zero and thus the
[age] and [name] of the first element were set in the
container as the parameters of the oldest person, so far. When the
second stream element ([Person] object) is emitted, its
[age] value is compared to the [age] value currently stored
in the container, and so on, until all elements of the stream are
processed. The result is shown in the previous comments.

When the stream is sequential, the combiner is never called. But when we
make it parallel ([list.parallelStream()]), the message [Combiner
is called!] is printed three times. Well, as in the case
of the [reduce()] operation, the number of partial results may
vary, depending on the number of CPUs and the internal logic of the
[collect()] operation implementation. So, the [Combiner is
called!] message can be printed any number of times. 

Now let\'s look at the first form of the [collect()] operation. It
requires an object of the class that implements
the [java.util.stream.Collector\<T,A,R\>] interface, where
[T] is the stream type, [A] is the container type, and
[R] is the result type. You can use one of the following methods
[of()] (from the [Collector] interface) to create the
necessary [Collector] object:


```
static Collector<T,R,R> of(Supplier<R> supplier, 
                           BiConsumer<R,T> accumulator, 
                           BinaryOperator<R> combiner, 
                           Collector.Characteristics... characteristics)
```

Or


```
static Collector<T,A,R> of(Supplier<A> supplier, 
                           BiConsumer<A,T> accumulator, 
                           BinaryOperator<A> combiner, 
                           Function<A,R> finisher, 
                           Collector.Characteristics... characteristics).
```

The functions you have to pass to the preceding methods are similar to
those we have demonstrated already. But we are not going to do this, for
two reasons. First, it is more involved and pushes us beyond the scope
of this book, and, second, before doing that, you have to look in
the [java.util.stream.Collectors] class, which provides many
ready-to-use collectors.

As we have mentioned already, together with the operations discussed so
far and the numeric streams operations we are going to present in the
next section, ready-to-use collectors cover the vast majority of
processing needs in mainstream programming, and there is a good chance
you will never need to create a custom collector. 

Collectors {#collectors .header-title}
----------

The [java.util.stream.Collectors] class provides more than 40
methods that create [Collector] objects. We are going to
demonstrate only the simplest and most popular ones, as follows:

-   [Collector\<T,?,List\<T\>\> toList()]: Creates a collector
    that generates a [List] object from stream elements
-   [Collector\<T,?,Set\<T\>\> toSet()]: Creates a collector that
    generates a [Set] object from stream elements
-   [Collector\<T,?,Map\<K,U\>\> toMap (Function\<T,K\> keyMapper,
    Function\<T,U\> valueMapper)]: Creates a collector that
    generates a [Map] object from stream elements
-   [Collector\<T,?,C\> toCollection (Supplier\<C\>
    collectionFactory)]: Creates a collector that generates a
    [Collection] object of the type provided by [Supplier\<C\>
    collectionFactory]
-   [Collector\<CharSequence,?,String\> joining()]: Creates a
    collector that generates a [String] object by concatenating
    stream elements
-   [Collector\<CharSequence,?,String\> joining (CharSequence
    delimiter)]: Creates a collector that generates a
    delimiter-separated [String] object from stream elements
-   [Collector\<CharSequence,?,String\> joining (CharSequence delimiter,
    CharSequence prefix, CharSequence suffix)]: Creates a
    collector that generates a delimiter-separated [String] object
    from the stream elements and adds the specified [prefix] and
    [suffix]
-   [Collector\<T,?,Integer\> summingInt(ToIntFunction\<T\>)]:
    Creates a collector that calculates the sum of the results generated
    by the provided function applied to each element; the same method
    exists for [long] and [double] types
-   [Collector\<T,?,IntSummaryStatistics\>
    summarizingInt(ToIntFunction\<T\>)]: Creates a collector that
    calculates the sum, min, max, count, and average of the results
    generated by the provided function applied to each element; the same
    method exists for [long] and [double] types
-   [Collector\<T,?,Map\<Boolean,List\<T\>\>\> partitioningBy
    (Predicate\<? super T\> predicate)]: Creates a collector that
    separates the elements using the provided [Predicate] function
-   [Collector\<T,?,Map\<K,List\<T\>\>\>
    groupingBy(Function\<T,U\>)]: Creates a collector that groups
    elements into a [Map] with keys generated by the provided
    function

The following demo code shows how to use the collectors created by the
methods listed earlier. First, we demonstrate usage of
the [toList()], [toSet()], t[oMap()], and
[toCollection()] methods:


```
List<String> ls = Stream.of("a", "b", "c")
                        .collect(Collectors.toList());
System.out.println(ls);                //prints: [a, b, c]

Set<String> set = Stream.of("a", "a", "c")
                        .collect(Collectors.toSet());
System.out.println(set);                //prints: [a, c]

List<Person> list = List.of(new Person(23, "Bob"),
                            new Person(33, "Jim"),
                            new Person(28, "Jill"),
                            new Person(27, "Bill"));
Map<String, Person> map = list.stream()
                              .collect(Collectors
                              .toMap(p -> p.getName() + "-" + 
                                          p.getAge(), p -> p));
System.out.println(map); //prints: {Bob-23=Person{name='Bob', age:23},
                         //         Bill-27=Person{name='Bill', age:27},
                         //         Jill-28=Person{name='Jill', age:28},
                         //         Jim-33=Person{name='Jim', age:33}}

Set<Person> personSet = list.stream()
                            .collect(Collectors
                            .toCollection(HashSet::new));
System.out.println(personSet);  //prints: [Person{name='Bill', age=27},
                                //         Person{name='Jim', age=33},
                                //         Person{name='Bob', age=23},
                                //         Person{name='Jill', age=28}]
```

The [joining()] method allows concatenating [Character] and
[String] values in a delimited list with [prefix] and
[suffix]:


```
List<String> list1 = List.of("a", "b", "c", "d");
String result = list1.stream()
                     .collect(Collectors.joining());
System.out.println(result);                    //prints: abcd

result = list1.stream()
              .collect(Collectors.joining(", "));
System.out.println(result);                 //prints: a, b, c, d

result = list1.stream()
              .collect(Collectors.joining(", ", "The result: ", ""));
System.out.println(result);           //prints: The result: a, b, c, d

result = list1.stream()
        .collect(Collectors.joining(", ", "The result: ", ". The End."));
System.out.println(result);    //prints: The result: a, b, c, d. The End.
```

Now let\'s turn to the [summingInt()] and
[summarizingInt()] methods. They create collectors that calculate
the sum and other statistics of the [int] values produced by the
provided function applied to each element:


```
List<Person> list2 = List.of(new Person(23, "Bob"),
                             new Person(33, "Jim"),
                             new Person(28, "Jill"),
                             new Person(27, "Bill"));
int sum = list2.stream()
               .collect(Collectors.summingInt(Person::getAge));
System.out.println(sum);                 //prints: 111

IntSummaryStatistics stats = list2.stream()
           .collect(Collectors.summarizingInt(Person::getAge));
System.out.println(stats); //prints: IntSummaryStatistics{count=4,
                           //sum=111, min=23, average=27.750000, max=33}
System.out.println(stats.getCount());    //prints: 4
System.out.println(stats.getSum());      //prints: 111
System.out.println(stats.getMin());      //prints: 23
System.out.println(stats.getAverage());  //prints: 27.750000
System.out.println(stats.getMax());      //prints: 33
```

There are also the [summingLong()], [summarizingLong()],
[summingDouble()], and [summarizingDouble()] methods.

The [partitioningBy()] method creates a collector that groups the
elements by the provided criteria and put the groups (lists) in a
[Map] object with a [boolean] value as the key:


```
Map<Boolean, List<Person>> map2 = list2.stream()
       .collect(Collectors.partitioningBy(p -> p.getAge() > 27));
System.out.println(map2);
     //{false=[Person{name='Bob', age=23}, Person{name='Bill', age=27},
     //  true=[Person{name='Jim', age=33}, Person{name='Jill', age=28}]}
```

As you can see, using the [p.getAge() \> 27] criteria, we were
able to put all the persons in two groups: one is below or equals
to [27] years of [age] (the key is [false]), and
another is above [27] (the key is [true]).

And, finally, the [groupingBy()] method allows grouping elements
by a value and puts the groups (lists) in a [Map] object with this
value as a key:


```
List<Person> list3 = List.of(new Person(23, "Bob"),
                             new Person(33, "Jim"),
                             new Person(23, "Jill"),
                             new Person(33, "Bill"));
Map<Integer, List<Person>> map3 = list3.stream()
                       .collect(Collectors.groupingBy(Person::getAge));
System.out.println(map3);  
      // {33=[Person{name='Jim', age=33}, Person{name='Bill', age=33}], 
      //  23=[Person{name='Bob', age=23}, Person{name='Jill', age=23}]} 
```

To be able to demonstrate this method, we changed our list of
[Person] objects by setting the [age] on each of them either
to [23] or to [33]. The result is two groups ordered by
their [age].

There are also overloaded [toMap()], [groupingBy()], and
[partitioningBy()] methods as well as the following, often
overloaded, methods that create corresponding [Collector] objects,
as follows:

-   [counting()]
-   [reducing()]
-   [filtering()]
-   [toConcurrentMap()]
-   [collectingAndThen()]
-   [maxBy()], [minBy()]
-   [mapping()], [flatMapping()]
-   [averagingInt()], [averagingLong()],
    [averagingDouble()]
-   [toUnmodifiableList()], [toUnmodifiableMap()],
    [toUnmodifiableSet()]

If you cannot find the operation you need among those discussed in this
book, search the [Collectors] API first, before building your own
[Collector] object.

Numeric stream interfaces
=====================

As we have mentioned already, all three numeric
interfaces, [IntStream], [LongStream], and
[DoubleStream], have methods similar to the methods in the
[Stream] interface, including the methods of the
[Stream.Builder] interface. This means that everything we have
discussed so far in this chapter equally applies to any numeric stream
interfaces. That is why in this section we will only talk about those
methods that are not present in the [Stream] interface, as
follows:

-   The [range(lower,upper)] and
    [rangeClosed(lower,upper)] methods in the [IntStream]
    and [LongStream] interfaces allow creating a stream from the
    values in the specified range
-   The [boxed()] and [mapToObj()] intermediate
    operations convert a numeric stream to [[Stream]]
-   The [mapToInt()], [mapToLong()], and
    [mapToDouble()] intermediate operations convert a numeric
    stream of one type to a numeric stream of another type
-   The intermediate operations [flatMapToInt()],
    [flatMapToLong()], and [flatMapToDouble()] convert a
    stream to a numeric stream
-   The [sum()] and [average()] terminal
    operations calculate the sum and average of numeric stream elements

Creating a stream {#creating-a-stream .header-title}
-----------------

In addition to the methods of the [Stream] interface that create
streams, the [IntStream] and [LongStream] interfaces allow
creating a stream from the values in the specified range.

Intermediate operations {#intermediate-operations .header-title}
-----------------------

In addition to the intermediate operations of the [Stream]
interface, the [IntStream], [LongStream], and
[DoubleStream] interfaces also have number-specific intermediate
operations: [boxed()], [mapToObj()], [mapToInt()],
[mapToLong()], [mapToDouble()], [flatMapToInt()],
[flatMapToLong()], and [flatMapToDouble()].

boxed(), mapToObj() {#boxed-maptoobj .header-title}
-------------------

The [boxed()] intermediate operation converts (boxes) elements of
the primitive numeric type to the corresponding wrapper type:


```
    //IntStream.range(1, 3).map(Integer::shortValue) //compile error
    //                  .forEach(System.out::print);

    IntStream.range(1, 3)
             .boxed()
             .map(Integer::shortValue)
             .forEach(System.out::print);            //prints: 12

    //LongStream.range(1, 3).map(Long::shortValue)   //compile error
    //                .forEach(System.out::print);

    LongStream.range(1, 3)
              .boxed()
              .map(Long::shortValue)
              .forEach(System.out::print);           //prints: 12
    
    //DoubleStream.of(1).map(Double::shortValue)     //compile error
    //              .forEach(System.out::print);

    DoubleStream.of(1)
                .boxed()
                .map(Double::shortValue)
                .forEach(System.out::print);         //prints: 1
```

In the preceding code, we have commented out the lines that generate
compilation error because the elements generated by
the [range()] method are of primitive types. The [boxed()]
operation converts a primitive value to the corresponding wrapping type,
so it can be processed as a reference type. The [mapToObj()]
intermediate operation does a similar transformation, but it is not as
specialized as the [boxed()] operation and allows using an element
of primitive type to produce an object of any type:


```
IntStream.range(1, 3)
         .mapToObj(Integer::valueOf)
         .map(Integer::shortValue)
         .forEach(System.out::print);           //prints: 12

IntStream.range(42, 43)
      .mapToObj(i -> new Person(i, "John"))
      .forEach(System.out::print); //prints: Person{name='John', age=42}

LongStream.range(1, 3)
          .mapToObj(Long::valueOf)
          .map(Long::shortValue)
          .forEach(System.out::print);          //prints: 12

DoubleStream.of(1)
            .mapToObj(Double::valueOf)
            .map(Double::shortValue)
            .forEach(System.out::print);        //prints: 1
```

In the preceding code, we have added the [map()] operation just to
prove that the [mapToObj()] operation does the job and creates an
object of the wrapping type as expected. Also, by adding the pipeline
that produces [Person] objects, we have demonstrated how the
[mapToObj()] operation can be used to create an object of any
type. 

mapToInt(), mapToLong(), mapToDouble() {#maptoint-maptolong-maptodouble .header-title}
--------------------------------------

The [mapToInt()], [mapToLong()], [mapToDouble()]
intermediate operations allow converting a numeric stream of one type to
a numeric stream of another type. For the sake of example, we convert a
list of [String] values to a numeric stream of different types by
mapping each [String] value to its length:


```
List<String> list = List.of("one", "two", "three");
list.stream()
    .mapToInt(String::length)
    .forEach(System.out::print);               //prints: 335

list.stream()
    .mapToLong(String::length)
    .forEach(System.out::print);               //prints: 335

list.stream()
    .mapToDouble(String::length)
    .forEach(d -> System.out.print(d + " "));  //prints: 3.0 3.0 5.0

list.stream()
    .map(String::length)
    .map(Integer::shortValue)
    .forEach(System.out::print);               //prints: 335
```

The elements of the created numeric streams are of the primitive type:


```
//list.stream().mapToInt(String::length)
//             .map(Integer::shortValue) //compile error
//             .forEach(System.out::print);
```

And, as we are on this topic, if you would like to convert elements to a
numeric wrapping type, the intermediate [map()] operation is the
way to do it (instead of [mapToInt()]):


```
list.stream().map(String::length)
        .map(Integer::shortValue)
        .forEach(System.out::print);      //prints: 335
```

flatMapToInt(), flatMapToLong(), flatMapToDouble() {#flatmaptoint-flatmaptolong-flatmaptodouble .header-title}
--------------------------------------------------

The [flatMapToInt()], [flatMapToLong()],
[flatMapToDouble()] intermediate operations produce a numeric
stream of the corresponding type:


```
List<Integer> list = List.of(1, 2, 3);
list.stream()
    .flatMapToInt(i -> IntStream.rangeClosed(1, i))
    .forEach(System.out::print);               //prints: 112123

list.stream()
    .flatMapToLong(i -> LongStream.rangeClosed(1, i))
    .forEach(System.out::print);               //prints: 112123

list.stream()
    .flatMapToDouble(DoubleStream::of)
    .forEach(d -> System.out.print(d + " "));  //prints: 1.0 2.0 3.0
```

As you can see in the preceding code, we have used [int] values in
the original stream. But it can be a stream of any type:


```
List.of("one", "two", "three")
    .stream()
    .flatMapToInt(s -> IntStream.rangeClosed(1, s.length()))
    .forEach(System.out::print);             //prints: 12312312345
```

Terminal operations {#terminal-operations .header-title}
-------------------

Numeric-specific terminal operations are pretty straightforward. There
are two of them, as follows:

-   [sum()]: Calculates the sum of numeric stream elements
-   [average()]: Calculates the average of numeric stream elements


Parallel streams
=====================

We have seen that changing from a sequential stream to a parallel stream
can lead to incorrect results if the code was not written and tested for
processing a parallel stream. The following are a few more
considerations related to parallel streams.

Stateless and stateful operations {#stateless-and-stateful-operations .header-title}
---------------------------------

There are **stateless operations**, such as [filter()],
[map()], and [flatMap()], which do not keep data around (do
not maintain state) while moving from processing from one stream element
to the next. And there are stateful operations, such as
[distinct()], [limit()], [sorted()], [reduce()],
and [collect()], that can pass the state from previously processed
elements to the processing of the next element.

Stateless operations usually do not pose a problem while switching from
a sequential stream to a parallel one. Each element is processed
independently and the stream can be broken into any number of
sub-streams for independent processing. With stateful operations, the
situation is different. To start with, using them for an infinite stream
may never finish processing. Also, while discussing the [reduce()]
and [collect()] stateful operations, we have demonstrated how
switching to a parallel stream can produce a different result if the
initial value (or identity) is set without parallel processing in mind.

And there are performance considerations too. Stateful operations often
require processing all the stream elements in several passes using
buffering. For large streams, it may tax JVM resources and slow down, if
not completely shut down, the application.

That is why a programmer should not take switching from a sequential to
a parallel streams lightly. If stateful operations are involved, the
code has to be designed and tested to be able to perform parallel stream
processing without negative effects.

Sequential or parallel processing? {#sequential-or-parallel-processing .header-title}
----------------------------------

As we indicated in the previous section, parallel processing may or may
not produce better performance. You have to test every use case before
deciding on using parallel streams. Parallelism can yield better
performance, but the code has to be designed and possibly optimized to
do it. And each assumption has to be tested in an environment that is as
close to the production as possible.

However, there are a few considerations you can take into account while
deciding between sequential and parallel processing, as follows:

-   Small streams are typically processed faster sequentially (well,
    what is *small* for your environment should be determined through
    testing and by measuring the performance)
-   If stateful operations cannot be replaced with stateless
    ones, carefully design your code for the parallel processing or just
    avoid it
-   Consider parallel processing for procedures that require extensive
    calculations, but think about bringing the partial results together
    for the final result

Summary
=====================

In this chapter, we have talked about data-streams processing, which is
different from processing the I/O streams we reviewed in
[Chapter 5] *Strings,
Input/Output, and Files*. We defined what the data streams are, how to
process their elements using stream operations, and how to chain
(connect) stream operations in a pipeline. We also discussed stream
initialization and how to process streams in parallel. 

In the next chapter, the reader will be introduced to The **Reactive
Manifesto**, its thrust, and examples of its implementations. We will
discuss the difference between reactive and responsive systems and what
**asynchronous** and **non-blocking** processings are. We will also talk
about **Reactive Streams** and **RxJava**.
