package no.hiof.itf23019.AlbumAnalytics;

import java.util.List;

public class Album {

	private List<Track> tracks;
	private String name;
	private int year;

	
	public Album(String name, int year, List<Track> tracks)
	{
		this.name = name;
		this.year = year;
		this.tracks = tracks;
		
	}
	
	
	public List<Track> getTracks() {
		return tracks;
	}

	public void setTracks(List<Track> tracks) {
		this.tracks = tracks;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}


}
