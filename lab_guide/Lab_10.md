<img align="right" src="../logo-small.png">


Network Programming 
===================

In this chapter, we will describe and discuss the most popular network
protocols -- **User Datagram Protocol** (**UDP**), **Transmission
Control Protocol** (**TCP**), **HyperText Transfer Protocol**
(**HTTP**), and **WebSocket** -- and their support from the **Java Class
Library** (**JCL**). We will demonstrate how to use these protocols and
how to implement client-server communication in Java code. We will
also review **Uniform Resource Locator** (**URL**)-based communication
and the latest **Java HTTP Client API**.

The following topics will be covered in this chapter:

-   Network protocols
-   UDP-based communication
-   TCP-based communication
-   UDP versus TCP protocols
-   URL-based communication
-   Using the HTTP 2 Client API


### Run Java Code
You can run the example by running following command in the terminal:
`java -cp target/learnjava-1.0.jar com.lv.learnjava.ch11_network.HttpClientDemo.java`

### Run Java Code
You can run the example by running following command in the terminal:
`java -cp target/learnjava-1.0.jar com.lv.learnjava.ch11_network.TcpClient.java`

### Run Java Code
You can run the example by running following command in the terminal:
`java -cp target/learnjava-1.0.jar com.lv.learnjava.ch11_network.TcpServer.java`

### Run Java Code
You can run the example by running following command in the terminal:
`java -cp target/learnjava-1.0.jar com.lv.learnjava.ch11_network.UdpReceiver.java`

### Run Java Code
You can run the example by running following command in the terminal:
`java -cp target/learnjava-1.0.jar com.lv.learnjava.ch11_network.UdpSender.java`

### Run Java Code
You can run the example by running following command in the terminal:
`java -cp target/learnjava-1.0.jar com.lv.learnjava.ch11_network.UrlClient.java`

### Run Java Code
You can run the example by running following command in the terminal:
`java -cp target/learnjava-1.0.jar com.lv.learnjava.ch11_network.UrlServer.java`

Network protocols
===================

Network programming is a vast area. The **internet protocol** (**IP**)
suite consists of four layers, each of which has a dozen or more
protocols:

-   **The link layer**: The group of protocols used when a client is
    physically connected to the host; three core protocols include the
    **Address Resolution Protocol** (**ARP**), the **Reverse Address
    Resolution** **Protocol** (**RARP**), and the **Neighbor Discovery
    Protocol** (**NDP**).
-   **The internet layer**: The group of inter-networking methods,
    protocols, and specifications used to transport network packets from
    the originating host to the destination host, specified by an IP
    address. The core protocols of this layer are **Internet Protocol
    version 4** (**IPv4**) and **Internet Protocol version 6**
    (**IPv6**); IPv6 specifies a new packet format and allocates 128
    bits for the dotted IP address, compared to 32 bits in IPv4. An
    example of an IPv4 address
    is [10011010.00010111.11111110.00010001], which results in an
    IP address of [154.23.254.17].
-   **The transport layer**: The group of host-to-host communication
    services. It includes TCP, also known as TCP/IP protocol, and UDP
    (which we are going to discuss shortly); other protocols in this
    group are the **Datagram Congestion Control Protocol** (**DCCP**)
    and the **Stream Control Transmission Protocol** (**SCTP**).
-   **The application layer**: The group of protocols and interface
    methods used by hosts in a communication network. It includes
    **Telnet**, **File Transfer Protocol** (**FTP**), **Domain Name
    System** (**DNS**), **Simple Mail Transfer Protocol** (**SMTP**),
    **Lightweight Directory Access Protocol** (**LDAP**), **Hypertext
    Transfer Protocol** (**HTTP**), **Hypertext Transfer Protocol
    Secure** (**HTTPS**), and **Secure Shell** (**SSH**).

The link layer is the lowest layer; it is used by the internet layer
that is, in turn, used by the transport layer. This transport layer is
then used by the application layer in support of the protocol
implementations.

For security reasons, Java does not provide access to the protocols of
the link layer and the internet layer. This means that Java does not
allow you to create custom transport protocols that, for example, serve
as an alternative to TCP/IP. That is why, in this chapter, we will
review only the protocols of the transport layer (TCP and UDP) and the
application layer (HTTP). We will explain and demonstrate how Java
supports them and how a Java application can take advantage of this
support.

Java supports the TCP and UDP protocols with classes of the
[java.net] package, while the HTTP protocol can be implemented in
the Java application using the classes of the [java.net.http]
package (which was introduced with Java 11).

Both the TCP and UDP protocols can be implemented in Java using
*sockets*. Sockets are identified by a combination of an IP address and
a port number, and they represent a connection between two
applications. Since the UDP protocol is somewhat simpler than the TCP
protocol, we\'ll start with UDP.


UDP-based communication
===================

The UDP protocol was designed by David P. Reed in 1980. It allows
applications to send messages called **datagrams** using a simple
connectionless communication model with a minimal protocol mechanism,
such as a checksum, for data integrity. It has no handshaking dialogs
and, thus, does not guarantee message delivery or preserving the order
of messages. It is suitable for those cases when dropping messages or
mixing up orders are preferred to waiting for retransmission.

A datagram is represented by the [java.net.DatagramPacket] class.
An object of this class can be created using one of six constructors;
the following two constructors are the most commonly used:

-   [DatagramPacket(byte\[\] buffer, int length)]: This
    constructor creates a datagram packet and is used to receive the
    packets; [buffer] holds the incoming datagram,
    while [length] is the number of bytes to be read.
-   [DatagramPacket(byte\[\] buffer, int length, InetAddress address,
    int port)]: This creates a datagram packet and is used to send
    the packets; [buffer] holds the packet data, [length] is
    the packet data length, [address] holds the destination IP
    address, and [port] is the destination port number.

Once constructed, the [DatagramPacket] object exposes the
following methods that can be used to extract data from the object or
set/get its properties:

-   [void setAddress(InetAddress iaddr)]: This sets the
    destination IP address.
-   [InetAddress getAddress()]: This returns the destination or
    source IP address.
-   [void setData(byte\[\] buf)]: This sets the data buffer.
-   [void setData(byte\[\] buf, int offset, int length)]: This
    sets the data buffer, data offset, and length.
-   [void setLength(int length)]: This sets the length for the
    packet.
-   [byte\[\] getData()]: This returns the data buffer
-   [int getLength()]: This returns the length of the packet that
    is to be sent or received.
-   [int getOffset()]: This returns the offset of the data that is
    to be sent or received.
-   [void setPort(int port)]: This sets the destination port
    number.
-   [int getPort()]: This returns the port number where data is to
    be sent or received from.

Once a [DatagramPacket] object is created, it can be sent or
received using the [DatagramSocket] class, which represents a
connectionless socket for sending and receiving datagram packets. An
object of this class can be created using one of six constructors; the
following three constructors are the most commonly used:

-   [DatagramSocket()]: This creates a datagram socket and binds
    it to any available port on the local host machine. It is typically
    used to create a sending socket because the destination address (and
    port) can be set inside the packet (see the preceding
    [DatagramPacket] constructors and methods).
-   [DatagramSocket(int port)]: This creates a datagram socket and
    binds it to the specified port on the local host machine. It is used
    to create a receiving socket when any local machine address (called
    a **wildcard address**) is good enough.
-   [DatagramSocket(int port, InetAddress address)]: This creates
    a datagram socket and binds it to the specified port and the
    specified local address; the local port must be between [0]
    and [65535]. It is used to create a receiving socket when a
    particular local machine address needs to be bound.

