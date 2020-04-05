<img align="right" src="../logo-small.png">


Strings, Input/Output, and Files
================================

In this chapter, a reader will be presented with [String] class
methods in more detail. We will also discuss popular string utilities
from standard libraries and Apache Commons project. An overview of Java
input/output streams and related classes of the [java.io] packages
will follow along with some classes of
the [org.apache.commons.io] package. The file managing classes and
their methods are described in the dedicated section.

The following topics will be covered in this chapter:

-   Strings processing
-   I/O streams
-   Files management
-   Apache Commons utilities [FileUtils] and [IOUtils]


<h3><span style="color:red;">Run Java Code</span></h3>

You can run the example by running following command in the terminal:
`java -cp target/learnjava-1.0.jar:target/libs/* com.lv.learnjava.ch05_stringsIoStreams.ConsoleDemo`

<h3><span style="color:red;">Run Java Code</span></h3>

You can run the example by running following command in the terminal:
`java -cp target/learnjava-1.0.jar:target/libs/* com.lv.learnjava.ch05_stringsIoStreams.Files`

<h3><span style="color:red;">Run Java Code</span></h3>

You can run the example by running following command in the terminal:
`java -cp target/learnjava-1.0.jar:target/libs/* com.lv.learnjava.ch05_stringsIoStreams.InputOutputStream`

<h3><span style="color:red;">Run Java Code</span></h3>

You can run the example by running following command in the terminal:
`java -cp target/learnjava-1.0.jar:target/libs/* com.lv.learnjava.ch05_stringsIoStreams.ReaderWriter`

<h3><span style="color:red;">Run Java Code</span></h3>

You can run the example by running following command in the terminal:
`java -cp target/learnjava-1.0.jar:target/libs/* com.lv.learnjava.ch05_stringsIoStreams.SerializableDemo`

<h3><span style="color:red;">Run Java Code</span></h3>

You can run the example by running following command in the terminal:
`java -cp target/learnjava-1.0.jar:target/libs/* com.lv.learnjava.ch05_stringsIoStreams.Strings`

Strings processing
================================

In a mainstream programming, [String] probably is the most popular
class. We have learned about this class, its
literals and its specific feature called **string immutability**. In
this section, we will explain how a string can be processed using
[String] class methods and utility classes from the standard
library and the [StringUtils] class
from the [org.apache.commons.lang3] package in particular.

