<h1 align="center"> ITF23019 - Parallel and Distributed Programming </h1>
<h3 align="center"> Spring 2021 </h2>
<h3 align="center"> Lab 4: Thread Synchronization 1 </h3>
<h3 align="center"> Student: Sander Jelsness-Larsen Riis </h3>


### Notice:
All images included in your report didn't work properly due to **wrong name**. Please check your report before submitting.


### Exercise 1 (<span style="color:red">10</span>/10 points).
Good answer.

### Exercise 2 (<span style="color:red">20</span>/20 points).
Good answer.

### Exercise 3 (<span style="color:red">28</span>/40 points).
<blockquote>This is one I need some explanation for! I have written the code in the given class and checked with some of my friends if my code is right. Even if I run identical code as they are (The code that I deliver is what I have written myself) I do get inconsistent results. Some times I will get the right "welcome to Østfold college" on all three lines. other times I will get 3x "welcome to" in a row followed by 3x "østfold college" and the last outcome is that I get "welcome to østfold college", and then i get 2x "welcome to" followed 2x "østfold college".</blockquote>

Your implementation **doesn't define the order** of printing, it only make sure that only one thread can access `doTaskA` / `doTaskB` at a time. For this reason, you get inconsistent results. Check out the solution for detail.


* **Semaphore (3/15):** wrong implementation.
* **Condition (15/15):** good answer.
* **Monitor with condition (10/10):** good answer.

### Exercise 4 (<span style="color:red">30</span>/30 points).
<blockquote>
This is the result. The problem with this is that main never runs VideoConferenceBarrier. This means that the run method is that class never is started.
Even if it was started the program would not have been working properly. Because its no wait() function implemented in the run() function. This means that the program will just sout everything in the run function, then do the rest of the functions.
</blockquote>

If you initialize the barrier with `this` keyword which refer to current object, the `run()` method inside  would be executed **after all thread arrive** and you don't have to create and start the thread `threadConference` in Main class. You also don't have to create new class `BarrierAction` 
```java
this.barrier = new CyclicBarrier(number, this);
```
I can see a small mistake that the line `VideoConference: Initialization: 10 participants.` should be on top of everything .It can be fixed easily by moving only that line to the end of constructor but this is not the point of this exercise. Your answer is also accepted.

<blockquote>
What I didn't like with this is that "waiting for 0 participants" comes after "all the participants have come". Is it an easy way to fix this? (works some times, my guess its that some threads are faster then others)</blockquote>

Yes I agree that it could happen because the print method "waiting for 0 participants" is called after `controller.countDown()`, so there is still a chance that `threadConference` may be awaken before print method. To totally get rid of this problem, I think we can move print method  to be above `controller.countDown()` method and use `controller.getCount()-1` instead of `controller.getCount()`.

```java
System.out.printf("VideoConference: Waiting for %d participants.\n", controller.getCount()-1);
controller.countDown();			
```

### Exercise Bonus (<span style="color:red">0</span>/15 points).
No answer.

### Total result: (<span style="color:red">88</span>/115 points).
