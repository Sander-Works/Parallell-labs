package no.hiof.itf23019.completable;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletetableMain {

	public static void main(String[] args) {

		System.out.printf("Main: Start\n");

		SeedGenerator seedTask = new SeedGenerator();
		CompletableFuture<Integer> seedFuture = CompletableFuture.supplyAsync(seedTask);

		System.out.printf("Main: Getting the seed\n");
		int seed = 0;
		try {
			seed = seedFuture.get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		System.out.printf("Main: The seed is: %d\n", seed);

		System.out.printf("Main: Launching the list of numbers generator\n");
		NumberListGenerator task = new NumberListGenerator(seed);
		CompletableFuture<List<Long>> startFuture = CompletableFuture.supplyAsync(task);

		System.out.printf("Main: Launching step 1\n");

		// TODO: Rewrite the below step1Future by using the ClosestDistanceSelector and
		// the pivot (uncomment the line below)
		// Hint: use thenCombineAsync method
		
		CompletableFuture<Long> pivot = CompletableFuture.completedFuture((long)1000);
		ClosestDistanceSelector selector = new ClosestDistanceSelector();

		CompletableFuture<Long> step1Future = startFuture.thenCombineAsync(pivot, selector);
		//CompletableFuture<Long> step1Future = CompletableFuture.completedFuture(g)


	/*	// Return the number that is closest to the pivot
		CompletableFuture<Long> step1Future = startFuture.thenApplyAsync(list -> {
			System.out.printf("%s: Step 1: Start\n", Thread.currentThread().getName());
			long selected = 0;
			long selectedDistance = Long.MAX_VALUE;
			long distance;
			long pivot = 1000;
			for (Long number : list) {
				distance = Math.abs(number - pivot);
				if (distance < selectedDistance) {
					selected = number;
					selectedDistance = distance;
				}
			}
			System.out.printf("%s: Step 1: Result - %d\n", Thread.currentThread().getName(), selected);
			return selected;
		});*/

		// Return the maximum number in the list
		System.out.printf("Main: Launching step 2\n");
		CompletableFuture<Long> step2Future = startFuture
				.thenApplyAsync(list -> list.stream().max(Long::compare).get());

		// TODO: Rewrite the below write2Future by using the NumberPrinter consumer.

		NumberPrinter numberPrinter = new NumberPrinter();
		CompletableFuture<Void> write2Future = step2Future.thenAccept(selected -> {
			numberPrinter.accept(selected);
		});

		/*// Write the result of previous step2Future
		CompletableFuture<Void> write2Future = step2Future.thenAccept(selected -> {
			System.out.printf("%s: Step 2: Result - %d\n", Thread.currentThread().getName(), selected);
		});*/

		System.out.printf("Main: Launching step 3\n");
		NumberSelector numberSelector = new NumberSelector();
		CompletableFuture<Long> step3Future = startFuture.thenApplyAsync(numberSelector);

		System.out.printf("Main: Waiting for the end of the three steps\n");
		CompletableFuture<Void> waitFuture = CompletableFuture.allOf(step1Future, write2Future, step3Future);
		CompletableFuture<Void> finalFuture = waitFuture.thenAcceptAsync((param) -> {
			System.out.printf("Main: The CompletableFuture example has been completed.");
		});

		finalFuture.join();

	}

}
