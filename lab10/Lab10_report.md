<h1 align="center"> ITF23019 - Parallel and Distributed Programming </h1>
<h3 align="center"> Spring 2021 - Lab10 </h2>
<h3 align="center"> REST  </h2>

###Task 1.2 

The ExecutorService simplifies running tasks asynchronously, ExecutorService automatically provides a pool of threads, and an API for assigning tasks to it. 
Since this is an executor it also comes with all the executor features we are used to, like shutdown, incokeAll, etc... 
It also has futures witch is also really useful when it comes to servers. 

KKMultiServerThread pros
* Quick and Efficient: Multithreaded server could respond efficiently and quickly to the increasing client queries.  
* Waiting time for users decreases: In a single-threaded server, other users had to wait until the running process gets completed but in multithreaded servers, all users can get a response at a single time so no user has to wait for other processes to finish.  
* Threads are independent of each other: There is no relation between any two threads. When a client is connected a new thread is generated every time.  
* The issue in one thread does not affect other threads: If any error occurs in any of the threads then no other thread is disturbed, all other processes keep running normally. In a single-threaded server, every other client had to wait if any problem occurs in the thread. 

KKMultiServerThread cons

* Complicated Code: It is difficult to write the code of the multithreaded server. These programs can not be created easily
* Debugging is difficult: Analyzing the main reason and origin of the error is difficult.
 ------------------------
  (source: https://www.geeksforgeeks.org/multithreaded-servers-in-java/ )
