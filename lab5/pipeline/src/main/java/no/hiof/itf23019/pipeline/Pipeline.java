package no.hiof.itf23019.pipeline;

import java.util.concurrent.Phaser;

public class Pipeline {

	//Simulation of work processing for each stage
	public static void doWork(long miliseconds) {
		try {
			Thread.sleep(miliseconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		long totSeqRunTime = 0;
		long totalPipelineTime = 0;

		final int n = 100;  //Number of items process by 3 stage pipeline
		
		final int RUNS = 5;
		
		for(int numRun = 0; numRun < RUNS; numRun++)
		{
			System.out.println("Run " + (numRun + 1));
			
			//create two phaser objects for three stages pipeline 
			final Phaser ph0 = new Phaser(1);
			final Phaser ph1 = new Phaser(1);

			//TODO: Add phaser methods for the first stage
			Thread t0 = new Thread(() -> {
				for(int i = 0; i < n; i ++) {
					doWork(10);
					ph0.arrive();
				}
			});
			
			//TODO: Add phaser methods for the second stage
			Thread t1 = new Thread(() -> {
				for(int i = 0; i < n; i ++) {
					ph0.awaitAdvance(0);
					doWork(10);
					ph1.arrive();
				}
			});
			
			//TODO: Add phaser methods for the third stage
			Thread t2 = new Thread(() -> {
				for(int i = 0; i < n; i ++) {
					ph1.awaitAdvance(1);
					doWork(10);
				}
			});
			
			
			//Run sequential version
			long startTime = System.currentTimeMillis();
			for(int i = 0; i < n; i++) {
				doWork(10); //first stage
				doWork(10); //second stage
				doWork(10); //third stage
				
			}
			long runTime = System.currentTimeMillis() - startTime;
			totSeqRunTime += runTime;
			System.out.println("Sequential version completed in " + runTime + " miliseconds");
			
			//Run Paralell version
			startTime = System.currentTimeMillis();
			
			//Start threads
			t0.start();
			t1.start();
			t2.start();
			
			//Wait for threads to complete
			try {
				t0.join();
				t1.join();
				t2.join();
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
			
			runTime = System.currentTimeMillis() - startTime;
			totalPipelineTime = runTime;
			System.out.println("Pipeline version completed in " + runTime + " miliseconds");
			
		}

		//TODO: Compute speedup
		System.out.println("Speedup pipeline: " + totSeqRunTime/totalPipelineTime);

	}

}
