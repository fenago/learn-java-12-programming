

Data Structures, Generics, and Popular Utilities
================================================

This chapter presents the Java collections framework and its three main
interfaces: List, Set, and Map, including a discussion and demonstration
of generics. The equals() and hashCode() methods are also discussed in
the context of Java collections. Utility classes for managing arrays,
objects, and time/date values have corresponding dedicated sections too.

The following topics will be covered in this chapter:

-   List, Set, and Map interfaces
-   Collections utilities
-   Arrays utilities
-   Object utilities
-   The java.time package



### Run Java Code
You can run the example by running following command in the terminal:
`java -cp target/learnjava-1.0.jar com.lv.learnjava.ch06_collections.ArraysUtils.java`

### Run Java Code
You can run the example by running following command in the terminal:
`java -cp target/learnjava-1.0.jar com.lv.learnjava.ch06_collections.CollectionsDemo.java`

### Run Java Code
You can run the example by running following command in the terminal:
`java -cp target/learnjava-1.0.jar com.lv.learnjava.ch06_collections.CollectionsUtils.java`

### Run Java Code
You can run the example by running following command in the terminal:
`java -cp target/learnjava-1.0.jar com.lv.learnjava.ch06_collections.ObjectsUtils.java`

### Run Java Code
You can run the example by running following command in the terminal:
`java -cp target/learnjava-1.0.jar com.lv.learnjava.ch06_collections.TimeRelatedClasses.java`


Data Structures, Generics, and Popular Utilities
================================================

The **Java collections framework** consists of the classes and
interfaces that implement a collection data structure. Collections are
similar to arrays in that respect as they can hold references to objects
and can be managed as a group. The difference is that arrays require
their capacity being defined before they can be used, while collections
can increase and decrease their size automatically as needed. You just
add or remove an object reference to a collection, and the collection
changes its size accordingly. Another difference is that the collections
cannot have their elements to be primitive types, such as short, int, or
double. If you need to store such type values, the elements must be of a
corresponding wrapper type, such as Short, Integer, or Double, for
example.

Java collections support various algorithms of storing and accessing the
elements of a collection: an ordered list, a unique set, a dictionary
called a **map** in Java, a **stack**, a **queue**, and some others. All
the classes and interfaces of the Java collections framework belong to
the java.util package of the Java Class Library. The java.util package
contains the following:

-   The interfaces that extend the Collection interface: List, Set,
    and Queue, to name the most popular ones
-   The classes that implement the previously listed interfaces:
    ArrayList, HashSet, Stack, LinkedList, and some others
-   The Map interface and its sub-interfaces, ConcurrentMap,
    and SortedMap, to name a couple
-   The classes that implement the Map-related interfaces: HashMap,
    HashTable, and TreeMap, to name the three most frequently used

To review all the classes and interfaces of the java.util package would
require a dedicated book. So, in this section, we will just have a brief
overview of the three main interfaces: List, Set, and Map—and one
implementation class for each of them—ArrayList, HashSet, and HashMap.
We start with methods that are shared by the List and Set interfaces.
The principal difference between List and Set is that Set does not allow
duplication of the elements. Another difference is that List preserves
the order of the elements and also allows them to be sorted.

To identify an element inside a collection, the equals() method is used.
To improve performance, the classes that implement the Set interface
often use the hashCode() method too. This facilitates rapid calculation
of an integer (called a **hash value** or **hash code**) that is, most
of the time (but not always), unique to each element. The elements with
the same hash value are placed in the same *bucket*. While establishing
whether there is already a certain value in the set, it is enough to
check the internal hash table and see whether such a value has already
been used. If not, the new element is unique. If yes, then the new
element can be compared (using the equals() method) with each of the
elements with the same hash value. Such a procedure is faster than
comparing a new element with each element of the set one by one. 

That is why we often see that the name of the classes has a "hash"
prefix, indicating that the class uses the hash value, so the element
must implement the hashCode() method. While doing this, you must make
sure that it is implemented so that every time the equals() method
returns true for two objects, the hash values of these two objects
returned by the hashCode() method are equal too. Otherwise, the just
described algorithm of using the hash value will not work.

And finally, before talking about the java.util interfaces, a few words
about generics.

Generics
--------

You can see these most often in such declarations:

```
List<String> list = new ArrayList<String>();Set<Integer> set = new HashSet<Integer>();
```

In the preceding examples, **generics** is the element nature
declaration surrounded by the angle brackets. As you can see, they are
redundant, as they are repeated in the left- and right-hand sides of the
assignment statement. That is why Java allows replacement of the
generics on the right side with empty brackets (\<\>) called a
**diamond**:

```
List<String> list = new ArrayList<>();Set<Integer> set = new HashSet<>();
```

Generics inform the compiler about the expected type of the collection
elements. This way, the compiler can check whether the element a
programmer tries to add to the declared collection is of a compatible
type. Observe the following, for example:

```
List<String> list = new ArrayList<>();list.add("abc");list.add(42);   //compilation error
```

This helps to avoid runtime errors. It also tips off the programmer
(because an IDE compiles the code when a programmer writes it) about
possible manipulations of the collection elements.

We will also see these other types of generics:

-   \<? extends T\> means *a type that is either* T *or a child of* T,
    where T is the type used as the generics of the collection.
-   \<? super T\> means *a type* T *or any of its base (parent) class*,
    where T is the type used as the generics of the collection.

With that, let's start with the way an object of the class that
implements the List or Set interface can be created, or, in other words,
the List or Set type of variable can be initialized. To demonstrate the
methods of these two interfaces, we will use two classes: an ArrayList
(implements List) and HashSet (implements Set).

How to initialize List and Set
------------------------------

Since Java 9, the List or Set interfaces have static of()
factory methods that can be used to initialize a collection:

-   of(): Returns an empty collection.
-   of(E... e): Returns a collection with as many elements as are passed
    in during the call. They can be passed in a comma-separated list or
    as an array.

Here are a few examples:

```
//Collection<String> coll = List.of("s1", null); //does not allow nullCollection<String> coll = List.of("s1", "s1", "s2");//coll.add("s3");                        //does not allow add element//coll.remove("s1");                     //does not allow remove element((List<String>) coll).set(1, "s3");      //does not allow modify elementSystem.out.println(coll);                //prints: [s1, s1, s2]//coll = Set.of("s3", "s3", "s4");       //does not allow duplicate//coll = Set.of("s2", "s3", null);       //does not allow nullcoll = Set.of("s3", "s4");System.out.println(coll);                //prints: [s3, s4]//coll.add("s5");                        //does not allow add element//coll.remove("s2");                     //does not allow remove
```

As you might expect, the factory method for Set does not allow
duplicates, so we have commented the line out (otherwise, the preceding
example would stop running at that line). What was less expected is that
you cannot have a null element, and you cannot add/remove/modify
elements of a collection after it was initialized using one of the of()
methods. That's why we have commented out some lines of the preceding
example. If you need to add elements after the collection was
initialized, you have to initialize it using a constructor or some other
utilities that create a modifiable collection (we will see an example of
Arrays.asList() shortly).

The interface Collection provides two methods for adding elements to an
object that implements Collection (the parent interface of List and Set)
that looks as follows:

-   boolean add(E e): This attempts to add the provided element e to the
    collection; returns true in case of success, and false in case of
    not being able to accomplish it (for example, when such an element
    already exists in the Set).

