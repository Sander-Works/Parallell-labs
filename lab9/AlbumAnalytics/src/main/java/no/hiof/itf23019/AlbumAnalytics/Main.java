package no.hiof.itf23019.AlbumAnalytics;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {



	public static void main(String[] args) {
		final int REPEATS = 5;
		final int N_ALBUMS = 1_000_000;
		List<Album> albums = generateAlbum(N_ALBUMS);
		long start, end;
		
		AlbumAnalytics analytics = new AlbumAnalytics();
		
		//Printing album name
		analytics.printAlbumNamePar(albums);
		
		//Printing track name
		//analytics.printTrackNamePar(albums);
		
		
		for(int i = 1; i <= REPEATS; i++)
		{
			System.out.println("RUN# " + i);
			
			//Running getAlbumOfYear2011and2012 methods
			System.out.println("getAlbumOfYear2011and2012");
			
			start = System.currentTimeMillis();
			analytics.getAlbumOfYear2011and2012(albums);	
			end = System.currentTimeMillis();
			System.out.println("Serial Execution Time : " + (end - start));
			
			start = System.currentTimeMillis();
			analytics.getAlbumOfYear2011and2012Par(albums);			
			end = System.currentTimeMillis();
			System.out.println("Parallel Execution Time : " + (end - start));
			
			
			//Running sumOfYear method
			System.out.println("sumOfYear");
			start = System.currentTimeMillis();
			analytics.sumOfYear(albums);			
			end = System.currentTimeMillis();
			System.out.println("Serial Execution Time : " + (end - start));
			
			start = System.currentTimeMillis();
			analytics.sumOfYearPar(albums);			
			end = System.currentTimeMillis();
			System.out.println("Parallel Execution Time : " + (end - start));
			
			//TODO: Compute speed up for all of the methods in AlbumAnalytics except the first two printing methods.
			
			System.out.println();
			
		}
		
		
	}

	public static List<Album> generateAlbum(int n) {

		System.out.println("Generating albums ...");
		
		List<String> words = new ArrayList<String>();
		try (Stream<String> lines = Files.lines(Paths.get("data/wordlist.txt"))) {
			words = lines.collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		int wordsNum = words.size();

		List<Album> albums = new ArrayList<Album>();
		Random random = new Random(20200315);
		int year;
		String name, trackName;
		List<Track> tracks;
		int nameSize, trackNameSize;

		for (int i = 0; i < n; i++) {
			
			year = random.nextInt() % (2022 - 1950) + 1950;
			nameSize = random.nextInt(10) + 1;
			name = "Album:";
			for(int k =  1; k <= nameSize; k++)
				name = name + " " +  words.get(random.nextInt(wordsNum));
			
			tracks = new ArrayList<Track>();
			
			int numberOfTrack = random.nextInt(20) + 1;
			for (int j = 0; j < numberOfTrack; j++) {
				trackName = "Track:";
				trackNameSize = random.nextInt(10) + 1;
				for(int k =  1; k <= trackNameSize; k++)
					trackName = trackName + " " + words.get(random.nextInt(wordsNum));
				
				Track track = new Track(trackName, random.nextInt(5));
				
				tracks.add(track);
			}
			
			Album album = new Album(name, year, tracks);
			albums.add(album);

		}

		return albums;
	}

}
