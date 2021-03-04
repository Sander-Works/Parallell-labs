package no.hiof.itf23019.quick_sort.parallel;

import java.util.concurrent.RecursiveAction;

import no.hiof.itf23019.quick_sort.serial.SerialQuickSort;


public class QuickSortTask extends RecursiveAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4482265599948830720L;

	private int[] data;
	private int start, end;
	
	public QuickSortTask(int[] data, int start, int end) {
		

		this.data = data;
		this.start = start;
		this.end = end;
	}
	
	
	@Override
	protected void compute() {
		if (end - start >= 1024) {
			
			int split = partition(data, start, end);
			
			//TODO: Create two tasks for two half
			// [start, split) and [split + 1, end)
			// and then invokeAll them
			QuickSortTask left = new QuickSortTask(data, start, split);
			QuickSortTask right = new QuickSortTask(data, split+1, end);
			invokeAll(left, right);
			
		} else {
			new SerialQuickSort().quickSort(data, start, end);
			
		}
	}


	private int partition(int data[], int start, int end) 
	{
		int pivot = data[start];
		int split = start;
		for(int i = start + 1; i < end; i++)
		{
			if(data[i] <= pivot)
			{
				split++;
				swap(data, i, split);
			}
		}
		
		swap(data, start, split);
		
		return split;
	}
	
	private void swap(int data[], int index1, int index2)
	{
		int temp = data[index1];
		data[index1] = data[index2];
		data[index2] = temp;
	}
	
	
	
}
