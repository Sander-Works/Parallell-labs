package no.hiof.itf23019.completable;

import java.util.function.Consumer;

public class NumberPrinter implements Consumer<Long>{

	@Override
	public void accept(Long t) {
		System.out.printf("%s : NumberPrinter : Start\n",Thread.currentThread().getName());
		
		System.out.printf("%s: Step 2: Result - %d\n", Thread.currentThread().getName(), t);
		
		System.out.printf("%s : NumberPrinter : End\n",Thread.currentThread().getName());
	}
	

}
