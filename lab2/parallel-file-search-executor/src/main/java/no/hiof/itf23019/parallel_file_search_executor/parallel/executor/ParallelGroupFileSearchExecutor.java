package no.hiof.itf23019.parallel_file_search_executor.parallel.executor;

import java.io.File;
import java.lang.Thread.State;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import no.hiof.itf23019.parallel_file_search_executor.Result;

public class ParallelGroupFileSearchExecutor {

	public static void searchFiles(File file, String fileName, Result parallelResult) {

		ConcurrentLinkedQueue<File> directories = new ConcurrentLinkedQueue<>();
		File[] contents = file.listFiles();

		for (File content : contents) {
			if (content.isDirectory()) {
				directories.add(content);
			}
		}

		// Create the executor
		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors
				.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

		// List to store the Future objects that control the execution of the task
		List<Future<Boolean>> futureTaskList = new ArrayList<>();

		boolean finish = false;
		//Number of task to create
		int numTasks = Runtime.getRuntime().availableProcessors();
		String result;
		//Create the task and submit them to the executor
		for (int i = 0; i < numTasks; i++) {
			ParallelGroupFileTaskExecutor task = new ParallelGroupFileTaskExecutor(fileName, parallelResult,
					directories);
			Future<Boolean> futureTask = executor.submit(task);
			futureTaskList.add(futureTask);
		}

		while (!finish) {
			for (Future<Boolean> booleanFuture : futureTaskList)
				try {
					if (booleanFuture.get()|| executor.getCompletedTaskCount() == numTasks) {
						finish = true;
						break;
					}
				} catch (InterruptedException | ExecutionException e) {
					e.printStackTrace();
				}
			executor.shutdown();
		}

			if (executor.getCompletedTaskCount() != numTasks) {
				executor.shutdownNow();
				//Could also have used cancel(true)
			}


		}
	}