Methods of the String class  {#methods-of-the-string-class .header-title}
----------------------------

The [String] class has more than 70 methods that enable analyzing,
modifying, comparing strings, and converting numeric literals into the
corresponding string literals. To see all the methods of the
[String] class, please refer the Java API online at
<https://docs.oracle.com/en/java/javase>.

Strings analysis {#strings-analysis .header-title}
----------------

The [length()] method returns the count of characters in the
string as shown in the following code:


```
String s7 = "42";
System.out.println(s7.length());    //prints: 2
System.out.println("0 0".length()); //prints: 3
```

The following [isEmpty()] method returns true when the length of
the string (count of characters) is [0]: 


```
System.out.println("".isEmpty());   //prints: true
System.out.println(" ".isEmpty());  //prints: false
```

The [indexOf()] and [lastIndexOf()] methods return
the position of the specified substring in the string shown in this code
snippet:


```
String s6 = "abc42t%";
System.out.println(s6.indexOf(s7));            //prints: 3
System.out.println(s6.indexOf("a"));           //prints: 0
System.out.println(s6.indexOf("xyz"));         //prints: -1
System.out.println("ababa".lastIndexOf("ba")); //prints: 3
```

As you can see, the first character in the string has a position
(index) [0], and the absence of the specified substring results in
the index [-1].

The [matches()] method applies the regular expression (passed as
an argument) to the string as follows: 


```
System.out.println("abc".matches("[a-z]+"));   //prints: true
System.out.println("ab1".matches("[a-z]+"));   //prints: false
```

Regular expressions are outside the scope of this book. You can learn
about them
at [https://www.regular-expressions.info](https://www.regular-expressions.info/).
In the preceding example, the expression [\[a-z\]+] matches one or
more letters only.

Strings comparison {#strings-comparison .header-title}
------------------

In [Chapter
3](https://subscription.packtpub.com/book/programming/9781789957051/3),
*Java Fundamentals*, we have already talked about
the [equals()] method that returns [true] only when
two [String] objects or literals are spelled exactly the same way.
The following code snippet demonstrates how it works:


```
String s1 = "abc";
String s2 = "abc";
String s3 = "acb";
System.out.println(s1.equals(s2));     //prints: true
System.out.println(s1.equals(s3));     //prints: false
System.out.println("abc".equals(s2));  //prints: true
System.out.println("abc".equals(s3));  //prints: false
```

Another [String] class [equalsIgnoreCase()] method does a
similar job, but ignores the difference in the characters\' case as
follows:


```
String s4 = "aBc";
String s5 = "Abc";
System.out.println(s4.equals(s5));           //prints: false
System.out.println(s4.equalsIgnoreCase(s5)); //prints: true
```

The [contentEquals()] method acts similar to the
[equals()] method shown here:


```
String s1 = "abc";
String s2 = "abc";
System.out.println(s1.contentEquals(s2));    //prints: true
System.out.println("abc".contentEquals(s2)); //prints: true 
```

The difference is that [equals()] method checks if both values are
represented by the [String] class,\
while [contentEquals()] compares only characters (content) of the
character sequence. The character sequence can be represented
by [String], [StringBuilder], [StringBuffer], [CharBuffer],
or any other class that implements a [CharSequence] interface.
Nevertheless, the [contentEquals()] method will
return [true] if both sequences contain the same characters, while
the [equals()] method will return [false] if one of the
sequences is not created by the [String] class.

The [contains()] method returns [true] if the [string]
contains a certain substring as follows:


```
String s6 = "abc42t%";
String s7 = "42";
String s8 = "xyz";
System.out.println(s6.contains(s7));    //prints: true
System.out.println(s6.contains(s8));    //prints: false
```

The [startsWith()] and [endsWith()] methods perform a
similar check but only at the start of the string or at the end of the
string value as shown in the following code:


```
String s6 = "abc42t%";
String s7 = "42";

System.out.println(s6.startsWith(s7));      //prints: false
System.out.println(s6.startsWith("ab"));    //prints: true
System.out.println(s6.startsWith("42", 3)); //prints: true

System.out.println(s6.endsWith(s7));        //prints: false
System.out.println(s6.endsWith("t%"));      //prints: true
```

The
[compareTo()] and [compareToIgnoreCase()] methods compare
strings lexicographically---based on the Unicode value of each character
in the strings. They return the value [0] if the strings are
equal, a negative integer value if the first string is lexicographically
less (has a smaller Unicode value) than the second string, and a
positive integer value if the first string is lexicographically greater
than the second string (has a bigger Unicode value). For example:


```
String s4 = "aBc";
String s5 = "Abc";
System.out.println(s4.compareTo(s5));             //prints: 32
System.out.println(s4.compareToIgnoreCase(s5));   //prints: 0
System.out.println(s4.codePointAt(0));            //prints: 97
System.out.println(s5.codePointAt(0));            //prints: 65
```

From this code snippet, you can see that the
[compareTo()] and [compareToIgnoreCase()] methods are based
on the code points of the characters that compose the strings. The
reason the string [s4] is bigger than the
string [s5] by [32] because the code point of the
character [a] ([97]) is bigger than the code point of the
character [A] ([65]) by [32]. 

The given example also shows that the
[codePointAt()] method returns the code point of the character
located in the string at the specified position. The code points were
described in the *Integral types* section of [Chapter
1](https://subscription.packtpub.com/book/programming/9781789957051/1),
*Getting Started with Java*.

Strings transformation {#strings-transformation .header-title}
----------------------

The [substring()] method returns the substring starting with the
specified position (index) as follows:


```
System.out.println("42".substring(0));   //prints: 42
System.out.println("42".substring(1));   //prints: 2
System.out.println("42".substring(2));   //prints:
System.out.println("42".substring(3));   //error: index out of range: -1
String s6 = "abc42t%";
System.out.println(s6.substring(3));     //prints: 42t%
System.out.println(s6.substring(3, 5));  //prints: 42
```

The [format()] method uses the passed-in first argument as a
template and inserts the other arguments in the corresponding position
of the template sequentially. The following code example prints the
sentence, \"*Hey, Nick! Give me 2 apples, please!*\" three times:


```
String t = "Hey, %s! Give me %d apples, please!";
System.out.println(String.format(t, "Nick", 2));

String t1 = String.format(t, "Nick", 2);
System.out.println(t1);

System.out.println(String
          .format("Hey, %s! Give me %d apples, please!", "Nick", 2));
```

The [%s] and [%d] symbols are called **format specifiers**.
There are many specifiers and various flags, that allow the programmer
to fine-control the result. You can read about them in the API of
the [java.util.Formatter] class.

The [concat()] method works the same way as the arithmetic
operator ([+]) as shown:


```
String s7 = "42";
String s8 = "xyz";
String newStr1 = s7.concat(s8);
System.out.println(newStr1);    //prints: 42xyz

String newStr2 = s7 + s8;
System.out.println(newStr2);    //prints: 42xyz
```

The following [join()] method acts similarly but allows the
addition of a delimiter:


```
String newStr1 = String.join(",", "abc", "xyz");
System.out.println(newStr1);        //prints: abc,xyz

List<String> list = List.of("abc","xyz");
String newStr2 = String.join(",", list);
System.out.println(newStr2);        //prints: abc,xyz
```

The following group of [replace()], [replaceFirst()],
and [replaceAll()] methods replace certain characters in the
string with the provided ones:


```
System.out.println("abcbc".replace("bc", "42"));         //prints: a4242
System.out.println("abcbc".replaceFirst("bc", "42"));    //prints: a42bc
System.out.println("ab11bcd".replaceAll("[a-z]+", "42"));//prints: 421142
```

The first line of the preceding code replaces all the instances of
[\"bc\"] with [\"42\"]. The second replaces only the first
instance of [\"bc\"] with [\"42\"]. And the last one
replaces all the substrings that match the provided regular expression
with [\"42\"].

The [toLowerCase()] and [toUpperCase()] methods change the
case of the whole string as shown here:


```
System.out.println("aBc".toLowerCase());   //prints: abc
System.out.println("aBc".toUpperCase());   //prints: ABC
```

The [split()] method breaks the string into substrings, using the
provided character as the delimiter, as follows:


```
String[] arr = "abcbc".split("b");
System.out.println(arr[0]);   //prints: a
System.out.println(arr[1]);   //prints: c
System.out.println(arr[2]);   //prints: c
```

There are several [valueOf()] methods that transform values of a
primitive type to a [String] type. For example:


```
float f = 23.42f;
String sf = String.valueOf(f);
System.out.println(sf);         //prints: 23.42
```

There are also [()] and [getChars()] methods that transform
a string to an array of a corresponding type, while
the [chars()] method creates an [IntStream] of characters
(their code points). We will talk about streams in [Chapter
14](https://subscription.packtpub.com/book/programming/9781789957051/14), *Java
Standard Streams*.

Methods added with Java 11 {#methods-added-with-java-11 .header-title}
--------------------------

Java 11 introduced several new methods in the [String] class.

The [repeat()] method allows you to create a new String value
based on multiple concatenations of the same string as shown in the
following code:


```
System.out.println("ab".repeat(3)); //prints: ababab
System.out.println("ab".repeat(1)); //prints: ab
System.out.println("ab".repeat(0)); //prints:
```

The [isBlank()] method returns true if the string has
length [0] or consists of white spaces only. For example:


```
System.out.println("".isBlank());     //prints: true
System.out.println("   ".isBlank());  //prints: true
System.out.println(" a ".isBlank());  //prints: false
```

The [stripLeading()] method removes leading white spaces from the
string, the [stripTrailing()] method removes trailing white
spaces, and [strip()] method removes both, as shown here:


```
String sp = "   abc   ";
System.out.println("'" + sp + "'");                 //prints: '   abc   '
System.out.println("'" + sp.stripLeading() + "'");  //prints: 'abc   '
System.out.println("'" + sp.stripTrailing() + "'"); //prints: '  abc'
System.out.println("'" + sp.strip() + "'");         //prints: 'abc'
```

And, finally, the [lines()] method breaks the string by line
terminators and returns a [Stream\<String\>] of resulting lines. A
line terminator is an escape sequence line
feed [\\n] ([\\u000a]), or a carriage
return [\\r] ([\\u000d]), or a carriage return followed
immediately by a line feed [\\r\\n] ([\\u000d\\u000a]). For
example:


```
String line = "Line 1\nLine 2\rLine 3\r\nLine 4";
line.lines().forEach(System.out::println); 
```

The output of the preceding code is as follows:

![]./images_5/8f2bbd68-c6f9-4a27-a068-acfffb9f045f.png)

We will talk about streams in [Chapter
14](https://subscription.packtpub.com/book/programming/9781789957051/14), *Java
Standard Streams*.

String utilities {#string-utilities .header-title}
----------------

In addition to the [String] class, there are many other classes
that have methods that process [String] values. Among the most
useful is the [StringUtils] class of
the [org.apache.commons.lang3] package from a project called
an **Apache Commons**, maintained by an open source community of
programmers called **Apache Software Foundation**. We will talk more
about this project and its libraries in
[](https://subscription.packtpub.com/book/programming/9781789957051/7)[Chapter
7](https://subscription.packtpub.com/book/programming/9781789957051/7), *Java
Standard and External Libraries*. To use it in your project, add the
following dependency in the [pom.xml] file:


```
<dependency>
    <groupId>org.apache.commons</groupId>
    <artifactId>commons-lang3</artifactId>
    <version>3.8.1</version>
</dependency>
```

The [StringUtils] class is the favorite of many programmers. It
complements methods of the [String] class by providing the
following null-safe operations:

-   [isBlank(CharSequence cs)]: Returns [true] if the input
    value is white space, empty ([\"\"]), or [null]
-   [isNotBlank(CharSequence cs)]: Returns [false] when the
    preceding method returns [true]
-   [isEmpty(CharSequence cs)]: Returns [true] if the input
    value is empty ([\"\"]) or [null]
-   [isNotEmpty(CharSequence cs)]: Returns [false] when the
    preceding method returns [true]
-   [trim(String str)]: Removes leading and trailing white space
    from the input value and processes [null], empty
    ([\"\"]), and white space, as follows:


```
System.out.println("'" + StringUtils.trim(" x ") + "'"); //prints: 'x'
System.out.println(StringUtils.trim(null));              //prints: null
System.out.println("'" + StringUtils.trim("") + "'");    //prints: ''
System.out.println("'" + StringUtils.trim("   ") + "'"); //prints: ''
```

-   [trimToNull(String str)]: Removes leading and trailing white
    space from the input value and processes [null], empty
    ([\"\"]), and white space, as follows:


```
System.out.println("'" + StringUtils.trimToNull(" x ") + "'");  // 'x'
System.out.println(StringUtils.trimToNull(null));        //prints: null
System.out.println(StringUtils.trimToNull(""));          //prints: null
System.out.println(StringUtils.trimToNull("   "));       //prints: null
```

-   [trimToEmpty(String str)]: Removes leading and trailing white
    space from the input value and processes [null], empty
    ([\"\"]), and white space, as follows:


```
System.out.println("'" + StringUtils.trimToEmpty(" x ") + "'");   // 'x'
System.out.println("'" + StringUtils.trimToEmpty(null) + "'");    // ''
System.out.println("'" + StringUtils.trimToEmpty("") + "'");      // ''
System.out.println("'" + StringUtils.trimToEmpty("   ") + "'");   // ''
```

-   [strip(String str)], [stripToNull(String
    str)], [stripToEmpty(String str)]: Produce the same
    result as the preceding [trim\*(String str)] methods but use a
    more extensive definition of white space (based on
    [Character.isWhitespace(int codepoint)]) and thus remove the
    same characters as [trim\*(String str)] does, and more


-   [strip(String str, String stripChars)], [stripAccents(String
    input)], [stripAll(String\...
    strs)], [stripAll(String\[\] strs, String
    stripChars)], [stripEnd(String str, String
    stripChars)], [stripStart(String str, String
    stripChars)]: Remove particular characters from particular
    parts of a [String] or a [String\[\]] array elements
-   [startsWith(CharSequence str, CharSequence prefix)],
    [startsWithAny(CharSequence string, CharSequence\...
    searchStrings)], [startsWithIgnoreCase(CharSequence str,
    CharSequence prefix)], and similar [endsWith\*()]
    methods: Check if a [String] value starts (or ends) with a
    certain prefix (or suffix)
-   [indexOf], [lastIndexOf], [contains]: Check index
    in a null-safe manner
-   [indexOfAny], [lastIndexOfAny], [indexOfAnyBut], [lastIndexOfAnyBut]:
    Return index 
-   [containsOnly], [containsNone], [containsAny]:
    Check if the value contains or not certain characters
-   [substring], [left], [right], [mid]: Return
    substring in a null-safe manner
-   [substringBefore], [substringAfter], [substringBetween]:
    Return substring from relative position
-   [split], [join]: Split or join a value (correspondingly)
-   [remove], [delete]: Eliminate substring
-   [replace], [overlay]: Replace a value
-   [chomp], [chop]: Remove the end
-   [appendIfMissing]: Adds a value if not present
-   [prependIfMissing]: Prepends a prefix to the start of the
    [String] value if not present
-   [leftPad], [rightPad], [center], [repeat]:
    Add padding
-   [upperCase], [lowerCase], [swapCase], [capitalize], [uncapitalize]:
    Change the case


-   [countMatches]: Returns the number of the substring
    occurrences
-   [isWhitespace], [isAsciiPrintable], [isNumeric], [isNumericSpace], [isAlpha], [isAlphaNumeric], [isAlphaSpace], [isAlphaNumericSpace]:
    Check the presence of certain type of characters
-   [isAllLowerCase], [isAllUpperCase]: Check the case
-   [defaultString], [defaultIfBlank], [defaultIfEmpty]:
    Returns default value if [null]
-   [rotate]: Rotates characters using a circular shift 
-   [reverse], [reverseDelimited]: Reverse characters or
    delimited groups of characters 
-   [abbreviate], [abbreviateMiddle]: Abbreviate value using
    an ellipsis or another value
-   [difference]: Returns the differences in values
-   [getLevenshteinDistance]: Returns the number of changes needed
    to transform one value to another

As you can see, the [StringUtils] class has a very rich (we have
not listed everything) set of methods for strings analysis, comparison,
and transformation that compliment the methods of
the [String] class.


I/O streams
================================

Any software system has to receive and produce some kind of data that
can be organized as a set of isolated inputs/outputs or as a stream of
data. A stream can be limited or endless. A program can read from a
stream (then it is called an **input stream**), or write to a stream
(then it is called an **output stream**). The Java I/O stream is either
byte-based or character-based, meaning that its data is interpreted
either as raw bytes or as characters.

The [java.io] package contains classes that support many, but not
all, possible data sources. It is built for the most part around the
input from and to files, network streams, and internal memory buffers.
It does not contain many classes necessary for network communication.
They belong to the [java.net], [javax.net], and other
packages of Java Networking API. Only after the networking source or
destination is established (a network socket, for example), can a
program read and write data using [InputStream] and
[OutputStream] classes of the [java.io] package. 

Classes of the [java.nio] package have pretty much the same
functionality as the classes of [java.io] packages. But, in
addition, they can work in *a non-blocking* mode, which can
substantially increase the performance in certain situations. We will
talk about non-blocking processing in
[](https://subscription.packtpub.com/book/programming/9781789957051/15)Chapter
15, *Reactive Programming*.

Stream data {#stream-data .header-title}
-----------

The data a program understands has to be binary---expressed in 0s and
1s---at the very basis. The data can be read or written one byte at a
time or an array of several bytes at a time. These bytes can remain
binary or can be interpreted as characters.

In the first case, they can be read as bytes or byte arrays by the
descendants of [InputStream] and [OutputStream] classes. For
example (we omit the package name if the class belongs to
the [java.io] package): [ByteArrayInputStream],
[ByteArrayOutputStream], [FileInputStream],
[FileOutputStream], [ObjectInputStream],
[ObjectOutputStream],
[javax.sound.sampled.AudioInputStream], and
[org.omg.CORBA.portable.OutputStream]; which one to use depends on
the source or destination of the data.
The [InputStream] and [OutputStream] classes themselves are
abstract and cannot be instantiated.

In the second case, the data that can be interpreted as characters are
called **text data**, and there are character-oriented reading and
writing classes based on the [Reader] and [Writer], which
are abstract classes too. Examples of their sub-classes
are: [CharArrayReader], [CharArrayWriter],
[InputStreamReader], [OutputStreamWriter],
[PipedReader], [PipedWriter], [StringReader],
and [StringWriter].

You may have noticed that we listed the classes in pairs. But not every
input class has a matching output specialization. For example, there are
[PrintStream] and [PrintWriter] classes that support output
to a printing device, but there is no corresponding input partner, not
by name at least. However, there is
a [java.util.Scanner] class that parses input text in a known
format. 

There is also a set of buffer-equipped classes that help to improve
performance by reading or writing a bigger chunk of data at a time,
especially in the cases when access to the source or destination takes a
long time.

In the rest of this section, we will review classes of the
[java.io] package and some popular related classes from other
packages.

Class InputStream and its subclasses {#class-inputstream-and-its-subclasses .header-title}
------------------------------------

In Java Class Library, the [InputStream] abstract class has the
following direct implementations: [ByteArrayInputStream],
[FileInputStream], [ObjectInputStream],
[PipedInputStream], [SequenceInputStream],
[FilterInputStream],
and [javax.sound.sampled.AudioInputStream]. 

All of them either use as-is or override the following methods of
the [InputStream] class:

-   [int available()]: Returns the number of bytes available for
    reading
-   [void close()]: Closes the stream and releases the resources
-   [void mark(int readlimit)]: Marks a position in the stream and
    defines how many bytes can be read
-   [boolean markSupported()]: Returns [true] if the marking
    is supported
-   [static InputStream nullInputStream()]: Creates an empty
    stream
-   [abstract int read()]: Reads the next byte in the stream
-   [int read(byte\[\] b)]: Reads data from the stream into
    the [b] buffer
-   [int read(byte\[\] b, int off, int len)]: Reads [len] or
    fewer bytes from the stream into the [b] buffer
-   [byte\[\] readAllBytes()]: Reads all the remaining bytes from
    the stream
-   [int readNBytes(byte\[\] b, int off, int len)]: Reads
    [len] or fewer bytes into the [b] buffer at the
    [off] offset
-   [byte\[\] readNBytes(int len)]: Reads [len] or fewer
    bytes into the [b] buffer
-   [void reset()]: Resets the reading location to the position
    where the [mark()] method was last called
-   [long skip(long n)]: Skips [n] or fewer bytes of the
    stream; returns the actual number of bytes skipped
-   [long transferTo(OutputStream out)]: Reads from the input
    stream and writes to the provided output stream byte by byte;
    returns the actual number of bytes transferred

The [abstract int read()] is the only method that has to be
implemented, but most of the descendants of this class override many of
the other methods too.

ByteArrayInputStream {#bytearrayinputstream .header-title}
--------------------

The [ByteArrayInputStream] class allows reading a byte array as an
input stream. It has the following two constructors that create an
object of the class and defines the buffer used to read the input stream
of bytes: 

-   [ByteArrayInputStream(byte\[\] buffer)]
-   [ByteArrayInputStream(byte\[\] buffer, int offset, int
    length)]

The second of the constructors allows setting, in addition to the
buffer, the offset and the length of the buffer too. Let\'s look at the
example and see how this class can be used. We assume there is a source
of [byte\[\]] array with data:


```
byte[] bytesSource(){
    return new byte[]{42, 43, 44};
}
```

Then we can write the following:


```
byte[] buffer = bytesSource();
try(ByteArrayInputStream bais = new ByteArrayInputStream(buffer)){
    int data = bais.read();
    while(data != -1) {
        System.out.print(data + " ");   //prints: 42 43 44
        data = bais.read();
    }
} catch (Exception ex){
    ex.printStackTrace();
}
```

The [bytesSource()] method produces the array of bytes that fills
the buffer that is passed into the constructor of
the [ByteArrayInputStream] class as a parameter. The resulting
stream is then read byte by byte using the [read()] method until
the end of the stream is reached (and [read()] method returns
[-1]). Each new byte is printed out (without line feed and with
white space after it, so all the read bytes are displayed in one line
separated by the white space). 

The preceding code is usually expressed in a more compact form as
follows:


```
byte[] buffer = bytesSource();
try(ByteArrayInputStream bais = new ByteArrayInputStream(buffer)){
    int data;
    while ((data = bais.read()) != -1) {
        System.out.print(data + " ");   //prints: 42 43 44
    }
} catch (Exception ex){
    ex.printStackTrace();
}
```

Instead of just printing the bytes, they can be processed in any other
manner necessary, including interpreting them as characters. For
example:


```
byte[] buffer = bytesSource();
try(ByteArrayInputStream bais = new ByteArrayInputStream(buffer)){
    int data;
    while ((data = bais.read()) != -1) {
        System.out.print(((char)data) + " ");   //prints: * + ,
    }
} catch (Exception ex){
    ex.printStackTrace();
}
```

But, in such a case, it is better to use one of the [Reader]
classes that are specialized for characters processing. We will talk
about them in the *Reader and Writer classes and their
subclasses* section.

FileInputStream {#fileinputstream .header-title}
---------------

The [FileInputStream] class gets data from a file in a filesystem,
the raw bytes of an image, for example. It has the following three
constructors:

-   [FileInputStream(File file)]
-   [FileInputStream(String name)]
-   [FileInputStream(FileDescriptor fdObj)]

Each constructor opens the file specified as the parameter. The first
constructor accepts [File] object, the second, the path to the
file in the filesystem, and the third, the file descriptor object that
represents an existing connection to an actual file in the filesystem.
Let\'s look at the following example:


```
String filePath = "src/main/resources/hello.txt";
try(FileInputStream fis=new FileInputStream(filePath)){
    int data;
    while ((data = fis.read()) != -1) {
        System.out.print(((char)data) + " ");   //prints: H e l l o !
    }
} catch (Exception ex){
    ex.printStackTrace();
}
```

In the [src/main/resources] folder, we have created
the [hello.txt] file that has only one line in
it---[Hello!]. The output of the preceding example looks as
follows:

![]./images_5/fd95e8e0-7abb-49f5-a0cc-55cd6f1a5a9a.png)

Since we are running this example inside the IDE, it is executed in the
project root directory. In order to find where your code is executed,
you can always print it out like this:


```
File f = new File(".");                //points to the current directory
System.out.println(f.getAbsolutePath()); //prints the directory path
```

After reading bytes from the [hello.txt] file, we decided, for
demo purposes, to cast each [byte] to [char], so you can see
that our code does read from the specified file, but
the [FileReader] class is a better choice for text file processing
(we will discuss it shortly). Without the cast, the result would be the
following:


```
System.out.print((data) + " ");   //prints: 72 101 108 108 111 33
```

By the way, because the [src/main/resources] folder is placed by
the IDE (using Maven) on the classpath, a file placed in it can also be
accessed via a classloader that creates a stream using its own
[InputStream] implementation:


```
try(InputStream is = InputOutputStream.class.getResourceAsStream("/hello.txt")){
    int data;
    while ((data = is.read()) != -1) {
        System.out.print((data) + " ");   //prints: 72 101 108 108 111 33
    }
} catch (Exception ex){
    ex.printStackTrace();
}
```

The [InputOutputStream] class in the preceding example is not a
class from some library. It is just the main class we used to run the
example. The [InputOutputStream.class.getResourceAsStream()]
construct allows using the same classloader that has loaded
the [InputOutputStream] class for the purpose of finding a file on
the classpath and creating a stream that contains its content. In
the *Files Management* section, we will present other ways of reading a
file too.

ObjectInputStream {#objectinputstream .header-title}
-----------------

The set of methods of the [ObjectInputStream] class is much bigger
than the set of methods of any other [InputStream] implementation.
The reason for that is that it is built around reading the values of the
object fields that can be of various types. In order for the
[ObjectInputStream] to be able to construct an object from the
input stream of data, the object has to be *deserializable*, which means
it has to be *serializable* in the first place---that is to be
convertible into a byte stream. Usually, it is done for the purpose of
transporting objects over a network. At the destination, the serialized
objects are deserialized and the values of the original objects are
restored.

Primitive types and most of Java classes, including [String]
class and primitive type wrappers, are serializable. If a class has
fields of custom types, they have to be made serializable by
implementing [java.io.Serizalizable]. How to do it is outside the
scope of this book. For now, we are going to use only the serializable
types. Let\'s look at this class:


```
class SomeClass implements Serializable {
    private int field1 = 42;
    private String field2 = "abc";
}
```

We have to tell the compiler that it is serializable. Otherwise, the
compilation will fail. It is done in order to make sure that, before
stating that the class is serializable, the programmer either reviewed
all the fields and made sure they are serializable or has implemented
the methods necessary for the serialization. 

Before we can create an input stream and use [ObjectInputStream]
for deserialization, we need to serialize the object first. That is why
we first use [ObjectOutputStream] and [FileOutputStream] to
serialize an object and write it into the [someClass.bin] file. We
will talk more about them in the *Class OutputStream and its
subclasses* section. Then we read from the file using
[FileInputStream] and deserialize the file content using
[ObjectInputStream]:


```
String fileName = "someClass.bin";
try (ObjectOutputStream objectOutputStream =
             new ObjectOutputStream(new FileOutputStream(fileName));
     ObjectInputStream objectInputStream =
              new ObjectInputStream(new FileInputStream(fileName))){
    SomeClass obj = new SomeClass();
    objectOutputStream.writeObject(obj);
    SomeClass objRead = (SomeClass) objectInputStream.readObject();
    System.out.println(objRead.field1);  //prints: 42
    System.out.println(objRead.field2);  //prints: abc
} catch (Exception ex){
    ex.printStackTrace();
}
```

Note that, the file has to be created first before the preceding code is
run. We will show how it can be done in the *Creating files and
directories* section. And, to remind you, we have used the
try-with-resources statement because [InputStream] and
[OutputStream] both implement the [Closeable] interface. 

PipedInputStream {#pipedinputstream .header-title}
----------------

A piped input stream has very particular specialization; it is used as
one of the mechanisms of communication between threads. One thread
reads from a [PipedInputStream] object and passes data to another
thread that writes data to a [PipedOutputStream] object. Here is
an example:


```
PipedInputStream pis = new PipedInputStream();
PipedOutputStream pos = new PipedOutputStream(pis);
```

Alternatively, data can be moved in the opposite direction when one
thread reads from a [PipedOutputStream] object and another thread
writes to a [PipedInputStream] object as follows:


```
PipedOutputStream pos = new PipedOutputStream();
PipedInputStream pis = new PipedInputStream(pos);
```

Those who work in this area are familiar with the message, \"*Broken
pipe*\"*,* which means that the supplying data pipe stream has stopped
working.

The piped streams can also be created without any connection and
connected later as shown here:


```
PipedInputStream pis = new PipedInputStream();
PipedOutputStream pos = new PipedOutputStream();
pos.connect(pis); 
```

For example, here are two classes that are going to be executed by
different threads. First, the [PipedOutputWorker] class as
follows:


```
class PipedOutputWorker implements Runnable{
    private PipedOutputStream pos;
    public PipedOutputWorker(PipedOutputStream pos) {
        this.pos = pos;
    }
    @Override
    public void run() {
        try {
            for(int i = 1; i < 4; i++){
                pos.write(i);
            }
            pos.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
```

The [PipedOutputWorker] class has [run()] method (because it
implements a [Runnable] interface) that writes into the stream the
three numbers [1], [2], and [3], and then closes. Now
let\'s look at [PipedInputWorker] class as shown here: 


```
class PipedInputWorker implements Runnable{
    private PipedInputStream pis;
    public PipedInputWorker(PipedInputStream pis) {
        this.pis = pis;
    }
    @Override
    public void run() {
        try {
            int i;
            while((i = pis.read()) > -1){
                System.out.print(i + " ");  
            }
            pis.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
```

It also has a [run()] method (because it implements
a [Runnable] interface) that reads from the stream and prints out
each byte until the stream ends (indicated by [-1]). Now let\'s
connect these pipes and execute a [run()] methods of these
classes:


```
PipedOutputStream pos = new PipedOutputStream();
PipedInputStream pis = new PipedInputStream();
try {
    pos.connect(pis);
    new Thread(new PipedOutputWorker(pos)).start();
    new Thread(new PipedInputWorker(pis)).start(); //prints: 1 2 3
} catch (Exception ex) {
    ex.printStackTrace();
}
```

As you can see, the objects of the workers were passed into the
constructor of the [Thread] class. The [start()] method of
the [Thread] object executes the [run()] method of the
passed in [Runnable]. And we see the results we have expected; the
[PipedInputWorker] prints all the bytes written to the piped
stream by the [PipedOutputWorker]. We will get into more details
about threads in [Chapter
8](https://subscription.packtpub.com/book/programming/9781789957051/8), *Multithreading
and Concurrent Processing.*

SequenceInputStream {#sequenceinputstream .header-title}
-------------------

The [SequenceInputStream] class concatenates input streams passed
into one of the following constructors as parameters:

-   [SequenceInputStream(InputStream s1, InputStream s2)]
-   [SequenceInputStream(Enumeration\<InputStream\> e)]

**Enumeration** is a collection of objects of the type indicated in the
angle brackets, called **generics***, *meaning *of type T*.
The [SequenceInputStream] class reads from the first input string
until it ends, whereupon it reads from the second one, and so on, until
the end of the last of the streams. For example, let\'s create
a [howAreYou.txt] file (with the text [How are you?]) in the
[resources] folder next to
the [hello.txt] file. The [SequenceInputStream] class can
then be used as follows:


```
try(FileInputStream fis1 = 
                    new FileInputStream("src/main/resources/hello.txt");
    FileInputStream fis2 = 
                new FileInputStream("src/main/resources/howAreYou.txt");
    SequenceInputStream sis=new SequenceInputStream(fis1, fis2)){
    int i;
    while((i = sis.read()) > -1){
        System.out.print((char)i);       //prints: Hello!How are you?
    }
} catch (Exception ex) {
    ex.printStackTrace();
}
```

Similarly, when an enumeration of input streams is passed in, each of
the streams is read (and printed in our case) until the end.

FilterInputStream {#filterinputstream .header-title}
-----------------

The [FilterInputStream] class is a wrapper around the
[InputStream] object passed as a parameter in the constructor.
Here is the constructor and two [read()] methods of the
[FilterInputStream] class:


```
protected volatile InputStream in;
protected FilterInputStream(InputStream in) { this.in = in; }
public int read() throws IOException { return in.read(); }
public int read(byte b[]) throws IOException { 
    return read(b, 0, b.length);
}
```

All the other methods of the [InputStream] class are overridden
similarly; the function is delegated to the object assigned to
the [in] property.

As you can see, the constructor is protected, which means that only the
child has access to it. Such a design hides from the client the actual
source of the stream and forces the programmer to use one of
the [FilterInputStream] class
extensions: [BufferedInputStream], [CheckedInputStream],
[DataInputStream], [PushbackInputStream],
[javax.crypto.CipherInputStream], [java.util.zip.DeflaterInputStream], [java.util.zip.InflaterInputStream], [java.security.DigestInputStream],
or [javax.swing.ProgressMonitorInputStream]. Alternatively, one
can create a custom extension. But, before creating your own extension,
look at the listed classes and see if one of them fits your needs. Here
is an example of using a [BufferedInputStream] class:


```
try(FileInputStream  fis = 
        new FileInputStream("src/main/resources/hello.txt");
    FilterInputStream filter = new BufferedInputStream(fis)){
    int i;
    while((i = filter.read()) > -1){
        System.out.print((char)i);     //prints: Hello!
    }
} catch (Exception ex) {
    ex.printStackTrace();
}
```

The [BufferedInputStream] class uses the buffer to improve the
performance. When the bytes from the stream are skipped or read, the
internal buffer is automatically refilled with as many bytes as
necessary at the time, from the contained input stream.

The [CheckedInputStream] class adds a checksum of the data being
read that allows the verification of the integrity of the input data
using [getChecksum()] method.

The [DataInputStream] class reads and interprets input data as
primitive Java data types in a machine-independent way.

The [PushbackInputStream] class adds the ability to push back the
read data using the [unread()] method. It is useful in situations
when the code has the logic of analyzing the just read data and deciding
to unread it, so it can be re-read at the next step.

The [javax.crypto.CipherInputStream] class adds a [Cipher]
to the [read()] methods. If the [Cipher] is initialized for
decryption, the [javax.crypto.CipherInputStream] will attempt to
decrypt the data before returning.

The [java.util.zip.DeflaterInputStream] class compresses data in
the deflate compression format.

The [java.util.zip.InflaterInputStream] class uncompresses data in
the deflate compression format.

The [java.security.DigestInputStream] class updates the associated
message digest using the bits going through the stream. The [on (boolean
on)] method turns the digest function on or off. The calculated
digest can be retrieved using the [getMessageDigest()] method.

The [javax.swing.ProgressMonitorInputStream] class provides a
monitor of the progress of reading from the [InputStream]. The
monitor object can be accessed using
the [getProgressMonitor()] method.

javax.sound.sampled.AudioInputStream {#javax.sound.sampled.audioinputstream .header-title}
------------------------------------

The [AudioInputStream] class represents an input stream with a
specified audio format and length. It has the following two
constructors:

-   [AudioInputStream (InputStream stream, AudioFormat format, long
    length)]: Accepts the stream of audio data, the requested
    format, and the length in sample frames
-   [AudioInputStream (TargetDataLine line)]: Accepts the target
    data line indicated

The [javax.sound.sampled.AudioFormat] class describes audio-format
properties such as channels, encoding, frame rate, and similar.
The [javax.sound.sampled.TargetDataLine] class has [open()]
method that opens the line with the specified format
and [read()] method that reads audio data from the data line\'s
input buffer.

There is also the [javax.sound.sampled.AudioSystem] class and its
methods handle [AudioInputStream] objects. They can be used for
reading from an audio file, a stream, or a URL, and write to an audio
file. They also can be used to convert an audio stream to another audio
format.

Class OutputStream and its subclasses {#class-outputstream-and-its-subclasses .header-title}
-------------------------------------

The [OutputStream] class is a peer of the [InputStream]
class that writes data instead of reading. It is an abstract class that
has the following direct implementations in the **Java Class Library**
(**JCL**): [ByteArrayOutputStream], [FilterOutputStream],
[ObjectOutputStream], [PipedOutputStream], and
[FileOutputStream]. 

The [FileOutputStream] class has the following direct extensions:
[BufferedOutputStream], [CheckedOutputStream],
[DataOutputStream], [PrintStream],
[javax.crypto.CipherOutputStream],
[java.util.zip.DeflaterOutputStream],
[java.security.DigestOutputStream],
and [java.util.zip.InflaterOutputStream].

All of them either use as-is or override the following methods of the
[OutputStream] class:

-   [void close()]: Closes the stream and releases the resources
-   [void flush()]: Forces the remaining bytes to be written out
-   [static OutputStream nullOutputStream()]: Creates a
    new [OutputStream] that writes nothing
-   [void write(byte\[\] b)]: Writes the provided byte array to
    the output stream
-   [void write(byte\[\] b, int off, int len)]:
    Writes [len] bytes of the provided byte array starting at
    [off] offset to the output stream
-   [abstract void write(int b)]: Writes the provided byte to the
    output stream

The only method that has to be implemented is [abstract void write(int
b)], but most of the descendants of [OutputStream] class
override many of the other methods too. 

After learning about the input streams in the *Class InputStream and its
subclasses *section, all of the [OutputStream] implementations,
except the [PrintStream] class, should be intuitively familiar to
you. So, we will discuss here only the [PrintStream] class.

Reader and Writer classes and their subclasses {#reader-and-writer-classes-and-their-subclasses .header-title}
----------------------------------------------

As we mentioned several times already, [Reader] and
[Writer] classes are very similar in their function
to [InputStream] and [OutputStream] classes but specialize
in processing texts. They interpret stream bytes as characters and have
their own independent [InputStream] and [OutputStream] class
hierarchy. It is possible to process stream bytes as characters without
[Reader] and [Writer] or any of their subclasses. We have
seen such examples in the preceding sections
describing [InputStream] and [OutputStream] classes.
However, using [Reader] and [Writer] classes makes text
processing simpler and the code easier to read.

Reader and its subclasses {#reader-and-its-subclasses .header-title}
-------------------------

The class [Reader] is an abstract class that reads streams as
characters. It is an analog to [InputStream] and has the following
methods:

-   [abstract void close()]: Closes the stream and other used
    resources
-   [void mark(int readAheadLimit)]: Marks the current position in
    the stream
-   [boolean markSupported()]: Returns [true] if the stream
    supports the [mark()] operation
-   [static Reader nullReader()]: Creates an empty Reader that
    reads no characters
-   [int read()]: Reads one character
-   [int read(char\[\] buf)]: Reads characters into the
    provided [buf] array and returns the count of the read
    characters 
-   [abstract int read(char\[\] buf, int off, int len)]:
    Reads [len] characters into an array starting from
    the [off] index
-   [int read(CharBuffer target)]: Attempts to read characters
    into the provided [target] buffer
-   [boolean ready()]: Returns [true] when the stream is
    ready to be read
-   [void reset()]: Resets the mark; however, not all streams
    support this operation, while some support it, but do not support
    setting a mark


-   [long skip(long n)]: Attempts to skip [n] characters;
    returns the count of skipped characters
-   [long transferTo(Writer out)]: Reads all characters from this
    reader and writes the characters to the provided [Writer]
    object 

As you can see, the only methods that need to be implemented are two
abstract [read()] and [close()] methods. Nevertheless, many
children of this class override other methods too, sometimes for better
performance or different functionality. The [Reader] subclasses in
the JCL are: [CharArrayReader], [InputStreamReader],
[PipedReader], [StringReader], [BufferedReader], and
[FilterReader]. The [BufferedReader] class has
a [LineNumberReader] subclass, and the [FilterReader]
class has a [PushbackReader] subclass.

Writer and its subclasses {#writer-and-its-subclasses .header-title}
-------------------------

The abstract [Writer] class writes to character streams. It is an
analog to [OutputStream] and has the following methods:

-   [Writer append(char c)]: Appends the provided character to the
    stream
-   [Writer append(CharSequence c)]: Appends the provided
    character sequence to the stream
-   [Writer append(CharSequence c, int start, int end)]: Appends a
    subsequence of the provided character sequence to the stream
-   [abstract void close()]: Flushes and closes the stream and
    related system resources
-   [abstract void flush()]: Flushes the stream
-   [static Writer nullWriter()]: Creates a new [Writer]
    object that discards all characters
-   [void write(char\[\] c)]: Writes an array
    of [c] characters
-   [abstract void write(char\[\] c, int off, int len)]: Writes
    [len] elements of an array of [c] characters starting
    from the [off] index
-   [void write(int c)]: Writes one character
-   [void write(String str)]: Writes the provided string
-   [void write(String str, int off, int len)]: Writes a substring
    of [len] length from the provided [str] string starting
    from the [off] index

As you can see, the three abstract methods: [write(char\[\], int,
int)], [flush()], and [close()] must be implemented by
the children of this class. They also typically override other methods
too.

The [Writer] subclasses in the JCL are: [CharArrayWriter],
[OutputStreamWriter], [PipedWriter],
[StringWriter], [BufferedWriter], [FilterWriter],
and [PrintWriter]. The [OutputStreamWriter] class has
a [FileWriter] subclass.

Other classes of java.io package {#other-classes-of-java.io-package .header-title}
--------------------------------

Other classes of the [java.io] package include the following: 

-   [Console]: Allows interacting with the character-based console
    device, associated with the current JVM instance
-   [StreamTokenizer]: Takes an input stream and parses it into
    [tokens]
-   [ObjectStreamClass]: The serialization\'s descriptor for
    classes
-   [ObjectStreamField]: A description of a serializable field
    from a serializable class
-   [RandomAccessFile]: Allows random access for reading from and
    writing to a file, but its discussion is outside the scope of this
    book
-   [File]: Allows creating and managing files and directories;
    described in the *Files management* section

Console {#console .header-title}
-------

There are several ways to create and run a **Java Virtual Machine**
(**JVM**) instance that executes an application. If the JVM is started
from a command line, a console window is automatically opened. It allows
typing on the display from the keyboard; however, the JVM can be started
by a background process too. In such a case, a console is not created.

To check programmatically if a console exists, one can invoke
the [System.console()] static method. If no console device is
available then an invocation of that method will return [null].
Otherwise, it will return an object of the [Console] class that
allows interacting with the console device and the application user.

Let\'s create the following [ConsoleDemo] class:


```
package com.lv.learnjava.ch05_stringsIoStreams;
import java.io.Console;
public class ConsoleDemo {
    public static void main(String... args)  {
        Console console = System.console();
        System.out.println(console);
    }
}
```

If we run it from the IDE, as we usually do, the result will be as
follows:

![]./images_5/b42df196-3db6-4b7f-adf8-83f57dc12cd6.png)

That is because the JVM was launched not from the command line. In order
to do it, let\'s compile our application and create a [.jar] file
by executing the [mvn clean package] Maven command in the root
directory of the project. It will delete the [target] folder, then
recreate it, and compile all the [.java] files to the
corresponding [.class] files in the [target] folder, and
then will archive them in a [.jar] file
[learnjava-1.0.jar].

Now we can launch the [ConsoleDemo] application from the same
project root directory using the following command: 


```
java -cp ./target/learnjava-1.0.jar  
                com.lv.learnjava.ch05_stringsIoStreams.ConsoleDemo
```

The preceding command is shown in two lines because the page width
cannot accommodate it. But if you would like to run it, make sure you do
it as one line. The result will be as follows:

![]./images_5/3a5892a1-6668-4a19-be3d-fab7176e37dc.png)

It tells us that we have the [Console] class object now. Let\'s
see what we can do with it. The class has the following methods:

-   [String readLine()]: Waits until the user hits the *Enter*
    key and reads the line of text from the console
-   [String readLine(String format, Object\... args)]: Displays
    prompt (the message produced after the provided format got the
    placeholders substituted with the provided arguments), waits until
    the user hits key *Enter*, and reads the line of text from the
    console; if no arguments [args] are provided, displays the
    format as the prompt


-   [char\[\] readPassword()]: Performs the same function as
    [readLine()] function but without echoing the typed characters
-   [char\[\] readPassword(String format, Object\...
    args)]: Performs the same function as [readLine(String format,
    Object\... args)] but without echoing the typed characters

Let\'s demonstrate the preceding methods with the following example:


```
Console console = System.console();

String line = console.readLine();
System.out.println("Entered 1: " + line);
line = console.readLine("Enter something 2: ");
System.out.println("Entered 2: " + line);
line = console.readLine("Enter some%s", "thing 3: ");
System.out.println("Entered 3: " + line);

char[] password = console.readPassword();
System.out.println("Entered 4: " + new String(password));
password = console.readPassword("Enter password 5: ");
System.out.println("Entered 5: " + new String(password));
password = console.readPassword("Enter pass%s", "word 6: ");
System.out.println("Entered 6: " + new String(password));
```

The result of the preceding example is as follows:

![]./images_5/4d6b7a3b-239f-44fa-bb04-c7c998b8c63b.png)

Another group of [Console] class methods can be used in
conjunction with the just demonstrated methods:

-   [Console format(String format, Object\... args)]: Substitutes
    the placeholders in the provided [format] string with the
    provided [args] values and displays the result
-   [Console printf(String format, Object\... args)]: Behaves the
    same way as the [format()] method

For example, look at the following line:


```
String line = console.format("Enter some%s", "thing:").readLine();
```

It produces the same result as this line:


```
String line = console.readLine("Enter some%s", "thing:");
```

And, finally, the last three methods of the Console class are as
follows:

-   [PrintWriter writer()]: Creates a [PrintWriter] object
    associated with this console that can be used for producing an
    output stream of characters
-   [Reader reader()]: Creates a [Reader] object associated
    with this console that can be used for reading the input as a stream
    of characters
-   [void flush()]: Flushes the console and forces any buffered
    output to be written immediately

Here is an example of their usage:


```
try (Reader reader = console.reader()){
    char[] chars = new char[10];
    System.out.print("Enter something: ");
    reader.read(chars);
    System.out.print("Entered: " + new String(chars));
} catch (IOException e) {
    e.printStackTrace();
}

PrintWriter out = console.writer();
out.println("Hello!");

console.flush();
```

The result of the preceding code looks as follows:

![]./images_5/92c1540a-8bcc-4cee-84b6-3a5583feb26e.png)

[Reader] and [PrintWriter] can also be used to create other
[Input] and [Output] streams we have been talking about in
this section.

StreamTokenizer {#streamtokenizer .header-title}
---------------

The [StreamTokenizer] class parses the input stream and produces
tokens. Its [StreamTokenizer(Reader r)] constructor accepts
a [Reader] object that is the source of the tokens. Every time
the [int nextToken()] method is called on the
[StreamTokenizer] object, the following happens:

1.  The next token is parsed.
2.  The [StreamTokenizer] instance field [ttype] is
    populated by the value that indicates the token type:
    -   The [ttype] value can be one of the following integer
        constants: [TT\_WORD], [TT\_NUMBER], [TT\_EOL]
        (end of line), or [TT\_EOF] (end of stream).
    -   If the [ttype] value is
        [TT\_WORD], the [StreamTokenizer] instance [sval] field is
        populated by the [String] value of the token.
    -   If the [ttype] value
        is [TT\_NUMBER], the [StreamTokenizer] instance field [nval] is
        populated by the [double] value of the token.
3.  The [lineno()] method of the [StreamTokenizer] instance
    returns the current line number.

Let\'s look at an example before talking about other methods of the
[StreamTokenizer] class. Let\'s assume that, in the project
[resources] folder, there is a [tokens.txt] file that
contains the following four lines of text:


```
There
happened
42
events.
```

The following code will read the file and tokenize its content:


```
String filePath = "src/main/resources/tokens.txt";
try(FileReader fr = new FileReader(filePath);
 BufferedReader br = new BufferedReader(fr)){
 StreamTokenizer st = new StreamTokenizer(br);
    st.eolIsSignificant(true);
    st.commentChar('e');
    System.out.println("Line " + st.lineno() + ":");
    int i;
    while ((i = st.nextToken()) != StreamTokenizer.TT_EOF) {
        switch (i) {
            case StreamTokenizer.TT_EOL:
                System.out.println("\nLine " + st.lineno() + ":");
                break;
            case StreamTokenizer.TT_WORD:
                System.out.println("TT_WORD => " + st.sval);
                break;
            case StreamTokenizer.TT_NUMBER:
                System.out.println("TT_NUMBER => " + st.nval);
                break;
            default:
                System.out.println("Unexpected => " + st.ttype);
        }
    }         
} catch (Exception ex){
    ex.printStackTrace();
}
```

If we run this code, the result will be the following:

![]./images_5/5c49e7b2-57b3-4051-8f8b-1b48146f9fca.png) 

We have used the [BufferedReader] class, which is a good practice
for higher efficiency, but in our case, we could easily avoid it like
this:


```
 FileReader fr = new FileReader(filePath);
 StreamTokenizer st = new StreamTokenizer(fr);
```

The result would not change. We also used the following three methods we
have not described yet:

-   [void eolIsSignificant(boolean flag)]: Indicates whether the
    end-of-line should be treated as a token
-   [void commentChar(int ch)]: Indicates which character starts a
    comment, so the rest of the line is ignored
-   [int lineno()]: Returns the current line number

The following methods can be invoked using the [StreamTokenizer]
object:

-   [void lowerCaseMode(boolean fl)]: Indicates whether a word
    token should be lowercased
-   [void ordinaryChar(int ch)], [void ordinaryChars(int low, int
    hi)]: Indicate a specific character or the range of characters
    that have to be treated as *ordinary* (not as a comment character,
    word component, string delimiter, white space, or number character)
-   [void parseNumbers()]: Indicates that a word token that has
    the format of a double precision floating-point number has to be
    interpreted as a number, rather than a word
-   [void pushBack()]: Forces the [nextToken()] method to
    return the current value of the [ttype] field
-   [void quoteChar(int ch)]: Indicates that the provided
    character has to be interpreted as the beginning and the end of the
    string value that has to be taken as-is (as a quote)
-   [void resetSyntax()]: Resets this tokenizer\'s syntax table so
    that all characters are *ordinary*
-   [void slashSlashComments(boolean flag)]: Indicates that C++
    style comments have to be recognized
-   [void slashStarComments(boolean flag)]: Indicates that C style
    comments have to be recognized
-   [String toString()]: Returns the string representation of the
    token and the line number\
    [void whitespaceChars(int low, int hi)]: Indicates the range
    of characters that have to be interpreted as white space
-   [void wordChars(int low, int hi)]: Indicates the range of
    characters that have to be interpreted as a word

As you can see, using the wealth of the preceding methods allows
fine-tuning of the text interpretation.

ObjectStreamClass and ObjectStreamField {#objectstreamclass-and-objectstreamfield .header-title}
---------------------------------------

The [ObjectStreamClass] and [ObjectStreamField] class
provide access to the serialized data of a class loaded in the
JVM. The [ObjectStreamClass] object can be found/created using one
of the following lookup methods:

-   [static ObjectStreamClass lookup(Class cl)]: Finds the
    descriptor of a serializable class
-   [static ObjectStreamClass lookupAny(Class cl)]: Finds
    the descriptor for any class, whether serializable or not

After the [ObjectStreamClass] is found and the class is
serializable (implements [Serializable] interface), it can be used
to access [ObjectStreamField] objects, each containing information
about one serialized field. If the class is not serializable, there is
no [ObjectStreamField] object associated with any of the fields.

Let\'s look at an example. Here is the method that displays information
obtained from the
[ObjectStreamClass] and [ObjectStreamField] objects :


```
void printInfo(ObjectStreamClass osc) {
    System.out.println(osc.forClass());
    System.out.println("Class name: " + osc.getName());
    System.out.println("SerialVersionUID: " + osc.getSerialVersionUID());
    ObjectStreamField[] fields = osc.getFields();
    System.out.println("Serialized fields:");
    for (ObjectStreamField osf : fields) {
        System.out.println(osf.getName() + ": ");
        System.out.println("\t" + osf.getType());
        System.out.println("\t" + osf.getTypeCode());
        System.out.println("\t" + osf.getTypeString());
    }
}
```

To demonstrate how it works, we created a
serializable [Person1] class:


```
package com.lv.learnjava.ch05_stringsIoStreams;
import java.io.Serializable;
public class Person1 implements Serializable {
    private int age;
    private String name;
    public Person1(int age, String name) {
        this.age = age;
        this.name = name;
    }
}
```

We did not add methods because only the object state is serializable,
not the methods. Now let\'s run the following code:


```
ObjectStreamClass osc1 = ObjectStreamClass.lookup(Person1.class);
printInfo(osc1);
```

The result will be as follows:

![]./images_5/a73baeb7-d8d3-499f-b99b-aae3ada88d4b.png)

As you can see, there is information about the class name and all field
names and types. There are also two other methods that can be called
using the [ObjectStreamField] object: 

-   [boolean isPrimitive()]: Returns [true] if this field
    has a primitive type
-   [boolean isUnshared()]: Returns [true] if this field is
    unshared (private or accessible only from the same package)

Now let\'s create a non-serializable [Person2] class:


```
package com.lv.learnjava.ch05_stringsIoStreams;
public class Person2 {
    private int age;
    private String name;
    public Person2(int age, String name) {
        this.age = age;
        this.name = name;
    }
}
```

This time, we will run the code that only looks up the class as follows:


```
ObjectStreamClass osc2 = ObjectStreamClass.lookup(Person2.class);
System.out.println("osc2: " + osc2);    //prints: null
```

As has been expected, the non-serializable object was not found using
the [lookup()] method. In order to find a non-serializable object,
we need to use the [lookupAny()] method:


```
ObjectStreamClass osc3 = ObjectStreamClass.lookupAny(Person2.class);
printInfo(osc3);
```

If we run the preceding example, the result will be as follows:

![]./images_5/8bc4d72a-a088-4154-a23d-b801babc1c0f.png)

From a non-serializable object, we were able to extract information
about the class, but not about the fields.

Class java.util.Scanner {#class-java.util.scanner .header-title}
-----------------------

The [java.util.Scanner] class is typically used to read an input
from a keyboard but can read text from any object that implements the
[Readable] interface (this interface only has [int read(CharBuffer
buffer)] method). It breaks the input value by a delimiter (white
space is a default delimiter) into tokens that are processed using
different methods.

For example, we can read an input from [System.in]---a standard
input stream, which typically represents the keyboard input:


```
Scanner sc = new Scanner(System.in);
System.out.print("Enter something: ");
while(sc.hasNext()){
    String line = sc.nextLine();
    if("end".equals(line)){
        System.exit(0);
    }
    System.out.println(line);
}
```

It accepts many lines (each line ends after the key *Enter* is pressed)
until the line [*end*] is entered as follows:

![]./images_5/939602f5-20c6-4ddd-9c15-ea53bb0e8b1e.png)

Alternatively, [Scanner] can read lines from a file:


```
String filePath = "src/main/resources/tokens.txt";
try(Scanner sc = new Scanner(new File(filePath))){
    while(sc.hasNextLine()){
        System.out.println(sc.nextLine());
    }
} catch (Exception ex){
    ex.printStackTrace();
}
```

As you can see, we have used the [tokens.txt] file again. The
results are as follows:

![]./images_5/d636780d-a330-43a9-b3d5-dd527c988591.png)

To demonstrate [Scanner] breaking the input by a delimiter, let\'s
run the following code:


```
String input = "One two three";
Scanner sc = new Scanner(input);
while(sc.hasNext()){
    System.out.println(sc.next());
}
```

The result is as follows:

![]./images_5/912666b8-c3f5-453f-be4a-d45aec47dd0d.png)

To use another delimiter, it can be set as follows:


```
String input = "One,two,three";
Scanner sc = new Scanner(input).useDelimiter(",");
while(sc.hasNext()){
    System.out.println(sc.next());
}
```

The result remains the same:

![]./images_5/4b59a98e-30bb-44e8-bfc8-43026a7a3c3c.png)

It is also possible to use a regular expression for extracting the
tokens, but this topic is outside the scope of this book.

The [Scanner] class has many other methods that make its usage
applicable to a variety of sources and required results. The
[findInLine()], [findWithinHorizon()], [skip()], and
[findAll()] methods do not use the delimiter; they just try to
match the provided pattern. For more information, refer to Scanner
documentation
(<https://docs.oracle.com/en/java/javase/12/docs/api/java.base/java/util/Scanner.html>).


File management
================================

We have already used some methods for finding, creating, reading, and
writing files using JCL classes. We had to do it in order to support a
demo code of input/output streams. In this section, we are going to talk
about file management using JCL in more detail.

The [File] class from the [java.io] package represents the
underlying filesystem. An object of the [File] class can be
created with one of the following constructors:

-   [File(String pathname)]: Creates a new [File] instance
    based on the provided pathname
-   [File(String parent, String child)]: Creates a
    new [File] instance based on the provided parent pathname and
    a child pathname
-   [File(File parent, String child)]: Creates a
    new [File] instance based on the
    provided parent [File] object and a child pathname
-   [File(URI uri)]: Creates a new [File] instance based on
    the provided [URI] object that represents the pathname

We will now see the examples of the constructors\' usage while talking
about creating and deleting files.

Creating and deleting files and directories {#creating-and-deleting-files-and-directories .header-title}
-------------------------------------------

To create a file or directory in the filesystem, one needs first to
construct a new [File] object using one of the constructors listed
in the *Files management* section. For example, assuming that the file
name is [FileName.txt], the [File] object can be created as
[new File(\"FileName.txt\")]. If the file has to be created inside
a directory, then either a path has to be added in front of the file
name (when it is passed into the constructor) or one of the other three
constructors has to be used. For example:


```
String path = "demo1" + File.separator + "demo2" + File.separator;
String fileName = "FileName.txt";
File f = new File(path + fileName);
```

Note the usage of [File.separator] instead of the slash symbol
([/]) or ([\\]). That is because the [File.separator]
returns the platform-specific slash symbol. And here is an example of
another [File] constructor usage:


```
String path = "demo1" + File.separator + "demo2" + File.separator;
String fileName = "FileName.txt";
File f = new File(path, fileName);
```

Yet another constructor can be used as follows:


```
String path = "demo1" + File.separator + "demo2" + File.separator;
String fileName = "FileName.txt";
File f = new File(new File(path), fileName);
```

However, if you prefer or have to use a **Universal Resource
Identifier** (**URI**), you can construct a [File] object like
this: 


```
String path = "demo1" + File.separator + "demo2" + File.separator;
String fileName = "FileName.txt";
URI uri = new File(path + fileName).toURI();
File f = new File(uri);
```

Then, one of the following methods has to be invoked on the newly
created [File] object:

-   [boolean createNewFile()]: If a file with this name does not
    yet exist, creates a new file and returns [true]; otherwise,
    returns [false]


-   [static File createTempFile(String prefix, String suffix)]:
    Creates a file in the temporary-file directory
-   [static File createTempFile(String prefix, String suffix, File
    directory)]: Creates the directory; the provided prefix and
    suffix are used to generate the directory name

If the file you would like to create has to be placed inside a directory
that does not exist yet, one of the following methods has to be used
first, invoked on the [File] object that represents the filesystem
path to the file:

-   [boolean mkdir()]: creates the directory with the provided
    name
-   [boolean mkdirs()]: Creates the directory with the provided
    name, including any necessary but nonexistent parent directories

And, before we look at a code example, we need to explain how
the [delete()] method works:

-   [boolean delete()]: Deletes the file or empty directory, which
    means you can delete the file but not all of the directories as
    follows:


```
String path = "demo1" + File.separator + "demo2" + File.separator;
String fileName = "FileName.txt";
File f = new File(path + fileName);
f.delete();
```

Let\'s look at how to overcome this limitation in the following example:


```
String path = "demo1" + File.separator + "demo2" + File.separator;
String fileName = "FileName.txt";
File f = new File(path + fileName);
try {
    new File(path).mkdirs();
    f.createNewFile();
    f.delete();
    path = StringUtils.substringBeforeLast(path, File.separator);
    while (new File(path).delete()) {
        path = StringUtils.substringBeforeLast(path, File.separator);
    }
} catch (Exception e) {
    e.printStackTrace();
}
```

This example creates and deletes a file and all related
directories. Notice our usage of
the [org.apache.commons.lang3.StringUtils] class, which we have
discussed in the *String utilities* section. It allowed us to
remove from the path the just deleted directory and to continue doing it
until all the nested directories are deleted, and the top level
directory is deleted last. 

Listing files and directories {#listing-files-and-directories .header-title}
-----------------------------

The following methods can be used for listing directories and the files
in them:

-   [String\[\] list()]: Returns names of the files and
    directories in the directory
-   [File\[\] listFiles()]: Returns [File] objects that
    represent the files and directories in the directory
-   [static File\[\] listRoots()]: Lists the available filesystem
    roots

In order to demonstrate the preceding methods, let\'s assume we have
created the directories and two files in them, as follows:


```
String path1 = "demo1" + File.separator;
String path2 = "demo2" + File.separator;
String path = path1 + path2;
File f1 = new File(path + "file1.txt");
File f2 = new File(path + "file2.txt");
File dir1 = new File(path1);
File dir = new File(path);
dir.mkdirs();
f1.createNewFile();
f2.createNewFile();
```

After that, we should be able to run the following code:


```
System.out.print("\ndir1.list(): ");
for(String d: dir1.list()){
    System.out.print(d + " ");
}
System.out.print("\ndir1.listFiles(): ");
for(File f: dir1.listFiles()){
    System.out.print(f + " ");
}
System.out.print("\ndir.list(): ");
for(String d: dir.list()){
    System.out.print(d + " ");
}
System.out.print("\ndir.listFiles(): ");
for(File f: dir.listFiles()){
    System.out.print(f + " ");
}
System.out.print("\nFile.listRoots(): ");
for(File f: File.listRoots()){
    System.out.print(f + " ");
}
```

The result should be as follows:

![]./images_5/c20b7299-6f08-4375-834d-a5dfae9732d5.png)

The demonstrated methods can be enhanced by adding the following filters
to them, so they will list only the files and directories that match the
filter:

-   [String\[\] list(FilenameFilter filter)]
-   [File\[\] listFiles(FileFilter filter)]
-   [File\[\] listFiles(FilenameFilter filter)]

However, discussion of the file filters is outside the scope of this
book.


Apache Commons utilities FileUtils and IOUtils
================================

The popular companion of JCL is the Apache Commons project
([https://commons.apache.org](https://commons.apache.org/)) that
provides many libraries that compliment the JCL functionality. The
classes of the [org.apache.commons.io] package are contained in
the following root package and sub-packages:

-   The [org.apache.commons.io] root package contains utility
    classes with static methods for common tasks, like the
    popular [FileUtils] and [IOUtils] classes described in
    the sections *Class FileUtils* and *Class IOUtils*, respectively.
-   The [org.apache.commons.io.input] package contains classes
    that support input based
    on [InputStream] and [Reader] implementations,
    like [XmlStreamReader] or [ReversedLinesFileReader].


-   The [org.apache.commons.io.output] package contains classes
    that support output based
    on [OutputStream] and [Writer] implementations,
    like [XmlStreamWriter] or [StringBuilderWriter].
-   The [org.apache.commons.io.filefilter] package contains
    classes that serve as file filters,
    like [DirectoryFileFilter] or [RegexFileFilter].
-   The [org.apache.commons.io.comparator] package contains
    various implementations of [java.util.Comparator] for files,
    like [NameFileComparator].
-   The [org.apache.commons.io.serialization] package provides a
    framework for controlling the deserialization of classes.
-   The [org.apache.commons.io.monitor] package allows monitoring
    filesystems and checking for a directory or file creating, updating,
    or deleting; one can launch the [FileAlterationMonitor] object
    as a thread and create an object of
    [FileAlterationObserver] that performs a check of the changes
    in the filesystem at the specified interval.

Refer to Apache Commons project documentation
([https://commons.apache.org](https://commons.apache.org/)) for more
details.

Class FileUtils {#class-fileutils .header-title}
---------------

A popular [org.apache.commons.io.FileUtils] class allows doing all
possible operations with files, as follows:

-   Writing to a file
-   Reading from a file
-   Making a directory including parent directories
-   Copying files and directories
-   Deleting files and directories
-   Converting to and from a URL
-   Listing files and directories by filter and extension
-   Comparing files content
-   Getting a file last-changed date
-   Calculating a checksum

If you plan to manage files and directories programmatically, it is
imperative that you study the documentation of this class on the Apache
Commons project website
(<https://commons.apache.org/proper/commons-io/javadocs/api-2.5/org/apache/commons/io/FileUtils.html>).

Class IOUtils {#class-ioutils .header-title}
-------------

The [org.apache.commons.io.IOUtils] is another very useful utility
class that provides the following general IO streams manipulation
methods:

-   The [closeQuietly] methods that close a stream ignoring nulls
    and exceptions
-   [toXxx/read] methods that read data from a stream
-   [write] methods that write data to a stream
-   [copy] methods that copy all the data from one stream to
    another
-   [contentEquals] methods that compare the content of two
    streams

All the methods in this class that read a stream are buffered
internally, so there is no need to use the [BufferedInputStream]
or [BufferedReader] class. The [copy] methods all use
[copyLarge] methods behind the scene that substantially increase
their performance and efficiency.

This class is indispensable for managing the IO streams. See more
details about this class and its methods on the Apache Commons project
website
(<https://commons.apache.org/proper/commons-io/javadocs/api-2.5/org/apache/commons/io/IOUtils.html>).



Summary
================================

In this chapter, we have discussed the [String] class methods that
allow analyzing strings, comparing, and transforming them. We have also
discussed popular string utilities from JCL and the Apache Commons
project. Two big sections of this chapter were dedicated to the
input/output streams and the supporting classes in JCL and the Apache
Commons project. The file managing classes and their methods were also
discussed and demonstrated in specific code examples.

In the next chapter, we will present the Java collections framework and
its three main interfaces, [List], [Set], and [Map],
including discussion and demonstration of generics. We will also discuss
utility classes for managing arrays, objects, and time/date values.
