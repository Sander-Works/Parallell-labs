package no.hiof.itf23019.concurrent_counter;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import no.hiof.itf23019.concurrent_counter.counter.DoubleCounter;
import no.hiof.itf23019.concurrent_counter.counter.SingleCounter;
import no.hiof.itf23019.concurrent_counter.task.DecreaseTask;
import no.hiof.itf23019.concurrent_counter.task.IncreaseTask;


public class Main {

	public static void main(String[] args) {
		
		SingleCounter singleCounter = new SingleCounter();
		DoubleCounter doubleCounter = new DoubleCounter();

		ThreadPoolExecutor executor=(ThreadPoolExecutor)Executors.newCachedThreadPool();

		//Create 10 increase tasks
		for (int i=0; i<10; i++) {
			executor.execute(new IncreaseTask(singleCounter, doubleCounter));
		}
		
		//Create 5 decrease tasks
		for (int i=0; i<5; i++) {
			executor.execute(new DecreaseTask(singleCounter, doubleCounter));
		}

		executor.shutdown();
		
		try {
			executor.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
 			e.printStackTrace();
		}
		
		System.out.println(singleCounter);
		System.out.println(doubleCounter);

	}

}
