package no.hiof.itf23019.matrix_vector_mul.parallel;
import java.util.concurrent.RecursiveAction;


public class MatrixVectorMulTask extends RecursiveAction{

	private static final long serialVersionUID = 935729182687362668L;

	private int[][] matrix;
	private int[] vector;
	private int startIndex, endIndex;
	private long[] result;
	private int threshold = 1_000;
	
	public MatrixVectorMulTask(int[][] matrix, int[] vector, int startIndex, int endIndex, long[] result) {
		this.matrix = matrix;
		this.vector = vector;
		this.startIndex = startIndex;
		this.endIndex = endIndex;
		this.result = result;
	}
	@Override
	protected void compute() {
		//TODO: implement the fork/join method
	/*
		int N = matrix.length;
		int M = vector.length;
		long[] result = new long[N];

		for(int i = 0; i < N; i++)
			for(int j = 0; j < M; j++)
				result[i] += matrix[i][j]*vector[j];

		return result;
		*/

		MatrixVectorMulTask left = new MatrixVectorMulTask(matrix, vector,  startIndex, endIndex, result); //This is to get to get the first half of the matrix's
		MatrixVectorMulTask right = new MatrixVectorMulTask(matrix, vector, startIndex, endIndex, result); //This is to get the second half of the matrix's

		left.fork();
		right.compute();
		left.join();
			
	}
	
	

}
