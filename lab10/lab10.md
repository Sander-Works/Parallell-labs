<h1 align="center"> ITF23019 - Parallel and Distributed Programming </h1>
<h3 align="center"> Spring 2021 </h2>
<h3 align="center"> Lab 10: Java Socket and RESTful service </h2>




In this lab, we will learn how to develop simple socket server and RESTful server.

## Submission Deadline:

You need to commit your codes and lab report to your GitHub repository **before 10:00 AM Wednesday 7th April**.

## GitHub

If you have not config your `labs-yourusername` repository for upstream remote repository, please go back to the instruction from `lab2`.

Remember to commit and push your changes before starting this lab. Then, start the **Command Prompt** of GitHub Desktop and run the command:

```bash
> git pull upstream main
```

Then, **push** everything to your `labs-yourusername`.

## Java Socket

> A *socket* is one endpoint of a two-way communication link between two programs running on the network. A socket is bound to a port number so that the TCP layer can identify the application that data is destined to be sent to.

An endpoint is a combination of an IP address and a port number. Every TCP connection can be uniquely identified by its two endpoints. That way you can have multiple connections between your host and the server.

The `java.net` package in the Java platform provides a class, `Socket`, that implements one side of a two-way connection between your Java program and another program on the network. The `Socket` class sits on top of a platform-dependent implementation, hiding the details of any particular system from your Java program. By using the `java.net.Socket` class instead of relying on native code, your Java programs can communicate over the network in a platform-independent fashion.

Additionally, `java.net` includes the `ServerSocket` class, which implements a socket that servers can use to listen for and accept connections to clients. 

Let's look at a simple example that illustrates how a program can establish a connection to a server program using the `Socket` class and then, how the client can send data to and receive data from the server through the socket.

The example program implements a client, `EchoClient`, that connects to an echo server. The echo server receives data from its client and echoes it back. The example `EchoServer`implements an echo server. 

The `EchoClient` example creates a socket, thereby getting a connection to the echo server. It reads input from the user on the standard input stream, and then forwards that text to the echo server by writing the text to the socket. The server echoes the input back through the socket to the client. The client program reads and displays the data passed back to it from the server.

Note that the `EchoClient` example both writes to and reads from its socket, thereby sending data to and receiving data from the echo server.

Let's walk through the program and investigate the interesting parts. The following statements in the `try`statement in the `EchoClient` example are critical. These lines establish the socket connection between the client and the server and open a `PrintWriter` and a `BufferedReader` on the socket:

```java
String hostName = "localhost"; //127.0.0.1
int portNumber = 6565;

try (
    Socket echoSocket = new Socket(hostName, portNumber);
    PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
    BufferedReader in =
        new BufferedReader(
            new InputStreamReader(echoSocket.getInputStream()));
    BufferedReader stdIn =
        new BufferedReader(
            new InputStreamReader(System.in))
)
```

The first statement in the `try`-with resources statement creates a new `Socket` object and names it `echoSocket`. The `Socket` constructor used here requires the name of the computer and the port number to which you want to connect. The second statement in the `try` statement gets the socket's output stream and opens a `PrintWriter` on it. Similarly, the third statement gets the socket's input stream and opens a `BufferedReader` on it. The example uses readers and writers so that it can write Unicode characters over the socket.

To send data through the socket to the server, the `EchoClient` example needs to write to the `PrintWriter`. To get the server's response, `EchoClient` reads from the `BufferedReader` object `stdIn`, which is created in the fourth statement in the `try` statement. 

The next interesting part of the program is the `while` loop. The loop reads a line at a time from the standard input stream and immediately sends it to the server by writing it to the `PrintWriter` connected to the socket:

```java
String userInput;
while ((userInput = stdIn.readLine()) != null) {
    out.println(userInput);
    System.out.println("echo: " + in.readLine());
}
```

The last statement in the `while` loop reads a line of information from the `BufferedReader` connected to the socket. The `readLine` method waits until the server echoes the information back to `EchoClient`. When `readline` returns, `EchoClient` prints the information to the standard output.

The `while` loop continues until the user types an end-of-input character. That is, the `EchoClient` example reads input from the user, sends it to the Echo server, gets a response from the server, and displays it, until it reaches the end-of-input. (You can type an end-of-input character by pressing **Ctrl-C**.) The `while` loop then terminates, and the Java runtime automatically closes the readers and writers connected to the socket and to the standard input stream, and it closes the socket connection to the server. The Java runtime closes these resources automatically because they were created in the `try`-with-resources statement. The Java runtime closes these resources in reverse order that they were created. (This is good because streams connected to a socket should be closed before the socket itself is closed.)

This client program is straightforward and simple because the echo server implements a simple protocol. The client sends text to the server, and the server echoes it back. When your client programs are talking to a more complicated server such as an HTTP server, your client program will also be more complicated. However, the basics are much the same as they are in this program:

1. Open a socket.
2. Open an input stream and output stream to the socket.
3. Read from and write to the stream according to the server's protocol.
4. Close the streams.
5. Close the socket.

Only step 3 differs from client to client, depending on the server. The other steps remain largely the same.

For the server, `ServerSocket` is used to open the port and start listening to the client with the `accept()` method.

```java
int portNumber = 6565;

System.out.printf("Starting Server at port %d\n", portNumber);

try (ServerSocket serverSocket = new ServerSocket(portNumber);
     Socket clientSocket = serverSocket.accept();
     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));) {
    String inputLine;
    while ((inputLine = in.readLine()) != null) {
        out.println(inputLine);
    }
```

## Exercise 1 (50 points):

In this exercise, we will implement the client/server pair that serves up Knock Knock jokes. Knock Knock jokes are favored by children and are usually vehicles for bad puns. They go like this:

**Server**: "Knock knock!"

**Client**: "Who's there?"

**Server**: "Dexter."

**Client**: "Dexter who?"

**Server**: "Dexter halls with boughs of holly."

**Client**: "Groan."

The client program is implemented by a single class, `KnockKnockClient`, and is very similar to the `EchoClient` example from the previous section. The server program is implemented by two classes: `KKSingleServer` and `KnockKnockProtocol`. `KKSingleServer`, which is similar to `EchoServer`, contains the `main` method for the server program and performs the work of listening to the port, establishing connections, and reading from and writing to the socket. The class `KnockKnockProtocol`serves up the jokes. It keeps track of the current joke, the current state (sent knock knock, sent clue, and so on), and returns the various text pieces of the joke depending on the current state. This object implements the protocol—the language that the client and server have agreed to use to communicate.

To keep the server example simple, we designed it to listen for and handle a single connection request. However, multiple client requests can come into the same port and, consequently, into the same `ServerSocket`. Client connection requests are queued at the port, so the server must accept the connections sequentially. However, the server can service them simultaneously through the use of threads—one thread per each client connection.

The basic flow of logic in such a server is this:

```java
while (true) {
    accept a connection;
    create a thread to deal with the client;
}
```

The thread reads from and writes to the client connection as necessary.

The two classes `KKMultiServer` and `KKMultiThreadPoolServer` implements such multithreading server with `Runnable` and `ThreadPool`.

Your tasks in this exercise are:

1. Complete the implementations of the server. Follow the instruction in the corresponding classes.
2. Discuss the advantages and disadvantages  (if any) of the two multithreading implementations: `KKMultiServer` and `KKMultiThreadPoolServer`.

## RESTful Web service

“REST” was coined by Roy Fielding in his Ph.D. dissertation to describe a design pattern for implementing networked systems. REST is Representational State Transfer, an architectural style for designing distributed systems. 

In a REST based architecture everything is a resource. A resource is accessed via a common interface based on the HTTP standard methods. In a REST based architecture you have a REST server which provides access to the resources. A REST client can access and modify the REST resources.

Every resource should support the HTTP common operations. Resources are identified by global IDs (which are typically URIs).

REST allows that resources have different representations, e.g., text, XML, JSON etc. The REST client can ask for a specific representation via the HTTP protocol.

### HTTP Methods

The *PUT*, *GET*, *POST* and *DELETE* methods are typical used in REST based architectures. The following table gives an explanation of these operations.

- GET defines a reading access of the resource without side-effects. The resource is never changed via a GET request, e.g., the request has no side effects.
- PUT creates a new resource. 
- DELETE removes the resources. They can get repeated without leading to different results.
- POST updates an existing resource or creates a new resource.

### XML vs JSON

eXtensible Markup Language - XML is a text-based markup language which is standard for data interchange on the Web. As with HTML, you identify data using tags (identifiers enclosed in angle brackets, like this: <...>). Collectively, the tags are known as “markup.” It puts a label on a piece of data that identifies it (for example: <message>...</message>). In the same way that you define the field names for a data structure, you are free to use any XML tags that make sense for a given application.

JSON or JavaScript Object Notation is a lightweight text-based open standard designed for human-readable data interchange. Conventions used by JSON are known to programmers, which include those with knowledge of C, C++, Java, Python, Perl, etc.

Here is an example of some XML and JSON data:

```xml
<person first-name="John" last-name="Smith"/>
```

``` xml
<object type="Person">
	<property name="first-name">John</property>
	<property name="last-name">Smith</property>
</object>
```

```xml
<person>
	<first-name>John</first-name>
	<last-name>Smith</last-name>
</person>
```

```json
{
    "first-name" : "John",
	"last-name" : "Smith" 
}
```

### JAX-RS and Jersey

