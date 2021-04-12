package no.hiof.itf23019.project.serial;

import java.util.ArrayList;

public class SerialArraySearch {

    public int sequentialArraySearch(ArrayList<String> array) throws InterruptedException {

        System.out.println("Sequential");
        String string = "PAIN";
        int numOfOccurrences = 0;

        for (String s : array) {
            if (string.equals(s)) {
                numOfOccurrences++;
            }
        }
        return numOfOccurrences;
    }
}
