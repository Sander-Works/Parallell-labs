package no.hiof.itf23019.completable;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;

public class ClosestDistanceSelector implements BiFunction<List<Long>, Long, Long> {



	@Override
	public Long apply(List<Long> t, Long pivlot) {

		//TODO: implement the function that return the number in the list t that is closed to the pivot u
		//Hint: look at the implementation of step1Future in the Main


		System.out.printf("%s: Step 1: Start\n", Thread.currentThread().getName());
		long selected = 0;
		long selectedDistance = Long.MAX_VALUE;
		long distance;
		for (Long number : t) {
			distance = Math.abs(number - pivlot);
			if (distance < selectedDistance) {
				selected = number;
				selectedDistance = distance;
			}
		}
		System.out.printf("%s: Step 1: Result - %d\n", Thread.currentThread().getName(), selected);
		return selected;
	}
}
