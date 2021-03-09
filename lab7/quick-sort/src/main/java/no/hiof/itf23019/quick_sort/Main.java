package no.hiof.itf23019.quick_sort;

import java.util.Arrays;

import no.hiof.itf23019.quick_sort.common.Utils;
import no.hiof.itf23019.quick_sort.parallel.ParallelQuickSort;
import no.hiof.itf23019.quick_sort.serial.SerialQuickSort;

public class Main {

	public static void main(String[] args) {

		int RUNS = 10;
		double totalParallelTime = 0;
		double totalSerialTime = 0;
		for (int j = 0; j < RUNS; j++) {
			
			System.out.println("RUN: #" + j);
			
			int n = 200_000;
			int[] data = Utils.generateArray(n);
			int[] data2 = Arrays.copyOf(data, data.length);// copy(data);
			int[] data3 = Arrays.copyOf(data, data.length);// copy(data);

			long start;
			long end;

			start = System.currentTimeMillis();
			Arrays.parallelSort(data);
			end = System.currentTimeMillis();
			System.out.println("Execution Time Java Arrays.parallelSort(): " + (end - start));

	
			ParallelQuickSort mySorter = new ParallelQuickSort();
			start = System.currentTimeMillis();
			mySorter.quickSort(data2, 0, data2.length);
			end = System.currentTimeMillis();

			System.out.println("Execution Time Java ParallelQuickSort: " + (end - start));
			totalParallelTime = (end-start);

			for (int i = 0; i < data.length; i++) {
				if (data[i] != data2[i]) {
					System.err.println("There's a difference is position " + i);
					System.exit(-1);
				}
			}

			SerialQuickSort mySerialSorter = new SerialQuickSort();
			start = System.currentTimeMillis();
			mySerialSorter.quickSort(data3, 0, data3.length);
			end = System.currentTimeMillis();

			System.out.println("Execution Time Java SerialQuickSort: " + (end - start));
			totalSerialTime = (end-start);

			for (int i = 0; i < data.length; i++) {
				if (data[i] != data3[i]) {
					System.err.println("There's a difference is position " + i);
					System.exit(-1);
				}
			}
		}
		
		//TODO: Compute speedup
		System.out.println("Parallell speedup: " + (totalSerialTime/totalParallelTime));
	}
}
