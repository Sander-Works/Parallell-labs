<h1 align="center"> ITF23019 - Parallel and Distributed Programming </h1>
<h3 align="center"> Spring 2021 </h2>
<h3 align="center"> Lab 4: Thread Synchronization 1 </h2>


In this lab, we will evaluate different Java utilities such as monitor, lock, barrier, semaphore, etc. to synchronize threads within parallel and concurrent program

 

## Submission Deadline:

You need to commit your codes and lab report to your GitHub repository **before 10:00 AM Monday 15th February**.

## GitHub

If you have not config your `labs-yourusername` repository for upstream remote repository, please go back to the instruction from `lab2`.

Remember to commit and push your changes before starting this lab. Then, start the **Command Prompt** of GitHub Desktop and run the command:

```bash
> git pull upstream main
```

Then, **push** everything to your `labs-yourusername`.

## Synchronization Mechanisms in Java

In a concurrent application, it is normal for multiple threads to *read or write the same data structure* or have *access to the same file or database connection*. These shared resources can provoke error situations or data inconsistency, and we have to implement mechanisms to avoid these errors. These situations are called **race conditions** and they occur when different threads have access to the same shared resource at the same time. Therefore, the final result depends on the order of the execution of threads, and most of the time, it is incorrect.

The solution for these problems lies in the concept of critical section. A critical section is a block of code that accesses a shared resource and can't be executed by more than one thread at the same time.

To help programmers implement critical sections, Java (and almost all programming languages) offers synchronization mechanisms. When a thread wants access to a critical section, it uses one of these synchronization mechanisms to find out whether there is any other thread executing the critical section. If not, the thread enters the critical section. If yes, the thread is suspended by the synchronization mechanism until the thread that is currently executing the critical section ends it. When more than one thread is waiting for a thread to finish the execution of a critical section, JVM chooses one of them and the rest wait for their turn.

Java provides several utilities to synchronize threads:

* The most basic synchronization mechanism is `Monitor` with the `synchronized` keyword. This keyword can be
  applied to a method or to a block of code.
* The `Lock` interfaces and its implementation classes: This mechanism allows you to implement a critical section to guarantee that only one thread will execute that block of code.
* The `Condition` class (condition variable)  allows you to specify the condition to define the order of execution of the threads.
* The `Semaphore` class that implements the well-known semaphore synchronization mechanism introduced by **Edsger Dijkstra**.
* `CountDownLatch` allows you to implement a situation where one or more threads wait for the finalization of other threads.
* `CyclicBarrier` allows you to synchronize different tasks in a common point.
* `Exchanger` allows you to implement a point of data interchange between two threads.
* `Phaser` allows you to implement concurrent tasks divided into phases.
* `CompletableFuture`, a new feature of Java 8, extends the `Future` mechanism of executor tasks to generate the result of a task in an asynchronous way. You can specify tasks to be executed after the result is generated, so you can control the order of the execution of tasks.

In this lab and the next lab, we will work with those mechanisms to synchronize threads and tasks for parallel program.

## Monitor

> A **monitor** is a synchronization construct that allows threads to have both **mutual exclusion** and the ability to wait (block) for a certain condition to become false. Monitors also have a mechanism for signaling other threads that their condition has been met.

In java, monitor is implemented with `synchronized` keyword. `synchronized` keyword is used to control concurrent access to a method or a block of code. All the `synchronized` sentences (used on methods or blocks of code) use **an object reference**. Only one thread can execute a method or block of code protected by the same object reference.

### Synchronize the Methods or Block of Code

When you use the `synchronized` keyword with a method, the object reference is implicit. When you use the `synchronized` keyword in one or more methods of an object, only one execution thread will have access to all these methods. If another thread tries to access any method declared with the `synchronized` keyword of the same object, it will be suspended until the first thread finishes the execution of the method. In other words, every method declared with the synchronized keyword is a critical section, and Java only allows the execution of one of the critical sections of an object at a time. In this case, the object reference used is the `own` object, represented by the `this` keyword.

```java
public class EventStorage {
    
	public synchronized void set(){
		...
	}
	
	public synchronized void get(){
		...
	}
```

When you use the `synchronized` keyword to protect a block of code, you must pass an **object reference** as a parameter. Normally, you will use the `this` keyword to reference the object that executes the method, but you can use other object references as well. Normally, these objects will be created exclusively for this purpose. You should keep the objects used for synchronization private. For example, if you have two independent attributes in a class shared by multiple threads, you must synchronize access to each variable; however, it wouldn't be a problem if one thread is accessing one of the attributes and the other accessing a different attribute at the same time. 