The following two methods of the [DatagramSocket] object are the
most commonly used for sending and receiving messages (or packets):

-   [void send(DatagramPacket p)]: This sends the specified
    packet.
-   [void receive(DatagramPacket p)]: This receives a packet by
    filling the specified [DatagramPacket] object\'s buffer with
    the data received. The specified [DatagramPacket] object also
    contains the sender\'s IP address and the port number on the
    sender\'s machine.

Let\'s take a look at a code example; here is the UDP message receiver
that exits after the message has been received:


```
public class UdpReceiver {
   public static void main(String[] args){
        try(DatagramSocket ds = new DatagramSocket(3333)){
            DatagramPacket dp = new DatagramPacket(new byte[16], 16);
            ds.receive(dp);
            for(byte b: dp.getData()){
                System.out.print(Character.toString(b));
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
```

As you can see, the receiver is listening for a text message (it
interprets each byte as a character) on any address of the local
machine on port [3333]. It uses a buffer of 16 bytes only; as soon
as the buffer is filled with the received data, the receiver prints its
content and exits.

Here is an example of the UDP message sender:


```
public class UdpSender {
    public static void main(String[] args) {
        try(DatagramSocket ds = new DatagramSocket()){
            String msg = "Hi, there! How are you?";
            InetAddress address = InetAddress.getByName("127.0.0.1");
            DatagramPacket dp = new DatagramPacket(msg.getBytes(), 
                                        msg.length(), address, 3333);
            ds.send(dp);
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
```

As you can see, the sender constructs a packet with the message, the
local machine address, and the same port as the one that the receiver
uses. After the constructed packet is sent, the sender exits.

We can run the sender now, but without the receiver running there is
nobody to get the message. So, we\'ll start the receiver first. It
listens on port [3333], but there is no message coming -- so it
waits. Then, we run the sender and the receiver displays the following
message:

![]./images_10/19c064a6-7d2d-4c70-99f3-90d07208b57f.png)

Since the buffer is smaller than the message, it was only partially
received -- the rest of the message is lost. We can create an infinite
loop and let the receiver run indefinitely:


```
while(true){
    ds.receive(dp);
    for(byte b: dp.getData()){
        System.out.print(Character.toString(b));
    }
    System.out.println();
}
```

By doing so, we can run the sender several times; here is what the
receiver prints if we run the sender three times:

![]./images_10/891f896e-6289-42c2-8ddc-e0fdebb3ef79.png)

As you can see, all three messages are received; however, only the first
16 bytes of each message are captured by the receiver. 

Now let\'s make the receiving buffer bigger than the message:


```
DatagramPacket dp = new DatagramPacket(new byte[30], 30);
```

If we send the same message now, the result will be as follows:

![]./images_10/9cd256d1-287b-4758-a27e-0183b42c639c.png)

To avoid processing empty buffer elements, you can use
the [getLength()] method of the [DatagramPacket] class,
which returns the actual number of buffer elements filled with the
message:


```
int i = 1;
for(byte b: dp.getData()){
    System.out.print(Character.toString(b));
    if(i++ == dp.getLength()){
        break;
    }
}
```

The result of the preceding code will be as follows:

![]./images_10/025f0a32-52f2-4051-bc37-f07729fea109.png)

So, this is the basic idea of the UDP protocol. The sender sends a
message to a certain address and port even if there is no socket that
*listens* on this address and port. It does not require establishing any
kind of connection before sending the message, which makes the UDP
protocol faster and more lightweight than the TCP protocol (which
requires you to establish the connection first). This way, the TCP
protocol takes message sending to another level of reliability -- by
making sure that the destination exists and that the message can be
delivered.

TCP-based communication
===================

TCP was designed by the **Defense Advanced Research Projects Agency**
(**DARPA**) in the 1970s for use in the **Advanced Research Projects
Agency Network** (**ARPANET**). It complements IP and, thus, is also
referred to as TCP/IP. The TCP protocol, even by its name, indicates
that it provides reliable (that is, error-checked or controlled) data
transmission. It allows the ordered delivery of bytes in an IP network
and is widely used by the web, email, secure shell, and file transfer. 

An application that uses TCP/IP is not even aware of all the handshaking
that takes place between the socket and the transmission details -- such
as network congestion, traffic load balancing, duplication, and even
loss of some IP packets. The underlying protocol implementation of the
transport layer detects these problems, resends the data, reconstructs
the order of the sent packets, and minimizes network congestion. 

In contrast to the UDP protocol, TCP/IP-based communication is focused
on accurate delivery at the expense of the delivery period. That\'s why
it is not used for real-time applications, such as voice over IP, where
reliable delivery and correct sequential ordering are required. However,
if every bit needs to arrive exactly as it was sent and in the same
sequence, then TCP/IP is irreplaceable. 

To support such behavior, TCP/IP communication maintains a session
throughout the communication. The session is identified by the client
address and port. Each session is represented by an entry in a table on
the server. This contains all the metadata about the session: the client
IP address and port, the connection status, and the buffer parameters.
But these details are usually hidden from the application developer, so
we won\'t go into any more detail here. Instead, we will turn to the
Java code.

Similar to the UDP protocol, the TCP/IP protocol implementation in Java
uses sockets. But instead of the [java.net.DatagramSocket] class
that implements the UDP protocol, the TCP/IP-based sockets are
represented by the [java.net.ServerSocket] and
[java.net.Socket] classes. They allow sending and receiving
messages between two applications, one of them being a server and the
other a client.

The [ServerSocket] and [SocketClass] classes perform very
similar jobs. The only difference is that the [ServerSocket] class
has the [accept()] method that *accepts* the request from the
client. This means that the server has to be up and ready to receive the
request first. Then, the connection is initiated by the client that
creates its own socket that sends the connection request (from the
constructor of the [Socket] class). The server then accepts the
request and creates a local socket connected to the remote socket (on
the client side).

