<h1 align="center"> ITF23019 - Parallel and Distributed Programming </h1>
<h3 align="center"> Spring 2021 </h2>
<h3 align="center"> Lab 8: Graph and Tree Algorithms </h2>




In this lab, we will implement Dijkstra algorithm on a graph and searching algorithms on a tree.

## Submission Deadline:

You need to commit your codes and lab report to your GitHub repository **before 10:00 AM Wednesday 17th March**.

## GitHub

If you have not config your `labs-yourusername` repository for upstream remote repository, please go back to the instruction from `lab2`.

Remember to commit and push your changes before starting this lab. Then, start the **Command Prompt** of GitHub Desktop and run the command:

```bash
> git pull upstream main
```

Then, **push** everything to your `labs-yourusername`.

## Exercise 1 (30 points)

Your task for this exercise is to evaluate performance of the  parallel version of Dijkstra algorithm. This parallel version is implemented with parallel stream (will be introduce in the next lab). The starter code of this exercise is inside `dijkstra` project. You also need to import the project `common-structure` that implements the data structure of graph and tree. 

In the serial version of the algorithm, we use java stream library to process the list of edges and nodes of the graph. For parallel version, all you need to do is replace the `stream()` method with `parallelStream()`. Then, the elements of the list will be processed simultaneously. 

Follow the instruction in corresponding java classes to finish the implementation.

Remember to analyze and discuss the speedup of your implementation, and include the screenshot of the output to the report. 

## Exercise 2 (70 points)

In this exercise, we will implement Depth First Search and Breadth First Search algorithms on a tree. The starter code is inside `tree-search` project.

For parallel version of these two algorithms, the original tree will be split into multiple sub-trees which roots are the (direct) children of the original root. Then, either serial DFS or serial BFS will be performed on these sub-trees. To implement this algorithm, we use `Executors` with `Runnable` tasks.

Follow the instruction in corresponding java classes to finish the implementation.

Remember to analyze and discuss the speedup of your implementation, and include the screenshot of the output to the report. 

## What To Submit

Complete the the exercises in this lab and put your code along with **lab8_report** (Markdown, TXT or PDF file) into the **lab8** directory of your repository. Commit and push your changes and remember to check the GitHub website to make sure all files have been submitted. 



