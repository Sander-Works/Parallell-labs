package no.hiof.itf23019.concurrent_counter.task;

import java.util.concurrent.TimeUnit;

import no.hiof.itf23019.concurrent_counter.counter.DoubleCounter;
import no.hiof.itf23019.concurrent_counter.counter.SingleCounter;

public class DecreaseTask implements Runnable{

	private SingleCounter singleCounter;
	private DoubleCounter doubleCounter;
	
	public DecreaseTask(SingleCounter singleC, DoubleCounter doubleC) {
		this.singleCounter = singleC;
		this.doubleCounter = doubleC;
		
	}
	@Override
	public void run() {
		System.out.printf("Thread: %s is decreasing the counters%n", Thread.currentThread().getId());
		
		for(int i = 1; i <= 10; i++)
		{
			
			
			singleCounter.decrement();
			doubleCounter.decrementCounter1();
			doubleCounter.decrementCounter2();
			
			try {
				TimeUnit.MILLISECONDS.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		
		}
		
		System.out.printf("Thread: %s finished%n", Thread.currentThread().getId());
	}
	
	

}
