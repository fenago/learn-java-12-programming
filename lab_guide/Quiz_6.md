Quiz
================================================

1.  What is a Java collections framework? Select all that apply:
    a.  A collection of frameworks
    b.  Classes and interfaces of the [java.util] package
    c.  [List], [Set], and [Map] Interfaces
    d.  Classes and interfaces that implement a collection data
        structure


2.  What is generics in a collection? Select all that apply:

-   a.  A collection structure definition
    b.  The element type declaration
    c.  The type generalization
    d.  The mechanism that provides compile-time safety

3.  What are the limitations of the collection of [of()] factory
    methods? Select all that apply:
    a.  They do not allow a [null] element.
    b.  They do not allow elements to be added to the initialized
        collection.
    c.  They do not allow elements to be added to the initialized
        collection.
    d.  They do not allow modification of elements in relation to the
        initialized collection.


4.  What does the implementation of the [java.lang.Iterable]
    interface allow? Select all that apply:
    a.  It allows elements of the collection to be accessed one by one.
    b.  It allows the collection to be used in [FOR] statements.
    c.  It allows the collection to be used in [WHILE] statements.
    d.  It allows the collection to be used in [DO\...WHILE]
        statements.


5.  What does the implementation of
    the [java.util.Collection] interface allow? Select all that
    apply:
    a.  Addition to the collection of elements from another collection
    b.  Removal from the collection of objects that are elements of
        another collection
    c.  Modification of just only those elements of the collection that
        belong to another collection
    d.  Removal from the collection of objects that do not belong to
        another collection


6.  Select all the correct statements pertaining to the
    [List] interface methods:

-   a.  [E get(int index)]: This returns the element at the
        specified position in the list.
    b.  [E remove(int index)]: This removes the element at the
        specified position in the list; it returns the removed element.
    c.  [static List\<E\> copyOf(Collection\<E\> coll)]: This
        returns an unmodifiable [List] containing the elements of
        the given [Collection] and preserves their order.
    d.  [int indexOf(Object o)]: This returns the position of the
        specified element in the list.

7.  Select all the correct statements pertaining to the
    [Set] interface methods:

-   a.  [E get(int index)]: This returns the element at the
        specified position in the list.
    b.  [E remove(int index)]: This removes the element at the
        specified position in the list; it returns the removed element.
    c.  [static Set\<E\> copyOf(Collection\<E\> coll)]: This
        returns an unmodifiable [Set] containing the elements of
        the given [Collection].
    d.  [int indexOf(Object o)]: This returns the position of the
        specified element in the list.

8.  Select all the correct statements pertaining to the
    [Map] interface methods:
    a.  [int size()]: This returns the count of key-value pairs
        stored in the map; when the [isEmpty()] method
        returns [true], this method returns [0].
    b.  [V remove(Object key)]: This removes both key and value
        from the map; returns value, or [null] if there is no such
        key or the value is [null].
    c.  [default boolean remove(Object key, Object value)]: This
        removes the key-value pair if such a pair exists in the map;
        returns [true] if the value is removed.
    d.  [default boolean replace(K key, V oldValue, V newValue)]:
        This replaces the [oldValue] value with
        the [newValue] provided if the key provided is currently
        mapped to the [oldValue]; it returns [true] if
        the [oldValue] was replaced; otherwise, it
        returns [false].


9.  Select all correct statements pertaining to the [static void
    sort(List\<T\> list, Comparator\<T\> comparator)] method of
    the [Collections] class:
    a.  It sorts the list\'s natural order if the list elements
        implement the [Comparable] interface.
    b.  It sorts the list\'s order according to
        the [Comparator] object provided.
    c.  It sorts the list\'s order according to
        the [Comparator] object provided if list elements
        implement the [Comparable] interface.
    d.  It sorts the list\'s order according to the
        provided [Comparator] object irrespective of whether the
        list elements implement the [Comparable] interface.


10. What is the outcome of executing the following code?


```
List<String> list1 = Arrays.asList("s1","s2", "s3");
List<String> list2 = Arrays.asList("s3", "s4");
Collections.copy(list1, list2);
System.out.println(list1);    
```

