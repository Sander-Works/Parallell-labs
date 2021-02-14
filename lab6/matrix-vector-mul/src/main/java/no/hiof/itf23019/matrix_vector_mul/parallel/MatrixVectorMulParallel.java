package no.hiof.itf23019.matrix_vector_mul.parallel;

import java.util.concurrent.ForkJoinPool;


public class MatrixVectorMulParallel {

	public long[] multiply(int[][] matrix, int[] vector) {
		
		System.out.println("Parallel Running ....");
		
		int N = matrix.length;
		long[] result = new long[N];
	
		MatrixVectorMulTask arraySumTask = new MatrixVectorMulTask(matrix, vector, 0, N, result);
		ForkJoinPool pool = new ForkJoinPool();
		pool.invoke(arraySumTask);

		return result;
	}
}
