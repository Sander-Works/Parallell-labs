package no.hiof.itf23019.AlbumAnalytics;

public class Track {
	
	private String name;
	private int rating;
	
	public Track(String name, int rating)
	{
		this.name = name;
		this.rating = rating;
	}
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}
	
	
	

}
