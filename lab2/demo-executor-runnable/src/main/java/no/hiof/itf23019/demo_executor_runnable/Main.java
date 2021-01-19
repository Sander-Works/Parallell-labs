package no.hiof.itf23019.demo_executor_runnable;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Main class of the example. Creates a server and 100 request of the Task class
 * that sends to the server
 *
 */
public class Main {

	/**
	 * Main method of the example
	 * @param args
	 */
	public static void main(String[] args) {
		// Create the executor
		ThreadPoolExecutor executor=(ThreadPoolExecutor)Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		// create 100 task		
		System.out.printf("Main: Starting.\n");
		for (int i=0; i<100; i++){
			Task task=new Task("Task_"+i);
			executor.execute(task);
		}
		
		// Shutdown the executor
		System.out.printf("Main: Shuting down the Executor.\n");
		executor.shutdown();

		System.out.printf("Main: End.\n");		
		
	}

}
