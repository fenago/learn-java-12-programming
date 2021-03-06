<img align="right" src="../logo-small.png">


Functional Programming
======================

This lab brings the reader into the world of functional programming.
It explains what a functional interface is, provides an overview of
functional interfaces that come with JDK, and defines and demonstrates
lambda expressions and how to use them with functional interfaces,
including using **method reference**.

The following topics will be covered in this lab:

-   What is functional programming?
-   Standard functional interfaces
-   Functional pipelines
-   Lambda expression limitations
-   Method reference

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
`java -cp target/learnjava-1.0.jar:target/libs/* com.lv.learnjava.ch13_functional.FunctionalInterface`

<h3><span style="color:red;">Run Java Code</span></h3>

You can run the example by running following command in the terminal:
`java -cp target/learnjava-1.0.jar:target/libs/* com.lv.learnjava.ch13_functional.LambdaExpressions`

<h3><span style="color:red;">Run Java Code</span></h3>

You can run the example by running following command in the terminal:
`java -cp target/learnjava-1.0.jar:target/libs/* com.lv.learnjava.ch13_functional.MethodReferenceDemo`

<h3><span style="color:red;">Run Java Code</span></h3>

You can run the example by running following command in the terminal:
`java -cp target/learnjava-1.0.jar:target/libs/* com.lv.learnjava.ch13_functional.StandardFunctionalInterfaces`

What is functional programming?
======================

We have actually used functional programming in the preceding labs.
In 
*Data Structures, Generics and Popular Utilities*, we talked about the
[Iterable] interface and its [default void forEach (Consumer\<T\>
function)] method and provided the following example:


```
Iterable<String> list = List.of("s1", "s2", "s3");
System.out.println(list);                       //prints: [s1, s2, s3]
list.forEach(e -> System.out.print(e + " "));   //prints: s1 s2 s3
```

