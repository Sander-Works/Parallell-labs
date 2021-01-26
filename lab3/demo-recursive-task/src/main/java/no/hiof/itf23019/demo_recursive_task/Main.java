package no.hiof.itf23019.demo_recursive_task;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

import no.hiof.itf23019.demo_recursive_task.task.Task;
import no.hiof.itf23019.demo_recursive_task.util.Product;
import no.hiof.itf23019.demo_recursive_task.util.ProductListGenerator;

/**
 * Main class of the example. Creates a ForkJoinPool, an array of 100
 * elements and a Task object. Executes the Task object in the pool
 * and process the exception thrown by the Task
 *
 */
public class Main {

	/**
	 * Main method of the example
	 * @param args
	 */
	public static void main(String[] args) {

		// Create a list of products
		ProductListGenerator generator=new ProductListGenerator();
		List<Product> products=generator.generate(10_000_000);
		
		// Craete a task
		Task task=new Task(products, 0, products.size(), 0.20);
		
		// Create a ForkJoinPool
		ForkJoinPool pool= ForkJoinPool.commonPool();
		
		// Execute the Task
		pool.execute(task);

		// Write information about the pool
		do {
			System.out.printf("******************************************\n");
			System.out.printf("Main: Parallelism: %d\n",pool.getParallelism());
			System.out.printf("Main: Active Threads: %d\n",pool.getActiveThreadCount());
			System.out.printf("Main: Task Count: %d\n",pool.getQueuedTaskCount());
			System.out.printf("Main: Steal Count: %d\n",pool.getStealCount());
			System.out.printf("******************************************\n");
			try {
				TimeUnit.MILLISECONDS.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} while (!task.isDone());
	
		// Shutdown the pool
		pool.shutdown();
		
		
		// Check if the task has completed normally
		if (task.isCompletedNormally()){
			System.out.printf("Main: The process has completed normally.\n");
		}
		
		// Expected result: 10 * 10_000_000
		try {
			double result = task.get();
			System.out.printf("Sum of the prices: %f%n", result);
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		
		
		// End of the program
		System.out.println("Main: End of the program.\n");

	}

}
