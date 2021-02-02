package no.hiof.itf23019.synchronization.barrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import no.hiof.itf23019.synchronization.common.CommonTask;

public class BarrierTask implements Runnable {
	
	private CyclicBarrier barrier;
	
	public BarrierTask(CyclicBarrier barrier) {
		this.barrier=barrier;
	}

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName()+": Phase 1");
		CommonTask.doTask();
		try {
			barrier.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName()+": Phase 2");
		
	}
}
