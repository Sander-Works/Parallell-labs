package no.hiof.itf23019.parallel_file_search;

public class Result {
	
	private String path;
	private boolean found=false;

	public boolean isFound() {
		return found;
	}

	public void setFound(boolean found) {
		this.found = found;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
