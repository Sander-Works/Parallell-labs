<h1 align="center"> ITF23019 - Parallel and Distributed Programming </h1>
<h3 align="center"> Spring 2021 </h2>
<h3 align="center"> Lab 1: Multithreading In Java </h2>

In this lab, you will learn how to develop multithreading applications in Java. You will be asked to perform a few exercises and read various manuals available in the lab. 

## Submission Deadline:

You need to commit your codes and lab report to your GitHub repository **before 10:00 AM Monday 25th January**.

## GitHub

To access the class GitHub, you need to be granted access right, please ask us to add you into the group.

Next, you need to accept the class assignment. We will provide you a link to the assignment. Follow the steps in the link and you will be assigned with a private repository named **labs-[github\_username]**. Only you and the lecturers have access to this repository. For this lab and future labs, you should use this repository to submit your lab reports and exercises. 

We can now clone the master labs repository. You are free to use any Git GUI clients (e.g., GitHub Desktop, Tortoise Git, etc.) or even use command line to commit your code.	

## Java IDE

In this course, all the demonstrations are developed with Eclipse. However, you are free to choose another Java IDE to do the programing exercises. Other well-known IDE: IntelliJ IDEA, NetBeans, etc.

## Concurrency and Parallelism

> Users of computer systems are always looking for better performance of their systems. They want to get higher quality videos, better video games, and faster network speeds. Some years ago, processors gave better performance to users by increasing their speed. But now, processors don't increase their speed. Instead of this, they add more cores so that the operating system can execute more than one task at a time. This is called concurrency. Concurrent programming includes all the tools and techniques to have multiple tasks or processes running at the same time in a computer, communicating and synchronizing between them without data loss or inconsistency.

> Concurrency and parallelism are very similar concepts. Different authors give different definitions for these concepts. The most accepted definition talks about concurrency as being when you have more than one task in a single processor with a single core. In this case, the operating system's task scheduler quickly switches from one task to another, so it seems that all the tasks run simultaneously. The same definition talks about parallelism as being when you have more than one task running simultaneously on different computers, processors, or cores inside a processor.

> All modern operating systems allow the execution of concurrent tasks. You can read your emails while listening to music or reading news on a web page. We can say this is process level concurrency. But inside a process, we can also have various simultaneous tasks. Concurrent tasks that run inside a process are called threads.

## Thread In Java

Java implements execution threads using the `Thread` class. You can create an execution thread in your application using the following mechanisms:

* You can **extend** the `Thread` class and override the `run()` method.

* You can **implement** the `Runnable` interface and pass an object of that class to the constructor of a `Thread` object.

### Extending `Thread` class

First, create a maven project  `no.hiof.itf23019.demo-thread`.

Then, create a class `DemoThread` that extends the`java.lang.Thread` class. This class overrides the `run()` method available in the Thread class. A thread begins its life inside `run()` method. 

```java
public class DemoThread extends Thread 
{ 
    public void run() 
    { 
        try
        { 
            // Displaying the thread that is running 
            System.out.println ("Thread " + Thread.currentThread().getId() + " is running"); 
        } 
        catch (Exception e) 
        { 
            // Throwing an exception 
            System.out.println ("Exception is caught"); 
        } 
    } 
} 
```



Create `Main` class that calls `start()` method to start the execution of a thread. `Start()` invokes the `run()` method on the `Thread` object.

```java
public class Main 
{ 
    public static void main(String[] args) 
    { 
        int n = 8; // Number of threads 
        for (int i=0; i<n; i++) 
        { 
            DemoThread object = new DemoThread(); 
            object.start(); 
        } 
    } 
}
```



Run the application and check the output if there is 8 threads created.

### Implement `Runnable` class

First, create a maven project  `no.hiof.itf23019.demo-runnable`.

Then, create a new class `DemoRunnable` which implements `java.lang.Runnable` interface and override `run()` method.

```java
public class DemoRunnable implements Runnable 
{ 
    public void run() 
    { 
        try
        { 
            // Displaying the thread that is running 
            System.out.println ("Thread " + Thread.currentThread().getId() + " is running"); 
  
        } 
        catch (Exception e) 
        { 
            // Throwing an exception 
            System.out.println ("Exception is caught"); 
        } 
    } 

```