``` java
public class SafeParkingStats {
	
	/**
	 * This two variables store the number of cars and motorcycles in the parking
	 */
	private long numberCars;
	private long numberMotorcycles;

	/**
	 * Two objects for the synchronization. ControlCars synchronizes the
	 * access to the numberCars attribute and controlMotorcycles synchronizes
	 * the access to the numberMotorcycles attribute.
	 */
	private final Object controlCars, controlMotorcycles;
	
	
	public void carComeIn() {
		synchronized (controlCars) {
			numberCars++;
		}
	}
	
	public void motoComeIn() {
		synchronized (controlMotorcycles) {
			numberMotorcycles++;
		}
	}
    
}
```

Take into account that if you use the `own` object (represented by the `this` keyword), you might interfere with other synchronized code (as mentioned before, the this object is used to synchronize the methods marked with the `synchronized` keyword).

### Using Condition in Synchronized Code

Java provides the `wait()`, `notify()`, and `notifyAll()` methods implemented in the Object class. A thread can call the `wait()` method inside a `synchronized` block of code. If it calls the `wait()` method outside a synchronized block of code, JVM throws an `IllegalMonitorStateException` exception. When the thread calls the `wait()` method, JVM puts the thread to sleep and releases the object that controls the `synchronized` block of code that it's executing and allows other threads to execute other blocks of `synchronized` code protected by this object. To wake up the thread, you must call the `notify()` or `notifyAll() `methods inside a block of code protected by the same object.

These methods can be used to address the popular producer-consumer problem in which there is data buffer that is shared among multiple producers and consumers. The buffer need to be control in a way that a producer can't save data in the buffer if it's full, and a consumer can't take data from the buffer if it's empty. 

The `event-storage` project simulates the same situation in which the producer will push 100 events into the `EventStorage` which has `maxSize` is only 10. In this case, we use conditions and `wait()/notify()` methods to synchronize the producer and consumers. 

``` java
public class EventStorage {
   
	...
	
	/**
	 * This method creates and storage an event.
	 */
	public synchronized void set(){
			while (storage.size()==maxSize){
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			storage.add(new Date());
			System.out.printf("Set: %d\n",storage.size());
			notify();
	}
	
	/**
	 * This method delete the first event of the storage.
	 */
	public synchronized void get(){
			while (storage.size()==0){
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			String element=storage.poll().toString();
			System.out.printf("Get: %d: %s\n",storage.size(),element);
			notify();
	}
}
```

## Lock

Java provides another mechanism for synchronizing blocks of code. It's a more powerful and flexible mechanism than the `synchronized` keyword. It's based on the `Lock` interface  (of the `java.util.concurrent.locks` package) and classes that implement it is `ReentrantLock`. The `Lock` interface provides two important methods  `lock()` and `unlock()` to enable mutual exclusion.

The `Lock` interface offers better performance than the `synchronized` keyword. In addition, it provides additional functionalities over the `synchronized` keyword. One of the new functionalities is implemented by the `tryLock()` method. This method doesn't put the thread to sleep but tries to get control of the lock, and if it can't, because it's used by another thread, it returns `false`. With the `synchronized` keyword, if thread (A) tries to execute a synchronized block of code when thread (B) is executing it, thread (A) is suspended until thread (B) finishes the execution of the `synchronized` block. With lock, you can execute the `tryLock()` method. This method returns a `Boolean` value indicating whether there is another thread running the code protected by this lock.

For example, the following task (in the `synchronization` project) gets a lock in the first line of its code using the `lock()` method and releases it in the last line using the `unlock()` method. You must include the calling to the `unlock()` method **in a finally section** to avoid any problems. Otherwise, if an `Exception` is thrown, the lock won't be released and you will have a deadlock. Only one task can execute the code between these two sentences at the same time.

```java
public class LockTask implements Runnable {

	private static ReentrantLock lock = new ReentrantLock();
	private String name;

	public LockTask(String name) {
		this.name = name;
	}

	@Override
	public void run() {
		try {
			lock.lock();
			System.out.println("Task: " + name + "; Date: " + new Date() + ": Running the task");
			CommonTask.doTask();
			System.out.println("Task: " + name + "; Date: " + new Date() + ": The execution has finished");
		} finally {
			lock.unlock();
		}
	}
}
```

