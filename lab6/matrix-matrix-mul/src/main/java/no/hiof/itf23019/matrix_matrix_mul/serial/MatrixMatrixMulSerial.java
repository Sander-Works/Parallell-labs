package no.hiof.itf23019.matrix_matrix_mul.serial;

public class MatrixMatrixMulSerial {
	
	public long[][] multiply(int[][] matrix1, int[][] matrix2)
	{
		System.out.println("Serial Running ....");
		
		int N = matrix1.length;
		int M = matrix2.length;
		int P = matrix2[0].length;
		
		long[][] result = new long[N][P];
		
		for(int i = 0; i < N; i++)
			for(int j = 0; j < P; j++)
				for(int k = 0; k < M; k++)
					result[i][j] += matrix1[i][k]*matrix2[k][j];
		
		return result;
	}
}
