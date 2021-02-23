<h1 align="center"> ITF23019 - Parallel and Distributed Programming </h1>
<h3 align="center"> Spring 2021 </h3>
<h3 align="center"> Lab 3: Java Fork/Join Framework </h3>
<h3 align="center"> Student: Sander Jelsness-Larsen Riis </h3>




### Exercise 1 (<span style="color:red">18</span>/20 points).
<blockquote>
In option 2, task 1 is getting forked and joined like in option 1. This differs from option 1 because compute() is getting called on task 2. Compute() is an abstract function from the interface RecursiveAction and from RecursiveTask. Compute() is where the task's logic is defined
</blockquote>

There is the noticeable difference that by calling `fork` or `invokeAll`, you will submit a task to **task queue** and waiting for **a thread in ForkJoinPool** to execute it. By calling `compute()`, there is nothing submited to the task queue and it will execute on the **current thread** as just a normal function.

### Exercise 2 (<span style="color:red">7</span>/10 points).
Because `get()` is a blocking method and it will wait until the task finished and return its value so there is no need to `join` here. `get` is almost the same as `join` but have a different way to handle exception.

### Exercise 3 (<span style="color:red">0</span>/10 points).
You misundertood the exercise.<br>
Please check the solution for detail. 

### Exercise 4 (<span style="color:red">30</span>/30 points).
Good answer.

### Exercise 5 (<span style="color:red">10</span>/30 points).
There are some issue with your implementation:

* In your main class, you use the same implementation on exercise 4 to measure both `parallelArraySum output` and `parallelArraySumRecursiveTask output` and this doesn't mean anything.<br>
* I can't find the way where you use `ArraySumRecursiveTask` to get the result.
<br>
* You should implement `RecursiveTask` with an explicit type ( in this case it should be `RecursiveTask<Integer>` rather than `RecursiveTask`). The same for your method `compute` should have explicit return type `Integer` rather than `Object`
* You didn't combine the result from `left` and `right` for the result.



### Exercise Bonus (<span style="color:red">0</span>/20 points).
No answer.


### Total result: (<span style="color:red">65</span>/120 points).