One of the most significant improvements offered by locks is the `ReadWriteLock` interface and the `ReentrantReadWriteLock` class. This class has **two locks**: one for read operations and one for write operations. There can be more than one thread using read operations simultaneously, but only one thread can use write operations. If a thread is doing a write operation, other threads can't write or read.

The following example (in the `synchronization` project) demonstrates the use of `ReadWriteLock` to simulate the reader and writer that have access to the same shared `PriceInfo` data.

``` java
public class PricesInfo {
	
	/**
	 * The two prices
	 */
	private double price1;
	private double price2;
	
	...

	/**
	 * Returns the first price
	 * @return the first price
	 */
	public double getPrice1() {
		lock.readLock().lock();
		double value=price1;
		lock.readLock().unlock();
		return value;
	}

	/**
	 * Establish the prices
	 * @param price1 The price of the first product
	 * @param price2 The price of the second product
	 */
	public void setPrices(double price1, double price2) {
		lock.writeLock().lock();
		System.out.printf("%s: PricesInfo: Write Lock Acquired.\n", new Date());
		try {
			TimeUnit.SECONDS.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.price1=price1;
		this.price2=price2;
		System.out.printf("%s: PricesInfo: Write Lock Released.\n", new Date());
		lock.writeLock().unlock();
	}
}
```

## Condition Variable

A `Lock` may be associated with one or more conditions. These conditions are declared in the `Condition` interface. The purpose of these conditions is to allow threads to have control of a lock and check whether a condition is `true` or not. If it's `false`, the thread will be suspended until another thread wakes it up. The `Condition` interface provides the mechanisms to suspend a thread (`await()` method) and wake up a suspended thread (`signal()` or`signalAll()`).

For example, the following `TaskController`  (in the `synchronization` project)  synchronizes the execution of two tasks: `ConditionTask1` and `ConditionTask2` with the `turn` integer. If `turn` is 1, `ConditionTask1` will run. Otherwise, `ConditionTask2` will run.

``` java
public class TaskController {

	/**
	 * Lock to control the order of the tasks
	 */
	private final ReentrantLock lock;

	/**
	 * Conditions to control that the order of the task
	 */
	private final Condition condition;
    
    private int turn;
    
    public TaskController() {
		lock = new ReentrantLock();
		condition = lock.newCondition();
		turn = 2;
	}

	public void doTask1() {
		lock.lock();

		try {

			while (turn != 1)
				condition.await();

			System.out.println("Task1 " + "Date: " + new Date() + ": Running the task");
			CommonTask.doTask();
			System.out.println("Task1 " + "Date: " + new Date() + ": The execution has finished\n");

			turn = 2;
			condition.signalAll();

		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}

	}

	public void doTask2() {

		lock.lock();
		
		try {

			while (turn != 2)
				condition.await();

			System.out.println("Task2: " + "Date: " + new Date() + ": Running the task");
			CommonTask.doTask();
			System.out.println("Task2: " + "Date: " + new Date() + ": The execution has finished\n");

			turn = 1;
			condition.signalAll();

		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
		
	}

}
```

## Semaphore

A semaphore is a counter that protects access to one or more shared resources. When a thread wants to access one of the shared resources, it must first acquire the semaphore. If the internal counter of the semaphore is greater than 0, the semaphore decrements the counter and allows access to the shared resource. A counter bigger than 0 implies that there are free resources that can be used, so the thread can access and use one of them.

Otherwise, if the counter is 0, the semaphore puts the thread to sleep until the counter is greater than 0. A value of 0 in the counter means all the shared resources are used by other threads, so the thread that wants to use one of them must wait until one is free.

When the thread has finished using the shared resource, it must release the semaphore so that another thread can access the resource. This operation increases the internal counter of the semaphore.

In Java, the semaphore is implemented in the `Semaphore` class with two basic methods: `acquire()` and `release()`.

For example, the following task (in the `synchronization` project) use `Semaphore` to protect its code:

``` java
public class SemaphoreTask implements Runnable{

	private Semaphore semaphore;
	
	public SemaphoreTask(Semaphore semaphore) {
		this.semaphore=semaphore;
	}
	
	@Override
	public void run() {

		try {
			semaphore.acquire();
			CommonTask.doTask();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaphore.release();
		}
	}
}
```

