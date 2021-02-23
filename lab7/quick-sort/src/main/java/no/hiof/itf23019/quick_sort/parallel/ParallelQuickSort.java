package no.hiof.itf23019.quick_sort.parallel;

import java.util.concurrent.ForkJoinPool;

public class ParallelQuickSort {

	public void quickSort (int data[], int start, int end) {
		QuickSortTask task=new QuickSortTask(data, start, end);
		ForkJoinPool.commonPool().invoke(task);
	}
}
