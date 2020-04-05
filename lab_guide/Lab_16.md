<img align="right" src="../logo-small.png">


Best Practices for Writing High-Quality Code 
============================================

When programmers talk to each other, they often use jargon that cannot
be understood by non-programmers, or vaguely understood by the
programmers of different programming languages. But those who use the
same programming language understand each other just fine. Sometimes it
may also depend on how knowledgeable a programmer is. A novice may not
understand what an experienced programmer is talking about, while a
seasoned colleague nods and responds in kind. 

In this chapter, readers will be introduced to some Java programming
jargon---the Java idioms that describe certain features, functionality,
design solutions, and so on. The reader will also learn the most popular
and useful practices of designing and writing application code. By the
end of this chapter, the reader will have a solid understanding of what
other Java programmers are talking about while discussing their design
decisions and the functionalities they use.

The following topics will be covered in this chapter:

-   Java idioms, their implementation, and their usage
-   The [equals()], [hashCode()], [compareTo()], and
    [clone()] methods
-   The [StringBuffer] and [StringBuilder] classes
-   [try], [catch], and [finally] clauses
-   Best design practices
-   Code is written for people
-   Testing---the shortest path to quality code


<h3><span style="color:red;">Run Java Code</span></h3>

You can run the example by running following command in the terminal:
`java -cp target/learnjava-1.0.jar:target/libs/* com.lv.learnjava.ch18_bestpractices.Address`

<h3><span style="color:red;">Run Java Code</span></h3>

You can run the example by running following command in the terminal:
`java -cp target/learnjava-1.0.jar:target/libs/* com.lv.learnjava.ch18_bestpractices.EqualsDemo`

<h3><span style="color:red;">Run Java Code</span></h3>

You can run the example by running following command in the terminal:
`java -cp target/learnjava-1.0.jar:target/libs/* com.lv.learnjava.ch18_bestpractices.Person`

<h3><span style="color:red;">Run Java Code</span></h3>

You can run the example by running following command in the terminal:
`java -cp target/learnjava-1.0.jar:target/libs/* com.lv.learnjava.ch18_bestpractices.Person1`

<h3><span style="color:red;">Run Java Code</span></h3>

You can run the example by running following command in the terminal:
`java -cp target/learnjava-1.0.jar:target/libs/* com.lv.learnjava.ch18_bestpractices.Person2`

<h3><span style="color:red;">Run Java Code</span></h3>

You can run the example by running following command in the terminal:
`java -cp target/learnjava-1.0.jar:target/libs/* com.lv.learnjava.ch18_bestpractices.Person3`

<h3><span style="color:red;">Run Java Code</span></h3>

You can run the example by running following command in the terminal:
`java -cp target/learnjava-1.0.jar:target/libs/* com.lv.learnjava.ch18_bestpractices.Person4`

Java idioms, their implementation, and their usage
============================================

