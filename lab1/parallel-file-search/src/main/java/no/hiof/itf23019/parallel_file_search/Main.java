package no.hiof.itf23019.parallel_file_search;

import java.io.File;
import java.util.Date;

import no.hiof.itf23019.parallel_file_search.parallel.ParallelGroupFileSearch;
import no.hiof.itf23019.parallel_file_search.serial.SerialFileSearch;


public class Main {

	public static void main(String[] args) {

		File file = new File("C:\\Windows\\");
		String regex = "hosts";
		Date start, end;

		Result result = new Result();
		start = new Date();
		SerialFileSearch.searchFiles(file, regex, result);
		end = new Date();


		System.out.printf("Serial Search: Execution Time: %d%n", end.getTime() - start.getTime());

		
		Result parallelResult = new Result();
		start = new Date();
		ParallelGroupFileSearch.searchFiles(file, regex, parallelResult);
		end = new Date();

		System.out.printf("Parallel Group Search: Path: %s%n", parallelResult.getPath());
		System.out.printf("Parallel Group Search: Execution Time: %d%n", end.getTime() - start.getTime());
		
		
		//TODO: Compute and print out the speed up.
		Double speedup = -1.0;
		System.out.printf("The speedup is %f%n", speedup);
	}

}
