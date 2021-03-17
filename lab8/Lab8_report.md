<h1 align="center"> ITF23019 - Parallel and Distributed Programming </h1>
<h3 align="center"> Report 8 </h2>
<h3 align="center"> Lab 8: Graph and Tree Algorithms </h2>



###Task 1 
I was wondering why the parallell implementation didn't seem to yield any results I did some research and found a paper online that refered to this problem.


In the Satin statistics of the full-traversal on one node with 16 cores (Table 5.3), there is noobservable difference between Search and Search IA.
This can be attributed to the lack of abortsbecause in a full traversal these will not be executed.  The overhead the inlets have is negligible.
Both have more than enough spawns, meaning there are enough threats that can be stolen. 
Thisis attempted not that many times,  and even fewer attempts succeed.  
This leads to nearly noparallel work.The two versions with Shared Objects differ not that much from each other.  
Both have alot more steal attempts than the other two versions, but relatively even fewer successful steals.
They both use a lot more data and send some of it using the Shared Objects.  This creates morework, with no advantages.
When looking at the Satin statistics of the random searches on one node with 16 cores (Table5.4), all numbers have the be divided by 64, when comparing them to the previous table.
Theseare the totals for 64 searches, not the averages.

It would seem that each job is too small to effectively give over, because the visiting of onenode is nothing more than some memory look-ups. 
One processor can do it so quickly that theparallel actions are not needed and even bad for performance. 
From these observations it lookslike the only thing these applications create is parallel overhead,
and nearly no parallel work at all.

source(https://esc.fnwi.uva.nl/thesis/centraal/files/f515813033.pdf)

Parallell could be used on graphs and trees however, but it only yield good results when you want to traverse multiple graphs and trees at the same time. 

)
!["Condition output"](Images/Task1.png)


###Task 2 
To add to what i wrote in the first task...

The speedup is not too impressive, this is partly due to ConcurrentSkipListSet. This is synchronized (which is important), but time expensive.
This is synchronized because it's preventing more then one thread to access any given object at the same time. This thread safety is executed by the thread-safe class: ConcurrentSkipListMap.
The other thing I would like to point out is that this is only a tree traversing. Traversing of a tree(with no CPU-intensive work, like string comparison) is not very CPU heavy.

These are rather small things, but could also yield som small decreases in speed. 

###DFS
!["Condition output"](Images/DfsMain.png)
###BFS
!["Condition output"](Images/BfsMain.png)

