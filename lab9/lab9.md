<h1 align="center"> ITF23019 - Parallel and Distributed Programming </h1>
<h3 align="center"> Spring 2021 </h2>
<h3 align="center"> Lab 9: Java Parallel Stream </h2>





In this lab, we will investigate the parallism feature of `java.util.stream`.

## Submission Deadline:

You need to commit your codes and lab report to your GitHub repository **before 10:00 AM Wednesday 31st March**.

## GitHub

If you have not config your `labs-yourusername` repository for upstream remote repository, please go back to the instruction from `lab2`.

Remember to commit and push your changes before starting this lab. Then, start the **Command Prompt** of GitHub Desktop and run the command:

```bash
> git pull upstream main
```

Then, **push** everything to your `labs-yourusername`.

## An introduction to streams

A stream is a sequence of data (is not a data structure) that allows you to apply a sequence of operations in a sequential or concurrent way to filter, convert, sort, reduce, or organize those elements to obtain a final object. For example, if you
have a stream with the data of your employees, you can use a stream to:

* Count the total number of employees (this is an expensive terminal operation).
* Calculate the average salary of all employees who live in a particular place.
* Obtain a list of the employees who haven't met their objectives.
* Any operation that implies work with all or some of the employees.

A stream does not store its elements. A stream takes the elements from its source and sends them across all the operations that form the pipeline.

You can work with streams in parallel without any extra work. When you create a stream, you can use the `stream()` method to create a sequential stream or `parallelStream()` to create a concurrent one. Internally, parallel streams in Oracle JDK 9 and Open JDK 9 use an implementation of the fork/join framework to execute concurrent operations.

A stream has three different sections:

* A source which generates the data consumed by the stream.
* Zero or more intermediate operations, which generate another stream as an output.
* One terminal operation which generates an object, which can be a simple object or a collection as an array, a list, or a hash table. There can also be terminal operations that don't produce any explicit result.

``` java
List<Person> roster = new ArrrayList<Person>();

roster.stream()
      .filter(e -> e.getGender() == Person.Sex.MALE)
      .forEach(e -> System.out.println(e.getName()));

```

Streams make for lazy processing of data. They don't obtain the data until it's necessary. The data isn't processed until the terminal operation needs it, so stream processing doesn't begin until the terminal operation is executed.

### Sources of a stream

The source of the stream generates the data that will be processed by the `Stream` object. You can create a stream from different data sources. For example, the `Collection` interface included the `stream()` methods in Java 8 to generate a sequential stream and `parallelStream()` to generate a parallel one. This allows you to generate a stream to process all the data from almost all the data structures implemented in Java as lists (`ArrayList`, `LinkedList`, and so on), sets (`HashSet`, `EnumSet`), or concurrent data structures (`LinkedBloFmackingDeque`, `PriorityBlockingQueue`, and so on). 

```java
List<Integer> integers = new ArrayList<Integer>();
// Using List.parallelStream() to convert 
// List into Stream 
Stream<Integer> integerStream = integers.parallelStream();
```

Another data structure that can generate streams is arrays. The `Array` classes includes four versions of the `stream()` method to generate a stream from the array. If you pass an array of `int` numbers to the method, it will generate `IntStream`. This is a special kind of stream, implemented to work with integer numbers. Similarly, you can create `LongStream` or `DoubleStream` from the `long[]` or `double[]` arrays. Of course, if you pass an array of object to the `stream()` method, you will obtain a generic stream of the same type. In this case, there is no `parallelStream()` method, but once you have obtained the stream, you can call the `parallel()` method defined in the `BaseStream` interface to convert the sequential stream into a concurrent one.

```java
int arr[] = { 1, 2, 3, 4, 5 }; 
  
// Using Arrays.stream() to convert 
// array into Stream 
IntStream stream = Arrays.stream(arr).parallel(); 
```

