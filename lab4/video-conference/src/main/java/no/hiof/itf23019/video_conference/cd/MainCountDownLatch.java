package no.hiof.itf23019.video_conference.cd;

import no.hiof.itf23019.video_conference.core.Participant;
import no.hiof.itf23019.video_conference.core.VideoConference;

/**
 * Main class of the example. Create, initialize and execute all the objects
 * necessaries for the example
 *
 */
public class MainCountDownLatch {

	/**
	 * Main method of the example
	 * @param args
	 */
	public static void main(String[] args) {

		// Creates a VideoConference with 10 participants.
		VideoConference conference=new VideoConferenceCountDownLatch(10);
		// Creates a thread to run the VideoConference and start it.
		Thread threadConference= new Thread(conference);
		threadConference.start();
		
		// Creates ten participants, a thread for each one and starts them
		for (int i=0; i<10; i++){
			Participant p=new Participant(conference, "Participant "+i);
			Thread t=new Thread(p);
			t.start();
		}

	}

}