Create `Main` class that instantiates a Thread object and call start() method on this object.

```java
public class Main 
{ 
    public static void main(String[] args) 
    { 
        int n = 8; // Number of threads 
        for (int i=0; i<n; i++) 
        { 
            Thread object = new Thread(new DemoRunnable()); 
            object.start(); 
        } 
    } 
} 
```



## Exercise 1 (20 pts):

Complete the above steps and submit the code of the two projects: `no.hiof.itf23019.demo-thread` and `no.hiof.itf23019.demo-runnable` to **lab1** directory of your repository. 

Please also include a screenshot of the program output to your lab report.

## Exercise 2 (10 pts): 

What are the advantages of creating thread by implementing `Runnable` interface over extending `Thread` class?

## Attributes Of Java Thread:

The `Thread` class saves some information attributes that can help us identify a thread, know its status, or control its priority. These attributes are: 

* **ID**: This attribute stores a unique identifier for each thread. To access it, use `getId()` method.
* **Name**: This attribute stores the name of the thread. Use `getName()/setName()` methods to update this attribute.
* **Priority**: This attribute stores the priority of the Thread objects. In Java 9, threads can have priority between 1 and 10, where 1 is the lowest priority and 10 is the highest. It's not recommended that you change the priority of the threads. It's only a hint to the underlying operating system and it doesn't guarantee anything, but it's a possibility that you can use if you want.  To update this attribute, use `getPriority()/setPriority()` methods.
* **Status**: This attribute stores the status of a thread. To access this attribute, use `getState()` method. In Java, a thread can be present in one of the six states defined in the `Thread.State` enumeration: `NEW, RUNNABLE, BLOCKED, WAITING, TIMED_WAITING,` or `TERMINATED`. The following is a list specifying what each of these states means: 
  * `NEW`: The thread has been created and it has not yet started.
  * `RUNNABLE`: The thread is being executed in the JVM. 
  * `BLOCKED`: The thread is blocked and it is waiting for a lock. 
  * `WAITING`: The thread is waiting for another thread. 
  * `TIMED_WAITING`: The thread is waiting for another thread with a specified waiting time. 
  * `TERMINATED`: The thread has finished its execution.

## Interrupt A Thread

A Java program with more than one execution thread only finishes when the execution of all of its threads end (more specifically, when all its **non-daemon threads** end their execution or when one of the threads uses the `System.exit()` method). Sometimes, you may need to finish a thread because you want to terminate a program or when a user of the program wants to cancel the tasks that a thread object is doing.

Java provides an interruption mechanism that indicates to a thread that you want to finish it. One peculiarity of this mechanism is that thread objects have to check whether they have been interrupted or not, and they can decide whether they respond to the finalization request or not. A thread object can ignore it and continue with its execution.

There are three methods: `interrupt()/interrupted()/isInterrupted()` related to interruption mechanism. The first method is used to indicate to a Thread that you're requesting the end of its execution. The other two methods can be used to check the interrupt status. The main difference between those methods is that the `interrupted()` method clears the value of the interrupted flag when it's called and the `isInterrupted()` method does not. A call to the `interrupt()` method doesn't end the execution of a Thread. It is the responsibility of the Thread to check the status of that flag and respond accordingly.

## Exercise 3 (20 pts):

The project `prime-calculator-interprupt` creates one thread which keeps checking a number is prime or not, and prints message accordingly. The thread only stop when it receives interrupt signal from the main.

```java 
public void run() {
    long number = 1L;

    // This never ends... until is interrupted
    while (true) {
        if (isPrime(number)) {
            System.out.printf("Number %d is Prime\n", number);
        }

        // When is interrupted, write a message and ends
        if (isInterrupted()) {
            System.out.printf("The Prime Generator has been Interrupted\n");
            return;
        }
        number++;
    }
}
```

The main method, after creating the thread, sleeps for 5 seconds, and then interrupt the thread.

```java
public static void main(String[] args) {

    // Launch the prime numbers generator
    Thread task = new Calculator();
    task.start();

    // Wait 5 seconds
    try {
        TimeUnit.SECONDS.sleep(5);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }

    // Interrupt the prime number generator
    task.interrupt();

    // Write information about the status of the Thread
    System.out.printf("Main: Status of the Thread: %s\n", task.getState());
    System.out.printf("Main: isInterrupted: %s\n", task.isInterrupted());
    System.out.printf("Main: isAlive: %s\n", task.isAlive());
}
```

