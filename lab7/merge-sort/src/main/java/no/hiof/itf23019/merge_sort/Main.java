package no.hiof.itf23019.merge_sort;

import java.util.Arrays;

import no.hiof.itf23019.merge_sort.common.Utils;
import no.hiof.itf23019.merge_sort.parallel.ParallelMergeSort;
import no.hiof.itf23019.merge_sort.serial.SerialMergeSort;

public class Main {

	public static void main(String[] args) {

		
		int RUNS = 10;
		double totalParallelTime = 0;
		double totalSerialTime = 0;
		for (int j = 0; j < RUNS; j++) {
			
			System.out.println("RUN: #" + j);
			
			int n = 100_000;
			int data[] = Utils.generateArray(n);
			int data2[] = Arrays.copyOf(data, data.length);// copy(data);
			int data3[] = Arrays.copyOf(data, data.length);// copy(data);

			long start;
			long end;

			start = System.currentTimeMillis();
			Arrays.parallelSort(data);
			end = System.currentTimeMillis();
			System.out.println("Execution Time Java Arrays.parallelSort(): " + (end - start));

	
			ParallelMergeSort mySorter = new ParallelMergeSort();
			start = System.currentTimeMillis();
			mySorter.mergeSort(data2, 0, data2.length);
			end = System.currentTimeMillis();

			System.out.println("Execution Time Java ParallelMergeSort: " + (end - start));
			totalParallelTime = (end - start);

			for (int i = 0; i < data.length; i++) {
				if (data[i] != data2[i]) {
					System.err.println("There's a difference is position " + i);
					System.exit(-1);
				}
			}

			SerialMergeSort mySerialSorter = new SerialMergeSort();
			start = System.currentTimeMillis();
			mySerialSorter.mergeSort(data3, 0, data3.length);
			end = System.currentTimeMillis();

			System.out.println("Execution Time Java SerialMergeSort: " + (end - start));
			totalSerialTime = (end -start);
			for (int i = 0; i < data.length; i++) {
				if (data[i] != data3[i]) {
					System.err.println("There's a difference is position " + i);
					System.exit(-1);
				}
			}
		}
		//TODO: Compute speedup
		System.out.println("Parallel speedup: " + (totalSerialTime/totalParallelTime));
	}
}
