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
		final int N_ALBUMS = 2_000_000;
		List<Album> albums = generateAlbum(N_ALBUMS);
		long start, end;
		
		AlbumAnalytics analytics = new AlbumAnalytics();
		
		//Printing album name
		//analytics.printAlbumNamePar(albums);
		
		//Printing track name
		//analytics.printTrackNamePar(albums);

		long totalSer1=0,totalSer2=0,totalSer3=0,totalSer4=0,totalSer5=0,totalSer6=0,totalSer7=0;
		long totalPar1=0,totalPar2=0,totalPar3=0,totalPar4=0,totalPar5=0,totalPar6=0,totalPar7=0;

		for(int i = 1; i <= REPEATS; i++) {
			System.out.println("RUN# " + i);

			//Running getAlbumOfYear2011and2012 methods
			System.out.println("getAlbumOfYear2011and2012");
			start = System.currentTimeMillis();
			analytics.getAlbumOfYear2011and2012(albums);
			end = System.currentTimeMillis();
			System.out.println("Serial Execution Time : " + (end - start));
			totalSer1 += (end - start);

			start = System.currentTimeMillis();
			analytics.getAlbumOfYear2011and2012Par(albums);
			end = System.currentTimeMillis();
			System.out.println("Parallel Execution Time : " + (end - start));
			totalPar1 += (end - start);

			//Running sumOfYear method
			System.out.println("sumOfYear");
			start = System.currentTimeMillis();
			analytics.sumOfYear(albums);
			end = System.currentTimeMillis();
			System.out.println("Serial Execution Time : " + (end - start));
			totalSer2 += (end - start);


			start = System.currentTimeMillis();
			analytics.sumOfYearPar(albums);
			end = System.currentTimeMillis();
			System.out.println("Parallel Execution Time : " + (end - start));
			totalPar2 += (end - start);
			//TODO: Compute speed up for all of the methods in AlbumAnalytics except the first two printing methods.

			//Counts the total number of tracks of the albums released in 2011 m
			System.out.println("sumOfYear - 2011");
			start = System.currentTimeMillis();
			analytics.countNumberOfTracksOfAlbumsOfYear2011(albums);
			end = System.currentTimeMillis();
			System.out.println("Serial Execution Time : " + (end - start));
			totalSer3 += (end - start);


			start = System.currentTimeMillis();
			analytics.countNumberOfTracksOfAlbumsOfYear2011Par(albums);
			end = System.currentTimeMillis();
			System.out.println("Parallel Execution Time : " + (end - start));
			totalPar3 += (end - start);

			//Counts the number of tracks of the albums grouped by released year
			System.out.println("Counts grouped tracks");
			start = System.currentTimeMillis();
			analytics.countNumberOfTrackByYear(albums);
			end = System.currentTimeMillis();
			System.out.println("Serial Execution Time : " + (end - start));
			totalSer4 += (end - start);


			start = System.currentTimeMillis();
			analytics.countNumberOfTrackByYearPar(albums);
			end = System.currentTimeMillis();
			System.out.println("Parallel Execution Time : " + (end - start));
			totalPar4 += (end - start);

			//Group the album by the released year
			System.out.println("Group the album by the released year");
			start = System.currentTimeMillis();
			analytics.getAlbumNameByYear(albums);
			end = System.currentTimeMillis();
			System.out.println("Serial Execution Time : " + (end - start));
			totalSer5 += (end - start);


			start = System.currentTimeMillis();
			analytics.getAlbumNameByYearPar(albums);
			end = System.currentTimeMillis();
			System.out.println("Parallel Execution Time : " + (end - start));
			totalPar5 += (end - start);

			//Get the albums with rating values greater or equal 4
			System.out.println("Get the albums with rating values greater or equal 4");
			start = System.currentTimeMillis();
			analytics.getFavoriteAlbum(albums);
			end = System.currentTimeMillis();
			System.out.println("Serial Execution Time : " + (end - start));
			totalSer6 += (end - start);

			start = System.currentTimeMillis();
			analytics.getFavoriteAlbumPar(albums);
			end = System.currentTimeMillis();
			System.out.println("Parallel Execution Time : " + (end - start));
			totalPar6 += (end - start);

			//Group the album by the released year
			System.out.println("Group the tracks by released year");
			start = System.currentTimeMillis();
			analytics.getTracksByYear(albums);
			end = System.currentTimeMillis();
			System.out.println("Serial Execution Time : " + (end - start));
			totalSer7 += (end - start);


			start = System.currentTimeMillis();
			analytics.getTracksByYearPar(albums);
			end = System.currentTimeMillis();
			System.out.println("Parallel Execution Time : " + (end - start));
			totalPar7 += (end - start);

			System.out.println();


		}
		System.out.println("getAlbumOfYear2011and2012 parallel speedup: " + (totalSer1 / totalPar1));
		System.out.println("sumOfYear parallel speedup: " + (totalSer2 / totalPar2));
		System.out.println("sumOfYear - 2011 parallel speedup: " + (totalSer3 / totalPar3));
		System.out.println("Counts grouped tracks parallel speedup: " + (totalSer4 / totalPar4));
		System.out.println("Group the album by the released year parallel speedup: " + (totalSer5 / totalPar5));
		System.out.println("Get the albums with rating values greater or equal 4 speedup: " + (totalSer6 / totalPar6));
		System.out.println("Group the tracks by released year parallel speedup: " + (totalSer7 / totalPar7));
	}

	public static List<Album> generateAlbum(int n) {

		System.out.println("Generating albums ...");
		//data/wordlist.txt"
		List<String> words = new ArrayList<String>();
		try (Stream<String> lines = Files.lines(Paths.get("F:\\Skole\\Parallell prog\\labs-Sander-Works\\lab9\\AlbumAnalytics\\data\\wordlist.txt"))) {
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