After establishing the connection, data transmission can occur using I/O
streams as described in [Chapter
5](https://subscription.packtpub.com/book/programming/9781789957051/5), *Strings,
Input/Output, and Files*. The [Socket] object has
the [getOutputStream()] and [getInputStream()] methods that
provide access to the socket\'s data streams. Data from
the [java.io.OutputStream] object on the local computer appear as
coming from the [java.io.InputStream] object on the remote
machine.

Let\'s now take a closer look at
the [java.net.ServerSocket] and [java.net.Socket] classes,
and then run some examples of their usage.

The java.net.ServerSocket class {#the-java.net.serversocket-class .header-title}
-------------------------------

The [java.net.ServerSocket] class has four constructors:

-   [ServerSocket()]: This creates a server socket object that is
    not bound to a particular address and port. It requires the use of
    the [bind()] method to bind the socket.
-   [ServerSocket(int port)]: This creates a server socket object
    bound to the provided port. The [port] value must be between
    [0] and [65535]. If the port number is specified as a
    value of [0], this means that the port number needs to be
    bound automatically. By default, the maximum queue length for
    incoming connections is [50].
-   [ServerSocket(int port, int backlog)]: This provides the same
    functionality as the [ServerSocket(int port)] constructor, and
    allows you to set the maximum queue length for incoming connections
    by the [backlog] parameter.
-   [ServerSocket(int port, int backlog, InetAddress bindAddr)]:
    This creates a server socket object that is similar to the preceding
    constructor, but also bound to the provided IP address. When
    the [bindAddr] value is [null], it will default to
    accepting connections on any or all local addresses.

The following four methods of the [ServerSocket] class are the
most commonly used, and they are essential for establishing a socket\'s
connection:

-   [void bind(SocketAddress endpoint)]: This binds the
    [ServerSocket] object to a specific IP address and port. If
    the provided address is [null], then the system will pick up a
    port and a valid local address automatically (which can be later
    retrieved using the [getLocalPort()],
    [getLocalSocketAddress()], and [getInetAddress()]
    methods). Additionally, if the [ServerSocket] object was
    created by the constructor without any parameters, then this method,
    or the following [bind()] method, needs to be invoked before a
    connection can be established.
-   [void bind(SocketAddress endpoint, int backlog)]: This acts in
    a similar way to the preceding method; the [backlog] argument
    is the maximum number of pending connections on the socket (that is,
    the size of the queue). If the [backlog] value is less than or
    equal to [0], then an implementation-specific default will be
    used.
-   [void setSoTimeout(int timeout)]: This sets the value (in
    milliseconds) of how long the socket waits for a client after
    the [accept()] method is called. If the client has not called
    and the timeout expires, an
    [java.net.SocketTimeoutException] exception is thrown, but the
    [ServerSocket] object remains valid and can be reused.
    The [timeout] value of [0] is interpreted as an infinite
    timeout (the [accept()] method blocks until a client calls).
-   [Socket accept()]: This blocks until a client calls or the
    timeout period (if set) expires.

Other methods of the class allow you to set or get other properties of
the [Socket] object and they can be used for better dynamic
management of the socket connection. You can refer to the online
documentation of the class to understand the available options in more
detail.

The following code is an example of a server implementation using
the [ServerSocket] class:


```
public class TcpServer {
  public static void main(String[] args){
    try(Socket s = new ServerSocket(3333).accept();
      DataInputStream dis = new DataInputStream(s.getInputStream());
      DataOutputStream dout = new DataOutputStream(s.getOutputStream());
      BufferedReader console = 
                  new BufferedReader(new InputStreamReader(System.in))){
      while(true){
         String msg = dis.readUTF();
         System.out.println("Client said: " + msg);
         if("end".equalsIgnoreCase(msg)){
             break;
         }
         System.out.print("Say something: ");
         msg = console.readLine();
         dout.writeUTF(msg);
         dout.flush();
         if("end".equalsIgnoreCase(msg)){
             break;
         }
      }
    } catch(Exception ex) {
      ex.printStackTrace();
    }
  }
}
```

Let\'s walk through the preceding code. In the try-with-resources
statement, we create [Socket], [DataInputStream], and
[DataOutputStream] objects based on our newly-created socket, and
the [BufferedReader] object to read the user input from the
console (we will use it to enter the data). While creating the socket,
the [accept()] method blocks until a client tries to connect to
port [3333] of the local server. 

Then, the code enters an infinite loop. First, it reads the bytes sent
by the client as a Unicode character string encoded in a modified UTF-8
format by using the [readUTF()] method of [DataInputStream].
The result is printed with the [\"Client said: \"] prefix. If the
received message is an [\"end\"] string, then the code exits the
loop and the server\'s program exits. If the message is not
[\"end\"], then the [\"Say something: \"] prompt is
displayed on the console and the [readLine()] method blocks until
a user types something and clicks on *Enter*. 

The server takes the input from the screen and writes it as a Unicode
character string to the output stream using
the [writeUtf()] method. As we mentioned already, the output
stream of the server is connected to the input stream of the client. If
the client reads from the input stream, it receives the message sent by
the server. If the sent message is [\"end\"], then the sever exits
the loop and the program. If not, then the loop body is executed again.

The described algorithm assumes that the client exits only when it sends
or receives the [\"end\"] message. Otherwise, the client generates
an exception if it tries to send a message to the server afterward. This
demonstrates the difference between the UDP and TCP protocols that we
mentioned already -- TCP is based on the session that is established
between the server and client sockets. If one side drops it, the other
side immediately encounters an error.

Now let\'s review an example of a TCP-client implementation.

The java.net.Socket class {#the-java.net.socket-class .header-title}
-------------------------

The [java.net.Socket] class should now be familiar to you since it
was used in the preceding example. We used it to access the input and
output streams of the connected sockets. Now we are going to review
the [Socket] class systematically and explore how it can be used
to create a TCP client. The [Socket] class has four constructors:

-   [Socket()]: This creates an unconnected socket. It uses
    the [connect()] method to establish a connection of this
    socket with a socket on a server.
-   [Socket(String host, int port)]: This creates a socket and
    connects it to the provided port on the [host] server. If it
    throws an exception, the connection to the server is not
    established; otherwise; you can start sending data to the server.
-   [Socket(InetAddress address, int port)]: This acts in a
    similar way to the preceding constructor, except that the host is
    provided as an [InetAddress] object.
-   [Socket(String host, int port, InetAddress localAddr, int
    localPort)]: This works in a similar way to the preceding
    constructor, except that it also allows you to bind the socket to
    the provided local address and port (if the program is run on a
    machine with multiple IP addresses). If the provided
    [localAddr] value is [null], any local address is
    selected. Alternatively, if the provided [localPort] value is
    [null], then the system picks up a free port in the bind
    operation.
-   [Socket(InetAddress address, int port, InetAddress localAddr, int
    localPort)]: This acts in a similar way to the preceding
    constructor, except that the local address is provided as
    an [InetAddress] object.

Here are the following two methods of the [Socket] class that we
have used already:

-   [InputStream getInputStream()]: This returns an object that
    represents the source (the remote socket) and brings the data
    (inputs them) into the program (the local socket).


-   [OutputStream getOutputStream()]: This returns an object that
    represents the source (the local socket) and sends the data (outputs
    them) to a remote socket.

Let\'s now examine the TCP-client code, as follows:


```
public class TcpClient {
  public static void main(String[] args) {
    try(Socket s = new Socket("localhost",3333);
      DataInputStream dis = new DataInputStream(s.getInputStream());
      DataOutputStream dout = new DataOutputStream(s.getOutputStream());
      BufferedReader console = 
                  new BufferedReader(new InputStreamReader(System.in))){
         String prompt = "Say something: ";
         System.out.print(prompt);
         String msg;
         while ((msg = console.readLine()) != null) {
             dout.writeUTF( msg);
             dout.flush();
             if (msg.equalsIgnoreCase("end")) {
                 break;
             }
             msg = dis.readUTF();
             System.out.println("Server said: " +msg);
             if (msg.equalsIgnoreCase("end")) {
                 break;
             }
             System.out.print(prompt);
         }
    } catch(Exception ex){
          ex.printStackTrace();
    }
  }
}
```

The preceding [TcpClient] code looks almost exactly the same as
the [TcpServer] code we reviewed. The only principal difference is
that the [new Socket(\"localhost\", 3333)] constructor attempts to
establish a connection with the [\"localhost:3333\"] server
immediately, so it expects that the [localhost] server is up and
listening on port [3333]; the rest is the same as the server code.

Therefore, the only reason we need to use the [ServerSocket]
class is to allow the server to run while waiting for the client to
connect to it; everything else can be done using only the [Socket]
class.

Other methods of the [Socket] class allow you to set or get other
properties of the [socket] object, and they can be used for better
dynamic management of the socket connection. You can read the online
documentation of the class to understand the available options in more
detail.

Running the examples {#running-the-examples .header-title}
--------------------

Let\'s now run the [TcpServer] and [TcpClient] programs. If
we start [TcpClient] first, we get
[java.net.ConnectException] with the [Connection
refused] message. So, we launch the [TcpServer]
program first. When it starts, no messages are displayed, instead, it
just waits until the client connects. So, we then start
[TcpClient] and see the following message on the screen:

![]./images_10/b2dbbcd5-c089-4aa0-a3d1-e67764cf1f73.png)

We type [Hello!] and click on *Enter*:

![]./images_10/fe9d26c4-3329-4e17-8b8f-fc75a249aa89.png)

Now let\'s look at the server-side screen:

![]./images_10/4dc09de3-7adf-4072-84bb-a78abd970b84.png)

We type [Hi!] on the server-side screen and click on *Enter*:

![]./images_10/856f1d6e-55bc-45d1-be77-27d3927f9295.png)

On the client-side screen, we see the following messages:

![]./images_10/57f0bd93-ea43-43fb-9529-bbea8b385d1f.png)

We can continue this dialog indefinitely until the server or the client
sends the message, [end]. Let\'s make the client do it; the client
says [end] and then exits:

![]./images_10/858b4dd4-c4aa-498f-a8a3-60a59d95f00c.png)

Then, the server follows suit:

![]./images_10/0d7f3ac1-836b-494a-9b8d-c805ff96f46f.png)

That\'s all we wanted to demonstrate while discussing the TCP protocol.
Now let\'s review the differences between the UDP and TCP protocols.


UDP versus TCP protocols
===================

The differences between the UDP and TCP/IP protocol can be listed as
follows:

-   UDP simply sends data, whether the data receiver is up and running
    or not. That\'s why UDP is better suited to sending data compared to
    many other clients using multicast distribution. TCP, on the other
    hand, requires establishing the connection between the client and
    the server first. The TCP client sends a special control message;
    the server receives it and responds with a confirmation. The client
    then sends a message to the server that acknowledges the server
    confirmation. Only after this, data transmission between the client
    and server is possible. 
-   TCP guarantees message delivery or raises an error, while UDP does
    not, and a datagram packet may be lost.
-   TCP guarantees the preservation of the order of messages on
    delivery, while UDP does not.
-   As a result of these provided guarantees, TCP is slower than UDP. 
-   Additionally, protocols require headers to be sent along with the
    packet. The header size of a TCP packet is 20 bytes, while a
    datagram packet is 8 bytes. The UDP header contains [Length],
    [Source Port], [Destination Port], and [Checksum],
    while the TCP header contains [Sequence Number], [Ack
    Number], [Data Offset], [Reserved], [Control
    Bit], [Window], [Urgent Pointer], [Options],
    and [Padding], in addition to the UDP headers. 
-   There are different application protocols that are based on the TCP
    or UDP protocols. The **TCP**-based protocols are **HTTP**,
    **HTTPS**, **Telnet**, **FTP**, and **SMTP**. The
    **UDP**-based protocols are **Dynamic Host Configuration Protocol**
    (**DHCP**), **DNS**, **Simple ****Network Management Protocol**
    (**SNMP**), **Trivial File Transfer Protocol** (**TFTP**),
    **Bootstrap Protocol** (**BOOTP**), and early versions of
    the **Network File System** (**NFS**).

We can capture the difference between UDP and TCP in one sentence: the
UDP protocol is faster and more lightweight than TCP, but less reliable.
As with many things in life, you have to pay a higher price for
additional services. However, not all these services will be needed in
all cases, so think about the task in hand and decide which protocol to
use based on your application requirements.

URL-based communication
===================

Nowadays, it seems that everybody has some notion of a URL; those who
use a browser on their computers or smartphones will see URLs every day.
In this section, we will briefly explain the different parts that make
up a URL and demonstrate how it can be used programmatically to request
data from a website (or a file) or to send (post) data to a website.

The URL syntax {#the-url-syntax .header-title}
--------------

Generally speaking, the URL syntax complies with the syntax of
a **Uniform Resource Identifier** (**URI**) that has the following
format:


```
scheme:[//authority]path[?query][#fragment]
```

The square brackets indicate that the component is optional. This means
that a URI will consist of [scheme:path] at the very least.
The [scheme] component can
be [http], [https], [ftp], [mailto], [file], [data],
or another value. The [path] component consists of a sequence of
path segments separated by a slash ([/]). Here is an example of a
URL consisting only of [scheme] and [path]:


```
file:src/main/resources/hello.txt
```

The preceding URL points to a file on a local filesystem that is
relative to the directory where this URL is used. We will demonstrate
how it works shortly.

The [path] component can be empty, but then the URL would seem
useless. Nevertheless, an empty path is often used in conjunction with
[authority], which has the following format:


```
[userinfo@]host[:port]
```

The only required component of authority is [host], which can be
either an IP address ([137.254.120.50], for example) or a domain
name ([oracle.com], for example).

The [userinfo] component is typically used with
the [mailto] value of the [scheme] component, so
[userinfo\@host] represents an email address.

The [port] component, if omitted, assumes a default value. For
example, if the [scheme] value is [http], then the default
[port] value is [80], and if the [scheme] value
is [https], then the default [port] value is [443].

An optional [query] component of a URL is a sequence of key-value
pairs separated by a delimiter ([&]):


```
key1=value1&key2=value2
```

Finally, the optional [fragment] component is an identifier of a
section of an HTML document, so that a browser can scroll this section
into view.

It is necessary to mention that Oracle\'s online documentation uses
slightly different terminology:

-   [protocol] instead of [scheme]
-   [reference] instead of [fragment]
-   [file] instead of [path\[?query\]\[\#fragment\]]
-   [resource] instead of
    [host\[:port\]path\[?query\]\[\#fragment\]]

So, from the Oracle documentation perspective, the URL is composed
of [protocol] and [resource] values.

Let\'s now take a look at the programmatic usage of URLs in Java.

The java.net.URL class {#the-java.net.url-class .header-title}
----------------------

In Java, a URL is represented by an object of the [java.net.URL]
class that has six constructors:

-   [URL(String spec)]: This creates a [URL] object from the
    URL as a string.
-   [URL(String protocol, String host, String file)]: This creates
    a [URL] object from the provided values of [protocol],
    [host], and [file] ([path] and [query]), and
    the default port number based on the provided [protocol]
    value.
-   [URL(String protocol, String host, int port, String
    path)]: This creates a [URL] object from the provided
    values of [protocol], [host], [port], and
    [file] ([path] and [query]). A [port] value
    of [-1] indicates that the default port number needs to be
    used based on the provided [protocol] value.
-   [URL(String protocol, String host, int port, String file,
    URLStreamHandler handler)]: This acts in the same way as the
    preceding constructor and additionally allows you to pass in an
    object of the particular protocol handler; all the preceding
    constructors load default handlers automatically.
-   [URL(URL context, String spec)]: This creates a [URL]
    object that extends the provided [URL] object or overrides its
    components using the provided [spec] value, which is a string
    representation of a URL or some of its components. For example, if
    the scheme is present in both parameters, the scheme value from
    [spec] overrides the scheme value in [context] and many
    others.
-   [URL(URL context, String spec, URLStreamHandler handler)]:
    This acts in the same way as the preceding constructor and
    additionally allows you to pass in an object of the particular
    protocol handler.

Once created, a [URL] object allows you to get the values
of various components of the underlying URL. The [InputStream
openStream()] method provides access to the stream of data
received from the URL. In fact, it is implemented as
[openConnection.getInputStream()]. The [URLConnection
openConnection()] method of the [URL] class returns
a [URLConnection] object with many methods that provide
details about the connection to the URL, including
the [getOutputStream()] method that allows you to send data to the
URL.

Let\'s take a look at the code example; we start with reading data from
a [hello.txt] file, which is a local file that we created in
[Chapter
5](https://subscription.packtpub.com/book/programming/9781789957051/5), *Strings,
Input/Output, and Files*. The file contains only one line: \"*Hello!*\";
here is the code that reads it:


```
try {
   URL url = new URL("file:src/main/resources/hello.txt");
   System.out.println(url.getPath());    // src/main/resources/hello.txt
   System.out.println(url.getFile());    // src/main/resources/hello.txt
   try(InputStream is = url.openStream()){
      int data = is.read();
      while(data != -1){
          System.out.print((char) data); //prints: Hello!
          data = is.read();
      }            
   }
} catch (Exception e) {
    e.printStackTrace();
}
```

In the preceding code, we used the
[file:src/main/resources/hello.txt] URL. It is based on the path
to the file that is relative to the program\'s executing location. The
program is executed in the root directory of our project. To begin, we
demonstrate the [getPath()] and [getFile()] methods. The
returned values are not different because the URL does not have a
[query] component value. Otherwise, the [getFile()] method
would include it too. We will see this in the following code example.

The rest of the preceding code is opening an input stream of data from a
file and prints the incoming bytes as characters. The result is shown in
the inline comment.

Now, let\'s demonstrate how Java code can read data from the URL that
points to a source on the internet. Let\'s call the Google search engine
with a [Java] keyword:


```
try {
   URL url = new URL("https://www.google.com/search?q=Java&num=10");
   System.out.println(url.getPath()); //prints: /search
   System.out.println(url.getFile()); //prints: /search?q=Java&num=10
   URLConnection conn = url.openConnection();
   conn.setRequestProperty("Accept", "text/html");
   conn.setRequestProperty("Connection", "close");
   conn.setRequestProperty("Accept-Language", "en-US");
   conn.setRequestProperty("User-Agent", "Mozilla/5.0");
   try(InputStream is = conn.getInputStream();
    BufferedReader br = new BufferedReader(new InputStreamReader(is))){
      String line;
      while ((line = br.readLine()) != null){
         System.out.println(line);
      }
   }
} catch (Exception e) {
  e.printStackTrace();
}
```

Here, we came up with the
[https://www.google.com/search?q=Java&num=10] URL and requested
the properties after some research and experimentation. There is no
guarantee that it will always work, so do not be surprised if it does
not return the same data we describe. Besides, it is a live search, so
the result may change at any time. 

The preceding code also demonstrates the difference in the values
returned by the [getPath()] and [getFile()] methods. You can
view the inline comments in the preceding code example.

In comparison to the example of using a file URL, the Google search
example used the [URLConnection] object because we need to set the
request header fields:

-   [Accept] tells the server what type of content the caller
    requests ([understands]).
-   [Connection] tells the server that the connection will be
    closed after the response is received.
-   [Accept-Language] tells the server which language the caller
    requests ([understands]).
-   [User-Agent] tells the server information about the caller;
    otherwise, the Google search engine
    ([www.google.com](https://www.google.com/)) responds with a 403
    (forbidden) HTTP code.

The remaining code in the preceding example just reads from the input
stream of data (HTML code) coming from the URL, and prints it, line by
line. We captured the result (copied it from the screen), pasted it into
the online HTML
Formatter (<https://jsonformatter.org/html-pretty-print>), and ran it.
The result is presented in the following screenshot:

![]./images_10/33a2fd66-c044-4389-aac4-9ee29e5042b3.png)

As you can see, it looks like a typical page with the search results,
except there is no *Google* image in the upper-left corner with the
returned HTML.

Similarly, it is possible to send (post) data to a URL; here is an
example code:


```
try {
    URL url = new URL("http://localhost:3333/something");
    URLConnection conn = url.openConnection();
    //conn.setRequestProperty("Method", "POST");
    //conn.setRequestProperty("User-Agent", "Java client");
    conn.setDoOutput(true);
    OutputStreamWriter osw =
            new OutputStreamWriter(conn.getOutputStream());
    osw.write("parameter1=value1&parameter2=value2");
    osw.flush();
    osw.close();

    BufferedReader br =
       new BufferedReader(new InputStreamReader(conn.getInputStream()));
    String line;
    while ((line = br.readLine()) != null) {
        System.out.println(line);
    }
    br.close();
} catch (Exception e) {
    e.printStackTrace();
}
```

The preceding code expects a server running on
the [localhost] server on port [3333] that can process the
[POST] request with the [\"/something\"] path. If the server
does not check the method (is it [POST] or any other HTTP method)
and it does not check [User-Agent] value, there is no need to
specify any of it. So, we comment the settings out and keep them there
just to demonstrate how these, and similar, values can be set if
required.

Notice we used the [setDoOutput()] method to indicate that output
has to be sent; by default, it is set to [false]. Then, we let the
output stream send the query parameters to the server. 

Another important aspect of the preceding code is that the output stream
has to be closed before the input stream is opened. Otherwise, the
content of the output stream will not be sent to the server. While we
did it explicitly, a better way to do it is by using
the try-with-resources block that guarantees the [close()] method
is called, even if an exception was raised anywhere in the block.

Here is a better version of the preceding example:


```
try {
    URL url = new URL("http://localhost:3333/something");
    URLConnection conn = url.openConnection();
    //conn.setRequestProperty("Method", "POST");
    //conn.setRequestProperty("User-Agent", "Java client");
    conn.setDoOutput(true);
    try(OutputStreamWriter osw =
                new OutputStreamWriter(conn.getOutputStream())){
        osw.write("parameter1=value1&parameter2=value2");
        osw.flush();
    }
    try(BufferedReader br =
      new BufferedReader(new InputStreamReader(conn.getInputStream()))){
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
    }
} catch (Exception ex) {
    ex.printStackTrace();
}
```

To demonstrate how this example works, we also created a simple server
that listens on port [3333] of [localhost] and has a handler
assigned to process all the requests that come with the
[\"/something\"] path:


```
public static void main(String[] args) throws Exception {
    HttpServer server = HttpServer.create(new InetSocketAddress(3333),0);
    server.createContext("/something", new PostHandler());
    server.setExecutor(null);
    server.start();
}
static class PostHandler implements HttpHandler {
    public void handle(HttpExchange exch) {
       System.out.println(exch.getRequestURI());   //prints: /something
       System.out.println(exch.getHttpContext().getPath());///something
       try(BufferedReader in = new BufferedReader(
                new InputStreamReader(exch.getRequestBody()));
           OutputStream os = exch.getResponseBody()){
           System.out.println("Received as body:");
           in.lines().forEach(l -> System.out.println("  " + l));

           String confirm = "Got it! Thanks.";
           exch.sendResponseHeaders(200, confirm.length());
           os.write(confirm.getBytes());
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
```

To implement the server, we used the classes of
the [com.sun.net.httpserver] package that comes with the JCL. To
demonstrate that the URL comes without parameters, we print the URI and
the path. They both have the same [\"/something\"] value; the
parameters come from the body of the request.

After the request is processed, the server sends back the message *\"Got
it! Thanks.\"* Let\'s see how it works; we first run the server. It
starts listening on port [3333] and blocks until the request comes
with the [\"/something\"] path. Then, we execute the client and
observe the following output on the server-side screen:

![]./images_10/95016957-df28-413a-b185-1aedb3fe11f5.png)

As you can see, the server received the parameters (or any other message
for that matter) successfully. Now it can parse them and use as needed.

If we look at the client-side screen, we will see the following output:

![]./images_10/b5058307-eeaa-49d0-b573-75178e2250a1.png)

This means that the client received the message from the server and
exited as expected. Notice that the server in our example does not exit
automatically and has to be closed manually.

Other methods of the [URL] and [URLConnection] classes allow
you to set/get other properties and can be used for more dynamic
management of the client-server communication. There is also the
[HttpUrlConnection] class (and other classes) in
the [java.net] package that simplifies and enhances URL-based
communication. You can read the online documentation of
the [java.net] package to understand the available options better.

Using the HTTP 2 Client API
===================

The HTTP Client API was introduced with Java 9 as an incubating API in
the [jdk.incubator.http] package. In Java 11, it was standardized
and moved to the [java.net.http] package. It is a far richer and
easier-to-use alternative to the [URLConnection] API. In addition
to all the basic connection-related functionality, it provides
non-blocking (asynchronous) request and response using
[CompletableFuture] and supports both HTTP 1.1 and HTTP 2.

HTTP 2 added the following new capabilities to the HTTP protocol:

-   The ability to send data in a binary format rather than textual
    format; the binary format is more efficient for parsing, more
    compact, and less susceptible to various errors.
-   It is fully multiplexed, thus allowing multiple requests and
    responses to be sent concurrently using just one connection.
-   It uses header compression, thus reducing the overhead.
-   It allows a server to push a response into the client\'s cache if
    the client indicates that it supports HTTP 2.

The package contains the following classes:

-   [HttpClient]: This is used to send requests and receive
    responses both synchronously and asynchronously. An instance can be
    created using the static [newHttpClient()] method with default
    settings or using the [HttpClient.Builder] class (returned by
    the static [newBuilder()] method) that allows you to customize
    the client configuration. Once created, the instance is immutable
    and can be used multiple times.
-   [HttpRequest]: This creates and represents an HTTP request
    with the destination URI, headers, and other related information. An
    instance can be created using the [HttpRequest.Builder] class
    (returned by the static [newBuilder()] method). Once created,
    the instance is immutable and can be sent multiple times.
-   [HttpRequest.BodyPublisher]: This publishes a body (for
    the [POST], [PUT], and [DELETE] methods) from a
    certain source, such as a string, a file, input stream, or byte
    array.
-   [HttpResponse]: This represents an HTTP response received by
    the client after an HTTP request has been sent. It contains the
    origin URI, headers, message body, and other related information.
    Once created, the instance can be queried multiple times.
-   [HttpResponse.BodyHandler]: This is a functional interface
    that accepts the response and returns an instance
    of [HttpResponse.BodySubscriber] that can process the response
    body.
-   [HttpResponse.BodySubscriber]: This receives the response body
    (its bytes) and transforms it into a string, a file, or a type.

The [HttpRequest.BodyPublishers], [HttpResponse.BodyHandlers],
and [HttpResponse.BodySubscribers] classes are factory
classes that create instances of the corresponding classes. For example,
the [BodyHandlers.ofString()] method creates
a [BodyHandler] instance that processes the response body bytes as
a string, while the [BodyHandlers.ofFile()] method creates a
[BodyHandler] instance that saves the response body in a file.

You can read the online documentation of
the [java.net.http] package to learn more about these and other
related classes and interfaces. Next, we will take a look at and discuss
some examples of HTTP API usage.

Blocking HTTP requests {#blocking-http-requests .header-title}
----------------------

The following code is an example of a simple HTTP client that sends a
[GET] request to an HTTP server:


```
HttpClient httpClient = HttpClient.newBuilder()
     .version(HttpClient.Version.HTTP_2) // default
     .build();
HttpRequest req = HttpRequest.newBuilder()
     .uri(URI.create("http://localhost:3333/something"))
     .GET()                            // default
     .build();
try {
 HttpResponse<String> resp = 
          httpClient.send(req, BodyHandlers.ofString());
 System.out.println("Response: " + 
               resp.statusCode() + " : " + resp.body());
} catch (Exception ex) {
   ex.printStackTrace();
}
```

We created a builder to configure an [HttpClient] instance.
However, since we used default settings only, we can do it with the same
result as follows:


```
HttpClient httpClient = HttpClient.newHttpClient();
```

To demonstrate the client\'s functionality, we will use the
same [UrlServer] class that we used already. As a reminder, this
is how it processes the client\'s request and responds with [\"Got it!
Thanks.\"]:


```
try(BufferedReader in = new BufferedReader(
            new InputStreamReader(exch.getRequestBody()));
    OutputStream os = exch.getResponseBody()){
    System.out.println("Received as body:");
    in.lines().forEach(l -> System.out.println("  " + l));

    String confirm = "Got it! Thanks.";
    exch.sendResponseHeaders(200, confirm.length());
    os.write(confirm.getBytes());
    System.out.println();
} catch (Exception ex){
    ex.printStackTrace();
}
```

If we launch this server and run the preceding client\'s code, the
server prints the following message on its screen:

![]./images_10/4bba9491-4c29-4164-aca8-7c98e54d9de9.png)

The client did not send a message because it used the HTTP [GET]
method. Nevertheless, the server responds, and the client\'s screen
shows the following message:

![]./images_10/12c75012-a1b1-4c84-b4a4-d5342e9a71c9.png)

The [send()] method of the [HttpClient] class is blocked
until the response has come back from the server. 

Using the HTTP [POST], [PUT], or [DELETE] methods
produces similar results; let\'s run the following code now:


```
HttpClient httpClient = HttpClient.newBuilder()
        .version(Version.HTTP_2)  // default
        .build();
HttpRequest req = HttpRequest.newBuilder()
        .uri(URI.create("http://localhost:3333/something"))
        .POST(BodyPublishers.ofString("Hi there!"))
        .build();
try {
    HttpResponse<String> resp = 
                   httpClient.send(req, BodyHandlers.ofString());
    System.out.println("Response: " + 
                        resp.statusCode() + " : " + resp.body());
} catch (Exception ex) {
    ex.printStackTrace();
}
```

As you can see, this time the client posts the message [Hi
there!], and the server\'s screen shows the following:

![]./images_10/d01764c8-17cd-4864-a9a3-83d83f398360.png)

The [send()] method of the [HttpClient] class is blocked
until the same response has come back from the server:

![]./images_10/1e54d212-a93e-44d6-b445-11661de6bf1e.png) 

So far, the demonstrated functionality was not much different from the
URL-based communication that we saw in the previous section. Now we are
going to use the [HttpClient] methods that are not available in
the URL streams.

Non-blocking (asynchronous) HTTP requests {#non-blocking-asynchronous-http-requests .header-title}
-----------------------------------------

The [sendAsync()] method of the [HttpClient] class allows
you to send a message to a server without blocking. To demonstrate how
it works, we will execute the following code:


```
HttpClient httpClient = HttpClient.newHttpClient();
HttpRequest req = HttpRequest.newBuilder()
        .uri(URI.create("http://localhost:3333/something"))
        .GET()   // default
        .build();
CompletableFuture<Void> cf = httpClient
        .sendAsync(req, BodyHandlers.ofString())
        .thenAccept(resp -> System.out.println("Response: " +
                             resp.statusCode() + " : " + resp.body()));
System.out.println("The request was sent asynchronously...");
try {
    System.out.println("CompletableFuture get: " +
                                cf.get(5, TimeUnit.SECONDS));
} catch (Exception ex) {
    ex.printStackTrace();
}
System.out.println("Exit the client...");
```

In comparison to the example with the [send()] method (which
returns the [HttpResponse] object), the
[sendAsync()] method returns an instance of
the [CompletableFuture\<HttpResponse\>] class. If you read the
documentation of the [CompletableFuture\<T\>] class, you will see
that it implements
the [java.util.concurrent.CompletionStage] interface that provides
many methods that can be chained and allow you to set various functions
to process the response.

To give you an idea, here is the list of the methods declared in
the [CompletionStage] interface: [acceptEither],
[acceptEitherAsync], [acceptEitherAsync],
[applyToEither], [applyToEitherAsync],
[applyToEitherAsync],
[handle], [handleAsync], [handleAsync],
[runAfterBoth], [runAfterBothAsync],
[runAfterBothAsync], [runAfterEither],
[runAfterEitherAsync],
[runAfterEitherAsync], [thenAccept],
[thenAcceptAsync],
[thenAcceptAsync], [thenAcceptBoth],
[thenAcceptBothAsync],
[thenAcceptBothAsync], [thenApply], [thenApplyAsync],
[thenApplyAsync], [thenCombine], [thenCombineAsync],
[thenCombineAsync], [thenCompose], [thenComposeAsync],
[thenComposeAsync], [thenRun], [thenRunAsync],
[thenRunAsync], [whenComplete], [whenCompleteAsync],
and [whenCompleteAsync].

We will talk about functions and how they can be passed as parameters in *Functional
Programming*. For now, we will just mention that the [resp -\>
System.out.println(\"Response: \" + resp.statusCode() + \" : \" +
resp.body())] construction represents the same functionality as
the following method:


```
void method(HttpResponse resp){
    System.out.println("Response: " + 
                             resp.statusCode() + " : " + resp.body());
}
```

The [thenAccept()] method applies the passed-in functionality to
the result returned by the previous method of the chain.

After the [CompletableFuture\<Void\>] instance is returned, the
preceding code prints [The request was sent
asynchronously\...] message and blocks it on the
[get()] method of the [CompletableFuture\<Void\>] object.
This method has an overloaded version [get(long timeout, TimeUnit
unit)], with two parameters -- the [TimeUnit unit] and the
[long] [timeout] that specifies the number of the units,
indicating how long the method should wait for the task that is
represented by the [CompletableFuture\<Void\>] object to complete.
In our case, the task is to send a message to the server and to get back
the response (and process it using the provided function). If the task
is not completed in the allotted time, the [get()] method is
interrupted (and the stack trace is printed in the catch block).

The [Exit the client\...] message should appear on the screen
either in five seconds (in our case) or after the [get()] method
returns.

If we run the client, the server\'s screen shows the following message
again with the blocking HTTP [GET] request:

![]./images_10/27141e5f-a4cc-45ab-83a7-a428697ca974.png)

The client\'s screen displays the following message:

![]./images_10/88b6701c-2806-4396-bb81-e6addbbdec1e.png)

As you can see, [The request was sent
asynchronously\...] message appears before the response
came back from the server. This is the point of an asynchronous call;
the request to the server was sent and the client is free to continue to
do anything else. The passed-in function will be applied to the server
response. At the same time, you can pass
the [CompletableFuture\<Void\>] object around and call it at any
time to get the result. In our case, the result is [void], so
the [get()] method simply indicates that the task was completed. 

We know that the server returns the message, and so we can take
advantage of it by using another method of
the [CompletionStage] interface. We have chosen
the [thenApply()] method, which accepts a function that returns a
value:


```
CompletableFuture<String> cf = httpClient
                .sendAsync(req, BodyHandlers.ofString())
                .thenApply(resp -> "Server responded: " + resp.body());
```

Now the [get()] method returns the value produced by the [resp -\>
\"Server responded: \" + resp.body()] function, so it should
return the server message body; let\'s run this code and see the
result: 

![]./images_10/ab28350c-290a-481a-b89e-d13257fa5177.png)

Now the [get()] method returns the server\'s message as expected,
and it is presented by the function and passed as a parameter into
the [thenApply()] method.

Similarly, we can use the HTTP [POST], [PUT], or
[DELETE] methods for sending a message:


```
HttpClient httpClient = HttpClient.newHttpClient();
HttpRequest req = HttpRequest.newBuilder()
        .uri(URI.create("http://localhost:3333/something"))
        .POST(BodyPublishers.ofString("Hi there!"))
        .build();
CompletableFuture<String> cf = httpClient
        .sendAsync(req, BodyHandlers.ofString())
        .thenApply(resp -> "Server responded: " + resp.body());
System.out.println("The request was sent asynchronously...");
try {
    System.out.println("CompletableFuture get: " +
                                cf.get(5, TimeUnit.SECONDS));
} catch (Exception ex) {
    ex.printStackTrace();
}
System.out.println("Exit the client...");
```

The only difference from the previous example is that the server now
displays the received client\'s message:

![]./images_10/19fb5128-e536-423e-a23a-73661089c990.png)

The client\'s screen displays the same message as in the case of the
[GET] method:

![]./images_10/58f4e3cb-6ea1-4862-8f4c-ae75a3b037d5.png)

The advantage of asynchronous requests is that they can be sent quickly
and without needing to wait for each of them to complete. The HTTP 2
protocol supports it by multiplexing; for example, let\'s send three
requests as follows:


```
HttpClient httpClient = HttpClient.newHttpClient();
List<CompletableFuture<String>> cfs = new ArrayList<>();
List<String> nums = List.of("1", "2", "3");
for(String num: nums){
    HttpRequest req = HttpRequest.newBuilder()
           .uri(URI.create("http://localhost:3333/something"))
           .POST(BodyPublishers.ofString("Hi! My name is " + num + "."))
           .build();
    CompletableFuture<String> cf = httpClient
           .sendAsync(req, BodyHandlers.ofString())
           .thenApply(rsp -> "Server responded to msg " + num + ": "
                              + rsp.statusCode() + " : " + rsp.body());
    cfs.add(cf);
}
System.out.println("The requests were sent asynchronously...");
try {
    for(CompletableFuture<String> cf: cfs){
        System.out.println("CompletableFuture get: " + 
                                          cf.get(5, TimeUnit.SECONDS));
    }
} catch (Exception ex) {
    ex.printStackTrace();
}
System.out.println("Exit the client...");
```

The server\'s screen shows the following messages:

![]./images_10/6cb821a0-24db-40e6-814b-4e9ea91c2d08.png)

Notice the arbitrary sequence of the incoming requests; this is because
the client uses a pool of
[Executors.newCachedThreadPool()] threads to send the messages.
Each message is sent by a different thread, and the pool has its own
logic of using the pool members (threads). If the number of messages is
large, or if each of them consumes a significant amount of memory, it
may be beneficial to limit the number of threads run concurrently. 

The [HttpClient.Builder] class allows you to specify the pool that
is used for acquiring the threads that send the messages:


```
ExecutorService pool = Executors.newFixedThreadPool(2);
HttpClient httpClient = HttpClient.newBuilder().executor(pool).build();
List<CompletableFuture<String>> cfs = new ArrayList<>();
List<String> nums = List.of("1", "2", "3");
for(String num: nums){
    HttpRequest req = HttpRequest.newBuilder()
          .uri(URI.create("http://localhost:3333/something"))
          .POST(BodyPublishers.ofString("Hi! My name is " + num + "."))
          .build();
    CompletableFuture<String> cf = httpClient
          .sendAsync(req, BodyHandlers.ofString())
          .thenApply(rsp -> "Server responded to msg " + num + ": "
                              + rsp.statusCode() + " : " + rsp.body());
    cfs.add(cf);
}
System.out.println("The requests were sent asynchronously...");
try {
    for(CompletableFuture<String> cf: cfs){
        System.out.println("CompletableFuture get: " + 
                                           cf.get(5, TimeUnit.SECONDS));
    }
} catch (Exception ex) {
    ex.printStackTrace();
}
System.out.println("Exit the client...");
```

If we run the preceding code, the results will be the same, but the
client will use only two threads to send messages. The performance may
be a bit slower (in comparison to the previous example) as the number of
messages grows. So, as is often the case in a software system design,
you need to balance between the amount of memory used and the
performance.

Similarly to the executor, several other objects can be set on the
[HttpClient] object to configure the connection to handle
authentication, request redirection, cookies management, and more.

Server push functionality {#server-push-functionality .header-title}
-------------------------

The second (after multiplexing) significant advantage of the HTTP 2
protocol over HTTP 1.1 is allowing the server to push the response into
the client\'s cache if the client indicates that it supports HTTP 2.
Here is the client code that takes advantage of this feature:


```
HttpClient httpClient = HttpClient.newHttpClient();
HttpRequest req = HttpRequest.newBuilder()
        .uri(URI.create("http://localhost:3333/something"))
        .GET()
        .build();
CompletableFuture cf = httpClient
        .sendAsync(req, BodyHandlers.ofString(), 
                (PushPromiseHandler) HttpClientDemo::applyPushPromise);

System.out.println("The request was sent asynchronously...");
try {
    System.out.println("CompletableFuture get: " + 
                                          cf.get(5, TimeUnit.SECONDS));
} catch (Exception ex) {
    ex.printStackTrace();
}
System.out.println("Exit the client...");
```

Notice the third parameter of the [sendAsync()] method. It is a
function that handles the push response if one comes from the server. It
is up to the client developer to decide how to implement this function;
here is one possible example:


```
void applyPushPromise(HttpRequest initReq, HttpRequest pushReq,
      Function<BodyHandler, CompletableFuture<HttpResponse>> acceptor) {
  CompletableFuture<Void> cf = acceptor.apply(BodyHandlers.ofString())
      .thenAccept(resp -> System.out.println("Got pushed response " 
                                                       + resp.uri()));
  try {
        System.out.println("Pushed completableFuture get: " + 
                                         cf.get(1, TimeUnit.SECONDS));
  } catch (Exception ex) {
        ex.printStackTrace();
  }
  System.out.println("Exit the applyPushPromise function...");
}
```

This implementation of the function does not do much. It just prints out
the URI of the push origin. But, if necessary, it can be used for
receiving the resources from the server (for example, images that
support the provided HTML) without requesting them. This solution saves
the round-trip request-response model and shortens the time of the page
loading. It also can be used for updating the information on the page.

You can find many code examples of a server that sends push requests;
all major browsers support this feature too.

WebSocket support {#websocket-support .header-title}
-----------------

HTTP is based on the request-response model. A client requests
a resource, and the server provides a response to this request. As we
demonstrated several times, the client initiates the communication.
Without it, the server cannot send anything to the client. To get over
this limitation, the idea was first introduced as TCP connection in the
HTML5 specification and, in 2008, the first version of the WebSocket
protocol was designed.

It provides a full-duplex communication channel between the client and
the server. After the connection is established, the server can send
a message to the client at any time. Together with JavaScript and HTML5,
the WebSocket protocol support allows web applications to present a far
more dynamic user interface.

The WebSocket protocol specification defines WebSocket ([ws])
and WebSocket Secure ([wss]) as two schemes, which are used for
unencrypted and encrypted connections, respectively. The protocol does
not support fragmentation but allows all the other URI components
described in the *URL syntax* section.

All the classes that support the WebSocket protocol for a client are
located in the [java.net] package. To create a client, we need to
implement the [WebSocket.Listener] interface, which has the
following methods: 

-   [onText()]: Invoked when textual data has been received
-   [onBinary()]: Invoked when binary data has been received
-   [onPing()]: Invoked when a ping message has been received
-   [onPong()]: Invoked when a pong message has been received
-   [onError()]: Invoked when an error has happened
-   [onClose()]: Invoked when a close message has been received

All the methods of this interface are [default]. This means that
you do not need to implement all of them, but only those that the client
requires for a particular task:


```
class WsClient implements WebSocket.Listener {
    @Override
    public void onOpen(WebSocket webSocket) {
        System.out.println("Connection established.");
        webSocket.sendText("Some message", true);
        Listener.super.onOpen(webSocket);
    }
    @Override
    public CompletionStage onText(WebSocket webSocket, 
                                     CharSequence data, boolean last) {
        System.out.println("Method onText() got data: " + data);
        if(!webSocket.isOutputClosed()) {
            webSocket.sendText("Another message", true);
        }
        return Listener.super.onText(webSocket, data, last);
    }
    @Override
    public CompletionStage onClose(WebSocket webSocket, 
                                       int statusCode, String reason) {
        System.out.println("Closed with status " + 
                                 statusCode + ", reason: " + reason);
        return Listener.super.onClose(webSocket, statusCode, reason);
    }
}
```

A server can be implemented in a similar way, but server implementation
is beyond the scope of this book. To demonstrate the preceding client
code, we are going to use a WebSocket server provided by
the [echo.websocket.org] website. It allows a WebSocket connection
and sends the received message back; such a server is typically called
an **echo server**.

We expect that our client will send the message after the connection is
established. Then, it will receive (the same) message from the server,
display it, and send back another message, and so on, until it is
closed. The following code invokes the client that we created:


```
HttpClient httpClient = HttpClient.newHttpClient();
WebSocket webSocket = httpClient.newWebSocketBuilder()
    .buildAsync(URI.create("ws://echo.websocket.org"), new WsClient())
    .join();
System.out.println("The WebSocket was created and ran asynchronously.");
try {
    TimeUnit.MILLISECONDS.sleep(200);
} catch (InterruptedException ex) {
    ex.printStackTrace();
}
webSocket.sendClose(WebSocket.NORMAL_CLOSURE, "Normal closure")
         .thenRun(() -> System.out.println("Close is sent."));
```

The preceding code creates a [WebSocket] object using
the [WebSocket.Builder] class. The [buildAsync()]
method returns the [CompletableFuture] object. The [join()]
method of the [CompletableFuture] class returns the result value
when complete, or throws an exception. If an exception is not generated,
then, as we mentioned already, the [WebSocket] communication
continues until either side sends a **Close** message. That is why our
client waits for 200 milliseconds, and then sends the **Close** message
and exits. If we run this code, we will see the following messages:

![]./images_10/a10745d2-c8c9-4bb5-bb1c-db505f9cd953.png) 

As you can see, the client behaves as expected. To finish our
discussion, we would like to mention that all modern web browsers
support the WebSocket protocol.

Summary
===================

In this chapter, the reader was presented with a description of the most
popular network protocols: UDP, TCP/IP, and WebSocket. The discussion
was illustrated with code examples using JCL. We also reviewed URL-based
communication and the latest Java HTTP 2 Client API.

The next chapter provides an overview of Java GUI technologies and
demonstrates a GUI application using JavaFX, including code examples
with control elements, charts, CSS, FXML, HTML, media, and various other
effects. The reader will learn how to use JavaFX to create a GUI
application.

