package no.hiof.itf23019.array_sum.parallel;


import java.util.concurrent.RecursiveTask;

public class ArraySumRecursiveTask extends RecursiveTask {

    /**
     *
     */
    private static final long serialVersionUID = 1621644740178701763L;

    private int[] input;
    private int startIndex, endIndex;
    private int sum;
    private int threshold = 100_000;

    public ArraySumRecursiveTask(int[] input, int startIndex, int endIndex) {
        this.input = input;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }

    protected Object compute() {

        //If the size of the task is smaller than threshold, compute the sum directly
        if (endIndex - startIndex <= threshold) {
            for (int i = startIndex; i < endIndex; i++) {
                this.sum += input[i];
            }
        }
        else {

            ArraySumRecursiveTask left = new ArraySumRecursiveTask(input, startIndex,(endIndex + startIndex) /2 ); //This is to get to get the first half of the array
            ArraySumRecursiveTask right = new ArraySumRecursiveTask(input, (endIndex + startIndex) / 2, endIndex); //This is to get the second half of the array

            left.fork();
            right.compute();
            left.join();
        }
        return sum;
    }

}