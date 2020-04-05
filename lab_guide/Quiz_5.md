
Quiz
================================

1.  What does the following code print?


```
String str = "&8a!L";
System.out.println(str.indexOf("a!L"));
```

1.  a.  3
    b.  2
    c.  1
    d.  0

2.  What does the following code print?


```
String s1 = "x12";
String s2 = new String("x12");
System.out.println(s1.equals(s2)); 
```

2.  a.  Error
    b.  Exception
    c.  true
    d.  false

3.  What does the following code print?


```
System.out.println("%wx6".substring(2));
```

-   a.  wx
    b.  x6
    c.  %w
    d.  Exception

4.  What does the following code print?


```
System.out.println("ab"+"42".repeat(2));
```

1.  a.  ab4242
    b.  ab42ab42
    c.  ab422
    d.  Error


5.  What does the following code print?


```
String s = "  ";
System.out.println(s.isBlank()+" "+s.isEmpty());
```

-   a.  false false
    b.  false true
    c.  true true
    d.  true false

6.  Select all correct statements:\
    a.  A stream can represent a data source
    b.  An input stream can write to a file
    c.  A stream can represent a data destination
    d.  An output stream can display data on a screen
7.  Select all correct statements about classes of [java.io]
    package:
    a.  Reader extends [InputStream]
    b.  Reader extends [OutputStream]
    c.  Reader extends [java.lang.Object]
    d.  Reader extends [java.lang.Input]
8.  Select all correct statements about classes
    of [java.io] package:
    a.  Writer extends [FilterOutputStream]
    b.  Writer extends [OutputStream]
    c.  Writer extends [java.lang.Output]
    d.  Writer extends [java.lang.Object]
9.  Select all correct statements about classes
    of [java.io] package:
    a.  [PrintStream] extends [FilterOutputStream]
    b.  [PrintStream] extends [OutputStream]
    c.  [PrintStream] extends [java.lang.Object]
    d.  [PrintStream] extends [java.lang.Output]


10. What does the following code do?


```
String path = "demo1" + File.separator + "demo2" + File.separator;
String fileName = "FileName.txt";
File f = new File(path, fileName);
try {
    new File(path).mkdir();
    f.createNewFile();
} catch (Exception e) {
    e.printStackTrace();
} 
```

-   a.  Creates two directories and a file in [demo2] directory
    b.  Creates one directory and a file in it
    c.  Does not create any directory
    d.  Exception
