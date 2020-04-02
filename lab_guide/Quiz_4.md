

Exception Handling
==================

1.  What is a stack trace? Select all that apply:\
    1.  A list of classes currently loaded
    2.  A list of methods currently executing
    3.  A list of code lines currently executing
    4.  A list of variables currently used

2.  What kinds of exceptions are there? Select all that apply:
    1.  Compilation exceptions
    2.  Runtime exceptions
    3.  Read exceptions
    4.  Write exceptions

3.  What is the output of the following code?

```
try {    throw null;} catch (RuntimeException ex) {    System.out.print("RuntimeException ");} catch (Exception ex) {    System.out.print("Exception ");} catch (Error ex) {    System.out.print("Error ");} catch (Throwable ex) {    System.out.print("Throwable ");} finally {    System.out.println("Finally ");}
```

1.  1.  A RuntimeException Error
    2.  Exception Error Finally
    3.  RuntimeException Finally
    4.  Throwable Finally

4.  Which of the following methods will compile without an error?

```
void method1() throws Exception { throw null; }void method2() throws RuntimeException { throw null; }void method3() throws Throwable { throw null; }void method4() throws Error { throw null; }
```

1.  1.  method1()
    2.  method2()
    3.  method3()
    4.  method4()

5.  Which of the following statements will compile without an error?

```
throw new NullPointerException("Hi there!"); //1throws new Exception("Hi there!");          //2throw RuntimeException("Hi there!");       //3throws RuntimeException("Hi there!");     //4
```

1.  1.  1
    2.  2
    3.  3
    4.  4

6.  Assuming that int x = 4, which of the following statements will
    compile without an error?

```
assert (x > 3); //1assert (x = 3); //2assert (x < 4); //3assert (x = 4); //4
```

1.  1.  1
    2.  2
    3.  3
    4.  4

7.  What are the best practices from the following list?
    1.  Always catch all exceptions and errors
    2.  Always catch all exceptions
    3.  Never throw unchecked exceptions
    4.  Try not to throw checked exceptions unless you have to

