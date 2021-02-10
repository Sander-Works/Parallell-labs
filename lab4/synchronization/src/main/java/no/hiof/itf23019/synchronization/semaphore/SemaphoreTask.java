package no.hiof.itf23019.synchronization.semaphore;

import java.util.concurrent.Semaphore;

import no.hiof.itf23019.synchronization.common.CommonTask;

public class SemaphoreTask implements Runnable{

	private Semaphore semaphore;
	
	public SemaphoreTask(Semaphore semaphore) {
		this.semaphore=semaphore;
	}
	
	@Override
	public void run() {

		try {
			semaphore.acquire();
			CommonTask.doTask();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaphore.release();
		}
	}
}
