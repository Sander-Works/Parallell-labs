<h1 align="center"> ITF23019 - Parallel and Distributed Programming </h1>
<h3 align="center"> Spring 2021 </h2>
<h3 align="center"> Lab 6: Dense Matrix Multiplication </h2>



In this lab, we will implement different matrix/vector multiplication algorithms.

## Submission Deadline:

You need to commit your codes and lab report to your GitHub repository **before 10:00 AM Wednesday 3rd March**.

## GitHub

If you have not config your `labs-yourusername` repository for upstream remote repository, please go back to the instruction from `lab2`.

Remember to commit and push your changes before starting this lab. Then, start the **Command Prompt** of GitHub Desktop and run the command:

```bash
> git pull upstream main
```

Then, **push** everything to your `labs-yourusername`.

## Exercise 1 (40 points):

Your task for this exercise is to implement a task that compute the multiplication of a matrix and a vector. The starter code of the serial and parallel versions is `matrix-vector-mul` project.

Remember to analyze the speedup of your implementation, and include the screenshot of the output to the report. 

## Exercise 2 (60 points):

Your task for this exercise is to implement a task that compute the multiplication of two matrices. The starter code of the serial and parallel versions is `matrix-matrix-mul` project.

Remember to analyze the speedup of your implementation, and include the screenshot of the output to the report. 

## Bonus Exercise (20 points):

Your task for this exercise is to implement a task that compute dot product of two vectors.  The serial version is computed as follow:

```java
public long multiply(int[] vector1, int[] vector2)
{
    int N = vector1.length;
    long result = 0;

    for(int i = 0; i < N; i++)
        result += vector1[i]*vector2[i];

    return result;
}
```

Create a project and name it `no.hiof.itf23019.vector-vector-mul` for this exercise.

Use the fork/join framework to implement the parallel version of this task. You may reuse the structure of the tasks in previous exercise. 

Remember to analyze the speedup of your implementation, and include the screenshot of the output to the report. 

## What To Submit

Complete the the exercises in this lab and put your code along with **lab6_report** (Markdown, TXT or PDF file) into the **lab6** directory of your repository. Commit and push your changes and remember to check the GitHub website to make sure all files have been submitted. 