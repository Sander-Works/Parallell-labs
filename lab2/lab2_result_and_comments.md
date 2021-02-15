<h1 align="center"> ITF23019 - Parallel and Distributed Programming </h1>
<h3 align="center"> Spring 2021 </h3>
<h3 align="center"> Lab 2: Thread Executor </h3>
<h3 align="center"> Student: Sander Jelsness-Larsen Riis </h3>




### Exercise 1 (<span style="color:red">20</span>/20 points).
Good answer.
### Exercise 2 (<span style="color:red">30</span>/30 points).
Good answer.
### Exercise 3 (<span style="color:red">50</span>/50 points).
Good answer.<br><br>

<blockquote>
When it didn't work as it was supposed to it used this path: C:\Windows\WinSxS\amd64_microsoft-windows-w..ucture-other-minwin_31bf3856ad364e35_10.0.19041.1_none_eb30aafa046f78ad\hosts. This had a way slower execution time of 7935. This is the same file, but I have no idea why it would sometimes skip the system32 folder and go for WinSxS instead</blockquote>

I ran your project multiple times but I didn't find anything like this.
You may run into this problem if you try to use `booleanFuture.get()` without checking `booleanFuture.isDone()`. <br>

Since `booleanFuture.get()` is a blocking method, and there is no guarantee that the first thread will search on the first directory, it means that sometime you will search in folder `C:\Windows\WinSxS` before folder `C:\Windows\System32`. Both folders contain a file named `host` so you got different result at different time.

This can be solved by checking `booleanFuture.isDone()`. This is not a blocking method so whenever a thread/task finished its job, we can get the result right away.

### Exercise Bonus (<span style="color:red">0</span>/30 points).
No answer.

### Total result: (<span style="color:red">100</span>/130 points).