-   a.  [\[s1, s2, s3, s4\]]
    b.  [\[s3, s4, s3\]]
    c.  [\[s1, s2, s3, s3, s4\]]
    d.  [\[s3, s4\]]

11. What is the functionality of the [CollectionUtils] class
    methods? Select all that apply:
    a.  It matches the functionality of the [Collections] class
        methods, but by handling [null.]
    b.  It complements the functionality of
        the [Collections] class methods.
    c.  It searches, processes, and compares Java collections in a way
        that the [Collections] class methods do not do.
    d.  It duplicates the functionality of the [Collections] class
        methods.


12. What is the result of executing the following code?


```
Integer[][] ar1 = {{42}};
Integer[][] ar2 = {{42}};
System.out.print(Arrays.equals(ar1, ar2) + " "); 
System.out.println(Arrays.deepEquals(arr3, arr4)); 
```

-   a.  false true
    b.  false false
    c.  true false
    d.  true true

13. What is the result of executing the following code?


```
String[] arr1 = { "s1", "s2" };
String[] arr2 = { null };
String[] arr3 = null;
System.out.print(ArrayUtils.getLength(arr1) + " "); 
System.out.print(ArrayUtils.getLength(arr2) + " "); 
System.out.print(ArrayUtils.getLength(arr3) + " "); 
System.out.print(ArrayUtils.isEmpty(arr2) + " "); 
System.out.print(ArrayUtils.isEmpty(arr3));
```

-   a.  1 2 0 false true
    b.  2 1 1 false true
    c.  2 1 0 false true
    d.  2 1 0 true false

14. What is the result of executing the following code?


```
 String str1 = "";
 String str2 = null;
 System.out.print((Objects.hash(str1) == 
                   Objects.hashCode(str2)) + " ");
 System.out.print(Objects.hash(str1) + " ");
 System.out.println(Objects.hashCode(str2) + " "); 
```

-   a.  true 0 0
    b.  Error
    c.  false -1 0
    d.  false 31 0

15. What is the result of executing the following code?


```
String[] arr = {"c", "x", "a"};
System.out.print(ObjectUtils.min(arr) + " ");
System.out.print(ObjectUtils.median(arr) + " ");
System.out.println(ObjectUtils.max(arr));
```

-   a.  c x a
    b.  a c x
    c.  x c a
    d.  a x c

16. What is the result of executing the following code?


```
LocalDate lc = LocalDate.parse("1900-02-23");
System.out.println(lc.withYear(21)); 
```

-   a.  [1921-02-23]
    b.  [21-02-23]
    c.  [0021-02-23]
    d.  Error

17. What is the result of executing the following code?


```
LocalTime lt2 = LocalTime.of(20, 23, 12);
System.out.println(lt2.withNano(300));      
```

-   a.  [20:23:12.000000300]
    b.  [20:23:12.300]
    c.  [20:23:12:300]
    d.  Error

18. What is the result of executing the following code?


```
LocalDate ld = LocalDate.of(2020, 2, 23);
LocalTime lt = LocalTime.of(20, 23, 12);
LocalDateTime ldt = LocalDateTime.of(ld, lt);
System.out.println(ldt);                
```

-   a.  [2020-02-23 20:23:12]
    b.  [2020-02-23T20:23:12]
    c.  [2020-02-23:20:23:12]
    d.  Error

19. What is the result of executing the following code?


```
LocalDateTime ldt = LocalDateTime.parse("2020-02-23T20:23:12");
System.out.print(ldt.minus(Period.ofYears(2)) + " ");
System.out.print(ldt.plus(Duration.ofMinutes(12)) + " ");
System.out.println(ldt);
```

-   a.  [2020-02-23T20:23:12 2020-02-23T20:23:12
        2020-02-23T20:23:12]
    b.  [2020-02-23T20:23:12 2020-02-23T20:35:12
        2020-02-23T20:35:12]
    c.  [2018-02-23T20:23:12 2020-02-23T20:35:12
        2020-02-23T20:23:12]
    d.  [2018-02-23T20:23:12 2020-02-23T20:35:12
        2018-02-23T20:35:12]