-   boolean addAll(Collection\<? extends E\> c): This attempts to add
    all of the elements in the provided collection to the collection; it
    returns true if at least one element was added, and false in case of
    not able to add an element to the collection (for example, when all
    the elements of the provided collection c already exist in the Set).

Here is an example of using the add() method:

```
List<String> list1 = new ArrayList<>();list1.add("s1");list1.add("s1");System.out.println(list1);     //prints: [s1, s1]Set<String> set1 = new HashSet<>();set1.add("s1");set1.add("s1");System.out.println(set1);      //prints: [s1]
```

And here is an example of using the addAll() method:

```
List<String> list1 = new ArrayList<>();list1.add("s1");list1.add("s1");System.out.println(list1);      //prints: [s1, s1]List<String> list2 = new ArrayList<>();list2.addAll(list1);System.out.println(list2);      //prints: [s1, s1]Set<String> set = new HashSet<>();set.addAll(list1);System.out.println(set);        //prints: [s1]
```

The following is an example of the add() and
addAll() methods' functionality:

```
List<String> list1 = new ArrayList<>();list1.add("s1");list1.add("s1");System.out.println(list1);     //prints: [s1, s1]List<String> list2 = new ArrayList<>();list2.addAll(list1);System.out.println(list2);      //prints: [s1, s1]Set<String> set = new HashSet<>();set.addAll(list1);System.out.println(set);      //prints: [s1]Set<String> set1 = new HashSet<>();set1.add("s1");Set<String> set2 = new HashSet<>();set2.add("s1");set2.add("s2");System.out.println(set1.addAll(set2)); //prints: trueSystem.out.println(set1);              //prints: [s1, s2]
```

Notice how, in the last example in the preceding code snippet,
the set1.addAll(set2) method returns true, although not all elements
were added. To see the case of the add() and addAll() methods returning
false, look at the following examples:

```
Set<String> set = new HashSet<>();System.out.println(set.add("s1"));   //prints: trueSystem.out.println(set.add("s1"));   //prints: falseSystem.out.println(set);             //prints: [s1]Set<String> set1 = new HashSet<>();set1.add("s1");set1.add("s2");Set<String> set2 = new HashSet<>();set2.add("s1");set2.add("s2");System.out.println(set1.addAll(set2)); //prints: falseSystem.out.println(set1);              //prints: [s1, s2]
```

The ArrayList and HashSet classes also have constructors that accept a
collection:

```
Collection<String> list1 = List.of("s1", "s1", "s2");System.out.println(list1);      //prints: [s1, s1, s2]List<String> list2 = new ArrayList<>(list1);System.out.println(list2);      //prints: [s1, s1, s2]Set<String> set = new HashSet<>(list1);System.out.println(set);        //prints: [s1, s2]List<String> list3 = new ArrayList<>(set);System.out.println(list3);      //prints: [s1, s2]
```

Now, after we have learned how a collection can be initialized, we can
turn to other methods in the List and Set interfaces.

java.lang.Iterable interface
----------------------------

The Collection interface extends the java.lang.Iterable interface, which
means that those classes that implement the Collection interface,
directly or not, also implement the java.lang.Iterable interface. There
are only three methods in the Iterable interface:

-   Iterator\<T\> iterator(): This returns an object of a class that
    implements the java.util.Iterator interface; it allows the
    collection to be used in FOR statements, for example:

```
Iterable<String> list = List.of("s1", "s2", "s3");System.out.println(list);       //prints: [s1, s2, s3]for(String e: list){    System.out.print(e + " ");  //prints: s1 s2 s3}
```