Take a look at the code to understand how thread interruption works and complete the method `private boolean isPrime(long number)` in `Calculator.java` file.

Please also include a screenshot of the program output to your lab report.

## Controlling The Interruption Of A Thread

The mechanism shown in the previous example can be used if the thread that can be interrupted is simple. But if the thread implements a complex algorithm divided into some methods or it has methods with recursive calls, we will need to use a better mechanism to control the interruption of the thread. Java provides the `InterruptedException` exception for this purpose. You can throw this exception when you detect the interruption of a thread and catch it in the run() method.

## Exercise 4 (30 pts):

The project `parallel-file-search` implements an algorithm that looks for a file with a predetermined name. Our algorithms will take the initial path to start the search and the file name we're going to look for as input. 

There are two versions of searching algorithm:

* **Serial version** is implemented in `SerialFileSearch.java`. We take the initial path of the search, get the files and the directories' contents, and process them. For files, we compare their name with the name we're looking for. If both names are equal, we fill the Result object and finish the execution of the algorithm. For directories, we made a recursive call to the operation to search the file inside those directories. 

  ```java
  public static void searchFiles(File file, String fileName, Result result) {
  
      File[] contents;
  
      contents=file.listFiles();
  
      if ((contents==null) || (contents.length==0)) {
          return;
      }
  
      for (File content : contents) {
          if (content.isDirectory()) {
              searchFiles(content,fileName, result);
          } else {
              if (content.getName().equals(fileName)) {
                  result.setPath(content.getAbsolutePath());
                  result.setFound(true);
                  System.out.printf("Serial Search: Path: %s%n", result.getPath());
                  return;
              }
          }
  
          if (result.isFound()) {
              return;
          }
      }
  }
  ```

