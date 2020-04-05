<img align="right" src="../logo-small.png">


Java Object-Oriented Programming (OOP)
======================================

**Object-Oriented Programming** (**OOP**) was born out of the necessity
for better control over concurrent modification of the shared data,
which was the curse of pre-OOP programming. The core of the idea was not
to allow direct access to the data but to do it only through the
dedicated layer of code. Since the data needs to be passed around and
modified in the process, the concept of an object was conceived. In the
most general sense, an *object* is a set of data that can be passed
around and accessed only through the set of methods passed along too.
This data is said to compose an **object state**, while the methods
constitute the **object behavior**. The object state is hidden
(**encapsulated**) from direct access.

Each object is constructed based on a certain template called a
**class***. *In other words, a class defines a class of objects. Each
object has a certain **interface**, a formal definition of the way
other objects can interact with it. Originally, it was said that one
object sends a message to another object by calling its method. But this
terminology did not hold, especially after actual message-based
protocols and systems were introduced.

To avoid code duplication, a parent-child relationship between objects
was introduced: it was said that one class can inherit behavior from
another class. In such a relationship, the first class is called a
**child class**, or a **subclass**, while the second is called a
**parent** or **base class** or **superclass**.

Another form of relationship was defined between classes and interfaces:
it is said that a class can *implement* an interface. Since an interface
describes how you can interact with an object, but not how an object
responds to the interaction, different objects can behave differently
while implementing the same interface.

In Java, a class can have only one direct parent but can implement many
interfaces. The ability to behave as any of its ancestors and adhere to
multiple interfaces is called **polymorphism**.

In this chapter, we will look at these OOP concepts and how they are
implemented in Java. The topics discussed include the following:

-   OOP concepts
-   Class
-   Interface
-   Overloading, overriding, and hiding
-   Final variable, method, and class
-   Polymorphism in action


### Run Java Code
You can run the example by running following command in the terminal:
`java -cp target/learnjava-1.0.jar com.lv.learnjava.ch02_oop.Constructor.java`


### Run Java Code
You can run the example by running following command in the terminal:
`java -cp target/learnjava-1.0.jar com.lv.learnjava.ch02_oop.Final.java`


### Run Java Code
You can run the example by running following command in the terminal:
`java -cp target/learnjava-1.0.jar com.lv.learnjava.ch02_oop.hiding.C.java`


### Run Java Code
You can run the example by running following command in the terminal:
`java -cp target/learnjava-1.0.jar com.lv.learnjava.ch02_oop.hiding.D.java`


### Run Java Code
You can run the example by running following command in the terminal:
`java -cp target/learnjava-1.0.jar com.lv.learnjava.ch02_oop.hiding.HidingProperty.java`


### Run Java Code
You can run the example by running following command in the terminal:
`java -cp target/learnjava-1.0.jar com.lv.learnjava.ch02_oop.Hiding.java`

### Run Java Code
You can run the example by running following command in the terminal:
`java -cp target/learnjava-1.0.jar com.lv.learnjava.ch02_oop.Instanceof.java`

### Run Java Code
You can run the example by running following command in the terminal:
`java -cp target/learnjava-1.0.jar com.lv.learnjava.ch02_oop.InterfaceExamples.java`

### Run Java Code
You can run the example by running following command in the terminal:
`java -cp target/learnjava-1.0.jar com.lv.learnjava.ch02_oop.Overloading.java`

### Run Java Code
You can run the example by running following command in the terminal:
`java -cp target/learnjava-1.0.jar com.lv.learnjava.ch02_oop.Overriding.java`

### Run Java Code
You can run the example by running following command in the terminal:
`java -cp target/learnjava-1.0.jar com.lv.learnjava.ch02_oop.Polymorphism1.java`

### Run Java Code
You can run the example by running following command in the terminal:
`java -cp target/learnjava-1.0.jar com.lv.learnjava.ch02_oop.Polymorphism2.java`

### Run Java Code
You can run the example by running following command in the terminal:
`java -cp target/learnjava-1.0.jar com.lv.learnjava.ch02_oop.StaticMembers.java`

### Run Java Code
You can run the example by running following command in the terminal:
`java -cp target/learnjava-1.0.jar com.lv.learnjava.ch02_oop.Varargs.java`


OOP concepts
======================================

As we have already stated in the introduction, the main OOP concepts are
as follows:

-   **Object/Class**: It defines a state (data) and behavior (methods)
    and holds them together
-   **Inheritance**: It propagates behavior down the chain of classes
    connected via parent-child relationships
-   **Abstraction/Interface**: It describes how the object data and
    behavior can be accessed. It isolates (abstracts) an object's
    appearance from its implementations (behavior)
-   **Encapsulation**: It hides the state and details of the
    implementation
-   **Polymorphism**: It allows an object to assume an appearance of
    implemented interfaces and behave as any of the ancestor classes

Object/class
------------

In principle, you can create a very powerful application with minimal
usage of classes and objects. It became even easier to do this after
functional programming was added to Java 8, to JDK, which allowed you to
pass around behavior as a function. Yet passing data (state) still
requires classes/objects. This means that the position of Java as an OOP
language remains intact.

A class defines the types of all internal object properties that hold
the object state. A class also defines object behavior expressed by
the code of the methods. It is possible to have a class/object without a
state or without a behavior. Java also has a provision for making the
behavior accessible statically—without creating an object. But these
possibilities are no more than just additions to the object/class
concept that was introduced for keeping the state and behavior together.

To illustrate this concept, a class Vehicle, for example, defines the
properties and behavior of a vehicle in principle. Let's make the model
simple and assume that a vehicle has only two properties: weight and
engine of a certain power. It also can have a certain behavior: it can
reach a certain speed in a certain period of time, depending on the
values of its two properties. This behavior can be expressed in a method
that calculates the speed the vehicle can reach in a certain period of
time. Every object of the Vehicle class will have a specific state
(values of its properties) and the speed calculation will result in a
different speed in the same time period. 

All Java code is contained inside methods. A **method** is a group of
statements that have (optional) input parameters and a return a value
(also optional). In addition, each method can have side effects: it can
display a message or write data into the database, for example.
Class/object behavior is implemented in the methods.

To follow our example, speed calculations could reside in
a double calculateSpeed(float seconds) method, for instance. As you can
guess, the name of the method is calculateSpeed. It accepts a number of
seconds (with a fractional part) as a parameter and returns the speed
value as double.

