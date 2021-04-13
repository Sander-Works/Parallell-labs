<h1 align="center"> ITF23019 - Parallel and Distributed Programming </h1>
<h3 align="center"> Report </h2>
<h3 align="center"> Lab 11: MPI </h2>

###Task1
!["Condition output"](Images/Task1.png)

###Task2
!["Condition output"](Images/Task2.png)


###Task3
This algorithm is a matrix multiplier. it takes in a number as type args later parses this number to an int (n).
n is both the row count and column count of the matrix.

The algorithm starts with a check to validate that your input != 0.
It does this by saying if your input is 0.
I will check if the program is about to start and if it is about to start I will print an error and break. 
This is done by using the me var, which returns the rank of a process in a communicator. Each process inside a communicator is assigned an incremental rank starting from zero.  
(source: https://mpitutorial.com/tutorials/mpi-hello-world/)  
Now if your input has passed the check, the algorithm will create the matrix and print some values about the matrix's (as long as me still is 0).   
If you had selected debug mode in the top of the code, you would now print all the matrix's  
When the program is finished with printing the matrix's or skips it (depending if you have debug on).
It will check if me is back at 0 and start a timer. It then divides the work on the threads and establishes a barrier.
When this is done, each thread is saving a copy of their assign rows of matrixA and thread 0 (process0) broadcasts matrixB to the other threads.
Then the computing starts. When they are done the send their results back to thread 0, and it combines the results in matrixC.
The algorithm wraps up with calculating the worktime and finishes. 


!["Condition output"](Images/Task3-2Cores.png)
!["Condition output"](Images/Task3-4Cores.png)
!["Condition output"](Images/Task3-6Cores.png)
!["Condition output"](Images/Task3-8Cores.png)

From the look of these results it seems like this problem is best solved with 6 cores (12 threads).
The Program doesn't get computed faster after 6 cores and I would guess that the reason why it ran slower with 8 cores, is just up to the STD (standard deviation) that comes with running a program. 
The amount of cores that is needed to hit the "softcap" will probably change depending on your cpu (more precise, your CPU's GHz). This was ran on an AMD Ryzen 7 3700x with a speed of 4GHz. 