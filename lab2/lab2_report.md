<h1 align="center"> ITF23019 - Parallel and Distributed Programming </h1>
<h3 align="center"> Spring 2021 </h2>
<h3 align="center"> Lab 2: Report </h2>

##Task 1 
* What will happen to the submitted tasks when we call the `shutdown()` on the executor?

When you call shutdown() with the exector interface implemented. It will put the remaining tasks in a queue and reject all later injected tasks. It will also kill the threads when it's done with the tasks. 


##Task 2 

###2.1
* What will happen when we call `get()` method from a task that is not done.

If you call the get() function You will get the result of the task. If the task is not finished however the exection will be blocked until the task is finished, and the result is available  

###2.2.1
* How to cancel a submitted task?

If you want to cancel tasks that have been sent to the executor you can call Future.cancel(). This will affect the tasks in three different ways depending on the stage of the task. 

1.  The task hasn’t started executing yet - it’s waiting in the work queue for a thread to start executing it.

In this case it will simply be removed from the queue 
    
2.  A thread is executing the task.

You can not always stop a running task, because java is depending co-operative mechanism called interruption. This sends a signal that will tell the thread to stop itself at its next opportunity. 
The problem is that the thread dosent always respect this signal. that's why you will need to check if it's interrupted with Thread.currentThread().isInterrupted().
    
3.  The task has finished executing.

If this happens it will not happen anything 


Source (https://codeahoy.com/java/Cancel-Tasks-In-Executors-Threads/)

###2.2.2

If you are going to shut down the process anyways you can also call shutDownNow(). This will terminate all the running tasks and stop all queued tasks. 

###2.3 

###Execution 

This  doesn't return a result, you have no state or isDone function. You can however check if a thread is done by checking if future.get() == null. If this doesn't throw an exception. It means that the thread was successful 

###Submit 

This returns a Future object that will allow you to systematically cancel running threads and get the result that is returned on completion of the callable. 


##Task 3 

The first thing that I noticed when I was done was really weired. It appeared I got a different path from time to time with the Parallel Group Search with Executor. Some times it was working well and took the path it is supposed to do:
C:\Windows\System32\drivers\etc\hosts and got an execution times that could be even betterthen parallel without an executor. 

When it didn't work as it was supposed to it used this path: C:\Windows\WinSxS\amd64_microsoft-windows-w..ucture-other-minwin_31bf3856ad364e35_10.0.19041.1_none_eb30aafa046f78ad\hosts. 
This had a way slower execution time of 7935. This is the same file, but I have no idea why it would sometimes skip the system32 folder and go for WinSxS instead.
The fix for this was to have an if test to see if the thread was done. 

Serial was slowest all the time which came as no surprise. Just dont use this for file searching.

The most interesting thing to see is that with and without an executor are swapping on being the fastest method. If you just need to find a file it looks like you dont need to use an executor.
But the executor bring a lot more features and flexibility and this functionality can be useful. The automatic thread handling is also worth a mention. 