Inheritance
-----------

As we have mentioned already, objects can establish a parent-child
relationship and share properties and behavior this way. For example, we
can create a Car class that inherits properties (weight, for example)
and behavior (speed calculation) of the Vehicle class. In addition, the
child class can have its own properties (number of passengers, for
example) and car-specific behavior (soft shock absorption, for example).
But if we create a Truck class as the vehicle's child, its additional
truck-specific property (payload, for example) and behavior (hard shock
absorption) will be different.

It is said that each object of the Car class or of the Truck class has a
parent object of the Vehicle class. But objects of the Car and
Truck class do not share the specific Vehicle object (every time a child
object is created, a new parent object is created first). They share
only the parent's behavior. That is why all the child objects can have
the same behavior but different states. That is one way to achieve code
reusability. It may not be flexible enough when the object behavior has
to change dynamically. In such cases, object composition (bringing
behavior from other classes) or functional programming would be more
appropriate (see [Chapter
13](https://subscription.packtpub.com/book/programming/9781789957051/13),
*Functional Programming*).

It is possible to make a child behave differently than the inherited
behavior would do. To achieve it, the method that captures the behavior
can be re-implemented in the child class. It is said that a child
can *override* the inherited behavior. We will explain how to do it
shortly (see the *Overloading, overriding, and hiding* section). If, for
example, the Car class has its own method for speed calculation, the
corresponding method of the parent class Vehicle is not inherited, but
the new speed calculation, implemented in the child class, is used
instead.

Properties of a parent class can be inherited (but not overridden) too.
However, class properties are typically declared private; they cannot be
inherited—that's the point of encapsulation. See the description of
various access levels—public, protected, and private—in the *Access
modifiers* section.

If the parent class inherits some behavior from another class, the child
class acquires (inherits) this behavior too, unless, of course, the
parent class overrides it. There is no limit to how long the chain of
inheritance can be.

The parent-child relationship in Java is expressed using
the extends keyword:

```
class A { }
class B extends A { }
class C extends B { }
class D extends C { }
```

In this code, the classes A, B, C, and D have the following
relationships:

-   Class D inherits from classes C, B, and A
-   Class C inherits from classes B and A
-   Class B inherits from class A

All non-private methods of class A are inherited (if not overridden) by
classes B, C, and D. 

Abstraction/interface
---------------------

The name of a method and the list of its parameter types is called a
**method signature***. *It describes how the behavior of an object (of
Car or Truck, in our example) can be accessed. Such a description
together with a return type is presented as an interface. It does not
say anything about the code that does calculations—only about the method
name, parameters' types, their position in the parameter list, and the
result type. All the implementation details are hidden (encapsulated)
within the class that *implements* this interface.

As we have mentioned already, a class can implement many different
interfaces. But two different classes (and their objects) can behave
differently even when they implement the same interface. 

Similarly to classes, interfaces can have a parent-child relationship
using the extends keyword too:

```
interface A { }
interface B extends A {}
interface C extends B {}
interface D extends C {}
```

In this code, the interfaces A, B, C, and D have the following
relationships:

-   An Interface D inherits from interfaces C, B, and A
-   An Interface C inherits from interfaces B and A
-   An Interface B inherits from interface A

All non-private methods of interface A are inherited by interfaces B, C,
and D. 

Abstraction/interface also reduces dependency between different sections
of the code, thus increasing its maintainability. Each class can be
changed without the need to coordinate it with its clients, as long as
the interface stays the same.

Encapsulation
-------------

**Encapsulation** is often defined either as a data hiding or a bundling
together of publicly accessible methods and privately accessible data.
In a broad sense, encapsulation is a controlled access to the object
properties. 

The snapshot of values of object properties is called an
**object state.** The object state is the data that is encapsulated. So,
encapsulation addresses the main issue that motivated the creation of
object-oriented programming: better management of concurrent access to
the shared data. For example:

```
class A {
  private String prop = "init value";
  public void setProp(String value){
     prop = value;
  }
  public String getProp(){
     return prop;
  }
}
```

As you can see, to read or to modify the value of the prop property, we
cannot access it directly because of the access modifier private.
Instead, we can do it only via the methods setProp(String value) and
getProp().

Polymorphism
------------

**Polymorphism** is the ability of an object to behave as an object of
a different class or as an implementation of a different interface. It
owes its existence to all the concepts that have been mentioned
previously: inheritance, interface, and encapsulation. Without them,
polymorphism would not be possible. 

Inheritance allows an object to acquire or override the behaviors of all
its ancestors. An interface hides from the client code the name of the
class that implemented it. The encapsulation prevents exposing the
object state. 

In the following sections, we will demonstrate all these concepts in
action and look at the specific usage of polymorphism in the
*Polymorphism in action* section.


Class
======================================

Java program is a sequence of statements that express an executable
action. The statements are organized in methods, and methods are
organized in classes. One or more classes are stored in .java
files. They can be compiled (transformed from the Java language into a
bytecode) by the Java compiler javac and stored in .class files. Each
.class file contains one compiled class only and can be executed by JVM.

A java command starts JVM and tells it which class is the main one, the
class that has the method called main(). The main method has a
particular declaration: it has to be public static, must return void,
has the name main, and accepts a single parameter of an array of
a String type.

JVM *loads* the main class into memory, finds the main() method, and
starts executing it, statement by statement. The java command can also
pass parameters (arguments) that the main() method receives as an array
of String values. If JVM encounters a statement that requires the
execution of a method from another class, that class (its .class file)
is loaded into the memory too and the corresponding method is
executed. So, a Java program flow is all about loading classes and
executing their methods.

Here is an example of the main class:

```
public class MyApp {
  public static void main(String[] args){
     AnotherClass an = new AnotherClass();
     for(String s: args){
        an.display(s);
     }
   }
}
```

It represents a very simple application that receives any number of
parameters and passes them, one by one, into the display()
method of AnotherClass class. As JVM starts, it loads the MyApp
class from the MyApp.class file first. Then it loads
the AnotherClass class from the AnotherClass.class file, creates an
object of this class using the new operator (we will talk about it
shortly), and calls on it the display() method.

And here is the AnotherClass class:

```
public class AnotherClass {
   private int result;
   public void display(String s){
      System.out.println(s);
   }
   public int process(int i){
      result = i *2;
      return result;
   }
   public int getResult(){
      return result;
   }
} 
```

As you can see, the display() method is used for its side effect only—it
prints out the passed-in value and returns nothing (void).
The AnotherClass class has other two methods:

-   The process() method doubles the input integer, stores it in
    its result property, and returns the value to the caller
-   The getResult() method allows getting the result from the object any
    time later

These two methods are not used in our demo application. We have shown
them just for the demonstration that a class can have properties
(result, in this case) and many other methods.

The private keyword makes the value accessible only from inside the
class, from its methods. The public keyword makes a property or a method
accessible by any other class.

Method
------

As we have stated already, Java statements are organized as methods:

```
<return type> <method name>(<list of parameter types>){
     <method body that is a sequence of statements>
}
```

We have seen a few examples already. A method has a name, a set of input
parameters or no parameters at all, a body inside {} brackets, and a
return type or void keyword that indicates that the method does not
return any value.

The method name and the list of parameter types together are called
the **method signature**. The number of input parameters is called
an **arity**.

Two methods have the same *signature* if they have the same name, the
same arity, and the same sequence of types in the list of input
parameters.

The following two methods have the same signature:

```
double doSomething(String s, int i){
    //some code goes here
}

double doSomething(String i, int s){
    //some code other code goes here
}
```

The code inside the methods may be different even if the signature is
the same. 

The following two methods have different signatures:

```
double doSomething(String s, int i){
    //some code goes here
}

double doSomething(int s, String i){
    //some code other code goes here
}
```

Just a change in the sequence of parameters makes the signature
different, even if the method name remains the same.

Constructor
-----------

When an object is created, JVM uses a **constructor**. The purpose of a
constructor is to initialize the object state to assign values to all
the declared properties. If there is no constructor declared in the
class, JVM just assigns to the properties default values. We have talked
about the default values for primitive types: it is 0 for integral
types, 0.0 for floating-point types, and false for boolean types. For
other Java reference types (see [Chapter
3](https://subscription.packtpub.com/book/programming/9781789957051/3),
*Java Fundamentals), *the default value is null, which means that the
property of a reference type is not assigned any value.

When there is no constructor declared in a class, it is said that the
class has a default constructor without parameters provided by the JVM.

If necessary, it is possible to declare any number of
constructors explicitly, each taking a different set of parameters to
set the initial state. Here is an example:

```
class SomeClass {
     private int prop1;
     private String prop2;
     public SomeClass(int prop1){
         this.prop1 = prop1;
     }
     public SomeClass(String prop2){
         this.prop2 = prop2;
     }
     public SomeClass(int prop1, String prop2){
         this.prop1 = prop1;
         this.prop2 = prop2;
     }   
     // methods follow 
}
```

If a property is not set by a constructor, the default value of the
corresponding type is going to be assigned to it automatically.

When several classes are related along the same line of succession, the
parent object is created first. If the parent object requires
the setting of non-default initial values to its properties, its
constructor must be called as the first line of the child constructor
using the super keyword as follows:

```
class TheParentClass {
    private int prop;
    public TheParentClass(int prop){
        this.prop = prop;
    }
    // methods follow
}

class TheChildClass extends TheParentClass{
 private int x;
 private String prop;
 private String anotherProp = "abc";
 public TheChildClass(String prop){
 super(42);
 this.prop = prop;
 }
 public TheChildClass(int arg1, String arg2){
 super(arg1);
 this.prop = arg2;
 }
 // methods follow
}
```

In the preceding code example, we added two constructors to
TheChildClass: one that always passes 42 to the constructor of
TheParentClass and another that accepts two parameters. Note the x
property that is declared but not initialized explicitly. It is going to
be set to value 0, the default value of the int type, when an object of
TheChildClass is created. Also, note the anotherProp property that is
initialized explicitly to the value of "abc". Otherwise, it would be
initialized to the value null, the default value of any reference type,
including String.

Logically, there are three cases when an explicit definition of a
constructor in the class is not required:

-   When neither the object nor any of its parents do not have
    properties that need to be initialized
-   When each property is initialized along with the type declaration
    (int x = 42, for example)
-   When default values for the properties initialization are good
    enough 

Nevertheless, it is possible that a constructor is still implemented
even when all three conditions (mentioned in the list) are met. For
example, you may want to execute some statements that initialize some
external resource—a file or another database—the object will need as
soon as it is created.

As soon as an explicit constructor is added, the default constructor is
not provided and the following code generates an error:

```
class TheParentClass {
    private int prop;
    public TheParentClass(int prop){
        this.prop = prop;
    }
    // methods follow
}

class TheChildClass extends TheParentClass{
    private String prop;
    public TheChildClass(String prop){
        //super(42);       //No call to the parent's contuctor
        this.prop = prop;
    }
    // methods follow
}
```

To avoid the error, either add a constructor without parameters to
TheParentClass or call an explicit constructor of the parent class as
the first statement of the child's constructor. The following code does
not generate an error:

```
class TheParentClass {
    private int prop;
    public TheParentClass() {}
    public TheParentClass(int prop){
        this.prop = prop;
    }
    // methods follow
}

class TheChildClass extends TheParentClass{
    private String prop;
    public TheChildClass(String prop){
        this.prop = prop;
    }
    // methods follow
}
```

One important aspect to note is that constructors, although they look
like methods, are not methods or even members of the class. A
constructor doesn’t have a return type and always has the same name as
the class. Its only purpose is to be called when a new instance of the
class is created.

The new operator
----------------

The new operator creates an object of a class (it also can be said
it **instantiates a class** or **creates an instance of a class**) by
allocating memory for the properties of the new object and returning a
reference to that memory. This memory reference is assigned to a
variable of the same type as the class used to create the object or the
type of its parent:

```
TheChildClass ref1 = new TheChildClass("something"); 
TheParentClass ref2 = new TheChildClass("something");
```

Here is an interesting observation. In the code, both object references
ref1 and ref2, provide access to the methods
of TheChildClass and TheParentClass. For example, we can add methods to
these classes as follows:

```
class TheParentClass {
    private int prop;
    public TheParentClass(int prop){
        this.prop = prop;
    }
    public void someParentMethod(){}
}

class TheChildClass extends TheParentClass{
    private String prop;
    public TheChildClass(int arg1, String arg2){
        super(arg1);
        this.prop = arg2;
    }
    public void someChildMethod(){}
}
```

Then we can call them using any of the following references:

```
TheChildClass ref1 = new TheChildClass("something");
TheParentClass ref2 = new TheChildClass("something");
ref1.someChildMethod();
ref1.someParentMethod();
((TheChildClass) ref2).someChildMethod();
ref2.someParentMethod();
```

Note that, to access the child's methods using the parent's type
reference, we had to cast it to the child's type. Otherwise, the
compiler generates an error. That is possible because we have assigned
to the parent's type reference the reference to the child's object. That
is the power of polymorphism. We will talk more about it in
the *Polymorphism in action* section.

Naturally, if we had assigned the parent's object to the variable of the
parent's type, we would not be able to access the child's method even
with casting, as the following example shows:

```
TheParentClass ref2 = new TheParentClass(42);
((TheChildClass) ref2).someChildMethod();  //compiler's error
ref2.someParentMethod();
```

The area where memory for the new object is allocated is called
**heap**. The JVM has a process called **garbage collection** that
watches for the usage of this area and releases memory for usage as soon
as an object is not needed anymore. For example, look at the following
method:

```
void someMethod(){
   SomeClass ref = new SomeClass();
   ref.someClassMethod();
   //other statements follow
}
```

As soon as the execution of the someMethod() method is completed, the
object of SomeClass is not accessible anymore. That's what the garbage
collector notices and releases the memory occupied by this object. We
will talk about the garbage collection process in [Chapter
9](https://subscription.packtpub.com/book/programming/9781789957051/9),
*JVM Structure and Garbage Collection*.

Class java.lang.Object
----------------------

In Java, all classes are children of the Object class by default, even
if you do not specify it implicitly. The Object class is declared in
the java.lang package of the standard JDK library. We will define what
*package* is in the *Packages, importing, and access* section and
describe libraries in [Chapter
7](https://subscription.packtpub.com/book/programming/9781789957051/7),
*Java Standard and External Libraries*.

Let's look back at the example we have provided in
the *Inheritance* section:

```
class A { }
class B extends A {}
class C extends B {}
class D extends C {}3
```

All classes, A, B, C, D, are children of the Object class, which has ten
methods that every class inherits:

-   public String toString()
-   public int hashCode()
-   public boolean equals (Object obj)
-   public Class getClass()
-   protected Object clone()
-   public void notify()
-   public void notifyAll()
-   public void wait()
-   public void wait(long timeout)
-   public void wait(long timeout, int nanos)

The first three, toString(), hashCode(), and equals(), are the most
often used methods and often re-implemented (overridden).
The toString() method is typically used to print the state of the
object. Its default implementation in JDK looks like this:

```
public String toString() {
   return getClass().getName()+"@"+Integer.toHexString(hashCode());
}
```

If we use it on the object of the TheChildClass class, the result will
be as follows:

```
TheChildClass ref1 = new TheChildClass("something");
System.out.println(ref1.toString());  
//prints: com.lv.learnjava.ch02_oop.Constructor$TheChildClass@72ea2f77
```

By the way, there is no need to call toString() explicitly while passing
an object into the System.out.println() method and similar output
methods, because they do it inside the method anyway
and System.out.println(ref1), in our case, produces the same result.

So, as you can see, such an output is not human-friendly, so it is a
good idea to override the toString() method. The easiest way to do it is
by using IDE. For example, in IntelliJ IDEA, right-click
inside TheChildClass code, as shown in the following screenshot:

![](./images_2/90c56eda-e4bb-4a3d-9733-112af33bb1d7.png)

Select and click Generate... and then select and click toString(), as
shown in the following screenshot:

![](./images_2/d376a1b7-1c77-4496-97cd-637ac764f6b5.png)

The new pop-up window will enable you to select which properties to
include in the toString() method. Select only properties of
TheChildClass as follows:

![](./images_2/ce1c360c-7426-435f-bdbe-2a63534b8e42.png)

After you click the OK button, the following code will be generated:

```
@Override
public String toString() {
    return "TheChildClass{" +
            "prop='" + prop + '\'' +
            '}';
}
```

If there were more properties in the class and you had selected them,
more properties and their values would be included in the method output.
If we print the object now, the result will be this:

```
TheChildClass ref1 = new TheChildClass("something");
System.out.println(ref1.toString());  
                          //prints: TheChildClass{prop='something'}
```

That is why the toString() method is often overridden and even included
in the services of an IDE.

We will talk about the hashCode() and equals() methods in more detail in
[Chapter
6](https://subscription.packtpub.com/book/programming/9781789957051/6), *Data
Structures, Generics, and Popular Utilities*.

The getClass() and clone() methods are used not as often. The
getClass() method returns an object of the Class class that has many
methods which provide various system information. The most used method
is the one that returns the name of the class of the current object. The
clone() method can be used to copy the current object. It works just
fine as long as all the properties of the current object are of
primitive types. But, if there is a reference type property, the clone()
method has to be re-implemented so that the copy of the reference type
can be done correctly. Otherwise, only the reference will be copied, not
the object itself. Such a copy is called a **shallow copy**, which may
be good enough in some cases. The protected keyword indicates that only
children of the class can access it. See the *Packages, importing, and
access* section.

The last five of the class Object methods are used for communication
between threads—the lightweight processes for concurrent processing.
They are typically not re-implemented.

Instance and static properties and methods
------------------------------------------

So far, we have seen mostly methods that can be invoked only on an
object (instance) of a class. Such methods are called **instance
methods**. They typically use the values of the object properties (the
object state). Otherwise, if they do not use the object state, they can
be made static and invoked without creating an object. The example of
such a method is the main() method. Here is another example:

```
class SomeClass{
    public static void someMethod(int i){
        //do something
    }
}
```

This method can be called as follows:

```
SomeClass.someMethod(42);
```

Static methods can be called on an object too, but it is considered bad
practice as it hides the static nature of the method from a human who
tries to understand the code. Besides, it raises a compiler warning and,
depending on the compiler implementation, may even generate a compiler
error.

Similarly, a property can be declared static and thus accessible without
creating an object. For example:

```
class SomeClass{
    public static String SOME_PROPERTY = "abc";
}
```

This property can be accessed directly via class too, as follows:

```
System.out.println(SomeClass.SOME_PROPERTY);  //prints: abc
```

Having such a static property works against the idea of the state
encapsulation and may cause all the problems of concurrent data
modification because it exists as a single copy in JVM memory and all
the methods that use it, share the same value. That is why a static
property is typically used for two purposes:

-   To store a constant—a value that can be read but not modified (also
    called a **read-only value**)
-   To store a stateless object that is expensive to create or that
    keeps read-only values

A typical example of a constant is a name of a resource:

```
class SomeClass{
    public static final String INPUT_FILE_NAME = "myFile.csv";
}
```

Note the final keyword in front of the static property. It tells the
compiler and JVM that this value, once assigned, cannot change. An
attempt to do it generates an error. It helps to protect the value and
express clearly the intent to have this value as a constant. When a
human tries to understand how the code works, such seemingly small
details make the code easier to understand.

That said, consider using interfaces for such a purpose. Since Java 1.8,
all the fields declared in an interface are implicitly static and final,
so there is less chance you'll forget to declare a value to be final. We
will talk about interfaces shortly.

When an object is declared a static final class property, it does not
mean all its properties become final automatically. It only protects the
property from assigning another object of the same type. We will discuss
the complicated procedure of concurrent access of an object property
in [Chapter
8](https://subscription.packtpub.com/book/programming/9781789957051/8), *Multithreading
and Concurrent Processing*. Nevertheless, programmers often use static
final objects to store the values that are read-only just by the way
they are used in the application. A typical example would be an
application configuration information. Once created after reading from a
disk, it is not changed, even if it could be. Also, caching of data is
obtained from an external resource. 

Again, before using such a class property for this purpose, consider
using an interface that provides more default behavior that supports a
read-only functionality. 

Similar to static properties, static methods can be invoked without
creating an instance of the class. Consider, for example, the following
class:

```
class SomeClass{
    public static String someMethod() {
        return "abc";
    }
}
```

We can call the preceding method by using just a class name:

```
System.out.println(SomeClass.someMethod()); //prints: abc
```



Interface
======================================

In the *Abstraction/Interface* section*,* we talked about an interface
in general terms. In this section, we are going to describe a Java
language construct that expresses it. 

An **interface** presents what can be expected of an object. It hides
the implementation and exposes only method signatures with return
values. For example, here is an interface that declares two abstract
methods:

```
interface SomeInterface {
    void method1();
    String method2(int i);
}
```

And here is a class that implements it:

```
class SomeClass implements SomeInterface{
    public void method1(){
        //method body
    }
    public String method2(int i) {
        //method body
        return "abc";
    }
}
```

An interface cannot be instantiated. An object of an interface type can
be created only by creating an object of a class that *implements* this
interface:

```
SomeInterface si = new SomeClass(); 
```

If not all of the abstract methods of the interface have been
implemented, the class must be declared abstract and cannot be
instantiated. See the *Interface versus abstract class* section.

An interface does not describe how the object of the class can be
created. To discover that, you must look at the class and see what
constructors it has. An interface also does not describe the static
class methods. So, an interface is the public face of a class instance
(object) only.

With Java 8, interface acquired an ability to have not just abstract
methods (without a body), but really implemented ones. According to The
Java Language Specification, "*The body of an interface may declare
members of the interface, that is, fields, methods, classes, and
interfaces."* Such a broad statement brings up the question, what is the
difference between an interface and a class? One principal difference we
have pointed out already is this: an interface cannot be instantiated;
only a class can be instantiated.

Another difference is that a non-static method implemented inside
an interface is declared default or private. By contrast, a default
declaration is not available for the class methods.

Also, fields in an interface are implicitly public, static, and final.
By contrast, class properties and methods are not static or final by
default. The implicit (default) access modifier of a class itself, its
fields, methods, and constructors is package-private, which means it is
visible only within its own package. 

Default methods
---------------

To get an idea about the function of default methods in an interface,
let's look at an example of an interface and a class that implements it,
as follows:

```
interface SomeInterface {
    void method1();
    String method2(int i);
    default int method3(){
        return 42;
    }
}

class SomeClass implements SomeInterface{
    public void method1(){
        //method body
    }
    public String method2(int i) {
        //method body
        return "abc";
    }
}
```

We can now create an object of SomeClass class and make the following
call:

```
SomeClass sc = new SomeClass();
sc.method1();
sc.method2(22);  //returns: "abc"
sc.method3();    //returns: 42
```

As you can see, method3() is not implemented in the SomeClass class, but
it looks as if the class has it. That is one way to add a new method to
an existing class without changing it—by adding the default method to
the interface the class implements.

Let's now add method3() implementation to the class too, as follows:

```
class SomeClass implements SomeInterface{
    public void method1(){
        //method body
    }
    public String method2(int i) {
        //method body
        return "abc";
    }
    public int method3(){
        return 15;
    }
}
```

Now the interface implementation of method3() will be ignored:

```
SomeClass sc = new SomeClass();
sc.method1();
sc.method2(22);  //returns: "abc"
sc.method3();    //returns: 15
```

The purpose of the default method in an interface is to provide a new
method to the classes (that implement this interface) without changing
them. But the interface implementation is ignored as soon as a class
implements the new method too.

Private methods
---------------

If there are several default methods in an interface, it is possible to
create private methods accessible only by the default methods of the
interface. They can be used to contain common functionality, instead of
repeating it in every default method:

```
interface SomeInterface {
    void method1();
    String method2(int i);
    default int method3(){
        return getNumber();
    }
    default int method4(){
        return getNumber() + 22;
    }
    private int getNumber(){
        return 42;
    }
}
```

This concept of private methods is not different to private methods in
classes (see the *Packages, importing, and access* section). The private
methods cannot be accessed from outside the interface.

Static fields and methods
-------------------------

Since Java 8, all the fields declared in an interface are implicitly
public, static, and final constants. That is why an interface is a
preferred location for the constants. You do not need to add public
static final to their declarations.

As for the static methods, they function in an interface in the same way
as in a class:

```
interface SomeInterface{
   static String someMethod() {
      return "abc";
   }
}
```

Note, there is no need to mark the interface method as public. All
non-private interface methods are public by default.

We can call the preceding method by using just an interface name:

```
System.out.println(SomeInetrface.someMethod()); //prints: abc
```

Interface versus abstract class
-------------------------------

We have mentioned already that a class can be declared abstract. It may
be a regular class that we do not want to be instantiated, or it may be
a class that contains (or inherits) abstract methods. In the latter
case, we must declare such a class as abstract to avoid a compilation
error.

In many respects, an abstract class is very similar to an interface. It
forces every child class that extends it to implement the abstract
methods. Otherwise, the child cannot be instantiated and has to be
declared abstract itself. 

However, a few principal differences between an interface and abstract
class make each of them useful in different situations:

-   An abstract class can have a constructor, while an interface cannot.
-   An abstract class can have a state, while an interface cannot.
-   The fields of an abstract class can be public, private or protected,
    static or not, final or not, while, in an interface, fields are
    always public, static, and final.
-   The methods in an abstract class can be public, private,
    or protected, while the interface methods can be public, or private
    only.
-   If the class you would like to amend extends another class already,
    you cannot use an abstract class, but you can implement an interface
    because a class can extend only one other class, but can implement
    multiple interfaces

See an example of an abstract usage in the *Polymorphism in action*
section.



Overloading, overriding, and hiding
======================================

We have already mentioned overriding in the *Inheritance* and
*Abstraction/Interface* sections. It is a replacing of a non-static
method implemented in a parent class with the method of the same
signatures in the child class. A default method of an interface also can
be overridden in the interface that extends it.

Hiding is similar to overriding but applies only to static methods and
static, as well as properties of the instance .

Overloading is creating several methods with the same name and different
parameters (thus, different signatures) in the same class or interface. 

In this section, we will discuss all these concepts and demonstrate how
they work for classes and interfaces.

Overloading
-----------

It is not possible to have two methods in the same interface or a class
with the same signature. To have a different signature, the new method
has to have either a new name or a different list of parameter types
(and the sequence of the type does matter). Having two methods with the
same name but a different list of parameter types constitutes
overloading. Here are a few examples of a legitimate method of
overloading in an interface:

```
interface A {
    int m(String s);
    int m(String s, double d);
    default int m(String s, int i) { return 1; }
    static int m(String s, int i, double d) { return 1; }
}
```

Note that no two of the preceding methods have the same signature,
including the default and static methods. Otherwise, a compiler's error
would be generated. Neither designation as default, nor static, plays
any role in the overloading. A return type does not affect the
overloading either. We use int as a return type everywhere just to make
the examples less cluttered.

Method overloading is done similarly in a class:

```
class C {
        int m(String s){ return 42; }
        int m(String s, double d){ return 42; }
        static int m(String s, double d, int i) { return 1; }
    }
```

And, it does not matter where the methods with the same name are
declared. The following method overloading is not different to the
previous example, as follows:

```
interface A {
    int m(String s);
    int m(String s, double d);
}
interface B extends A {
    default int m(String s, int i) { return 1; }
    static int m(String s, int i, double d) { return 1; }
}
class C {
     int m(String s){ return 42; }
}
class D extends C {
     int m(String s, double d){ return 42; }
     static int m(String s, double d, int i) { return 1; }
}
```

A private non-static method can be overloaded only by a non-static
method of the same class.

Overloading happens when methods have the same name but a different list
of parameter types and belong to the same interface (or class) or to
different interfaces (or classes), one of which is an ancestor to
another. A private method can be overloaded only by a method in the same
class.

Overriding
----------

In contrast to overloading, which happens with the static and non-static
methods, method overriding happens only with non-static methods and only
when they have *exactly the same signature* and *belong to different
interfaces (or classes),* one of which is an ancestor to another.

The overriding method resides in the child interface (or class), while
the overridden method has the same signature and belongs to one of the
ancestor interfaces (or classes). A private method cannot be overridden.

The following are examples of a method overriding in an interface: 

```
interface A {
    default void method(){
        System.out.println("interface A");
    }
}
interface B extends A{
    @Override
    default void method(){
        System.out.println("interface B");
    }
}
class C implements B { }
```

If we call the method() using the C class instance, the result will be
as follows:

```
C c = new C();
c.method();      //prints: interface B
```

Please notice the usage of the annotation @Override. It tells the
compiler that the programmer thinks that the annotated method overrides
a method of one of the ancestor interfaces. This way, the compiler can
make sure that the overriding does happen and generates an error if not.
For example, a programmer may misspell the name of the method as
follows:

```
interface B extends A{
    @Override
    default void metod(){
        System.out.println("interface B");
    }
}
```

If that happens, the compiler generates an error because there is no
method metod() to override. Without the annotation @Overrride, this
mistake may go unnoticed by the programmer and the result would be quite
different:

```
C c = new C();
c.method();      //prints: interface A
```

The same rules of overriding apply to the class instance methods. In the
following example, the C2 class overrides a method of the C1 class:

```
class C1{
    public void method(){
        System.out.println("class C1");
    }
}
class C2 extends C1{
    @Override
    public void method(){
        System.out.println("class C2");
    }
}
```

The result is as follows:

```
C2 c2 = new C2();
c2.method();      //prints: class C2
```

And, it does not matter how many ancestors are between the class or
interface with the overridden method and the class or interface with the
overriding method:

```
class C1{
    public void method(){
        System.out.println("class C1");
    }
}
class C3 extends C1{
    public void someOtherMethod(){
        System.out.println("class C3");
    }
}
class C2 extends C3{
    @Override
    public void method(){
        System.out.println("class C2");
    }
}
```

The result of the preceding method's overriding will still be the same.

Hiding
------

**Hiding** is considered by many to be a complicated topic, but it
should not be, and we will try to make it look simple.

The name *hiding* came from the behavior of static properties and
methods of classes and interfaces. Each static property or method
exists as a single copy in the JVM's memory because they are associated
with the interface or class, not with an object. And interface or class
exists as a single copy. That is why we cannot say that the child's
static property or method overrides the parent's static property or
method with the same name. All static properties and methods are loaded
into the memory only once when the class or interface is loaded and stay
there, not copied anywhere. Let's look at the example.

Let's create two interfaces that have a parent-child relationship and
static fields and methods with the same name:

```
interface A {
    String NAME = "interface A";
    static void method() {
        System.out.println("interface A");
    }
}
interface B extends A {
    String NAME = "interface B";
    static void method() {
        System.out.println("interface B");
    }
}
```

Please note the capital case for an identifier of an interface field.
That is the convention often used to denote a constant, whether it is
declared in an interface or in a class. Just to remind you, a constant
in Java is a variable that, once initialized, cannot be re-assigned
another value. An interface field is a constant by default because any
field in an interface is *final* (see the *Final properties, methods,
and classes* section).

If we print NAME from the B interface and execute its method(), we get
the following result:

```
System.out.println(B.NAME); //prints: interface B
B.method();                 //prints: interface B
```

It looks very much like overriding, but, in fact, it is just that we
call a particular property or a method associated with this particular
interface.

Similarly, consider the following classes:

```
public class C {
    public static String NAME = "class C";
    public static void method(){
        System.out.println("class C"); 
    }
    public String name1 = "class C";
}
public class D extends C {
    public static String NAME = "class D";
    public static void method(){
        System.out.println("class D"); 
    }
    public String name1 = "class D";
}
```

If we try to access the static members of D class using the class
itself, we will get what we asked for:

```
System.out.println(D.NAME);  //prints: class D
D.method();                  //prints: class D
```

The confusion appears only when a property or a static method is
accessed using an object:

```
C obj = new D();

System.out.println(obj.NAME);       //prints: class C
System.out.println(((D) obj).NAME); //prints: class D

obj.method();                       //prints: class C
((D)obj).method();                  //prints: class D

System.out.println(obj.name1);       //prints: class C
System.out.println(((D) obj).name1); //prints: class D
```

The obj variable refers to the object of the D class, and the casting
proves it, as you can see in the preceding example. But, even if we use
an object, trying to access a static property or method brings us the
members of the class that was used as the declared variable type. As for
the instance property in the last two lines of the example, the
properties in Java do not conform to polymorphic behavior and we get
the name1 property of the parent C class, instead of the expected
property of the child D class.

To avoid confusion with static members of a class, always access them
using the class, not an object. To avoid confusion with instance
properties, always declare them private and access via methods.

To illustrate the last tip, consider the following classes:

```
class X {
    private String name = "class X";
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
class Y extends X {
    private String name = "class Y";
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
```

If we run the same test for the instance properties as we did for
classes C and D, the result will be this:

```
X x = new Y();
System.out.println(x.getName());      //prints: class Y
System.out.println(((Y)x).getName()); //prints: class Y
```

Now we access instance properties using methods, which are subjects for
an overriding effect and do not have unexpected results anymore.

To close the discussion of hiding in Java, we would like to mention
another type of hiding, namely, when a local variable hides the instance
or static property with the same name. Here is a class that does it:

```
public class HidingProperty {
   private static String name1 = "static property";
   private String name2 = "instance property";

   public void method() {
      var name1 = "local variable";
      System.out.println(name1);     //prints: local variable

      var name2 = "local variable";  //prints: local variable
      System.out.println(name2);

      System.out.println(HidingProperty.name1); //prints: static property
      System.out.println(this.name2);         //prints: instance property
   }
}
```

As you can see, the local variable name1 hides the static property with
the same name, while the local variable name2 hides the instance
property. It is possible still to access the static property using the
class name (see HidingProperty.name1). Please note that, despite being
declared private, it is accessible from inside the class. 

The instance property can always be accessed by using the this
keyword that means **current object**.



Final variable, method, and classes
======================================

We have mentioned a final property several times in relation to the
notion of a constant in Java. But that is only one case of using
the final keyword. It can be applied to any variable in general. Also, a
similar constraint can be applied to a method and even a class too, thus
preventing the method from being overridden and the class being
extended.

Final variable
--------------

The final keyword placed in front of a variable declaration makes this
variable immutable after the initialization. For example:

```
final String s = "abc";
```

The initialization can even be delayed:

```
final String s;
s = "abc";
```

In the case of an object property, this delay can last only until the
object is created. This means that the property can be initialized in
the constructor. For example:

```
private static class A {
    private final String s1 = "abc";
    private final String s2;
    private final String s3;   //error
    private final int x;       //error

    public A() {
        this.s1 = "xyz";      //error
        this.s2 = "xyz";     
    }
}
```

Notice that, even during the object construction, it is not possible to
initialize the property twice—during declaration and in the constructor.
It is also interesting to note that a final property has to be
initialized explicitly. As you can see from the preceding example, the
compiler does not allow the initialization of the final property to a
default value.

It is also possible to initialize a final property in an initialization
block:

```
class B {
    private final String s1 = "abc";
    private final String s2;
    {
        s1 = "xyz"; //error
        s2 = "abc";
    }
}
```

In the case of a static property, it is not possible to initialize it in
a constructor, so it has to be initialized either during its declaration
or in a static initialization block:

```
class C {
    private final static String s1 = "abc";
    private final static String s2;
    static {
        s1 = "xyz"; //error
        s2 = "abc";
    }
}
```

In an interface, all fields are always final, even if they are not
declared as such. Since neither constructor nor initialization block is
not allowed in an interface, the only way to initialize an interface
field is during declaration. Failing to do it results in a compilation
error:

```
interface I {
    String s1;  //error
    String s2 = "abc";
}
```

Final method
------------

A method declared final cannot be overridden in a child class or hidden
in the case of a static method. For example,
the java.lang.Object class, which is the ancestor of all classes in
Java, has some of its methods declared final:

```
public final Class getClass()
public final void notify()
public final void notifyAll()
public final void wait() throws InterruptedException
public final void wait(long timeout) throws InterruptedException
public final void wait(long timeout, int nanos)
                                     throws InterruptedException
```

All private methods and uninherited methods of a final class are
effectively final because you cannot override them.

Final class
-----------

A final class cannot be extended. It cannot have children, which makes
all the methods of the class effectively final too. This feature is used
for security or when a programmer would like to make sure the class
functionality cannot be overridden, overloaded, or hidden because of
some other design considerations.



Polymorphism in action
======================================

Polymorphism is the most powerful and useful feature of OOP. It uses all
other OOP concepts and features we have presented so far. It is the
highest conceptual point on the way to mastering Java programming. After
it, the rest of the book will be mostly about Java language syntax and
JVM functionality. 

As we have stated in the *OOP concepts* section, **polymorphism** is the
ability of an object to behave as an object of different classes or as
an implementation of different interfaces. If you search the word
polymorphism on the Internet, you will find that it is "*the condition
of occurring in several different forms*." Metamorphosis is "*a change
of the form or nature of a thing or person into a completely different
one, by natural or supernatural means*." So, **Java polymorphism** is
the ability of an object to behave as if going through a metamorphosis
and to exhibit completely different behaviors under different
conditions.

We will present this concept in a practical hands-on way, using an
**object factory**—a specific programming implementation of a factory,
which is a *method that returns objects of a varying prototype or
class.* See
[https://en.wikipedia.org/wiki/Factory\_(object-oriented\_programming)](https://en.wikipedia.org/wiki/Factory_(object-oriented_programming)).

Object factory
--------------

The idea behind the object factory is to create a method that returns a
new object of a certain type under certain conditions. For example, look
at the CalcUsingAlg1 and CalcUsingAlg2 classes:

```
interface CalcSomething{ double calculate(); }
class CalcUsingAlg1 implements CalcSomething{
    public double calculate(){ return 42.1; }
}
class CalcUsingAlg2 implements CalcSomething{
    private int prop1;
    private double prop2;
    public CalcUsingAlg2(int prop1, double prop2) {
        this.prop1 = prop1;
        this.prop2 = prop2;
    }
    public double calculate(){ return prop1 * prop2; }
}
```

As you can see, they both implement the same
interface, CalcSomething, but use different algorithms. Now, let's say
that we decided that the selection of the algorithm used will be done in
a property file. Then we can create the following object factory:

```
class CalcFactory{
    public static CalcSomething getCalculator(){
        String alg = getAlgValueFromPropertyFile();
        switch(alg){
            case "1":
                return new CalcUsingAlg1();
            case "2":
                int p1 = getAlg2Prop1FromPropertyFile();
                double p2 = getAlg2Prop2FromPropertyFile();
                return new CalcUsingAlg2(p1, p2);
            default:
                System.out.println("Unknown value " + alg);
                return new CalcUsingAlg1();
        }
    }
}
```

The factory selects which algorithm to use based on the value returned
by the getAlgValueFromPropertyFile() method. In the case of the second
algorithm, it also uses the getAlg2Prop1FromPropertyFile()
methods and getAlg2Prop2FromPropertyFile() to get the input parameters
for the algorithm. But this complexity is hidden from a client:

```
CalcSomething calc = CalcFactory.getCalculator();
double result = calc.calculate();
```

We can add new algorithm variations, change the source for the algorithm
parameters or the process of the algorithm selection, but the client
will not need to change the code. And that is the power of polymorphism.

Alternatively, we could use inheritance to implement polymorphic
behavior. Consider the following classes:

```
class CalcSomething{
    public double calculate(){ return 42.1; }
}
class CalcUsingAlg2 extends CalcSomething{
    private int prop1;
    private double prop2;
    public CalcUsingAlg2(int prop1, double prop2) {
        this.prop1 = prop1;
        this.prop2 = prop2;
    }
    public double calculate(){ return prop1 * prop2; }
}
```

Then our factory may look as follows:

```
class CalcFactory{
    public static CalcSomething getCalculator(){
        String alg = getAlgValueFromPropertyFile();
        switch(alg){
            case "1":
                return new CalcSomething();
            case "2":
                int p1 = getAlg2Prop1FromPropertyFile();
                double p2 = getAlg2Prop2FromPropertyFile();
                return new CalcUsingAlg2(p1, p2);
            default:
                System.out.println("Unknown value " + alg);
                return new CalcSomething();
        }
    }
}
```

But the client code does not change:

```
CalcSomething calc = CalcFactory.getCalculator();
double result = calc.calculate();
```

Given a choice, an experienced programmer uses a common interface for
the implementation. It allows for a more flexible design, as a class in
Java can implement multiple interfaces, but can extend (inherit from)
one class.

Operator instanceof
-------------------

Unfortunately, life is not always that easy and, once in a while, a
programmer has to deal with a code that is assembled from unrelated
classes, coming even from different frameworks. In such a case, using
polymorphism may be not an option. Still, you can hide the complexity of
an algorithm selection and even simulate polymorphic behavior using
the instanceof operator, which returns true when the object is an
instance of a certain class.

Let's assume we have two unrelated classes:

```
class CalcUsingAlg1 {
    public double calculate(CalcInput1 input){
        return 42. * input.getProp1();
    }
}

class CalcUsingAlg2{
    public double calculate(CalcInput2 input){
        return input.getProp2() * input.getProp1();
    }
}
```

Each of the classes expects as an input an object of a certain type:

```
class CalcInput1{
    private int prop1;
    public CalcInput1(int prop1) { this.prop1 = prop1; }
    public int getProp1() { return prop1; }
}

class CalcInput2{
    private int prop1;
    private double prop2;
    public CalcInput2(int prop1, double prop2) {
        this.prop1 = prop1;
        this.prop2 = prop2;
    }
    public int getProp1() { return prop1; }
    public double getProp2() { return prop2; }
}
```

And let's assume that the method we implement receives such an object:

```
void calculate(Object input) {
    double result = Calculator.calculate(input);
    //other code follows
}
```

We still use polymorphism here because we describe our input as
the Object type. We can do it because the Object class is the base class
for all Java classes.

Now let's look at how the Calculator class is implemented:

```
class Calculator{
    public static double calculate(Object input){
        if(input instanceof CalcInput1){
            return new CalcUsingAlg1().calculate((CalcInput1)input);
        } else if (input instanceof CalcInput2){
            return new CalcUsingAlg2().calculate((CalcInput2)input);
        } else {
            throw new RuntimeException("Unknown input type " + 
                               input.getClass().getCanonicalName());
        }
    }
}
```

As you can see, it uses the instanceof operator for selecting the
appropriate algorithm. By using the Object class as an input type,
the Calculator class takes advantage of polymorphism too, but most of
its implementation has nothing to do with it. Yet, from outside, it
looks polymorphic and it is, but only to a degree.


Summary
======================================

This chapter introduced readers to the concepts of OOP and how they are
implemented in Java. It provided an explanation of each concept and
demonstrated how to use it in specific code examples. The Java language
constructs of class and interface were discussed in detail. The reader
also has learned what overloading, overriding, and hiding are and how to
use the final keyword to protect methods from being overridden. 

From the *Polymorphism in action* section, the reader learned about the
powerful Java feature of polymorphism. This section brought all the
presented material together and showed how polymorphism stays in the
center of OOP.

In the next chapter, the reader will become familiar with Java language
syntax, including packages, importing, access modifiers, reserved and
restricted keywords, and some aspects of Java reference types. The
reader will also learn how to use the this and super keywords, what
widening and narrowing conversions of primitive types are, boxing and
unboxing, primitive and reference types assignment, and how the equals()
method of a reference type works.



