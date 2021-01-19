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
		if (endIndex - startIndex <= threshold) {
			for (int i = startIndex; i < endIndex; i++) {
				this.sum += input[i];
			}
		} else {
			int mid = (startIndex + endIndex) / 2;
			ArraySumTask left = new ArraySumTask(input, startIndex, mid);
			ArraySumTask right = new ArraySumTask(input, mid, endIndex);


			invokeAll(left, right); 
			
			//OR
			//right.fork();
			//left.compute();
			//right.join();
			
			sum = left.getSum() + right.getSum();
		}
	}

	public int getSum() {
		return sum;
	}

}
