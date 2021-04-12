package no.hiof.itf23019.project.parallel;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ForkJoinPool;

public class ParallelArraySearch {

    public int parallelArraySearch(ArrayList<String> array) {


        System.out.println("Parallel");
        Task task = new Task(array, 0, array.size());
        ForkJoinPool pool = new ForkJoinPool(6); //Swap this number for the amount of cores you want (Limited amount by your processor)
        
        return pool.invoke(task);
    }
}
