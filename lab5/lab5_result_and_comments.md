<h1 align="center"> ITF23019 - Parallel and Distributed Programming </h1>
<h3 align="center"> Spring 2021 </h2>
<h3 align="center"> Lab 5: Thread Synchronization 2 </h3>
<h3 align="center"> Student: Sander Jelsness-Larsen Riis </h3>





### Exercise 1 (<span style="color:red">40</span>/40 points).
Good answer.

### Exercise 2 (<span style="color:red">60</span>/60 points).
Good answer.

### Exercise Bonus (<span style="color:red">7</span>/20 points).
<blockquote> But since the code from the task didn't even use the parameter (which I found required) and it wasn't any TODO over the implementation I didn't want ot do it like that 
</blockquote>

Yes, I find that `An Lam` give you an sample code for `awaitAdvance()` without any parameter. I think he did it on purpose because if he gives you a completed solution in his sample code then there's nothing for you to do.

There are 2 ways to implement this exercise.

**First solution:** is using the same way you did in exercise 2. In this case we need to define 2 global static variable to store phase1 and phase2 and use them to synchronize with function `awaitAdvance()`.

```java
	private static int phase1 = 0;
	private static int phase2 = 0;
```
```java
			//TODO: Add phaser methods for the first stage
            Thread t0 = new Thread(() -> {
                for(int i = 0; i < n; i ++) {					
                    doWork(10);
                    phase1 = ph0.arrive();
                }

            });

            //TODO: Add phaser methods for the second stage
            Thread t1 = new Thread(() -> {
                for(int i = 0; i < n; i ++) {
                    ph0.awaitAdvance(phase1);
                    doWork(10);
                    phase2 = ph1.arrive();
                }		
            });

            //TODO: Add phaser methods for the third stage
            Thread t2 = new Thread(() -> {
                for(int i = 0; i < n; i ++) {
                    ph1.awaitAdvance(phase2);
                    doWork(10);
                }				
            });
```

**Second solution:** is using counter `i` in the loop to synchronized the phase because Phaser start from 0 (the same with counter `i`).
```java
			//TODO: Add phaser methods for the first stage
			Thread t0 = new Thread(() -> {
				for(int i = 0; i < n; i ++) {					
					doWork(10);
					ph0.arrive();
				}				
			});
			
			//TODO: Add phaser methods for the second stage
			Thread t1 = new Thread(() -> {
				for(int i = 0; i < n; i ++) {
					ph0.awaitAdvance(i);
					doWork(10);
					ph1.arrive();
				}
				
			});
			
			//TODO: Add phaser methods for the third stage
			Thread t2 = new Thread(() -> {
				for(int i = 0; i < n; i ++) {
					ph1.awaitAdvance(i);
					doWork(10);
				}				
			});
```

### Total result: (<span style="color:red">107</span>/120 points).