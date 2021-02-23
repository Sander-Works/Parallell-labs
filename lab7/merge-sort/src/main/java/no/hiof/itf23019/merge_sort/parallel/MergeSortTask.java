package no.hiof.itf23019.merge_sort.parallel;

import java.util.concurrent.RecursiveAction;

import no.hiof.itf23019.merge_sort.serial.SerialMergeSort;

public class MergeSortTask extends RecursiveAction {

	private static final long serialVersionUID = -5183127767439978300L;
	private int[] data;
	private int start, end;
	private int middle;

	public MergeSortTask(int[] data, int start, int end) {
	

		this.data = data;
		this.start = start;
		this.end = end;
	}

	@Override
	public void compute() {
		if (end - start >= 1024) {
			middle = (end + start) >>> 1;  //divided by 2
			
			
			//TODO: Create the two tasks for two half, invokeAll them, and then merge.
			
			
			//Merging
			merge();
			
		} else {
			new SerialMergeSort().mergeSort(data, start, end);
			
		}
	}


	public void merge() {

		if (middle == 0) {
			return;
		}
		int length = end - start + 1;
		int tmp[] = new int[length];

		int i, j, index;
		i = start;
		j = middle;
		index = 0;

		while ((i < middle) && (j < end)) {
			if (data[i] <= data[j]) {
				tmp[index] = data[i];
				i++;
			} else {
				tmp[index] = data[j];
				j++;
			}
			index++;
		}

		while (i < middle) {
			tmp[index] = data[i];
			i++;
			index++;
		}

		while (j < end) {
			tmp[index] = data[j];
			j++;
			index++;
		}

		for (index = 0; index < (end - start); index++) {
			data[index + start] = tmp[index];
		}

	}

}
