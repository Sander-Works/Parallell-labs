package no.hiof.itf23019.array_sum;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

import no.hiof.itf23019.array_sum.parallel.ArraySumParallel;
import no.hiof.itf23019.array_sum.serial.ArraySumSerial;

public class Main {
	public static void main(String[] args) {

		int[] input = generateArray(500_000_000);
		int RUNS = 10;

		ArraySumParallel arraySumParallel = new ArraySumParallel();
		ArraySumSerial arraySumSerial = new ArraySumSerial();
		
		for(int i = 1; i <= RUNS; i++)
		{
			System.out.println("Run #" + i);
			
			
			int seqSum = arraySumSerial.sequentialArraySum(input);
			System.out.println("sequentialArraySum output is " + seqSum);
			
			int parSum = arraySumParallel.parallelArraySum(input);
			System.out.println("parallelArraySum output is " + parSum);
			
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
