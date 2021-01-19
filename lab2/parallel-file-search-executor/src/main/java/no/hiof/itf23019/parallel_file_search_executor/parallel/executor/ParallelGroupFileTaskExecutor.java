package no.hiof.itf23019.parallel_file_search_executor.parallel.executor;

import java.io.File;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentLinkedQueue;

import no.hiof.itf23019.parallel_file_search_executor.Result;


/**
 * @author nal
 *
 */
public class ParallelGroupFileTaskExecutor implements Callable<Boolean> {

	private final String fileName;
	private final ConcurrentLinkedQueue<File> directories;
	private final Result parallelResult;
	private boolean found;

	public ParallelGroupFileTaskExecutor(String fileName, Result parallelResult, ConcurrentLinkedQueue<File> directories) {
		this.fileName = fileName;
		this.parallelResult = parallelResult;
		this.directories = directories;
		this.found = false;
	}

	@Override
	public Boolean call() throws Exception {
		while (directories.size() > 0) {
			File file = directories.poll();
			try {
				processDirectory(file, fileName, parallelResult);
				if (found) {
					System.out.printf("%s has found the file%n", Thread.currentThread().getName());
					System.out.printf("Parallel Search with Executor: Path: %s%n", parallelResult.getPath());
					return found;
				}
			} catch (InterruptedException e) {
				//System.out.printf("%s has been interrupted%n", Thread.currentThread().getName());
			}
		}
		return found;
	}

	
	/**
	 * Looking for the file in the <code>dir</code> directory recursively.
	 * @param dir the directory to be searched
	 * @param fileName name of the file to be searched
	 * @param parallelResult result of the search process
	 * @throws InterruptedException throw if the thread is interrupted. 
	 */
	private void processDirectory(File dir, String fileName, Result parallelResult) throws InterruptedException {
		File[] contents;
		contents = dir.listFiles();

		if ((contents == null) || (contents.length == 0)) {
			return;
		}

		for (File content : contents) {
			if (content.isDirectory()) {
				processDirectory(content, fileName, parallelResult);
				if (Thread.currentThread().isInterrupted()) {
					throw new InterruptedException();
				}
				if (found) {
					return;
				}
			} else {
				processFile(content, fileName, parallelResult);
				if (Thread.currentThread().isInterrupted()) {
					throw new InterruptedException();
				}
				if (found) {
					return;
				}
			}
		}
	}

	
	
	/**
	 * Check if the file <code>content</code> is the file being looked for by comparing the name of the 
	 * file with the string <code>fileName</code>. If it is, set the boolean <code>found</code> to true. Also, update 
	 * the value of <code>parallelResult</code> accordingly (set the <code>found</code> to true, and the 
	 * <code>path</code> to the absolute path of the file).
	 * @param content the file to be process
	 * @param fileName name of the file to be searched
	 * @param parallelResult result of the search process
	 */
	private void processFile(File content, String fileName, Result parallelResult) {
		if (content.getName().equals(fileName)) {
			parallelResult.setPath(content.getAbsolutePath());
			this.found = true;
			}
	}




}
