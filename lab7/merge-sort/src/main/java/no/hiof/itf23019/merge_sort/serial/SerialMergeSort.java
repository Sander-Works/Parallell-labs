package no.hiof.itf23019.merge_sort.serial;

public class SerialMergeSort {

	public void mergeSort (int data[], int start, int end) {
		if (end-start < 2) { 
			return;
		}
		int middle= (end+start)>>>1; // the middle index to divide the array
		
		//TODO: Implement the serial meresort.
		//Hint: Recursive calls to two half:
		//[start, middle) and [middle, end)
		//and then merge.
	}

	private void merge(int[] data, int start, int middle, int end) {
		int length=end-start+1;
		int[] tmp=new int[length];
		
		int i, j, index;
		i=start;
		j=middle;
		index=0;
		
		while ((i<middle) && (j<end)) {
			if (data[i] <= data[j]) {
				tmp[index]=data[i];
				i++;
			} else {
				tmp[index]=data[j];
				j++;
			}
			index++;
		}
		
		while (i<middle) {
			tmp[index]=data[i];
			i++;
			index++;
		}

		while (j<end) {
			tmp[index]=data[j];
			j++;
			index++;
		}
		
		for (index=0; index < (end-start); index++) {
			data[index+start]=tmp[index];
		}
	}
}
