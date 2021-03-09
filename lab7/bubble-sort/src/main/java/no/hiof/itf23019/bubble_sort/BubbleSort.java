package no.hiof.itf23019.bubble_sort;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.Phaser;

public class BubbleSort {

	public static void main(String[] args) {

		int n, tasks;
		int[] arr, original_arr;
		long startTime, endTime, seqRunTime = 0, parRunTime = 0, parFuzzyRunTime = 0;

		
		//Testing if the implementations are correct.

		System.out.println("Testing Correctness");
		n = 30;
		tasks = Runtime.getRuntime().availableProcessors();
		
		original_arr = generateArray(n);
		
		arr = Arrays.copyOf(original_arr, original_arr.length);
		System.out.println("Initial Array:");
		System.out.println(Arrays.toString(arr));
		System.out.println("Running Sequential Version");
		runSequential(arr, n);
		System.out.println("Result:");
		System.out.println(Arrays.toString(arr));
		
		System.out.println();
		
		arr = Arrays.copyOf(original_arr, original_arr.length);
		System.out.println("Running Parallel Version");
		runParallel(arr, n, tasks);
		System.out.println("Result:");
		System.out.println(Arrays.toString(arr));
		
		System.out.println();
		
		arr = Arrays.copyOf(original_arr, original_arr.length);
		System.out.println("Running Parallel Version (Fuzzy Barrier)");
		runParallelFuzzy(arr, n, tasks);
		System.out.println("Result:");
		System.out.println(Arrays.toString(arr));
		

		
		//Testing on large array
		System.out.println("\nTesting Performance");
		n = 200_000;
		tasks = Runtime.getRuntime().availableProcessors();
			
		original_arr = generateArray(n);
		
		arr = Arrays.copyOf(original_arr, original_arr.length);
		System.out.println("Running Sequential Version");
		startTime = System.currentTimeMillis();
		runSequential(arr, n);
		endTime = System.currentTimeMillis();
		seqRunTime = endTime - startTime;
		System.out.println("Sequential version took " + seqRunTime + " miliseconds"); 


        
        arr = Arrays.copyOf(original_arr, original_arr.length);
		System.out.println("Running Parallel Version");
		startTime = System.currentTimeMillis();
		runParallel(arr, n, tasks);
		endTime = System.currentTimeMillis();
		parRunTime = endTime - startTime;
		System.out.println("Parallel version took " + parRunTime + " miliseconds");
		System.out.println("Bubble Speedup: " + seqRunTime/parRunTime);
		

        
        arr = Arrays.copyOf(original_arr, original_arr.length);
		System.out.println("Running Parallel Version (Fuzzy Barrier)");
		startTime = System.currentTimeMillis();
		runParallelFuzzy(arr, n, tasks);
		endTime = System.currentTimeMillis();
		parFuzzyRunTime = endTime - startTime;
		System.out.println("Parallel version took " + parFuzzyRunTime + " miliseconds");
		System.out.println("Bubble speedup fuzzy: " + seqRunTime/parFuzzyRunTime);


	        
		//TODO: Compute the speedup
		//Placed in the blocks above
		

	}

	public static int[] generateArray(int size) {
		Random random = new Random(20200218);
		int[] array = new int[size];
		for (int i = 0; i < size; i++)
			array[i] = random.nextInt(size*10);

		return array;
	}

	public static void runSequential(final int[] input, final int n) {

		//TODO: implement bubble sort here
		//n was taken
		int N = input.length;
		for (int i = 0; i < N - 1; i++) {
			for (int j = 0; j < N - i - 1; j++) {
				//swapping if the number on index j is bigger then the number on index j+1
				if (input[j] > input[j + 1]) {
					//Creating temp int to save the value in
					int temp = input[j];
					input[j] = input[j + 1];
					input[j + 1] = temp;
				}
			}
		}
	}

