<h1 align="center"> ITF23019 - Parallel and Distributed Programming </h1>
<h3 align="center"> Spring 2021 </h2>
<h3 align="center"> Lab 7: Sorting Algorithms </h2>




In this lab, we will implement different sorting algorithms introduced in the lecture with the libraries introduced in previous lab.

## Submission Deadline:

You need to commit your codes and lab report to your GitHub repository **before 10:00 AM Wednesday 10th March**.

## GitHub

If you have not config your `labs-yourusername` repository for upstream remote repository, please go back to the instruction from `lab2`.

Remember to commit and push your changes before starting this lab. Then, start the **Command Prompt** of GitHub Desktop and run the command:

```bash
> git pull upstream main
```

Then, **push** everything to your `labs-yourusername`.

## Exercise 1 (30 points):

Your task in this exercise is analyzing the serial and parallel version of bubble sort. The starter code is inside `bubble-sort` project. There are two implementations of the parallel bubble sort: with "strict barrier" and "fuzzy barrier". 

For the implementation with "strict barrier", we divide the original arrays into multiple chunks which are processed by the threads. The thread will be synchronized at the end of each phase with barrier.

For the implementation with "fuzzy barrier", in each phase, each thread will process the value at the right border of the chunk first (because the values at border may be used by the other chunks) and then the remaining values of the chunk.

Follow the instruction in corresponding java class to finish the implementation.

Remember to analyze and discuss the speedup of your implementation, and include the screenshot of the output to the report. 

## Exercise 2 (35 points)

Your task in this exercise is analyzing the serial and parallel version of merge sort. The starter code is inside `merge-sort` project. In this project, we use Java Fork/Join framework to implement the parallel merge sort algorithm. Accordingly, the original array will be recursively divided into smaller sub-arrays. Then, serial merge sort will be performed on each sub-arrays. Finally, the parent tasks again merge the sorted sub-arrays of the child tasks.

Follow the instruction in corresponding java class to finish the implementation.

Remember to analyze and discuss the speedup of your implementation, and include the screenshot of the output to the report. 

## Exercise 3 (35 points)

Your task in this exercise is analyzing the serial and parallel version of quick sort. The starter code is inside `quick-sort` project. In this project, we use Java Fork/Join framework to implement the parallel quick sort algorithm. Accordingly, the original array will be recursively partitioned (with the *pivot* at the right position already)  into smaller sub-arrays . Then, serial quick sort will be performed on each sub-arrays.

Follow the instruction in corresponding java class to finish the implementation.

Remember to analyze and discuss the speedup of your implementation, and include the screenshot of the output to the report. 

## What To Submit

Complete the the exercises in this lab and put your code along with **lab7_report** (Markdown, TXT or PDF file) into the **lab7** directory of your repository. Commit and push your changes and remember to check the GitHub website to make sure all files have been submitted. 
