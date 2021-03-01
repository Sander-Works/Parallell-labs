package no.hiof.itf23019.matrix_matrix_mul;

import java.util.Arrays;
import java.util.Random;

import no.hiof.itf23019.matrix_matrix_mul.parallel.MatrixMatrixMulParallel;
import no.hiof.itf23019.matrix_matrix_mul.serial.MatrixMatrixMulSerial;

public class Main 
{
    public static void main( String[] args )
    {
    	int N = 10_000;
		int M = 1_000;
		int P = 1_000;
		int[][] matrix1 = generate2DArray(N, M);
		int[][] matrix2 = generate2DArray(M, P);
		
		
		MatrixMatrixMulSerial matrixMatrixMulSerial = new MatrixMatrixMulSerial();
		MatrixMatrixMulParallel matrixMatrixMulParallel = new MatrixMatrixMulParallel();
		
		long startTime, endTime, serialTime, parTime;
		startTime = System.currentTimeMillis();
		long[][] resultSerial =  matrixMatrixMulSerial.multiply(matrix1, matrix2);
		endTime = System.currentTimeMillis();
		serialTime = endTime - startTime;
		
		//System.out.println(Arrays.deepToString(matrix1).replace("], ", "]\n"));
		//System.out.println(Arrays.deepToString(matrix2).replace("], ", "]\n"));
		//System.out.println(Arrays.deepToString(resultSerial).replace("], ", "]\n"));
		
		startTime = System.currentTimeMillis();
		long[][] resultParallel = matrixMatrixMulParallel.multiply(matrix1, matrix2);
		endTime = System.currentTimeMillis();
		parTime = endTime - startTime;
		
		compareResults(resultSerial, resultParallel);
		System.out.println("serialTime=" + serialTime);
		System.out.println("parTime=" + parTime);
		
		//TODO: compute speedup
		//Almsot 8 times the speed with parallel
		System.out.println("Parallel speedup: " + ((double) serialTime / parTime));
    }
    
	private static int[] generate1DArray(int N) {

		
		System.out.println("generate1DArray ....");
		Random random = new Random(210214);

		int[] a = new int[N];
		for (int i = 0; i < N; i++)
			a[i] = random.nextInt(10);

		return a;
	}
	
	private static int[][] generate2DArray(int N, int M)
	{
		System.out.println("generate2DArray ....");
		Random random = new Random(210214);
		
		int[][] a = new int[N][M];
		for (int i = 0; i < N; i++)
			for(int j = 0; j < M; j++)
				a[i][j] = random.nextInt(10);

		return a;
	}
	
	private static void compareResults(long[][] arr1, long[][] arr2)
	{
		if (Arrays.deepEquals(arr1, arr2)) 
            System.out.println("The results are the same"); 
        else
            System.out.println("The results are NOT the same"); 
	}
}
