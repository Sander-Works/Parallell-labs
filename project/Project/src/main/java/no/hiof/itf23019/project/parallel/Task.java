package no.hiof.itf23019.project.parallel;

import java.util.ArrayList;
import java.util.concurrent.RecursiveTask;

public class Task extends RecursiveTask<Integer> {

    private final ArrayList<String> words;
    private final int startIndex;
    private final int endIndex;
    public int numOfOccurrences = 0;

    public Task(ArrayList<String> words, int startIndex, int endIndex) {
        this.words = words;
        this.startIndex = startIndex;
        this.endIndex = endIndex ;
    }

    protected Integer compute() {

        //If the size of the task is smaller than threshold, compute the sum directly
        int threshold = 100_000;
        if (endIndex - startIndex <= threshold) {
            for (int i = startIndex; i < endIndex; i++) {
                String string = "PAIN";
                if(string.equals(words.get(i)))
                    numOfOccurrences++;
            }
        }
        else {

            int middleIndex = (startIndex + endIndex) / 2;

            Task left = new Task(words, startIndex,middleIndex); //This is to get to get the first half of the array
            Task right = new Task(words, middleIndex, endIndex); //This is to get the second half of the array

            invokeAll(left,right);

            numOfOccurrences = left.getNumOfOccurrences() + right.getNumOfOccurrences();
        }
        return numOfOccurrences;
    }
    public int getNumOfOccurrences() {
        return numOfOccurrences;
    }
}
