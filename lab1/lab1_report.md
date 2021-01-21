<h1 align="center">Assignment 1 in ITF23019 - Parallel and Distributed Programming </h1> 
<h3 align="center">By Sander Riis </h3>


##Images 
Note that all the images are placed in their own folders for their respected projects main folders

## Task 1

````java
package no.hiof.itf23019;

public class DemoThread extends java.lang.Thread
    {
        @Override
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
````

````java

package no.hiof.itf23019;

public class DemoRunnable implements Runnable {

    @Override
    public void run() {
        try {
            // Displaying the thread that is running
            System.out.println("Thread " + Thread.currentThread().getId() + " is running");

        } catch (Exception e) {
            // Throwing an exception
            System.out.println("Exception is caught");
        }
    }
}
````

## Task 2

The bonus with Runnable interface is that its not waiting for the previous thread to start run, but will start all the threads at the same time (Runnable does also implement threads)
The runnable method is supposed to be used only if you are going to override run() and only run(). Because you cant override any thread function since threads are a subclass of this interface and you are not supposed to change the behavior of a subclass


## Task 3

Here I finished the isPrime boolean function (I did look this function up online, because I had some problem creating this algorithm), but I have read and do understand the boolean check 

````java
private boolean isPrime(long number) {
    boolean numPrime = true;
    for (int i = 2; i <= number / 2; ++i) {
        // condition for nonprime number
        if (number % i == 0) {
            numPrime = false;
            break;
        }
    }
    return numPrime;
}
````

##Task 4 

In this task I did a simple test for checking if we are looking on the right filem by checking if the contents name is the same as the filename. 
If it was, I set the found boolean to true and setting the path to the path we are on. 
````java
private void processFile(File content, String fileName, Result parallelResult) {
        if(content.getName().equals(fileName)) {
            found = true;
            parallelResult.setPath(content.getPath());
        }
}

````

In this task I created som easy variable to have the execute time of each method. For getting the speedup time variable I divided the serialRunTime by parallelRun time 
````java
public class Main {

	public static void main(String[] args) {

		File file = new File("C:\\Windows\\");
		String regex = "hosts";
		Date start, end;

		Result result = new Result();
		start = new Date();
		SerialFileSearch.searchFiles(file, regex, result);
		end = new Date();

		double serialRunTime = (end.getTime() - start.getTime());
		System.out.printf("Serial Search: Execution Time: %d%n", end.getTime() - start.getTime());

		
		Result parallelResult = new Result();
		start = new Date();
		ParallelGroupFileSearch.searchFiles(file, regex, parallelResult);
		end = new Date();

		System.out.printf("Parallel Group Search: Path: %s%n", parallelResult.getPath());
		System.out.printf("Parallel Group Search: Execution Time: %d%n", end.getTime() - start.getTime());
		double parallelRunTime = (end.getTime() - start.getTime());
		

		Double speedup = (serialRunTime / parallellRunTime);
		System.out.printf("The speedup is %f%n", speedup);
	}
}
````

##Task 5


##Task 6 
The first thing you can see, even before running the program is that safemain is using something that is called threadLocal data. 
This is a variable that is thread safe  this and writing immutable classes is the only way to achive that. This means that a simplae date frame 
format which is used in the unsafemain, is not threadsafe.
````java
	private static ThreadLocal<Date> startDate = new ThreadLocal<Date>() {
            protected Date initialValue() {
                return new Date();
             }
    };
-----------------------------------------------------------------------------

        private Date startDate;
        startDate = new Date();


````

Safe
--------------------------------------------------
Starting Thread: 22 : Thu Jan 21 12:01:09 CET 2021\
Thread Finished: 22 : Thu Jan 21 12:01:09 CET 2021\
Starting Thread: 23 : Thu Jan 21 12:01:11 CET 2021\
Starting Thread: 24 : Thu Jan 21 12:01:13 CET 2021\
Thread Finished: 23 : Thu Jan 21 12:01:11 CET 2021\
Starting Thread: 25 : Thu Jan 21 12:01:15 CET 2021\
Starting Thread: 26 : Thu Jan 21 12:01:17 CET 2021\
Starting Thread: 27 : Thu Jan 21 12:01:19 CET 2021\
Thread Finished: 24 : Thu Jan 21 12:01:13 CET 2021\
Starting Thread: 28 : Thu Jan 21 12:01:21 CET 2021\
Thread Finished: 26 : Thu Jan 21 12:01:17 CET 2021\
Thread Finished: 27 : Thu Jan 21 12:01:19 CET 2021\
Starting Thread: 29 : Thu Jan 21 12:01:23 CET 2021\
Thread Finished: 25 : Thu Jan 21 12:01:15 CET 2021

Unsafe
--------------------------------------------------
tarting Thread: 22 : Thu Jan 21 12:01:44 CET 2021\
Starting Thread: 23 : Thu Jan 21 12:01:46 CET 2021\
Starting Thread: 24 : Thu Jan 21 12:01:48 CET 2021\
Thread Finished: 24 : Thu Jan 21 12:01:48 CET 2021\
Thread Finished: 23 : Thu Jan 21 12:01:48 CET 2021\
Starting Thread: 25 : Thu Jan 21 12:01:50 CET 2021\
Starting Thread: 26 : Thu Jan 21 12:01:52 CET 2021\
Thread Finished: 22 : Thu Jan 21 12:01:52 CET 2021\
Starting Thread: 27 : Thu Jan 21 12:01:54 CET 2021\
Starting Thread: 28 : Thu Jan 21 12:01:56 CET 2021\
Thread Finished: 26 : Thu Jan 21 12:01:56 CET 2021\
Starting Thread: 29 : Thu Jan 21 12:01:58 CET 2021\
Thread Finished: 25 : Thu Jan 21 12:01:58 CET 2021\
--------------------------------------------------

Here I have taken the output of both the mains. You can see that the safe method always finished the earlier initialized thread before a later one is finished.
Even tho the queue of the threads are not displaying this. The timestamps are. Compared to the unsafe that does not do that. 
It may start a thread and finish it after a thread that is initialized earlier than that thread. 