package no.hiof.itf23019.quick_sort.serial;

public class SerialQuickSort {
	
	public void quickSort (int data[], int start, int end) {
		if(start < end)
		{
			
			//Partition the array to two half using the pivot
			//split is the index of the pivot to split the array to two half
			int split =  partition(data, start, end); 
			
			//TODO: Implement quick sort
			//Hint: Recursive call quickSort to the two half
			// [start, split) and [split + 1, end)
			
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
