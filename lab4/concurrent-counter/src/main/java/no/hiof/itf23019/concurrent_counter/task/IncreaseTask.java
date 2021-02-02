package no.hiof.itf23019.concurrent_counter.task;

import java.util.concurrent.TimeUnit;

import no.hiof.itf23019.concurrent_counter.counter.DoubleCounter;
import no.hiof.itf23019.concurrent_counter.counter.SingleCounter;

public class IncreaseTask implements Runnable{

	private SingleCounter singleCounter;
	private DoubleCounter doubleCounter;
	
	public IncreaseTask(SingleCounter singleC, DoubleCounter doubleC) {
		this.singleCounter = singleC;
		this.doubleCounter = doubleC;
		
	}
	@Override
	public void run() {
		System.out.printf("Thread: %s is increasing the counters%n", Thread.currentThread().getId());
		
		for(int i = 1; i <= 10; i++)
		{
			singleCounter.increment();
			doubleCounter.incrementCounter1();
			doubleCounter.incrementCounter2();
			
			try {
				TimeUnit.MILLISECONDS.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		
		}
		
		System.out.printf("Thread: %s finished%n", Thread.currentThread().getId());
	}
	
	

}
