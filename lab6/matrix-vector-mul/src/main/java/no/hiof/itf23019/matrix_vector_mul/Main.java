package no.hiof.itf23019.matrix_vector_mul;

import java.util.Arrays;
import java.util.Random;

import no.hiof.itf23019.matrix_vector_mul.parallel.MatrixVectorMulParallel;
import no.hiof.itf23019.matrix_vector_mul.serial.MatrixVectorMulSerial;

public class Main {

	public static void main(String[] args) {
		int N = 100_000;
		int M = 10_000;
		int[][] matrix = generate2DArray(N, M);
		int[] vector = generate1DArray(M);
		
		MatrixVectorMulSerial matrixVectorMulSerial = new MatrixVectorMulSerial();
		MatrixVectorMulParallel matrixVectorMulParallel = new MatrixVectorMulParallel();
		
		long startTime, endTime, serialTime, parTime;
		startTime = System.currentTimeMillis();
		long[] resultSerial =  matrixVectorMulSerial.multiply(matrix, vector);
		endTime = System.currentTimeMillis();
		serialTime = endTime - startTime;
		
		startTime = System.currentTimeMillis();
		long[] resultParallel = matrixVectorMulParallel.multiply(matrix, vector);
		endTime = System.currentTimeMillis();
		parTime = endTime - startTime;
		
		compareResults(resultSerial, resultParallel);
		System.out.println("serialTime=" + serialTime);
		System.out.println("parTime=" + parTime);
		
		//System.out.println(Arrays.deepToString(matrix).replace("], ", "]\n"));
		//System.out.println(Arrays.toString(vector));
		//System.out.println(Arrays.toString(resultSerial));
		
		//TODO: compute speedup
		
		
		
		

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
	
	private static void compareResults(long[] arr1, long[] arr2)
	{
		if (Arrays.equals(arr1, arr2)) 
            System.out.println("The results are the same"); 
        else
            System.out.println("The results are NOT the same"); 
	}
	

}