* **Parallel version** is implemented in `ParallelGroupFileSearch.java` and `ParallelGroupFileTask.java`.  Here, we create an execution thread per directory we want to process. Specifically, we will store the directories included in the initial path in a `ConcurrentLinkedQueue` (an implementation of a `Queue` interface that can be used in concurrent applications) and create as many threads as processors that are available to the JVM. Each thread will take a path from the queue and process this directory and all its subdirectories and files. When it has processed all the files and directories in that directory, it takes another from the queue. 

  If one of the threads finds the file we were looking for, it ends its execution immediately. In that case, we finish the execution of the other threads using the `interrupt()` method.

  

  The `ParallelGroupFileTask` class implements all the threads we're going to use to find the file. It implements the `Runnable` interface and uses four internal attributes: a `String` attribute named `fileName` that stores the name of the file we're looking for, the `ConcurrentLinkedQueue` of `File` objects named `directories` that stores the list of directories we're going to process, a `Result` object named `parallelResult` to store the result of our search, and a `Boolean` attribute named
  `found` to mark if we find the file we were looking for. We're going to use the constructor of the class to initialize all the attributes:

  ``` java
  private final String fileName;
  private final ConcurrentLinkedQueue<File> directories;
  private final Result parallelResult;
  private boolean found;
  
  public ParallelGroupFileTask(String fileName, Result parallelResult, ConcurrentLinkedQueue<File> directories) {
      this.fileName = fileName;
      this.parallelResult = parallelResult;
      this.directories = directories;
      this.found = false;
  }
  ```

  The `run()` method has a loop that will be executed while there are elements in the queue and we haven't found the file. It takes the next directory to process using the `poll()` method of the `ConcurrentLinkedQueue` class and calls to the auxiliary
  method `processDirectory()`. If we have found the file (the `found` attribute is true), we end the execution of the thread with the return instruction:

  ```java
  public void run() {
      while (directories.size() > 0) {
          File file = directories.poll();
          try {
              processDirectory(file, fileName, parallelResult);
              if (found) {
                  System.out.printf("%s has found the file%n", Thread.currentThread().getName());
                  System.out.printf("Parallel Search: Path: %s%n", parallelResult.getPath());
                  return;
              }
          } catch (InterruptedException e) {
              System.out.printf("%s has been interrupted%n", Thread.currentThread().getName());
          }
      }
  }
  ```

  The `processDirectory()` method will receive the `File` object that stores the directory to process, the name of the file we're looking for, and the `Result` object to store the result if we found it as parameters. It obtains the contents of the `File` using
  the `listFiles()` method that returns an array of `File` objects and processes that array. For directories, it makes a recursive call to this method with the new object. For files, it calls the auxiliary `processFile()` method:

  ``` java
  private void processDirectory(File dir, String fileName, Result parallelResult) throws InterruptedException {
      File[] contents;
      contents = dir.listFiles();
  
      if ((contents == null) || (contents.length == 0)) {
          return;
      }
  
      for (File content : contents) {
          if (content.isDirectory()) {
              processDirectory(content, fileName, parallelResult);
              if (Thread.currentThread().isInterrupted()) {
                  throw new InterruptedException();
              }
              if (found) {
                  return;
              }
          } else {
              processFile(content, fileName, parallelResult);
              if (Thread.currentThread().isInterrupted()) {
                  throw new InterruptedException();
              }
              if (found) {
                  return;
              }
          }
      }
  }
  ```

  We also check, after we have processed every directory and every file, if the thread has been interrupted. We use the `currentThread()` method of the `Thread` class to get the `Thread` object that is executing this task and then the `isInterrupted()` method to verify if the thread has been interrupted or not. If the thread has been interrupted, we throw a new `InterruptedExeption` exception that we catch in the `run()` method to end the execution of the thread. This mechanism allows us to finish our search when we have found the file.

  The `ParallelGroupFileSearch` class implements the whole algorithm using the auxiliary tasks. It's going to implement the static `searchFiles()` method. It receives a `File` object that points to the base path of the search, a `String` named `fileName` that stores the name of the file we're looking for, and a `Result` object to store the result
  of the operation as parameters.

  First, it creates the `ConcurrentLinkedQueue` object and stores in it all the directories included in the base path:

  ``` java
  public static void searchFiles(File file, String fileName, Result parallelResult) {
  
      ConcurrentLinkedQueue<File> directories = new ConcurrentLinkedQueue<>();
      File[] contents = file.listFiles();
  
      for (File content : contents) {
          if (content.isDirectory()) {
              directories.add(content);
          }
      }
  ```
  
  Then, we obtain the number of threads available to the JVM using the `availableProcessors()` method of the `Runtime` class and create a `ParallelFileGroupTask` and a `Thread` per processor.
  
  ```java
  int numThreads = Runtime.getRuntime().availableProcessors();
      Thread[] threads = new Thread[numThreads];
      ParallelGroupFileTask[] tasks = new ParallelGroupFileTask[numThreads];
  
      for (int i = 0; i < numThreads; i++) {
          tasks[i] = new ParallelGroupFileTask(fileName, parallelResult, directories);
          threads[i] = new Thread(tasks[i]);
          threads[i].start();
      }
  ```
  
  Finally, we wait until one thread finds the file or all the threads have finished their execution. In the first case, we cancel the execution of the other threads using the `interrupt()` method and the mechanism explained before. We use the `getState()` method of the  `Thread` class to check if the threads have finished their execution:
  
  ``` java
      boolean finish = false;
      int numFinished = 0;
  
      while (!finish) {
          numFinished = 0;
          for (int i = 0; i < threads.length; i++) {
              if (threads[i].getState() == State.TERMINATED) {
                  numFinished++;
                  if (tasks[i].getFound()) {
                      finish = true;
                  }
              }
          }
          if (numFinished == threads.length) {
              finish = true;
          }
      }
  
      // Interrupt the remaining threads.
      if (numFinished != threads.length) {
          for (Thread thread : threads) {
              thread.interrupt();
          }
      }
  
  }
  ```

Please pay attention to how the `InterruptedException` is throwed in `processDirectory` and caught in `run`.

Your tasks for this exercise are:

* Complete the implementation of method `processFile` in `ParallelGroupFileTask.java`
* Compute the **Speedup = Execution time of serial version / Execution time of parallel version**. The speed needs to be computed and printed at the end of the `public static void main(String[] args) ` method.

Please also include a screenshot of the program output to your lab report.

## Waiting For The Finalization Of A Thread

