package no.hiof.itf23019.AlbumAnalytics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author nal
 *
 */
public class AlbumAnalytics {

	
	/**
	 * Printing all the name of the albums sequentially
	 * @param albums
	 */
	public void printAlbumName(List<Album> albums) {
		for (Album a : albums)
			System.out.println(a.getName());
	}

	/**
	 * Printing all the name of the albums with parallel stream
	 * @param albums
	 */
	public void printAlbumNamePar(List<Album> albums) {
		//TODO: Use forEach
		
	}

	
	/**
	 * Printing the name of all tracks of the albums
	 * @param albums
	 */
	public void printTrackName(List<Album> albums) {
		for (Album a : albums)
			for (Track t : a.getTracks())
				System.out.println(t.getName());
	}

	/**
	 *  Printing the name of all tracks of the albums with parallel stream
	 * @param albums
	 */
	public void printTrackNamePar(List<Album> albums) {
		//TODO: Use forEach twice
		
	}

	/**
	 * Get the albums released in 2011 and 2012
	 * @param albums
	 * @return list of the name of the selected albums
	 */
	public List<String> getAlbumOfYear2011and2012(List<Album> albums) {
		List<String> ret = new ArrayList<String>();
		for (Album a : albums)
			if (a.getYear() == 2011 || a.getYear() == 2012)
				ret.add(a.getName());

		return ret;
	}

	/**
	 * Get the albums released in 2011 and 2012 with parllel stream
	 * @param albums
	 * @return list of the name of the selected albums
	 */
	public List<String> getAlbumOfYear2011and2012Par(List<Album> albums) {
		// TODO: Use filter, map, collect with Collectors.toList()
		return null;
	}

	/**
	 * Calculates the sum of the released year of the albums
	 * @param albums
	 * @return the sum
	 */
	public int sumOfYear(List<Album> albums) {
		int ret = 0;
		for (Album a : albums)
			ret = ret + a.getYear();

		return ret;
	}

	/**
	 * Calculates the sum of the released year of the albums with parallel stream
	 * @param albums
	 * @return the sum
	 */
	public int sumOfYearPar(List<Album> albums) {
		// TODO: Use mapToInt, sum
		return -1;
	}

	/**
	 * Counts the total number of tracks of the albums released in 2011
	 * @param albums
	 * @return the total number of tracks
	 */
	public int countNumberOfTracksOfAlbumsOfYear2011(List<Album> albums) {
		int count = 0;
		for (Album a : albums) {
			if (a.getYear() == 2011)
				count += a.getTracks().size();

		}
		return count;
	}

	/**
	 * Counts the total number of tracks of the albums released in 2011 with parallel stream
	 * @param albums
	 * @return the total number of tracks
	 */
	public int countNumberOfTracksOfAlbumsOfYear2011Par(List<Album> albums) {
		// TODO: use filter, mapToInt, sum
		return -1;
	}

	/**
	 * Counts the number of tracks of the albums grouped by released year
	 * @param albums
	 * @return a Map with the key is the released year and value is the total number of tracks of the albums released in that year
	 */
	public Map<Integer, Integer> countNumberOfTrackByYear(List<Album> albums) {
		Map<Integer, Integer> ret = new TreeMap<Integer, Integer>();
		for (Album a : albums) {
			int count = 0;
			if (ret.containsKey(a.getYear()))
				count = ret.get(a.getYear());
			count = count + a.getTracks().size();
			ret.put(a.getYear(), count);
		}

		return ret;
	}

	/**
	 * Counts the number of tracks of the albums grouped by released year with parallel stream
	 * @param albums
	 * @return a Map with the key is the released year and value is the total number of tracks of the albums released in that year
	 */
	public Map<Integer, Integer> countNumberOfTrackByYearPar(List<Album> albums) {
		// TODO: use collect with Collectors.groupingBy, Collectors.reducing
		return null;
	}

	/**
	 * Group the album by the released year
	 * @param albums
	 * @return a Map with the key is the released year and the value is the list of the album names released in that year
	 */
	public Map<Integer, List<String>> getAlbumNameByYear(List<Album> albums) {

		Map<Integer, List<String>> albumMap = new TreeMap<Integer, List<String>>();

		for (Album a : albums) {
			List<String> albumList = new ArrayList<String>();
			if (albumMap.containsKey(a.getYear()))
				albumList = albumMap.get(a.getYear());
			albumList.add(a.getName());
			albumMap.put(a.getYear(), albumList);
		}
		
		return albumMap;
	}

	/**
	 * Group the album by the released year with parallel stream
	 * @param albums
	 * @return a Map with the key is the released year and the value is the list of the album names released in that year
	 */
	public Map<Integer, List<String>> getAlbumNameByYearPar(List<Album> albums) {
		// TODO: Use collect with Collectors.groupingBy, Collectors.mapping, Collectors.toList
		return null;

	}

	/**
	 * Get the albums with rating values greater or equal 4
	 * @param albums
	 * @return list of the albums
	 */
	public List<Album> getFavoriteAlbum(List<Album> albums) {
		List<Album> favs = new ArrayList<>();
		for (Album a : albums) {
			boolean hasFavorite = false;
			for (Track t : a.getTracks()) {
				if (t.getRating() >= 4) {
					hasFavorite = true;
					break;
				}
			}
			if (hasFavorite)
				favs.add(a);
		}
		Collections.sort(favs, new Comparator<Album>() {
			public int compare(Album a1, Album a2) {
				return a1.getName().compareTo(a2.getName());
			}
		});

		return favs;

	}

	/**
	 * Get the albums with rating values greater or equal 4 with parallel stream
	 * @param albums
	 * @return list of the albums
	 */
	public List<Album> getFavoriteAlbumPar(List<Album> albums) {
		// TODO: Use filter with anyMatch, sorted, collect with Collectors.toList
		return null;
	}

	/**
	 * Group the tracks by released year
	 * @param albums
	 * @return a Map with key is the released year of the albums and value is the list of all the tracks of the albums released in that year
	 */
	public Map<Integer, List<Track>> getTracksByYear(List<Album> albums) {
		Map<Integer, List<Track>> trackMap = new TreeMap<Integer, List<Track>>();

		for (Album a : albums) {
			List<Track> trackList = new ArrayList<Track>();
			if (trackMap.containsKey(a.getYear()))
				trackList = trackMap.get(a.getYear());
			trackList.addAll(a.getTracks());
			trackMap.put(a.getYear(), trackList);
		}

		return trackMap;
	}

	/**
	 * Group the tracks by released year with parallel stream
	 * @param albums
	 * @return a Map with key is the released year of the albums and value is the list of all the tracks of the albums released in that year
	 */
	public Map<Integer, List<Track>> getTracksByYearPar(List<Album> albums) {
		// TODO: Use collect with Collectors.groupingBy, Collectors.reducing
		return null;
	}
}
