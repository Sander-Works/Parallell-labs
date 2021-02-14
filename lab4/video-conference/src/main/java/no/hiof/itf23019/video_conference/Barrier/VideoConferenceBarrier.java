package no.hiof.itf23019.video_conference.Barrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
//import javax.swing.KeyStroke;
//import com.sun.java.swing.action.FinishAction;
import no.hiof.itf23019.video_conference.core.VideoConference;

/**
 * This class implements the controller of the Videoconference
 * 
 * It uses a CyclicBarrier to control the arrival of all the 
 * participants in the conference.
 *
 */
public class VideoConferenceBarrier implements VideoConference {

	/**
	 * This class uses a CyclicBarrier to control the arrival of all
	 * the participants
	 */
	private final CyclicBarrier barrier;
	
	/**
	 * Constructor of the class. Initializes the CountDownLatch
	 * @param number The number of participants in the videoconference
	 */
	public VideoConferenceBarrier(int number) {
		
		//TODO: Initialize the barrier 
		// with the constructor CyclicBarrier(int parties, Runnable barrierAction)
		this.barrier = new CyclicBarrier(number, new BarrierAction());
		
	}

	@Override
	public void arrive(String name){


		System.out.printf("%s has arrived.\n",name);
		
		//Printing out the number of participants has not arrive yet
		System.out.printf("VideoConference: Waiting for %d participants.\n", barrier.getParties() - barrier.getNumberWaiting() - 1);
		
		//TODO: Wait for the other to arrive with barrier
		try {
			barrier.await();
		} catch (InterruptedException | BrokenBarrierException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This is the main method of the Controller of the VideoConference. It waits for all
	 * the participants and the, starts the conference
	 */
	@Override
	public void run() {
		System.out.printf("VideoConference: Initialization: %d participants.\n",barrier.getParties());
		
		/*// Starts the conference
		System.out.printf("VideoConference: All the participants have come\n");
		System.out.printf("VideoConference: Let's start...\n");*/
		
	}
	
	
}