In the main program, we execute 10 tasks that share a `Semaphore` class initialized with **two shared resources**, so we will have two tasks running at the same time:

```java
public static void main(String[] args) {

    Semaphore semaphore=new Semaphore(2);
    ThreadPoolExecutor executor=(ThreadPoolExecutor)Executors.newCachedThreadPool();

    for (int i=0; i<10; i++) {
        executor.execute(new SemaphoreTask(semaphore));
    }

    executor.shutdown();
    try {
        executor.awaitTermination(1, TimeUnit.DAYS);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
}
```

## CountDownLatch

This `CountDownLatch` class provides a mechanism to wait for the finalization of one or more concurrent operations. This class is initialized with **an integer number**, which is the number of operations the threads are going to wait for. When a thread wants to wait for the execution of these operations, it uses the `await()` method. This method puts the thread to sleep until the operations are completed. When one of these operations finishes, it uses the `countDown()` method to decrement the internal counter of the `CountDownLatch` class. When the counter arrives at 0, the class wakes up all the threads that were sleeping in the `await()` method.

For example, in following task (in the `synchronization` project), we use the `countDown()` method to decrement the internal counter of the `CountDownLatch` object it receives as a parameter in its constructor:

``` java
public class CountDownTask implements Runnable {

	private CountDownLatch countDownLatch;
	
	public CountDownTask(CountDownLatch countDownLatch) {
		this.countDownLatch=countDownLatch;
	}
	
	@Override
	public void run() {
		CommonTask.doTask();
		countDownLatch.countDown();
		
	}
}
```

Then, in the `main()` method, we execute the tasks in an executor and wait for their finalization using the `await()` method of `CountDownLatch`. The object is initialized with the number of tasks we want to wait for.

```java
public static void main(String[] args) {

    CountDownLatch countDownLatch=new CountDownLatch(10);

    ThreadPoolExecutor executor=(ThreadPoolExecutor)Executors.newCachedThreadPool();

    System.out.println("Main: Launching tasks");
    for (int i=0; i<10; i++) {
        executor.execute(new CountDownTask(countDownLatch));
    }

    try {
        countDownLatch.await();
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
    System.out.println("Main: Tasks finished at "+new Date());

    executor.shutdown();
}
```

## Barrier

The Java concurrency API provides a synchronizing utility that allows the synchronization of two or more threads at a determined point. It's the `CyclicBarrier` class. 

The `CyclicBarrier` class is initialized with an integer number, which is the number of threads that will be synchronized at a determined point. When one of these threads arrives at the determined point, it calls the `await()` method to wait for the other threads. When the thread calls this method, the `CyclicBarrier` class blocks the thread that is sleeping until the other threads arrive. When the last thread calls the `await()` method of the `CyclicBarrier` object, it wakes up all the threads that were waiting and continues with its job.

One interesting advantage of the` CyclicBarrier` class is that you can pass an additional `Runnable` object as an initialization parameter, and the `CyclicBarrier` class executes this object as a thread when all the threads arrive at the common point. 

For example, we have implemented the following `Runnable` that uses a `CyclicBarrier` object to wait for other tasks:

```java
public class BarrierTask implements Runnable {
	
	private CyclicBarrier barrier;
	
	public BarrierTask(CyclicBarrier barrier) {
		this.barrier=barrier;
	}

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName()+": Phase 1");
		CommonTask.doTask();
		try {
			barrier.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName()+": Phase 2");
		
	}
}
```

We have also implemented another `Runnable` object that will be executed by `CyclicBarrier` when all the tasks have executed the `await()` method.

```java
public class FinishBarrierTask implements Runnable {

	@Override
	public void run() {
		System.out.println("FinishBarrierTask: All the tasks have finished");
	}
}
```

Finally, in the `main()` method, we execute 10 tasks in an executor. You can see how `CyclicBarrier` is initialized with the number of tasks we want to synchronize and with an object of the `FinishBarrierTask` object:

```java
public static void main(String[] args) {
    CyclicBarrier barrier = new CyclicBarrier(10,new FinishBarrierTask());

    ThreadPoolExecutor executor=(ThreadPoolExecutor)Executors.newCachedThreadPool();

    for (int i=0; i<10; i++) {
        executor.execute(new BarrierTask(barrier));
    }

    executor.shutdown();

    try {
        executor.awaitTermination(1, TimeUnit.DAYS);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
}
```

## Exchanger

