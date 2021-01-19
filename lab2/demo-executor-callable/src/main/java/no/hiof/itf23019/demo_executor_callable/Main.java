package no.hiof.itf23019.demo_executor_callable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

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
		
		// List to store the Future objects that control the execution of  the task and
		// are used to obtain the results
		List<Future<Long>> resultList=new ArrayList<>();
		
		// create 100 tasks	
		System.out.printf("Main: Starting.\n");
		for (int i=0; i<100; i++){
			Task task=new Task("Task_"+i);
			Future<Long> result = executor.submit(task);
			resultList.add(result);
		}
		
		// Wait for the finalization of the ten tasks
		do {
			System.out.printf("Main: Number of Completed Tasks: %d\n",executor.getCompletedTaskCount());
			for (int i=0; i<resultList.size(); i++) {
				Future<Long> result=resultList.get(i);
				System.out.printf("Main: Task %d: isDone: %s\n",i,result.isDone());
			}
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} while (executor.getCompletedTaskCount() < resultList.size());
		
		// Write the results
		System.out.printf("Main: Results\n");
		for (int i=0; i<resultList.size(); i++) {
			Future<Long> result=resultList.get(i);
			Long number=null;
			try {
				number=result.get();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
			System.out.printf("Core: Task %d: %d\n",i,number);
		}
		

		// Shutdown the executor
		System.out.printf("Main: Shuting down the Executor.\n");
		executor.shutdown();

		System.out.printf("Main: End.\n");		
		
	}

}
