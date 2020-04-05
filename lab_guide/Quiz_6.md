Quiz
================================================

1.  What is a Java collections framework? Select all that apply:
    1.  A collection of frameworks
    2.  Classes and interfaces of the [java.util] package
    3.  [List], [Set], and [Map] Interfaces
    4.  Classes and interfaces that implement a collection data
        structure


2.  What is generics in a collection? Select all that apply:

    1.  A collection structure definition
    2.  The element type declaration
    3.  The type generalization
    4.  The mechanism that provides compile-time safety

3.  What are the limitations of the collection of [of()] factory
    methods? Select all that apply:
    1.  They do not allow a [null] element.
    2.  They do not allow elements to be added to the initialized
        collection.
    3.  They do not allow elements to be added to the initialized
        collection.
    4.  They do not allow modification of elements in relation to the
        initialized collection.


4.  What does the implementation of the [java.lang.Iterable]
    interface allow? Select all that apply:
    1.  It allows elements of the collection to be accessed one by one.
    2.  It allows the collection to be used in [FOR] statements.
    3.  It allows the collection to be used in [WHILE] statements.
    4.  It allows the collection to be used in [DO\...WHILE]
        statements.


5.  What does the implementation of
    the [java.util.Collection] interface allow? Select all that
    apply:
    1.  Addition to the collection of elements from another collection
    2.  Removal from the collection of objects that are elements of
        another collection
    3.  Modification of just only those elements of the collection that
        belong to another collection
    4.  Removal from the collection of objects that do not belong to
        another collection


6.  Select all the correct statements pertaining to the
    [List] interface methods:

    1.  [E get(int index)]: This returns the element at the
        specified position in the list.
    2.  [E remove(int index)]: This removes the element at the
        specified position in the list; it returns the removed element.
    3.  [static List\<E\> copyOf(Collection\<E\> coll)]: This
        returns an unmodifiable [List] containing the elements of
        the given [Collection] and preserves their order.
    4.  [int indexOf(Object o)]: This returns the position of the
        specified element in the list.

7.  Select all the correct statements pertaining to the
    [Set] interface methods:

    1.  [E get(int index)]: This returns the element at the
        specified position in the list.
    2.  [E remove(int index)]: This removes the element at the
        specified position in the list; it returns the removed element.
    3.  [static Set\<E\> copyOf(Collection\<E\> coll)]: This
        returns an unmodifiable [Set] containing the elements of
        the given [Collection].
    4.  [int indexOf(Object o)]: This returns the position of the
        specified element in the list.

8.  Select all the correct statements pertaining to the
    [Map] interface methods:
    1.  [int size()]: This returns the count of key-value pairs
        stored in the map; when the [isEmpty()] method
        returns [true], this method returns [0].
    2.  [V remove(Object key)]: This removes both key and value
        from the map; returns value, or [null] if there is no such
        key or the value is [null].
    3.  [default boolean remove(Object key, Object value)]: This
        removes the key-value pair if such a pair exists in the map;
        returns [true] if the value is removed.
    4.  [default boolean replace(K key, V oldValue, V newValue)]:
        This replaces the [oldValue] value with
        the [newValue] provided if the key provided is currently
        mapped to the [oldValue]; it returns [true] if
        the [oldValue] was replaced; otherwise, it
        returns [false].


9.  Select all correct statements pertaining to the [static void
    sort(List\<T\> list, Comparator\<T\> comparator)] method of
    the [Collections] class:
    1.  It sorts the list\'s natural order if the list elements
        implement the [Comparable] interface.
    2.  It sorts the list\'s order according to
        the [Comparator] object provided.
    3.  It sorts the list\'s order according to
        the [Comparator] object provided if list elements
        implement the [Comparable] interface.
    4.  It sorts the list\'s order according to the
        provided [Comparator] object irrespective of whether the
        list elements implement the [Comparable] interface.


10. What is the outcome of executing the following code?


```
List<String> list1 = Arrays.asList("s1","s2", "s3");
List<String> list2 = Arrays.asList("s3", "s4");
Collections.copy(list1, list2);
System.out.println(list1);    
```

    1.  [\[s1, s2, s3, s4\]]
    2.  [\[s3, s4, s3\]]
    3.  [\[s1, s2, s3, s3, s4\]]
    4.  [\[s3, s4\]]

11. What is the functionality of the [CollectionUtils] class
    methods? Select all that apply:
    1.  It matches the functionality of the [Collections] class
        methods, but by handling [null.]
    2.  It complements the functionality of
        the [Collections] class methods.
    3.  It searches, processes, and compares Java collections in a way
        that the [Collections] class methods do not do.
    4.  It duplicates the functionality of the [Collections] class
        methods.


12. What is the result of executing the following code?


```
Integer[][] ar1 = {{42}};
Integer[][] ar2 = {{42}};
System.out.print(Arrays.equals(ar1, ar2) + " "); 
System.out.println(Arrays.deepEquals(arr3, arr4)); 
```

    1.  false true
    2.  false false
    3.  true false
    4.  true true

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

    1.  1 2 0 false true
    2.  2 1 1 false true
    3.  2 1 0 false true
    4.  2 1 0 true false

14. What is the result of executing the following code?


```
 String str1 = "";
 String str2 = null;
 System.out.print((Objects.hash(str1) == 
                   Objects.hashCode(str2)) + " ");
 System.out.print(Objects.hash(str1) + " ");
 System.out.println(Objects.hashCode(str2) + " "); 
```

    1.  true 0 0
    2.  Error
    3.  false -1 0
    4.  false 31 0

15. What is the result of executing the following code?


```
String[] arr = {"c", "x", "a"};
System.out.print(ObjectUtils.min(arr) + " ");
System.out.print(ObjectUtils.median(arr) + " ");
System.out.println(ObjectUtils.max(arr));
```

    1.  c x a
    2.  a c x
    3.  x c a
    4.  a x c

16. What is the result of executing the following code?


```
LocalDate lc = LocalDate.parse("1900-02-23");
System.out.println(lc.withYear(21)); 
```

    1.  [1921-02-23]
    2.  [21-02-23]
    3.  [0021-02-23]
    4.  Error

17. What is the result of executing the following code?


```
LocalTime lt2 = LocalTime.of(20, 23, 12);
System.out.println(lt2.withNano(300));      
```

    1.  [20:23:12.000000300]
    2.  [20:23:12.300]
    3.  [20:23:12:300]
    4.  Error

18. What is the result of executing the following code?


```
LocalDate ld = LocalDate.of(2020, 2, 23);
LocalTime lt = LocalTime.of(20, 23, 12);
LocalDateTime ldt = LocalDateTime.of(ld, lt);
System.out.println(ldt);                
```

    1.  [2020-02-23 20:23:12]
    2.  [2020-02-23T20:23:12]
    3.  [2020-02-23:20:23:12]
    4.  Error

19. What is the result of executing the following code?


```
LocalDateTime ldt = LocalDateTime.parse("2020-02-23T20:23:12");
System.out.print(ldt.minus(Period.ofYears(2)) + " ");
System.out.print(ldt.plus(Duration.ofMinutes(12)) + " ");
System.out.println(ldt);
```

    1.  [2020-02-23T20:23:12 2020-02-23T20:23:12
        2020-02-23T20:23:12]
    2.  [2020-02-23T20:23:12 2020-02-23T20:35:12
        2020-02-23T20:35:12]
    3.  [2018-02-23T20:23:12 2020-02-23T20:35:12
        2020-02-23T20:23:12]
    4.  [2018-02-23T20:23:12 2020-02-23T20:35:12
        2018-02-23T20:35:12]