In addition to serving the means of communication among professionals,
programming idioms are also proven programming solutions and common
practices that are not directly derived from the language specification,
but born out of the programming experience. In this section, we are
going to discuss the ones that are used most often. You can find and
study the full list of idioms in the official Java documentation
(<https://docs.oracle.com/javase/tutorial>).

The equals() and hashCode() methods {#the-equals-and-hashcode-methods .header-title}
-----------------------------------

The default implementation of the [equals()] and
[hashCode()] methods in the [java.lang.Object] class looks
as follows:


```
public boolean equals(Object obj) {
    return (this == obj);
}
/**
* Whenever it is invoked on the same object more than once during
* an execution of a Java application, the hashCode method
* must consistently return the same integer...
* As far as is reasonably practical, the hashCode method defined
* by class Object returns distinct integers for distinct objects.
*/
@HotSpotIntrinsicCandidate
public native int hashCode();
```

As you can see, the default implementation of the [equals()]
method compares only memory references that point to the addresses where
the objects are stored. Similarly, as you can see from the comments
(quoted from the source code), the [hashCode()] method returns the
same integer for the same object and a different integer for different
objects. Let\'s demonstrate it using the [Person] class:


```
public class Person {
    private int age;
    private String firstName, lastName;
    public Person(int age, String firstName, String lastName) {
        this.age = age;
        this.lastName = lastName;
        this.firstName = firstName;
    }
    public int getAge() { return age; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
}
```

Here is an example of how the default [equals()] and
[hashCode()] methods behave:


```
Person person1 = new Person(42, "Nick", "Samoylov");
Person person2 = person1;
Person person3 = new Person(42, "Nick", "Samoylov");
System.out.println(person1.equals(person2)); //prints: true
System.out.println(person1.equals(person3)); //prints: false
System.out.println(person1.hashCode());      //prints: 777874839
System.out.println(person2.hashCode());      //prints: 777874839
System.out.println(person3.hashCode());      //prints: 596512129
```

The [person1] and [person2] references and their hash codes
are equal because they point to the same object (the same area of the
memory, and the same address), while the [person3] reference
points to another object.

In practice, though, as we have described in  *Data
Structures, Generics, and Popular Utilities*, we would like the equality
of the object to be based on the value of all or some of the object
properties, so here is a typical implementation of the [equals()]
and [hashCode()] methods:


```
@Override
public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null) return false;
    if(!(o instanceof Person)) return false;
    Person person = (Person)o;
    return getAge() == person.getAge() &&
            Objects.equals(getFirstName(), person.getFirstName()) &&
            Objects.equals(getLastName(), person.getLastName());
}
@Override
public int hashCode() {
    return Objects.hash(getAge(), getFirstName(), getLastName());
}
```

It used to be more involved, but using [java.util.Objects]
utilities makes it much easier, especially if you notice that the method
[Objects.equals()] method handles [null] too.

We have added the described implementation of [equals()] and
[hashCode()] methods to the [Person1] class and have
executed the same comparisons:


```
Person1 person1 = new Person1(42, "Nick", "Samoylov");
Person1 person2 = person1;
Person1 person3 = new Person1(42, "Nick", "Samoylov");
System.out.println(person1.equals(person2)); //prints: true
System.out.println(person1.equals(person3)); //prints: true
System.out.println(person1.hashCode());      //prints: 2115012528
System.out.println(person2.hashCode());      //prints: 2115012528
System.out.println(person3.hashCode());      //prints: 2115012528
```

As you can see, the change we have made not only makes the same objects
equal but makes equal two different objects with the same values of the
properties too. Furthermore, the hash code value is now based on the
values of the same properties as well.

In  *Data
Structures, Generics, and Popular Utilities*, we explained why it is
important to implement the [hasCode()] method while implementing
the [equals()] method.


It is very important that exactly the same set of properties is used for
establishing equality in the [equals()] method and for the hash
calculation in the [hashCode()] method.


Having the [\@Override] annotation in front of these methods
assures that they really override the default implementation in the
[Object] class. Otherwise, a typo in the method name may create
the illusion that the new implementation is used when in fact it is not.
Debugging such cases has proved much more difficult and costly than just
adding the [\@Override] annotation, which generates an error if
the method does not override anything.

The compareTo() method {#the-compareto-method .header-title}
----------------------

In  *Data
Structures, Generics, and Popular Utilities*, we used
the [compareTo()] method (the only method of the
[Comparable] interface) extensively and pointed out that the order
that is established based on this method (its implementation by the
elements of a collection) is called a **natural order**.

To demonstrate it, we created the [Person2] class:


```
public class Person2 implements Comparable<Person2> {
    private int age;
    private String firstName, lastName;
    public Person2(int age, String firstName, String lastName) {
        this.age = age;
        this.lastName = lastName;
        this.firstName = firstName;
    }
    public int getAge() { return age; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    @Override
    public int compareTo(Person2 p) {
        int result = Objects.compare(getFirstName(), 
                     p.getFirstName(), Comparator.naturalOrder());
        if (result != 0) {
            return result;
        }
        result = Objects.compare(getLastName(), 
                      p.getLastName(), Comparator.naturalOrder());
        if (result != 0) {
            return result;
        }
        return Objects.compare(age, p.getAge(), 
                                      Comparator.naturalOrder());
    }
    @Override
    public String toString() {
        return firstName + " " + lastName + ", " + age;
    }
}
```

Then we composed a list of [Person2] objects and sorted it:


```
Person2 p1 = new Person2(15, "Zoe", "Adams");
Person2 p2 = new Person2(25, "Nick", "Brook");
Person2 p3 = new Person2(42, "Nick", "Samoylov");
Person2 p4 = new Person2(50, "Ada", "Valentino");
Person2 p6 = new Person2(50, "Bob", "Avalon");
Person2 p5 = new Person2(10, "Zoe", "Adams");
List<Person2> list = new ArrayList<>(List.of(p5, p2, p6, p1, p4, p3));
Collections.sort(list);
list.stream().forEach(System.out::println); 
```

The result looks as follows:

![]./images_16/b6365f22-331c-40fb-b118-4a93037468a6.png)

There are three things worth noting:

-   According to the [Comparable] interface,
    the [compareTo()] method must return a negative integer, zero,
    or a positive integer as the object is less than, equal to, or
    greater than another object. In our implementation, we returned the
    result immediately if the values of the same property of two objects
    were different. We know already that this object is *bigger* or
    *smaller* no matter what the other properties are. But the sequence,
    in which you compare the properties of two objects, has an effect on
    the final result. It defines the precedence in which the property
    value affects the order.
-   We have put the result of [List.of()] into a [new
    ArrayList()] object. We did so because, as we have mentioned
    already in [Chapter
    6] *Data
    Structures, Generics, and Popular Utilities*, the collection created
    by a factory method [of()] is unmodifiable. No elements can be
    added or removed from it and the order of the elements cannot be
    changed either, while we need to sort the created collection. We
    used the [of()] method, only because it is more convenient and
    provides a shorter notation. 
-   Finally, using [java.util.Objects] for properties comparison
    makes the implementation much easier and more reliable than custom
    coding.

While implementing the [compareTo()] method, it is important to
make sure that the following rules are not violated:

-   [obj1.compareTo(obj2)] returns the same value
    as [obj2.compareTo(obj1)] only when the returned value is
    [0].
-   If the returned value is not [0], [obj1.compareTo(obj2)]
    has the opposite sign of [obj2.compareTo(obj1)].
-   If [obj1.compareTo(obj2) \> 0] and [obj2.compareTo(obj3) \>
    0] then [obj1.compareTo(obj3) \> 0].
-   If [obj1.compareTo(obj2) \< 0] and [obj2.compareTo(obj3) \<
    0] then [obj1.compareTo(obj3) \< 0].
-   If [obj1.compareTo(obj2) ==
    0] then [obj2.compareTo(obj3)] and [obj1.compareTo(obj3) \>
    0] have the same sign.
-   Both [obj1.compareTo(obj2)] and [obj2.compareTo(obj1)] throw
    the same exceptions, if any.

It is also recommended, but not always required, that
if [obj1.equals(obj2)] then [obj1.compareTo(obj2) == 0] and,
at the same time, if [obj1.compareTo(obj2) ==
0] then [obj1.equals(obj2)].

The clone() method {#the-clone-method .header-title}
------------------

The [clone()] method implementation in the
[java.lang.Object] class looks like this:


```
@HotSpotIntrinsicCandidate
protected native Object clone() throws CloneNotSupportedException;
```

The comment states the following:


```
/**
 * Creates and returns a copy of this object.  The precise meaning
 * of "copy" may depend on the class of the object.
 ***
```

The default result of this method returns a copy of the object fields as
is, which is fine if the values are of primitive types. However, if an
object property holds a reference to another object, only the reference
itself will be copied, not the referred object itself. That is why such
a copy is called a **shallow copy**. To get a **deep copy**, one has
to override the [clone()] method and clone each of the object
properties that refers an object. 

In any case, to be able to clone an object, it has to implement
the [Cloneable] interface and make sure that all the objects along
the inheritance tree (and the properties that are objects) implement
the [Cloneable] interface too (except the [java.lang.Object]
class). The [Cloneable] interface is just a marker interface that
tells the compiler that the programmer made a conscious decision to
allow this object to be cloned (whether because the shallow copy was
good enough or because the [clone()] method was overridden). An
attempt to call [clone()] on an object that does not implement
the [Cloneable] interface will result in
a [CloneNotSupportedException].

It looks complex already, but in practice, there are even more pitfalls.
For example, let\'s say that the [Person] class has
an [address] property of type [Address] class. The shallow
copy [p2] of the [Person] object [p1] will refer the
same object of [Address] so that [p1.address == p2.address].
Here is an example. The [Address] class looks as follows:


```
class Address {
    private String street, city;
    public Address(String street, String city) {
        this.street = street;
        this.city = city;
    }
    public void setStreet(String street) { this.street = street; }
    public String getStreet() { return street; }
    public String getCity() { return city; }
}
```

The [Person3] class uses it like this:


```
class Person3 implements Cloneable{
    private int age;
    private Address address;
    private String firstName, lastName;

    public Person3(int age, String firstName, 
                             String lastName, Address address) {
        this.age = age;
        this.address = address;
        this.lastName = lastName;
        this.firstName = firstName;
    }
    public int getAge() { return age; }
    public Address getAddress() { return address; }
    public String getLastName() { return lastName; }
    public String getFirstName() { return firstName; }
    @Override
    public Person3 clone() throws CloneNotSupportedException{
        return (Person3) super.clone();
    }
}
```

Notice that the method clone does a shallow copy because it does not
clone the [address] property. Here is the result of using such
a [clone()] method implementation:


```
Person3 p1 = new Person3(42, "Nick", "Samoylov",
                             new Address("25 Main Street", "Denver"));
Person3 p2 = p1.clone();
System.out.println(p1.getAge() == p2.getAge());                // true
System.out.println(p1.getLastName() == p2.getLastName());      // true
System.out.println(p1.getLastName().equals(p2.getLastName())); // true
System.out.println(p1.getAddress() == p2.getAddress());        // true
System.out.println(p2.getAddress().getStreet());  //prints: 25 Main Street
p1.getAddress().setStreet("42 Dead End");
System.out.println(p2.getAddress().getStreet());  //prints: 42 Dead End
```

As you can see, after the cloning is complete, the change made to the
[address] property of the source object is reflected in the same
property of the clone. That isn\'t very intuitive, is it? While cloning
we expected independent copy, didn\'t we? 

To avoid sharing the [Address] object, one needs to clone it
explicitly too. In order to do it, one has to make the [Address]
object cloneable, as follows:


```
public class Address implements Cloneable{
    private String street, city;
    public Address(String street, String city) {
        this.street = street;
        this.city = city;
    }
    public void setStreet(String street) { this.street = street; }
    public String getStreet() { return street; }
    public String getCity() { return city; }
    @Override
    public Address clone() throws CloneNotSupportedException {
        return (Address)super.clone();
    }
}
```

With that implementation in place, we can now add the [address]
property cloning:


```
class Person4 implements Cloneable{
    private int age;
    private Address address;
    private String firstName, lastName;
    public Person4(int age, String firstName, 
                             String lastName, Address address) {
        this.age = age;
        this.address = address;
        this.lastName = lastName;
        this.firstName = firstName;
    }
    public int getAge() { return age; }
    public Address getAddress() { return address; }
    public String getLastName() { return lastName; }
    public String getFirstName() { return firstName; }
    @Override
    public Person4 clone() throws CloneNotSupportedException{
        Person4 cl = (Person4) super.clone();
        cl.address = this.address.clone();
        return cl;
    }
}
```

Now, if we run the same test, the results are going to be as we expected
them originally:


```
Person4 p1 = new Person4(42, "Nick", "Samoylov",
        new Address("25 Main Street", "Denver"));
Person4 p2 = p1.clone();
System.out.println(p1.getAge() == p2.getAge());                // true
System.out.println(p1.getLastName() == p2.getLastName());      // true
System.out.println(p1.getLastName().equals(p2.getLastName())); // true
System.out.println(p1.getAddress() == p2.getAddress());        // false
System.out.println(p2.getAddress().getStreet()); //prints: 25 Main Street
p1.getAddress().setStreet("42 Dead End");
System.out.println(p2.getAddress().getStreet()); //prints: 25 Main Street
```

So, if the application expects all the properties to be deeply copied,
all the objects involved have to be cloneable. That is fine as long as
none of the related objects, whether a property in the current object or
in the parent class (and their properties and parents), do not acquire a
new object property without making them cloneable and are cloned
explicitly in the [clone()] method of the container object. This
last statement is complex. And the reason for its complexity is the
underlying complexity of the cloning process. That is why programmers
often stay away from making objects cloneable.

Instead, they prefer to clone the object manually, if need be. For
example:


```
Person4 p1 = new Person4(42, "Nick", "Samoylov",
                              new Address("25 Main Street", "Denver"));
Address address = new Address(p1.getAddress().getStreet(), 
                                            p1.getAddress().getCity());
Person4 p2 = new Person4(p1.getAge(), p1.getFirstName(), 
                                            p1.getLastName(), address);
System.out.println(p1.getAge() == p2.getAge());                // true
System.out.println(p1.getLastName() == p2.getLastName());      // true
System.out.println(p1.getLastName().equals(p2.getLastName())); // true
System.out.println(p1.getAddress() == p2.getAddress());        // false
System.out.println(p2.getAddress().getStreet()); //prints: 25 Main Street
p1.getAddress().setStreet("42 Dead End");
System.out.println(p2.getAddress().getStreet()); //prints: 25 Main Street
```

This approach still requires code changes if another property is added
to any related object. However, it provides more control over the result
and has less chance of unexpected consequences.

Fortunately, the [clone()] method is not used very often. In fact,
you may never encounter a need to use it.

The StringBuffer and StringBuilder classes {#the-stringbuffer-and-stringbuilder-classes .header-title}
------------------------------------------

We have talked about the difference between the [StringBuffer] and
[StringBuilder] classes in  *Data
Structures, Generics, and Popular Utilities.* We are not going to repeat
it here. Instead, we will just mention that, in a single-threaded
process (which is the vast majority of cases), the [StringBuilder]
class is a preferred choice because it is faster.

Try, catch, and finally clauses {#try-catch-and-finally-clauses .header-title}
-------------------------------

This book contains *Exception
Handling*, dedicated to the usage of [try], [catch],
and [finally] clauses, so we are not going to repeat it here. We
would like just to repeat again that using a try-with-resources
statement is a much-preferred way to release resources (traditionally
done in a [finally] block). Deferring to the library makes the
code simpler and more reliable.


Best design practices
============================================

The term *best* is often subjective and context dependent. That is why
we would like to disclose that the following recommendations are based
on the vast majority of cases in mainstream programming. However, they
should not be followed blindly and unconditionally because there are
cases when some of these practices in some contexts are useless or even
wrong. Before following them, try to understand the motivation behind
them and use it as your guide for your decisions. For example, size
matters. If the application is not going to grow beyond a few thousand
lines of code, a simple monolith with laundry-list style code is good
enough. But if there are complicated pockets of code and several people
working on it, breaking the code into specialized pieces would be
beneficial for code understanding, maintenance, and even scaling, if one
particular code area requires more resources than others.

We will start with higher-level design decisions in no particular order.

Identifying loosely coupled functional areas {#identifying-loosely-coupled-functional-areas .header-title}
--------------------------------------------

These design decisions can be made very early on, based just on the
general understanding of the main parts of the future system, their
functionality, and the data they produce and exchange. There are several
benefits of doing this:

-   An identification of the structure of the future system that has
    bearings on the further design steps and implementation
-   Specialization and deeper analysis of parts
-   Parallel development of parts
-   A better understanding of data flow

Breaking the functional area into traditional tiers {#breaking-the-functional-area-into-traditional-tiers .header-title}
---------------------------------------------------

With each functional area in place, there can be specializations based
on the technical aspects and technologies used. The traditional
separation of technical specialization is:

-   The frontend (user graphic or web interface)
-   The middle tier with extensive business logic
-   The backend (data storage or data source)

The benefits of doing this include the following:

-   Deployment and scaling by tiers
-   Programmer specialization based on their expertise
-   Parallel development of parts

Coding to an interface {#coding-to-an-interface .header-title}
----------------------

The specialized parts, based on the decisions described in the previous
two subsections, have to be described in an interface that hides the
implementation details. The benefits of such a design lie in the
foundations of object-oriented programming and are described in detail
in 
*Java Object-Oriented Programming (OOP)*, so we are not going to repeat
it here.

Using factories {#using-factories .header-title}
---------------

We talked about this
in *Java
Object-Oriented Programming (OOP)*, too. An interface, by definition,
does not and cannot describe the constructor of a class that implements
the interface. Using factories allows you to close this gap and expose
just an interface to a client. 

Preferring composition over inheritance {#preferring-composition-over-inheritance .header-title}
---------------------------------------

Originally, object-oriented programming was focused on inheritance as
the way to share the common functionality between objects. Inheritance
is even one of the four object-oriented programming principles as we
have described them in 
*Java Object-Oriented Programming (OOP)*. In practice, however, this
method of functionality sharing creates too much of the dependency
between classes included in the same inheritance line. The evolution of
application functionality is often unpredictable, and some of the
classes in the inheritance chain start to acquire functionality
unrelated to the original purpose of the class chain. We can argue that
there are design solutions that allow us not to do it, and keep the
original classes intact. But, in practice, such things happen all the
time, and the subclasses may suddenly change behavior just because they
acquired new functionality through inheritance. We cannot choose our
parents, can we? Besides, it breaks the encapsulation this way, while
encapsulation is another foundation principle of OOP.

Composition, on the other hand, allows us to choose and control which
functionality of the class to use and which to ignore. It also allows
the object to stay light and not be burdened by the inheritance. Such a
design is more flexible, more extensible, and more predictable.

Using libraries {#using-libraries .header-title}
---------------

Throughout the book, we mentioned many times that using the **Java Class
Library** (**JCL**) and external (to the **Java Development Kit**
(**JDK**) Java libraries makes programming much easier and produces a
code of higher quality. There is even a dedicated chapter,  *Java
Standard and External Libraries*, which contains an overview of the most
popular Java libraries. People who create libraries invest a lot of time
and effort, so you should take advantage of them any time you can.

In 
*Functional Programming*, we described standard functional interfaces
that reside in the [java.util.function] package of JCL. That is
another way to take advantage of a library---by using the set of
well-known and shared interfaces, instead of defining your own ones.

This last statement is a good segue to the next topic of this chapter
about writing code that is easily understood by other people.

Code is written for people
============================================

The first decades of programming required writing machine commands so
that electronic devices could execute them. Not only was it a tedious
and error-prone endeavor, but it also required you to write the
instructions in a manner that yielded the best performance possible
because the computers were slow and did not do much code optimization,
if at all.

Since then, we have made a lot of progress in both hardware and
programming. The modern compiler went a long way towards making the
submitted code work as fast as possible, even when a programmer did not
think about it.

It allowed programmers to write more lines of code without thinking much
about the optimization. But tradition and many books about programming
continued to call for it, and some programmers still worry about their
code performance, more so than the results it produces. It is easier to
follow the tradition than to break away from it. That is why programmers
tend to pay more attention to the way they write code than to the
business they automate, although a good code that implements an
incorrect business logic is useless.

However, back to the topic. With modern JVM, the need for code
optimization by a programmer is not as pressing as it used to be.
Nowadays, a programmer has to pay attention mostly to a big picture, to
avoid structural mistakes that lead to poor code performance and to code
that is used multiple times. The latter becomes less pressing as the JVM
becomes more sophisticated, observing the code in real time and just
returning the results (without execution) when the same code block is
called several times with the same input.

That leaves us with the only conclusion possible: while writing code,
one has to make sure it is easy to read and understand for a human, not
for a computer. Those who have worked in the industry for some time have
been puzzled over the code they have themselves written a few years
before. One improves the code-writing style via clarity and the
transparency of the intent.

We can discuss the need for comments until the cows are back in the
barn. We definitely do not need comments that literally echo what the
code does. For example:


```
//Initialize variable
int i = 0;
```

The comments that explain the intent are much more valuable:


```
// In case of x > 0, we are looking for a matching group 
// because we would like to reuse the data from the account.
// If we find the matching group, we either cancel it and clone,
// or add the x value to the group, or bail out.
// If we do not find the matching group,
// we create a new group using data of the matched group.
```

The commented code can be very complex. Good comments explain the intent
and provide guidance that helps us to understand the code. Yet
programmers often do not bother to write comments. The argument against
writing comments typically includes two statements:

-   Comments have to be maintained and evolve along with the code,
    otherwise, they may become misleading, but there is no tool that can
    prompt the programmer to adjust the comments along with changing the
    code. Thus, comments are dangerous.
-   The code itself has to be written so (including name selection for
    variables and methods) that no extra explanation is needed.

Both statements are true, but it is also true that comments can be very
helpful, especially those that capture the intent. Besides, such
comments tend to require fewer adjustments because the code intent
doesn\'t change often, if ever.

Testing is the shortest path to quality code
============================================

The last best practice we will discuss is this statement: *testing is
not an overhead or* *a burden; it is* *the programmer\'s guide to
success*. The only question is when to write the test. 

There is a compelling argument that requires writing a test before any
line of code is written. If you can do it, that is great. We are not
going to try and talk you out of it. But if you do not do it, try to
start writing a test after you have written one, or all lines of code,
you had been tasked to write.

In practice, many experienced programmers find it helpful to start
writing testing code after some of the new functionality is implemented,
because that is when the programmer understands better how the new code
fits into the existing context. They may even try and hard-code some
values to see how well the new code is integrated with the code that
calls the new method. After making sure the new code is well integrated,
the programmer can continue implementing and tuning it all the time
testing the new implementation against the requirements in the context
of the calling code.

One important qualification has to be added: while writing the test, it
is better if the input data and the test criteria are set not by you,
but by a person who assigned you the task or the tester. Setting the
test according to the results the code produces is a well-known
programmer\'s trap. Objective self-assessment is not easy, if possible
at all. 

Summary
============================================

In this chapter, we have discussed the Java idioms that a mainstream
programmer encounters daily. We have also discussed the best design
practices and related recommendations, including code-writing style and
testing.

In this chapter, readers have learned about the most popular Java idioms
related to certain features, functionalities, and design solutions. The
idioms were demonstrated with practical examples, and readers have
learned how to incorporate them into their code and the professional
language they use to communicate with other programmers. 

In the next chapter, we will introduce the reader to four projects that
add new features to Java: Panama, Valhalla, Amber, and Loom. We hope it
will help the reader to follow the Java development and envision the
roadmap of future releases.
