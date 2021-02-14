package no.hiof.itf23019.video_conference.cd;

import java.util.concurrent.CountDownLatch;

import no.hiof.itf23019.video_conference.core.VideoConference;

/**
 * This class implements the controller of the Videoconference
 * 
 * It uses a CountDownLatch to control the arrival of all the 
 * participants in the conference.
 *
 */
public class VideoConferenceCountDownLatch implements VideoConference{

	/**
	 * This class uses a CountDownLatch to control the arrivel of all
	 * the participants
	 */
	private final CountDownLatch controller;
	
	/**
	 * Constructor of the class. Initializes the CountDownLatch
	 * @param number The number of participants in the videoconference
	 */
	public VideoConferenceCountDownLatch(int number) {
		
		//TODO: Initialize the controller with CountDownLatch(int count) 
		this.controller=new CountDownLatch(number);
	}

	@Override
	public void arrive(String name){
		System.out.printf("%s has arrived.\n",name);
		// TODO: Uses the countDown method to decrement the internal counter of the CountDownLatch
		controller.countDown();
		
		
		//Printing out the number of participants has not arrive yet
		System.out.printf("VideoConference: Waiting for %d participants.\n", controller.getCount());
	}
	
	/**
	 * This is the main method of the Controller of the VideoConference. It waits for all
	 * the participants and the, starts the conference
	 */
	@Override
	public void run() {
		System.out.printf("VideoConference: Initialization: %d participants.\n",controller.getCount());
		
		//TODO: Wait for all the participants with the countdownlatch
		try {
			controller.await();
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		// Starts the conference
		System.out.printf("VideoConference: All the participants have come\n");
		System.out.printf("VideoConference: Let's start...\n");
	}
	
	
}