-   default void forEach (Consumer\<? super T\> function): This applies
    the provided function of the Consumer type to each element of the
    collection until all elements have been processed or the function
    throws an exception. What function is, we will discuss this in
    [Chapter
    13](https://subscription.packtpub.com/book/programming/9781789957051/13), *Functional
    Programming;* for now, we will just provide an example:

```
Iterable<String> list = List.of("s1", "s2", "s3");System.out.println(list);                     //prints: [s1, s2, s3]list.forEach(e -> System.out.print(e + " ")); //prints: s1 s2 s3
```

-   default Spliterator\<T\> splititerator(): This returns an object of
    a class that implements the java.util.Spliterator interface; it is
    used primarily for implementing methods that allow parallel
    processing and is outside the scope of this book. 

Collection interface
--------------------

As we have mentioned already, the List and Set interfaces extend
the Collection interface, which means that all the methods of
the Collection interface are inherited by List and Set. These methods
are as follows:

-   boolean add(E e): This attempts to add an element to the collection.

-   boolean addAll(Collection\<? extends E\> c): This attempts to
    add all of the elements in the collection provided.
-   boolean equals(Object o): This compares the collection with
    the o object provided; if the object provided is not a collection,
    this object returns false; otherwise, it compares the composition of
    the collection with the composition of the collection provided (as o
    object); in the case of List, it also compares the order of the
    elements. Let's illustrate this with a few examples:

```
Collection<String> list1 = List.of("s1", "s2", "s3");System.out.println(list1);       //prints: [s1, s2, s3]Collection<String> list2 = List.of("s1", "s2", "s3");System.out.println(list2);       //prints: [s1, s2, s3]System.out.println(list1.equals(list2));  //prints: trueCollection<String> list3 = List.of("s2", "s1", "s3");System.out.println(list3);       //prints: [s2, s1, s3]System.out.println(list1.equals(list3));  //prints: falseCollection<String> set1 = Set.of("s1", "s2", "s3");System.out.println(set1);   //prints: [s2, s3, s1] or different orderCollection<String> set2 = Set.of("s2", "s1", "s3");System.out.println(set2);   //prints: [s2, s1, s3] or different orderSystem.out.println(set1.equals(set2));  //prints: trueCollection<String> set3 = Set.of("s4", "s1", "s3");System.out.println(set3);   //prints: [s4, s1, s3] or different orderSystem.out.println(set1.equals(set3));  //prints: false
```

-   int hashCode(): This returns the hash value for the collection; it
    is used in the case where the collection is an element of a
    collection that requires the hashCode() method implementation.
-   boolean isEmpty(): This returns true if the collection does not have
    any elements.
-   int size(): This returns the count of elements of the collection;
    when the isEmpty() method returns true, this method returns 0.
-   void clear(): This removes all of the elements from the collection;
    after this method is called, the isEmpty() method returns true, and
    the size() method returns 0.
-   boolean contains(Object o): This returns true if the collection
    contains the provided object o; for this method to work correctly,
    each element of the collection and the provided object must
    implement equals() method and, in the case of Set, hashCode() method
    should be implemented.
-   boolean containsAll(Collection\<?\> c): This returns true if the
    collection contains all of the elements in the collection
    provided; for this method to work correctly, each element of the
    collection and each element of the collection provided must
    implement the equals() method and, in the case of Set,
    the hashCode() method should be implemented.
-   boolean remove(Object o): This attempts to remove the specified
    element from this collection and returns true if it was present; for
    this method to work correctly, each element of the collection and
    the object provided must implement the equals() method and, in the
    case of Set, the hashCode() method should be implemented
-   boolean removeAll(Collection\<?\> c): This attempts to remove from
    the collection all the elements of the collection provided; similar
    to the addAll() method, this method returns true if at least one of
    the elements was removed; otherwise, it returns false; for this
    method to work correctly, each element of the collection and each
    element of the collection provided must implement
    the equals() method and, in the case of Set, the hashCode() method
    should be implemented
-   default boolean removeIf(Predicate\<? super E\> filter):
    This attempts to remove from the collection all the elements that
    satisfy the given predicate; it is a function we are going to
    describe in [Chapter
    13](https://subscription.packtpub.com/book/programming/9781789957051/13), *Functional
    Programming*; it returns true if at least one element was removed

-   boolean retainAll(Collection\<?\> c): This attempts to retain in the
    collection just the elements contained in the collection
    provided; similar to the addAll() method, this method
    returns true if at least one of the elements is retained; otherwise,
    it returns false; for this method to work correctly, each element of
    the collection and each element of the collection provided must
    implement the equals() method and, in the case of Set, the
    hashCode() method should be implemented.
-   Object[] toArray(), T[] toArray(T[] a): This converts the collection
    to an array.
-   default T[] toArray(IntFunction\<T[]\> generator): This converts the
    collection to an array, using the function provided; we are going to
    explain functions in [Chapter
    13](https://subscription.packtpub.com/book/programming/9781789957051/13), *Functional
    Programming*.
-   default Stream\<E\> stream(): This returns a Stream object (we talk
    about streams in [Chapter
    14](https://subscription.packtpub.com/book/programming/9781789957051/14), *Java
    Standard Streams*).
-   default Stream\<E\> parallelStream(): This returns a possibly
    parallel Stream object (we talk about streams in
    [](https://subscription.packtpub.com/book/programming/9781789957051/14)[Chapter
    14](https://subscription.packtpub.com/book/programming/9781789957051/14), *Java
    Standard Streams*).

List interface
--------------

The List interface has several other methods that do not belong to any
of its parent interfaces:

-   Static factory of() methods described in the *How to initialize List
    and Set* subsection
-   void add(int index, E element): This inserts the element provided at
    the provided position in the list.
-   static List\<E\> copyOf(Collection\<E\> coll): This returns an
    unmodifiable List containing the elements of the given Collection
    and preserves their order; here is the code that demonstrates the
    functionality of this method:

```
Collection<String> list = List.of("s1", "s2", "s3");System.out.println(list);         //prints: [s1, s2, s3]List<String> list1 = List.copyOf(list);//list1.add("s4");                //run-time error//list1.set(1, "s5");             //run-time error//list1.remove("s1");             //run-time errorSet<String> set = new HashSet<>();System.out.println(set.add("s1"));System.out.println(set);          //prints: [s1]Set<String> set1 = Set.copyOf(set);//set1.add("s2");                 //run-time error//set1.remove("s1");              //run-time errorSet<String> set2 = Set.copyOf(list);System.out.println(set2);         //prints: [s1, s2, s3] 
```

-   E get(int index): This returns the element located at the position
    specified in the list
-   List\<E\> subList(int fromIndex, int toIndex): Extracts a sublist
    between the fromIndex, inclusive, and the toIndex, exclusive
-   int indexOf(Object o): This returns the first index (position) of
    the specified element in the list; the first element in the list has
    an index (position), 0.
-   int lastIndexOf(Object o): This returns the last index (position) of
    the specified element in the list; the final element in the list has
    list.size() - 1 index position.
-   E remove(int index): This removes the element located at the
    specified position in the list; it returns the element removed.
-   E set(int index, E element): This replaces the element located at
    the position specified in the list; it returns the element replaced.
-   default void replaceAll(UnaryOperator\<E\> operator): This
    transforms the list by applying the function provided to each
    element; the UnaryOperator function will be described in
    [](https://subscription.packtpub.com/book/programming/9781789957051/13)[Chapter
    13](https://subscription.packtpub.com/book/programming/9781789957051/13), *Functional
    Programming.*
-   ListIterator\<E\> listIterator(): Returns a ListIterator object that
    allows the list to be traversed backward.
-   ListIterator\<E\> listIterator(int index): Returns
    a ListIterator object that allows the sublist (starting from the
    provided position) to be traversed backward. Observe the following,
    for example:

```
List<String> list = List.of("s1", "s2", "s3");ListIterator<String> li = list.listIterator();while(li.hasNext()){    System.out.print(li.next() + " ");         //prints: s1 s2 s3}while(li.hasPrevious()){    System.out.print(li.previous() + " ");     //prints: s3 s2 s1}ListIterator<String> li1 = list.listIterator(1);while(li1.hasNext()){    System.out.print(li1.next() + " ");        //prints: s2 s3}ListIterator<String> li2 = list.listIterator(1);while(li2.hasPrevious()){    System.out.print(li2.previous() + " ");    //prints: s1}
```

-   default void sort(Comparator\<? super E\> c): This sorts the list
    according to the order generated by the Comparator provided. Observe
    the following, for example:

```
List<String> list = new ArrayList<>();list.add("S2");list.add("s3");list.add("s1");System.out.println(list);                //prints: [S2, s3, s1]list.sort(String.CASE_INSENSITIVE_ORDER);System.out.println(list);                //prints: [s1, S2, s3]//list.add(null);                 //causes NullPointerExceptionlist.sort(Comparator.naturalOrder());System.out.println(list);               //prints: [S2, s1, s3]list.sort(Comparator.reverseOrder());System.out.println(list);               //prints: [s3, s1, S2]list.add(null);list.sort(Comparator.nullsFirst(Comparator.naturalOrder()));System.out.println(list);              //prints: [null, S2, s1, s3]list.sort(Comparator.nullsLast(Comparator.naturalOrder()));System.out.println(list);              //prints: [S2, s1, s3, null]Comparator<String> comparator = (s1, s2) ->  s1 == null ? -1 : s1.compareTo(s2);list.sort(comparator);System.out.println(list);              //prints: [null, S2, s1, s3]
```

There are principally two ways to sort a list:

-   Using Comparable interface implementation (called **natural order**)
-   Using Comparator interface implementation

The Comparable interface only has a compareTo() method. In the preceding
example, we have implemented the Comparator interface basing it on
the Comparable interface implementation in the String class. As you can
see, this implementation provided the same sort order as
Comparator.nullsFirst(Comparator.naturalOrder()). This style of
implementing is called **functional programming***,* which we will
discuss in more detail in [Chapter
13](https://subscription.packtpub.com/book/programming/9781789957051/13), *Functional
Programming.*

Set interface 
--------------

The Set interface has the following methods that do not belong to any of
its parent interfaces:

-   Static of() factory methods described in the *How to initialize List
    and Set* subsection.
-   The static Set\<E\> copyOf(Collection\<E\> coll) method: This
    returns an unmodifiable Set containing the elements of the
    given Collection; it works the same way as the static \<E\>
    List\<E\> copyOf(Collection\<E\> coll) method described in
    the *Interface List* section.

Map interface
-------------

The Map interface has many methods similar to the List and Set methods:

-   int size()
-   void clear()
-   int hashCode()
-   boolean isEmpty()
-   boolean equals(Object o)
-   default void forEach(BiConsumer\<K,V\> action)
-   Static factory methods: of(), of(K k, V v), of(K k1, V v1, K k2, V
    v2), and many other methods besides

Map interface, however, does not extend Iterable, Collection, or any
other interface for that matter. It is designed to be able to store
**values** by their **keys***.* Each key is unique. While several equal
values can be stored with different keys on the same map. The
combination of key and value constitutes an Entry, which is an internal
interface of Map. Both value and key objects must implement
the equals() method. A key object must also implement the hashCode()
method. 

Many methods of Map interface have exactly the same signature and
functionality as in the List and Set interfaces, so we are not going to
repeat them here. We will only walk through the Map-specific methods:

-   V get(Object key): This retrieves the value according to the key
    provided; it returns null if there is no such key.
-   Set\<K\> keySet(): This retrieves all the keys from the map.
-   Collection\<V\> values(): This retrieves all the values from the
    map.
-   boolean containsKey(Object key): This returns true if the key
    provided exists in the map.
-   boolean containsValue(Object value): This returns true if the value
    provided exists in the map.
-   V put(K key, V value): This adds the value and its key to the map;
    it returns the previous value stored with the same key.
-   void putAll(Map\<K,V\> m): This copies from the map provided all the
    key-value pairs.
-   default V putIfAbsent(K key, V value): This stores the value
    provided and maps to the key provided if such a key is not already
    used by the map; returns the value mapped to the key provided—either
    the existing or the new one.
-   V remove(Object key): This removes both the key and value from the
    map; it returns a value or null if there is no such key, or if the
    value is null.
-   default boolean remove(Object key, Object value): This removes the
    key-value pair from the map if such a pair exists in the map.
-   default V replace(K key, V value): This replaces the value if the
    key providedis currently mapped to the value provided; it returns
    the old value if it was replaced; otherwise, it returns null.
-   default boolean replace(K key, V oldValue, V
    newValue): This replaces the value oldValue with the newValue
    provided if the key provided is currently mapped to the oldValue; it
    returns true if the oldValue was replaced; otherwise, it
    returns false.
-   default void replaceAll(BiFunction\<K,V,V\> function): This applies
    the function provided to each key-value pair in the map and replaces
    it with the result, or throws an exception if this is not possible.

-   Set\<Map.Entry\<K,V\>\> entrySet(): This returns a set of all
    key-value pairs as the objects of Map.Entry.
-   default V getOrDefault(Object key, V defaultValue): This returns the
    value mapped to the key provided or the defaultValue if the map does
    not have the key provided.
-   static Map.Entry\<K,V\> entry(K key, V value): This returns an
    unmodifiable Map.Entry object with the key and value provided in it.
-   static Map\<K,V\> copy(Map\<K,V\> map):
    This converts the Map provided to an unmodifiable one.

The following Map methods are much too complicated for the scope of this
book, so we are just mentioning them for the sake of completeness. They
allow multiple values to be combined or calculated and aggregated in a
single existing value in the Map, or a new one to be created:

-   default V merge(K key, V value, BiFunction\<V,V,V\>
    remappingFunction): If the provided key-value pair exists and the
    value is not null, the provided function is used to calculate a new
    value; it removes the key-value pair if the newly calculated value
    is null; if the key-value pair provided does not exist or the value
    is null, the non-null value provided replaces the current one; this
    method can be used for aggregating several values; for example, it
    can be used for concatenating the following string values:
    map.merge(key, value, String::concat); we will explain what
    String::concat means in [Chapter
    13](https://subscription.packtpub.com/book/programming/9781789957051/13),
    *Functional Programming.*
-   default V compute(K key, BiFunction\<K,V,V\> remappingFunction): It
    computes a new value using the function provided.
-   default V computeIfAbsent(K key, Function\<K,V\> mappingFunction):
    It computes a new value using the function provided only if the
    provided key is not already associated with a value, or the value is
    null.
-   default V computeIfPresent(K key, BiFunction\<K,V,V\>
    remappingFunction): This computes a new value using the function
    provided only if the provided key is already associated with a value
    and the value is not null.

This last group of *computing* and *merging* methods is rarely used. The
most popular, by far, are the V put(K key, V value) and V get(Object
key) methods, which allow the use of the main Map function of storing
key-value pairs and retrieving the value using the key. The Set\<K\>
keySet() method is often used for iterating over the map's key-value
pairs, although the entrySet() method seems a more natural way of doing
that. Here is an example:

```
Map<Integer, String> map = Map.of(1, "s1", 2, "s2", 3, "s3");for(Integer key: map.keySet()){    System.out.print(key + ", " + map.get(key) + ", ");                                     //prints: 3, s3, 2, s2, 1, s1,}for(Map.Entry e: map.entrySet()){    System.out.print(e.getKey() + ", " + e.getValue() + ", ");                                    //prints: 2, s2, 3, s3, 1, s1,}
```

The first of the for loops in the preceding code example uses the more
widespread way to access the key-pair values of a map by iterating over
the keys. The second for loop iterates over the set of entries, which,
in our opinion, is a more natural way to do it. Notice that the
printed-out values are not in the same order we have put them in the
map. That is because, since Java 9, the unmodifiable collections (that
is, what of() factory methods produce) have added randomization to the
order of Set elements. It changes the order of the elements between
different code executions. Such a design was done to make sure a
programmer does not rely on a certain order of Set elements, which is
not guaranteed for a set. 

Unmodifiable collections
------------------------

Please note that collections produced by the of() factory methods used
to be called **immutable** in Java 9, and **unmodifiable** since Java
10. That is because an immutable implies that you cannot change anything
in them, while, in fact, the collection elements can be changed if they
are modifiable objects. For example, let's build a collection of objects
of the Person1 class that appears as follows: 

```
class Person1 {    private int age;    private String name;    public Person1(int age, String name) {        this.age = age;        this.name = name == null ? "" : name;    }    public void setName(String name){ this.name = name; }    @Override    public String toString() {        return "Person{age=" + age +                ", name=" + name + "}";    }}
```

For simplicity, we will create a list with one element only and
will then try to modify the element:

```
Person1 p1 = new Person1(45, "Bill");List<Person1> list = List.of(p1);//list.add(new Person1(22, "Bob")); //UnsupportedOperationExceptionSystem.out.println(list);        //prints: [Person{age=45, name=Bill}]p1.setName("Kelly");       System.out.println(list);        //prints: [Person{age=45, name=Kelly}]
```

As you can see, although it is not possible to add an element to the
list created by the of() factory method, its element can still be
modified if the reference to the element exists outside the list.



Data Structures, Generics, and Popular Utilities
================================================

There are two classes with static methods handling collections that are
very popular and helpful:

-   java.util.Collections
-   org.apache.commons.collections4.CollectionUtils

The fact that the methods are static means they do not depend on the
object state, so they are also called **stateless methods**, or
**utilities methods**.

java.util.Collections class
---------------------------

There are many methods in the Collections class that manage collections,
and analyze, sort, and compare them. There are more than 70 of them, so
we do not have a chance to talk about all of them. Instead, we are going
to look at the ones most often used by mainstream application
developers:

-   static copy(List\<T\> dest, List\<T\> src): This copies elements of
    the src list to the dest list and preserves the order of the
    elements and their position in the list; the destination dest list
    size has to be equal to, or bigger than, the src list size,
    otherwise a runtime exception is raised; here is an example of this
    method usage:

```
List<String> list1 = Arrays.asList("s1","s2");List<String> list2 = Arrays.asList("s3", "s4", "s5");Collections.copy(list2, list1);System.out.println(list2);    //prints: [s1, s2, s5]
```

-   static void sort(List\<T\> list): This sorts the list in the order
    according to the compareTo(T) method implemented by each element
    (called **natural ordering**); only accepts lists with elements that
    implement the Comparable interface (which requires implementation of
    the compareTo(T) method); in the example that follows, we use
    List\<String\> because the String class implements Comparable:

```
//List<String> list = List.of("a", "X", "10", "20", "1", "2");List<String> list = Arrays.asList("a", "X", "10", "20", "1", "2");Collections.sort(list);System.out.println(list);         //prints: [1, 10, 2, 20, X, a]
```

Note that we could not use the List.of() method to create a list because
the list would be unmodifiable and its order could not be changed. Also,
look at the resulting order: numbers come first, then capital letters,
followed by lowercase letters. That is because the compareTo() method in
the String class uses code points of the characters to establish the
order. Here is the code that demonstrates it:

```
List<String> list = Arrays.asList("a", "X", "10", "20", "1", "2");Collections.sort(list);System.out.println(list);     //prints: [1, 10, 2, 20, X, a]list.forEach(s -> {    for(int i = 0; i < s.length(); i++){        System.out.print(" " + Character.codePointAt(s, i));    }    if(!s.equals("a")) {        System.out.print(",");   //prints: 49, 49 48, 50, 50 48, 88, 97    }});
```

As you can see, the order is defined by the value of the code points of
the characters that compose the string.

-   static void sort(List\<T\> list, Comparator\<T\> comparator): This
    sorts the order of the list according to the Comparator object
    provided, irrespective of whether the list elements implement
    the Comparable interface or not; as an example, let's sort a list
    that consists of objects in the Person class:

```
class Person  {    private int age;    private String name;    public Person(int age, String name) {        this.age = age;        this.name = name == null ? "" : name;    }    public int getAge() { return this.age; }    public String getName() { return this.name; }    @Override    public String toString() {        return "Person{name=" + name + ", age=" + age + "}";    }}
```

And here is a Comparator class to sort the list of Person objects:

```
class ComparePersons implements Comparator<Person> {    public int compare(Person p1, Person p2){        int result = p1.getName().compareTo(p2.getName());        if (result != 0) { return result; }        return p1.age - p2.getAge();    }}
```

Now, we can use the Person and ComparePersons classes as follows:

```
List<Person> persons = Arrays.asList(new Person(23, "Jack"),        new Person(30, "Bob"), new Person(15, "Bob"));Collections.sort(persons, new ComparePersons());System.out.println(persons);    //prints: [Person{name=Bob, age=15},                                            Person{name=Bob, age=30},                                            Person{name=Jack, age=23}]
```

As we have mentioned already, there are many more utilities in
the Collections class, so we recommend you look through the related
documentation at least once and understand all its capabilities.

CollectionUtils class
---------------------

The org.apache.commons.collections4.CollectionUtils class in the Apache
commons project contains static stateless methods that complement the
methods of the java.util.Collections class. They help to search,
process, and compare Java collections.

To use this class, you would need to add the following dependency to the
Maven pom.xml configuration file:

```
 <dependency>    <groupId>org.apache.commons</groupId>    <artifactId>commons-collections4</artifactId>    <version>4.1</version> </dependency>
```

There are many methods in this class, and more methods will probably be
added over time. These utilities are created in addition to
the Collections methods, so they are more complex and nuanced and do not
fit the scope of this book. To give you an idea of the methods available
in the CollectionUtils class, here are some brief descriptions of the
methods grouped according to their functionality:

-   Methods that retrieve an element from a collection
-   Methods that add an element or a group of elements to a collection
-   Methods that merge Iterable elements into a collection
-   Methods that remove or retain elements with or without criteria
-   Methods that compare two collections
-   Methods that transform a collection
-   Methods that select from, and filter, a collection
-   Methods that generate the union, intersection, or difference of two
    collections
-   Methods that create an immutable empty collection
-   Methods that check collection size and emptiness
-   A method that reverses an array

This last method should probably belong to the utility class that
handles arrays. And that is what we are going to discuss now.



Data Structures, Generics, and Popular Utilities
================================================

There are two classes with static methods handling collections that are
very popular and helpful:

-   java.util.Arrays
-   org.apache.commons.lang3.ArrayUtils

We will briefly review each of them.

java.util.Arrays class
----------------------

We have already used the java.util.Arrays class several times. It is the
primary utility class for arrays management. This utility class used to
be very popular because of the asList(T...a) method. It was the most
compact way of creating and initializing a collection:

```
List<String> list = Arrays.asList("s0", "s1");Set<String> set = new HashSet<>(Arrays.asList("s0", "s1");
```

It is still a popular way of creating a modifiable list. We used it,
too. However, after a List.of() factory method was introduced and
the Arrays class declined substantially.

Nevertheless, if you need to manage arrays, then the Arrays class may be
a big help. It contains more than 160 methods. Most of them are
overloaded with different parameters and array types. If we group them
by method name, there will be 21 groups. And if we further group them by
functionality, only the following 10 groups will cover all the Arrays
class functionality:

-   asList(): This creates an ArrayList object based on the provided
    array or comma-separated list of parameters.
-   binarySearch(): The searches an array or only the specified
    (according to the range of indices) part of it
-   compare(), mismatch(), equals(), and deepEquals(): This compares two
    arrays or their elements (according to the range of indices).

-   copyOf() and copyOfRange(): This copies all arrays or only the
    specified (according to the range of indices) part of it.
-   hashcode() and deepHashCode(): This generates the hash code value
    based on the array provided.
-   toString() and deepToString(): This creates a String representation
    of an array.
-   fill(), setAll(), parallelPrefix(), and parallelSetAll(): This sets
    a value (fixed or generated by the function provided) for every
    element of an array or those specified according to the range of
    indices.
-   sort() and parallelSort(): This sorts elements of an array or only
    part of it (specified according to the range of indices).
-   splititerator(): This returns a Splititerator object for parallel
    processing of an array or part of it (specified according to the
    range of indices).
-   stream(): This generates a stream of array elements or some of them
    (specified according to the range of indices); see [Chapter
    14](https://subscription.packtpub.com/book/programming/9781789957051/14),
    *Java Standard Streams.*

All of these methods are helpful, but we would like to draw your
attention to equals(a1, a2) methods and deepEquals(a1, a2). They are
particularly helpful for the array comparison because an array object
cannot implement an equals() custom method and uses the implementation
of the Object class instead (that compares only references).
The equals(a1, a2) and deepEquals(a1, a2) methods allow a comparison of
not just a1 and a2 references, but use the equals() method to compare
elements as well. The following are the code examples that demonstrate
how these methods work:

```
String[] arr1 = {"s1", "s2"};String[] arr2 = {"s1", "s2"};System.out.println(arr1.equals(arr2));             //prints: falseSystem.out.println(Arrays.equals(arr1, arr2));     //prints: trueSystem.out.println(Arrays.deepEquals(arr1, arr2)); //prints: trueString[][] arr3 = {{"s1", "s2"}};String[][] arr4 = {{"s1", "s2"}};System.out.println(arr3.equals(arr4));             //prints: falseSystem.out.println(Arrays.equals(arr3, arr4));     //prints: falseSystem.out.println(Arrays.deepEquals(arr3, arr4)); //prints: true
```

As you can see, Arrays.deepEquals() returns true every time two equal
arrays are compared when every element of one array equals the element
of another array in the same position, while the Arrays.equals()
method does the same, but for one-dimensional arrays only.

ArrayUtils class
----------------

The org.apache.commons.lang3.ArrayUtils class complements
the java.util.Arrays class by adding new methods to the array managing
the toolkit and the ability to handle null in cases when, otherwise,
NullPointerException could be thrown. To use this class, you would need
to add the following dependency to the Maven pom.xml configuration file:

```
<dependency>   <groupId>org.apache.commons</groupId>   <artifactId>commons-lang3</artifactId>   <version>3.8.1</version></dependency>
```

The ArrayUtils class has around 300 overloaded methods that can be
collected in the following 12 groups:

-   add(), addAll(), and insert(): This adds elements to an array.
-   clone(): This clones an array, similar to the copyOf() method of
    the Arrays class and the arraycopy() method of java.lang.System.
-   getLength(): This returns an array length or 0, when the array
    itself is null.
-   hashCode(): This calculates the hash value of an array, including
    nested arrays.
-   contains(), indexOf(), and lastIndexOf(): The searches an array.
-   isSorted(), isEmpty, and isNotEmpty(): The checks an array and
    handles null.
-   isSameLength() and isSameType(): this compares arrays.
-   nullToEmpty(): This converts a null array to an empty one.
-   remove(), removeAll(), removeElement(), removeElements(), and
    removeAllOccurances(): This removes certain or all elements.
-   reverse(), shift(), shuffle(), swap(): This changes the order of
    array elements.
-   subarray(): This extracts part of an array according to the range of
    indices.
-   toMap(), toObject(), toPrimitive(), toString(), toStringArray():
    This converts an array to another type and handles null values.



Data Structures, Generics, and Popular Utilities
================================================

The two utilities described in this section are as follows:

-   java.util.Objects
-   org.apache.commons.lang3.ObjectUtils

They are especially useful during class creation, so we will concentrate
largely on the methods related to this task.

java.util.Objects class
-----------------------

The Objects class has only 17 methods that are all static. Let's look at
some of them while applying them to the Person class. Let's assume this
class will be an element of a collection, which means it has to
implement the equals() and hashCode() methods:

```
class Person {    private int age;    private String name;    public Person(int age, String name) {        this.age = age;        this.name = name;    }    public int getAge(){ return this.age; }    public String getName(){ return this.name; }    @Override    public boolean equals(Object o) {        if (this == o) return true;        if (o == null) return false;        if(!(o instanceof Person)) return false;        Person person = (Person)o;        return age == person.getAge() &&                Objects.equals(name, person.getName());     }    @Override    public int hashCode(){        return Objects.hash(age, name);    }}
```

Notice that we do not check name property for null because
Object.equals() does not break when any of the parameters is null. It
just does the job of comparing the objects. If only one of them is null,
it returns false. If both are null, it returns true.

Using Object.equals() is a safe way to implement the
equals() method. But if you need to compare to objects that may be
arrays, it is better to use the Objects.deepEquals() method because it
not only handles null, as the Object.equals() method does, but also
compares values of all array elements, even if the array is
multidimensional:

```
String[][] x1 = {{"a","b"},{"x","y"}};String[][] x2 = {{"a","b"},{"x","y"}};String[][] y =  {{"a","b"},{"y","y"}};System.out.println(Objects.equals(x1, x2));      //prints: falseSystem.out.println(Objects.equals(x1, y));       //prints: falseSystem.out.println(Objects.deepEquals(x1, x2));  //prints: trueSystem.out.println(Objects.deepEquals(x1, y));   //prints: false
```

The Objects.hash() method handles null values too. One important thing
to remember is that the list of the properties compared in the equals()
method has to match the list of the properties passed into
Objects.hash() as parameters. Otherwise, two equal Person objects will
have different hash values, which makes hash-based collections work
incorrectly.

Another thing worth noticing is that there is another
hash-related Objects.hashCode() method that accepts only one parameter.
But the value it generates is not equal to the value generated
by Objects.hash() with only one parameter. Observe the following, for
example:

```
System.out.println(Objects.hash(42) == Objects.hashCode(42));                                                          //prints: falseSystem.out.println(Objects.hash("abc") == Objects.hashCode("abc"));                                                          //prints: false
```

To avoid this caveat, always use Objects.hash().

Another potential source of confusion is demonstrated by the following
code:

```
System.out.println(Objects.hash(null));      //prints: 0System.out.println(Objects.hashCode(null));  //prints: 0System.out.println(Objects.hash(0));         //prints: 31System.out.println(Objects.hashCode(0));     //prints: 0
```

As you can see, the Objects.hashCode() method generates the same hash
value for null and 0, which can be problematic for some algorithms based
on the hash value.

static \<T\> int compare (T a, T b, Comparator\<T\> c) is another
popular method that returns 0 (if the arguments are equal), otherwise
the result of c.compare(a, b). It is very useful for implementing
the Comparable interface (establishing a natural order for a custom
object sorting). Observe the following, for example:

```
class Person implements Comparable<Person> {    private int age;    private String name;    public Person(int age, String name) {        this.age = age;        this.name = name;    }    public int getAge(){ return this.age; }    public String getName(){ return this.name; }    @Override    public int compareTo(Person p){        int result = Objects.compare(name, p.getName(),                                         Comparator.naturalOrder());        if (result != 0) {            return result;        }        return Objects.compare(age, p.getAge(),                                          Comparator.naturalOrder());    }}
```

This way, you can easily change the sorting algorithm by setting
the Comparator.reverseOrder() value, or by adding Comparator.nullFirst()
or Comparator.nullLast().

Also, the Comparator implementation we used in the previous section can
be made more flexible by using the Objects.compare() method:

```
class ComparePersons implements Comparator<Person> {    public int compare(Person p1, Person p2){        int result = Objects.compare(p1.getName(), p2.getName(),                                         Comparator.naturalOrder());        if (result != 0) {            return result;        }        return Objects.compare(p1.getAge(), p2.getAge(),                                          Comparator.naturalOrder());    }}
```

Finally, the last two methods of the Objects class that we are going to
discuss are the methods that generate a string representation of an
object. They come in handy when you need to call a toString() method on
an object but are not sure if the object reference is null. Observe the
following, for example:

```
List<String> list = Arrays.asList("s1", null);for(String e: list){    //String s = e.toString();  //NullPointerException}
```

In the preceding example, we know the exact value of each element. But
imagine a scenario where the list is passed into the method as a
parameter. Then, we are forced to write something as follows:

```
void someMethod(List<String> list){    for(String e: list){        String s = e == null ? "null" : e.toString();    }
```

It seems like this is not a big deal. But after writing such code a
dozen times, a programmer naturally thinks about some kind of utility
method that does all of that, and that is when the following two methods
of the Objects class help:

-   static String toString(Object o): The returns the result of calling
    toString() on the parameter when it is not null, and returns null
    when the parameter value is null.

-   static String toString(Object o, String nullDefault): This returns
    the result of calling toString() on the first parameter when it is
    not null, and returns the second nullDefault parameter value when
    the first parameter value is null.

The following code demonstrates these two methods:

```
List<String> list = Arrays.asList("s1", null);for(String e: list){    String s = Objects.toString(e);    System.out.print(s + " ");          //prints: s1 null}for(String e: list){    String s = Objects.toString(e, "element was null");    System.out.print(s + " ");          //prints: s1 element was null}
```

As of the time of writing, the Objects class has 17 methods. We
recommend you become familiar with them so as to avoid writing your own
utilities in the event that the same utility already exists. 

ObjectUtils class
-----------------

The last statement of the previous section applies to
the org.apache.commons.lang3.ObjectUtils class of the Apache Commons
library that complements the methods of the java.util.Objects
class described in the preceding section. The scope of this book and its
allotted size does not allow for a detailed review of all the methods
under the ObjectUtils class, so we will describe them briefly in groups
according to their related functionality. To use this class, you would
need to add the following dependency to the Maven pom.xml configuration
file:

```
<dependency>    <groupId>org.apache.commons</groupId>    <artifactId>commons-lang3</artifactId>    <version>3.8.1</version></dependency>
```

All the methods of the ObjectUtils class can be organized into seven
groups:

-   Object cloning methods.
-   Methods that support a comparison of two objects.
-   The notEqual() method, which compares two objects for inequality,
    where either one or both objects may be null.
-   Several identityToString() methods that generate a String
    representation of the provided object as if produced by
    the toString(), which is a default method of the Object base
    class and, optionally, append it to another object.
-   The allNotNull() and anyNotNull() methods, which analyze an array of
    objects for null
-   The firstNonNull() and defaultIfNull() methods, which analyze an
    array of objects and return the first not-null object or default
    value.
-   The max(), min(), median(), and mode() methods, which analyze an
    array of objects and return one of them that corresponds to the
    method name.



Data Structures, Generics, and Popular Utilities
================================================

There are many classes in the java.time package and its sub-packages.
They were introduced as a replacement for other (older packages) that
handled date and time. The new classes are thread-safe (hence, better
suited for multithreaded processing) and what is also important is that
they are more consistently designed and easier to understand. Also, the
new implementation follows **International Standard Organization**
(**ISO**) standards as regards the date and time formats, but allows any
other custom format to be used as well.

We will describe the main five classes, and demonstrate how to use them:

-   java.time.LocalDate
-   java.time.LocalTime
-   java.time.LocalDateTime
-   java.time.Period
-   java.time.Duration

All these, and other classes of the java.time package, as well as its
sub-packages, are rich in various functionality that cover all practical
cases. But we are not going to discuss all of them; we will just
introduce the basics and the most popular use cases.

LocalDate class
---------------

LocalDate class does not carry time. It represents a date in ISO 8601
format (YYYY-MM- DD):

```
System.out.println(LocalDate.now()); //prints: 2019-03-04
```

That is the current date in this location at the time of writing. The
value was picked up from the computer clock. Similarly, you can get the
current date in any other time zone using that static now(ZoneId
zone) method. The ZoneId object can be constructed using the
static ZoneId.of(String zoneId) method, where String zoneId is any
of the string values returned by the ZonId.getAvailableZoneIds() method:

```
Set<String> zoneIds = ZoneId.getAvailableZoneIds();for(String zoneId: zoneIds){    System.out.println(zoneId);}
```

The preceding code prints almost 600 time zone IDs. Here are a few of
them:

```
Asia/AdenEtc/GMT+9Africa/NairobiAmerica/MarigotPacific/HonoluluAustralia/HobartEurope/LondonAmerica/Indiana/PetersburgAsia/YerevanEurope/BrusselsGMTChile/ContinentalPacific/YapCETEtc/GMT-1Canada/YukonAtlantic/St_HelenaLibyaUS/Pacific-NewCubaIsraelGB-EireGBMexico/GeneralUniversalZuluIranNavajoEgyptEtc/UTCSystemV/AST4ADTAsia/Tokyo
```

Let's try to use "Asia/Tokyo", for example:

```
ZoneId zoneId = ZoneId.of("Asia/Tokyo");System.out.println(LocalDate.now(zoneId)); //prints: 2019-03-05
```

An object of LocalDate can represent any date in the past, or in the
future too, using the following methods:

-   LocalDate parse(CharSequence text): This constructs an object from a
    string in ISO 8601 format (YYYY-MM-DD).

-   LocalDate parse(CharSequence text, DateTimeFormatter formatter):
    This constructs an object from a string in a format specified by
    the DateTimeFormatter object that has a rich system of patterns and
    many predefined formats as well; the following are a few of them:

-   -   BASIC\_ISO\_DATE, for example, 20111203
    -   ISO\_LOCAL\_DATE ISO, for example, 2011-12-03
    -   ISO\_OFFSET\_DATE, for example, 2011-12-03+01:00
    -   ISO\_DATE, for example, 2011-12-03+01:00; 2011-12-03
    -   ISO\_LOCAL\_TIME, for example, 10:15:30
    -   ISO\_OFFSET\_TIME, for example, 10:15:30+01:00
    -   ISO\_TIME, for example, 10:15:30+01:00; 10:15:30
    -   ISO\_LOCAL\_DATE\_TIME, for example, 2011-12-03T10:15:30

-   LocalDate of(int year, int month, int dayOfMonth): This constructs
    an object from a year, month, and day.
-   LocalDate of(int year, Month month, int dayOfMonth): This constructs
    an object from a year, month (enum constant), and day.
-   LocalDate ofYearDay(int year, int dayOfYear): This constructs an
    object form from a year and day-of-year.

The following code demonstrates the preceding methods listed:

```
LocalDate lc1 = LocalDate.parse("2020-02-23");System.out.println(lc1);                     //prints: 2020-02-23LocalDate lc2 =            LocalDate.parse("20200223", DateTimeFormatter.BASIC_ISO_DATE);System.out.println(lc2);                     //prints: 2020-02-23DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");LocalDate lc3 =  LocalDate.parse("23/02/2020", formatter);System.out.println(lc3);                     //prints: 2020-02-23LocalDate lc4 =  LocalDate.of(2020, 2, 23);System.out.println(lc4);                     //prints: 2020-02-23LocalDate lc5 =  LocalDate.of(2020, Month.FEBRUARY, 23);System.out.println(lc5);                     //prints: 2020-02-23LocalDate lc6 = LocalDate.ofYearDay(2020, 54);System.out.println(lc6);                     //prints: 2020-02-23
```

A LocalDate object can provide various values:

```
LocalDate lc = LocalDate.parse("2020-02-23");System.out.println(lc);                  //prints: 2020-02-23System.out.println(lc.getYear());        //prints: 2020System.out.println(lc.getMonth());       //prints: FEBRUARYSystem.out.println(lc.getMonthValue());  //prints: 2System.out.println(lc.getDayOfMonth());  //prints: 23System.out.println(lc.getDayOfWeek());   //prints: SUNDAYSystem.out.println(lc.isLeapYear());     //prints: trueSystem.out.println(lc.lengthOfMonth());  //prints: 29System.out.println(lc.lengthOfYear());   //prints: 366
```

The LocalDate object can be modified as follows:

```
LocalDate lc = LocalDate.parse("2020-02-23");System.out.println(lc.withYear(2021)); //prints: 2021-02-23System.out.println(lc.withMonth(5));       //prints: 2020-05-23System.out.println(lc.withDayOfMonth(5));  //prints: 2020-02-05System.out.println(lc.withDayOfYear(53));  //prints: 2020-02-22System.out.println(lc.plusDays(10));       //prints: 2020-03-04System.out.println(lc.plusMonths(2));      //prints: 2020-04-23System.out.println(lc.plusYears(2));       //prints: 2022-02-23System.out.println(lc.minusDays(10));      //prints: 2020-02-13System.out.println(lc.minusMonths(2));     //prints: 2019-12-23System.out.println(lc.minusYears(2));      //prints: 2018-02-23
```

The LocalDate objects can be compared as follows:

```
LocalDate lc1 = LocalDate.parse("2020-02-23");LocalDate lc2 = LocalDate.parse("2020-02-22");System.out.println(lc1.isAfter(lc2));       //prints: trueSystem.out.println(lc1.isBefore(lc2));      //prints: false
```

There are many other helpful methods in the LocalDate class. If you have
to work with dates, we recommend that you read the API of this class and
other classes of the java.time package and its sub-packages.

LocalTime class
---------------

The LocalTime class contains time without a date. It has similar methods
to the methods of the LocalDate class. Here is how an object of
the LocalTime class can be created:

```
System.out.println(LocalTime.now());         //prints: 21:15:46.360904ZoneId zoneId = ZoneId.of("Asia/Tokyo");System.out.println(LocalTime.now(zoneId));   //prints: 12:15:46.364378LocalTime lt1 =  LocalTime.parse("20:23:12");System.out.println(lt1);                     //prints: 20:23:12LocalTime lt2 = LocalTime.of(20, 23, 12);System.out.println(lt2);                     //prints: 20:23:12
```

Each component of time value can be extracted from a LocalTime object as
follows:

```
LocalTime lt2 =  LocalTime.of(20, 23, 12);System.out.println(lt2);                     //prints: 20:23:12System.out.println(lt2.getHour());           //prints: 20System.out.println(lt2.getMinute());         //prints: 23System.out.println(lt2.getSecond());         //prints: 12System.out.println(lt2.getNano());           //prints: 0
```

The object of the LocalTime class can be modified as follows:

```
LocalTime lt2 = LocalTime.of(20, 23, 12);System.out.println(lt2.withHour(3)); //prints: 03:23:12System.out.println(lt2.withMinute(10)); //prints: 20:10:12System.out.println(lt2.withSecond(15)); //prints: 20:23:15System.out.println(lt2.withNano(300)); //prints: 20:23:12.000000300System.out.println(lt2.plusHours(10));       //prints: 06:23:12System.out.println(lt2.plusMinutes(2));      //prints: 20:25:12System.out.println(lt2.plusSeconds(2));      //prints: 20:23:14System.out.println(lt2.plusNanos(200));      //prints: 20:23:12.000000200System.out.println(lt2.minusHours(10));      //prints: 10:23:12System.out.println(lt2.minusMinutes(2));     //prints: 20:21:12System.out.println(lt2.minusSeconds(2));     //prints: 20:23:10System.out.println(lt2.minusNanos(200));     //prints: 20:23:11.999999800
```

And two objects of the LocalTime class can also be compared, as follows:

```
LocalTime lt2 =  LocalTime.of(20, 23, 12);LocalTime lt4 =  LocalTime.parse("20:25:12");System.out.println(lt2.isAfter(lt4));       //prints: falseSystem.out.println(lt2.isBefore(lt4));      //prints: true
```

There are many other helpful methods in the LocalTime class. If you have
to work with dates, we recommend that you read the API of this class and
other classes of the java.time package and its sub-packages.

LocalDateTime class
-------------------

The LocalDateTime class contains both the date and time and has all the
methods the LocalDate and LocalTime classes have, so we are not going to
repeat them here. We will only show how an object of
the LocalDateTime class can be created:

```
System.out.println(LocalDateTime.now());                                          //prints: 2019-03-04T21:59:00.142804ZoneId zoneId = ZoneId.of("Asia/Tokyo");System.out.println(LocalDateTime.now(zoneId));                                    //prints: 2019-03-05T12:59:00.146038LocalDateTime ldt1 = LocalDateTime.parse("2020-02-23T20:23:12");System.out.println(ldt1);                 //prints: 2020-02-23T20:23:12DateTimeFormatter formatter =        DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");LocalDateTime ldt2 =        LocalDateTime.parse("23/02/2020 20:23:12", formatter);System.out.println(ldt2);                 //prints: 2020-02-23T20:23:12LocalDateTime ldt3 = LocalDateTime.of(2020, 2, 23, 20, 23, 12);System.out.println(ldt3);                 //prints: 2020-02-23T20:23:12LocalDateTime ldt4 =        LocalDateTime.of(2020, Month.FEBRUARY, 23, 20, 23, 12);System.out.println(ldt4);                 //prints: 2020-02-23T20:23:12LocalDate ld = LocalDate.of(2020, 2, 23);LocalTime lt = LocalTime.of(20, 23, 12);LocalDateTime ldt5 = LocalDateTime.of(ld, lt);System.out.println(ldt5);                 //prints: 2020-02-23T20:23:12
```

There are many other helpful methods in the LocalDateTime class. If you
have to work with dates, we recommend that you read the API of this
class and other classes of the java.time package and its sub-packages.

Period and Duration classes
---------------------------

The java.time.Period and java.time.Duration classes are designed to
contain an amount of time:

-   A Period object contains an amount of time in units of years,
    months, and days.
-   A Duration object contains an amount of time in hours, minutes,
    seconds, and nanoseconds.

The following code demonstrates their creation and usage using the
LocalDateTime class, but the same methods exist in the LocalDate (for
Period) and LocalTime (for Duration) classes:

```
LocalDateTime ldt1 = LocalDateTime.parse("2020-02-23T20:23:12");LocalDateTime ldt2 = ldt1.plus(Period.ofYears(2));System.out.println(ldt2);      //prints: 2022-02-23T20:23:12
```

The following methods work the same way:

```
LocalDateTime ldt = LocalDateTime.parse("2020-02-23T20:23:12");ldt.minus(Period.ofYears(2));ldt.plus(Period.ofMonths(2));ldt.minus(Period.ofMonths(2));ldt.plus(Period.ofWeeks(2));ldt.minus(Period.ofWeeks(2));ldt.plus(Period.ofDays(2));ldt.minus(Period.ofDays(2));ldt.plus(Duration.ofHours(2));ldt.minus(Duration.ofHours(2));ldt.plus(Duration.ofMinutes(2));ldt.minus(Duration.ofMinutes(2));ldt.plus(Duration.ofMillis(2));ldt.minus(Duration.ofMillis(2));
```

Some other methods of creating and using Period objects are demonstrated
by the following code:

```
LocalDate ld1 =  LocalDate.parse("2020-02-23");LocalDate ld2 =  LocalDate.parse("2020-03-25");Period period = Period.between(ld1, ld2);System.out.println(period.getDays());       //prints: 2System.out.println(period.getMonths());     //prints: 1System.out.println(period.getYears());      //prints: 0System.out.println(period.toTotalMonths()); //prints: 1period = Period.between(ld2, ld1);System.out.println(period.getDays());       //prints: -2
```

Duration objects can be similarly created and used:

```
LocalTime lt1 =  LocalTime.parse("10:23:12");LocalTime lt2 =  LocalTime.parse("20:23:14");Duration duration = Duration.between(lt1, lt2);System.out.println(duration.toDays());     //prints: 0System.out.println(duration.toHours());    //prints: 10System.out.println(duration.toMinutes());  //prints: 600System.out.println(duration.toSeconds());  //prints: 36002System.out.println(duration.getSeconds()); //prints: 36002System.out.println(duration.toNanos());    //prints: 36002000000000System.out.println(duration.getNano());    //prints: 0
```

There are many other helpful methods in Period and Duration classes. If
you have to work with dates, we recommend that you read the API of this
class and other classes of the java.time package and its sub-packages.



Data Structures, Generics, and Popular Utilities
================================================

This chapter introduced the reader to the Java collections framework and
its three main interfaces: List, Set, and Map. Each of the interfaces
was discussed and its methods demonstrated with one of the implementing
classes. The generics were explained and demonstrated as well.
The equals() and hashCode() methods have to be implemented in order for
the object to be capable of being handled by Java collections correctly.

The Collections and CollectionUtils utility classes have many useful
methods for collection handling and were presented in examples, along
with Arrays, ArrayUtils, Objects, and ObjectUtils.

The class methods of the java.time package allow time/date values to be
managed, class was demonstrated in specific practical code snippets.

In the next chapter, we will overview the Java Class Library and some
external libraries, including those that support testing. Specifically,
we will explore the org.junit, org.mockito, org.apache.log4j, org.slf4j,
and org.apache.commons packages and their sub-packages.



