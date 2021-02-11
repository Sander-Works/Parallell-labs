package no.hiof.itf23019.synchronization.countdown;

import java.util.concurrent.CountDownLatch;

import no.hiof.itf23019.synchronization.common.CommonTask;

public class CountDownTask implements Runnable {

	private CountDownLatch countDownLatch;
	
	public CountDownTask(CountDownLatch countDownLatch) {
		this.countDownLatch=countDownLatch;
	}
	
	@Override
	public void run() {
		CommonTask.doTask();
		countDownLatch.countDown();
		
	}
}
