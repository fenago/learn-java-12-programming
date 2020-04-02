
Getting Started with Java 12
============================

1.  What does JDK stand for?

-   1.  Java Document Kronos
    2.  June Development Karate
    3.  Java Development Kit
    4.  Java Developer Kit

2.  What does JCL stand for?

-   1.  Java Classical Library
    2.  Java Class Library
    3.  Junior Classical Liberty
    4.  Java Class Libras

3.  What does Java SE stand for?

-   1.  Java Senior Edition
    2.  Java Star Edition
    3.  Java Structural Elections
    4.  Java Standard Edition

4.  What does IDE stand for?

-   1.  Initial Development Edition
    2.  Integrated Development Environment
    3.  International Development Edition
    4.  Integrated Development Edition

5.  What are Maven's functions?

-   1.  Project building
    2.  Project configuration
    3.  Project documentation
    4.  Project cancellation

6.  What are Java primitive types?

-   1.  boolean
    2.  numeric
    3.  integer
    4.  string

7.  What are Java primitive types?

-   1.  long
    2.  bit
    3.  short
    4.  byte

8.  What is a *literal*?

-   1.  A letter-based string
    2.  A number-based string
    3.  A variable representation
    4.  A value representation

9.  Which of the following are literals?

-   1.  \\\\
    2.  2\_0
    3.  2\_\_0f
    4.  \\f

10. Which of the following are Java operators?

-   1.  %
    2.  \$
    3.  &
    4.  -\>

11. What does the following code snippet print?

```
int i = 0; System.out.println(i++);
```

-   1.  0
    2.  1
    3.  2
    4.  3

12. What does the following code snippet print?

```
boolean b1 = true; boolean b2 = false; System.out.println((b1 & b2) + " " + (b1 && b2));
```

-   1.  false true
    2.  false false
    3.  true false
    4.  true true

13. What does the following code snippet print?

```
int x = 10; x %= 6; System.out.println(x);
```

-   1.  1
    2.  2
    3.  3
    4.  4

14. What is the result of the following code snippet?

```
System.out.println("abc" - "bc");
```

-   1.  a
    2.  abc-bc
    3.  Compilation error
    4.  Execution error

15. What does the following code snippet print?

```
System.out.println("A".repeat(3).lastIndexOf("A"));
```

-   1.  1
    2.  2
    3.  3
    4.  4

16. What are the correct identifiers?

-   1.  int \_\_ (two underscores)
    2.  2a
    3.  a2
    4.  \$

17. What does the following code snippet print?

```
for (int i=20, j=-1; i < 23 && j < 0; ++i, ++j){         System.out.println(i + " " + j + " "); }
```

-   1.  20 -1 21 0
    2.  Endless loop
    3.  21 0
    4.  20 -1

18. What does the following code snippet print?

```
int x = 10;try {    if(x++ > 10){        throw new RuntimeException("The x value is out of the range: " + x);    }    System.out.println("The x value is within the range: " + x);} catch (RuntimeException ex) {    System.out.println(ex.getMessage());}
```

-   1.  Compilation error
    2.  The x value is out of the range: 11
    3.  The x value is within the range: 11
    4.  Execution time error

19. What does the following code snippet print?

```
int result = 0;List<List<Integer>> source = List.of(        List.of(1, 2, 3, 4, 6),        List.of(22, 23, 24, 25),        List.of(32, 33));cont: for(List<Integer> l: source){    for (int i: l){        if(i > 7){            result = i;            continue cont;        }     }}System.out.println("result=" + result);
```

-   1.  result = 22
    2.  result = 23
    3.  result = 32
    4.  result = 33

20. Select all the following statements that are correct:

-   1.  A variable can be declared
    2.  A variable can be assigned
    3.  A variable can be defined
    4.  A variable can be determined

21. Select all the correct Java statement types from the following:

-   1.  An executable statement
    2.  A selection statement
    3.  A method end statement
    4.  An increment statement
