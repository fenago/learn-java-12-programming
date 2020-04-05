
Quiz
================================

1.  What does the following code print?


```
String str = "&8a!L";
System.out.println(str.indexOf("a!L"));
```

    1.  3
    2.  2
    3.  1
    4.  0

2.  What does the following code print?


```
String s1 = "x12";
String s2 = new String("x12");
System.out.println(s1.equals(s2)); 
```

2.  a.  Error
    2.  Exception
    3.  true
    4.  false

3.  What does the following code print?


```
System.out.println("%wx6".substring(2));
```

    1.  wx
    2.  x6
    3.  %w
    4.  Exception

4.  What does the following code print?


```
System.out.println("ab"+"42".repeat(2));
```

    1.  ab4242
    2.  ab42ab42
    3.  ab422
    4.  Error


5.  What does the following code print?


```
String s = "  ";
System.out.println(s.isBlank()+" "+s.isEmpty());
```

    1.  false false
    2.  false true
    3.  true true
    4.  true false

6.  Select all correct statements:\
    1.  A stream can represent a data source
    2.  An input stream can write to a file
    3.  A stream can represent a data destination
    4.  An output stream can display data on a screen
7.  Select all correct statements about classes of [java.io]
    package:
    1.  Reader extends [InputStream]
    2.  Reader extends [OutputStream]
    3.  Reader extends [java.lang.Object]
    4.  Reader extends [java.lang.Input]
8.  Select all correct statements about classes
    of [java.io] package:
    1.  Writer extends [FilterOutputStream]
    2.  Writer extends [OutputStream]
    3.  Writer extends [java.lang.Output]
    4.  Writer extends [java.lang.Object]
9.  Select all correct statements about classes
    of [java.io] package:
    1.  [PrintStream] extends [FilterOutputStream]
    2.  [PrintStream] extends [OutputStream]
    3.  [PrintStream] extends [java.lang.Object]
    4.  [PrintStream] extends [java.lang.Output]


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

    1.  Creates two directories and a file in [demo2] directory
    2.  Creates one directory and a file in it
    3.  Does not create any directory
    4.  Exception
