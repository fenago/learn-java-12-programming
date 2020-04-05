
Quiz
==================

1.  What is a stack trace? Select all that apply:\
    a.  A list of classes currently loaded
    b.  A list of methods currently executing
    c.  A list of code lines currently executing
    d.  A list of variables currently used


2.  What kinds of exceptions are there? Select all that apply:
    a.  Compilation exceptions
    b.  Runtime exceptions
    c.  Read exceptions
    d.  Write exceptions


3.  What is the output of the following code?


```
try {
    throw null;
} catch (RuntimeException ex) {
    System.out.print("RuntimeException ");
} catch (Exception ex) {
    System.out.print("Exception ");
} catch (Error ex) {
    System.out.print("Error ");
} catch (Throwable ex) {
    System.out.print("Throwable ");
} finally {
    System.out.println("Finally ");
}
```

1.  a.  A RuntimeException Error
    b.  Exception Error Finally
    c.  RuntimeException Finally
    d.  Throwable Finally


4.  Which of the following methods will compile without an error?


```
void method1() throws Exception { throw null; }
void method2() throws RuntimeException { throw null; }
void method3() throws Throwable { throw null; }
void method4() throws Error { throw null; }
```

1.  a.  [method1()]
    b.  [method2()]
    c.  [method3()]
    d.  [method4()]


5.  Which of the following statements will compile without an error?


```
throw new NullPointerException("Hi there!"); //1
throws new Exception("Hi there!");          //2
throw RuntimeException("Hi there!");       //3
throws RuntimeException("Hi there!");     //4
```

1.  a.  1
    b.  2
    c.  3
    d.  4


6.  Assuming that [int x = 4], which of the following statements
    will compile without an error?


```
assert (x > 3); //1
assert (x = 3); //2
assert (x < 4); //3
assert (x = 4); //4
```

1.  a.  1
    b.  2
    c.  3
    d.  4


7.  What are the best practices from the following list?
    a.  Always catch all exceptions and errors
    b.  Always catch all exceptions
    c.  Never throw unchecked exceptions
    d.  Try not to throw checked exceptions unless you have to