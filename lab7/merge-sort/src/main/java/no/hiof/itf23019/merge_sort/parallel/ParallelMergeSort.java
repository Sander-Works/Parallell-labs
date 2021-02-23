package no.hiof.itf23019.merge_sort.parallel;

import java.util.concurrent.ForkJoinPool;

public class ParallelMergeSort {

	public void mergeSort (int data[], int start, int end) {
		
		MergeSortTask task=new MergeSortTask(data, start, end);
		ForkJoinPool.commonPool().invoke(task);

	}
}
