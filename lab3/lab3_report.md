<h1 align="center"> ITF23019 - Parallel and Distributed Programming </h1>
<h3 align="center"> Spring 2021 </h2>
<h3 align="center"> Lab 3 report: Java Fork/Join Framework </h2>


##Task 1

```java
//Option 1:
t1.fork();
t2.fork();
t1.join();
t2.join();

//Option 2:
t1.fork();
t2.compute();
t1.join();

//Option 3:
invokeAll(t1, t2);
```

 * Discuss any differences or similarities among these options.

###Option 1 
In this option both tasks are getting split up to be computed in smaller shards (small enough to be done asynchronously) and then the results are joined together at the end. This will allow using more then one thread on each task and therefore 
amplifying the work speed. 

###Option 2
In option 2, task 1 is getting forked and joined like in option 1. This differs from option 1 because compute() is getting called on task 2. Compute()
is an abstract function from the interface RecursiveAction and from RecursiveTask<v>. Compute() is where the task's logic is defined 

###Option 3
In option 2 the inokeAll() is called, this function takes tasks that have been divided up until they are smaller then the threshold and submits them ti a "common pool",
then it returns a list of Future. 


##Task 2 

###Option 1
In this first case, invokeAll function is called. This function takes either two tasks, var args or a collection as parameters. 
It forks these parameters and returns the as a collection of Future objects, in the order they were produced. This is why you dont need to call the join() function. 

###Option 2 
In the second case we are taking the forked result and adding them together as the var result. This is in practise what the join () function does.

##Task 3 
````java
left.fork();
right.compute();
left.join();

sum = left.getSum() + right.getSum();
````

If I understood the question right this is what you ment. I took this code from the arraySumTask. Here i forked one task and computed the other.
The result are simply added together by using the getSum() function. which returns the result of that task. It does this as many times as it needs to get the array under the threshold limit.  

##Task 4 
The code can be found in ArraySumTask in the folder parallel in array-sum module.

The compute() function is first checking if the array is to big. If it is to big we are making a right, and a left (basically splitting the array in the middle).
We will fork the left side and compute() the right side. this will be called recursivly until the array is the right smaller than the threshold. 
At the end of compute() we take the sum of both the right and left part of the array and add them together.
##Task 5 
The code can be found in ArraySumRecursiveTask in the parallel folder inside the array-sum module.
This code is more or less the same as task 4. The recursiveTask extension has built in return. That means we don't have to implement a function to return the sum of the split array.
This seems to be faster from what I have seen when i run the code. 





