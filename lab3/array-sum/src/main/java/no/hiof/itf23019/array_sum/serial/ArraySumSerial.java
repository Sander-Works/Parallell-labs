package no.hiof.itf23019.array_sum.serial;

public class ArraySumSerial {
	
	public int sequentialArraySum(int[] array) {

		System.out.println("Sequential Running ....");
		int s = 0;
		
		for (int i = 0; i < array.length; i++)
			s += array[i];
		
		return s;
	}

}