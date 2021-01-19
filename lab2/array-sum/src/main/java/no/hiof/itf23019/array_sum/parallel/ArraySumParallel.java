package no.hiof.itf23019.array_sum.parallel;

import java.util.concurrent.ForkJoinPool;

public class ArraySumParallel {
	
	
	public int parallelArraySum(int[] array) {
		
		System.out.println("Parallel Running ....");
	
		long startTime = System.currentTimeMillis();
		
		ArraySumTask arraySumTask = new ArraySumTask(array, 0, array.length);
		ForkJoinPool pool = new ForkJoinPool();
		pool.invoke(arraySumTask);
		long endTime = System.currentTimeMillis();

		System.out.println("parallelArraySum took " + (endTime - startTime) + " milliseconds.");
		return arraySumTask.getSum();
	}
}
