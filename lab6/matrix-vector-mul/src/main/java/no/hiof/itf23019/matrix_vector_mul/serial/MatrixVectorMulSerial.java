package no.hiof.itf23019.matrix_vector_mul.serial;

public class MatrixVectorMulSerial {
	
	public long[] multiply(int[][] matrix, int[] vector)
	{
		System.out.println("Serial Running ....");
		
		int N = matrix.length;
		int M = vector.length;
		long[] result = new long[N];
		
		for(int i = 0; i < N; i++)
			for(int j = 0; j < M; j++)
					result[i] += matrix[i][j]*vector[j];
		
		return result;
	}
}
