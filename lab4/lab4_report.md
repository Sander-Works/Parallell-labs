<h1 align="center"> ITF23019 - Parallel and Distributed Programming </h1>
<h3 align="center"> REPORT </h2>
<h3 align="center"> Lab 4: Thread Synchronization 1 </h2>


##Task 1



### Safe  Parking Simulator <br />
Parking Simulator <br />
numberSensors=32 <br />
Number of cars: 0 <br />
Number of motorcycles: 0 <br />
Closing accounting <br />
The total ammount is : 640 <br />



### Unsafe Parking Simulator <br />
numberSensors=32 <br />
Number of cars: -11 <br />
Number of motorcycles: -2 <br />
Closing accounting <br />
The total ammount is : 487 <br />

###Explanation 

The safe method implements the keyword synchronized. "This requires you to pass the object reference as a parameter".
Its is also important to synchronize any attribute that is used by more then one thread!
Now that we know how to implement it, what does it do? 
This synchronization is blocking out other threads to use the object, if it is being used by another thread. If the element is in use, the other threads will have to wait until the current working thread is finished with its task. 
This will keep the data consistency in check. 

The unsafe does not implement the keyword synchronized. This means that more then 1 thread can have access to an element that is already in use and thus
in this example the counting will be off. This will ruin the consistency of the data and will give you a wrong result. 

From the result from the console we can se that the safe version got no errors while counting the cars. 
While the unsafe version got totally wrong results. 

##Task 2

!["Condition output"](Images/Task2.png)


##Task 3

###Condition
!["Condition output"](Images/conditionOutput.png)

###Monitor

!["Monitor output"](Images/monitorOutput.png)


###Semaphore 

This is one I need some explanation for! I have written the code in the given class and checked with some of my friends if my code is right.
Even if I run identical code as they are (The code that I deliver is what I have written myself) I do get inconsistent results. Some times I will get the right "welcome to Østfold college" on all three lines. 
other times I will get 3x "welcome to" in a row followed by 3x "østfold college" and the last outcome is that I get "welcome to østfold college", and then i get 2x "welcome to" followed 2x "østfold college". 

!["Semaphore output "](Images/semaphoreOutput.png)


##Task 4

### Barrier

This task was really bad. I have done what is asked, but the program is flawed from the start. 


!["Semaphore output "](Images/Task4Barrier.png)

This is the result. The problem with this is that main never runs VideoConferenceBarrier. This means that the run method is that class never is started. Even if it was started the program would not have been working properly. 
Because its no wait() function implemented in the run() function. This means that the program will just sout everything in the run function, then do the rest of the functions. I assume that we were going to make the class barrierAction.
I did some work to make it a little better, its still lacking the sync on the "waiting for participants", but its atleast doing what it's supposed to.

!["Semaphore output "](Images/Task4Barrier2.png)


### CountDownLatch 

What I didn't like with this is that "waiting for 0 participants" comes after "all the participants have come". Is it an easy way to fix this? (works some times, my guess its that some threads are faster then others)

!["Semaphore output "](Images/Task4CDL.png)


