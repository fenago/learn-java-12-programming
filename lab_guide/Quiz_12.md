Quiz
=====================

1.  What is the difference between I/O streams and
    [java.util.stream.Stream]? Select all that apply:
    1.  I/O streams are oriented toward data delivery, while
        [Stream] is oriented toward data processing
    2.  Some I/O streams can be transformed into [Stream]
    3.  I/O streams can read from a file, while [Stream] cannot
    4.  I/O streams can write to a file, while [Stream] cannot


2.  What do the [empty()] and [of(T\...
    values)] [Stream] methods have in common?
3.  What type are the elements emitted by
    the [Stream.ofNullable(Set.of(1,2,3 )] stream?
4.  What does the following code print?


```
Stream.iterate(1, i -> i + 2)
      .limit(3)
      .forEach(System.out::print);
```

5.  What does the following code print?


```
Stream.concat(Set.of(42).stream(), 
             List.of(42).stream()).limit(1)
                                  .forEach(System.out::print);
```

6.  What does the following code print?


```
Stream.generate(() -> 42 / 2)
      .limit(2)
      .forEach(System.out::print);
```

7.  Is [Stream.Builder] a functional interface?
8.  How many elements does the following stream emit?


```
new Random().doubles(42).filter(d -> d >= 1)
```

9.  What does the following code print? 


```
Stream.of(1,2,3,4)
        .skip(2)
        .takeWhile(i -> i < 4)
        .forEach(System.out::print);
```

10. What is the value of [d] in the following code? 


```
double d = Stream.of(1, 2)
                 .mapToDouble(Double::valueOf)
                 .map(e -> e / 2)
                 .sum();
```

11. What is the value of the [s] string in the following code?


```
String s = Stream.of("a","X","42").sorted()
 .collect(Collectors.joining(","));
```

12. What is the result of the following code?


```
List.of(1,2,3).stream()
              .peek(i -> i > 2 )
              .forEach(System.out::print);
```

13. How many stream elements the [peek()] operation prints in the
    following code?


```
List.of(1,2,3).stream()
              .peek(System.out::println)
              .noneMatch(e -> e == 2);
```

14. What does the [or()] method return when the [Optional]
    object is empty?
15. What is the value of the [s] string in the following code?


```
String s = Stream.of("a","X","42")
 .max(Comparator.naturalOrder())
 .orElse("12");
```

16. How many elements does
    the [IntStream.rangeClosed(42, 42)] stream emit?
17. Name two stateless operations.
18. Name two stateful operations.
