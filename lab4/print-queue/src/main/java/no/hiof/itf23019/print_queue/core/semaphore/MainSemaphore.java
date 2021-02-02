package no.hiof.itf23019.print_queue.core.semaphore;

import no.hiof.itf23019.print_queue.core.Job;
import no.hiof.itf23019.print_queue.core.Printers;

public class MainSemaphore {
	/**
	 * Main method of the class. Run ten jobs in parallel that
	 * send documents to the print queue at the same time.
	 */
	public static void main (String args[]){
		
		int numberOfPrinters = 5;
		int numberOfJobs = 10;
		// Creates the print queue
		//TODO: Initialize the printQueue with PrintQueueSemaphore
		Printers printQueue=null;
		
		// Creates twelve Threads
		Thread[] threads=new Thread[numberOfJobs];
		for (int i=0; i < numberOfJobs; i++){
			threads[i]=new Thread(new Job(printQueue),"Thread "+i);
		}
		
		// Starts the Threads
		for (int i=0; i < threads.length; i++){
			threads[i].start();
		}
	

}

}
