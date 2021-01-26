package no.hiof.itf23019.array_sum;

import java.util.Random;

import no.hiof.itf23019.array_sum.parallel.ArraySumParallel;
import no.hiof.itf23019.array_sum.serial.ArraySumSerial;

public class Main {
	public static void main(String[] args) {

		int[] input = generateArray(500_000_000);
		int RUNS = 10;

		ArraySumParallel arraySumParallel = new ArraySumParallel();
		ArraySumSerial arraySumSerial = new ArraySumSerial();
		
		long startTime, endTime;
		
		for(int i = 1; i <= RUNS; i++)
		{
			System.out.println("Run #" + i);
			
			startTime = System.currentTimeMillis();
			int seqSum = arraySumSerial.sequentialArraySum(input);
			endTime = System.currentTimeMillis();
			System.out.println("sequentialArraySum output is " + seqSum);
			System.out.println("sequentialArraySum took " + (endTime - startTime) + " milliseconds.");
			
			startTime = System.currentTimeMillis();
			int parSum = arraySumParallel.parallelArraySum(input);
			endTime = System.currentTimeMillis();
			System.out.println("parallelArraySum output is " + parSum);
			System.out.println("parallelArraySum took " + (endTime - startTime) + " milliseconds.");
			
			System.out.println();
		}
		
		//TODO: Compute the speedup
		
	}

	private static int[] generateArray(int length) {

		Random random = new Random(210120);

		int[] a = new int[length];
		for (int i = 0; i < length; i++)
			a[i] = random.nextInt();

		return a;
	}

}