Other interesting functionality provided by the Stream API is that you can generate a stream to process the contents of directory or a file. The `Files` class provides different methods to work with files using streams. For example, the`find()` method returns a stream with the `Path` objects of the files in a file tree that meet certain conditions. The `list()` method returns a stream of the `Path` objects with the contents of a directory. The `walk()` method returns a stream of the Path objects processing all the objects in a directory tree using a depth-first algorithm. But the most interesting method is the `lines()` method, which creates a stream of`String` objects with the lines of a file, so you can process its contents using a stream. Unfortunately, all of the methods mentioned here parallelize badly unless you have many thousands of elements (files or lines).

### Intermediate operations

The most important characteristic of intermediate operations is that they return another stream as their result. The objects of the input and output stream can be of a different type, but an intermediate operation will always generate a new stream. You can have zero or more intermediate operations in a stream. The most important intermediate operations provided by the `Stream` interface are:

* `distinct()`: This method returns a stream with unique values. All the repeated elements will be eliminated.
* `filter()`: This method returns a stream with the elements that meet certain criteria.

* `flatMap()`: This method is used to convert a stream of streams (for example, a stream of list, sets, and so on) in a single stream.

* `limit()`: This method returns a stream that contains, at the most, the specified number of the original elements in the encounter order, starting from the first element.
* `map()`: This method is used to transform the elements of a stream from one type to another.
* `peek()`: This method returns the same stream, but it executes some code; normally, it is used to write log messages.
* `skip()`: This method ignores the first elements (the concrete number is
  passed as a parameter) of the stream.
* `sorted()`: This method sorts the elements of the stream.

### Terminal operations

A terminal operation returns an object as a result. It never returns a stream. In general, all streams will end with a terminal operation that returns the final result of the sequence of operations. The most important terminal operations are:

* `collect()`: This method provides a way to reduce the number of elements of the source stream, organizing the elements of the stream in a data structure. For example, if you want to group the elements of your stream by a
  criterion.
* `count()`: This returns the number of elements of the stream.
* `max()`: This returns the maximum element of the stream.
* `min()`: This returns the minimum element of the stream.
* `reduce()`: This method transforms the elements of the stream into a unique object that represents the stream.
* `forEach()/forEachOrdered()`: This methods apply an action to every element in the stream. The second method uses the order of the elements of the stream if the stream has a defined order.
* `findFirst()/findAny()`: This returns 1 or the first element of the stream, respectively, if they exist.
* `anyMatch()/allMatch()/noneMatch()`: They receive a predicate as a parameter and return a `Boolean` value to indicate if any, all, or none of the elements of the stream match the predicate.
* `toArray()`: This method returns an array with the elements of the stream.



## MapReduce and MapCollect with Java Stream

**MapReduce** is a programming model to process very large datasets in distributed environments with a lot of machines working in a cluster. It has two steps, generally implemented by two methods:

* Map: This filters and transforms the data
* Reduce: This applies a summary operation in the data

To make this operation in a distributed environment, we have to split the data and then distribute it over the machines of the cluster. This programming model has been used for a long time in the functional programming world. Google recently developed a framework based on this principle, and in the Apache Foundation, the Hadoop project is very popular as an open source implementation of this model.

Java 9 with streams allows programmers to implement something very similar to this. The `Stream` interface defines intermediate operations (`map()`, `filter()`, `sorted()`, `skip()`, and so on) that can be considered as `map()` functions, and it provides the
`reduce()` method as a terminal operation whose main objective is to make a reduction of the elements of the stream as the reduction of the MapReduce model. The main idea of the reduce operation is to create a new intermediate result based on a previous intermediate result and a stream element. An alternative method of reduction (also called mutable reduction) is to incorporate the new resulting item into the mutable container (for example, adding it into `ArrayList`). Such reduction is performed by the `collect()` operation, and we will call it as a **MapCollect** model.

### The collect() method

The `collect()` method allows you to transform and group the elements of the stream generating a new data structure with the final results of the stream.

