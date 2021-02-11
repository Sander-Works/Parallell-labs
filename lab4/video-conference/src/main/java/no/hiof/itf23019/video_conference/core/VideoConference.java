package no.hiof.itf23019.video_conference.core;

public interface VideoConference extends Runnable {
	/**
	 * This method is called by every participant when he incorporates to the VideoConference
	 * @param participant
	 */
	public void arrive(String name);
}