In some situations, we will have to wait for the end of the execution of a thread (the `run()` method ends its execution). For example, we may have a program that will begin initializing the resources it needs before proceeding with the rest of the execution. We can run initialization tasks as threads and wait for their finalization before continuing with the rest of the program. 

For this purpose, we can use the `join()` method of the `Thread` class. When we call this method using a thread object, it suspends the execution of the calling thread until the object that is called finishes its execution.

An example of waiting for thread finalization is demonstrated in the project `data-sources-loader`. The `main` method creates two thread: `DataSourceThread` and `NetworkConnectionLoader`. After starting those threads, it waits for them to finish by calling `join()` on both threads and proceed accordingly.

``` java
public static void main(String[] args) {

    // Creates and starts a DataSourceLoader runnable object
    DataSourcesLoader dsLoader = new DataSourcesLoader();
    Thread thread1 = new Thread(dsLoader,"DataSourceThread");

    // Creates and starts a NetworkConnectionsLoader runnable object
    NetworkConnectionsLoader ncLoader = new NetworkConnectionsLoader();
    Thread thread2 = new Thread(ncLoader,"NetworkConnectionLoader");

    // Start both threads
    thread1.start();
    thread2.start();

    // Wait for the finalization of the two threads
    try {
        thread1.join();
        thread2.join();
    } catch (InterruptedException e) {
        e.printStackTrace();
    }

    // Waits a message
    System.out.printf("Main: Configuration has been loaded: %s\n",new Date());
}
```

## Exercise 5 (10 pts):

Run the `data-sources-loader` and include the screenshot of the program output to your lab report.

## Using Thread Local Variables

One of the most critical aspects of a concurrent application is shared data. This has special importance in objects that extend the `Thread` class or implement the `Runnable` interface and in objects that are shared between two or more threads.

If you create an object of a class that implements the `Runnable` interface and then start various thread objects using the same Runnable object, all the threads would share the same attributes. This means that if you change an attribute in a thread, all the threads will be affected by this change.

Sometimes, you will be interested in having an attribute that won't be shared among all the threads that run the same object. The Java Concurrency API provides a clean mechanism called `thread-local variables` with very good performance. They have some disadvantages as well. They retain their value while the thread is alive. This can be problematic in situations where threads are reused.

The project `thread-local-variables` demonstrate the use of `thread-local variables`. There are two kinds of threads implemented in this project: `UnsafeTask` and `SafeTask`. Accordingly, the thread implemented in `UnsafeTask` class has a `startDate` that will be shared by all the threads whereas in `SafeTask` the variable is declared as `ThreadLocal`:

``` java
private static ThreadLocal<Date> startDate = new ThreadLocal<Date>() {
    protected Date initialValue() {
        return new Date();
    }
};
```

The two tasks does the same thing: they print out the value of `startDate` when they start and end their execution:

``` java
public void run() {
		// Writes the start date
		System.out.printf("Starting Thread: %s : %s\n", Thread.currentThread().getId(), startDate.get());
		try {
			TimeUnit.SECONDS.sleep((int) Math.rint(Math.random() * 10));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// Writes the start date
		System.out.printf("Thread Finished: %s : %s\n", Thread.currentThread().getId(), startDate.get());
	}
```

## Exercise 6 (10 points):

Run the `UnsafeMain` and `SafeMain` and discuss the differences between the output of these two programs in term of `startDate` values.

## Markdown:

I wrote this lab using Markdown (`.md`) which is a very simple markup language for documentation. You can find  more information about markdown [here](https://github.com/ITF23019/demo-markdown). I was thinking maybe you should also try using markdown to write your reports. **This is not mandatory, just a suggestion**. I just wanted to introduce to you a very cool language that developers use to document their code.

## What To Submit

Complete the the exercises in this lab and put your code along with **lab1_report** (Markdown, TXT or PDF file) into the **lab1** directory of your repository. Commit your changes and remember to check the GitHub website to make sure all files have been submitted.

## References:

1. [Multithreading in Java - GeeksforGeeks](https://www.geeksforgeeks.org/multithreading-in-java/#:~:text=Multithreading%20is%20a%20Java%20feature,weight%20processes%20within%20a%20process.)
2. González, Javier Fernández. *Mastering Concurrency Programming with Java 9*. Packt Publishing Ltd, 2017.