You can see how a [Consumer] [e -\> System.out.print(e + \"
\")] function is passed into the [forEach()] method and
applied to each element flowing into this method from the list. We will
discuss the [Consumer] function shortly.

We also mentioned two methods of the [Collection] interface that
accept a function as a parameter too:

-   The [default boolean remove(Predicate\<E\> filter)] method,
    which attempts to remove all the elements that satisfy the given
    predicate from the collection; a [Predicate] function accepts
    an element of the collection and returns a [boolean] value
-   The [default T\[\] toArray(IntFunction\<T\[\]\>
    generator)] method, which returns an array of all the elements
    of the collection, using the provided [IntFunction] generator
    function to allocate the returned array

In the same lab, we also mentioned the following method of
the [List] interface:

-   [default void replaceAll(UnaryOperator\<E\> operator)]:
    Replaces each element of the list with the result of applying the
    provided [UnaryOperator] to that element; a
    [UnaryOperator] is one of the functions we are going to review
    in this lab.

We described the [Map] interface, its method [default V merge(K
key, V value, BiFunction\<V,V,V\> remappingFunction)] and how it
can be used for concatenating the [String] values: [map.merge(key,
value, String::concat)]. The [BiFunction\<V,V,V\>] takes two
parameters of the same type and returns the value of the same type as
well. The [String::concat] construct is called a method
reference and will be explained in the *Method reference* section.

We provided the following example of passing a [Comparator]
function:


```
list.sort(Comparator.naturalOrder());
Comparator<String> cmp = (s1, s2) -> s1 == null ? -1 : s1.compareTo(s2);
list.sort(cmp);
```

It takes two [String] parameters, then compares the first one to
[null]. If the first parameter is [null], the function
returns [-1]; otherwise, it compares the first parameter and the
second one using the [compareTo()] method.

In 
*Network Programming*, we looked at the following code:


```
HttpClient httpClient = HttpClient.newBuilder().build();
HttpRequest req = HttpRequest.newBuilder()
          .uri(URI.create("http://localhost:3333/something")).build();
try {
    HttpResponse<String> resp = 
                        httpClient.send(req, BodyHandlers.ofString());
    System.out.println("Response: " + 
                             resp.statusCode() + " : " + resp.body());
} catch (Exception ex) {
    ex.printStackTrace();
}
```

The [BodyHandler] object (a function) is generated by
the [BodyHandlers.ofString()] factory method and passed into
the [send()] method as a parameter. Inside the method, the code
calls its [apply()] method :


```
BodySubscriber<T> apply​(ResponseInfo responseInfo)
```

Finally, in 
*Java GUI Programming*, we used an [EventHandler] function as a
parameter in the following code snippet:


```
btn.setOnAction(e -> { 
                       System.out.println("Bye! See you later!");
                       Platform.exit();
                     }
               );
primaryStage.onCloseRequestProperty()
       .setValue(e -> System.out.println("Bye! See you later!"));
```

The first function is [EventHanlder\<ActionEvent\>]. It prints a
message and forces the application to exit. The second is
the [EventHandler\<WindowEvent\>] function. It just prints the
message.

All these examples give you a pretty good idea of how a function can be
constructed and passed around as a parameter. This ability constitutes
functional programming. It is present in many programming languages. It
does not require the managing of object states. The function is
stateless. Its result depends only on the input data, no matter how many
times it was called. Such coding makes the outcome more predictable,
which is the most attractive aspect of functional programming.

The area that benefits the most from such a design is parallel data
processing. Functional programming allows for shifting the
responsibility for parallelism from the client code to the library.
Before that, in order to process elements of Java collections, the
client code had to iterate over the collection and organize the
processing. In Java 8, new (default) methods were added that accept a
function as a parameter and then apply it to each element of the
collection, in parallel or not, depending on the internal processing
algorithm. So, it is the library\'s responsibility to organize parallel
processing.

What is a functional interface?
-------------------------------

When we define a function, we, in fact, provide an implementation of an
interface that has only one abstract method. That is how the Java
compiler knows where to put the provided functionality. The compiler
looks at the interface ([Consumer], [Predicate],
[Comparator], [IntFunction], [UnaryOperator],
[BiFunction], [BodyHandler], and [EvenHandler] in the
preceding examples), sees only one abstract method there, and uses the
passed-in functionality as the method implementation. The only
requirement is that the passed-in parameters must match the method
signature. Otherwise, the compile-time error is generated.

That is why any interface that has only one abstract method is called a
**functional interface**. Please note that the requirement of
having **only one abstract method** includes the method inherited from
the parent interface. For example, consider the following interfaces:


```
@FunctionalInterface
interface A {
    void method1();
    default void method2(){}
    static void method3(){}
}

@FunctionalInterface
interface B extends A {
    default void method4(){}
}

@FunctionalInterface
interface C extends B {
    void method1();
}

//@FunctionalInterface 
interface D extends C {
    void method5();
}
```

The [A] is a functional interface because it has only one abstract
method [method1()]. The [B] is a functional interface too
because it has only one abstract method---the same [method1()]
inherited from the [A] interface. The [C] is a functional
interface because it has only one abstract method, [method1()],
which overrides the abstract [method1()] of the parent interface
[A]. Interface [D] cannot be a functional interface because
it has two abstract methods---[method1()] from the parent
interface [A] and [method5()].

To help avoid runtime errors, the [\@FunctionalInterface]
annotation was introduced in Java 8. It tells the compiler about the
intent so the compiler can check and see if there is truly only one
abstract method in the annotated interface. This annotation also warns a
programmer, who reads the code, that this interface has only one
abstract method intentionally. Otherwise, a programmer may waste time
adding another abstract method to the interface only to discover at
runtime that it cannot be done.

For the same reason, the [Runnable] and [Callable]
interfaces, which have existed in Java since its early versions, were
annotated in Java 8 as [\@FunctionalInterface]. This distinction
is made explicit and serves as a reminder to users that these interfaces
can be used for creating a function:


```
@FunctionalInterface
interface Runnable {
    void run(); 
}

@FunctionalInterface
interface Callable<V> {
    V call() throws Exception;
}
```

As with any other interface, the functional interface can be implemented
using the anonymous class:


```
Runnable runnable = new Runnable() {
    @Override
    public void run() {
        System.out.println("Hello!");
    }
};
```

An object created this way can later be used as follows:


```
runnable.run();   //prints: Hello!
```

If we look closely at the preceding code, we notice that there is
unnecessary overhead. First, there is no need to repeat the interface
name, because we declared it already as the type for the object
reference. And, second, in the case of a functional interface that has
only one abstract method, there is no need to specify the method name
that has to be implemented. The compiler and Java runtime can figure it
out. All we need is to provide the new functionality. The** **lambda
expressions were introduced especially for this purpose.

What is a lambda expression?
----------------------------

The term lambda comes from lambda calculus---a universal model of
computation that can be used to simulate any Turing machine. It was
introduced by mathematician Alonzo Church in the 1930s. A **lambda
expression** is a function, implemented in Java as an anonymous method.
It also allows for the omitting of modifiers, return types, and
parameter types. That makes for a very compact notation.

The syntax of lambda expression includes the list of parameters, an
arrow token ([-\>]), and a body. The list of parameters can be
empty, such as [()], without parentheses (if there is only one
parameter), or a comma-separated list of parameters surrounded
by parentheses. The body can be a single expression or a statement block
inside the braces ([{}]). Let\'s look at a few examples:

-   [() -\> 42;] always returns [42].
-   [x -\> x\*42 + 42;] multiplies the [x] value by
    [42], then adds [42] to the result and returns it.
-   [(x, y) -\> x \* y;] multiplies the passed-in parameters and
    returns the result.
-   [s -\> \"abc\".equals(s);] compares the value of variable
    [s] and literal [\"abc\"]; it returns
    a [boolean] result value.
-   [s -\> System.out.println(\"x=\" + s);] prints the [s]
    value with the prefix [\"x=\"].
-   [(i, s) -\> { i++; System.out.println(s + \"=\" + i); };]
    increments the input integer and prints the new value with the
    prefix [s + \"=\"], [s] being the value of the second
    parameter.

Without functional programming, the only way to pass some functionality
as a parameter in Java would be by writing a class that implements an
interface, creating its object, and then passing it as a parameter. But
even the least-involved style using an anonymous class requires writing
too much of the boilerplate code. Using functional interfaces and lambda
expressions makes the code shorter, clearer, and more expressive.

For example, lambda expressions allow us to reimplement our preceding
example with the [Runnable] interface as follows:


```
Runnable runnable = () -> System.out.println("Hello!");
```

As you can see, creating a functional interface is easy, especially with
lambda expressions. But before doing that, consider using one of the 43
functional interfaces provided in the package
[java.util.][function]. This will not only allow you to
writing less code, but will also help other programmers who are familiar
with the standard interfaces to understand your code better.


Standard functional interfaces
======================

Most of the interfaces provided in the [java.util.function]
package are specializations of the following four interfaces:
[Consumer\<T\>], [Predicate\<T\>], [Supplier\<T\>],
and [Function\<T,R\>]. Let\'s review them and then look at a short
overview of the other 39 standard functional interfaces.

Consumer\<T\>
-------------

By looking at the [Consumer\<T\>] interface definition, you can
\<indexentry content=\"standard functional interfaces:Consumer\"\>guess
already that this interface has an abstract method that accepts a
parameter of type [T] and does not return anything. Well, when
only one type is listed, it may define the type of the return value, as
in the case of the [Supplier\<T\>] interface. But the interface
name serves as a clue: the **consumer** name indicates that the method
of this interface just takes the value and returns nothing, while
the **supplier** returns the value. This clue is not precise but helps
to jog the memory.

The best source of information about any functional interface is
the [java.util.function] package API documentation
(<https://docs.oracle.com/en/java/javase/12/docs/api/java.base/java/util/function/package-summary.html>).
If we read it, we learn that the [Consumer\<T\>] interface has one
abstract and one default method:

-   [void accept(T t)]: Applies the operation to the given
    argument
-   [default Consumer\<T\> andThen(Consumer\<T\> after)]: Returns
    a composed [Consumer] function that performs, in sequence, the
    current operation followed by the [after] operation

It means that, for example, we can implement and then execute it as
follows:


```
Consumer<String> printResult = s -> System.out.println("Result: " + s);
printResult.accept("10.0");   //prints: Result: 10.0
```

We can also have a factory method that creates the function, for
example:


```
Consumer<String> printWithPrefixAndPostfix(String pref, String postf){
    return s -> System.out.println(pref + s + postf);
```

Now we can use it as follows:


```
printWithPrefixAndPostfix("Result: ", " Great!").accept("10.0");            
                                           //prints: Result: 10.0 Great!
```

To demonstrate the [andThen()] method, let\'s create the class
[Person]:


```
public class Person {
    private int age;
    private String firstName, lastName, record;
    public Person(int age, String firstName, String lastName) {
        this.age = age;
        this.lastName = lastName;
        this.firstName = firstName;
    }
    public int getAge() { return age; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getRecord() { return record; }
    public void setRecord(String fullId) { this.record = record; }
}
```

You may have noticed that [record] is the only property that has a
setting. We will use it to set a personal record in a consumer function:


```
String externalData = "external data";
Consumer<Person> setRecord =
      p -> p.setFullId(p.getFirstName() + " " +
             p.getLastName() + ", " + p.getAge() + ", " + externalData);
```

The [setRecord] function takes the values of the [Person]
object properties and some data from an external source and sets the
resulting value as the [record] property value. Obviously, it
could be done in several other ways, but we do it for demo purposes.
Let\'s also create a function that prints the [record] property:


```
Consumer<Person> printRecord = p -> System.out.println(p.getRecord());
```

The composition of these two functions can be created and executed as
follows:


```
Consumer<Person> setRecordThenPrint = setRecord.andThen(printPersonId);
setRecordThenPrint.accept(new Person(42, "Nick", "Samoylov"));   
                         //prints: Nick Samoylov, age 42, external data
```

This way, it is possible to create a whole processing pipe of the
operations that transform the properties of an object that is passed
through the pipe. 

Predicate\<T\>
--------------

This functional interface, [Predicate\<T\>], has one abstract
method, five defaults, and a static method that allows predicates
chaining: 

-   [boolean test(T t)]: Evaluates the provided parameter to see
    if it meets the criteria or not
-   [default Predicate\<T\> negate()]: Returns the negation of the
    current predicate
-   [static \<T\> Predicate\<T\> not(Predicate\<T\>
    target)]: Returns the negation of the provided predicate
-   [default Predicate\<T\> or(Predicate\<T\> other)]: Constructs
    a logical [OR] from this predicate and the provided one
-   [default Predicate\<T\> and(Predicate\<T\> other)]: Constructs
    a logical [AND] from this predicate and the provided one
-   [static \<T\> Predicate\<T\> isEqual(Object targetRef)]:
    Constructs a predicate that evaluates whether or not two arguments
    are equal according to [Objects.equals(Object, Object)]

The basic use of this interface is pretty straightforward:


```
Predicate<Integer> isLessThan10 = i -> i < 10;
System.out.println(isLessThan10.test(7));      //prints: true
System.out.println(isLessThan10.test(12));     //prints: false
```

We can also combine it with the previously
created [printWithPrefixAndPostfix(String pref, String
postf)] function:


```
int val = 7;
Consumer<String> printIsSmallerThan10 = printWithPrefixAndPostfix("Is " 
                               + val + " smaller than 10? ", " Great!");
printIsSmallerThan10.accept(String.valueOf(isLessThan10.test(val)));         
                          //prints: Is 7 smaller than 10? true Great!
```

The other methods (also called **operations**) can be used for creating
operational chains (also called **pipelines**) and can be seen in the
following examples:


```
Predicate<Integer> isEqualOrGreaterThan10 = isLessThan10.negate();
System.out.println(isEqualOrGreaterThan10.test(7));   //prints: false
System.out.println(isEqualOrGreaterThan10.test(12));  //prints: true

isEqualOrGreaterThan10 = Predicate.not(isLessThan10);
System.out.println(isEqualOrGreaterThan10.test(7));   //prints: false
System.out.println(isEqualOrGreaterThan10.test(12));  //prints: true

Predicate<Integer> isGreaterThan10 = i -> i > 10;
Predicate<Integer> is_lessThan10_OR_greaterThan10 = 
                                       isLessThan10.or(isGreaterThan10);
System.out.println(is_lessThan10_OR_greaterThan10.test(20));  // true
System.out.println(is_lessThan10_OR_greaterThan10.test(10));  // false

Predicate<Integer> isGreaterThan5 = i -> i > 5;
Predicate<Integer> is_lessThan10_AND_greaterThan5 = 
                                       isLessThan10.and(isGreaterThan5);
System.out.println(is_lessThan10_AND_greaterThan5.test(3));  // false
System.out.println(is_lessThan10_AND_greaterThan5.test(7));  // true

Person nick = new Person(42, "Nick", "Samoylov");
Predicate<Person> isItNick = Predicate.isEqual(nick);
Person john = new Person(42, "John", "Smith");
Person person = new Person(42, "Nick", "Samoylov");
System.out.println(isItNick.test(john));              //prints: false
System.out.println(isItNick.test(person));            //prints: true
```

The predicate objects can be chained into more complex logical
statements and include all necessary external data, as was demonstrated
before.

Supplier\<T\>
-------------

This functional interface, [Supplier\<T\>], has only one abstract
method [T get()], which returns a value. The basic usage can be
seen as follows:


```
Supplier<Integer> supply42 = () -> 42;
System.out.println(supply42.get());  //prints: 42
```

It can be chained with the functions discussed in the preceding
sections:


```
int input = 7;
int limit = 10;
Supplier<Integer> supply7 = () -> input;
Predicate<Integer> isLessThan10 = i -> i < limit;
Consumer<String> printResult = printWithPrefixAndPostfix("Is " + input + 
                             " smaller than " + limit + "? ", " Great!");
printResult.accept(String.valueOf(isLessThan10.test(supply7.get())));
                           //prints: Is 7 smaller than 10? true Great!
```

 The [Supplier\<T\>] function is typically used as an entry point
of data going into a processing pipeline.

Function\<T, R\>
----------------

The notation of this and other functional interfaces that return values,
includes the listing of the return type as the last in the list of
generics ([R] in this case) and the type of the input data in
front of it (an input parameter of type [T] in this case). So, the
notation [Function\<T, R\>] means that the only abstract method of
this interface accepts an argument of type [T] and produces a
result of type [R]. Let\'s look at the online documentation
(<https://docs.oracle.com/en/java/javase/12/docs/api/java.base/java/util/function/Function.html>).

The [Function\<T, R\>] interface has one abstract method, [R
apply(T)], and two methods for operations chaining:

-   [default \<V\> Function\<T,V\> andThen(Function\<R, V\>
    after)]: Returns a composed function that first applies the
    current function to its input, and then applies the [after]
    function to the result.
-   [default \<V\> Function\<V,R\> compose(Function\<V, T\>
    before)]: Returns a composed function that first applies the
    [before] function to its input, and then applies the current
    function to the result.

There is also an [identity()] method:

-   [static \<T\> Function\<T,T\> identity()]: Returns a function
    that always returns its input argument

Let\'s review all these methods and how they can be used. Here is an
example of the basic usage of the [Function\<T,R\>] interface:


```
Function<Integer, Double> multiplyByTen = i -> i * 10.0;
System.out.println(multiplyByTen.apply(1));    //prints: 10.0
```

We can also chain it with all the functions we have discussed in the
preceding sections:


```
Supplier<Integer> supply7 = () -> 7;
Function<Integer, Double> multiplyByFive = i -> i * 5.0;
Consumer<String> printResult = 
                       printWithPrefixAndPostfix("Result: ", " Great!");
printResult.accept(multiplyByFive.
        apply(supply7.get()).toString()); //prints: Result: 35.0 Great!
```

The [andThen()] method allows for constructing a complex function
from the simpler ones. Notice the [divideByTwo.amdThen()] line in
the following code:


```
Function<Double, Long> divideByTwo = 
                               d -> Double.valueOf(d / 2.).longValue();
Function<Long, String> incrementAndCreateString = 
                                            l -> String.valueOf(l + 1);
Function<Double, String> divideByTwoIncrementAndCreateString = 
                         divideByTwo.andThen(incrementAndCreateString);
printResult.accept(divideByTwoIncrementAndCreateString.apply(4.));
                                             //prints: Result: 3 Great!
```

It describes the sequence of the operations applied to the input value.
Notice how the return type of the [divideByTwo()] function
([Long]) matches the input type of the
[incrementAndCreateString()] function.

The [compose()] method accomplishes the same result, but in
reverse order:


```
Function<Double, String> divideByTwoIncrementAndCreateString =  
                        incrementAndCreateString.compose(divideByTwo);
printResult.accept(divideByTwoIncrementAndCreateString.apply(4.));  
                                            //prints: Result: 3 Great!
```

Now the sequence of composition of the complex function does not match
the sequence of the execution. It may be very convenient in the case
where the function [divideByTwo()] is not created yet and you
would like to create it in-line. Then the following construct will not
compile:


```
Function<Double, String> divideByTwoIncrementAndCreateString =
        (d -> Double.valueOf(d / 2.).longValue())
                                    .andThen(incrementAndCreateString); 
```

The following line will compile just fine:


```
Function<Double, String> divideByTwoIncrementAndCreateString =
        incrementAndCreateString
                     .compose(d -> Double.valueOf(d / 2.).longValue());
```

It allows for more flexibility while constructing a functional pipeline,
so one can build it in a fluent style without breaking the continuous
line when creating the next operations.

The [identity()] method is useful when you need to pass in a
function that matches the required function signature but does nothing.
But it can substitute only the function that returns the same type as
the input type. For example:


```
Function<Double, Double> multiplyByTwo = d -> d * 2.0; 
System.out.println(multiplyByTwo.apply(2.));  //prints: 4.0

multiplyByTwo = Function.identity();
System.out.println(multiplyByTwo.apply(2.));  //prints: 2.0
```

To demonstrate its usability, let\'s assume we have the following
processing pipeline:


```
Function<Double, Double> multiplyByTwo = d -> d * 2.0;
System.out.println(multiplyByTwo.apply(2.));  //prints: 4.0

Function<Double, Long> subtract7 = d -> Math.round(d - 7);
System.out.println(subtract7.apply(11.0));   //prints: 4

long r = multiplyByTwo.andThen(subtract7).apply(2.);
System.out.println(r);                       //prints: -3
```

 Then, we decide that, under certain circumstances,
the [multiplyByTwo()] function should do nothing. We could add to
it a conditional close that turns it on/off. But if we want to keep the
function intact or if this function is passed to us from third-party
code, we can just do the following:


```
Function<Double, Double> multiplyByTwo = d -> d * 2.0;
System.out.println(multiplyByTwo.apply(2.));  //prints: 4.0

Function<Double, Long> subtract7 = d -> Math.round(d - 7);
System.out.println(subtract7.apply(11.0));   //prints: 4

multiplyByTwo = Function.identity();

r = multiplyByTwo.andThen(subtract7).apply(2.);
System.out.println(r);                      //prints: -5
```

As you can see, now the [multiplyByTwo()] function does nothing,
and the final result is different.

Other standard functional interfaces
------------------------------------

The other 39 functional interfaces in the [java.util.function]
package are variations of the four interfaces we have just reviewed.
These variations are created in order to achieve one or any combination
of the following:

-   Better performance by avoiding autoboxing and unboxing via the
    explicit usage of [int], [double], or
    [long] primitives
-   Allowing two input parameters and/or a shorter notation

Here are just a few examples:

-   [IntFunction\<R\>] with the method [R
    apply(int)] provides a shorter notation (without generics for
    the input parameter type) and avoids autoboxing by requiring the
    primitive [int] as a parameter.
-   [BiFunction\<T,U,R\>] with the method [R apply(T,U)]
    allows two input parameters; [BinaryOperator\<T\>] with the
    method [T apply(T,T)] allows two input parameters of type
    [T] and returns a value of the same type, [T].
-   [IntBinaryOperator] with the method [int
    applAsInt(int,int)] accepts two parameters of type [int]
    and returns value of type [int], too.

If you are going to use functional interfaces, we encourage you to study
the API of the interfaces of the [java.util.functional] package
(<https://docs.oracle.com/en/java/javase/12/docs/api/java.base/java/util/function/package-summary.html>).


Lambda expression limitations
======================

There are two aspects of a lambda expression that we would like to point
out and clarify:

-   If a lambda expression uses a local variable created outside it,
    this local variable has to be final or effectively final (not
    reassigned in the same context).
-   The [this] keyword in a lambda expression refers to the
    enclosing context, not the lambda expression itself.

As in an anonymous class, the variable created outside and used inside a
lambda expression becomes effectively final and cannot be modified. The
following is an example of an error caused by the attempt to change the
value of an initialized variable:


```
int x = 7;
//x = 3; //compilation error
Function<Integer, Integer> multiply = i -> i * x;
```

The reason for this restriction is that a function can be passed around
and executed in different contexts (different threads, for example), and
an attempt to synchronize these contexts would defeat the original idea
of the stateless function and the evaluation of the expression,
depending only on the input parameters, not on the context variables.
That is why all the local variables used in the lambda expression have
to be effectively final, meaning that they can either be declared final
explicitly or become final by the virtue of not changing the value.

There is one possible workaround for this limitation though. If the
local variable is of reference type (but not [String] or primitive
wrapping type), it is possible to change its state, even if this local
variable is used in the lambda expression:


```
List<Integer> list = new ArrayList();
list.add(7);
int x = list.get(0);
System.out.println(x);  // prints: 7
list.set(0, 3);
x = list.get(0);
System.out.println(x);  // prints: 3
Function<Integer, Integer> multiply = i -> i * list.get(0);
```

This workaround should be used with care because of the danger of
unexpected side effects should this lambda be executed in a different
context.

The [this] keyword inside an anonymous class refers to the
instance of the anonymous class. By contrast, inside the lambda
expression, the [this] keyword refers to the instance of the class
that surrounds the expression, also called an **enclosing instance**,
**enclosing context**, or **enclosing scope**.

Let\'s create a [ThisDemo] class that illustrates the difference:


```
class ThisDemo {
    private String field = "ThisDemo.field";

    public void useAnonymousClass() {
        Consumer<String> consumer = new Consumer<>() {
            private String field = "Consumer.field";
            public void accept(String s) {
                System.out.println(this.field);
            }
        };
        consumer.accept(this.field);
    }

    public void useLambdaExpression() {
        Consumer<String> consumer = consumer = s -> {
            System.out.println(this.field);
        };
        consumer.accept(this.field);
    }
}
```

If we execute the preceding methods, the output will be as shown in the
following code comments:


```
ThisDemo d = new ThisDemo();
d.useAnonymousClass();      //prints: Consumer.field
d.useLambdaExpression();    //prints: ThisDemo.field
```

As you can see, the keyword [this] inside the anonymous class
refers to the anonymous class instance, while [this] in a lambda
expression refers to the enclosing class instance. A lambda expression
just does not have, and cannot have, a field. A lambda expression is not
a class instance and cannot be referred by [this]. According to
Java\'s specifications, such an approach *allows more flexibility for
implementations* by treating [this] the same as the surrounding
context.


Method references
======================

So far, all our functions were short one-liners. Here is another
example:


```
Supplier<Integer> input = () -> 3;
Predicate<Integer> checkValue = d -> d < 5;
Function<Integer, Double> calculate = i -> i * 5.0;
Consumer<Double> printResult = d -> System.out.println("Result: " + d);

if(checkValue.test(input.get())){
    printResult.accept(calculate.apply(input.get()));
} else {
    System.out.println("Input " + input.get() + " is too small.");
} 
```

If the function consists of two or more lines, we could implement them
as follows:


```
Supplier<Integer> input = () -> {
     // as many line of code here as necessary
     return 3;
};
Predicate<Integer> checkValue = d -> {
    // as many line of code here as necessary
    return d < 5;
};
Function<Integer, Double> calculate = i -> {
    // as many lines of code here as necessary
    return i * 5.0;
};
Consumer<Double> printResult = d -> {
    // as many lines of code here as necessary
    System.out.println("Result: " + d);
};
if(checkValue.test(input.get())){
    printResult.accept(calculate.apply(input.get()));
} else {
    System.out.println("Input " + input.get() + " is too small.");
}
```

When the size of a function implementation grows beyond several lines of
code, such a code layout may not be easy to read. It may obscure the
overall code structure. To avoid the issue, it is possible to move the
function implementation into a method and then refer to this method in
the lambda expression. For example, let\'s add one static and one
instance method to the class where the lambda expression is used:


```
private int generateInput(){
    // Maybe many lines of code here
    return 3;
}
private static boolean checkValue(double d){
    // Maybe many lines of code here
    return d < 5;
}
```

Also, to demonstrate the variety of possibilities, let\'s create another
class with one static method and one instance method:


```
class Helper {
    public double calculate(int i){
        // Maybe many lines of code here
        return i* 5; 
    }
    public static void printResult(double d){
        // Maybe many lines of code here
        System.out.println("Result: " + d);
    }
}
```

Now we can rewrite our last example as follows:


```
Supplier<Integer> input = () -> generateInput();
Predicate<Integer> checkValue = d -> checkValue(d);
Function<Integer, Double> calculate = i -> new Helper().calculate(i);
Consumer<Double> printResult = d -> Helper.printResult(d);

if(checkValue.test(input.get())){
    printResult.accept(calculate.apply(input.get()));
} else {
    System.out.println("Input " + input.get() + " is too small.");
}
```

As you can see, even if each function consists of many lines of code,
such a structure keeps the code easy to read. Yet, when a one-line
lambda expression consists of a reference to an existing method, it is
possible to further simplify the notation by using method reference
without listing the parameters.

The syntax of the method reference is [Location::methodName],
where [Location] indicates in which object or class
the [methodName] method belongs, and the two colons ([::])
serve as a separator between the location and the method name. Using
method reference notation, the preceding example can be rewritten as
follows:


```
Supplier<Integer> input = this::generateInput;
Predicate<Integer> checkValue = MethodReferenceDemo::checkValue;
Function<Integer, Double> calculate = new Helper()::calculate;
Consumer<Double> printResult = Helper::printResult;

if(checkValue.test(input.get())){
    printResult.accept(calculate.apply(input.get()));
} else {
    System.out.println("Input " + input.get() + " is too small.");
}
```

You have probably noticed that we have intentionally used different
locations, two instance methods, and two static methods in order to
demonstrate the variety of possibilities. If it feels like too much to
remember, the good news is that a modern IDE (IntelliJ IDEA is one
example) can do it for you and convert the code you are writing to the
most compact form. You have just to accept with the IDE\'s suggestion.


Summary
======================

This lab introduced the reader to functional programming by
explaining and demonstrating the concept of functional interface and
lambda expressions. The overview of standard functional interfaces that
comes with JDK helps the reader to avoid writing custom code, while the
method reference notation allows the reader to write well-structured
code that is easy to understand and maintain.

In the next lab, we will talk about data streams processing. We will
define what data streams are, and look at how to process their data and
how to chain stream operations in a pipeline. Specifically, we will
discuss the stream\'s initialization and operations (methods), how to
connect them in a fluent style, and how to create parallel streams.
