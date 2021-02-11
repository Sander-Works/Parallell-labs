package no.hiof.itf23019.array_sum.parallel;

import java.util.concurrent.RecursiveAction;

public class ArraySumTask extends RecursiveAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1621644740178701763L;
	
	private int[] input;
	private int startIndex, endIndex;
	private int sum;
	private int threshold = 100_000;

	public ArraySumTask(int[] input, int startIndex, int endIndex) {
		this.input = input;
		this.startIndex = startIndex;
		this.endIndex = endIndex;
	}

	protected void compute() {
		
		//If the size of the task is smaller than threshold, compute the sum directly
		if (endIndex - startIndex <= threshold) {
			for (int i = startIndex; i < endIndex; i++) {
				this.sum += input[i];
			}
		} 
		else {
			
			ArraySumTask left = null, right =null;
			
			//TODO:
			//Compute the indices for the two sub tasks.
			//Initialize the two sub tasks: left and right
			//start the tasks and wait for them to finish

			
			
			//Compute the sum of the two sub-tasks.
			sum = left.getSum() + right.getSum();
		}
	}

	public int getSum() {
		return sum;
	}

}