JAX-RS is a specification. Further explaining it a specification for implementing REST web services in Java, currently defined by the [JSR 311](https://jcp.org/en/jsr/detail?id=311), [JSR 339](https://jcp.org/en/jsr/detail?id=339) and [JSR-370](https://jcp.org/en/jsr/detail?id=370) which standardize development and deployment of RESTful web services. JSR stands for Java Specification Requests (JSRs) which are the actual descriptions of proposed and final specifications for the Java platform

[Jersey](https://jersey.github.io/) is one of the most popular implementations of JAX-RS used for developing RESTful web services in the Java ecosystem.

### Resource

Using JAX-RS a Web resource is implemented as a resource class and requests are handled by resource methods.

A resource class is a Java class that uses JAX-RS annotations to implement a corresponding Web resource. Resource classes are POJOs ("plain old Java objects") that have at least one method annotated with `@Path` or a request method designator.

In this example, we are going to develop a methods to access and update a car database which is implemented as `ConcurrentHashMap<Integer, Car> cars`, in which the `Car` is define as:

```java
public class Car {

	private String brand;
	private String color;
}
```

```java
@GET
@Produces(MediaType.TEXT_PLAIN) // individual methods can override class level annotations
public String simpleGetRequest() {
    return "This is an example REST resource!";
}

/*
	 * GET requests are usually used to get a resource from the database, often
	 * identified by a unique ID. Example URL:
	 * http://<server_address>:<server_port>/example/cars/5
	 */
@GET
@Path("cars/{id}")
public Response getCarById(@PathParam("id") Integer id) {
    Car retrievedCar = cars.get(id);
    if (retrievedCar != null) {
        return Response.status(Status.OK).entity(retrievedCar).build();
    } else {
        return Response.status(Status.OK).build();
    }
}

/*
	 * Get all cars, optional filter parameters are the brand and color of the cars.
	 * QueryParameters are optional in Jersey, for example brand will be null if not
	 * specified. Example URL:
	 * http://<server_address>:<server_port>/example/cars?brand=Volvo&color=red
	 */
@GET
@Path("cars")
public Response getCars(@QueryParam("brand") String brand, @QueryParam("color") String color) {
    // Get all the cars in a list
    List<Car> returnedCars = new ArrayList<>();
    for (Entry<Integer, Car> mapEntry : cars.entrySet()) {
        returnedCars.add(mapEntry.getValue());
    }

    // Filter the list based on the specified brand and color
    if (brand != null) {
        returnedCars.removeIf(car -> !brand.equals(car.getBrand()));
    }
    if (color != null) {
        returnedCars.removeIf(car -> !color.equals(car.getColor()));
    }

    // Response contains the status code, and the response entity
    return Response.status(Status.OK).entity(returnedCars).build();
}

// Return the complete Map with IDs included
@GET
@Path("raw")
public Response getAll() {
    return Response.status(Status.OK).entity(cars).build();
}

/*
	 * POST requests are usually for creating/saving new resources. The resource is
	 * in the payload of the request, which will be automatically be deserialized by
	 * the JSON library (if the project is configured correctly).
	 */
@POST
@Path("cars")
public Response createCar(Car car) {
    // Save the car instance to the database
    cars.put(idCounter, car);
    // Increment the ID for the next call of this method
    idCounter++;
    return Response.status(Status.CREATED).entity(car).build();
}

/*
	 * PUT requests are usually for updating existing resources. The ID is from the
	 * database, to identify the car instance. Usually PUT requests fully update a
	 * resource, meaning fields which are not specified by the client, will also be
	 * null in the database (overriding existing data). PATCH requests are used for
	 * partial updates.
	 */
@PUT
@Path("cars/{id}")
public Response updateCar(@PathParam("id") Integer id, Car updatedCar) {
    Car carFromTheDatabase = cars.get(id);
    // Throw an exception if the car with the specified ID does not exist
    if (carFromTheDatabase == null) {
        return Response.status(Status.BAD_REQUEST).build();
    }
    // Update the car
    cars.put(id, updatedCar);

    // Return a response with Accepted status code
    return Response.status(Status.ACCEPTED).entity(updatedCar).build();
}

/*
	 * And finally, DELETE requests are usually used to delete a resource from
	 * database.
	 */
@DELETE
@Path("cars/{id}")
public Response deleteCar(@PathParam("id") Integer id) {
    cars.remove(id);
    return Response.ok().build();
}

```

### Exercise 2 (50 points)

Develop a PUT method to update the car with `id` to new color `color`.

## What To Submit

Complete the the exercises in this lab and put your code along with **lab10_report** (Markdown, TXT or PDF file) into the **lab10** directory of your repository. Commit and push your changes and remember to check the GitHub website to make sure all files have been submitted. 

## References

1. Java Socket Tutorial. https://docs.oracle.com/javase/tutorial/networking/sockets/index.html
2. Simple Java RESTful Web Service with Jersey — JAX-RS implementation. https://madhawacperera.medium.com/simple-java-restful-web-service-with-jersey-jax-rs-implementation-615739857961
3. REST with Java (JAX-RS) using Jersey. https://www.vogella.com/tutorials/REST/article.html
