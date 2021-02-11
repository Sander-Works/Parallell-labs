package no.hiof.itf23019.parallel_file_search_executor;

import java.io.File;
import java.util.Date;

import no.hiof.itf23019.parallel_file_search_executor.parallel.ParallelGroupFileSearch;
import no.hiof.itf23019.parallel_file_search_executor.parallel.executor.ParallelGroupFileSearchExecutor;
import no.hiof.itf23019.parallel_file_search_executor.serial.SerialFileSearch;


public class Main {

	public static void main(String[] args) {

		File file = new File("C:\\Windows\\");
		String regex = "hosts";
		Date start, end;

		//Serial search
		Result result = new Result();
		start = new Date();
		SerialFileSearch.searchFiles(file, regex, result);
		end = new Date();


		System.out.printf("Serial Search: Execution Time: %d%n", end.getTime() - start.getTime());

		//Parallel search without executor
		Result parallelResult = new Result();
		start = new Date();
		ParallelGroupFileSearch.searchFiles(file, regex, parallelResult);
		end = new Date();

		System.out.printf("Parallel Group Search: Path: %s%n", parallelResult.getPath());
		System.out.printf("Parallel Group Search: Execution Time: %d%n", end.getTime() - start.getTime());
		
		//Parallel search with executor
		Result parallelResultExecutor = new Result();
		start = new Date();
		ParallelGroupFileSearchExecutor.searchFiles(file, regex, parallelResultExecutor);
		end = new Date();

		System.out.printf("Parallel Group Search with Executor: Path: %s%n", parallelResultExecutor.getPath());
		System.out.printf("Parallel Group Search with Exectuor: Execution Time: %d%n", end.getTime() - start.getTime());
	}

}
