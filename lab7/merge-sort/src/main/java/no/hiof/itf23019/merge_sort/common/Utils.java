package no.hiof.itf23019.merge_sort.common;

import java.util.Random;

public class Utils {

	public static int[] generateArray(int size) {
		Random random = new Random(20200218);
		int[] array = new int[size];
		for (int i = 0; i < size; i++)
			array[i] = random.nextInt(size*10);

		return array;
	}
}