The Java concurrency API provides a synchronization utility that allows interchange of data between two concurrent tasks. In more detail, the `Exchanger` class allows you to have a definition of a synchronization point between two threads. When the two threads arrive at this point, they interchange a data structure such that the data structure of the first thread goes to the second one and vice versa.

This class may be very useful in a situation similar to the producer-consumer problem. This is a classic concurrent problem where you have a common buffer of data, one or more producers of data, and one or more consumers of data. As the Exchanger class synchronizes only two threads, you can use it if you have a producer-consumer problem with one producer and one consumer.

For example,  the main function of the producer is implemented below. Basically, in each cycle, it produce the data to the buffer and exchange this buffer with the buffer of the consumer, and start the next cycle again.

```java
/**
* Main method of the producer. It produces 100 events. 10 cycles of 10 events.
* After produce 10 events, it uses the exchanger object to synchronize with 
* the consumer. The producer sends to the consumer the buffer with ten events and
* receives from the consumer an empty buffer
*/
@Override
public void run() {

    for (int cycle=1; cycle<=10; cycle++){
        System.out.printf("Producer: Cycle %d\n",cycle);

        for (int j=0; j<10; j++){
            String message="Event "+(((cycle-1)*10)+j);
            System.out.printf("Producer: %s\n",message);
            buffer.add(message);
        }

        try {
            /*
				 * Change the data buffer with the consumer
				 */
            buffer=exchanger.exchange(buffer);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.printf("Producer: %d\n",buffer.size());

    }
}
```



## Exercise 1 (10 points):

The `parking` project simulates a parking system which consists of multiple sensors tracking the number of cars and motorcycles coming in and out. In this simulation, each sensor will have 10 cars and 10 motorcycles coming in and out respectively. There are multiple sensors each of which is handled by one thread. The `ParkingStats` contains two shared variables: `numberCars` and `numberMotorcycles` to keep track of the number of cars and motorcycles.  The `ParkingCash` contains one shared variable `cash` that will be increased by one every time one vehicle going out. 

The classes in the `unsafe` package is the ones that do not have any synchronization methods and have data race issue. The others in `safe` package use monitor to handle the issue. 

Your task in this exercise is to run the two version: `safe` and `unsafe` and discuss the data race issue focusing on the use of `sychronized` keyword.

## Exercise 2 (20 points): 

The `print-queue` project simulates the situatation in which there are multiple printing jobs submiited to several printers. Usually, the number of printing jobs are much largers than the number of available printers. In this project, we use semaphore and lock to ensure the synchronzation of the printing queue.

`Semaphore` will block  the job until there is an available printers. The `Lock` is used to ensure mutual exclusion on the shared information of the printers. 

Follow the instructions in the corresponding classes. Remember to include the output of your program into the report.

## Exercise 3 (40 points)

The `printing-tasks` project creates to concurent tasks: `PrintingTaskA` that prints "Welcome to" and `PrintingTaskB` that prints "Østfold University College" three times. Your task in this exercise is to synchronize the tasks so that the messages are printed in correct order by using:

* Semaphore (15 points)
* Condition (15 points)
* Monitor with condition (10 points)

Follow the instructions in the corresponding classes.

## Exercise 4 (30 points)

The `video-conference` simulates the situation in which there are multiple participants joining the conference. The conference will start only when all the partitipants arrive. Your task in this exercise is to synchronize the threads that represent the `Participant` so that `VideoConference` will run after all those `Participant` threads finish, by using:

* CountDownLatch (15 points)
* Barrier (15 points).

Follow the instructions in the corresponding classes.

## Bonus Exercise (15 points):

The `concurrent-counter` project implements two type of counter: `SingleCounter` and `DoubleCounter` which respectively contain one and two shared integers which will be either increased or decreased by multiple threads. 

Your task in this exercise is to address the data race problem with `monitor`. Follow the instructions in the corresponding classes and start your implementation. In the report, you should mentioned which type of monitor you used for each type of counter. Remember to include the output of your program.

## What To Submit

Complete the the exercises in this lab and put your code along with **lab4_report** (Markdown, TXT or PDF file) into the **lab4** directory of your repository. Commit and push your changes and remember to check the GitHub website to make sure all files have been submitted. 

## References:

1. González, Javier Fernández. *Mastering Concurrency Programming with Java 9*. Packt Publishing Ltd, 2017.
2. González, Javier Fernández. *Java 9 Concurrency Cookbook*. Packt Publishing Ltd, 2017.