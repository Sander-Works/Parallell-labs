package no.hiof.itf23019.completable;

import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class SeedGenerator implements Supplier<Integer> {

	
	public SeedGenerator () {
	}
	
	@Override
	public Integer get() {

		System.out.printf("SeedGenerator: Generating seed...\n");
		// Wait 5 seconds
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		int seed=(int) Math.rint(Math.random() * 10);
		
		System.out.printf("SeedGenerator: Seed generated: %d\n",seed);
		
		return seed;
		
	}


}
