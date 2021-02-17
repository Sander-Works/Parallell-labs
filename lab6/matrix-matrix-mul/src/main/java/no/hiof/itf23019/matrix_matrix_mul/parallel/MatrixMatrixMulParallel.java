package no.hiof.itf23019.matrix_matrix_mul.parallel;

import java.util.concurrent.ForkJoinPool;


public class MatrixMatrixMulParallel {

	public long[][] multiply(int[][] matrix1, int[][] matrix2) {
		
		System.out.println("Parallel Running ....");
		
		int N = matrix1.length;
		int P = matrix2[0].length;
		long[][] result = new long[N][P];
	
		MatrixMatrixMulTask arraySumTask = new MatrixMatrixMulTask(matrix1, matrix2, 0, N, result);
		ForkJoinPool pool = new ForkJoinPool();
		pool.invoke(arraySumTask);

		return result;
	}
}