	public static void runParallel(final int[] input, final int n, final int tasks) {
		Phaser ph = new Phaser(0);
		ph.bulkRegister(tasks);

		Thread[] threads = new Thread[tasks];

		for (int ii = 0; ii < tasks; ii++) {
			final int m = ii;

			threads[ii] = new Thread(() -> {

				final int chunkSize = (n + tasks - 1) / tasks;
				final int left = m * chunkSize;
				int right = left + chunkSize - 1;
				if (right >= n)
					right = n - 1;

				for (int k = 0; k < n; k++) {
					if (k % 2 == 0) { //If even phase

						int sIdx = left / 2;
						
						int eIdx = 2 * (int) (right / 2) + 1 < n ? right / 2 : right / 2 - 1;
						

						for (int i = sIdx; i <= eIdx; i++) {
							if (input[2 * i] > input[2 * i + 1]) { //Compare and Swap the elements at even indices
								int temp = input[2 * i];
								input[2 * i] = input[2 * i + 1];
								input[2 * i + 1] = temp;
							}
						}

					} else { //If odd phase

						int sIdx = left / 2;
						int eIdx = 2 * (int) (right / 2) + 2 < n ? right / 2 : (int) (right - 1) / 2 - 1;
						
						for (int i = sIdx; i <= eIdx; i++) {
							if (input[2 * i + 1] > input[2 * i + 2]) { //Compare and Swap the elements at odd indices
								int temp = input[2 * i + 1];
								input[2 * i + 1] = input[2 * i + 2];
								input[2 * i + 2] = temp;
							}
						}
					}

					ph.arriveAndAwaitAdvance();

				}
			});
			threads[ii].start();
		}

		for (int ii = 0; ii < tasks; ii++) {
			try {
				threads[ii].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void runParallelFuzzy(final int[] input, final int n, final int tasks) {
		Phaser ph = new Phaser(0);
		ph.bulkRegister(tasks);

		Thread[] threads = new Thread[tasks];

		for (int ii = 0; ii < tasks; ii++) {
			final int m = ii;

			threads[ii] = new Thread(() -> {

				final int chunkSize = (n + tasks - 1) / tasks;
				final int left = m * chunkSize;
				int right = (left + chunkSize) - 1;
				if (right > n)
					right = n;

				int phase = 0;
				
				if(left >= right)
				{
					ph.arriveAndDeregister();
					return;
					
				}

				for (int k = 0; k < n - 1; k++) {
					if (k % 2 == 0) { //If even phase
						if (right % 2 == 1 && m != tasks - 1) {  //process the elements at the right indices first
							int i = right / 2;
							if (input[2 * i] > input[2 * i + 1]) {
								int temp = input[2 * i];
								input[2 * i] = input[2 * i + 1];
								input[2 * i + 1] = temp;
							}
						}

						phase = ph.arrive();

						int sIdx = left / 2;
						int eIdx = 2 * (int) (right / 2) + 1 < n ? right / 2 : right / 2 - 1;
						
						for (int i = sIdx; i <= eIdx; i++) { //process the remaining elements 
	
							//TODO: Compare and Swap the elements at even indices. 
							//Hint: Refer to the runParallel method above
								if (input[2 * i] > input[2 * i + 1]) { //Compare and Swap the elements at even indices
									int temp = input[2 * i];
									input[2 * i] = input[2 * i + 1];
									input[2 * i + 1] = temp;
								}
						}

					} else { //If odd phase
						if (right % 2 == 0 && m != tasks - 1) {  //process the elements at the right indices first
							int i = right / 2;
							if (input[2 * i + 1] > input[2 * i + 2]) { 
								int temp = input[2 * i + 1];
								input[2 * i + 1] = input[2 * i + 2];
								input[2 * i + 2] = temp;
							}
						}

						phase = ph.arrive();

						int sIdx = left / 2;
						int eIdx = 2 * (int) (right / 2) + 2 < n ? right / 2 : (int) (right - 1) / 2 - 1;

						for (int i = sIdx; i <= eIdx; i++) {  //process the remaining elements 
							
							//TODO: Compare and Swap the elements at odd indices. 
							//Hint: Refer to the runParallel method above
							if (input[2 * i + 1] > input[2 * i + 2]) { //Compare and Swap the elements at odd indices
								int temp = input[2 * i + 1];
								input[2 * i + 1] = input[2 * i + 2];
								input[2 * i + 2] = temp;
							}

						}
					}

					ph.awaitAdvance(phase);

				}
			});
			threads[ii].start();
		}

		for (int ii = 0; ii < tasks; ii++) {
			try {
				threads[ii].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
