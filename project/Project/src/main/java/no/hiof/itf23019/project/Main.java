package no.hiof.itf23019.project;

import no.hiof.itf23019.project.parallel.ParallelArraySearch;
import no.hiof.itf23019.project.serial.FileReader;
import no.hiof.itf23019.project.serial.SerialArraySearch;

import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws FileNotFoundException, InterruptedException {


        int RUNS = 10;
        FileReader fileReader = new FileReader();
        fileReader.readFile();

        ArrayList<String> words = fileReader.getWords();

        SerialArraySearch serialSearch = new SerialArraySearch();
        ParallelArraySearch parallelSearch = new ParallelArraySearch();

        long startTime, endTime;
        double totalSeq = 0, totalParallel=0;

        for(int i = 1; i <= RUNS; i++) {
            System.out.println("\nRun #" + i );

            startTime = System.currentTimeMillis();
            int seqResult = serialSearch.sequentialArraySearch(words);
            endTime = System.currentTimeMillis();
            System.out.println("Word found " + seqResult + " in sequentiualsearch");
            System.out.println("sequentialArray took " + (endTime - startTime) + " milliseconds.");
            totalSeq += (endTime - startTime);

            startTime = System.currentTimeMillis();
            int parallelResult = parallelSearch.parallelArraySearch(words);
            endTime = System.currentTimeMillis();
            System.out.println("Word found " + parallelResult + " in parallellsearch");
            System.out.println("parallelArray took " + (endTime - startTime) + " milliseconds.");
            totalParallel += (endTime - startTime);


        }
        System.out.println("\n ----------------------------\n");
        System.out.println("Parallel speedup: " + totalSeq/totalParallel);
    }


}
