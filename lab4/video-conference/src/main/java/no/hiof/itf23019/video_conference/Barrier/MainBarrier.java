package no.hiof.itf23019.video_conference.Barrier;

import no.hiof.itf23019.video_conference.core.Participant;
import no.hiof.itf23019.video_conference.core.VideoConference;

import java.util.concurrent.TimeUnit;

public class MainBarrier {

	public static void main(String[] args) {
		//TODO: Creates a VideoConferenceBarrier with 10 participants.
		VideoConference conference = new VideoConferenceBarrier(10);
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
