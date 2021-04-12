package no.hiof.itf23019.project.serial;

import java.io.*;
import java.io.FileNotFoundException;
import java.util.*;

public class FileReader {
    public ArrayList<String> words = new ArrayList<>();

    public static String filename = "Textfile";
    public void readFile() throws FileNotFoundException {

        // pass the path to the file as a parameter
        File file = new File( filename + ".txt"); //Change this to your location, you might want to change working directory for this 
        Scanner sc = new Scanner(file);
        sc.useDelimiter("");
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (sc.hasNext())
            words.add(sc.next().toUpperCase(Locale.ROOT));
        sc.close();
    }
    public ArrayList<String> getWords() {
        return words;
    }
}