There are two different versions of the `collect()` method. The first version accepts the following three functional parameters:

* Supplier: This is a function that creates an object of the intermediate data type. If you use a sequential stream, this method will be called once. If you use a parallel stream, this method may be called many times and must produce a fresh object every time.
* Accumulator: This function is called to process an input element and store it in the intermediate data structure.
* Combiner: This function is called to merge two intermediate data structures into one. This function will be only called with parallel streams.

The second version of the collect() method accepts an object that implements the `Collector` interface. Java provides you with some predefined collectors in the `Collectors` factory class. Some of those methods are:

* `averagingDouble()`, `averagingInt()`, and `averagingLong()`: This returns a collector that allows you to calculate the arithmetic mean of a `double`, `int`, or `long` function.
* `groupingBy()`: This returns a collector that allows you to group the elements of a stream by an attribute of its objects, generating a map where the keys are the values of the selected attribute and the values are a list of the objects
  that have a determined value.
* `groupingByConcurrent()`: This is similar to the previous one except there are two important differences. The first one is that it may work faster in the parallel but slower in the sequential mode than the `groupingBy()` method. The second
  and most important difference is that the `groupingByConcurrent()` function is an unordered collector. The items in the lists are not guaranteed to be in the same order as in the stream. The `groupingBy()` collector, on the other hand, guarantees the ordering.
* `joining()`: This returns a `Collector` factory class that concatenates the input elements into a string.
* `partitioningBy()`: This returns a `Collector` factory class that makes a partition of the input elements based on the results of a predicate.
* `summarizingDouble()`, `summarizingInt()`, and `summarizingLong()`: These return a `Collector` factory class that calculates summary statistics of the input elements.
* `toMap()`: This returns a `Collector` factory class that allows you to transform input elements into a map based on two mapping functions.
* `toConcurrentMap()`: This is similar to the previous one, but in a concurrent way. Without a custom merger, `toConcurrentMap()` is just faster for parallel streams. As occurs with `groupingByConcurrent()`, this is an unordered collector too, whereas `toMap()` uses the encounter order to make the conversion.
* `toList()`:This returns a Collector factory class that stores the input elements
  into a list.
* `toCollection()`: This method allows you to accumulate the input elements into a new `Collection` factory class (`TreeSet`, `LinkedHashSet`, and so on) in the encounter order. The method receives an implementation of the `Supplier`
  interface that creates the collection as a parameter.
* `maxBy()` and `minBy()`: These return a `Collector` factory class that produces the maximal and minimal element according to the comparator passed as a parameter.
* `toSet()`: This returns a Collector that stores the input elements into a set.

## Exercise (110 points)

In this exercise, you are asked to perform analysis tasks on a list of `Album` data structure. Accordingly, an album has a name, released year and a list of  `Track`.

```java
public class Album {

	private List<Track> tracks;
	private String name;
	private int year;
```

A track has a name and a rating value (0-5)

```java
public class Track {
	
	private String name;
	private int rating;
```

The `AlbumAnalytics` class contains different methods to analyze the list of albums. For each analysis task, there are two implementation versions: sequential version and parallel version (suffix **Par**). The implementation of the sequential versions are done for you already. Your task is to implement the parallel version with parallel stream. 

There are 9 methods need to be implemented. The last method is the bonus one.

Follow the instruction in corresponding java classes to finish the implementation.

Remember to analyze and discuss the speedup of your implementation (the first two methods do not need to be analyzed), and include the screenshot of the output to the report. 

## What To Submit

Complete the the exercises in this lab and put your code along with **lab9_report** (Markdown, TXT or PDF file) into the **lab9** directory of your repository. Commit and push your changes and remember to check the GitHub website to make sure all files have been submitted. 

## References

1. González, Javier Fernández. *Mastering Concurrency Programming with Java 9*. Packt Publishing Ltd, 2017.
2. González, Javier Fernández. *Java 9 Concurrency Cookbook*. Packt Publishing Ltd, 2017.

